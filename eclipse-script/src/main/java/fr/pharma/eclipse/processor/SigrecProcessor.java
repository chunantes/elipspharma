package fr.pharma.eclipse.processor;

/**
 * Interface des processors sigrec.
 
 * @version $Revision$ $Date$
 * @param <BEAN> Classe du bean concerné par le traitement.
 */
public interface SigrecProcessor<BEAN extends Object>
{
    /**
     * Applique un traitement spécifique à partir du bean en paramètre.
     * @param bean Bean.
     */
    void process(BEAN bean);
}
