/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package fr.pharma.eclipse.controller;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pharma.eclipse.service.stock.StockService;

/**
 * Recupere notamment les valeurs de certaines clés du project.properties
 * @author dev
 */
public class ApplicationController {

    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(ApplicationController.class);
    /**
     * Service de gestion des mouvements de stock.
     */
    @Resource(name = "stockService")
    private StockService stockService;

    private String environnement;
    
    //propriété d'activation du module reprise passif (project.properties)
    private String reprisePassif;
    
    //logo affiché dans recap prescription (project.properties)
    private String logo;
    
    private String erreurMailto;
    
    private Boolean moduleImportExterne;
    
    //propriété d'activation du module pour strasbourg
    private String strasbourg;
    
    public String getStrasbourg() {
		return strasbourg;
	}

	public void setStrasbourg(String strasbourg) {
		this.strasbourg = strasbourg;
	}
    
    @PostConstruct
    public void initApplication() {
        this.log.info("Début - Initialisation de l'application");
        this.stockService.initialiseTableLigneStock();
        this.log.info("Fin - Initialisation de l'application");
    }

    public StockService getStockService() {
        return this.stockService;
    }

    public void setStockService(final StockService stockService) {
        this.stockService = stockService;
    }
    
    public String getEnvironnement() {
        return this.environnement;
    }
    
    public void setEnvironnement(String environnement) {
        this.environnement=environnement;
    }

	/**
	 * @return the reprisePassif
	 */
	public String getReprisePassif() {
		return reprisePassif;
	}

	/**
	 * @param reprisePassif the reprisePassif to set
	 */
	public void setReprisePassif(String reprisePassif) {
		this.reprisePassif = reprisePassif;
	}

	/**
	 * @return the logo
	 */
	public String getLogo() {
		return logo;
	}

	/**
	 * @param logo the logo to set
	 */
	public void setLogo(String logo) {
		this.logo = logo;
	}

    public String getErreurMailto() {
        return erreurMailto;
    }

    public void setErreurMailto(String erreurMailto) {
        this.erreurMailto = erreurMailto;
    }

    public Boolean getModuleImportExterne() {
        return moduleImportExterne;
    }

    public void setModuleImportExterne(Boolean moduleImportExterne) {
        this.moduleImportExterne = moduleImportExterne;
    }

}
