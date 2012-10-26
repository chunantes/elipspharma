package fr.pharma.eclipse.service.dispensation.processor.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import fr.pharma.eclipse.domain.model.dispensation.ConseilDispensation;
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;
import fr.pharma.eclipse.service.dispensation.processor.ConseilDispensationProcessor;
import fr.pharma.eclipse.service.dispensation.processor.helper.FrequenceHelper;

/**
 * Description de la classe.
 
 * @version $Revision$ $Date$
 */
public abstract class GenericConseilDispensationProcessor
    implements ConseilDispensationProcessor, Serializable
{
    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -7388410771092362168L;

    /**
     * Helper gerant les frequences.
     */
    @Resource(name = "frequenceHelper")
    protected FrequenceHelper frequenceHelper;

    public ConseilDispensation process(final ProduitPrescrit produitPrescrit)
    {
        final ConseilDispensation conseil = new ConseilDispensation();
        conseil.setProduitPrescrit(produitPrescrit);
        final Integer nbASortir = this.processNbASortir(produitPrescrit);
        if (nbASortir == null)
        {
            return null;
        }
        else
        {
            conseil.setNbASortir(nbASortir);
        }
        return conseil;
    }

    /**
     * Méthode en charge de calculer le nombre à sortir.
     * @param produitPrescrit Le produitPrescrit.
     * @return Le nombre d'unités à sortir.
     */
    protected abstract Integer processNbASortir(ProduitPrescrit produitPrescrit);

    /**
     * Setter pour frequenceHelper.
     * @param frequenceHelper le frequenceHelper à écrire.
     */
    public void setFrequenceHelper(final FrequenceHelper frequenceHelper)
    {
        this.frequenceHelper = frequenceHelper;
    }

}
