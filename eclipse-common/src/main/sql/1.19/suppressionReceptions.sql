-- Script qui supprime des receptions en erreur de la base en production
-- 20120221
-- JOS
--
delete from mvt_approvisionnement where id in (1, 2, 3, 4, 5, 6, 7);
delete from mvtstock where id in (1, 2, 3, 4, 5, 6, 7);
