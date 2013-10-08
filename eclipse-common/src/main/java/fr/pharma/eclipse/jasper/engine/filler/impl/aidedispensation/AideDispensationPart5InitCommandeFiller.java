package fr.pharma.eclipse.jasper.engine.filler.impl.aidedispensation;

import fr.pharma.eclipse.domain.enums.Responsabilite;
import fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean;
import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanFicheAideDispensationPart5;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.pharma.embedded.InfosComplementaires;
import fr.pharma.eclipse.jasper.engine.filler.JasperReportBeanFiller;

/**
 * Filler en charge de valoriser l'attribut respCommande de
 * {@link JRBeanFicheAideDispensationPart5}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class AideDispensationPart5InitCommandeFiller implements JasperReportBeanFiller {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 7802258466044731454L;

    /**
     * {@inheritDoc}
     */
    @Override
    public void fill(final Essai essai,
                     final JasperReportBean bean) {
        final JRBeanFicheAideDispensationPart5 partie5 = (JRBeanFicheAideDispensationPart5) bean;
        final InfosComplementaires infosComplementaires = essai.getDetailDonneesPharma().getInfosComplementaires();
        final Responsabilite resp = infosComplementaires.getResponsabiliteCommande();
        if (resp != null) {
            partie5.setRespCommande(resp.getLibelle());
        }
    }

}
