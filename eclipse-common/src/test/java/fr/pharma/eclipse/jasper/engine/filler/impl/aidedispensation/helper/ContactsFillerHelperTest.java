package fr.pharma.eclipse.jasper.engine.filler.impl.aidedispensation.helper;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.enums.Droit;
import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanContact;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.habilitation.Habilitation;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.PersonneUtils;

/**
 * Test de la classe {@link ContactsFillerHelper}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ContactsFillerHelperTest extends AbstractEclipseJUnitTest {
    /**
     * Classe testée.
     */
    private ContactsFillerHelper helper;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.helper = new ContactsFillerHelper();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.helper = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit() {
        Assert.assertNotNull(this.helper);
    }

    /**
     * Test de la méthode transform(java.util.Set).
     */
    @Test
    public void testTransform() {
        final Droit expectedDroit = Droit.INVESTIGATEUR_CO;
        final String prenom = "jean";
        final String nom = "dumont";
        final String telephone = "0878965412";
        final String mail = "jean.dumont@eclipse.fr";
        long id = 1;
        final Personne personne = PersonneUtils.makeInvestigateur(id++, "M.", prenom, nom);
        personne.setTelephone(telephone);
        personne.setMail(mail);
        final Habilitation habilitation = new Habilitation();
        habilitation.setId(1L);
        habilitation.setDroit(expectedDroit);
        habilitation.setPersonne(personne);
        final Set<Habilitation> habilitations = new HashSet<Habilitation>();
        habilitations.add(habilitation);

        final Collection<JRBeanContact> res = this.helper.transform(habilitations);
        Assert.assertNotNull(res);
        Assert.assertEquals(1, res.size());
        final JRBeanContact bean = res.iterator().next();
        Assert.assertEquals(expectedDroit.getLibelle(), bean.getHabilitation());
        Assert.assertEquals(mail, bean.getMail());
        Assert.assertEquals(telephone, bean.getTel());
        Assert.assertEquals(nom, bean.getNom());
        Assert.assertEquals(prenom, bean.getPrenom());
        Assert.assertEquals(personne.getType().getLibelle(), bean.getProfil());
    }
}
