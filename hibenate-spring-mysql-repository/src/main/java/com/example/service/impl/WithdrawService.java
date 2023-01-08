package com.example.service.impl;

import com.example.model.Withdraw;
import com.example.repository.IWithdrawRepository;
import com.example.service.IWithdrawService;
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
public class WithdrawService implements IWithdrawService {
    @Autowired
    private IWithdrawRepository withdrawRepository;
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Iterable<Withdraw> findAll() {
        return withdrawRepository.findAll();
    }

    @Override
    public Optional<Withdraw> findById(long id) {
        return withdrawRepository.findById(id);
    }

    @Override
    public void save(Withdraw withdraw) {
        withdrawRepository.save(withdraw);
    }

    @Override
    public void remove(long id) {
        withdrawRepository.deleteById(id);
    }

    @Override
    public String withdraw(long id, BigDecimal withdrawMoney) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("stpr_withdraw");
        query.registerStoredProcedureParameter(1, long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, BigDecimal.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, String.class, ParameterMode.OUT);
        query.setParameter(1,id);
        query.setParameter(2,withdrawMoney);
        query.execute();
        String message = (String) query.getOutputParameterValue(3);
        return message;
    }
}
