package fr.pharma.eclipse.component.essai.helper.impl;

import fr.pharma.eclipse.component.essai.TypeContact;
import fr.pharma.eclipse.component.essai.helper.DroitHabilitationInitializer;
import fr.pharma.eclipse.domain.model.habilitation.Habilitation;

/**
 * Initialiseur simple de droit d'habilitation, basé sur le type de contact avec
 * lequel il est paramétré.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class SimpleDroitHabilitationInitializer implements DroitHabilitationInitializer {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -2893526293334395192L;

    /**
     * Type du contact.
     */
    private final TypeContact typeContact;

    /**
     * Constructeur.
     * @param typeContactName Nom de la valeur de l'énumération
     * {@link TypeContact}.
     */
    public SimpleDroitHabilitationInitializer(final String typeContactName) {
        this(TypeContact.valueOf(typeContactName));
    }

    /**
     * Constructeur.
     * @param typeContact Type du contact.
     */
    public SimpleDroitHabilitationInitializer(final TypeContact typeContact) {
        this.typeContact = typeContact;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final Habilitation habilitation) {
        habilitation.setDroit(this.typeContact.getDroit());
    }

    /**
     * Getter sur typeContact.
     * @return Retourne le typeContact.
     */
    TypeContact getTypeContact() {
        return this.typeContact;
    }

}
