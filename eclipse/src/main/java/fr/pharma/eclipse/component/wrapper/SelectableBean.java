package fr.pharma.eclipse.component.wrapper;

import java.io.Serializable;

import fr.pharma.eclipse.domain.model.common.BeanObject;

/**
 * Wrapper de BeanObject pour l'ajout de la sélection.
 * @param <BEAN> Type d'objet générique.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class SelectableBean<BEAN extends BeanObject> implements Serializable {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -4691661712605128745L;

    /**
     * Objet métier wrappé.
     */
    private final BEAN bean;

    /**
     * Sélection.
     */
    private Boolean selected = Boolean.FALSE;

    /**
     * Constructeur.
     * @param bean Objet métier wrappé.
     */
    public SelectableBean(final BEAN bean) {
        this.bean = bean;
    }

    /**
     * Getter sur bean.
     * @return Retourne le bean.
     */
    public BEAN getBean() {
        return this.bean;
    }

    /**
     * Getter sur selected.
     * @return Retourne le selected.
     */
    public Boolean getSelected() {
        return this.selected;
    }

    /**
     * Setter pour selected.
     * @param selected le selected à écrire.
     */
    public void setSelected(final Boolean selected) {
        this.selected = selected;
    }

}
