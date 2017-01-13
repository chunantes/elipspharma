package fr.pharma.eclipse.dao.search;

import java.io.Serializable;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import fr.pharma.eclipse.domain.enums.TypePersonne;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.acteur.Pharmacien;

@Component
public class HabilitationSearchDao extends CommonSearchDao implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -8821346773312312213L;
    
    public void saveHabilitationForPersonne(final Personne personne) {
        final SQLQuery q =
                this.getCurrentSession()
                        .createSQLQuery("update habilitation set droit = ? where id_personne = ?");
            Assert.isTrue(TypePersonne.PHARMACIEN.equals(personne.getType()), "La personne n'est pas un pharmacien!");
            final Pharmacien pharmacien = (Pharmacien) personne;
            String droit = pharmacien.getType().name() + "_" + pharmacien.getTypePharmacien().name();
            q.setParameter(0, droit);
            q.setParameter(1, personne.getId());
            q.executeUpdate();
    }
}
