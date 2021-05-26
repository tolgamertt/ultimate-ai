package com.chat.application.controller;


import com.chat.application.model.CustomerRequest;
import com.chat.application.model.CustomerResponse;
import com.chat.application.service.ReplyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController
public class CustomerReplyController {

    private final ReplyService replyService;

    @PostMapping(value = "/chat-bot")
    public ResponseEntity<CustomerResponse> chatBot(@RequestBody CustomerRequest customerRequest) {
        final CustomerResponse customerResponse = replyService.replyToCustomer(customerRequest);
        return new ResponseEntity<>(customerResponse, HttpStatus.OK);
    }
}
