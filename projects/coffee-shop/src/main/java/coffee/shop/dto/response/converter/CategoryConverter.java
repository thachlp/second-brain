package coffee.shop.dto.response.converter;

import coffee.shop.dto.response.CategoryResponse;
import coffee.shop.model.entity.Category;
import lombok.experimental.UtilityClass;

import java.util.Objects;

@UtilityClass
public class CategoryConverter {
    public static CategoryResponse convert(Category category) {
        if (Objects.isNull(category)) {
            return CategoryResponse.builder()
                    .build();
        }
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
