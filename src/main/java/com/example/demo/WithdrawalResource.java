package com.example.demo;


//import com.example.demo.models.Transaction;
//import com.example.demo.repos.UserRepository;
//import com.example.demo.repos.WithdrawalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WithdrawalResource {
//    @Autowired
//    WithdrawalService withdrawalService;
//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    TransactionService transactionService;
//
//    @Autowired
//    WithdrawalRepository withdrawalRepository;


//    public WithdrawalResource(UserRepository userRepository ) {
//        this.userRepository = userRepository;
//
//    }
    //@PathVariable("ip") String ip, @PathVariable("action") String action
    @GetMapping("/{ip}&{action}")
    public String evaluate(@PathVariable("ip") String ip, @PathVariable("action") String action){
//        return transactionService.evaluate(ip,action);

        return "ip";
    }
    @GetMapping("/baby")
    public String baby(){
        return "connected";
    }

//    @GetMapping(value = "/all")
//    public List<Transaction> getAll()
//    {
//        return transactionService.getTransactions();
//    }
//    @DeleteMapping(value = "/{id}")
//    public ResponseEntity<?> deleteWithdrawal(@PathVariable("id") String id ){
//        return withdrawalService.deleteWithdrawal(id);
//    }
//
//    @PostMapping("/")
//    public ResponseEntity<?> addWithdrawal(@RequestBody Withdrawal withdrawal){
//        return withdrawalService.createWithdrawal(withdrawal);
//    }
//    @PutMapping(value = "/{id}")
//    public ResponseEntity<?> updateWithdrawal(@PathVariable("id") String id, @RequestBody Withdrawal withdrawal){
//        return withdrawalService.updateWithdrawal( id,withdrawal);
//    }
//







}
