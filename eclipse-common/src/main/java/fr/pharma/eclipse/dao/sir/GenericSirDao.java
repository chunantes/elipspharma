package fr.pharma.eclipse.dao.sir;

import java.util.List;

import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.model.sir.common.BeanSirObject;

/**
 * DAO (Data Access Object) générique pour l'utilisation de SIR.
 * @param <SIR> Bean Objet Métier SIR.
 * @author NETAPSYS
 * @version $Revision$ $Date$
 */
public interface GenericSirDao<SIR extends BeanSirObject> {
    /**
     * Méthode générique de listing de tous les objets d'un type particulier
     * avec un critère de recherche.
     * @param criteria Le critère de recherche.
     * @return La liste de tous les objets filtrés.
     */
    List<SIR> getAll(SearchCriteria criteria);

    /**
     * Méthode générique de listing de tous les objets d'un type particulier.
     * @return La liste de tous les objets filtrés.
     */
    List<SIR> getAll();

    /**
     * Méthode générique de récupération d'un objet d'un type particulier, en
     * fonction de son identifiant.
     * @param id L'identifiant de l'objet (sa clef primaire).
     * @return L'objet recherché.
     */
    SIR get(Integer id);

}
