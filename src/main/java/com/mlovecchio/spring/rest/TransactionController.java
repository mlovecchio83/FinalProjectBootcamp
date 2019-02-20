package com.mlovecchio.spring.rest;

import com.mlovecchio.spring.Payload.TransactionRequest;
import com.mlovecchio.spring.service.UserService;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/transactions")
public class TransactionController {

    @Autowired
    UserService userService;

    @PostMapping(path = "/addUserGame")
    public @ResponseBody String addNewTransactionUserGame(@RequestBody TransactionRequest transactionRequest){
        userService.saveTransactionUserGame(transactionRequest);
        return "Transaction completed";
    }

    @PostMapping(path = "/addUserMod")
    public @ResponseBody String addNewTransactionUserMod(@RequestBody TransactionRequest transactionRequest){
        userService.saveTransactionUserMod(transactionRequest);
        return "Transaction completed";
    }



}
