package fr.pharma.eclipse.utils;

import fr.pharma.eclipse.domain.model.localisation.Service;

/**
 * Classe utilitaire pour la création de jeux de données Service.
 
 * @version $Revision$ $Date$
 */
public final class ServiceUtils
{
    /**
     * Constructeur privé.
     */
    private ServiceUtils()
    {
        super();
    }

    /**
     * Crée un service pour les tests.
     * @param id Identifiant du test.
     * @return Service.
     */
    public static Service makeServiceTest(final long id)
    {
        return ServiceUtils.makeServiceTest(id,
                                            "service_"
                                                    + id);
    }

    /**
     * Crée un service pour les tests.
     * @param id Identifiant du test.
     * @param nom Nom.
     * @return Service.
     */
    public static Service makeServiceTest(final long id,
                                          final String nom)
    {
        final Service service = new Service();
        service.setId(id);
        service.setNom(nom);
        return service;
    }

}
