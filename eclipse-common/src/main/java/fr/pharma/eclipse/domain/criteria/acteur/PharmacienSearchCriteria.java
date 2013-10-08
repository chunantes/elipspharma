package fr.pharma.eclipse.domain.criteria.acteur;

import java.util.ArrayList;
import java.util.List;

import fr.pharma.eclipse.domain.enums.TypePharmacien;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;

/**
 * Critère de recherche de pharmaciens.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PharmacienSearchCriteria extends PersonneSearchCriteria {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 7865783390550784270L;

    /**
     * Type de pharmacien.
     */
    private TypePharmacien typePharmacien;

    /**
     * Pharmacies.
     */
    private List<Pharmacie> pharmacies;

    /**
     * Getter sur typePharmacien.
     * @return Retourne le typePharmacien.
     */
    public TypePharmacien getTypePharmacien() {
        return this.typePharmacien;
    }

    /**
     * Setter pour typePharmacien.
     * @param typePharmacien le typePharmacien à écrire.
     */
    public void setTypePharmacien(final TypePharmacien typePharmacien) {
        this.typePharmacien = typePharmacien;
    }

    /**
     * Getter sur pharmacies.
     * @return Retourne le pharmacies.
     */
    public List<Pharmacie> getPharmacies() {
        return this.pharmacies;
    }

    /**
     * Setter pour pharmacies.
     * @param pharmacies le pharmacies à écrire.
     */
    public void setPharmacies(final List<Pharmacie> pharmacies) {
        this.pharmacies = pharmacies;
    }

    /**
     * Initialise la liste des pharmacies avec la pharmacie passée en
     * paramètres.
     * @param pharmacie Pharmacie.
     */
    public void setPharmacie(final Pharmacie pharmacie) {
        this.pharmacies = new ArrayList<Pharmacie>();
        if (pharmacie != null) {
            this.pharmacies.add(pharmacie);
        }
    }
}
