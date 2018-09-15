package com.ab.multivariate.sdk.strategy;


import com.ab.multivariate.sdk.context.ExceptionContext;

public interface ExceptionHandler {
    void onException(Exception e, ExceptionContext exceptionContext);
}
