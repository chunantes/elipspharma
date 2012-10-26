package fr.pharma.eclipse.domain.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Enumération des groupes d'habilitations affichées à l'écran.
 
 * @version $Revision$ $Date$
 */
public enum GroupeContacts
{
    /**
     * Groupe promoteur.
     */
    PROMOTEURS(Droit.ARC_PROMOTEUR, Droit.CRO, Droit.PROMOTEUR),

    /**
     * Groupe Investigateurs.
     */
    INVESTIGATEURS(Droit.INVESTIGATEUR_PRINCIPAL, Droit.INVESTIGATEUR_CO, Droit.ARC_INVESTIGATEUR),

    /**
     * Groupe DRC.
     */
    DRC(Droit.DIRECTION_RECHERCHE_ADMINISTRATIF, Droit.DIRECTION_RECHERCHE_PHARMACOVIGILANT),

    /**
     * Groupe pharmaciens.
     */
    PHARMACIENS(Droit.PHARMACIEN_INTERNE, Droit.PHARMACIEN_EXTERNE, Droit.PHARMACIEN_PREPARATEUR,
            Droit.PHARMACIEN_TITULAIRE, Droit.PHARMACIEN_ASSISTANT, Droit.PHARMACIEN_ATTACHE,
            Droit.PHARMACIEN_INTERNE_GARDE);

    /**
     * Liste des droits des habilitations du groupe.
     */
    private List<Droit> droits;

    /**
     * Constructeur.
     * @param droits Liste des droits des habilitations du groupe.
     */
    private GroupeContacts(final Droit... droits)
    {
        this.droits = new ArrayList<Droit>(Arrays.asList(droits));
    }

    /**
     * Getter sur droits.
     * @return Retourne le droits.
     */
    public List<Droit> getDroits()
    {
        return this.droits;
    }
}
