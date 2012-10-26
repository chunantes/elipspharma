package fr.pharma.eclipse.dao.common;

import java.util.List;

import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.model.common.BeanObject;

/**
 * DAO (Data Access Object) générique pour l'utilisation standard des POJOs (CRUD).
 * @param <BEAN> Bean Objet Métier.
 
 * @version $Revision$ $Date$
 */
public interface GenericDao<BEAN extends BeanObject>
{

    /**
     * Purge un objet de la session.
     * @param bean Bean à détacher de la session.
     */
    void dettach(final BEAN bean);

    /**
     * Reattach un objet à une session pour obtenir le lazyloading.
     * @param bean Objet à rattacher.
     * @return Objet rattaché.
     */
    BEAN reattach(final BEAN bean);

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
     * Méthode générique de suppression d'un objet d'un type particulier, en fonction de son
     * identifiant.
     * @param object Objet à supprimer.
     */
    void remove(BEAN object);

}
