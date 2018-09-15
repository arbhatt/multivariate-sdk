package com.ab.multivariate.sdk.service;

import com.ab.multivariate.sdk.exception.FeatureSelectionException;
import com.ab.multivariate.sdk.model.ServiceSelectionResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class MultiVariateService {

    private static final String GET_BY_CALLER_ID = "/feature/{feature-name}/callerId/{id}";
    private static final String GET_BY_CALLER_GROUP_ID = "/feature/{feature-name}/callerGroupId/{id}";

    @Autowired
    private RestTemplate restTemplate;

    @Value("${base.mv.service.url}")
    private String baseMvServiceUrl;


    public ServiceSelectionResponse getExecutionServiceByCallerId(String featureName, String callerId) throws FeatureSelectionException {
        try {
            ResponseEntity<ServiceSelectionResponse> response = restTemplate.getForEntity(buildUrl(baseMvServiceUrl, GET_BY_CALLER_ID), ServiceSelectionResponse.class, featureName, callerId);
            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new FeatureSelectionException(response.getBody().toString());
            }

            return response.getBody();
        } catch (HttpClientErrorException e) {
            throw new FeatureSelectionException(e);
        }
    }

    private String buildUrl(String base, String path) {
        return base + path;
    }

    public ServiceSelectionResponse getExecutionServiceByCallerGroupId(String featureName, String callerGroupId) throws FeatureSelectionException {
        try {
            ResponseEntity<ServiceSelectionResponse> response = restTemplate.getForEntity(buildUrl(baseMvServiceUrl, GET_BY_CALLER_GROUP_ID), ServiceSelectionResponse.class, featureName, callerGroupId);
            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new FeatureSelectionException(response.getBody().toString());
            }

            return response.getBody();
        } catch (HttpClientErrorException e) {
            throw new FeatureSelectionException(e);
        }
    }

}
