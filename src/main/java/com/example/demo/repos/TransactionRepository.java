package com.example.demo.repos;

import com.example.demo.models.Transaction;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
//    List<Transaction> find(Query query, Class<Transaction> transactionClass);

    @Query("{'ipAdresa' : ?0}")
    List<Transaction> findPositionalParameter(String ipAdresa, Sort date);
}
