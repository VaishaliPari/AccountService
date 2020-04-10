package com.example.AccountService.Model;

import com.example.AccountService.Entity.AccountType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class AccountModel {

    @JsonProperty
    private long accountId;
    @JsonProperty
    private String accountType;
    @JsonProperty
    private long minimumBalance;
    @JsonProperty
    private String branchId;
    @JsonProperty
    private String creationDate;
    @JsonProperty
    private String accountHolderId;
    @JsonProperty
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
