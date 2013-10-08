package fr.pharma.eclipse.service.acteur.helper;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import fr.pharma.eclipse.domain.model.acteur.Personne;

/**
 * Classe de helper pour l'encodage de mot de passe.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PasswordEncoderHelper implements Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 580806275465052191L;

    /**
     * Gestionnaire d'encodage de mot de passe.
     */
    private Md5PasswordEncoder passwordEncoder;

    /**
     * Taille de détection d'un mot de passe encodé.
     */
    private static final int SIZE_DETECT_PASSWORD_ENCOD = 15;

    /**
     * Méthode en charge d'encoder le mot de passe d'une Personne.
     * @param personne Personne.
     */
    public void encodePassword(final Personne personne) {
        if ((StringUtils.isNotEmpty(personne.getPassword())) && (personne.getPassword().length() < PasswordEncoderHelper.SIZE_DETECT_PASSWORD_ENCOD)) {
            personne.setPassword(this.passwordEncoder.encodePassword(personne.getPassword(), null));
        }
    }

    /**
     * Setter pour passwordEncoder.
     * @param passwordEncoder le passwordEncoder à écrire.
     */
    public void setPasswordEncoder(final Md5PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

}
