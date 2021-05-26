package com.chat.api.model;


import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class IntentDto {
    private String intent;
    private Double confidence;
}

