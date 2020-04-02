package com.example.bankSpring.Controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Controller
public class HistoryController {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/history")
    public String history(Model model, RedirectAttributes redirectAttributes) {
        User user = userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        if(user.getAccounts().size() == 0) {
            redirectAttributes.addFlashAttribute("message", "You have no accounts!");
            return "redirect:/home";
        }
        Set<Account> accs = user.getAccounts();
        Set<Operation> ops = new HashSet<>();
        for (Account acc: accs) {
            Set<Operation> temp = new HashSet<>(operationRepository.findByAccFrom(acc));
            temp.addAll(operationRepository.findByAccTo(acc));
            ops.addAll(temp);
        }
        if(ops.size() == 0) {
            redirectAttributes.addFlashAttribute("message", "History is empty!");
            return "redirect:/home";
        }
        ArrayList<Operation> opsList = new ArrayList<>(ops);
        Collections.sort(opsList);
        model.addAttribute("operations", opsList);
        return "history";
    }
}