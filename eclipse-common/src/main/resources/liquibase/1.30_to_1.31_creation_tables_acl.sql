-- Creation des tables "ACL" pour gérer les habilitations
--
-- Table: acl_essai
CREATE TABLE acl_essai
(
  id_personne bigint NOT NULL,
  id_essai bigint NOT NULL,
  CONSTRAINT acl_essai_pkey PRIMARY KEY (id_personne, id_essai)
)
WITH (OIDS=FALSE);
ALTER TABLE acl_essai OWNER TO eclipse;

-- Index: acl_essai_id_essai
CREATE INDEX acl_essai_id_essai ON acl_essai USING btree (id_essai);

-- Index: acl_essai_id_personne
CREATE INDEX acl_essai_id_personne ON acl_essai USING btree (id_personne);
  
-- Table: acl_pharmacie
CREATE TABLE acl_pharmacie
(
  id_pharmacie bigint NOT NULL,
  id_personne bigint NOT NULL,
  CONSTRAINT acl_pharmacie_pkey PRIMARY KEY (id_pharmacie, id_personne)
)
WITH (OIDS=FALSE);
ALTER TABLE acl_pharmacie OWNER TO eclipse;

-- Index: acl_pharmacie_id_personne
CREATE INDEX acl_pharmacie_id_personne ON acl_pharmacie USING btree (id_personne);

-- Index: acl_pharmacie_id_pharmacie
CREATE INDEX acl_pharmacie_id_pharmacie ON acl_pharmacie USING btree(id_pharmacie);  

-- Alimentation des acl des essais pour les admin
INSERT INTO acl_essai (id_essai, id_personne) SELECT DISTINCT e.id, p.id FROM essai e, personne p WHERE p.isadmin IS true;

-- Alimentation des acl des essais pour les pharmaciens
INSERT INTO acl_essai (id_essai, id_personne) SELECT DISTINCT e.id, pers.id FROM essai e, habilitation h, personne pers, essai_detail_contacts edc WHERE e.id = edc.id_essai AND h.id_detail_contacts = edc.id AND h.id_personne = pers.id  AND h.active IS true AND type = 'PHARMACIEN' AND isadmin IS false UNION SELECT DISTINCT e.id, pers.id FROM essai e, pharmacien_pharmacie p, essai_detail_pharma_pharmacie ep, personne pers WHERE  p.id_pharmacien = pers.id AND type = 'PHARMACIEN' AND isadmin IS false AND (e.id_pharma = p.id_pharmacie OR (e.id=ep.id_detail_pharma and p.id_pharmacie=ep.id_pharmacie));

-- Alimentation des acl des essais pour les autres profils
INSERT INTO acl_essai (id_essai, id_personne) SELECT DISTINCT e.id, id_personne FROM habilitation h INNER JOIN essai_detail_contacts edc ON h.id_detail_contacts = edc.id INNER JOIN essai e on edc.id_essai = e.id INNER JOIN personne p ON h.id_personne = p.id WHERE h.active IS true AND type <> 'PHARMACIEN' AND isadmin IS false;

-- Alimentation des acl des pharmacies pour les admin
INSERT INTO acl_pharmacie (id_pharmacie, id_personne) SELECT DISTINCT p.id, pers.id FROM pharmacie p, personne pers WHERE pers.isadmin IS true;

-- Alimentation des acl des pharmacies pour les pharmacien
INSERT INTO acl_pharmacie (id_pharmacie, id_personne) SELECT DISTINCT p.id, pers.id FROM pharmacie p INNER JOIN pharmacien_pharmacie pp ON p.id = pp.id_pharmacie INNER JOIN personne pers ON pp.id_pharmacien = pers.id WHERE pers.isadmin IS false AND type='PHARMACIEN';



alter table evenement alter column commentaire set type text;
update personne set version = 0 where version is null;