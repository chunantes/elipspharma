package fr.pharma.eclipse.service.indicateur.builder.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import fr.pharma.eclipse.domain.criteria.evenement.EvenementSearchCriteria;
import fr.pharma.eclipse.domain.enums.evenement.ResultatVisite;
import fr.pharma.eclipse.domain.enums.evenement.TypeEvenement;
import fr.pharma.eclipse.domain.enums.evenement.TypeVisite;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.evenement.Evenement;
import fr.pharma.eclipse.domain.model.indicateur.Indicateur;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.evenement.EvenementService;
import fr.pharma.eclipse.service.indicateur.builder.IndicateurBuilder;

/**
 * Implémentation pour l'indicateur de mise en place. Il compte le nombre de
 * visite de mise en place durant la période.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class GenericEvenementBuilder implements IndicateurBuilder, Serializable {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 6893465536839881943L;

    /**
     * TypeEvenement.
     */
    private TypeEvenement typeEvenement;

    /**
     * Type de visite
     */
    private TypeVisite typeVisite;

    /**
     * Libellé.
     */
    private String libelle;

    /**
     * Service Evenement.
     */
    @Resource(name = "evenementService")
    private EvenementService service;

    /**
     * {@inheritDoc}
     */
    @Override
    public Indicateur build(final Pharmacie pharmacie,
                            final Calendar dateDebut,
                            final Calendar dateFin) {
        // on récupère toutes les évènements correspondants
        final EvenementSearchCriteria criteria = new EvenementSearchCriteria();
        criteria.setTypeEvenement(this.typeEvenement);
        criteria.setTypeVisite(this.typeVisite);
        criteria.setDateDebut(dateDebut);
        criteria.setDateFin(dateFin);
        criteria.setResultatVisite(ResultatVisite.EFFECTUE);

        final List<Evenement> evenements = this.service.getAllWithoutPurge(criteria);

        // on les filtre selon la pharmacie.
        CollectionUtils.filter(evenements, new Predicate() {

            @Override
            public boolean evaluate(final Object object) {
                final Essai essai = ((Evenement) object).getEssai();
                return essai.getPharmaciePrincipale().equals(pharmacie) || essai.getDetailDonneesPharma().getPharmacies().contains(pharmacie);
            }
        });

        return new Indicateur(this.libelle, new BigDecimal(evenements.size()));
    }
    /**
     * Setter pour service.
     * @param service Le service à écrire.
     */
    public void setService(final EvenementService service) {
        this.service = service;
    }

    /**
     * Getter pour typeEvenement.
     * @return Le typeEvenement
     */
    public TypeEvenement getTypeEvenement() {
        return this.typeEvenement;
    }

    /**
     * Setter pour typeEvenement.
     * @param typeEvenement Le typeEvenement à écrire.
     */
    public void setTypeEvenement(final TypeEvenement typeEvenement) {
        this.typeEvenement = typeEvenement;
    }

    /**
     * Getter pour typeVisite.
     * @return Le typeVisite
     */
    public TypeVisite getTypeVisite() {
        return this.typeVisite;
    }

    /**
     * Setter pour typeVisite.
     * @param typeVisite Le typeVisite à écrire.
     */
    public void setTypeVisite(final TypeVisite typeVisite) {
        this.typeVisite = typeVisite;
    }

    /**
     * Getter pour libelle.
     * @return Le libelle
     */
    public String getLibelle() {
        return this.libelle;
    }

    /**
     * Setter pour libelle.
     * @param libelle Le libelle à écrire.
     */
    public void setLibelle(final String libelle) {
        this.libelle = libelle;
    }
}
