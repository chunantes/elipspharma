-- Table: incident

-- DROP TABLE incident;

CREATE TABLE incident
(
  id bigserial NOT NULL,
  commentaire text,
  date timestamp without time zone NOT NULL,
  libelle character varying(255) NOT NULL,
  id_essai bigint NOT NULL,
  CONSTRAINT incident_pkey PRIMARY KEY (id),
  CONSTRAINT fk52f44d239fd10dc FOREIGN KEY (id_essai)
      REFERENCES essai (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (OIDS=FALSE);
ALTER TABLE incident OWNER TO eclipse;

-- Index: idx_essai_incident

-- DROP INDEX idx_essai_incident;

CREATE INDEX idx_essai_incident
  ON incident
  USING btree
  (id_essai);


-- Table: incident_suivi

-- DROP TABLE incident_suivi;

CREATE TABLE incident_suivi
(
  id bigserial NOT NULL,
  datemaj timestamp without time zone NOT NULL,
  majpar character varying(255) NOT NULL,
  id_incident bigint NOT NULL,
  CONSTRAINT incident_suivi_pkey PRIMARY KEY (id),
  CONSTRAINT fk5b30998db77789cb FOREIGN KEY (id_incident)
      REFERENCES incident (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (OIDS=FALSE);
ALTER TABLE incident_suivi OWNER TO eclipse;

-- Index: idx_suivi_incident

-- DROP INDEX idx_suivi_incident;

CREATE INDEX idx_suivi_incident
  ON incident_suivi
  USING btree
  (id_incident);


  

-- Modification de la table evenement
ALTER TABLE evenement ADD COLUMN arc character varying(255);
ALTER TABLE evenement ADD COLUMN datefin timestamp without time zone;
ALTER TABLE evenement ADD COLUMN heurefin character varying(255);
ALTER TABLE evenement ADD COLUMN journee boolean;
ALTER TABLE evenement ADD COLUMN personnelpharmacie text;

UPDATE evenement set datefin='2011-01-19 12:00:00';
UPDATE  evenement set journee=true;

ALTER TABLE evenement ALTER COLUMN datefin SET NOT NULL;
  ALTER TABLE evenement
   ALTER COLUMN heuredebut DROP NOT NULL;


-- Création de la table retour_patient
CREATE TABLE retour_patient
(
  id bigserial NOT NULL,
  date timestamp without time zone,
  id_conditionnement bigint NOT NULL,
  id_essai bigint NOT NULL,
  id_patient bigint NOT NULL,
  id_personne bigint NOT NULL,
  id_produit bigint NOT NULL,
  CONSTRAINT retour_patient_pkey PRIMARY KEY (id),
  CONSTRAINT fkc38b7dd139fd10dc FOREIGN KEY (id_essai)
      REFERENCES essai (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fkc38b7dd18800c21 FOREIGN KEY (id_personne)
      REFERENCES personne (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fkc38b7dd19d7535de FOREIGN KEY (id_conditionnement)
      REFERENCES conditionnement (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fkc38b7dd1a1ddf650 FOREIGN KEY (id_produit)
      REFERENCES produit (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fkc38b7dd1aedb3264 FOREIGN KEY (id_patient)
      REFERENCES patient (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (OIDS=FALSE);
ALTER TABLE retour_patient OWNER TO eclipse;


CREATE INDEX idx_conditionnement_retourpatient
  ON retour_patient
  USING btree
  (id_conditionnement);

CREATE INDEX idx_essai_retourpatient
  ON retour_patient
  USING btree
  (id_essai);


CREATE INDEX idx_patient_retourpatient
  ON retour_patient
  USING btree
  (id_patient);


CREATE INDEX idx_personne_retourpatient
  ON retour_patient
  USING btree
  (id_personne);


CREATE INDEX idx_produit_retourpatient
  ON retour_patient
  USING btree
  (id_produit);


  