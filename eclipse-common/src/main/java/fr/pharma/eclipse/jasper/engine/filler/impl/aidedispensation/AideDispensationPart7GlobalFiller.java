package fr.pharma.eclipse.jasper.engine.filler.impl.aidedispensation;

import fr.pharma.eclipse.domain.enums.Responsabilite;
import fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean;
import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanFicheAideDispensationPart7;
import fr.pharma.eclipse.domain.model.common.DocumentEclipse;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.pharma.embedded.InfosComplementaires;
import fr.pharma.eclipse.jasper.engine.filler.JasperReportBeanFiller;

/**
 * Filler en charge de valoriser les attributs de {@link JRBeanFicheAideDispensationPart7}.
 
 * @version $Revision$ $Date$
 */
public class AideDispensationPart7GlobalFiller
    implements JasperReportBeanFiller
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 4486786014978704166L;

    /**
     * {@inheritDoc}
     */
    @Override
    public void fill(final Essai essai,
                     final JasperReportBean bean)
    {
        final JRBeanFicheAideDispensationPart7 partie7 = (JRBeanFicheAideDispensationPart7) bean;
        final InfosComplementaires infosComplementaires =
            essai.getDetailDonneesPharma().getInfosComplementaires();

        // Levée de l'insu
        final Responsabilite resp = infosComplementaires.getResponsabiliteInsu();
        if (resp != null)
        {
            partie7.setRespLeveeInsu(resp.getLibelle());
        }

        // Mo Associé
        final DocumentEclipse doc = infosComplementaires.getDocumentResponsabiliteInsu();
        if (Responsabilite.PHARMACIE.equals(resp)
            && doc != null)
        {
            partie7.setMoAssocie(doc.getNomUtilisateur());
        }
    }

}
