package coffee.shop.service;

import coffee.shop.converter.UserInfoConverter;
import coffee.shop.dto.request.UserRegistrationRequestDto;
import coffee.shop.dto.response.UserRegistrationResponseDto;
import coffee.shop.entity.UserInfo;
import coffee.shop.model.exception.UsernameAlreadyExistsException;
import coffee.shop.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInfoService {
    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;

    public UserRegistrationResponseDto registerNewUserAccount(UserRegistrationRequestDto userRegistrationDto) {
        final String username = userRegistrationDto.getUsername();
        if(userInfoRepository.findByUsername(username).isPresent()) {
            throw new UsernameAlreadyExistsException("Username " + username + " already exists");
        }

        final UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
        return UserInfoConverter.convert(userInfoRepository.save(userInfo));
    }
}
