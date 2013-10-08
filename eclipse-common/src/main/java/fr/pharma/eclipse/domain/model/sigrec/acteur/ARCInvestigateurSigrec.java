package fr.pharma.eclipse.domain.model.sigrec.acteur;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Index;

import fr.pharma.eclipse.domain.model.sigrec.common.IntervenantSigrec;
import fr.pharma.eclipse.domain.model.sigrec.essai.EssaiSigrec;

/**
 * Classe du modèle d'import SIGREC représentant un ARC Investigateur.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "arc_investigateur_sigrec")
public class ARCInvestigateurSigrec extends IntervenantSigrec implements Contactable {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -8346341002237934459L;

    /**
     * Promoteur.
     */
    @ManyToOne()
    @JoinColumn(name = "id_essai")
    @Index(name = "idx_arc_investigateur_essai_sigrec")
    private EssaiSigrec essai;

    /**
     * Contact.
     */
    @ManyToOne()
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

    /**
     * Getter sur essai.
     * @return Retourne le essai.
     */
    public EssaiSigrec getEssai() {
        return this.essai;
    }

    /**
     * Setter pour essai.
     * @param essai le essai à écrire.
     */
    public void setEssai(final EssaiSigrec essai) {
        this.essai = essai;
    }

}
