package coffee.shop.converter;

import coffee.shop.dto.response.UserRegistrationResponse;
import coffee.shop.entity.UserInfo;
import lombok.experimental.UtilityClass;

import java.util.Objects;

@UtilityClass
public class UserInfoConverter {
    public static UserRegistrationResponse convert(UserInfo userInfo) {
        if (Objects.isNull(userInfo)) {
            return UserRegistrationResponse.builder()
                    .build();
        }
        return UserRegistrationResponse.builder()
                .id(userInfo.getId())
                .username(userInfo.getUsername())
                .build();
    }
}
