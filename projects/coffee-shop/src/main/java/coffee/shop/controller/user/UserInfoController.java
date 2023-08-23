package coffee.shop.controller.user;

import coffee.shop.dto.request.UserRegistrationRequestDto;
import coffee.shop.dto.response.UserRegistrationResponseDto;
import coffee.shop.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserInfoController {
    private final UserInfoService userInfoService;

    @PostMapping("/register")
    public ResponseEntity<UserRegistrationResponseDto> registerUserAccount(@RequestBody UserRegistrationRequestDto userRegistrationDto) {
        final UserRegistrationResponseDto registeredUser = userInfoService.registerNewUserAccount(userRegistrationDto);
        return ResponseEntity.ok(registeredUser);
    }
}
