package fr.uphf.etu.bank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.uphf.etu.bank.exceptions.ResourceNotFoundException;
import fr.uphf.etu.bank.services.AccountService;

/**
 * Main webpage controller
 */
@Controller
@RequestMapping
public class WebController {
    @Autowired
    AccountService accountService;

    @RequestMapping
    public String viewList(Model model) {
        model.addAttribute("accounts", accountService.getAllAccounts());

        return "index";
    }

    @RequestMapping("/{id}")
    public String viewAccount(Model model, @PathVariable("id") Integer id) throws ResourceNotFoundException {
        model.addAttribute("account", accountService.getAccount(id));

        return "account";
    }
}
