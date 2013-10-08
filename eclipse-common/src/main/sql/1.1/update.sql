-- Script à passer pour le passage de la version 1.0 à 1.1

-- Création de la table ordonnancier_dispensation
CREATE TABLE ordonnancier_dispensation
(
  id bigserial NOT NULL,
  datemaj timestamp without time zone NOT NULL,
  majpar character varying(255) NOT NULL,
  datedebut date NOT NULL,
  datefin date NOT NULL,
  id_pharma bigint NOT NULL,
  CONSTRAINT ordonnancier_dispensation_pkey PRIMARY KEY (id),
  CONSTRAINT fk9ea891de3a903eb7 FOREIGN KEY (id_pharma)
      REFERENCES pharmacie (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (OIDS=FALSE);
ALTER TABLE ordonnancier_dispensation OWNER TO eclipse;

-- Création de l'index idx_pharma_ordo_disp
CREATE INDEX idx_pharma_ordo_disp ON ordonnancier_dispensation USING btree (id_pharma);

ALTER SEQUENCE ordonnancier_dispensation_id_seq RESTART WITH 100000;

-- Ajout de la colonne numOrdonnancier sur la table Dispensation
ALTER TABLE dispensation ADD COLUMN numOrdonnancier integer;
ALTER TABLE dispensation ALTER COLUMN numOrdonnancier SET STORAGE PLAIN;

-- Ajout de la clé étrangère Dispensation -> Ordonnancier Dispensation
ALTER TABLE dispensation ADD COLUMN id_ordonnancier bigint;
ALTER TABLE dispensation ALTER COLUMN id_ordonnancier SET STORAGE PLAIN;
ALTER TABLE dispensation
  ADD CONSTRAINT fk2eaeffedbabca2b4 FOREIGN KEY (id_ordonnancier)
      REFERENCES ordonnancier_dispensation (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;
      
-- Ajout des colonnes numOrdonnancierDisp et numOrdonnancierFab sur la table Pharmacie initialisé à 0
ALTER TABLE pharmacie ADD COLUMN numOrdonnancierDisp integer DEFAULT 0 NOT NULL;
ALTER TABLE pharmacie ALTER COLUMN numOrdonnancierDisp SET STORAGE PLAIN;
ALTER TABLE pharmacie ADD COLUMN numOrdonnancierFab integer DEFAULT 0 NOT NULL;
ALTER TABLE pharmacie ALTER COLUMN numOrdonnancierFab SET STORAGE PLAIN;

-- Ajout de champs dans prescription_type
ALTER TABLE prescription_type ADD COLUMN notNull_frequence character varying(255) DEFAULT '';
ALTER TABLE prescription_type ADD COLUMN notNull_debut character varying(255) DEFAULT '';
ALTER TABLE prescription_type ADD COLUMN notNull_duree character varying(255) DEFAULT '';

-- Ajout de champs dans produit_prescrit
ALTER TABLE produit_prescrit ADD COLUMN notNull_frequence character varying(255) DEFAULT '';
ALTER TABLE produit_prescrit ADD COLUMN notNull_debut character varying(255) DEFAULT '';
ALTER TABLE produit_prescrit ADD COLUMN notNull_duree character varying(255) DEFAULT '';

-- Ajout de champs dans sequence
ALTER TABLE sequence ADD COLUMN notNull_debut character varying(255) DEFAULT '';
ALTER TABLE sequence ADD COLUMN notNull_fin character varying(255) DEFAULT '';

-- Passage en not null de la colonne numLot sur mvtstock 
ALTER TABLE mvtstock ALTER COLUMN numlot SET NOT NULL;

-- Création de la table ordonnancier_fab_reconst
CREATE TABLE ordonnancier_fab_reconst
(
  id bigserial NOT NULL,
  datemaj timestamp without time zone NOT NULL,
  majpar character varying(255) NOT NULL,
  datedebut date NOT NULL,
  datefin date NOT NULL,
  id_pharma bigint NOT NULL,
  CONSTRAINT ordonnancier_fab_reconst_pkey PRIMARY KEY (id),
  CONSTRAINT fk12c037c73a903eb7 FOREIGN KEY (id_pharma)
      REFERENCES pharmacie (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (OIDS=FALSE);
ALTER TABLE ordonnancier_fab_reconst OWNER TO eclipse;

-- Création de l'index idx_pharma_ordo_fab_reconst
CREATE INDEX idx_pharma_ordo_fab_reconst ON ordonnancier_fab_reconst USING btree (id_pharma);

-- Ajout de la colonne numOrdonnancier sur la table element_to_check
ALTER TABLE element_to_check ADD COLUMN numordonnancier integer;
ALTER TABLE element_to_check ALTER COLUMN numordonnancier SET STORAGE PLAIN;

-- Ajout de la clé étrangère ElementToCheck -> Ordonnancier Fabrication Reconstitution
ALTER TABLE element_to_check ADD COLUMN id_ordonnancier bigint;
ALTER TABLE element_to_check ALTER COLUMN id_ordonnancier SET STORAGE PLAIN;
ALTER TABLE element_to_check
  ADD CONSTRAINT fkf50b7c2797d4f410 FOREIGN KEY (id_ordonnancier)
      REFERENCES ordonnancier_fab_reconst (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;

-- Ajout de la colonne datechecked sur la table element_to_check
ALTER TABLE element_to_check ADD COLUMN datechecked timestamp without time zone;
ALTER TABLE element_to_check ALTER COLUMN datechecked SET STORAGE PLAIN;

-- Ajout de la colonne id_personne sur la table element_to_check
ALTER TABLE element_to_check ADD COLUMN id_personne bigint;
ALTER TABLE element_to_check ALTER COLUMN id_personne SET STORAGE PLAIN;
ALTER TABLE element_to_check
  ADD CONSTRAINT fkf50b7c278800c21 FOREIGN KEY (id_personne)
      REFERENCES personne (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;
      
-- Ajout de la pharmacie sur la table Dispensation
ALTER TABLE dispensation ADD COLUMN id_pharmacie bigint;
ALTER TABLE dispensation ALTER COLUMN id_pharmacie SET STORAGE PLAIN;

ALTER TABLE dispensation ALTER COLUMN id_pharmacie SET NOT NULL;
ALTER TABLE dispensation
  ADD CONSTRAINT fk2eaeffed4de40194 FOREIGN KEY (id_pharmacie)
      REFERENCES pharmacie (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;
      
-- Ajout des champs document dans la table essai_detail_administratif      
ALTER TABLE essai_detail_administratif ADD COLUMN  autorisationdistribution_docsdosspapier boolean ;
ALTER TABLE essai_detail_administratif ADD COLUMN  autorisationimportation_docsdosspapier boolean;


-- Ajout du numero de telephone portable dans les personnes.
ALTER TABLE personne ADD COLUMN  telephoneportable character varying(255);

-- Ajout de la table donnees_prevision.
CREATE TABLE donnees_prevision
(
  id bigserial NOT NULL,
  nbannees integer,
  nbdestructions integer,
  nbdispensations integer,
  nbinclusions integer,
  nbprescriptions integer,
  nbreconstitutions integer,
  nbreetiquetages integer,
  id_detail_surcout bigint,
  CONSTRAINT donnees_prevision_pkey PRIMARY KEY (id),
  CONSTRAINT fk75589534f5ae6985 FOREIGN KEY (id_detail_surcout)
      REFERENCES essai_detail_surcout (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (OIDS=FALSE);
ALTER TABLE donnees_prevision OWNER TO eclipse;
ALTER SEQUENCE donnees_prevision_id_seq RESTART WITH 100000;
-- Ajout des champs dans la table grille
ALTER TABLE grille ADD COLUMN id_detail_surcout bigint;
ALTER TABLE grille
  ADD CONSTRAINT fkb63afd47f5ae6985 FOREIGN KEY (id_detail_surcout)
      REFERENCES essai_detail_surcout (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;


-- Ajout des champs dans la table regle_surcout.
ALTER TABLE regle_surcout ADD COLUMN premiereAnnee numeric(19,2);
ALTER TABLE regle_surcout ADD COLUMN anneessuivantes numeric(19,2);
ALTER TABLE regle_surcout ADD COLUMN max integer;
ALTER TABLE regle_surcout ALTER COLUMN max SET STORAGE PLAIN;
ALTER TABLE regle_surcout ADD COLUMN min integer;
ALTER TABLE regle_surcout ALTER COLUMN min SET STORAGE PLAIN;
ALTER TABLE regle_surcout ADD COLUMN "mode" character varying(255);
ALTER TABLE regle_surcout ADD COLUMN montant numeric(19,2);
ALTER TABLE regle_surcout ADD COLUMN perimetre character varying(255);
ALTER TABLE regle_surcout DROP COLUMN valeur;

-- Ajout du champs acte dans categorie
ALTER TABLE categorie ADD COLUMN acte character varying(255);

-- Modification en not null du champs libelle de conditionnement
ALTER TABLE conditionnement
   ALTER COLUMN libelle SET NOT NULL;

-- Modification du champs commentaire de la table essai_detail_etat
ALTER TABLE essai_detail_etat
   ALTER COLUMN commentaire DROP NOT NULL;

-- Ajout du champs acte dans la table item.
ALTER TABLE item ADD COLUMN acte character varying(255);

-- Ajout du champ initiales dans patients 
ALTER TABLE patient ADD COLUMN initiales character varying(255);

