package fr.pharma.eclipse.domain.model.surcout;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.domain.model.essai.detail.surcout.DetailSurcout;

/**
 * Classe du modèle contenant les données prévisionnelles des surcouts.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "donnees_prevision")
public class DonneesPrevision extends BeanObject {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 7342698218368579780L;

    /**
     * DetailSurcout de l'essai auquel est rattaché le bean DonneesPrevision.
     */
    @OneToOne()
    @JoinColumn(name = "id_detail_surcout")
    private DetailSurcout detailSurcout;

    /**
     * Nombre d'inclusions.
     */
    @Column(name = "nbInclusions")
    private Integer nbInclusions;

    /**
     * Nombre de prescriptions.
     */
    @Column(name = "nbPrescriptions")
    private Integer nbPrescriptions;

    /**
     * Nombre de destructions.
     */
    @Column(name = "nbDestructions")
    private Integer nbDestructions;

    /**
     * Nombre de dispensations.
     */
    @Column(name = "nbDispensations")
    private Integer nbDispensations;

    /**
     * Nombre de dispensations renouvellement.
     */
    @Column(name = "nbDispensationsRenouvellement")
    private Integer nbDispensationsRenouvellement;

    /**
     * Nombre de réétiquetages.
     */
    @Column(name = "nbReetiquetages")
    private Integer nbReetiquetages;

    /**
     * Nombre de réétiquetages.
     */
    @Column(name = "nbVisiteMonitoring")
    private Integer nbVisiteMonitoring;

    /**
     * Durée de l'essai en années.
     */
    @Column(name = "nbAnnees")
    private Integer nbAnnees;

    /**
     * Nombre d'approvisionnements.
     */
    @Column(name = "nbApprovisionnements")
    private Integer nbApprovisionnements;

    /**
     * Nombre de Préparations stériles.
     */
    @Column(name = "nbPreparationsSteriles")
    private Integer nbPreparationsSteriles;

    /**
     * Nombre de préparations non stériles.
     */
    @Column(name = "nbPreparationsNonSteriles")
    private Integer nbPreparationsNonSteriles;

    /**
     * Nombre de visites de type audit.
     */
    @Column(name = "nbAudits")
    private Integer nbAudits;

    /**
     * Getter sur nbInclusions.
     * @return Retourne le nbInclusions.
     */
    public Integer getNbInclusions() {
        return this.nbInclusions;
    }

    /**
     * Getter sur nbPrescriptions.
     * @return Retourne le nbPrescriptions.
     */
    public Integer getNbPrescriptions() {
        return this.nbPrescriptions;
    }

    /**
     * Getter sur nbDestructions.
     * @return Retourne le nbDestructions.
     */
    public Integer getNbDestructions() {
        return this.nbDestructions;
    }

    /**
     * Getter sur nbDispensations.
     * @return Retourne le nbDispensations.
     */
    public Integer getNbDispensations() {
        return this.nbDispensations;
    }

    /**
     * Getter sur nbReetiquetages.
     * @return Retourne le nbReetiquetages.
     */
    public Integer getNbReetiquetages() {
        return this.nbReetiquetages;
    }

    /**
     * Setter pour nbInclusions.
     * @param nbInclusions le nbInclusions à écrire.
     */
    public void setNbInclusions(final Integer nbInclusions) {
        this.nbInclusions = nbInclusions;
    }

    /**
     * Setter pour nbPrescriptions.
     * @param nbPrescriptions le nbPrescriptions à écrire.
     */
    public void setNbPrescriptions(final Integer nbPrescriptions) {
        this.nbPrescriptions = nbPrescriptions;
    }

    /**
     * Setter pour nbDestructions.
     * @param nbDestructions le nbDestructions à écrire.
     */
    public void setNbDestructions(final Integer nbDestructions) {
        this.nbDestructions = nbDestructions;
    }

    /**
     * Setter pour nbDispensations.
     * @param nbDispensations le nbDispensations à écrire.
     */
    public void setNbDispensations(final Integer nbDispensations) {
        this.nbDispensations = nbDispensations;
    }

    /**
     * Setter pour nbReetiquetages.
     * @param nbReetiquetages le nbReetiquetages à écrire.
     */
    public void setNbReetiquetages(final Integer nbReetiquetages) {
        this.nbReetiquetages = nbReetiquetages;
    }

    /**
     * Getter sur detailSurcout.
     * @return Retourne le detailSurcout.
     */
    public DetailSurcout getDetailSurcout() {
        return this.detailSurcout;
    }

    /**
     * Setter pour detailSurcout.
     * @param detailSurcout le detailSurcout à écrire.
     */
    public void setDetailSurcout(final DetailSurcout detailSurcout) {
        this.detailSurcout = detailSurcout;
    }

    /**
     * Getter sur nbAnnees.
     * @return Retourne le nbAnnees.
     */
    public Integer getNbAnnees() {
        return this.nbAnnees;
    }

    /**
     * Setter pour nbAnnees.
     * @param nbAnnees le nbAnnees à écrire.
     */
    public void setNbAnnees(final Integer nbAnnees) {
        this.nbAnnees = nbAnnees;
    }

    /**
     * Getter pour nbVisiteMonitoring.
     * @return Le nbVisiteMonitoring
     */
    public Integer getNbVisiteMonitoring() {
        return this.nbVisiteMonitoring;
    }

    /**
     * Setter pour nbVisiteMonitoring.
     * @param nbVisiteMonitoring Le nbVisiteMonitoring à écrire.
     */
    public void setNbVisiteMonitoring(final Integer nbVisiteMonitoring) {
        this.nbVisiteMonitoring = nbVisiteMonitoring;
    }

    /**
     * Getter pour nbDispensationsRenouvellement.
     * @return Le nbDispensationsRenouvellement
     */
    public Integer getNbDispensationsRenouvellement() {
        return this.nbDispensationsRenouvellement;
    }

    /**
     * Setter pour nbDispensationsRenouvellement.
     * @param nbDispensationsRenouvellement Le nbDispensationsRenouvellement à
     * écrire.
     */
    public void setNbDispensationsRenouvellement(final Integer nbDispensationsRenouvellement) {
        this.nbDispensationsRenouvellement = nbDispensationsRenouvellement;
    }

    /**
     * Getter pour nbApprovisionnements.
     * @return Le nbApprovisionnements
     */
    public Integer getNbApprovisionnements() {
        return this.nbApprovisionnements;
    }

    /**
     * Setter pour nbApprovisionnements.
     * @param nbApprovisionnements Le nbApprovisionnements à écrire.
     */
    public void setNbApprovisionnements(final Integer nbApprovisionnements) {
        this.nbApprovisionnements = nbApprovisionnements;
    }

    /**
     * Getter pour nbPreparationsSteriles.
     * @return Le nbPreparationsSteriles
     */
    public Integer getNbPreparationsSteriles() {
        return this.nbPreparationsSteriles;
    }

    /**
     * Setter pour nbPreparationsSteriles.
     * @param nbPreparationsSteriles Le nbPreparationsSteriles à écrire.
     */
    public void setNbPreparationsSteriles(final Integer nbPreparationsSteriles) {
        this.nbPreparationsSteriles = nbPreparationsSteriles;
    }

    /**
     * Getter pour nbPreparationsNonSteriles.
     * @return Le nbPreparationsNonSteriles
     */
    public Integer getNbPreparationsNonSteriles() {
        return this.nbPreparationsNonSteriles;
    }

    /**
     * Setter pour nbPreparationsNonSteriles.
     * @param nbPreparationsNonSteriles Le nbPreparationsNonSteriles à écrire.
     */
    public void setNbPreparationsNonSteriles(final Integer nbPreparationsNonSteriles) {
        this.nbPreparationsNonSteriles = nbPreparationsNonSteriles;
    }

    /**
     * Getter pour nbAudits.
     * @return Le nbAudits
     */
    public Integer getNbAudits() {
        return this.nbAudits;
    }

    /**
     * Setter pour nbAudits.
     * @param nbAudits Le nbAudits à écrire.
     */
    public void setNbAudits(final Integer nbAudits) {
        this.nbAudits = nbAudits;
    }

}
