package coffee.shop.controller.user;

import coffee.shop.dto.request.UserRegisterRequest;
import coffee.shop.dto.request.UserUpdateRequest;
import coffee.shop.dto.response.UserRegisterResponse;
import coffee.shop.dto.response.UserUpdateResponse;
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
    public ResponseEntity<UserRegisterResponse> registerUserAccount(@RequestBody UserRegisterRequest userRegistrationRequest) {
        final UserRegisterResponse userRegisterResponse = userInfoService.registerNewUserAccount(userRegistrationRequest);
        return ResponseEntity.ok(userRegisterResponse);
    }

    @Operation(summary = "Update info of a user")
    @PutMapping("/update")
    public ResponseEntity<UserUpdateResponse> updateUserInfo(@RequestBody UserUpdateRequest userUpdateRequest) {
        final UserUpdateResponse userUpdateResponse = userInfoService.updateUserInfo(userUpdateRequest);
        return ResponseEntity.ok(userUpdateResponse);
    }
}
