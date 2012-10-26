package fr.pharma.eclipse.domain.model.essai.detail.administratif;

import java.io.Serializable;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;

import fr.pharma.eclipse.comparator.suivi.SuiviComparator;
import fr.pharma.eclipse.domain.model.common.BeanObjectSuivi;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.administratif.embedded.InfosArchivage;
import fr.pharma.eclipse.domain.model.essai.detail.administratif.embedded.InfosAssurance;
import fr.pharma.eclipse.domain.model.essai.detail.administratif.embedded.InfosAutorisationDistribution;
import fr.pharma.eclipse.domain.model.essai.detail.administratif.embedded.InfosAutorisationImportation;
import fr.pharma.eclipse.domain.model.essai.detail.administratif.embedded.InfosAutoriteCompetente;
import fr.pharma.eclipse.domain.model.essai.detail.administratif.embedded.InfosBrochureProduits;
import fr.pharma.eclipse.domain.model.essai.detail.administratif.embedded.InfosComiteProtection;
import fr.pharma.eclipse.domain.model.essai.detail.administratif.embedded.InfosConvention;
import fr.pharma.eclipse.domain.model.essai.detail.administratif.embedded.InfosProtocole;
import fr.pharma.eclipse.domain.model.suivi.essai.detail.DetailAdministratifSuivi;

/**
 * Classe métier représentant les informations administratives/reglementaires d'un essai clinique.
 
 * @version $Revision$ $Date$
 */
@Entity(name = "essai_detail_administratif")
public class DetailAdministratif
    extends BeanObjectSuivi
    implements Serializable
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 3413313669943610973L;

    /**
     * Essai auquel est rattaché le détail.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_essai")
    private Essai essai;

    /**
     * Informations sur l'autorité compétente.
     */
    @Embedded
    private InfosAutoriteCompetente infosAC = new InfosAutoriteCompetente();

    /**
     * Informations sur le comité de protection des personnes.
     */
    @Embedded
    private InfosComiteProtection infosCPP = new InfosComiteProtection();

    /**
     * Informations sur la convention et ses avenants.
     */
    @Embedded
    private InfosConvention infosConvention = new InfosConvention();

    /**
     * Informations de l'assurance liée à l'essai.
     */
    @Embedded
    private InfosAssurance infosAssurance = new InfosAssurance();

    /**
     * Informations sur le protocole.
     */
    @Embedded
    private InfosProtocole infosProtocole = new InfosProtocole();

    /**
     * Informations sur la brochure investigateur des produits.
     */
    @Embedded
    private InfosBrochureProduits infosBrochure = new InfosBrochureProduits();

    /**
     * Informations sur l'autorisation d'importation.
     */
    @Embedded
    private InfosAutorisationImportation infosAutorisationImportation =
        new InfosAutorisationImportation();

    /**
     * Informations sur l'autorisation de distribution.
     */
    @Embedded
    private InfosAutorisationDistribution infosAutorisationDistribution =
        new InfosAutorisationDistribution();

    /**
     * Archivage.
     */
    @Embedded
    private InfosArchivage infosArchivage = new InfosArchivage();

    /**
     * Liste des modifications du détail.
     */
    @OneToMany(mappedBy = "detailAdministratif", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.TRUE)
    @Sort(type = SortType.COMPARATOR, comparator = SuiviComparator.class)
    private final SortedSet<DetailAdministratifSuivi> modifs =
        new TreeSet<DetailAdministratifSuivi>(new SuiviComparator());

    /**
     * Getter sur modifs.
     * @return Retourne le modifs.
     */
    @Override
    public SortedSet<DetailAdministratifSuivi> getModifs()
    {
        return this.modifs;
    }

    /**
     * Getter sur essai.
     * @return Retourne le essai.
     */
    public Essai getEssai()
    {
        return this.essai;
    }

    /**
     * Setter pour essai.
     * @param essai le essai à écrire.
     */
    public void setEssai(final Essai essai)
    {
        this.essai = essai;
    }

    /**
     * Getter sur infosAssurance.
     * @return Retourne le infosAssurance.
     */
    public InfosAssurance getInfosAssurance()
    {
        return this.infosAssurance;
    }

    /**
     * Setter pour infosAssurance.
     * @param infosAssurance le infosAssurance à écrire.
     */
    public void setInfosAssurance(final InfosAssurance infosAssurance)
    {
        this.infosAssurance = infosAssurance;
    }

    /**
     * Getter sur infosAC.
     * @return Retourne le infosAC.
     */
    public InfosAutoriteCompetente getInfosAC()
    {
        return this.infosAC;
    }

    /**
     * Setter pour infosAC.
     * @param infosAC le infosAC à écrire.
     */
    public void setInfosAC(final InfosAutoriteCompetente infosAC)
    {
        this.infosAC = infosAC;
    }

    /**
     * Getter sur infosCPP.
     * @return Retourne le infosCPP.
     */
    public InfosComiteProtection getInfosCPP()
    {
        return this.infosCPP;
    }

    /**
     * Setter pour infosCPP.
     * @param infosCPP le infosCPP à écrire.
     */
    public void setInfosCPP(final InfosComiteProtection infosCPP)
    {
        this.infosCPP = infosCPP;
    }

    /**
     * Getter sur infosConvention.
     * @return Retourne le infosConvention.
     */
    public InfosConvention getInfosConvention()
    {
        return this.infosConvention;
    }

    /**
     * Setter pour infosConvention.
     * @param infosConvention le infosConvention à écrire.
     */
    public void setInfosConvention(final InfosConvention infosConvention)
    {
        this.infosConvention = infosConvention;
    }

    /**
     * Getter sur infosArchivage.
     * @return Retourne le infosArchivage.
     */
    public InfosArchivage getInfosArchivage()
    {
        return this.infosArchivage;
    }

    /**
     * Setter pour infosArchivage.
     * @param infosArchivage le infosArchivage à écrire.
     */
    public void setInfosArchivage(final InfosArchivage infosArchivage)
    {
        this.infosArchivage = infosArchivage;
    }

    /**
     * Getter sur infosProtocole.
     * @return Retourne le infosProtocole.
     */
    public InfosProtocole getInfosProtocole()
    {
        return this.infosProtocole;
    }

    /**
     * Setter pour infosProtocole.
     * @param infosProtocole le infosProtocole à écrire.
     */
    public void setInfosProtocole(final InfosProtocole infosProtocole)
    {
        this.infosProtocole = infosProtocole;
    }

    /**
     * Getter sur infosBrochure.
     * @return Retourne le infosBrochure.
     */
    public InfosBrochureProduits getInfosBrochure()
    {
        return this.infosBrochure;
    }

    /**
     * Setter pour infosBrochure.
     * @param infosBrochure le infosBrochure à écrire.
     */
    public void setInfosBrochure(final InfosBrochureProduits infosBrochure)
    {
        this.infosBrochure = infosBrochure;
    }

    /**
     * Getter sur infosAutorisationImportation.
     * @return Retourne le infosAutorisationImportation.
     */
    public InfosAutorisationImportation getInfosAutorisationImportation()
    {
        return this.infosAutorisationImportation;
    }

    /**
     * Getter sur infosAutorisationDistribution.
     * @return Retourne le infosAutorisationDistribution.
     */
    public InfosAutorisationDistribution getInfosAutorisationDistribution()
    {
        return this.infosAutorisationDistribution;
    }

    /**
     * Setter pour infosAutorisationImportation.
     * @param infosAutorisationImportation le infosAutorisationImportation à écrire.
     */
    public void setInfosAutorisationImportation(final InfosAutorisationImportation infosAutorisationImportation)
    {
        this.infosAutorisationImportation = infosAutorisationImportation;
    }

    /**
     * Setter pour infosAutorisationDistribution.
     * @param infosAutorisationDistribution le infosAutorisationDistribution à écrire.
     */
    public void setInfosAutorisationDistribution(final InfosAutorisationDistribution infosAutorisationDistribution)
    {
        this.infosAutorisationDistribution = infosAutorisationDistribution;
    }

}
