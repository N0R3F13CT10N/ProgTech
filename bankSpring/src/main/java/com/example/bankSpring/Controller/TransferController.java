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
public class TransferController {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/transfer")
    public String transfer(Model model, RedirectAttributes redirectAttributes) {
        User user = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("accounts", user.getAccounts());
        if(user.getAccounts().size() == 0) {
            redirectAttributes.addFlashAttribute("message", "You have no accounts!");
            return "redirect:/home";
        }
        return "transfer";
    }

    @PostMapping("/transfer")
    public String transfer(@ModelAttribute("phone") String phone,
                           @ModelAttribute("uuid") String account,
                           @ModelAttribute("sum") String sumStr,
                           @ModelAttribute("currency") String currency,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        Optional<Account> accEntity = accountRepository.findById(UUID.fromString(account));
        Account accFrom = accEntity.get();
        User receiver;
        try {
            receiver = userService.findByPhone(phone);
        }
        catch (Exception e){
            redirectAttributes.addFlashAttribute("phone_message", "Invalid phone!");
            return "redirect:/transfer";
        }
        Account accTo;
        try {
            accTo = receiver.getAccounts().iterator().next();
        }
        catch(Exception ex){
            redirectAttributes.addFlashAttribute("phone_message", "This user has no accounts!");
            return "redirect:/transfer";
        }
        double sum;
        try{
            sum = Double.parseDouble(sumStr);
        }
        catch (Exception e){
            redirectAttributes.addFlashAttribute("message", "Incorrect data!");
            return "redirect:/transfer";
        }
        if(sum <= 0){
            redirectAttributes.addFlashAttribute("message", "Amount must be greater than 0!");
            return "redirect:/transfer";
        }
        if(BigDecimal.valueOf(sum).compareTo(Converter.convert(accFrom.getAmount(), accFrom.getAccCode(), currency)) > 0){
            redirectAttributes.addFlashAttribute("message", "U don't have enough money!");
            return "redirect:/transfer";
        }
        BigDecimal oldSum = accTo.getAmount();
        accFrom.withdraw(sum, currency);
        accTo.deposit(sum, currency);
        Operation op = new Operation(new Date(),
                currency, accFrom, accTo, BigDecimal.valueOf(sum), oldSum, accTo.getAmount());
        operationRepository.save(op);
        return "redirect:/home";
    }
}
