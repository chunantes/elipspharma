package fr.pharma.eclipse.domain.jasper.model.accusereception;

import java.io.Serializable;

import net.sf.jasperreports.engine.JRDataSource;
import fr.pharma.eclipse.domain.jasper.model.common.AbstractJRBeanWithHeader;
import fr.pharma.eclipse.domain.jasper.model.common.JRBeanHeader;
import fr.pharma.eclipse.jasper.constants.JasperConstants;

/**
 * Classe représentant la source de données principale<br>
 * du rapport Jasper intitulé<br>
 * "Accusé de réception (CESSION PUI)".
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class JRBeanModeleAccuseReception extends AbstractJRBeanWithHeader implements Serializable {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -5895302601496700975L;

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
     * centre.
     */
    private String centre = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Motif de sortie.
     */
    private String motif = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Pharmacie.
     */
    private String pharmacie = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Traitements.
     */
    private JRDataSource traitements;

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
     * Getter pour motif.
     * @return Le motif
     */
    public String getMotif() {
        return this.motif;
    }

    /**
     * Setter pour motif.
     * @param motif Le motif à écrire.
     */
    public void setMotif(final String motif) {
        this.motif = motif;
    }

    /**
     * Getter pour centre.
     * @return Le centre
     */
    public String getCentre() {
        return this.centre;
    }

    /**
     * Setter pour centre.
     * @param centre Le centre à écrire.
     */
    public void setCentre(final String centre) {
        this.centre = centre;
    }

    /**
     * Getter pour traitements.
     * @return Le traitements
     */
    public JRDataSource getTraitements() {
        return this.traitements;
    }

    /**
     * Setter pour traitements.
     * @param traitements Le traitements à écrire.
     */
    public void setTraitements(final JRDataSource traitements) {
        this.traitements = traitements;
    }

    /**
     * Getter pour pharmacie.
     * @return Le pharmacie
     */
    public String getPharmacie() {
        return this.pharmacie;
    }

    /**
     * Setter pour pharmacie.
     * @param pharmacie Le pharmacie à écrire.
     */
    public void setPharmacie(final String pharmacie) {
        this.pharmacie = pharmacie;
    }

}
