package coffee.shop.controller.user;

import coffee.shop.dto.request.UserRegistrationRequest;
import coffee.shop.dto.request.UserUpdateRequest;
import coffee.shop.service.UserInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserInfoController.class)
class UserInfoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserInfoService userInfoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void registerUserAccount() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/user/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(new UserRegistrationRequest("test", "test")))
                )
                .andExpect(status().is(200));
    }

    @Test
    void updateUserInfo() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.put("/user/update")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(new UserUpdateRequest("test", "test@gmail.com",
                                        "84123456052", "Alex", "HCM City")))
                )
                .andExpect(status().is(200));
    }
}
