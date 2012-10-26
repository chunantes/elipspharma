package fr.pharma.eclipse.component.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.pharma.eclipse.comparator.common.BeanWithNomComparator;
import fr.pharma.eclipse.component.BeanManager;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.localisation.Service;
import fr.pharma.eclipse.service.common.GenericService;

/**
 * Manager de services.
 
 * @version $Revision$ $Date$
 */
public class ServiceManager
    extends BeanManager<Service>
    implements Serializable
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -634054951013915238L;

    /**
     * Personnes.
     */
    private List<Personne> personnes;

    /**
     * Constructeur.
     * @param service Le service.
     */
    public ServiceManager(final GenericService<Service> service)
    {
        super(service);
    }

    /**
     * Méthode en charge de retourner les personnes du services.
     * @return La liste des personnes du service.
     */
    public List<Personne> findPersonnes()
    {
        this.personnes = new ArrayList<Personne>();
        this.personnes.addAll(this.getBean().getArcInvestigateurs());
        this.personnes.addAll(this.getBean().getInvestigateurs());
        Collections.sort(this.personnes,
                         new BeanWithNomComparator());
        return this.personnes;

    }

    /**
     * Getter sur personnes.
     * @return Retourne le personnes.
     */
    public List<Personne> getPersonnes()
    {
        return this.personnes;
    }

    /**
     * Setter pour personnes.
     * @param personnes le personnes à écrire.
     */
    public void setPersonnes(final List<Personne> personnes)
    {
        this.personnes = personnes;
    }

}
