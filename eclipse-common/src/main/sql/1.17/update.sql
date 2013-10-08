ALTER TABLE mvt_preparationentree ADD COLUMN numordonnancier integer;
ALTER TABLE mvt_preparationentree ADD COLUMN id_ordonnancier bigint;
ALTER TABLE mvt_preparationentree ADD  FOREIGN KEY (id_ordonnancier)
      REFERENCES ordonnancier_fab_reconst (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE mvt_preparationentree ADD COLUMN sterile boolean;

UPDATE mvt_preparationentree SET sterile=false; 
      
update mvt_preparationentree set numOrdonnancier=0 where numordonnancier is null;      