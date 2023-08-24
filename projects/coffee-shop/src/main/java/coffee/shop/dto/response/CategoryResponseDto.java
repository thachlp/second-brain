package coffee.shop.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryResponseDto {
    private long id;
    private String name;
}
