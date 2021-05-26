package com.chat.application.service;

import com.chat.api.model.IntentServiceRequest;
import com.chat.application.collection.ReplyEntity;
import com.chat.application.model.CustomerRequest;
import com.chat.application.model.CustomerResponse;
import com.chat.application.repository.ReplyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final IntentService intentService;

    private static final String DEFAULT = "default";

    /**
     * Returns the reply from database according to most likely intent. Returns default answer if intent is not found in db.
     */
    public CustomerResponse replyToCustomer(CustomerRequest customerRequest) {
        final String mostLikelyIntent = intentService.getMostLikelyIntent(new IntentServiceRequest(customerRequest.getBotId(), customerRequest.getMessage()));
        final ReplyEntity reply = replyRepository.findReplyByIntent(mostLikelyIntent);
        return new CustomerResponse(reply == null ? replyRepository.findReplyByIntent(DEFAULT).getReply() : reply.getReply());
    }


}
