package fr.pharma.eclipse.jasper.engine.filler.impl.aidedispensation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean;
import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanFicheAideDispensationPart1;
import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanGroupeTraitement;
import fr.pharma.eclipse.domain.model.design.Designable;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.jasper.engine.factory.JRDataSourceFactory;
import fr.pharma.eclipse.jasper.engine.filler.JasperReportBeanFiller;
import fr.pharma.eclipse.jasper.engine.filler.impl.aidedispensation.helper.GroupesTraitFillerHelper;
import fr.pharma.eclipse.service.helper.design.DesignHelper;

/**
 * Filler en charge de construire l'attribut groupesTraitement du bean
 * {@link JRBeanFicheAideDispensationPart1}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class AideDispensationPart1GroupesTraitFiller implements JasperReportBeanFiller {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 1307936657540522311L;

    /**
     * Fabrique de JRDataSource.
     */
    private JRDataSourceFactory jrDataSourceFactory;

    /**
     * Helper pour la gestion du design.
     */
    private DesignHelper designHelper;

    /**
     * Helper.
     */
    private GroupesTraitFillerHelper helper;

    /**
     * {@inheritDoc}
     */
    @Override
    public void fill(final Essai essai,
                     final JasperReportBean bean) {
        final JRBeanFicheAideDispensationPart1 beanSource = (JRBeanFicheAideDispensationPart1) bean;

        // Création des beans.
        final List<JRBeanGroupeTraitement> groupesTraitement = new ArrayList<JRBeanGroupeTraitement>();

        final Set<Designable> roots = this.designHelper.getDesignRoots(essai.getDetailDesign());
        final int nbAlineas = 0;
        for (final Designable root : roots) {
            this.handleTree(groupesTraitement, root, nbAlineas);
        }

        // Transformation en source de données.
        beanSource.setGroupesTraitement(this.jrDataSourceFactory.getInitializedObject(groupesTraitement));
    }

    /**
     * Méthode (récursive) en charge de traiter un arbre de bras.
     * @param groupesTraitement Liste des groupes à compléter.
     * @param root Parent de l'arbre.
     * @param niveau Niveau de l'arbre.
     */
    private void handleTree(final List<JRBeanGroupeTraitement> groupesTraitement,
                            final Designable root,
                            final int niveau) {
        // Ajout du root.
        groupesTraitement.add(this.helper.transform(root, niveau));
        // Ajout des enfants, s'il y en a.
        for (final Designable child : root.getEnfants()) {
            this.handleTree(groupesTraitement, child, niveau + 1);
        }
    }

    /**
     * Getter sur designHelper.
     * @return Retourne le designHelper.
     */
    DesignHelper getDesignHelper() {
        return this.designHelper;
    }

    /**
     * Setter pour designHelper.
     * @param designHelper le designHelper à écrire.
     */
    public void setDesignHelper(final DesignHelper designHelper) {
        this.designHelper = designHelper;
    }

    /**
     * Getter sur helper.
     * @return Retourne le helper.
     */
    GroupesTraitFillerHelper getHelper() {
        return this.helper;
    }

    /**
     * Setter pour helper.
     * @param helper le helper à écrire.
     */
    public void setHelper(final GroupesTraitFillerHelper helper) {
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

}
