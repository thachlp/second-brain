package coffee.shop.service;

import coffee.shop.dto.request.UserRegistrationRequestDto;
import coffee.shop.entity.UserInfo;
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
    void register(){
        final UserInfo userInfo = new UserInfo();
        userInfo.setId(1L);
        userInfo.setUsername("test");
        when(userInfoRepository.findByUsername("test")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("test")).thenReturn("test");
        when(userInfoRepository.save(any())).thenReturn(userInfo);
        final var userRegistrationResponseDto = userInfoService.registerNewUserAccount(new UserRegistrationRequestDto("test", "test"));
        assertThat(userRegistrationResponseDto).isNotNull();
        verify(userInfoRepository, times(1)).save(any());
    }

    @Test
    void registerWithSaveFail(){
        when(userInfoRepository.findByUsername("test")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("test")).thenReturn("test");
        when(userInfoRepository.save(any())).thenReturn(null);
        final var userRegistrationResponseDto = userInfoService.registerNewUserAccount(new UserRegistrationRequestDto("test", "test"));
        assertThat(userRegistrationResponseDto.getId()).isNull();
        verify(userInfoRepository, times(1)).save(any());
    }

    @Test
    void registerWithExistUsername(){
        final UserInfo userInfo = new UserInfo();
        userInfo.setId(1L);
        userInfo.setUsername("test");
        final UserRegistrationRequestDto userRegistrationRequestDto = new UserRegistrationRequestDto("test", "test");
        when(userInfoRepository.findByUsername("test")).thenReturn(Optional.of(userInfo));
        final Exception exception = assertThrows(UsernameAlreadyExistsException.class, () ->
                userInfoService.registerNewUserAccount(userRegistrationRequestDto));
        assertEquals("Username test already exists", exception.getMessage());
    }
}
