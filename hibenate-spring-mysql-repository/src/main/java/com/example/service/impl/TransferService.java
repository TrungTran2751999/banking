package com.example.service.impl;

import com.example.model.Transfer;
import com.example.service.ICustomerService;
import com.example.service.ITransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.math.BigDecimal;
import java.util.Optional;
@Service
@Transactional
public class TransferService implements ITransferService {
    @Autowired
    private ITransferService transferService;
    @Autowired
    private ICustomerService customerService;
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Iterable<Transfer> findAll() {
        return transferService.findAll();
    }

    @Override
    public Optional<Transfer> findById(long id) {
        return transferService.findById(id);
    }

    @Override
    public void save(Transfer transfer) {
        transferService.save(transfer);
    }

    @Override
    public void remove(long id) {
        transferService.remove(id);
    }

    @Override
    public String transfer(long senderId, long recipientId, BigDecimal money) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("stpr_transfers");
        query.registerStoredProcedureParameter(1, long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, BigDecimal.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(4,String.class, ParameterMode.OUT);
        query.setParameter(1,senderId);
        query.setParameter(2, recipientId);
        query.setParameter(3,money);
        query.execute();
        String message = (String) query.getOutputParameterValue(4);
        return message;
    }

}
