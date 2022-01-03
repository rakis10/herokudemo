package com.example.demo;

import com.example.demo.models.Transaction;
import com.example.demo.repos.TransactionRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }

    public ResponseEntity<?> evaluate(Transaction transaction){
        int count_same_action = 0;
        transaction.setDate(new Date());
        List<Transaction> transactions = transactionRepository.findPositionalParameter(transaction.getLastIP(), Sort.by(Sort.Direction.DESC, "date"));
        transactionRepository.save(transaction);
        int i = 0;
        while (transactions.get(i) != null && i < 5){
            if(transactions.get(i).getLastTransaction().equals(transaction.getLastTransaction())){
              count_same_action++;
            }
            i++;
        }

        if (count_same_action<1){
            return new ResponseEntity<>("high risk", HttpStatus.OK);
        }else if (count_same_action<3){
            return new ResponseEntity<>("medium risk", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("low risk", HttpStatus.OK);
        }
    }

    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }
}
