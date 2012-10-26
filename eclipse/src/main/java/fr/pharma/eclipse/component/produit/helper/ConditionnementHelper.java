package fr.pharma.eclipse.component.produit.helper;

import fr.pharma.eclipse.domain.enums.produit.FormeConditionnement;
import fr.pharma.eclipse.domain.enums.produit.TypeUniteGestion;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.utils.constants.EclipseConstants;
import fr.pharma.eclipse.utils.introspection.BeanTool;

/**
 * Helper du manager de conditionnement.
 
 * @version $Revision$ $Date$
 */
public class ConditionnementHelper
{

    /**
     * Champs à vérifier pour afficher le résumé.
     */
    String[] propriete =
    {"uniteGestion", "forme", "voieAdministration", };

    /**
     * Méthode en charge de construire le résumé si tous les champs nécessaires sont settés.
     * @param conditionnement Le conditionnement.
     * @return Le résumé.
     */
    public String buildResume(final Conditionnement conditionnement)
    {
        final StringBuffer result = new StringBuffer();
        boolean sette = true;

        for (final String s : this.propriete)
        {
            if (BeanTool.getPropriete(conditionnement,
                                      s) == null)
            {
                sette = false;
            }
        }
        boolean flacon = false;
        if (sette)
        {
            flacon =
                conditionnement.getUniteGestion().getType().equals(TypeUniteGestion.VOLUME)
                        && conditionnement
                                .getForme()
                                .equals(FormeConditionnement.CONDITIONNEMENT_PRIMAIRE);

            // si on est dans le cas d'un flacon on vérifie la contenance.
            if (flacon)
            {
                if (conditionnement.getContenance() == null
                    || conditionnement.getUniteContenance() == null)
                {
                    sette = false;
                }
            }
        }
        // si toutes les valeurs nécessaires sont présentes.
        if (sette)
        {
            if (!flacon)
            {
                result.append(conditionnement.getUniteGestion().getLibelle())
                        .append(EclipseConstants.SPACE);
                result.append(conditionnement.getNbUnitePrescription()).append(" Unités");
                if (conditionnement.getDosage() != null
                    && conditionnement.getUniteDosage() != null)
                {
                    result.append(" de "
                                  + conditionnement.getDosage())
                            .append(EclipseConstants.SPACE)
                            .append(conditionnement.getUniteDosage());
                }
                result.append(EclipseConstants.SPACE);
                result.append("sous forme ");
                if (conditionnement
                        .getForme()
                        .equals(FormeConditionnement.CONDITIONNEMENT_PRIMAIRE))
                {
                    result.append("de ");
                }
                result.append(conditionnement.getForme());
            }
            else
            {
                result.append(conditionnement.getUniteGestion().getLibelle())
                        .append(" contenant ")
                        .append(conditionnement.getContenance())
                        .append(EclipseConstants.SPACE)
                        .append(conditionnement.getUniteContenance());
            }
        }
        return result.toString();
    }
}
