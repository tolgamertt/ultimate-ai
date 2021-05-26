package com.chat.api.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class IntentServiceResponse {
    private List<IntentDto> intentList;
}

