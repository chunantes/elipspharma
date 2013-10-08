package fr.pharma.eclipse.factory.dispensation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.BooleanUtils;

import fr.pharma.eclipse.domain.enums.TypeElementToCheck;
import fr.pharma.eclipse.domain.enums.produit.TypeProduit;
import fr.pharma.eclipse.domain.model.dispensation.Dispensation;
import fr.pharma.eclipse.domain.model.dispensation.ElementToCheck;
import fr.pharma.eclipse.domain.model.prescription.Prescription;
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.factory.common.BeanObjectFactory;
import fr.pharma.eclipse.utils.introspection.BeanTool;

/**
 * Factory pour Dispensation.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DispensationFactory extends BeanObjectFactory<Dispensation> {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -4983338082003592041L;

    /**
     * Factory ElementToCheck.
     */
    @Resource(name = "elementToCheckFactory")
    private BeanObjectFactory<ElementToCheck> elementToCheckFactory;

    /**
     * Map contenant les champs à checker avant de dispenser en fonction du type
     * de produit.
     */
    private Map<TypeProduit, List<String>> mapForCheck = new HashMap<TypeProduit, List<String>>();

    /**
     * Construteur.
     * @param bean Bean.
     */
    public DispensationFactory(final Class<Dispensation> bean) {
        super(bean);
    }

    /**
     * Méthode en charge d'initialiser un dispensation à partir d'une
     * prescription.
     * @param prescription La prescription
     * @return La dispensation initialisée.
     */
    public Dispensation getInitializedObject(final Prescription prescription) {
        final Dispensation dispensation = super.getInitializedObject();
        dispensation.setPrescription(prescription);
        dispensation.setDispense(false);
        this.initElementsToCheck(dispensation);
        return dispensation;
    }

    /**
     * Méthode en charge d'initialiser les elements à vérifier lors de la phase
     * de dispensation.
     * @param dispensation L'objet Dispensation.
     */
    private void initElementsToCheck(final Dispensation dispensation) {

        ElementToCheck toCheck = null;
        // Pour chaque produit prescrit, on regarde le produit.
        for (final ProduitPrescrit p : dispensation.getPrescription().getProduitsPrescrits()) {
            final Produit produit = p.getProduit();
            for (final String s : this.mapForCheck.get(produit.getType())) {
                final Boolean value = (Boolean) BeanTool.getPropriete(produit, TypeElementToCheck.valueOf(s).getLibelle());
                if (BooleanUtils.isTrue(value)) {
                    toCheck = this.elementToCheckFactory.getInitializedObject();
                    toCheck.setChecked(false);
                    toCheck.setProduitPrescrit(p);
                    toCheck.setType(TypeElementToCheck.valueOf(s));
                    toCheck.setDispensation(dispensation);
                    toCheck.setNomChamps(TypeElementToCheck.valueOf(s).getLibelle());
                    dispensation.getElementsToCheck().add(toCheck);
                }
            }
        }
    }

    /**
     * Setter pour elementToCheckFactory.
     * @param elementToCheckFactory le elementToCheckFactory à écrire.
     */
    public void setElementToCheckFactory(final BeanObjectFactory<ElementToCheck> elementToCheckFactory) {
        this.elementToCheckFactory = elementToCheckFactory;
    }

    /**
     * Setter pour mapForCheck.
     * @param mapForCheck le mapForCheck à écrire.
     */
    public void setMapForCheck(final Map<TypeProduit, List<String>> mapForCheck) {
        this.mapForCheck = mapForCheck;
    }
}
