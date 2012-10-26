package fr.pharma.eclipse.jasper.document;

import java.io.Serializable;
import java.util.Map;

import fr.pharma.eclipse.domain.enums.jasper.TypeRapportJasper;
import fr.pharma.eclipse.exception.common.CommonException;
import fr.pharma.eclipse.jasper.document.maker.DocumentMaker;
import fr.pharma.eclipse.jasper.exception.JasperReportBuildException;

/**
 * Stratégy des maker de document.
 
 * @version $Revision$ $Date$
 */
public class DocumentMakerDictionary
    implements Serializable
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -6634566886764481583L;

    /**
     * Map de strategies.
     */
    private Map<TypeRapportJasper, DocumentMaker> map;

    /**
     * Méthode en charge d'appeler le bon maker.
     */
    public void make(final TypeRapportJasper type,
                     final Object source)
    {
        try
        {
            if (this.map.containsKey(type))
            {
                this.map.get(type).make(type,
                                        source);
            }
        }
        catch (final JasperReportBuildException e)
        {
            throw new CommonException(e);
        }
    }

    /**
     * Setter pour map.
     * @param map Le map à écrire.
     */
    public void setMap(final Map<TypeRapportJasper, DocumentMaker> map)
    {
        this.map = map;
    }
}
