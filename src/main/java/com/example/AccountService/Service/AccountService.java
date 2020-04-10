package com.example.AccountService.Service;

import com.example.AccountService.Entity.Account;
import com.example.AccountService.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AccountService {

    private AccountRepository repository;

    @Autowired
    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public Account saveAccount(Account account){
        return repository.saveAndFlush(account);
    }

    public Optional<Account> getAccountById(long accountId){
        return repository.findById(accountId);
    }

    public List<Account> getAccounts(){
        return repository.findAll();
    }

    public void deleteAccount(long accountId){
        repository.deleteById(accountId);
    }
}
