package com.ab.multivariate.sdk.strategy;


import com.ab.multivariate.sdk.context.FeatureChoiceContext;
import com.ab.multivariate.sdk.exception.FeatureSelectionException;

public interface FeatureSelector {
	
	String getExecutionStrategy(FeatureChoiceContext ctx) throws FeatureSelectionException;

}
