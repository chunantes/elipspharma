package fr.pharma.eclipse.service.common;

import java.util.List;

import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.model.common.BeanObject;

/**
 * Interface de définition du manager générique pour l'utilisation standard des POJOs (CRUD).
 
 * @version $Revision$ $Date$
 * @param <BEAN> Bean Objet Métier.
 */
public interface GenericService<BEAN extends BeanObject>
{
    /**
     * Purge un objet de la session.
     * @param bean Bean à détacher de la session.
     */
    void dettach(final BEAN bean);

    /**
     * Reattache un objet à une session pour obtenir le lazyloading.
     * @param bean Objet à rattacher.
     * @return Objet rattaché.
     */
    BEAN reattach(final BEAN bean);

    /**
     * Réattache une liste d'objets à une session pour obtenir le lazyloading.
     * @param beans Liste des objets à rattacher.
     * @return Liste des objets rattachés.
     */
    List<BEAN> reattachAll(List<BEAN> beans);

    /**
     * Méthode générique de listing de tous les objets d'un type particulier avec un critère de
     * recherche.
     * @param criteria Le critère de recherche.
     * @return La liste de tous les objets filtrés.
     */
    List<BEAN> getAll(SearchCriteria criteria);

    /**
     * Méthode générique de listing de tous les objets d'un type particulier.
     * @return La liste de tous les objets filtrés.
     */
    List<BEAN> getAll();

    /**
     * Méthode générique permettant de savoir si des enregistrements répondant au critère de
     * recherche existe.
     * @param criteria Critère de recherche.
     * @return Résultat de l'existence de résultats.
     */
    Boolean hasResult(SearchCriteria criteria);

    /**
     * Méthode générique de récupération d'un objet d'un type particulier, en fonction de son
     * identifiant.
     * @param id L'identifiant de l'objet (sa clef primaire).
     * @return L'objet recherché.
     */
    BEAN get(Long id);

    /**
     * Méthode générique de sauvegarde d'un objet d'un type particulier - doit gérer à la fois
     * update et insert.
     * @param object L'objet à sauvegarder.
     * @return BEAN.
     */
    BEAN save(BEAN object);

    /**
     * Méthode générique de sauvegarde d'objets d'un type particulier - doit gérer à la fois
     * update et insert.
     * @param beans La liste d'objets à sauvegarder.
     * @return Liste des objets BEAN sauvegardés.
     */
    List<BEAN> saveAll(List<BEAN> beans);

    /**
     * Méthode générique de suppression d'un objet d'un type particulier.
     * @param object L'objet à supprimer.
     */
    void remove(BEAN object);

    /**
     * Méthode générique de suppression d'une liste d'objets d'un type particulier.
     * @param objects Les objets à supprimer.
     */
    void remove(List<BEAN> objects);

}
