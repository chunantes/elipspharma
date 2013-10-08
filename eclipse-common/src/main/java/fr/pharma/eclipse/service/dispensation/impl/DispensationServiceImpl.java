package fr.pharma.eclipse.service.dispensation.impl;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.springframework.transaction.annotation.Transactional;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.dao.search.DispensationSearchDao;
import fr.pharma.eclipse.domain.criteria.dispensation.DispensationSearchCriteria;
import fr.pharma.eclipse.domain.dto.DispensationDTO;
import fr.pharma.eclipse.domain.model.dispensation.Dispensation;
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;
import fr.pharma.eclipse.domain.model.stock.LigneStock;
import fr.pharma.eclipse.domain.model.stock.Sortie;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.service.dispensation.DispensationService;
import fr.pharma.eclipse.service.prescription.PrescriptionService;
import fr.pharma.eclipse.service.stock.StockService;
import fr.pharma.eclipse.service.stockage.PharmacieService;
import fr.pharma.eclipse.utils.constants.EclipseConstants;
import fr.pharma.eclipse.validator.save.impl.DispensationSaveValidator;

/**
 * Implémentation des services liés à la Dispensation.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DispensationServiceImpl extends GenericServiceImpl<Dispensation> implements DispensationService {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 7562214176714485455L;

    /**
     * Validator de sauvegarde de Prescription.
     */
    @Resource(name = "dispensationSaveValidator")
    private DispensationSaveValidator dispensationSaveValidator;

    /**
     * Service de gestion des prescriptions.
     */
    @Resource(name = "prescriptionService")
    private PrescriptionService prescriptionService;

    /**
     * Service de gestion de stock.
     */
    @Resource(name = "stockService")
    private StockService stockService;

    /**
     * Service pharmacie.
     */
    @Resource(name = "pharmacieService")
    private PharmacieService pharmacieService;

    /**
     * DAO de recherche de dispensations.
     */
    @Resource(name = "dispensationSearchDao")
    private DispensationSearchDao dispensationSearchDao;

    /**
     * Constructeur.
     * @param genericDao Dao.
     */
    public DispensationServiceImpl(final GenericDao<Dispensation> genericDao) {
        super(genericDao);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dispensation save(final Dispensation object) {
        // si c'est une action de dispensation et pas de sauvegarde simple, on
        // valide la
        // dispensation.
        if (object.getDispense()) {
            this.dispensationSaveValidator.validate(object, this);
        }
        object.setDateDispensation(Calendar.getInstance(Locale.FRANCE));
        return this.getGenericDao().save(object);
    }

    @Transactional
    @Override
    public Dispensation dispenser(final Dispensation object,
                                  final List<Sortie> sorties) {
        this.prescriptionService.save(object.getPrescription());
        object.setDispense(Boolean.TRUE);

        for (final Sortie sortie : sorties) {
            final List<LigneStock> ligneStocks = sortie.getLignesStockCompletees();
            for (final LigneStock ligneStock : ligneStocks) {
                final LigneStock l = this.stockService.get(ligneStock.getId());
                if (ligneStock.getDotation()) {
                    ligneStock.setQteDispensationGlobal(l.getQteDispensationGlobal() - ligneStock.getQteASortir());
                } else {
                    ligneStock.setQteGlobalStock(l.getQteGlobalStock() - ligneStock.getQteASortir());
                }
            }
            this.stockService.saveAll(ligneStocks);
        }
        return this.save(object);
    }

    @Override
    public void validRemoveProduitPrescrit(final ProduitPrescrit p) {
        if (!p.getDispensationProduits().isEmpty()) {
            throw new ValidationException("prescriptions.produits", new String[]{"notEmpty" }, null);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dispensation genererNumOrdonnancier(final Dispensation object) {
        final Pharmacie pharmacie = object.getPharmacie();

        Integer numOrdonnancier = pharmacie.getNumOrdonnancierDisp();
        object.setNumOrdonnancier(++numOrdonnancier);
        pharmacie.setNumOrdonnancierDisp(numOrdonnancier);

        this.pharmacieService.save(pharmacie);
        return this.save(object);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Dispensation> getAll() {
        return this.getAll(new DispensationSearchCriteria());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dispensation loadDispensation(final Dispensation d) {
        final Dispensation res = this.get(d.getId());
        if (res.getPrescription() != null && !Hibernate.isInitialized(res.getPrescription())) {
            Hibernate.initialize(res.getPrescription());
            if (res.getPrescription().getInclusion() != null && !Hibernate.isInitialized(res.getPrescription().getInclusion())) {
                Hibernate.initialize(res.getPrescription().getInclusion());
            }
            if (res.getPrescription().getInclusion().getPatient() != null && !Hibernate.isInitialized(res.getPrescription().getInclusion().getPatient())) {
                Hibernate.initialize(res.getPrescription().getInclusion().getPatient());
            }
        }
        return res;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DispensationDTO> retrieveResults(final DispensationSearchCriteria criteria) {
        final List<DispensationDTO> dispensationDTOs =
            this.dispensationSearchDao.findByEssaiAndPatientAndDispenseOrderByIdAsc(criteria.getEssaiDTO(), criteria.getPatient(), criteria.getDispense());

        // Récupération des produits
        for (final DispensationDTO dispensationDTO : dispensationDTOs) {

            final StringBuffer sb = new StringBuffer();

            final List<Object[]> dispensationsProduits = this.dispensationSearchDao.findProduitsByIdDispensation(dispensationDTO.getId());

            for (final Object[] dispensationProduit : dispensationsProduits) {
                final String denominationProduit = (String) dispensationProduit[0];
                final String numLot = (String) dispensationProduit[1];
                final String numTraitement = (String) dispensationProduit[2];
                final Integer quantite = (Integer) dispensationProduit[3];
                final String prenomPersonne = (String) dispensationProduit[4];
                final String nomPersonne = (String) dispensationProduit[5];
                sb.append(denominationProduit).append(" - ").append(numLot);
                if (numTraitement != null) {
                    sb.append(" - ").append(numTraitement);
                } else {
                    sb.append(" - ").append(EclipseConstants.NON_APPLICABLE);
                }
                sb.append(" - ").append(quantite);
                sb.append(" \r");
                dispensationDTO.setRealisePar(prenomPersonne + EclipseConstants.SPACE + nomPersonne);
            }
            dispensationDTO.setDescriptionProduits(sb.toString());
        }
        return dispensationDTOs;
    }

    /**
     * Setter pour dispensationSaveValidator.
     * @param dispensationSaveValidator le dispensationSaveValidator à écrire.
     */
    public void setDispensationSaveValidator(final DispensationSaveValidator dispensationSaveValidator) {
        this.dispensationSaveValidator = dispensationSaveValidator;
    }

    /**
     * Setter pour pharmacieService.
     * @param pharmacieService Le pharmacieService à écrire.
     */
    public void setPharmacieService(final PharmacieService pharmacieService) {
        this.pharmacieService = pharmacieService;
    }

    public void setPrescriptionService(final PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

}
