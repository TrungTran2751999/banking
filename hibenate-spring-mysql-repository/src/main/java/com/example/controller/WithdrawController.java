package com.example.controller;

import com.example.model.Customer;
import com.example.model.Withdraw;
import com.example.service.ICustomerService;
import com.example.service.IWithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Controller
@Transactional
public class WithdrawController {
    @Autowired
    private IWithdrawService withdrawService;
    @Autowired
    private ICustomerService customerService;
    @GetMapping("/withdraw/{id}")
    private String showWithdraw(@PathVariable("id") long id, Model model){
        Optional<Customer> customer = customerService.findById(id);
        if(customer.isPresent()){
            model.addAttribute("customer",customer.get());
            model.addAttribute("withdraw", new Withdraw());
            return "withdraw";
        }else{
            return "404";
        }
    }
    @PostMapping("/withdraw/{id}")
    private String widthdraw(@PathVariable("id") long id, RedirectAttributes redirectAttributes, @ModelAttribute Withdraw withdraw){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = dateFormat.format(new Date());
        withdraw.setCreatedAt(date); withdraw.setUpdatedAt(date);
        Optional<Customer> customer = customerService.findById(id);
        if(customer.isPresent()){
            String message = withdrawService.withdraw(id, withdraw.getTransactionAmount());
            redirectAttributes.addFlashAttribute("customer",customer.get());
            redirectAttributes.addFlashAttribute("message", "success");
            if(message.equals("Traction amount must be smaller than blance")){
                redirectAttributes.addFlashAttribute("message", message);
            }else{
                redirectAttributes.addFlashAttribute("message", "success");
            }
            return "redirect:/withdraw/"+id;
        }else{
            return "404";
        }
    }
}
