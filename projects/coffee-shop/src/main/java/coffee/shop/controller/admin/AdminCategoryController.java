package coffee.shop.controller.admin;

import coffee.shop.model.request.CategoryRequest;
import coffee.shop.model.response.CommonDataResponse;
import coffee.shop.service.CategoryService;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/categories")
public class AdminCategoryController {
    private final CategoryService categoryService;

    @PostMapping
    ResponseEntity<CommonDataResponse> newCategory(@RequestBody CategoryRequest categoryRequest) {
        return new ResponseEntity<>(categoryService.addCategory(categoryRequest),
                HttpStatus.CREATED);
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
