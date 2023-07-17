package coffee.shop.controller;

import coffee.shop.model.request.CategoryRequest;
import coffee.shop.model.response.CommonDataPageResponse;
import coffee.shop.model.response.CommonDataResponse;
import coffee.shop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    ResponseEntity<CommonDataResponse> newCategory(@RequestBody CategoryRequest categoryRequest) {
        return ResponseEntity.ok(categoryService.addCategory(categoryRequest));
    }

    @GetMapping
    ResponseEntity<CommonDataPageResponse> getCategories(@RequestParam(required= false, defaultValue = "1") Integer page,
                                                         @RequestParam(required= false, defaultValue = "15") Integer size) {
        return ResponseEntity.ok(categoryService.getCategories(page, size));
    }

    @GetMapping("/{id}")
    ResponseEntity<CommonDataResponse> getCategoryDetail(@PathVariable("id") Long categoryId) {
        return ResponseEntity.ok(categoryService.getCategoryDetail(categoryId));
    }

    @PutMapping("/{id}")
    ResponseEntity<CommonDataResponse> updateCategory(@PathVariable("id") Long categoryId,
                                                      @RequestBody CategoryRequest categoryRequest) {
        return ResponseEntity.ok(categoryService.updateCategory(categoryId, categoryRequest));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<CommonDataResponse> deleteCategory(@PathVariable("id") Long categoryId) {
        return ResponseEntity.ok(categoryService.deleteCategory(categoryId));
    }
}
