package fr.pharma.eclipse.utils.converter.filler.impl.essai;

import java.util.List;

import javax.annotation.Resource;

import fr.pharma.eclipse.domain.criteria.acteur.PersonneSearchCriteria;
import fr.pharma.eclipse.domain.enums.Droit;
import fr.pharma.eclipse.domain.model.acteur.Investigateur;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.habilitation.Habilitation;
import fr.pharma.eclipse.domain.model.sigrec.acteur.CoInvestigateurSigrec;
import fr.pharma.eclipse.domain.model.sigrec.common.IntervenantSigrec;
import fr.pharma.eclipse.domain.model.sigrec.essai.EssaiSigrec;
import fr.pharma.eclipse.factory.habilitation.HabilitationFactory;
import fr.pharma.eclipse.service.acteur.PersonneService;
import fr.pharma.eclipse.utils.converter.BeanConverter;
import fr.pharma.eclipse.utils.converter.filler.Filler;

/**
 * Filler en charge de populer les informations relatives aux investigateurs
 * dans les beans Essai à partir de bean EssaiSigrec.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class InvestigateurFiller implements Filler<EssaiSigrec, Essai> {

    /**
     * Converter.
     */
    @Resource(name = "investigateurBeanConverter")
    private BeanConverter<IntervenantSigrec, Investigateur> converter;

    /**
     * Service personne.
     */
    @Resource(name = "investigateurService")
    private PersonneService<Investigateur> service;

    /**
     * Fabrique d'objets Habilitation.
     */
    @Resource(name = "habilitationFactory")
    private HabilitationFactory habilitationFactory;

    /**
     * {@inheritDoc}
     */
    @Override
    public void fill(final EssaiSigrec source,
                     final Essai destination) {
        // Investigateurs principal
        if (source.getInvestigateurPrincipal() != null) {

            this.process(source.getInvestigateurPrincipal(), Droit.INVESTIGATEUR_PRINCIPAL, destination);
        }

        // Co investigateurs.
        for (final CoInvestigateurSigrec i : source.getCoInvestigateurs()) {
            this.process(i, Droit.INVESTIGATEUR_CO, destination);
        }
    }

    /**
     * Méthode en charge d'attacher l'investigateur en paramètre selon les droit
     * en paramètre sur l'essai en paramètre.
     * @param intervenant L'invistigateur.
     * @param droit Le droit.
     * @param essai L'essai.
     */
    private void process(final IntervenantSigrec intervenant,
                         final Droit droit,
                         final Essai essai) {
        // Recherche de l'investigateur en base.
        Investigateur investigateur = this.searchPersonne(intervenant);

        // S'il est null on l'initialise.
        if (investigateur == null) {
            investigateur = this.converter.convert(intervenant);
            investigateur = this.service.saveForSigrec(investigateur);
        }

        // Construction de l'habilitation.
        this.buildHabilitation(essai, investigateur, droit);
    }
    /**
     * Méthode en charge de construire l'habilitation pour l'essai, le droit et
     * la personne en paramètre.
     * @param essai L'essai.
     * @param personne La personne.
     * @param droit Le droit.
     */
    private void buildHabilitation(final Essai essai,
                                   final Personne personne,
                                   final Droit droit) {
        final Habilitation h = this.habilitationFactory.getInitializedObject();
        h.setPersonne(personne);
        h.setDetailContacts(essai.getDetailContacts());
        h.setDroit(droit);
        essai.getDetailContacts().getHabilitations().add(h);
    }

    /**
     * Retourne <code>true</code> si l'investigateur en parametre est présent en
     * base.
     * @param intervenant L'investigateur.
     * @return <code>true</code> si l'investigateur en parametre est présent en
     * base.
     */
    private Investigateur searchPersonne(final IntervenantSigrec intervenant) {
        final PersonneSearchCriteria criteria = new PersonneSearchCriteria();
        criteria.setNom(intervenant.getContact().getNom());
        criteria.setPrenom(intervenant.getContact().getPrenom());
        final List<Investigateur> results = this.service.getAll(criteria);
        if (results.size() > 0) {
            return results.get(0);
        } else {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean support(final EssaiSigrec source) {
        return source != null;
    }

    /**
     * Setter pour converter.
     * @param converter le converter à écrire.
     */
    public void setConverter(final BeanConverter<IntervenantSigrec, Investigateur> converter) {
        this.converter = converter;
    }

    /**
     * Setter pour service.
     * @param service le service à écrire.
     */
    public void setService(final PersonneService<Investigateur> service) {
        this.service = service;
    }

    /**
     * Setter pour habilitationFactory.
     * @param habilitationFactory le habilitationFactory à écrire.
     */
    public void setHabilitationFactory(final HabilitationFactory habilitationFactory) {
        this.habilitationFactory = habilitationFactory;
    }

}
