package fr.pharma.eclipse.component.surcout.validator;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;

import org.springframework.stereotype.Component;

import fr.pharma.eclipse.comparator.surcout.RegleBorneMinComparator;
import fr.pharma.eclipse.domain.enums.surcout.TypeCout;
import fr.pharma.eclipse.domain.model.surcout.Categorie;
import fr.pharma.eclipse.domain.model.surcout.regle.Regle;
import fr.pharma.eclipse.utils.FacesUtils;

/**
 * Validateur des regles de calcul de cout.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Component
public class GrilleValidator implements Serializable {
    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -6388997703265682541L;

    /**
     * Utilitaire Faces.
     */
    @Resource(name = "facesUtils")
    private FacesUtils facesUtils;

    public void setFacesUtils(FacesUtils facesUtils) {
        this.facesUtils = facesUtils;
    }

    /**
     * Méthode en charge de valider une règle.
     * @param regle La règle à valider.
     * @return <true> si la règle est valide.
     */
    public boolean validate(final Categorie categorie) {
        boolean valid = true;
        Regle precedent = null;     

        //1- verification que des regles de types différents ne cohabitent pas et que plusieurs regles de type cout fixe ne cohabitent pas
        for (final Regle regle : categorie.getRegles()) {

        	//si le cout est fixe
        	if (regle.getType().equals(TypeCout.FIXE)){
        		//si precedent != null
        		if (precedent != null){
        			//erreur : pas de possibilité de mettre deux couts fixes ou un cout fixe et un cout variable pour un même acte
        			this.facesUtils.addMessage(FacesMessage.SEVERITY_ERROR, "regle.fixe.error");
        			valid = false;
        		}    			
        		//si precedent = null
        		if (precedent == null ){
        			precedent = regle;
        		}    			
        	}
        	//si le cout est variable
        	if (regle.getType().equals(TypeCout.VARIABLE)){
        		//si precedent != null et que ce n'est pas un update de la regle
        		if(precedent!=null && ((regle.getId()!=null && precedent.getId()!=null && !precedent.getId().equals(regle.getId())) || regle.getId()==null || precedent.getId()==null)){
        			if (precedent.getType().equals(TypeCout.FIXE)){
        				//erreur : pas de possibilité de mettre deux couts fixes ou un cout fixe et un cout variable pour un même acte
        				this.facesUtils.addMessage(FacesMessage.SEVERITY_ERROR, "regle.fixe.error");
        				valid = false;
        			}
        		}
        		precedent = regle;
        	}
        }
        
        //2-Si ce sont des règles de type cout variable : Verification que la séquence des regles de type cout variable est valide
        if (valid==true){        		
        	//Si les regles de la categorie sont de type cout variable
        	if(categorie.getRegles().first().getType()==TypeCout.VARIABLE){

        		precedent = null;

        		//On liste toutes les regles de la categorie en les triant du min le plus petit au min le plus grand
        		Set<Regle> regles = new TreeSet<Regle>(new RegleBorneMinComparator());
        		regles.addAll(categorie.getRegles());

        		for (final Regle regleV : regles){

        			// si c'est la première règle alors elle doit commencer à 0, 1 ou
        			// être à null.
        			if (precedent == null) {
        				if ((regleV.getMin() != null) && (regleV.getMin() > 1)) {
        					this.facesUtils.addMessage(FacesMessage.SEVERITY_ERROR, "regle.premier.error");
        					valid = false;
        				}
        			}
        			//si precedent != null et que ce n'est pas un update de la regle
        			else if((regleV.getId()!=null && precedent.getId()!=null && !precedent.getId().equals(regleV.getId())) || regleV.getId()==null || precedent.getId()==null){
        				// si ce n'est pas la première elle doit suivre directement la
            			// précédent.
        				if (!regleV.getMin().equals(precedent.getMax() + 1)) {
        					this.facesUtils.addMessage(FacesMessage.SEVERITY_ERROR, "regle.sequence.error");
        					valid = false;
        				}
        			}
        			
        			precedent=regleV;

        		}
        	}
        }


        this.facesUtils.putCallbackValidityParam(valid);
        return valid;
        }
}
