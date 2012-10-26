package fr.pharma.eclipse.jasper.document.maker.impl;

import java.util.List;

import fr.pharma.eclipse.domain.model.common.DocumentEclipse;
import fr.pharma.eclipse.domain.model.common.Fichier;
import fr.pharma.eclipse.domain.model.stock.Destruction;
import fr.pharma.eclipse.domain.model.stock.MvtStock;
import fr.pharma.eclipse.domain.model.stock.document.DocumentDestruction;

/**
 * Description de la classe.
 
 * @version $Revision$ $Date$
 */
public class AccuseReceptionMaker
    extends GenericCertificatMaker
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -5783902786510058032L;

    /**
     * {@inheritDoc}
     */
    @Override
    protected DocumentEclipse makeForMvt(final List<? extends MvtStock> mvts,
                                         final Fichier fichier)
    {
        DocumentEclipse result = null;
        for (final MvtStock mvt : mvts)
        {
            final Destruction destruction = (Destruction) mvt;
            final DocumentDestruction doc =
                (DocumentDestruction) this.getDocumentFactory().getInitializedObject(fichier,
                                                                                     mvt);
            ((Destruction) mvt).setDocumentDestruction(doc);
            this.getService().save(destruction);
            this.getDocService().saveOnDisk(mvt,
                                            doc,
                                            fichier);
            result = doc;
        }
        return result;
    }

}
