package fr.pharma.eclipse.domain.model.common;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang.StringUtils;

import fr.pharma.eclipse.domain.enums.document.EnumTypeDocument;
import fr.pharma.eclipse.domain.model.suivi.common.Suivi;

/**
 * Classe abstraite représentant un document de l'application Eclipse.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@MappedSuperclass
public abstract class DocumentEclipse extends Suivi {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -3807724477786555402L;

    /**
     * Nom original du fichier lors de son téléchargement.
     */
    @Column(name = "nomUser")
    private String nomUtilisateur;

    /**
     * Nom du fichier sur le disque.
     */
    @Column(name = "nomDisque")
    private String nomDisque;

    /**
     * Méthode de récupération du type de document.
     * @return Le type du document.
     */
    public abstract EnumTypeDocument getTypeDocument();

    /**
     * Getter sur nomUtilisateur.
     * @return Retourne le nomUtilisateur.
     */
    public String getNomUtilisateur() {
        // Reformatage lié aux problèmes rencontrés en prod.
        if (this.nomUtilisateur.contains("\\")) {
            return StringUtils.substringAfterLast(this.nomUtilisateur, "\\");
        } else if (this.nomUtilisateur.contains("/")) {
            return StringUtils.substringAfter(this.nomUtilisateur, "/");
        } else {
            return this.nomUtilisateur;
        }
    }

    /**
     * Setter pour nomUtilisateur.
     * @param nomUtilisateur le nomUtilisateur à écrire.
     */
    public void setNomUtilisateur(final String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    /**
     * Getter sur nomDisque.
     * @return Retourne le nomDisque.
     */
    public String getNomDisque() {
        return this.nomDisque;
    }

    /**
     * Setter pour nomDisque.
     * @param nomDisque le nomDisque à écrire.
     */
    public void setNomDisque(final String nomDisque) {
        this.nomDisque = nomDisque;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new StringBuilder("[DocumentEclipse] ").append(this.getId()).append(",").append(this.getTypeDocument()).append(",").append(this.nomUtilisateur).toString();
    }
}
