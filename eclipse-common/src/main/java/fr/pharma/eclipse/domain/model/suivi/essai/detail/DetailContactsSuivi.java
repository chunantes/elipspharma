package fr.pharma.eclipse.domain.model.suivi.essai.detail;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Index;

import fr.pharma.eclipse.domain.model.essai.detail.contact.DetailContacts;
import fr.pharma.eclipse.domain.model.suivi.common.Suivi;

/**
 * Bean métier représentant les suivis de modifications sur le bean {@link DetailContacts}.
 
 * @version $Revision$ $Date$
 */
@Entity(name = "essai_detail_contacts_suivi")
public class DetailContactsSuivi
    extends Suivi
    implements Serializable
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 6607273140317849395L;

    /**
     * Bean suivi.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_detail_contacts", nullable = false)
    @Index(name = "idx_suivi_detail_contacts")
    private DetailContacts detailContacts;

    /**
     * Getter sur detailContacts.
     * @return Retourne le detailContacts.
     */
    public DetailContacts getDetailContacts()
    {
        return this.detailContacts;
    }

    /**
     * Setter pour detailContacts.
     * @param detailContacts le detailContacts à écrire.
     */
    public void setDetailContacts(final DetailContacts detailContacts)
    {
        this.detailContacts = detailContacts;
    }

}
