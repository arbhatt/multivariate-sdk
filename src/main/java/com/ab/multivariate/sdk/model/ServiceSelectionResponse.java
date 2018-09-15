package com.ab.multivariate.sdk.model;

import lombok.Data;

@Data
public class ServiceSelectionResponse {
    private String featureName;
    private String featureGroupId;
    private int featureTestExpireInDays;
}
