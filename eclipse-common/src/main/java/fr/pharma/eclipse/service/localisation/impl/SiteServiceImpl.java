package fr.pharma.eclipse.service.localisation.impl;

import javax.annotation.Resource;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.model.localisation.Site;
import fr.pharma.eclipse.domain.model.suivi.localisation.SiteSuivi;
import fr.pharma.eclipse.factory.suivi.SuiviFactory;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.service.localisation.SiteService;

/**
 * Classe d'implémentation du service de gestion de site.
 
 * @version $Revision$ $Date$
 */
public class SiteServiceImpl
    extends GenericServiceImpl<Site>
    implements SiteService
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -2474709851788441312L;

    /**
     * Factory de suivi de site.
     */
    @Resource(name = "siteSuiviFactory")
    private SuiviFactory<SiteSuivi> siteSuiviFactory;

    /**
     * Constructeur.
     * @param siteDao Dao de gestion des sites.
     */
    public SiteServiceImpl(final GenericDao<Site> siteDao)
    {
        super(siteDao);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Site save(final Site site)
    {
        final Site siteToSave = this.reattach(site);
        final SiteSuivi siteSuivi = this.siteSuiviFactory.getInitializedObject();
        siteSuivi.setSite(siteToSave);
        siteToSave.getModifs().add(siteSuivi);
        return super.save(siteToSave);
    }

    /**
     * Setter pour siteSuiviFactory.
     * @param siteSuiviFactory le siteSuiviFactory à écrire.
     */
    public void setSiteSuiviFactory(final SuiviFactory<SiteSuivi> siteSuiviFactory)
    {
        this.siteSuiviFactory = siteSuiviFactory;
    }

}
