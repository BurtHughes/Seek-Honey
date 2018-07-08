package com.penguin.find.seekhoney.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log
{
    static Logger logger = LoggerFactory.getLogger(Log.class);
    
    public static void debug(String msg) {
        logger.debug(msg);
    }
    
    public static void info(String msg) {
        logger.info(msg);
    }

    public static void error(String msg) {
        logger.error(msg);
    }
}
