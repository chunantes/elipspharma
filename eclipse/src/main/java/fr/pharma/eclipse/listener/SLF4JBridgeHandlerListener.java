package fr.pharma.eclipse.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.bridge.SLF4JBridgeHandler;

/**
 * Listener permettant d'enreigtrer SLF4JBridgeHandler qui permet de rediriger
 * tous les logs java.util.logging vers SLF4j et donc avoir qu'une seule conf
 * pour les logs. (sans ça on a d'une par des logs log4j par l'appli et d'autre
 * par des logs j.u.l. sur la console par la couche JSF. cf.
 * http://www.slf4j.org/legacy.html
 * @author sebastien.helbert
 */
public class SLF4JBridgeHandlerListener implements ServletContextListener {

    @Override
    public void contextInitialized(final ServletContextEvent sce) {
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
    }

    @Override
    public void contextDestroyed(final ServletContextEvent sce) {
        // Nothing to do
    }

}
