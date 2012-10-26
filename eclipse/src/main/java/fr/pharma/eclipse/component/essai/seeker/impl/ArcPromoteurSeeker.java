package fr.pharma.eclipse.component.essai.seeker.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

import fr.pharma.eclipse.component.essai.TypeContact;
import fr.pharma.eclipse.component.essai.seeker.SelectableContactsSeeker;
import fr.pharma.eclipse.domain.criteria.acteur.ArcPromoteurSearchCriteria;
import fr.pharma.eclipse.domain.criteria.acteur.PersonneSearchCriteria;
import fr.pharma.eclipse.domain.model.acteur.ArcPromoteur;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.service.common.GenericService;

/**
 * Classe en charge de rechercher les ARC promoteurs sélectionnables pour l'essai.
 
 * @version $Revision$ $Date$
 */
public class ArcPromoteurSeeker
    implements SelectableContactsSeeker, BeanFactoryAware
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 8725569785840371709L;

    /**
     * Fabrique Spring.
     */
    private BeanFactory beanFactory;

    /**
     * Service spécifique à la classe.
     */
    private GenericService<ArcPromoteur> service;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supports(final TypeContact typeContact)
    {
        return TypeContact.ARC_PROMOTEUR.equals(typeContact);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Personne> getContacts(final Essai essai)
    {
        return new ArrayList<Personne>(this.service.getAll(this.getSearchCriteria(essai)));
    }

    /**
     * Méthode en charge de préparer un critère de recherche qui permet<br>
     * de récupérer des contacts ajoutables à un essai.
     * @param essai Essai pour lequel on souhaite ajouter des contacts.
     * @return Le critère de recherche qui permet de récupérer les contacts.
     */
    private PersonneSearchCriteria getSearchCriteria(final Essai essai)
    {
        final ArcPromoteurSearchCriteria criteria =
            (ArcPromoteurSearchCriteria) this.beanFactory.getBean("arcPromoteurCriteria");
        criteria.setPromoteur(essai.getPromoteur());
        return criteria;
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
     * Getter sur service.
     * @return Retourne le service.
     */
    GenericService<ArcPromoteur> getService()
    {
        return this.service;
    }

    /**
     * Setter pour service.
     * @param service le service à écrire.
     */
    public void setService(final GenericService<ArcPromoteur> service)
    {
        this.service = service;
    }
}
