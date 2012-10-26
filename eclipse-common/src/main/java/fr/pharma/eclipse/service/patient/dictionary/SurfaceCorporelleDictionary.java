package fr.pharma.eclipse.service.patient.dictionary;

import java.io.Serializable;
import java.util.Map;

import fr.pharma.eclipse.domain.enums.patient.FormuleSurfaceCorporelle;

/**
 * Dictionnaire de stratégie contenant les algorithme de calcul de la surface corporelle.
 
 * @version $Revision$ $Date$
 */
public class SurfaceCorporelleDictionary
    implements Serializable
{
    /**
     * SserialVersionUID.
     */
    private static final long serialVersionUID = -5310308044053091441L;

    /**
     * Map contenant les stratégies.
     */
    private Map<FormuleSurfaceCorporelle, SurfaceCorporelleProcessor> dictionary;

    /**
     * Recherche la stratégie à appliquer en fonction de la valeur d'entrée.
     * @param formule La formule à utiliser.
     * @param taille La taille.
     * @param poids Le poids.
     * @return La surface corporelle.
     */
    public double process(final FormuleSurfaceCorporelle formule,
                          final double taille,
                          final double poids)
    {
        return this.dictionary.get(formule).process(taille,
                                                    poids);
    }

    /**
     * Getter sur dictionary.
     * @return Retourne le dictionary.
     */
    public Map<FormuleSurfaceCorporelle, SurfaceCorporelleProcessor> getDictionary()
    {
        return this.dictionary;
    }

    /**
     * Setter pour dictionary.
     * @param dictionary le dictionary à écrire.
     */
    public void setDictionary(final Map<FormuleSurfaceCorporelle, SurfaceCorporelleProcessor> dictionary)
    {
        this.dictionary = dictionary;
    }

}
