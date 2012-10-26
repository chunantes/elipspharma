package fr.pharma.eclipse.jasper.document.maker.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import fr.pharma.eclipse.domain.enums.jasper.TypeRapportJasper;
import fr.pharma.eclipse.domain.model.common.DocumentEclipse;
import fr.pharma.eclipse.domain.model.common.Fichier;
import fr.pharma.eclipse.domain.model.stock.DocumentStock;
import fr.pharma.eclipse.domain.model.stock.MvtStock;
import fr.pharma.eclipse.domain.model.stock.ResultSortie;
import fr.pharma.eclipse.factory.common.FichierFactory;
import fr.pharma.eclipse.factory.document.stock.DocumentStockFactory;
import fr.pharma.eclipse.jasper.document.maker.DocumentMaker;
import fr.pharma.eclipse.jasper.engine.manager.JasperReportBuildManager;
import fr.pharma.eclipse.jasper.exception.JasperReportBuildException;
import fr.pharma.eclipse.service.document.DocumentService;
import fr.pharma.eclipse.service.stock.MvtStockService;

/**
 * Clase en charge de générer un document eclipse pour les certificats.
 
 * @version $Revision$ $Date$
 */
public abstract class GenericCertificatMaker
    implements DocumentMaker, Serializable
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 332041109677880355L;

    /**
     * Map des managers de génération de rapport Jasper par type de rapport.
     */
    @Resource(name = "jasperReportsMapManagers")
    private Map<String, JasperReportBuildManager> buildManagers;

    /**
     * Factory de fichier.
     */
    @Resource(name = "fichierFactory")
    private FichierFactory fichierFactory;

    /**
     * Nom du fichier.
     */
    private String nom;

    /**
     * Type de fichiers.
     */
    private String typeFichier;

    /**
     * Factory de documents.
     */
    private DocumentStockFactory<DocumentStock> documentFactory;

    /**
     * Service mvt de stock.
     */
    @Resource(name = "mouvementStockService")
    private MvtStockService<MvtStock> service;

    /**
     * Service document.
     */
    @Resource(name = "documentService")
    private DocumentService docService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void make(final TypeRapportJasper type,
                     final Object source)
        throws JasperReportBuildException
    {
        final byte[] bytes = this.buildManagers.get(type.name()).build(source);
        final Fichier fichier = this.fichierFactory.getInitializedObject();
        fichier.setContenu(bytes);
        fichier.setNom(this.nom);
        fichier.setTypeFichier(this.typeFichier);

        final DocumentEclipse doc = this.makeForMvt(((ResultSortie) source).getMvts(),
                                                    fichier);

        ((ResultSortie) source).setDocument(doc);

    }

    /**
     * @param mvts
     * @param fichier
     */
    protected abstract DocumentEclipse makeForMvt(List<? extends MvtStock> mvts,
                                                  Fichier fichier);

    /**
     * Setter pour buildManagers.
     * @param buildManagers Le buildManagers à écrire.
     */
    public void setBuildManagers(final Map<String, JasperReportBuildManager> buildManagers)
    {
        this.buildManagers = buildManagers;
    }

    /**
     * Setter pour fichierFactory.
     * @param fichierFactory Le fichierFactory à écrire.
     */
    public void setFichierFactory(final FichierFactory fichierFactory)
    {
        this.fichierFactory = fichierFactory;
    }

    /**
     * Setter pour nom.
     * @param nom Le nom à écrire.
     */
    public void setNom(final String nom)
    {
        this.nom = nom;
    }

    /**
     * Setter pour typeFichier.
     * @param typeFichier Le typeFichier à écrire.
     */
    public void setTypeFichier(final String typeFichier)
    {
        this.typeFichier = typeFichier;
    }

    /**
     * Setter pour documentFactory.
     * @param documentFactory Le documentFactory à écrire.
     */
    public void setDocumentFactory(final DocumentStockFactory<DocumentStock> documentFactory)
    {
        this.documentFactory = documentFactory;
    }

    /**
     * Setter pour service.
     * @param service Le service à écrire.
     */
    public void setService(final MvtStockService<MvtStock> service)
    {
        this.service = service;
    }

    /**
     * Getter pour fichierFactory.
     * @return Le fichierFactory
     */
    public FichierFactory getFichierFactory()
    {
        return this.fichierFactory;
    }

    /**
     * Getter pour documentFactory.
     * @return Le documentFactory
     */
    public DocumentStockFactory<DocumentStock> getDocumentFactory()
    {
        return this.documentFactory;
    }

    /**
     * Getter pour service.
     * @return Le service
     */
    public MvtStockService<MvtStock> getService()
    {
        return this.service;
    }

    /**
     * Getter pour docService.
     * @return Le docService
     */
    public DocumentService getDocService()
    {
        return this.docService;
    }

    /**
     * Setter pour docService.
     * @param docService Le docService à écrire.
     */
    public void setDocService(final DocumentService docService)
    {
        this.docService = docService;
    }

}
