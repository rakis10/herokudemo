package com.example.demo.repos;

import com.example.demo.models.Blacklist;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BlacklistRepository  extends MongoRepository<Blacklist, String> {
}
