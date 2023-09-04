package coffee.shop.converter;

import coffee.shop.dto.response.UserRegisterResponse;
import coffee.shop.dto.response.UserUpdateResponse;
import coffee.shop.entity.UserInfo;
import lombok.experimental.UtilityClass;

import java.util.Objects;

@UtilityClass
public class UserInfoConverter {
    public static UserRegisterResponse convertRegisterUser(UserInfo userInfo) {
        if (Objects.isNull(userInfo)) {
            return UserRegisterResponse.builder()
                    .build();
        }
        return UserRegisterResponse.builder()
                .id(userInfo.getId())
                .username(userInfo.getUsername())
                .build();
    }

    public static UserUpdateResponse convertUpdateUser(UserInfo userInfo) {
        if (Objects.isNull(userInfo)) {
            return UserUpdateResponse.builder()
                    .build();
        }
        return UserUpdateResponse.builder()
                .username(userInfo.getUsername())
                .email(userInfo.getEmail())
                .fullName(userInfo.getFullName())
                .phoneNumber(userInfo.getPhoneNumber())
                .address(userInfo.getAddress())
                .build();
    }
}
