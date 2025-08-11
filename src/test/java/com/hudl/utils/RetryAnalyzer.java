package com.hudl.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import java.util.logging.Logger;

public class RetryAnalyzer implements IRetryAnalyzer {

    private static final Logger logger = Logger.getLogger(RetryAnalyzer.class.getName());
    private int retryCount = 0;
    private static final int maxRetryCount = 2; // retry 2 times

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            logger.info("Retrying test " + result.getName() + " | Attempt " + (retryCount + 1));
            return true;
        }
        return false;
    }
}
