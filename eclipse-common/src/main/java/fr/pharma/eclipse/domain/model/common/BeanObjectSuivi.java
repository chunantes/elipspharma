package fr.pharma.eclipse.domain.model.common;

import java.util.SortedSet;

import fr.pharma.eclipse.domain.model.suivi.common.Suivi;

/**
 * Classe commune des objets métier suivis.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public abstract class BeanObjectSuivi extends BeanObject {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 8252754626152254592L;

    /**
     * Récupération des élément de suivi du bean métier suivi.
     * @return Ensemble des modifications apportées au bean métier.
     */
    public abstract SortedSet<? extends Suivi> getModifs();
}
