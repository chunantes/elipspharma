package fr.pharma.eclipse.utils;

import fr.pharma.eclipse.domain.enums.document.TypeDocumentEssai;
import fr.pharma.eclipse.domain.enums.document.TypeDocumentProduit;
import fr.pharma.eclipse.domain.enums.document.TypeDocumentStock;
import fr.pharma.eclipse.domain.model.essai.detail.administratif.document.DocumentAutoriteCompetente;
import fr.pharma.eclipse.domain.model.produit.document.DocumentRandomisation;
import fr.pharma.eclipse.domain.model.stock.document.DocumentAppro;

/**
 * Classe utilitaire pour la génération de documents de tests.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public final class DocumentsUtils {
    /**
     * Constructeur privé.
     */
    private DocumentsUtils() {
        super();
    }

    /**
     * Méthode en charge de créer un DocumentAutoriteCompetente pour les tests.
     * @param id Identifiant technique.
     * @return Le document d'autorité compétente.
     */
    public static DocumentAutoriteCompetente makeDocumentACTest(final long id) {
        final DocumentAutoriteCompetente doc = new DocumentAutoriteCompetente();
        doc.setType(TypeDocumentEssai.AUTORITE_COMPETENTE);
        doc.setId(id);
        return doc;
    }

    /**
     * Méthode en charge de créer un DocumentRandomisation pour les tests.
     * @param id Identifiant technique.
     * @return Le document randomisation.
     */
    public static DocumentRandomisation makeDocumentRandomisationTest(final long id) {
        final DocumentRandomisation doc = new DocumentRandomisation();
        doc.setType(TypeDocumentProduit.RANDOMISATION);
        doc.setId(id);
        return doc;
    }

    /**
     * Méthode en charge de créer un DocumentAppro pour les tests.
     * @param id Identifiant technique.
     * @return Le document appro.
     */
    public static DocumentAppro makeDocumentApproTest(final long id) {
        final DocumentAppro doc = new DocumentAppro();
        doc.setType(TypeDocumentStock.APPRO);
        doc.setId(id);
        return doc;
    }
}
