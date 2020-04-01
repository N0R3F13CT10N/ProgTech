package com.example.bankSpring.Controller;

import com.example.bankSpring.Entity.Account;
import com.example.bankSpring.Entity.User;
import com.example.bankSpring.Repository.AccountRepository;
import com.example.bankSpring.Repository.UserRepository;
import com.example.bankSpring.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

@Controller
public class AddAccountController {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/newacc")
    public String newacc(Model model) {
        model.addAttribute("accForm", new Account());
        return "newacc";
    }

    @PostMapping("/newacc")
    public String newacc(@ModelAttribute("accForm") @Valid Account accForm, BindingResult bindingResult) {
        accForm.setUuid(UUID.randomUUID());
        accForm.setAmount(BigDecimal.valueOf(0));
        User user = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        accForm.setUser(user);
        accountRepository.save(accForm);
        return "redirect:/home";
    }
}

