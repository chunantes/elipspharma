package fr.pharma.eclipse.component.design;

import javax.annotation.Resource;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import fr.pharma.eclipse.component.BeanManager;
import fr.pharma.eclipse.component.design.helper.PrescriptionTypeHelper;
import fr.pharma.eclipse.component.design.validator.SequenceValidator;
import fr.pharma.eclipse.component.produit.helper.ConditionnementHelper;
import fr.pharma.eclipse.domain.enums.produit.ModePrescription;
import fr.pharma.eclipse.domain.enums.produit.TypeProduit;
import fr.pharma.eclipse.domain.model.design.PrescriptionType;
import fr.pharma.eclipse.domain.model.design.Sequence;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.service.helper.design.TimeHelper;

/**
 * Manager pour le bean PrescriptionType.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PrescriptionTypeManager extends BeanManager<PrescriptionType> {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 314472037978508156L;

    /**
     * Helper de conditionnement.
     */
    @Resource(name = "conditionnementHelper")
    private ConditionnementHelper conditionnementHelper;

    /**
     * Helper TempsPrescription.
     */
    @Resource(name = "timeHelper")
    private TimeHelper timeHelper;

    /**
     * Validateur de sequence.
     */
    @Resource(name = "sequenceValidator")
    private SequenceValidator sequenceValidator;

    /**
     * Prescription type helper.
     */
    @Resource(name = "prescriptionTypeHelper")
    private PrescriptionTypeHelper helper;

    /**
     * Resumé conditionnement.
     */
    private String resumeConditionnement;

    /**
     * Resumé prescription.
     */
    private String resume;

    /**
     * Constructeur.
     * @param service Service attaché.
     */
    public PrescriptionTypeManager(final GenericService<PrescriptionType> service) {
        super(service);
    }

    /**
     * Méthode en charge de préparer l'édition d'une prescription.
     * @param event Evenement.
     */
    public void editPrescription(final ActionEvent event) {
        final PrescriptionType prescription = (PrescriptionType) event.getComponent().getAttributes().get("prescriptionToEdit");
        this.setBean(prescription);
        this.setResumeConditionnement(this.conditionnementHelper.buildResume(this.getBean().getConditionnement()));
        this.setResume(this.helper.buildResume(prescription));

    }

    /**
     * Méthode en charge de reinitialiser les données du mananger.
     */
    public void reinit() {
        this.setBean(null);
        this.setResumeConditionnement(null);
        this.setResume(null);
    }

    /**
     * Méthode en charge d'ajouter un prescription à la séquence.
     * @param event Evenement.
     */
    public void addPrescription(final ActionEvent event) {
        final Sequence seq = (Sequence) event.getComponent().getAttributes().get("sequence");
        if (this.sequenceValidator.validateSequence(this.getBean(), seq)) {
            this.getBean().setSequence(seq);
            if (!seq.getPrescriptions().contains(this.getBean())) {
                seq.getPrescriptions().add(this.getBean());
            }
            this.majDebutFin(seq);
        }
    }
    /**
     * Méthode en charge de mettre à jour les propriétés debut et fin de la
     * sequence en paramètre.
     * @param seq La séquence.
     */
    private void majDebutFin(final Sequence seq) {
        seq.setDebut(this.timeHelper.getDebut(seq.getPrescriptions()));

        // La fin n'est plus calculé à partir des prescription mais à partir de
        // la durée saisie
        // par l'utilisateur sur la séquence.
        // seq.setFin(this.timeHelper.getFin(seq.getPrescriptions()));

    }

    /**
     * Méthode en charge de supprimer la prescription.
     * @param event Evenement.
     */
    public void removePrescription(final ActionEvent event) {
        final PrescriptionType prescription = (PrescriptionType) event.getComponent().getAttributes().get("prescriptionToDelete");
        prescription.getSequence().getPrescriptions().remove(prescription);
    }

    /**
     * Retourne <true> si le dosage est visible ( si le produit sélectionné est
     * un médicament).
     * @return <true> si le dosage est visible ( si le produit sélectionné est
     * un médicament).
     */
    public boolean isDosageVisible() {
        return (null != this.getBean()) && (this.getBean().getProduit() != null) && this.getBean().getProduit().getType().equals(TypeProduit.MEDICAMENT);
    }

    /**
     * Méthode appelée par l'IHM lors de la sélection d'un conditionnement.
     * @param event Evenement JSF.
     */
    public void handleConditionnement(final AjaxBehaviorEvent event) {
        final HtmlSelectOneMenu menu = (HtmlSelectOneMenu) event.getSource();
        final Conditionnement conditionnement = (Conditionnement) menu.getLocalValue();
        this.getBean().setConditionnement(conditionnement);
        this.setResumeConditionnement(this.conditionnementHelper.buildResume(conditionnement));
    }

    /**
     * Retourne <true> si le mode de prescription du conditionnement sélectionné
     * est du type de celui en paramètre.
     * @param mode Mode de prescription.
     * @return <true> si le mode de prescription du conditionnement sélectionné
     * est du type de celui en paramètre.
     */
    public boolean isModePrescription(final ModePrescription mode) {
        return (this.getBean() != null) && (this.getBean().getConditionnement() != null) && (this.getBean().getConditionnement().getModePrescription() != null)
               && this.getBean().getConditionnement().getModePrescription().equals(mode);
    }

    public void buildResume() {
        this.setResume(this.helper.buildResume(this.getBean()));
    }

    /**
     * Setter pour timeHelper.
     * @param timeHelper le timeHelper à écrire.
     */
    public void setTimeHelper(final TimeHelper timeHelper) {
        this.timeHelper = timeHelper;
    }

    /**
     * Setter pour sequenceValidator.
     * @param sequenceValidator le sequenceValidator à écrire.
     */
    public void setSequenceValidator(final SequenceValidator sequenceValidator) {
        this.sequenceValidator = sequenceValidator;
    }

    /**
     * Setter pour conditionnementHelper.
     * @param conditionnementHelper le conditionnementHelper à écrire.
     */
    public void setConditionnementHelper(final ConditionnementHelper conditionnementHelper) {
        this.conditionnementHelper = conditionnementHelper;
    }

    /**
     * Getter sur resumeConditionnement.
     * @return Retourne le resumeConditionnement.
     */
    public String getResumeConditionnement() {
        return this.resumeConditionnement;
    }

    /**
     * Setter pour resumeConditionnement.
     * @param resumeConditionnement le resumeConditionnement à écrire.
     */
    public void setResumeConditionnement(final String resumeConditionnement) {
        this.resumeConditionnement = resumeConditionnement;
    }

    /**
     * Setter pour helper.
     * @param helper le helper à écrire.
     */
    public void setHelper(final PrescriptionTypeHelper helper) {
        this.helper = helper;
    }

    /**
     * Getter sur resume.
     * @return Retourne le resume.
     */
    public String getResume() {
        return this.resume;
    }

    /**
     * Setter pour resume.
     * @param resume le resume à écrire.
     */
    public void setResume(final String resume) {
        this.resume = resume;
    }

}
