package fr.pharma.eclipse.domain.model.sigrec.essai;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Index;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import fr.pharma.eclipse.domain.enums.TypePromoteur;
import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.domain.model.sigrec.acteur.ARCInvestigateurSigrec;
import fr.pharma.eclipse.domain.model.sigrec.acteur.CROSigrec;
import fr.pharma.eclipse.domain.model.sigrec.acteur.CoInvestigateurSigrec;
import fr.pharma.eclipse.domain.model.sigrec.acteur.InvestigateurSigrec;
import fr.pharma.eclipse.domain.model.sigrec.acteur.PromoteurSigrec;
import fr.pharma.eclipse.domain.model.sigrec.essai.detail.AssuranceSigrec;
import fr.pharma.eclipse.domain.model.sigrec.essai.detail.DetailRechercheSigrec;
import fr.pharma.eclipse.domain.model.sigrec.essai.detail.PrevisionSigrec;

/**
 * Classe métier représentant un Essai clinique (appelé aussi étude) importé de SIGREC.
 
 * @version $Revision$ $Date$
 */
@Entity(name = "essai_sigrec")
public class EssaiSigrec
    extends BeanObject
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 8359894786830830474L;

    /**
     * Nom usuel.
     */
    @Column(name = "nom")
    private String nom;

    /**
     * Numéro d'identification AC.
     */
    @Column(name = "numIdentAC")
    private String numIdentificationAC;

    /**
     * Multicentrique.
     */
    @Column(name = "multicentrique")
    private Boolean multicentrique;

    /**
     * Nombre de centres.
     */
    @Column(name = "nbCentres")
    private Integer nbCentres;

    /**
     * Code promoteur.
     */
    @Column(name = "codePromoteur")
    private String codePromoteur;

    /**
     * Type du promoteur sur l'essai.
     */
    @Column(name = "typePromoteur")
    @Enumerated(EnumType.STRING)
    private TypePromoteur typePromoteur;

    /**
     * Promoteur.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_promoteur")
    @Index(name = "idx_promo_essai_sigrec")
    private PromoteurSigrec promoteur;

    /**
     * Détail de la recherche.
     */
    @OneToOne(mappedBy = "essai", fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    private DetailRechercheSigrec detailRecherche;

    /**
     * Prévisions de la recherche.
     */
    @OneToOne(mappedBy = "essai", fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    private PrevisionSigrec prevision;

    /**
     * InfosAssurance.
     */
    @OneToMany(mappedBy = "essai", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.TRUE)
    private final List<AssuranceSigrec> assurances = new ArrayList<AssuranceSigrec>();

    /**
     * CROs.
     */
    @OneToMany(mappedBy = "essai", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.TRUE)
    private final List<CROSigrec> cros = new ArrayList<CROSigrec>();

    /**
     * Investigateur principal.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_investigateurPrincipal")
    @Index(name = "idx_investigateur_principal_essai_sigrec")
    private InvestigateurSigrec investigateurPrincipal;

    /**
     * CoInvestigateurs.
     */
    @OneToMany(mappedBy = "essai", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.TRUE)
    private final List<CoInvestigateurSigrec> coInvestigateurs =
        new ArrayList<CoInvestigateurSigrec>();

    /**
     * ARC Investigateurs.
     */
    @OneToMany(mappedBy = "essai", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.TRUE)
    private final List<ARCInvestigateurSigrec> arcInvestigateurs =
        new ArrayList<ARCInvestigateurSigrec>();

    /**
     * Getter sur nom.
     * @return Retourne le nom.
     */
    public String getNom()
    {
        return this.nom;
    }

    /**
     * Setter pour nom.
     * @param nom le nom à écrire.
     */
    public void setNom(final String nom)
    {
        this.nom = nom;
    }

    /**
     * Getter sur promoteur.
     * @return Retourne le promoteur.
     */
    public PromoteurSigrec getPromoteur()
    {
        return this.promoteur;
    }

    /**
     * Setter pour promoteur.
     * @param promoteur le promoteur à écrire.
     */
    public void setPromoteur(final PromoteurSigrec promoteur)
    {
        this.promoteur = promoteur;
    }

    /**
     * Getter sur codePromoteur.
     * @return Retourne le codePromoteur.
     */
    public String getCodePromoteur()
    {
        return this.codePromoteur;
    }

    /**
     * Setter pour codePromoteur.
     * @param codePromoteur le codePromoteur à écrire.
     */
    public void setCodePromoteur(final String codePromoteur)
    {
        this.codePromoteur = codePromoteur;
    }

    /**
     * Getter sur numIdentificationAC.
     * @return Retourne le numIdentificationAC.
     */
    public String getNumIdentificationAC()
    {
        return this.numIdentificationAC;
    }

    /**
     * Setter pour numIdentificationAC.
     * @param numIdentificationAC le numIdentificationAC à écrire.
     */
    public void setNumIdentificationAC(final String numIdentificationAC)
    {
        this.numIdentificationAC = numIdentificationAC;
    }

    /**
     * Getter sur typePromoteur.
     * @return Retourne le typePromoteur.
     */
    public TypePromoteur getTypePromoteur()
    {
        return this.typePromoteur;
    }

    /**
     * Setter pour typePromoteur.
     * @param typePromoteur le typePromoteur à écrire.
     */
    public void setTypePromoteur(final TypePromoteur typePromoteur)
    {
        this.typePromoteur = typePromoteur;
    }

    /**
     * Getter sur detailRecherche.
     * @return Retourne le detailRecherche.
     */
    public DetailRechercheSigrec getDetailRecherche()
    {
        return this.detailRecherche;
    }

    /**
     * Setter pour detailRecherche.
     * @param detailRecherche le detailRecherche à écrire.
     */
    public void setDetailRecherche(final DetailRechercheSigrec detailRecherche)
    {
        this.detailRecherche = detailRecherche;
    }

    /**
     * Getter sur prevision.
     * @return Retourne le prevision.
     */
    public PrevisionSigrec getPrevision()
    {
        return this.prevision;
    }

    /**
     * Setter pour prevision.
     * @param prevision le prevision à écrire.
     */
    public void setPrevision(final PrevisionSigrec prevision)
    {
        this.prevision = prevision;
    }

    /**
     * Getter sur assurances.
     * @return Retourne le assurances.
     */
    public List<AssuranceSigrec> getAssurances()
    {
        return this.assurances;
    }

    /**
     * Getter sur cros.
     * @return Retourne le cros.
     */
    public List<CROSigrec> getCros()
    {
        return this.cros;
    }

    /**
     * Getter sur investigateurPrincipal.
     * @return Retourne le investigateurPrincipal.
     */
    public InvestigateurSigrec getInvestigateurPrincipal()
    {
        return this.investigateurPrincipal;
    }

    /**
     * Setter pour investigateurPrincipal.
     * @param investigateurPrincipal le investigateurPrincipal à écrire.
     */
    public void setInvestigateurPrincipal(final InvestigateurSigrec investigateurPrincipal)
    {
        this.investigateurPrincipal = investigateurPrincipal;
    }

    /**
     * Getter sur coInvestigateurs.
     * @return Retourne le coInvestigateurs.
     */
    public List<CoInvestigateurSigrec> getCoInvestigateurs()
    {
        return this.coInvestigateurs;
    }

    /**
     * Getter sur arcInvestigateurs.
     * @return Retourne le arcInvestigateurs.
     */
    public List<ARCInvestigateurSigrec> getArcInvestigateurs()
    {
        return this.arcInvestigateurs;
    }

    /**
     * Getter sur multicentrique.
     * @return Retourne le multicentrique.
     */
    public Boolean getMulticentrique()
    {
        return this.multicentrique;
    }

    /**
     * Setter pour multicentrique.
     * @param multicentrique le multicentrique à écrire.
     */
    public void setMulticentrique(final Boolean multicentrique)
    {
        this.multicentrique = multicentrique;
    }

    /**
     * Getter sur nbCentres.
     * @return Retourne le nbCentres.
     */
    public Integer getNbCentres()
    {
        return this.nbCentres;
    }

    /**
     * Setter pour nbCentres.
     * @param nbCentres le nbCentres à écrire.
     */
    public void setNbCentres(final Integer nbCentres)
    {
        this.nbCentres = nbCentres;
    }

}
