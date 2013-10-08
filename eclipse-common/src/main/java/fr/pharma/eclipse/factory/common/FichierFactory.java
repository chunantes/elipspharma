package fr.pharma.eclipse.factory.common;

import java.io.Serializable;

import fr.pharma.eclipse.domain.model.common.Fichier;

/**
 * Fabrique d'objets {@link Fichier}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class FichierFactory implements Serializable {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -8364660706510593437L;

    /**
     * Méthode de création d'un nouveau fichier.
     * @return Fichier.
     */
    public Fichier getInitializedObject() {
        return new Fichier();
    }

}
