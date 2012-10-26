package fr.pharma.eclipse.service.acteur;

import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.service.common.GenericService;

/**
 * Interface de service de gestion de personne.
 
 * @version $Revision$ $Date$
 * @param <PERSONNE> Bean Objet Personne.
 */
public interface PersonneService<PERSONNE extends Personne>
    extends GenericService<PERSONNE>
{

    /**
     * Méthode générique de sauvegarde d'un objet d'un type particulier sans appliquer les
     * validators - doit gérer à la fois update et insert.
     * @param personne La personne à sauvegarder
     * @return La personne.
     */
    PERSONNE saveForSigrec(PERSONNE personne);

}
