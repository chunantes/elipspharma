package fr.pharma.eclipse.domain.model.design.embedded;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import fr.pharma.eclipse.domain.enums.design.UniteTemps;

/**
 * Classe modèle représentant un objet temps de la prescription (Début et durée). Il aggrège un
 * nombre et un type de temps.
 
 * @version $Revision$ $Date$
 */
@Embeddable
public class TempsPrescription
    implements Serializable
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 3957655497719005260L;

    /**
     * Nombre d'unité de temps.
     */
    @Column(name = "nb")
    private Integer nb;

    /**
     * Unité.
     */
    @Column(name = "unite")
    @Enumerated(EnumType.STRING)
    private UniteTemps unite;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        final StringBuffer buff = new StringBuffer();
        if (this.getNb() != null)
        {
            buff.append(this.nb);
        }
        if (this.getUnite() != null)
        {
            buff.append(" ");
            buff.append(this.unite.getLibelleCourt());
        }
        return buff.toString();
    }

    /**
     * Getter sur nb.
     * @return Retourne le nb.
     */
    public Integer getNb()
    {
        return this.nb;
    }

    /**
     * Setter pour nb.
     * @param nb le nb à écrire.
     */
    public void setNb(final Integer nb)
    {
        this.nb = nb;
    }

    /**
     * Getter sur unite.
     * @return Retourne le unite.
     */
    public UniteTemps getUnite()
    {
        return this.unite;
    }

    /**
     * Setter pour unite.
     * @param unite le unite à écrire.
     */
    public void setUnite(final UniteTemps unite)
    {
        this.unite = unite;
    }

}
