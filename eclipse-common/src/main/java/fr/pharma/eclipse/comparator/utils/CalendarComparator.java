package fr.pharma.eclipse.comparator.utils;

import java.util.Calendar;
import java.util.Comparator;

/**
 * Comparateur d'objets Calendar.
 
 * @version $Revision$ $Date$
 */
public class CalendarComparator
    implements Comparator<Calendar>
{

    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(final Calendar cal1,
                       final Calendar cal2)
    {
        return cal1.getTime().compareTo(cal2.getTime());
    }

}
