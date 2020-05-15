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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(value = "/application-test.properties")
public class BasketApiControllerITTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser(roles = {"SECURE_API_USER"})
    public void testGetBaskets() throws Exception {
        mvc.perform(
                get("/api/baskets")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(jsonPath("$[0].orderStatus").value("NEW"))
                .andExpect(jsonPath("$[0].userPhone").value("+375290000000"))
                .andExpect(jsonPath("$[0].orderedItem.name").value("item name"))
                .andExpect(jsonPath("$[0].orderedItem.price").value(10))
                .andExpect(jsonPath("$[0].orderedItem.amount").value(1));
    }

    @Test
    @WithMockUser(roles = {"SECURE_API_USER"})
    public void testGetBasket() throws Exception {
        mvc.perform(
                get("/api/baskets/1")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(jsonPath("$.orderStatus").value("NEW"))
                .andExpect(jsonPath("$.userPhone").value("+375290000000"))
                .andExpect(jsonPath("$.orderedItem.name").value("item name"))
                .andExpect(jsonPath("$.orderedItem.price").value(10))
                .andExpect(jsonPath("$.orderedItem.amount").value(1));
    }

}
