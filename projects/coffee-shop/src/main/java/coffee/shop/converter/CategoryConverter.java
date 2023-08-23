package coffee.shop.converter;

import coffee.shop.dto.response.CategoryResponseDto;
import coffee.shop.entity.Category;
import lombok.experimental.UtilityClass;

import java.util.Objects;

@UtilityClass
public class CategoryConverter {
    public static CategoryResponseDto convert(Category category) {
        if (Objects.isNull(category)) {
            return CategoryResponseDto.builder()
                    .build();
        }
        return CategoryResponseDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
