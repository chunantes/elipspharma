-- Elips'Pharma
-- Version 1.19
-- Script d'import d'une grille de surcout

-- grille_modele
-- select * from grille_modele where id=100005;
INSERT INTO grille_modele (id,datecreation,datedebut,datefin,nom) VALUES (100005,'2011-12-21 00:00:00','2008-06-01 00:00:00',null,'Grille surcoûts études industrielles 2008');

--  * theme
-- select * from theme where id_grille_modele=100005;
INSERT INTO theme (id,libelle,id_grille_modele) VALUES (100015,'Actes pharmaceutiques supplémentaires',100005);
INSERT INTO theme (id,libelle,id_grille_modele) VALUES (100016,'Frais spécifiques',100005);
INSERT INTO theme (id,libelle,id_grille_modele) VALUES (100017,'Prestation standard',100005);

--    * categorie
-- select * from categorie where id_theme in (100015, 100016, 100017);
INSERT INTO categorie (id,libelle,id_theme,acte) VALUES (100067,'Actes IVRS ou @VRS',100015,'AUCUN');
INSERT INTO categorie (id,libelle,id_theme,acte) VALUES (100068,'Audits',100015,'AUCUN');
INSERT INTO categorie (id,libelle,id_theme,acte) VALUES (100069,'Autres',100015,'AUCUN');
INSERT INTO categorie (id,libelle,id_theme,acte) VALUES (100070,'Conditions particulières de conservation',100015,'CONDITION_CONSERVATION');
INSERT INTO categorie (id,libelle,id_theme,acte) VALUES (100071,'Destruction',100015,'DESTRUCTION');
INSERT INTO categorie (id,libelle,id_theme,acte) VALUES (100072,'Préparation non HFAL',100015,'RECONSTITUTION');
INSERT INTO categorie (id,libelle,id_theme,acte) VALUES (100073,'Reconstitution HFAL',100015,'RECONSTITUTION');
INSERT INTO categorie (id,libelle,id_theme,acte) VALUES (100074,'Réception / Livraison de produits supplémentaires',100015,'AUCUN');
INSERT INTO categorie (id,libelle,id_theme,acte) VALUES (100075,'Réétiquetage',100015,'REETIQUETAGE');
INSERT INTO categorie (id,libelle,id_theme,acte) VALUES (100077,'Visite supplémentaire de suivi (de monitoring)',100015,'VISITE_MONITORING');
INSERT INTO categorie (id,libelle,id_theme,acte) VALUES (100078,'Frais spécifiques',100016,'AUCUN');
INSERT INTO categorie (id,libelle,id_theme,acte) VALUES (100079,'Forfait dispensation nominative par ordonnance : Nouvelle',100017,'DISPENSATION');
INSERT INTO categorie (id,libelle,id_theme,acte) VALUES (100080,'Forfait dispensation nominative par ordonnance : Renouvellement ou fractionnée',100017,'DISPENSATION_RENOUVELLEMENT');
INSERT INTO categorie (id,libelle,id_theme,acte) VALUES (100081,'Forfait pharmaceutique',100017,'AUCUN');
INSERT INTO categorie (id,libelle,id_theme,acte) VALUES (100076,'Traçabilité spécifique : MDS et DMI',100015,'TRACABILITE');

--    * regle_surcout
-- select * from regle_surcout where id_categorie >= 100067 and id_categorie <= 100081;
INSERT INTO regle_surcout (id,type,premiereannee,anneessuivantes,max,min,mode,montant,perimetre,id_theme,id_categorie) VALUES (100047,'VARIABLE',null,null,null,1,'UNITE',10.00,'ESSAI',null,100067);
INSERT INTO regle_surcout (id,type,premiereannee,anneessuivantes,max,min,mode,montant,perimetre,id_theme,id_categorie) VALUES (100048,'VARIABLE',null,null,null,1,'UNITE',100.00,'ESSAI',null,100068);
INSERT INTO regle_surcout (id,type,premiereannee,anneessuivantes,max,min,mode,montant,perimetre,id_theme,id_categorie) VALUES (100049,'FIXE',50.00,0.00,null,null,null,null,'ESSAI',null,100070);
INSERT INTO regle_surcout (id,type,premiereannee,anneessuivantes,max,min,mode,montant,perimetre,id_theme,id_categorie) VALUES (100050,'VARIABLE',null,null,null,0,'UNITE',80.00,'ESSAI',null,100071);
INSERT INTO regle_surcout (id,type,premiereannee,anneessuivantes,max,min,mode,montant,perimetre,id_theme,id_categorie) VALUES (100051,'VARIABLE',null,null,null,0,'UNITE',15.00,'PATIENT',null,100072);
INSERT INTO regle_surcout (id,type,premiereannee,anneessuivantes,max,min,mode,montant,perimetre,id_theme,id_categorie) VALUES (100052,'VARIABLE',null,null,null,0,'UNITE',50.00,'PATIENT',null,100073);
INSERT INTO regle_surcout (id,type,premiereannee,anneessuivantes,max,min,mode,montant,perimetre,id_theme,id_categorie) VALUES (100053,'VARIABLE',null,null,4,0,'LOT_FORFAITAIRE_AN',0.00,'ESSAI',null,100074);
INSERT INTO regle_surcout (id,type,premiereannee,anneessuivantes,max,min,mode,montant,perimetre,id_theme,id_categorie) VALUES (100054,'VARIABLE',null,null,null,5,'LOT_FORFAITAIRE_AN',10.00,'ESSAI',null,100074);
INSERT INTO regle_surcout (id,type,premiereannee,anneessuivantes,max,min,mode,montant,perimetre,id_theme,id_categorie) VALUES (100055,'VARIABLE',null,null,9,0,'LOT_FORFAITAIRE',15.00,'ESSAI',null,100075);
INSERT INTO regle_surcout (id,type,premiereannee,anneessuivantes,max,min,mode,montant,perimetre,id_theme,id_categorie) VALUES (100056,'VARIABLE',null,null,50,10,'LOT_FORFAITAIRE',25.00,'ESSAI',null,100075);
INSERT INTO regle_surcout (id,type,premiereannee,anneessuivantes,max,min,mode,montant,perimetre,id_theme,id_categorie) VALUES (100057,'VARIABLE',null,null,null,51,'LOT_FORFAITAIRE',50.00,'ESSAI',null,100075);
INSERT INTO regle_surcout (id,type,premiereannee,anneessuivantes,max,min,mode,montant,perimetre,id_theme,id_categorie) VALUES (100059,'VARIABLE',null,null,4,0,'LOT_FORFAITAIRE_AN',0.00,'ESSAI',null,100077);
INSERT INTO regle_surcout (id,type,premiereannee,anneessuivantes,max,min,mode,montant,perimetre,id_theme,id_categorie) VALUES (100060,'VARIABLE',null,null,30,5,'LOT_FORFAITAIRE_AN',15.00,'ESSAI',null,100077);
INSERT INTO regle_surcout (id,type,premiereannee,anneessuivantes,max,min,mode,montant,perimetre,id_theme,id_categorie) VALUES (100061,'VARIABLE',null,null,null,0,'UNITE',28.00,'PATIENT',null,100079);
INSERT INTO regle_surcout (id,type,premiereannee,anneessuivantes,max,min,mode,montant,perimetre,id_theme,id_categorie) VALUES (100062,'VARIABLE',null,null,null,0,'UNITE',15.00,'PATIENT',null,100080);
INSERT INTO regle_surcout (id,type,premiereannee,anneessuivantes,max,min,mode,montant,perimetre,id_theme,id_categorie) VALUES (100063,'FIXE',300.00,200.00,null,0,'LOT_FORFAITAIRE',5.00,'ESSAI',null,100081);
INSERT INTO regle_surcout (id,type,premiereannee,anneessuivantes,max,min,mode,montant,perimetre,id_theme,id_categorie) VALUES (100058,'FIXE',70.00,0.00,null,null,null,null,'ESSAI',null,100076);
