package fr.pharma.eclipse.jasper.engine.filler.impl.aidedispensation;

import java.util.Collection;

import fr.pharma.eclipse.domain.enums.GroupeContacts;
import fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean;
import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanContact;
import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanFicheAideDispensationPart1;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.jasper.engine.factory.JRDataSourceFactory;
import fr.pharma.eclipse.jasper.engine.filler.JasperReportBeanFiller;
import fr.pharma.eclipse.jasper.engine.filler.impl.aidedispensation.helper.ContactsFillerHelper;
import fr.pharma.eclipse.service.habilitation.helper.HabilitationsHelper;
import fr.pharma.eclipse.utils.introspection.BeanTool;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * Filler en charge de construire un attribut de contacts du bean
 * {@link JRBeanFicheAideDispensationPart1}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class AideDispensationPart1ContactsFiller implements JasperReportBeanFiller {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 7508083604555960687L;

    /**
     * Helper pour la gestion des habilitations.
     */
    private HabilitationsHelper habilitationsHelper;

    /**
     * Fabrique de JRDataSource.
     */
    private JRDataSourceFactory jrDataSourceFactory;

    /**
     * Helper.
     */
    private ContactsFillerHelper helper;

    /**
     * Groupe de contacts pour lequel travaille ce filler.
     */
    private final GroupeContacts groupeContacts;

    /**
     * Propriété du JasperReportBean valorisée par ce filler.
     */
    private final String jrPropertyToSet;

    /**
     * Constructeur.
     * @param jrPropertyToSet Propriété du JasperReportBean valorisée par ce
     * filler.
     * @param groupeContactsName Nom du groupe de contacts pour lequel travaille
     * ce filler (cf. GroupeContacts.name()).
     */
    public AideDispensationPart1ContactsFiller(final String jrPropertyToSet, final String groupeContactsName) {
        this(jrPropertyToSet, GroupeContacts.valueOf(groupeContactsName));
    }

    /**
     * Constructeur.
     * @param jrPropertyToSet Propriété du JasperReportBean valorisée par ce
     * filler.
     * @param groupeContacts Groupe de contacts pour lequel travaille ce filler.
     */
    public AideDispensationPart1ContactsFiller(final String jrPropertyToSet, final GroupeContacts groupeContacts) {
        this.groupeContacts = groupeContacts;
        this.jrPropertyToSet = jrPropertyToSet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fill(final Essai essai,
                     final JasperReportBean bean) {
        final JRBeanFicheAideDispensationPart1 beanSource = (JRBeanFicheAideDispensationPart1) bean;

        // Beans contacts.
        final Collection<JRBeanContact> beans = this.helper.transform(this.habilitationsHelper.getHabilitations(essai, this.groupeContacts.getDroits(), true));

        // Création de la source de données.
        final JRDataSource dataSource = this.jrDataSourceFactory.getInitializedObject(beans);
        BeanTool.setPropriete(beanSource, this.jrPropertyToSet, dataSource);
    }

    /**
     * Getter sur habilitationsHelper.
     * @return Retourne le habilitationsHelper.
     */
    HabilitationsHelper getHabilitationsHelper() {
        return this.habilitationsHelper;
    }

    /**
     * Setter pour habilitationsHelper.
     * @param habilitationsHelper le habilitationsHelper à écrire.
     */
    public void setHabilitationsHelper(final HabilitationsHelper habilitationsHelper) {
        this.habilitationsHelper = habilitationsHelper;
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
     * Getter sur helper.
     * @return Retourne le helper.
     */
    ContactsFillerHelper getHelper() {
        return this.helper;
    }

    /**
     * Setter pour helper.
     * @param helper le helper à écrire.
     */
    public void setHelper(final ContactsFillerHelper helper) {
        this.helper = helper;
    }

    /**
     * Getter sur groupeContacts.
     * @return Retourne le groupeContacts.
     */
    GroupeContacts getGroupeContacts() {
        return this.groupeContacts;
    }

    /**
     * Getter sur jrPropertyToSet.
     * @return Retourne le jrPropertyToSet.
     */
    String getJrPropertyToSet() {
        return this.jrPropertyToSet;
    }

}
