package fr.pharma.eclipse.script.impl;

import fr.pharma.eclipse.script.ScriptParameterBuilder;
import fr.pharma.eclipse.script.ScriptRunner;

/**
 * Classe de base des scripts.
 
 * @version $Revision$ $Date$
 */
public abstract class AbstractScriptRunner
    implements ScriptRunner
{

    /**
     * Classe chargée de retournée la classe des paramètres du script en fonction des arguments.
     */
    private ScriptParameterBuilder scriptParameterBuilder;

    /**
     * Getter pour scriptParameterBuilder.
     * @return Retourne le scriptParameterBuilder.
     */
    public ScriptParameterBuilder getScriptParameterBuilder()
    {
        return this.scriptParameterBuilder;
    }

    /**
     * Setter pour scriptParameterBuilder.
     * @param scriptParameterBuilder le scriptParameterBuilder à écrire.
     */
    public void setScriptParameterBuilder(final ScriptParameterBuilder scriptParameterBuilder)
    {
        this.scriptParameterBuilder = scriptParameterBuilder;
    }

}
