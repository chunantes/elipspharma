package fr.pharma.eclipse.jasper.engine.builder.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.jasperreports.engine.JRDataSource;
import fr.pharma.eclipse.domain.enums.jasper.TypeRapportJasper;
import fr.pharma.eclipse.domain.jasper.model.certificat.JRBeanModeleCertificat;
import fr.pharma.eclipse.domain.jasper.model.certificat.JRBeanProduitSorti;
import fr.pharma.eclipse.domain.jasper.model.common.JRBeanHeader;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.prescription.Prescription;
import fr.pharma.eclipse.domain.model.stock.ResultSortie;
import fr.pharma.eclipse.jasper.engine.builder.JasperReportDatasBuilder;
import fr.pharma.eclipse.jasper.engine.builder.helper.common.JRBeanHeaderBuilder;
import fr.pharma.eclipse.jasper.engine.factory.JRDataSourceFactory;
import fr.pharma.eclipse.jasper.engine.filler.impl.certificat.helper.ProduitSortiFillerHelper;
import fr.pharma.eclipse.jasper.engine.helper.ReportNameBuildHelper;
import fr.pharma.eclipse.jasper.engine.helper.SourceCheckingHandler;
import fr.pharma.eclipse.jasper.exception.JasperReportBuildException;

/**
 * Classe en charge de constuire les données pour le rapport Jasper de type
 * TypeRapportJasper.CERTIFICAT_DESTRUCTION ou
 * TypeRapportJasper.CERTIFICAT_RETOUR.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class CertificatSortieDatasBuilder implements JasperReportDatasBuilder {

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
    private String sousTitre;

    /**
     * Helper pour construire les JRBEAN de produits sortis.
     */
    @Resource(name = "produitSortiFillerHelper")
    private ProduitSortiFillerHelper helper;

    /**
     * {@inheritDoc}
     */
    @Override
    public JRDataSource buildDataSource(final Object source) {
        final ResultSortie result = (ResultSortie) source;

        // Conctruction du bean.
        final JRBeanModeleCertificat dataSource = new JRBeanModeleCertificat();
        dataSource.setPromoteur(result.getEssai().getPromoteur().getRaisonSociale());
        dataSource.setCodeProtocole(result.getEssai().getNumInterne());
        if (result.getRaisonSortie() != null) {
            dataSource.setMotif(result.getRaisonSortie().name());
        }
        // Beans produits.
        final Collection<JRBeanProduitSorti> beansProduit = this.helper.transform(result.getMvts());

        // Création de la source de données.
        final JRDataSource dtProduits = this.jrDataSourceFactory.getInitializedObject(beansProduit);
        dataSource.setProduits(dtProduits);

        // Construction de l'en-tête.
        final JRBeanHeader dataHeader = this.headerBuilder.build(this.sousTitre, "", "Essais cliniques", "Pharmacie");
        dataSource.setHeader(dataHeader);

        // Retour
        return this.jrDataSourceFactory.getInitializedObject(dataSource);
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
        if (source instanceof Essai) {
            essai = (Essai) source;
        } else {
            essai = ((Prescription) source).getInclusion().getEssai();
        }
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
     * Setter pour helper.
     * @param helper Le helper à écrire.
     */
    public void setHelper(final ProduitSortiFillerHelper helper) {
        this.helper = helper;
    }
    /**
     * Getter pour sousTitre.
     * @return Le sousTitre
     */
    public String getSousTitre() {
        return this.sousTitre;
    }
    /**
     * Setter pour sousTitre.
     * @param sousTitre Le sousTitre à écrire.
     */
    public void setSousTitre(final String sousTitre) {
        this.sousTitre = sousTitre;
    }

}
