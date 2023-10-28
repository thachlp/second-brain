package coffee.shop.service;

import coffee.shop.model.constant.MessageConstants;
import coffee.shop.dto.response.converter.CategoryConverter;
import coffee.shop.dto.response.CategoryResponse;
import coffee.shop.model.entity.Category;
import coffee.shop.model.exception.ResourceNotFoundException;
import coffee.shop.dto.request.CategoryRequest;
import coffee.shop.dto.response.PageResponse;
import coffee.shop.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static coffee.shop.service.CategoryValidator.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryResponse addCategory(CategoryRequest categoryRequest) {
        final var name = categoryRequest.getName();
        validateName(name);
        final var category = Category.builder()
                .name(name)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        final var data = categoryRepository.save(category);
        return CategoryConverter.convert(data);
    }

    public PageResponse<CategoryResponse> getCategories(Integer pageNumber, Integer pageSize) {
        final Pageable pageable = PageRequest.of(pageNumber, pageSize);
        final Page<Category> pageCategories = categoryRepository.findAll(pageable);
        final List<CategoryResponse> categoryResponses = pageCategories.get()
                .map(CategoryConverter::convert)
                .collect(Collectors.toList());
        return PageResponse.<CategoryResponse>builder()
                .data(categoryResponses)
                .pageSize(pageCategories.getSize())
                .pageNumber(pageCategories.getNumber())
                .totalPages(pageCategories.getTotalPages())
                .totalElements(pageCategories.getTotalElements())
                .build();
    }

    public CategoryResponse getCategoryDetail(Long categoryId) {
        final Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(MessageConstants.CATEGORY_NOT_FOUND, categoryId)));
        return CategoryConverter.convert(category);
    }

    public CategoryResponse updateCategory(Long categoryId, CategoryRequest categoryRequest) {
        final var name = categoryRequest.getName();
        validateName(name);
        final Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(MessageConstants.CATEGORY_NOT_FOUND, categoryId)));
        category.setName(name);
        category.setUpdatedAt(LocalDateTime.now());
        final var data = categoryRepository.save(category);
        return CategoryConverter.convert(data);
    }

    public void deleteCategory(Long categoryId) {
        final Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(MessageConstants.CATEGORY_NOT_FOUND, categoryId)));
        categoryRepository.delete(category);
    }

}
