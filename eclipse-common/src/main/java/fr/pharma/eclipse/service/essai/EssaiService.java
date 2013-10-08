package fr.pharma.eclipse.service.essai;

import java.util.Calendar;
import java.util.List;

import fr.pharma.eclipse.domain.dto.EssaiDTO;
import fr.pharma.eclipse.domain.enums.EtatEssai;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.evenement.Evenement;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.common.GenericService;

/**
 * Interface de service de gestion de essai.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public interface EssaiService extends GenericService<Essai> {
    /**
     * Méthode de sauvegarde d'un essai.
     * @param essai Essai à sauvegarder. séparés par des virgules. affiché lors
     * de la demande de sauvegarde.
     * @return L'essai enregistré.
     */
    Essai save(final Essai essai,
               final String idOngletsVisites);

    /**
     * Méthode en charge d'initialiser le numéro d'enregistrement (ou numéro
     * Sigrec) de l'essai par défaut.
     * @param essai Essai dont on souhaite initialiser le numéro
     * d'enregistrement.
     */
    void initNumEnregistrement(final Essai essai);

    /**
     * Méthode en charge de retourner l'ensemble des pharmacies associées à un
     * essai.
     * @param essai Essai.
     * @return Liste des pharmacies.
     */
    List<Pharmacie> getAllPharmacies(final Essai essai);

    /**
     * Méthode en charge de retourner l'ensemble des pharmacies associées à un
     * essai en prenant en compte les habilitations de l'utilisateur connecté.
     * @param essai Essai.
     * @return Liste des pharmacies.
     */
    List<Pharmacie> getAllPharmaciesOfUser(final Essai essai);

    /**
     * Méthode en charge de supprimer un événement d'un essai.
     * @param essai Essai.
     * @param evenement Evenement.
     */
    void removeEvenement(final Essai essai,
                         final Evenement evenement);

    /**
     * Méthode en charge d'ajouter un nouveau DetailEtatEssai sur un essai.
     * @param essai Essai.
     * @param newEtat Nouvel Etat.
     * @param commentaireNewEtat Commentaire sur le nouvel état.
     */
    void addDetailEtatEssai(final Essai essai,
                            final EtatEssai newEtat,
                            final String commentaireNewEtat);

    /**
     * Méthode en charge de retourner l'ensemble des essais sans purge.
     * @return l'ensemble des essais sans purge.
     */
    List<Essai> getAllWithoutPurge();

    /**
     * Méthdoe en charge de retourner la liste des essais actifs.
     * @param dateFin Date Fin.
     * @param pharmacie Pharmacie.
     * @return La liste des essais actifs.
     */
    List<Essai> getEssaisActifs(Calendar dateFin,
                                Pharmacie pharmacie);

    /**
     * Mettre à jour l'etat si un evenement externe le provoque.
     * @param essai
     * @return l'etat de l'essai à la fin
     */
    EtatEssai updateEtat(Essai essai);

    /**
     * Méthode en charge de récupérer les identifiants techniques des essais
     * visibles de l'utilisateur.
     * @param personne Objet Personne correspondant à l'utilisateur connecté.
     * @return Liste des identifiants techniques des essais.
     */
    List<Long> getIdsEssaisOfUser(final Personne personne);

    /**
     * Méthode permettant d'autocompléter la recherche d'essai promoteur.
     * @param requete Chaîne de caractères saisie par l'utilisateur.
     * @return La liste résultat pour l'auto-complétion.
     */
    List<EssaiDTO> autoCompleteEssai(String requete);

    /**
     * Retourne un essaiDTO à partir d'un id technique d'essai
     * @param id Id d'essai.
     * @return Essai DTO
     */
    EssaiDTO getEssaiDTO(Long id);
}
