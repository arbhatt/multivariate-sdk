package com.ab.multivariate.sdk.strategy;

import com.ab.multivariate.sdk.context.FeatureExecutionContext;
import com.ab.multivariate.sdk.exception.FeatureExecutionException;

public interface FeatureExecutor {

	void executeFeature(FeatureExecutionContext featureExecutionContext) throws FeatureExecutionException ;
}
