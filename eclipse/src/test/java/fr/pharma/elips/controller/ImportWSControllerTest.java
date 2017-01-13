package fr.pharma.elips.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pharma.eclipse.domain.enums.NatureRecherche;
import fr.pharma.eclipse.domain.enums.ObjetRecherche;
import fr.pharma.eclipse.domain.enums.PhaseRecherche;
import fr.pharma.eclipse.domain.enums.Thematique;
import fr.pharma.eclipse.domain.enums.TypePersonne;
import fr.pharma.eclipse.domain.enums.TypePromoteur;
import fr.pharma.eclipse.domain.enums.TypeRecherche;
import fr.pharma.eclipse.domain.enums.UniteTempsPrevision;
import fr.pharma.eclipse.domain.model.acteur.Investigateur;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.habilitation.Habilitation;
import fr.pharma.eclipse.service.acteur.PersonneService;
import fr.pharma.eclipse.service.localisation.ServiceService;
import fr.pharma.elips.controller.dto.EssaiImportDTO;
import fr.pharma.elips.controller.exception.ImportException;

@RunWith(MockitoJUnitRunner.class)
public class ImportWSControllerTest {

	/** Mock du service d'appel à Elips. */
	@Mock
	private PersonneService<Investigateur> investServiceMock;
	
	@Mock
	private ServiceService serviceService;


	/** Logger. */
	private final static Logger LOG = LoggerFactory
			.getLogger(ImportWSControllerTest.class);

	/** Controller. */
	@InjectMocks
	private ImportWSController controller;

	@Test
	public void testMappingLight() throws ImportException {
		// Construction d'un DTO
		final EssaiImportDTO dto = new EssaiImportDTO();
		// -- Définition d'une valeur Enum
		final ObjetRecherche objet = ObjetRecherche.ALICAMENT;
		dto.setObjetRecherche(objet.name());
		// -- Définition d'une valeur Date
		final Date date = Calendar.getInstance().getTime();
		dto.setDatePrevDebEtude(date);
		// -- Définition d'une valeur String
		final String libelle = "Libellé";
		dto.setNomCompagnieAssurance(libelle);
		// -- Définition d'une valeur Integer
		final int num = 2;
		dto.setNbPatientPrevuTotal(Integer.valueOf(num));

		// Initialisation de l'objet Essai
		final Essai essai = new Essai();
		this.controller.mappingEssai(essai, dto);

		// Vérifications
		assertEquals(objet, essai.getDetailRecherche().getObjetRecherche());
		assertEquals(date, essai.getDetailDates().getDebutEtudePrev().getTime());
		assertEquals(libelle, essai.getDetailAdministratif()
				.getInfosAssurance().getNomCompagnie());
		assertEquals(num, essai.getDetailDonneesPharma().getInfosGenerales()
				.getNbPatientsPrevusTotal().intValue());
	}

	/**
	 * @throws ImportException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	@Test
	public void testMappingFull() throws ImportException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		// Construction d'un DTO
		final EssaiImportDTO dto = new EssaiImportDTO();

		final Map<String, Enum<?>> enumValues = new HashMap<String, Enum<?>>();
		enumValues.put("typePromoteur", TypePromoteur.INDUSTRIEL);
		enumValues.put("typeRecherche", TypeRecherche.COLLECTIONS_BIOLOGIQUES);
		enumValues.put("objetRecherche", ObjetRecherche.COSMETIQUE);
		enumValues.put("phaseRecherche", PhaseRecherche.III);
		enumValues.put("natureRecherche", NatureRecherche.EPIDEMIOLOGIE);
		enumValues.put("thematique", Thematique.BACTERIOLOGIE);
		enumValues.put("uniteDureeTotPrevue", UniteTempsPrevision.MOIS);

		// Mapping automatique de tous les champs
		final Map<String, String> properties = PropertyUtils.describe(dto);
		int index = 1;
		for (String champ : properties.keySet()) {
			if (Arrays.asList(
					new String[] { "class", "mappingPropertiesKO", "modif" })
					.contains(champ)) {
				continue;
			}
			final PropertyDescriptor property = PropertyUtils
					.getPropertyDescriptor(dto, champ);
			final Class<?> targetClass = property.getPropertyType();

			final Object valeur;

			if (enumValues.containsKey(champ)) {
				valeur = enumValues.get(champ).name();
			} else if (String.class.isAssignableFrom(targetClass)) {
				valeur = champ;
			} else if (Date.class.isAssignableFrom(targetClass)) {
				valeur = Calendar.getInstance().getTime();
			} else if (Integer.class.isAssignableFrom(targetClass)) {
				valeur = Integer.valueOf(index++);
			} else if (Long.class.isAssignableFrom(targetClass)) {
				valeur = Long.valueOf(index++);
			} else if (Boolean.class.isAssignableFrom(targetClass)) {
				valeur = Boolean.TRUE;
			} else {
				System.err.println("Type inconnu " + targetClass);
				valeur = null;
			}

			PropertyUtils.setProperty(dto, champ, valeur);
		}

		// Ajout d'un investigateur
		final Investigateur invest = new Investigateur();
		invest.setId(dto.getIdInvestigateur());
		invest.setType(TypePersonne.INVESTIGATEUR);
		invest.setNom("Nom de l'investigateur");
		when(this.investServiceMock.get(dto.getIdInvestigateur())).thenReturn(
				invest);
		this.controller.setInvestService(this.investServiceMock);

		// Initialisation de l'objet Essai
		final Essai essai = new Essai();
		this.controller.mappingEssai(essai, dto);

		// Vérifications
		for (String champ : dto.getMappingPropertiesKO()) {
			LOG.error("Mapping KO pour le champ [{}]", champ);
		}
		assertEquals(1, dto.getMappingPropertiesKO().size());
		assertEquals("conventionSignee", dto.getMappingPropertiesKO().get(0));

		// Vérification du mapping de l'investigateur
		boolean investOK = false;
		for (Habilitation hab : essai.getDetailContacts().getHabilitations()) {
			if (hab.getPersonne() == invest) {
				investOK = true;
				break;
			}
		}
		assertTrue("Investigateur KO", investOK);
	}
}
