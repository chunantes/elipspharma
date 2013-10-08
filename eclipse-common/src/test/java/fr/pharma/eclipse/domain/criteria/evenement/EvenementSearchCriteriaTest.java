package fr.pharma.eclipse.domain.criteria.evenement;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.enums.evenement.ResultatVisite;
import fr.pharma.eclipse.domain.enums.evenement.TypeEvenement;
import fr.pharma.eclipse.domain.enums.evenement.TypeVisite;
import fr.pharma.eclipse.domain.model.essai.Essai;

/**
 * Classe en charge de tester le critère de recherche sur Evenement.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EvenementSearchCriteriaTest {
    /**
     * Méthode en charge de tester le clear des properties du critère de
     * recherche.
     */
    @Test
    public void testClear() {
        final EvenementSearchCriteria criteria = new EvenementSearchCriteria();
        final Essai essai = new Essai();
        essai.setId(1L);
        criteria.setEssai(essai);
        criteria.setTypeEvenement(TypeEvenement.VISITE);
        criteria.setTypeVisite(TypeVisite.AUDIT_EXTERNE);
        criteria.setResultatVisite(ResultatVisite.EFFECTUE);
        criteria.setDateDebut(Calendar.getInstance());
        criteria.setDateFin(Calendar.getInstance());
        criteria.setResultVisiteVide(Boolean.TRUE);
        criteria.clear();
        Assert.assertNull(criteria.getTypeEvenement());
        Assert.assertNull(criteria.getEssai());
        Assert.assertNull(criteria.getTypeVisite());
        Assert.assertNull(criteria.getResultatVisite());
        Assert.assertNull(criteria.getDateDebut());
        Assert.assertNull(criteria.getDateFin());
        Assert.assertNull(criteria.getResultVisiteVide());
    }

}
