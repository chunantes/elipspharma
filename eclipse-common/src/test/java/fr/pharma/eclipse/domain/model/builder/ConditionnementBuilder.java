package fr.pharma.eclipse.domain.model.builder;

import java.math.BigDecimal;

import fr.pharma.eclipse.domain.enums.produit.FormeConditionnement;
import fr.pharma.eclipse.domain.enums.produit.ModePrescription;
import fr.pharma.eclipse.domain.enums.produit.UniteDosage;
import fr.pharma.eclipse.domain.enums.produit.UniteGestion;
import fr.pharma.eclipse.domain.enums.produit.VoieAdministration;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Produit;

/**
 * Classe de builder de Conditionnement.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ConditionnementBuilder {

    private final Conditionnement conditionnement;

    public ConditionnementBuilder() {
        this.conditionnement = new Conditionnement();
    }

    public ConditionnementBuilder withId(final Long id) {
        this.conditionnement.setId(id);
        return this;
    }

    public void withLibelle(final String value) {
        this.conditionnement.setLibelle(value);
    }

    public void withModePrescription(final ModePrescription value) {
        this.conditionnement.setModePrescription(value);
    }

    public void withUniteGestion(final UniteGestion value) {
        this.conditionnement.setUniteGestion(value);
    }

    public void withUnitePrescription(final String value) {
        this.conditionnement.setUnitePrescription(value);
    }

    public void withVoieAdministration(final VoieAdministration value) {
        this.conditionnement.setVoieAdministration(value);
    }

    public void withDosage(final BigDecimal value) {
        this.conditionnement.setDosage(value);
    }

    public void withContenance(final BigDecimal value) {
        this.conditionnement.setContenance(value);
    }

    public void withUniteContenance(final UniteDosage value) {
        this.conditionnement.setUniteContenance(value);
    }

    public void withNbUnitePrescription(final BigDecimal value) {
        this.conditionnement.setNbUnitePrescription(value);
    }

    public void withUniteDosage(final UniteDosage value) {
        this.conditionnement.setUniteDosage(value);
    }

    public void withQuantiteParPatient(final Integer value) {
        this.conditionnement.setQuantiteParPatient(value);
    }

    public void withProduit(final Produit value) {
        this.conditionnement.setProduit(value);
    }

    public void withForme(final FormeConditionnement value) {
        this.conditionnement.setForme(value);
    }

    public Conditionnement build() {
        return this.conditionnement;
    }

    public Conditionnement buildRevLimid10mg() {
        this.withLibelle("LENALIDOMIDE 10 mg");
        return this.conditionnement;
    }

}
