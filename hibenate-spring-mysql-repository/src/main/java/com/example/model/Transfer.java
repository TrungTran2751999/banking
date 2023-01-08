package com.example.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="transfers")
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "created_at", columnDefinition = "datetime")
    private String createdAt;
    @Column(name = "created_by")
    private long createBy;
    @Column(name = "deleted", columnDefinition = "tinyint(1) default 0")
    private int delected;
    @Column(name = "updated_at", columnDefinition = "datetime")
    private String updatedAt;
    @Column(name = "updated_by")
    private long updatedBy;
    @Column(name = "fee", nullable = false)
    private int fee;
    @Column(name = "fee_amount", nullable = false)
    private long feeAmount;
    @Column(name="transaction_amount", nullable = false)
    private long transactionAmount;
    @Column(name = "transfer_amount", nullable = false)
    private long transferAmount;
    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "recipient_id", nullable = false)
    private List<Customer> recipientId;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "sender_id", nullable = false)
    private List<Customer> senderId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(long createBy) {
        this.createBy = createBy;
    }

    public int getDelected() {
        return delected;
    }

    public void setDelected(int delected) {
        this.delected = delected;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public long getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(long feeAmount) {
        this.feeAmount = feeAmount;
    }

    public long getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(long transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public long getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(long transferAmount) {
        this.transferAmount = transferAmount;
    }

    public List<Customer> getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(List<Customer> recipientId) {
        this.recipientId = recipientId;
    }

    public List<Customer> getSenderId() {
        return senderId;
    }

    public void setSenderId(List<Customer> senderId) {
        this.senderId = senderId;
    }
}
