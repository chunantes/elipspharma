package fr.pharma.eclipse.jasper.engine.filler.impl.aidedispensation;

import fr.pharma.eclipse.domain.enums.TypeDispensation;
import fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean;
import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanFicheAideDispensationPart4;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.pharma.embedded.InfosDispensations;
import fr.pharma.eclipse.jasper.engine.filler.JasperReportBeanFiller;

/**
 * Filler en charge de valoriser les attributs de type de dispensation
 * {@link JRBeanFicheAideDispensationPart4}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class AideDispensationPart4TypeDispFiller implements JasperReportBeanFiller {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 2923425654973518398L;

    /**
     * {@inheritDoc}
     */
    @Override
    public void fill(final Essai essai,
                     final JasperReportBean bean) {
        final JRBeanFicheAideDispensationPart4 partie4 = (JRBeanFicheAideDispensationPart4) bean;
        final InfosDispensations infosDispensation = essai.getDetailDonneesPharma().getInfosDispensations();

        // Type de dispensation
        final TypeDispensation typeDispensation = infosDispensation.getTypeDispensation();
        boolean hasTracabilite = false;
        if (typeDispensation != null) {
            partie4.setTypeDispensation(typeDispensation.getLibelle());
            hasTracabilite = TypeDispensation.GLOBALE.equals(typeDispensation);
        }

        // Traçabilité
        partie4.setHasTracabilite(hasTracabilite);
        if (hasTracabilite) {
            partie4.setTracabiliteObligatoire(infosDispensation.getTracabilitePatient());
        }
    }

}
