package fr.pharma.eclipse.factory.acteur;

import javax.annotation.Resource;

import fr.pharma.eclipse.domain.enums.TypePersonne;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.sir.PersonneSir;
import fr.pharma.eclipse.factory.common.BeanObjectFactory;
import fr.pharma.eclipse.service.sir.GenericSirService;

/**
 * Factory de Bean Personne.
 
 * @version $Revision$ $Date$
 * @param <PERSONNE> Bean Objet Personne.
 */
public class PersonneFactory<PERSONNE extends Personne>
    extends BeanObjectFactory<PERSONNE>
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -3171922165561292902L;

    /**
     * Type de la personne.
     */
    private final TypePersonne typePersonne;

    /**
     * Service de gestion des personnes SIR.
     */
    @Resource(name = "personneSirService")
    private GenericSirService<PersonneSir> personneSirService;

    /**
     * Constructeur.
     * @param bean Classe.
     * @param typePersonne Type de la personne.
     */
    public PersonneFactory(final Class<PERSONNE> bean, final TypePersonne typePersonne)
    {
        super(bean);
        this.typePersonne = typePersonne;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PERSONNE getInitializedObject()
    {
        final PERSONNE personne = super.getInitializedObject();
        personne.setType(this.typePersonne);
        return personne;
    }

    /**
     * Méthode en charge de retourner une PERSONNE à partir des informations d'une PersonneSir.
     * @param idPersonneSir Identifiant technique de la Personne SIR.
     * @return PERSONNE.
     */
    public PERSONNE getInitializedObject(final Integer idPersonneSir)
    {
        final PERSONNE personne = this.getInitializedObject();
        if (idPersonneSir != null)
        {
            final PersonneSir personneSir = this.personneSirService.get(idPersonneSir);
            personne.setLogin(personneSir.getLogin());
            personne.setNom(personneSir.getNom());
            personne.setPrenom(personneSir.getPrenom());
            personne.setMail(personneSir.getMail());
        }
        return personne;
    }

    /**
     * Setter pour personneSirService.
     * @param personneSirService le personneSirService à écrire.
     */
    public void setPersonneSirService(final GenericSirService<PersonneSir> personneSirService)
    {
        this.personneSirService = personneSirService;
    }

}
