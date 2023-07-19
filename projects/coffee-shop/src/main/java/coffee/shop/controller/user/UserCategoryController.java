package coffee.shop.controller.user;

import coffee.shop.model.response.CommonDataPageResponse;
import coffee.shop.model.response.CommonDataResponse;
import coffee.shop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/categories")
@RequiredArgsConstructor
public class UserCategoryController {
    private final CategoryService categoryService;

    @GetMapping
    ResponseEntity<CommonDataPageResponse> getCategories(@RequestParam(required= false, defaultValue = "0") Integer pageNumber,
                                                         @RequestParam(required= false, defaultValue = "10") Integer pageSize) {
        return ResponseEntity.ok(categoryService.getCategories(pageNumber, pageSize));
    }

    @GetMapping("/{id}")
    ResponseEntity<CommonDataResponse> getCategoryDetail(@PathVariable("id") Long categoryId) {
        return ResponseEntity.ok(categoryService.getCategoryDetail(categoryId));
    }
}
