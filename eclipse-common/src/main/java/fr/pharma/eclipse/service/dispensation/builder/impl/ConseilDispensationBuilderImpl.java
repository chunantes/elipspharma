package fr.pharma.eclipse.service.dispensation.builder.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import fr.pharma.eclipse.domain.enums.produit.ModePrescription;
import fr.pharma.eclipse.domain.model.dispensation.ConseilDispensation;
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;
import fr.pharma.eclipse.service.dispensation.builder.ConseilDispensationBuilder;
import fr.pharma.eclipse.service.dispensation.checker.Checker;
import fr.pharma.eclipse.service.dispensation.formatter.ConseilFormatter;
import fr.pharma.eclipse.service.dispensation.processor.ConseilDispensationProcessor;

/**
 * Implémentation du constructeur de conseil à la dispensation.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ConseilDispensationBuilderImpl implements ConseilDispensationBuilder, Serializable {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -40319995680297906L;

    /**
     * Liste de checker à appliquer sur le produit prescrit lors de l'exécution
     * de la méthode support.
     */
    private List<Checker<ProduitPrescrit>> checkers;

    /**
     * Stratégie de processor en charge de calculer le conseil à la
     * dispensation.
     */
    private Map<ModePrescription, ConseilDispensationProcessor> processors;

    /**
     * Stratégie de formatter.
     */
    private Map<ModePrescription, ConseilFormatter> formatters;

    /**
     * {@inheritDoc}
     */
    @Override
    public ConseilDispensation build(final ProduitPrescrit produitPrescrit) {
        return this.processors.get(produitPrescrit.getConditionnement().getModePrescription()).process(produitPrescrit);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean support(final ProduitPrescrit produitPrescrit) {
        boolean result = true;
        for (final Checker<ProduitPrescrit> checker : this.checkers) {
            result &= checker.check(produitPrescrit);
        }
        return result && this.processors.containsKey(produitPrescrit.getConditionnement().getModePrescription());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String format(final ConseilDispensation conseil) {
        return this.formatters.get(conseil.getProduitPrescrit().getConditionnement().getModePrescription()).format(conseil);
    }

    /**
     * Setter pour checkers.
     * @param checkers le checkers à écrire.
     */
    public void setCheckers(final List<Checker<ProduitPrescrit>> checkers) {
        this.checkers = checkers;
    }

    /**
     * Setter pour processors.
     * @param processors le processors à écrire.
     */
    public void setProcessors(final Map<ModePrescription, ConseilDispensationProcessor> processors) {
        this.processors = processors;
    }

    /**
     * Setter pour formatters.
     * @param formatters le formatters à écrire.
     */
    public void setFormatters(final Map<ModePrescription, ConseilFormatter> formatters) {
        this.formatters = formatters;
    }
}
