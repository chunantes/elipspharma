package fr.pharma.eclipse.service.actualite.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import fr.pharma.eclipse.domain.criteria.essai.EssaiSearchCriteria;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.service.actualite.ActualiteService;
import fr.pharma.eclipse.service.essai.EssaiService;

/**
 * Classe d'implémentation du service de gestion des actualités.
 
 * @version $Revision$ $Date$
 */
public class ActualiteServiceImpl
    implements ActualiteService, Serializable
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 6364599361983153932L;

    /**
     * Service de gestion des essais.
     */
    @Resource(name = "essaiService")
    private EssaiService essaiService;

    /**
     * Constante représentant le nombre d'essais retournés pour les actualités.
     */
    protected static final int NB_ESSAIS = 15;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Essai> getLastEssais()
    {
        List<Essai> result = new ArrayList<Essai>();

        final EssaiSearchCriteria criteria = new EssaiSearchCriteria();
        criteria.setActiveOrder("id");
        criteria.setAscending(false);

        // Récupération des essais de l'utilisateur
        result = this.essaiService.getAll(criteria);

        if (result.size() > ActualiteServiceImpl.NB_ESSAIS)
        {
            result = result.subList(0,
                                    ActualiteServiceImpl.NB_ESSAIS);
        }

        return result;

    }

    /**
     * Setter pour essaiService.
     * @param essaiService Le essaiService à écrire.
     */
    public void setEssaiService(final EssaiService essaiService)
    {
        this.essaiService = essaiService;
    }
}
