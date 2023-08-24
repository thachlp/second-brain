package coffee.shop.converter;

import coffee.shop.dto.response.UserRegistrationResponseDto;
import coffee.shop.entity.UserInfo;
import lombok.experimental.UtilityClass;

import java.util.Objects;

@UtilityClass
public class UserInfoConverter {
    public static UserRegistrationResponseDto convert(UserInfo userInfo) {
        if (Objects.isNull(userInfo)) {
            return UserRegistrationResponseDto.builder()
                    .build();
        }
        return UserRegistrationResponseDto.builder()
                .id(userInfo.getId())
                .username(userInfo.getUsername())
                .build();
    }
}
