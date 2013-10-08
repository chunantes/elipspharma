package fr.pharma.eclipse.component.surcout.validator;


import javax.faces.application.FacesMessage;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.enums.surcout.Acte;
import fr.pharma.eclipse.domain.model.surcout.Categorie;
import fr.pharma.eclipse.domain.model.surcout.Theme;
import fr.pharma.eclipse.utils.FacesUtils;

/**
 * Test du validateur CategorieValidator.
 * @author amelie.gautier
 * @version $Revision$ $Date$
 */
public class CategorieValidatorTest {

	/**
	 * Teste la méthode validate !
	 */
	@Test
	public void testValidate() {
		CategorieValidator validator = new CategorieValidator();
		FacesUtils facesUtils = Mockito.mock(FacesUtils.class);
		validator.setFacesUtils(facesUtils);

		final Theme theme = new Theme();

		//Catégorie sans acte
		Categorie cat1 = new Categorie();
		cat1.setLibelle("cat_1");
		Assert.assertFalse("test catégorie sans acte", validator.validate(cat1));
		Mockito.verify(facesUtils,Mockito.atLeastOnce()).putCallbackValidityParam(false);
		Mockito.verify(facesUtils,Mockito.atLeastOnce()).addMessage(FacesMessage.SEVERITY_ERROR, "grilleModele.acte.notEmpty");
		
		//Catégorie avec acte
		cat1.setActe(Acte.APPROVISIONNEMENT);
		Assert.assertTrue("test catégorie avec acte", validator.validate(cat1));
	}


}
