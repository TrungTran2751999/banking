package com.example.service;

import com.example.model.Customer;

import java.util.List;

public interface ICustomerService extends IGeneratedService<Customer> {
    List<Customer> findEmailByEmail(String email);
}
