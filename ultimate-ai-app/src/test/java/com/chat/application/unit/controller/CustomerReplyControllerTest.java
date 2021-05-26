package com.chat.application.unit.controller;

import com.chat.application.controller.CustomerReplyController;
import com.chat.application.model.CustomerRequest;
import com.chat.application.model.CustomerResponse;
import com.chat.application.service.ReplyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class CustomerReplyControllerTest {


    public static final String SAMPLE_BOT_ID = "fk4dk45";
    public static final String SAMPLE_MESSAGE = "Hello!";
    public static final String SAMPLE_REPLY = "Hello! How can I help you?";

    @InjectMocks
    CustomerReplyController customerReplyController;

    @InjectMocks
    CustomerRequest customerRequest;

    @InjectMocks
    CustomerResponse customerResponse;

    @Mock
    ReplyService replyService;


    @BeforeEach
    public void onSetUp() {
        customerRequest.setBotId(SAMPLE_BOT_ID);
        customerRequest.setMessage(SAMPLE_MESSAGE);
    }

    @Test
    void chatBotTest_01() {

        customerResponse.setReply(SAMPLE_REPLY);
        Mockito.when(replyService.replyToCustomer(customerRequest)).thenReturn(customerResponse);
        ResponseEntity<CustomerResponse> response = customerReplyController.chatBot(customerRequest);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(response.getBody().getReply(), customerResponse.getReply());
    }

}
