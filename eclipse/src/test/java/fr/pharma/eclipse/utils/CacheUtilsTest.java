package fr.pharma.eclipse.utils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.pharma.eclipse.domain.model.essai.Essai;
import net.sf.ehcache.CacheManager;

public class CacheUtilsTest {

    /**
     * Manager de cache.
     */
    private CacheManager cacheManager;

    /**
     * Utilitaire de cache.
     */
    private CacheUtils cacheUtils;

    /**
     * Initialisation.
     */
    @Before
    public void setUp()
    {
        this.cacheManager = CacheManager.getInstance();
        this.cacheUtils = new CacheUtils();
        this.cacheUtils.setCacheManager(this.cacheManager);
        this.cacheUtils.setTypeCache("testCache");
        
        if (!this.cacheManager.cacheExists("testCache"))
        {
            this.cacheManager.addCache("testCache");
        }
    }

    /**
     * Finalisation.
     */
    @After
    public void tearDown()
    {
        this.cacheManager = null;
        this.cacheUtils = null;
    }

    /**
     * Test du stockage et de la suppression d'un objet.
     */
    @Test
    public void store()
    {
        final Essai essai = new Essai();
        essai.setId(83L);
        String idEssai = Long.toString(essai.getId());
        this.cacheUtils.store(idEssai,idEssai);

        Assert.assertNotNull(this.cacheUtils.getCachedObject(idEssai));

        this.cacheUtils.clearCacheEssai(essai.getId());
        Assert.assertNull(this.cacheUtils.getCachedObject(idEssai));
    }

    /**
     * Test la récupération d'un objet stocké.
     */
    @Test
    public void getCachedObject()
    {
        final Essai essai = new Essai();
        essai.setId(83L);
        String idEssai = Long.toString(essai.getId());
        this.cacheUtils.store(idEssai,idEssai);

        Assert.assertNotNull(this.cacheUtils.getCachedObject(idEssai));

        this.cacheUtils.clearCacheEssai(83L);
        Assert.assertNull(this.cacheUtils.getCachedObject(idEssai));
    }

    /**
     * Test la récupération d'un objet qui n'a pas été stocké.
     */
    @Test
    public void getCachedObjectInexistant()
    {
        final Essai essai = new Essai();
        essai.setId(83L);
        this.cacheUtils.store(essai);

        Assert.assertNull(this.cacheUtils.getCachedObject("82"));
        this.cacheUtils.clearCacheEssai(83L);
    }

}
