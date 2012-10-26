package fr.pharma.eclipse.domain.model.suivi.acteur;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Index;

import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.suivi.common.Suivi;

/**
 * Bean métier représentant les suivis de modifications sur le bean Personne.
 
 * @version $Revision$ $Date$
 */
@Entity(name = "personne_suivi")
public class PersonneSuivi
    extends Suivi
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -2457827382689034650L;

    /**
     * Objet Personne.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_personne", nullable = false)
    @Index(name = "idx_suivi_personne")
    private Personne personne;

    /**
     * Getter sur personne.
     * @return Retourne le personne.
     */
    public Personne getPersonne()
    {
        return this.personne;
    }

    /**
     * Setter pour personne.
     * @param personne le personne à écrire.
     */
    public void setPersonne(final Personne personne)
    {
        this.personne = personne;
    }

}
