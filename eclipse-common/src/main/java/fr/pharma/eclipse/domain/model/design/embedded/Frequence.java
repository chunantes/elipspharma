package fr.pharma.eclipse.domain.model.design.embedded;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import fr.pharma.eclipse.domain.enums.design.TypeRegularite;
import fr.pharma.eclipse.domain.enums.design.UniteTemps;

/**
 * Classe du modèle représentant une fréquence pour la prescription.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Embeddable
public class Frequence implements Serializable {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 1066032983326493478L;

    /**
     * Nombre d'unité.
     */
    @Column(name = "nbFrequence")
    private Integer nbFrequence;

    /**
     * Nombre d'unité de temps (pour "tous les").
     */
    @Column(name = "nbUniteTempsFrequence")
    private Integer nbUniteTempsFrequence;

    /**
     * Unite de temps.
     */
    @Column(name = "uniteFrequence")
    @Enumerated(EnumType.STRING)
    private UniteTemps uniteFrequence;

    /**
     * Type de régularité.
     */
    @Column(name = "typeRegularite")
    @Enumerated(EnumType.STRING)
    private TypeRegularite typeRegularite;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuffer buff = new StringBuffer();
        if (this.getNbFrequence() != null) {
            buff.append(this.nbFrequence);
            buff.append(" fois ");
        }
        if (this.getTypeRegularite() != null) {
            if (!this.getTypeRegularite().equals(TypeRegularite.FOIS)) {
                buff.append(this.typeRegularite.getLibelle());
                buff.append(" ");
                if (this.typeRegularite.equals(TypeRegularite.TOUS_LES)) {
                    buff.append(this.getNbUniteTempsFrequence());
                    buff.append(" ");
                }
                if (this.getUniteFrequence() != null) {
                    buff.append(this.uniteFrequence.getLibelleCourt());
                }
            }
        }
        return buff.toString();
    }

    /**
     * Getter sur nbFrequence.
     * @return Retourne le nbFrequence.
     */
    public Integer getNbFrequence() {
        return this.nbFrequence;
    }

    /**
     * Setter pour nbFrequence.
     * @param nbFrequence le nbFrequence à écrire.
     */
    public void setNbFrequence(final Integer nbFrequence) {
        this.nbFrequence = nbFrequence;
    }

    /**
     * Getter sur nbUniteTempsFrequence.
     * @return Retourne le nbUniteTempsFrequence.
     */
    public Integer getNbUniteTempsFrequence() {
        return this.nbUniteTempsFrequence;
    }

    /**
     * Setter pour nbUniteTempsFrequence.
     * @param nbUniteTempsFrequence le nbUniteTempsFrequence à écrire.
     */
    public void setNbUniteTempsFrequence(final Integer nbUniteTempsFrequence) {
        this.nbUniteTempsFrequence = nbUniteTempsFrequence;
    }

    /**
     * Getter sur uniteFrequence.
     * @return Retourne le uniteFrequence.
     */
    public UniteTemps getUniteFrequence() {
        return this.uniteFrequence;
    }

    /**
     * Setter pour uniteFrequence.
     * @param uniteFrequence le uniteFrequence à écrire.
     */
    public void setUniteFrequence(final UniteTemps uniteFrequence) {
        this.uniteFrequence = uniteFrequence;
    }

    /**
     * Getter sur typeRegularite.
     * @return Retourne le typeRegularite.
     */
    public TypeRegularite getTypeRegularite() {
        return this.typeRegularite;
    }

    /**
     * Setter pour typeRegularite.
     * @param typeRegularite le typeRegularite à écrire.
     */
    public void setTypeRegularite(final TypeRegularite typeRegularite) {
        this.typeRegularite = typeRegularite;
    }

}
