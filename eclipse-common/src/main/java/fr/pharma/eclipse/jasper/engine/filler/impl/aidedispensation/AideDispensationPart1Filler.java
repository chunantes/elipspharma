package fr.pharma.eclipse.jasper.engine.filler.impl.aidedispensation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;

import fr.pharma.eclipse.domain.enums.QualiteInsu;
import fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean;
import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanFicheAideDispensation;
import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanFicheAideDispensationPart1;
import fr.pharma.eclipse.domain.model.acteur.Investigateur;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.dates.DetailDates;
import fr.pharma.eclipse.domain.model.localisation.Service;
import fr.pharma.eclipse.jasper.constants.JasperConstants;
import fr.pharma.eclipse.jasper.engine.factory.JRDataSourceFactory;
import fr.pharma.eclipse.jasper.engine.filler.JasperReportBeanFiller;
import fr.pharma.eclipse.jasper.utils.JasperUtils;
import fr.pharma.eclipse.service.habilitation.helper.HabilitationsHelper;
import fr.pharma.eclipse.utils.Utils;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Filler en charge de construire la partie 1 du bean
 * {@link JRBeanFicheAideDispensation}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class AideDispensationPart1Filler implements JasperReportBeanFiller {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -867975613875845853L;

    /**
     * Fabrique de JRDataSource.
     */
    private JRDataSourceFactory jrDataSourceFactory;

    /**
     * Helper pour la gestion des habilitations.
     */
    private HabilitationsHelper habilitationsHelper;

    /**
     * Fillers secondaires.
     */
    private List<JasperReportBeanFiller> subFillers;

    /**
     * {@inheritDoc}
     */
    @Override
    public void fill(final Essai essai,
                     final JasperReportBean bean) {

        // Construction du bean.
        final JRBeanFicheAideDispensationPart1 part1 = new JRBeanFicheAideDispensationPart1();
        part1.setPromoteur(essai.getPromoteur().getRaisonSociale());
        part1.setCodeProtocole(essai.getNumInterne());
        part1.setNomUsuel(essai.getNom());
        part1.setTitreProtocole(essai.getDetailRecherche().getTitreProtocole());
        this.fillInfosInvestigateur(essai, part1);
        part1.setNumeroCentre(essai.getDetailDonneesPharma().getInfosGenerales().getNumeroCentre());
        part1.setServiceInvestigateur(JasperUtils.formatterListeStrings(this.prepareNomsServices(essai.getServices())));
        this.fillPhase(essai, part1);
        this.fillInfosCentres(essai, part1);
        this.fillNbPatients(essai, part1);
        part1.setNbGroupes(String.valueOf(essai.getDetailDesign().getBras().size()));
        this.fillQualiteInsu(essai, part1);
        this.fillDates(essai, part1);

        // Appel des sous-fillers.
        for (final JasperReportBeanFiller subFiller : this.subFillers) {
            subFiller.fill(essai, part1);
        }

        // Valorisation dans la source principale.
        final JRBeanFicheAideDispensation dataSource = (JRBeanFicheAideDispensation) bean;
        dataSource.setPartie1(this.jrDataSourceFactory.getInitializedObject(part1));
    }
    /**
     * Méthode en charge de valoriser les informations sur les dates de l'essai.
     * @param essai Essai.
     * @param part1 Partie 1 à valoriser.
     */
    private void fillDates(final Essai essai,
                           final JRBeanFicheAideDispensationPart1 part1) {
        final DetailDates detailDates = essai.getDetailDates();
        final String pattern = EclipseConstants.PATTERN_SIMPLE;
        final String defaultValue = JasperConstants.DEFAULT_FIELD_VALUE;
        part1.setDateActivation(Utils.formatDate(detailDates.getActivation(), pattern, defaultValue));
        part1.setDateMiseEnPlace(Utils.formatDate(detailDates.getDebutEtude(), pattern, defaultValue));
        part1.setDatePrevueFinEssai(Utils.formatDate(detailDates.getFinEtudePrev(), pattern, defaultValue));
        part1.setDatePrevueFinInclusions(Utils.formatDate(detailDates.getFinInclusionPrev(), pattern, defaultValue));
    }

    /**
     * Méthode en charge de valoriser la qualité de l'insu de l'essai.
     * @param essai Essai.
     * @param part1 Partie 1 à valoriser.
     */
    private void fillQualiteInsu(final Essai essai,
                                 final JRBeanFicheAideDispensationPart1 part1) {
        final QualiteInsu qualiteInsu = essai.getDetailDonneesPharma().getInfosGenerales().getQualiteInsu();
        if (qualiteInsu != null) {
            part1.setQualiteInsu(qualiteInsu.getLibelle());
        }
    }

    /**
     * Méthode en charge de valoriser le nombre de patients prévus sur l'essai.
     * @param essai Essai.
     * @param part1 Partie 1 à valoriser.
     */
    private void fillNbPatients(final Essai essai,
                                final JRBeanFicheAideDispensationPart1 part1) {
        final Integer nbPatientsPrevus = essai.getDetailDonneesPharma().getInfosGenerales().getNbPatientsPrevus();
        if (nbPatientsPrevus != null) {
            part1.setNbPatients(String.valueOf(nbPatientsPrevus));
        }
    }

    /**
     * Méthode en charge de valoriser les informations relatives à
     * l'investigateur de l'essai.
     * @param essai Essai.
     * @param part1 Partie 1 à valoriser.
     */
    private void fillInfosInvestigateur(final Essai essai,
                                        final JRBeanFicheAideDispensationPart1 part1) {
        final Investigateur invPrincipal = this.habilitationsHelper.getInvestigateurPrincipal(essai);
        part1.setInvestigateur(JasperUtils.makeLibelleInvestigateur(invPrincipal));
    }

    /**
     * Méthode en charge de valoriser les informations sur les centres de
     * l'essai.
     * @param essai Essai.
     * @param part1 Partie 1 à valoriser.
     */
    private void fillInfosCentres(final Essai essai,
                                  final JRBeanFicheAideDispensationPart1 part1) {
        final Integer nbCentresPrevus = essai.getDetailDonneesPharma().getInfosGenerales().getNbCentresPrevus();
        if (nbCentresPrevus != null) {
            part1.setNbCentres(String.valueOf(nbCentresPrevus));
            part1.setMulticentrique(JasperUtils.transformToOuiNon(nbCentresPrevus > 1));
        }
    }

    /**
     * Méthode en charge de valoriser la phase.
     * @param essai Essai.
     * @param part1 Partie 1 à valoriser.
     */
    private void fillPhase(final Essai essai,
                           final JRBeanFicheAideDispensationPart1 part1) {
        if (essai.getDetailRecherche().getPhaseRecherche() != null) {
            part1.setPhase(essai.getDetailRecherche().getPhaseRecherche().getLibelle());
        }
    }

    /**
     * Méthode en charge de préparer la liste des noms des services donnés.
     * @param services Services à inspecter.
     * @return Liste des noms des services de l'investigateur
     */
    @SuppressWarnings("unchecked")
    private Collection<String> prepareNomsServices(final Collection<Service> services) {
        if ((services == null) || services.isEmpty()) {
            return new ArrayList<String>();
        }
        final Collection collection = new ArrayList(services);
        CollectionUtils.transform(collection, new Transformer() {

            @Override
            public Object transform(final Object input) {
                final Service service = (Service) input;
                return service.getNom();
            }
        });
        return collection;
    }

    /**
     * Getter sur jrDataSourceFactory.
     * @return Retourne le jrDataSourceFactory.
     */
    JRDataSourceFactory getJrDataSourceFactory() {
        return this.jrDataSourceFactory;
    }

    /**
     * Setter pour jrDataSourceFactory.
     * @param jrDataSourceFactory le jrDataSourceFactory à écrire.
     */
    public void setJrDataSourceFactory(final JRDataSourceFactory jrDataSourceFactory) {
        this.jrDataSourceFactory = jrDataSourceFactory;
    }

    /**
     * Getter sur habilitationsHelper.
     * @return Retourne le habilitationsHelper.
     */
    HabilitationsHelper getHabilitationsHelper() {
        return this.habilitationsHelper;
    }

    /**
     * Setter pour habilitationsHelper.
     * @param habilitationsHelper le habilitationsHelper à écrire.
     */
    public void setHabilitationsHelper(final HabilitationsHelper habilitationsHelper) {
        this.habilitationsHelper = habilitationsHelper;
    }

    /**
     * Getter sur subFillers.
     * @return Retourne le subFillers.
     */
    List<JasperReportBeanFiller> getSubFillers() {
        return this.subFillers;
    }

    /**
     * Setter pour subFillers.
     * @param subFillers le subFillers à écrire.
     */
    public void setSubFillers(final List<JasperReportBeanFiller> subFillers) {
        this.subFillers = subFillers;
    }

}
