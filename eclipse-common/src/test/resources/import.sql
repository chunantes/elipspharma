DELETE FROM acl_essai;
DELETE FROM acl_pharmacie;
DELETE FROM habilitation;
DELETE FROM essai_commentaire_detail_administratif_archi;
DELETE FROM essai_document_detail_administratif;
DELETE FROM essai_detail_administratif_suivi;
DELETE FROM essai_detail_administratif;
DELETE FROM essai_detail_contacts_suivi;
DELETE FROM essai_detail_contacts;
DELETE FROM mvtstock_document;
DELETE FROM cro_sigrec;
DELETE FROM pharmacien_document_pharmacien;
DELETE FROM essai_detail_faisabilite_suivi;
DELETE FROM essai_detail_faisabilite_service;
DELETE FROM essai_detail_autres_documents_suivi;
DELETE FROM essai_detail_autres_documents;
DELETE FROM essai_commentaire_detail_faisabilite;
DELETE FROM essai_detail_faisabilite;
DELETE FROM essai_suivi;
DELETE FROM lignestock;
DELETE FROM arcinvestigateur_service;
DELETE FROM element_to_check;
DELETE FROM evenement_suivi;
DELETE FROM mvt_preparation;
DELETE FROM essai_detail_pharma_suivi;
DELETE FROM incident;
DELETE FROM essai_detail_dates_suivi;
DELETE FROM co_investigateur_sigrec;
DELETE FROM prevision_sigrec;
DELETE FROM assurance_sigrec;
DELETE FROM arc_investigateur_sigrec;
DELETE FROM essai_detail_recherche_sigrec;
DELETE FROM essai_sigrec;
DELETE FROM investigateur_sigrec;
DELETE FROM centre_sigrec;
DELETE FROM mvt_destruction;
DELETE FROM essai_detail_dates;
DELETE FROM produit_suivi;
DELETE FROM essai_commentaire_detail_recherche;
DELETE FROM pharmacie_suivi;
DELETE FROM produit_therapeutique;
DELETE FROM mvt_cession_pui;
DELETE FROM mvt_dispensation;
DELETE FROM produit_prescrit;
DELETE FROM dispensation;
DELETE FROM prescription;
DELETE FROM retour_patient;
DELETE FROM arc_promoteur_sigrec;
DELETE FROM produit_detail_stockage;
DELETE FROM produit_detail_logistique;
DELETE FROM pharmacie_site;
DELETE FROM mvt_retour_promoteur;
DELETE FROM inclusion;
DELETE FROM historique_patient;
DELETE FROM essai_detail_pharma_pharmacie;
DELETE FROM essai_detail_pharma_etablissement;
DELETE FROM essai_document_detail_pharma;
DELETE FROM essai_detail_pharma;
DELETE FROM donnees_prevision;
DELETE FROM essai_detail_recherche_suivi;
DELETE FROM preparation;
DELETE FROM evenement;
DELETE FROM mvt_autre_sortie;
DELETE FROM essai_detail_recherche;
DELETE FROM essai_document_detail_autres_documents;
DELETE FROM essai_detail_design_suivi;
DELETE FROM etablissement_suivi;
DELETE FROM medicament;
DELETE FROM pole_suivi;
DELETE FROM promoteur_suivi;
DELETE FROM essai_document_detail_surcout;
DELETE FROM essai_detail_surcout_suivi;
DELETE FROM item_regle;
DELETE FROM item;
DELETE FROM grille;
DELETE FROM essai_detail_surcout;
DELETE FROM pharmacien_pharmacie;
DELETE FROM essai_service;
DELETE FROM prescription_type;
DELETE FROM mvt_preparationentree;
DELETE FROM mvt_approvisionnement;
DELETE FROM service_suivi;
DELETE FROM produit_service;
DELETE FROM incident_suivi;
DELETE FROM promoteur_sigrec;
DELETE FROM dispositif_medical;
DELETE FROM sequence;
DELETE FROM bras;
DELETE FROM investigateur_service;
DELETE FROM mvt_dispensation_globale;
DELETE FROM dotation;
DELETE FROM essai_detail_etat;
DELETE FROM contact_sigrec;
DELETE FROM essai_detail_design;
DELETE FROM essai_detail_produit_suivi;
DELETE FROM produit_document_actes_pharma;
DELETE FROM regle_surcout;
DELETE FROM categorie;
DELETE FROM personne_suivi;
DELETE FROM mvtstock;
DELETE FROM personne;
DELETE FROM conditionnement;
DELETE FROM produit;
DELETE FROM essai_detail_produit;
DELETE FROM patient_suivi;
DELETE FROM patient;
DELETE FROM essai;
DELETE FROM ordonnancier_dispensation;
DELETE FROM ordonnancier_fab_reconst;
DELETE FROM stockage;
DELETE FROM pharmacie;
DELETE FROM promoteur;
DELETE FROM service;
DELETE FROM theme;
DELETE FROM grille_modele;
DELETE FROM pole;
DELETE FROM site_suivi;
DELETE FROM site;
DELETE FROM etablissement;

---------------------------------
-- Donnees des tests unitaires
---------------------------------

-- Table Etablissement
INSERT INTO etablissement(id, nom, telephone, fax, mail, adresseDirection, codePostal, ville, pays) VALUES (1, 'CHU Nantes', '', '', '', '', '44000', 'Nantes', 'France');

-- Table Site
INSERT INTO site(id, code, nom, adresse, codePostal, ville, id_etablissement) VALUES (1, 'HD/HME', 'Hôpital Hôtel-Dieu/Mère Enfant', '', '', '', 1);
INSERT INTO site(id, code, nom, adresse, codePostal, ville, id_etablissement) VALUES (2, 'HGRL', 'Hôpital Guillaume/René Laennec', '', '', '', 1);
INSERT INTO site(id, code, nom, adresse, codePostal, ville, id_etablissement) VALUES (3, 'HSJ', 'Hôpital Saint-Jacques', '', '', '', 1);

-- Table Pole
INSERT INTO pole(id, nom, id_etablissement) VALUES (1, 'IMAD-Institut des maladies de l''appareil digestif', 1);
INSERT INTO pole(id, nom, id_etablissement) VALUES (2, 'Institut de Transplantations, Urologie, Néphrologie', 1);
INSERT INTO pole(id, nom, id_etablissement) VALUES (3, 'Institut du Thorax', 1);
INSERT INTO pole(id, nom, id_etablissement) VALUES (4, 'Pôle Anesthésie-Réanimations', 1);
INSERT INTO pole(id, nom, id_etablissement) VALUES (5, 'Pôle Gérontologie Clinique', 1);
INSERT INTO pole(id, nom, id_etablissement) VALUES (6, 'Pôle Imageries & Explorations Fonctionnelles', 1);
INSERT INTO pole(id, nom, id_etablissement) VALUES (7, 'Pôle Médecine Cancérologie Hématologie', 1);
INSERT INTO pole(id, nom, id_etablissement) VALUES (8, 'Pôle Medecine Physique & Réadaptation/Soins de suite', 1);
INSERT INTO pole(id, nom, id_etablissement) VALUES (9, 'Pôle Mère Enfant', 1);
INSERT INTO pole(id, nom, id_etablissement) VALUES (10, 'Pôle Neurosciences', 1);
INSERT INTO pole(id, nom, id_etablissement) VALUES (11, 'Pôle Ostéo-Articulaire', 1);
INSERT INTO pole(id, nom, id_etablissement) VALUES (12, 'Pôle Psychiatries', 1);
INSERT INTO pole(id, nom, id_etablissement) VALUES (13, 'Pôle Tête & Cou', 1);
INSERT INTO pole(id, nom, id_etablissement) VALUES (14, 'Pôle Urgences', 1);
INSERT INTO pole(id, nom, id_etablissement) VALUES (15, 'Recherche-CIC', 1);

-- Table Service
INSERT INTO service(id, nom, id_pole) VALUES (1, 'GASTRO-ENTEROLOGIE-HEPATOLOGIE', 1);
INSERT INTO service(id, nom, id_pole) VALUES (2, 'HEMATOLOGIE', 7);
INSERT INTO service(id, nom, id_pole) VALUES (3, 'ONCOLOGIE PEDIATRIQUE', 7);
INSERT INTO service(id, nom, id_pole) VALUES (4, 'DERMATOLOGIE', 7);
INSERT INTO service(id, nom, id_pole) VALUES (5, 'MEDECINE INTERNE', 7);
INSERT INTO service(id, nom, id_pole) VALUES (6, 'CIC GASTRO-ENTEROLOGIE /NUTRITION', 15);
INSERT INTO service(id, nom, id_pole) VALUES (7, 'ANESTHESIE HD/JEAN MONNET/HME', 4);
INSERT INTO service(id, nom, id_pole) VALUES (8, 'GYNECOLOGIE & OBSTETRIQUE', 9);
INSERT INTO service(id, nom, id_pole) VALUES (9, 'MALADIES INFECTIEUSES ET TROPICALES', 7);
INSERT INTO service(id, nom, id_pole) VALUES (10, 'NEPHROLOGIE', 2);
INSERT INTO service(id, nom, id_pole) VALUES (11, 'OPHTALMOLOGIE', 13);
INSERT INTO service(id, nom, id_pole) VALUES (12, 'PEDIATRIE', 9);
INSERT INTO service(id, nom, id_pole) VALUES (13, 'RHUMATOLOGIE', 11);
INSERT INTO service(id, nom, id_pole) VALUES (14, 'UROLOGIE', 2);
INSERT INTO service(id, nom, id_pole) VALUES (15, 'URGENCES', 14);
INSERT INTO service(id, nom, id_pole) VALUES (16, 'CTCV Réanimation Chirurgie Thoracique Cardiaque et Vasculaire', 4);
INSERT INTO service(id, nom, id_pole) VALUES (17, 'PNEUMOLOGIE', 3);
INSERT INTO service(id, nom, id_pole) VALUES (18, 'NEUROLOGIE', 10);
INSERT INTO service(id, nom, id_pole) VALUES (19, 'EXPLORATIONS FONCTIONNELLES', 6);
INSERT INTO service(id, nom, id_pole) VALUES (20, 'ENDOCRINOLOGIE', 3);
INSERT INTO service(id, nom, id_pole) VALUES (21, 'MEDECINE AIGUE GERIATRIQUE', 5);
INSERT INTO service(id, nom, id_pole) VALUES (22, 'PSYCHIATRIE 1', 12);
INSERT INTO service(id, nom, id_pole) VALUES (23, 'MEDECINE DU SPORT ET DE L''EFFORT', 8);

-- Table Promoteur
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (1, 'AMGEN', '', 'INDUSTRIEL');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (2, 'ANRS', '', 'ACADEMIQUE');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (3, 'AP-HP', '', 'ACADEMIQUE');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (4, 'ASTRAZENECA', '', 'INDUSTRIEL');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (5, 'BAYER Santé', '', 'INDUSTRIEL');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (6, 'BIOGEN IDEC', '', 'INDUSTRIEL');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (7, 'BMS', '', 'INDUSTRIEL');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (8, 'BOEHRINGER INGELHEIM', '', 'INDUSTRIEL');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (9, 'CELGENE', '', 'INDUSTRIEL');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (10, 'CH LENS', '', 'ETABLISSEMENT_SOINS');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (11, 'CH VERSAILLES', '', 'ETABLISSEMENT_SOINS');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (12, 'CHU ANGERS', '', 'ETABLISSEMENT_SOINS');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (13, 'CHU BESANCON', '', 'ETABLISSEMENT_SOINS');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (14, 'CHU BORDEAUX', '', 'ETABLISSEMENT_SOINS');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (15, 'CHU BREST', '', 'ETABLISSEMENT_SOINS');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (16, 'CHU LILLE', '', 'ETABLISSEMENT_SOINS');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (17, 'CHU MONTPELLIER', '', 'ETABLISSEMENT_SOINS');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (18, 'CHU NANTES', '', 'ETABLISSEMENT_SOINS');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (19, 'CHU NICE', '', 'ETABLISSEMENT_SOINS');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (20, 'CHU RENNES', '', 'ETABLISSEMENT_SOINS');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (21, 'CHU ROUEN', '', 'ETABLISSEMENT_SOINS');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (22, 'CHU SAINT-ETIENNE', '', 'ETABLISSEMENT_SOINS');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (23, 'CHU STRASBOURG', '', 'ETABLISSEMENT_SOINS');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (24, 'CHU TOULOUSE', '', 'ETABLISSEMENT_SOINS');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (25, 'CNRS', '', 'ACADEMIQUE');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (26, 'ERYTECH Pharma', '', 'INDUSTRIEL');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (27, 'EUSA Pharma', '', 'INDUSTRIEL');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (28, 'GALDERMA', '', 'INDUSTRIEL');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (29, 'GENENTECH', '', 'INDUSTRIEL');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (30, 'GENFIT', '', 'INDUSTRIEL');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (31, 'GERCOR', '', 'INDUSTRIEL');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (32, 'GETAID', '', 'ACADEMIQUE');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (33, 'GFM Groupe Français des Myelodysplasies', '', 'ACADEMIQUE');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (34, 'GILEAD', '', 'INDUSTRIEL');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (35, 'GOELAMS', '', 'ACADEMIQUE');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (36, 'GSK', '', 'INDUSTRIEL');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (37, 'HOSPICES CIVILS DE LYON', '', 'ACADEMIQUE');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (38, 'IFM', '', 'ACADEMIQUE');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (39, 'INRA', '', 'ACADEMIQUE');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (40, 'INSTITUT CURIE', '', 'ACADEMIQUE');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (41, 'INSTITUT GUSTAVE ROUSSY', '', 'ACADEMIQUE');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (42, 'INSTITUT PAOLI CALMETTE', '', 'ACADEMIQUE');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (43, 'IPSEN', '', 'INDUSTRIEL');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (44, 'IRIS SERVIER', '', 'INDUSTRIEL');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (45, 'JOHNSON&JOHNSON  JANSSEN-CILAG', '', 'INDUSTRIEL');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (46, 'LA ROCHE POSAY', '', 'INDUSTRIEL');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (47, 'LACTALIS', '', 'INDUSTRIEL');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (48, 'LILLY', '', 'INDUSTRIEL');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (49, 'MAUNA KEA TECHNOLOGIES', '', 'INDUSTRIEL');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (50, 'MERCK SERONO', '', 'INDUSTRIEL');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (51, 'MILLENNIUM PHARMACEUTICALS', '', 'INDUSTRIEL');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (52, 'MSD', '', 'INDUSTRIEL');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (53, 'MUNDIPHARMA', '', 'INDUSTRIEL');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (54, 'NOVARTIS', '', 'INDUSTRIEL');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (55, 'NOVIMMUNE', '', 'INDUSTRIEL');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (56, 'NOVO NORDISK', '', 'INDUSTRIEL');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (57, 'PFIZER', '', 'INDUSTRIEL');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (58, 'PIERRE FABRE', '', 'INDUSTRIEL');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (59, 'PTC Therapeutics', '', 'INDUSTRIEL');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (60, 'ROCHE', '', 'INDUSTRIEL');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (61, 'SANOFI AVENTIS', '', 'INDUSTRIEL');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (62, 'SCHERING-PLOUGH', '', 'INDUSTRIEL');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (63, 'SOCIETE FRANCAISE DE CARDIOLOGIE', '', 'ACADEMIQUE');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (64, 'UCB Pharma', '', 'INDUSTRIEL');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (65, 'WYETH', '', 'INDUSTRIEL');
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (66, 'Promoteur 1 (SIGREC)', 'identifiant promoteur 1', 'INDUSTRIEL');

-- Table Personne
INSERT INTO personne(id, type, typePharmacien, login, nom, prenom, telephone, mail, fax, adresse, codePostal, ville, isAdmin, titre, nomSociete, id_promoteur, password) VALUES (1, 'INVESTIGATEUR', null, 'thierry.biais', 'Durand', 'Pierre', '0987656565', 'pierre.durand@eclipse.fr', '0967654345', '', '', '', false, 'Docteur', '', null, null);
INSERT INTO personne(id, type, typePharmacien, login, nom, prenom, telephone, mail, fax, adresse, codePostal, ville, isAdmin, titre, nomSociete, id_promoteur, password) VALUES (2, 'ARC_PROMOTEUR', null, 'jean.dupont', 'Dupont', 'Jean', '0987656565', 'jean.dupont@eclipse.fr', '0967654345', '', '', '', false, null, '', 1, null);
INSERT INTO personne(id, type, typePharmacien, login, nom, prenom, telephone, mail, fax, adresse, codePostal, ville, isAdmin, titre, nomSociete, id_promoteur, password) VALUES (3, 'PHARMACIEN', 'TITULAIRE', 'admin', 'admin', 'admin', '', '', '', '', '', '', true, null, '', null, null);
INSERT INTO personne(id, type, typePharmacien, login, nom, prenom, telephone, mail, fax, adresse, codePostal, ville, isAdmin, titre, nomSociete, id_promoteur, password) VALUES (4, 'ARC_INVESTIGATEUR', null, null, 'Ronald', 'Vincent', '', '', '', '', '', '', false, null, '', null, null);
INSERT INTO personne(id, type, typePharmacien, login, nom, prenom, telephone, mail, fax, adresse, codePostal, ville, isAdmin, titre, nomSociete, id_promoteur, password) VALUES (5, 'CRO', null, null, 'QUINTILES', '', '', '', '', '', '', '', false, null, 'QUINTILES', null, null);
INSERT INTO personne(id, type, typePharmacien, login, nom, prenom, telephone, mail, fax, adresse, codePostal, ville, isAdmin, titre, nomSociete, id_promoteur, password) VALUES (6, 'PROMOTEUR', null, null, 'nom du contact promoteur 1', 'prénom du contact promoteur 1', '', '', '', '', '', '', false, null, '', 1, null);
INSERT INTO personne(id, type, typePharmacien, login, nom, prenom, telephone, mail, fax, adresse, codePostal, ville, isAdmin, titre, nomSociete, id_promoteur, password) VALUES (7, 'CRO', null, null, 'QUINTILES 2', '', '', '', '', '', '', '', false, null, 'QUINTILES 2', null, null);
INSERT INTO personne(id, type, typePharmacien, login, nom, prenom, telephone, mail, fax, adresse, codePostal, ville, isAdmin, titre, nomSociete, id_promoteur, password) VALUES (8, 'CRO', null, null, 'QUINTILES 3', '', '', '', '', '', '', '', false, null, 'QUINTILES 3', null, null);
INSERT INTO personne(id, type, typePharmacien, login, nom, prenom, telephone, mail, fax, adresse, codePostal, ville, isAdmin, titre, nomSociete, id_promoteur, password) VALUES (9, 'PROMOTEUR', null, null, 'nom du contact promoteur 2', 'prénom du contact promoteur 2', '', '', '', '', '', '', false, null, '', 1, null);
INSERT INTO personne(id, type, typePharmacien, login, nom, prenom, telephone, mail, fax, adresse, codePostal, ville, isAdmin, titre, nomSociete, id_promoteur, password) VALUES (10, 'PHARMACIEN', 'TITULAIRE', 'pharmacien', 'pharmacien', 'pharmacien', '', '', '', '', '', '', false, null, '', null, null);

-- Table Pharmacie
INSERT INTO pharmacie(id, nom, adresse, adresseLivraison, telephone, fax, responsablePrincipal, numOrdonnancierDisp, numOrdonnancierFab, id_etablissement) VALUES (1, 'PUI HOTEL-DIEU RDJ', '', '', '', '', '', 0, 0, 1);
INSERT INTO pharmacie(id, nom, adresse, adresseLivraison, telephone, fax, responsablePrincipal, numOrdonnancierDisp, numOrdonnancierFab, id_etablissement) VALUES (2, 'PUI HOTEL-DIEU UPCO', '', '', '', '', '', 0, 0, 1);
INSERT INTO pharmacie(id, nom, adresse, adresseLivraison, telephone, fax, responsablePrincipal, numOrdonnancierDisp, numOrdonnancierFab, id_etablissement) VALUES (3, 'PUI HGRL', '', '', '', '', '', 0, 0, 1);
INSERT INTO pharmacie(id, nom, adresse, adresseLivraison, telephone, fax, responsablePrincipal, numOrdonnancierDisp, numOrdonnancierFab, id_etablissement) VALUES (4, 'PUI St JACQUES', '', '', '', '', '', 0, 0, 1);
INSERT INTO pharmacie(id, nom, adresse, adresseLivraison, telephone, fax, responsablePrincipal, numOrdonnancierDisp, numOrdonnancierFab, id_etablissement) VALUES (5, 'ARSENAL St JACQUES', '', '', '', '', '', 0, 0, 1);

-- Table Pharmacie-Site
INSERT INTO pharmacie_site(id_pharmacie, id_site) VALUES (1, 1);
INSERT INTO pharmacie_site(id_pharmacie, id_site) VALUES (2, 1);
INSERT INTO pharmacie_site(id_pharmacie, id_site) VALUES (3, 2);
INSERT INTO pharmacie_site(id_pharmacie, id_site) VALUES (4, 3);
INSERT INTO pharmacie_site(id_pharmacie, id_site) VALUES (5, 3);

-- Table Pharmacien-Pharmacie
INSERT INTO pharmacien_pharmacie(id_pharmacien, id_pharmacie) VALUES (3,1);

-- Table Stockage
INSERT INTO stockage(id, nom, identifiantStockage, conservation, identifiantSondeTemp, identifiantEnregistreurTemp, id_stockage_parent, id_pharmacie) VALUES (1, 'Armoire A', null, null, null, null, null, 1);
INSERT INTO stockage(id, nom, identifiantStockage, conservation, identifiantSondeTemp, identifiantEnregistreurTemp, id_stockage_parent, id_pharmacie) VALUES (2, 'Armoire A1', null, null, null, null, 1, 1);
INSERT INTO stockage(id, nom, identifiantStockage, conservation, identifiantSondeTemp, identifiantEnregistreurTemp, id_stockage_parent, id_pharmacie) VALUES (3, 'Armoire A2', null, null, null, null, 1, 1);
INSERT INTO stockage(id, nom, identifiantStockage, conservation, identifiantSondeTemp, identifiantEnregistreurTemp, id_stockage_parent, id_pharmacie) VALUES (4, 'Armoire A21', null, null, null, null, 3, 1);
INSERT INTO stockage(id, nom, identifiantStockage, conservation, identifiantSondeTemp, identifiantEnregistreurTemp, id_stockage_parent, id_pharmacie) VALUES (5, 'Armoire A22', null, null, null, null, 3, 1);
INSERT INTO stockage(id, nom, identifiantStockage, conservation, identifiantSondeTemp, identifiantEnregistreurTemp, id_stockage_parent, id_pharmacie) VALUES (6, 'Armoire B', null, null, null, null, null, 1);
INSERT INTO stockage(id, nom, identifiantStockage, conservation, identifiantSondeTemp, identifiantEnregistreurTemp, id_stockage_parent, id_pharmacie) VALUES (7, 'Armoire B1', null, null, null, null, 6, 1);

-- Table Essai
INSERT INTO essai(id, version, numInterne, codePromoteur, nom, dci, libelleProduitEvalue, etat, typePromoteur, emplacementDossier, anneeCreation, id_promoteur, id_pharma, alerteActive) VALUES (1, 0, '2010-01', 'P01-C', 'Essai 1', 'n/a', 'n/a', 'EN_EVALUATION', 'ACADEMIQUE','Armoire du fond', 2010, 1, 1, true);
INSERT INTO essai(id, version, numInterne, codePromoteur, nom, dci, libelleProduitEvalue, etat, typePromoteur, emplacementDossier, anneeCreation, id_promoteur, id_pharma, alerteActive) VALUES (2, 0, '2010-02', 'P01-C', 'Essai 2', 'n/a', 'n/a', 'EN_COURS', 'ACADEMIQUE','Meuble de devant', 2010, 10, 2, true);

-- Table DetailRecherche
INSERT INTO essai_detail_recherche(id, version, id_essai, titreProtocole, numEnregistrement, typeRecherche, objetRecherche, phaseRecherche, natureRecherche, thematique, motsCles) VALUES(1, 0, 1, 'Mon premier protocole.', 'SIGREC-001', 'OBSERVATIONNELLE', 'IMAGERIE', 'I', 'PHARMACOLOGIE', 'UROLOGIE', 'motClé1, motClé2, motClé3');
INSERT INTO essai_detail_recherche(id, version, id_essai, titreProtocole, numEnregistrement, typeRecherche, objetRecherche, phaseRecherche, natureRecherche, thematique, motsCles) VALUES(2, 0, 2, 'Mon deuxième protocole.', 'SIGREC-001', 'OBSERVATIONNELLE', 'IMAGERIE', 'III', 'PHARMACOLOGIE', 'UROLOGIE', 'motClé2');

-- Table Detail Surcout
INSERT INTO essai_detail_surcout(id, id_essai, version) VALUES(1, 1, 0);
INSERT INTO essai_detail_surcout(id, id_essai, version) VALUES(2, 2, 0);

-- Table donnees_prevision
INSERT INTO donnees_prevision(id, id_detail_surcout, version) VALUES (1,1,0);
INSERT INTO donnees_prevision(id, id_detail_surcout, version) VALUES (2,2,0);


-- Table CommentaireEssaiRecherche
INSERT INTO essai_commentaire_detail_recherche(id, dateMaj, majPar, libelle, id_detailRecherche, version) VALUES (1, '2010-10-01', 'SRM', 'Commentaire n°1.', 1, 0);
INSERT INTO essai_commentaire_detail_recherche(id, dateMaj, majPar, libelle, id_detailRecherche, version) VALUES (2, '2010-11-01', 'SRM', 'Commentaire n°2.', 1, 0);
INSERT INTO essai_commentaire_detail_recherche(id, dateMaj, majPar, libelle, id_detailRecherche, version) VALUES (3, '2010-01-01', 'SRM', 'Commentaire création.', 2, 0);

-- Table DetailContacts
INSERT INTO essai_detail_contacts(id, id_essai, version) VALUES(1,1,0);
INSERT INTO essai_detail_contacts(id, id_essai, version) VALUES(2,2,0);

-- Table Habilitation
INSERT INTO habilitation(id, id_detail_contacts, id_personne, droit, active, desactivable, creeLe, creePar) VALUES (1, 1, 3, 'PHARMACIEN_TITULAIRE', true, false, '2010-11-01', 'SRM');
INSERT INTO habilitation(id, id_detail_contacts, id_personne, droit, active, desactivable, creeLe, creePar) VALUES (2, 1, 2, 'ARC_PROMOTEUR', true, true, '2010-12-03', 'SRM');
INSERT INTO habilitation(id, id_detail_contacts, id_personne, droit, active, desactivable, creeLe, creePar) VALUES (3, 1, 5, 'CRO', true, true, '2010-12-03', 'SRM');
INSERT INTO habilitation(id, id_detail_contacts, id_personne, droit, active, desactivable, creeLe, creePar) VALUES (4, 1, 1, 'INVESTIGATEUR_PRINCIPAL', true, true, '2010-12-03', 'SLB');

-- Table DetailFaisabilite
INSERT INTO essai_detail_faisabilite(id, id_essai, version) VALUES (1,1,0);
INSERT INTO essai_detail_faisabilite(id, id_essai, version) VALUES (2,2,0);

-- Table CommentaireEssaiFaisabilite
INSERT INTO essai_commentaire_detail_faisabilite(id, version, type, dateMaj, majPar, libelle, id_detailFaisabilite) VALUES(1, 0, 'FAISABILITE_CONCL', '2010-11-15', 'SRM', 'Mon premier commentaire sur la conclusion.', 1);
INSERT INTO essai_commentaire_detail_faisabilite(id, version, type, dateMaj, majPar, libelle, id_detailFaisabilite) VALUES(2, 0, 'FAISABILITE_CONCL', '2010-11-16', 'SRM', 'Mon deuxieme commentaire sur la conclusion.', 1);
INSERT INTO essai_commentaire_detail_faisabilite(id, version, type, dateMaj, majPar, libelle, id_detailFaisabilite) VALUES(3, 0, 'FAISABILITE_CONCL', '2010-11-10', 'SRM', 'Mon premier commentaire sur la conclusion.', 2);
INSERT INTO essai_commentaire_detail_faisabilite(id, version, type, dateMaj, majPar, libelle, id_detailFaisabilite) VALUES(4, 0, 'FAISABILITE_ACHAT_PROD', '2010-11-10', 'SRM', 'Mon premier commentaire sur les achats produits.', 1);
INSERT INTO essai_commentaire_detail_faisabilite(id, version, type, dateMaj, majPar, libelle, id_detailFaisabilite) VALUES(5, 0, 'FAISABILITE_DISTRIB_PHARMA', '2010-11-10', 'SRM', 'Mon premier commentaire sur les distributions aux autres pharmacies.', 1);
INSERT INTO essai_commentaire_detail_faisabilite(id, version, type, dateMaj, majPar, libelle, id_detailFaisabilite) VALUES(6, 0, 'FAISABILITE_ETUDE', '2010-11-10', 'SRM', 'Mon premier commentaire global sur l étude de faisabilité.', 1);

-- Table DetailDates
INSERT INTO essai_detail_dates(id, id_essai, debutEtudePrev, finEtudePrev, version) VALUES(1, 1, '2010-01-01', '2011-01-31', 0);
INSERT INTO essai_detail_dates(id, id_essai, debutEtudePrev, finEtudePrev, version) VALUES(2, 2, '2010-11-01', '2011-06-30', 0);

-- Table DetailAdministratif
INSERT INTO essai_detail_administratif(id, version, id_essai, ac_nom, ac_docsDossPapier, assur_numero_contrat, cpp_nom, cpp_docsDossPapier, conv_signee, conv_docsDossPapier, assur_docsDossPapier, proto_docsDossPapier, bropro_docsDossPapier, autorisationDistribution_docsDossPapier, autorisationImportation_docsDossPapier, arc_ident) VALUES(1, 0, 1, '', true, '', '', false, false, false, false, true, false, false, false, '');
INSERT INTO essai_detail_administratif(id, version, id_essai, ac_nom, ac_docsDossPapier, assur_numero_contrat, cpp_nom, cpp_docsDossPapier, conv_signee, conv_docsDossPapier, assur_docsDossPapier, proto_docsDossPapier, bropro_docsDossPapier, autorisationDistribution_docsDossPapier, autorisationImportation_docsDossPapier, arc_ident) VALUES(2, 0, 2, '', false, '', '', true, true, false, true, false, true,  false, false,'');

-- Table DocumentsAdministratifs

-- Table CommentaireEssaiArchivage
INSERT INTO essai_commentaire_detail_administratif_archi(id, dateMaj, majPar, libelle, id_detailAdministratif, version) VALUES (1, '2010-11-01', 'SRM', 'Commentaire archivage n°1.', 1, 0);

-- Table DetailAutresDocuments
INSERT INTO essai_detail_autres_documents(id, id_essai, version) VALUES(1,1,0);
INSERT INTO essai_detail_autres_documents(id, id_essai, version) VALUES(2,2,0);

-- Table Essai-Service
INSERT INTO essai_service(id_essai, id_service) VALUES(1,1);

-- Table DetailProduit
INSERT INTO essai_detail_produit(id, id_essai, version) VALUES(1, 1, 0);
INSERT INTO essai_detail_produit(id, id_essai, version) VALUES(2, 2, 0);

-- Table Produit
INSERT INTO produit(id, denomination, code, classeTherapeutique, type, alerteActive, id_detailProduit) VALUES (1, 'Produit 1', 'prod1', 'classe 2', 'MEDICAMENT', true, 1);

-- Table DetailLogistique
INSERT INTO produit_detail_logistique(id, id_produit) VALUES (1, 1);

-- Table Medicament
INSERT INTO medicament(id, stupefiant, mds, dci) VALUES (1, true, false, 'paracetamol');

-- Table Conditionnement
INSERT INTO conditionnement(id, libelle, modePrescription, uniteGestion, unitePrescription, voieAdministration, dosage, nbUnitePrescription, uniteDosage, quantiteParPatient, id_produit) VALUES (1, 'conditionnement num traitement', 'NUM_TRAITEMENT', null, null, null, null, 2, 'MILLIGRAMME', null, 1);
INSERT INTO conditionnement(id, libelle, modePrescription, uniteGestion, unitePrescription, voieAdministration, dosage, nbUnitePrescription, uniteDosage, quantiteParPatient, id_produit) VALUES (2, 'conditionnement dose', 'DOSE', null, null, null, null, 2, 'MILLILITRE', null, 1);

-- Table ProduitDetailStockage
INSERT INTO produit_detail_stockage(id, id_pharmacie, id_stockage, identifiantStockage, id_detail_logistique, type) VALUES (1, 1, 1, 'id stock', 1, 'STOCK');

INSERT INTO essai_detail_pharma(id, dureeTotalePrevue, modaliteReception, conseilPatient, typedispensation, id_essai, version) VALUES(1, 1, 'aucune', 'aucun','NOMINATIVE', 1, 0);
INSERT INTO essai_detail_pharma(id, dureeTotalePrevue, modaliteReception, conseilPatient, typedispensation, id_essai, version) VALUES(2, 1, 'aucune', 'aucun','GLOBALE', 2, 0);

-- Table Essai detail pharma-Pharmacie
INSERT INTO essai_detail_pharma_pharmacie(id_detail_pharma, id_pharmacie) VALUES (1, 2);
INSERT INTO essai_detail_pharma_pharmacie(id_detail_pharma, id_pharmacie) VALUES (2, 3);

-- Table DetailDesign
INSERT INTO essai_detail_design(id, id_essai, version) VALUES (1,1,0);
INSERT INTO essai_detail_design(id, id_essai, version) VALUES (2,2,0);

INSERT INTO bras(id, id_detail_design, type, nom) VALUES (1, 1, 'BRAS', 'bras n°1');
INSERT INTO bras(id, id_detail_design, type, nom, id_bras_parent) VALUES (3, 1, 'BRAS', 'Sous-bras 1-1', 1);
INSERT INTO bras(id, id_detail_design, type, nom, id_bras_parent) VALUES (4, 1, 'BRAS', 'Sous-bras 1-2', 1);

INSERT INTO sequence(id, id_bras_sequence, type, nom) VALUES (1, 4, 'SEQUENCE', 'sequence 1');

INSERT INTO ordonnancier_dispensation(id, datemaj, majpar, datedebut, datefin, id_pharma, version) VALUES (1, now(), 'netapsys', '2012-01-01', '2012-12-31', 1, 0);

INSERT INTO patient(id, nom, prenom, numeroipp) VALUES (1, 'Dupond', 'Marcel', 'ipp1');
INSERT INTO patient(id, nom, prenom, numeroipp) VALUES (2, 'Durand', 'Jean', 'ipp2');

-- Table Dotation
INSERT INTO dotation(id, commentaire, datedemande, quantite, traitee, id_conditionnement, id_essai, id_personne, id_pharmacie, id_produit, id_service) VALUES (1, null, '2011-01-01', 2, false, 1, 1, 1, 1, 1, 1);

-- Table Inclusion
INSERT INTO inclusion(id, actif, numinclusion, dateInclusion, numrandomisation, id_essai, id_patient) VALUES (1, true, 0001, '2010-01-01', 00001, 1, 1);

-- Table Prescription
INSERT INTO prescription(id, datedebuttraitement, dateprescription, dispense, numprescription, id_inclusion, id_investigateur, id_sequence, id_service) VALUES (1, '2010-01-01', '2010-01-01', false, 1, 1, 1, 1, 1);

INSERT INTO prescription_type(id, nb_debut, unite_debut, description, nb_duree, unite_duree, nbfrequence, nbunitetempsfrequence, typeregularite, unitefrequence, nbunitedosage, id_conditionnement, id_produit, id_sequence) VALUES (1, 3, 'JOUR', null, 2, 'SEMAINE', 0, null, null, null, null,1, 1, 1);

INSERT INTO dispensation(id, datedispensation, dispense, id_prescription, numordonnancier, id_ordonnancier, id_pharmacie, commentaire, version) VALUES (1, null, false, 1, '01', 1, 1, null, 0);

-- Table ProduitPrescrit
INSERT INTO produit_prescrit (id, nb_debut, unite_debut, description, dispense, nb_duree, unite_duree, nbfrequence, nbunitetempsfrequence, typeregularite, unitefrequence, nbunitedosage, numtraitement,id_conditionnement, id_prescription, id_produit) VALUES (1, 0, 'JOUR', null, true, null, null, 0, null, null, null, null, null, 1, 1, 1);

-- Table grilleModele
INSERT INTO grille_modele (id, nom, dateCreation) VALUES (1, 'grille 1', '2010-10-01');

-- Table theme
INSERT INTO theme (id, libelle, id_grille_modele) VALUES (1, 'Prestation standard', 1);
INSERT INTO theme (id, libelle, id_grille_modele) VALUES (2, 'Actes pharmaceutiques supplémentaires', 1);
INSERT INTO theme (id, libelle, id_grille_modele) VALUES (3, 'Frais spécifiques (18)', 1);

-- Table categorie
INSERT INTO categorie (id, libelle, acte, id_theme) VALUES (1, 'Forfait pharmaceutique (7)', 'AUCUN', 1);
INSERT INTO categorie (id, libelle, acte, id_theme) VALUES (2, 'Forfait dispensation nominative (8) par ordonnance : Nouvelle', 'DISPENSATION', 1);
INSERT INTO categorie (id, libelle, acte, id_theme) VALUES (3, 'Forfait dispensation nominative (8) par ordonnance : Renouvellement ou fractionnée', 'AUCUN', 1);
INSERT INTO categorie (id, libelle, acte, id_theme) VALUES (4, 'Destruction (9)', 'DESTRUCTION', 2);
INSERT INTO categorie (id, libelle, acte, id_theme) VALUES (5, 'Reconstitution (10)', 'RECONSTITUTION', 2);
INSERT INTO categorie (id, libelle, acte, id_theme) VALUES (6, 'Conditions particulières de conservation (11)', 'AUCUN', 2);
INSERT INTO categorie (id, libelle, acte, id_theme) VALUES (7, 'Ré-étiquetage (12)', 'REETIQUETAGE', 2);
INSERT INTO categorie (id, libelle, acte, id_theme) VALUES (8, 'Visite supplémentaire de suivi (de monitoring) (13)','AUCUN', 2);
INSERT INTO categorie (id, libelle, acte, id_theme) VALUES (9, 'Réception / Livraison de produits supplémentaires (14) Actes IVRS ou @VRS (14 bis)', 'AUCUN', 2);
INSERT INTO categorie (id, libelle, acte, id_theme) VALUES (10, 'Traçabilité spécifique (15) : MDS et DMI', 'AUCUN', 2);
INSERT INTO categorie (id, libelle, acte, id_theme) VALUES (11, 'Attribution d’un traitement au patient (appel d’un serveur vocal – IVRS) ', 'PRESCRIPTION', 2);
INSERT INTO categorie (id, libelle, acte, id_theme) VALUES (12, 'Audits (16)', 'AUCUN', 2);
INSERT INTO categorie (id, libelle, acte, id_theme) VALUES (13, 'Autres (17)', 'AUCUN', 2);

-- Table acl_essai
INSERT INTO acl_essai(id_essai, id_personne) VALUES (1, 3);
INSERT INTO acl_essai(id_essai, id_personne) VALUES (2, 3);

-- Table acl_pharmacie
INSERT INTO acl_pharmacie(id_pharmacie, id_personne) VALUES (1, 3);
INSERT INTO acl_pharmacie(id_pharmacie, id_personne) VALUES (2, 3);
INSERT INTO acl_pharmacie(id_pharmacie, id_personne) VALUES (3, 3);
INSERT INTO acl_pharmacie(id_pharmacie, id_personne) VALUES (4, 3);
INSERT INTO acl_pharmacie(id_pharmacie, id_personne) VALUES (5, 3);

------------------------
-------- SIGREC --------
------------------------

-- Table contact_sigrec
INSERT INTO contact_sigrec (id, adresse, civilite, codepostal, email, fax, nom, prenom, raisonsociale, telephone, ville) VALUES (1, 'adresse', NULL, 'code postal', 'email', 'fax', NULL, NULL, 'Promoteur 1 (Sigrec)', 'telephone', 'ville');
INSERT INTO contact_sigrec (id, adresse, civilite, codepostal, email, fax, nom, prenom, raisonsociale, telephone, ville) VALUES (2, NULL, 'MR', NULL, NULL, NULL, 'nom : Arc promoteur', 'prenom', NULL, NULL, NULL);
INSERT INTO contact_sigrec (id, adresse, civilite, codepostal, email, fax, nom, prenom, raisonsociale, telephone, ville) VALUES (3, 'Adresse centre 1 ', NULL, '44000', 'mail centre 1', 'fax ', NULL, NULL, 'organisation', 'telephone', 'Nantes');
INSERT INTO contact_sigrec (id, adresse, civilite, codepostal, email, fax, nom, prenom, raisonsociale, telephone, ville) VALUES (4, NULL, 'MR', NULL, NULL, NULL, 'nom investigateur ARC', 'prenom investigateur ARC', NULL, NULL, NULL);
INSERT INTO contact_sigrec (id, adresse, civilite, codepostal, email, fax, nom, prenom, raisonsociale, telephone, ville) VALUES (5, NULL, 'MME', NULL, NULL, NULL, 'nom investigateur ASSO', 'prenom investigateur ASSO', NULL, NULL, NULL);
INSERT INTO contact_sigrec (id, adresse, civilite, codepostal, email, fax, nom, prenom, raisonsociale, telephone, ville) VALUES (6, NULL, 'MLLE', NULL, NULL, NULL, 'nom investigateur co', 'prenom investigateur co', NULL, NULL, NULL);
INSERT INTO contact_sigrec (id, adresse, civilite, codepostal, email, fax, nom, prenom, raisonsociale, telephone, ville) VALUES (7, NULL, 'MLLE', NULL, NULL, NULL, 'nom investigateur principal', 'prenom investigateur principal', NULL, NULL, NULL);
INSERT INTO contact_sigrec (id, adresse, civilite, codepostal, email, fax, nom, prenom, raisonsociale, telephone, ville) VALUES (8, 'adresse assurance', NULL, '44100', NULL, NULL, NULL, NULL, 'nom assurance', NULL, 'Nantes');
INSERT INTO contact_sigrec (id, adresse, civilite, codepostal, email, fax, nom, prenom, raisonsociale, telephone, ville) VALUES (9, 'adresse assurance 2', NULL, '44100', NULL, NULL, NULL, NULL, 'nom assurance 2', NULL, 'Nantes');
INSERT INTO contact_sigrec (id, adresse, civilite, codepostal, email, fax, nom, prenom, raisonsociale, telephone, ville) VALUES (10, 'Adresse 1 cro', NULL, '44100', NULL, NULL, 'Nom cro 1', NULL, 'Nom cro 1', NULL, 'Nantes');
INSERT INTO contact_sigrec (id, adresse, civilite, codepostal, email, fax, nom, prenom, raisonsociale, telephone, ville) VALUES (11, 'Adresse 2 cro', NULL, '44100', NULL, NULL, 'Nom cro 2', NULL, 'Nom cro 2', NULL, 'Nantes');

-- Table promoteur_sigrec
INSERT INTO promoteur_sigrec (id, identifiant, type, id_contact) VALUES (1, 'identifiant promoteur 1', 'ACADEMIQUE', 1);

-- Table centre_sigrec
INSERT INTO centre_sigrec (id, idcentre, nom, numero, numerofiness, id_contact) VALUES (1, NULL, 'nom centre', 'nombre 1', 'numero finess', 3);

-- Table arc_promoteur_sigrec 
INSERT INTO arc_promoteur_sigrec (id, identifiant, intervenantid, numadeli, titre, id_contact, id_promoteur) VALUES (1, 'idArcPromoteur', 2, 'numadeli', 'titre', 2, 1);

-- Table investigateur_sigrec
INSERT INTO investigateur_sigrec (id, identifiant, intervenantid, numadeli, titre, id_contact, id_centre) VALUES (1, '101', 10, 'numero adeli investigateur principal', 'titre principal', 7, 1);

-- Table essai_sigrec
INSERT INTO essai_sigrec (id, codepromoteur, multicentrique, nbcentres, nom, numidentac, typepromoteur, id_investigateurprincipal, id_promoteur) VALUES (1, 'num_interne/codePromoteur', NULL, 10, 'Titre abrege', 'EDRACT number', 'ACADEMIQUE', 1, 1);

-- Table prevision_sigrec
INSERT INTO prevision_sigrec (id, datedebut, datefin, dureetotale, nbcentres, id_essai) VALUES (1, '2010-10-08 00:00:00', '2010-10-10 00:00:00', 10, 10, 1);

-- Table detail_recherche_sigrec
INSERT INTO essai_detail_recherche_sigrec (id, naturerecherche, numenregistrement, objetrecherche, phaserecherche, qualiteinsu, titreprotocole, typerecherche, id_essai) VALUES (1, 'DEPISTAGE', 'num_sigrec', 'IMAGERIE', 'IIa', 'ESSAI_DOUBLE_AVEUGLE', 'Titre complet', 'OBSERVATIONNELLE', 1);

-- Table cro_sigrec
INSERT INTO cro_sigrec (id, identifiant, id_contact, id_essai) VALUES (1, '1', 10, 1);
INSERT INTO cro_sigrec (id, identifiant, id_contact, id_essai) VALUES (2, '2', 11, 1);

-- Table co_investigateur_sigrec
INSERT INTO co_investigateur_sigrec (id, identifiant, intervenantid, numadeli, titre, id_contact, id_centre, id_essai) VALUES (1, '103', 12, 'numero adeli investigateur ASSO', 'titre ASSO', 5, 1, 1);
INSERT INTO co_investigateur_sigrec (id, identifiant, intervenantid, numadeli, titre, id_contact, id_centre, id_essai) VALUES (2, '102', 11, 'numero adeli investigateur co', 'titre co', 6, 1, 1);

-- Table assurance_sigrec
INSERT INTO assurance_sigrec (id, datedebutvalidite, datefinvalidite, numerocontrat, id_contact, id_essai) VALUES (1, '2010-02-02 00:00:00', '2010-03-02 00:00:00', 'numero contrat assurance', 8, 1);
INSERT INTO assurance_sigrec (id, datedebutvalidite, datefinvalidite, numerocontrat, id_contact, id_essai) VALUES (2, '2010-02-02 00:00:00', '2010-03-02 00:00:00', 'numero contrat assurance2 2', 9, 1);

-- table arc_investigateur_sigrec
INSERT INTO arc_investigateur_sigrec (id, identifiant, intervenantid, numadeli, titre, id_contact, id_centre, id_essai) VALUES (1, '104', 10, 'numero adeli investigateur ARC', 'titre ARC', 4, 1, 1);


