package jp.go.fss.btr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest{
    private static Logger logger = LoggerFactory.getLogger(LogTest.class);


    public void logTest(){
        System.out.println("[start]logTest");
        logger.info("infoLog");
        logger.error("error");
        logger.warn("warn");
        logger.debug("debug");
        logger.trace("trace");
        System.out.println("[end]logTest");
    }



}