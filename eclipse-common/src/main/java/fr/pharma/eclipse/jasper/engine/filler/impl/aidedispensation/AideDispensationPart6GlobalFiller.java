package fr.pharma.eclipse.jasper.engine.filler.impl.aidedispensation;

import fr.pharma.eclipse.domain.enums.Responsabilite;
import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean;
import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanFicheAideDispensationPart6;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.pharma.embedded.InfosComplementaires;
import fr.pharma.eclipse.jasper.engine.filler.JasperReportBeanFiller;

/**
 * Filler en charge de valoriser les attributs de
 * {@link JRBeanFicheAideDispensationPart6}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class AideDispensationPart6GlobalFiller implements JasperReportBeanFiller {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 4486786014978704166L;

    /**
     * {@inheritDoc}
     */
    @Override
    public void fill(final Essai essai,
                     final JasperReportBean bean) {
        final JRBeanFicheAideDispensationPart6 partie6 = (JRBeanFicheAideDispensationPart6) bean;
        final InfosComplementaires infosComplementaires = essai.getDetailDonneesPharma().getInfosComplementaires();

        // Gestion des retours
        final Responsabilite resp = infosComplementaires.getGestionRetour();
        if (resp != null) {
            partie6.setRespRetour(resp.getLibelle());
        }

        // Type de retour
        final TypeMvtStock typeMvtStock = infosComplementaires.getTypeRetour();
        if (Responsabilite.PHARMACIE.equals(resp) && (typeMvtStock != null)) {
            partie6.setTypeRetour(typeMvtStock.getLibelle());
        }
    }

}
