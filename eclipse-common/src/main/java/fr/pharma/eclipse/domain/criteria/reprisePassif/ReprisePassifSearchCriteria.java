/**
 * 
 */
package fr.pharma.eclipse.domain.criteria.reprisePassif;

import java.util.Calendar;

import fr.pharma.eclipse.domain.criteria.common.AbstractSearchCriteria;

/**
 * @author Netapsys
 *
 */
public class ReprisePassifSearchCriteria extends AbstractSearchCriteria{

	private static final long serialVersionUID = -692914962184508462L;

	/**
	 * Date de debut pour date de dispensiation
	 */
	private Calendar dateDebut;
	
	/**
	 * Date de fin pour date de dispensiation
	 */
	private Calendar dateFin;
	
	/**
	 * Initiales du patient
	 */
	private String initialesPatient;
	
	/**
	 * numéro du protocole suivi de la référence du protocole et du nom du promoteur
	 */
	private String essaiPromoteur;
	
	@Override
	public void clear() {
		this.setDateDebut(null);
		this.setDateFin(null);
		this.setInitialesPatient(null);
		this.setEssaiPromoteur(null);
		
	}
		

	/**
	 * @return the dateDispensation
	 */
	public Calendar getDateDebut() {
		return dateDebut;
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
	 * @param dateDispensation the dateDispensation to set
	 */
	public void setDateDebut(Calendar dateDebut) {
		this.dateDebut = dateDebut;
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
	 * @return the dateFin
	 */
	public Calendar getDateFin() {
		return dateFin;
	}

	/**
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(Calendar dateFin) {
		this.dateFin = dateFin;
	}

	

	
}
