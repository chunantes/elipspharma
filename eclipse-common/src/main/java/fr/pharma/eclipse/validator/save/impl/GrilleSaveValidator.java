package fr.pharma.eclipse.validator.save.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import fr.pharma.eclipse.comparator.surcout.RegleBorneMinComparator;
import fr.pharma.eclipse.domain.enums.surcout.TypeCout;
import fr.pharma.eclipse.domain.model.surcout.Categorie;
import fr.pharma.eclipse.domain.model.surcout.GrilleModele;
import fr.pharma.eclipse.domain.model.surcout.Theme;
import fr.pharma.eclipse.domain.model.surcout.regle.Regle;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.predicate.GenericPredicate;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.utils.introspection.BeanTool;
import fr.pharma.eclipse.validator.save.SaveValidator;

/**
 * Validateur de la grille de surcouts saisie dans l'onglet de surcout.
 
 * @version $Revision$ $Date$
 */
public class GrilleSaveValidator
    implements SaveValidator<GrilleModele>
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -6012654919538008142L;

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate(final GrilleModele bean,
                         final GenericService<GrilleModele> beanService)
    {

        // si une grille est sasie on lance la validation.
        if (bean != null)
        {

            // pour chaque Theme on valide les regles.
            for (final Theme theme : bean.getThemes())
            {
                for (final Categorie categorie : theme.getCategories())
                {
                    this.valideRegles(categorie.getRegles());
                }
            }

        }
    }

    /**
     * Méthode en charge de valider la liste des règles en vérifiant qu'il n'y ait pas de vide et
     * aucune superposition dans les séquences (pour les variables : forfait / unite).
     * @param regles Les règles.
     */
    @SuppressWarnings("unchecked")
    private void valideRegles(final Collection<Regle> regles)
    {

        this.applyValidator(new ArrayList<Regle>(CollectionUtils
                                    .select(regles,
                                            new GenericPredicate("type",
                                                                 TypeCout.VARIABLE))),
                            "min",
                            "max");

    }

    /**
     * Méthode en charge d'appliquer les vérification sur les regles en paramètre pour les valeurs
     * min en max en paramètre.
     * @param regles Les règles.
     * @param min Le champs min.
     * @param max Le champs max.
     */
    private void applyValidator(final List<Regle> regles,
                                final String min,
                                final String max)
    {
        // tri des règles par ordre de min.
        Collections.sort(regles,
                         new RegleBorneMinComparator(min,
                                                     max));

        Regle precedent = null;

        for (final Regle regle : regles)
        {

            final Integer minValue = (Integer) BeanTool.getPropriete(regle,
                                                                     min);
            // si c'est la première règle alors elle doit commencer à 0, 1 ou être à null.
            if (precedent == null)
            {
                if (minValue != null
                    && minValue > 1)
                {
                    throw new ValidationException("regle.premier",
                                                  new String[]
                                                  {"error" },
                                                  regle);
                }
            }
            // si ce n'est pas la première elle doit suivre directement la précédent.
            else
            {
                if (!minValue.equals((Integer) BeanTool.getPropriete(precedent,
                                                                     max) + 1))
                {
                    throw new ValidationException("regle.sequence",
                                                  new String[]
                                                  {"error" },
                                                  regle);
                }
            }
            precedent = regle;
        }
    }

}
