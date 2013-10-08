package fr.pharma.eclipse.utils.converter.filler.impl.essai;

import java.util.List;

import javax.annotation.Resource;

import fr.pharma.eclipse.domain.criteria.acteur.PersonneSearchCriteria;
import fr.pharma.eclipse.domain.enums.Droit;
import fr.pharma.eclipse.domain.model.acteur.Cro;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.habilitation.Habilitation;
import fr.pharma.eclipse.domain.model.sigrec.acteur.CROSigrec;
import fr.pharma.eclipse.domain.model.sigrec.essai.EssaiSigrec;
import fr.pharma.eclipse.factory.habilitation.HabilitationFactory;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.utils.converter.BeanConverter;
import fr.pharma.eclipse.utils.converter.filler.Filler;

/**
 * Filler en charge de populer les cros d'un Essai à partir des cros d'un Essai
 * Sigrec.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class CroFiller implements Filler<EssaiSigrec, Essai> {

    /**
     * Converter CroSigrec => Cro.
     */
    @Resource(name = "croConverter")
    private BeanConverter<CROSigrec, Cro> converter;

    /**
     * Service cro.
     */
    @Resource(name = "croService")
    private GenericService<Cro> croService;

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
        final PersonneSearchCriteria criteria = new PersonneSearchCriteria();
        List<Cro> cros = null;
        for (final CROSigrec c : source.getCros()) {
            criteria.setNomSociete(c.getContact().getRaisonSociale());
            cros = this.croService.getAll(criteria);
            Cro cro = null;
            // S'il existe, on l'affecte sinon on le créé et on l'enregistre.
            if (cros.size() > 0) {
                cro = cros.get(0);

            } else {
                cro = this.converter.convert(c);
                cro = this.croService.save(cro);
            }

            // habilitation
            final Habilitation h = this.habilitationFactory.getInitializedObject();
            h.setDroit(Droit.CRO);
            h.setPersonne(cro);
            h.setDetailContacts(destination.getDetailContacts());
            destination.getDetailContacts().getHabilitations().add(h);
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean support(final EssaiSigrec source) {
        return source.getCros() != null;
    }

    /**
     * Setter pour converter.
     * @param converter le converter à écrire.
     */
    public void setConverter(final BeanConverter<CROSigrec, Cro> converter) {
        this.converter = converter;
    }
    /**
     * Setter pour croService.
     * @param croService le croService à écrire.
     */
    public void setCroService(final GenericService<Cro> croService) {
        this.croService = croService;
    }
    /**
     * Setter pour habilitationFactory.
     * @param habilitationFactory le habilitationFactory à écrire.
     */
    public void setHabilitationFactory(final HabilitationFactory habilitationFactory) {
        this.habilitationFactory = habilitationFactory;
    }

}
