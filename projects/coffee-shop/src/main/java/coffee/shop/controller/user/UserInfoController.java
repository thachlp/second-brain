package coffee.shop.controller.user;

import coffee.shop.dto.request.UserRegistrationRequest;
import coffee.shop.dto.request.UserUpdateRequest;
import coffee.shop.model.response.CommonDataResponse;
import coffee.shop.service.UserInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "UserInfo", description = "Operations for user registering, updating, retrieving info")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserInfoController {
    private final UserInfoService userInfoService;

    @Operation(summary = "Register a new user")
    @PostMapping("/register")
    public ResponseEntity<CommonDataResponse> registerUserAccount(@RequestBody UserRegistrationRequest userRegistrationRequest) {
        final CommonDataResponse registeredResponse = userInfoService.registerNewUserAccount(userRegistrationRequest);
        return ResponseEntity.ok(registeredResponse);
    }

    @Operation(summary = "Update info of a user")
    @PutMapping("/update")
    public ResponseEntity<CommonDataResponse> updateUserInfo(@RequestBody UserUpdateRequest userUpdateRequest) {
        final CommonDataResponse registeredResponse = userInfoService.updateUserInfo(userUpdateRequest);
        return ResponseEntity.ok(registeredResponse);
    }
}
