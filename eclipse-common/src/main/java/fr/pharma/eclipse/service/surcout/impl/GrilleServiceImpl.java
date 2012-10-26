package fr.pharma.eclipse.service.surcout.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.enums.surcout.Acte;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.surcout.DonneesPrevision;
import fr.pharma.eclipse.domain.model.surcout.Grille;
import fr.pharma.eclipse.domain.model.surcout.Item;
import fr.pharma.eclipse.domain.model.surcout.Resultat;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.service.surcout.GrilleService;
import fr.pharma.eclipse.service.surcout.counter.ActeCounter;
import fr.pharma.eclipse.service.surcout.processor.SurcoutProcessor;
import fr.pharma.eclipse.validator.save.impl.PrevisionValidator;

/**
 * Implémentaion des services de gestion d'un Grille.
 
 * @version $Revision$ $Date$
 */
public class GrilleServiceImpl
    extends GenericServiceImpl<Grille>
    implements GrilleService
{

    /**
     * Processors.
     */
    private List<SurcoutProcessor> processors;

    /**
     * Validateur de prévision.
     */
    @Resource(name = "previsionValidator")
    private PrevisionValidator previsionValidator;

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -7079403464777950502L;

    /**
     * Map de compteur d'actes.
     */
    private Map<Acte, ActeCounter> acteCounters;

    /**
     * Constructeur.
     * @param genericDao Dao.
     */
    public GrilleServiceImpl(final GenericDao<Grille> genericDao)
    {
        super(genericDao);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Item, Resultat> processReel(final Grille grille,
                                           final Calendar dateDebut,
                                           final Calendar dateFin)
    {
        if (dateDebut == null
            || dateFin == null)
        {
            throw new ValidationException("surcout.date",
                                          new String[]
                                          {"notNull" });
        }

        final Map<Item, Resultat> results = new HashMap<Item, Resultat>();
        for (final Item item : grille.getItems())
        {
            results.put(item,
                        this.processReel(item,
                                         grille.getDetailSurcout().getEssai(),
                                         dateDebut,
                                         dateFin));

        }
        return results;
    }

    /**
     * Méthode en charge d'appliquer la liste des processors pour l'item en paramètre.
     * @param item L'item.
     * @param essai L'essai.
     * @param dateDebut Date de début de l'intervalle de calcul.
     * @param dateFin Date de fin de l'intervalle de calcul.
     * @return le montant.
     */
    private Resultat processReel(final Item item,
                                 final Essai essai,
                                 final Calendar dateDebut,
                                 final Calendar dateFin)
    {
        final Resultat result = new Resultat();
        for (final SurcoutProcessor processor : this.processors)
        {
            final Resultat r = processor.process(item,
                                                 essai,
                                                 dateDebut,
                                                 dateFin);
            result.setMontant(result.getMontant().add(r.getMontant()));
            result.setNbActes(r.getNbActes());
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Item, Resultat> processPrevision(final Grille grille,
                                                final DonneesPrevision prevision)
    {

        this.previsionValidator.validate(prevision);

        final Map<Item, Resultat> results = new HashMap<Item, Resultat>();
        for (final Item item : grille.getItems())
        {
            results.put(item,
                        this.processPrevision(item,
                                              grille.getDetailSurcout().getEssai(),
                                              prevision));

        }
        return results;
    }
    /**
     * Méthode en charge d'appliquer la liste des processors pour l'item en paramètre.
     * @param item L'item.
     * @param essai L'essai.
     * @param dateDebut Date de début de l'intervalle de calcul.
     * @param dateFin Date de fin de l'intervalle de calcul.
     * @return le montant.
     */
    private Resultat processPrevision(final Item item,
                                      final Essai essai,
                                      final DonneesPrevision prevision)
    {
        final Resultat result = new Resultat();
        for (final SurcoutProcessor processor : this.processors)
        {
            result.setMontant(result.getMontant().add(processor.process(item,
                                                                        essai,
                                                                        prevision).getMontant()));
        }
        if (item.getActe() != null
            && this.acteCounters.containsKey(item.getActe()))
        {
            result.setNbActes(this.acteCounters.get(item.getActe()).process(essai,
                                                                            null,
                                                                            prevision));
        }
        return result;
    }
    /**
     * Setter pour processors.
     * @param processors le processors à écrire.
     */
    public void setProcessors(final List<SurcoutProcessor> processors)
    {
        this.processors = processors;
    }

    /**
     * Setter pour previsionValidator.
     * @param previsionValidator le previsionValidator à écrire.
     */
    public void setPrevisionValidator(final PrevisionValidator previsionValidator)
    {
        this.previsionValidator = previsionValidator;
    }

    /**
     * Setter pour acteCounters.
     * @param acteCounters le acteCounters à écrire.
     */
    public void setActeCounters(final Map<Acte, ActeCounter> acteCounters)
    {
        this.acteCounters = acteCounters;
    }
}
