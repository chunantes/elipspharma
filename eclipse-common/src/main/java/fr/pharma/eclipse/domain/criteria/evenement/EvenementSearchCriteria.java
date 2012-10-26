package fr.pharma.eclipse.domain.criteria.evenement;

import java.util.Calendar;
import java.util.List;

import fr.pharma.eclipse.domain.criteria.common.AbstractSearchCriteria;
import fr.pharma.eclipse.domain.enums.evenement.ResultatVisite;
import fr.pharma.eclipse.domain.enums.evenement.TypeEvenement;
import fr.pharma.eclipse.domain.enums.evenement.TypeVisite;
import fr.pharma.eclipse.domain.model.essai.Essai;

/**
 * Critère de recherche sur Evenement.
 
 * @version $Revision$ $Date$
 */
public class EvenementSearchCriteria
    extends AbstractSearchCriteria
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 6800377468370162836L;

    /**
     * Type d'événement.
     */
    private TypeEvenement typeEvenement;

    /**
     * Essai.
     */
    private Essai essai;

    /**
     * Liste d'essais.
     */
    private List<Essai> essais;

    /**
     * Type de visite.
     */
    private TypeVisite typeVisite;

    /**
     * Résultat de la visite.
     */
    private ResultatVisite resultatVisite;

    /**
     * Date de début de la recherche.
     */
    private Calendar dateDebut;

    /**
     * Date de fin de la recherche.
     */
    private Calendar dateFin;

    /**
     * Booléen indiquant la recherche de visite dont le resultat est non rempli.
     */
    private Boolean resultVisiteVide;

    /**
     * Booléen indiquant la date de reception est vide (Cession PUI)
     */
    private Boolean dateReceptionVide;

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear()
    {
        this.setEssai(null);
        this.setTypeEvenement(null);
        this.setTypeVisite(null);
        this.setResultatVisite(null);
        this.setDateDebut(null);
        this.setDateFin(null);
        this.setEssais(null);
        this.setResultVisiteVide(null);
        this.setDateReceptionVide(null);
    }

    /**
     * Getter pour typeEvenement.
     * @return Le typeEvenement
     */
    public TypeEvenement getTypeEvenement()
    {
        return this.typeEvenement;
    }

    /**
     * Setter pour typeEvenement.
     * @param typeEvenement Le typeEvenement à écrire.
     */
    public void setTypeEvenement(final TypeEvenement typeEvenement)
    {
        this.typeEvenement = typeEvenement;
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
     * Getter pour typeVisite.
     * @return Le typeVisite
     */
    public TypeVisite getTypeVisite()
    {
        return this.typeVisite;
    }

    /**
     * Setter pour typeVisite.
     * @param typeVisite Le typeVisite à écrire.
     */
    public void setTypeVisite(final TypeVisite typeVisite)
    {
        this.typeVisite = typeVisite;
    }

    /**
     * Getter pour resultatVisite.
     * @return Le resultatVisite
     */
    public ResultatVisite getResultatVisite()
    {
        return this.resultatVisite;
    }

    /**
     * Setter pour resultatVisite.
     * @param resultatVisite Le resultatVisite à écrire.
     */
    public void setResultatVisite(final ResultatVisite resultatVisite)
    {
        this.resultatVisite = resultatVisite;
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

    /**
     * Getter pour essais.
     * @return Le essais
     */
    public List<Essai> getEssais()
    {
        return this.essais;
    }

    /**
     * Setter pour essais.
     * @param essais Le essais à écrire.
     */
    public void setEssais(final List<Essai> essais)
    {
        this.essais = essais;
    }

    /**
     * Getter pour resultVisiteVide.
     * @return Le resultVisiteVide
     */
    public Boolean getResultVisiteVide()
    {
        return this.resultVisiteVide;
    }

    /**
     * Setter pour resultVisiteVide.
     * @param resultVisiteVide Le resultVisiteVide à écrire.
     */
    public void setResultVisiteVide(final Boolean resultVisiteVide)
    {
        this.resultVisiteVide = resultVisiteVide;
    }

    /**
     * Getter pour dateReceptionVide.
     * @return Le dateReceptionVide
     */
    public Boolean getDateReceptionVide()
    {
        return this.dateReceptionVide;
    }

    /**
     * Setter pour dateReceptionVide.
     * @param dateReceptionVide Le dateReceptionVide à écrire.
     */
    public void setDateReceptionVide(final Boolean dateReceptionVide)
    {
        this.dateReceptionVide = dateReceptionVide;
    }
}
