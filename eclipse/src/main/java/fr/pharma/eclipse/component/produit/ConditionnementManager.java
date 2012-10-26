package fr.pharma.eclipse.component.produit;

import java.util.Map;

import javax.annotation.Resource;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;

import fr.pharma.eclipse.component.BeanManager;
import fr.pharma.eclipse.component.produit.builder.UnitePrescriptionBuilder;
import fr.pharma.eclipse.component.produit.helper.ConditionnementHelper;
import fr.pharma.eclipse.domain.enums.produit.FormeConditionnement;
import fr.pharma.eclipse.domain.enums.produit.ModePrescription;
import fr.pharma.eclipse.domain.enums.produit.UniteGestion;
import fr.pharma.eclipse.domain.enums.produit.VoieAdministration;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.factory.produit.ConditionnementFactory;
import fr.pharma.eclipse.service.common.GenericService;

/**
 * Manager pour la gestion d'un conditionnement.
 
 * @version $Revision$ $Date$
 */
public class ConditionnementManager
    extends BeanManager<Conditionnement>
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 366744018968659156L;

    /**
     * Phrase résumé du conditionnement.
     */
    private String resume;

    /**
     * Map de fabricants de d'unité de prescription.
     */
    private Map<ModePrescription, UnitePrescriptionBuilder> builders;

    /**
     * Helper de conditionnement.
     */
    @Resource(name = "conditionnementHelper")
    private ConditionnementHelper conditionnementHelper;

    /**
     * Factory de conditionnement.
     */
    @Resource(name = "conditionnementFactory")
    private ConditionnementFactory<Conditionnement> factory;

    /**
     * Constructeur.
     * @param service Le service.
     */
    public ConditionnementManager(final GenericService<Conditionnement> service)
    {
        super(service);

    }

    /**
     * Méthode en charge de populer les propriétés du conditionnement courant en fonction du type
     * sélectionné.
     */
    public void init()
    {
        this.setBean(null);
        this.resume = "";
    }

    /**
     * Méthode appelée pour l'édition d'un conditionnement.
     * @param conditionnement Conditionnement
     */
    public void editConditionnement(final Conditionnement conditionnement)
    {
        this.setBean(conditionnement);
        this.buildResume();

    }

    /**
     * Méthode en charge d'intialiser le résumé.
     */
    public void buildResume()
    {
        this.setResume(this.conditionnementHelper.buildResume(this.getBean()));
    }

    /**
     * Méthode appelée par l'IHM lors de la sélection d'un mode de prescription/
     * @param event Evenement JSF.
     */
    public void handleUniteGestion(final AjaxBehaviorEvent event)
    {
        final HtmlSelectOneMenu menu = (HtmlSelectOneMenu) event.getSource();
        final UniteGestion value = (UniteGestion) menu.getLocalValue();
        this.getBean().setUniteGestion(value);
        if (value.equals(UniteGestion.KIT))
        {
            this.getBean().setForme(FormeConditionnement.CONDITIONNEMENT_PRIMAIRE);
        }
        this.setResume(this.conditionnementHelper.buildResume(this.getBean()));
        this.buildUnitePrescription();
    }

    /**
     * Méthode appelée par l'IHM lors de la sélection d'une forme.
     * @param event Evenement JSF.
     */
    public void handleForme(final AjaxBehaviorEvent event)
    {

        final HtmlSelectOneMenu menu = (HtmlSelectOneMenu) event.getSource();
        final FormeConditionnement value = (FormeConditionnement) menu.getLocalValue();
        this.getBean().setForme(value);
        this.setResume(this.conditionnementHelper.buildResume(this.getBean()));
    }

    /**
     * Méthode appelée par l'IHM lors de la sélection d'une voie d'administration.
     * @param event Evenement JSF.
     */
    public void handleVoieAdministration(final AjaxBehaviorEvent event)
    {
        final HtmlSelectOneMenu menu = (HtmlSelectOneMenu) event.getSource();
        final VoieAdministration value = (VoieAdministration) menu.getLocalValue();
        this.getBean().setVoieAdministration(value);
        this.setResume(this.conditionnementHelper.buildResume(this.getBean()));
    }

    /**
     * Méthode en charge de construire l'unité de prescription du conditionnement.
     */
    public void buildUnitePrescription()
    {
        if (this.getBean().getModePrescription() != null
            && this.getBean().getUniteGestion() != null)
        {
            this.builders.get(this.getBean().getModePrescription()).build(this.getBean());
        }
    }

    /**
     * Setter pour factory.
     * @param factory le factory à écrire.
     */
    public void setFactory(final ConditionnementFactory<Conditionnement> factory)
    {
        this.factory = factory;
    }

    /**
     * Getter sur resume.
     * @return Retourne le resume.
     */
    public String getResume()
    {
        return this.resume;
    }

    /**
     * Setter pour resume.
     * @param resume le resume à écrire.
     */
    public void setResume(final String resume)
    {
        this.resume = resume;
    }

    /**
     * Setter pour builders.
     * @param builders le builders à écrire.
     */
    public void setBuilders(final Map<ModePrescription, UnitePrescriptionBuilder> builders)
    {
        this.builders = builders;
    }

    public void setConditionnementHelper(final ConditionnementHelper conditionnementHelper)
    {
        this.conditionnementHelper = conditionnementHelper;
    }
}
