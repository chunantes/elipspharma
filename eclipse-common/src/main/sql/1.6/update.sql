-- SCRIPT METTANT A JOUR LES CONTRAINTES MANQUANTE PROVOQUANT DES ERREURS
ALTER TABLE essai_document_detail_surcout ALTER COLUMN id SET DEFAULT nextval('essai_document_detail_surcout_id_seq'::regclass);
ALTER TABLE ONLY essai_document_detail_surcout
    ADD CONSTRAINT essai_document_detail_surcout_pkey PRIMARY KEY (id);
ALTER TABLE ONLY item_regle
    ADD CONSTRAINT item_regle_pkey PRIMARY KEY (id_regle, id_item);
CREATE INDEX idx_categorie_regle ON regle_surcout USING btree (id_categorie);
CREATE INDEX idx_detailstockage_retourpatient ON retour_patient USING btree (id_detailstockage);
CREATE INDEX idx_disp_ordon ON dispensation USING btree (id_ordonnancier);
CREATE INDEX idx_disp_pharmacie ON dispensation USING btree (id_pharmacie);
CREATE INDEX idx_eltcheck_personne ON element_to_check USING btree (id_personne);
CREATE INDEX idx_elementtocheck_ordon ON element_to_check USING btree (id_ordonnancier);
CREATE INDEX idx_essai_document_detail_surcout ON essai_document_detail_surcout USING btree (id_detailsurcout);
CREATE INDEX idx_theme_regle ON regle_surcout USING btree (id_theme);
ALTER TABLE ONLY essai_document_detail_surcout
    ADD CONSTRAINT fk24399e3f3233d23a FOREIGN KEY (id_detailsurcout) REFERENCES essai_detail_surcout(id);
ALTER TABLE ONLY item_regle
    ADD CONSTRAINT fk8b91f4e11622e8a9 FOREIGN KEY (id_regle) REFERENCES regle_surcout(id);
ALTER TABLE ONLY item_regle
    ADD CONSTRAINT fk8b91f4e1e0ff5276 FOREIGN KEY (id_item) REFERENCES item(id);
ALTER TABLE ONLY retour_patient
    ADD CONSTRAINT fkc38b7dd1d8bb7cd7 FOREIGN KEY (id_detailstockage) REFERENCES produit_detail_stockage(id);
ALTER TABLE ONLY regle_surcout
    ADD CONSTRAINT fkd387012940161942 FOREIGN KEY (id_theme) REFERENCES theme(id);
ALTER TABLE ONLY regle_surcout
    ADD CONSTRAINT fkd387012961ea981e FOREIGN KEY (id_categorie) REFERENCES categorie(id);
    
UPDATE evenement set typevisite='AUDIT_INTERNE' WHERE typevisite='AUDIT';

