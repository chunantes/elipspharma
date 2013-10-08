

-- Initialisation des valeurs de séquences : démarrage à 100000 sur toutes les séquences
ALTER SEQUENCE arc_investigateur_sigrec_id_seq RESTART WITH 100000;
ALTER SEQUENCE arc_promoteur_sigrec_id_seq RESTART WITH 100000;
ALTER SEQUENCE assurance_sigrec_id_seq RESTART WITH 100000;
ALTER SEQUENCE centre_sigrec_id_seq RESTART WITH 100000;
ALTER SEQUENCE co_investigateur_sigrec_id_seq RESTART WITH 100000;
ALTER SEQUENCE contact_sigrec_id_seq RESTART WITH 100000;
ALTER SEQUENCE cro_sigrec_id_seq RESTART WITH 100000;
ALTER SEQUENCE pharmacie_id_seq RESTART WITH 100000;
ALTER SEQUENCE pharmacie_suivi_id_seq RESTART WITH 100000;
ALTER SEQUENCE promoteur_id_seq RESTART WITH 100000;
ALTER SEQUENCE promoteur_sigrec_id_seq RESTART WITH 100000;
ALTER SEQUENCE promoteur_suivi_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_commentaire_detail_recherche_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_commentaire_detail_faisabilite_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_commentaire_detail_administratif_archi_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_document_detail_administratif_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_detail_recherche_suivi_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_detail_recherche_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_detail_contacts_suivi_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_detail_contacts_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_detail_faisabilite_suivi_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_detail_faisabilite_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_detail_administratif_suivi_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_detail_administratif_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_detail_dates_suivi_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_detail_dates_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_detail_produit_suivi_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_detail_produit_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_detail_pharma_suivi_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_detail_pharma_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_detail_design_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_detail_design_suivi_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_detail_surcout_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_document_detail_surcout_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_detail_surcout_suivi_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_document_detail_autres_documents_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_detail_autres_documents_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_detail_autres_documents_suivi_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_document_detail_pharma_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_detail_recherche_sigrec_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_sigrec_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_suivi_id_seq RESTART WITH 100000;
ALTER SEQUENCE investigateur_sigrec_id_seq RESTART WITH 100000;
ALTER SEQUENCE personne_id_seq RESTART WITH 100000;
ALTER SEQUENCE personne_suivi_id_seq RESTART WITH 100000;
ALTER SEQUENCE produit_id_seq RESTART WITH 100000;
ALTER SEQUENCE produit_suivi_id_seq RESTART WITH 100000;
ALTER SEQUENCE produit_detail_logistique_id_seq RESTART WITH 100000;
ALTER SEQUENCE produit_document_actes_pharma_id_seq RESTART WITH 100000;
ALTER SEQUENCE conditionnement_id_seq RESTART WITH 100000;
ALTER SEQUENCE service_id_seq RESTART WITH 100000;
ALTER SEQUENCE service_suivi_id_seq RESTART WITH 100000;
ALTER SEQUENCE prevision_sigrec_id_seq RESTART WITH 100000;
ALTER SEQUENCE pole_suivi_id_seq RESTART WITH 100000;
ALTER SEQUENCE pole_id_seq RESTART WITH 100000;
ALTER SEQUENCE etablissement_suivi_id_seq RESTART WITH 100000;
ALTER SEQUENCE etablissement_id_seq RESTART WITH 100000;
ALTER SEQUENCE site_suivi_id_seq RESTART WITH 100000;
ALTER SEQUENCE site_id_seq RESTART WITH 100000;
ALTER SEQUENCE habilitation_id_seq RESTART WITH 100000;
ALTER SEQUENCE stockage_id_seq RESTART WITH 100000;
ALTER SEQUENCE mvtstock_id_seq RESTART WITH 100000;
ALTER SEQUENCE bras_id_seq RESTART WITH 100000;
ALTER SEQUENCE sequence_id_seq RESTART WITH 100000;
ALTER SEQUENCE prescription_type_id_seq RESTART WITH 100000;
ALTER SEQUENCE patient_suivi_id_seq RESTART WITH 100000;
ALTER SEQUENCE patient_id_seq RESTART WITH 100000;
ALTER SEQUENCE historique_patient_id_seq RESTART WITH 100000;
ALTER SEQUENCE prescription_id_seq RESTART WITH 100000;
ALTER SEQUENCE produit_prescrit_id_seq RESTART WITH 100000;
ALTER SEQUENCE dispensation_id_seq RESTART WITH 100000;
ALTER SEQUENCE mvtstock_document_id_seq RESTART WITH 100000;
ALTER SEQUENCE produit_detail_stockage_id_seq RESTART WITH 100000;
ALTER SEQUENCE dotation_id_seq RESTART WITH 100000;
ALTER SEQUENCE element_to_check_id_seq RESTART WITH 100000;
ALTER SEQUENCE evenement_id_seq RESTART WITH 100000;
ALTER SEQUENCE evenement_suivi_id_seq RESTART WITH 100000;
ALTER SEQUENCE inclusion_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_detail_etat_id_seq RESTART WITH 100000;
ALTER SEQUENCE categorie_id_seq RESTART WITH 100000;
ALTER SEQUENCE theme_id_seq RESTART WITH 100000;
ALTER SEQUENCE grille_modele_id_seq RESTART WITH 100000;
ALTER SEQUENCE grille_id_seq RESTART WITH 100000;
ALTER SEQUENCE item_id_seq RESTART WITH 100000;
ALTER SEQUENCE donnees_prevision_id_seq RESTART WITH 100000;
ALTER SEQUENCE regle_surcout_id_seq RESTART WITH 100000;
ALTER SEQUENCE ordonnancier_dispensation_id_seq RESTART WITH 100000;
ALTER SEQUENCE ordonnancier_fab_reconst_id_seq RESTART WITH 100000;
ALTER SEQUENCE incident_id_seq RESTART WITH 100000;
ALTER SEQUENCE incident_suivi_id_seq RESTART WITH 100000;
ALTER SEQUENCE retour_patient_id_seq RESTART WITH 100000;


-- Table Etablissement
INSERT INTO etablissement(id, nom, telephone, fax, mail, adresseDirection, codePostal, ville, pays) VALUES (1, 'Etablissement 1', '', '', '', '', '', '', '');

-- Table Site
INSERT INTO site(id, code, nom, adresse, codePostal, ville, id_etablissement) VALUES (1, 'Site 1', 'Site 1', '', '', '', 1);

-- Table Pole
INSERT INTO pole(id, nom, id_etablissement) VALUES (1, 'Pole 1', 1);

-- Table Service
INSERT INTO service(id, nom, id_pole) VALUES (1, 'Service 1', 1);

-- Table Promoteur
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (1, 'Promoteur 1', '', 'INDUSTRIEL');

-- Table Pharmacie
INSERT INTO pharmacie(id, nom, adresse, adresseLivraison, telephone, fax, responsablePrincipal, numOrdonnancierDisp, numOrdonnancierFab, id_etablissement) VALUES (1, 'Pharmacie 1', '', '', '', '', '', 0, 0, 1);

-- Table Pharmacie-Site
INSERT INTO pharmacie_site(id_pharmacie, id_site) VALUES (1, 1);

-- Table Personne
INSERT INTO personne(id, type, typePharmacien, login, nom, prenom, telephone, mail, fax, adresse, codePostal, ville, isAdmin, titre, nomSociete, id_promoteur, password) VALUES (1, 'PHARMACIEN', 'TITULAIRE', 'admin', 'admin', 'admin', '', '', '', '', '', '', true, null, '', null, 'ea617e2de44cac984883b76bd81092b6');
INSERT INTO personne(id, type, typePharmacien, login, nom, prenom, telephone, mail, fax, adresse, codePostal, ville, isAdmin, titre, nomSociete, id_promoteur, password) VALUES (2, 'PHARMACIEN', 'TITULAIRE', 'pharma1', 'Pharmacien 1', 'Pharmacien 1', '', '', '', '', '', '', true, null, '', null, 'ea617e2de44cac984883b76bd81092b6');
INSERT INTO personne(id, type, typePharmacien, login, nom, prenom, telephone, mail, fax, adresse, codePostal, ville, isAdmin, titre, nomSociete, id_promoteur, password) VALUES (3, 'PHARMACIEN', 'TITULAIRE', 'pharma2', 'Pharmacien 2', 'Pharmacien 2', '', '', '', '', '', '', true, null, '', null, 'ea617e2de44cac984883b76bd81092b6');