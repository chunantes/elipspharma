package fr.pharma.eclipse.jasper.engine.filler.impl.aidedispensation.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;

import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanContact;
import fr.pharma.eclipse.domain.model.habilitation.Habilitation;

/**
 * Helper pour la création des beans de contacts à partir des habilitations de
 * l'essai.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ContactsFillerHelper implements Serializable {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 2362097161669367869L;

    /**
     * Méthode en charge de transformer un ensemble d'habilitations en une
     * collection de {@link JRBeanContact}.
     * @param habilitations Habilitations à transformer.
     * @return La collection des {@link JRBeanContact} correspondante.
     */
    @SuppressWarnings("unchecked")
    public Collection<JRBeanContact> transform(final Set<Habilitation> habilitations) {
        final Collection<? extends Object> beanContacts = new ArrayList<Habilitation>(habilitations);
        final Transformer transformer = new Transformer() {
            @Override
            public Object transform(final Object input) {
                final Habilitation habilitation = (Habilitation) input;
                final JRBeanContact beanContact = new JRBeanContact();
                beanContact.setHabilitation(habilitation.getDroit().getLibelle());
                beanContact.setMail(habilitation.getPersonne().getMail());
                beanContact.setNom(habilitation.getPersonne().getNom());
                beanContact.setPrenom(habilitation.getPersonne().getPrenom());
                beanContact.setProfil(habilitation.getPersonne().getType().getLibelle());
                beanContact.setTel(habilitation.getPersonne().getTelephone());
                return beanContact;
            }
        };
        CollectionUtils.transform(beanContacts, transformer);

        return (Collection<JRBeanContact>) beanContacts;
    }

}
