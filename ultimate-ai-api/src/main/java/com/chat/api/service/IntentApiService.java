package com.chat.api.service;

import com.chat.api.configuration.AuthorizationProperties;
import com.chat.api.model.IntentDto;
import com.chat.api.model.IntentServiceRequest;
import com.chat.api.model.IntentServiceResponse;
import com.ultimateai.swagger.ApiClient;
import com.ultimateai.swagger.backend.model.InlineObject;
import com.ultimateai.swagger.backend.model.InlineResponse200;
import com.ultimateai.swagger.controller.IntentsApi;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Service
public class IntentApiService {

    private AuthorizationProperties authorizationProperties;
    private IntentsApi intentsApi;

    @Autowired
    private IntentApiService(AuthorizationProperties authorizationProperties) {
        this.authorizationProperties = authorizationProperties;
        this.intentsApi = getIntentApi();
    }

    /**
     * Creates IntentsApi as a single instance and injects it to the service.
     */
    private IntentsApi getIntentApi() {
        ApiClient apiClient = new ApiClient();
        apiClient.setApiKey(authorizationProperties.getApiKey());
        return new IntentsApi(apiClient);
    }

    /**
     * Returns the intents form API.
     */
    public IntentServiceResponse getIntentList(IntentServiceRequest intentServiceRequest) {
        InlineObject inlineObject = new InlineObject();
        inlineObject.setBotId(intentServiceRequest.getBotId());
        inlineObject.setMessage(intentServiceRequest.getMessage());
        InlineResponse200 inlineResponse200 = intentsApi.intentsPost(inlineObject);
        List<IntentDto> intentList = inlineResponse200.getIntents().stream().map(o -> new IntentDto(o.getName(), o.getConfidence().doubleValue())).collect(Collectors.toList());
        return new IntentServiceResponse(intentList);
    }
}
