package fr.pharma.eclipse.service.acteur.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.enums.TypePersonne;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.habilitation.Habilitation;
import fr.pharma.eclipse.domain.model.suivi.acteur.PersonneSuivi;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.factory.suivi.SuiviFactory;
import fr.pharma.eclipse.service.acl.AclService;
import fr.pharma.eclipse.service.acteur.PersonneService;
import fr.pharma.eclipse.service.acteur.helper.PasswordEncoderHelper;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.service.habilitation.HabilitationService;
import fr.pharma.eclipse.validator.save.SaveValidator;
import fr.pharma.eclipse.validator.save.impl.PersonneSaveValidator;

/**
 * Classe d'implémentation du service de gestion de personne.
 * @author Netapsys
 * @version $Revision$ $Date$
 * @param <PERSONNE> Bean Objet Personne.
 */
public class PersonneServiceImpl<PERSONNE extends Personne> extends GenericServiceImpl<PERSONNE> implements
		PersonneService<PERSONNE> {

	/**
	 * Logger
	 */
	private final Logger log = LoggerFactory.getLogger(PersonneServiceImpl.class);

	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = -954027108871394549L;

	/**
	 * Factory de suivi de personne.
	 */
	@Resource(name = "personneSuiviFactory")
	private SuiviFactory<PersonneSuivi> personneSuiviFactory;

	/**
	 * Classe helper pour l'encodage de password.
	 */
	@Resource(name = "passwordEncoderHelper")
	private PasswordEncoderHelper passwordEncoderHelper;

	/**
	 * Validator de sauvegarde.
	 */
	private SaveValidator<PERSONNE> saveValidator;

	/**
	 * Validator commun de personne.
	 */
	@Resource(name = "personneSaveValidator")
	private PersonneSaveValidator personneSaveValidator;

	/**
	 * Service de gestion des acls.
	 */
	@Autowired
	private AclService aclService;
	
	@Autowired
    private HabilitationService habilitationService;

	/**
	 * Constructeur.
	 * @param personneDao Dao de gestion des personnes.
	 */
	public PersonneServiceImpl(final GenericDao<PERSONNE> personneDao) {
		super(personneDao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PERSONNE save(final PERSONNE personne) {

		// Validation commune de Personne
		this.personneSaveValidator.validate(personne);

		// Validation de la personne si un validator spécifique est défini
		if (this.saveValidator != null) {
			this.saveValidator.validate(personne, this);
		}

		// Encodage du mot de passe de l'utilisateur
		this.passwordEncoderHelper.encodePassword(personne);

		final PERSONNE personneSaved = this.saveCommon(personne);
		this.updateAcls(personneSaved);
		if(TypePersonne.PHARMACIEN.equals(personne.getType())) {
		    this.updateHabilitation(personneSaved);
		}

		return personneSaved;
	}

	/**
	 * Méthode en charge de mettre à jour les acls.
	 * @param personneSaved Personne sauvegardée.
	 */
	@Transactional
	public void updateAcls(final Personne personneSaved) {
		this.aclService.updateAclsPharmacies(personneSaved);
		this.aclService.updateAclsEssais(personneSaved);
	}
	
	/**
     * Méthode en charge de mettre à jour la table habilitation.
     * @param personneSaved Personne sauvegardée.
     */
    @Transactional
    public void updateHabilitation(final Personne personneSaved) {
        this.habilitationService.updateHabilitationPersonne(personneSaved);
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void remove(final PERSONNE personne) {
		try {
			super.remove(personne);
		} catch (final DataIntegrityViolationException exception) {
			throw new ValidationException("remove", new String[] { "impossible" }, personne);
		}
		this.aclService.removeAclsPharmacies(personne);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PERSONNE get(final Long id) {
		final PERSONNE personne = super.get(id);
		if (personne != null) {
			personne.setConfirmPassword(personne.getPassword());
		} else {
			this.log.debug("get : l'id personne n'est pas en base : " + id);
		}
		return personne;
	}

	/**
	 * Méthode contenant le traitement commun aux deux méthodes save.
	 * @param personne Personne à sauvegarde.
	 * @return La personne sauvegardée.
	 */
	private PERSONNE saveCommon(final PERSONNE personne) {
		final PERSONNE personneToSave = this.reattach(personne);
		final PersonneSuivi personneSuivi = this.personneSuiviFactory.getInitializedObject();
		personneSuivi.setPersonne(personneToSave);
		personneToSave.getModifs().add(personneSuivi);
		return super.save(personneToSave);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PERSONNE saveForSigrec(final PERSONNE personne) {
		return this.saveCommon(personne);
	}

	/**
	 * Setter pour personneSuiviFactory.
	 * @param personneSuiviFactory le personneSuiviFactory à écrire.
	 */
	public void setPersonneSuiviFactory(final SuiviFactory<PersonneSuivi> personneSuiviFactory) {
		this.personneSuiviFactory = personneSuiviFactory;
	}

	/**
	 * Setter pour saveValidator.
	 * @param saveValidator Le saveValidator à écrire.
	 */
	@Override
	public void setSaveValidator(final SaveValidator<PERSONNE> saveValidator) {
		this.saveValidator = saveValidator;
	}

	/**
	 * Setter pour passwordEncoderHelper.
	 * @param passwordEncoderHelper le passwordEncoderHelper à écrire.
	 */
	public void setPasswordEncoderHelper(final PasswordEncoderHelper passwordEncoderHelper) {
		this.passwordEncoderHelper = passwordEncoderHelper;
	}

	/**
	 * Setter pour personneSaveValidator.
	 * @param personneSaveValidator le personneSaveValidator à écrire.
	 */
	public void setPersonneSaveValidator(final PersonneSaveValidator personneSaveValidator) {
		this.personneSaveValidator = personneSaveValidator;
	}

	/**
	 * Setter pour aclService.
	 * @param aclService Le aclService à écrire.
	 */
	public void setAclService(final AclService aclService) {
		this.aclService = aclService;
	}

}
