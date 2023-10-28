package coffee.shop.service;

import coffee.shop.dto.response.converter.UserInfoConverter;
import coffee.shop.dto.request.UserRegisterRequest;
import coffee.shop.dto.request.UserUpdateRequest;
import coffee.shop.dto.response.UserRegisterResponse;
import coffee.shop.dto.response.UserUpdateResponse;
import coffee.shop.model.entity.UserInfo;
import coffee.shop.model.exception.ResourceNotFoundException;
import coffee.shop.model.exception.UsernameAlreadyExistsException;
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
    public UserRegisterResponse registerNewUserAccount(UserRegisterRequest userRegistrationRequest) {
        final String username = userRegistrationRequest.getUsername();
        if(userInfoRepository.findByUsername(username).isPresent()) {
            throw new UsernameAlreadyExistsException("Username " + username + " already exists");
        }

        final UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setPassword(passwordEncoder.encode(userRegistrationRequest.getPassword()));
        return UserInfoConverter.convertRegisterUser(userInfoRepository.save(userInfo));
    }

    public UserUpdateResponse updateUserInfo(UserUpdateRequest userUpdateRequest) {
        final Optional<UserInfo> optionalUserInfo = userInfoRepository.findByUsername(userUpdateRequest.getUsername());
        if(optionalUserInfo.isEmpty()) {
            throw new ResourceNotFoundException("User with username " + userUpdateRequest.getUsername() + " not found");
        }
        final UserInfo userInfo = optionalUserInfo.get();
        userInfo.setEmail(userUpdateRequest.getEmail());
        userInfo.setFullName(userUpdateRequest.getFullName());
        userInfo.setPhoneNumber(userUpdateRequest.getPhoneNumber());
        userInfo.setAddress(userUpdateRequest.getAddress());
        return UserInfoConverter.convertUpdateUser(userInfoRepository.save(userInfo));
    }
}
