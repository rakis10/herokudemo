package com.example.demo;


//import com.example.demo.models.Transaction;
//import com.example.demo.repos.UserRepository;
//import com.example.demo.repos.WithdrawalRepository;
import com.example.demo.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionResource {

    @Autowired
    TransactionService transactionService;

    @GetMapping("/print")
    public List<Transaction> printService(){
        return transactionService.findAll();

    }
    @PostMapping("/evaluate")
    public ResponseEntity<?> evaluate(@RequestBody Transaction transaction){
        return transactionService.evaluate(transaction);
    }

    @GetMapping("/test")
    public String test(){return "connected";}





}
