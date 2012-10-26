package fr.pharma.eclipse.utils;

import fr.pharma.eclipse.domain.enums.TypePersonne;
import fr.pharma.eclipse.domain.enums.TypePharmacien;
import fr.pharma.eclipse.domain.model.acteur.Cro;
import fr.pharma.eclipse.domain.model.acteur.Investigateur;
import fr.pharma.eclipse.domain.model.acteur.Pharmacien;

/**
 * Classe utilitaire pour les données de tests relatives aux beans Personne.
 
 * @version $Revision$ $Date$
 */
public final class PersonneUtils
{
    /**
     * Constructeur privé.
     */
    private PersonneUtils()
    {
        super();
    }

    /**
     * Méthode de création d'un bean Pharmacien.
     * @param id Identifiant technique.
     * @return Un bean Pharmacien.
     */
    public static Pharmacien makePharmacien(final long id)
    {
        return PersonneUtils.makePharmacien(id,
                                            TypePharmacien.TITULAIRE);
    }

    /**
     * Méthode de création d'un bean Pharmacien.
     * @param id Identifiant technique.
     * @param type Type du pharmacien.
     * @return Un bean Pharmacien.
     */
    public static Pharmacien makePharmacien(final long id,
                                            final TypePharmacien type)
    {
        final Pharmacien personne = new Pharmacien();
        personne.setId(1L);
        personne.setType(TypePersonne.PHARMACIEN);
        personne.setTypePharmacien(type);
        return personne;
    }

    /**
     * Méthode de création d'un bean Cro.
     * @param id Identifiant technique.
     * @return Un bean Pharmacien.
     */
    public static Cro makeCro(final long id)
    {
        return PersonneUtils.makeCro(id,
                                     "nom_"
                                             + id);
    }

    /**
     * Méthode de création d'un bean Cro.
     * @param id Identifiant technique.
     * @param nom Nom.
     * @return Un bean Pharmacien.
     */
    public static Cro makeCro(final long id,
                              final String nom)
    {
        return PersonneUtils.makeCro(id,
                                     nom,
                                     "prenom_"
                                             + id);
    }

    /**
     * Méthode de création d'un bean Cro.
     * @param id Identifiant technique.
     * @param nom Nom.
     * @param prenom Prénom.
     * @return Un bean Pharmacien.
     */
    public static Cro makeCro(final long id,
                              final String nom,
                              final String prenom)
    {
        final Cro cro = new Cro();
        cro.setId(id);
        cro.setType(TypePersonne.CRO);
        cro.setNom(nom);
        cro.setPrenom(prenom);
        return cro;
    }

    /**
     * Méthode de création d'un bean Investigateur.
     * @param id Identifiant technique.
     * @return Un bean Investigateur.
     */
    public static Investigateur makeInvestigateur(final long id)
    {
        final Investigateur personne = new Investigateur();
        personne.setId(1L);
        personne.setType(TypePersonne.INVESTIGATEUR);
        return personne;
    }
}
