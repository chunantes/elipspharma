package fr.pharma.eclipse.component.essai.helper;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.component.essai.TypeContact;
import fr.pharma.eclipse.domain.enums.Droit;
import fr.pharma.eclipse.domain.model.acteur.Cro;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.habilitation.Habilitation;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.EssaiUtils;
import fr.pharma.eclipse.utils.PersonneUtils;

/**
 * Test de la classe SelectableContactsFilter.
 
 * @version $Revision$ $Date$
 */
public class SelectableContactsFilterTest
    extends AbstractEclipseJUnitTest
{
    /**
     * Filter.
     */
    private SelectableContactsFilter filter;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp()
    {
        this.filter = new SelectableContactsFilter();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown()
    {
        this.filter = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit()
    {
        Assert.assertNotNull(this.filter);
    }

    /**
     * Test de la méthode filter.
     */
    @Test
    public void testFilterEmpty()
    {
        long id = 1;
        final Essai essai = EssaiUtils.makeEssaiTest(id++);
        final TypeContact typeContact = TypeContact.CRO;
        final List<Cro> selectableBeans = new ArrayList<Cro>();

        this.filter.filter(essai,
                           typeContact,
                           selectableBeans);

        Assert.assertTrue(selectableBeans.isEmpty());
    }

    /**
     * Test de la méthode filter.
     */
    @Test
    public void testFilter()
    {
        long id = 1;
        final Essai essai = EssaiUtils.makeEssaiTest(id++);
        final TypeContact typeContact = TypeContact.CRO;
        final Cro personne1 = PersonneUtils.makeCro(id++);
        final Cro personne2 = PersonneUtils.makeCro(id++);
        final Cro personne3 = PersonneUtils.makeCro(id++);

        final Habilitation habilitationActive = new Habilitation();
        habilitationActive.setPersonne(personne2);
        habilitationActive.setDetailContacts(essai.getDetailContacts());
        habilitationActive.setDroit(Droit.CRO);
        habilitationActive.setActive(true);
        essai.getDetailContacts().getHabilitations().add(habilitationActive);
        final Habilitation habilitationInActive = new Habilitation();
        habilitationInActive.setPersonne(personne1);
        habilitationInActive.setDetailContacts(essai.getDetailContacts());
        habilitationInActive.setDroit(Droit.CRO);
        habilitationInActive.setActive(false);
        essai.getDetailContacts().getHabilitations().add(habilitationInActive);

        final List<Cro> selectableBeans = new ArrayList<Cro>();
        selectableBeans.add(personne1);
        selectableBeans.add(personne2);
        selectableBeans.add(personne3);

        this.filter.filter(essai,
                           typeContact,
                           selectableBeans);

        Assert.assertTrue(selectableBeans.contains(personne1));
        Assert.assertFalse(selectableBeans.contains(personne2));
        Assert.assertTrue(selectableBeans.contains(personne3));
    }
}
