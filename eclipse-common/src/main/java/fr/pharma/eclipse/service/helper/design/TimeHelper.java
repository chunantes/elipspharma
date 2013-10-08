package fr.pharma.eclipse.service.helper.design;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.Transformer;

import fr.pharma.eclipse.comparator.design.TempsPrescriptionComparator;
import fr.pharma.eclipse.domain.enums.design.UniteTemps;
import fr.pharma.eclipse.domain.model.design.Designable;
import fr.pharma.eclipse.domain.model.design.PrescriptionType;
import fr.pharma.eclipse.domain.model.design.Sequence;
import fr.pharma.eclipse.domain.model.design.embedded.TempsPrescription;
import fr.pharma.eclipse.domain.model.essai.detail.design.DetailDesign;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.transformer.design.FinTransformer;
import fr.pharma.eclipse.transformer.design.GenericTransformer;
import fr.pharma.eclipse.utils.introspection.BeanTool;

/**
 * Helper en charge de fournir des méthodes aidant à déduire le début et la fin
 * d'un designable.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class TimeHelper {

    /**
     * Map de conversion Unité temps => Entier de Calendar.
     */
    Map<UniteTemps, Integer> mapUniteTemps = new HashMap<UniteTemps, Integer>();

    /**
     * Table permettant de convertir les différentes unités.
     */
    private final Map<UniteTemps, Map<UniteTemps, Integer>> converter;

    /**
     * Constructeur.s
     */
    public TimeHelper() {
        this.mapUniteTemps.put(UniteTemps.SEMAINE, Calendar.WEEK_OF_YEAR);
        this.mapUniteTemps.put(UniteTemps.MOIS, Calendar.MONTH);
        this.mapUniteTemps.put(UniteTemps.JOUR, Calendar.DAY_OF_YEAR);
        this.converter = new HashMap<UniteTemps, Map<UniteTemps, Integer>>();
        final Map<UniteTemps, Integer> mapJour = new HashMap<UniteTemps, Integer>();
        mapJour.put(UniteTemps.JOUR, 1);
        mapJour.put(UniteTemps.MOIS, 30);
        mapJour.put(UniteTemps.SEMAINE, 7);
        final Map<UniteTemps, Integer> mapSemaine = new HashMap<UniteTemps, Integer>();
        mapSemaine.put(UniteTemps.SEMAINE, 1);
        mapSemaine.put(UniteTemps.MOIS, 4);
        this.converter.put(UniteTemps.JOUR, mapJour);
        this.converter.put(UniteTemps.SEMAINE, mapSemaine);
    }

    /**
     * Méthode en charge de retourner le debut minimal de toutes les
     * prescription d'une séquence.
     * @param collection Collection de prescription.
     * @return Le TempsPrescription minimal.
     */
    public TempsPrescription getDebut(final Collection<PrescriptionType> collection) {
        // On filtre les prescription sans valeurs dans la propriété demandée.
        @SuppressWarnings("rawtypes")
        final Collection coll = this.filterNull(collection, "debut");

        CollectionUtils.transform(coll, new GenericTransformer("debut"));

        @SuppressWarnings("unchecked")
        final SortedSet<TempsPrescription> result = this.sortCollectionOfTempsPrescription(coll);
        if (result.isEmpty()) {
            return new TempsPrescription();
        }
        return result.first();
    }

    /**
     * Filtre les éléments Prescription dont la propriété de type
     * TempsPrescription est non rempli.
     * @param collection La collection de prescription
     * @param property La propriété
     * @return La liste filtrée.
     */
    @SuppressWarnings("all")
    private Collection<PrescriptionType> filterNull(final Collection<PrescriptionType> collection,
                                                    final String property) {
        // On filtre les prescription sans valeurs dans la propriété demandée.
        return CollectionUtils.select(collection, new Predicate() {

            @Override
            public boolean evaluate(final Object object) {
                final TempsPrescription t = (TempsPrescription) BeanTool.getPropriete(object, property);
                return (t != null) && (t.getNb() != null) && (t.getUnite() != null);
            }
        });
    }

    /**
     * Méthode en charge de trier la collection de TempsPrescription.
     * @param collection La collection.
     * @return La collection triée.
     */
    @SuppressWarnings("all")
    private SortedSet<TempsPrescription> sortCollectionOfTempsPrescription(final Collection<TempsPrescription> collection) {
        // on applique le comparateur et on ajoute les éléments.
        final SortedSet<TempsPrescription> temps = new TreeSet<TempsPrescription>(new TempsPrescriptionComparator());
        temps.addAll(collection);

        return temps;
    }

    /**
     * Méthode en charge de construire le TempsPrescription fin pour une liste
     * de prescription donnée.
     * @param prescriptions Liste de prescriptions.
     * @return Le TempsPrescription représentant la fin la plus élevée pour la
     * liste de prescriptions.
     */
    @SuppressWarnings("all")
    public TempsPrescription getFin(final Collection<PrescriptionType> prescriptions) {
        // On ne garde que les prescriptions dont les debut et duree sont
        // correctement renseignés.
        final Collection<PrescriptionType> collection = this.filterNull(prescriptions, "debut");
        final Collection collectionWithoutNull = this.filterNull(collection, "duree");
        CollectionUtils.transform(collectionWithoutNull, new FinTransformer());

        // Transformation de la collection en collection de fin
        // (TempsPrescription)
        final SortedSet<TempsPrescription> fins = this.sortCollectionOfTempsPrescription(collectionWithoutNull);

        if (fins.isEmpty()) {
            return new TempsPrescription();
        }
        return fins.last();
    }

    /**
     * Méthode en charge de construire le TempsPrescription fin pour une
     * séquence à partir du début et de la durée.
     * @param sequence la séquence.
     */
    @SuppressWarnings("all")
    public void buildFin(final Sequence sequence) {
        sequence.setFin(this.add(sequence.getDebut(), sequence.getDuree()));
    }

    /**
     * Méthode en charge de retourner le temps prescription de fin maximum du
     * design.
     * @param design Le design.
     * @return Le temps prescription max des fin des bras du design.
     */
    public TempsPrescription getDateFinForDesign(final DetailDesign design) {
        @SuppressWarnings("rawtypes")
        final List designables = new ArrayList<Designable>(design.getBras());
        CollectionUtils.transform(designables, new Transformer() {

            @Override
            public Object transform(final Object input) {
                return ((Designable) input).getFin();
            }
        });
        if (designables.isEmpty()) {
            return null;
        }
        @SuppressWarnings("unchecked")
        final Object max = Collections.max(designables, new TempsPrescriptionComparator());
        return (TempsPrescription) max;
    }

    /**
     * Méthode en charge de convertir un UniteTemps en Calendar en fonction de
     * la date courante.
     * @param date La date
     * @param temps Temps de Prescription.
     * @return La date calculée.
     */
    public Calendar convertTime(final Calendar date,
                                final TempsPrescription temps) {
        final Calendar c = new GregorianCalendar();
        c.setTime(date.getTime());
        c.add(this.mapUniteTemps.get(temps.getUnite()), temps.getNb());
        return c;
    }

    /**
     * Méthode en charge d'additionner le TempsPrescription debut et duree pour
     * produire la fin.
     * @param debut Le TempsPrescription Debut.
     * @param duree Le TempsPrescription Duree.
     * @return Le temps prescription.
     */
    private TempsPrescription add(final TempsPrescription debut,
                                  final TempsPrescription duree) {
        if ((debut == null) || (debut.getUnite() == null) || (debut.getNb() == null) || (duree == null) || (duree.getUnite() == null) || (duree.getNb() == null)) {
            throw new ValidationException("sequence.temps", new String[]{"notEmpty", });
        }

        final TempsPrescription result = new TempsPrescription();

        // si c'est la même unité
        if (debut.getUnite().equals(duree.getUnite())) {
            result.setUnite(debut.getUnite());
            result.setNb(debut.getNb() + duree.getNb());
            return result;
        }

        // sinon on cherche la plus petite et on converti.
        final UniteTemps reference = this.getUniteMin(debut, duree);
        result.setUnite(reference);
        result.setNb((this.converter.get(reference).get(debut.getUnite()) * debut.getNb()) + this.convertDuree(reference, duree));
        return result;
    }

    /**
     * Méthode en charge de convertir des durees.
     * @param duree La duree.
     * @return Le nombre d'unités.
     */
    private int convertDuree(final UniteTemps reference,
                             final TempsPrescription duree) {
        // si
        if (duree.getUnite().equals(UniteTemps.HEURE)) {
            return duree.getNb() / 24;
        } else if (duree.getUnite().equals(UniteTemps.MINUTE)) {
            return duree.getNb() / (24 * 60);
        } else {
            return this.converter.get(reference).get(duree.getUnite()) * duree.getNb();
        }
    }

    /**
     * Retourné la plus petite unité des deux objets TempsPrescrition.
     * @param debut Le début.
     * @param duree La durée.
     * @return La plus petite unité des deux objets TempsPrescription.
     */
    private UniteTemps getUniteMin(final TempsPrescription debut,
                                   final TempsPrescription duree) {

        // L'unité de référence est soit le Jour soit la Semaine.
        if (debut.getUnite().equals(UniteTemps.JOUR) || duree.getUnite().equals(UniteTemps.JOUR) || duree.getUnite().equals(UniteTemps.HEURE)
            || duree.getUnite().equals(UniteTemps.MINUTE)) {
            return UniteTemps.JOUR;
        } else {
            return UniteTemps.SEMAINE;
        }

    }
}
