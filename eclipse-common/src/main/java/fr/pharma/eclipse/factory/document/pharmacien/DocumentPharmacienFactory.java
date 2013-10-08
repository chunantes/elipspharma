package fr.pharma.eclipse.factory.document.pharmacien;

import java.io.Serializable;

import fr.pharma.eclipse.domain.model.acteur.Pharmacien;
import fr.pharma.eclipse.domain.model.acteur.document.DocumentPharmacien;
import fr.pharma.eclipse.domain.model.common.Fichier;

/**
 * Interface des fabriques de documents Pharmacien.
 * @param <DOC> Type de document de pharmacien créé.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public interface DocumentPharmacienFactory<DOC extends DocumentPharmacien> extends Serializable {
    /**
     * Méthode de création d'un nouveau document de Pharmacien.
     * @param fichier Fichier importé par l'utilisateur.
     * @param produit Le pharmacien auquel est rattaché le document.
     * @return Un nouveau document de pharmacien.
     */
    DOC getInitializedObject(Fichier fichier,
                             Pharmacien pharmacien);
}
