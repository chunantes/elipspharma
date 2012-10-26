package fr.pharma.eclipse.component.stock.helper;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import fr.pharma.eclipse.domain.model.stock.NumTraitement;

/**
 * Classe en charge de gérer la saisie automatique de numéros de traitement.
 
 * @version $Revision$ $Date$
 */
public class AutoSaisieNumTraitement
    implements Serializable
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -686004996211809535L;

    /**
     * Méthode en charge de gérer le traitement de la saisie automatique des numéros de
     * traitements.
     * @param numsTraitements Liste des numéros de traitement.
     */
    public void handle(final List<NumTraitement> numsTraitements)
    {
        if (this.launchSaisieAuto(numsTraitements))
        {
            final String numTraitementModele = numsTraitements.get(0).getNumTraitement();
            final int length = numTraitementModele.length();
            String suffixe = StringUtils.EMPTY;
            for (int i = length - 1; i >= 0; i--)
            {
                final char car = numTraitementModele.charAt(i);
                if (this.isDigit(numTraitementModele.charAt(i)))
                {
                    suffixe = String.valueOf(car).concat(suffixe);
                }
                else
                {
                    break;
                }
            }

            if (StringUtils.isNotEmpty(suffixe))
            {
                final String prefixe =
                    numTraitementModele.substring(0,
                                                  numTraitementModele.lastIndexOf(suffixe));

                Integer numberSuffixe = Integer.valueOf(suffixe);

                for (int i = 1; i < numsTraitements.size(); i++)
                {
                    final StringBuilder sb = new StringBuilder();
                    sb.append(prefixe);
                    sb.append(this.complete(suffixe,
                                            String.valueOf(++numberSuffixe)));
                    numsTraitements.get(i).setNumTraitement(sb.toString());
                }
            }
        }
    }

    /**
     * Méthode en charge de compléter avec des zéros le nombre.
     * @param suffixe Suffixe.
     * @param nb Nombre.
     * @return Nombre complété avec des zéros devant pour que la taille du suffixe soit bonne.
     */
    private String complete(final String suffixe,
                            final String nb)
    {
        String result = nb;

        int taille = result.length();

        while (taille < suffixe.length())
        {
            result = "0"
                     + result;
            taille++;
        }
        return result;
    }

    /**
     * Méthode en charge de détecter le lancement du remplissage automatique des numéros de
     * traitement.
     * @param numsTraitements Liste des numéros de traitement.
     * @return Résultat.
     */
    protected boolean launchSaisieAuto(final List<NumTraitement> numsTraitements)
    {
        boolean launch = true;

        // On lance la saisie automatique si le premier élément est rempli et les autres vides.
        int index = 0;
        for (final NumTraitement numTraitement : numsTraitements)
        {
            // Traitement de l'élément index 0
            if ((index == 0)
                && (StringUtils.isEmpty(numTraitement.getNumTraitement())))
            {
                launch = false;
                break;
            }
            // Traitement des éléments suivants
            if (index != 0)
            {
                if (StringUtils.isNotEmpty(numTraitement.getNumTraitement()))
                {
                    launch = false;
                    break;
                }
            }
            index++;
        }
        return launch;
    }

    /**
     * Méthode en charge de dire si un caractère est un chiffre.
     * @param caractere Caractère à tester.
     * @return Indique si le caractère es un chiffre ou non.
     */
    private boolean isDigit(final char caractere)
    {
        return Character.isDigit(caractere);
    }

}
