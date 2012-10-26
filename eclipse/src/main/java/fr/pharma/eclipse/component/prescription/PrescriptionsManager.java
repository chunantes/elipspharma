package fr.pharma.eclipse.component.prescription;

import fr.pharma.eclipse.component.BeansManager;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.enums.EtatEssai;
import fr.pharma.eclipse.domain.model.prescription.Prescription;
import fr.pharma.eclipse.exception.ValidationException;

/**
 * Description de la classe.
 
 * @version $Revision$ $Date$
 */
public class PrescriptionsManager
    extends BeansManager<Prescription>
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 7618116404545225666L;

    /**
     * Constructeur.
     * @param searchCriteria Critère de recherche.
     */
    public PrescriptionsManager(final SearchCriteria searchCriteria)
    {
        super(searchCriteria);
    }

    /**
     * Méthode en charge de vérifier que la prescription courante est dispensable.
     */
    public void validForDispensation()
    {
        if (this.getBeanSelected()
                .getEssai()
                .getDetailDonneesPharma()
                .getInfosDispensations()
                .getTypeDispensation() == null)
        {
            this.setBeanSelected(null);
            throw new ValidationException("prescription.typeDispensation",
                                          new String[]
                                          {"notEmpty" });
        }
        if (!this.getBeanSelected().getEssai().getEtat().equals(EtatEssai.EN_COURS))
        {
            this.setBeanSelected(null);
            throw new ValidationException("prescription.etatEssai",
                                          new String[]
                                          {"notValid" });
        }
    }
}
