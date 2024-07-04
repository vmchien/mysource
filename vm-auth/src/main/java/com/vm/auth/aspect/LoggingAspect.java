//package com.vm.auth.aspect;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.core.env.Environment;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//
///**
// * Aspect for logging execution of service and repository Spring components.
// */
//@Aspect
//@Component
//@Slf4j
//public class LoggingAspect {
//
//    private final String PROFILE_DEV = "dev";
//    private final String PROFILE_PROD = "prod";
//
//    private final Environment env;
//    private static final ObjectMapper objectMapper = new ObjectMapper();
//
//    public LoggingAspect(Environment environment) {
//        env = environment;
//    }
//
//    @Pointcut("within(@org.springframework.stereotype.Repository *)" +
//            " || within(@org.springframework.stereotype.Service *)" +
//            " || within(@org.springframework.web.bind.annotation.RestController *)")
//    public void springBeanPointcut() {
//    }
//
//    private Logger logger(JoinPoint joinPoint) {
//        return LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringTypeName());
//    }
//
//
//    @Pointcut("within(vn.futa.data.warehouse.api..*)" +
//            " || within(vn.futa.data.warehouse.api.service..*)" +
//            " || within(vn.futa.data.warehouse.api.controller..*)")
//    public void applicationPackagePointcut() {
//    }
//
//    @Around(value = "applicationPackagePointcut() && springBeanPointcut()()")
//    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
//        if (PROFILE_DEV.equals(env.getProperty("spring.profiles.active"))) {
//            Object[] args = joinPoint.getArgs();
//            String methodName = joinPoint.getSignature().getName();
//            log.info(">>> {}() - {}", methodName, Arrays.toString(args));
//            Object result = joinPoint.proceed();
//            String resultStr = convertResultToJsonString(result);
//            log.info("<<< {}() - {}", methodName, resultStr);
//            return result;
//        }
//        return joinPoint.proceed();
//    }
//
//    private static String convertResultToJsonString(Object result) throws JsonProcessingException {
//            return objectMapper.writeValueAsString(result);
//    }
//
//    @AfterThrowing(pointcut = "applicationPackagePointcut() && springBeanPointcut()", throwing = "e")
//    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
//        logger(joinPoint)
//                .error(
//                        "Exception in {}() with cause = {}",
//                        joinPoint.getSignature().getName(),
//                        e.getCause() != null ? e.getCause().getMessage() : e.getMessage()
//                );
//
//    }
//
//}