package com.example.demo.configuration;

import com.sun.istack.logging.Logger;
import java.util.Arrays;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class DemoAspect {
    private static final Logger log = Logger.getLogger(DemoAspect.class);

    // Pointcut to target all methods in your application
    @Pointcut("execution(* com.example.demo..*.*(..))")
    public void allMethods() {
    }

    // Advice to log before method execution
    @Before("allMethods()")
    public void logBeforeMethod(JoinPoint joinPoint) {
        log.info("Entering method: " + joinPoint.getSignature().toShortString() + "with argument/s: "
                + Arrays.toString(joinPoint.getArgs()));
    }

    // Advice to log after method execution
    @After("allMethods()")
    public void logAfterMethod(JoinPoint joinPoint) {
        log.info("Exiting method: " + joinPoint.getSignature().toShortString());
    }
}
