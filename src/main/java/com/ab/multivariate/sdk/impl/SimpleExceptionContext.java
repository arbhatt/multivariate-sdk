package com.ab.multivariate.sdk.impl;

import com.ab.multivariate.sdk.context.ExceptionContext;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class SimpleExceptionContext implements ExceptionContext {

    List<Exception> exceptionList = new ArrayList<>();

}
