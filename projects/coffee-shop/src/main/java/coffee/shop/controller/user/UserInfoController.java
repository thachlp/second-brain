package coffee.shop.controller.user;

import coffee.shop.dto.request.UserRegistrationRequest;
import coffee.shop.model.response.CommonDataResponse;
import coffee.shop.service.UserInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "UserInfo", description = "Operations for user registering, updating, retrieving info")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserInfoController {
    private final UserInfoService userInfoService;

    @Operation(summary = "Register a new user")
    @PostMapping("/register")
    public ResponseEntity<CommonDataResponse> registerUserAccount(@RequestBody UserRegistrationRequest userRegistrationDto) {
        final CommonDataResponse registeredResponse = userInfoService.registerNewUserAccount(userRegistrationDto);
        return ResponseEntity.ok(registeredResponse);
    }
}
