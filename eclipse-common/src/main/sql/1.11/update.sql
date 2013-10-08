-- Modification des types de champs dans essai_detail_pharma
ALTER TABLE essai_detail_pharma ALTER COLUMN dureeTotalePrevue TYPE numeric(19,2);
ALTER TABLE essai_detail_pharma ALTER COLUMN dureeTotalePatientPrevue TYPE numeric(19,2);

-- modification  table service
ALTER TABLE service ALTER COLUMN id_pole DROP NOT NULL;
ALTER TABLE service ADD COLUMN id_site bigint ;
ALTER TABLE ONLY service
    ADD CONSTRAINT fk7643c6b57aad8e07 FOREIGN KEY (id_site)
      REFERENCES site (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;