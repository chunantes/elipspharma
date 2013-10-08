package fr.pharma.eclipse.jasper.engine.builder.helper.common;

import java.io.Serializable;

import org.springframework.util.StringUtils;

import fr.pharma.eclipse.domain.jasper.model.common.JRBeanHeader;

/**
 * Classe en charge de construire la source de l'en-tête commune des rapports
 * Jasper.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class JRBeanHeaderBuilder implements Serializable {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 7430698644722359765L;

    /**
     * Chemin d'accès au logo du CHU.
     */
    private final String cheminLogoCHU;

    /**
     * Constructeur.
     * @param cheminLogoCHU Chemin d'accès au logo du CHU.
     */
    public JRBeanHeaderBuilder(final String cheminLogoCHU) {
        this.cheminLogoCHU = cheminLogoCHU;
    }

    /**
     * Méthode en charge de construire l'en-tête commune à partir des
     * informations données.
     * @param sousTitre Sous-titre de l'imprimé.
     * @param processus Nom du processus de l'imprimé.
     * @param themes Thèmes de l'imprimé.
     * @param diffuseur Responsable de la diffusion de l'imprimé.
     * @return Le bean JRBeanHeader correspondant à l'en-tête.
     */
    public JRBeanHeader build(final String sousTitre,
                              final String processus,
                              final String themes,
                              final String diffuseur) {
        final JRBeanHeader dataHeader = new JRBeanHeader();
        dataHeader.setSousTitre(sousTitre);
        dataHeader.setProcessus(processus);
        dataHeader.setThemes(themes);
        dataHeader.setDiffusionPar(diffuseur);
        if (StringUtils.hasText(this.cheminLogoCHU)) {
            dataHeader.setUrlImage(this.cheminLogoCHU);
        }
        return dataHeader;
    }

    /**
     * Getter sur cheminLogoCHU.
     * @return Retourne le cheminLogoCHU.
     */
    String getCheminLogoCHU() {
        return this.cheminLogoCHU;
    }

}
