package fr.pharma.eclipse.domain.model.sir.common;

import java.io.Serializable;

import fr.pharma.eclipse.domain.model.common.constants.BeanCstes;

/**
 * Classe commune des objets métiers de SIR.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public abstract class BeanSirObject implements Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -4456948906370191100L;

    /**
     * Permet de setter l'identifiant de l'objet.
     * @param id Identifiant.
     */
    public abstract void setId(final Integer id);

    /**
     * Permet de retourner l'identifiant de l'objet.
     * @return Identifiant de l'objet.
     */
    public abstract Integer getId();

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof BeanSirObject)) {
            return false;
        }

        final BeanSirObject other = (BeanSirObject) obj;

        if ((other == null) || (this.getId() == null) || (other.getId() == null)) {
            return false;
        }

        if (other == this) {
            return true;
        }

        final String otherCle = other.getId().toString();
        final String thisCle = this.getId().toString();

        if (otherCle.equalsIgnoreCase(thisCle)) {
            return true;
        }

        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        if (this.getId() != null) {
            return (BeanCstes.NB_PREMIER + this.getId().hashCode()) * BeanCstes.NB_PREMIER;
        } else {
            return super.hashCode();
        }
    }

}
