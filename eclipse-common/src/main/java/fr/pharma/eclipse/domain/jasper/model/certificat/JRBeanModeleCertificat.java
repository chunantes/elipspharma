package fr.pharma.eclipse.domain.jasper.model.certificat;

import java.io.Serializable;

import net.sf.jasperreports.engine.JRDataSource;
import fr.pharma.eclipse.domain.jasper.model.common.AbstractJRBeanWithHeader;
import fr.pharma.eclipse.domain.jasper.model.common.JRBeanHeader;
import fr.pharma.eclipse.jasper.constants.JasperConstants;

/**
 * Classe représentant la source de données principale<br>
 * du rapport Jasper intitulé<br>
 * "Certificat de retour ou destruction".
 
 * @version $Revision$ $Date$
 */
public class JRBeanModeleCertificat
    extends AbstractJRBeanWithHeader
    implements Serializable
{

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
     * Investigateur.
     */
    private String investigateur = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Motif de sortie.
     */
    private String motif = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Produits prescrits.
     */
    private JRDataSource produits;

    /**
     * Getter sur header.
     * @return Retourne le header.
     */
    @Override
    public JRBeanHeader getHeader()
    {
        return this.header;
    }

    /**
     * Setter pour header.
     * @param header le header à écrire.
     */
    public void setHeader(final JRBeanHeader header)
    {
        this.header = header;
    }

    /**
     * Getter sur promoteur.
     * @return Retourne le promoteur.
     */
    public String getPromoteur()
    {
        return this.promoteur;
    }

    /**
     * Setter pour promoteur.
     * @param promoteur le promoteur à écrire.
     */
    public void setPromoteur(final String promoteur)
    {
        this.promoteur = promoteur;
    }

    /**
     * Getter sur codeProtocole.
     * @return Retourne le codeProtocole.
     */
    public String getCodeProtocole()
    {
        return this.codeProtocole;
    }

    /**
     * Setter pour codeProtocole.
     * @param codeProtocole le codeProtocole à écrire.
     */
    public void setCodeProtocole(final String codeProtocole)
    {
        this.codeProtocole = codeProtocole;
    }

    /**
     * Getter pour produits.
     * @return Le produits
     */
    public JRDataSource getProduits()
    {
        return this.produits;
    }

    /**
     * Setter pour produits.
     * @param produits Le produits à écrire.
     */
    public void setProduits(final JRDataSource produits)
    {
        this.produits = produits;
    }

    /**
     * Getter pour investigateur.
     * @return Le investigateur
     */
    public String getInvestigateur()
    {
        return this.investigateur;
    }

    /**
     * Getter pour motif.
     * @return Le motif
     */
    public String getMotif()
    {
        return this.motif;
    }

    /**
     * Setter pour motif.
     * @param motif Le motif à écrire.
     */
    public void setMotif(final String motif)
    {
        this.motif = motif;
    }

    /**
     * Setter pour investigateur.
     * @param investigateur Le investigateur à écrire.
     */
    public void setInvestigateur(final String investigateur)
    {
        this.investigateur = investigateur;
    }

}
