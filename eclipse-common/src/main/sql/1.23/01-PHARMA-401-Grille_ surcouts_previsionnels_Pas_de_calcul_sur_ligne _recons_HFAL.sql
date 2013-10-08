-- PHARMA-401 :  Grille surcouts previsionnels : pas de calcul sur la ligne recons HFAL

-- Suppression de l'acte RECONSTITUTION 
-- changement des actes des items de 'RECONSTITUTION' à 'PREPARATIONS_NON_STERILES' et 'PREPARATIONS_STERILES'
UPDATE item SET acte='PREPARATIONS_NON_STERILES' WHERE UPPER(categorie) like UPPER('%non%hfal%') AND acte='RECONSTITUTION';
UPDATE item SET acte='PREPARATIONS_STERILES' WHERE UPPER(categorie) like UPPER('%hfal%') AND acte='RECONSTITUTION';
UPDATE item SET acte='PREPARATIONS_STERILES' WHERE acte='RECONSTITUTION';

UPDATE categorie SET acte='PREPARATIONS_NON_STERILES' WHERE UPPER(libelle) like UPPER('%non%hfal%') AND acte='RECONSTITUTION';
UPDATE categorie SET acte='PREPARATIONS_STERILES' WHERE UPPER(libelle) like UPPER('%hfal%') AND acte='RECONSTITUTION';
UPDATE categorie SET acte='PREPARATIONS_STERILES' WHERE acte='RECONSTITUTION';

-- suppression de la colonne donnees_prevision 
ALTER TABLE donnees_prevision DROP COLUMN nbreconstitutions;