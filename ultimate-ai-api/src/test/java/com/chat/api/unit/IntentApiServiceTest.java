package com.chat.api.unit;

import com.chat.api.model.IntentServiceRequest;
import com.chat.api.model.IntentServiceResponse;
import com.chat.api.service.IntentApiService;
import com.ultimateai.swagger.backend.model.InlineObject;
import com.ultimateai.swagger.backend.model.InlineResponse200;
import com.ultimateai.swagger.backend.model.InlineResponse200Intents;
import com.ultimateai.swagger.controller.IntentsApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class IntentApiServiceTest {

    public static final String SAMPLE_BOT_ID = "fk4dk45";
    public static final String SAMPLE_MESSAGE = "Hello!";
    public static final String SAMPLE_INTENT = "Greeting";
    public static final BigDecimal SAMPLE_CONFIDENCE = BigDecimal.ONE;


    @InjectMocks
    private IntentApiService IntentApiService = new IntentApiService();

    @Mock
    private IntentsApi intentsApi;


    @Test
    void getIntentListTest_01() {

        InlineObject inlineObject = new InlineObject();
        inlineObject.setBotId(SAMPLE_BOT_ID);
        inlineObject.setMessage(SAMPLE_MESSAGE);

        List<InlineResponse200Intents> intentList = new ArrayList<>();
        InlineResponse200Intents intent = new InlineResponse200Intents();
        intent.setName(SAMPLE_INTENT);
        intent.setConfidence(SAMPLE_CONFIDENCE);
        intentList.add(intent);
        InlineResponse200 inlineResponse200 = new InlineResponse200();
        inlineResponse200.setIntents(intentList);
        Mockito.when(intentsApi.intentsPost(inlineObject)).thenReturn(inlineResponse200);

        IntentServiceRequest testRequest = new IntentServiceRequest(SAMPLE_BOT_ID, SAMPLE_MESSAGE);
        IntentServiceResponse intentServiceResponse = IntentApiService.getIntentList(testRequest);
        Assertions.assertNotNull(intentServiceResponse);
        Assertions.assertNotNull(intentServiceResponse.getIntentList());
        Assertions.assertFalse(intentServiceResponse.getIntentList().isEmpty());
    }

}
