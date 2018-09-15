package com.ab.multivariate.sdk.impl;

import com.ab.multivariate.sdk.context.FeatureChoiceContext;
import com.ab.multivariate.sdk.exception.FeatureSelectionException;
import com.ab.multivariate.sdk.model.ServiceSelectionResponse;
import com.ab.multivariate.sdk.service.MultiVariateService;
import com.ab.multivariate.sdk.strategy.FeatureSelector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Slf4j
@Service
public class SimpleFeatureSelector implements FeatureSelector {

    @Autowired
    private MultiVariateService multiVariateService;

    @Override
    public String getExecutionStrategy(FeatureChoiceContext context) throws FeatureSelectionException {
        if (context == null || StringUtils.isEmpty(context.getFeatureName())) {
            throw new FeatureSelectionException("Bad Context. Feature name is required. Caller Id or Caller Group Id must be provided");
        }


        String executionGroupName = "";
        if (null != context.getCallerGroupId()) {
            ServiceSelectionResponse response = multiVariateService.getExecutionServiceByCallerGroupId(context.getFeatureName(), context.getCallerGroupId());
            executionGroupName = response.getFeatureGroupId();
        } else if (null != context.getCallerId()){
            ServiceSelectionResponse response = multiVariateService.getExecutionServiceByCallerId(context.getFeatureName(), context.getCallerId());
            executionGroupName = response.getFeatureGroupId();
        }

        log.info("Execution Group Selected {}", executionGroupName);
        return executionGroupName;
    }
}
