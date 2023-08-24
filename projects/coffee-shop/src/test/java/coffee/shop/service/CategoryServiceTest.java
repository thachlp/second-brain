package coffee.shop.service;

import coffee.shop.constant.MessageConstants;
import coffee.shop.dto.response.CategoryResponseDto;
import coffee.shop.entity.Category;
import coffee.shop.model.exception.NotFoundException;
import coffee.shop.model.request.CategoryRequest;
import coffee.shop.model.response.CommonDataPageResponse;
import coffee.shop.model.response.CommonDataResponse;
import coffee.shop.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {
    @InjectMocks
    private CategoryService categoryService;
    @Mock
    private CategoryRepository categoryRepository;

    private static Category category;

    @BeforeAll
    static void init() {
        category = Category.builder()
                .id(1L)
                .name("Coffee")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    @Test
    void addCategory() {
        when(categoryRepository.save(any())).thenReturn(any());
        final CommonDataResponse dataResponse = categoryService.addCategory(new CategoryRequest("Coffee"));
        assertThat(dataResponse.isResult()).isTrue();
    }

    @Test
    void addEmptyNameCategory() {
        final CategoryRequest categoryRequest = new CategoryRequest();
        final Exception exception = assertThrows(IllegalArgumentException.class, () ->
                categoryService.addCategory(categoryRequest));
        assertEquals(MessageConstants.CATEGORY_NAME_INVALID, exception.getMessage());
    }

    @Test
    void getCategories() {
        final List<Category> categories = List.of(category);
        final Pageable pageable = PageRequest.of(0, 10);
        final Page<Category> pageCategories = new PageImpl<>(categories, pageable, 1);
        when(categoryRepository.findAll(PageRequest.of(0, 10))).thenReturn(pageCategories);
        final CommonDataPageResponse dataResponse = categoryService.getCategories(0, 10);
        assertThat(dataResponse.getTotalElements()).isOne();
    }

    @Test
    void getCategoryDetail() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        final CommonDataResponse categoryResponse = categoryService.getCategoryDetail(1L);
        assertThat(categoryResponse.isResult()).isTrue();
        assertThat(categoryResponse.getData()).isNotNull().isEqualTo(CategoryResponseDto.builder().id(1L).name("Coffee").build());
    }

    @Test
    void getCategoryDetailNotFound() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());
        final Exception exception = assertThrows(NotFoundException.class, () ->
                categoryService.getCategoryDetail(1L));
        assertEquals(MessageConstants.CATEGORY_NOT_FOUND, exception.getMessage());
    }

    @Test
    void updateCategory() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        final CommonDataResponse categoryResponse = categoryService.updateCategory(1L, new CategoryRequest("Tea"));
        verify(categoryRepository, times(1)).save(category);
        assertThat(categoryResponse.isResult()).isTrue();
    }

    @Test
    void deleteCategory() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        final CommonDataResponse categoryResponse = categoryService.deleteCategory(1L);
        verify(categoryRepository, times(1)).delete(category);
        assertThat(categoryResponse.isResult()).isTrue();
    }

}
