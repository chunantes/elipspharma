package fr.pharma.eclipse.validator.save.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import fr.pharma.eclipse.domain.criteria.acteur.PersonneSearchCriteria;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.service.common.GenericService;

/**
 * Classe en charge de valider la sauvegarde d'un bean Personne.
 
 * @version $Revision$ $Date$
 */
public class PersonneSaveValidator
    implements Serializable
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -4037191312142199353L;

    /**
     * Service de gestion de Personne.
     */
    @Resource(name = "personneService")
    private GenericService<Personne> personneService;

    /**
     * Méthode en charge de valider une personne (unicité du login de la personne pour tous les
     * types).
     * @param personne Personne à valider.
     */
    public void validate(final Personne personne)
    {
        // Vérification de l'unicité du login
        final PersonneSearchCriteria criteria = new PersonneSearchCriteria();
        criteria.setLogin(personne.getLogin());

        final List<Personne> personnes = this.personneService.getAll(criteria);

        if (!personnes.isEmpty()
            && personnes.size() == 1
            && !personnes.get(0).getId().equals(personne.getId()))
        {
            throw new ValidationException("personne.login",
                                          new String[]
                                          {"unique", },
                                          personne);
        }

        // Vérification de l'égalité entre les champs password et confirmPassword si un password
        // est défini
        if ((StringUtils.isNotEmpty(personne.getPassword()))
            && (!personne.getPassword().equals(personne.getConfirmPassword())))
        {
            throw new ValidationException("personne.passwords",
                                          new String[]
                                          {"different", },
                                          personne);
        }
    }

    /**
     * Setter pour personneService.
     * @param personneService le personneService à écrire.
     */
    public void setPersonneService(final GenericService<Personne> personneService)
    {
        this.personneService = personneService;
    }

}
