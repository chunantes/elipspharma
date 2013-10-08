
-- Modification de la table retour_patient
ALTER TABLE retour_patient ADD COLUMN commentaire text;
ALTER TABLE retour_patient ADD COLUMN quantite integer;
ALTER TABLE retour_patient add id_detailstockage bigint NOT NULL;
  
-- AJout de champs dans la table essai_detail_pharma
ALTER TABLE essai_detail_pharma ADD COLUMN unitedureetotaleprevue character varying(255);
ALTER TABLE essai_detail_pharma ADD COLUMN unitedureetotalepatientprevue character varying(255);
ALTER TABLE essai_detail_pharma ADD COLUMN nbpatientsprevustotal integer;

-- Ajout de champs dans la table conditionnement
ALTER TABLE conditionnement ADD COLUMN forme character varying(255);
ALTER TABLE conditionnement ALTER dosage TYPE numeric(19,2);
ALTER TABLE conditionnement ALTER nbuniteprescription TYPE numeric(19,2);
ALTER TABLE conditionnement ADD COLUMN contenance numeric(19,2);
ALTER TABLE conditionnement ADD COLUMN unitecontenance character varying(255);

-- Prescription type
ALTER TABLE prescription_type ADD COLUMN dosage numeric(19,2);
ALTER TABLE prescription_type ALTER nbunitedosage TYPE numeric(19,2); 

-- Produit prescrit
ALTER TABLE produit_prescrit ADD COLUMN dosage numeric(19,2);
ALTER TABLE produit_prescrit ALTER nbunitedosage TYPE numeric(19,2); 



