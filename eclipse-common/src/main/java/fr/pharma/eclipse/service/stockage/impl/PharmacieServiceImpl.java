package fr.pharma.eclipse.service.stockage.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.dao.search.AclSearchDao;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.criteria.stockage.PharmacieSearchCriteria;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.domain.model.stockage.Stockage;
import fr.pharma.eclipse.domain.model.suivi.stockage.PharmacieSuivi;
import fr.pharma.eclipse.factory.suivi.SuiviFactory;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.service.stockage.PharmacieService;
import fr.pharma.eclipse.validator.remove.impl.StockageRemoveValidator;
import fr.pharma.eclipse.validator.save.impl.PharmacieSaveValidator;

/**
 * Classe d'implémentation du service de gestion de pharmacie.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PharmacieServiceImpl extends GenericServiceImpl<Pharmacie> implements PharmacieService {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -5057989242884040793L;

    /**
     * Factory de suivi de pharmacie.
     */
    @Resource(name = "pharmacieSuiviFactory")
    private SuiviFactory<PharmacieSuivi> pharmacieSuiviFactory;

    /**
     * Validator de sauvegarde de pharmacie.
     */
    @Resource(name = "pharmacieSaveValidator")
    private PharmacieSaveValidator pharmacieSaveValidator;

    /**
     * Validateur de suppression d'un stockage.
     */
    @Resource(name = "stockageRemoveValidator")
    private StockageRemoveValidator stockageRemoveValidator;

    /**
     * DAO des acls.
     */
    @Autowired
    private AclSearchDao aclSearchDao;

    /**
     * Constructeur.
     * @param pharmacieDao Dao de gestion des pharmacies.
     */
    public PharmacieServiceImpl(final GenericDao<Pharmacie> pharmacieDao) {
        super(pharmacieDao);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Pharmacie> getAll() {
        return this.getAll(new PharmacieSearchCriteria());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Pharmacie> getAllSansAcl(final SearchCriteria searchCriteria) {
        final PharmacieSearchCriteria pharmacieSearchCriteria = (PharmacieSearchCriteria) searchCriteria;
        pharmacieSearchCriteria.setWithAcl(false);
        return this.getAll(pharmacieSearchCriteria);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pharmacie save(final Pharmacie pharmacie) {
    	
    	final Pharmacie pharmacieToSave = this.reattach(pharmacie);
    	
        // Validation de la pharmacie
        this.pharmacieSaveValidator.validate(pharmacieToSave, this);

        // Création du suivi       
        final PharmacieSuivi pharmacieSuivi = this.pharmacieSuiviFactory.getInitializedObject();
        pharmacieSuivi.setPharmacie(pharmacieToSave);
        pharmacieToSave.getModifs().add(pharmacieSuivi);

        // Sauvegarde de la pharmacie
        boolean addPharma = false;
        if (pharmacieToSave.getId() == null) {
            addPharma = true;
        }
        final Pharmacie pharmacieSaved = super.save(pharmacieToSave);

        // Ajout des acls des pharmacies pour les admins
        if (addPharma) {
            this.aclSearchDao.saveAclsPharmaciesForAdmins(pharmacieSaved.getId());
        }

        return pharmacieSaved;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(final Pharmacie pharmacie) {
        super.remove(pharmacie);
        this.aclSearchDao.removeAclsPharmaciesOfPharma(pharmacie.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeStockage(final Pharmacie pharmacie,
                               final Stockage stockage) {
        // Suppression du stockage et de tous ses enfants dans la liste des
        // stockages de la pharmacie
        final List<Stockage> stockagesToDelete = new ArrayList<Stockage>();
        this.stockageRemoveValidator.validate(stockage);
        stockagesToDelete.add(stockage);
        this.getStockagesChildren(stockage, stockagesToDelete);
        for (final Stockage stockageToDelete : stockagesToDelete) {

            this.removeStockage(pharmacie.getStockages(), stockageToDelete);
        }

        // MAJ de la liste des enfants du parent si le stockage a un parent
        if (stockage.getParent() != null) {
            this.removeStockage(stockage.getParent().getEnfants(), stockage);
        }
    }

    /**
     * Méthode en charge d'ajouter dans la liste de stockage fournis en
     * paramètres tous les enfants du stockage.
     * @param stockage Stockage à analyser.
     * @param stockages Liste de stockages à compléter avec tous les enfants.
     */
    final void getStockagesChildren(final Stockage stockage,
                                    final List<Stockage> stockages) {
        final Iterator<Stockage> it = stockage.getEnfants().iterator();
        while (it.hasNext()) {
            final Stockage s = it.next();
            stockages.add(s);
            this.getStockagesChildren(s, stockages);
        }
    }

    /**
     * Méthode en charge de supprimer un stockage dans une liste de stockages.
     * @param stockages Liste de stockages.
     * @param stockageToDelete Stockage à supprimer.
     */
    public void removeStockage(final SortedSet<Stockage> stockages,
                               final Stockage stockageToDelete) {
        final Iterator<Stockage> it = stockages.iterator();
        while (it.hasNext()) {
            final Stockage stock = it.next();

            if (this.isMatching(stockageToDelete, stock)) {
                it.remove();
                break;
            }
        }
    }

    /**
     * Méthode en charge de déterminer la correspondance entre 2 beans de
     * stockage.
     * @param stockage1 Stockage1.
     * @param stockage2 Stockage2.
     * @return résultat de la correspondance.
     */
    protected boolean isMatching(final Stockage stockage1,
                                 final Stockage stockage2) {
        final boolean egaliteId = stockage1.getId() != null && stockage1.getId().equals(stockage2.getId());

        final boolean egaliteReference = stockage1 == stockage2;
        return egaliteId || egaliteReference;
    }

    /**
     * Setter pour pharmacieSuiviFactory.
     * @param pharmacieSuiviFactory le pharmacieSuiviFactory à écrire.
     */
    public void setPharmacieSuiviFactory(final SuiviFactory<PharmacieSuivi> pharmacieSuiviFactory) {
        this.pharmacieSuiviFactory = pharmacieSuiviFactory;
    }

    /**
     * Setter pour pharmacieSaveValidator.
     * @param pharmacieSaveValidator le pharmacieSaveValidator à écrire.
     */
    public void setPharmacieSaveValidator(final PharmacieSaveValidator pharmacieSaveValidator) {
        this.pharmacieSaveValidator = pharmacieSaveValidator;
    }

    /**
     * Setter pour stockageRemoveValidator.
     * @param stockageRemoveValidator Le stockageRemoveValidator à écrire.
     */
    public void setStockageRemoveValidator(final StockageRemoveValidator stockageRemoveValidator) {
        this.stockageRemoveValidator = stockageRemoveValidator;
    }

    /**
     * Setter pour aclSearchDao.
     * @param aclSearchDao Le aclSearchDao à écrire.
     */
    public void setAclSearchDao(final AclSearchDao aclSearchDao) {
        this.aclSearchDao = aclSearchDao;
    }

}
