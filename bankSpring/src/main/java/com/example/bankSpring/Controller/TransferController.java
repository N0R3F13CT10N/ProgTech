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
    public String transfer(Model model) {
        User user = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("accounts", user.getAccounts());
        if(user.getAccounts().size() == 0)
            return "redirect:/home";
        return "transfer";
    }

    @PostMapping("/transfer")
    public String transfer(@ModelAttribute("phone") String phone,
                           @ModelAttribute("uuid") String account,
                           @ModelAttribute("sum") Double sum,
                           @ModelAttribute("currency") String currency,
                           BindingResult bindingResult) {
        Optional<Account> accEntity = accountRepository.findById(UUID.fromString(account));
        Account accFrom = accEntity.get();
        User receiver = userService.findByPhone(phone);
        Account accTo = receiver.getAccounts().iterator().next();
        BigDecimal oldSum = accTo.getAmount();
        accFrom.withdraw(sum, currency);
        accTo.deposit(sum, currency);
        Operation op = new Operation(new Date(),
                currency, accFrom, accTo, BigDecimal.valueOf(sum), oldSum, accTo.getAmount());
        operationRepository.save(op);
        return "redirect:/home";
    }
}
