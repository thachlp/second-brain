package coffee.shop.service;

import coffee.shop.entity.Category;
import coffee.shop.model.exception.NotFoundException;
import coffee.shop.model.request.CategoryRequest;
import coffee.shop.model.response.CommonDataResponse;
import coffee.shop.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CommonDataResponse addCategory(CategoryRequest categoryRequest) {
        final var name = categoryRequest.getName();
        validateName(name);
        final var category = Category.builder()
                .name(name)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        final var data = categoryRepository.save(category);
        return CommonDataResponse.builder()
                .result(true)
                .data(data)
                .build();
    }

    public CommonDataResponse getCategoryDetail(Long categoryId) {
        final Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Category not found!"));
        return CommonDataResponse.builder()
                .result(true)
                .data(category)
                .build();
    }

    public CommonDataResponse updateCategory(Long categoryId, CategoryRequest categoryRequest) {
        final var name = categoryRequest.getName();
        validateName(name);
        final Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Category not found!"));
        category.setName(name);
        category.setUpdatedAt(LocalDateTime.now());
        final var data = categoryRepository.save(category);
        return CommonDataResponse.builder()
                .result(true)
                .data(data)
                .build();
    }

    public CommonDataResponse deleteCategory(Long categoryId) {
        final Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Category not found!"));
        categoryRepository.delete(category);
        return CommonDataResponse.builder()
                .result(true)
                .data("Delete success")
                .build();
    }

    private static void validateName(String name) {
        if (name == null || name.isEmpty()) {
            log.warn("Category name is invalid");
            throw new IllegalArgumentException("Category name is invalid");
        }
    }
}
