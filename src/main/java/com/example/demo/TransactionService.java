package com.example.demo;

import com.example.demo.models.Blacklist;
import com.example.demo.models.Transaction;
import com.example.demo.repos.BlacklistRepository;
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

    private final BlacklistRepository blacklistRepository;

    public TransactionService(TransactionRepository transactionRepository, BlacklistRepository blacklistRepository){
        this.transactionRepository = transactionRepository;
        this.blacklistRepository = blacklistRepository;
    }

    public ResponseEntity<?> evaluate(Transaction transaction){
        int count_same_action = 0;
        transaction.setDatum(new Date());
        List<Blacklist> blacklists = blacklistRepository.findAll();

        for (Blacklist b:blacklists
             ) {
            if (b.getMeno().equals(transaction.getKrajina())   ){
                return new ResponseEntity<>("blacklist", HttpStatus.OK);
            }
        }

//        List<Transaction> transactions = transactionRepository.findPositionalParameter(transaction.getIpAdresa(), Sort.by(Sort.Direction.DESC, "date"));
        List<Transaction> transactions = transactionRepository.findNamedParameters(transaction.getEmail());
        if(transactions.size() > 0){
            // porovnanie s poslednou transakciou
            Transaction last = transactions.get(0);
            if(last.getKrajina().equals(transaction.getKrajina())){
                int score = getScore(last, transaction);

                if(score < 2 ){
                    return new ResponseEntity<>("low risk", HttpStatus.OK);
                }else { return new ResponseEntity<>("medium risk", HttpStatus.OK);}
            }else{
                return new ResponseEntity<>("high risk", HttpStatus.OK);

            }
//            if(!last.getOperatingSystem().equals(transaction.getOperatingSystem()) || !last.getBrowser().equals(transaction.getBrowser()) ){
//
//            }else {
//                //
//                return new ResponseEntity<>("medium risk", HttpStatus.OK);
//            }

        }else{
            // prvy krat tento mail
            transactionRepository.save(transaction);
            return new ResponseEntity<>("high risk", HttpStatus.OK);
        }

//        int i = 0;


//        while (  i < transactions.size()  && i < 5){
//            if(transactions.get(i).getPozadovanaTransakcia().equals(transaction.getPozadovanaTransakcia())){
//              count_same_action++;
//            }
//            i++;
//        }
//
//        if (count_same_action<1){
//            return new ResponseEntity<>("high risk", HttpStatus.OK);
//        }else if (count_same_action<3){
//            return new ResponseEntity<>("medium risk", HttpStatus.OK);
//        }else {
//            return new ResponseEntity<>("low risk", HttpStatus.OK);
//        }
//        return new ResponseEntity<>("asi nic", HttpStatus.OK);
    }

    private int getScore(Transaction last, Transaction transaction) {
        int score = 0;
        if(!last.getOperatingSystem().equals(transaction.getOperatingSystem())){ score++;}
        if(!last.getBrowser().equals(transaction.getBrowser())){score++;}
        if(!last.getIpAdresa().equals(transaction.getIpAdresa())){score++;}
        return score;
    }

    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }
}
