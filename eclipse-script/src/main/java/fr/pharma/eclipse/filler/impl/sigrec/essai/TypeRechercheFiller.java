package fr.pharma.eclipse.filler.impl.sigrec.essai;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pharma.eclipse.domain.enums.TypeRecherche;
import fr.pharma.eclipse.domain.model.sigrec.TrialType;
import fr.pharma.eclipse.domain.model.sigrec.TypeRechercheType;
import fr.pharma.eclipse.domain.model.sigrec.essai.EssaiSigrec;
import fr.pharma.eclipse.filler.helper.BooleanFillerHelper;
import fr.pharma.eclipse.utils.converter.filler.Filler;

/**
 * Filler déterminant et copiant le type de recherche de l'essai.
 
 * @version $Revision$ $Date$
 */
public class TypeRechercheFiller
    implements Filler<TrialType, EssaiSigrec>
{
    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(TypeRechercheFiller.class);

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
        final TypeRechercheType type = source.getGeneralInformation().getTypeRecherche();
        if (this.helper.checkTrue(type.getRechercheBiomedicale()))
        {
            destination
                    .getDetailRecherche()
                    .setTypeRecherche(TypeRecherche.RECHERCHE_BIOMEDICALE);
        }
        else if (this.helper.checkTrue(type.getSoinsCourants()))
        {
            destination.getDetailRecherche().setTypeRecherche(TypeRecherche.SOINS_COURANTS);
        }
        else if (this.helper.checkTrue(type.getObservationnelle()))
        {
            destination.getDetailRecherche().setTypeRecherche(TypeRecherche.OBSERVATIONNELLE);
        }
        else if (this.helper.checkTrue(type.getSurDonnees()))
        {
            destination.getDetailRecherche().setTypeRecherche(TypeRecherche.SUR_DONNEES);
        }
        else if (this.helper.checkTrue(type.getCollectionsBiologiques()))
        {
            destination
                    .getDetailRecherche()
                    .setTypeRecherche(TypeRecherche.COLLECTIONS_BIOLOGIQUES);
        }
        else
        {
            this.log.info("Aucune information sur le type de la recherche.");
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean support(final TrialType source)
    {
        return null != source.getGeneralInformation()
               && null != source.getGeneralInformation().getTypeRecherche();
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
