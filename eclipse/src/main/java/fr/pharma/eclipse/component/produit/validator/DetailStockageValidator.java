package fr.pharma.eclipse.component.produit.validator;

import java.io.Serializable;
import java.util.Collection;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;

import fr.pharma.eclipse.domain.model.produit.detail.DetailStockage;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.domain.model.stockage.Stockage;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.utils.FacesUtils;

/**
 * Classe en charge de valider un détail de stockage de produit, avant de
 * l'ajouter au produit.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DetailStockageValidator implements Serializable {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -1259573986176265248L;

    /**
     * Utilitaire Faces.
     */
    @Resource(name = "facesUtils")
    private FacesUtils facesUtils;

    /**
     * Cette methode valide le detail de stockage passé en parametre. La
     * variable de validite est positionnée en conséquence dans la requête.
     * @param detailStockage Détail de stockage à valider.
     * @param detailsExistants Collection des détails de stockage existants.
     * @return Indique si la validation est OK.
     */
    public boolean validate(final DetailStockage detailStockage,
                            final Collection<DetailStockage> detailsExistants) {
        boolean valid = true;
        try {
            // Vérification de la validité de la pharmacie.
            this.validPharmacie(detailStockage);

            // Vérification de la validité de la pharmacie.
            this.validSite(detailStockage);

            // Vérification de l'unicité du détail dans la liste.
            this.validUnicity(detailStockage, detailsExistants);
        } catch (final ValidationException vExc) {
            valid = false;
        }

        // Valorisation du paramètre de retour dans la requête.
        this.facesUtils.putCallbackValidityParam(valid);
        return valid;
    }

    /**
     * Méthode en charge de vérifier l'unicité du détail.
     * @param detailStockage Détail de stockage.
     * @param detailsExistants Les details existants.
     */
    private void validUnicity(final DetailStockage detailStockage,
                              final Collection<DetailStockage> detailsExistants) {
        final boolean validUnicity = !detailsExistants.contains(detailStockage);
        if (!validUnicity) {
            final String errorCode = "produit.stockage.unique";
            this.facesUtils.addMessage(FacesMessage.SEVERITY_ERROR, errorCode);
            throw new ValidationException(errorCode);
        }

    }

    /**
     * Méthode de vérification de la pharmacie.
     * @param detailStockage Détail de stockage.
     * @throws ValidationException Si la pharmacie n'est pas valide.
     */
    private void validPharmacie(final DetailStockage detailStockage) throws ValidationException {
        final Pharmacie pharmacie = detailStockage.getPharmacie();
        final boolean validPharmacie = pharmacie != null;
        if (!validPharmacie) {
            final String errorCode = "produit.stockage.pharmacie.notEmpty";
            this.facesUtils.addMessage(FacesMessage.SEVERITY_ERROR, errorCode);
            throw new ValidationException(errorCode);
        }
    }

    /**
     * Méthode de vérification du site.
     * @param detailStockage Détail de stockage.
     * @throws ValidationException Si le site n'est pas valide.
     */
    private void validSite(final DetailStockage detailStockage) throws ValidationException {
        final Stockage s = detailStockage.getStockage();
        if (s == null) {
            final String errorCode = "produit.stockage.stockage.notEmpty";
            this.facesUtils.addMessage(FacesMessage.SEVERITY_ERROR, errorCode);
            throw new ValidationException(errorCode);
        }
    }

    /**
     * Getter sur facesUtils.
     * @return Retourne le facesUtils.
     */
    FacesUtils getFacesUtils() {
        return this.facesUtils;
    }

    /**
     * Setter pour facesUtils.
     * @param facesUtils le facesUtils à écrire.
     */
    public void setFacesUtils(final FacesUtils facesUtils) {
        this.facesUtils = facesUtils;
    }
}
