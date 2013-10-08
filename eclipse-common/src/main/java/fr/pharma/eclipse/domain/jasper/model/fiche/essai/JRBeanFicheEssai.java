package fr.pharma.eclipse.domain.jasper.model.fiche.essai;

import fr.pharma.eclipse.domain.jasper.model.common.AbstractJRBeanWithHeader;
import fr.pharma.eclipse.domain.jasper.model.common.JRBeanHeader;
import fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean;
import fr.pharma.eclipse.jasper.constants.JasperConstants;

/**
 * Classe représentant la source de données principale<br>
 * du rapport Jasper intitulé<br>
 * "Fiche information 'essais cliniques'".
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class JRBeanFicheEssai extends AbstractJRBeanWithHeader implements JasperReportBean {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 141145247007044672L;

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
     * Investigateur.
     */
    private String investigateur = JasperConstants.DEFAULT_FIELD_VALUE;

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
     * Getter sur investigateur.
     * @return Retourne le investigateur.
     */
    public String getInvestigateur() {
        return this.investigateur;
    }

    /**
     * Setter pour investigateur.
     * @param investigateur le investigateur à écrire.
     */
    public void setInvestigateur(final String investigateur) {
        this.investigateur = investigateur;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("[");
        builder.append("promoteur: ").append(this.promoteur);
        builder.append(", codeProtocole: ").append(this.codeProtocole);
        builder.append(", nomUsuel: ").append(this.nomUsuel);
        builder.append(", investigateur: ").append(this.investigateur);
        return builder.append("]").toString();
    }

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
}
