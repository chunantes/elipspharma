package fr.pharma.eclipse.domain.model.sir.common;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.model.common.constants.BeanCstes;
import fr.pharma.eclipse.domain.model.sir.PersonneSir;

/**
 * Classe en charge de tester les méthodes génériques de BeanSirObject.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class BeanSirObjectTest {
    /**
     * Méthode en charge de tester le calcul de la valeur de hashCode sans id.
     */
    @Test
    public void calculHashCodeSansId() {
        final BeanSirObject bean = new PersonneSir();
        Assert.assertNotNull(bean.hashCode());
    }

    /**
     * Méthode en charge de tester le calcul de la valeur de hashCode avec id.
     */
    @Test
    public void calculHashCodeAvecId() {
        final BeanSirObject bean = new PersonneSir();
        bean.setId(1);
        Assert.assertEquals((BeanCstes.NB_PREMIER + bean.getId().hashCode()) * BeanCstes.NB_PREMIER, bean.hashCode());
    }

    /**
     * Méthode en charge de tester la méthode equals avec un objet autre null.
     */
    @Test
    public void equalsObjectWithOtherNull() {
        final BeanSirObject bean = new PersonneSir();
        final BeanSirObject other = null;
        Assert.assertEquals(false, bean.equals(other));
    }

    /**
     * Méthode en charge de tester la méthode equals avec le bean ayant un id
     * null.
     */
    @Test
    public void equalsObjectWithBeanIdNull() {
        final BeanSirObject bean = new PersonneSir();
        final BeanSirObject other = new PersonneSir();
        other.setId(1);
        Assert.assertEquals(false, bean.equals(other));
    }

    /**
     * Méthode en charge de tester la méthode equals avec l'objet autre ayant un
     * id null.
     */
    @Test
    public void equalsObjectWithOtherIdNull() {
        final BeanSirObject bean = new PersonneSir();
        bean.setId(1);
        final BeanSirObject other = new PersonneSir();
        Assert.assertEquals(false, bean.equals(other));
    }

    /**
     * Méthode en charge de tester la méthode equals avec 2 références
     * différentes pointant sur le même objet.
     */
    @Test
    public void equalsObjectWithTwoReferences() {
        final BeanSirObject bean = new PersonneSir();
        bean.setId(1);
        final BeanSirObject other = bean;
        Assert.assertEquals(true, bean.equals(other));
    }

    /**
     * Méthode en charge de tester la méthode equals avec un objet autre n'ayant
     * pas le bon type.
     */
    @Test
    public void equalsObjectWithOtherBadType() {
        final BeanSirObject bean = new PersonneSir();
        bean.setId(1);
        final Object other = new Object();
        Assert.assertEquals(false, bean.equals(other));
    }

    /**
     * Méthode en charge de tester la méthode equals avec 2 objets de même id.
     */
    @Test
    public void equalsObjectSame() {
        final BeanSirObject bean = new PersonneSir();
        bean.setId(1);
        final BeanSirObject other = new PersonneSir();
        other.setId(1);
        Assert.assertEquals(true, bean.equals(other));
    }

    /**
     * Méthode en charge de tester la méthode equals avec 2 objets ayant des ids
     * différents.
     */
    @Test
    public void equalsObjectDifferent() {
        final BeanSirObject bean = new PersonneSir();
        bean.setId(1);
        final BeanSirObject other = new PersonneSir();
        other.setId(2);
        Assert.assertEquals(false, bean.equals(other));
    }

}
