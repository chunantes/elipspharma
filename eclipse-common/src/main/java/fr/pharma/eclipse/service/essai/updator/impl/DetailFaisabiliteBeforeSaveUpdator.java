package fr.pharma.eclipse.service.essai.updator.impl;

import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.faisabilite.embedded.InfosEtudeFaisabilite;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.service.essai.updator.EssaiBeforeSaveUpdator;
import fr.pharma.eclipse.service.helper.common.BeanPropertyReinitializer;

/**
 * Implémentation de l'interface {@link EssaiBeforeSaveUpdator} spécifique à la partie étude de
 * faisabilité de l'essai.
 
 * @version $Revision$ $Date$
 */
public class DetailFaisabiliteBeforeSaveUpdator
    implements EssaiBeforeSaveUpdator
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -2571472507860743084L;

    /**
     * Helper pour la réinitialisation des propriétés.
     */
    private BeanPropertyReinitializer<InfosEtudeFaisabilite> reinitializer;

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final Essai essai,
                       final EssaiService service)
    {
        final InfosEtudeFaisabilite infosEtude = essai.getDetailFaisabilite().getInfosEtude();
        // Réinitialisation éventuelle des données en fonction des blocs
        // affichés à l'écran (en application des règles de gestion décrites
        // dans le cahier des charges).
        if (infosEtude.getPrestaParticulieresPharmacie() != Boolean.TRUE)
        {
            this.handleDetailGlobal(infosEtude);
        }

        if (infosEtude.getAchatsProduitsPUI() != Boolean.TRUE)
        {
            this.handleBlocAchatsPduits(infosEtude);
        }

        if (infosEtude.getRandomisationParPharma() != Boolean.TRUE)
        {
            this.reinitializer.resetPropertyToNull(infosEtude,
                                                   "randomisationPossEnGarde");
        }

        if (infosEtude.getDistribAutresPharmaPossible() != Boolean.TRUE)
        {
            this.handleBlocDistribution(infosEtude);
        }

    }
    /**
     * Traitements des informations dépendant de
     * InfosEtudeFaisabilite.distribAutresPharmaPossible.
     * @param infosEtude Informations de l'étude.
     */
    private void handleBlocDistribution(final InfosEtudeFaisabilite infosEtude)
    {
        this.reinitializer.resetPropertyToNull(infosEtude,
                                               "circuitDistribDefini");
        this.reinitializer.resetPropertyToNull(infosEtude,
                                               "socTransportDefinie");
        this.reinitializer.resetPropertyToNull(infosEtude,
                                               "suiviTempNecessairePdtTransp");
        this.reinitializer.resetPropertyToNull(infosEtude,
                                               "suiviStocksParPharmacie");
        this.reinitializer.resetCollection(infosEtude,
                                           "commentairesDistribAutresPharma");
    }

    /**
     * Traitements des informations dépendant de InfosEtudeFaisabilite.achatsProduitsPUI.
     * @param infosEtude Informations de l'étude.
     */
    private void handleBlocAchatsPduits(final InfosEtudeFaisabilite infosEtude)
    {
        this.reinitializer.resetPropertyToNull(infosEtude,
                                               "refProduitsCHU");
        this.reinitializer.resetPropertyToNull(infosEtude,
                                               "accordPharmaCentrale");
        this.reinitializer.resetCollection(infosEtude,
                                           "servicesImputation");
        this.reinitializer.resetCollection(infosEtude,
                                           "commentairesAchatsPUI");
    }

    /**
     * Traitements des informations dépendant de
     * InfosEtudeFaisabilite.prestaParticulieresPharmacie.
     * @param infosEtude Informations de l'étude.
     */
    private void handleDetailGlobal(final InfosEtudeFaisabilite infosEtude)
    {
        this.reinitializer.resetPropertyToNull(infosEtude,
                                               "achatsProduitsPUI");
        this.reinitializer.resetPropertyToNull(infosEtude,
                                               "demandeImportation");
        this.reinitializer.resetPropertyToNull(infosEtude,
                                               "randomisationParPharma");
        this.reinitializer.resetPropertyToNull(infosEtude,
                                               "gestionAveugle");
        this.reinitializer.resetPropertyToNull(infosEtude,
                                               "dispensationPossEnGarde");
        this.reinitializer.resetPropertyToNull(infosEtude,
                                               "distribAutresPharmaPossible");
        this.reinitializer.resetPropertyToNull(infosEtude,
                                               "reconstitutions");
        this.reinitializer.resetPropertyToNull(infosEtude,
                                               "preparations");
        this.reinitializer.resetPropertyToNull(infosEtude,
                                               "etiquetagesPduits");
        this.reinitializer.resetPropertyToNull(infosEtude,
                                               "conditionnementPduits");
        this.reinitializer.resetPropertyToNull(infosEtude,
                                               "donneesStabilite");
        this.reinitializer.resetCollection(infosEtude,
                                           "commentaires");
    }

    /**
     * Setter pour reinitializer.
     * @param reinitializer le reinitializer à écrire.
     */
    public void setReinitializer(final BeanPropertyReinitializer<InfosEtudeFaisabilite> reinitializer)
    {
        this.reinitializer = reinitializer;
    }

}
