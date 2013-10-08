package fr.pharma.eclipse.jasper.engine.filler.impl.aidedispensation;

import org.apache.commons.collections.Predicate;

import fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean;
import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanFicheAideDispensationPart4;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.jasper.engine.filler.JasperReportBeanFiller;

/**
 * Filler en charge de valoriser les attributs de numérotation des
 * conditionnements {@link JRBeanFicheAideDispensationPart4}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class AideDispensationPart4NumerodationCondFiller implements JasperReportBeanFiller {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 2923425654973518398L;

    /**
     * Prédicat à appliquer sur l'essai.
     */
    private final Predicate essaiPredicate;

    /**
     * Constructeur.
     * @param essaiPredicate Prédicat à appliquer sur l'essai.
     */
    public AideDispensationPart4NumerodationCondFiller(final Predicate essaiPredicate) {
        this.essaiPredicate = essaiPredicate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fill(final Essai essai,
                     final JasperReportBean bean) {
        final JRBeanFicheAideDispensationPart4 partie4 = (JRBeanFicheAideDispensationPart4) bean;

        // Evaluation de l'essai.
        final boolean res = this.essaiPredicate.evaluate(essai);

        // Valorisation de la propriété du bean Jasper.
        partie4.setHasNumeroTraitement(res);
    }

    /**
     * Getter sur essaiPredicate.
     * @return Retourne le essaiPredicate.
     */
    Predicate getEssaiPredicate() {
        return this.essaiPredicate;
    }

}
