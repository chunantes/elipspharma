package fr.pharma.eclipse.domain.model.localisation;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;
import org.hibernate.validator.constraints.NotEmpty;

import fr.pharma.eclipse.comparator.common.BeanWithNomComparator;
import fr.pharma.eclipse.comparator.suivi.SuiviComparator;
import fr.pharma.eclipse.domain.model.common.BeanObjectSuivi;
import fr.pharma.eclipse.domain.model.common.BeanWithNom;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.domain.model.suivi.localisation.EtablissementSuivi;

/**
 * Classe métier représentant un établissement.
 
 * @version $Revision$ $Date$
 */
@Entity(name = "etablissement")
public class Etablissement
    extends BeanObjectSuivi
    implements BeanWithNom
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -7417965637859395364L;

    /**
     * Nom.
     */
    @Column(name = "nom")
    @NotNull
    @NotEmpty
    private String nom;

    /**
     * Téléphone.
     */
    @Column(name = "telephone")
    private String telephone;

    /**
     * Fax.
     */
    @Column(name = "fax")
    private String fax;

    /**
     * Mail.
     */
    @Column(name = "mail")
    private String mail;

    /**
     * Adresse de la direction.
     */
    @Column(name = "adresseDirection", columnDefinition = "TEXT")
    private String adresseDirection;

    /**
     * Ville.
     */
    @Column(name = "ville")
    private String ville;

    /**
     * Code postal.
     */
    @Column(name = "codePostal")
    private String codePostal;

    /**
     * Pays.
     */
    @Column(name = "pays")
    private String pays;

    /**
     * Liste des pharmacies.
     */
    @OneToMany(mappedBy = "etablissement", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @Sort(type = SortType.COMPARATOR, comparator = BeanWithNomComparator.class)
    private SortedSet<Pharmacie> pharmacies = new TreeSet<Pharmacie>(new BeanWithNomComparator());

    /**
     * Liste des modifications.
     */
    @OneToMany(mappedBy = "etablissement", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.TRUE)
    @Sort(type = SortType.COMPARATOR, comparator = SuiviComparator.class)
    private final SortedSet<EtablissementSuivi> modifs =
        new TreeSet<EtablissementSuivi>(new SuiviComparator());

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
     * Getter sur telephone.
     * @return Retourne le telephone.
     */
    public String getTelephone()
    {
        return this.telephone;
    }

    /**
     * Setter pour telephone.
     * @param telephone le telephone à écrire.
     */
    public void setTelephone(final String telephone)
    {
        this.telephone = telephone;
    }

    /**
     * Getter sur fax.
     * @return Retourne le fax.
     */
    public String getFax()
    {
        return this.fax;
    }

    /**
     * Setter pour fax.
     * @param fax le fax à écrire.
     */
    public void setFax(final String fax)
    {
        this.fax = fax;
    }

    /**
     * Getter sur mail.
     * @return Retourne le mail.
     */
    public String getMail()
    {
        return this.mail;
    }

    /**
     * Setter pour mail.
     * @param mail le mail à écrire.
     */
    public void setMail(final String mail)
    {
        this.mail = mail;
    }

    /**
     * Getter sur adresseDirection.
     * @return Retourne le adresseDirection.
     */
    public String getAdresseDirection()
    {
        return this.adresseDirection;
    }

    /**
     * Setter pour adresseDirection.
     * @param adresseDirection le adresseDirection à écrire.
     */
    public void setAdresseDirection(final String adresseDirection)
    {
        this.adresseDirection = adresseDirection;
    }

    /**
     * Getter sur ville.
     * @return Retourne le ville.
     */
    public String getVille()
    {
        return this.ville;
    }

    /**
     * Setter pour ville.
     * @param ville le ville à écrire.
     */
    public void setVille(final String ville)
    {
        this.ville = ville;
    }

    /**
     * Getter sur pays.
     * @return Retourne le pays.
     */
    public String getPays()
    {
        return this.pays;
    }

    /**
     * Setter pour pays.
     * @param pays le pays à écrire.
     */
    public void setPays(final String pays)
    {
        this.pays = pays;
    }

    /**
     * Getter sur modifs.
     * @return Retourne le modifs.
     */
    @Override
    public SortedSet<EtablissementSuivi> getModifs()
    {
        return this.modifs;
    }

    /**
     * Getter pour codePostal.
     * @return Le codePostal
     */
    public String getCodePostal()
    {
        return this.codePostal;
    }

    /**
     * Setter pour codePostal.
     * @param codePostal Le codePostal à écrire.
     */
    public void setCodePostal(final String codePostal)
    {
        this.codePostal = codePostal;
    }

    /**
     * Getter pour pharmacies.
     * @return Le pharmacies
     */
    public SortedSet<Pharmacie> getPharmacies()
    {
        return this.pharmacies;
    }

    /**
     * Setter pour pharmacies.
     * @param pharmacies Le pharmacies à écrire.
     */
    public void setPharmacies(final SortedSet<Pharmacie> pharmacies)
    {
        this.pharmacies = pharmacies;
    }

}
