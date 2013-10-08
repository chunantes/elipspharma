-- PHARMA-629
-- A lancer APRES le démarrage de l'application (qui remplit la table "ligne_stock")
--
-- Correction des données de BIRKEN BSG-12 pour le num de traitement 670
delete from lignestock where id = 90;

-- Correction des données de CEFASLEEVE
delete from lignestock where id_essai = 20;

