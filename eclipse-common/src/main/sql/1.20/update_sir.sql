-- A NE JOUER QU'EN DEV
-- Script à passer pour le passage de la version 1.19 à 1.20 sur la base SIR

-- Ces lignes sont à exécuter sur la base 'sir'
-- Création d'une colonne 'per_salt' et cryptage des mots de pass (mdp non crypté : 'eclipse*')
ALTER TABLE annuaire.personne ADD COLUMN per_salt character varying(15);
UPDATE annuaire.personne set per_salt='1', per_password='242f81a34dd8197c3b80521534bf46e6';
