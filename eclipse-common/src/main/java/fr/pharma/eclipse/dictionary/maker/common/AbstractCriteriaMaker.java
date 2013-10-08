package fr.pharma.eclipse.dictionary.maker.common;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import fr.pharma.eclipse.dao.search.AclSearchDao;
import fr.pharma.eclipse.dictionary.maker.CriteriaMaker;

/**
 * Classe abstraite de base pour les artisans des critères de recherche.
 * @author NETAPSYS
 * @version $Revision$ $Date$
 */
public abstract class AbstractCriteriaMaker implements CriteriaMaker, Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -4110449095770174380L;

    /**
     * La classe du critère de recherche supporté.
     */
    @SuppressWarnings({"rawtypes" })
    private final Class supportedClass;

    /**
     * DAO de recherche des acls.
     */
    @Autowired
    private AclSearchDao aclSearchDao;

    /**
     * Constructeur unique.
     * @param supportedCriteriaClass La classe du critère de recherche supporté.
     */
    @SuppressWarnings({"rawtypes" })
    public AbstractCriteriaMaker(final Class supportedCriteriaClass) {
        this.supportedClass = supportedCriteriaClass;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings({"unchecked", "rawtypes" })
    public boolean supports(final Class clazz) {
        return this.supportedClass.isAssignableFrom(clazz);
    }

    /**
     * Getter pour aclSearchDao.
     * @return Le aclSearchDao
     */
    public AclSearchDao getAclSearchDao() {
        return this.aclSearchDao;
    }

    /**
     * Setter pour aclSearchDao.
     * @param aclSearchDao Le aclSearchDao à écrire.
     */
    public void setAclSearchDao(final AclSearchDao aclSearchDao) {
        this.aclSearchDao = aclSearchDao;
    }

}
