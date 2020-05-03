package ru.mail.dimaushenko.webmodule.controller.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(value = "/application-test.properties")
public class ArticleApiControllerITTest {

    @Autowired
    private MockMvc mvc;

    public ArticleApiControllerITTest() {
    }

    @Test
    @WithMockUser(roles = {"SECURE_API_USER"})
    public void testGetAtricles() throws Exception {
        mvc.perform(
                get("/api/articles")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(jsonPath("$[0].title").value("article test"))
                .andExpect(jsonPath("$[0].content").value("Some text"))
                .andExpect(jsonPath("$[0].date").value("2020-04-29T10:28:53.000+0000"));
    }

    @Test
    @WithMockUser(roles = {"SECURE_API_USER"})
    public void testGetArticle() throws Exception {
        mvc.perform(
                get("/api/articles/1")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.title").value("article test"))
                .andExpect(jsonPath("$.content").value("Some text"))
                .andExpect(jsonPath("$.date").value("2020-04-29T10:28:53.000+0000"));
    }

    @Test
    @WithMockUser(roles = {"SECURE_API_USER"})
    public void testDeleteArticle() throws Exception {
        mvc.perform(
                delete("/api/articles/2"))
                .andExpect(status().isOk());
    }

}
