package com.example.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name="deposits")
public class Deposits {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "created_at",nullable = false, columnDefinition = "datetime")
    private String createdAt;
    @Column(name = "created_by")
    private int createdBy;
    @Column(name = "deleted", columnDefinition = "tinyint(1) default 0")
    private int deleted;
    @Column(name = "updated_at", nullable = false, columnDefinition = "datetime")
    private String updatedAt;
    @Column(name = "updatedBy")
    private int updatedBy;
    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "customer_id",nullable = false)
    private List<Customer> customer;
    @Column(name = "transaction_amount", nullable = false, precision = 12, scale = 0)
    private BigDecimal transactionAmount;

    public Deposits(long id, String createdAt, int createdBy, int deleted, String updatedAt, int updatedBy, List<Customer> customer, BigDecimal transactionAmount) {
        this.id = id;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.deleted = deleted;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
        this.customer = customer;
        this.transactionAmount = transactionAmount;
    }

    public Deposits(){}
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String created_at) {
        this.createdAt = created_at;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(int updatedBy) {
        this.updatedBy = updatedBy;
    }

    public List<Customer> getCustomer() {
        return customer;
    }

    public void setCustomer(List<Customer> customer) {
        this.customer = customer;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
}
