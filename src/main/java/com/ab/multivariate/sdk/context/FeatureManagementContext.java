package com.ab.multivariate.sdk.context;

import com.ab.multivariate.sdk.strategy.FeatureExecutor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class FeatureManagementContext {

    @Getter
    @Setter
    private FeatureChoiceContext featureChoiceContext;

    @Getter
    @Setter
    private FeatureExecutionContext featureExecutionContext;

    private Map<String, List<FeatureExecutor>> executionStrategyMapper;

    public void resetExecutionStrategy() {
        executionStrategyMapper = new HashMap<>();
    }

    public FeatureManagementContext addExecutionStrategyToGroup(String groupId, FeatureExecutor featureExecutor) {
        if (executionStrategyMapper.containsKey(groupId)) {
            executionStrategyMapper.put(groupId, Arrays.asList(featureExecutor));
        } else {
            executionStrategyMapper.get(groupId).add(featureExecutor);
        }
        log.info("Chained {} execution streties to group {}", executionStrategyMapper.get(groupId).size(), groupId);
        return this;
    }

    public List<FeatureExecutor> getExecutorForStrategy(String strategyName) {
        return executionStrategyMapper.get(strategyName);
    }
}
