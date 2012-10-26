package fr.pharma.eclipse.component.acteur.helper;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.enums.TypePersonne;
import fr.pharma.eclipse.domain.model.acteur.ArcInvestigateur;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test du helper PersonneHelper.
 
 * @version $Revision$ $Date$
 */
public class PersonneHelperTest
    extends AbstractEclipseJUnitTest
{

    /**
     * Helper.
     */
    private PersonneHelper helper;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp()
    {
        this.helper = new PersonneHelper();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown()
    {
        this.helper = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit()
    {
        Assert.assertNotNull(this.helper);
    }

    /**
     * Test de la méthode sortPersonnes.
     */
    @Test
    public void testSortPersonnes()
    {

        final List<Personne> personnes = new ArrayList<Personne>();

        final Personne p1 = this.createPersonne(TypePersonne.ARC_INVESTIGATEUR,
                                                "nom1",
                                                "prenom");
        final Personne p2 = this.createPersonne(TypePersonne.CRO,
                                                "nom2",
                                                "prenom");
        final Personne p3 = this.createPersonne(TypePersonne.ARC_INVESTIGATEUR,
                                                "na",
                                                "prenom");
        final Personne p4 = this.createPersonne(TypePersonne.ARC_INVESTIGATEUR,
                                                "na",
                                                "pa");
        personnes.add(p1);
        personnes.add(p2);
        personnes.add(p3);
        personnes.add(p4);

        final List<Personne> result = this.helper.sortPersonnes(personnes);

        Assert.assertEquals("nom2",
                            result.get(0).getNom());
        Assert.assertEquals("na",
                            result.get(1).getNom());
        Assert.assertEquals("pa",
                            result.get(1).getPrenom());
        Assert.assertEquals("na",
                            result.get(2).getNom());
        Assert.assertEquals("prenom",
                            result.get(2).getPrenom());

        Assert.assertEquals("nom1",
                            result.get(3).getNom());

    }
    /**
     * Méthode en charge de créer un personne pour le test.
     * @param type Le type.
     * @param nom Le nom .
     * @param prenom Le prénom
     * @return La personne.
     */
    private Personne createPersonne(final TypePersonne type,
                                    final String nom,
                                    final String prenom)
    {
        final Personne personne = new ArcInvestigateur();
        personne.setType(type);
        personne.setNom(nom);
        personne.setPrenom(prenom);
        return personne;
    }
}
