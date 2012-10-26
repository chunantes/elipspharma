package fr.pharma.eclipse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.pharma.eclipse.exception.common.CommonException;
import fr.pharma.eclipse.script.launcher.ScriptLauncher;

/**
 * Classe principale d'exécution des scripts.
 
 * @version $Revision$ $Date$
 */
public final class Main
{

    /**
     * Constructeur privé.
     */
    private Main()
    {
    }

    /**
     * Nom du fichier de configuration des scripts.
     */
    public static final String SPRING_CONFIG_SCRIPT_XML = "config/script-context.xml";

    /**
     * Méthode de recopie d'arguments, compatible en Java 5.
     * @param original the array from which a range is to be copied.
     * @param to the initial index of the range to be copied, inclusive.
     * @param from the final index of the range to be copied, exclusive. (This index may lie
     * outside the array.).
     * @return Le tableau redimensionné.
     */
    public static String[] copyOfRange(final String[] original,
                                       final int from,
                                       final int to)
    {

        final int newLength = to
                              - from;
        if (newLength < 0)
        {
            throw new IllegalArgumentException(from
                                               + " > "
                                               + to);
        }
        final String[] copy = new String[newLength];
        System.arraycopy(original,
                         from,
                         copy,
                         0,
                         Math.min(original.length
                                          - from,
                                  newLength));

        return copy;
    }

    /**
     * Point d'entrée principal.
     * @param args Les arguments.
     */
    public static void main(final String[] args)
    {
        final ApplicationContext context = Main.initContext();
        if (args.length != 0)
        {
            try
            {
                ((ScriptLauncher) context.getBean("scriptLauncher"))
                        .run(args[0],
                             Main.copyOfRange(args,
                                              1,
                                              args.length));
            }
            catch (final CommonException e)
            {
                System.err.println(e.getMessage()
                                   + ".\n"); //$NON-NLS-1$
                System.exit(-1);
            }
        }
        else
        {
            System.err.println("Le nom de la tâche n'est pas définie.\n"); //$NON-NLS-1$
            System.exit(-1);
        }
        System.exit(0);
    }

    /**
     * Méthode en charge de monter le contexte Spring.
     * @return Le contexte.
     */
    private static ApplicationContext initContext()
    {
        return new ClassPathXmlApplicationContext("config/applicationContext-script.xml");
    }
}
