package fr.pharma.eclipse.component.stock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import com.lowagie.text.BadElementException;
import com.lowagie.text.DocumentException;

import fr.pharma.eclipse.component.BeanListManager;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.enums.EtatRetour;
import fr.pharma.eclipse.domain.model.stock.RetourPatient;

/**
 * Manager de retours patient.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class RetourPatientListManager<BEAN extends RetourPatient> extends BeanListManager<BEAN> {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 4969920960994743246L;

    /**
     * Etat.
     */
    private EtatRetour etat;

    /**
     * Commentaire.
     */
    private String commentaireEtat;

    /**
     * Date du changement de l'état.
     */
    private Calendar dateEtat;

    /**
     * Constructeur.
     * @param searchCriteria Critère de recherche.
     */
    public RetourPatientListManager(final SearchCriteria searchCriteria) {
        super(searchCriteria);
    }

    /**
     * Initialisation.
     */
    public void init() {
        this.setCommentaireEtat(null);
        this.setEtat(null);
        this.setDateEtat(null);
        this.setBeans(new ArrayList<BEAN>());
    }
    /**
     * Méthode en charge d'appliquyer le changement d'état à tous les beans
     * sélectionnés.
     */
    public void applyEtat() {
        for (final RetourPatient r : this.getBeansSelected()) {
            r.setEtat(this.getEtat());
            r.setCommentaireEtat(this.getCommentaireEtat());
            r.setDateEtat(this.getDateEtat());
        }
    }
    /**
     * Applique un pre processor avant le génération des documents dataExporter.
     * @param document Document.
     * @throws IOException en cas d'erreur.
     * @throws BadElementException en cas d'erreur .
     * @throws DocumentException en cas d'erreur.
     */
    public void preProcessPDF(final Object document) throws IOException, BadElementException, DocumentException {
        this.getDocProcessor().preProcessPDF(document, "Formulaire de retour : Récapitulatif par patient");
    }

    public void preProcessXLS(final Object document) throws IOException, BadElementException, DocumentException {
        this.getDocProcessor().preProcessXLS(document, "Formulaire de retour : Récapitulatif par patient");
    }

    /**
     * Getter pour etat.
     * @return Le etat
     */
    public EtatRetour getEtat() {
        return this.etat;
    }

    /**
     * Setter pour etat.
     * @param etat Le etat à écrire.
     */
    public void setEtat(final EtatRetour etat) {
        this.etat = etat;
    }

    /**
     * Getter pour commentaireEtat.
     * @return Le commentaireEtat
     */
    public String getCommentaireEtat() {
        return this.commentaireEtat;
    }

    /**
     * Setter pour commentaireEtat.
     * @param commentaireEtat Le commentaireEtat à écrire.
     */
    public void setCommentaireEtat(final String commentaireEtat) {
        this.commentaireEtat = commentaireEtat;
    }

    /**
     * Getter pour dateEtat.
     * @return Le dateEtat
     */
    public Calendar getDateEtat() {
        return this.dateEtat;
    }

    /**
     * Setter pour dateEtat.
     * @param dateEtat Le dateEtat à écrire.
     */
    public void setDateEtat(final Calendar dateEtat) {
        this.dateEtat = dateEtat;
    }

}
