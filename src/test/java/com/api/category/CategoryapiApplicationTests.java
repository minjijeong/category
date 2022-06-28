package com.api.category;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;

import com.api.category.model.form.CategoryForm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@AutoConfigureMockMvc
@SpringBootTest
class CategoryapiApplicationTests {

    // REST Docs 폴더 지정
    @RegisterExtension
    final RestDocumentationExtension restDocumentation = new RestDocumentationExtension ("custom");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // REST Docs 세팅
    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext
                    , RestDocumentationContextProvider restDocumentation){
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .alwaysDo(document("{method-name}", preprocessRequest(prettyPrint()),  preprocessResponse(prettyPrint())))
                .build();
    }

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
                .largeCateId(1L)
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
