package fr.pharma.eclipse.jasper.engine.filler.impl.aidedispensation;

import fr.pharma.eclipse.domain.enums.Responsabilite;
import fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean;
import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanFicheAideDispensationPart3;
import fr.pharma.eclipse.domain.model.common.DocumentEclipse;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.pharma.embedded.InfosComplementaires;
import fr.pharma.eclipse.jasper.engine.filler.JasperReportBeanFiller;

/**
 * Filler en charge de construire les attributs de randomisation du bean
 * {@link JRBeanFicheAideDispensationPart3}.
 
 * @version $Revision$ $Date$
 */
public class AideDispensationPart3RandomisationFiller
    implements JasperReportBeanFiller
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -4412898735291023812L;

    /**
     * {@inheritDoc}
     */
    @Override
    public void fill(final Essai essai,
                     final JasperReportBean bean)
    {
        final JRBeanFicheAideDispensationPart3 beanSource =
            (JRBeanFicheAideDispensationPart3) bean;

        final InfosComplementaires infosComplementaires =
            essai.getDetailDonneesPharma().getInfosComplementaires();

        // Responsabilité randomisation.
        final Responsabilite resp = infosComplementaires.getResponsabiliteRandomisation();
        if (resp != null)
        {
            beanSource.setRespRandomisation(resp.getLibelle());
        }

        // MO Associé.
        final DocumentEclipse doc = infosComplementaires.getDocumentResponsabiliteRandomisation();
        if (doc != null)
        {
            beanSource.setMoAssocie(doc.getNomUtilisateur());
        }

        // HasMoAssocie.
        final Boolean hasMoAssocie = resp != null
                                     && doc != null
                                     && Responsabilite.PHARMACIE.equals(resp);
        beanSource.setHasMoAssocie(hasMoAssocie);
    }
}
