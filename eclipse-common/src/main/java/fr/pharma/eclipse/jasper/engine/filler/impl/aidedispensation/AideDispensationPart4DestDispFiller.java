package fr.pharma.eclipse.jasper.engine.filler.impl.aidedispensation;

import java.util.ArrayList;
import java.util.List;

import fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean;
import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanFicheAideDispensationPart4;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.pharma.embedded.InfosDispensations;
import fr.pharma.eclipse.jasper.engine.filler.JasperReportBeanFiller;
import fr.pharma.eclipse.jasper.utils.JasperUtils;

/**
 * Filler en charge de valoriser les attributs de destinataires de dispensation
 * de {@link JRBeanFicheAideDispensationPart4}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class AideDispensationPart4DestDispFiller implements JasperReportBeanFiller {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 2647147234996952629L;

    /**
     * {@inheritDoc}
     */
    @Override
    public void fill(final Essai essai,
                     final JasperReportBean bean) {
        final JRBeanFicheAideDispensationPart4 partie4 = (JRBeanFicheAideDispensationPart4) bean;
        final InfosDispensations infosDispensation = essai.getDetailDonneesPharma().getInfosDispensations();

        // Formation de la liste des destinataires.
        final List<String> libelles = new ArrayList<String>();
        this.buildLibelle(libelles, infosDispensation.getDestinataireService(), "Service");
        this.buildLibelle(libelles, infosDispensation.getDestinatairePatient(), "Patient ou représentant du patient");
        this.buildLibelle(libelles, infosDispensation.getDestinataireInvestigateur(), "Investigateur");

        // Construction de l'attribut du bean Jasper.
        partie4.setDestinatairesDispensation(JasperUtils.formatterListeStrings(libelles));
    }

    /**
     * Méthode en charge d'ajouter un libellé, si besoin, à la liste passée en
     * paramètre.
     * @param libelles Liste des libellés à compléter.
     * @param toAdd Indique si le nouveau libellé doit être ajouté.
     * @param nouveauLibelle Nouveau libellé.
     */
    private void buildLibelle(final List<String> libelles,
                              final Boolean toAdd,
                              final String nouveauLibelle) {
        if (toAdd) {
            libelles.add(nouveauLibelle);
        }
    }

}
