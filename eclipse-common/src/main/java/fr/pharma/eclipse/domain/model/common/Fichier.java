package fr.pharma.eclipse.domain.model.common;

import java.io.IOException;
import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

/**
 * Contenu d'un fichier.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class Fichier implements Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 4649065165074292217L;

    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(Fichier.class);

    /**
     * Pour conversion vers fichier lors de reception depuis html.
     */
    private transient MultipartFile file;

    /**
     * Contenu binaire du fichier.
     */
    private byte[] contenu = new byte[0];

    /**
     * Nom du fichier.
     */
    private String nom = StringUtils.EMPTY;

    /**
     * Type de fichier.
     */
    private String typeFichier = StringUtils.EMPTY;

    /**
     * Getter pour contenu.
     * @return Retourne le contenu.
     */
    public byte[] getContenu() {
        return this.contenu;
    }

    /**
     * Setter pour contenu.
     * @param contenu le contenu à écrire.
     */
    public void setContenu(final byte[] contenu) {
        this.contenu = contenu;
    }

    /**
     * Getter pour nom.
     * @return Retourne le nom.
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Setter pour nom.
     * @param nom le nom à écrire.
     */
    public void setNom(final String nom) {
        this.nom = nom;
    }

    /**
     * Getter pour file.
     * @return Retourne le file.
     */
    public MultipartFile getFile() {
        return this.file;
    }

    /**
     * Setter pour file.
     * @param file le file à écrire.
     */
    public void setFile(final MultipartFile file) {
        try {
            this.setFiledata(file.getBytes(), file.getContentType(), file.getOriginalFilename());
        } catch (final IOException e) {
            this.log.error("Exception dans l'affectation du fichier", e.getCause());
        }
    }

    /**
     * Méthode de constitution du contenu d'un Fichier.
     * @param data Le contenu du fichier.
     * @param contentType Le type du fichier.
     * @param filename le nom du fichier.
     */
    public void setFiledata(final byte[] data,
                            final String contentType,
                            final String filename) {
        this.setContenu(data);
        this.setTypeFichier(contentType);
        this.setNom(filename);
    }

    /**
     * Getter pour typeFichier.
     * @return Retourne le typeFichier.
     */
    public String getTypeFichier() {
        return this.typeFichier;
    }

    /**
     * Setter pour typeFichier.
     * @param typeFichier le typeFichier à écrire.
     */
    public void setTypeFichier(final String typeFichier) {
        this.typeFichier = typeFichier;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("[");
        builder.append("Nom: ").append(this.nom);
        builder.append(", du contenu? ").append((this.contenu != null) && (this.contenu.length > 0));
        return builder.append("]").toString();
    }
}
