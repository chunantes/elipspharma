package fr.pharma.eclipse.filler.helper;

/**
 * Helper permettant de gérer les booleen présents dans le fichier sigrec sous forme de chaine de
 * caractères.
 
 * @version $Revision$ $Date$
 */
public class BooleanFillerHelper
{
    /**
     * Retourne <code>true</code> si la valeur représente un booleen à True.
     * @param value La valeur.
     * @return <code>true</code> si la valeur représente un booleen à True. Retourne
     * <code>false</code> si la valeur est null ou représente un booleen à false.
     */
    public boolean checkTrue(final String value)
    {
        return null != value
               && value.equals("Y");
    }
}
