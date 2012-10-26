package fr.pharma.eclipse.json;

import java.util.Calendar;

import javax.annotation.Resource;

import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

import fr.pharma.eclipse.domain.model.design.Sequence;
import fr.pharma.eclipse.exception.common.CommonException;
import fr.pharma.eclipse.service.helper.design.TimeHelper;

/**
 * Converter en charge de convertir des sequences en JSON.
 
 * @version $Revision$ $Date$
 */
public class SequenceToJSONConverter
{

    /**
     * Helper de gestion des temps prescription et des dates.
     */
    @Resource(name = "timeHelper")
    private TimeHelper timeHelper;

    /**
     * Méthode en charge de convertir une sequence en JSONObject.
     * @param sequence La séquence.
     * @param date La date.
     * @return l'objet JSON.
     */
    public JSONObject convert(final Sequence sequence,
                              final Calendar date)
    {
        final JSONObject object = new JSONObject();
        try
        {

            object.put("seriesName",
                       sequence.getNom());

            final Calendar dateDebut = this.timeHelper.convertTime(date,
                                                                   sequence.getDebut());
            final Calendar dateFin = this.timeHelper.convertTime(date,
                                                                 sequence.getFin());
            dateFin.add(Calendar.DAY_OF_MONTH,
                        1);

            object.put("debut",
                       this.convertDateToJSON(dateDebut));
            object.put("fin",
                       this.convertDateToJSON(dateFin));
            object.put("idSequence",
                       sequence.getId());
        }
        catch (final JSONException e)
        {
            throw new CommonException("Impossible de convertir la séquence.",
                                      e);
        }

        return object;

    }

    /**
     * Méthode en charge de convertir une date en Date format JSON utilisée par la page de
     * génération du graph de design.
     * @param date La date.
     * @return L'objet JSON.
     */
    private JSONObject convertDateToJSON(final Calendar date)
    {
        final JSONObject object = new JSONObject();
        try
        {
            object.put("annee",
                       date.get(Calendar.YEAR));
            object.put("mois",
                       date.get(Calendar.MONTH));
            object.put("jours",
                       date.get(Calendar.DAY_OF_MONTH));
        }
        catch (final JSONException e)
        {
            throw new CommonException("Erreur de conversion de date en JSON",
                                      e);
        }
        return object;
    }

    /**
     * Retourne <code>true</code> si la sequence supporte la conversion.
     * @param sequence La sequence.
     * @return <code>true</code> si la sequence supporte la conversion.
     */
    public boolean support(final Sequence sequence)
    {
        return sequence.getDebut().getNb() != null
               && sequence.getDebut().getUnite() != null
               && sequence.getFin().getNb() != null
               && sequence.getFin().getUnite() != null;
    }

    /**
     * Setter pour timehelper.
     * @param timehelper le timehelper à écrire.
     */
    public void setTimehelper(final TimeHelper timehelper)
    {
        this.timeHelper = timehelper;
    }
}
