package fr.pharma.eclipse.dao.search;

import java.io.Serializable;
import java.math.BigInteger;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import fr.pharma.eclipse.domain.model.acl.AclPharmacie;
import fr.pharma.eclipse.domain.model.user.UserSecurity;

/**
 * Classe de DAO de recherche des acls.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Component
public class AclSearchDao extends CommonSearchDao implements Serializable {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 6343108865702273345L;

    /**
     * Identifiant ne correspondant à aucun élement.
     */
    private final Long idAbsent = -1L;

    /**
     * Méthode en charge de retourner la liste des identifiants d'essais
     * visibles pour l'utilisateur connecté.
     * @param idPersonne Identifiant technique de la personne connectée.
     * @return Liste des identifiants d'essais.
     */
    public List<Long> findIdsEssais() {
        final List<Long> idsEssais = new ArrayList<Long>();
        final String req = "select distinct id_essai from acl_essai where id_personne = ?";
        final SQLQuery q = this.getCurrentSession().createSQLQuery(req);
        final UserSecurity userSecurity = (UserSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        q.setParameter(0, userSecurity.getIdPersonne());
        @SuppressWarnings("unchecked")
        final List<BigInteger> result = q.list();
        for (final BigInteger res : result) {
            idsEssais.add(res.longValue());
        }
        if (idsEssais.isEmpty()) {
            idsEssais.add(this.idAbsent);
        }
        return idsEssais;
    }

    /**
     * Méthode en charge de retourner la liste des identifiants de pharmacies
     * visibles d'une personne.
     * @param idPersonne Identifiant technique de la personne connectée.
     * @return Liste des identifiants de pharamcies.
     */
    @SuppressWarnings("unchecked")
    public List<Long> findIdsPharmacies() {
        final List<Long> idsPharamcies = new ArrayList<Long>();
        final String req = "select distinct id_pharmacie from acl_pharmacie where id_personne = ?";
        final SQLQuery q = this.getCurrentSession().createSQLQuery(req);
        final UserSecurity userSecurity = (UserSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        q.setParameter(0, userSecurity.getIdPersonne());
        final List<BigInteger> result = q.list();
        for (final BigInteger res : result) {
            idsPharamcies.add(res.longValue());
        }
        if (idsPharamcies.isEmpty()) {
            idsPharamcies.add(this.idAbsent);
        }
        return idsPharamcies;
    }

    /**
     * Méthode en charge de supprimer les acls de pharmacies d'une personne.
     * @param idPersonne Identifiant de la personne.
     */
    public void removeAclsPharmaciesOfPersonne(final Long idPersonne) {
        final SQLQuery q = this.getCurrentSession().createSQLQuery("delete from acl_pharmacie where id_personne = ?");
        q.setParameter(0, idPersonne);
        q.executeUpdate();
    }

    /**
     * Méthode en charge de supprimer les acls de pharmacies d'une pharmacie.
     * @param idPharmacie Identifiant de la pharmacie.
     */
    public void removeAclsPharmaciesOfPharma(final Long idPharmacie) {
        final SQLQuery q = this.getCurrentSession().createSQLQuery("delete from acl_pharmacie where id_pharmacie = ?");
        q.setParameter(0, idPharmacie);
        q.executeUpdate();
    }

    /**
     * Méthode en charge de supprimer les acls des essais d'un essai.
     * @param idEssai Identifiant de l'essai.
     */
    public void removeAclsEssaisOfEssai(final Long idEssai) {
        final SQLQuery q = this.getCurrentSession().createSQLQuery("delete from acl_essai where id_essai = ?");
        q.setParameter(0, idEssai);
        q.executeUpdate();
    }

    /**
     * Méthode en charge de supprimer les acls des essais d'une personne.
     * @param idPersonne Identifiant de la personne.
     */
    public void removeAclsEssaisOfPersonne(final Long idPersonne) {
        final SQLQuery q = this.getCurrentSession().createSQLQuery("delete from acl_essai where id_personne = ?");
        q.setParameter(0, idPersonne);
        q.executeUpdate();
    }

    /**
     * Méthode en charge de sauvegarder une liste d'acls de pharmacies.
     * @param aclPharmacies Liste d'aclPharmacies à sauvegarder.
     */
    public void saveAclsPharmacies(final List<AclPharmacie> aclPharmacies) {
        for (final AclPharmacie aclPharmacie : aclPharmacies) {
            this.getEntityManager().persist(aclPharmacie);
        }
    }

    /**
     * Méthode en charge de sauvegarder une liste d'acls de pharmacies pour un
     * admin.
     * @param idPersonne Identifiant technique de la personne.
     */
    public void saveAclsPharmaciesForAdmin(final Long idPersonne) {
        final SQLQuery q =
            this.getCurrentSession()
                    .createSQLQuery("INSERT INTO acl_pharmacie (id_pharmacie, id_personne) SELECT DISTINCT p.id, pers.id FROM pharmacie p, personne pers WHERE pers.id=?");
        q.setParameter(0, idPersonne);
        q.executeUpdate();
    }

    /**
     * Méthode en charge de sauvegarder une liste d'acls d'essais pour un admin.
     * @param idPersonne Identifiant technique de la personne.
     */
    public void saveAclsEssaisForAdmin(final Long idPersonne) {
        final SQLQuery q =
            this.getCurrentSession().createSQLQuery("INSERT INTO acl_essai (id_essai, id_personne) SELECT DISTINCT e.id, pers.id FROM essai e, personne pers WHERE pers.id=?");
        q.setParameter(0, idPersonne);
        q.executeUpdate();
    }

    /**
     * Méthode en charge de sauvegarder une liste d'acls d'essais pour un
     * pharmacien
     * @param idPersonne
     * @param idsPharmacies
     */
    public void saveAclsEssaisForPharmacien(final Long idPersonne,
                                            final List<Long> idsPharmacies) {
        final String requete =
            "INSERT INTO acl_essai (id_essai, id_personne) SELECT DISTINCT e.id, {0} FROM essai e, essai_detail_pharma_pharmacie ep WHERE (e.id_pharma IN ({1}) OR (e.id=ep.id_detail_pharma and ep.id_pharmacie in ({2})))";
        final String paramIdsPharmacies = Arrays.toString(idsPharmacies.toArray(new Object[idsPharmacies.size()])).replace("[", "").replace("]", "");
        final String paramIdPersonne = "" + idPersonne;

        final SQLQuery q = this.getCurrentSession().createSQLQuery(MessageFormat.format(requete, new Object[]{paramIdPersonne, paramIdsPharmacies, paramIdsPharmacies }));
        q.executeUpdate();
    }

    /**
     * Méthode en charge de sauvegarder une liste d'acls de pharmacies pour tous
     * les admin concernant une pharmacie.
     * @param idPharmacie Identifiant de la pharmacie.
     */
    public void saveAclsPharmaciesForAdmins(final Long idPharmacie) {
        final SQLQuery q =
            this.getCurrentSession()
                    .createSQLQuery("INSERT INTO acl_pharmacie (id_pharmacie, id_personne) SELECT DISTINCT p.id, pers.id FROM pharmacie p, personne pers WHERE pers.isadmin IS true and p.id=?");
        q.setParameter(0, idPharmacie);
        q.executeUpdate();
    }

    /**
     * Méthode en charge de sauvegarder une liste d'acls d'essais pour tous les
     * admin concernant un essai.
     * @param idEssai Identifiant de l'essai.
     */
    public void saveAclsEssaisForAdmins(final Long idEssai) {
        final SQLQuery q =
            this.getCurrentSession()
                    .createSQLQuery("INSERT INTO acl_essai (id_essai, id_personne) SELECT DISTINCT e.id, p.id FROM essai e, personne p WHERE p.isadmin IS true and e.id=?");
        q.setParameter(0, idEssai);
        q.executeUpdate();
    }

    /**
     * Méthode en charge de sauvegarder une liste d'acls d'essais pour tous les
     * pharmaciens concernant un essai.
     * @param idEssai Identifiant de l'essai.
     */
    public void saveAclsEssaisForPharmaciens(final Long idEssai) {
        final SQLQuery q =
            this.getCurrentSession()
                    .createSQLQuery("INSERT INTO acl_essai (id_essai, id_personne) SELECT DISTINCT e.id, pers.id FROM essai e, habilitation h, personne pers, essai_detail_contacts edc WHERE e.id = edc.id_essai AND h.id_detail_contacts = edc.id AND h.id_personne = pers.id AND h.active IS true AND type = 'PHARMACIEN' AND isadmin IS false AND e.id=? "
                                            + "UNION SELECT DISTINCT e.id, pers.id FROM essai e, pharmacien_pharmacie p, essai_detail_pharma_pharmacie ep, personne pers WHERE  p.id_pharmacien = pers.id AND type = 'PHARMACIEN' AND isadmin IS false AND (e.id_pharma = p.id_pharmacie OR (e.id=ep.id_detail_pharma and p.id_pharmacie=ep.id_pharmacie)) AND e.id=?");
        q.setParameter(0, idEssai);
        q.setParameter(1, idEssai);
        q.executeUpdate();
    }

    /**
     * Méthode en charge de sauvegarder une liste d'acls d'essais pour tous les
     * autres profils concernant un essai.
     * @param idEssai Identifiant de l'essai.
     */
    public void saveAclsEssaisForOthersProfils(final Long idEssai) {
        final SQLQuery q =
            this.getCurrentSession()
                    .createSQLQuery("INSERT INTO acl_essai (id_essai, id_personne) SELECT DISTINCT e.id, id_personne FROM habilitation h INNER JOIN essai_detail_contacts edc ON h.id_detail_contacts = edc.id INNER JOIN essai e on edc.id_essai = e.id INNER JOIN personne p ON h.id_personne = p.id WHERE h.active IS true AND type <> 'PHARMACIEN' AND isadmin IS false AND e.id=?");
        q.setParameter(0, idEssai);
        q.executeUpdate();
    }

}
