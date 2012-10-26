package fr.pharma.eclipse.script.launcher;

import java.util.Map;

import fr.pharma.eclipse.script.ScriptRunner;

/**
 * Processus principal des scripts.
 
 * @version $Revision$ $Date$
 */
public class ScriptLauncher
{

    /**
     * Classes de script à lancer classées par nom du script.
     */
    private Map<String, ScriptRunner> scriptRunnerByScriptNames;

    /**
     * Exécuter le script avec les arguments à charger.
     * @param scriptName Nom du script.
     * @param args Arguments.
     */
    public void run(final String scriptName,
                    final String[] args)
    {
        final ScriptRunner scriptRunner = this.getScriptRunnerByName(scriptName);

        scriptRunner.run(args);
    }

    /**
     * Retourne le script à exécuter en fonction du nom du script.
     * @param scriptName Nom du script.
     * @return Script à exécuter.
     */
    private ScriptRunner getScriptRunnerByName(final String scriptName)
    {
        return this.scriptRunnerByScriptNames.get(scriptName);
    }

    /**
     * Setter pour scriptRunnerByScriptNames.
     * @param scriptRunnerByScriptNames le scriptRunnerByScriptNames à écrire.
     */
    public void setScriptRunnerByScriptNames(final Map<String, ScriptRunner> scriptRunnerByScriptNames)
    {
        this.scriptRunnerByScriptNames = scriptRunnerByScriptNames;
    }

}
