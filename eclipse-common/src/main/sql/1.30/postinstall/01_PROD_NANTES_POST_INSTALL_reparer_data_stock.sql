-- PHARMA-629
-- A lancer APRES le démarrage de l'application (qui remplit la table "lignestock")
--
-- Correction des données de BIRKEN BSG-12 pour le num de traitement 670
delete from lignestock where id = 90;

-- Correction des données de CEFASLEEVE
delete from lignestock where id_essai = 20;

-- Suppression des lignestock où le stock est mal calculé (à cause de la date de peremption) mais devrait être 0
-- Requete : select * from lignestock where quantite_global <= 0;
-- 3516;t;"2013-11-30 00:00:00";"2J73461";"A052";0;-3;"ARMOIRE n°21";0;182;77;1;137
-- 3476;t;"2013-11-30 00:00:00";"2J73461";"A053";0;-3;"ARMOIRE n°21";0;182;77;1;137
-- 3683;t;"2013-11-30 00:00:00";"2J73461";"A054";0;-3;"ARMOIRE n°21";0;182;77;1;137
-- 4366;t;"2013-11-30 00:00:00";"2J73461";"A051";0;-3;"ARMOIRE n°21";0;182;77;1;137
-- 3619;t;"2014-12-01 00:00:00";"12-004637";"";0;-5;"CHAMBRE FROIDE 3 "Essais cliniques"";0;82;41;1;71
-- 3654;t;"2014-01-31 00:00:00";"121357598";"";0;-1;"ARMOIRE n°4";0;90;43;1;73
-- 4993;t;"2017-11-30 00:00:00";"SML00143";"";0;-1;"ARMOIRE n°24";0;201;80;1;146

delete from lignestock where numlot='2J73461' and numtraitement in ('A051', 'A052', 'A053', 'A054');
delete from lignestock where numlot='12-004637';
delete from lignestock where numlot='121357598';
delete from lignestock where numlot='SML00143' and id_conditionnement=201;
