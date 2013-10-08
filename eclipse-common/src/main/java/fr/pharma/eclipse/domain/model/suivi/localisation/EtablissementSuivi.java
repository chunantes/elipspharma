package fr.pharma.eclipse.domain.model.suivi.localisation;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Index;

import fr.pharma.eclipse.domain.model.localisation.Etablissement;
import fr.pharma.eclipse.domain.model.suivi.common.Suivi;

/**
 * Bean métier représentant les suivis de modifications sur le bean
 * Etablissement.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "etablissement_suivi")
public class EtablissementSuivi extends Suivi {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 5799391856680779292L;

    /**
     * Objet Etablissement.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_etablissement", nullable = false)
    @Index(name = "idx_suivi_etablissement")
    private Etablissement etablissement;

    /**
     * Getter sur etablissement.
     * @return Retourne le etablissement.
     */
    public Etablissement getEtablissement() {
        return this.etablissement;
    }

    /**
     * Setter pour etablissement.
     * @param etablissement le etablissement à écrire.
     */
    public void setEtablissement(final Etablissement etablissement) {
        this.etablissement = etablissement;
    }

}
