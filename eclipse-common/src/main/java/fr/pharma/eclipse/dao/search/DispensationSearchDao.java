package fr.pharma.eclipse.dao.search;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;

import fr.pharma.eclipse.dictionary.maker.common.utils.CriteriaMakerUtils;
import fr.pharma.eclipse.domain.dto.DispensationDTO;
import fr.pharma.eclipse.domain.dto.EssaiDTO;
import fr.pharma.eclipse.domain.model.dispensation.Dispensation;
import fr.pharma.eclipse.domain.model.patient.Patient;

/**
 * Classe de DAO de recherche des dispensations.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DispensationSearchDao extends CommonSearchDao implements Serializable {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 5813569586294334861L;

    /**
     * DAO de recherche des ACLs.
     */
    @Autowired
    private AclSearchDao aclSearchDao;

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<DispensationDTO> findByEssaiAndPatientAndDispenseOrderByIdAsc(final EssaiDTO essai,
                                                                              final Patient patient,
                                                                              final Boolean dispense) {
        final Criteria crit = this.createCriteria(Dispensation.class);

        final Criteria critPrescription = crit.createCriteria("prescription", "prescription", CriteriaSpecification.INNER_JOIN);
        final Criteria critInclusion = critPrescription.createCriteria("inclusion", "inclusion", CriteriaSpecification.INNER_JOIN);
        critInclusion.createCriteria("patient", "patient", CriteriaSpecification.INNER_JOIN);
        final Criteria critEssai = critInclusion.createCriteria("essai", "essai", CriteriaSpecification.INNER_JOIN);
        critEssai.createCriteria("promoteur", "promoteur", CriteriaSpecification.INNER_JOIN);

        // Projections
        crit.setProjection(Projections.projectionList().add(Projections.property("id"), "id").add(Projections.property("essai.id"), "idEssai")
                .add(Projections.property("essai.nom"), "nomEssai").add(Projections.property("essai.codePromoteur"), "codePromoteur")
                .add(Projections.property("patient.nom"), "nomPatient").add(Projections.property("patient.prenom"), "prenomPatient")
                .add(Projections.property("patient.initiales"), "initialesPatient").add(Projections.property("prescription.numPrescription"), "numPrescription")
                .add(Projections.property("dispense"), "dispense").add(Projections.property("inclusion.numInclusion"), "numInclusion")
                .add(Projections.property("prescription.datePrescription"), "datePrescription").add(Projections.property("promoteur.raisonSociale"), "raisonSociale")
                .add(Projections.property("this.dateDispensation"), "dateDispensation").add(Projections.property("this.numOrdonnancier"), "numOrdonnancier"));
        crit.setResultTransformer(Transformers.aliasToBean(DispensationDTO.class));

        // Restrictions
        if (patient != null) {
            CriteriaMakerUtils.addCritere(critInclusion, "patient", patient);
        }

        // Essai
        if (essai != null) {
            critEssai.add(Restrictions.idEq(essai.getId()));
        }

        // Dispensé ?
        if (dispense != null) {
            crit.add(Restrictions.eq("this.dispense", dispense));
        }

        // Tri par id ascendant
        crit.addOrder(Order.asc("this.id"));

        // Restriction sur les droits de voir les essais
        final List<Long> idsEssais = this.aclSearchDao.findIdsEssais();
        CriteriaMakerUtils.addInCritere(critEssai, "essai.id", idsEssais.toArray(new Object[idsEssais.size()]));

        return crit.list();
    }

    /**
     * Méthode en charge de retourner les informations des produits associées à
     * une dispensation.
     * @param idDispensation Identifiant de la dispensation.
     * @return Tableau avec Dénomination prod / numlot / numTraitement /
     * quantité / prénom personne / nom personne.
     */
    @SuppressWarnings("unchecked")
    public List<Object[]> findProduitsByIdDispensation(final Long idDispensation) {
        final String req =
            "select p.denomination, m.numlot, m.numtraitement, m.quantite, pers.prenom, pers.nom " + "from mvt_dispensation md inner join mvtstock m on md.id = m.id "
                    + "inner join personne pers on pers.id = m.id_personne " + "inner join produit p on p.id = m.id_produit where id_dispensation = ?";
        final SQLQuery q = this.getCurrentSession().createSQLQuery(req);
        q.setParameter(0, idDispensation);
        return q.list();
    }

}
