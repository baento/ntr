package fr.uphf.etu.bank;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import fr.uphf.etu.bank.services.AccountService;

@SpringBootTest
@AutoConfigureMockMvc
public class OperationTests {
    @MockBean
    private AccountService accountServiceMock;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldApprove() throws Exception {
        this.mockMvc.perform(post("/rest/operations")
                .content("<request><id>0</id><amount>-1</amount><merchant>Test</merchant><code>0000</code></request>")
                .contentType("application/xml")).andExpect(status().isOk());
    }

    @Test
    public void shouldDenyFunds() throws Exception {
        this.mockMvc.perform(post("/rest/operations")
                .content("<request><id>0</id><amount>2</amount><merchant>Test</merchant><code>0000</code></request>")
                .contentType("application/xml")).andExpect(status().isForbidden());
    }

    @Test
    public void shouldDenyWrongCode() throws Exception {
        this.mockMvc.perform(post("/rest/operations").content(
                "<request><id>0</id><amount>-1</amount><merchant>Test</merchant><code>wrongcode</code></request>")
                .contentType("application/xml")).andExpect(status().isUnauthorized());
    }
}
