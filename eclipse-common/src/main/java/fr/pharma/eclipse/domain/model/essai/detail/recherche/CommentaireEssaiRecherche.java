package fr.pharma.eclipse.domain.model.essai.detail.recherche;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Index;

import fr.pharma.eclipse.domain.model.essai.CommentaireEssai;

/**
 * Classe métier représentant un commentaire d'esssai clinique.
 
 * @version $Revision$ $Date$
 */
@Entity(name = "essai_commentaire_detail_recherche")
public class CommentaireEssaiRecherche
    extends CommentaireEssai
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -6068123147677711221L;

    /**
     * Objet auquel est rattaché le commentaire.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_detailRecherche", nullable = false)
    @Index(name = "idx_essai_commentaire_detail_recherche")
    private DetailRecherche detailRecherche;

    /**
     * Getter sur detailRecherche.
     * @return Retourne le detailRecherche.
     */
    public DetailRecherche getDetailRecherche()
    {
        return this.detailRecherche;
    }

    /**
     * Setter pour detailRecherche.
     * @param detailRecherche le detailRecherche à écrire.
     */
    public void setDetailRecherche(final DetailRecherche detailRecherche)
    {
        this.detailRecherche = detailRecherche;
    }

}
