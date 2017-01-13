package fr.pharma.eclipse.domain.jasper.model.prescnominative;

import java.io.Serializable;

import fr.pharma.eclipse.domain.jasper.model.common.AbstractJRBeanWithHeader;
import fr.pharma.eclipse.domain.jasper.model.common.JRBeanHeader;
import fr.pharma.eclipse.jasper.constants.JasperConstants;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * Classe représentant la source de données principale<br>
 * du rapport Jasper intitulé<br>
 * "Prescription nominative de médicament en expérimentation clinique".
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class JRBeanModelePrescNominative extends AbstractJRBeanWithHeader implements Serializable {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 5353097406671347266L;

    /**
     * Données de l'en-tête.
     */
    private JRBeanHeader header;

    /**
     * Promoteur.
     */
    private String promoteur = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Code protocole.
     */
    private String codeProtocole = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Nom usuel.
     */
    private String nomUsuel = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Numero inclusion.
     */
    private String numInclusion = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Numero visite.
     */
    private String numVisite = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Produits prescrits.
     */
    private JRDataSource produits;

    /**
     * Getter sur header.
     * @return Retourne le header.
     */
    @Override
    public JRBeanHeader getHeader() {
        return this.header;
    }

    /**
     * Setter pour header.
     * @param header le header à écrire.
     */
    public void setHeader(final JRBeanHeader header) {
        this.header = header;
    }

    /**
     * Getter sur promoteur.
     * @return Retourne le promoteur.
     */
    public String getPromoteur() {
        return this.promoteur;
    }

    /**
     * Setter pour promoteur.
     * @param promoteur le promoteur à écrire.
     */
    public void setPromoteur(final String promoteur) {
        this.promoteur = promoteur;
    }

    /**
     * Getter sur codeProtocole.
     * @return Retourne le codeProtocole.
     */
    public String getCodeProtocole() {
        return this.codeProtocole;
    }

    /**
     * Setter pour codeProtocole.
     * @param codeProtocole le codeProtocole à écrire.
     */
    public void setCodeProtocole(final String codeProtocole) {
        this.codeProtocole = codeProtocole;
    }

    /**
     * Getter sur nomUsuel.
     * @return Retourne le nomUsuel.
     */
    public String getNomUsuel() {
        return this.nomUsuel;
    }

    /**
     * Setter pour nomUsuel.
     * @param nomUsuel le nomUsuel à écrire.
     */
    public void setNomUsuel(final String nomUsuel) {
        this.nomUsuel = nomUsuel;
    }

    /**
     * Getter pour produits.
     * @return Le produits
     */
    public JRDataSource getProduits() {
        return this.produits;
    }

    /**
     * Setter pour produits.
     * @param produits Le produits à écrire.
     */
    public void setProduits(final JRDataSource produits) {
        this.produits = produits;
    }

    /**
     * Getter pour numInclusion.
     * @return Le numInclusion
     */
    public String getNumInclusion() {
        return this.numInclusion;
    }

    /**
     * Setter pour numInclusion.
     * @param numInclusion Le numInclusion à écrire.
     */
    public void setNumInclusion(final String numInclusion) {
        this.numInclusion = numInclusion;
    }

    /**
     * Getter pour numVisite.
     * @return Le numVisite
     */
    public String getNumVisite() {
        return this.numVisite;
    }

    /**
     * Setter pour numVisite.
     * @param numVisite Le numVisite à écrire.
     */
    public void setNumVisite(final String numVisite) {
        this.numVisite = numVisite;
    }

}
