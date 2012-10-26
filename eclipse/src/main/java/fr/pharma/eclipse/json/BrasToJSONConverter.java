package fr.pharma.eclipse.json;

import java.util.Calendar;

import javax.annotation.Resource;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

import fr.pharma.eclipse.domain.model.design.Bras;
import fr.pharma.eclipse.domain.model.design.Sequence;
import fr.pharma.eclipse.exception.common.CommonException;

/**
 * Classe en charge de convertir un bras en JSON pour la chronologie des designs.
 
 * @version $Revision$ $Date$
 */
public class BrasToJSONConverter
{

    /**
     * Converter de Sequence => JSON.
     */
    @Resource(name = "sequenceToJSONConverter")
    private SequenceToJSONConverter sequenceConverter;

    /**
     * Méthode en charge de convertir le bras en paramètre en JSONArray.
     * @param bras Le bras.
     * @param date La date.
     * @return Le JSONArray contenant les objets à affcher.
     */
    public JSONArray convert(final Bras bras,
                             final Calendar date)
    {
        final JSONArray array = new JSONArray();

        // création de l'obejt correspondant au bras.
        final JSONObject brasJSON = new JSONObject();

        try
        {
            // propriétés simple.
            brasJSON.put("id",
                         bras.getId());
            brasJSON.put("itemName",
                         bras.getNom());
            brasJSON.put("niveau",
                         this.getNiveau(bras,
                                        0));

            // traitement des séquences
            final JSONArray sequences = new JSONArray();
            for (final Sequence sequence : bras.getSequences())
            {
                if (this.sequenceConverter.support(sequence))
                {
                    sequences.put(this.sequenceConverter.convert(sequence,
                                                                 date));
                }
            }
            brasJSON.put("series",
                         sequences);

            // ajout à la liste.
            array.put(brasJSON);
            // traitement des sous bras.
            for (final Bras ssBras : bras.getSousBras())
            {
                if (this.support(ssBras))
                {
                    this.addToArray(array,
                                    this.convert(ssBras,
                                                 date));
                }
            }

        }
        catch (final JSONException e)
        {
            throw new CommonException("Impossible de convertir le design en JSON.",
                                      e);
        }
        return array;
    }

    /**
     * Méthode récursive en charge de déterminer le niveau du bras dans la hiérarchie à partir du
     * bras et du niveau d'origine.
     * @param bras Le bras.
     * @param niveau Le niveau d'origine.
     * @return Le niveau.
     */
    private int getNiveau(final Bras bras,
                          final int niveau)
    {
        int newNiveau = niveau;
        if (bras.getParent() != null)
        {
            final int n = niveau + 1;
            newNiveau = this.getNiveau(bras.getParent(),
                                       n);
        }
        return newNiveau;
    }

    /**
     * Méthode en charge d'ajouter tous les Objets contenu dans le arrayToAdd dans le array.
     * @param array Tableau destination.
     * @param arrayToAdd Tableau source.
     */
    private void addToArray(final JSONArray array,
                            final JSONArray arrayToAdd)
    {
        try
        {
            for (int i = 0; i < arrayToAdd.length(); i++)
            {
                array.put(arrayToAdd.get(i));
            }
        }
        catch (final JSONException e)
        {
            throw new CommonException("Impossible de convertir le bras.",
                                      e);
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean support(final Bras bras)
    {
        return bras.getDebut() != null
               && bras.getFin() != null;
    }

    /**
     * Setter pour sequenceConverter.
     * @param sequenceConverter le sequenceConverter à écrire.
     */
    public void setSequenceConverter(final SequenceToJSONConverter sequenceConverter)
    {
        this.sequenceConverter = sequenceConverter;
    }
}
