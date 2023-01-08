package com.example.service.impl;

import com.example.model.Deposits;
import com.example.repository.IDepositsRepository;
import com.example.service.IDepositsService;
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
public class DepositsService implements IDepositsService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private IDepositsRepository depositsRepository;

    @Override
    public String deposits(long id, BigDecimal money) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("stpr_deposits");
        query.registerStoredProcedureParameter(1, long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, BigDecimal.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, String.class, ParameterMode.OUT);
        query.setParameter(1,id);
        query.setParameter(2,money);
        query.execute();
        String message = (String) query.getOutputParameterValue(3);
        return message;
    }

    @Override
    public Iterable<Deposits> findAll() {
        return null;
    }

    @Override
    public Optional<Deposits> findById(long id) {
        return Optional.empty();
    }

    @Override
    public void save(Deposits deposits) {

    }

    @Override
    public void remove(long id) {

    }
}
