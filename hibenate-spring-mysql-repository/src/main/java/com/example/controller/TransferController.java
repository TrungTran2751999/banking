package com.example.controller;

import com.example.model.Customer;
import com.example.model.Transfer;
import com.example.service.ICustomerService;
import com.example.service.ITransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.Optional;

@Controller
public class TransferController {
    @Autowired
    private ITransferService transferService;
    @Autowired
    private ICustomerService customerService;
    @GetMapping("/transfer/{id}")
    private String showTransfer(@PathVariable("id") long id, Model model){
        Optional< Customer> customer = customerService.findById(id);
        Iterable<Customer> customers = customerService.findAll();
        if(customer.isPresent()){
            model.addAttribute("sender", customer.get());
            model.addAttribute("recipients", customers);
            model.addAttribute("transfer", new Transfer());
            return "transfer";
        }else{
            return "404";
        }
    }
    @PostMapping("/transfer/{id}/action")
    private String transfer(@PathVariable("id") long senderId,
                            @ModelAttribute("sender") Customer customer,
                            @RequestParam("recipientId") long recipientId,
                            @RequestParam("transactionAmount") BigDecimal money,
                            RedirectAttributes redirectAttributes){
        String message = transferService.transfer(senderId,recipientId,money);
        if(!message.equals("success")){
            redirectAttributes.addFlashAttribute("message", message);
        }else{
            redirectAttributes.addFlashAttribute("message", "success");
        }
        return "redirect:/transfer/"+senderId;
    }
}
