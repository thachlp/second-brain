package coffee.shop.converter;

import coffee.shop.dto.CategoryDTO;
import coffee.shop.entity.Category;
import lombok.experimental.UtilityClass;

import java.util.Objects;

@UtilityClass
public class CategoryConverter {
    public static CategoryDTO convert(Category category) {
        if (Objects.isNull(category)) {
            return CategoryDTO.builder()
                    .build();
        }
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
