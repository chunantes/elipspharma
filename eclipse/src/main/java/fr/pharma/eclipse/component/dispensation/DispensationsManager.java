package fr.pharma.eclipse.component.dispensation;

import java.io.IOException;

import javax.annotation.Resource;

import com.lowagie.text.BadElementException;
import com.lowagie.text.DocumentException;

import fr.pharma.eclipse.component.BeansManager;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.model.dispensation.Dispensation;
import fr.pharma.eclipse.domain.model.stock.DispensationProduit;
import fr.pharma.eclipse.service.dispensation.DispensationService;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Manager de liste de dispensations.
 
 * @version $Revision$ $Date$
 */
public class DispensationsManager
    extends BeansManager<Dispensation>
{

    /**
     * SserialVersionUID.
     */
    private static final long serialVersionUID = 197691295028833783L;

    /**
     * Service dispensation.
     */
    @Resource(name = "dispensationService")
    private DispensationService dispensationService;

    /**
     * @param searchCriteria
     */
    public DispensationsManager(final SearchCriteria searchCriteria)
    {
        super(searchCriteria);
    }

    /**
     * Applique un pre processor avant le génération des documents dataExporter.
     * @param document Document.
     * @throws IOException en cas d'erreur.
     * @throws BadElementException en cas d'erreur .
     * @throws DocumentException en cas d'erreur.
     */
    public void preProcessPDF(final Object document)
        throws IOException,
            BadElementException,
            DocumentException
    {
        this.getDocProcessor().preProcessPDF(document,
                                             "Liste des dispensations");
    }

    /**
     * Retourne la personne correspondant à la dispensation en paramètre.
     * @return La personne.
     */
    public String getPersonneAsString(final Dispensation dispensation)
    {
        final Dispensation dispensationReattached =
            this.dispensationService.reattach(dispensation);
        String personne = "";
        if (!dispensationReattached.getDispensationsProduit().isEmpty())
        {
            personne +=
                dispensationReattached
                        .getDispensationsProduit()
                        .first()
                        .getPersonne()
                        .getPrenom()
                        + dispensationReattached
                                .getDispensationsProduit()
                                .first()
                                .getPersonne()
                                .getNom();
        }
        return personne;
    }

    /**
     * Getter pour personne.
     * @return Le personne
     */
    public String getProduits(final Dispensation dispensation)
    {
        final Dispensation dispensationReattached =
            this.dispensationService.reattach(dispensation);
        final StringBuffer sb = new StringBuffer();

        for (final DispensationProduit d : dispensationReattached.getDispensationsProduit())
        {
            sb.append(d.getProduit().getNom()).append(" - ").append(d.getNumLot());
            if (d.getNumTraitement() != null)
            {
                sb.append(" - ").append(d.getNumTraitement());
            }
            else
            {
                sb.append(" - ").append(EclipseConstants.NON_APPLICABLE);
            }
            sb.append(" - ").append(d.getQuantite());
            sb.append(" \r");
        }

        return sb.toString();
    }

    /**
     * Setter pour dispensationService.
     * @param dispensationService Le dispensationService à écrire.
     */
    public void setDispensationService(final DispensationService dispensationService)
    {
        this.dispensationService = dispensationService;
    }
}
