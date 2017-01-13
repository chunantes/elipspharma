package fr.pharma.eclipse.json;

import java.util.Calendar;

import javax.annotation.Resource;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

import fr.pharma.eclipse.domain.model.design.Bras;
import fr.pharma.eclipse.domain.model.essai.detail.design.DetailDesign;
import fr.pharma.eclipse.exception.common.CommonException;

/**
 * Classe en charge de convertir un detaikl design en JSON afin d'afficher
 * l'arbre chronologique des designs.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DesignConverter {

    /**
     * Converter de bras en JSON.
     */
    @Resource(name = "brasToJSONConverter")
    private BrasToJSONConverter brasConverter;

    /**
     * Méthode en charge de convertir un detail designs en JSONArray.
     * @param detailDesign Detail design.
     * @param date La date.
     * @return Le tableau de JSONObjet.
     */
    public JSONArray convert(final DetailDesign detailDesign,
                             final Calendar date) {
        final JSONArray array = new JSONArray();

        for (final Bras bras : detailDesign.getBras()) {
            if (this.brasConverter.support(bras) && !this.isAlreadyPresent(array, bras)) {
                this.addToArray(array, this.brasConverter.convert(bras, date));
            }
        }
        return array;
    }

    /**
     * Retourne <true> si le bras est déja présent.
     * @param array Le tableau.
     * @param bras Le bras.
     * @return <true> si le bras est déja présent.
     */
    private boolean isAlreadyPresent(final JSONArray array,
                                     final Bras bras) {
        try {
            for (int i = 0; i < array.length(); i++) {
            	if(bras.getId()!=null){
	                if (((JSONObject) array.get(i)).getLong("id") == bras.getId().longValue()) {
	                    return true;
	                }
            	}
            }
        } catch (final JSONException e) {
            throw new CommonException("Erreur lors de la generation des blocs.", e);
        }
        return false;
    }

    /**
     * Méthode en charge d'ajouter tous les Objets contenu dans le arrayToAdd
     * dans le array.
     * @param array Tableau destination.
     * @param arrayToAdd Tableau source.
     */
    private void addToArray(final JSONArray array,
                            final JSONArray arrayToAdd) {
        try {
            for (int i = 0; i < arrayToAdd.length(); i++) {
                array.put(arrayToAdd.get(i));
            }
        } catch (final JSONException e) {
            throw new CommonException("Impossible de convertir le bras.", e);
        }
    }

    /**
     * Setter pour brasConverter.
     * @param brasConverter le brasConverter à écrire.
     */
    public void setBrasConverter(final BrasToJSONConverter brasConverter) {
        this.brasConverter = brasConverter;
    }
}
