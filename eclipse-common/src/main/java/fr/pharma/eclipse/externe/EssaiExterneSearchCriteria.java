/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pharma.eclipse.externe;

import fr.pharma.eclipse.domain.criteria.sigrec.essai.EssaiSigrecSearchCriteria;
import fr.pharma.eclipse.domain.model.localisation.Service;

/**
 *
 * @author sgl
 */
public class EssaiExterneSearchCriteria extends EssaiSigrecSearchCriteria {
    
    private String numInterne;
        /**
     * Nom.
     */
    private String titreProtocole;
        /**
     * Nom.
     */
    private Service service;

    public String getNumInterne() {
        return numInterne;
    }

    public void setNumInterne(String numInterne) {
        this.numInterne = numInterne;
    }

    public String getTitreProtocole() {
        return titreProtocole;
    }

    public void setTitreProtocole(String titreProtocole) {
        this.titreProtocole = titreProtocole;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
    
}
