package coffee.shop.controller.user;

import coffee.shop.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserCategoryController.class)
class UserCategoryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @Test
    void getCategories() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/user/categories")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is(200));
    }

    @Test
    void getCategoryDetail() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/user/categories/1")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is(200));
    }
}
