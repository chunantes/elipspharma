package fr.pharma.eclipse.domain.model.builder;

import java.util.Calendar;
import java.util.SortedSet;

import fr.pharma.eclipse.domain.enums.EtatEssai;
import fr.pharma.eclipse.domain.enums.TypePromoteur;
import fr.pharma.eclipse.domain.model.acteur.Promoteur;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.administratif.DetailAdministratif;
import fr.pharma.eclipse.domain.model.essai.detail.autresdocs.DetailAutresDocuments;
import fr.pharma.eclipse.domain.model.essai.detail.contact.DetailContacts;
import fr.pharma.eclipse.domain.model.essai.detail.dates.DetailDates;
import fr.pharma.eclipse.domain.model.essai.detail.design.DetailDesign;
import fr.pharma.eclipse.domain.model.essai.detail.etat.DetailEtatEssai;
import fr.pharma.eclipse.domain.model.essai.detail.faisabilite.DetailFaisabilite;
import fr.pharma.eclipse.domain.model.essai.detail.pharma.DetailDonneesPharma;
import fr.pharma.eclipse.domain.model.essai.detail.produit.DetailProduit;
import fr.pharma.eclipse.domain.model.essai.detail.recherche.DetailRecherche;
import fr.pharma.eclipse.domain.model.essai.detail.surcout.DetailSurcout;
import fr.pharma.eclipse.domain.model.incident.Incident;
import fr.pharma.eclipse.domain.model.localisation.Service;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;

/**
 * Classe de builder de Essai.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EssaiBuilder {

    private final Essai essai;

    public EssaiBuilder() {
        this.essai = new Essai();
    }

    public EssaiBuilder withId(final Long id) {
        this.essai.setId(id);
        return this;
    }

    public EssaiBuilder withNumInterne(final String value) {
        this.essai.setNumInterne(value);
        return this;
    }

    public EssaiBuilder withNom(final String value) {
        this.essai.setNom(value);
        return this;
    }

    public EssaiBuilder withDci(final String value) {
        this.essai.setDci(value);
        return this;
    }

    public EssaiBuilder withPromoteur(final Promoteur value) {
        this.essai.setPromoteur(value);
        return this;
    }

    public EssaiBuilder withEtat(final EtatEssai value) {
        this.essai.setEtat(value);
        return this;
    }

    public EssaiBuilder withCodePromoteur(final String value) {
        this.essai.setCodePromoteur(value);
        return this;
    }

    public EssaiBuilder withTypePromoteur(final TypePromoteur value) {
        this.essai.setTypePromoteur(value);
        return this;
    }

    public EssaiBuilder withEmplacementPhysiqueDossier(final String value) {
        this.essai.setEmplacementPhysiqueDossier(value);
        return this;
    }

    public EssaiBuilder withDetailRecherche(final DetailRecherche value) {
        this.essai.setDetailRecherche(value);
        return this;
    }

    public EssaiBuilder withLibelleProduitEvalue(final String value) {
        this.essai.setLibelleProduitEvalue(value);
        return this;
    }

    public EssaiBuilder addService(final Service value) {
        this.essai.getServices().add(value);
        return this;
    }

    public EssaiBuilder withDetailDates(final DetailDates value) {
        this.essai.setDetailDates(value);
        return this;
    }

    public EssaiBuilder withDetailFaisabilite(final DetailFaisabilite value) {
        this.essai.setDetailFaisabilite(value);
        return this;
    }

    public EssaiBuilder withAnneeCreation(final Integer value) {
        this.essai.setAnneeCreation(value);
        return this;
    }

    public EssaiBuilder withDetailAdministratif(final DetailAdministratif value) {
        this.essai.setDetailAdministratif(value);
        return this;
    }

    public EssaiBuilder withDetailProduit(final DetailProduit value) {
        this.essai.setDetailProduit(value);
        return this;
    }

    public EssaiBuilder withPharmaciePrincipale(final Pharmacie value) {
        this.essai.setPharmaciePrincipale(value);
        return this;
    }

    public EssaiBuilder withDetailContacts(final DetailContacts value) {
        this.essai.setDetailContacts(value);
        return this;
    }

    public EssaiBuilder withDetailDonneesPharma(final DetailDonneesPharma value) {
        this.essai.setDetailDonneesPharma(value);
        return this;
    }

    public EssaiBuilder withDetailDesign(final DetailDesign value) {
        this.essai.setDetailDesign(value);
        return this;
    }

    public EssaiBuilder withDetailAutresDocuments(final DetailAutresDocuments value) {
        this.essai.setDetailAutresDocuments(value);
        return this;
    }

    public EssaiBuilder withAlerteActive(final Boolean value) {
        this.essai.setAlerteActive(value);
        return this;
    }

    public EssaiBuilder withDetailsEtatEssai(final SortedSet<DetailEtatEssai> value) {
        this.essai.setDetailsEtatEssai(value);
        return this;
    }

    public EssaiBuilder withDetailSurcout(final DetailSurcout value) {
        this.essai.setDetailSurcout(value);
        return this;
    }

    public EssaiBuilder withIncidents(final Incident value) {
        this.essai.getIncidents().add(value);
        return this;
    }

    public EssaiBuilder withDateSignature(final Calendar value) {
        this.essai.setDateSignature(value);
        return this;
    }

    public Essai build() {
        return this.essai;
    }

    public Essai buildEssaiChimio() {
        this.withDetailFaisabilite(new DetailFaisabilite());
        this.withDetailProduit(new DetailProduit());
        this.withDetailContacts(new DetailContacts());
        this.withDetailDates(new DetailDates());
        this.withNumInterne("09/02-M");
        this.withCodePromoteur("GFM-Chimio-Rev-08");
        this.withNom("GFM-Chimio-Rev-08");
        this.addService(new ServiceBuilder().buildHematologie());
        this.withPromoteur(new PromoteurBuilder().buildGFM());
        this.withTypePromoteur(TypePromoteur.ACADEMIQUE);
        return this.essai;
    }

}
