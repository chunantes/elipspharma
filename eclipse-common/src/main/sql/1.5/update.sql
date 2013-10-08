ALTER TABLE essai_detail_recherche_sigrec ALTER titreprotocole TYPE text;

UPDATE categorie SET libelle='Réétiquetage' where libelle='Réétiquetage (12)';
UPDATE categorie SET libelle='Traçabilitée spécifique : MDS et DMI' where libelle='Traçabilitée spécifique (15) : MDS et DMI';

update categorie set libelle='Attribution d''un traitement au patient (appel d''un serveur vocal / IVRS) ' where libelle ='Attribution d''un traitement au patient (appel d''un serveur vocal / IVRS) ';

-- Supprimer les contraintes  inutiles sur les embeded
ALTER TABLE prescription_type DROP COLUMN notnull_frequence;
ALTER TABLE prescription_type DROP COLUMN notnull_debut;
ALTER TABLE prescription_type DROP COLUMN notnull_duree;
ALTER TABLE "sequence" DROP COLUMN notnull_debut;
ALTER TABLE "sequence" DROP COLUMN notnull_fin;
ALTER TABLE produit_prescrit DROP COLUMN notnull_frequence;
ALTER TABLE produit_prescrit DROP COLUMN notnull_debut;
ALTER TABLE produit_prescrit DROP COLUMN notnull_duree;

