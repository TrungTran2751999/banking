package com.example.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "not Empty")
    @Pattern(regexp = "^\\w{1,20}$")
    @Column(name = "full_name",nullable = false)
    private String fullName;

    @NotEmpty
    @Column(name = "email",nullable = false)
    private String email;

    @NotEmpty(message = "Phone is not blank")
    @Pattern(regexp = "^0[3798][0-9]{8}", message = "Phone is not valid")
    @Column(name="phone", nullable = false)
    private String phone;

    @NotEmpty
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "balance", columnDefinition = "decimal(12,0) default 0")
    private BigDecimal balance;
    @Column(name="created_at", nullable = false, columnDefinition = "datetime")
    private String createdAt;
    @Column(name="created_by")
    private int createdBy;
    @Column(name="updated_at", nullable = false, columnDefinition = "datetime")
    private String updatedAt;
    @Column(name="updated_by")
    private int updatedBy;
    @Column(name = "deleted",columnDefinition = "tinyint(1) default 0")
    private int deleted;

    public Customer(long id, String fullName, String email, String phone, String address, String createdAt, int createdBy, String updatedAt, int updatedBy, int deleted) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
        this.deleted = deleted;
    }

    public Customer(String fullName, String email, String phone, String address, String createdAt, String updatedAt, BigDecimal balance) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.balance = balance;
    }
    public Customer(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
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

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }
}
