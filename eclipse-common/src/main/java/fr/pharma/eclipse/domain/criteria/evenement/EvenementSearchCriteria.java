package fr.pharma.eclipse.domain.criteria.evenement;

import java.util.Calendar;
import java.util.List;

import fr.pharma.eclipse.domain.criteria.common.AbstractSearchCriteria;
import fr.pharma.eclipse.domain.dto.EssaiDTO;
import fr.pharma.eclipse.domain.enums.evenement.ResultatVisite;
import fr.pharma.eclipse.domain.enums.evenement.TypeEvenement;
import fr.pharma.eclipse.domain.enums.evenement.TypeVisite;
import fr.pharma.eclipse.domain.model.essai.Essai;

/**
 * Critère de recherche sur Evenement.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EvenementSearchCriteria extends AbstractSearchCriteria {
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
     * Essai DTO.
     */
    private EssaiDTO essaiDTO;

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
     * Booléen indiquant l'essai est vide
     */
    private Boolean essaiVide;

    /**
     * Liste d'identifiants d'essais.
     */
    private List<Long> idsEssais;

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        this.setEssai(null);
        this.setEssaiDTO(null);
        this.setIdsEssais(null);
        this.setTypeEvenement(null);
        this.setTypeVisite(null);
        this.setResultatVisite(null);
        this.setDateDebut(null);
        this.setDateFin(null);
        this.setResultVisiteVide(null);
        this.setDateReceptionVide(null);
    }

    /**
     * Getter pour typeEvenement.
     * @return Le typeEvenement
     */
    public TypeEvenement getTypeEvenement() {
        return this.typeEvenement;
    }

    /**
     * Setter pour typeEvenement.
     * @param typeEvenement Le typeEvenement à écrire.
     */
    public void setTypeEvenement(final TypeEvenement typeEvenement) {
        this.typeEvenement = typeEvenement;
    }

    /**
     * Getter pour essai.
     * @return Le essai
     */
    public Essai getEssai() {
        return this.essai;
    }

    /**
     * Setter pour essai.
     * @param essai Le essai à écrire.
     */
    public void setEssai(final Essai essai) {
        this.essai = essai;
    }

    /**
     * Getter pour typeVisite.
     * @return Le typeVisite
     */
    public TypeVisite getTypeVisite() {
        return this.typeVisite;
    }

    /**
     * Setter pour typeVisite.
     * @param typeVisite Le typeVisite à écrire.
     */
    public void setTypeVisite(final TypeVisite typeVisite) {
        this.typeVisite = typeVisite;
    }

    /**
     * Getter pour resultatVisite.
     * @return Le resultatVisite
     */
    public ResultatVisite getResultatVisite() {
        return this.resultatVisite;
    }

    /**
     * Setter pour resultatVisite.
     * @param resultatVisite Le resultatVisite à écrire.
     */
    public void setResultatVisite(final ResultatVisite resultatVisite) {
        this.resultatVisite = resultatVisite;
    }

    /**
     * Getter pour dateDebut.
     * @return Le dateDebut
     */
    public Calendar getDateDebut() {
        return this.dateDebut;
    }

    /**
     * Setter pour dateDebut.
     * @param dateDebut Le dateDebut à écrire.
     */
    public void setDateDebut(final Calendar dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * Getter pour dateFin.
     * @return Le dateFin
     */
    public Calendar getDateFin() {
        return this.dateFin;
    }

    /**
     * Setter pour dateFin.
     * @param dateFin Le dateFin à écrire.
     */
    public void setDateFin(final Calendar dateFin) {
        this.dateFin = dateFin;
    }

    /**
     * Getter pour resultVisiteVide.
     * @return Le resultVisiteVide
     */
    public Boolean getResultVisiteVide() {
        return this.resultVisiteVide;
    }

    /**
     * Setter pour resultVisiteVide.
     * @param resultVisiteVide Le resultVisiteVide à écrire.
     */
    public void setResultVisiteVide(final Boolean resultVisiteVide) {
        this.resultVisiteVide = resultVisiteVide;
    }

    /**
     * Getter pour dateReceptionVide.
     * @return Le dateReceptionVide
     */
    public Boolean getDateReceptionVide() {
        return this.dateReceptionVide;
    }

    /**
     * Setter pour dateReceptionVide.
     * @param dateReceptionVide Le dateReceptionVide à écrire.
     */
    public void setDateReceptionVide(final Boolean dateReceptionVide) {
        this.dateReceptionVide = dateReceptionVide;
    }

    /**
     * Getter pour idsEssais.
     * @return Le idsEssais
     */
    public List<Long> getIdsEssais() {
        return this.idsEssais;
    }

    /**
     * Setter pour idsEssais.
     * @param idsEssais Le idsEssais à écrire.
     */
    public void setIdsEssais(final List<Long> idsEssais) {
        this.idsEssais = idsEssais;
    }

    /**
     * Getter pour essaiDTO.
     * @return Le essaiDTO
     */
    public EssaiDTO getEssaiDTO() {
        return this.essaiDTO;
    }

    /**
     * Setter pour essaiDTO.
     * @param essaiDTO Le essaiDTO à écrire.
     */
    public void setEssaiDTO(final EssaiDTO essaiDTO) {
        this.essaiDTO = essaiDTO;
    }

	/**
	 * @return the essaiVide
	 */
	public Boolean getEssaiVide() {
		return essaiVide;
	}

	/**
	 * @param essaiVide the essaiVide to set
	 */
	public void setEssaiVide(Boolean essaiVide) {
		this.essaiVide = essaiVide;
	}

}
