package fr.pharma.eclipse.component.essai;

import java.io.Serializable;
import java.util.SortedSet;

import org.springframework.util.StringUtils;

import fr.pharma.eclipse.domain.enums.TypeCommentaireEssai;
import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.domain.model.essai.CommentaireEssai;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.factory.common.BeanObjectWithParentFactory;
import fr.pharma.eclipse.utils.introspection.BeanTool;

/**
 * Classe générique en charge de gérer la création d'un nouveau commentaire sur un essai.
 * @param <COMMENT> Type dérivé de {@link CommentaireEssai} représentant le commentaire managé.
 * @param <PARENT> Type dérivé de {@link BeanObject} représentant le parent du commentaire.
 
 * @version $Revision$ $Date$
 */
public class GenericCommentaireManager<COMMENT extends CommentaireEssai, PARENT extends BeanObject>
    implements Serializable
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -6963814626237082286L;

    /**
     * Type des commentaires managés.
     */
    private final TypeCommentaireEssai typeCommentaire;

    /**
     * Fabrique d'objets CommentaireEssai.
     */
    private BeanObjectWithParentFactory<COMMENT, PARENT> factory;

    /**
     * Libellé pour un nouveau commentaire.
     */
    private String libelle = org.apache.commons.lang.StringUtils.EMPTY;

    /**
     * Dernier commentaire enregistré.
     */
    private COMMENT lastCommentaire;

    /**
     * Constructeur.
     * @param typeCommentaireEssaiName Nom du type de commentaire managé.
     * @throws IllegalArgumentException Si typeCommentaireEssaiName ne correspond pas à une valeur
     * de l'énumération {@link TypeCommentaireEssai}.
     */
    public GenericCommentaireManager(final String typeCommentaireEssaiName)
    {
        this.typeCommentaire = TypeCommentaireEssai.valueOf(typeCommentaireEssaiName);
    }

    /**
     * Méthode qui indique si un commentaire peut être créé.
     * @return true ssi la méthode createCommentaire peut être appelée.
     */
    public boolean canCreateCommentaire()
    {
        return StringUtils.hasText(this.libelle);
    }

    /**
     * Crée un objet commentaire à partir du libellé renseigné <br>
     * et remet à blanc le libellé. La méthode canCreateCommentaire <br>
     * doit avoir été appelée et son retour doit être true.
     * @param essai Essai sur lequel on souhaite créer le commentaire.
     * @return Un nouveau CommentaireEssai.
     */
    @SuppressWarnings("unchecked")
    public COMMENT createCommentaire(final Essai essai)
    {
        final PARENT parent =
            (PARENT) BeanTool.getPropriete(essai,
                                           this.typeCommentaire
                                                   .getCommentairesParentPropertyFromEssai());
        final COMMENT beanCommentaire = this.factory.getInitializedObject(parent);
        beanCommentaire.setLibelle(this.libelle.trim());
        this.resetLibelle();
        return beanCommentaire;
    }

    /**
     * Méthode de réinitialisation du libellé.
     */
    public void resetLibelle()
    {
        this.libelle = org.apache.commons.lang.StringUtils.EMPTY;
    }

    /**
     * Méthode de récupération du dernier commentaire du type managé d'un essai.
     * @param essai Essai.
     */
    @SuppressWarnings("unchecked")
    public void initLastCommentaire(final Essai essai)
    {
        this.resetLastCommentaire();
        final SortedSet<COMMENT> commentaires =
            (SortedSet<COMMENT>) BeanTool
                    .getPropriete(essai,
                                  this.typeCommentaire.getCommentairesPropertyFromEssai());
        if (commentaires.isEmpty())
        {
            return;
        }
        this.setLastCommentaire(commentaires.first());
    }

    /**
     * Méthode de réinitialisation du dernier commentaire mémorisé.
     */
    private void resetLastCommentaire()
    {
        this.lastCommentaire = null;
    }

    /**
     * Getter sur lastCommentaire.
     * @return Retourne le lastCommentaire.
     */
    public COMMENT getLastCommentaire()
    {
        return this.lastCommentaire;
    }

    /**
     * Setter pour lastCommentaire.
     * @param lastCommentaire le lastCommentaire à écrire.
     */
    public void setLastCommentaire(final COMMENT lastCommentaire)
    {
        this.lastCommentaire = lastCommentaire;
    }

    /**
     * Getter sur libelle.
     * @return Retourne le libelle.
     */
    public String getLibelle()
    {
        return this.libelle;
    }

    /**
     * Setter pour libelle.
     * @param libelle le libelle à écrire.
     */
    public void setLibelle(final String libelle)
    {
        this.libelle = libelle;
    }

    /**
     * Setter pour factory.
     * @param factory le factory à écrire.
     */
    public void setFactory(final BeanObjectWithParentFactory<COMMENT, PARENT> factory)
    {
        this.factory = factory;
    }

    /**
     * Getter sur typeCommentaire.
     * @return Retourne le typeCommentaire.
     */
    public TypeCommentaireEssai getTypeCommentaire()
    {
        return this.typeCommentaire;
    }
}
