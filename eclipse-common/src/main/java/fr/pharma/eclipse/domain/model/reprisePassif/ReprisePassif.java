/**
 * 
 */
package fr.pharma.eclipse.domain.model.reprisePassif;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import fr.pharma.eclipse.domain.model.common.BeanObject;

/**
 * Bean représentant une reprise passif.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "reprise_passif")
public class ReprisePassif extends BeanObject implements Serializable{
	
	
	private static final long serialVersionUID = 6364053476954618315L;

	/**
	 * Numéro d'ordonnancier composé du nom de la Pharmacie et du numéro de dispensiation
	 */
	@Column(name="numero_ordonnancier")
	@NotNull
	private String numeroOrdonnancier;
	
	/**
	 * Date de dispensiation
	 */
	@Column(name="date_dispensation")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dateDispensation;
	
	/**
	 * Initiales du patient
	 */
	@Column(name="initiales_patient")
	private String initialesPatient;
	
	/**
	 * champ constitué du numéro du protocole suivi de la référence du protocole et du nom du promoteur
	 */
	@Column(name="essai_promoteur")
	private String essaiPromoteur;
	
	/**
	 * champ constitué du numéro de lot suivi du numéro de traitement
	 */
	@Column(name="numlot_traitement")
	private String numLotTraitement;
	
	/**
	 * Champ contenant le nom du produit, son DCI, sa quantité et l'unité
	 */
	@Column
	private String produits;
	
	/**
	 * Nom de l'investigateur qui dispense le traitement
	 */
	@Column(name="dispense_par")
	private String dispensePar;

	/**
	 * @return the numeroOrdonnancier
	 */
	public String getNumeroOrdonnancier() {
		return numeroOrdonnancier;
	}

	/**
	 * @return the dateDispensation
	 */
	public Calendar getDateDispensation() {
		return dateDispensation;
	}

	/**
	 * @return the initialesPatient
	 */
	public String getInitialesPatient() {
		return initialesPatient;
	}

	/**
	 * @return the essaiPromoteur
	 */
	public String getEssaiPromoteur() {
		return essaiPromoteur;
	}

	/**
	 * @return the numLotTraitement
	 */
	public String getNumLotTraitement() {
		return numLotTraitement;
	}

	/**
	 * @return the produits
	 */
	public String getProduits() {
		return produits;
	}

	/**
	 * @return the dispensePar
	 */
	public String getDispensePar() {
		return dispensePar;
	}

	/**
	 * @param numeroOrdonnancier the numeroOrdonnancier to set
	 */
	public void setNumeroOrdonnancier(String numeroOrdonnancier) {
		this.numeroOrdonnancier = numeroOrdonnancier;
	}

	/**
	 * @param dateDispensation the dateDispensation to set
	 */
	public void setDateDispensation(Calendar dateDispensation) {
		this.dateDispensation = dateDispensation;
	}

	/**
	 * @param initialesPatient the initialesPatient to set
	 */
	public void setInitialesPatient(String initialesPatient) {
		this.initialesPatient = initialesPatient;
	}

	/**
	 * @param essaiPromoteur the essaiPromoteur to set
	 */
	public void setEssaiPromoteur(String essaiPromoteur) {
		this.essaiPromoteur = essaiPromoteur;
	}

	/**
	 * @param numLotTraitement the numLotTraitement to set
	 */
	public void setNumLotTraitement(String numLotTraitement) {
		this.numLotTraitement = numLotTraitement;
	}

	/**
	 * @param produits the produits to set
	 */
	public void setProduits(String produits) {
		this.produits = produits;
	}

	/**
	 * @param dispensePar the dispensePar to set
	 */
	public void setDispensePar(String dispensePar) {
		this.dispensePar = dispensePar;
	}

}
