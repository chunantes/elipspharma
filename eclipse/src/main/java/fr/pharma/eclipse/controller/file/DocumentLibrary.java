package fr.pharma.eclipse.controller.file;

import java.io.Serializable;
import java.util.Map;

import fr.pharma.eclipse.domain.enums.document.TypeDocumentStatique;

/**
 * Classe contenant les chemins vers les documents statiques de l'application.
 
 * @version $Revision$ $Date$
 */
public class DocumentLibrary
    implements Serializable
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 306319896932424290L;

    /**
     * Map contenant tous les chemins vers les documents en fonction de leur type.
     */
    private Map<TypeDocumentStatique, String> library;

    public String getPathToDocument(final TypeDocumentStatique type)
    {
        return this.library.get(type);
    }

    /**
     * Setter pour library.
     * @param library Le library à écrire.
     */
    public void setLibrary(final Map<TypeDocumentStatique, String> library)
    {
        this.library = library;
    }

}
