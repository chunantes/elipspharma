package fr.pharma.eclipse.component.essai.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.Transformer;

import fr.pharma.eclipse.component.essai.TypeContact;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.habilitation.Habilitation;

/**
 * Helper en charge de filtrer des contacts retournés de la base, pour ne conserver que ceux qui
 * peuvent être ajoutés à l'essai.
 
 * @version $Revision$ $Date$
 */
public class SelectableContactsFilter
    implements Serializable
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 7350017023436221015L;

    /**
     * Méthode en charge de retirer de la liste des personnes les personnes qui sont déjà
     * habilitées sur l'essai.
     * @param essai Essai à inspecter.
     * @param typeContact Type de contact.
     * @param selectableBeans Liste des sélectionnables à filtrer.
     */
    public void filter(final Essai essai,
                       final TypeContact typeContact,
                       final Collection<? extends Personne> selectableBeans)
    {
        if (selectableBeans.isEmpty())
        {
            return;
        }

        // Récupération des habilitations existantes à inspecter.
        final Collection<Habilitation> habilitationsReference =
            new ArrayList<Habilitation>(essai.getDetailContacts().getHabilitations());
        // Conservation des habilitations actives
        CollectionUtils.filter(habilitationsReference,
                               new Predicate() {

                                   @Override
                                   public boolean evaluate(final Object object)
                                   {
                                       final Habilitation habilitation = (Habilitation) object;
                                       return habilitation.isActive();
                                   }
                               });
        // Récupération des personnes habilitées à inspecter.
        final Collection<Object> personnesHabilitees =
            new ArrayList<Object>(habilitationsReference);
        CollectionUtils.transform(personnesHabilitees,
                                  new Transformer() {

                                      @Override
                                      public Object transform(final Object input)
                                      {
                                          final Habilitation habilitation = (Habilitation) input;
                                          return habilitation.getPersonne();
                                      }
                                  });
        // Filtrage des personnes sélectionnables.
        CollectionUtils.filter(selectableBeans,
                               new Predicate() {

                                   @Override
                                   public boolean evaluate(final Object object)
                                   {
                                       final Personne personne = (Personne) object;
                                       return !personnesHabilitees.contains(personne);
                                   }
                               });
    }
}
