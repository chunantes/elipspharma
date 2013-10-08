package fr.pharma.eclipse.domain.model.produit;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Where;

import fr.pharma.eclipse.domain.enums.produit.ClasseDM;
import fr.pharma.eclipse.domain.enums.produit.NatureDM;
import fr.pharma.eclipse.domain.model.produit.document.DocumentRandomisation;

/**
 * Classe métier représentant un dispositif médical.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "dispositif_medical")
public class DispositifMedical extends Produit {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -5530934611041931166L;

    /**
     * Modèle.
     */
    @Column(name = "modele")
    private String modele;

    /**
     * Nomenclature.
     */
    @Column(name = "nomenclature")
    private String nomenclature;

    /**
     * Marquage CE.
     */
    @Column(name = "marquageCE")
    private Boolean marquageCE;

    /**
     * Randomisation.
     */
    @Column(name = "randomisation")
    private Boolean randomisation;

    /**
     * Statut LPP.
     */
    @Column(name = "statutLpp")
    private String statutLpp;

    /**
     * Code LPPR.
     */
    @Column(name = "codeLppr")
    private String codeLppr;

    /**
     * Code du logiciel de gestion économique.
     */
    @Column(name = "codeLogiciel")
    private String codeLogicielGestion;

    /**
     * Fournisseur.
     */
    @Column(name = "fournisseur")
    private String fournisseur;

    /**
     * Numéro de marché.
     */
    @Column(name = "numeroMarche")
    private String numeroMarche;

    /**
     * Nature du DM.
     */
    @Column(name = "nature")
    @Enumerated(EnumType.STRING)
    private NatureDM nature;

    /**
     * Classe du DM.
     */
    @Column(name = "classe")
    @Enumerated(EnumType.STRING)
    private ClasseDM classe;

    /**
     * Document randomisation.
     */
    @OneToOne(mappedBy = "produit", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Where(clause = "type='RANDOMISATION'")
    private DocumentRandomisation documentRandomisation;

    /**
     * {@inheritDoc}
     */
    @Override
    public Produit cloneMe() {
        final DispositifMedical dm = new DispositifMedical();

        // Clonage de la super classe.
        super.cloneMe(dm);

        // clonage spécifique au DM.
        dm.setClasse(this.getClasse());
        dm.setCodeLogicielGestion(this.getCodeLogicielGestion());
        dm.setCodeLppr(this.getCodeLppr());
        dm.setFournisseur(this.getFournisseur());
        dm.setStatutLpp(this.getStatutLpp());
        dm.setRandomisation(this.getRandomisation());
        dm.setNumeroMarche(this.getNumeroMarche());
        dm.setNature(this.getNature());
        dm.setModele(this.getModele());
        dm.setMarquageCE(this.getMarquageCE());
        dm.setNomenclature(this.getNomenclature());
        return dm;
    }
    /**
     * Getter sur modele.
     * @return Retourne le modele.
     */
    public String getModele() {
        return this.modele;
    }

    /**
     * Setter pour modele.
     * @param modele le modele à écrire.
     */
    public void setModele(final String modele) {
        this.modele = modele;
    }

    /**
     * Getter sur nomenclature.
     * @return Retourne le nomenclature.
     */
    public String getNomenclature() {
        return this.nomenclature;
    }

    /**
     * Setter pour nomenclature.
     * @param nomenclature le nomenclature à écrire.
     */
    public void setNomenclature(final String nomenclature) {
        this.nomenclature = nomenclature;
    }

    /**
     * Getter sur marquageCE.
     * @return Retourne le marquageCE.
     */
    public Boolean getMarquageCE() {
        return this.marquageCE;
    }

    /**
     * Setter pour marquageCE.
     * @param marquageCE le marquageCE à écrire.
     */
    public void setMarquageCE(final Boolean marquageCE) {
        this.marquageCE = marquageCE;
    }

    /**
     * Getter sur statutLpp.
     * @return Retourne le statutLpp.
     */
    public String getStatutLpp() {
        return this.statutLpp;
    }

    /**
     * Setter pour statutLpp.
     * @param statutLpp le statutLpp à écrire.
     */
    public void setStatutLpp(final String statutLpp) {
        this.statutLpp = statutLpp;
    }

    /**
     * Getter sur codeLppr.
     * @return Retourne le codeLppr.
     */
    public String getCodeLppr() {
        return this.codeLppr;
    }

    /**
     * Setter pour codeLppr.
     * @param codeLppr le codeLppr à écrire.
     */
    public void setCodeLppr(final String codeLppr) {
        this.codeLppr = codeLppr;
    }

    /**
     * Getter sur codeLogicielGestion.
     * @return Retourne le codeLogicielGestion.
     */
    public String getCodeLogicielGestion() {
        return this.codeLogicielGestion;
    }

    /**
     * Setter pour codeLogicielGestion.
     * @param codeLogicielGestion le codeLogicielGestion à écrire.
     */
    public void setCodeLogicielGestion(final String codeLogicielGestion) {
        this.codeLogicielGestion = codeLogicielGestion;
    }

    /**
     * Getter sur numeroMarche.
     * @return Retourne le numeroMarche.
     */
    public String getNumeroMarche() {
        return this.numeroMarche;
    }

    /**
     * Setter pour numeroMarche.
     * @param numeroMarche le numeroMarche à écrire.
     */
    public void setNumeroMarche(final String numeroMarche) {
        this.numeroMarche = numeroMarche;
    }

    /**
     * Getter sur nature.
     * @return Retourne le nature.
     */
    public NatureDM getNature() {
        return this.nature;
    }

    /**
     * Setter pour nature.
     * @param nature le nature à écrire.
     */
    public void setNature(final NatureDM nature) {
        this.nature = nature;
    }

    /**
     * Getter sur classe.
     * @return Retourne le classe.
     */
    public ClasseDM getClasse() {
        return this.classe;
    }

    /**
     * Setter pour classe.
     * @param classe le classe à écrire.
     */
    public void setClasse(final ClasseDM classe) {
        this.classe = classe;
    }

    /**
     * Getter sur randomisation.
     * @return Retourne le randomisation.
     */
    public Boolean getRandomisation() {
        return this.randomisation;
    }

    /**
     * Setter pour randomisation.
     * @param randomisation le randomisation à écrire.
     */
    public void setRandomisation(final Boolean randomisation) {
        this.randomisation = randomisation;
    }

    /**
     * Getter sur fournisseur.
     * @return Retourne le fournisseur.
     */
    public String getFournisseur() {
        return this.fournisseur;
    }

    /**
     * Setter pour fournisseur.
     * @param fournisseur le fournisseur à écrire.
     */
    public void setFournisseur(final String fournisseur) {
        this.fournisseur = fournisseur;
    }
    /**
     * Getter sur documentRandomisation.
     * @return Retourne le documentRandomisation.
     */
    public DocumentRandomisation getDocumentRandomisation() {
        return this.documentRandomisation;
    }
    /**
     * Setter pour documentRandomisation.
     * @param documentRandomisation le documentRandomisation à écrire.
     */
    public void setDocumentRandomisation(final DocumentRandomisation documentRandomisation) {
        this.documentRandomisation = documentRandomisation;
    }

}
