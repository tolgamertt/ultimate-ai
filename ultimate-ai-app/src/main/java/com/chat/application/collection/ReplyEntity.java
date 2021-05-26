package com.chat.application.collection;

import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@AllArgsConstructor
@Document(collection = "replies")
public class ReplyEntity {
    @Id
    private String intent;
    private String reply;
}