package fr.pharma.eclipse.domain.criteria.acteur;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.enums.TypePersonne;

/**
 * Classe en charge de tester les méthodes de PersonneSearchCriteria.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PersonneSearchCriteriaTest {

    /**
     * Méthode en charge de tester le clear des properties du critère de
     * recherche.
     */
    @Test
    public void testClear() {
        final PersonneSearchCriteria criteria = new PersonneSearchCriteria();
        criteria.setNom("nom");
        criteria.setNomSociete("nom");
        criteria.setTypePersonne(TypePersonne.INVESTIGATEUR);
        criteria.setPrenom("prenom");
        criteria.setLogin("login");
        criteria.clear();
        Assert.assertNull(criteria.getNom());
        Assert.assertNull(criteria.getPrenom());
        Assert.assertNull(criteria.getNomSociete());
        Assert.assertNull(criteria.getTypePersonne());
        Assert.assertNull(criteria.getLogin());
    }

}
