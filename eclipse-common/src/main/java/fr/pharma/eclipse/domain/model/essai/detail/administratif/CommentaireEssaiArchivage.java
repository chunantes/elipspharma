package fr.pharma.eclipse.domain.model.essai.detail.administratif;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Index;

import fr.pharma.eclipse.domain.model.essai.CommentaireEssai;

/**
 * Classe métier représentant un commentaire sur l'archivage de l'essai clinique
 * (onglet administratif/réglementaire).
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "essai_commentaire_detail_administratif_archi")
public class CommentaireEssaiArchivage extends CommentaireEssai {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -33277516552271419L;

    /**
     * Objet auquel est rattaché le commentaire.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_detailAdministratif", nullable = false)
    @Index(name = "idx_essai_commentaire_detail_administratif")
    private DetailAdministratif detailAdministratif;

    /**
     * Getter sur detailAdministratif.
     * @return Retourne le detailAdministratif.
     */
    public DetailAdministratif getDetailAdministratif() {
        return this.detailAdministratif;
    }

    /**
     * Setter pour detailAdministratif.
     * @param detailAdministratif le detailAdministratif à écrire.
     */
    public void setDetailAdministratif(final DetailAdministratif detailAdministratif) {
        this.detailAdministratif = detailAdministratif;
    }

}
