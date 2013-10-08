package fr.pharma.eclipse.utils;

import java.util.Calendar;

import org.mockito.Mockito;

import fr.pharma.eclipse.domain.enums.Droit;
import fr.pharma.eclipse.domain.enums.TypePersonne;
import fr.pharma.eclipse.domain.enums.TypePromoteur;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.administratif.DetailAdministratif;
import fr.pharma.eclipse.domain.model.essai.detail.autresdocs.DetailAutresDocuments;
import fr.pharma.eclipse.domain.model.essai.detail.contact.DetailContacts;
import fr.pharma.eclipse.domain.model.essai.detail.dates.DetailDates;
import fr.pharma.eclipse.domain.model.essai.detail.design.DetailDesign;
import fr.pharma.eclipse.domain.model.essai.detail.faisabilite.DetailFaisabilite;
import fr.pharma.eclipse.domain.model.essai.detail.pharma.DetailDonneesPharma;
import fr.pharma.eclipse.domain.model.essai.detail.produit.DetailProduit;
import fr.pharma.eclipse.domain.model.essai.detail.recherche.CommentaireEssaiRecherche;
import fr.pharma.eclipse.domain.model.essai.detail.recherche.DetailRecherche;
import fr.pharma.eclipse.domain.model.habilitation.Habilitation;

/**
 * Classe utilitaire pour le traitement des essais dans les tests.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public final class EssaiUtils {
    /**
     * Constructeur privé.
     */
    private EssaiUtils() {
        super();
    }

    /**
     * Méthode de création d'un essai pour les tests.
     * @param id Identifiant de l'essai.
     * @return Un essai initialisé pour les tests.
     */
    public static Essai makeEssaiTest(final long id) {
        return EssaiUtils.makeEssaiTest(id, null);
    }

    /**
     * Méthode de création d'un essai pour les tests.
     * @param idEssai Identifiant de l'essai.
     * @param typePromoteur Type du promoteur associé à l'essai.
     * @return Un essai initialisé pour les tests.
     */
    public static Essai makeEssaiTest(final Long idEssai,
                                      final TypePromoteur typePromoteur) {
        final Essai essai = EssaiUtils.makeEssai(idEssai);
        essai.setId(idEssai);
        essai.setTypePromoteur(typePromoteur);
        return essai;
    }

    /**
     * Crée un essai vide.
     * @param idDetail Identifiant du détail.
     * @return Un Essai.
     */
    private static Essai makeEssai(final long idDetail) {
        final Essai essai = new Essai();
        final DetailRecherche detailRecherche = new DetailRecherche();
        detailRecherche.setId(idDetail);
        essai.setDetailRecherche(detailRecherche);
        final DetailContacts detailContacts = new DetailContacts();
        detailContacts.setId(idDetail);
        essai.setDetailContacts(detailContacts);
        final DetailFaisabilite detailFaisabilite = new DetailFaisabilite();
        detailFaisabilite.setId(idDetail);
        essai.setDetailFaisabilite(detailFaisabilite);
        final DetailDates detailDates = new DetailDates();
        detailDates.setId(idDetail);
        essai.setDetailDates(detailDates);
        final DetailAdministratif detailAdmin = new DetailAdministratif();
        essai.setDetailAdministratif(detailAdmin);
        final DetailProduit detailProduit = new DetailProduit();
        essai.setDetailProduit(detailProduit);
        final DetailDonneesPharma detailPharma = new DetailDonneesPharma();
        essai.setDetailDonneesPharma(detailPharma);
        final DetailDesign detailDesign = new DetailDesign();
        essai.setDetailDesign(detailDesign);
        final DetailAutresDocuments detailAutresDoc = new DetailAutresDocuments();
        detailAutresDoc.setId(idDetail);
        essai.setDetailAutresDocuments(detailAutresDoc);
        return essai;
    }

    /**
     * Méthode de création d'un commentaire d'essai pour les tests.
     * @param id Son identifiant.
     * @param dMaj Sa date de mise à jour.
     * @return Nouveau CommentaireEssaiRecherche.
     */
    public static CommentaireEssaiRecherche makeCommentaireTest(final Long id,
                                                                final Calendar dMaj) {
        final CommentaireEssaiRecherche commentaire = new CommentaireEssaiRecherche();
        commentaire.setId(id);
        commentaire.setDateMaj(dMaj);
        return commentaire;
    }

    /**
     * Méthode de création d'une habilitation vide.
     * @param id Identifiant de l'habilitation.
     * @param droit Droit à poser sur l'habilitation.
     * @param nomPersonne Nom de la personne.
     * @param typePersonne Type de la personne.
     * @return Nouveau bean Habilitation (personne mockée!).
     */
    public static Habilitation makeHabilitationTest(final Long id,
                                                    final Droit droit,
                                                    final String nomPersonne,
                                                    final TypePersonne typePersonne) {
        final Personne personne = Mockito.mock(Personne.class);
        Mockito.when(personne.getNom()).thenReturn(nomPersonne);
        Mockito.when(personne.getType()).thenReturn(typePersonne);
        return EssaiUtils.makeHabilitationTest(id, droit, personne);
    }

    /**
     * Méthode de création d'une habilitation.
     * @param id Identifiant de l'habilitation.
     * @param droit Droit à poser sur l'habilitation.
     * @param personne Personne associée à l'habilitation.
     * @return Nouveau bean Habilitation (personne mockée!).
     */
    public static Habilitation makeHabilitationTest(final Long id,
                                                    final Droit droit,
                                                    final Personne personne) {
        final Habilitation hab = EssaiUtils.makeHabilitationTest(id, false, true);
        hab.setDroit(droit);
        hab.setPersonne(personne);
        return hab;
    }

    /**
     * Méthode de création d'une habilitation vide.
     * @param id Identifiant de l'habilitation.
     * @param droit Droit à poser sur l'habilitation.
     * @return Nouveau bean Habilitation.
     */
    public static Habilitation makeHabilitationTest(final Long id,
                                                    final Droit droit) {
        final Habilitation hab = EssaiUtils.makeHabilitationTest(id, false, true);
        hab.setDroit(droit);
        return hab;
    }

    /**
     * Méthode de création d'une habilitation vide.
     * @param id Identifiant de l'habilitation.
     * @return Nouveau bean Habilitation.
     */
    public static Habilitation makeHabilitationTest(final Long id) {
        return EssaiUtils.makeHabilitationTest(id, false, true);
    }

    /**
     * Méthode de création d'une habilitation vide.
     * @param id Identifiant de l'habilitation.
     * @param selected Indique si l'habilitation doit être marquée comme
     * sélectionnée.
     * @param active Indique si l'habilitation est active.
     * @return Nouveau bean Habilitation.
     */
    public static Habilitation makeHabilitationTest(final Long id,
                                                    final boolean selected,
                                                    final boolean active) {
        final Habilitation hab = new Habilitation();
        hab.setId(id);
        hab.setSelected(selected);
        return hab;
    }
}
