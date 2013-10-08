ALTER TABLE evenement ADD COLUMN nombre integer;
ALTER TABLE evenement ADD COLUMN validation character varying(255);
ALTER TABLE evenement ADD COLUMN realisePar character varying(255);
ALTER TABLE evenement ADD COLUMN id_personne bigint ;
ALTER TABLE evenement ALTER COLUMN libelle DROP NOT NULL;


ALTER TABLE evenement ADD CONSTRAINT fk1174a698800c21 FOREIGN KEY (id_personne)
      REFERENCES personne (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;
      
UPDATE categorie SET acte='CONDITION_CONSERVATION' WHERE libelle='Conditions particulières de conservation';
UPDATE categorie SET acte='VISITE_MONITORING' WHERE libelle='Visite supplémentaire de suivi (de monitoring)';
UPDATE categorie SET acte='DISPENSATION_RENOUVELLEMENT' WHERE libelle='Forfait dispensation nominative par ordonnance : Renouvellement ou fractionnée';
UPDATE item SET acte='CONDITION_CONSERVATION' WHERE categorie='Conditions particulières de conservation';
UPDATE item SET acte='VISITE_MONITORING' WHERE categorie='Visite supplémentaire de suivi (de monitoring)';
UPDATE item SET acte='DISPENSATION_RENOUVELLEMENT' WHERE categorie='Forfait dispensation nominative par ordonnance : Renouvellement ou fractionnée';




ALTER TABLE donnees_prevision ADD COLUMN nbVisiteMonitoring integer;
ALTER TABLE donnees_prevision ADD COLUMN nbDispensationsRenouvellement integer;

ALTER TABLE sequence ADD COLUMN nb_duree integer;
ALTER TABLE sequence ADD COLUMN unite_duree character varying(255);

ALTER TABLE retour_patient ADD COLUMN numtraitement character varying(255);
ALTER TABLE retour_patient ADD COLUMN numLot character varying(255);

update donnees_prevision set nbVisiteMonitoring =0;
update donnees_prevision set nbDispensationsRenouvellement =0;
update donnees_prevision set nbReetiquetages =0;
update donnees_prevision set nbReconstitutions =0;

-- AJout de colonne dans la table patient
ALTER TABLE patient ADD COLUMN numeroSejour character varying(255);

-- Ajout de la raison et du commentaire sur la raison de sortie sur les mvts de sortie
ALTER TABLE mvt_cession_pui ADD COLUMN raisonSortie character varying(255);
ALTER TABLE mvt_cession_pui ADD COLUMN commentaireRaison text;
ALTER TABLE mvt_destruction ADD COLUMN raisonSortie character varying(255);
ALTER TABLE mvt_destruction ADD COLUMN commentaireRaison text;
ALTER TABLE mvt_retour_promoteur ADD COLUMN raisonSortie character varying(255);
ALTER TABLE mvt_retour_promoteur ADD COLUMN commentaireRaison text;
ALTER TABLE mvt_autre_sortie ADD COLUMN raisonSortie character varying(255);
ALTER TABLE mvt_autre_sortie ADD COLUMN commentaireRaison text;

-- Extension de peremption
ALTER TABLE mvt_approvisionnement ADD COLUMN historiqueExtensionPeremption  text;

-- Etablissements dans donnees pharma
CREATE TABLE essai_detail_pharma_etablissement
(
  id_detail_pharma bigint NOT NULL,
  id_etablissement bigint NOT NULL,
  CONSTRAINT essai_detail_pharma_etablissement_pkey PRIMARY KEY (id_detail_pharma, id_etablissement),
  CONSTRAINT fk93a102d0cd45a413 FOREIGN KEY (id_etablissement)
      REFERENCES etablissement (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk93a102d0d08532d FOREIGN KEY (id_detail_pharma)
      REFERENCES essai_detail_pharma (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (OIDS=FALSE);
ALTER TABLE essai_detail_pharma_etablissement OWNER TO eclipse;

-- Evenement CESSION PUI 
ALTER TABLE evenement ADD COLUMN destinataire character varying(255);
ALTER TABLE evenement ADD COLUMN dateReception timestamp without time zone;


-- Preparation

CREATE TABLE preparation
(
  id bigint NOT NULL,
  CONSTRAINT preparation_pkey PRIMARY KEY (id),
  CONSTRAINT fkb1982697ae5cce6 FOREIGN KEY (id)
      REFERENCES produit (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (OIDS=FALSE);
ALTER TABLE preparation OWNER TO eclipse;

CREATE TABLE mvt_preparationentree
(
  composition text,
  datefabrication timestamp without time zone,
  id bigint NOT NULL,
  CONSTRAINT mvt_preparationentree_pkey PRIMARY KEY (id),
  CONSTRAINT fk414415ea389dcf45 FOREIGN KEY (id)
      REFERENCES mvt_approvisionnement (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (OIDS=FALSE);
ALTER TABLE mvt_preparationentree OWNER TO eclipse;
CREATE TABLE mvt_preparation
(
  id bigint NOT NULL,
  CONSTRAINT mvt_preparation_pkey PRIMARY KEY (id),
  CONSTRAINT fkfe8aa4433e8f1c97 FOREIGN KEY (id)
      REFERENCES mvtstock (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (OIDS=FALSE);
ALTER TABLE mvt_preparation OWNER TO eclipse;


-- Modification de la table detailDeign
ALTER TABLE essai_detail_design ADD COLUMN typeDesign character varying(255);

-- Modification de la table essai_document_detail_administratif
ALTER TABLE essai_document_detail_administratif ADD COLUMN typeDocumentProtocole character varying(255);
ALTER TABLE essai_document_detail_administratif ADD COLUMN typeDocumentBrochure character varying(255);
ALTER TABLE essai_document_detail_administratif ADD COLUMN version character varying(255);

-- Modification de la table prescription
ALTER TABLE prescription ADD COLUMN numVisite character varying(255);


-- Table: pharmacien_document_pharmacien

-- DROP TABLE pharmacien_document_pharmacien;

CREATE TABLE pharmacien_document_pharmacien
(
  "type" character varying(31) NOT NULL,
  id bigserial NOT NULL,
  datemaj timestamp without time zone NOT NULL,
  majpar character varying(255) NOT NULL,
  nomdisque character varying(255),
  nomuser character varying(255),
  id_pharmacien bigint NOT NULL,
  CONSTRAINT pharmacien_document_pharmacien_pkey PRIMARY KEY (id),
  CONSTRAINT fk47ad8dfd24482761 FOREIGN KEY (id_pharmacien)
      REFERENCES personne (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (OIDS=FALSE);
ALTER TABLE pharmacien_document_pharmacien OWNER TO eclipse;

-- Index: idx_pharmacien_document_pharmacien

-- DROP INDEX idx_pharmacien_document_pharmacien;

CREATE INDEX idx_pharmacien_document_pharmacien
  ON pharmacien_document_pharmacien
  USING btree
  (id_pharmacien);

-- Ajout de colonne dans la table personne pour les pharmaciens.
ALTER TABLE personne ADD COLUMN dateArriveeService timestamp without time zone;
ALTER TABLE personne ADD COLUMN dateDepartService timestamp without time zone;
ALTER TABLE personne ADD COLUMN dateValidationFormation timestamp without time zone;