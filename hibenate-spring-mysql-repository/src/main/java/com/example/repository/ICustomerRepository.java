package com.example.repository;

import com.example.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICustomerRepository extends PagingAndSortingRepository<Customer,Long> {
    @Query(value = "from Customer where email=:email")
    List<Customer> findEmailByEmail(@Param("email") String email);
}
