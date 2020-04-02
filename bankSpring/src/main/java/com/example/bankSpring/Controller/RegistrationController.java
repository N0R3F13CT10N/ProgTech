package com.example.bankSpring.Controller;

import com.example.bankSpring.Entity.User;
import com.example.bankSpring.Service.SecurityService;
import com.example.bankSpring.Service.UserService;
import com.example.bankSpring.Service.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;


    @GetMapping("/registration")
    public String registration(Model model, String error) {
        if (error != null)
            model.addAttribute("message", "Incorrect data!");
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("message", "Incorrect data!");
            return "registration";
        }

        userService.save(userForm);
        securityService.autoLogin(userForm.getLogin(), userForm.getPasswordConfirm());
        return "redirect:/home";
    }
}
