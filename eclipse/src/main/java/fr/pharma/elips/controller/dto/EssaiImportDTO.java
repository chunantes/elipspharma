/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pharma.elips.controller.dto;

import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnore;

import fr.pharma.elips.controller.exception.ImportException;

/**
 * DTO pour l'import d'un essai en mise à jour.
 */
public class EssaiImportDTO {

	private Long idElips;

	private String numInterne;

	private Long idPromoteur;

	private String nomPromoteur;

	private Long idInvestigateur;

	private String nomInvestigateur;

	private String prenomInvestigateur;

	private Long idService;

	private String nomService;

	private String codePromoteur;

	private String nomUsuel;

	private String typePromoteur;

	private String titreProtocole;

	private String typeRecherche;

	private String objetRecherche;

	private String phaseRecherche;

	private String natureRecherche;

	private String thematique;

	private Date datePrevDebEtude;

	private Date datePrevFinEtude;

	private Date datePrevFinInclusion;

	private Boolean autoriteComp;

	private String nomAutoriteComp;

	private Date dateAccordAC;

	private String eudract;

	private Boolean cpp;

	private String nomCpp;

	private Date dateAutorisationCpp;

	private Boolean conventionSignee;

	private Date dateSignatureConv;

	private String nomCompagnieAssurance;

	private String numContratAssurance;

	private String nomAvenantAssurance;

	private Integer nbPatientPrevuLocal;

	private Integer dureeTotPrevue;

	private String uniteDureeTotPrevue;

	private Integer nbCentrePrevu;

	private Integer nbPatientPrevuTotal;

	private String numCentre;

	private boolean modif;

	private List<String> mappingPropertiesKO;

	/**
	 * Constructeur.
	 */
	public EssaiImportDTO() {
		super();
		this.mappingPropertiesKO = new ArrayList<String>();
	}

	public Long getIdElips() {
		return this.idElips;
	}

	public void setIdElips(Long idElips) {
		this.idElips = idElips;
	}

	// Mapping des objets liés

	// -- Mapping du promoteur

	public Long getIdPromoteur() {
		return this.idPromoteur;
	}

	public void setIdPromoteur(Long idPromoteur) {
		this.idPromoteur = idPromoteur;
	}

	/**
	 * @deprecated Utilisé pour la désérialisation JSON
	 * @see EssaiImportDTO#getIdPromoteur()
	 */
	@Deprecated
	public String getNomPromoteur() {
		return this.nomPromoteur;
	}

	/**
	 * @deprecated Utilisé pour la désérialisation JSON
	 * @see EssaiImportDTO#setIdPromoteur(Long)
	 */
	@Deprecated
	public void setNomPromoteur(String nomPromoteur) {
		this.nomPromoteur = nomPromoteur;
	}

	// -- Mapping Investigateur

	public Long getIdInvestigateur() {
		return this.idInvestigateur;
	}

	public void setIdInvestigateur(Long idInvestigateur) {
		this.idInvestigateur = idInvestigateur;
	}

	/**
	 * @deprecated Utilisé pour la désérialisation JSON
	 * @see EssaiImportDTO#getIdInvestigateur()
	 */
	@Deprecated
	public String getNomInvestigateur() {
		return this.nomInvestigateur;
	}

	/**
	 * @deprecated Utilisé pour la désérialisation JSON
	 * @see EssaiImportDTO#setIdInvestigateur(Long)
	 */
	@Deprecated
	public void setNomInvestigateur(String nomInvestigateur) {
		this.nomInvestigateur = nomInvestigateur;
	}

	/**
	 * @deprecated Utilisé pour la désérialisation JSON
	 * @see EssaiImportDTO#getIdInvestigateur()
	 */
	@Deprecated
	public String getPrenomInvestigateur() {
		return this.prenomInvestigateur;
	}

	/**
	 * @deprecated Utilisé pour la désérialisation JSON
	 * @see EssaiImportDTO#setIdInvestigateur(Long)
	 */
	@Deprecated
	public void setPrenomInvestigateur(String prenom) {
		this.prenomInvestigateur = prenom;
	}

	// -- Mapping Service

	public Long getIdService() {
		return this.idService;
	}

	public void setIdService(Long idService) {
		this.idService = idService;
	}

	/**
	 * @deprecated Utilisé pour la désérialisation JSON
	 * @see EssaiImportDTO#getIdService()
	 */
	@Deprecated
	public String getNomService() {
		return this.nomService;
	}

	/**
	 * @deprecated Utilisé pour la désérialisation JSON
	 * @see EssaiImportDTO#setIdService(Long)
	 */
	@Deprecated
	public void setNomService(String nom) {
		this.nomService = nom;
	}

	// Mapping des champs

	public String getNumInterne() {
		return numInterne;
	}

	public void setNumInterne(String numInterne) {
		this.numInterne = numInterne;
	}

	public String getCodePromoteur() {
		return codePromoteur;
	}

	public void setCodePromoteur(String codePromoteur) {
		this.codePromoteur = codePromoteur;
	}

	public String getNomUsuel() {
		return nomUsuel;
	}

	public void setNomUsuel(String nomUsuel) {
		this.nomUsuel = nomUsuel;
	}

	public String getTypePromoteur() {
		return typePromoteur;
	}

	public void setTypePromoteur(String typePromoteur) {
		this.typePromoteur = typePromoteur;
	}

	public String getTitreProtocole() {
		return titreProtocole;
	}

	public void setTitreProtocole(String titreProtocole) {
		this.titreProtocole = titreProtocole;
	}

	public String getTypeRecherche() {
		return typeRecherche;
	}

	public void setTypeRecherche(String typeRecherche) {
		this.typeRecherche = typeRecherche;
	}

	public String getObjetRecherche() {
		return objetRecherche;
	}

	public void setObjetRecherche(String objetRecherche) {
		this.objetRecherche = objetRecherche;
	}

	public String getPhaseRecherche() {
		return phaseRecherche;
	}

	public void setPhaseRecherche(String phaseRecherche) {
		this.phaseRecherche = phaseRecherche;
	}

	public String getNatureRecherche() {
		return natureRecherche;
	}

	public void setNatureRecherche(String natureRecherche) {
		this.natureRecherche = natureRecherche;
	}

	public String getThematique() {
		return thematique;
	}

	public void setThematique(String thematique) {
		this.thematique = thematique;
	}

	public Date getDatePrevDebEtude() {
		return datePrevDebEtude;
	}

	public void setDatePrevDebEtude(Date datePrevDebEtude) {
		this.datePrevDebEtude = datePrevDebEtude;
	}

	public Date getDatePrevFinEtude() {
		return datePrevFinEtude;
	}

	public void setDatePrevFinEtude(Date datePrevFinEtude) {
		this.datePrevFinEtude = datePrevFinEtude;
	}

	public Date getDatePrevFinInclusion() {
		return datePrevFinInclusion;
	}

	public void setDatePrevFinInclusion(Date datePrevFinInclusion) {
		this.datePrevFinInclusion = datePrevFinInclusion;
	}

	public Boolean getAutoriteComp() {
		return autoriteComp;
	}

	public void setAutoriteComp(Boolean autoriteComp) {
		this.autoriteComp = autoriteComp;
	}

	public String getNomAutoriteComp() {
		return nomAutoriteComp;
	}

	public void setNomAutoriteComp(String nomAutoriteComp) {
		this.nomAutoriteComp = nomAutoriteComp;
	}

	public Date getDateAccordAC() {
		return dateAccordAC;
	}

	public void setDateAccordAC(Date dateAccordAC) {
		this.dateAccordAC = dateAccordAC;
	}

	public String getEudract() {
		return eudract;
	}

	public void setEudract(String eudract) {
		this.eudract = eudract;
	}

	public Boolean getCpp() {
		return cpp;
	}

	public void setCpp(Boolean cpp) {
		this.cpp = cpp;
	}

	public String getNomCpp() {
		return nomCpp;
	}

	public void setNomCpp(String nomCpp) {
		this.nomCpp = nomCpp;
	}

	public Date getDateAutorisationCpp() {
		return dateAutorisationCpp;
	}

	public void setDateAutorisationCpp(Date dateAutorisationCpp) {
		this.dateAutorisationCpp = dateAutorisationCpp;
	}

	public Boolean getConventionSignee() {
		return conventionSignee;
	}

	public void setConventionSignee(Boolean conventionSignee) {
		this.conventionSignee = conventionSignee;
	}

	public Date getDateSignatureConv() {
		return dateSignatureConv;
	}

	public void setDateSignatureConv(Date dateSignatureConv) {
		this.dateSignatureConv = dateSignatureConv;
	}

	public String getNomCompagnieAssurance() {
		return nomCompagnieAssurance;
	}

	public void setNomCompagnieAssurance(String nomCompagnieAssurance) {
		this.nomCompagnieAssurance = nomCompagnieAssurance;
	}

	public String getNumContratAssurance() {
		return numContratAssurance;
	}

	public void setNumContratAssurance(String numContratAssurance) {
		this.numContratAssurance = numContratAssurance;
	}

	public String getNomAvenantAssurance() {
		return nomAvenantAssurance;
	}

	public void setNomAvenantAssurance(String nomAvenantAssurance) {
		this.nomAvenantAssurance = nomAvenantAssurance;
	}

	public Integer getNbPatientPrevuLocal() {
		return nbPatientPrevuLocal;
	}

	public void setNbPatientPrevuLocal(Integer nbPatientPrevuLocal) {
		this.nbPatientPrevuLocal = nbPatientPrevuLocal;
	}

	public Integer getDureeTotPrevue() {
		return dureeTotPrevue;
	}

	public void setDureeTotPrevue(Integer dureeTotPrevue) {
		this.dureeTotPrevue = dureeTotPrevue;
	}

	public String getUniteDureeTotPrevue() {
		return uniteDureeTotPrevue;
	}

	public void setUniteDureeTotPrevue(String uniteDureeTotPrevue) {
		this.uniteDureeTotPrevue = uniteDureeTotPrevue;
	}

	public Integer getNbCentrePrevu() {
		return nbCentrePrevu;
	}

	public void setNbCentrePrevu(Integer nbCentrePrevu) {
		this.nbCentrePrevu = nbCentrePrevu;
	}

	public Integer getNbPatientPrevuTotal() {
		return nbPatientPrevuTotal;
	}

	public void setNbPatientPrevuTotal(Integer nbPatientPrevuTotal) {
		this.nbPatientPrevuTotal = nbPatientPrevuTotal;
	}

	public String getNumCentre() {
		return numCentre;
	}

	public void setNumCentre(String numCentre) {
		this.numCentre = numCentre;
	}

	@Override
	public String toString() {
		return new StringBuilder(this.getClass() + " [numinterne=")
				.append(getNumInterne()).append("]").toString();
	}

	@JsonIgnore
	public boolean isModif() {
		return this.modif;
	}

	public void setModif() {
		this.modif = true;
	}

	@JsonIgnore
	public List<String> getMappingPropertiesKO() {
		return this.mappingPropertiesKO;
	}

	/**
	 * Setter une valeur dans l'objet cible : effectuer les conversions
	 * nécessaires.
	 * 
	 * @param cible
	 *            Objet cible
	 * @param champCible
	 *            Champ cible
	 * @param valeur
	 *            Valeur
	 * @throws ImportException
	 *             Erreur lors de l'accès aux propriétés
	 */
	private static void setValue(Object cible, String champCible, Object valeur)
			throws ImportException {

		try {
			// Récupération des infos du champ à setter
			final PropertyDescriptor propCible = PropertyUtils
					.getPropertyDescriptor(cible, champCible);
			final Class<?> classeCible = propCible.getPropertyType();

			Object retour;
			if (Calendar.class.isAssignableFrom(classeCible)
					&& valeur instanceof Date) {
				// Date -> Calendar
				retour = date2Cal((Date) valeur);
			} else if (classeCible.isEnum() && valeur instanceof String) {
				try {
					// String -> Enum
					retour = Enum.valueOf((Class<? extends Enum>) classeCible,
							(String) valeur);
				} catch (IllegalArgumentException e) {
					// Valeur d'enum inconnu
					retour = null;
				}
			} else if (BigDecimal.class.isAssignableFrom(classeCible)
					&& valeur instanceof Number) {
				// String -> Enum
				retour = BigDecimal.valueOf(((Number) valeur).doubleValue());
			} else {
				retour = valeur;
			}

			// Définir la valeur
			PropertyUtils.setProperty(cible, champCible, retour);
		} catch (Exception e) {
			throw new ImportException(e);
		}
	}

	/**
	 * Mapping d'un champ du DTO dans un objet.
	 * 
	 * @param cible
	 *            Objet cible
	 * @param champ
	 *            Nom du champ (source et cible)
	 * @throws ImportException
	 *             Erreur d'accès aux propriétés
	 */
	public void mapping(Object cible, String champ) throws ImportException {
		this.mapping(cible, champ, champ);
	}

	/**
	 * Mapping d'un champ du DTO dans un objet.
	 * 
	 * @param cible
	 *            Objet cible
	 * @param champCible
	 *            Champ cible
	 * @param champSource
	 *            Champ source
	 * @throws ImportException
	 *             Erreur d'accès aux propriétés
	 */
	public void mapping(Object cible, String champCible, String champSource)
			throws ImportException {
		try {
			boolean localModif = false;
			// Récupération de la valeur cible
			final Object valeurCible = PropertyUtils.getProperty(cible,
					champCible);
			// => Mapping si nul
			if (isVide(valeurCible)) {
				// Récupération de la valeur source
				final Object valeurSource = PropertyUtils.getProperty(this,
						champSource);
				// => Mapping si non nul
				if (!isVide(valeurSource)) {
					// Transformation de l'objet source
					setValue(cible, champCible, valeurSource);
					localModif = true;

				}
			}

			this.modif = this.modif || localModif;
			if (!localModif) {
				this.mappingPropertiesKO.add(champSource);
			}
		} catch (Exception e) {
			throw new ImportException(e);
		}
	}

	/**
	 * Vérifier si un objet est vide (et donc doit être écrasé).
	 * 
	 * @param objet
	 *            Objet
	 * @return Vrai si l'objet est vide
	 */
	private static boolean isVide(Object objet) {
		boolean retour;
		if (objet == null) {
			// Ecraser les champs nuls
			retour = true;
		} else if (StringUtils.isBlank(String.valueOf(objet))) {
			// Ecraser les chaines vides
			retour = true;
		} else if (objet instanceof Number) {
			// Ecraser les 0
			retour = ((Number) objet).doubleValue() == 0;
			// ELIPSCHUNANTES-5 - Ne pas écraser les boolean false
			// } else if (objet instanceof Boolean) {
			// // Ecraser les false
			// retour = !((Boolean) objet).booleanValue();
		} else {
			retour = false;
		}

		return retour;
	}

	/**
	 * Transformer une {@link Date} en {@link Calendar}.
	 * 
	 * @param date
	 *            Date
	 * @return Calendar
	 */
	private static Calendar date2Cal(Date date) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

}
