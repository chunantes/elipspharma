package fr.pharma.eclipse.script.sigrec;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pharma.eclipse.processor.Processor;
import fr.pharma.eclipse.processor.impl.SynchroProcessor;
import fr.pharma.eclipse.script.ScriptRunner;
import fr.pharma.eclipse.script.impl.AbstractScriptRunner;

/**
 * Runner en charge de déclencher le script de synchronisation avec le fichier Sigrec.
 
 * @version $Revision$ $Date$
 */
public class SynchroRunner
    extends AbstractScriptRunner
    implements ScriptRunner
{

    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(SynchroRunner.class);

    /**
     * Processor de synchronisation.
     */
    @Resource(name = "synchroProcessor")
    private SynchroProcessor synchroProcessor;

    /**
     * Constructeur.
     */
    public SynchroRunner()
    {
        super();
    }

    /**
     * {@inheritDoc}
     */
    public void run(final String[] args)
    {
        this.log.info("Synchronisation SIGREC : Opération en cours.");
        final SynchroParameter parameters = this.makeParam(args);

        // si un chemin est spécifié en argument alors on le paramètre.
        if (StringUtils.isNotEmpty(parameters.getFilePath()))
        {
            this.synchroProcessor.setCheminFichier(parameters.getFilePath());
        }
        this.synchroProcessor.process();

        this.log.info("Fin de la synchronisation SIGREC.");
    }
    /**
     * Méthode en charge de fabriquer et de remplir le stockeur de parametre.
     * @param args Tableau d'arguments.
     * @return Stockeur de parametre
     */
    private SynchroParameter makeParam(final String[] args)
    {
        final SynchroParameter synchroParameter = new SynchroParameter();

        this.getScriptParameterBuilder().getScriptParameter(args,
                                                            synchroParameter);
        this.log.info("#Préparation du paramètre de traitement : Synchro."
                      + "\n");
        return synchroParameter;
    }

    /**
     * Getter sur synchroProcessor.
     * @return Retourne le synchroProcessor.
     */
    public Processor getSynchroProcessor()
    {
        return this.synchroProcessor;
    }

    /**
     * Setter pour synchroProcessor.
     * @param synchroProcessor le synchroProcessor à écrire.
     */
    public void setSynchroProcessor(final SynchroProcessor synchroProcessor)
    {
        this.synchroProcessor = synchroProcessor;
    }

}
