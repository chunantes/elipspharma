package fr.pharma.eclipse.comparator.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.domain.model.essai.Essai;

public class EclipseListComparatorTest {

	/**
	 * Test avec deux objets dont les id ne sont pas nuls et sont positifs.
	 */
	@Test
	public void comparePos() {
		BeanObject o1 = new Essai();
		o1.setId(1L);
		BeanObject o2 = new Essai();
		o1.setId(2L);
		
		EclipseListComparator comparator = new EclipseListComparator();
		
		assertTrue(comparator.compare(o1, o2) < 0);
		assertTrue(comparator.compare(o2, o1) > 0);
	}

	/**
	 * Test avec deux objets dont les id ne sont pas nuls et sont négatifs.
	 */
	@Test
	public void compareNeg() {
		BeanObject o1 = new Essai();
		o1.setId(-1L);
		BeanObject o2 = new Essai();
		o1.setId(-2L);
		
		EclipseListComparator comparator = new EclipseListComparator();
		
		assertTrue(comparator.compare(o1, o2) < 0);
		assertTrue(comparator.compare(o2, o1) > 0);
	}

	/**
	 * Test avec un objet dont l'id est négatif et un objet dont l'id est positif.
	 */
	@Test
	public void comparePosNeg() {
		BeanObject o1 = new Essai();
		o1.setId(-1L);
		BeanObject o2 = new Essai();
		o1.setId(2L);
		
		EclipseListComparator comparator = new EclipseListComparator();
		
		assertTrue(comparator.compare(o1, o2) < 0);
		assertTrue(comparator.compare(o2, o1) > 0);
	}
	
	/**
	 * Test avec deux objets dont les id sont nuls.
	 */
	@Test
	public void compareNulls() {
		BeanObject o1 = new Essai();
		BeanObject o2 = new Essai();
		
		EclipseListComparator comparator = new EclipseListComparator();
		
		assertEquals(0, comparator.compare(o1, o2));
		assertEquals(0, comparator.compare(o2, o1));
	}

	/**
	 * Test avec un objet dont l'id est nul et un objet dont l'id n'est pas nul.
	 */
	@Test
	public void compareNull() {
		BeanObject o1 = new Essai();
		o1.setId(1L);
		BeanObject o2 = new Essai();
		
		EclipseListComparator comparator = new EclipseListComparator();
		
		assertTrue(comparator.compare(o1, o2) < 0);
		assertTrue(comparator.compare(o2, o1) > 0);
	}
	
	/**
	 * Test avec deux objets dont les id sont égaux.
	 */
	@Test
	public void compareEquals() {
		BeanObject o1 = new Essai();
		o1.setId(1L);
		BeanObject o2 = new Essai();
		o2.setId(1L);
		
		EclipseListComparator comparator = new EclipseListComparator();
		
		assertEquals(0, comparator.compare(o1, o2));
		assertEquals(0, comparator.compare(o2, o1));
	}

}
