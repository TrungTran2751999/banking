package com.example.controller;

import com.example.model.Customer;
import com.example.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    @GetMapping("")
    private String findAllCustomer(Model model){
        Iterable<Customer> customers = customerService.findAll();
        model.addAttribute("customers",customers);
        return "index";
    }
    @GetMapping("/customer/create-customer")
    private String showCreateCustomer(Model model){
        model.addAttribute("customer", new Customer());
        return "create-customer";
    }
    @PostMapping("/customer/create-customer/action")
    private String createCustomer(RedirectAttributes model, @Validated @ModelAttribute("customer") Customer customer, BindingResult bindingResult){
        if(!bindingResult.hasFieldErrors()) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = dateFormat.format(new Date());
            customer.setCreatedAt(date);
            customer.setUpdatedAt(date);
            BigDecimal balance = new BigDecimal(0);
            Customer newCustomer = new Customer(
                    customer.getFullName(),
                    customer.getEmail(),
                    customer.getPhone(),
                    customer.getAddress(),
                    customer.getCreatedAt(),
                    customer.getUpdatedAt(),
                    balance
            );
            List<Customer> email = customerService.findEmailByEmail(customer.getEmail());
            if (email.size() == 0) {
                customerService.save(newCustomer);
                model.addFlashAttribute("message", "Create Customer Successfully");
            } else {
                model.addFlashAttribute("error", "This email have been exist");
            }
            return "redirect:/customer/create-customer";
        }else{
            model.addFlashAttribute("error", "Create new customer failed");
            return "redirect:/customer/create-customer";
        }
    }
    @GetMapping("/customer/deposits")
    private String showDeposits(Model model){
        model.addAttribute("customer", new Customer());
        return "deposit";
    }
    @GetMapping("/customer/{id}")
    private String showEditCustomer(@PathVariable("id") long id, Model model){
        Optional<Customer> customer = customerService.findById(id);
        if(customer.isPresent()){
            model.addAttribute("customer", customer.get());
            return "edit-customer";
        }else{
            return "404";
        }
    }
    @PostMapping("/customer/edit/{id}")
    private String editCustomer(@PathVariable("id") long id,
                                RedirectAttributes model,
                                @Valid @ModelAttribute("customer") Customer customer,
                                BindingResult bindingResult){
        Optional<Customer> customerOptional = customerService.findById(id);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = dateFormat.format(new Date());
        customer.setId(id);
        customer.setUpdatedAt(date);
        customer.setCreatedAt(customerOptional.get().getCreatedAt());
        customer.setBalance(customerOptional.get().getBalance());
        if(customerOptional.isPresent()){
            if(!bindingResult.hasFieldErrors()){
                customerService.save(customer);
                model.addFlashAttribute("message","Update Customer Successfully");
                return "redirect:/customer/"+id;
            }else{
                model.addFlashAttribute("error", "Error");
                return "redirect:/customer/"+id;
            }
        }else{
            model.addFlashAttribute("error","This customer is not exist");
            return "redirect:/customer/"+id;
        }
    }

}
