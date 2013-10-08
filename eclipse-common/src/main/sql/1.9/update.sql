-- Ajout / Modification de champs concernant la prescription
ALTER TABLE prescription ADD COLUMN commentaire text;
ALTER TABLE produit_prescrit ADD COLUMN aDispenser bool;
ALTER TABLE prescription
   ALTER COLUMN id_sequence DROP NOT NULL;

-- Modification de la table de mouvement de cession pour ajouter des champs.
ALTER TABLE mvt_cession_pui ADD COLUMN id_pharmaciedest bigint;
ALTER TABLE mvt_cession_pui ADD CONSTRAINT fkfd557b77382dd136 FOREIGN KEY (id_pharmaciedest)
      REFERENCES pharmacie (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;
   
-- Mise à jour de table retour_patient       
ALTER TABLE retour_patient ADD COLUMN etat character varying(255);
ALTER TABLE retour_patient ADD COLUMN type character varying(255);
ALTER TABLE retour_patient ADD COLUMN dateEtat timestamp without time zone;     
ALTER TABLE retour_patient ADD COLUMN commentaireEtat text;       
ALTER TABLE retour_patient ADD COLUMN commentaireEntame text;
ALTER TABLE retour_patient ADD COLUMN numOrdonnancier integer;
UPDATE retour_patient SET etat='PRESENT';

      
-- Generation du num d'ordonanncier 
UPDATE dispensation SET numOrdonnancier=x.numordonnancierdisp+1 FROM (select numordonnancierdisp, id_pharmacie from pharmacie, dispensation where pharmacie.id = dispensation.id_pharmacie) x
WHERE numOrdonnancier IS NULL AND dispensation.id_pharmacie = x.id_pharmacie AND dispensation.dispense=true;
UPDATE pharmacie SET numordonnancierdisp=x.num FROM (select MAX(numOrdonnancier) AS num, id_pharmacie from dispensation, pharmacie WHERE id_pharmacie=pharmacie.id GROUP BY dispensation.id_pharmacie) x WHERE id=x.id_pharmacie;

-- Correction en retard.
ALTER TABLE evenement ALTER COLUMN id_essai DROP NOT NULL;

-- Ajout du champ contenant la quantité dispensée nominativement dans un mouvement de dispensation globale
ALTER TABLE mvt_dispensation_globale ADD COLUMN quantiteDispensee integer;
UPDATE mvt_dispensation_globale SET quantiteDispensee=0;