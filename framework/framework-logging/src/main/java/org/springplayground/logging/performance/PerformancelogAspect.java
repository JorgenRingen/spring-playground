package org.springplayground.logging.performance;

import java.lang.reflect.Method;
import java.util.Optional;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;

@Aspect
public class PerformancelogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(PerformancelogAspect.class);

    @Pointcut("execution(@org.springplayground.logging.performance.EnablePerformanceLogging * *(..)) || " +
            "execution(@(@org.springplayground.logging.performance.EnablePerformanceLogging *) * *(..)) || " +
            "within(@org.springplayground.logging.performance.EnablePerformanceLogging *) || " +
            "within(@(@org.springplayground.logging.performance.EnablePerformanceLogging *) *) || " +
            "@within(org.springplayground.logging.performance.EnablePerformanceLogging) || " +
            "@annotation(org.springplayground.logging.performance.EnablePerformanceLogging)")
    public void isPerformancelogAnnotationPresent() {
        // Pointcut for klasser eller metoder annotert med @Performancelog-annotasjon
    }

    @Around("isPerformancelogAnnotationPresent()")
    public Object log(final ProceedingJoinPoint joinPoint) throws Throwable {
        final long startTimeMillis = System.currentTimeMillis();
        final Object returnValue = joinPoint.proceed();
        final long elapsedTimeMillis = System.currentTimeMillis() - startTimeMillis;

        final Optional<Method> method = getInvokedMethod(joinPoint);

        if (method.isPresent()) {
            PerformanceLogger.logMethodInvocation(method.get(), elapsedTimeMillis);
        } else {
            LOGGER.warn("Couldn't get method for joinPoint {}", joinPoint);
        }

        return returnValue;
    }

    private Optional<Method> getInvokedMethod(final JoinPoint joinPoint) {
        return Optional.ofNullable(joinPoint)
                .filter(jp -> MethodInvocationProceedingJoinPoint.class.isAssignableFrom(jp.getClass()))
                .map(MethodInvocationProceedingJoinPoint.class::cast)
                .map(MethodInvocationProceedingJoinPoint::getSignature)
                .filter(s -> MethodSignature.class.isAssignableFrom(s.getClass()))
                .map(MethodSignature.class::cast)
                .map(MethodSignature::getMethod);
    }

}
