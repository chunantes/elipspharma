package fr.pharma.eclipse.factory.essai;

import javax.annotation.Resource;

import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.surcout.DetailSurcout;
import fr.pharma.eclipse.domain.model.surcout.DonneesPrevision;
import fr.pharma.eclipse.factory.common.BeanObjectFactory;
import fr.pharma.eclipse.factory.common.BeanObjectWithParentFactory;

/**
 * Description de la classe.
 
 * @version $Revision$ $Date$
 */
public class DetailSurcoutFactory
    extends BeanObjectWithParentFactory<DetailSurcout, Essai>
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 386725468400436397L;

    /**
     * Factory de données de prévision.
     */
    @Resource(name = "donneesPrevisionFactory")
    private BeanObjectFactory<DonneesPrevision> donneesPrevisionFactory;

    /**
     * {@inheritDoc}
     */
    @Override
    public DetailSurcout getInitializedObject(final Essai essai)
    {
        final DetailSurcout detailSurcout = super.getInitializedObject(essai);
        final DonneesPrevision prevision = this.donneesPrevisionFactory.getInitializedObject();
        prevision.setDetailSurcout(detailSurcout);
        detailSurcout.setDonneesPrevision(prevision);
        return detailSurcout;
    }
}
