package fr.pharma.eclipse.utils;

import java.io.Serializable;

import javax.annotation.Resource;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;


/**
 * Classe en charge de gÃ©rer le stockage et la rÃ©cupÃ©ration d'objets en
 * cache.
 * 
 * @author Netapsys - BCO
 * @version $Revision$ $Date$
 */
public class CacheUtils {
	/**
	 * Manager ehcache.
	 */
	@Resource(name = "cacheManager")
	private CacheManager cacheManager;

	/**
	 * ClÃ© utilisÃ©e pour stocker les objets en cache. Nom du cache utilisÃ©
	 * dans la configuration de ehcache (voir le fichier ehcache.xml).
	 */
	private String typeCache;

	/**
	 * MÃ©thode en charge de stocker un objet de l'utilisateur dans le cache.
	 * 
	 * @param toStore
	 *            Objet Ã  stocker
	 */
	public void store(final Serializable toStore) {
		this.store("", toStore);
	}

	/**
	 * MÃ©thode en charge de stocker un objet de l'utilisateur dans le cache.
	 * 
	 * @param key
	 *            ClÃ© spÃ©cifique de stockage
	 * @param toStore
	 *            Objet Ã  stocker
	 */
	public void store(final String key, final Serializable toStore) {
		final String id = "essai" + key;

		final Cache cache = this.cacheManager.getCache(this.typeCache);
		cache.put(new Element(id, toStore));
	}

	/**
	 * MÃ©thode en charge de retourner un objet de l'utilisateur ayant Ã©tÃ©
	 * placÃ© dans le cache.
	 * 
	 * @return Objet stockÃ©
	 */
	public Serializable getCachedObject() {
		return this.getCachedObject("");
	}

	/**
	 * MÃ©thode en charge de retourner un objet de l'utilisateur ayant Ã©tÃ©
	 * placÃ© dans le cache.
	 * 
	 * @param key
	 *            ClÃ© spÃ©cifique de l'objet
	 * @return Objet stockÃ©
	 */
	public Serializable getCachedObject(final String key) {
		final String id = "essai" + key;

		Serializable ser = null;

		final Cache cache = this.cacheManager.getCache(this.typeCache);
		final boolean isStored = cache.isElementInMemory(id);
		if (isStored) {
			final Element element = cache.get(id);
			if ((null != element) && (!cache.isExpired(element))) {
				ser = element.getValue();
			}
		}

		return ser;
	}

	/**
	 * Vide le cache de l'utilisateur connectÃ©.
	 */
	public void clearCache() {
		final Cache cache = this.cacheManager.getCache(this.typeCache);
		cache.remove("");
	}

	/**
	 * MÃ©thode en charge de vide le cache d'un utilisateur.
	 * 
	 * @param loginUser
	 *            Login du user dont il faut viderle cache
	 */
	public void clearCacheEssai(final Long idEssai) {
		final Cache cache = this.cacheManager.getCache(this.typeCache);
		cache.remove("essai"+idEssai);
	}

	/**
	 * Setter pour cacheManager.
	 * 
	 * @param cacheManager
	 *            le cacheManager Ã  Ã©crire.
	 */
	public void setCacheManager(final CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	/**
	 * Setter pour typeCache.
	 * 
	 * @param typeCache
	 *            le typeCache Ã  Ã©crire.
	 */
	public void setTypeCache(final String typeCache) {
		this.typeCache = typeCache;
	}
}
