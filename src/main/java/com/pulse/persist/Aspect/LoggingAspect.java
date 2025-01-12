package com.pulse.persist.Aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final static Logger logger = LogManager.getLogger(LoggingAspect.class);

    @Before("execution(* com.pulse.persist.Controller.OrderController.*(..))")
    public void beforeLogger(){
            logger.debug("Aspect logging");
    }
}
