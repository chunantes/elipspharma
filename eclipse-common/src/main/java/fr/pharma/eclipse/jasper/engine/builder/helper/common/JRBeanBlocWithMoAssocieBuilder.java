package fr.pharma.eclipse.jasper.engine.builder.helper.common;

import java.io.Serializable;

import org.springframework.util.StringUtils;

import fr.pharma.eclipse.domain.jasper.model.common.JRBeanBlocWithMoAssocie;
import fr.pharma.eclipse.domain.model.common.DocumentEclipse;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.utils.introspection.BeanTool;

/**
 * Classe en charge de construire un bean jasper {@link JRBeanBlocWithMoAssocie}.
 
 * @version $Revision$ $Date$
 */
public class JRBeanBlocWithMoAssocieBuilder
    implements Serializable
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 4886443359665173931L;

    /**
     * Titre du bloc géré.
     */
    private final String titreBloc;

    /**
     * Propriété pour calculer la valeur du champ presence,<br>
     * à partir de l'essai.
     */
    private String essaiPropertyForPresence;

    /**
     * Propriété pour calculer la valeur du champ commentaire,<br>
     * à partir de l'essai.
     */
    private final String essaiPropertyForCommentaire;

    /**
     * Propriété pour calculer la valeur du champ moAssocie,<br>
     * à partir de l'essai.
     */
    private final String essaiPropertyForMoAssocie;

    /**
     * Constructeur.
     * @param titreBloc Titre du bloc géré.
     * @param essaiPropertyForPresence Propriété pour calculer la valeur du champ presence,<br>
     * à partir de l'essai.
     * @param essaiPropertyForCommentaire Propriété pour calculer la valeur<br>
     * du champ commentaire, à partir de l'essai.
     * @param essaiPropertyForMoAssocie Propriété pour calculer la valeur du champ moAssocie,<br>
     * à partir de l'essai.
     */
    public JRBeanBlocWithMoAssocieBuilder(
                                          final String titreBloc,
                                          final String essaiPropertyForPresence,
                                          final String essaiPropertyForCommentaire,
                                          final String essaiPropertyForMoAssocie)
    {
        this.titreBloc = titreBloc;
        this.essaiPropertyForPresence = essaiPropertyForPresence;
        this.essaiPropertyForCommentaire = essaiPropertyForCommentaire;
        this.essaiPropertyForMoAssocie = essaiPropertyForMoAssocie;
    }

    /**
     * Constructeur simple.
     * @param titreBloc Titre du bloc géré.
     * @param essaiPropertyForPresence Propriété pour calculer la valeur du champ presence,<br>
     * à partir de l'essai.
     */
    public JRBeanBlocWithMoAssocieBuilder(
                                          final String titreBloc,
                                          final String essaiPropertyForPresence)
    {
        this(titreBloc,
             essaiPropertyForPresence,
             null,
             null);
    }

    /**
     * Méthode en charge de créer le bean jasper {@link JRBeanBlocWithMoAssocie}<br>
     * à partir de l'essai et des paramètres de ce filler.
     * @param essai Essai à partir duquel créer le bean jasper.
     * @return Le bean {@link JRBeanBlocWithMoAssocie}.
     */
    public JRBeanBlocWithMoAssocie build(final Essai essai)
    {
        final JRBeanBlocWithMoAssocie jrBlocWithMoAssocie = new JRBeanBlocWithMoAssocie();
        jrBlocWithMoAssocie.setTitre(this.titreBloc);

        final boolean isPresent = this.fillPresence(essai,
                                                    jrBlocWithMoAssocie);
        this.fillCommentaire(essai,
                             jrBlocWithMoAssocie,
                             isPresent);
        this.fillMoAssocie(essai,
                           jrBlocWithMoAssocie,
                           isPresent);

        return jrBlocWithMoAssocie;
    }

    /**
     * Méthode en charge de valoriser le champ presence.
     * @param essai Essai à partir duquel créer le bean jasper.
     * @param jrBlocWithMoAssocie Bean jasper à valoriser.
     * @return Présent ou non.
     */
    private boolean fillPresence(final Essai essai,
                                 final JRBeanBlocWithMoAssocie jrBlocWithMoAssocie)
    {
        boolean isPresent;
        if (!StringUtils.hasText(this.essaiPropertyForPresence))
        {
            // Si propriété pas renseignée, présence à true par défaut.
            isPresent = true;
            jrBlocWithMoAssocie.setPresence(isPresent);
            jrBlocWithMoAssocie.setShowPresence(false); // on ne montre pas le OUI/NON
            return isPresent;
        }

        final Boolean presence = (Boolean) BeanTool.getPropriete(essai,
                                                                 this.essaiPropertyForPresence);
        isPresent = Boolean.TRUE.equals(presence);
        jrBlocWithMoAssocie.setPresence(isPresent);
        return isPresent;
    }

    /**
     * Méthode en charge de valoriser le champ MoAssocie.
     * @param essai Essai à partir duquel créer le bean jasper.
     * @param jrBlocWithMoAssocie Bean jasper à valoriser.
     * @param isPresent Indique si l'élément est présent.
     */
    private void fillMoAssocie(final Essai essai,
                               final JRBeanBlocWithMoAssocie jrBlocWithMoAssocie,
                               final boolean isPresent)
    {
        if (!StringUtils.hasText(this.essaiPropertyForMoAssocie)
            || !isPresent)
        {
            return;
        }

        final DocumentEclipse docAssocie =
            (DocumentEclipse) BeanTool.getPropriete(essai,
                                                    this.essaiPropertyForMoAssocie);
        if (docAssocie != null)
        {
            jrBlocWithMoAssocie.setMoAssocie(docAssocie.getNomUtilisateur());
        }
    }

    /**
     * Méthode en charge de valoriser le champ commentaire.
     * @param essai Essai à partir duquel créer le bean jasper.
     * @param jrBlocWithMoAssocie Bean jasper à valoriser.
     * @param isPresent Indique si l'élément est présent.
     */
    private void fillCommentaire(final Essai essai,
                                 final JRBeanBlocWithMoAssocie jrBlocWithMoAssocie,
                                 final Boolean isPresent)
    {
        if (!StringUtils.hasText(this.essaiPropertyForCommentaire)
            || !isPresent)
        {
            return;
        }

        final String commentaire =
            (String) BeanTool.getPropriete(essai,
                                           this.essaiPropertyForCommentaire);
        jrBlocWithMoAssocie.setCommentaire(commentaire);
    }

    /**
     * Getter sur titreBloc.
     * @return Retourne le titreBloc.
     */
    String getTitreBloc()
    {
        return this.titreBloc;
    }

    /**
     * Getter sur essaiPropertyForPresence.
     * @return Retourne le essaiPropertyForPresence.
     */
    String getEssaiPropertyForPresence()
    {
        return this.essaiPropertyForPresence;
    }

    /**
     * Getter sur essaiPropertyForCommentaire.
     * @return Retourne le essaiPropertyForCommentaire.
     */
    String getEssaiPropertyForCommentaire()
    {
        return this.essaiPropertyForCommentaire;
    }

    /**
     * Getter sur essaiPropertyForMoAssocie.
     * @return Retourne le essaiPropertyForMoAssocie.
     */
    String getEssaiPropertyForMoAssocie()
    {
        return this.essaiPropertyForMoAssocie;
    }

    /**
     * Setter pour essaiPropertyForPresence.
     * @param essaiPropertyForPresence le essaiPropertyForPresence à écrire.
     */
    void setEssaiPropertyForPresence(final String essaiPropertyForPresence)
    {
        this.essaiPropertyForPresence = essaiPropertyForPresence;
    }

}
