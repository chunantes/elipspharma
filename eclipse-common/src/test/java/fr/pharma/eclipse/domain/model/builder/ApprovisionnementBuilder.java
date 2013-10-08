package fr.pharma.eclipse.domain.model.builder;

import java.util.Calendar;

import fr.pharma.eclipse.domain.enums.stock.MotifRefus;
import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stock.Approvisionnement;
import fr.pharma.eclipse.domain.model.stock.document.DocumentAppro;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;

/**
 * Classe de builder de Approvisionnement.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ApprovisionnementBuilder {

    private final Approvisionnement approvisionnement;

    public ApprovisionnementBuilder() {
        this.approvisionnement = new Approvisionnement();
    }

    public ApprovisionnementBuilder withId(final Long id) {
        this.approvisionnement.setId(id);
        return this;
    }

    public ApprovisionnementBuilder withMotifRefus(final MotifRefus value) {
        this.approvisionnement.setMotifRefus(value);
        return this;
    }

    public ApprovisionnementBuilder withCommentaireRefus(final String value) {
        this.approvisionnement.setCommentaireRefus(value);
        return this;
    }

    public ApprovisionnementBuilder withDateReception(final Calendar value) {
        this.approvisionnement.setDateReception(value);
        return this;
    }

    public ApprovisionnementBuilder withDateArriveeColis(final Calendar value) {
        this.approvisionnement.setDateArriveeColis(value);
        return this;
    }

    public ApprovisionnementBuilder withCommentaireExtensionPeremption(final String value) {
        this.approvisionnement.setCommentaireExtensionPeremption(value);
        return this;
    }

    public ApprovisionnementBuilder withExtensionPeremption(final Boolean value) {
        this.approvisionnement.setExtensionPeremption(value);
        return this;
    }

    public ApprovisionnementBuilder withDocumentAppro(final DocumentAppro value) {
        this.approvisionnement.setDocumentAppro(value);
        return this;
    }

    public ApprovisionnementBuilder withHistoriqueExtensionPeremption(final String value) {
        this.approvisionnement.setHistoriqueExtensionPeremption(value);
        return this;
    }

    public ApprovisionnementBuilder withProduit(final Produit value) {
        this.approvisionnement.setProduit(value);
        return this;
    }

    public ApprovisionnementBuilder withPersonne(final Personne value) {
        this.approvisionnement.setPersonne(value);
        return this;
    }

    public ApprovisionnementBuilder withQuantite(final Integer value) {
        this.approvisionnement.setQuantite(value);
        return this;
    }

    public ApprovisionnementBuilder withType(final TypeMvtStock value) {
        this.approvisionnement.setType(value);
        return this;
    }

    public ApprovisionnementBuilder withEssai(final Essai value) {
        this.approvisionnement.setEssai(value);
        return this;
    }

    public ApprovisionnementBuilder withDateCreation(final Calendar value) {
        this.approvisionnement.setDateCreation(value);
        return this;
    }

    public ApprovisionnementBuilder withConditionnement(final Conditionnement value) {
        this.approvisionnement.setConditionnement(value);
        return this;
    }

    public ApprovisionnementBuilder withPharmacie(final Pharmacie value) {
        this.approvisionnement.setPharmacie(value);
        return this;
    }

    public ApprovisionnementBuilder withNumLot(final String value) {
        this.approvisionnement.setNumLot(value);
        return this;
    }

    public ApprovisionnementBuilder withNumTraitement(final String value) {
        this.approvisionnement.setNumTraitement(value);
        return this;
    }

    public ApprovisionnementBuilder withApproApprouve(final Boolean value) {
        this.approvisionnement.setApproApprouve(value);
        return this;
    }

    public ApprovisionnementBuilder withDatePeremption(final Calendar value) {
        this.approvisionnement.setDatePeremption(value);
        return this;
    }

    public Approvisionnement build() {
        return this.approvisionnement;
    }

}
