package fr.pharma.eclipse.service.dispensation.processor.helper;

import java.io.Serializable;
import java.util.Map;

import fr.pharma.eclipse.domain.enums.design.TypeRegularite;
import fr.pharma.eclipse.domain.enums.design.UniteTemps;
import fr.pharma.eclipse.domain.model.design.embedded.Frequence;
import fr.pharma.eclipse.domain.model.design.embedded.TempsPrescription;

/**
 * Helper en charge de fournir des méthodes utiles afin de convertir les fréquences en un nombre.
 
 * @version $Revision$ $Date$
 */
public class FrequenceHelper
    implements Serializable
{
    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -6043942687581783780L;

    /**
     * Map contenant les stratégies de conversion.
     */
    private Map<UniteTemps, UniteTempsConverter> conversionStrategy;

    /**
     * Méthode en charge de convertir un TempsPrescription en entier. La conversion est différente
     * selon le type de régularité.
     * @param duree Durée.
     * @param frequence Frequence.
     * @return un entier représentant la durée dans l'unité de la fréquence.
     */
    public Integer convertToInt(final TempsPrescription duree,
                                final Frequence frequence)
    {

        // si la frequence est de type de regularité PAR.
        if (frequence.getTypeRegularite().equals(TypeRegularite.PAR))
        {
            // Si le converter correspondant prend en charge l'unité.
            if (this.conversionStrategy
                    .get(frequence.getUniteFrequence())
                    .support(duree.getUnite()))
            {
                return duree.getNb()
                       * this.conversionStrategy
                               .get(frequence.getUniteFrequence())
                               .convert(frequence.getNbFrequence(),
                                        duree.getUnite());
            }
            return null;
        }
        else if (frequence.getTypeRegularite().equals(TypeRegularite.TOUS_LES))
        {
            // Si le converter correspondant prend en charge l'unité.
            if (frequence.getNbUniteTempsFrequence() != null
                && this.conversionStrategy
                        .get(frequence.getUniteFrequence())
                        .support(duree.getUnite()))
            {
                final int n =
                    this.conversionStrategy
                            .get(frequence.getUniteFrequence())
                            .convert(duree.getNb(),
                                     duree.getUnite());
                return frequence.getNbFrequence()
                       * Math.abs(n
                                  / frequence.getNbUniteTempsFrequence());
            }
            return null;
        }
        else if (frequence.getTypeRegularite().equals(TypeRegularite.FOIS))
        {
            return frequence.getNbFrequence();
        }
        return null;
    }

    /**
     * Setter pour conversionStrategy.
     * @param conversionStrategy le conversionStrategy à écrire.
     */
    public void setConversionStrategy(final Map<UniteTemps, UniteTempsConverter> conversionStrategy)
    {
        this.conversionStrategy = conversionStrategy;
    }
}
