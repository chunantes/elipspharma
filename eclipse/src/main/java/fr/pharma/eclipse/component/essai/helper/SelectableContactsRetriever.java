package fr.pharma.eclipse.component.essai.helper;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.collections.ComparatorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pharma.eclipse.comparator.GenericComparator;
import fr.pharma.eclipse.component.essai.TypeContact;
import fr.pharma.eclipse.component.essai.seeker.SelectableContactsSeeker;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.essai.Essai;

/**
 * Classe en charge de récupérer la liste des contacts sélectionnables pour un essai.
 
 * @version $Revision$ $Date$
 */
public class SelectableContactsRetriever
    implements Serializable
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 7092631114158662272L;

    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(SelectableContactsRetriever.class);

    /**
     * Dictionnaire des services pour la récupération des contacts sélectionnables sur un essai.
     */
    private List<SelectableContactsSeeker> contactsSeekers;

    /**
     * Helper pour le filtrage des contacts.
     */
    private SelectableContactsFilter filter;

    /**
     * Cette méthode renvoie la liste des contacts d'un certain type que l'on peut habiliter pour
     * un essai.
     * @param essai Essai à inspecter.
     * @param typeContact Type de contact.
     * @return La liste des contacts du type spécifié que l'on peut encore habiliter sur l'essai.
     */
    public Collection<Personne> retrieveSelectableContacts(final Essai essai,
                                                           final TypeContact typeContact)
    {
        final Comparator<Personne> comparator = this.prepareComparator();
        final SortedSet<Personne> selectableBeans = new TreeSet<Personne>(comparator);
        boolean supportTrouve = false;
        for (final SelectableContactsSeeker seeker : this.contactsSeekers)
        {
            if (seeker.supports(typeContact))
            {
                selectableBeans.addAll(seeker.getContacts(essai));
                supportTrouve = true;
            }
        }
        this.handleSupportTrouve(typeContact,
                                 supportTrouve);

        // On retire des personnes sélectionnables celles qui sont déjà habilitées sur l'essai.
        this.filter.filter(essai,
                           typeContact,
                           selectableBeans);
        return selectableBeans;
    }

    /**
     * Log une erreur si aucun préparateur n'est trouvé.
     * @param typeContact Type du contact.
     * @param supportTrouve Flag qui indique si un exécuteur a été trouvé.
     */
    private void handleSupportTrouve(final TypeContact typeContact,
                                     final boolean supportTrouve)
    {
        if (!supportTrouve)
        {
            this.log.error(new StringBuilder("[retrieveSelectableContacts] ")
                    .append("Aucun support pour le type de contacts '")
                    .append(typeContact)
                    .append("' => aucun ajout possible!")
                    .toString());
        }
    }

    /**
     * Méthode de préparation du comparateur sur les personnes.
     * @return Comparateur de personnes.
     */
    @SuppressWarnings("unchecked")
    private Comparator<Personne> prepareComparator()
    {
        final Comparator<Personne> comparator =
            ComparatorUtils.chainedComparator(Arrays
                    .asList(new GenericComparator<Personne>("nom"),
                            new GenericComparator<Personne>("prenom")));
        return comparator;
    }

    /**
     * Getter sur contactsSeekers.
     * @return Retourne le contactsSeekers.
     */
    List<SelectableContactsSeeker> getContactsSeekers()
    {
        return this.contactsSeekers;
    }

    /**
     * Setter pour contactsSeekers.
     * @param contactsSeekers le contactsSeekers à écrire.
     */
    public void setContactsSeekers(final List<SelectableContactsSeeker> contactsSeekers)
    {
        this.contactsSeekers = contactsSeekers;
    }

    /**
     * Getter sur filter.
     * @return Retourne le filter.
     */
    SelectableContactsFilter getFilter()
    {
        return this.filter;
    }

    /**
     * Setter pour filter.
     * @param filter le filter à écrire.
     */
    public void setFilter(final SelectableContactsFilter filter)
    {
        this.filter = filter;
    }

}
