package fr.pharma.eclipse.service.acteur.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.suivi.acteur.PersonneSuivi;
import fr.pharma.eclipse.factory.suivi.SuiviFactory;
import fr.pharma.eclipse.service.acteur.PersonneService;
import fr.pharma.eclipse.service.acteur.helper.PasswordEncoderHelper;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.validator.save.SaveValidator;
import fr.pharma.eclipse.validator.save.impl.PersonneSaveValidator;

/**
 * Classe d'implémentation du service de gestion de personne.
 
 * @version $Revision$ $Date$
 * @param <PERSONNE> Bean Objet Personne.
 */
public class PersonneServiceImpl<PERSONNE extends Personne>
    extends GenericServiceImpl<PERSONNE>
    implements PersonneService<PERSONNE>
{

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
     * Constructeur.
     * @param personneDao Dao de gestion des personnes.
     */
    public PersonneServiceImpl(final GenericDao<PERSONNE> personneDao)
    {
        super(personneDao);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PERSONNE save(final PERSONNE personne)
    {
        // Validation commune de Personne
        this.personneSaveValidator.validate(personne);

        // Validation de la personne si un validator spécifique est défini
        if (this.saveValidator != null)
        {
            this.saveValidator.validate(personne,
                                        this);
        }

        // Encodage du mot de passe de l'utilisateur
        this.passwordEncoderHelper.encodePassword(personne);

        return this.saveCommon(personne);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PERSONNE get(final Long id)
    {
        final PERSONNE personne = super.get(id);
        if (personne != null)
        {
            personne.setConfirmPassword(personne.getPassword());
        }
        else
        {
            this.log.error("get : l'id personne n'est pas en base : "
                           + id);
        }
        return personne;
    }

    /**
     * Méthode contenant le traitement commun aux deux méthodes save.
     * @param personne Personne à sauvegarde.
     * @return La personne sauvegardée.
     */
    private PERSONNE saveCommon(final PERSONNE personne)
    {
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
    public PERSONNE saveForSigrec(final PERSONNE personne)
    {
        return this.saveCommon(personne);
    }

    /**
     * Setter pour personneSuiviFactory.
     * @param personneSuiviFactory le personneSuiviFactory à écrire.
     */
    public void setPersonneSuiviFactory(final SuiviFactory<PersonneSuivi> personneSuiviFactory)
    {
        this.personneSuiviFactory = personneSuiviFactory;
    }

    /**
     * Setter pour saveValidator.
     * @param saveValidator Le saveValidator à écrire.
     */
    @Override
    public void setSaveValidator(final SaveValidator<PERSONNE> saveValidator)
    {
        this.saveValidator = saveValidator;
    }

    /**
     * Setter pour passwordEncoderHelper.
     * @param passwordEncoderHelper le passwordEncoderHelper à écrire.
     */
    public void setPasswordEncoderHelper(final PasswordEncoderHelper passwordEncoderHelper)
    {
        this.passwordEncoderHelper = passwordEncoderHelper;
    }

    /**
     * Setter pour personneSaveValidator.
     * @param personneSaveValidator le personneSaveValidator à écrire.
     */
    public void setPersonneSaveValidator(final PersonneSaveValidator personneSaveValidator)
    {
        this.personneSaveValidator = personneSaveValidator;
    }

}
