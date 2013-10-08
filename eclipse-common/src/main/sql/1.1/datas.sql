UPDATE essai_detail_administratif
   SET autorisationdistribution_docsdosspapier=false, autorisationimportation_docsdosspapier=false;
   
UPDATE dispensation
   SET id_pharmacie=1;
   
   -- Insertion des donnees prevision pour les essais en cours.
INSERT INTO donnees_prevision(
            id, nbannees, nbdestructions, nbdispensations, nbinclusions, 
            nbprescriptions, nbreconstitutions, nbreetiquetages, id_detail_surcout)
    VALUES (1, null, null, null, null, 
            null, null, null, 1);

INSERT INTO donnees_prevision(
            id, nbannees, nbdestructions, nbdispensations, nbinclusions, 
            nbprescriptions, nbreconstitutions, nbreetiquetages, id_detail_surcout)
    VALUES (2, null, null, null, null, 
            null, null, null, 2);

INSERT INTO donnees_prevision(
            id, nbannees, nbdestructions, nbdispensations, nbinclusions, 
            nbprescriptions, nbreconstitutions, nbreetiquetages, id_detail_surcout)
    VALUES (3, null, null, null, null, 
            null, null, null, 100000);

INSERT INTO donnees_prevision(
            id, nbannees, nbdestructions, nbdispensations, nbinclusions, 
            nbprescriptions, nbreconstitutions, nbreetiquetages, id_detail_surcout)
    VALUES (4, null, null, null, null, 
            null, null, null, 100003);

-- Association d'actes aux catégories
UPDATE categorie SET acte='AUCUN' WHERE id='1';
UPDATE categorie SET acte='AUCUN' WHERE id='3';
UPDATE categorie SET acte='AUCUN' WHERE id='6';
UPDATE categorie SET acte='AUCUN' WHERE id='8';
UPDATE categorie SET acte='AUCUN' WHERE id='9';
UPDATE categorie SET acte='AUCUN' WHERE id='10';
UPDATE categorie SET acte='AUCUN' WHERE id='11';
UPDATE categorie SET acte='AUCUN' WHERE id='12';
UPDATE categorie SET acte='AUCUN' WHERE id='13';
UPDATE categorie SET acte='DESTRUCTION' WHERE id='4';
UPDATE categorie SET acte='DISPENSATION' WHERE id='2';
UPDATE categorie SET acte='RECONSTITUTION' WHERE id='5';
UPDATE categorie SET acte='REETIQUETAGE' WHERE id='7';