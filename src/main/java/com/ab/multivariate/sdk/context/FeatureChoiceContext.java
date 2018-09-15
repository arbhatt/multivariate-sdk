package com.ab.multivariate.sdk.context;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class FeatureChoiceContext {

	private String callerId;
	private String callerGroupId;
	private String featureName;

	public enum FeatureCallerType {CALLER_GROUP_TYPE, CALLER_TYPE};

	public static FeatureChoiceContext withCallerType(String featureName, String id, FeatureCallerType type) {
		FeatureChoiceContext context = new FeatureChoiceContext(featureName);
		if (FeatureCallerType.CALLER_GROUP_TYPE.equals(type)) {
			context.callerGroupId = id;
		}

		else if (FeatureCallerType.CALLER_TYPE.equals(type)) {
			context.callerId = id;
		}


		else {
			throw new RuntimeException("Bad initiation");
		}

		return context;
	}

	private FeatureChoiceContext(String featureName) {
		this.featureName = featureName;
	}

}