package fr.pharma.eclipse.jasper.engine.filler.impl.aidedispensation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;

import fr.pharma.eclipse.domain.enums.Droit;
import fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean;
import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanFicheAideDispensationPart4;
import fr.pharma.eclipse.domain.model.acteur.Investigateur;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.habilitation.Habilitation;
import fr.pharma.eclipse.jasper.engine.filler.JasperReportBeanFiller;
import fr.pharma.eclipse.jasper.utils.JasperUtils;
import fr.pharma.eclipse.service.habilitation.helper.HabilitationsHelper;

/**
 * Filler en charge de valoriser les attributs de prescripteurs du bean
 * {@link JRBeanFicheAideDispensationPart4}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class AideDispensationPart4PrescripteursFiller implements JasperReportBeanFiller {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 7946984761184399964L;

    /**
     * Helper pour la gestion des habilitations.
     */
    private HabilitationsHelper habilitationsHelper;

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void fill(final Essai essai,
                     final JasperReportBean bean) {
        final JRBeanFicheAideDispensationPart4 partie4 = (JRBeanFicheAideDispensationPart4) bean;

        // Investigateur principal
        final Investigateur invPrincipal = this.habilitationsHelper.getInvestigateurPrincipal(essai);
        partie4.setInvestigateurPrincipal(JasperUtils.makeLibelleInvestigateur(invPrincipal));

        // Co-investigateurs
        final List<Droit> droits = new ArrayList<Droit>();
        droits.add(Droit.INVESTIGATEUR_CO);
        final Collection<? extends Object> coInv = new ArrayList<Object>(this.habilitationsHelper.getHabilitations(essai, droits));
        // ... récupération des noms des co-investigateurs
        CollectionUtils.transform(coInv, new Transformer() {

            @Override
            public Object transform(final Object input) {
                final Habilitation current = (Habilitation) input;
                return JasperUtils.makeLibelleInvestigateur((Investigateur) current.getPersonne());
            }
        });
        // ... formation de la liste des noms et stockage dans la propriété du
        // bean.
        partie4.setCoInvestigateurs(JasperUtils.formatterListeStrings((Collection<String>) coInv));
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

}
