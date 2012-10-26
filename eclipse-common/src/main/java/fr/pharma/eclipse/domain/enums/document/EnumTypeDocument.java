package fr.pharma.eclipse.domain.enums.document;

/**
 * Interface commune des énumérations définissant des types de documents.
 
 * @version $Revision$ $Date$
 */
public interface EnumTypeDocument
{
    /**
     * Méthode qui récupère le type de documents Eclipse.
     * @return Le type général de document Eclipse.
     */
    TypeDocumentEclipse getTypeEclipse();

    /**
     * Récupération du nom courant de la valeur de l'énumération.
     * @return Le nom de la valeur de l'énumération.
     */
    String name();
}
