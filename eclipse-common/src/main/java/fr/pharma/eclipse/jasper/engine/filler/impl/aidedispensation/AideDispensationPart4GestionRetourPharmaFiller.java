package fr.pharma.eclipse.jasper.engine.filler.impl.aidedispensation;

import fr.pharma.eclipse.domain.enums.Responsabilite;
import fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean;
import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanFicheAideDispensationPart4;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.pharma.embedded.InfosComplementaires;
import fr.pharma.eclipse.jasper.engine.filler.JasperReportBeanFiller;

/**
 * Filler en charge de valoriser l'attribut de
 * {@link JRBeanFicheAideDispensationPart4}<br>
 * qui indique si la gestion des retours est faite par la pharmacie.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class AideDispensationPart4GestionRetourPharmaFiller implements JasperReportBeanFiller {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 5805268593949228613L;

    /**
     * {@inheritDoc}
     */
    @Override
    public void fill(final Essai essai,
                     final JasperReportBean bean) {
        final JRBeanFicheAideDispensationPart4 partie4 = (JRBeanFicheAideDispensationPart4) bean;
        final InfosComplementaires infosComplementaires = essai.getDetailDonneesPharma().getInfosComplementaires();
        partie4.setHasGestionRetoursPharma(Responsabilite.PHARMACIE.equals(infosComplementaires.getGestionRetour()));
    }

}
