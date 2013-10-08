package fr.pharma.eclipse.domain.model.common;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.model.common.constants.BeanCstes;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;

/**
 * Classe en charge de tester les méthodes génériques de BeanObject.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class BeanObjectTest {
    /**
     * Méthode en charge de tester le calcul de la valeur de hashCode sans id.
     */
    @Test
    public void calculHashCodeSansId() {
        final BeanObject bean = new Pharmacie();
        Assert.assertNotNull(bean.hashCode());
        Assert.assertEquals(Boolean.FALSE, bean.getSelected());
    }

    /**
     * Méthode en charge de tester le calcul de la valeur de hashCode avec id.
     */
    @Test
    public void calculHashCodeAvecId() {
        final BeanObject bean = new Pharmacie();
        bean.setId(1L);
        Assert.assertEquals((BeanCstes.NB_PREMIER + bean.getId().hashCode()) * BeanCstes.NB_PREMIER, bean.hashCode());
    }

    /**
     * Méthode en charge de tester la méthode equals avec un objet autre null.
     */
    @Test
    public void equalsObjectWithOtherNull() {
        final BeanObject bean = new Pharmacie();
        final BeanObject other = null;
        Assert.assertEquals(false, bean.equals(other));
    }

    /**
     * Méthode en charge de tester la méthode equals avec le bean ayant un id
     * null.
     */
    @Test
    public void equalsObjectWithBeanIdNull() {
        final BeanObject bean = new Pharmacie();
        final BeanObject other = new Pharmacie();
        other.setId(1L);
        Assert.assertEquals(false, bean.equals(other));
    }

    /**
     * Méthode en charge de tester la méthode equals avec l'objet autre ayant un
     * id null.
     */
    @Test
    public void equalsObjectWithOtherIdNull() {
        final BeanObject bean = new Pharmacie();
        bean.setId(1L);
        final BeanObject other = new Pharmacie();
        Assert.assertEquals(false, bean.equals(other));
    }

    /**
     * Méthode en charge de tester la méthode equals avec 2 références
     * différentes pointant sur le même objet.
     */
    @Test
    public void equalsObjectWithTwoReferences() {
        final BeanObject bean = new Pharmacie();
        bean.setId(1L);
        final BeanObject other = bean;
        Assert.assertEquals(true, bean.equals(other));
    }

    /**
     * Méthode en charge de tester la méthode equals avec un objet autre n'ayant
     * pas le bon type.
     */
    @Test
    public void equalsObjectWithOtherBadType() {
        final BeanObject bean = new Pharmacie();
        bean.setId(1L);
        final Object other = new Object();
        Assert.assertEquals(false, bean.equals(other));
    }

    /**
     * Méthode en charge de tester la méthode equals avec 2 objets de même id.
     */
    @Test
    public void equalsObjectSame() {
        final BeanObject bean = new Pharmacie();
        bean.setId(1L);
        final BeanObject other = new Pharmacie();
        other.setId(1L);
        Assert.assertEquals(true, bean.equals(other));
    }

    /**
     * Méthode en charge de tester la méthode equals avec 2 objets ayant des ids
     * différents.
     */
    @Test
    public void equalsObjectDifferent() {
        final BeanObject bean = new Pharmacie();
        bean.setId(1L);
        final BeanObject other = new Pharmacie();
        other.setId(2L);
        Assert.assertEquals(false, bean.equals(other));
    }

    /**
     * Méthode en charge de tester la sélection d'un object.
     */
    @Test
    public void selected() {
        final BeanObject bean = new Pharmacie();
        Assert.assertEquals(Boolean.FALSE, bean.getSelected());
        bean.setSelected(Boolean.TRUE);
        Assert.assertEquals(Boolean.TRUE, bean.getSelected());
    }
}
