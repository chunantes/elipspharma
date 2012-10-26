package fr.pharma.eclipse.component.stock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.lowagie.text.BadElementException;
import com.lowagie.text.DocumentException;

import fr.pharma.eclipse.component.wrapper.FluxStock;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stock.DispensationProduit;
import fr.pharma.eclipse.domain.model.stock.MvtStock;
import fr.pharma.eclipse.service.produit.ProduitService;
import fr.pharma.eclipse.utils.EclipseDocumentProcessor;
import fr.pharma.eclipse.utils.Utils;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Manager de flux wrappés.
 
 * @version $Revision$ $Date$
 */
public class FluxStockManager
{
    /**
     * Flux.
     */
    private List<FluxStock> flux = new ArrayList<FluxStock>();

    /**
     * Bean Selectionné.
     */
    private FluxStock beanSelected;

    /**
     * Processor pour les documents dataExporter.
     */
    @Resource(name = "eclipseDocProcessor")
    private EclipseDocumentProcessor processor;

    /**
     * Service de gestion des produits.
     */
    @Resource(name = "produitService")
    private ProduitService<Produit> produitService;

    /**
     * Méthode en charge de wrappé dans le manager la liste de mvtsStock en paramètre.
     * @param mvts Mouvements stock.
     * @return La liste de wrapper FluxStock.
     */
    public List<FluxStock> wrap(final List<MvtStock> mvts)
    {
        this.flux.clear();
        final Map<String, FluxStock> map = new HashMap<String, FluxStock>();

        // on traite tous les mvts.
        for (final MvtStock mvt : mvts)
        {
            final String key = this.buildKey(mvt);
            if (!map.containsKey(key))
            {
                map.put(key,
                        this.buildFluxStock(mvt));
            }
            else
            {
                map.get(key).getMvts().add(mvt);
                map.get(key).setQuantite(map.get(key).getQuantite()
                                         + mvt.getQuantite());
            }
        }

        this.flux.addAll(map.values());
        Collections.sort(this.flux,
                         new Comparator<FluxStock>() {

                             @Override
                             public int compare(final FluxStock o1,
                                                final FluxStock o2)
                             {
                                 return o2.getDate().getTime().compareTo(o1.getDate().getTime());
                             }

                         });
        return this.flux;
    }

    /**
     * Méthode en charge de construire un objet FluxStock à partir d'un mvt.
     * @param mvt Le mouvement.
     * @return L'objet FLuxStock.
     */
    public FluxStock buildFluxStock(final MvtStock mvt)
    {
        final FluxStock flux = new FluxStock();
        flux.getMvts().add(mvt);
        flux.setNumLot(mvt.getNumLot());
        flux.setConditionnement(mvt.getConditionnement());
        flux.setPharmacie(mvt.getPharmacie());
        flux.setDate(mvt.getDateCreation());
        flux.setProduit(mvt.getProduit());
        flux.setQuantite(mvt.getQuantite());
        flux.setType(mvt.getType());
        flux.setPersonne(mvt.getPersonne());
        flux.setEssai(mvt.getEssai());
        flux.setStockage(this.produitService.getStockageProduitPharma(mvt.getProduit(),
                                                                      mvt.getPharmacie()));

        if (mvt instanceof DispensationProduit)
        {
            final DispensationProduit d = (DispensationProduit) mvt;
            flux.setNumOrdonnancier(d.getDispensation().getNumOrdonnancier());
            flux.setPatient(d.getProduitPrescrit().getPrescription().getInclusion().getPatient());
        }

        return flux;
    }

    /**
     * Construit la clé sur un fluxStock.
     * @return la clé.
     */
    private String buildKey(final MvtStock mvt)
    {
        final StringBuffer sb = new StringBuffer();

        // si c'est un mvt par numero de traitement alors on créé une clé commune pour les numeros
        // de lots.
        if (!StringUtils.isEmpty(mvt.getNumTraitement()))
        {
            sb.append(mvt.getEssai().getNom())
                    .append(mvt.getProduit().getCode())
                    .append(mvt.getConditionnement().getLibelle())
                    .append(mvt.getType().getLibelle())
                    .append(mvt.getNumLot())
                    .append(mvt.getDateCreation().get(Calendar.MONTH))
                    .append(mvt.getDateCreation().get(Calendar.YEAR))
                    .append(mvt.getDateCreation().get(Calendar.DAY_OF_MONTH))
                    .append(mvt.getDateCreation().get(Calendar.MINUTE))
                    .append(mvt.getDateCreation().get(Calendar.HOUR_OF_DAY));

            // sinon une clé unique sur l'id.
        }
        else
        {
            sb.append(mvt.getId());
        }
        return sb.toString();
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
        final Calendar date = Calendar.getInstance(Locale.FRANCE);
        this.processor.preProcessPDF(document,
                                     "Consultation du flux au "
                                             + Utils.formatDate(date.getTime(),
                                                                EclipseConstants.PATTERN_SIMPLE));
    }

    /**
     * Méthode en charge de formatter les lignes de stocks pour l'export pdf.
     * @param stock Stock.
     * @return La chaine à afficher dans la cellule.
     */
    public String getQuantites(final FluxStock flux)
    {
        final StringBuffer sb = new StringBuffer();

        for (final MvtStock e : flux.getMvts())
        {
            sb.append(e.getQuantite()).append(" - ").append(e.getNumLot());
            if (e.getNumTraitement() != null)
            {
                sb.append(" - ").append(e.getNumTraitement());
            }
            else
            {
                sb.append(" - ").append(EclipseConstants.NON_APPLICABLE);
            }
            if (e.getDatePeremption() != null)
            {
                sb.append(" - ").append(Utils.formatDate(e.getDatePeremption().getTime(),
                                                         EclipseConstants.PATTERN_SIMPLE));
            }
            sb.append(" \r");
        }

        return sb.toString();
    }

    /**
     * Getter sur flux.
     * @return Retourne le flux.
     */
    public List<FluxStock> getFlux()
    {
        return this.flux;
    }

    /**
     * Setter pour flux.
     * @param flux le flux à écrire.
     */
    public void setFlux(final List<FluxStock> flux)
    {
        this.flux = flux;
    }

    /**
     * Getter sur beanSelected.
     * @return Retourne le beanSelected.
     */
    public FluxStock getBeanSelected()
    {
        return this.beanSelected;
    }

    /**
     * Setter pour beanSelected.
     * @param beanSelected le beanSelected à écrire.
     */
    public void setBeanSelected(final FluxStock beanSelected)
    {
        this.beanSelected = beanSelected;
    }

    /**
     * Setter pour processor.
     * @param processor Le processor à écrire.
     */
    public void setProcessor(final EclipseDocumentProcessor processor)
    {
        this.processor = processor;
    }


    /**
     * Setter pour produitService.
     * @param produitService Le produitService à écrire.
     */
    public void setProduitService(final ProduitService<Produit> produitService)
    {
        this.produitService = produitService;
    }

}
