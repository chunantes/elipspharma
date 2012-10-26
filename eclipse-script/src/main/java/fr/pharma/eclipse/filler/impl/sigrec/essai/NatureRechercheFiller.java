package fr.pharma.eclipse.filler.impl.sigrec.essai;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pharma.eclipse.domain.enums.NatureRecherche;
import fr.pharma.eclipse.domain.model.sigrec.TrialScopeType;
import fr.pharma.eclipse.domain.model.sigrec.TrialType;
import fr.pharma.eclipse.domain.model.sigrec.essai.EssaiSigrec;
import fr.pharma.eclipse.filler.helper.BooleanFillerHelper;
import fr.pharma.eclipse.utils.converter.filler.Filler;

/**
 * Filler en charge de déterminer l'objet de recherche de l'essai.
 
 * @version $Revision$ $Date$
 */
public class NatureRechercheFiller
    implements Filler<TrialType, EssaiSigrec>
{
    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(NatureRechercheFiller.class);

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
        final TrialScopeType nature = source.getGeneralInformation().getTrialScope();
        if (this.helper.checkTrue(nature.getPharmacologie()))
        {
            destination.getDetailRecherche().setNatureRecherche(NatureRecherche.PHARMACOLOGIE);
        }
        else if (this.helper.checkTrue(nature.getTherapeutique()))
        {
            destination.getDetailRecherche().setNatureRecherche(NatureRecherche.THERAPEUTIQUE);
        }
        else if (this.helper.checkTrue(nature.getPronostique()))
        {
            destination.getDetailRecherche().setNatureRecherche(NatureRecherche.PRONOSTIQUE);
        }
        else if (this.helper.checkTrue(nature.getDiagnostique()))
        {
            destination.getDetailRecherche().setNatureRecherche(NatureRecherche.DIAGNOSTIQUE);
        }
        else if (this.helper.checkTrue(nature.getPhysiologie()))
        {
            destination.getDetailRecherche().setNatureRecherche(NatureRecherche.PHYSIOLOGIE);
        }
        else if (this.helper.checkTrue(nature.getPhysiopathologie()))
        {
            destination.getDetailRecherche().setNatureRecherche(NatureRecherche.PHYSIOPATHOLOGIE);
        }
        else if (this.helper.checkTrue(nature.getPrevention()))
        {
            destination.getDetailRecherche().setNatureRecherche(NatureRecherche.PREVENTION);
        }
        else if (this.helper.checkTrue(nature.getDepistage()))
        {
            destination.getDetailRecherche().setNatureRecherche(NatureRecherche.DEPISTAGE);
        }
        else if (this.helper.checkTrue(nature.getGenetique()))
        {
            destination.getDetailRecherche().setNatureRecherche(NatureRecherche.GENETIQUE);
        }
        else if (this.helper.checkTrue(nature.getEpidemiologie()))
        {
            destination.getDetailRecherche().setNatureRecherche(NatureRecherche.EPIDEMIOLOGIE);
        }
        else if (this.helper.checkTrue(nature.getOther()))
        {
            destination.getDetailRecherche().setNatureRecherche(NatureRecherche.AUTRE);
        }
        else
        {
            this.log.info("Aucune information sur la nature de la recherche.");
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean support(final TrialType source)
    {
        return null != source.getGeneralInformation()
               && null != source.getGeneralInformation().getTrialScope();
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
