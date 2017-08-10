package cn.niceabc.springbootadminclient;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ServiceMonitor {

    @Autowired
    private CounterService counterService;

    @Autowired
    private GaugeService gaugeService;

    @Before("execution(* cn.niceabc.springbootadminclient.controller.*.*(..))")
    public void countServiceInvoke(JoinPoint joinPoint) {
        counterService.increment(joinPoint.getSignature()+"");
    }

    @Around("execution(* cn.niceabc.springbootadminclient.controller.*.*(..))")
    public Object latencyService(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = pjp.proceed();
        long end = System.currentTimeMillis();
        gaugeService.submit(pjp.getSignature().toString(), end - start);

        return result;
    }

}
