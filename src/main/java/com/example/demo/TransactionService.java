package com.example.demo;

import com.example.demo.models.Blacklist;
import com.example.demo.models.Transaction;
import com.example.demo.repos.BlacklistRepository;
import com.example.demo.repos.TransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private final BlacklistRepository blacklistRepository;

    public TransactionService(TransactionRepository transactionRepository, BlacklistRepository blacklistRepository){
        this.transactionRepository = transactionRepository;
        this.blacklistRepository = blacklistRepository;
    }

    public ResponseEntity<?> evaluate(Transaction transaction){
        int count_same_action = 0;
        transaction.setDatum(new Date().toString());
        List<Blacklist> blacklists = blacklistRepository.findAll();

        for (Blacklist b:blacklists
             ) {
            if (b.getMeno().equals(transaction.getKrajina())   ){
                return new ResponseEntity<>("blacklist", HttpStatus.OK);
            }
        }

        List<Transaction> transactions = transactionRepository.findByEmail(transaction.getEmail() );




        return new ResponseEntity<>(  "low risk", HttpStatus.OK);
//        if(transactions.size() > 0){
//            // porovnanie s poslednou transakciou
//            Transaction last = transactions.get(0);
//            if(last.getKrajina().equals(transaction.getKrajina())){
//                int score = getScore(last, transaction);
//                // FIXME neulozenie
//                // transactionRepository.save(transaction);
//                if(score < 2 ){
//                    return new ResponseEntity<>("low risk", HttpStatus.OK);
//                }else { return new ResponseEntity<>("medium risk", HttpStatus.OK);}
//            }else{
//                return new ResponseEntity<>("high risk", HttpStatus.OK);
//
//            }


//        }else{
//            // prvy krat tento mail
//            // FIXME neulozenie
////            transactionRepository.save(transaction);
//            return new ResponseEntity<>("high risk", HttpStatus.OK);
//        }


    }

    private int getScore(Transaction last, Transaction transaction) {
        int score = 0;
        if(!last.getOperatingSystem().equals(transaction.getOperatingSystem())){ score++;}
        if(!last.getBrowser().equals(transaction.getBrowser())){score++;}
        if(!last.getIpAdresa().equals(transaction.getIpAdresa())){score++;}
        return score;
    }

    public long findAll() {
        return   transactionRepository.count();
    }
}
