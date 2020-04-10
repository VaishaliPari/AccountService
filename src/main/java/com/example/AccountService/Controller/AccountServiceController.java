package com.example.AccountService.Controller;

import com.example.AccountService.Entity.Account;
import com.example.AccountService.Helper.AccountMapper;
import com.example.AccountService.Model.AccountHolderModel;
import com.example.AccountService.Model.AccountModel;
import com.example.AccountService.Service.AccountService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/accounts")
@RefreshScope
@Api(value = "AccountServiceController")
public class AccountServiceController {

    @Autowired
    private AccountService service;

    @Autowired
    private AccountMapper mapper;

    public AccountServiceController(AccountService service, AccountMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Autowired
    public AccountServiceController(AccountService service) {
        this.service = service;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @GetMapping
    public ResponseEntity<List<AccountModel>> fetchAccounts(){
        List<AccountModel> accounts = service.getAccounts().stream().map(o->AccountMapper.toModel(o))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(accounts);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @GetMapping("/{accountId}")
    public ResponseEntity<AccountModel> fetchAccountId(@ApiParam @PathVariable long accountId){
        Optional<Account> account = service.getAccountById(accountId);
        if (!account.isPresent())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(AccountMapper.toModel(account.get()));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @PostMapping
    public ResponseEntity<AccountModel> addOrUpdateAccount(@RequestBody AccountModel model){
        Optional<Account> account = service.getAccountById(model.getAccountId());
        if(account.isPresent()){
            AccountMapper.merge(model, account.get());
            return ResponseEntity.ok(AccountMapper.toModel(service.saveAccount(account.get())));
        }else{
            AccountMapper.toModel(service.saveAccount(AccountMapper.toEntity(model)));
            return ResponseEntity.ok().build();
        }
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @DeleteMapping("/{accountId}")
    public ResponseEntity<AccountModel> deleteAccount(@PathVariable long accountId){
        Optional<Account> account = service.getAccountById(accountId);
        if(!account.isPresent()){
            ResponseEntity.notFound().build();
        }
        service.deleteAccount(accountId);
        return ResponseEntity.ok().build();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<AccountHolderModel> fetchCustomerId(@ApiParam @PathVariable long customerId){
        Optional<Account> account = service.getAccountById(customerId);
        if (!account.isPresent())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(mapper.getCustomerById(customerId));
    }

}
