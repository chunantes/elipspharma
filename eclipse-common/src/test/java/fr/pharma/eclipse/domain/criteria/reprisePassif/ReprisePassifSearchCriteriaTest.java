/**
 * 
 */
package fr.pharma.eclipse.domain.criteria.reprisePassif;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Netapsys
 * Classe en charge de tester la gestion des critères de recherche des reprises passif
 */
public class ReprisePassifSearchCriteriaTest {

	@Test
	public void testclear(){
		
		ReprisePassifSearchCriteria crit = createCriteriaTest();
		crit.clear();
		Assert.assertNull(crit.getEssaiPromoteur());
		Assert.assertNull(crit.getInitialesPatient());
	}
			
	
	public ReprisePassifSearchCriteria createCriteriaTest(){
		ReprisePassifSearchCriteria criteria = new ReprisePassifSearchCriteria();
		criteria.setEssaiPromoteur("test");
		criteria.setInitialesPatient("AA");
		return criteria;
		
	}
}
