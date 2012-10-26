package fr.pharma.eclipse.processor.impl;

import java.io.File;

import org.apache.camel.CamelContext;
import org.apache.camel.CamelContextAware;
import org.apache.camel.CamelExecutionException;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pharma.eclipse.processor.Processor;

/**
 * Processor en charge de gérer la synchronisation en appelant la route camel concernée.
 
 * @version $Revision$ $Date$
 */
public class SynchroProcessor
    implements Processor, CamelContextAware
{

    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(SynchroProcessor.class);

    /**
     * Contexte Camel.
     */
    private CamelContext camelContext;

    /**
     * Chemin d'accès au fichier SIGREC.
     */
    private String cheminFichier;

    /**
     * {@inheritDoc}
     */
    @Override
    public void process()
    {
        this.log.info("Lancement de la route Camel sur le fichier suivant : "
                      + this.getCheminFichier());
        final ProducerTemplate t = this.camelContext.createProducerTemplate();
        try
        {
            t.sendBody("direct:start",
                       new File(this.cheminFichier));
        }
        catch (final CamelExecutionException e)
        {

            this.log.error("Echec de la synchronisation.",
                           e);
        }
        this.log.info("Route Camel terminée.");

    }

    /**
     * Getter sur camelContext.
     * @return Retourne le camelContext.
     */
    public CamelContext getCamelContext()
    {
        return this.camelContext;
    }

    /**
     * Setter pour camelContext.
     * @param camelContext le camelContext à écrire.
     */
    public void setCamelContext(final CamelContext camelContext)
    {
        this.camelContext = camelContext;
    }

    /**
     * Getter sur cheminFichier.
     * @return Retourne le cheminFichier.
     */
    public String getCheminFichier()
    {
        return this.cheminFichier;
    }

    /**
     * Setter pour cheminFichier.
     * @param cheminFichier le cheminFichier à écrire.
     */
    public void setCheminFichier(final String cheminFichier)
    {
        this.cheminFichier = cheminFichier;
    }

}
