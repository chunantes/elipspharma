package fr.pharma.eclipse.service.habilitation.helper;

import java.io.Serializable;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import fr.pharma.eclipse.comparator.habilitation.HabilitationComparator;
import fr.pharma.eclipse.domain.enums.Droit;
import fr.pharma.eclipse.domain.model.acteur.Investigateur;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.habilitation.Habilitation;

/**
 * Helper pour le traitement des habilitations.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class HabilitationsHelper implements Serializable {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 6197634439360403803L;

    /**
     * Méthode de récupération des habilitations (actives et inactives) sur
     * certains droits.
     * @param essai Essai.
     * @param droits Droits cherchés.
     * @return L'ensemble des habilitations (actives et inactives) sur les
     * droits donnés.
     */
    public SortedSet<Habilitation> getHabilitations(final Essai essai,
                                                    final List<Droit> droits) {
        return this.getHabilitations(essai, droits, null);
    }

    /**
     * Méthode de récupération des habilitations (actives et inactives) sur
     * certains droits.
     * @param essai Essai.
     * @param droits Droits cherchés.
     * @param filterActif Filtre à appliquer sur le caractère actif ou non de
     * l'habilitation :<br>
     * - null => pas de filtre;<br>
     * - true => seulement les habilitations actives;<br>
     * - false: seulement les habilitions inactives.
     * @return L'ensemble des habilitations (actives et inactives) sur les
     * droits donnés.
     */
    public SortedSet<Habilitation> getHabilitations(final Essai essai,
                                                    final List<Droit> droits,
                                                    final Boolean filterActif) {
        final SortedSet<Habilitation> habilitations = new TreeSet<Habilitation>(new HabilitationComparator());
        habilitations.addAll(essai.getDetailContacts().getHabilitations());
        CollectionUtils.filter(habilitations, new Predicate() {

            @Override
            public boolean evaluate(final Object object) {
                final Habilitation current = (Habilitation) object;
                boolean actifOk = true;
                if (filterActif != null) {
                    actifOk = Boolean.valueOf(filterActif).equals(Boolean.valueOf(current.isActive()));
                }
                return actifOk && droits.contains(current.getDroit());
            }
        });
        return habilitations;
    }

    /**
     * Méthode en charge de récupérer l'investigateur principal d'un essai.
     * @param essai Essai.
     * @return L'investigateur principal de l'essai, s'il existe.
     */
    public Investigateur getInvestigateurPrincipal(final Essai essai) {
        final Habilitation habInvPrincipal = (Habilitation) CollectionUtils.find(essai.getDetailContacts().getHabilitations(), new Predicate() {

            @Override
            public boolean evaluate(final Object object) {
                final Habilitation current = (Habilitation) object;
                return current.isActive() && Droit.INVESTIGATEUR_PRINCIPAL.equals(current.getDroit());
            }
        });
        Investigateur invPrincipal = null;
        if (habInvPrincipal != null) {
            invPrincipal = (Investigateur) habInvPrincipal.getPersonne();
        }
        return invPrincipal;
    }

}
