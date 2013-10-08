package fr.pharma.eclipse.jasper.engine.filler.impl.aidedispensation;

import java.util.Collection;
import java.util.Set;

import net.sf.jasperreports.engine.JRDataSource;
import fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean;
import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanFicheAideDispensationPart2;
import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanProduit;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.jasper.engine.factory.JRDataSourceFactory;
import fr.pharma.eclipse.jasper.engine.filler.JasperReportBeanFiller;
import fr.pharma.eclipse.jasper.engine.filler.impl.aidedispensation.helper.ProduitsFillerHelper;
import fr.pharma.eclipse.utils.introspection.BeanTool;

/**
 * Filler en charge de construire un attribut de produits du
 * {@link JRBeanFicheAideDispensationPart2}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class AideDispensationPart2ProduitsFiller implements JasperReportBeanFiller {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -8232142138639565817L;

    /**
     * Helper pour la construction du bean.
     */
    private ProduitsFillerHelper helper;

    /**
     * Fabrique de JRDataSource.
     */
    private JRDataSourceFactory jrDataSourceFactory;

    /**
     * Nom de la propriété de la liste des produits<br>
     * à introspecter depuis l'essai.
     */
    private final String produitsPropertyFromEssai;

    /**
     * Propriété du JasperReportBean valorisée par ce filler.
     */
    private final String jrPropertyToSet;

    /**
     * Constructeur.
     * @param produitsPropertyFromEssai Nom de la propriété de la liste des
     * produits<br>
     * à introspecter depuis l'essai.
     * @param jrPropertyToSet Propriété du JasperReportBean valorisée par ce
     * filler. renseigner.
     */

    public AideDispensationPart2ProduitsFiller(final String produitsPropertyFromEssai, final String jrPropertyToSet) {
        this.produitsPropertyFromEssai = produitsPropertyFromEssai;
        this.jrPropertyToSet = jrPropertyToSet;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void fill(final Essai essai,
                     final JasperReportBean bean) {
        final JRBeanFicheAideDispensationPart2 part2 = (JRBeanFicheAideDispensationPart2) bean;

        // Beans produits.
        final Collection<JRBeanProduit> beansProduit = this.helper.transform((Set<Produit>) BeanTool.getPropriete(essai, this.produitsPropertyFromEssai));

        // Création de la source de données.
        final JRDataSource dataSource = this.jrDataSourceFactory.getInitializedObject(beansProduit);
        BeanTool.setPropriete(part2, this.jrPropertyToSet, dataSource);
    }

    /**
     * Getter sur produitsPropertyFromEssai.
     * @return Retourne le produitsPropertyFromEssai.
     */
    String getProduitsPropertyFromEssai() {
        return this.produitsPropertyFromEssai;
    }

    /**
     * Getter sur helper.
     * @return Retourne le helper.
     */
    ProduitsFillerHelper getHelper() {
        return this.helper;
    }

    /**
     * Setter pour helper.
     * @param helper le helper à écrire.
     */
    public void setHelper(final ProduitsFillerHelper helper) {
        this.helper = helper;
    }

    /**
     * Getter sur jrDataSourceFactory.
     * @return Retourne le jrDataSourceFactory.
     */
    JRDataSourceFactory getJrDataSourceFactory() {
        return this.jrDataSourceFactory;
    }

    /**
     * Setter pour jrDataSourceFactory.
     * @param jrDataSourceFactory le jrDataSourceFactory à écrire.
     */
    public void setJrDataSourceFactory(final JRDataSourceFactory jrDataSourceFactory) {
        this.jrDataSourceFactory = jrDataSourceFactory;
    }

    /**
     * Getter sur jrPropertyToSet.
     * @return Retourne le jrPropertyToSet.
     */
    String getJrPropertyToSet() {
        return this.jrPropertyToSet;
    }

}
