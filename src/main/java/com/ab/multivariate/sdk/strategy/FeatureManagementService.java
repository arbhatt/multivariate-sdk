package com.ab.multivariate.sdk.strategy;

import com.ab.multivariate.sdk.context.ExceptionContext;
import com.ab.multivariate.sdk.context.FeatureChoiceContext;
import com.ab.multivariate.sdk.context.FeatureExecutionContext;
import com.ab.multivariate.sdk.context.FeatureManagementContext;
import com.ab.multivariate.sdk.exception.FeatureExecutionException;

public interface FeatureManagementService {

    public void execute(FeatureManagementContext context) throws FeatureExecutionException;


    public void onException(ExceptionContext exceptionContext) throws FeatureExecutionException;
}
