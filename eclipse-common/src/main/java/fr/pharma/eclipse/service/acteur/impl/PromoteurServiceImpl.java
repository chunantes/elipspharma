package fr.pharma.eclipse.service.acteur.impl;

import javax.annotation.Resource;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.model.acteur.Promoteur;
import fr.pharma.eclipse.domain.model.suivi.acteur.PromoteurSuivi;
import fr.pharma.eclipse.factory.suivi.SuiviFactory;
import fr.pharma.eclipse.service.acteur.PromoteurService;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;

/**
 * Classe d'implémentation du service de gestion de promoteur.
 
 * @version $Revision$ $Date$
 */
public class PromoteurServiceImpl
    extends GenericServiceImpl<Promoteur>
    implements PromoteurService
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 2311658678802286831L;

    /**
     * Factory de suivi de promoteur.
     */
    @Resource(name = "promoteurSuiviFactory")
    private SuiviFactory<PromoteurSuivi> promoteurSuiviFactory;

    /**
     * Constructeur.
     * @param promoteurDao Dao de gestion des promoteurs.
     */
    public PromoteurServiceImpl(final GenericDao<Promoteur> promoteurDao)
    {
        super(promoteurDao);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Promoteur save(final Promoteur promoteur)
    {
        final Promoteur promoteurToSave = this.reattach(promoteur);
        final PromoteurSuivi promoteurSuivi = this.promoteurSuiviFactory.getInitializedObject();
        promoteurSuivi.setPromoteur(promoteurToSave);
        promoteurToSave.getModifs().add(promoteurSuivi);
        return super.save(promoteurToSave);
    }

    /**
     * Setter pour promoteurSuiviFactory.
     * @param promoteurSuiviFactory le promoteurSuiviFactory à écrire.
     */
    public void setPromoteurSuiviFactory(final SuiviFactory<PromoteurSuivi> promoteurSuiviFactory)
    {
        this.promoteurSuiviFactory = promoteurSuiviFactory;
    }

}
