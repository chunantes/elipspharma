package fr.pharma.eclipse.component.design.validator;

import javax.faces.context.FacesContext;

import org.mockito.Mockito;

/**
 * Création d'un FacesContext mocké pour les TUs.
 * Il faut une classe parce que "setCurrentInstance" est "protected".
 */
public abstract class FacesContextMock extends FacesContext {

	private FacesContextMock() { 
		
	}
	
	public static FacesContext mockFacesContext() {
		FacesContext context = Mockito.mock(FacesContext.class);
		setCurrentInstance(context);
		return context;
	}
}
