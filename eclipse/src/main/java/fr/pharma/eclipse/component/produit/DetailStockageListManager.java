package fr.pharma.eclipse.component.produit;

import java.util.Map;
import java.util.SortedSet;

import javax.annotation.Resource;

import fr.pharma.eclipse.component.BeanListManager;
import fr.pharma.eclipse.component.BeanManager;
import fr.pharma.eclipse.component.produit.validator.DetailStockageRemoveValidator;
import fr.pharma.eclipse.component.produit.validator.DetailStockageValidator;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.enums.produit.TypeProduit;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.produit.detail.DetailStockage;
import fr.pharma.eclipse.service.produit.ProduitService;

/**
 * Manager sur les beans de gestion de detail de stockage.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DetailStockageListManager extends BeanListManager<DetailStockage> {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -7548336078323446495L;

    /**
     * Stockage selectionné.
     */
    private DetailStockage stockageSelected;

    /**
     * Stockage retour selectionné.
     */
    private DetailStockage retourSelected;

    /**
     * Dictionnaire de chaine de redirection en fonction du type de produit.
     */
    private Map<TypeProduit, String> redirectDictionary;

    /**
     * Validateur d'ajout.
     */
    @Resource(name = "detailStockageValidator")
    private DetailStockageValidator addValidator;

    /**
     * Validator de suppression.
     */
    @Resource(name = "detailStockageRemoveValidator")
    private DetailStockageRemoveValidator removeValidator;

    /**
     * ManageR.
     */
    @Resource(name = "detailStockageManager")
    private DetailStockageManager manager;

    /**
     * Constructeur.
     * @param searchCriteria Critère de recherche.
     */
    public DetailStockageListManager(final SearchCriteria searchCriteria) {
        super(searchCriteria);
    }

    /**
     * Méthode en charge de valider puis d'ajouter au produit un detailStockage.
     * La variable de validité est valorisée dans la requête.
     * @param p Le produit.
     * @param detailStockage Le detailStockage.
     */
    public void addStockage(final Produit p,
                            final DetailStockage detailStockage) {
        final SortedSet<DetailStockage> stockages = p.getDetailLogistique().getDetailsStockages();
        final boolean valid = this.addValidator.validate(detailStockage, stockages);
        if (valid) {
            if (detailStockage.getId() == null) {
                stockages.add(detailStockage);
            }
            this.updateDetailStockage(stockages);
            this.setBeanSelected(null);
            this.reinit();
        }
    }

    /**
     * Mise à jour manuelle du détail stockage suite à une modification.
     * Nécessaire pour que le retour de la pop-up affiche les modifications.
     * @param stockages Liste des détails stockages en cours de modifications
     */
    private void updateDetailStockage(final SortedSet<DetailStockage> stockages) {
        for (final DetailStockage stockage : stockages) {
            if (stockage.equals(this.manager.getBean())) {
                stockage.setDetailLogistique(this.manager.getBean().getDetailLogistique());
                stockage.setIdentifiantStockage(this.manager.getBean().getIdentifiantStockage());
                stockage.setPharmacie(this.manager.getBean().getPharmacie());
                stockage.setSelected(this.manager.getBean().getSelected());
                stockage.setStockage(this.manager.getBean().getStockage());
                stockage.setType(this.manager.getBean().getType());
                break;
            }
        }
    }

    /**
     * Méthode en charge de supprimer du produit les beans sélectionnés.
     * @param produit Le produit.
     */
    public void removeStockage(final Produit produit) {
        this.getBeans().clear();
        this.getBeans().addAll(produit.getDetailLogistique().getDetailsStockages());
        for (final DetailStockage p : this.getBeansSelected()) {
            if (this.removeValidator.validate(p)) {

                produit.getDetailLogistique().getDetailsStockages().remove(p);
            }
        }
    }

    /**
     * Méthode en charge d'ajouter au produit le bean detailStockage.
     * @param produit Le produit.
     * @param detailStockage Le detailStockage.
     */
    public void addStockageRetour(final Produit produit,
                                  final DetailStockage detailStockage) {

        final SortedSet<DetailStockage> stockages = produit.getDetailLogistique().getStockagesRetours();
        final boolean valid = this.addValidator.validate(detailStockage, stockages);
        if (valid) {
            if (detailStockage.getId() == null) {
                stockages.add(detailStockage);
            }
            this.updateDetailStockage(stockages);
            this.setBeanSelected(null);
            this.reinit();
        }
    }

    /**
     * Méthode en charge de supprimer du produit les beans sélectionnés.
     * @param produit Le produit.
     */
    public void removeStockageRetour(final Produit produit) {
        this.getBeans().clear();
        this.getBeans().addAll(produit.getDetailLogistique().getStockagesRetours());
        for (final DetailStockage p : this.getBeansSelected()) {
            if (this.removeValidator.validate(p)) {

                produit.getDetailLogistique().getStockagesRetours().remove(p);
            }
        }
    }

    /**
     * Réinitialisation des beans selected.
     */
    public void reinit() {
        this.manager.setBean(null);
        this.setStockageSelected(null);
        this.setRetourSelected(null);
        this.resetBeanSelected();
    }

    /**
     * Edition d'un detailStockage. On flag d'abord l'affichage de la popup à
     * true et on affecte le bean à éditer. Enfin on calcule la redirection
     * (rechargement de la page courante) en fonction du type de produit auquel
     * est lié le detailStockage.
     * @param detailStockageManager Le detailStockageManager contenant le
     * detailStockage.
     */
    public void editDetailStockage(final BeanManager<DetailStockage> detailStockageManager) {
        detailStockageManager.setBean(this.getStockageSelected());
    }

    /**
     * Edition d'un detailStockage. On flag d'abord l'affichage de la popup à
     * true et on affecte le bean à éditer. Enfin on calcule la redirection
     * (rechargement de la page courante) en fonction du type de produit auquel
     * est lié le detailStockage.
     * @param detailStockageManager Le detailStockageManager contenant le
     * detailStockage.
     */
    public void editDetailStockageRetour(final BeanManager<DetailStockage> detailStockageManager) {
        detailStockageManager.setBean(this.getBeanSelected());
    }

    /**
     * Setter pour removeValidator.
     * @param removeValidator le removeValidator à écrire.
     */
    public void setRemoveValidator(final DetailStockageRemoveValidator removeValidator) {
        this.removeValidator = removeValidator;
    }

    /**
     * Getter sur redirectDictionary.
     * @return Retourne le redirectDictionary.
     */
    public Map<TypeProduit, String> getRedirectDictionary() {
        return this.redirectDictionary;
    }
    /**
     * Setter pour redirectDictionary.
     * @param redirectDictionary le redirectDictionary à écrire.
     */
    public void setRedirectDictionary(final Map<TypeProduit, String> redirectDictionary) {
        this.redirectDictionary = redirectDictionary;
    }

    /**
     * Getter sur addValidator.
     * @return Retourne le addValidator.
     */
    DetailStockageValidator getAddValidator() {
        return this.addValidator;
    }

    /**
     * Setter pour addValidator.
     * @param addValidator le addValidator à écrire.
     */
    public void setAddValidator(final DetailStockageValidator addValidator) {
        this.addValidator = addValidator;
    }

    /**
     * Getter sur stockageSelected.
     * @return Retourne le stockageSelected.
     */
    public DetailStockage getStockageSelected() {
        return this.stockageSelected;
    }

    /**
     * Getter sur retourSelected.
     * @return Retourne le retourSelected.
     */
    public DetailStockage getRetourSelected() {
        return this.retourSelected;
    }

    /**
     * Setter pour stockageSelected.
     * @param stockageSelected le stockageSelected à écrire.
     */
    public void setStockageSelected(final DetailStockage stockageSelected) {
        this.stockageSelected = stockageSelected;
    }

    /**
     * Setter pour retourSelected.
     * @param retourSelected le retourSelected à écrire.
     */
    public void setRetourSelected(final DetailStockage retourSelected) {
        this.retourSelected = retourSelected;
    }

    /**
     * Setter pour manager.
     * @param manager le manager à écrire.
     */
    public void setManager(final DetailStockageManager manager) {
        this.manager = manager;
    }

    /**
     * Setter pour produitService.
     * @param produitService Le produitService à écrire.
     */
    public void setProduitService(final ProduitService<Produit> produitService) {
    }

}
