-- Table: lignestock

-- DROP TABLE lignestock;

CREATE TABLE lignestock
(
  id bigserial NOT NULL,
  approapprouve boolean,
  dateperemption timestamp without time zone,
  numlot character varying(255),
  numtraitement character varying(255),
  quantite_dispensation_en_stock integer NOT NULL,
  quantite_global integer NOT NULL,
  stockage character varying(255),
  "version" bigint,
  id_conditionnement bigint NOT NULL,
  id_essai bigint NOT NULL,
  id_pharmacie bigint NOT NULL,
  id_produit bigint NOT NULL,
  CONSTRAINT lignestock_pkey PRIMARY KEY (id),
  CONSTRAINT fk187831f539fd10dc FOREIGN KEY (id_essai)
      REFERENCES essai (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk187831f54de40194 FOREIGN KEY (id_pharmacie)
      REFERENCES pharmacie (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk187831f59d7535de FOREIGN KEY (id_conditionnement)
      REFERENCES conditionnement (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk187831f5a1ddf650 FOREIGN KEY (id_produit)
      REFERENCES produit (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (OIDS=FALSE);
ALTER TABLE lignestock OWNER TO eclipse;

-- Index: idx_lignestock_conditionnement

-- DROP INDEX idx_lignestock_conditionnement;

CREATE INDEX idx_lignestock_conditionnement
  ON lignestock
  USING btree
  (id_conditionnement);

-- Index: idx_lignestock_essai

-- DROP INDEX idx_lignestock_essai;

CREATE INDEX idx_lignestock_essai
  ON lignestock
  USING btree
  (id_essai);

-- Index: idx_lignestock_pharmacie

-- DROP INDEX idx_lignestock_pharmacie;

CREATE INDEX idx_lignestock_pharmacie
  ON lignestock
  USING btree
  (id_pharmacie);

-- Index: idx_lignestock_produit

-- DROP INDEX idx_lignestock_produit;

CREATE INDEX idx_lignestock_produit
  ON lignestock
  USING btree
  (id_produit);



-- Ajout n° version hibernate
alter table arc_investigateur_sigrec add column version int8 default 0;
alter table arc_promoteur_sigrec add column version int8 default 0;
alter table assurance_sigrec add column version int8 default 0;
alter table bras add column version int8 default 0;
alter table categorie add column version int8 default 0;
alter table centre_sigrec add column version int8 default 0;
alter table co_investigateur_sigrec add column version int8 default 0;
alter table conditionnement add column version int8 default 0;
alter table contact_sigrec add column version int8 default 0;
alter table cro_sigrec add column version int8 default 0;
alter table dispensation add column version int8 default 0;
alter table donnees_prevision add column version int8 default 0;
alter table dotation add column version int8 default 0;
alter table element_to_check add column version int8 default 0;
alter table essai add column version int8 default 0;
alter table essai_commentaire_detail_administratif_archi add column version int8 default 0;
alter table essai_commentaire_detail_faisabilite add column version int8 default 0;
alter table essai_commentaire_detail_recherche add column version int8 default 0;
alter table essai_detail_administratif add column version int8 default 0;
alter table essai_detail_administratif_suivi add column version int8 default 0;
alter table essai_detail_autres_documents add column version int8 default 0;
alter table essai_detail_autres_documents_suivi add column version int8 default 0;
alter table essai_detail_contacts add column version int8 default 0;
alter table essai_detail_contacts_suivi add column version int8 default 0;
alter table essai_detail_dates add column version int8 default 0;
alter table essai_detail_dates_suivi add column version int8 default 0;
alter table essai_detail_design add column version int8 default 0;
alter table essai_detail_design_suivi add column version int8 default 0;
alter table essai_detail_etat add column version int8 default 0;
alter table essai_detail_faisabilite add column version int8 default 0;
alter table essai_detail_faisabilite_suivi add column version int8 default 0;
alter table essai_detail_pharma add column version int8 default 0;
alter table essai_detail_pharma_suivi add column version int8 default 0;
alter table essai_detail_produit add column version int8 default 0;
alter table essai_detail_produit_suivi add column version int8 default 0;
alter table essai_detail_recherche add column version int8 default 0;
alter table essai_detail_recherche_sigrec add column version int8 default 0;
alter table essai_detail_recherche_suivi add column version int8 default 0;
alter table essai_detail_surcout add column version int8 default 0;
alter table essai_detail_surcout_suivi add column version int8 default 0;
alter table essai_document_detail_administratif rename "version" to version_doc;
alter table essai_document_detail_administratif add column version int8 default 0;
alter table essai_document_detail_autres_documents add column version int8 default 0;
alter table essai_document_detail_pharma add column version int8 default 0;
alter table essai_document_detail_surcout add column version int8 default 0;
alter table essai_sigrec add column version int8 default 0;
alter table essai_suivi add column version int8 default 0;
alter table etablissement add column version int8 default 0;
alter table etablissement_suivi add column version int8 default 0;
alter table evenement add column version int8 default 0;
alter table evenement_suivi add column version int8 default 0;
alter table grille add column version int8 default 0;
alter table grille_modele add column version int8 default 0;
alter table habilitation add column version int8 default 0;
alter table historique_patient add column version int8 default 0;
alter table incident add column version int8 default 0;
alter table incident_suivi add column version int8 default 0;
alter table inclusion add column version int8 default 0;
alter table investigateur_sigrec add column version int8 default 0;
alter table item add column version int8 default 0;
alter table mvtstock add column version int8 default 0;
alter table mvtstock_document add column version int8 default 0;
alter table ordonnancier_dispensation add column version int8 default 0;
alter table ordonnancier_fab_reconst add column version int8 default 0;
alter table patient add column version int8 default 0;
alter table patient_suivi add column version int8 default 0;
alter table personne add column version int8 default 0;
alter table personne_suivi add column version int8 default 0;
alter table pharmacie add column version int8 default 0;
alter table pharmacie_suivi add column version int8 default 0;
alter table pharmacien_document_pharmacien add column version int8 default 0;
alter table pole add column version int8 default 0;
alter table pole_suivi add column version int8 default 0;
alter table prescription add column version int8 default 0;
alter table prescription_type add column version int8 default 0;
alter table prevision_sigrec add column version int8 default 0;
alter table produit add column version int8 default 0;
alter table produit_detail_logistique add column version int8 default 0;
alter table produit_detail_stockage add column version int8 default 0;
alter table produit_document_actes_pharma add column version int8 default 0;
alter table produit_prescrit add column version int8 default 0;
alter table produit_suivi add column version int8 default 0;
alter table promoteur add column version int8 default 0;
alter table promoteur_sigrec add column version int8 default 0;
alter table promoteur_suivi add column version int8 default 0;
alter table regle_surcout add column version int8 default 0;
alter table retour_patient add column version int8 default 0;
alter table sequence add column version int8 default 0;
alter table service add column version int8 default 0;
alter table service_suivi add column version int8 default 0;
alter table site add column version int8 default 0;
alter table site_suivi add column version int8 default 0;
alter table stockage add column version int8 default 0;
alter table theme add column version int8 default 0;
alter table lignestock alter version type int8;
alter table lignestock alter column version set default 0;-- Changement taille champ conditionnement.dosage
-- Taille originale : 19,2
-- 
alter table conditionnement alter column dosage type numeric(19,6);
-- Correction des données corrompues
update mvtstock set datePeremption='2013-01-31 00:00:00' where id = 3831;

-- Suppression des retours promoteurs crées en doublons
delete from mvt_retour_promoteur where id in (4622, 4623, 4624, 4625, 4626, 4627, 4628, 4629, 4630);
delete from mvtstock where id in (4622, 4623, 4624, 4625, 4626, 4627, 4628, 4629, 4630);
