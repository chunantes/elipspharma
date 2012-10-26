package fr.pharma.eclipse.component.essai;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import org.jfree.util.Log;

import fr.pharma.eclipse.component.SelectableBeansManager;
import fr.pharma.eclipse.component.essai.helper.DroitHabilitationInitializer;
import fr.pharma.eclipse.component.essai.helper.SelectableContactsRetriever;
import fr.pharma.eclipse.component.helper.BeanManagerHelper;
import fr.pharma.eclipse.component.wrapper.SelectableBean;
import fr.pharma.eclipse.domain.enums.Droit;
import fr.pharma.eclipse.domain.enums.GroupeContacts;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.contact.DetailContacts;
import fr.pharma.eclipse.domain.model.habilitation.Habilitation;
import fr.pharma.eclipse.factory.habilitation.HabilitationFactory;
import fr.pharma.eclipse.factory.wrapper.SelectableBeanFactory;
import fr.pharma.eclipse.service.habilitation.helper.HabilitationsHelper;
import fr.pharma.eclipse.service.user.UserService;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe en charge de manager les contacts d'un essai.
 
 * @version $Revision$ $Date$
 */
public class ContactsManager
    implements Serializable
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 2907141277690978782L;

    /**
     * Helper.
     */
    private BeanManagerHelper<BeanObject> helper;

    /**
     * Helper pour la récupération des contacts sélectionnables.
     */
    private SelectableContactsRetriever contactsRetriever;

    /**
     * Service des utilisateurs.
     */
    private UserService userService;

    /**
     * Fabrique d'habilitations.
     */
    private HabilitationFactory habilitationFactory;

    /**
     * Helper de gestiond es habilitations.
     */
    private HabilitationsHelper habilitationsHelper;

    /**
     * Fabrique d'object Selectable.
     */
    private SelectableBeanFactory<Personne> selectableBeanFactory;

    /**
     * Map des initialiseurs de droit d'habilitation par type de contact. (Clé : name de la valeur
     * de l'énumération de TypeContact.)
     */
    private Map<String, DroitHabilitationInitializer> initializers;

    /**
     * Manager des contacts sélectionnables (modifié dans l'IHM).<br>
     * On utilise un SelectableBeansManager et non un BeansManager pour avoir deux niveaux de
     * sélection :<br>
     * - selectableContactsManager.selected : donne la sélection des contacts.<br>
     * -selectableContactsManager.bean.selected : donne un second niveau de sélection (utilisé
     * pour l'attribution des droits pour distinguer Investigateur Co de Investigateur principal
     * notamment).
     */
    private SelectableBeansManager<Personne> selectableContactsManager;

    /**
     * Type de contact à ajouter (modifié dans l'IHM).
     */
    private TypeContact typeContactToAdd;

    /**
     * Habilitation sélectionnée pour l'affichage des informations.
     */
    private Habilitation selectedHabilitation;

    /**
     * Présence d'un investigateur principal.
     */
    private Boolean investigateurPrincipal;

    /**
     * Méthode en charge de retourner les habilitations d'un groupe.
     * @param essai Essai.
     * @param groupe Groupe de contact à l'écran.
     * @return L'ensemble des habilitations du groupe.
     */
    public Set<Habilitation> getGroupeHabilitations(final Essai essai,
                                                    final GroupeContacts groupe)
    {
        return this.habilitationsHelper.getHabilitations(essai,
                                                         groupe.getDroits());
    }

    /**
     * Méthode qui indique si l'essai possède un investigateur principal.
     * @param essai Essai à inspecter.
     * @return true ssi l'essai possède une habilitation avec un droit Investigateur principal.
     */
    public boolean hasInvestigateurPrincipal(final Essai essai)
    {
        this.investigateurPrincipal =
            this.habilitationsHelper.getInvestigateurPrincipal(essai) != null;
        return this.investigateurPrincipal;
    }

    /**
     * Méthode qui indique si l'essai possède un investigateur principal en sondant la liste
     * d'ahabilitation.
     * @param essai L'essai.
     * @return true ssi l'essai possède une habilitation avec un droit Investigateur principal.
     */
    public boolean hasInvestigateurPrincipalInList(final EssaiManager essaiManager)
    {
        Boolean result = false;
        for (final Habilitation h : essaiManager.getGroupeInvestigateurs())
        {
            if (h.getDroit().equals(Droit.INVESTIGATEUR_PRINCIPAL))
            {
                result = true;
            }
        }
        this.investigateurPrincipal = result;
        return result;
    }

    /**
     * Méthode en charge de désactiver les contacts sélectionnés d'un essai.
     * @param essai Essai à inspecter.
     */
    public void disableContacts(final Essai essai)
    {
        final DetailContacts detailContact = essai.getDetailContacts();
        final Personne personneConnectee = this.userService.getPersonne();
        this.disableContacts(detailContact.getHabilitations(),
                             personneConnectee);
    }

    /**
     * Méthode en charge d'initialiser la liste des contacts sélectionnables.
     * @param essai Essai à inspecter.
     */
    public void initSelectableContacts(final Essai essai)
    {
        this.selectableContactsManager.getBeans().clear();
        this.selectableContactsManager.resetBeanSelected();
        this.selectableContactsManager
                .getBeans()
                .addAll(this.selectableBeanFactory.getInitializedObjects(this.contactsRetriever
                        .retrieveSelectableContacts(essai,
                                                    this.typeContactToAdd)));
    }

    /**
     * Méthode en charge de valider l'ajout des contacts sélectionnés dans le manager.
     * @param essai Essai sur lequel on ajoute les contacts.
     */
    public void ajouterContacts(final Essai essai)
    {
        final DroitHabilitationInitializer initializer =
            this.initializers.get(this.typeContactToAdd.name());
        if (initializer == null)
        {
            Log.error(new StringBuilder("[ajouterContacts] ")
                    .append("Aucun initialiseur pour le type de contact '")
                    .append(this.typeContactToAdd)
                    .append("'=> pas d'ajout de contact possible!")
                    .toString());
            return;
        }

        for (final SelectableBean<Personne> nouveauContact : this.selectableContactsManager
                .getBeansSelected())
        {
            final Habilitation habilitation = this.habilitationFactory.getInitializedObject();
            habilitation.setPersonne(nouveauContact.getBean());
            habilitation.setDetailContacts(essai.getDetailContacts());
            initializer.initialize(habilitation);
            essai.getDetailContacts().getHabilitations().add(habilitation);
        }
        this.reset();
    }

    /**
     * Méthode en charge de réinitialiser le manager.
     */
    public void reset()
    {
        this.selectableContactsManager.getBeans().clear();
        this.selectedHabilitation = null;
    }

    /**
     * Méthode en charge de désactiver les habilitations sélectionnées d'une liste donnée.
     * @param habilitations Liste des habilitations à traiter.
     * @param personneConnectee Personne connectée.
     */
    private void disableContacts(final SortedSet<Habilitation> habilitations,
                                 final Personne personneConnectee)
    {
        for (final Habilitation habilitation : this.helper.getBeansSelected(habilitations))
        {
            habilitation.setActive(false);
            habilitation.setDateDesactivation(Calendar.getInstance(EclipseConstants.LOCALE));
            habilitation.setAuteurDesactivation(personneConnectee.getLogin());
            habilitation.setSelected(false);
        }
    }

    public void updateDroit(final Essai essai,
                            final String droit)
    {
        this.getSelectedHabilitation().setDroit(Droit.valueOf(droit));
        this.hasInvestigateurPrincipal(essai);

    }

    /** ***************** **/
    /** GETTERS / SETTERS **/
    /** ***************** **/
    /**
     * Getter sur helper.
     * @return Retourne le helper.
     */
    BeanManagerHelper<BeanObject> getHelper()
    {
        return this.helper;
    }

    /**
     * Setter pour helper.
     * @param helper le helper à écrire.
     */
    public void setHelper(final BeanManagerHelper<BeanObject> helper)
    {
        this.helper = helper;
    }

    /**
     * Getter sur userService.
     * @return Retourne le userService.
     */
    UserService getUserService()
    {
        return this.userService;
    }

    /**
     * Setter pour userService.
     * @param userService le userService à écrire.
     */
    public void setUserService(final UserService userService)
    {
        this.userService = userService;
    }

    /**
     * Getter sur typeContactToAdd.
     * @return Retourne le typeContactToAdd.
     */
    public TypeContact getTypeContactToAdd()
    {
        return this.typeContactToAdd;
    }

    /**
     * Setter pour typeContactToAdd.
     * @param typeContactToAdd le typeContactToAdd à écrire.
     */
    public void setTypeContactToAdd(final TypeContact typeContactToAdd)
    {
        this.typeContactToAdd = typeContactToAdd;
    }

    /**
     * Getter sur contactsRetriever.
     * @return Retourne le contactsRetriever.
     */
    SelectableContactsRetriever getContactsRetriever()
    {
        return this.contactsRetriever;
    }

    /**
     * Setter pour contactsRetriever.
     * @param contactsRetriever le contactsRetriever à écrire.
     */
    public void setContactsRetriever(final SelectableContactsRetriever contactsRetriever)
    {
        this.contactsRetriever = contactsRetriever;
    }

    /**
     * Getter sur habilitationFactory.
     * @return Retourne le habilitationFactory.
     */
    HabilitationFactory getHabilitationFactory()
    {
        return this.habilitationFactory;
    }

    /**
     * Setter pour habilitationFactory.
     * @param habilitationFactory le habilitationFactory à écrire.
     */
    public void setHabilitationFactory(final HabilitationFactory habilitationFactory)
    {
        this.habilitationFactory = habilitationFactory;
    }

    /**
     * Getter sur initializers.
     * @return Retourne le initializers.
     */
    Map<String, DroitHabilitationInitializer> getInitializers()
    {
        return this.initializers;
    }

    /**
     * Setter pour initializers.
     * @param initializers le initializers à écrire.
     */
    public void setInitializers(final Map<String, DroitHabilitationInitializer> initializers)
    {
        this.initializers = initializers;
    }

    /**
     * Getter sur selectableBeanFactory.
     * @return Retourne le selectableBeanFactory.
     */
    SelectableBeanFactory<Personne> getSelectableBeanFactory()
    {
        return this.selectableBeanFactory;
    }

    /**
     * Setter pour selectableBeanFactory.
     * @param selectableBeanFactory le selectableBeanFactory à écrire.
     */
    public void setSelectableBeanFactory(final SelectableBeanFactory<Personne> selectableBeanFactory)
    {
        this.selectableBeanFactory = selectableBeanFactory;
    }

    /**
     * Setter pour selectableContactsManager.
     * @param selectableContactsManager le selectableContactsManager à écrire.
     */
    public void setSelectableContactsManager(final SelectableBeansManager<Personne> selectableContactsManager)
    {
        this.selectableContactsManager = selectableContactsManager;
    }

    /**
     * Getter sur selectableContactsManager.
     * @return Retourne le selectableContactsManager.
     */
    SelectableBeansManager<Personne> getSelectableContactsManager()
    {
        return this.selectableContactsManager;
    }

    /**
     * Getter sur habilitationsHelper.
     * @return Retourne le habilitationsHelper.
     */
    HabilitationsHelper getHabilitationsHelper()
    {
        return this.habilitationsHelper;
    }

    /**
     * Setter pour habilitationsHelper.
     * @param habilitationsHelper le habilitationsHelper à écrire.
     */
    public void setHabilitationsHelper(final HabilitationsHelper habilitationsHelper)
    {
        this.habilitationsHelper = habilitationsHelper;
    }

    /**
     * Getter sur selectedHabilitation.
     * @return Retourne le selectedHabilitation.
     */
    public Habilitation getSelectedHabilitation()
    {
        return this.selectedHabilitation;
    }

    /**
     * Setter pour selectedHabilitation.
     * @param selectedHabilitation le selectedHabilitation à écrire.
     */
    public void setSelectedHabilitation(final Habilitation selectedHabilitation)
    {
        this.selectedHabilitation = selectedHabilitation;
    }

    /**
     * Getter pour investigateurPrincipal.
     * @return Le investigateurPrincipal
     */
    public Boolean getInvestigateurPrincipal()
    {
        return this.investigateurPrincipal;
    }

    /**
     * Setter pour investigateurPrincipal.
     * @param investigateurPrincipal Le investigateurPrincipal à écrire.
     */
    public void setInvestigateurPrincipal(final Boolean investigateurPrincipal)
    {
        this.investigateurPrincipal = investigateurPrincipal;
    }
}
