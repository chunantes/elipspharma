package fr.pharma.eclipse.service.acl.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.pharma.eclipse.dao.search.AclSearchDao;
import fr.pharma.eclipse.domain.enums.TypePersonne;
import fr.pharma.eclipse.domain.model.acl.AclPharmacie;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.acteur.Pharmacien;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.acl.AclService;

/**
 * Implémentation du service de gestion des ACLs.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Component
public class AclServiceImpl implements AclService, Serializable {

    /**
     * DAO de recherche des ACLs.
     */
    @Autowired
    private AclSearchDao aclSearchDao;

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 5719301921355430095L;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void updateAclsPharmacies(final Personne personne) {

        // Maj des acls uniquement pour les admin et les pharmaciens
        if (personne.getIsAdmin() || TypePersonne.PHARMACIEN.equals(personne.getType())) {

            // Suppression des acls existantes
            this.aclSearchDao.removeAclsPharmaciesOfPersonne(personne.getId());

            // Si la personne est admin => visibilité sur toutes les pharmacies
            if (personne.getIsAdmin()) {
                this.aclSearchDao.saveAclsPharmaciesForAdmin(personne.getId());
            } else {
                final Pharmacien pharmacien = (Pharmacien) personne;
                final SortedSet<Pharmacie> pharmacies = pharmacien.getPharmacies();
                final List<AclPharmacie> aclPharmacies = new ArrayList<AclPharmacie>();
                for (final Pharmacie pharmacie : pharmacies) {
                    aclPharmacies.add(new AclPharmacie(personne.getId(), pharmacie.getId()));
                }
                this.aclSearchDao.saveAclsPharmacies(aclPharmacies);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void updateAclsEssais(final Personne personne) {

        // Maj des acls uniquement pour les admin et les pharmaciens
        if (personne.getIsAdmin() || TypePersonne.PHARMACIEN.equals(personne.getType())) {
            // Suppression des acls existantes
            this.aclSearchDao.removeAclsEssaisOfPersonne(personne.getId());

            // Si la personne est admin => visibilité sur toutes les essais
            if (personne.getIsAdmin()) {
                this.aclSearchDao.saveAclsEssaisForAdmin(personne.getId());
            } else {
                final Pharmacien pharmacien = (Pharmacien) personne;
                final SortedSet<Pharmacie> pharmacies = pharmacien.getPharmacies();
                final List<Long> idsPharmacies = new ArrayList<Long>();
                for (final Pharmacie pharmacie : pharmacies) {
                    idsPharmacies.add(pharmacie.getId());
                }
                this.aclSearchDao.saveAclsEssaisForPharmacien(personne.getId(), idsPharmacies);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeAclsPharmacies(final Personne personne) {
        // Suppression des acls uniquement pour les admin et les pharmaciens
        if (personne.getIsAdmin() || TypePersonne.PHARMACIEN.equals(personne.getType())) {
            // Suppression des acls existantes
            this.aclSearchDao.removeAclsPharmaciesOfPersonne(personne.getId());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void updateAclsEssais(final Essai essai) {
        // Suppression des acls existantes
        this.aclSearchDao.removeAclsEssaisOfEssai(essai.getId());
        // Création des acls pour les admins
        this.aclSearchDao.saveAclsEssaisForAdmins(essai.getId());
        // Création des acls pour les pharmaciens
        this.aclSearchDao.saveAclsEssaisForPharmaciens(essai.getId());
        // Création des acls pour les autres profils
        this.aclSearchDao.saveAclsEssaisForOthersProfils(essai.getId());
    }
}
