package fr.pharma.eclipse.filler.impl.sigrec.essai;

import java.util.Collection;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import fr.pharma.eclipse.domain.model.sigrec.IntervenantType;
import fr.pharma.eclipse.domain.model.sigrec.InvestigatorType;
import fr.pharma.eclipse.domain.model.sigrec.IntervenantsType.Intervenant;
import fr.pharma.eclipse.domain.model.sigrec.acteur.ARCInvestigateurSigrec;
import fr.pharma.eclipse.domain.model.sigrec.acteur.CentreSigrec;
import fr.pharma.eclipse.domain.model.sigrec.acteur.CoInvestigateurSigrec;
import fr.pharma.eclipse.domain.model.sigrec.acteur.InvestigateurSigrec;
import fr.pharma.eclipse.predicate.GenericPredicate;
import fr.pharma.eclipse.utils.converter.filler.Filler;
import fr.pharma.eclipse.utils.converter.impl.GenericBeanConverter;

/**
 * Filler en charge de déterminer les Investigateurs SIGERC et de les ajouter au centre.
 
 * @version $Revision$ $Date$
 */
public class InvestigateurFiller
    implements Filler<InvestigatorType, CentreSigrec>
{
    /**
     * Id ARC investigateur.
     */
    public static final String FONCTION_ARC = "104";

    /**
     * Id Investigateur principal.
     */
    public static final String FONCTION_PRINCIPAL = "101";

    /**
     * Id co investigateur.
     */
    public static final String FONCTION_CO = "102";

    /**
     * Id Investigateur associe.
     */
    public static final String FONCTION_ASSO = "103";

    /**
     * Converter en charge de convertir un IntervenantType en InvestigateurSigrec.
     */
    @Resource(name = "investigateurSigrecConverter")
    private GenericBeanConverter<IntervenantType, InvestigateurSigrec> investigateurConverter;

    /**
     * Converter en charge de convertir un IntervenantType en ARCInvestigateurSigrec.
     */
    @Resource(name = "arcInvestigateurSigrecConverter")
    private GenericBeanConverter<IntervenantType, ARCInvestigateurSigrec> arcInvestigateurConverter;

    /**
     * Converter en charge de convertir un IntervenantType en CoInvestigateurSigrec.
     */
    @Resource(name = "coInvestigateurSigrecConverter")
    private GenericBeanConverter<IntervenantType, CoInvestigateurSigrec> coInvestigateurConverter;

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("all")
    public void fill(final InvestigatorType source,
                     final CentreSigrec destination)
    {
        final Collection<Intervenant> intervenants = source.getIntervenants().getIntervenant();

        // Liste de Intervenant avec la fonction ARC investigateur.
        final Collection<Intervenant> arcInvestigateurs =
            this.filtrer(intervenants,
                         InvestigateurFiller.FONCTION_ARC);

        // List d'intervenant avec la fonction investigateur coordinateur ou asocié.
        final Collection<Intervenant> coInvestigateurs =
            this.filtrer(intervenants,
                         InvestigateurFiller.FONCTION_ASSO);
        coInvestigateurs.addAll(this.filtrer(intervenants,
                                             InvestigateurFiller.FONCTION_CO));

        // List d'intervenant avec la fonction investigateur coordinateur ou asocié.
        final Collection<Intervenant> investigateurs =
            this.filtrer(intervenants,
                         InvestigateurFiller.FONCTION_PRINCIPAL);

        // arc investigateurs
        ARCInvestigateurSigrec arc = null;
        for (final IntervenantType i : arcInvestigateurs)
        {
            arc = this.arcInvestigateurConverter.convert(i);
            arc.setCentre(destination);
            destination.getArcInvestigateurs().add(arc);
        }

        // co investigateur
        CoInvestigateurSigrec co = null;
        for (final IntervenantType i : coInvestigateurs)
        {
            co = this.coInvestigateurConverter.convert(i);
            co.setCentre(destination);
            destination.getCoInvestigateurs().add(co);
        }

        // principals
        InvestigateurSigrec invest = null;
        for (final IntervenantType i : investigateurs)
        {
            invest = this.investigateurConverter.convert(i);
            invest.setCentre(destination);
            destination.getInvestigateurs().add(invest);
        }

    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean support(final InvestigatorType source)
    {
        return null != source.getIntervenants();

    }

    /**
     * Méthode en charge de filtrer la collection en paramètre avec la valeur de la fonction
     * spécifiée.
     * @param collection La collection à filtrer.
     * @param valeur de l'identifiant de la fonction.
     * @return La collection.
     */
    @SuppressWarnings("all")
    private Collection<Intervenant> filtrer(final Collection<Intervenant> collection,
                                            final String valeur)
    {
        return CollectionUtils.select(collection,
                                      new GenericPredicate("fonctions.id",
                                                           valeur));
    }
    /**
     * Getter sur investigateurConverter.
     * @return Retourne le investigateurConverter.
     */
    public GenericBeanConverter<IntervenantType, InvestigateurSigrec> getInvestigateurConverter()
    {
        return this.investigateurConverter;
    }
    /**
     * Setter pour investigateurConverter.
     * @param investigateurConverter le investigateurConverter à écrire.
     */
    public void setInvestigateurConverter(final GenericBeanConverter<IntervenantType, 
                                          InvestigateurSigrec> investigateurConverter)
    {
        this.investigateurConverter = investigateurConverter;
    }
    /**
     * Getter sur arcInvestigateurConverter.
     * @return Retourne le arcInvestigateurConverter.
     */
    public GenericBeanConverter<IntervenantType, ARCInvestigateurSigrec> getArcInvestigateurConverter()
    {
        return this.arcInvestigateurConverter;
    }
    /**
     * Setter pour arcInvestigateurConverter.
     * @param arcInvestigateurConverter le arcInvestigateurConverter à écrire.
     */
    public void setArcInvestigateurConverter(final GenericBeanConverter<IntervenantType, 
                                             ARCInvestigateurSigrec> arcInvestigateurConverter)
    {
        this.arcInvestigateurConverter = arcInvestigateurConverter;
    }
    /**
     * Getter sur coInvestigateurConverter.
     * @return Retourne le coInvestigateurConverter.
     */
    public GenericBeanConverter<IntervenantType, CoInvestigateurSigrec> getCoInvestigateurConverter()
    {
        return this.coInvestigateurConverter;
    }
    /**
     * Setter pour coInvestigateurConverter.
     * @param coInvestigateurConverter le coInvestigateurConverter à écrire.
     */
    public void setCoInvestigateurConverter(final GenericBeanConverter<IntervenantType, CoInvestigateurSigrec> coInvestigateurConverter)
    {
        this.coInvestigateurConverter = coInvestigateurConverter;
    }

}
