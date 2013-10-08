package fr.pharma.eclipse.validator.save.impl;

import java.io.Serializable;
import java.util.SortedSet;

import fr.pharma.eclipse.domain.model.localisation.Etablissement;
import fr.pharma.eclipse.domain.model.localisation.Site;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.validator.save.SaveValidator;

/**
 * Classe en charge de valider la sauvegarde d'un bean Pharmacie.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PharmacieSaveValidator implements SaveValidator<Pharmacie>, Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -3742021045126400884L;

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate(final Pharmacie pharmacie,
                         final GenericService<Pharmacie> pharmacieService) {
        final SortedSet<Site> sites = pharmacie.getSites();

        // Vérification de la saisie d'au moins un site.
        if (sites.isEmpty()) {
            throw new ValidationException("pharmacie.sites", new String[]{"notEmpty" }, pharmacie);
        }

        // Vérification de la concordance entre Etablissement de la pharmacie et
        // Etablissements
        // des sites de la pharmacie
        final Etablissement etablissement = pharmacie.getEtablissement();

        for (final Site site : sites) {
            if (!site.getEtablissement().equals(etablissement)) {
                throw new ValidationException("pharmacie.sites", new String[]{"notValid" }, pharmacie);
            }
        }
    }

}
