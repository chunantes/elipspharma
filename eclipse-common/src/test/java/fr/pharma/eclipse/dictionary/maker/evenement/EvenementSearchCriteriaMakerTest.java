/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.pharma.eclipse.dictionary.maker.evenement;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.criteria.evenement.EvenementSearchCriteria;
import fr.pharma.eclipse.domain.dto.EssaiDTO;
import fr.pharma.eclipse.domain.model.essai.Essai;

/**
 *
 * @author sgl
 */
public class EvenementSearchCriteriaMakerTest {
   /**
     * EssaiSearchCriteriaMaker à tester.
     */
    private EvenementSearchCriteriaMaker maker;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init() {
        this.maker = new EvenementSearchCriteriaMaker();
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.maker = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.maker);
    }

    /**
     * Méthode en charge de tester le traitement des critères posés sur
     * les essais.
     */
    @Test
    public void handleCriteriaPharmaEssaiAllNull() {
        final EvenementSearchCriteria crit = new EvenementSearchCriteria();
        final Criteria criteria = Mockito.mock(Criteria.class);
        this.maker.transform(criteria, crit);
    }

    /**
     * Méthode en charge de tester le traitement des critères posés sur
     * Pharmacie/Site.
     */
    @Test
    public void handleCriteriaEssaiNotNullAndEssaiVide() {
        final EvenementSearchCriteria crit = new EvenementSearchCriteria();
        final Essai essai = new Essai();
        essai.setId(Long.MIN_VALUE);
        crit.setEssai(essai);
        crit.setEssaiVide(Boolean.TRUE);
        final Criteria criteria = Mockito.mock(Criteria.class);
        final Criteria critDetail = Mockito.mock(Criteria.class);
        Mockito.when(criteria.createCriteria("essai", CriteriaSpecification.LEFT_JOIN)).thenReturn(critDetail);
        Mockito.when(critDetail.add(Restrictions.idEq(essai.getId()))).thenReturn(criteria);
        
    }

    /**
     * Méthode en charge de tester le traitement des critères posés sur
     * Pharmacie/Site.
     */
    @Test
    public void handleCriteriaEssaiDtoNotNullAndEssaiVide() {
        final EvenementSearchCriteria crit = new EvenementSearchCriteria();
        final EssaiDTO essai = new EssaiDTO();
        essai.setId(Long.MIN_VALUE);
        crit.setEssaiDTO(essai);
        crit.setEssaiVide(Boolean.TRUE);
        final Criteria criteria = Mockito.mock(Criteria.class);
        final Criteria critDetail = Mockito.mock(Criteria.class);
        Mockito.when(criteria.createCriteria("essai", CriteriaSpecification.LEFT_JOIN)).thenReturn(critDetail);
        Mockito.when(critDetail.add(Restrictions.idEq(essai.getId()))).thenReturn(criteria);
    }  
    
    /**
     * Méthode en charge de tester le traitement des critères posés sur
     * Pharmacie/Site.
     */
    @Test
    public void handleCriteriaEssaiNotNullAndNotEssaiVide() {
        final EvenementSearchCriteria crit = new EvenementSearchCriteria();
        final Essai essai = new Essai();
        essai.setId(Long.MIN_VALUE);
        crit.setEssai(essai);
        crit.setEssaiVide(Boolean.FALSE);
        final Criteria criteria = Mockito.mock(Criteria.class);
        final Criteria critDetail = Mockito.mock(Criteria.class);
        Mockito.when(criteria.createCriteria("essai", CriteriaSpecification.INNER_JOIN)).thenReturn(critDetail);
        Mockito.when(critDetail.add(Restrictions.idEq(essai.getId()))).thenReturn(criteria);
        
    }

    /**
     * Méthode en charge de tester le traitement des critères posés sur
     * Pharmacie/Site.
     */
    @Test
    public void handleCriteriaEssaiDtoNotNullAndNotEssaiVide() {
        final EvenementSearchCriteria crit = new EvenementSearchCriteria();
        final EssaiDTO essai = new EssaiDTO();
        essai.setId(Long.MIN_VALUE);
        crit.setEssaiDTO(essai);
        crit.setEssaiVide(Boolean.FALSE);
        final Criteria criteria = Mockito.mock(Criteria.class);
        final Criteria critDetail = Mockito.mock(Criteria.class);
        Mockito.when(criteria.createCriteria("essai", CriteriaSpecification.INNER_JOIN)).thenReturn(critDetail);
        Mockito.when(critDetail.add(Restrictions.idEq(essai.getId()))).thenReturn(criteria);
    }      
}
