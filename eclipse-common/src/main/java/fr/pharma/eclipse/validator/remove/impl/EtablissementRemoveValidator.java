package fr.pharma.eclipse.validator.remove.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import fr.pharma.eclipse.domain.criteria.localisation.PoleSearchCriteria;
import fr.pharma.eclipse.domain.criteria.localisation.SiteSearchCriteria;
import fr.pharma.eclipse.domain.criteria.stockage.PharmacieSearchCriteria;
import fr.pharma.eclipse.domain.model.localisation.Etablissement;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.service.localisation.PoleService;
import fr.pharma.eclipse.service.localisation.SiteService;
import fr.pharma.eclipse.service.stockage.PharmacieService;
import fr.pharma.eclipse.validator.remove.RemoveValidator;

/**
 * Classe de validation de suppression d'un objet Etablissement.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EtablissementRemoveValidator implements RemoveValidator<Etablissement>, Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -6036962109873985932L;

    /**
     * Service de gestion des pharmacies.
     */
    @Resource(name = "pharmacieService")
    private PharmacieService pharmacieService;

    /**
     * Service de gestion des pôles.
     */
    @Resource(name = "poleService")
    private PoleService poleService;

    /**
     * Service de gestion des sites.
     */
    @Resource(name = "siteService")
    private SiteService siteService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate(final Etablissement etablissement) {
        // Vérification Relation Etablissement-Pharmacie
        final PharmacieSearchCriteria pharmaCriteria = new PharmacieSearchCriteria();
        pharmaCriteria.setEtablissement(etablissement);
        if (this.pharmacieService.hasResult(pharmaCriteria)) {
            throw new ValidationException("remove", new String[]{"impossible" }, etablissement);
        }

        // Vérification Relation Etablissement-Pole
        final PoleSearchCriteria poleCriteria = new PoleSearchCriteria();
        poleCriteria.setEtablissement(etablissement);
        if (this.poleService.hasResult(poleCriteria)) {
            throw new ValidationException("remove", new String[]{"impossible" }, etablissement);
        }

        // Vérification Relation Etablissement-Site
        final SiteSearchCriteria siteCriteria = new SiteSearchCriteria();
        siteCriteria.setEtablissement(etablissement);
        if (this.siteService.hasResult(siteCriteria)) {
            throw new ValidationException("remove", new String[]{"impossible" }, etablissement);
        }

    }

    /**
     * Setter pour pharmacieService.
     * @param pharmacieService le pharmacieService à écrire.
     */
    public void setPharmacieService(final PharmacieService pharmacieService) {
        this.pharmacieService = pharmacieService;
    }

    /**
     * Setter pour poleService.
     * @param poleService le poleService à écrire.
     */
    public void setPoleService(final PoleService poleService) {
        this.poleService = poleService;
    }

    /**
     * Setter pour siteService.
     * @param siteService le siteService à écrire.
     */
    public void setSiteService(final SiteService siteService) {
        this.siteService = siteService;
    }

}
