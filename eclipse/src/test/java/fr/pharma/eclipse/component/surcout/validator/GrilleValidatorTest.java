package fr.pharma.eclipse.component.surcout.validator;


import javax.faces.application.FacesMessage;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;


import fr.pharma.eclipse.domain.enums.surcout.PerimetreCout;
import fr.pharma.eclipse.domain.enums.surcout.TypeCout;
import fr.pharma.eclipse.domain.model.surcout.Categorie;
import fr.pharma.eclipse.domain.model.surcout.regle.Regle;
import fr.pharma.eclipse.utils.FacesUtils;

/**
 * Test du validateur GrilleValidator.
 * @author sebastien.helbert
 * @version $Revision$ $Date$
 */
public class GrilleValidatorTest {

    /**
     * Teste la méthode validate !
     */
    @Test
    public void testValidate() {
        GrilleValidator validator = new GrilleValidator();
        FacesUtils facesUtils = Mockito.mock(FacesUtils.class);
        validator.setFacesUtils(facesUtils);

        final Categorie categorie = new Categorie();
        
        //Test de la premiere boucle de verification
        
        	//Ajout de deux règles fixes
        
        Regle regle1 = new Regle();
        regle1.setType(TypeCout.FIXE);
        regle1.setPerimetre(PerimetreCout.ESSAI);
        categorie.getRegles().add(regle1);
        Regle regle2 = new Regle();
        regle2.setType(TypeCout.FIXE);
        regle2.setPerimetre(PerimetreCout.PATIENT);
        categorie.getRegles().add(regle2);
        Assert.assertFalse("Test deux règles coût fixe.", validator.validate(categorie));
        Mockito.verify(facesUtils,Mockito.atLeastOnce()).addMessage(FacesMessage.SEVERITY_ERROR, "regle.fixe.error");
        categorie.getRegles().clear();
        
        	//Ajout de deux règle fixe et variable
        
        Regle regle3 = new Regle();
        regle3.setType(TypeCout.FIXE);
        categorie.getRegles().add(regle3);
        Regle regle4 = new Regle();
        regle4.setType(TypeCout.VARIABLE);
        categorie.getRegles().add(regle4);
        Assert.assertFalse("Test deux règles fixe et variable.", validator.validate(categorie));
        Mockito.verify(facesUtils,Mockito.atLeastOnce()).addMessage(FacesMessage.SEVERITY_ERROR, "regle.fixe.error");
        categorie.getRegles().clear();
        
    		//Ajout de deux règle variable et fixe
    
        Regle regle5 = new Regle();
        regle5.setType(TypeCout.VARIABLE);
        categorie.getRegles().add(regle5);
        Regle regle6 = new Regle();
        regle6.setType(TypeCout.FIXE);
        categorie.getRegles().add(regle6);
        Assert.assertFalse("Test deux règles variable et fixe.", validator.validate(categorie));
        Mockito.verify(facesUtils,Mockito.atLeastOnce()).addMessage(FacesMessage.SEVERITY_ERROR, "regle.fixe.error");
        categorie.getRegles().clear();
        
        
        
        //Test de la deuxième boucle de verification

        	// Ajout de la première règle

        Regle regle7 = new Regle();
        regle7.setType(TypeCout.VARIABLE);
        categorie.getRegles().add(regle7);

        regle7.setMin(10);
        regle7.setMax(100);
        Assert.assertFalse("Test première règle ne commençant pas à 0.", validator.validate(categorie));
        Mockito.verify(facesUtils).addMessage(FacesMessage.SEVERITY_ERROR, "regle.premier.error");

        regle7.setMin(0);

        Mockito.reset(facesUtils);
        Assert.assertTrue("Test première règle commence à 0.", validator.validate(categorie));

        regle1.setMin(1);

        Mockito.reset(facesUtils);
        Assert.assertTrue("Test première règle commence à 1.", validator.validate(categorie));

        	// Ajout d'une seconde règle

        Regle regle8 = new Regle();
        regle8.setType(TypeCout.VARIABLE);
        categorie.getRegles().add(regle8);

        regle8.setMin(50);
        regle8.setMax(200);

        Mockito.reset(facesUtils);
        Assert.assertFalse("Test règles se chevauchant.", validator.validate(categorie));
        Mockito.verify(facesUtils).addMessage(FacesMessage.SEVERITY_ERROR, "regle.sequence.error");

        regle8.setMin(150);
        regle8.setMax(200);

        Mockito.reset(facesUtils);
        Assert.assertFalse("Test discontinuité entre les règles.", validator.validate(categorie));
        Mockito.verify(facesUtils).addMessage(FacesMessage.SEVERITY_ERROR, "regle.sequence.error");

        regle8.setMin(101);
        regle8.setMax(200);

        Mockito.reset(facesUtils);
        Assert.assertTrue("Test continuité entre les règles.", validator.validate(categorie));
    }
}
