package fr.pharma.eclipse.domain.model.builder;

import fr.pharma.eclipse.domain.model.acteur.Pharmacien;
import fr.pharma.eclipse.domain.model.essai.detail.pharma.DetailDonneesPharma;
import fr.pharma.eclipse.domain.model.localisation.Etablissement;
import fr.pharma.eclipse.domain.model.localisation.Site;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.domain.model.stockage.Stockage;

/**
 * Classe de builder de Pharmacie.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PharmacieBuilder {

    private final Pharmacie pharmacie;

    public PharmacieBuilder() {
        this.pharmacie = new Pharmacie();
    }

    public PharmacieBuilder withId(final Long id) {
        this.pharmacie.setId(id);
        return this;
    }

    public PharmacieBuilder withNom(final String value) {
        this.pharmacie.setNom(value);
        return this;
    }

    public PharmacieBuilder withAdresse(final String value) {
        this.pharmacie.setAdresse(value);
        return this;
    }

    public PharmacieBuilder withAdresseLivraison(final String value) {
        this.pharmacie.setAdresseLivraison(value);
        return this;
    }

    public PharmacieBuilder withEtablissement(final Etablissement value) {
        this.pharmacie.setEtablissement(value);
        return this;
    }

    public PharmacieBuilder addDetailsDonneesPharma(final DetailDonneesPharma value) {
        this.pharmacie.getDetailsDonneesPharma().add(value);
        return this;
    }

    public PharmacieBuilder addSite(final Site value) {
        this.pharmacie.getSites().add(value);
        return this;
    }

    public PharmacieBuilder addPharmacien(final Pharmacien value) {
        this.pharmacie.getPharmaciens().add(value);
        return this;
    }

    public PharmacieBuilder withStockages(final Stockage value) {
        this.pharmacie.getStockages().add(value);
        return this;
    }

    public PharmacieBuilder withTelephone(final String value) {
        this.pharmacie.setTelephone(value);
        return this;
    }

    public PharmacieBuilder withFax(final String value) {
        this.pharmacie.setFax(value);
        return this;
    }

    public PharmacieBuilder withResponsablePrincipal(final String value) {
        this.pharmacie.setResponsablePrincipal(value);
        return this;
    }

    public PharmacieBuilder withNumOrdonnancierDisp(final Integer value) {
        this.pharmacie.setNumOrdonnancierDisp(value);
        return this;
    }

    public PharmacieBuilder withNumOrdonnancierFab(final Integer value) {
        this.pharmacie.setNumOrdonnancierFab(value);
        return this;
    }

    public Pharmacie build() {
        return this.pharmacie;
    }

    public Pharmacie buildPharmaHotelDieu() {
        this.withId(1L);
        this.withNom("GFM-Chimio-Rev-08");
        this.withEtablissement(new EtablissementBuilder().buildChuNantes());
        return this.pharmacie;
    }
}
