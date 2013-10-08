package fr.pharma.eclipse.jasper.engine.filler.impl.aidedispensation.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.apache.commons.lang.StringUtils;

import fr.pharma.eclipse.domain.enums.TypeDesignable;
import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanGroupeTraitement;
import fr.pharma.eclipse.domain.model.design.Bras;
import fr.pharma.eclipse.domain.model.design.Designable;
import fr.pharma.eclipse.domain.model.design.PrescriptionType;
import fr.pharma.eclipse.domain.model.design.Sequence;
import fr.pharma.eclipse.domain.model.design.embedded.TempsPrescription;
import fr.pharma.eclipse.jasper.constants.JasperConstants;
import fr.pharma.eclipse.jasper.utils.JasperUtils;
import fr.pharma.eclipse.service.helper.design.TimeHelper;

/**
 * Helper pour la transformation des bras en beans Jasper.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class GroupesTraitFillerHelper implements Serializable {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 43355856987496481L;

    /**
     * Elément pour faire un alinéas dans la présentation du nom.
     */
    private static final String ALINEA = "   ";

    /**
     * Helper pour les calculs de temps.
     */
    private TimeHelper timeHelper;

    /**
     * Méthode en charge de transformer un bras en
     * {@link JRBeanGroupeTraitement}.
     * @param designable Bras.
     * @param nbAlineas Nombre d'alinéas dans le nom.
     * @return Le bean {@link JRBeanGroupeTraitement} correspondant.
     */
    public JRBeanGroupeTraitement transform(final Designable designable,
                                            final int nbAlineas) {
        if (TypeDesignable.BRAS.equals(designable.getType())) {
            return this.transform((Bras) designable, nbAlineas);
        } else {
            return this.transform((Sequence) designable, nbAlineas);
        }
    }

    /**
     * Méthode en charge de transformer un bras en
     * {@link JRBeanGroupeTraitement}.
     * @param bras Bras.
     * @param nbAlineas Nombre d'alinéas dans le nom.
     * @return Le bean {@link JRBeanGroupeTraitement} correspondant.
     */
    private JRBeanGroupeTraitement transform(final Bras bras,
                                             final int nbAlineas) {
        final JRBeanGroupeTraitement jrBean = new JRBeanGroupeTraitement();
        jrBean.setDesign(this.addAlineas(bras.getNom(), nbAlineas));
        return jrBean;
    }

    /**
     * Méthode en charge de transformer une séquence en
     * {@link JRBeanGroupeTraitement}.
     * @param sequence Séquence.
     * @param nbAlineas Nombre d'alinéas dans le nom.
     * @return Le bean {@link JRBeanGroupeTraitement} correspondant.
     */
    private JRBeanGroupeTraitement transform(final Sequence sequence,
                                             final int nbAlineas) {
        final JRBeanGroupeTraitement jrBean = new JRBeanGroupeTraitement();
        jrBean.setDesign(this.addAlineas(sequence.getNom(), nbAlineas));
        jrBean.setDebut(this.toString(this.timeHelper.getDebut(sequence.getPrescriptions())));
        jrBean.setFin(this.toString(this.timeHelper.getFin(sequence.getPrescriptions())));
        jrBean.setProduits(JasperUtils.formatterListeStrings(this.toPduitsStrings(sequence.getPrescriptions())));
        return jrBean;
    }

    /**
     * Méthode en charge de construire le nom avec les alinéas voulus.
     * @param nom Nom.
     * @param nbAlineas Nombre d'alinéas à ajouter devant le nom.
     * @return Le nom, indenté.
     */
    private String addAlineas(final String nom,
                              final int nbAlineas) {
        final String alineas = StringUtils.repeat(GroupesTraitFillerHelper.ALINEA, nbAlineas);
        return alineas + nom;
    }

    /**
     * Méthode en charge de transformer la collection de prescriptions en liste
     * des noms de ses médicaments.
     * @param prescriptions Prescriptions.
     * @return Liste des noms de produits.
     */
    @SuppressWarnings("unchecked")
    private Set<String> toPduitsStrings(final SortedSet<PrescriptionType> prescriptions) {
        final Collection<? extends Object> collection = new ArrayList<Object>(prescriptions);

        // transformation en collection de noms de produits
        CollectionUtils.transform(collection, new Transformer() {

            @Override
            public Object transform(final Object input) {
                final PrescriptionType prescType = (PrescriptionType) input;
                return prescType.getProduit().getDenomination();
            }
        });

        // résultat
        return new HashSet<String>((Collection<String>) collection);
    }

    /**
     * Transforme un TempsPrescription en String.
     * @param bean Bean à transformer.
     * @return La chaîne correspondante.
     */
    private String toString(final TempsPrescription bean) {
        if ((bean == null) || (bean.getNb() == null) || (bean.getUnite() == null)) {
            return JasperConstants.DEFAULT_FIELD_VALUE;
        }
        return new StringBuilder().append(bean.getNb()).append(bean.getUnite().getLibelleCourt()).toString();
    }

    /**
     * Getter sur timeHelper.
     * @return Retourne le timeHelper.
     */
    TimeHelper getTimeHelper() {
        return this.timeHelper;
    }

    /**
     * Setter pour timeHelper.
     * @param timeHelper le timeHelper à écrire.
     */
    public void setTimeHelper(final TimeHelper timeHelper) {
        this.timeHelper = timeHelper;
    }

    /**
     * Getter sur alinea.
     * @return Retourne le alinea.
     */
    static String getAlinea() {
        return GroupesTraitFillerHelper.ALINEA;
    }

}
