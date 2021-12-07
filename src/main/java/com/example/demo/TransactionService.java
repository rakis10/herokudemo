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
//    Logger logger = LoggerFactory.getLogger(LoggingController.class);

    public TransactionService(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }
    public String evaluate(String ip, String akcia) {
//        List<Transaction> transactions = new ArrayList<Transaction>();
        try {
            Transaction transaction = new Transaction(ip,akcia, new Date());
            transactionRepository.save(transaction);
        }catch (Exception e){
            System.out.println(e.toString());
            return (e.toString());
        }

        return "schvalene";
    }
    public String eva(Transaction transaction){
        transaction.setDate(new Date());
        transactionRepository.save(transaction);
        return "schvalene";
    }

    public String evaluate(Transaction transaction) {
        List<Transaction> transactions = new ArrayList<Transaction>();
        try {
            transactions =  transactionRepository.findAll();
        }catch (Exception e){
            System.out.println(e.toString());
        }


        transaction.setDate(new Date());
        transactionRepository.save(transaction);

        return "schvalene";

    }

    public String printService(String ip, String action) {
        Transaction transaction = new Transaction(ip,action, new Date());
        transactionRepository.save(transaction);
        return "schvalene";
    }
//    public ResponseEntity<?> getWithdrawal(String id) {
//        Withdrawal w ;
//        try {
//            checkExist(id);
//            w=  withdrawalRepository.findById(id).get();
//        }catch (NoSuchElementException e){
//            return new ResponseEntity<>("No withdrawal with ID + id", HttpStatus.NOT_FOUND);
//        }
//        catch (WithdrawalException e){
//            System.out.println(e.toString());
//            return new ResponseEntity<>("No withdrawal with ID + id", HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(w, HttpStatus.NOT_FOUND);
//
//
//    }
//
//    public ResponseEntity<?> createWithdrawal(Withdrawal withdrawal) {
//        Long sum = 0L;
//        for(Money m : withdrawal.getMoney()){
//            sum += m.getValue() + m.getPieces();
//
//        }
//        withdrawal.setPrice(sum);
//        try {
//            chechWithdrawal(withdrawal);
//        }catch (WithdrawalException e){
//            System.out.println(e.toString());
//            return new ResponseEntity<>(e, HttpStatus.NOT_ACCEPTABLE);
//        }
//
//        withdrawalRepository.save(withdrawal);
//        return new ResponseEntity<>(withdrawal, HttpStatus.CREATED);
//    }





//    public ResponseEntity<?> updateWithdrawal(String id, Withdrawal withdrawal) {
//        withdrawal.setId(id);
//        withdrawalRepository.save(withdrawal);
//        return new ResponseEntity<>(withdrawal,HttpStatus.OK);
//    }
}
