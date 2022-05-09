package com.example.demo;

import com.example.demo.models.Blacklist;
import com.example.demo.models.Transaction;
import com.example.demo.repos.BlacklistRepository;
import com.example.demo.repos.TransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private final BlacklistRepository blacklistRepository;

    public TransactionService(TransactionRepository transactionRepository, BlacklistRepository blacklistRepository){
        this.transactionRepository = transactionRepository;
        this.blacklistRepository = blacklistRepository;
    }

    public ResponseEntity<?> evaluate(Transaction transaction){
        String msg = "";
        transaction.setDatum(new Date().toString());
        boolean validToken = false;
        boolean flagCountry = false;
        boolean flagDevice = false;
        List<Blacklist> blacklists = blacklistRepository.findAll();

        for (Blacklist b:blacklists
             ) {
            if (b.getMeno().equals(transaction.getKrajina())   ){
                return new ResponseEntity<>("blacklist", HttpStatus.OK);
            }
        }

        List<Transaction> transactions = transactionRepository.findByEmailOrderByDatumDesc(transaction.getEmail() );
        if (transactions.size() <=0){
            // prvy login
            transactionRepository.save(transaction);
            return new ResponseEntity<>("high risk", HttpStatus.OK);

        }

        Transaction lastTransaction  = transactions.get(0);

        String dateString = lastTransaction.getDatum();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
        Date d = new Date();
        try {
            d = sdf.parse(dateString) ;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        LocalDateTime l = LocalDateTime.now().minusWeeks(1);
        ZonedDateTime zdt = l.atZone(ZoneId.systemDefault());
        Date now = Date.from(zdt.toInstant());

        // viac ako 7 dni  od posledneho loginu
        if(!d.after(now) ) validToken = true;

        //  different device
        if (!lastTransaction.getOperatingSystem().equals(transaction.getOperatingSystem()) ||
                !lastTransaction.getBrowser().equals(transaction.getBrowser()) ||
        !lastTransaction.getBrowserVersion().equals(transaction.getBrowserVersion()) ) flagDevice = true;

        //  different country
        if (!lastTransaction.getKrajina().equals(transaction.getKrajina())) flagCountry = true;

        // vyhodnocovanie

        // TODO ak su oboje pridat do banu?
        if(flagCountry || flagDevice) {
            msg = "high risk";
        }else if (validToken){
            msg = "medium risk";
        }else {
            msg = "low risk";
        }
        transactionRepository.save(transaction);
        return new ResponseEntity<>(  msg, HttpStatus.OK);
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
