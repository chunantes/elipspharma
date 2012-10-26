package fr.pharma.eclipse.jasper.document.maker.impl;

import java.util.List;

import fr.pharma.eclipse.domain.model.common.DocumentEclipse;
import fr.pharma.eclipse.domain.model.common.Fichier;
import fr.pharma.eclipse.domain.model.stock.MvtStock;
import fr.pharma.eclipse.domain.model.stock.RetourPromoteur;
import fr.pharma.eclipse.domain.model.stock.document.DocumentRetourPromoteur;

/**
 * Description de la classe.
 
 * @version $Revision$ $Date$
 */
public class CertificatRetourPromoteurMaker
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
            final RetourPromoteur retour = (RetourPromoteur) mvt;
            final DocumentRetourPromoteur doc =
                (DocumentRetourPromoteur) this.getDocumentFactory().getInitializedObject(fichier,
                                                                                         mvt);
            ((RetourPromoteur) mvt).setDocumentRetourPromoteur(doc);
            this.getService().save(retour);
            this.getDocService().saveOnDisk(mvt,
                                            doc,
                                            fichier);
            result = doc;
        }
        return result;
    }

}
