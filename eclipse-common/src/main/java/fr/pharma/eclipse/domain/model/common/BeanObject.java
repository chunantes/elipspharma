package fr.pharma.eclipse.domain.model.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import fr.pharma.eclipse.domain.model.common.constants.BeanCstes;

/**
 * Classe commune des objets métiers.
 
 * @version $Revision$ $Date$
 */
@MappedSuperclass
public abstract class BeanObject
    implements Serializable
{
    /**
     * Serial Id.
     */
    private static final long serialVersionUID = 8082772436164226894L;

    /**
     * Identifiant technique de l'objet.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    /**
     * Sélection.<br />
     * ATTENTION : si la même instance d'objet est partagée dans deux listes d'objets
     * sélectionnables utilisées en même temps, la sélection dans une liste peut impacter la
     * deuxième !
     */
    @Transient
    private Boolean selected = Boolean.FALSE;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj)
    {
        if (!(obj instanceof BeanObject))
        {
            return false;
        }

        final BeanObject other = (BeanObject) obj;

        if ((other == null)
            || (this.getId() == null)
            || (other.getId() == null))
        {
            return false;
        }

        if (other == this)
        {
            return true;
        }

        final String otherCle = other.getId().toString();
        final String thisCle = this.getId().toString();

        if (otherCle.equalsIgnoreCase(thisCle))
        {
            return true;
        }

        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode()
    {
        if (this.getId() != null)
        {
            return (BeanCstes.NB_PREMIER + this.getId().hashCode())
                   * BeanCstes.NB_PREMIER;
        }
        else
        {
            return super.hashCode();
        }
    }

    /**
     * Getter pour id.
     * @return Retourne le id.
     */
    public Long getId()
    {
        return this.id;
    }

    /**
     * Setter pour id.
     * @param id le id à écrire.
     */
    public void setId(final Long id)
    {
        this.id = id;
    }

    /**
     * Getter sur selected.
     * @return Retourne le selected.
     */
    public Boolean getSelected()
    {
        return this.selected;
    }

    /**
     * Setter pour selected.
     * @param selected le selected à écrire.
     */
    public void setSelected(final Boolean selected)
    {
        this.selected = selected;
    }

}
