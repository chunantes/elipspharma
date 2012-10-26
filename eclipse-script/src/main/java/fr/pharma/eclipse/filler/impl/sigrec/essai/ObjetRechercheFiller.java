package fr.pharma.eclipse.filler.impl.sigrec.essai;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pharma.eclipse.domain.enums.ObjetRecherche;
import fr.pharma.eclipse.domain.model.sigrec.ObjetRechercheType;
import fr.pharma.eclipse.domain.model.sigrec.TrialType;
import fr.pharma.eclipse.domain.model.sigrec.essai.EssaiSigrec;
import fr.pharma.eclipse.filler.helper.BooleanFillerHelper;
import fr.pharma.eclipse.utils.converter.filler.Filler;

/**
 * Filler en charge de déterminer l'objet de recherche de l'essai.
 
 * @version $Revision$ $Date$
 */
public class ObjetRechercheFiller
    implements Filler<TrialType, EssaiSigrec>
{
    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(ObjetRechercheFiller.class);

    /**
     * Helper pour gérer les booleens.
     */
    @Resource(name = "booleanFillerHelper")
    private BooleanFillerHelper helper;

    /**
     * {@inheritDoc}
     */
    @Override
    public void fill(final TrialType source,
                     final EssaiSigrec destination)
    {
        final ObjetRechercheType objet = source.getGeneralInformation().getObjetRecherche();
        if (this.helper.checkTrue(objet.getAlicament()))
        {
            destination.getDetailRecherche().setObjetRecherche(ObjetRecherche.ALICAMENT);
        }
        else if (this.helper.checkTrue(objet.getDmAutres()))
        {
            destination.getDetailRecherche().setObjetRecherche(ObjetRecherche.DM_AUTRE);
        }
        else if (this.helper.checkTrue(objet.getDmDiagnosticInvitro()))
        {
            destination
                    .getDetailRecherche()
                    .setObjetRecherche(ObjetRecherche.DM_DIAGNOSTIC_IN_VITRO);
        }
        else if (this.helper.checkTrue(objet.getDmImplantableActif()))
        {
            destination
                    .getDetailRecherche()
                    .setObjetRecherche(ObjetRecherche.DM_IMPLANTABLE_ACTIF);
        }
        else if (this.helper.checkTrue(objet.getImagerie()))
        {
            destination.getDetailRecherche().setObjetRecherche(ObjetRecherche.IMAGERIE);
        }
        else if (this.helper.checkTrue(objet.getMedicament()))
        {
            destination.getDetailRecherche().setObjetRecherche(ObjetRecherche.MEDICAMENT);
        }
        else if (this.helper.checkTrue(objet.getNutrition()))
        {
            destination.getDetailRecherche().setObjetRecherche(ObjetRecherche.NUTRITION);
        }
        else if (this.helper.checkTrue(objet.getOther()))
        {
            destination.getDetailRecherche().setObjetRecherche(ObjetRecherche.AUTRE);
        }
        else if (this.helper.checkTrue(objet.getRadiotherapie()))
        {
            destination.getDetailRecherche().setObjetRecherche(ObjetRecherche.RADIOTHERAPIE);
        }
        else if (this.helper.checkTrue(objet.getTherapieCellulaire()))
        {
            destination
                    .getDetailRecherche()
                    .setObjetRecherche(ObjetRecherche.THERAPIE_CELLULAIRE);
        }
        else if (this.helper.checkTrue(objet.getTherapieChirurgicale()))
        {
            destination
                    .getDetailRecherche()
                    .setObjetRecherche(ObjetRecherche.THERAPEUTIQUES_CHIR);
        }
        else if (this.helper.checkTrue(objet.getTherapieGenetique()))
        {
            destination.getDetailRecherche().setObjetRecherche(ObjetRecherche.THERAPIE_GENIQUE);
        }
        else
        {
            this.log.info("Aucune information sur l'objet de la recherche.");
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean support(final TrialType source)
    {
        return null != source.getGeneralInformation()
               && null != source.getGeneralInformation().getObjetRecherche();
    }

    /**
     * Setter pour helper.
     * @param helper le helper à écrire.
     */
    public void setHelper(final BooleanFillerHelper helper)
    {
        this.helper = helper;
    }
}
