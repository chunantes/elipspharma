package fr.pharma.eclipse.domain.model.builder;

import fr.pharma.eclipse.domain.model.localisation.Etablissement;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;

/**
 * Classe de Builder de Etablissement.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EtablissementBuilder {

    private final Etablissement etablissement;

    public EtablissementBuilder() {
        this.etablissement = new Etablissement();
    }

    public EtablissementBuilder withId(final Long id) {
        this.etablissement.setId(id);
        return this;
    }

    public EtablissementBuilder withNom(final String value) {
        this.etablissement.setNom(value);
        return this;
    }

    public EtablissementBuilder withTelephone(final String value) {
        this.etablissement.setTelephone(value);
        return this;
    }

    public EtablissementBuilder withFax(final String value) {
        this.etablissement.setFax(value);
        return this;
    }

    public EtablissementBuilder withMail(final String value) {
        this.etablissement.setMail(value);
        return this;
    }

    public EtablissementBuilder withAdresseDirection(final String value) {
        this.etablissement.setAdresseDirection(value);
        return this;
    }

    public EtablissementBuilder withVille(final String value) {
        this.etablissement.setVille(value);
        return this;
    }

    public EtablissementBuilder withPays(final String value) {
        this.etablissement.setPays(value);
        return this;
    }

    public EtablissementBuilder withCodePostal(final String value) {
        this.etablissement.setCodePostal(value);
        return this;
    }

    public EtablissementBuilder addPharmacie(final Pharmacie value) {
        this.etablissement.getPharmacies().add(value);
        return this;
    }

    public Etablissement build() {
        return this.etablissement;
    }

    public Etablissement buildChuNantes() {
        this.withId(1L);
        this.withNom("CHU Nantes");
        return this.etablissement;
    }
}
