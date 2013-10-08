-- Script à passer pour le passage de la version 1.19 à 1.20

-- Creation de nouvelles colonnes dans la table donnees_prevision (PHARMA-359 : Modification grille de surcoûts)
ALTER TABLE donnees_prevision ADD COLUMN nbaudits integer;
ALTER TABLE donnees_prevision ADD COLUMN nbapprovisionnements integer;
ALTER TABLE donnees_prevision ADD COLUMN nbpreparationssteriles integer;
ALTER TABLE donnees_prevision ADD COLUMN nbpreparationsnonsteriles integer;