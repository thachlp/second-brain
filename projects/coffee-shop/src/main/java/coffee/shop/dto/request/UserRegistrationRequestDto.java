package coffee.shop.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegistrationRequestDto {
    private String username;
    private String password;
}
