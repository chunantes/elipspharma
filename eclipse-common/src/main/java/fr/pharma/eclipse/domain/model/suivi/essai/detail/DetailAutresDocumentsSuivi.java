package fr.pharma.eclipse.domain.model.suivi.essai.detail;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Index;

import fr.pharma.eclipse.domain.model.essai.detail.autresdocs.DetailAutresDocuments;
import fr.pharma.eclipse.domain.model.suivi.common.Suivi;

/**
 * Bean métier représentant les suivis de modifications sur le bean {@link DetailAutresDocuments}.
 
 * @version $Revision$ $Date$
 */
@Entity(name = "essai_detail_autres_documents_suivi")
public class DetailAutresDocumentsSuivi
    extends Suivi
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -2241035887354321543L;

    /**
     * Bean suivi.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_detail_autres_documents", nullable = false)
    @Index(name = "idx_suivi_detail_autres_documents")
    private DetailAutresDocuments detailAutresDocuments;

    /**
     * Getter sur detailAutresDocuments.
     * @return Retourne le detailAutresDocuments.
     */
    public DetailAutresDocuments getDetailAutresDocuments()
    {
        return this.detailAutresDocuments;
    }

    /**
     * Setter pour detailAutresDocuments.
     * @param detailAutresDocuments le detailAutresDocuments à écrire.
     */
    public void setDetailAutresDocuments(final DetailAutresDocuments detailAutresDocuments)
    {
        this.detailAutresDocuments = detailAutresDocuments;
    }

}
