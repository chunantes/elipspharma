package fr.pharma.eclipse.service.indicateur.builder.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;

import fr.pharma.eclipse.comparator.common.EclipseListComparator;
import fr.pharma.eclipse.domain.model.dispensation.Dispensation;
import fr.pharma.eclipse.domain.model.indicateur.Indicateur;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.indicateur.builder.IndicateurBuilder;

/**
 * Classe en charge de construire l'indicateur Nombre de patient.
 
 * @version $Revision$ $Date$
 */
public class NbPatientBuilder
    extends AbstractDispensationBuilder
    implements Serializable, IndicateurBuilder
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 4968606725050106359L;

    /**
     * Libellé.
     */
    private String libelle;

    /**
     * {@inheritDoc}
     */
    @Override
    public Indicateur build(final Pharmacie pharmacie,
                            final Calendar dateDebut,
                            final Calendar dateFin)
    {

        final List results = this.loadDispensations(pharmacie,
                                                    dateDebut,
                                                    dateFin,
                                                    true);

        CollectionUtils.transform(results,
                                  new Transformer() {

                                      @Override
                                      public Object transform(final Object input)
                                      {
                                          return ((Dispensation) input)
                                                  .getPrescription()
                                                  .getInclusion()
                                                  .getPatient();
                                      }
                                  });

        final SortedSet<Patient> patients = new TreeSet<Patient>(new EclipseListComparator());
        patients.addAll(results);
        return new Indicateur(this.libelle,
                              new BigDecimal(patients.size()));
    }

    /**
     * Getter pour libelle.
     * @return Le libelle
     */
    public String getLibelle()
    {
        return this.libelle;
    }

    /**
     * Setter pour libelle.
     * @param libelle Le libelle à écrire.
     */
    public void setLibelle(final String libelle)
    {
        this.libelle = libelle;
    }
}
