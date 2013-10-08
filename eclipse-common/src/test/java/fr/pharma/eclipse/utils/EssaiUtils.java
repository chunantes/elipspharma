package fr.pharma.eclipse.utils;

import java.util.Calendar;

import org.mockito.Mockito;

import fr.pharma.eclipse.domain.enums.Droit;
import fr.pharma.eclipse.domain.enums.EtatEssai;
import fr.pharma.eclipse.domain.enums.TypePersonne;
import fr.pharma.eclipse.domain.enums.TypePromoteur;
import fr.pharma.eclipse.domain.model.acteur.Investigateur;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.acteur.Promoteur;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.administratif.DetailAdministratif;
import fr.pharma.eclipse.domain.model.essai.detail.autresdocs.DetailAutresDocuments;
import fr.pharma.eclipse.domain.model.essai.detail.contact.DetailContacts;
import fr.pharma.eclipse.domain.model.essai.detail.dates.DetailDates;
import fr.pharma.eclipse.domain.model.essai.detail.design.DetailDesign;
import fr.pharma.eclipse.domain.model.essai.detail.faisabilite.CommentaireEssaiFaisabilite;
import fr.pharma.eclipse.domain.model.essai.detail.faisabilite.DetailFaisabilite;
import fr.pharma.eclipse.domain.model.essai.detail.faisabilite.embedded.InfosEtudeFaisabilite;
import fr.pharma.eclipse.domain.model.essai.detail.pharma.DetailDonneesPharma;
import fr.pharma.eclipse.domain.model.essai.detail.produit.DetailProduit;
import fr.pharma.eclipse.domain.model.essai.detail.recherche.CommentaireEssaiRecherche;
import fr.pharma.eclipse.domain.model.essai.detail.recherche.DetailRecherche;
import fr.pharma.eclipse.domain.model.habilitation.Habilitation;
import fr.pharma.eclipse.domain.model.localisation.Service;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;

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
        essai.setNom("nom");
        essai.setNumInterne("num");
        final Pharmacie pharmacie = new Pharmacie();
        essai.setCodePromoteur("code");
        pharmacie.setNom("pharmacie");
        essai.setPharmaciePrincipale(pharmacie);
        essai.setEtat(EtatEssai.ARCHIVE);
        essai.setPromoteur(new Promoteur());
        final Service service = new Service();
        service.setNom("nomService");
        essai.getServices().add(service);
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
        final DetailAdministratif detailAdministratif = new DetailAdministratif();
        detailAdministratif.setId(idDetail);
        essai.setDetailAdministratif(detailAdministratif);
        final DetailDonneesPharma detailDonneesPharma = new DetailDonneesPharma();
        detailDonneesPharma.setId(idDetail);
        essai.setDetailDonneesPharma(detailDonneesPharma);
        final DetailDesign detailDesign = new DetailDesign();
        detailDesign.setId(idDetail);
        essai.setDetailDesign(detailDesign);
        final DetailProduit detailProduit = new DetailProduit();
        detailProduit.setId(idDetail);
        essai.setDetailProduit(detailProduit);
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
     * Méthode de création d'un objet InfosEtudeFaisabilite où toutes les
     * propriétés sont non nulles.
     * @param defautBoolean Booléen que l'on doit affecter aux propriétés
     * booléennes du bean.<br>
     * Les listes sont initialisées avec un élément mocké.
     * @return Un InfosEtudeFaisabilite complètement initialisé.
     */
    public static InfosEtudeFaisabilite makeCompleteInfoEtude(final Boolean defautBoolean) {
        final InfosEtudeFaisabilite bean = new InfosEtudeFaisabilite();
        bean.setAccordPharmaCentrale(defautBoolean);
        bean.setAchatsProduitsPUI(defautBoolean);
        bean.setCircuitDistribDefini(defautBoolean);
        bean.getCommentaires().add(EssaiUtils.mockCommEssaiFaisab());
        bean.getCommentairesAchatsPUI().add(EssaiUtils.mockCommEssaiFaisab());
        bean.getCommentairesDistribAutresPharma().add(EssaiUtils.mockCommEssaiFaisab());
        bean.setConditionnementPduits(defautBoolean);
        bean.setDemandeImportation(defautBoolean);
        bean.setDispensationPossEnGarde(defautBoolean);
        bean.setDistribAutresPharmaPossible(defautBoolean);
        bean.setDonneesStabilite(defautBoolean);
        bean.setEtiquetagesPduits(defautBoolean);
        bean.setGestionAveugle(defautBoolean);
        bean.setPreparations(defautBoolean);
        bean.setPrestaParticulieresPharmacie(defautBoolean);
        bean.setRandomisationParPharma(defautBoolean);
        bean.setRandomisationPossEnGarde(defautBoolean);
        bean.setReconstitutions(defautBoolean);
        bean.setRefProduitsCHU(defautBoolean);
        bean.getServicesImputation().add(Mockito.mock(Service.class));
        bean.setSocTransportDefinie(defautBoolean);
        bean.setSuiviStocksParPharmacie(defautBoolean);
        bean.setSuiviTempNecessairePdtTransp(defautBoolean);

        return bean;
    }

    /**
     * Retourne un mock de CommentaireEssaiFaisabilite.
     * @return Mock de CommentaireEssaiFaisabilite.
     */
    public static CommentaireEssaiFaisabilite mockCommEssaiFaisab() {
        return Mockito.mock(CommentaireEssaiFaisabilite.class);
    }

    /**
     * Méthode de création d'une habilitation vide.
     * @param id Identifiant de l'habilitation.
     * @param active Habilitation active ou non.
     * @param droit Droit à poser sur l'habilitation.
     * @param nomPersonne Nom de la personne.
     * @param typePersonne Type de la personne.
     * @return Nouveau bean Habilitation (personne mockée!).
     */
    public static Habilitation makeHabilitationTest(final Long id,
                                                    final boolean active,
                                                    final Droit droit,
                                                    final String nomPersonne,
                                                    final TypePersonne typePersonne) {
        final Personne personne = Mockito.mock(Personne.class);
        Mockito.when(personne.getNom()).thenReturn(nomPersonne);
        Mockito.when(personne.getType()).thenReturn(typePersonne);
        return EssaiUtils.makeHabilitationTest(id, active, droit, personne);
    }

    /**
     * Méthode de création d'une habilitation.
     * @param id Identifiant de l'habilitation.
     * @param active Habilitation active ou non.
     * @param droit Droit à poser sur l'habilitation.
     * @param personne Personne associée à l'habilitation.
     * @return Nouveau bean Habilitation (personne mockée!).
     */
    public static Habilitation makeHabilitationTest(final Long id,
                                                    final boolean active,
                                                    final Droit droit,
                                                    final Personne personne) {
        final Habilitation hab = EssaiUtils.makeHabilitationTest(id, false, active);
        hab.setDroit(droit);
        hab.setPersonne(personne);
        return hab;
    }

    /**
     * Méthode de création d'une habilitation d'investigateur principal vide.
     * @param id Identifiant de l'habilitation.
     * @param active Habilitation active ou non.
     * @param nomPersonne Nom de la personne.
     * @return Nouveau bean Habilitation (personne mockée!).
     */
    public static Habilitation makeHabilitationInvPrincipalTest(final Long id,
                                                                final boolean active,
                                                                final String nomPersonne) {
        final Habilitation hab = EssaiUtils.makeHabilitationTest(id, false, active);
        hab.setDroit(Droit.INVESTIGATEUR_PRINCIPAL);
        final Investigateur personne = Mockito.mock(Investigateur.class);
        Mockito.when(personne.getNom()).thenReturn(nomPersonne);
        Mockito.when(personne.getType()).thenReturn(TypePersonne.INVESTIGATEUR);
        hab.setPersonne(personne);
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
        hab.setActive(active);
        return hab;
    }
}
