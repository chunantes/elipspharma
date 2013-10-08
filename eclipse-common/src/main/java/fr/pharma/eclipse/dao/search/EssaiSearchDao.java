package fr.pharma.eclipse.dao.search;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.pharma.eclipse.dictionary.maker.common.utils.CriteriaMakerUtils;
import fr.pharma.eclipse.domain.dto.EssaiDTO;
import fr.pharma.eclipse.domain.model.essai.Essai;

/**
 * DAO de recherche des essais.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Component
public class EssaiSearchDao extends CommonSearchDao implements Serializable {

    private static final long serialVersionUID = 3159635317291513022L;

    /**
     * DAO de recherche des ACLs.
     */
    @Autowired
    private AclSearchDao aclSearchDao;

    /**
     * Méthode de recherche d'essai sur le numéro interne ou le nom ou la raison
     * sociale du promoteur.
     * @param search Texte à recherche.
     * @return Liste des essaisDTO.
     */
    public List<EssaiDTO> findEssaiDTOByNumInterneOrNomOrPromoteur(final String search) {

        final Criteria crit = this.createCriteria(Essai.class);

        crit.createCriteria("promoteur", "promoteur", CriteriaSpecification.INNER_JOIN);

        crit.setProjection(Projections.projectionList().add(Projections.property("id"), "id").add(Projections.property("nom"), "nom")
                .add(Projections.property("numInterne"), "numInterne").add(Projections.property("promoteur.raisonSociale"), "raisonSociale"));
        crit.setResultTransformer(Transformers.aliasToBean(EssaiDTO.class));

        final Disjunction dij = Restrictions.disjunction();
        CriteriaMakerUtils.addSqlCritere(dij, "numInterne", search);
        CriteriaMakerUtils.addSqlCritere(dij, "nom", search);
        CriteriaMakerUtils.addSqlCritere(dij, "raisonSociale", search);
        crit.add(dij);

        // Restriction sur les droits de voir les essais
        final List<Long> idsEssais = this.aclSearchDao.findIdsEssais();
        CriteriaMakerUtils.addInCritere(crit, "this.id", idsEssais.toArray(new Object[idsEssais.size()]));

        final Order order = Order.asc("numInterne");
        crit.addOrder(order);

        @SuppressWarnings("unchecked")
        final List<EssaiDTO> res = crit.list();

        return res;
    }

    /**
     * Méthode en charge de récupérer un essai DTO à partir de son identifiant
     * technique.
     * @param id Identifiant technique de l'essai.
     * @return Liste d'objet EssaiDTO.
     */
    @SuppressWarnings("unchecked")
    public List<EssaiDTO> findEssaiDTOById(final Long id) {
        final Criteria crit = this.getCurrentSession().createCriteria(Essai.class);

        crit.createCriteria("promoteur", "promoteur", CriteriaSpecification.INNER_JOIN);

        crit.setProjection(Projections.projectionList().add(Projections.property("id"), "id").add(Projections.property("nom"), "nom")
                .add(Projections.property("numInterne"), "numInterne").add(Projections.property("promoteur.raisonSociale"), "raisonSociale"));
        crit.setResultTransformer(Transformers.aliasToBean(EssaiDTO.class));

        CriteriaMakerUtils.addCritere(crit, "this.id", id);

        return crit.list();
    }

}
