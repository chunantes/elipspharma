package fr.pharma.eclipse.domain.enums;

/**
 * Enumération représentant le type de personne.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public enum TypePersonne {
    /**
     * ARC Investigateur.
     */
    ARC_INVESTIGATEUR("ARC Investigateur", RolePersonne.ARC_INVESTIGATEUR, CategoriePersonne.INTERNE, new Droit[]{Droit.ARC_INVESTIGATEUR }),

    /**
     * ARC Promoteur.
     */
    ARC_PROMOTEUR("ARC Promoteur", RolePersonne.ARC_PROMOTEUR, CategoriePersonne.EXTERNE, new Droit[]{Droit.ARC_PROMOTEUR }),

    /**
     * CRO.
     */
    CRO("ARC CRO", RolePersonne.CRO, CategoriePersonne.EXTERNE, new Droit[]{Droit.CRO }),

    /**
     * Direction de la Recherche.
     */
    DIRECTION_RECHERCHE("Direction Recherche", RolePersonne.DIRECTION_RECHERCHE, CategoriePersonne.INTERNE, new Droit[]{Droit.DIRECTION_RECHERCHE_ADMINISTRATIF,
                                                                                                                        Droit.DIRECTION_RECHERCHE_PHARMACOVIGILANT }),

    /**
     * Investigateur.
     */
    INVESTIGATEUR("Investigateur", RolePersonne.INVESTIGATEUR, CategoriePersonne.INTERNE, new Droit[]{Droit.INVESTIGATEUR_CO, Droit.INVESTIGATEUR_PRINCIPAL }),

    /**
     * Pharmacien.
     */
    PHARMACIEN("Personnel Pharmaceutique", RolePersonne.PHARMACIEN, CategoriePersonne.INTERNE, new Droit[]{Droit.PHARMACIEN_INTERNE, Droit.PHARMACIEN_EXTERNE,
                                                                                                           Droit.PHARMACIEN_PREPARATEUR, Droit.PHARMACIEN_TITULAIRE,
                                                                                                           Droit.PHARMACIEN_ATTACHE, Droit.PHARMACIEN_ASSISTANT,
                                                                                                           Droit.PHARMACIEN_INTERNE_GARDE, }),

    /**
     * Promoteur.
     */
    PROMOTEUR("Promoteur", RolePersonne.PROMOTEUR, CategoriePersonne.EXTERNE, new Droit[]{Droit.PROMOTEUR });

    /**
     * Libellé.
     */
    private String libelle;

    /**
     * Role de la personne.
     */
    private RolePersonne rolePersonne;

    /**
     * Catégorie.
     */
    private CategoriePersonne categorie;

    /**
     * Liste des droits accessibles pour le type de personne.
     */
    private Droit[] droits;

    /**
     * Constructeur privé.
     * @param libelle Libellé de la valeur de l'énumération.
     * @param rolePersonne Rôle associé au type de la personne.
     * @param categorie Catégorie du type de personne.
     * @param droits Liste des droits possibles pour le type de personne.
     */
    private TypePersonne(final String libelle, final RolePersonne rolePersonne, final CategoriePersonne categorie, final Droit[] droits) {
        this.libelle = libelle;
        this.categorie = categorie;
        this.droits = droits;
        this.rolePersonne = rolePersonne;
    }

    /**
     * Getter sur libelle.
     * @return Retourne le libelle.
     */
    public String getLibelle() {
        return this.libelle;
    }

    /**
     * Getter pour droits.
     * @return Le droits
     */
    public Droit[] getDroits() {
        return this.droits;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.getLibelle();
    }

    /**
     * Getter sur categorie.
     * @return Retourne le categorie.
     */
    public CategoriePersonne getCategorie() {
        return this.categorie;
    }

    /**
     * Getter pour rolePersonne.
     * @return Le rolePersonne
     */
    public RolePersonne getRolePersonne() {
        return this.rolePersonne;
    }

}
