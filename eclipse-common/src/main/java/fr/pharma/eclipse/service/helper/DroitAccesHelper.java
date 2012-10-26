package fr.pharma.eclipse.service.helper;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;

import fr.pharma.eclipse.domain.enums.QualiteInsu;
import fr.pharma.eclipse.domain.enums.RolePersonne;
import fr.pharma.eclipse.domain.enums.TypeDispensation;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.service.user.UserService;

/**
 * Helper en charge de fournir des services afin de déterminer l'affichage ou non de certains
 * elements.
 
 * @version $Revision$ $Date$
 */
public class DroitAccesHelper
    implements Serializable
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -3775967038086290919L;

    /**
     * Type de personne suceptibles de ne pas voir l'onglet design si essai en double aveugle.
     */
    private final RolePersonne[] ongletDesign =
    {RolePersonne.ARC_INVESTIGATEUR, RolePersonne.INVESTIGATEUR, RolePersonne.PROMOTEUR,
     RolePersonne.CRO, RolePersonne.ARC_PROMOTEUR, };

    /**
     * Type de personne suceptibles de ne pas voir le patient en cas d'essai en double aveugle.
     */
    private final RolePersonne[] patient =
    {RolePersonne.PROMOTEUR, RolePersonne.CRO, RolePersonne.ARC_PROMOTEUR, };

    /**
     * Type de personne suceptibles de voir certains éléments en lecture seule.
     */
    private final RolePersonne[] lectureSeule =
    {RolePersonne.ARC_PROMOTEUR, RolePersonne.ARC_INVESTIGATEUR, };

    /**
     * Type de personne suceptibles de voir certains l'essai en lecture seule.
     */
    private final RolePersonne[] essaiLectureSeule =
    {RolePersonne.ARC_PROMOTEUR, RolePersonne.ARC_INVESTIGATEUR, RolePersonne.INVESTIGATEUR,
     RolePersonne.PROMOTEUR, RolePersonne.CRO, RolePersonne.DIRECTION_RECHERCHE,
     RolePersonne.PHARMACIEN_EXTERNE, RolePersonne.PHARMACIEN_INTERNE_GARDE,
     RolePersonne.PHARMACIEN_PREPARATEUR, };

    /**
     * Service utilisateur.
     */
    @Resource(name = "userService")
    private UserService userService;

    /**
     * Retourne <true> si le patient est visible selon le role de l'utilisateur connecté et la
     * qualité d'insu de l'essai.
     * @return <true> si le patient est visible selon le role de l'utilisateur connecté et la
     * qualité d'insu de l'essai.
     */
    public boolean isPatientVisible()
    {
        return !this.isPersonneConcerned(this.patient);
    }

    /**
     * Retourne <false> si l'essai est en double aveugle et que l'utilisateur correspond au
     * profils qui n'ont pas accès à l'onglet design.
     * @param essai Essai.
     * @return <false> si l'essai est en double aveugle et que l'utilisateur correspond au profils
     * qui n'ont pas accès à l'onglet design.
     */
    public boolean isDesignVisible(final Essai essai)
    {
        return !(this.isEssaiDoubleAveugle(essai) && this.isPersonneConcerned(this.ongletDesign));
    }
    /**
     * Retourne <true> si l'essai est en double aveugle.
     * @param essai Essai.
     * @return <true> si l'essai est en double aveugle.
     */
    public boolean isEssaiDoubleAveugle(final Essai essai)
    {
        final QualiteInsu insu =
            essai.getDetailDonneesPharma().getInfosGenerales().getQualiteInsu();
        final TypeDispensation typeDispensation =
            essai.getDetailDonneesPharma().getInfosDispensations().getTypeDispensation();
        return insu != null
               && essai.getDetailDonneesPharma()
                       .getInfosGenerales()
                       .getQualiteInsu()
                       .equals(QualiteInsu.ESSAI_DOUBLE_AVEUGLE)
               && (typeDispensation == null || !typeDispensation.equals(TypeDispensation.GLOBALE));
    }

    /**
     * Retourne <true> si l'élément est en lecture seule selon le role de l'utilisateur. Méthode
     * commune pour prescription, dispensation, dispensation globale, demande de dotation
     * @return <true> si l'élément est en lecture seule selon le role de l'utilisateur.
     */
    public Boolean isLectureSeule()
    {
        return this.isPersonneConcerned(this.lectureSeule);
    }

    /**
     * Retourne true si l'essai est en dispensation globale.
     * @param essai L'essai.
     * @return true si l'essai est en dispensation globale.
     */
    public Boolean isDispensationGlobale(final Essai essai)
    {
        final TypeDispensation type =
            essai.getDetailDonneesPharma().getInfosDispensations().getTypeDispensation();
        return type != null
               && type.equals(TypeDispensation.GLOBALE);
    }

    /**
     * Retourne <true> si l'essai est en lecture seule selon le role de l'utilisateur.
     * @return <true> si l'essai est en lecture seule selon le role de l'utilisateur.
     */
    public Boolean isEssaiLectureSeule()
    {
        return this.isPersonneConcerned(this.essaiLectureSeule);
    }

    /**
     * Methode en charge de determine si le role de l'utilisateur connecte est dans le tableau en
     * parametre.
     * @param types Tableau de types de personnes.
     * @return <true> si le type de l'utilisateur connecté est dans le tableau en paramètre.
     */
    private boolean isPersonneConcerned(final RolePersonne[] types)
    {
        for (final RolePersonne type : types)
        {
            for (final GrantedAuthority a : this.userService.getUser().getAuthorities())
            {
                if (a.getAuthority().equals("ROLE_"
                                            + type.name()))
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Setter pour userService.
     * @param userService le userService à écrire.
     */
    public void setUserService(final UserService userService)
    {
        this.userService = userService;
    }

}
