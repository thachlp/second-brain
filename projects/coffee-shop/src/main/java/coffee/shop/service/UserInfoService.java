package coffee.shop.service;

import coffee.shop.converter.CategoryConverter;
import coffee.shop.converter.UserInfoConverter;
import coffee.shop.dto.request.UserRegistrationRequest;
import coffee.shop.dto.response.UserRegistrationResponse;
import coffee.shop.entity.UserInfo;
import coffee.shop.model.exception.UsernameAlreadyExistsException;
import coffee.shop.model.response.CommonDataResponse;
import coffee.shop.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInfoService {
    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;

    public CommonDataResponse registerNewUserAccount(UserRegistrationRequest userRegistrationDto) {
        final String username = userRegistrationDto.getUsername();
        if(userInfoRepository.findByUsername(username).isPresent()) {
            throw new UsernameAlreadyExistsException("Username " + username + " already exists");
        }

        final UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
        return CommonDataResponse.builder()
                .data(UserInfoConverter.convert(userInfoRepository.save(userInfo)))
                .build();
    }
}
