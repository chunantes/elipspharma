package fr.pharma.eclipse.jasper.engine.builder.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import fr.pharma.eclipse.domain.enums.jasper.TypeRapportJasper;
import fr.pharma.eclipse.domain.jasper.model.accusereception.JRBeanModeleAccuseReception;
import fr.pharma.eclipse.domain.jasper.model.accusereception.JRBeanTraitement;
import fr.pharma.eclipse.domain.jasper.model.common.JRBeanHeader;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.stock.CessionPui;
import fr.pharma.eclipse.domain.model.stock.ResultSortie;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.jasper.engine.builder.JasperReportDatasBuilder;
import fr.pharma.eclipse.jasper.engine.builder.helper.common.JRBeanHeaderBuilder;
import fr.pharma.eclipse.jasper.engine.factory.JRDataSourceFactory;
import fr.pharma.eclipse.jasper.engine.filler.impl.accusereception.helper.TraitementFillerHelper;
import fr.pharma.eclipse.jasper.engine.helper.ReportNameBuildHelper;
import fr.pharma.eclipse.jasper.engine.helper.SourceCheckingHandler;
import fr.pharma.eclipse.jasper.exception.JasperReportBuildException;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * Classe en charge de constuire les données pour le rapport Jasper de type
 * TypeRapportJasper.ACCUSE_RECEPTION.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class AccuseReceptionDatasBuilder implements JasperReportDatasBuilder {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -6604593924258411723L;

    /**
     * Helper pour la création du nom de fichier.
     */
    private ReportNameBuildHelper reportNameHelper;

    /**
     * Fabrique de JRDataSource.
     */
    private JRDataSourceFactory jrDataSourceFactory;

    /**
     * Helper pour la levée d'exception JasperReportBuildException sur
     * condition.
     */
    private SourceCheckingHandler checkHandler;

    /**
     * Helper pour la construction de l'en-tête.
     */
    private JRBeanHeaderBuilder headerBuilder;

    /**
     * Titre.
     */
    private String titre;

    /**
     * Helper pour construire les JRBEAN de traitements.
     */
    @Resource(name = "traitementFillerHelper")
    private TraitementFillerHelper helper;

    /**
     * {@inheritDoc}
     */
    @Override
    public JRDataSource buildDataSource(final Object source) {
        final ResultSortie result = (ResultSortie) source;

        // Conctruction du bean.
        final JRBeanModeleAccuseReception dataSource = new JRBeanModeleAccuseReception();
        dataSource.setPromoteur(result.getEssai().getPromoteur().getRaisonSociale());
        dataSource.setCodeProtocole(result.getEssai().getCodePromoteur() + " - " + result.getEssai().getNom());
        final CessionPui cession = (CessionPui) result.getMvts().get(0);

        dataSource.setCentre(cession.getPharmacieDest().getEtablissement().getNom());

        dataSource.setPharmacie(this.buildPharmacie(result.getPharmacie()));

        // Beans produits.
        final Collection<JRBeanTraitement> beansProduit = this.helper.transform(result.getMvts());

        // Création de la source de données.
        final JRDataSource dtProduits = this.jrDataSourceFactory.getInitializedObject(beansProduit);
        dataSource.setTraitements(dtProduits);

        // Construction de l'en-tête.
        final String sousTitre = "Accusé de réception";
        final JRBeanHeader dataHeader = this.headerBuilder.build(sousTitre, "", "Essais cliniques", "Pharmacie");
        dataSource.setHeader(dataHeader);

        // Retour
        return this.jrDataSourceFactory.getInitializedObject(dataSource);
    }
    /**
     * Méthode en charge de construire le libellé de la pharmacie coordinatrice.
     * @param pharmacie Pharmacie.
     * @return Libellé de la pharmacie coordinatrice.
     */
    private String buildPharmacie(final Pharmacie pharmacie) {
        final StringBuffer sb = new StringBuffer();
        sb.append(pharmacie.getEtablissement().getNom() + " - " + pharmacie.getNom());
        if (StringUtils.isNotBlank(pharmacie.getAdresse())) {
            sb.append("\n" + pharmacie.getAdresse());
        }
        if (StringUtils.isNotBlank(pharmacie.getTelephone())) {
            sb.append("\nTéléphone : " + pharmacie.getTelephone());
        }
        if (StringUtils.isNotBlank(pharmacie.getFax())) {
            sb.append("\nFax : " + pharmacie.getFax());
        }
        return sb.toString();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Object> buildParameters(final Object source) {
        return new HashMap<String, Object>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String buildReportName(final Object source,
                                  final TypeRapportJasper typeRapport) {
        Essai essai = null;
        essai = ((ResultSortie) source).getEssai();
        final StringBuilder builder = new StringBuilder();
        this.reportNameHelper.addCommonNamePart(builder, typeRapport);
        this.reportNameHelper.addIdEssaiPart(builder, essai);
        this.reportNameHelper.addDatePart(builder);
        this.reportNameHelper.addCommonExtensionPart(builder, typeRapport);
        return builder.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void checkSource(final Object source) throws JasperReportBuildException {
        this.checkHandler.handleCheck(source != null, new StringBuilder("[CertificatSortiDatasBuilder] ").append("La source est nulle.").toString());
        this.checkHandler.handleCheck(source instanceof ResultSortie, new StringBuilder("[CertificatSortiDatasBuilder] ")
                .append("Le type attendu de la source est Result(source: ").append(source).append(").").toString());

    }

    /**
     * Getter sur reportNameHelper.
     * @return Retourne le reportNameHelper.
     */
    ReportNameBuildHelper getReportNameHelper() {
        return this.reportNameHelper;
    }

    /**
     * Setter pour reportNameHelper.
     * @param reportNameHelper le reportNameHelper à écrire.
     */
    public void setReportNameHelper(final ReportNameBuildHelper reportNameHelper) {
        this.reportNameHelper = reportNameHelper;
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
     * Getter sur checkHandler.
     * @return Retourne le checkHandler.
     */
    SourceCheckingHandler getCheckHandler() {
        return this.checkHandler;
    }

    /**
     * Setter pour checkHandler.
     * @param checkHandler le checkHandler à écrire.
     */
    public void setCheckHandler(final SourceCheckingHandler checkHandler) {
        this.checkHandler = checkHandler;
    }

    /**
     * Getter sur headerBuilder.
     * @return Retourne le headerBuilder.
     */
    JRBeanHeaderBuilder getHeaderBuilder() {
        return this.headerBuilder;
    }

    /**
     * Setter pour headerBuilder.
     * @param headerBuilder le headerBuilder à écrire.
     */
    public void setHeaderBuilder(final JRBeanHeaderBuilder headerBuilder) {
        this.headerBuilder = headerBuilder;
    }
    /**
     * Getter pour titre.
     * @return Le titre
     */
    public String getTitre() {
        return this.titre;
    }
    /**
     * Setter pour titre.
     * @param titre Le titre à écrire.
     */
    public void setTitre(final String titre) {
        this.titre = titre;
    }
    /**
     * Getter pour helper.
     * @return Le helper
     */
    public TraitementFillerHelper getHelper() {
        return this.helper;
    }
    /**
     * Setter pour helper.
     * @param helper Le helper à écrire.
     */
    public void setHelper(final TraitementFillerHelper helper) {
        this.helper = helper;
    }

}
