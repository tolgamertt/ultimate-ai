package com.chat.application.unit.service;

import com.chat.api.model.IntentServiceRequest;
import com.chat.application.collection.ReplyEntity;
import com.chat.application.model.CustomerRequest;
import com.chat.application.model.CustomerResponse;
import com.chat.application.repository.ReplyRepository;
import com.chat.application.service.IntentService;
import com.chat.application.service.ReplyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class ReplyServiceTest {


    public static final String SAMPLE_BOT_ID = "fk4dk45";
    public static final String SAMPLE_MESSAGE = "Hello!";
    public static final String SAMPLE_INTENT = "Greeting";
    public static final String SAMPLE_REPLY = "Hello! How can I help you?";
    public static final String SAMPLE_DEFAULT_INTENT = "default";
    public static final String SAMPLE_DEFAULT_REPLY = "reply";

    @InjectMocks
    ReplyService replyService;

    @InjectMocks
    CustomerRequest customerRequest;

    @Mock
    IntentService intentService;

    @Mock
    ReplyRepository replyRepository;

    @BeforeEach
    void onSetUp() {
        Mockito.when(intentService.getMostLikelyIntent(new IntentServiceRequest(SAMPLE_BOT_ID, SAMPLE_MESSAGE))).thenReturn(SAMPLE_INTENT);
        customerRequest.setBotId(SAMPLE_BOT_ID);
        customerRequest.setMessage(SAMPLE_MESSAGE);
    }

    @Test
    void replyToCustomerTest_01() {
        Mockito.when(replyRepository.findReplyByIntent(SAMPLE_INTENT)).thenReturn(new ReplyEntity(SAMPLE_INTENT, SAMPLE_REPLY));
        CustomerResponse testResponse = replyService.replyToCustomer(customerRequest);
        Assertions.assertNotNull(testResponse);
        Assertions.assertEquals(SAMPLE_REPLY, testResponse.getReply());
    }

    @Test
    void replyToCustomerTest_02() {
        Mockito.when(replyRepository.findReplyByIntent(SAMPLE_INTENT)).thenReturn(null);
        Mockito.when(replyRepository.findReplyByIntent(SAMPLE_DEFAULT_INTENT)).thenReturn(new ReplyEntity(SAMPLE_DEFAULT_INTENT, SAMPLE_DEFAULT_REPLY));
        CustomerResponse testResponse = replyService.replyToCustomer(customerRequest);
        Assertions.assertNotNull(testResponse);
        Assertions.assertEquals(SAMPLE_DEFAULT_REPLY, testResponse.getReply());

    }

}
