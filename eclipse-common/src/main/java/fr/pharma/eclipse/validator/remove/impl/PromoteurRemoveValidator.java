package fr.pharma.eclipse.validator.remove.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import fr.pharma.eclipse.domain.criteria.essai.EssaiSearchCriteria;
import fr.pharma.eclipse.domain.model.acteur.Promoteur;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.validator.remove.RemoveValidator;

/**
 * Classe de validation de suppression d'un objet Promoteur.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PromoteurRemoveValidator implements RemoveValidator<Promoteur>, Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -4256994894969851002L;

    /**
     * Service de gestion des essais.
     */
    @Resource(name = "essaiService")
    private EssaiService essaiService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate(final Promoteur promoteur) {
        // Vérification Relation Promoteur-Essai
        final EssaiSearchCriteria essaiCriteria = new EssaiSearchCriteria();
        essaiCriteria.setPromoteur(promoteur);
        if (this.essaiService.hasResult(essaiCriteria)) {
            throw new ValidationException("remove", new String[]{"impossible" }, promoteur);
        }

        // Vérification Relation Promoteur-ArcPromoteur
        if (!promoteur.getArcPromoteurs().isEmpty()) {
            throw new ValidationException("remove", new String[]{"impossible" }, promoteur);
        }

        // Vérification Relation Promoteur-ContactPromoteur
        if (!promoteur.getContactPromoteurs().isEmpty()) {
            throw new ValidationException("remove", new String[]{"impossible" }, promoteur);
        }
    }

    /**
     * Setter pour essaiService.
     * @param essaiService Le essaiService à écrire.
     */
    public void setEssaiService(final EssaiService essaiService) {
        this.essaiService = essaiService;
    }

}
