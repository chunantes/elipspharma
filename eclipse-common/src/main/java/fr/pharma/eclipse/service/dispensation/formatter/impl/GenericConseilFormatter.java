package fr.pharma.eclipse.service.dispensation.formatter.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import fr.pharma.eclipse.domain.enums.design.TypeRegularite;
import fr.pharma.eclipse.domain.model.dispensation.ConseilDispensation;
import fr.pharma.eclipse.utils.message.EclipseMessageBuilder;

/**
 * Formatter générique de conseil de dispensation.
 
 * @version $Revision$ $Date$
 */
public abstract class GenericConseilFormatter
{
    /**
     * Constructeur de message.
     */
    @Resource(name = "eclipseMessageBuilder")
    protected EclipseMessageBuilder messageBuilder;

    /**
     * Messages.
     */
    protected final Map<TypeRegularite, String> messages = new HashMap<TypeRegularite, String>();

    /**
     * Constructeur.
     */
    public GenericConseilFormatter()
    {
        this.messages.put(TypeRegularite.PAR,
                          "conseil.frequence.par");
        this.messages.put(TypeRegularite.TOUS_LES,
                          "conseil.frequence.tous");
        this.messages.put(TypeRegularite.FOIS,
                          "conseil.frequence.fois");
    }

    /**
     * Méthode en charge de construire l message sous forme de chaine de caractère.
     * @param conseil Le ConseilDispensation.
     * @return Le message représentant la fréquendce.
     */
    protected String formatFrequence(final ConseilDispensation conseil)
    {
        final Object[] args = new Object[5];
        args[0] = conseil.getProduitPrescrit().getFrequence().getNbFrequence();
        args[1] = conseil.getProduitPrescrit().getFrequence().getNbUniteTempsFrequence();
        args[2] = conseil.getProduitPrescrit().getFrequence().getUniteFrequence();
        args[3] = conseil.getProduitPrescrit().getDuree().getNb();
        args[4] = conseil.getProduitPrescrit().getDuree().getUnite();
        return this.messageBuilder.getMessage(this.messages.get(conseil
                                                      .getProduitPrescrit()
                                                      .getFrequence()
                                                      .getTypeRegularite()),
                                              args);
    }

    /**
     * Setter pour messageBuilder.
     * @param messageBuilder le messageBuilder à écrire.
     */
    public void setMessageBuilder(final EclipseMessageBuilder messageBuilder)
    {
        this.messageBuilder = messageBuilder;
    }

}
