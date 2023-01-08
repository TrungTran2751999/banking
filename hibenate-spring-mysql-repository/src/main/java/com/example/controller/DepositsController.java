package com.example.controller;

import com.example.model.Customer;
import com.example.service.ICustomerService;
import com.example.service.IDepositsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.Optional;

@Controller
@RequestMapping("/deposit")
public class DepositsController {
    @Autowired
    private IDepositsService depositsService;
    @Autowired
    private ICustomerService customerService;
    @GetMapping("/{id}")
    private String showFormDeposit(@PathVariable("id") String customer_id, Model model){
        try {
            long id = Long.parseLong(customer_id);
            Optional<Customer> customer  = customerService.findById(id);
            if(customer.isPresent()){
                model.addAttribute("customer",customer.get());
                return "deposit";
            }else{
                return "404";
            }
        }catch (Exception e){
            return "404";
        }
    }
    @PostMapping("/{id}")
    private String deposit(@RequestParam("amount") BigDecimal amount, @PathVariable("id") String customer_id, RedirectAttributes redirectAttributes){
        try {
            long id = Long.parseLong(customer_id);
            Optional<Customer> customer  = customerService.findById(id);
            if(customer.isPresent()){
                String message = depositsService.deposits(id,amount);
                redirectAttributes.addFlashAttribute("message", message);
                customer  = customerService.findById(id);
                redirectAttributes.addFlashAttribute("customer",customer.get());
                return "redirect:/deposit/"+id;
            }else{
                return "404";
            }
        }catch (Exception e){
            return "404";
        }
    }
}
