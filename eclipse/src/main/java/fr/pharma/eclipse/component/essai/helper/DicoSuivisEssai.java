package fr.pharma.eclipse.component.essai.helper;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import fr.pharma.eclipse.component.helper.BeanManagerHelper;
import fr.pharma.eclipse.domain.enums.TypeHistoriqueEssai;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.suivi.common.Suivi;

/**
 * Dictionnaire des derniers historiques associés à un essai.
 
 * @version $Revision$ $Date$
 */
public class DicoSuivisEssai
    implements Serializable
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 1812409755876613236L;

    /**
     * Helper.
     */
    private BeanManagerHelper<Essai> helper;

    /**
     * Map en charge de stocker les derniers historiques de chaque onglet.
     */
    private final Map<TypeHistoriqueEssai, Suivi> derniersHistoriques =
        new TreeMap<TypeHistoriqueEssai, Suivi>();

    /**
     * Méthode d'initialisation de la map des derniers historiques d'un essai.
     * @param essai Essai.
     */
    public void init(final Essai essai)
    {
        this.derniersHistoriques.clear();
        this.derniersHistoriques.put(TypeHistoriqueEssai.GENERAL,
                                     this.helper.getFirstOfCollection(essai.getModifs()));
        this.derniersHistoriques.put(TypeHistoriqueEssai.ONG_RECHERCHE,
                                     this.helper.getFirstOfCollection(essai
                                             .getDetailRecherche()
                                             .getModifs()));
        this.derniersHistoriques.put(TypeHistoriqueEssai.ONG_CONTACTS,
                                     this.helper.getFirstOfCollection(essai
                                             .getDetailContacts()
                                             .getModifs()));
        this.derniersHistoriques.put(TypeHistoriqueEssai.ONG_FAISABILITE,
                                     this.helper.getFirstOfCollection(essai
                                             .getDetailFaisabilite()
                                             .getModifs()));
        this.derniersHistoriques.put(TypeHistoriqueEssai.ONG_DATES,
                                     this.helper.getFirstOfCollection(essai
                                             .getDetailDates()
                                             .getModifs()));
        this.derniersHistoriques.put(TypeHistoriqueEssai.ONG_ADMIN_REG,
                                     this.helper.getFirstOfCollection(essai
                                             .getDetailAdministratif()
                                             .getModifs()));
        this.derniersHistoriques.put(TypeHistoriqueEssai.ONG_PRODUITS,
                                     this.helper.getFirstOfCollection(essai
                                             .getDetailProduit()
                                             .getModifs()));
        this.derniersHistoriques.put(TypeHistoriqueEssai.ONG_DATA_PHARMA,
                                     this.helper.getFirstOfCollection(essai
                                             .getDetailDonneesPharma()
                                             .getModifs()));
        this.derniersHistoriques.put(TypeHistoriqueEssai.ONG_DESIGN,
                                     this.helper.getFirstOfCollection(essai
                                             .getDetailDesign()
                                             .getModifs()));
        this.derniersHistoriques.put(TypeHistoriqueEssai.ONG_AUTRES_DOCS,
                                     this.helper.getFirstOfCollection(essai
                                             .getDetailAutresDocuments()
                                             .getModifs()));
    }

    /**
     * Méthode pour indiquer si le dictionnaire possède entrée non nulle pour le type donné.
     * @param ongletEssai Type de la modification à récupérer.
     * @return true ssi le dictionnaire possède entrée non nulle pour le type donné.
     */
    public boolean hasDerniereModif(final TypeHistoriqueEssai ongletEssai)
    {
        return this.derniersHistoriques.containsKey(ongletEssai)
               && this.derniersHistoriques.get(ongletEssai) != null;
    }

    /**
     * Méthode en charge de récupérer la dernière modification d'un certain type.
     * @param typeSuivi Type de la modification à récupérer.
     * @return La dernière modification (null si inexistante).
     */
    public Suivi getDerniereModif(final TypeHistoriqueEssai typeSuivi)
    {
        return this.derniersHistoriques.get(typeSuivi);
    }

    /**
     * Getter sur derniersHistoriques.
     * @return Retourne le derniersHistoriques.
     */
    Map<TypeHistoriqueEssai, Suivi> getDerniersHistoriques()
    {
        return this.derniersHistoriques;
    }

    /**
     * Setter pour helper.
     * @param helper le helper à écrire.
     */
    public void setHelper(final BeanManagerHelper<Essai> helper)
    {
        this.helper = helper;
    }
}
