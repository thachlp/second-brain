package coffee.shop.service;

import coffee.shop.constant.MessageConstants;
import coffee.shop.model.request.CategoryRequest;
import coffee.shop.model.response.CommonDataResponse;
import coffee.shop.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {
    @InjectMocks
    private CategoryService categoryService;
    @Mock
    private CategoryRepository categoryRepository;

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
        assertEquals(MessageConstants.CATEGORY_NAME_INVALID, exception.getMessage());    }

}
