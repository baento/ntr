package fr.uphf.etu.bank;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import fr.uphf.etu.bank.models.Account;
import fr.uphf.etu.bank.services.AccountService;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountTests {
    @MockBean
    private AccountService accountServiceMock;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnAccountList() throws Exception {
        Account testAccount1 = Account.builder().id(0).owner("TestAccount1").code("1234").build();
        Account testAccount2 = Account.builder().id(1).owner("TestAccount2").code("5678").build();

        List<Account> accounts = List.of(testAccount1, testAccount2);
        when(accountServiceMock.getAllAccounts()).thenReturn(accounts);

        this.mockMvc.perform(get("/rest/accounts").contentType("application/json")).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void shouldAddAccount() throws Exception {
        Account testAccount = Account.builder().owner("TestAccount").code("1234").build();

        this.mockMvc.perform(post("/rest/accounts").content(objectMapper.writeValueAsString(testAccount))
                .contentType("application/json")).andExpect(status().isCreated());
    }

    @Test
    public void shouldReturnAccount() throws Exception {
        Account testAccount = Account.builder().owner("TestAccount").code("1234").build();
        when(accountServiceMock.getAccount(0)).thenReturn(testAccount);

        this.mockMvc.perform(get("/rest/accounts/0")).andExpect(status().isOk())
                .andExpect(jsonPath("$.owner", is(testAccount.getOwner())))
                .andExpect(jsonPath("$.code", is(testAccount.getCode())));
    }
}
