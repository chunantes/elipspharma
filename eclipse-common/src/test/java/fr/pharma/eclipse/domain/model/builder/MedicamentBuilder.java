package fr.pharma.eclipse.domain.model.builder;

import fr.pharma.eclipse.domain.enums.produit.NatureProduit;
import fr.pharma.eclipse.domain.enums.produit.TypeProduit;
import fr.pharma.eclipse.domain.model.essai.detail.produit.DetailProduit;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.produit.detail.DetailLogistique;
import fr.pharma.eclipse.domain.model.produit.document.DocumentConditionnement;
import fr.pharma.eclipse.domain.model.produit.document.DocumentEtiquetage;
import fr.pharma.eclipse.domain.model.produit.document.DocumentFabrication;
import fr.pharma.eclipse.domain.model.produit.document.DocumentReconstitutionPSM;
import fr.pharma.eclipse.domain.model.produit.document.DocumentReconstitutionSimple;

/**
 * Classe de builder de Medicament.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class MedicamentBuilder {

    private final Medicament medicament;

    public MedicamentBuilder() {
        this.medicament = new Medicament();
    }

    public MedicamentBuilder withId(final Long id) {
        this.medicament.setId(id);
        return this;
    }

    public MedicamentBuilder withDenomination(final String value) {
        this.medicament.setDenomination(value);
        return this;
    }

    public MedicamentBuilder withCode(final String value) {
        this.medicament.setCode(value);
        return this;
    }

    public MedicamentBuilder withClasseTherapeutique(final String value) {
        this.medicament.setClasseTherapeutique(value);
        return this;
    }

    public MedicamentBuilder withType(final TypeProduit value) {
        this.medicament.setType(value);
        return this;
    }

    public MedicamentBuilder withDetailProduit(final DetailProduit value) {
        this.medicament.setDetailProduit(value);
        return this;
    }

    public MedicamentBuilder withConseils(final String value) {
        this.medicament.setConseils(value);
        return this;
    }

    public MedicamentBuilder withImputationUf(final Boolean value) {
        this.medicament.setImputationUf(value);
        return this;
    }

    public MedicamentBuilder withDetailLogistique(final DetailLogistique value) {
        this.medicament.setDetailLogistique(value);
        return this;
    }

    public MedicamentBuilder addConditionnements(final Conditionnement value) {
        this.medicament.getConditionnements().add(value);
        return this;
    }

    public MedicamentBuilder withAlerteActive(final Boolean value) {
        this.medicament.setAlerteActive(value);
        return this;
    }

    public MedicamentBuilder withStupefiant(final Boolean value) {
        this.medicament.setStupefiant(value);
        return this;
    }

    public MedicamentBuilder withMds(final Boolean value) {
        this.medicament.setMds(value);
        return this;
    }

    public MedicamentBuilder withDci(final String value) {
        this.medicament.setDci(value);
        return this;
    }

    public MedicamentBuilder withReconstitutionSimple(final Boolean value) {
        this.medicament.setReconstitutionSimple(value);
        return this;
    }

    public MedicamentBuilder withReconstitutionPSM(final Boolean value) {
        this.medicament.setReconstitutionPSM(value);
        return this;
    }

    public MedicamentBuilder withFabrication(final Boolean value) {
        this.medicament.setFabrication(value);
        return this;
    }

    public MedicamentBuilder withConditionnement(final Boolean value) {
        this.medicament.setConditionnement(value);
        return this;
    }

    public MedicamentBuilder withEtiquetage(final Boolean value) {
        this.medicament.setEtiquetage(value);
        return this;
    }

    public MedicamentBuilder withNature(final NatureProduit value) {
        this.medicament.setNature(value);
        return this;
    }

    public MedicamentBuilder withDocumentReconstitutionSimple(final DocumentReconstitutionSimple value) {
        this.medicament.setDocumentReconstitutionSimple(value);
        return this;
    }

    public MedicamentBuilder withDocumentReconstitutionPSM(final DocumentReconstitutionPSM value) {
        this.medicament.setDocumentReconstitutionPSM(value);
        return this;
    }

    public MedicamentBuilder withDocumentFabrication(final DocumentFabrication value) {
        this.medicament.setDocumentFabrication(value);
        return this;
    }

    public MedicamentBuilder withDocumentConditionnement(final DocumentConditionnement value) {
        this.medicament.setDocumentConditionnement(value);
        return this;
    }

    public MedicamentBuilder withDocumentEtiquetage(final DocumentEtiquetage value) {
        this.medicament.setDocumentEtiquetage(value);
        return this;
    }

    public Medicament build() {
        return this.medicament;
    }

    public Medicament buildRevLimid() {
        this.withDenomination("REVLIMID");
        return this.medicament;
    }

}
