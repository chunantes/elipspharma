package fr.pharma.eclipse.service.dispensation.checker;

/**
 * Interface définissant le comportement des checkers.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public interface Checker<BEAN> {
    /**
     * Méthode en charge de checker le bean en paramètre.
     * @param bean Le bean à verifier.
     * @return <true> si le bean satisfait les conditions.
     */
    boolean check(BEAN bean);
}
