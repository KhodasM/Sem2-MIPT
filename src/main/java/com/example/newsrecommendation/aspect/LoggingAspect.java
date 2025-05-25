package com.example.newsrecommendation.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Aspect
@Component
public class LoggingAspect {

  private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

  @Before("execution(* com.example.newsrecommendation.controllers.*.*.*( .. ))")
  public void logBefore(JoinPoint joinPoint){
    log.debug("Before calling method: {}", joinPoint.getSignature().getName());
  }

  @Around("execution(* com.example.newsrecommendation.controllers.*.*.*( .. ))")
  public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
    Instant start = Instant.now();
    Object result = joinPoint.proceed();
    long duration = Instant.now().toEpochMilli() - start.toEpochMilli();
    log.debug("Method "+ joinPoint.getSignature().getName()+" completed for "+duration+" ms");
    return result;
  }
}