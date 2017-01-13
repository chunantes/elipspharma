package fr.pharma.eclipse.service.surcout.counter.impl;

import java.io.Serializable;
import java.util.Calendar;

import javax.annotation.Resource;

import fr.pharma.eclipse.domain.criteria.dispensation.ElementToCheckSearchCriteria;
import fr.pharma.eclipse.domain.enums.TypeElementToCheck;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.service.dispensation.ElementToCheckService;
import fr.pharma.eclipse.service.surcout.counter.ActeCounter;

/**
 * Classe en charge de compter le nombre d'actes pharmaceutiques pour un essai
 * ou un patient dans un essai.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public abstract class ElementToCheckCounter implements ActeCounter, Serializable {

    /**
     * Serial UID.
     */
    private static final long serialVersionUID = -5454986233941697685L;

    /**
     * Service des actes pharma à vérifier.
     */
    @Resource(name = "elementToCheckService")
    private ElementToCheckService elementToCheckService;

    /**
     * {@inheritDoc}
     */
    protected int process(final TypeElementToCheck type,
                          final Essai essai,
                          final Patient patient,
                          final Calendar dateDebut,
                          final Calendar dateFin) {
        final ElementToCheckSearchCriteria crit = new ElementToCheckSearchCriteria();
        crit.setType(type);
        if (patient != null) {
            crit.setPatient(patient);
        }
        crit.setDateDebut(dateDebut);
        crit.setEssai(essai);
        crit.setDateFin(dateFin);
        return this.elementToCheckService.count(crit).intValue();
    }

    /**
     * Setter pour elementToCheckService.
     * @param elementToCheckService le elementToCheckService à écrire.
     */
    public void setElementToCheckService(final ElementToCheckService elementToCheckService) {
        this.elementToCheckService = elementToCheckService;
    }

}
