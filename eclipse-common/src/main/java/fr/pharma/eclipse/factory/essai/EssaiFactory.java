package fr.pharma.eclipse.factory.essai;

import java.util.Calendar;

import fr.pharma.eclipse.domain.enums.EtatEssai;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.factory.common.BeanObjectComplexFactory;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Fabrique des objets Essai.
 
 * @version $Revision$ $Date$
 */
public class EssaiFactory
    extends BeanObjectComplexFactory<Essai>
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -6744160660949866186L;

    /**
     * Constructeur.
     * @param bean Classe à instancier.
     */
    public EssaiFactory(final Class<Essai> bean)
    {
        super(bean);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Essai getInitializedObject()
    {
        final Essai essai = super.getInitializedObject();

        // Initialisation de l'état.
        essai.setEtat(EtatEssai.EN_EVALUATION);

        // Initialisation du dci à n/a
        essai.setDci(EclipseConstants.NON_APPLICABLE);

        // Initialisation du Produit evalué à n/a
        essai.setLibelleProduitEvalue(EclipseConstants.NON_APPLICABLE);

        // Initialisation de l'année de création.
        essai.setAnneeCreation(Calendar.getInstance(EclipseConstants.LOCALE).get(Calendar.YEAR));

        // Initialisation de l'activation des alertes sur l'essai
        essai.setAlerteActive(Boolean.TRUE);

        return essai;
    }

}
