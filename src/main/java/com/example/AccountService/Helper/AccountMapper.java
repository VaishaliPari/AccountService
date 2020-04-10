package com.example.AccountService.Helper;

import com.example.AccountService.Entity.Account;
import com.example.AccountService.Entity.RefreshableProperties;
import com.example.AccountService.Infrastructure.RestTemplateFactory;
import com.example.AccountService.Model.AccountHolderModel;
import com.example.AccountService.Model.AccountModel;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    @Autowired
    private RestTemplateFactory factory;

    @Autowired
    private RefreshableProperties properties;

    @HystrixCommand(fallbackMethod = "fallBackEvent", commandKey = "fallBack-event", groupKey = "fallBack-event")
    @Retryable(maxAttempts = 3)
    public AccountHolderModel getCustomerById(long customerId){
        StringBuilder url = new StringBuilder();
        url.append(properties.getRestCall());
        url.append("/v1/customers/");
        url.append(customerId);
        AccountHolderModel event = factory.getInstance("Customers",properties.getConnectionTimeout(), properties.getRequestTimeout()).getForObject(url.toString(), AccountHolderModel.class);
        return event;
    }

    public AccountHolderModel fallBackEvent(long customerId){
        AccountHolderModel event = new AccountHolderModel();
        event.setAddress("abcd");
        event.setCountryOfBirth("India");
        event.setDob("11-1-1994");
        return event;
    }

    public static Account toEntity(AccountModel model){
        Account account = new Account();
        account.setAccountHolderId(model.getAccountHolderId());
        account.setAccountType(model.getAccountType());
        account.setBalanceAvailable(model.getBalanceAvailable());
        account.setBranchId(model.getBranchId());
        account.setCreationDate(model.getCreationDate());
        account.setMinimumBalance(model.getMinimumBalance());
        return account;
    }

    public static AccountModel toModel(Account account){
        AccountModel model = new AccountModel();
        model.setAccountHolderId(account.getAccountHolderId());
        model.setAccountId(account.getAccountId());
        model.setAccountType(account.getAccountType());
        model.setBalanceAvailable(account.getBalanceAvailable());
        model.setBranchId(account.getBranchId());
        model.setCreationDate(account.getCreationDate());
        model.setMinimumBalance(account.getMinimumBalance());
        return model;
    }

    public static void merge(AccountModel model, Account entity) {
        entity.setMinimumBalance(model.getMinimumBalance());
        entity.setCreationDate(model.getCreationDate());
        entity.setBranchId(model.getBranchId());
        entity.setBalanceAvailable(model.getBalanceAvailable());
        entity.setAccountType(model.getAccountType());
        entity.setAccountHolderId(model.getAccountHolderId());
    }

}
