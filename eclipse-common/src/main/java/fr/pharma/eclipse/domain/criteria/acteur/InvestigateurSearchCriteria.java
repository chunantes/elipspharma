package fr.pharma.eclipse.domain.criteria.acteur;

import java.util.List;

import fr.pharma.eclipse.domain.model.localisation.Service;

/**
 * Critère de recherche des investigateurs.
 
 * @version $Revision$ $Date$
 */
public class InvestigateurSearchCriteria
    extends PersonneSearchCriteria
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -3189216877185824133L;

    /**
     * Services.
     */
    private List<Service> services;

    /**
     * Titre.
     */
    private String titre;

    /**
     * Getter sur services.
     * @return Retourne le services.
     */
    public List<Service> getServices()
    {
        return this.services;
    }

    /**
     * Setter pour services.
     * @param services le services à écrire.
     */
    public void setServices(final List<Service> services)
    {
        this.services = services;
    }

    /**
     * Getter sur titre.
     * @return Retourne le titre.
     */
    public String getTitre()
    {
        return this.titre;
    }

    /**
     * Setter pour titre.
     * @param titre le titre à écrire.
     */
    public void setTitre(final String titre)
    {
        this.titre = titre;
    }
}
