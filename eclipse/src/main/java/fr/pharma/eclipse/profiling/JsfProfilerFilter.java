package fr.pharma.eclipse.profiling;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

/**
 * Servlet Filter permettant de tracer les temps d'exécution des différentes
 * phases jsf pour logger un rapport pour chaque requête HTTP.<br/>
 * Doit être ajouté dans le web.xml.<br/>
 * Fonctionne en conjonction avec JsfProfilerPhaseListener.<br/>
 * Classe employée à des fins de profiling d'application, non destinée à
 * fonctionner en production.
 * @see JsfProfilerPhaseListener
 * @author sebastien.helbert
 */
public class JsfProfilerFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private static ThreadLocal<StopWatch> stopWatchThreadLocal = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        JsfProfilerFilter.stopWatchThreadLocal = new ThreadLocal<StopWatch>();
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        StopWatch stopWatch = new StopWatch("JSF phases profiler");

        stopWatchThreadLocal.set(stopWatch);
        try {
            chain.doFilter(request, response);
        } finally {
            if (stopWatch.getTaskCount() > 0) {
                logger.debug(stopWatch.prettyPrint());
            }
            stopWatchThreadLocal.remove();
        }
    }

    /**
     * @return StopWatch associé au thread courant ou null s'il n'y en a pas.
     */
    public static StopWatch getCurrentStopWatch() {
        return stopWatchThreadLocal.get();
    }

    @Override
    public void destroy() {
        JsfProfilerFilter.stopWatchThreadLocal = null;
    }
}
