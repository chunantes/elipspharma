package fr.pharma.eclipse.factory.design;

import fr.pharma.eclipse.domain.enums.TypeDesignable;
import fr.pharma.eclipse.domain.model.design.Bras;
import fr.pharma.eclipse.domain.model.design.Sequence;
import fr.pharma.eclipse.factory.common.BeanObjectComplexFactory;

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
    public Sequence getInitializedObject(final Bras brasPrincipal,
                                         final String nomCompletParent) {
        final Sequence sequence = super.getInitializedObject();
      // final Bras bras = (Bras) CollectionUtils.find(brasPrincipal, new GenericPredicate("nomComplet", nomCompletParent));
        
        sequence.setType(TypeDesignable.SEQUENCE);
        sequence.setParent(brasPrincipal);
        return sequence;
    }
}
