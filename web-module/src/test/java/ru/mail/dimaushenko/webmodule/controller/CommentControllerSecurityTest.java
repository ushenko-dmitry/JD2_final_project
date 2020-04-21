package ru.mail.dimaushenko.webmodule.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(value = "/application-test.properties")
@AutoConfigureMockMvc
public class CommentControllerSecurityTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void testShowComments() throws Exception {
        mockMvc.perform(get("/comments").
                contentType(MediaType.TEXT_HTML)).
                andExpect(status().
                        is(302)
                );
    }

    @Test
    @WithMockUser(roles = {"ADMINISTRATOR"})
    public void testShowComments_ADMINISTRATOR() throws Exception {
        mockMvc.perform(get("/comments").
                contentType(MediaType.TEXT_HTML)).
                andExpect(status().
                        isOk()
                );
    }

    @Test
    @WithMockUser(roles = {"CUSTOMER_USER"})
    public void testShowComments_CUSTOMER_USER() throws Exception {
        mockMvc.perform(get("/comments").
                contentType(MediaType.TEXT_HTML)).
                andExpect(status().
                        isForbidden()
                );
    }

    @Test
    @WithMockUser(roles = {"SALE_USER"})
    public void testShowComments_SALE_USER() throws Exception {
        mockMvc.perform(get("/comments").
                contentType(MediaType.TEXT_HTML)).
                andExpect(status().
                        isForbidden()
                );
    }

    @Test
    @WithMockUser(roles = {"SECURE_API_USER"})
    public void testShowComments_SECURE_API_USER() throws Exception {
        mockMvc.perform(get("/comments").
                contentType(MediaType.TEXT_HTML)).
                andExpect(status().
                        isForbidden()
                );
    }

    @Test
    public void testDeleteComment() throws Exception {
        mockMvc.perform(post("/comments/1/delete").
                contentType(MediaType.TEXT_HTML)).
                andExpect(status().
                        is(302)
                );
    }

    @Test
    @Disabled
    @WithMockUser(roles = {"ADMINISTRATOR"})
    public void testDeleteComment_ADMINISTRATOR() throws Exception {
        mockMvc.perform(post("/comments/0/delete").
                contentType(MediaType.TEXT_HTML)).
                andExpect(status().
                        isOk()
                );
    }

    @Test
    @WithMockUser(roles = {"CUSTOMER_USER"})
    public void testDeleteComment_CUSTOMER_USER() throws Exception {
        mockMvc.perform(post("/comments/1/delete").
                contentType(MediaType.TEXT_HTML)).
                andExpect(status().
                        isForbidden()
                );
    }

    @Test
    @WithMockUser(roles = {"SALE_USER"})
    public void testDeleteComment_SALE_USER() throws Exception {
        mockMvc.perform(post("/comments/1/delete").
                contentType(MediaType.TEXT_HTML)).
                andExpect(status().
                        isForbidden()
                );
    }

    @Test
    @WithMockUser(roles = {"SECURE_API_USER"})
    public void testDeleteComment_SECURE_API_USER() throws Exception {
        mockMvc.perform(post("/comments/1/delete").
                contentType(MediaType.TEXT_HTML)).
                andExpect(status().
                        isForbidden()
                );
    }

}
