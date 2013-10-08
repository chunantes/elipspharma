package fr.pharma.eclipse.factory.habilitation;

import java.util.Calendar;

import fr.pharma.eclipse.domain.model.habilitation.Habilitation;
import fr.pharma.eclipse.factory.common.BeanObjectFactory;
import fr.pharma.eclipse.service.user.UserService;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Fabrique d'objets Habilitation.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class HabilitationFactory extends BeanObjectFactory<Habilitation> {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 3661254743076108750L;

    /**
     * Service des utilisateurs.
     */
    private UserService userService;

    /**
     * Constructeur.
     * @param bean Classe à instancier.
     */
    public HabilitationFactory(final Class<Habilitation> bean) {
        super(bean);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Habilitation getInitializedObject() {
        final Habilitation habilitation = super.getInitializedObject();
        habilitation.setActive(true);
        habilitation.setDesactivable(true);
        habilitation.setDateCreation(Calendar.getInstance(EclipseConstants.LOCALE));
        habilitation.setAuteurCreation(this.userService.getPersonne().getLogin());
        return habilitation;
    }

    /**
     * Getter sur userService.
     * @return Retourne le userService.
     */
    UserService getUserService() {
        return this.userService;
    }

    /**
     * Setter pour userService.
     * @param userService le userService à écrire.
     */
    public void setUserService(final UserService userService) {
        this.userService = userService;
    }
}
