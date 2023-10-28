package coffee.shop.controller.admin;

import coffee.shop.dto.request.CategoryRequest;
import coffee.shop.dto.response.CategoryResponse;
import coffee.shop.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Tag(name = "AdminCategory", description = "Operations for admin managing categories")
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/categories")
public class AdminCategoryController {
    private final CategoryService categoryService;

    @Operation(summary = "Add a new category")
    @PostMapping
    ResponseEntity<CategoryResponse> newCategory(@RequestBody CategoryRequest categoryRequest) {
        return new ResponseEntity<>(categoryService.addCategory(categoryRequest),
                HttpStatus.CREATED);
    }

    @Operation(summary = "Update a category")
    @PutMapping("/{id}")
    ResponseEntity<CategoryResponse> updateCategory(@PathVariable("id") Long categoryId,
                                                    @RequestBody CategoryRequest categoryRequest) {
        return ResponseEntity.ok(categoryService.updateCategory(categoryId, categoryRequest));
    }

    @Operation(summary = "Delete a category")
    @DeleteMapping("/{id}")
    void deleteCategory(@PathVariable("id") Long categoryId) {
        categoryService.deleteCategory(categoryId);
    }
}
