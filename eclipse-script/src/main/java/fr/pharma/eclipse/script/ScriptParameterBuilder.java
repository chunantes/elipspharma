package fr.pharma.eclipse.script;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

/**
 * Remplir les paramètres du script.
 
 * @version $Revision$ $Date$
 */
public class ScriptParameterBuilder
{

    /**
     * Recupère les paramètres du script pour les stocker sur le scriptParameter.
     * @param args Arguments.
     * @param scriptParameter Paramètres du script à définir.
     */
    public void getScriptParameter(final String[] args,
                                   final ScriptParameter scriptParameter)
    {
        final CmdLineParser parser = new CmdLineParser(scriptParameter);
        try
        {
            parser.parseArgument(args);
        }
        catch (final CmdLineException e)
        {
            // handling of wrong arguments
            System.err.println(e.getMessage());
            parser.printUsage(System.err);
        }
    }

}
