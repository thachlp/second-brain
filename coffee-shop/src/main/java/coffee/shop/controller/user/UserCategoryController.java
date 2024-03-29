package coffee.shop.controller.user;

import coffee.shop.dto.response.CategoryResponse;
import coffee.shop.dto.response.PageResponse;
import coffee.shop.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "UserCategory", description = "Operations for user using categories")
@RestController
@RequestMapping("/user/categories")
@RequiredArgsConstructor
public class UserCategoryController {
    private final CategoryService categoryService;

    @Operation(summary = "Get list of categories")
    @GetMapping
    ResponseEntity<PageResponse> getCategories(@RequestParam(required= false, defaultValue = "0") Integer pageNumber,
                                               @RequestParam(required= false, defaultValue = "10") Integer pageSize) {
        return ResponseEntity.ok(categoryService.getCategories(pageNumber, pageSize));
    }

    @Operation(summary = "Get detail of category")
    @GetMapping("/{id}")
    ResponseEntity<CategoryResponse> getCategoryDetail(@PathVariable("id") Long categoryId) {
        return ResponseEntity.ok(categoryService.getCategoryDetail(categoryId));
    }
}
