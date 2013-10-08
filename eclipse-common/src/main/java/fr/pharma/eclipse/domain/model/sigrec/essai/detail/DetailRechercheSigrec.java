package fr.pharma.eclipse.domain.model.sigrec.essai.detail;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import fr.pharma.eclipse.domain.enums.NatureRecherche;
import fr.pharma.eclipse.domain.enums.ObjetRecherche;
import fr.pharma.eclipse.domain.enums.PhaseRecherche;
import fr.pharma.eclipse.domain.enums.QualiteInsu;
import fr.pharma.eclipse.domain.enums.TypeRecherche;
import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.domain.model.sigrec.essai.EssaiSigrec;

/**
 * Classe metier représentant les informations de détail general d'un essai
 * clinique importe de SIGREC.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "essai_detail_recherche_sigrec")
public class DetailRechercheSigrec extends BeanObject {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 6736243926941328369L;

    /**
     * Essai auquel est rattaché le détail.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_essai")
    private EssaiSigrec essai;

    /**
     * Titre du protocole.
     */
    @Column(name = "titreProtocole", columnDefinition = "TEXT")
    private String titreProtocole;

    /**
     * Numéro d'enregistrement (ou numéro SIGREC).
     */
    @Column(name = "numEnregistrement")
    private String numEnregistrement;

    /**
     * Type de recherche.
     */
    @Column(name = "typeRecherche")
    @Enumerated(EnumType.STRING)
    private TypeRecherche typeRecherche;

    /**
     * Objet de la recherche.
     */
    @Column(name = "objetRecherche")
    @Enumerated(EnumType.STRING)
    private ObjetRecherche objetRecherche;

    /**
     * Phase de la recherche.
     */
    @Column(name = "phaseRecherche")
    @Enumerated(EnumType.STRING)
    private PhaseRecherche phaseRecherche;

    /**
     * Nature de la recherche.
     */
    @Column(name = "natureRecherche")
    @Enumerated(EnumType.STRING)
    private NatureRecherche natureRecherche;

    /**
     * Qualité de l'insu.
     */
    @Column(name = "qualiteInsu")
    @Enumerated(EnumType.STRING)
    private QualiteInsu qualiteInsu;

    /**
     * Getter sur titreProtocole.
     * @return Retourne le titreProtocole.
     */
    public String getTitreProtocole() {
        return this.titreProtocole;
    }

    /**
     * Setter pour titreProtocole.
     * @param titreProtocole le titreProtocole à écrire.
     */
    public void setTitreProtocole(final String titreProtocole) {
        this.titreProtocole = titreProtocole;
    }

    /**
     * Getter sur numEnregistrement.
     * @return Retourne le numEnregistrement.
     */
    public String getNumEnregistrement() {
        return this.numEnregistrement;
    }

    /**
     * Setter pour numEnregistrement.
     * @param numEnregistrement le numEnregistrement à écrire.
     */
    public void setNumEnregistrement(final String numEnregistrement) {
        this.numEnregistrement = numEnregistrement;
    }

    /**
     * Getter sur typeRecherche.
     * @return Retourne le typeRecherche.
     */
    public TypeRecherche getTypeRecherche() {
        return this.typeRecherche;
    }

    /**
     * Setter pour typeRecherche.
     * @param typeRecherche le typeRecherche à écrire.
     */
    public void setTypeRecherche(final TypeRecherche typeRecherche) {
        this.typeRecherche = typeRecherche;
    }

    /**
     * Getter sur objetRecherche.
     * @return Retourne le objetRecherche.
     */
    public ObjetRecherche getObjetRecherche() {
        return this.objetRecherche;
    }

    /**
     * Setter pour objetRecherche.
     * @param objetRecherche le objetRecherche à écrire.
     */
    public void setObjetRecherche(final ObjetRecherche objetRecherche) {
        this.objetRecherche = objetRecherche;
    }

    /**
     * Getter sur phaseRecherche.
     * @return Retourne le phaseRecherche.
     */
    public PhaseRecherche getPhaseRecherche() {
        return this.phaseRecherche;
    }

    /**
     * Setter pour phaseRecherche.
     * @param phaseRecherche le phaseRecherche à écrire.
     */
    public void setPhaseRecherche(final PhaseRecherche phaseRecherche) {
        this.phaseRecherche = phaseRecherche;
    }

    /**
     * Getter sur natureRecherche.
     * @return Retourne le natureRecherche.
     */
    public NatureRecherche getNatureRecherche() {
        return this.natureRecherche;
    }

    /**
     * Setter pour natureRecherche.
     * @param natureRecherche le natureRecherche à écrire.
     */
    public void setNatureRecherche(final NatureRecherche natureRecherche) {
        this.natureRecherche = natureRecherche;
    }

    /**
     * Getter sur essai.
     * @return Retourne le essai.
     */
    public EssaiSigrec getEssai() {
        return this.essai;
    }

    /**
     * Setter pour essai.
     * @param essai le essai à écrire.
     */
    public void setEssai(final EssaiSigrec essai) {
        this.essai = essai;
    }

    /**
     * Getter sur qualiteInsu.
     * @return Retourne le qualiteInsu.
     */
    public QualiteInsu getQualiteInsu() {
        return this.qualiteInsu;
    }

    /**
     * Setter pour qualiteInsu.
     * @param qualiteInsu le qualiteInsu à écrire.
     */
    public void setQualiteInsu(final QualiteInsu qualiteInsu) {
        this.qualiteInsu = qualiteInsu;
    }

}
