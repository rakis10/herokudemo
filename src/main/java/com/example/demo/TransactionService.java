package com.example.demo;

import com.example.demo.models.Transaction;
import com.example.demo.repos.TransactionRepository;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
        List<Transaction> transactions = transactionRepository.findPositionalParameter(transaction.getLastIP(), Sort.by(Sort.Direction.DESC, "date"));
        transactionRepository.save(transaction);
        int i = 0;
        int count_same_action = 0;
        while (transactions.get(i) != null && i < 5){
            if(transactions.get(i).getLastTransaction().equals(transaction.getLastTransaction())){
              count_same_action++;
            }
            i++;
        }

        if (count_same_action<1){
            return "high risk";
        }else if (count_same_action<3){
            return "medium risk";
        }else {
            return "low risk";
        }
    }

    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }
}
