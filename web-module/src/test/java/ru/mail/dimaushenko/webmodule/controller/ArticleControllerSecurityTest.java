package ru.mail.dimaushenko.webmodule.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.AutoConfigureDataJdbc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import ru.mail.dimaushenko.webmodule.controller.config.TestSecurityConfig;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WebMvcTest(controllers = ArticleController.class)
@AutoConfigureDataJdbc
@Import(TestSecurityConfig.class)
@TestPropertySource(value = "/application-test.properties")
@AutoConfigureMockMvc
@Disabled
public class ArticleControllerSecurityTest {

    private static final int MOVED_TEMPORARILY = 302;

//    @Autowired
//    private WebApplicationContext context;
    @Autowired
    private MockMvc mockMvc;

//    @BeforeEach
//    public void setup() {
//        this.mockMvc = MockMvcBuilders
//                .webAppContextSetup(context)
//                .apply(springSecurity())
//                .build();
//    }
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
