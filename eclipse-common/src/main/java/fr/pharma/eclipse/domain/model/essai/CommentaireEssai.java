package fr.pharma.eclipse.domain.model.essai;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import fr.pharma.eclipse.domain.model.suivi.common.Suivi;

/**
 * Classe abstraite représentant un commentaire suivi posé sur l'essai.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@MappedSuperclass
public abstract class CommentaireEssai extends Suivi {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 4792702800738479481L;

    /**
     * Libellé du commentaire.
     */
    @Column(name = "libelle", columnDefinition = "TEXT")
    @NotNull
    @NotEmpty
    private String libelle;

    /**
     * Getter sur libelle.
     * @return Retourne le libelle.
     */
    public String getLibelle() {
        return this.libelle;
    }

    /**
     * Setter pour libelle.
     * @param libelle le libelle à écrire.
     */
    public void setLibelle(final String libelle) {
        this.libelle = libelle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new StringBuilder("[CommentaireEssai: ").append(this.getId()).append(", ").append(this.libelle).append("]").toString();
    }

}
