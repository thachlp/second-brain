package coffee.shop.service;

import coffee.shop.dto.request.UserRegisterRequest;
import coffee.shop.dto.request.UserUpdateRequest;
import coffee.shop.model.entity.UserInfo;
import coffee.shop.model.exception.ResourceNotFoundException;
import coffee.shop.model.exception.UsernameAlreadyExistsException;
import coffee.shop.repository.UserInfoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserInfoServiceTest {
    @InjectMocks
    private UserInfoService userInfoService;

    @Mock
    private UserInfoRepository userInfoRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void registerNewUserAccount(){
        final UserInfo userInfo = new UserInfo();
        userInfo.setId(1L);
        userInfo.setUsername("test");
        when(userInfoRepository.findByUsername("test")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("test")).thenReturn("test");
        when(userInfoRepository.save(any())).thenReturn(userInfo);
        final var commonDataResponse = userInfoService.registerNewUserAccount(new UserRegisterRequest("test", "test"));
        assertThat(commonDataResponse).isNotNull();
        verify(userInfoRepository, times(1)).save(any());
    }

    @Test
    void registerNewUserAccountWithExistUsername(){
        final UserInfo userInfo = new UserInfo();
        userInfo.setId(1L);
        userInfo.setUsername("test");
        final UserRegisterRequest userRegistrationRequest = new UserRegisterRequest("test", "test");
        when(userInfoRepository.findByUsername("test")).thenReturn(Optional.of(userInfo));
        final Exception exception = assertThrows(UsernameAlreadyExistsException.class, () ->
                userInfoService.registerNewUserAccount(userRegistrationRequest));
        assertEquals("Username test already exists", exception.getMessage());
    }

    @Test
    void updateUserInfo(){
        final UserInfo userInfo = new UserInfo();
        userInfo.setId(1L);
        userInfo.setUsername("test");

        final UserInfo newUserInfo = new UserInfo();
        newUserInfo.setUsername("test");
        newUserInfo.setEmail("test@gmail.com");
        newUserInfo.setPhoneNumber("84355123456");
        newUserInfo.setFullName("Alex");
        newUserInfo.setAddress("Ho Chi Minh City");
        when(userInfoRepository.findByUsername("test")).thenReturn(Optional.of(userInfo));
        when(userInfoRepository.save(any())).thenReturn(newUserInfo);
        final UserUpdateRequest userUpdateRequest = new UserUpdateRequest("test", "test@gmail.com",
                "84123456052", "Alex", "HCM City");
        final var commonDataResponse = userInfoService.updateUserInfo(userUpdateRequest);
        assertThat(commonDataResponse).isNotNull();
        verify(userInfoRepository, times(1)).save(any());
    }

    @Test
    void updateNotExistUser(){
        final UserUpdateRequest userUpdateRequest = new UserUpdateRequest("test", "test@gmail.com",
                "84123456052", "Alex", "HCM City");
        final Exception exception = assertThrows(ResourceNotFoundException.class, () ->
                userInfoService.updateUserInfo(userUpdateRequest));
        assertEquals("User with username test not found", exception.getMessage());
    }

}
