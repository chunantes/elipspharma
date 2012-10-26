package fr.pharma.eclipse.domain.model.suivi.localisation;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Index;

import fr.pharma.eclipse.domain.model.localisation.Site;
import fr.pharma.eclipse.domain.model.suivi.common.Suivi;

/**
 * Bean métier représentant les suivis de modifications sur le bean Site.
 
 * @version $Revision$ $Date$
 */
@Entity(name = "site_suivi")
public class SiteSuivi
    extends Suivi
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 5849123670792936726L;

    /**
     * Objet Site.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_site")
    @Index(name = "idx_suivi_site")
    private Site site;

    /**
     * Getter sur site.
     * @return Retourne le site.
     */
    public Site getSite()
    {
        return this.site;
    }

    /**
     * Setter pour site.
     * @param site le site à écrire.
     */
    public void setSite(final Site site)
    {
        this.site = site;
    }

}
