package fr.pharma.eclipse.profiling;

import java.util.Collection;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * AOP Pointcut permettant de logger les temps d'execution d'une méthode.<br/>
 * Classe employée à des fins de profiling d'application, non destinée à
 * fonctionner en production.
 * @author sebastien.helbert
 */
@Component
public class MethodExecutionProfiler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public Object profile(final ProceedingJoinPoint call) throws Throwable {
        final Class<?> targetClass = call.getTarget().getClass();
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start(call.toLongString());
        try {
            final Object ret = call.proceed();
            stopWatch.stop();
            if (ret instanceof Collection) {
                this.logger.debug("{} {} returns {} item(s) in {}ms", targetClass, call.toLongString(), ((Collection<?>) ret).size(), stopWatch.getLastTaskTimeMillis());
            } else {
                this.logger.debug("{} {} executed in {}ms", targetClass, call.toLongString(), stopWatch.getLastTaskTimeMillis());
            }
            return ret;
        } catch (final Exception e) {
            stopWatch.stop();
            this.logger.error("{} {} throws an exception of type [{}] after {}ms", targetClass, call.toLongString(), e.getClass(), stopWatch.getLastTaskTimeMillis());
            throw e;
        }
    }
}
