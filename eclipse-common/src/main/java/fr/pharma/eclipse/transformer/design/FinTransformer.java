package fr.pharma.eclipse.transformer.design;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.Transformer;

import fr.pharma.eclipse.domain.enums.design.UniteTemps;
import fr.pharma.eclipse.domain.model.design.PrescriptionType;
import fr.pharma.eclipse.domain.model.design.embedded.TempsPrescription;

/**
 * Transformer en charge d'extraire la fin (debut + duree) d'une prescription.
 
 * @version $Revision$ $Date$
 */
public class FinTransformer
    implements Transformer
{
    /**
     * Table permettant de convertir les différentes unités.
     */
    private final Map<UniteTemps, Map<UniteTemps, Integer>> converter;

    /**
     * Constructeur.
     */
    public FinTransformer()
    {
        this.converter = new HashMap<UniteTemps, Map<UniteTemps, Integer>>();
        final Map<UniteTemps, Integer> mapJour = new HashMap<UniteTemps, Integer>();
        mapJour.put(UniteTemps.JOUR,
                    1);
        mapJour.put(UniteTemps.MOIS,
                    30);
        mapJour.put(UniteTemps.SEMAINE,
                    7);
        final Map<UniteTemps, Integer> mapSemaine = new HashMap<UniteTemps, Integer>();
        mapSemaine.put(UniteTemps.SEMAINE,
                       1);
        mapSemaine.put(UniteTemps.MOIS,
                       4);
        this.converter.put(UniteTemps.JOUR,
                           mapJour);
        this.converter.put(UniteTemps.SEMAINE,
                           mapSemaine);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object transform(final Object input)
    {
        final PrescriptionType prescription = (PrescriptionType) input;
        return this.add(prescription.getDebut(),
                        prescription.getDuree());
    }

    /**
     * Méthode en charge d'additionner le TempsPrescription debut et duree pour produire la fin.
     * @param debut Le TempsPrescription Debut.
     * @param duree Le TempsPrescription Duree.
     * @return Le temps prescription.
     */
    private TempsPrescription add(final TempsPrescription debut,
                                  final TempsPrescription duree)
    {
        final TempsPrescription result = new TempsPrescription();

        // si c'est la même unité
        if (debut.getUnite().equals(duree.getUnite()))
        {
            result.setUnite(debut.getUnite());
            result.setNb(debut.getNb()
                         + duree.getNb());
            return result;
        }

        // sinon on cherche la plus petite et on converti.
        final UniteTemps reference = this.getUniteMin(debut,
                                                      duree);
        result.setUnite(reference);
        result.setNb(this.converter.get(reference).get(debut.getUnite())
                     * debut.getNb()
                     + this.convertDuree(reference,
                                         duree));
        return result;
    }

    /**
     * Méthode en charge de convertir des durees.
     * @param duree La duree.
     * @return Le nombre d'unités.
     */
    private int convertDuree(final UniteTemps reference,
                             final TempsPrescription duree)
    {
        // si
        if (duree.getUnite().equals(UniteTemps.HEURE))
        {
            return duree.getNb() / 24;
        }
        else if (duree.getUnite().equals(UniteTemps.MINUTE))
        {
            return duree.getNb()
                   / (24 * 60);
        }
        else
        {
            return this.converter.get(reference).get(duree.getUnite())
                   * duree.getNb();
        }
    }

    /**
     * Retourné la plus petite unité des deux objets TempsPrescrition.
     * @param debut Le début.
     * @param duree La durée.
     * @return La plus petite unité des deux objets TempsPrescription.
     */
    private UniteTemps getUniteMin(final TempsPrescription debut,
                                   final TempsPrescription duree)
    {

        // L'unité de référence est soit le Jour soit la Semaine.
        if (debut.getUnite().equals(UniteTemps.JOUR)
            || duree.getUnite().equals(UniteTemps.JOUR)
            || duree.getUnite().equals(UniteTemps.HEURE)
            || duree.getUnite().equals(UniteTemps.MINUTE))
        {
            return UniteTemps.JOUR;
        }
        else
        {
            return UniteTemps.SEMAINE;
        }

    }

}
