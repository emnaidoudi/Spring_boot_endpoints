package com.chatboty.chatboty.repositories;

import com.chatboty.chatboty.models.Intent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntentRepository extends MongoRepository<Intent,String> {
    Intent findByTag(String tag);
    boolean existsByTag(String tag);
}
