package fr.pharma.eclipse.component.essai;

import java.io.Serializable;
import java.util.Calendar;

import org.apache.commons.lang.StringUtils;

import fr.pharma.eclipse.domain.enums.document.TypeDocumentEssai;
import fr.pharma.eclipse.domain.enums.document.TypeDocumentProtocole;
import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.administratif.document.DocumentProtocole;
import fr.pharma.eclipse.utils.constants.EclipseConstants;
import fr.pharma.eclipse.utils.introspection.BeanTool;

/**
 * Classe générique en charge de gérer les documents liés aux protocoles sur un
 * essai clinique.
 * @param <DOC> Type générique de document d'essai.
 * @param <PARENT> Type dérivé de {@link BeanObject} représentant le parent du
 * document.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DocumentProtocoleManager<DOC extends DocumentProtocole, PARENT extends BeanObject> extends GenericDocumentEssaiManager<DOC, PARENT> implements Serializable {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -3601548264868774784L;

    /**
     * Date?.
     */
    private Calendar date = Calendar.getInstance(EclipseConstants.LOCALE);

    /**
     * Version.
     */
    private String version = StringUtils.EMPTY;

    /**
     * Type de document protocole.
     */
    private TypeDocumentProtocole typeDocumentProtocole = null;

    /**
     * Constructeur.
     * @param typeDocumentsName Nom du type des documents managés.
     * @throws IllegalArgumentException Si typeDocumentsName ne correspond pas à
     * une valeur de l'énumération {@link TypeDocumentEssai}.
     */
    public DocumentProtocoleManager(final String typeDocumentsName) {
        super(typeDocumentsName);
    }

    @Override
    @SuppressWarnings("unchecked")
    public DOC createDocument(final Essai essai) {
        // Document BDD.
        final PARENT parent = (PARENT) BeanTool.getPropriete(essai, this.getTypeDocuments().getDocumentsParentPropertyFromEssai());
        final DOC doc = this.getFactory().getInitializedObject(parent, this.getFichier(), this.getCommentaire().trim());

        doc.setDateMaj(this.date);
        doc.setTypeDocumentProtocole(this.typeDocumentProtocole);
        doc.setVersion(this.version);

        // Document Physique.
        this.getDocService().saveOnDisk(essai, doc, this.getFichier());

        this.resetFormDatas();
        return doc;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canCreateDocument() {
        return true;
    }

    /**
     * Méthode de réinitialisation des données du formulaire (fichier +
     * commentaire).
     */
    @Override
    public void resetFormDatas() {
        super.resetFormDatas();
        this.version = StringUtils.EMPTY;
        this.typeDocumentProtocole = null;
    }

    /**
     * Getter pour version.
     * @return Le version
     */
    public String getVersion() {
        return this.version;
    }

    /**
     * Setter pour version.
     * @param version Le version à écrire.
     */
    public void setVersion(final String version) {
        this.version = version;
    }

    /**
     * Getter pour typeDocumentProtocole.
     * @return Le typeDocumentProtocole
     */
    public TypeDocumentProtocole getTypeDocumentProtocole() {
        return this.typeDocumentProtocole;
    }

    /**
     * Setter pour typeDocumentProtocole.
     * @param typeDocumentProtocole Le typeDocumentProtocole à écrire.
     */
    public void setTypeDocumentProtocole(final TypeDocumentProtocole typeDocumentProtocole) {
        this.typeDocumentProtocole = typeDocumentProtocole;
    }

    /**
     * Getter pour date.
     * @return Le date
     */
    public Calendar getDate() {
        return this.date;
    }

    /**
     * Setter pour date.
     * @param date Le date à écrire.
     */
    public void setDate(final Calendar date) {
        this.date = date;
    }

}
