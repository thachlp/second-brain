package coffee.shop.controller.user;

import coffee.shop.dto.request.UserRegistrationRequest;
import coffee.shop.dto.response.UserRegistrationResponse;
import coffee.shop.model.response.CommonDataResponse;
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
    public ResponseEntity<CommonDataResponse> registerUserAccount(@RequestBody UserRegistrationRequest userRegistrationDto) {
        final CommonDataResponse registeredResponse = userInfoService.registerNewUserAccount(userRegistrationDto);
        return ResponseEntity.ok(registeredResponse);
    }
}
