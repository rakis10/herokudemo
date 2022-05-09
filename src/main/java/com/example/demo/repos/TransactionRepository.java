package com.example.demo.repos;

import com.example.demo.models.Transaction;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, Long> {
    List<Transaction> findByEmailOrderByDatumDesc( String email);
//    List<Transaction> find(Query query, Class<Transaction> transactionClass);

//    @Query("{'ipAdresa' : ?0}")
//    List<Transaction> findPositionalParameter(String ipAdresa, Sort date);

//    @Query("{'email' : ?0}")


//    @Query("{'operatingSystem' : :#{#operatingSystem}}")
//    List<Transaction> findNamedParameters(@Param("operatingSystem") String operatingSystem);


//    List<Transaction> findByOperatingSystemSortByDate(String operatingSystem);

//    @Query("{operatingSystem:'?0'}")
//    List<Transaction> findByOperatingSystem(String operatingSystem);

//    @Query("{'operatingSystem' : :operatingSystem }")
//    List<Transaction> findTransactionsByOperatingSystem(@Param("operatingSystem") String operatingSystem);
//        @Query("{ 'operatingSystem' : ?0 }")

//        @Query(value = "{'operatingSystem' : ?0}")
//        Transaction findTransactionByOperatingSystem(String operatingSystem);
//
//        @Query("{ operatingSystem : ?0}")
//        List<Transaction> getTransactionsByOperatingSystem(String operatingSystem);

//    @Query(value = "{id : ?0}")
//    Book findBookById(int id);

//    @Query("{'email' : :#{#email}}")
//    List<Transaction> findNamedParameters(@Param("email") String author);
//
//    @Query("{'operatingSystem' : :#{#operatingSystem}}")
//    List<Transaction> findNamedParameterss(@Param("operatingSystem") String author);

//    @Query("{'operatingSystem' : :#{#operatingSystem}}")
//    List<Transaction> findByOperatingSystem(@Param("operatingSystem") String operatingSystem);
}
