package fr.pharma.eclipse.service.patient.impl;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.criteria.patient.PatientSearchCriteria;
import fr.pharma.eclipse.domain.enums.patient.FormuleSurfaceCorporelle;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.patient.HistoriquePatient;
import fr.pharma.eclipse.domain.model.patient.Inclusion;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.domain.model.suivi.patient.PatientSuivi;
import fr.pharma.eclipse.factory.suivi.SuiviFactory;
import fr.pharma.eclipse.predicate.GenericPredicate;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.service.patient.PatientService;
import fr.pharma.eclipse.service.patient.dictionary.SurfaceCorporelleDictionary;
import fr.pharma.eclipse.utils.constants.EclipseConstants;
import fr.pharma.eclipse.validator.save.impl.DesinclusionPatientValidator;
import fr.pharma.eclipse.validator.save.impl.PatientSaveValidator;

/**
 * Classe d'implémentation du service de gestion de patient.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PatientServiceImpl extends GenericServiceImpl<Patient> implements PatientService {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 2723923953954098505L;

    /**
     * Validateur de desinclusion.
     */
    @Resource(name = "desinclusionPatientValidator")
    private DesinclusionPatientValidator validator;

    /**
     * Validateur avant sauvegarde du patient.
     */
    @Resource(name = "patientSaveValidator")
    private PatientSaveValidator<Patient> patientSaveValidator;

    /**
     * Dictionnaire de stratégie de calcul de surface corporelle.
     */
    @Resource(name = "surfaceCorporelleStrategy")
    private SurfaceCorporelleDictionary dictionary;

    /**
     * Factory de suivi de patient.
     */
    @Resource(name = "patientSuiviFactory")
    private SuiviFactory<PatientSuivi> patientSuiviFactory;

    /**
     * Constructeur.
     * @param patientDao Dao de gestion des patients.
     */
    public PatientServiceImpl(final GenericDao<Patient> patientDao) {
        super(patientDao);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Patient save(final Patient patient) {
        this.patientSaveValidator.validate(patient, this);
        final Patient patientToSave = this.reattach(patient);
        final PatientSuivi patientSuivi = this.patientSuiviFactory.getInitializedObject();
        patientSuivi.setPatient(patientToSave);
        patientToSave.getModifs().add(patientSuivi);
        return super.save(patientToSave);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FormuleSurfaceCorporelle updateSurfaceCorporelle(final HistoriquePatient historique) {
        if ((historique.getPatient().getDateNaissance() != null) && (historique.getTaille() != null) && (historique.getPoid() != null)) {
            final FormuleSurfaceCorporelle formule = this.getFormule(historique.getPatient());
            historique.setSurfaceCorporelle(this.dictionary.process(formule, historique.getTaille(), historique.getPoid()));
            return formule;
        }
        return null;
    }

    /**
     * Méthode en charge de retourner la formule corespondante à l'age du
     * patient en paramètre.
     * @param patient Le patient.
     * @return La formule correspondante.
     */
    private FormuleSurfaceCorporelle getFormule(final Patient patient) {
        final Calendar date = Calendar.getInstance(EclipseConstants.LOCALE);
        final int annees = date.get(Calendar.YEAR) - patient.getDateNaissance().get(Calendar.YEAR);
        if (annees >= 18) {
            return FormuleSurfaceCorporelle.DUBOIS;
        } else {
            return FormuleSurfaceCorporelle.MOSTELLER;

        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Inclusion getInclusionCourante(final Patient patient) {
        return (Inclusion) CollectionUtils.find(patient.getInclusions(), new GenericPredicate("actif", true));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void desinclure(final Patient patient) {
        this.validator.validate(patient, this);
        final Inclusion inclusion = this.getInclusionCourante(patient);
        inclusion.setActif(false);
        inclusion.setDateDesinclusion(Calendar.getInstance(EclipseConstants.LOCALE));
        this.save(patient);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Patient> getAllPatientsForEssai(final Essai essai) {
        final PatientSearchCriteria crit = new PatientSearchCriteria();
        crit.setEssai(essai);
        return this.getAll(crit);
    }

    /**
     * Setter pour patientSuiviFactory.
     * @param patientSuiviFactory le patientSuiviFactory à écrire.
     */
    public void setPatientSuiviFactory(final SuiviFactory<PatientSuivi> patientSuiviFactory) {
        this.patientSuiviFactory = patientSuiviFactory;
    }

    /**
     * Setter pour dictionary.
     * @param dictionary le dictionary à écrire.
     */
    public void setDictionary(final SurfaceCorporelleDictionary dictionary) {
        this.dictionary = dictionary;
    }

    /**
     * Setter pour validator.
     * @param validator le validator à écrire.
     */
    public void setValidator(final DesinclusionPatientValidator validator) {
        this.validator = validator;
    }

    /**
     * Setter pour patientSaveValidator.
     * @param patientSaveValidator Le patientSaveValidator à écrire.
     */
    public void setPatientSaveValidator(final PatientSaveValidator patientSaveValidator) {
        this.patientSaveValidator = patientSaveValidator;
    }

}
