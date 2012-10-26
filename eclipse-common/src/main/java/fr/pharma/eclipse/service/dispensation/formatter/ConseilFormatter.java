package fr.pharma.eclipse.service.dispensation.formatter;

import fr.pharma.eclipse.domain.model.dispensation.ConseilDispensation;

/**
 * Classe en charge de construire le message de conseil à afficher lors de la dispensation.
 
 * @version $Revision$ $Date$
 */
public interface ConseilFormatter
{
    /**
     * Méthode en charge de construire une chaine de caractère représentant le message à afficher
     * lors de la dispensation. Il est construit en fonction du conseil en paramètre.
     * @param conseil Le conseil.
     * @return Le message.
     */
    String format(ConseilDispensation conseil);
}
