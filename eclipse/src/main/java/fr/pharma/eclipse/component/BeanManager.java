package fr.pharma.eclipse.component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

import javax.faces.model.DataModel;

import fr.pharma.eclipse.component.helper.BeanManagerHelper;
import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.utils.introspection.BeanTool;

/**
 * Manager générique d'un BEAN métier.
 * @author Netapsys
 * @param <BEAN> Bean Objet Métier.
 * @version $Revision$ $Date$
 */
public class BeanManager<BEAN extends BeanObject> implements Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -5403188788966845906L;

    /**
     * Bean à manager.
     */
    private BEAN bean;

    /**
     * Service.
     */
    private final GenericService<BEAN> service;

    /**
     * Helper.
     */
    private BeanManagerHelper<BEAN> helper;

    /**
     * Constructeur.
     * @param service Service de gestion du BEAN.
     */
    public BeanManager(final GenericService<BEAN> service) {
        this.service = service;
    }

    /**
     * Méthode en charge de retourner une liste associée au bean. (Fait un
     * reattach.)
     * @param name Nom de la liste de SortedSet associée an bean.
     * @return DataModel pour l'affichage des données de la liste.
     */
    public DataModel<BEAN> getList(final String name) {
        return this.getList(name, true);
    }

    /**
     * Méthode en charge de retourner une liste associée au bean. (Fait un
     * reattach.)
     * @param name Nom de la liste de SortedSet associée an bean.
     * @return DataModel pour l'affichage des données de la liste.
     */
    @SuppressWarnings("unchecked")
    public List<BEAN> getListReattach(final String name) {
        this.reattach();
        final SortedSet<BEAN> listSet = (SortedSet<BEAN>) BeanTool.getPropriete(this.bean, name);
        final List<BEAN> list = new ArrayList<BEAN>();
        list.addAll(listSet);
        return list;
    }

    /**
     * Méthode en charge de retourner une liste associée au bean. (Fait un
     * reattach.)
     * @param needReattach Booléen qui indique si un reattach doit être fait sur
     * le bean attaché au manager.
     * @param name Nom de la liste de SortedSet associée an bean.
     * @return DataModel pour l'affichage des données de la liste.
     */
    @SuppressWarnings("unchecked")
    public DataModel<BEAN> getList(final String name,
                                   final boolean needReattach) {
        if (needReattach) {
            this.reattach();
        }
        final SortedSet<BEAN> listSet = (SortedSet<BEAN>) BeanTool.getPropriete(this.bean, name);
        return this.getHelper().returnAsDataModel(listSet);
    }

    /**
     * Réattache le bean managé à la session.
     */
    public void reattach() {
        this.bean = this.service.reattach(this.bean);
    }

    /**
     * Getter sur bean.
     * @return Retourne le bean.
     */
    public BEAN getBean() {
        return this.bean;
    }

    /**
     * Méthode de récupération du bean réattaché.
     * @return Retourne le bean réattaché.
     */
    public BEAN getBeanAfterReattach() {
        this.reattach();
        return this.getBean();
    }

    /**
     * Setter pour bean.
     * @param bean le bean à écrire.
     */
    public void setBean(final BEAN bean) {
        this.bean = bean;
    }

    /**
     * Getter sur service.
     * @return Retourne le service.
     */
    public GenericService<BEAN> getService() {
        return this.service;
    }

    /**
     * Getter sur helper.
     * @return Retourne le helper.
     */
    public BeanManagerHelper<BEAN> getHelper() {
        return this.helper;
    }

    /**
     * Setter pour helper.
     * @param helper le helper à écrire.
     */
    public void setHelper(final BeanManagerHelper<BEAN> helper) {
        this.helper = helper;
    }

}
