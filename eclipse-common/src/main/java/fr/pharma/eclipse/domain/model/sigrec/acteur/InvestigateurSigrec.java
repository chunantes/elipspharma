package fr.pharma.eclipse.domain.model.sigrec.acteur;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import fr.pharma.eclipse.domain.model.sigrec.common.IntervenantSigrec;

/**
 * Classe du modèle d'import SIGREC représentant un Investigateur.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "investigateur_sigrec")
public class InvestigateurSigrec extends IntervenantSigrec implements Contactable {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -8346341002237934459L;

    /**
     * Contact.
     */
    @ManyToOne
    @JoinColumn(name = "id_centre")
    private CentreSigrec centre;

    /**
     * Getter sur centre.
     * @return Retourne le centre.
     */
    public CentreSigrec getCentre() {
        return this.centre;
    }

    /**
     * Setter pour centre.
     * @param centre le centre à écrire.
     */
    public void setCentre(final CentreSigrec centre) {
        this.centre = centre;
    }

}
