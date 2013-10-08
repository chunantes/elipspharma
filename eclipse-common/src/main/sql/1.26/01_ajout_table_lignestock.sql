-- Création table: lignestock
--

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
