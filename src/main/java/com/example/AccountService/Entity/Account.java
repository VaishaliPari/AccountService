package com.example.AccountService.Entity;


import javax.persistence.*;

@Entity
@Table(name = "AccountDetails")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accountId")
    private long accountId;
    @Column(name = "accountType")
    private String accountType;
    @Column(name = "minimumBalance")
    private long minimumBalance;
    @Column(name = "branchId")
    private String branchId;
    @Column(name = "creationDate")
    private String creationDate;
    @Column(name = "accountHolderId")
    private String accountHolderId;
    @Column(name = "balanceAvailable")
    private String balanceAvailable;

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public long getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(long minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getAccountHolderId() {
        return accountHolderId;
    }

    public void setAccountHolderId(String accountHolderId) {
        this.accountHolderId = accountHolderId;
    }

    public String getBalanceAvailable() {
        return balanceAvailable;
    }

    public void setBalanceAvailable(String balanceAvailable) {
        this.balanceAvailable = balanceAvailable;
    }
}
