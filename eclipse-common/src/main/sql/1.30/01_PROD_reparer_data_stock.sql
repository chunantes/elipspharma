-- Correction des données corrompues
update mvtstock set datePeremption='2013-01-31 00:00:00' where id = 3831;

-- Suppression des retours promoteurs crées en doublons
delete from mvt_retour_promoteur where id in (4622, 4623, 4624, 4625, 4626, 4627, 4628, 4629, 4630);
delete from mvtstock where id in (4622, 4623, 4624, 4625, 4626, 4627, 4628, 4629, 4630);

update mvtstock set datePeremption='2013-11-30 00:00:00' where numtraitement = 'A051';
update mvtstock set datePeremption='2013-11-30 00:00:00' where numtraitement = 'A052';
update mvtstock set datePeremption='2013-11-30 00:00:00' where numtraitement = 'A054';
