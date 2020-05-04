package ru.mail.dimaushenko.webmodule.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(value = "/application-test.properties")
public class ArticleControllerSecurityTest {

    private static final int MOVED_TEMPORARILY = 302;

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        this.mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void testGetArticles() throws Exception {
        mvc.perform(get("/articles").
                contentType(MediaType.TEXT_HTML)).
                andExpect(status().
                        is(MOVED_TEMPORARILY)
                );
    }

    @Test
    @WithMockUser(roles = {"ADMINISTRATOR"})
    public void testGetArticles_ADMINISTRATOR() throws Exception {
        mvc.perform(get("/articles").
                contentType(MediaType.TEXT_HTML)).
                andExpect(status().
                        isForbidden()
                );
    }

    @Test
    @WithMockUser(roles = {"CUSTOMER_USER"})
    public void testGetArticles_CUSTOMER_USER() throws Exception {
        mvc.perform(get("/articles").
                contentType(MediaType.TEXT_HTML)).
                andExpect(status().
                        isOk()
                );
    }

    @Test
    @WithMockUser(roles = {"SALE_USER"})
    public void testGetArticles_SALE_USER() throws Exception {
        mvc.perform(get("/articles").
                contentType(MediaType.TEXT_HTML)).
                andExpect(status().
                        isOk()
                );
    }

    @Test
    @WithMockUser(roles = {"SECURE_API_USER"})
    public void testGetArticles_SECURE_API_USER() throws Exception {
        mvc.perform(get("/articles").
                contentType(MediaType.TEXT_HTML)).
                andExpect(status().
                        isForbidden()
                );
    }

    @Test
    public void testGetArticle() throws Exception {
        mvc.perform(get("/articles/1").
                contentType(MediaType.TEXT_HTML)).
                andExpect(status().
                        is(MOVED_TEMPORARILY)
                );
    }

    @Test
    @WithMockUser(roles = {"ADMINISTRATOR"})
    public void testGetArticle_ADMINISTRATOR() throws Exception {
        mvc.perform(get("/articles/1").
                contentType(MediaType.TEXT_HTML)).
                andExpect(status().
                        isForbidden()
                );
    }

    @Test
    @WithMockUser(roles = {"CUSTOMER_USER"})
    public void testGetArticle_CUSTOMER_USER() throws Exception {
        mvc.perform(get("/articles/1").
                contentType(MediaType.TEXT_HTML)).
                andExpect(status().
                        isOk()
                );
    }

    @Test
    @WithMockUser(roles = {"SALE_USER"})
    public void testGetArticle_SALE_USER() throws Exception {
        mvc.perform(get("/articles/1").
                contentType(MediaType.TEXT_HTML)).
                andExpect(status().
                        isOk()
                );
    }

    @Test
    @WithMockUser(roles = {"SECURE_API_USER"})
    public void testGetArticle_SECURE_API_USER() throws Exception {
        mvc.perform(get("/articles/1").
                contentType(MediaType.TEXT_HTML)).
                andExpect(status().
                        isForbidden()
                );
    }

}
