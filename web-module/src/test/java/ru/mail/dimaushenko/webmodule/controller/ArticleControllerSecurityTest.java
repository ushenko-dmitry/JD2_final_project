package ru.mail.dimaushenko.webmodule.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(value = "/application-test.properties")
public class ArticleControllerSecurityTest {

    private static final int MOVED_TEMPORARILY = 302;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetArticles() throws Exception {
        mockMvc.perform(get("/articles").
                contentType(MediaType.TEXT_HTML)).
                andExpect(status().
                        is(MOVED_TEMPORARILY)
                );
    }

    @Test
    @WithMockUser(roles = {"ADMINISTRATOR"})
    public void testGetArticles_ADMINISTRATOR() throws Exception {
        mockMvc.perform(get("/articles").
                contentType(MediaType.TEXT_HTML)).
                andExpect(status().
                        isForbidden()
                );
    }

    @Test
    @WithMockUser(roles = {"CUSTOMER_USER"})
    public void testGetArticles_CUSTOMER_USER() throws Exception {
        mockMvc.perform(get("/articles").
                contentType(MediaType.TEXT_HTML)).
                andExpect(status().
                        isOk()
                );
    }

    @Test
    @WithMockUser(roles = {"SALE_USER"})
    public void testGetArticles_SALE_USER() throws Exception {
        mockMvc.perform(get("/articles").
                contentType(MediaType.TEXT_HTML)).
                andExpect(status().
                        isOk()
                );
    }

    @Test
    @WithMockUser(roles = {"SECURE_API_USER"})
    public void testGetArticles_SECURE_API_USER() throws Exception {
        mockMvc.perform(get("/articles").
                contentType(MediaType.TEXT_HTML)).
                andExpect(status().
                        isForbidden()
                );
    }

    @Test
    public void testGetArticle() throws Exception {
        mockMvc.perform(get("/articles/1").
                contentType(MediaType.TEXT_HTML)).
                andExpect(status().
                        is(MOVED_TEMPORARILY)
                );
    }

    @Test
    @WithMockUser(roles = {"ADMINISTRATOR"})
    public void testGetArticle_ADMINISTRATOR() throws Exception {
        mockMvc.perform(get("/articles/1").
                contentType(MediaType.TEXT_HTML)).
                andExpect(status().
                        isForbidden()
                );
    }

    @Test
    @WithMockUser(roles = {"CUSTOMER_USER"})
    public void testGetArticle_CUSTOMER_USER() throws Exception {
        mockMvc.perform(get("/articles/1").
                contentType(MediaType.TEXT_HTML)).
                andExpect(status().
                        isOk()
                );
    }

    @Test
    @WithMockUser(roles = {"SALE_USER"})
    public void testGetArticle_SALE_USER() throws Exception {
        mockMvc.perform(get("/articles/1").
                contentType(MediaType.TEXT_HTML)).
                andExpect(status().
                        isOk()
                );
    }

    @Test
    @WithMockUser(roles = {"SECURE_API_USER"})
    public void testGetArticle_SECURE_API_USER() throws Exception {
        mockMvc.perform(get("/articles/1").
                contentType(MediaType.TEXT_HTML)).
                andExpect(status().
                        isForbidden()
                );
    }

}
