package fr.pharma.eclipse.component.essai.seeker;

import java.io.Serializable;
import java.util.List;

import fr.pharma.eclipse.component.essai.TypeContact;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.essai.Essai;

/**
 * Interface des helpers en charge de récupérer depuis la base des contacts
 * sélectionnables pour l'ajout de contact.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public interface SelectableContactsSeeker extends Serializable {
    /**
     * Indique si le préparateur gère le type de contact passé en paramètre.
     * @param typeContact Type de contact.
     * @return true ssi la méthode getSearchCriteria peut être appelée.
     */
    boolean supports(TypeContact typeContact);

    /**
     * Méthode en charge de préparer un critère de recherche qui permet<br>
     * de récupérer des contacts ajoutables à un essai.
     * @param essai Essai pour lequel on souhaite ajouter des contacts.
     * @return Le critère de recherche qui permet de récupérer les contacts.
     */

    /**
     * Méthode en charge de récupérer de la base des contacts pour l'ajout sur
     * un essai?
     * @param essai Essai pour lequel on souhaite ajouter des contacts.
     * @return Les contacts récupérés de la base.
     */
    List<Personne> getContacts(Essai essai);
}
