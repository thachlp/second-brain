package coffee.shop.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegisterResponse {
    private Long id;
    private String username;
}
