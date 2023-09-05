package coffee.shop.service;

import coffee.shop.converter.UserInfoConverter;
import coffee.shop.dto.request.UserRegistrationRequest;
import coffee.shop.dto.request.UserUpdateRequest;
import coffee.shop.entity.UserInfo;
import coffee.shop.model.exception.NotFoundException;
import coffee.shop.model.exception.UsernameAlreadyExistsException;
import coffee.shop.model.response.CommonDataResponse;
import coffee.shop.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserInfoService {
    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;
    public CommonDataResponse registerNewUserAccount(UserRegistrationRequest userRegistrationRequest) {
        final String username = userRegistrationRequest.getUsername();
        if(userInfoRepository.findByUsername(username).isPresent()) {
            throw new UsernameAlreadyExistsException("Username " + username + " already exists");
        }

        final UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setPassword(passwordEncoder.encode(userRegistrationRequest.getPassword()));
        return CommonDataResponse.builder()
                .data(UserInfoConverter.convertRegisterUser(userInfoRepository.save(userInfo)))
                .build();
    }

    public CommonDataResponse updateUserInfo(UserUpdateRequest userUpdateRequest) {
        final Optional<UserInfo> optionalUserInfo = userInfoRepository.findByUsername(userUpdateRequest.getUsername());
        if(optionalUserInfo.isEmpty()) {
            throw new NotFoundException("User with username " + userUpdateRequest.getUsername() + " not found");
        }
        final UserInfo userInfo = optionalUserInfo.get();
        userInfo.setEmail(userUpdateRequest.getEmail());
        userInfo.setFullName(userUpdateRequest.getFullName());
        userInfo.setPhoneNumber(userUpdateRequest.getPhoneNumber());
        userInfo.setAddress(userUpdateRequest.getAddress());
        return CommonDataResponse.builder()
                .data(UserInfoConverter.convertUpdateUser(userInfoRepository.save(userInfo)))
                .build();
    }
}
