package fr.pharma.eclipse.service.stock.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import fr.pharma.eclipse.domain.enums.evenement.TypeEvenement;
import fr.pharma.eclipse.domain.enums.jasper.TypeRapportJasper;
import fr.pharma.eclipse.domain.enums.stock.RaisonSortie;
import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.dispensation.Dispensation;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.evenement.Evenement;
import fr.pharma.eclipse.domain.model.stock.AutreSortie;
import fr.pharma.eclipse.domain.model.stock.CessionPui;
import fr.pharma.eclipse.domain.model.stock.Destruction;
import fr.pharma.eclipse.domain.model.stock.DispensationProduit;
import fr.pharma.eclipse.domain.model.stock.LigneStock;
import fr.pharma.eclipse.domain.model.stock.MvtStock;
import fr.pharma.eclipse.domain.model.stock.PreparationSortie;
import fr.pharma.eclipse.domain.model.stock.ResultSortie;
import fr.pharma.eclipse.domain.model.stock.RetourPromoteur;
import fr.pharma.eclipse.domain.model.stock.Sortie;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.factory.evenement.EvenementFactory;
import fr.pharma.eclipse.factory.stock.DispensationProduitFactory;
import fr.pharma.eclipse.factory.stock.MvtStockFactory;
import fr.pharma.eclipse.jasper.document.DocumentMakerDictionary;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.service.evenement.EvenementService;
import fr.pharma.eclipse.service.stock.MvtStockService;
import fr.pharma.eclipse.service.stock.SortieService;
import fr.pharma.eclipse.service.user.UserService;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe d'implémentation du service de gestion des sorties de stock.
 
 * @version $Revision$ $Date$
 */
public class SortieServiceImpl
    implements SortieService, Serializable
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -8628634686521982461L;

    /**
     * Service de gestion des retours promoteurs.
     */
    @Resource(name = "retourPromoteurService")
    private MvtStockService<RetourPromoteur> retourService;

    /**
     * Factory des retours promoteurs.
     */
    @Resource(name = "retourPromoteurFactory")
    private MvtStockFactory<RetourPromoteur> retourFactory;

    /**
     * Service de gestion des cessions Pui.
     */
    @Resource(name = "cessionPuiService")
    private MvtStockService<CessionPui> cessionPuiService;

    /**
     * Factory des cessions Pui.
     */
    @Resource(name = "cessionPuiFactory")
    private MvtStockFactory<CessionPui> cessionPuiFactory;

    /**
     * Service de gestion des destructions.
     */
    @Resource(name = "destructionService")
    private MvtStockService<Destruction> destructionService;

    /**
     * Service de gestion des destructions.
     */
    @Resource(name = "preparationSortieService")
    private MvtStockService<PreparationSortie> preparationSortieService;

    /**
     * Factory des destructions.
     */
    @Resource(name = "destructionFactory")
    private MvtStockFactory<Destruction> destructionFactory;

    /**
     * Service de gestion des autres sorties.
     */
    @Resource(name = "autreSortieService")
    private MvtStockService<AutreSortie> autreSortieService;

    /**
     * Service de gestion des dispensations.
     */
    @Resource(name = "dispensationService")
    private GenericService<Dispensation> dispensationService;

    /**
     * Factory des autres sorties.
     */
    @Resource(name = "autreSortieFactory")
    private MvtStockFactory<AutreSortie> autreSortieFactory;

    /**
     * Factory des preparations sorties
     */
    @Resource(name = "preparationSortieFactory")
    private MvtStockFactory<PreparationSortie> preparationSortieFactory;

    /**
     * Factory des dispensationProduits.
     */
    @Resource(name = "dispensationProduitFactory")
    private DispensationProduitFactory dispensationProduitFactory;

    /**
     * Factory d'évènement.
     */
    @Resource(name = "evenementFactory")
    private EvenementFactory evenementFactory;

    /**
     * Service Evenement.
     */
    @Resource(name = "evenementService")
    private EvenementService evenementService;

    /**
     * Service de gestion des utilisateurs.
     */
    @Resource(name = "userService")
    private UserService userService;

    /**
     * Dictionnaire de classe en charge de créer les documents liés.
     */
    @Resource(name = "documentMakerDictionary")
    private DocumentMakerDictionary documentMakerDictionary;

    /**
     * {@inheritDoc}
     */
    @Override
    public ResultSortie save(final RaisonSortie raisonSortie,
                             final String commentaireRaison,
                             final TypeMvtStock type,
                             final String nomSocieteTransport,
                             final String referenceEnvoi,
                             final String commentaire,
                             final Pharmacie pharmacieDest,
                             final List<Sortie> sorties,
                             final Dispensation dispensation)
    {
        final ResultSortie resultSortie = new ResultSortie();
        if (TypeMvtStock.RETOUR_PROMOTEUR.equals(type))
        {
            this.handleRetourPromoteur(raisonSortie,
                                       commentaireRaison,
                                       commentaire,
                                       nomSocieteTransport,
                                       referenceEnvoi,
                                       sorties,
                                       resultSortie);
        }
        else if (TypeMvtStock.CESSION_PUI.equals(type))
        {
            this.handleCessionPui(raisonSortie,
                                  commentaireRaison,
                                  commentaire,
                                  sorties,
                                  resultSortie,
                                  pharmacieDest);
        }
        else if (TypeMvtStock.DESTRUCTION.equals(type))
        {
            this.handleDestruction(raisonSortie,
                                   commentaireRaison,
                                   commentaire,
                                   sorties,
                                   resultSortie);
        }
        else if (TypeMvtStock.AUTRE_SORTIE.equals(type))
        {
            this.handleAutreSortie(raisonSortie,
                                   commentaireRaison,
                                   commentaire,
                                   sorties,
                                   resultSortie);
        }
        else if (TypeMvtStock.DISPENSATION.equals(type))
        {
            this.handleDispensation(commentaire,
                                    sorties,
                                    resultSortie,
                                    dispensation);
        }
        else if (TypeMvtStock.PREPARATION_SORTIE.equals(type))
        {
            this.handlePreparationSortie(commentaire,
                                         sorties,
                                         resultSortie,
                                         dispensation);
        }

        // Méthode en charge de compléter les informations du résultat de sortie
        this.completeResult(resultSortie);

        return resultSortie;
    }

    /**
     * Méthode en charge de compléter les informations de récapitulatif de la sortie.
     * @param resultSortie Récapitulatif à compléter.
     */
    protected void completeResult(final ResultSortie resultSortie)
    {
        if (resultSortie.getSorties().size() > 0)
        {
            final Sortie sortie = resultSortie.getSorties().get(0);
            final MvtStock mvt = sortie.getMvtSortie();
            resultSortie.setEssai(mvt.getEssai());
            resultSortie.setPromoteur(mvt.getEssai().getPromoteur());
            resultSortie.setPharmacie(mvt.getPharmacie());
            resultSortie.setDateSortie(Calendar.getInstance(EclipseConstants.LOCALE));
            resultSortie.setPersonne(this.getUserService().getPersonne());
        }
    }

    /**
     * Méthode en charge de gérer une saisie de sortie de type Autre.
     * @param commentaire Commentaire.
     * @param sorties Liste des sorties.
     * @param resultSortie Résultat de la sortie à compléter.
     */
    private void handleAutreSortie(final RaisonSortie raisonSortie,
                                   final String commentaireRaison,
                                   final String commentaire,
                                   final List<Sortie> sorties,
                                   final ResultSortie resultSortie)
    {
        final List<AutreSortie> autresSorties = new ArrayList<AutreSortie>();
        for (final Sortie sortie : sorties)
        {
            final List<LigneStock> lignesStock = sortie.getLignesStockCompletees();
            for (final LigneStock ligneStock : lignesStock)
            {
                final AutreSortie autreSortie = this.autreSortieFactory.getInitializedObject();
                autreSortie.setCommentaire(commentaire);
                autreSortie.setEssai(ligneStock.getEssai());
                autreSortie.setPharmacie(ligneStock.getPharmacie());
                autreSortie.setDatePeremption(ligneStock.getDatePeremption());
                autreSortie.setProduit(ligneStock.getProduit());
                autreSortie.setRaisonSortie(raisonSortie);
                autreSortie.setCommentaireRaison(commentaireRaison);
                autreSortie.setConditionnement(ligneStock.getConditionnement());
                autreSortie.setNumLot(ligneStock.getNumLot());
                autreSortie.setNumTraitement(ligneStock.getNumTraitement());
                autreSortie.setQuantite(ligneStock.getQteASortir());
                autreSortie.setApproApprouve(ligneStock.getApproApprouve());
                autresSorties.add(autreSortie);
            }
            if (!lignesStock.isEmpty())
            {
                resultSortie.getSorties().add(sortie);
            }
        }
        // Sauvegarde des autres sorties
        resultSortie.setMvts(this.autreSortieService.saveAll(autresSorties));
    }

    /**
     * Méthode en charge de gérer une saisie de sortie de type Destruction.
     * @param commentaire Commentaire.
     * @param sorties Liste des sorties.
     * @param resultSortie Résultat de la sortie à compléter.
     */
    private void handleDestruction(final RaisonSortie raisonSortie,
                                   final String commentaireRaison,
                                   final String commentaire,
                                   final List<Sortie> sorties,
                                   final ResultSortie resultSortie)
    {
        final List<Destruction> destructions = new ArrayList<Destruction>();
        resultSortie.setRaisonSortie(raisonSortie);
        for (final Sortie sortie : sorties)
        {
            final List<LigneStock> lignesStock = sortie.getLignesStockCompletees();
            for (final LigneStock ligneStock : lignesStock)
            {
                final Destruction destruction = this.destructionFactory.getInitializedObject();
                destruction.setCommentaire(commentaire);
                destruction.setEssai(ligneStock.getEssai());
                destruction.setPharmacie(ligneStock.getPharmacie());
                destruction.setProduit(ligneStock.getProduit());
                destruction.setDatePeremption(ligneStock.getDatePeremption());
                destruction.setConditionnement(ligneStock.getConditionnement());
                destruction.setNumLot(ligneStock.getNumLot());
                destruction.setRaisonSortie(raisonSortie);
                destruction.setCommentaireRaison(commentaireRaison);
                destruction.setNumTraitement(ligneStock.getNumTraitement());
                destruction.setQuantite(ligneStock.getQteASortir());
                destruction.setApproApprouve(ligneStock.getApproApprouve());
                destructions.add(destruction);
                resultSortie.setEssai(ligneStock.getEssai());
            }
            if (!lignesStock.isEmpty())
            {
                resultSortie.getSorties().add(sortie);
            }
        }
        // Sauvegarde des destructions
        resultSortie.setMvts(this.destructionService.saveAll(destructions));
        this.documentMakerDictionary.make(TypeRapportJasper.CERTIFICAT_DESTRUCTION,
                                          resultSortie);
    }

    /**
     * Méthode en charge de gérer une saisie de sortie de type CessionPui.
     * @param commentaire Commentaire.
     * @param sorties Liste des sorties.
     * @param resultSortie Résultat de la sortie à compléter.
     */
    private void handleCessionPui(final RaisonSortie raisonSortie,
                                  final String commentaireRaison,
                                  final String commentaire,
                                  final List<Sortie> sorties,
                                  final ResultSortie resultSortie,
                                  final Pharmacie pharmacieDest)
    {
        final List<CessionPui> cessions = new ArrayList<CessionPui>();
        Essai essai = null;
        for (final Sortie sortie : sorties)
        {
            final List<LigneStock> lignesStock = sortie.getLignesStockCompletees();
            for (final LigneStock ligneStock : lignesStock)
            {
                essai = ligneStock.getEssai();
                final CessionPui cessionPui = this.cessionPuiFactory.getInitializedObject();
                cessionPui.setCommentaire(commentaire);
                cessionPui.setEssai(ligneStock.getEssai());
                cessionPui.setPharmacie(ligneStock.getPharmacie());
                cessionPui.setDatePeremption(ligneStock.getDatePeremption());
                cessionPui.setProduit(ligneStock.getProduit());
                cessionPui.setConditionnement(ligneStock.getConditionnement());
                cessionPui.setNumLot(ligneStock.getNumLot());
                cessionPui.setRaisonSortie(raisonSortie);
                cessionPui.setCommentaireRaison(commentaireRaison);
                cessionPui.setNumTraitement(ligneStock.getNumTraitement());
                cessionPui.setQuantite(ligneStock.getQteASortir());
                cessionPui.setPharmacieDest(pharmacieDest);
                cessionPui.setApproApprouve(ligneStock.getApproApprouve());
                cessions.add(cessionPui);
            }
            if (!lignesStock.isEmpty())
            {
                resultSortie.getSorties().add(sortie);
            }
        }
        // Sauvegarde des cessions
        resultSortie.setMvts(this.cessionPuiService.saveAll(cessions));

        if (essai != null)
        {
            // création de l'évènement lié.
            final Evenement evenement = this.evenementFactory.getInitializedObject();
            evenement.setTypeEvenement(TypeEvenement.CESSION_PUI);
            final Calendar c = Calendar.getInstance(EclipseConstants.LOCALE);
            evenement.setDateDebut(c);
            evenement.setDateFin(c);
            evenement.setJournee(true);
            evenement.setLibelle(TypeEvenement.CESSION_PUI.getLibelle());
            evenement.setEssai(sorties.get(0).getLignesStock().get(0).getEssai());
            evenement.setDestinataire(pharmacieDest.getEtablissement().getNom()
                                      + EclipseConstants.SPACE
                                      + EclipseConstants.DASH
                                      + EclipseConstants.SPACE
                                      + pharmacieDest.getNom());
            this.evenementService.save(evenement);
        }

    }
    /**
     * Méthode en charge de gérer une saisie de sortie de type RetourPromoteur.
     * @param raisonSortie Raison de la sortie.
     * @param Commentaire sur la raison de la sortie.
     * @param commentaire Commentaire.
     * @param nomSocieteTransport Nom de la société de transport.
     * @param referenceEnvoi Référence de l'envoi.
     * @param sorties Liste de sorties.
     * @param resultSortie Résultat de la sortie à compléter.
     */
    private void handleRetourPromoteur(final RaisonSortie raisonSortie,
                                       final String commentaireRaison,
                                       final String commentaire,
                                       final String nomSocieteTransport,
                                       final String referenceEnvoi,
                                       final List<Sortie> sorties,
                                       final ResultSortie resultSortie)
    {
        final List<RetourPromoteur> retours = new ArrayList<RetourPromoteur>();

        resultSortie.setRaisonSortie(raisonSortie);
        for (final Sortie sortie : sorties)
        {
            final List<LigneStock> lignesStock = sortie.getLignesStockCompletees();
            for (final LigneStock ligneStock : lignesStock)
            {
                final RetourPromoteur retourPromoteur = this.retourFactory.getInitializedObject();
                retourPromoteur.setCommentaire(commentaire);
                retourPromoteur.setNomSocieteTransport(nomSocieteTransport);
                retourPromoteur.setReferenceEnvoi(referenceEnvoi);
                retourPromoteur.setEssai(ligneStock.getEssai());
                retourPromoteur.setPharmacie(ligneStock.getPharmacie());
                retourPromoteur.setDatePeremption(ligneStock.getDatePeremption());
                retourPromoteur.setProduit(ligneStock.getProduit());
                retourPromoteur.setConditionnement(ligneStock.getConditionnement());
                retourPromoteur.setRaisonSortie(raisonSortie);
                retourPromoteur.setCommentaireRaison(commentaireRaison);
                retourPromoteur.setNumLot(ligneStock.getNumLot());
                retourPromoteur.setNumTraitement(ligneStock.getNumTraitement());
                retourPromoteur.setQuantite(ligneStock.getQteASortir());
                retourPromoteur.setApproApprouve(ligneStock.getApproApprouve());
                retours.add(retourPromoteur);
                resultSortie.setEssai(ligneStock.getEssai());
            }
            if (!lignesStock.isEmpty())
            {
                resultSortie.getSorties().add(sortie);
            }
        }
        // Sauvegarde des retours promoteurs
        resultSortie.setMvts(this.retourService.saveAll(retours));
        this.documentMakerDictionary.make(TypeRapportJasper.CERTIFICAT_RETOUR,
                                          resultSortie);
    }
    /**
     * Méthode en charge de gérer une saisie de sortie de type DispensationProduit.
     * @param commentaire Commentaire.
     * @param sorties Liste de sorties.
     * @param resultSortie Résultat de la sortie à compléter.
     * @param dispensation Bean métier contenant les informations de dispensation.
     */
    private void handleDispensation(final String commentaire,
                                    final List<Sortie> sorties,
                                    final ResultSortie resultSortie,
                                    final Dispensation dispensation)
    {
        for (final Sortie sortie : sorties)
        {
            final List<LigneStock> lignesStock = sortie.getLignesStockCompletees();
            for (final LigneStock ligneStock : lignesStock)
            {
                final DispensationProduit dispensationProduit =
                    this.dispensationProduitFactory.getInitializedObject();
                dispensationProduit.setProduitPrescrit(((DispensationProduit) sortie
                        .getMvtSortie()).getProduitPrescrit());
                dispensationProduit.setDispensation(dispensation);
                dispensationProduit.setDatePeremption(ligneStock.getDatePeremption());
                dispensationProduit.setEssai(ligneStock.getEssai());
                dispensationProduit.setPharmacie(ligneStock.getPharmacie());
                dispensationProduit.setProduit(ligneStock.getProduit());
                dispensationProduit.setConditionnement(ligneStock.getConditionnement());
                dispensationProduit.setNumLot(ligneStock.getNumLot());
                dispensationProduit.setNumTraitement(ligneStock.getNumTraitement());
                dispensationProduit.setQuantite(ligneStock.getQteASortir());
                dispensationProduit.setApproApprouve(ligneStock.getApproApprouve());
                dispensationProduit
                        .setDateCreation(Calendar.getInstance(EclipseConstants.LOCALE));
                dispensationProduit.setPersonne(this.userService.getPersonne());
                dispensation.getDispensationsProduit().add(dispensationProduit);
            }
            if (!lignesStock.isEmpty())
            {
                resultSortie.getSorties().add(sortie);
            }
        }
    }

    /**
     * Méthode en charge de gérer une saisie de sortie de type Preparation Sortie.
     * @param commentaire Commentaire.
     * @param sorties Liste de sorties.
     * @param resultSortie Résultat de la sortie à compléter.
     * @param dispensation Bean métier contenant les informations de dispensation.
     */
    private void handlePreparationSortie(final String commentaire,
                                         final List<Sortie> sorties,
                                         final ResultSortie resultSortie,
                                         final Dispensation dispensation)
    {
        final List<PreparationSortie> preparations = new ArrayList<PreparationSortie>();
        for (final Sortie sortie : sorties)
        {
            final List<LigneStock> lignesStock = sortie.getLignesStockCompletees();
            for (final LigneStock ligneStock : lignesStock)
            {
                final PreparationSortie preparation =
                    this.preparationSortieFactory.getInitializedObject();
                preparation.setDatePeremption(ligneStock.getDatePeremption());
                preparation.setEssai(ligneStock.getEssai());
                preparation.setPharmacie(ligneStock.getPharmacie());
                preparation.setProduit(ligneStock.getProduit());
                preparation.setConditionnement(ligneStock.getConditionnement());
                preparation.setNumLot(ligneStock.getNumLot());
                preparation.setNumTraitement(ligneStock.getNumTraitement());
                preparation.setQuantite(ligneStock.getQteASortir());
                preparation.setApproApprouve(ligneStock.getApproApprouve());
                preparation.setDateCreation(Calendar.getInstance(EclipseConstants.LOCALE));
                preparation.setPersonne(this.userService.getPersonne());
                preparations.add(preparation);
            }
            if (!lignesStock.isEmpty())
            {
                resultSortie.getSorties().add(sortie);
            }
        }
        // Sauvegarde des cessions
        resultSortie.setMvts(this.preparationSortieService.saveAll(preparations));
    }

    /**
     * Setter pour cessionPuiService.
     * @param cessionPuiService Le cessionPuiService à écrire.
     */
    public void setCessionPuiService(final MvtStockService<CessionPui> cessionPuiService)
    {
        this.cessionPuiService = cessionPuiService;
    }

    /**
     * Setter pour retourService.
     * @param retourService Le retourService à écrire.
     */
    public void setRetourService(final MvtStockService<RetourPromoteur> retourService)
    {
        this.retourService = retourService;
    }

    /**
     * Setter pour retourFactory.
     * @param retourFactory Le retourFactory à écrire.
     */
    public void setRetourFactory(final MvtStockFactory<RetourPromoteur> retourFactory)
    {
        this.retourFactory = retourFactory;
    }

    /**
     * Setter pour destructionService.
     * @param destructionService Le destructionService à écrire.
     */
    public void setDestructionService(final MvtStockService<Destruction> destructionService)
    {
        this.destructionService = destructionService;
    }

    /**
     * Setter pour destructionFactory.
     * @param destructionFactory Le destructionFactory à écrire.
     */
    public void setDestructionFactory(final MvtStockFactory<Destruction> destructionFactory)
    {
        this.destructionFactory = destructionFactory;
    }

    /**
     * Setter pour autreSortieFactory.
     * @param autreSortieFactory Le autreSortieFactory à écrire.
     */
    public void setAutreSortieFactory(final MvtStockFactory<AutreSortie> autreSortieFactory)
    {
        this.autreSortieFactory = autreSortieFactory;
    }

    /**
     * Setter pour autreSortieService.
     * @param autreSortieService Le autreSortieService à écrire.
     */
    public void setAutreSortieService(final MvtStockService<AutreSortie> autreSortieService)
    {
        this.autreSortieService = autreSortieService;
    }

    /**
     * Getter pour userService.
     * @return Le userService
     */
    public UserService getUserService()
    {
        return this.userService;
    }

    /**
     * Setter pour userService.
     * @param userService Le userService à écrire.
     */
    public void setUserService(final UserService userService)
    {
        this.userService = userService;
    }

    /**
     * Setter pour cessionPuiFactory.
     * @param cessionPuiFactory Le cessionPuiFactory à écrire.
     */
    public void setCessionPuiFactory(final MvtStockFactory<CessionPui> cessionPuiFactory)
    {
        this.cessionPuiFactory = cessionPuiFactory;
    }

    /**
     * Setter pour dispensationService.
     * @param dispensationService le dispensationService à écrire.
     */
    public void setDispensationService(final GenericService<Dispensation> dispensationService)
    {
        this.dispensationService = dispensationService;
    }

    /**
     * Setter pour dispensationProduitFactory.
     * @param dispensationProduitFactory le dispensationProduitFactory à écrire.
     */
    public void setDispensationProduitFactory(final DispensationProduitFactory dispensationProduitFactory)
    {
        this.dispensationProduitFactory = dispensationProduitFactory;
    }

    /**
     * Setter pour evenementFactory.
     * @param evenementFactory Le evenementFactory à écrire.
     */
    public void setEvenementFactory(final EvenementFactory evenementFactory)
    {
        this.evenementFactory = evenementFactory;
    }

    /**
     * Setter pour evenementService.
     * @param evenementService Le evenementService à écrire.
     */
    public void setEvenementService(final EvenementService evenementService)
    {
        this.evenementService = evenementService;
    }

    /**
     * Setter pour documentMakerDictionary.
     * @param documentMakerDictionary Le documentMakerDictionary à écrire.
     */
    public void setDocumentMakerDictionary(final DocumentMakerDictionary documentMakerDictionary)
    {
        this.documentMakerDictionary = documentMakerDictionary;
    }

    /**
     * Setter pour preparationSortieFactory.
     * @param preparationSortieFactory Le preparationSortieFactory à écrire.
     */
    public void setPreparationSortieFactory(final MvtStockFactory<PreparationSortie> preparationSortieFactory)
    {
        this.preparationSortieFactory = preparationSortieFactory;
    }

    /**
     * Setter pour preparationSortieService.
     * @param preparationSortieService Le preparationSortieService à écrire.
     */
    public void setPreparationSortieService(final MvtStockService<PreparationSortie> preparationSortieService)
    {
        this.preparationSortieService = preparationSortieService;
    }

}
