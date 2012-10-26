package fr.pharma.eclipse.domain.model.produit;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Where;

import fr.pharma.eclipse.domain.enums.produit.NatureProduit;
import fr.pharma.eclipse.domain.model.produit.document.DocumentConditionnement;
import fr.pharma.eclipse.domain.model.produit.document.DocumentEtiquetage;
import fr.pharma.eclipse.domain.model.produit.document.DocumentFabrication;
import fr.pharma.eclipse.domain.model.produit.document.DocumentReconstitutionPSM;
import fr.pharma.eclipse.domain.model.produit.document.DocumentReconstitutionSimple;

/**
 * Classe métier représentant un médicament.
 
 * @version $Revision$ $Date$
 */
@Entity(name = "medicament")
public class Medicament
    extends Produit
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -1211483654129421700L;

    /**
     * Stupéfiant.
     */
    @Column(name = "stupefiant")
    private Boolean stupefiant;

    /**
     * MDS(Médicament dérivé du sang).
     */
    @Column(name = "mds")
    private Boolean mds;

    /**
     * DCI.
     */
    @Column(name = "dci")
    private String dci;

    /**
     * Reconstitution simple.
     */
    @Column(name = "reconstitutionSimple")
    private Boolean reconstitutionSimple;

    /**
     * Document relatif à la reconstitution simple.
     */
    @OneToOne(mappedBy = "produit", cascade = CascadeType.ALL)
    @Where(clause = "type='RECONSTITUTION_SIMPLE'")
    @LazyCollection(LazyCollectionOption.TRUE)
    private DocumentReconstitutionSimple documentReconstitutionSimple;

    /**
     * Reconstitution sous PSM.
     */
    @Column(name = "reconstitutionPSM")
    private Boolean reconstitutionPSM;

    /**
     * Document relatif à la reconstitution PSM.
     */
    @OneToOne(mappedBy = "produit", cascade = CascadeType.ALL)
    @Where(clause = "type='RECONSTITUTION_PSM'")
    @LazyCollection(LazyCollectionOption.TRUE)
    private DocumentReconstitutionPSM documentReconstitutionPSM;

    /**
     * Fabrication.
     */
    @Column(name = "fabrication")
    private Boolean fabrication;

    /**
     * Document relatif à la fabrication.
     */
    @OneToOne(mappedBy = "produit", cascade = CascadeType.ALL)
    @Where(clause = "type='FABRICATION'")
    @LazyCollection(LazyCollectionOption.TRUE)
    private DocumentFabrication documentFabrication;

    /**
     * Conditionnement.
     */
    @Column(name = "conditionnement")
    private Boolean conditionnement;

    /**
     * Document relatif au conditionnement.
     */
    @OneToOne(mappedBy = "produit", cascade = CascadeType.ALL)
    @Where(clause = "type='CONDITIONNEMENT'")
    @LazyCollection(LazyCollectionOption.TRUE)
    private DocumentConditionnement documentConditionnement;

    /**
     * Etiquetage.
     */
    @Column(name = "etiquetage")
    private Boolean etiquetage;

    /**
     * Document relatif à l'étiquetage.
     */
    @OneToOne(mappedBy = "produit", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Where(clause = "type='ETIQUETAGE'")
    private DocumentEtiquetage documentEtiquetage;

    /**
     * Nature du produit.
     */
    @Column(name = "nature")
    @Enumerated(EnumType.STRING)
    private NatureProduit nature;

    /**
     * {@inheritDoc}
     */
    @Override
    public Produit cloneMe()
    {
        final Medicament medicament = new Medicament();
        // Clonage de la super classe.
        super.cloneMe(medicament);

        // clonage spécifique au médicament.
        medicament.setDci(this.dci);
        medicament.setMds(this.mds);
        medicament.setStupefiant(this.stupefiant);
        medicament.setConditionnement(this.getConditionnement());
        medicament.setEtiquetage(this.getEtiquetage());
        medicament.setReconstitutionSimple(this.getReconstitutionSimple());
        medicament.setReconstitutionPSM(this.getReconstitutionPSM());
        medicament.setNature(this.getNature());
        medicament.setFabrication(this.getFabrication());

        return medicament;
    }

    /**
     * Getter sur stupefiant.
     * @return Retourne le stupefiant.
     */
    public Boolean getStupefiant()
    {
        if (this.stupefiant == null)
        {
            return false;
        }
        return this.stupefiant;
    }

    /**
     * Setter pour stupefiant.
     * @param stupefiant le stupefiant à écrire.
     */
    public void setStupefiant(final Boolean stupefiant)
    {
        this.stupefiant = stupefiant;
    }

    /**
     * Getter sur mds.
     * @return Retourne le mds.
     */
    public Boolean getMds()
    {
        if (this.mds == null)
        {
            return false;
        }
        return this.mds;
    }

    /**
     * Setter pour mds.
     * @param mds le mds à écrire.
     */
    public void setMds(final Boolean mds)
    {
        this.mds = mds;
    }

    /**
     * Getter sur dci.
     * @return Retourne le dci.
     */
    public String getDci()
    {
        return this.dci;
    }

    /**
     * Setter pour dci.
     * @param dci le dci à écrire.
     */
    public void setDci(final String dci)
    {
        this.dci = dci;
    }

    /**
     * Getter sur reconstitutionSimple.
     * @return Retourne le reconstitutionSimple.
     */
    public Boolean getReconstitutionSimple()
    {
        return this.reconstitutionSimple;
    }

    /**
     * Setter pour reconstitutionSimple.
     * @param reconstitutionSimple le reconstitutionSimple à écrire.
     */
    public void setReconstitutionSimple(final Boolean reconstitutionSimple)
    {
        this.reconstitutionSimple = reconstitutionSimple;
    }

    /**
     * Getter sur reconstitutionPSM.
     * @return Retourne le reconstitutionPSM.
     */
    public Boolean getReconstitutionPSM()
    {
        return this.reconstitutionPSM;
    }

    /**
     * Setter pour reconstitutionPSM.
     * @param reconstitutionPSM le reconstitutionPSM à écrire.
     */
    public void setReconstitutionPSM(final Boolean reconstitutionPSM)
    {
        this.reconstitutionPSM = reconstitutionPSM;
    }

    /**
     * Getter sur fabrication.
     * @return Retourne le fabrication.
     */
    public Boolean getFabrication()
    {
        return this.fabrication;
    }

    /**
     * Setter pour fabrication.
     * @param fabrication le fabrication à écrire.
     */
    public void setFabrication(final Boolean fabrication)
    {
        this.fabrication = fabrication;
    }

    /**
     * Getter sur conditionnement.
     * @return Retourne le conditionnement.
     */
    public Boolean getConditionnement()
    {
        return this.conditionnement;
    }

    /**
     * Setter pour conditionnement.
     * @param conditionnement le conditionnement à écrire.
     */
    public void setConditionnement(final Boolean conditionnement)
    {
        this.conditionnement = conditionnement;
    }

    /**
     * Getter sur etiquetage.
     * @return Retourne le etiquetage.
     */
    public Boolean getEtiquetage()
    {
        return this.etiquetage;
    }

    /**
     * Setter pour etiquetage.
     * @param etiquetage le etiquetage à écrire.
     */
    public void setEtiquetage(final Boolean etiquetage)
    {
        this.etiquetage = etiquetage;
    }

    /**
     * Getter sur nature.
     * @return Retourne le nature.
     */
    public NatureProduit getNature()
    {
        return this.nature;
    }

    /**
     * Setter pour nature.
     * @param nature le nature à écrire.
     */
    public void setNature(final NatureProduit nature)
    {
        this.nature = nature;
    }

    /**
     * Getter sur documentReconstitutionSimple.
     * @return Retourne le documentReconstitutionSimple.
     */
    public DocumentReconstitutionSimple getDocumentReconstitutionSimple()
    {
        return this.documentReconstitutionSimple;
    }

    /**
     * Setter pour documentReconstitutionSimple.
     * @param documentReconstitutionSimple le documentReconstitutionSimple à écrire.
     */
    public void setDocumentReconstitutionSimple(final DocumentReconstitutionSimple documentReconstitutionSimple)
    {
        this.documentReconstitutionSimple = documentReconstitutionSimple;
    }

    /**
     * Getter sur documentReconstitutionPSM.
     * @return Retourne le documentReconstitutionPSM.
     */
    public DocumentReconstitutionPSM getDocumentReconstitutionPSM()
    {
        return this.documentReconstitutionPSM;
    }

    /**
     * Setter pour documentReconstitutionPSM.
     * @param documentReconstitutionPSM le documentReconstitutionPSM à écrire.
     */
    public void setDocumentReconstitutionPSM(final DocumentReconstitutionPSM documentReconstitutionPSM)
    {
        this.documentReconstitutionPSM = documentReconstitutionPSM;
    }

    /**
     * Getter sur documentFabrication.
     * @return Retourne le documentFabrication.
     */
    public DocumentFabrication getDocumentFabrication()
    {
        return this.documentFabrication;
    }

    /**
     * Setter pour documentFabrication.
     * @param documentFabrication le documentFabrication à écrire.
     */
    public void setDocumentFabrication(final DocumentFabrication documentFabrication)
    {
        this.documentFabrication = documentFabrication;
    }

    /**
     * Getter sur documentConditionnement.
     * @return Retourne le documentConditionnement.
     */
    public DocumentConditionnement getDocumentConditionnement()
    {
        return this.documentConditionnement;
    }

    /**
     * Setter pour documentConditionnement.
     * @param documentConditionnement le documentConditionnement à écrire.
     */
    public void setDocumentConditionnement(final DocumentConditionnement documentConditionnement)
    {
        this.documentConditionnement = documentConditionnement;
    }

    /**
     * Getter sur documentEtiquetage.
     * @return Retourne le documentEtiquetage.
     */
    public DocumentEtiquetage getDocumentEtiquetage()
    {
        return this.documentEtiquetage;
    }

    /**
     * Setter pour documentEtiquetage.
     * @param documentEtiquetage le documentEtiquetage à écrire.
     */
    public void setDocumentEtiquetage(final DocumentEtiquetage documentEtiquetage)
    {
        this.documentEtiquetage = documentEtiquetage;
    }

}
