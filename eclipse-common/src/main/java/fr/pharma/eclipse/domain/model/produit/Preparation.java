package fr.pharma.eclipse.domain.model.produit;

import javax.persistence.Entity;

/**
 * Classe métier représentant une Préparation.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "preparation")
public class Preparation extends Produit {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -4510945145610043393L;

    /**
     * {@inheritDoc}
     */
    @Override
    public Produit cloneMe() {
        final Preparation preparation = new Preparation();

        // Clonage de la super classe.
        super.cloneMe(preparation);

        return preparation;
    }

}
