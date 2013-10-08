package fr.pharma.eclipse.component.design.validator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.SortedSet;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import fr.pharma.eclipse.comparator.design.TempsPrescriptionComparator;
import fr.pharma.eclipse.domain.model.design.PrescriptionType;
import fr.pharma.eclipse.domain.model.design.Sequence;
import fr.pharma.eclipse.service.helper.design.TimeHelper;
import fr.pharma.eclipse.utils.FacesUtils;

/**
 * Classe de validation de saisie d'une sequence en fonction des prescriptions.
 * Appellée lors de l'ajout d'une PrescriptionType.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class SequenceValidator implements Serializable {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 2923296524153517804L;

    /**
     * Utilitaire Faces.
     */
    @Resource(name = "facesUtils")
    private FacesUtils facesUtils;

    /**
     * Helper de gestion des temps.
     */
    @Resource(name = "timeHelper")
    private TimeHelper timeHelper;

    /**
     * Méthode en charge de valider la sequence en parametre en fonction de la
     * prescription à ajouter.
     * @param prescription La prescriptionType que l'on souhaite ajouter à la
     * séquence.
     * @param sequence La sequence.
     * @return <true> si la séquence est validée.
     */
    public boolean validateSequence(final PrescriptionType prescription,
                                    final Sequence sequence) {

        final Collection<Sequence> coll = this.filtreSequences(sequence.getParent().getSequences(), sequence);
        final List<PrescriptionType> prescriptions = new ArrayList<PrescriptionType>();
        prescriptions.add(prescription);
        final TempsPrescriptionComparator comparator = new TempsPrescriptionComparator();
        for (final Sequence s : coll) {
            if ((comparator.compare(prescription.getDebut(), s.getFin()) != 1) && (comparator.compare(s.getDebut(), this.timeHelper.getFin(prescriptions)) != 1)) {
                this.facesUtils.addMessage(FacesMessage.SEVERITY_ERROR, "sequence.chevauchement");
                FacesContext.getCurrentInstance().validationFailed();
                return false;
            }
        }
        return true;
    }
    /**
     * Méthode en charge de filtrer la liste de sequence en retirant la sequence
     * en deuxième paramètre, et les sequences avec un identifiant à null.
     * @param sequences Liste de sequences.
     * @param sequence la sequence courante.
     * @return la collection de sequence filtrée.
     */
    @SuppressWarnings("all")
    private Collection<Sequence> filtreSequences(final SortedSet<Sequence> sequences,
                                                 final Sequence sequence) {

        return CollectionUtils.selectRejected(sequences, new Predicate() {

            @Override
            public boolean evaluate(final Object object) {
                final Sequence seq = (Sequence) object;
                return (seq.getId() == null) || (seq.getId() == sequence.getId());
            }
        });

    }

    /**
     * Setter pour facesUtils.
     * @param facesUtils le facesUtils à écrire.
     */
    public void setFacesUtils(final FacesUtils facesUtils) {
        this.facesUtils = facesUtils;
    }
    /**
     * Setter pour timeHelper.
     * @param timeHelper le timeHelper à écrire.
     */
    public void setTimeHelper(final TimeHelper timeHelper) {
        this.timeHelper = timeHelper;
    }

}
