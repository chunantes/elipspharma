package fr.pharma.eclipse.factory.evenement;

import javax.annotation.Resource;

import fr.pharma.eclipse.domain.model.evenement.Evenement;
import fr.pharma.eclipse.factory.common.BeanObjectFactory;
import fr.pharma.eclipse.service.user.UserService;

/**
 * Factory de Bean Evenement.
 
 * @version $Revision$ $Date$
 */
public class EvenementFactory
    extends BeanObjectFactory<Evenement>
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -4646816131364363603L;

    /**
     * Service de gestion des utilisateurs.
     */
    @Resource(name = "userService")
    private UserService userService;

    /**
     * Constructeur.
     * @param bean Classe.
     */
    public EvenementFactory(final Class<Evenement> bean)
    {
        super(bean);
    }

    /**
     * Méthode en charge d'initialiser un Evenement avec un service.
     * @param essai Essai.
     * @param service Service.
     * @return Dotation.
     */
    @Override
    public Evenement getInitializedObject()
    {
        final Evenement evenement = super.getInitializedObject();
        evenement.setResponsable(this.userService.getPersonne());
        return evenement;
    }

    /**
     * Setter pour userService.
     * @param userService Le userService à écrire.
     */
    public void setUserService(final UserService userService)
    {
        this.userService = userService;
    }

}
