package fr.pharma.eclipse.component.stockage.validator;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;

import org.apache.commons.lang.StringUtils;

import fr.pharma.eclipse.domain.model.stockage.Stockage;
import fr.pharma.eclipse.service.stockage.StockageService;
import fr.pharma.eclipse.utils.FacesUtils;

/**
 * Classe de validation de saisie d'un stockage de pharmacie.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class StockageValidator implements Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 2731450125458678720L;

    /**
     * Utilitaire Faces.
     */
    @Resource(name = "facesUtils")
    private FacesUtils facesUtils;

    /**
     * Service de gestion des stockages.
     */
    @Resource(name = "stockageService")
    private StockageService stockageService;

    /**
     * Méthode en charge de valider les informations d'un stockage.
     * @param stockageCurrent Stockage courant (en cours d'ajout/ modif)
     * @param stockages Liste des stockages de la pharmacie.
     * @return Indique si la validation est OK.
     */
    public boolean validateStockage(final Stockage stockageCurrent) {
        boolean valid = true;
        final String nom = stockageCurrent.getNom();

        // Vérification de la valorisation du nom
        if ((nom == null) || StringUtils.isEmpty(nom.trim())) {
            this.facesUtils.addMessage(FacesMessage.SEVERITY_ERROR, "stockage.nom.notEmpty");
            valid = false;

        }

        // Vérification de l'unicité du nom (un stockage de même niveau ne doit
        // pas porter le même nom)
        if (this.stockageService.isNomStockageUtiliseParAutreStockageDeMemeNiveau(stockageCurrent, stockageCurrent.getPharmacie())) {
            this.facesUtils.addMessage(FacesMessage.SEVERITY_ERROR, "stockage.nom.notUnique");
            valid = false;
        }

        // Valorisation du paramètre de retour dans la requête.
        this.facesUtils.putCallbackValidityParam(valid);

        return valid;
    }
    /**
     * Setter pour facesUtils.
     * @param facesUtils Le facesUtils à écrire.
     */
    public void setFacesUtils(final FacesUtils facesUtils) {
        this.facesUtils = facesUtils;
    }

    /**
     * Setter pour stockageService.
     * @param stockageService Le stockageService à écrire.
     */
    public void setStockageService(final StockageService stockageService) {
        this.stockageService = stockageService;
    }

}
