package fr.pharma.elips.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TreeSet;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.pharma.eclipse.domain.enums.Droit;
import fr.pharma.eclipse.domain.enums.RolePersonne;
import fr.pharma.eclipse.domain.model.acteur.Investigateur;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.administratif.DetailAdministratif;
import fr.pharma.eclipse.domain.model.essai.detail.administratif.embedded.InfosAssurance;
import fr.pharma.eclipse.domain.model.essai.detail.administratif.embedded.InfosAutoriteCompetente;
import fr.pharma.eclipse.domain.model.essai.detail.administratif.embedded.InfosComiteProtection;
import fr.pharma.eclipse.domain.model.essai.detail.administratif.embedded.InfosConvention;
import fr.pharma.eclipse.domain.model.essai.detail.contact.DetailContacts;
import fr.pharma.eclipse.domain.model.essai.detail.dates.DetailDates;
import fr.pharma.eclipse.domain.model.essai.detail.faisabilite.DetailFaisabilite;
import fr.pharma.eclipse.domain.model.essai.detail.faisabilite.embedded.InfosConclusionFaisabilite;
import fr.pharma.eclipse.domain.model.essai.detail.pharma.DetailDonneesPharma;
import fr.pharma.eclipse.domain.model.essai.detail.pharma.embedded.InfosGenerales;
import fr.pharma.eclipse.domain.model.essai.detail.recherche.DetailRecherche;
import fr.pharma.eclipse.domain.model.habilitation.Habilitation;
import fr.pharma.eclipse.domain.model.localisation.Service;
import fr.pharma.eclipse.domain.model.user.UserSecurity;
import fr.pharma.eclipse.factory.habilitation.HabilitationFactory;
import fr.pharma.eclipse.service.acteur.PersonneService;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.service.localisation.ServiceService;
import fr.pharma.eclipse.utils.constants.EclipseConstants;
import fr.pharma.elips.controller.dto.EssaiImportDTO;
import fr.pharma.elips.controller.exception.ImportException;

@Controller
@RequestMapping("/import")
public class ImportWSController {

    /** Login du user Batch. */
    private static final String LOGIN_BATCH = "BatchImport";

    /** Service des essais. */
    @Autowired
    private EssaiService service;

    /** Service des services. */
    @Autowired
    private ServiceService serviceService;

    /** Fabrique d'objets Habilitation. */
    @Resource(name = "habilitationFactory")
    private HabilitationFactory habilitationFactory;

    /** Service de gestion des investigateurs. */
    @Resource(name = "investigateurService")
    private PersonneService<Investigateur> investService;

    public void setEssaiService(final EssaiService essaiService) {
        this.service = essaiService;
    }

    public void setInvestService(final PersonneService<Investigateur> investService) {
        this.investService = investService;
    }

	/**
     * Lister les clés des études.
     * 
     * @return Liste des clés des études
     * @throws ImportException
     *             Erreur lors du mapping
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public boolean updateEssai(@RequestBody final EssaiImportDTO beanExt)
                    throws ImportException {
        // Gestion de l'habilitation
        this.authentication();

        // Recherche de l'essai Elips
        final Essai essai = this.service.get(beanExt.getIdElips());
        if (essai == null) {
            throw new RuntimeException(
                            "Erreur : aucun essai trouvé pour l'id ["
                                            + beanExt.getIdElips() + "]");
        }

        // Mapping
        this.mappingEssai(essai, beanExt);

        // Enregistrement
        if (beanExt.isModif()) {
            this.service.save(essai);
        }

        return beanExt.isModif();
    }

    /**
     * Gestion d'une authentification.
     */
    private void authentication() {
        final RolePersonne role = RolePersonne.ADMIN;
        final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(
                        1);
        authorities.add(new SimpleGrantedAuthority(role.getLibelle()));
        final UserSecurity user = new UserSecurity(ImportWSController.LOGIN_BATCH, "pwd", null,
                        authorities, Long.valueOf(0), role, Boolean.TRUE);
        final Authentication authentication = new AnonymousAuthenticationToken(
                        "key", user, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

	/**
     * Mapping d'un essai.
     * 
     * @param essai
     *            Essai
     * @param beanExt
     *            Bean contenant les données à mettre à jour
     * @throws ImportException
     *             Erreur lors du mapping
     */
    public void mappingEssai(final Essai essai, final EssaiImportDTO beanExt)
                    throws ImportException {
        // Initialisation de l'essai
        this.initEssai(essai);

        // Mapping de l'essai
        beanExt.mapping(essai, "numInterne");
        beanExt.mapping(essai, "codePromoteur");
        beanExt.mapping(essai, "typePromoteur");
        beanExt.mapping(essai, "nom", "nomUsuel");
        beanExt.mapping(essai, "dateSignature", "dateSignatureConv");

        // Mapping du détail Recherche
        final DetailRecherche detailRecherche = essai.getDetailRecherche();
        beanExt.mapping(detailRecherche, "titreProtocole");
        beanExt.mapping(detailRecherche, "typeRecherche");
        beanExt.mapping(detailRecherche, "objetRecherche");
        beanExt.mapping(detailRecherche, "phaseRecherche");
        beanExt.mapping(detailRecherche, "natureRecherche");
        beanExt.mapping(detailRecherche, "thematique");

        // Mapping du détail des dates
        final DetailDates detailDates = essai.getDetailDates();
        beanExt.mapping(detailDates, "debutEtudePrev", "datePrevDebEtude");
        beanExt.mapping(detailDates, "finEtudePrev", "datePrevFinEtude");
        beanExt.mapping(detailDates, "finInclusionPrev", "datePrevFinInclusion");

        // Mapping du détail administratif
        final DetailAdministratif detailAdm = essai.getDetailAdministratif();
        // -- Infos autorité compétente
        final InfosAutoriteCompetente infosAC = detailAdm.getInfosAC();
        beanExt.mapping(infosAC, "accordAC", "autoriteComp");
        beanExt.mapping(infosAC, "nomAC", "nomAutoriteComp");
        beanExt.mapping(infosAC, "dateAC", "dateAccordAC");
        beanExt.mapping(infosAC, "numIdentAC", "eudract");
        // -- Infos CPP
        final InfosComiteProtection infosCPP = detailAdm.getInfosCPP();
        beanExt.mapping(infosCPP, "accordCPP", "cpp");
        beanExt.mapping(infosCPP, "nomCPP", "nomCpp");
        beanExt.mapping(infosCPP, "dateCPP", "dateAutorisationCpp");
        // -- Infos Convention
        final InfosConvention infosConv = detailAdm.getInfosConvention();
        beanExt.mapping(infosConv, "convSignee", "conventionSignee");
        // -- Infos Assurance
        final InfosAssurance infosAssurance = detailAdm.getInfosAssurance();
        beanExt.mapping(infosAssurance, "nomCompagnie", "nomCompagnieAssurance");
        beanExt.mapping(infosAssurance, "numeroContrat", "numContratAssurance");
        beanExt.mapping(infosAssurance, "numeroAvenant", "nomAvenantAssurance");

        // Mapping des détail pharma
        final DetailDonneesPharma detailPharma = essai.getDetailDonneesPharma();
        // -- Infos générales
        final InfosGenerales infosGen = detailPharma.getInfosGenerales();
        beanExt.mapping(infosGen, "nbPatientsPrevus", "nbPatientPrevuLocal");
        beanExt.mapping(infosGen, "dureeTotalePrevue", "dureeTotPrevue");
        beanExt.mapping(infosGen, "uniteDureeTotalePrevue",
                        "uniteDureeTotPrevue");
        beanExt.mapping(infosGen, "nbCentresPrevus", "nbCentrePrevu");
        beanExt.mapping(infosGen, "nbPatientsPrevusTotal",
                        "nbPatientPrevuTotal");
        beanExt.mapping(infosGen, "numeroCentre", "numCentre");

        // Mapping de l'étude de faisabilité
        final DetailFaisabilite detailFaisabilite = essai
                        .getDetailFaisabilite();
        final InfosConclusionFaisabilite infosConclusion = detailFaisabilite
                        .getInfosConclusion();
        beanExt.mapping(infosConclusion, "convSignee", "conventionSignee");

        // Habilitation pour l'investigateur
        if (this.habiliterInvestigateur(essai, beanExt.getIdInvestigateur())) {
            beanExt.setModif();
        }

        // Ajout du service
        if (this.ajouterService(essai, beanExt.getIdService())) {
            beanExt.setModif();
        }

        // -- Promoteur et service
        // obligatoires à la création => Pas de mise à jour possible
    }

    /**
     * Initialisation des objest fils de l'essai.
     *
     * @param essai
     *            Essai
     */
    private void initEssai(final Essai essai) {
        if (essai.getDetailRecherche() == null) {
            essai.setDetailRecherche(new DetailRecherche());
        }
        if (essai.getDetailDates() == null) {
            essai.setDetailDates(new DetailDates());
        }

        if (essai.getDetailAdministratif() == null) {
            essai.setDetailAdministratif(new DetailAdministratif());
        }
        final DetailAdministratif detailAdm = essai.getDetailAdministratif();
        if (detailAdm.getInfosAC() == null) {
            detailAdm.setInfosAC(new InfosAutoriteCompetente());
        }
        if (detailAdm.getInfosCPP() == null) {
            detailAdm.setInfosCPP(new InfosComiteProtection());
        }
        if (detailAdm.getInfosConvention() == null) {
            detailAdm.setInfosConvention(new InfosConvention());
        }
        if (detailAdm.getInfosAssurance() == null) {
            detailAdm.setInfosAssurance(new InfosAssurance());
        }

        if (essai.getDetailDonneesPharma() == null) {
            essai.setDetailDonneesPharma(new DetailDonneesPharma());
        }

        if (essai.getDetailContacts() == null) {
            essai.setDetailContacts(new DetailContacts());
        }
        if (essai.getDetailFaisabilite() == null) {
            essai.setDetailFaisabilite(new DetailFaisabilite());
        }
        final DetailFaisabilite detailFaisabilite = essai
                        .getDetailFaisabilite();
        if (detailFaisabilite.getInfosConclusion() == null) {
            detailFaisabilite
            .setInfosConclusion(new InfosConclusionFaisabilite());
        }
    }

	/**
     * Ajouter un service à l'essai (s'il n'est pas déjà présent).
     * 
     * @param essai
     *            Essai
     * @param idService
     *            Id du service
     * @return Vrai si le service a été ajouté
     */
    private boolean ajouterService(final Essai essai, final Long idService) {
        boolean modif = false;

        if (idService != null) {
            // Recherche si service déjà associé
            boolean exist = false;
            for (final Service service : essai.getServices()) {
                exist = exist || idService.equals(service.getId());
            }

            if (!exist) {
                // Recherche du service
                final Service service = this.serviceService.get(idService);
                // Ajout dans la liste
                essai.getServices().add(service);

                modif = true;
            }
        }

        return modif;
    }

	/**
     * Habiliter l'investigateur.
     * 
     * @param essai
     *            Essai
     * @param idInvest
     *            Id de l'investigateur
     * @return Vrai si l'habilitation a été ajoutée
     */
    private boolean habiliterInvestigateur(final Essai essai, final Long idInvest) {
        final DetailContacts detailContacts = essai.getDetailContacts();
        boolean modif = false;
        if (idInvest != null) {
            if (detailContacts.getHabilitations() == null) {
                detailContacts.setHabilitations(new TreeSet<Habilitation>());
            }

            // Recherche si investigateur déjà habilité
            boolean exist = false;
            boolean existPrincipal = false;
            for (final Habilitation hab : detailContacts.getHabilitations()) {
                exist = exist || idInvest.equals(hab.getPersonne().getId());
                existPrincipal = existPrincipal
                                || (Droit.INVESTIGATEUR_PRINCIPAL
                                                .equals(hab.getDroit()));
            }

            // Sinon, création d'une habilitation
            if (!exist) {
                // Récupération de l'investigateur
                final Investigateur invest = this.investService.get(idInvest);

                // Création de l'habilitation
                final Habilitation habilitation = new Habilitation();
                habilitation.setActive(true);
                habilitation.setDesactivable(true);
                habilitation.setDateCreation(Calendar
                                .getInstance(EclipseConstants.LOCALE));
                habilitation.setAuteurCreation(ImportWSController.LOGIN_BATCH);
                habilitation.setPersonne(invest);
                habilitation.setDetailContacts(detailContacts);
                if (existPrincipal) {
                    // S'il y a déjà un investigateur principal
                    // => Co-investigateur
                    habilitation.setDroit(Droit.INVESTIGATEUR_CO);
                } else {
                    // Sinon => investigateur principal
                    habilitation.setDroit(Droit.INVESTIGATEUR_PRINCIPAL);
                }

                // Ajout dans l'essai
                detailContacts.getHabilitations().add(habilitation);
                modif = true;
            }
        }

        return modif;
    }
}
