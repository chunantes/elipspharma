package fr.pharma.eclipse.domain.criteria.acteur;

import java.util.Calendar;

import fr.pharma.eclipse.domain.criteria.common.AbstractSearchCriteria;
import fr.pharma.eclipse.domain.enums.TypePersonne;
import fr.pharma.eclipse.domain.model.essai.Essai;

/**
 * Critère de recherche sur Personne.
 
 * @version $Revision$ $Date$
 */
public class PersonneSearchCriteria
    extends AbstractSearchCriteria
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 2238200434777672526L;

    /**
     * Login.
     */
    private String login;

    /**
     * Nom.
     */
    private String nom;

    /**
     * Prénom.
     */
    private String prenom;

    /**
     * Type de personne.
     */
    private TypePersonne typePersonne;

    /**
     * Nom de la société.
     */
    private String nomSociete;

    /**
     * Essai (habilitation sur)
     */
    private Essai essai;

    /**
     * DateDebut habilitation.
     */
    private Calendar dateDebut;

    /**
     * DateFin habilitation.
     */
    private Calendar dateFin;

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
     * {@inheritDoc}
     */
    @Override
    public void clear()
    {
        this.setNom(null);
        this.setTypePersonne(null);
        this.setNomSociete(null);
        this.setPrenom(null);
        this.setLogin(null);
        this.setEssai(null);
        this.setDateDebut(null);
        this.setDateFin(null);
    }

    /**
     * Getter sur typePersonne.
     * @return Retourne le typePersonne.
     */
    public TypePersonne getTypePersonne()
    {
        return this.typePersonne;
    }

    /**
     * Getter sur nomSociete.
     * @return Retourne le nomSociete.
     */
    public String getNomSociete()
    {
        return this.nomSociete;
    }

    /**
     * Setter pour nomSociete.
     * @param nomSociete le nomSociete à écrire.
     */
    public void setNomSociete(final String nomSociete)
    {
        this.nomSociete = nomSociete;
    }

    /**
     * Setter pour typePersonne.
     * @param typePersonne le typePersonne à écrire.
     */
    public void setTypePersonne(final TypePersonne typePersonne)
    {
        this.typePersonne = typePersonne;
    }

    /**
     * Getter sur prenom.
     * @return Retourne le prenom.
     */
    public String getPrenom()
    {
        return this.prenom;
    }

    /**
     * Setter pour prenom.
     * @param prenom le prenom à écrire.
     */
    public void setPrenom(final String prenom)
    {
        this.prenom = prenom;
    }

    /**
     * Getter pour login.
     * @return Le login
     */
    public String getLogin()
    {
        return this.login;
    }

    /**
     * Setter pour login.
     * @param login Le login à écrire.
     */
    public void setLogin(final String login)
    {
        this.login = login;
    }

    /**
     * Getter pour essai.
     * @return Le essai
     */
    public Essai getEssai()
    {
        return this.essai;
    }

    /**
     * Setter pour essai.
     * @param essai Le essai à écrire.
     */
    public void setEssai(final Essai essai)
    {
        this.essai = essai;
    }

    /**
     * Getter pour dateDebut.
     * @return Le dateDebut
     */
    public Calendar getDateDebut()
    {
        return this.dateDebut;
    }

    /**
     * Setter pour dateDebut.
     * @param dateDebut Le dateDebut à écrire.
     */
    public void setDateDebut(final Calendar dateDebut)
    {
        this.dateDebut = dateDebut;
    }

    /**
     * Getter pour dateFin.
     * @return Le dateFin
     */
    public Calendar getDateFin()
    {
        return this.dateFin;
    }

    /**
     * Setter pour dateFin.
     * @param dateFin Le dateFin à écrire.
     */
    public void setDateFin(final Calendar dateFin)
    {
        this.dateFin = dateFin;
    }

}
