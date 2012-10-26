package fr.pharma.eclipse.service.dispensation.processor.helper;

import java.io.Serializable;
import java.util.Map;

import fr.pharma.eclipse.domain.enums.design.UniteTemps;

/**
 * Classe en charge de convertir des unités de temps.
 
 * @version $Revision$ $Date$
 */
public class UniteTempsConverter
    implements Serializable
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -8489571308887533595L;

    /**
     * Map de conversion.
     */
    private Map<UniteTemps, Integer> conversion;

    /**
     * Méthode en charge de convertir le nombre d'unité en paramètre en nombre d'unité
     * destination.
     * @param nb Le nombre d'unité de temps.
     * @param unite L'unité d'origine.
     * @return Le nombre d'unité de destination.
     */
    public int convert(final int nb,
                       final UniteTemps unite)
    {
        return this.conversion.get(unite)
               * nb;
    }

    /**
     * Retourne <true> si le converter supporte l'unité de temps en paramètre.
     * @param unite L'unité.
     * @return <true> si le converter supporte l'unité de temps en paramètre.
     */
    public boolean support(final UniteTemps unite)
    {
        return this.conversion.containsKey(unite);
    }

    /**
     * Setter pour conversion.
     * @param conversion le conversion à écrire.
     */
    public void setConversion(final Map<UniteTemps, Integer> conversion)
    {
        this.conversion = conversion;
    }
}
