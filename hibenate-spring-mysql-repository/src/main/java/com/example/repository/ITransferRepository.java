package com.example.repository;

import com.example.model.Transfer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITransferRepository extends PagingAndSortingRepository<Transfer, Long> {
}
