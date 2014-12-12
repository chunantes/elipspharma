package fr.pharma.eclipse.jasper.engine.builder.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.jasperreports.engine.JRDataSource;
import fr.pharma.eclipse.domain.enums.jasper.TypeRapportJasper;
import fr.pharma.eclipse.domain.jasper.model.common.JRBeanHeader;
import fr.pharma.eclipse.domain.jasper.model.prescnominative.JRBeanModelePrescNominative;
import fr.pharma.eclipse.domain.jasper.model.prescnominative.JRBeanProduitPrescrit;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.prescription.Prescription;
import fr.pharma.eclipse.jasper.engine.builder.JasperReportDatasBuilder;
import fr.pharma.eclipse.jasper.engine.builder.helper.common.JRBeanHeaderBuilder;
import fr.pharma.eclipse.jasper.engine.factory.JRDataSourceFactory;
import fr.pharma.eclipse.jasper.engine.filler.impl.modelprescription.helper.ProduitPrescritFillerHelper;
import fr.pharma.eclipse.jasper.engine.helper.ReportNameBuildHelper;
import fr.pharma.eclipse.jasper.engine.helper.SourceCheckingHandler;
import fr.pharma.eclipse.jasper.exception.JasperReportBuildException;
import fr.pharma.eclipse.service.common.GenericService;

/**
 * Classe en charge de constuire les données pour le rapport Jasper de type
 * TypeRapportJasper.MODELE_PRESCRIPTION_NOMINATIVE.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ModelePrescNominativeDatasBuilder implements JasperReportDatasBuilder {

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
     * Helper pour construire les JRBEAN de produits prescrits.
     */
    @Resource(name = "produitPrescritFillerHelper")
    private ProduitPrescritFillerHelper helper;
    
    private GenericService<Essai> service;

    /**
     * {@inheritDoc}
     */
    @Override
    public JRDataSource buildDataSource(final Object source) {
        Essai essai = null;
        Prescription prescription = null;
        if (source instanceof Essai) {
            essai = (Essai) source;
        } else {
            prescription = (Prescription) source;
            essai = prescription.getEssai();
        }
        service.save(essai);

        // Conctruction du bean.
        final JRBeanModelePrescNominative dataSource = new JRBeanModelePrescNominative();
        dataSource.setPromoteur(essai.getPromoteur().getRaisonSociale());
        dataSource.setCodeProtocole(essai.getNumInterne());
        dataSource.setNomUsuel(essai.getNom());
        if (prescription != null) {
            // Beans produits.
            final Collection<JRBeanProduitPrescrit> beansProduit = this.helper.transform(prescription.getProduitsPrescrits());

            // Création de la source de données.
            final JRDataSource dtProduits = this.jrDataSourceFactory.getInitializedObject(beansProduit);
            dataSource.setProduits(dtProduits);

            dataSource.setNumVisite(prescription.getNumVisite());
            dataSource.setNumInclusion(prescription.getInclusion().getNumInclusion());
        }

        // Construction de l'en-tête.
        final JRBeanHeader dataHeader =
            this.headerBuilder.build("Prescription nominative de médicament en expérimentation clinique", "Métier pôle Pharmacie-Stérilisation\\Dispensation", "Essais cliniques",
                                     "Pharmacie");
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
        this.checkHandler.handleCheck(source != null, new StringBuilder("[ModelePrescNominativeDatasBuilder] ").append("La source est nulle.").toString());
        this.checkHandler.handleCheck((source instanceof Essai) || (source instanceof Prescription),
                                      new StringBuilder("[ModelePrescNominativeDatasBuilder] ").append("Le type attendu de la source est Essai ou Prescription(source: ")
                                              .append(source).append(").").toString());

    }

    ReportNameBuildHelper getReportNameHelper() {
        return this.reportNameHelper;
    }

    public void setReportNameHelper(final ReportNameBuildHelper reportNameHelper) {
        this.reportNameHelper = reportNameHelper;
    }

    JRDataSourceFactory getJrDataSourceFactory() {
        return this.jrDataSourceFactory;
    }

    public void setJrDataSourceFactory(final JRDataSourceFactory jrDataSourceFactory) {
        this.jrDataSourceFactory = jrDataSourceFactory;
    }

    SourceCheckingHandler getCheckHandler() {
        return this.checkHandler;
    }

    public void setCheckHandler(final SourceCheckingHandler checkHandler) {
        this.checkHandler = checkHandler;
    }

    JRBeanHeaderBuilder getHeaderBuilder() {
    	return headerBuilder;
    }

    public void setHeaderBuilder(final JRBeanHeaderBuilder headerBuilder) {
        this.headerBuilder = headerBuilder;
    }
    public void setHelper(final ProduitPrescritFillerHelper helper) {
        this.helper = helper;
    }

    public void setService(GenericService<Essai> service) {
        this.service = service;
    }

    public GenericService<Essai> getService() {
        return service;
    }
    
}
