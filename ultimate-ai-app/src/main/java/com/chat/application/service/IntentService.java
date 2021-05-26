package com.chat.application.service;

import com.chat.api.model.IntentDto;
import com.chat.api.model.IntentServiceRequest;
import com.chat.api.model.IntentServiceResponse;
import com.chat.api.service.IntentApiService;
import com.chat.application.configuration.ApplicationProperties;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
@Service
public class IntentService {

    private final IntentApiService intentApiService;
    private final ApplicationProperties applicationProperties;

    private static final String DEFAULT = "default";

    /**
     * Returns the most likely intent or default if there is no intent over threshold.
     */
    public String getMostLikelyIntent(IntentServiceRequest intentServiceRequest) {
        final IntentServiceResponse intentServiceResponse = intentApiService.getIntentList(intentServiceRequest);
        return confidenceDecider(intentServiceResponse);
    }

    /**
     * Checks if the best intent good enough.
     */
    private String confidenceDecider(IntentServiceResponse intentServiceResponse) {
        // Sort the list in order to compare best intent with the second best.
        List<IntentDto> intentsList = intentServiceResponse.getIntentList();
        intentsList.sort(Comparator.comparingDouble(IntentDto::getConfidence).reversed());

        // Returns if there is only one intent or checks if the best intent is distinguished enough.
        if ((intentsList.size() == 1)
                || (intentsList.size() > 1 && intentsList.get(0).getConfidence() > (intentsList.get(1).getConfidence() * applicationProperties.getConfidencePercentage()))) {
            return intentsList.get(0).getIntent();
        } else {
            return DEFAULT;
        }
    }
}
