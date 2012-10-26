package fr.pharma.eclipse.utils;

import fr.pharma.eclipse.domain.enums.document.TypeDocumentEssai;
import fr.pharma.eclipse.domain.model.essai.detail.administratif.document.DocumentAutoriteCompetente;

/**
 * Classe utilitaire pour la génération de documents de tests.
 
 * @version $Revision$ $Date$
 */
public final class DocumentsUtils
{
    /**
     * Constructeur privé.
     */
    private DocumentsUtils()
    {
        super();
    }

    /**
     * Méthode en charge de créer un DocumentAutoriteCompetente pour les tests.
     * @param id Identifiant technique.
     * @return Le document d'autorité compétente.
     */
    public static DocumentAutoriteCompetente makeDocumentACTest(final long id)
    {
        final DocumentAutoriteCompetente doc = new DocumentAutoriteCompetente();
        doc.setType(TypeDocumentEssai.AUTORITE_COMPETENTE);
        doc.setId(id);
        return doc;
    }
}
