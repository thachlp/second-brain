package coffee.shop.converter;

import coffee.shop.dto.CategoryDTO;
import coffee.shop.entity.Category;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryConverter {
    public static CategoryDTO convert(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
