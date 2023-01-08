package com.example.repository;

import com.example.model.Deposits;
import com.example.model.Withdraw;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWithdrawRepository extends PagingAndSortingRepository<Withdraw, Long> {
}
