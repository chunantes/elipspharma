package fr.pharma.eclipse.service.acl.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import fr.pharma.eclipse.dao.search.AclSearchDao;
import fr.pharma.eclipse.domain.enums.TypePersonne;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.acteur.Pharmacien;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;

/**
 * Classe en charge de tester le service de gestion des acls.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class AclServiceImplTest {

    /**
     * DAO de recherche des ACLs.
     */
    @Mock
    private AclSearchDao aclSearchDao;

    /**
     * Service à tester.
     */
    @InjectMocks
    private AclServiceImpl aclService;

    /**
     * Initialisation des données de test.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test method for
     * {@link fr.pharma.eclipse.service.acl.impl.AclServiceImpl#updateAclsPharmacies()}
     * .
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testUpdateAclsPharmaciesPharmacien() {

        final Pharmacien pharmacien = new Pharmacien();
        final Long idPersonne = 1L;
        pharmacien.setId(idPersonne);
        pharmacien.setType(TypePersonne.PHARMACIEN);
        final Pharmacie pharmacie = new Pharmacie();
        pharmacie.setNom("nom");
        pharmacie.setId(1L);
        pharmacien.getPharmacies().add(pharmacie);

        this.aclService.updateAclsPharmacies(pharmacien);

        Mockito.verify(this.aclSearchDao).removeAclsPharmaciesOfPersonne(idPersonne);
        Mockito.verify(this.aclSearchDao).saveAclsPharmacies(Matchers.anyList());
    }

    /**
     * Test method for
     * {@link fr.pharma.eclipse.service.acl.impl.AclServiceImpl#updateAclsPharmacies()}
     * .
     */
    @Test
    public void testUpdateAclsPharmaciesAdmin() {

        final Pharmacien pharmacien = new Pharmacien();
        final Long idPersonne = 1L;
        pharmacien.setId(idPersonne);
        pharmacien.setIsAdmin(true);
        pharmacien.setType(TypePersonne.PHARMACIEN);
        final Pharmacie pharmacie = new Pharmacie();
        pharmacie.setNom("nom");
        pharmacie.setId(1L);
        pharmacien.getPharmacies().add(pharmacie);

        this.aclService.updateAclsPharmacies(pharmacien);

        Mockito.verify(this.aclSearchDao).removeAclsPharmaciesOfPersonne(idPersonne);
        Mockito.verify(this.aclSearchDao).saveAclsPharmaciesForAdmin(idPersonne);
    }

    /**
     * Test method for
     * {@link fr.pharma.eclipse.service.acl.impl.AclServiceImpl#removeAclsPharmacies()}
     * .
     */
    @Test
    public void testRemoveAclsPharmacies() {
        final Pharmacien pharmacien = new Pharmacien();
        final Long idPersonne = 1L;
        pharmacien.setId(idPersonne);
        pharmacien.setType(TypePersonne.PHARMACIEN);
        this.aclService.removeAclsPharmacies(pharmacien);
        Mockito.verify(this.aclSearchDao).removeAclsPharmaciesOfPersonne(idPersonne);
    }

    /**
     * Test method for
     * {@link fr.pharma.eclipse.service.acl.impl.AclServiceImpl#updateAclsEssais()}
     * .
     */
    @Test
    public void testUpdateAclsEssais() {
        final Essai essai = new Essai();
        final Long idEssai = 1L;
        essai.setId(idEssai);
        this.aclService.updateAclsEssais(essai);
        Mockito.verify(this.aclSearchDao).saveAclsEssaisForAdmins(idEssai);
        Mockito.verify(this.aclSearchDao).saveAclsEssaisForPharmaciens(idEssai);
        Mockito.verify(this.aclSearchDao).saveAclsEssaisForOthersProfils(idEssai);
    }

    /**
     * Test method for
     * {@link fr.pharma.eclipse.service.acl.impl.AclServiceImpl#updateAclsEssais(Personne)}
     * .
     */
    @Test
    public void testUpdateAclsEssaisPersonneAdmin() {
        final Pharmacien pharmacien = new Pharmacien();
        final Long idPersonne = 1L;
        pharmacien.setId(idPersonne);
        pharmacien.setIsAdmin(true);
        pharmacien.setType(TypePersonne.PHARMACIEN);
        final Pharmacie pharmacie = new Pharmacie();
        pharmacie.setNom("nom");
        pharmacie.setId(1L);
        pharmacien.getPharmacies().add(pharmacie);

        this.aclService.updateAclsEssais(pharmacien);

        Mockito.verify(this.aclSearchDao).removeAclsEssaisOfPersonne(idPersonne);
        Mockito.verify(this.aclSearchDao).saveAclsEssaisForAdmin(idPersonne);
    }

    /**
     * Test method for
     * {@link fr.pharma.eclipse.service.acl.impl.AclServiceImpl#updateAclsEssais(Personne)}
     * .
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testUpdateAclsEssaisPersonnePharmacien() {
        final Pharmacien pharmacien = new Pharmacien();
        final Long idPersonne = 1L;
        pharmacien.setId(idPersonne);
        pharmacien.setIsAdmin(false);
        pharmacien.setType(TypePersonne.PHARMACIEN);
        final Pharmacie pharmacie = new Pharmacie();
        pharmacie.setNom("nom");
        pharmacie.setId(1L);
        pharmacien.getPharmacies().add(pharmacie);

        this.aclService.updateAclsEssais(pharmacien);

        Mockito.verify(this.aclSearchDao).removeAclsEssaisOfPersonne(idPersonne);
        Mockito.verify(this.aclSearchDao).saveAclsEssaisForPharmacien(Matchers.anyLong(), Matchers.anyList());
    }
}
