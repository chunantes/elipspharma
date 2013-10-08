package fr.pharma.eclipse.service.actualite.impl;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import fr.pharma.eclipse.dao.search.AclSearchDao;
import fr.pharma.eclipse.domain.model.actualite.Actualite;
import fr.pharma.eclipse.service.actualite.ActualiteService;
import fr.pharma.eclipse.service.essai.EssaiService;

/**
 * Classe d'implémentation du service de gestion des actualités.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ActualiteServiceImpl implements ActualiteService, Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 6364599361983153932L;

    /**
     * Service de gestion des essais.
     */
    @Resource(name = "essaiService")
    private EssaiService essaiService;

    /**
     * DAO de recherche des ACLs.
     */
    @Autowired
    private AclSearchDao aclSearchDao;

    /**
     * Constante représentant le nombre d'essais retournés pour les actualités.
     */
    protected static final int NB_ESSAIS = 15;

    /**
     * Requête de récupération des actualités pour un administrateur
     */
    protected static final String REQ_ACTUALITES = "select distinct e.id, e.numInterne, e.nom, p.raisonSociale, e.codePromoteur, e.dci " + "from essai e "
                                                   + "inner join promoteur p on e.id_promoteur=p.id and e.id in {0}" + " order by id desc limit " + ActualiteServiceImpl.NB_ESSAIS;

    /**
     * Tableau de colonnes.
     */
    protected static final String[] COLS = {"id", "numInterne", "nom", "raisonSociale", "codePromoteur", "dci" };

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Actualite> getLastEssais() {
        final List<Long> idsEssais = this.aclSearchDao.findIdsEssais();
        final String paramIdsEssais = Arrays.toString(idsEssais.toArray(new Object[idsEssais.size()])).replace("[", "(").replace("]", ")");
        return (List<Actualite>) this.essaiService.executeSQLQuery(MessageFormat.format(ActualiteServiceImpl.REQ_ACTUALITES, paramIdsEssais), null, ActualiteServiceImpl.COLS,
                                                                   Actualite.class);
    }

    /**
     * Setter pour essaiService.
     * @param essaiService Le essaiService à écrire.
     */
    public void setEssaiService(final EssaiService essaiService) {
        this.essaiService = essaiService;
    }

}
