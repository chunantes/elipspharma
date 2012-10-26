package fr.pharma.eclipse.script.promoteur;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.User;

import fr.pharma.eclipse.processor.Processor;
import fr.pharma.eclipse.script.ScriptRunner;
import fr.pharma.eclipse.script.impl.AbstractScriptRunner;

/**
 * Runner en charge de déclencher le script de synchronisation avec le fichier Sigrec.
 
 * @version $Revision$ $Date$
 */
public class PromoteurRunner
    extends AbstractScriptRunner
    implements ScriptRunner
{

    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(PromoteurRunner.class);

    /**
     * Processor en charge de copier les promoteurs sigrec en promoteurs eclipse.
     */
    @Resource(name = "promoteurEclipseProcessor")
    private Processor promoteurProcessor;

    /**
     * Constructeur.
     */
    public PromoteurRunner()
    {
        super();
    }

    /**
     * {@inheritDoc}
     */
    public void run(final String[] args)
    {
        this.log.info("Debut de la recopie des beans 'Promoteur' Sigrec en beans Eclipse");

        // Simulation d'une connexion pour le suivi
        final SecurityContext ctx = new SecurityContextImpl();
        final Authentication auth =
            new UsernamePasswordAuthenticationToken(new User("Sigrec",
                                                             "sigrec",
                                                             true,
                                                             true,
                                                             true,
                                                             true,
                                                             new ArrayList<GrantedAuthority>()),
                                                    null);
        ctx.setAuthentication(auth);
        SecurityContextHolder.setContext(ctx);

        // exécution
        this.promoteurProcessor.process();
        this.log.info("Recopie terminee.");
    }
    /**
     * Setter pour promoteurProcessor.
     * @param promoteurProcessor le promoteurProcessor à écrire.
     */
    public void setPromoteurProcessor(final Processor promoteurProcessor)
    {
        this.promoteurProcessor = promoteurProcessor;
    }

}
