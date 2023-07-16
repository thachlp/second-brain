package coffee.shop.controller;

import coffee.shop.model.request.CategoryRequest;
import coffee.shop.model.response.CommonDataResponse;
import coffee.shop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/categories")
    ResponseEntity<CommonDataResponse> newCategory(@RequestBody CategoryRequest categoryRequest) {
        return ResponseEntity.ok(categoryService.addCategory(categoryRequest));
    }

    @GetMapping("/categories/{id}")
    ResponseEntity<CommonDataResponse> getCategoryDetail(@PathVariable("id") Long categoryId) {
        return ResponseEntity.ok(categoryService.getCategoryDetail(categoryId));
    }

    @PutMapping("/categories/{id}")
    ResponseEntity<CommonDataResponse> updateCategory(@PathVariable("id") Long categoryId,
                                                      @RequestBody CategoryRequest categoryRequest) {
        return ResponseEntity.ok(categoryService.updateCategory(categoryId, categoryRequest));
    }

    @DeleteMapping("/categories/{id}")
    ResponseEntity<CommonDataResponse> deleteCategory(@PathVariable("id") Long categoryId) {
        return ResponseEntity.ok(categoryService.deleteCategory(categoryId));
    }
}
