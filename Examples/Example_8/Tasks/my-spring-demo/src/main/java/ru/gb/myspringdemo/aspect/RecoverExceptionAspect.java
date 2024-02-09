package ru.gb.myspringdemo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class RecoverExceptionAspect {

    @Pointcut("@annotation(ru.gb.myspringdemo.aspect.RecoverException)")
    public void methodAnnotatedExceptionWith() {

    }

    @Around("methodAnnotatedExceptionWith()")
    public Object getStringAspectException(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        RecoverException annotation = signature.getMethod().getAnnotation(RecoverException.class);
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            for (int i = 0; i < annotation.noRecoverFor().length; i++) {
                if (annotation.noRecoverFor()[i].isAssignableFrom(e.getClass())) {
                    throw e;
                }
            }
            log.warn("Aspect error! " + e.getClass().getName() + " " + e.getMessage());
        }
        return result;
    }
}
