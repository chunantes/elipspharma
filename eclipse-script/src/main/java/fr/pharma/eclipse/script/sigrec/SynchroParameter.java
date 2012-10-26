package fr.pharma.eclipse.script.sigrec;

import org.kohsuke.args4j.Option;

import fr.pharma.eclipse.script.ScriptParameter;

/**
 * Conteneur de parametre pour la synchronisation avec le fichier sigrec.
 
 * @version $Revision$ $Date$
 */
public class SynchroParameter
    implements ScriptParameter
{
    /**
     * Chemier du fichier source.
     */
    private String filePath;

    /**
     * Getter pour filePath.
     * @return Retourne le filePath.
     */
    public String getFilePath()
    {
        return this.filePath;
    }

    /**
     * Setter pour filePath.
     * @param filePath le filePath à écrire.
     */
    @Option(name = "-filePath", usage = "chemin du fichier source")
    public void setFilePath(final String filePath)
    {
        this.filePath = filePath;
    }

}
