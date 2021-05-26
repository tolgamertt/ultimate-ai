package com.chat.application.unit.service;


import com.chat.api.model.IntentDto;
import com.chat.api.model.IntentServiceRequest;
import com.chat.api.model.IntentServiceResponse;
import com.chat.api.service.IntentApiService;
import com.chat.application.service.IntentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
class IntentServiceTest {


    public static final String SAMPLE_BOT_ID = "fk4dk45";
    public static final String SAMPLE_MESSAGE = "Hello!";
    public static final String SAMPLE_INTENT = "Greeting";
    public static final String SAMPLE_DEFAULT_INTENT = "default";
    public static final double SAMPLE_CONFIDENCE = 1.0;

    @InjectMocks
    IntentService intentService;

    @InjectMocks
    IntentServiceRequest testRequest;

    @Spy
    ArrayList<IntentDto> intentList;

    @Mock
    IntentApiService IntentApiService;


    @BeforeEach
    void onSetUp() {
        testRequest.setBotId(SAMPLE_BOT_ID);
        testRequest.setMessage(SAMPLE_MESSAGE);
    }

    @Test
    void getMostLikelyIntentTest_01() {

        intentList.add(new IntentDto(SAMPLE_INTENT, SAMPLE_CONFIDENCE));
        Assertions.assertEquals(SAMPLE_INTENT, getMockMakeCall());
    }

    @Test
    void getMostLikelyIntentTest_02() {
        Assertions.assertEquals(SAMPLE_DEFAULT_INTENT, getMockMakeCall());
    }

    private String getMockMakeCall() {
        Mockito.when(IntentApiService.getIntentList(testRequest)).thenReturn(new IntentServiceResponse(intentList));
        return intentService.getMostLikelyIntent(testRequest);
    }

}
