package fr.pharma.eclipse.domain.model.builder;

import fr.pharma.eclipse.domain.enums.TypePromoteur;
import fr.pharma.eclipse.domain.model.acteur.ArcPromoteur;
import fr.pharma.eclipse.domain.model.acteur.ContactPromoteur;
import fr.pharma.eclipse.domain.model.acteur.Promoteur;

/**
 * Classe de builder de Promoteur.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PromoteurBuilder {

    private final Promoteur promoteur;

    public PromoteurBuilder() {
        this.promoteur = new Promoteur();
    }

    public PromoteurBuilder withId(final Long id) {
        this.promoteur.setId(id);
        return this;
    }

    public PromoteurBuilder withRaisonSociale(final String value) {
        this.promoteur.setRaisonSociale(value);
        return this;
    }

    public PromoteurBuilder withType(final TypePromoteur value) {
        this.promoteur.setType(value);
        return this;
    }

    public PromoteurBuilder withIdentifiant(final String value) {
        this.promoteur.setIdentifiant(value);
        return this;
    }

    public PromoteurBuilder addArcPromoteurs(final ArcPromoteur value) {
        this.promoteur.getArcPromoteurs().add(value);
        return this;
    }

    public PromoteurBuilder addContactPromoteur(final ContactPromoteur value) {
        this.promoteur.getContactPromoteurs().add(value);
        return this;
    }

    public Promoteur build() {
        return this.promoteur;
    }

    public Promoteur buildGFM() {
        this.withId(1L);
        this.withRaisonSociale("GFM - Groupe Français des Myélodysplasies");
        this.withType(TypePromoteur.ACADEMIQUE);
        return this.promoteur;
    }

}
