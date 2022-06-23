package com.api.category;

import com.api.category.service.CategoryService;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MvcResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class CategoryapiApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService service;

    @Test
    void 테스트() throws Exception{
        MvcResult mvcResult= mockMvc.perform(get("/hello"))
                                    .andDo(print())
                                    .andExpect(status().isOk())
                                    .andExpect(content().contentType("application/json"))
                                    .andExpect(jsonPath("$.success").value(true))
                                    .andExpect(jsonPath("$.code").value(0))
                                    .andExpect(jsonPath("$.msg").exists())
                                    .andReturn();
    }

}
