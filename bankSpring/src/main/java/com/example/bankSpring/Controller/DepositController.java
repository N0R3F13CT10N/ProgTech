package com.example.bankSpring.Controller;

import com.example.bankSpring.Config.Converter;
import com.example.bankSpring.Entity.Account;
import com.example.bankSpring.Entity.Operation;
import com.example.bankSpring.Entity.User;
import com.example.bankSpring.Repository.AccountRepository;
import com.example.bankSpring.Repository.OperationRepository;
import com.example.bankSpring.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Controller
public class DepositController {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/deposit")
    public String deposit(Model model, RedirectAttributes redirectAttributes) {
        User user = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        if(user.getAccounts().size() == 0) {
            redirectAttributes.addFlashAttribute("message", "You have no accounts, create one please.");
            return "redirect:/home";
        }
        model.addAttribute("accounts", user.getAccounts());
        return "deposit";
    }

    @PostMapping("/deposit")
    public String deposit(@ModelAttribute("uuid") String account,
                          @ModelAttribute("sum") String sumStr,
                          @ModelAttribute("currency") String currency,
                          RedirectAttributes redirectAttributes) {
        double sum;
        try{
            sum = Double.parseDouble(sumStr);
        }
        catch (Exception e){
            redirectAttributes.addFlashAttribute("message", "Incorrect data!");
            return "redirect:/deposit";
        }
        if(sum <= 0){
            redirectAttributes.addFlashAttribute("message", "Amount must be greater than 0!");
            return "redirect:/deposit";
        }
        Optional<Account> accEntity = accountRepository.findById(UUID.fromString(account));
        Account acc = accEntity.get();
        BigDecimal oldSum = acc.getAmount();
        acc.deposit(sum, currency);
        accountRepository.save(acc);
        Operation op = new Operation(new Date(),
                currency, null, acc, BigDecimal.valueOf(sum), oldSum, acc.getAmount());
        operationRepository.save(op);
        return "redirect:/home";
    }
}
