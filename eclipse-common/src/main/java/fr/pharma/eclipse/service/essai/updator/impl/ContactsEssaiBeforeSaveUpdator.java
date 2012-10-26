package fr.pharma.eclipse.service.essai.updator.impl;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

import fr.pharma.eclipse.domain.criteria.acteur.PharmacienSearchCriteria;
import fr.pharma.eclipse.domain.enums.Droit;
import fr.pharma.eclipse.domain.enums.TypePharmacien;
import fr.pharma.eclipse.domain.model.acteur.Pharmacien;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.habilitation.Habilitation;
import fr.pharma.eclipse.factory.habilitation.HabilitationFactory;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.service.essai.updator.EssaiBeforeSaveUpdator;

/**
 * Classe en charge d'ajouter, lors de la première sauvegarde d'un essai, les pharmaciens
 * titulaires de la pharmacie principale.
 
 * @version $Revision$ $Date$
 */
public class ContactsEssaiBeforeSaveUpdator
    implements EssaiBeforeSaveUpdator, BeanFactoryAware
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -8121296132050546913L;

    /**
     * Service d'accès aux personnes.
     */
    private GenericService<Pharmacien> pharmacienService;

    /**
     * Fabrique Spring.
     */
    private BeanFactory beanFactory;

    /**
     * Fabrique d'habilitations.
     */
    private HabilitationFactory habilitationFactory;

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final Essai essai,
                       final EssaiService service)
    {
        // Ne fait rien si l'essai existe déjà en base.
        if (essai.getId() != null)
        {
            return;
        }

        // Récupération des pharmaciens de la pharmacie principale.
        final PharmacienSearchCriteria criteria =
            (PharmacienSearchCriteria) this.beanFactory.getBean("pharmacienCriteria");
        criteria.setTypePharmacien(TypePharmacien.TITULAIRE);
        criteria.setPharmacie(essai.getPharmaciePrincipale());
        final List<Pharmacien> pharmaciens = this.pharmacienService.getAll(criteria);

        // Création d'une habilitation pour chaque pharmacien.
        for (final Pharmacien pharmacien : pharmaciens)
        {
            final Habilitation habilitation = this.habilitationFactory.getInitializedObject();
            habilitation.setDesactivable(false);
            habilitation.setPersonne(pharmacien);
            habilitation.setDetailContacts(essai.getDetailContacts());
            habilitation.setDroit(Droit.PHARMACIEN_TITULAIRE);
            essai.getDetailContacts().getHabilitations().add(habilitation);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBeanFactory(final BeanFactory beanFactory)
        throws BeansException
    {
        this.beanFactory = beanFactory;
    }

    /**
     * Getter sur beanFactory.
     * @return Retourne le beanFactory.
     */
    BeanFactory getBeanFactory()
    {
        return this.beanFactory;
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
     * Getter sur pharmacienService.
     * @return Retourne le pharmacienService.
     */
    GenericService<Pharmacien> getPharmacienService()
    {
        return this.pharmacienService;
    }

    /**
     * Setter pour pharmacienService.
     * @param pharmacienService le pharmacienService à écrire.
     */
    public void setPharmacienService(final GenericService<Pharmacien> pharmacienService)
    {
        this.pharmacienService = pharmacienService;
    }

}
