package com.example.repository;

import com.example.model.Deposits;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDepositsRepository extends PagingAndSortingRepository<Deposits, Long> {

}
