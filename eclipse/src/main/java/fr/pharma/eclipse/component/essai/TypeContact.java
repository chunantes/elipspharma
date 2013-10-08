package fr.pharma.eclipse.component.essai;

import fr.pharma.eclipse.domain.enums.Droit;
import fr.pharma.eclipse.domain.enums.TypePersonne;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Enumération des différents types de contacts que peux ajouter un utilisateur.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public enum TypeContact {
    /**
     * ARC Investigateur.
     */
    ARC_INVESTIGATEUR(TypePersonne.ARC_INVESTIGATEUR, Droit.ARC_INVESTIGATEUR),

    /**
     * ARC Promoteur.
     */
    ARC_PROMOTEUR(TypePersonne.ARC_PROMOTEUR, Droit.ARC_PROMOTEUR),

    /**
     * CRO.
     */
    CRO(TypePersonne.CRO, Droit.CRO),

    /**
     * Direction de la Recherche Administratif.
     */
    DIRECTION_RECHERCHE_ADMINISTRATIF(TypePersonne.DIRECTION_RECHERCHE, Droit.DIRECTION_RECHERCHE_ADMINISTRATIF, new StringBuilder()
            .append(TypePersonne.DIRECTION_RECHERCHE.getLibelle()).append(EclipseConstants.SPACE).append(Droit.DIRECTION_RECHERCHE_ADMINISTRATIF).toString()),

    /**
     * Direction de la Recherche Pharmacovigilan.
     */
    DIRECTION_RECHERCHE_PHARMACOVIGILANT(TypePersonne.DIRECTION_RECHERCHE, Droit.DIRECTION_RECHERCHE_PHARMACOVIGILANT, new StringBuilder()
            .append(TypePersonne.DIRECTION_RECHERCHE.getLibelle()).append(EclipseConstants.SPACE).append(Droit.DIRECTION_RECHERCHE_PHARMACOVIGILANT).toString()),

    /**
     * Investigateur.
     */
    INVESTIGATEUR(TypePersonne.INVESTIGATEUR, Droit.INVESTIGATEUR_CO),

    /**
     * Pharmacien.
     */
    PHARMACIEN(TypePersonne.PHARMACIEN, null),

    /**
     * Promoteur.
     */
    PROMOTEUR(TypePersonne.PROMOTEUR, Droit.PROMOTEUR);

    /**
     * Type de personne associé.
     */
    private TypePersonne typePersonne;

    /**
     * Droit associé.
     */
    private Droit droit;

    /**
     * Libellé.
     */
    private String libelle;

    /**
     * Constructeur.
     * @param typePersonne Type de personne associé.
     * @param droit Droit associé.
     */
    private TypeContact(final TypePersonne typePersonne, final Droit droit) {
        this(typePersonne, droit, typePersonne.getLibelle());
    }

    /**
     * Constructeur.
     * @param typePersonne Type de personne associé.
     * @param droit Droit associé.
     * @param libelle Libellé.
     */
    private TypeContact(final TypePersonne typePersonne, final Droit droit, final String libelle) {
        this.libelle = libelle;
        this.typePersonne = typePersonne;
        this.droit = droit;
    }

    /**
     * Getter sur libelle.
     * @return Retourne le libelle.
     */
    public String getLibelle() {
        return this.libelle;
    }

    /**
     * Getter sur typePersonne.
     * @return Retourne le typePersonne.
     */
    public TypePersonne getTypePersonne() {
        return this.typePersonne;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.getLibelle();
    }

    /**
     * Getter sur droit.
     * @return Retourne le droit.
     */
    public Droit getDroit() {
        return this.droit;
    }
}
