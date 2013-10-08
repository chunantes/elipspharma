package fr.pharma.eclipse.profiling;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

/**
 * Phases Listener JSF permettant de tracer les temps d'excution de chaque phase
 * JSF.<br/>
 * Doit-être ajouté au niveau du faces-config.xml.<br/>
 * Fonctionne en conjonction avec la servletFilter JsfProfilerFilter.<br/>
 * Classe employée à des fins de profiling d'application, non destinée à
 * fonctionner en production.
 * @See JsfProfilerFilter
 * @author sebastien.helbert
 */
public class JsfProfilerPhaseListener implements PhaseListener {
    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    @Override
    public void afterPhase(PhaseEvent event) {
        PhaseId phaseId = event.getPhaseId();
        StopWatch stopWatch = JsfProfilerFilter.getCurrentStopWatch();
        if (stopWatch != null) {
            JsfProfilerFilter.getCurrentStopWatch().stop();
            logger.debug("End of JSF phase [" + phaseId + "], {}ms.", JsfProfilerFilter.getCurrentStopWatch().getLastTaskTimeMillis());
        }
    }
    @Override
    public void beforePhase(PhaseEvent event) {
        PhaseId phaseId = event.getPhaseId();
        StopWatch stopWatch = JsfProfilerFilter.getCurrentStopWatch();
        if (stopWatch != null) {
            stopWatch.start(phaseId.toString());
            logger.debug("Start of JSF phase [" + phaseId + "].");
        } else {
            logger.debug("No ThreadLocal StopWatch found. Please check that '" + JsfProfilerFilter.class + "' servlet-filter is properly configured in your web.xml.");
        }
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }
}
