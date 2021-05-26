package com.chat.application.repository;

import com.chat.application.collection.ReplyEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReplyRepository extends MongoRepository<ReplyEntity, String> {

    ReplyEntity findReplyByIntent(String intent);

}