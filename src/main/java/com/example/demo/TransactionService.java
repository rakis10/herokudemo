package com.example.demo;

import com.example.demo.models.Transaction;
import com.example.demo.repos.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }

    public String evaluate(Transaction transaction){
        transaction.setDate(new Date());
        transactionRepository.save(transaction);
        return "schvalene";
    }

    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }
}
