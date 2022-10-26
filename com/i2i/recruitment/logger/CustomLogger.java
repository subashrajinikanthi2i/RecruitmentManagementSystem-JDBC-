package com.i2i.recruitment.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.i2i.recruitment.constants.Constants;

/**
 * This is the custom logger class for track the log records
 *
 */
public class CustomLogger {
    private Logger logger;

    public CustomLogger(Class<?> className) {
	logger = LogManager.getLogger(className);
    }

    public CustomLogger(String name) {
	logger = LogManager.getLogger(name);
    }
    
    public void info(String message){
        logger.info(message);
    }
    
    public void trace(String message){
        logger.trace(message);
    }
    
    public void debug(String message){
        logger.debug(message);
    }
    
    public void warn(String message){
        logger.warn(message);
    }
    
    public void fatal(String message){
        logger.fatal(message);
    }
    
    public void error(String message){
        logger.error(Constants.getErrorCode().get(message));
    }

    public void error(String message, Throwable clause){
        logger.error(message, clause);
    }

}