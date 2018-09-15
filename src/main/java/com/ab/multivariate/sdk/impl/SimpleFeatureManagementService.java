package com.ab.multivariate.sdk.impl;

import com.ab.multivariate.sdk.context.ExceptionContext;
import com.ab.multivariate.sdk.context.FeatureChoiceContext;
import com.ab.multivariate.sdk.context.FeatureManagementContext;
import com.ab.multivariate.sdk.exception.FeatureExecutionException;
import com.ab.multivariate.sdk.strategy.ExceptionHandler;
import com.ab.multivariate.sdk.strategy.FeatureExecutor;
import com.ab.multivariate.sdk.strategy.FeatureManagementService;
import com.ab.multivariate.sdk.strategy.FeatureSelector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SimpleFeatureManagementService implements FeatureManagementService {

    @Autowired
    private FeatureSelector featureSelector;

    @Autowired
    FeatureExecutor featureExecutor;

    @Autowired
    ExceptionHandler exceptionHandler;

    @Override
    public void execute(FeatureManagementContext context) throws FeatureExecutionException {
        SimpleExceptionContext exceptionContext = new SimpleExceptionContext();
        try {
            FeatureChoiceContext choiceContext = context.getFeatureChoiceContext();
            log.info("Determine Execution strategy for feature {}, caller {}, caller group {}",
                    choiceContext.getFeatureName(), choiceContext.getCallerId(), choiceContext.getCallerGroupId());
            String executionStrategy = featureSelector.getExecutionStrategy(choiceContext);

            List<FeatureExecutor> executorList = context.getExecutorForStrategy(executionStrategy);
            executorList.stream().forEach(x -> {
                try {
                    x.executeFeature(context.getFeatureExecutionContext());
                } catch (Exception e) {
                    exceptionContext.getExceptionList().add(e);
                }
            });
        } catch (Exception e) {
            throw new FeatureExecutionException(e);
        }

        onException(exceptionContext);

    }

    @Override
    public void onException(ExceptionContext exceptionContext) throws FeatureExecutionException{
        SimpleExceptionContext simpleExceptionContext = (SimpleExceptionContext) exceptionContext;
        boolean isBreakingException = simpleExceptionContext.getExceptionList().stream().anyMatch( e -> e instanceof RuntimeException);
        if (isBreakingException) {
            throw new FeatureExecutionException("Unrecoverable Error occurred", simpleExceptionContext.getExceptionList());
        }
    }
}

