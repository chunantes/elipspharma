package fr.pharma.eclipse.domain.model.sigrec.acteur;


/**
 * Interface définissant les éléments Contact.
 
 * @version $Revision$ $Date$
 */
public interface Contactable
{
    /**
     * Retourne le contact.
     * @return le contact.
     */
    ContactSigrec getContact();

    /**
     * Set le contact.
     * @param contact Le contact à setter.
     */
    void setContact(ContactSigrec contact);
}
