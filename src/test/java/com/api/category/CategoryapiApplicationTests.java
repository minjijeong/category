package com.api.category;

import com.api.category.controller.CategoryController;
import com.api.category.model.dto.CategoryForm;
import com.api.category.model.entity.Category;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class CategoryapiApplicationTests {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void 테스트() throws Exception{
        MvcResult mvcResult= mockMvc.perform(
                                MockMvcRequestBuilders
                                .get("/api/hello")
                                .accept(MediaType.APPLICATION_JSON)
                                    )
                                    .andExpect(status().isOk())
                                    .andExpect(content().contentType("application/json"))
                                    .andExpect(jsonPath("$.error").value(false))
                                    .andExpect(jsonPath("$.errorCode").isEmpty())
                                    .andExpect(jsonPath("$.message").value(HttpStatus.OK.getReasonPhrase()))
                                    .andExpect(jsonPath("$.result").exists())
                                    .andReturn();
    }

    @Test
    void 카테고리생성() throws Exception{
        String content = objectMapper.writeValueAsString(CategoryForm.builder()
                .cateName("중분류_카테_컨트롤러테스트")
                .level(2)
                .largeCateId(2L)
                .dispYn(true)
                .build());

        MvcResult mvcResult= mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/api/category/register")
                                .content(content)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.error").value(false))
                .andExpect(jsonPath("$.errorCode").isEmpty())
                .andExpect(jsonPath("$.message").value(HttpStatus.OK.getReasonPhrase()))
                .andExpect(jsonPath("$.result").exists())
                .andReturn();
    }

    @Test
    void 카테고리_단일조회() throws Exception{
        long cateId = 5;
        MvcResult mvcResult = mockMvc.perform(get("/api/category/" + cateId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.error").value(false))
                .andExpect(jsonPath("$.errorCode").isEmpty())
                .andExpect(jsonPath("$.message").value(HttpStatus.OK.getReasonPhrase()))
                .andExpect(jsonPath("$.result").exists())
                .andReturn();
    }

    @Test
    void 카테고리_대분류조회() throws Exception{
        long cateId = 2;
        MvcResult mvcResult = mockMvc.perform(get("/api/category/" + cateId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.error").value(false))
                .andExpect(jsonPath("$.errorCode").isEmpty())
                .andExpect(jsonPath("$.message").value(HttpStatus.OK.getReasonPhrase()))
                .andExpect(jsonPath("$.result").exists())
                .andReturn();
    }

    @Test
    void 카테고리_중분류조회() throws Exception{
        long cateId = 3;
        MvcResult mvcResult = mockMvc.perform(get("/api/category/" + cateId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.error").value(false))
                .andExpect(jsonPath("$.errorCode").isEmpty())
                .andExpect(jsonPath("$.message").value(HttpStatus.OK.getReasonPhrase()))
                .andExpect(jsonPath("$.result").exists())
                .andReturn();
    }
}
