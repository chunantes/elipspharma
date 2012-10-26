package fr.pharma.eclipse.filler.impl.eclipse;

import javax.annotation.Resource;

import fr.pharma.eclipse.domain.enums.TypePersonne;
import fr.pharma.eclipse.domain.model.acteur.ContactPromoteur;
import fr.pharma.eclipse.domain.model.acteur.Promoteur;
import fr.pharma.eclipse.domain.model.sigrec.acteur.ContactSigrec;
import fr.pharma.eclipse.domain.model.sigrec.acteur.PromoteurSigrec;
import fr.pharma.eclipse.utils.converter.BeanConverter;
import fr.pharma.eclipse.utils.converter.filler.Filler;

/**
 * Filler en charge de populer les contacts promoteurs d'un Promoteur à partir d'un
 * PromoteurSigrec.
 
 * @version $Revision$ $Date$
 */
public class ContactPromoteurFiller
    implements Filler<PromoteurSigrec, Promoteur>
{

    /**
     * Converter ARCPromoteurSigrec => ARCPromoteur.
     */
    @Resource(name = "contactPromoteurEclipseConverter")
    private BeanConverter<ContactSigrec, ContactPromoteur> converter;

    /**
     * {@inheritDoc}
     */
    @Override
    public void fill(final PromoteurSigrec source,
                     final Promoteur destination)
    {
        final ContactPromoteur contacts = this.converter.convert(source.getContact());
        contacts.setType(TypePersonne.PROMOTEUR);
        contacts.setPromoteur(destination);
        destination.getContactPromoteurs().add(contacts);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean support(final PromoteurSigrec source)
    {
        return source != null
               && source.getContact() != null;
    }
    /**
     * Getter sur converter.
     * @return Retourne le converter.
     */
    public BeanConverter<ContactSigrec, ContactPromoteur> getConverter()
    {
        return this.converter;
    }

    /**
     * Setter pour converter.
     * @param converter le converter à écrire.
     */
    public void setConverter(final BeanConverter<ContactSigrec, ContactPromoteur> converter)
    {
        this.converter = converter;
    }

}
