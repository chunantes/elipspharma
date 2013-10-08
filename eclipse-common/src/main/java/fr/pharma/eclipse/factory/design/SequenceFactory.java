package fr.pharma.eclipse.factory.design;

import org.apache.commons.collections.CollectionUtils;

import fr.pharma.eclipse.domain.enums.TypeDesignable;
import fr.pharma.eclipse.domain.model.design.Bras;
import fr.pharma.eclipse.domain.model.design.Sequence;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.factory.common.BeanObjectComplexFactory;
import fr.pharma.eclipse.predicate.GenericPredicate;

/**
 * Factory de Bean Sequence.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class SequenceFactory extends BeanObjectComplexFactory<Sequence> {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -3171922165561292902L;

    /**
     * Constructeur.
     * @param bean Classe.
     */
    public SequenceFactory(final Class<Sequence> bean) {
        super(bean);
    }

    /**
     * Méthode en charge de construire une séquence.
     * @param essai L'essai.
     * @param nomCompletParent Nom complet du parent.
     * @return La séquence.
     */
    public Sequence getInitializedObject(final Essai essai,
                                         final String nomCompletParent) {
        final Sequence sequence = super.getInitializedObject();
        final Bras bras = (Bras) CollectionUtils.find(essai.getDetailDesign().getBras(), new GenericPredicate("nomComplet", nomCompletParent));
        sequence.setType(TypeDesignable.SEQUENCE);
        sequence.setParent(bras);
        return sequence;
    }
}
