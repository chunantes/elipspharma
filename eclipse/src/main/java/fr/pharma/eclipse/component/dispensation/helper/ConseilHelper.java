package fr.pharma.eclipse.component.dispensation.helper;

import java.io.Serializable;

import javax.annotation.Resource;

import fr.pharma.eclipse.domain.model.dispensation.ConseilDispensation;
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;
import fr.pharma.eclipse.service.dispensation.builder.ConseilDispensationBuilder;

/**
 * Helper au manager DispensationManager en charge de construire les messages de
 * conseils.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ConseilHelper implements Serializable {

    /**
     * COnstructeur de conseil à la dispensation.
     */
    @Resource(name = "conseilDispensationBuilder")
    private ConseilDispensationBuilder conseilBuilder;

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -1517064793036842894L;

    /**
     * Méthode en charge de construire le message du conseil à la dispensation.
     * @param produitPrescrit Le produit prescrit.
     * @return Le message.
     */
    public String buildConseil(final ProduitPrescrit produitPrescrit)

    {
        String conseilText = "";
        ConseilDispensation conseil = null;
        if (this.conseilBuilder.support(produitPrescrit)) {
            conseil = this.conseilBuilder.build(produitPrescrit);
            if (conseil != null) {
                conseilText = this.conseilBuilder.format(conseil);
            }
        }
        return conseilText;
    }

    /**
     * Setter pour builder.
     * @param builder le builder à écrire.
     */
    public void setBuilder(final ConseilDispensationBuilder builder) {
        this.conseilBuilder = builder;
    }

}
