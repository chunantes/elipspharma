UPDATE inclusion SET dateInclusion='2011-01-01 12:00:00';

-- Mise à jour des thèmes et catégories de la grille des surcouts.
UPDATE theme SET libelle='Frais spécifiques' where libelle='Frais spécifiques (18)';
UPDATE categorie SET libelle='Forfait pharmaceutique' where libelle='Forfait pharmaceutique (7)';
UPDATE categorie SET libelle='Forfait dispensation nominative par ordonnance : Nouvelle' where libelle='Forfait dispensation nominative (8) par ordonnance : Nouvelle';
UPDATE categorie SET libelle='Forfait dispensation nominative par ordonnance : Renouvellement ou fractionnée' where libelle='Forfait dispensation nominative (8) par ordonnance : Renouvellement ou fractionnée';
UPDATE categorie SET libelle='Destruction' where libelle='Destruction (9)';
UPDATE categorie SET libelle='Reconstitution' where libelle='Reconstitution (10)';
UPDATE categorie SET libelle='Conditions particulières de conservation' where libelle='Conditions particulières de conservation (11)';
UPDATE categorie SET libelle='Ré-étiquetage' where libelle='Ré-étiquetage (12)';
UPDATE categorie SET libelle='Visite supplémentaire de suivi (de monitoring)' where libelle='Visite supplémentaire de suivi (de monitoring) (13)';
UPDATE categorie SET libelle='Réception / Livraison de produits supplémentaires Actes IVRS ou @VRS' where libelle='Réception / Livraison de produits supplémentaires (14) Actes IVRS ou @VRS (14 bis)';
UPDATE categorie SET libelle='Traçabilité spécifique : MDS et DMI' where libelle='Traçabilité spécifique (15) : MDS et DMI';
UPDATE categorie SET libelle='Audits' where libelle='Audits (16)';
UPDATE categorie SET libelle='Autres' where libelle='Autres (17)';