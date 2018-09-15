package com.ab.multivariate.sdk.exception;


import java.util.LinkedList;
import java.util.List;

public class FeatureExecutionException extends Exception {

	private List<Throwable> re;
	public FeatureExecutionException(Exception e) {
		super(e);
	}

	public FeatureExecutionException(String e) {
		super(e);
	}

	public FeatureExecutionException(String m, Exception e) {
		super(m, e);
	}

	public FeatureExecutionException(String m, List<Exception> e) {
		super(m);
		re = new LinkedList<>();
		e.forEach(x -> re.add(x));
	}
}
