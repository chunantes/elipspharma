select e.numInterne as essai, p.nom as pharma, prod.denomination as produit, cond.libelle as conditionnement, entree.numlot as numlot, entree.numtraitement as numtraitement, entree.type, entree.approapprouve, entree.quantite, entree.dateperemption , entree.datecreation as datecreation
from mvtstock entree 
inner join essai e on entree.id_essai = e.id
inner join pharmacie p on entree.id_pharmacie = p.id
inner join produit prod on entree.id_produit = prod.id
inner join conditionnement cond on entree.id_conditionnement = cond.id
where entree.type in ('APPROVISIONNEMENT', 'ENTREE_CORRECTIVE', 'PREPARATION_ENTREE')
union
select e.numInterne as essai, p.nom as pharma, prod.denomination as produit, cond.libelle as conditionnement, sortie.numlot as numlot, sortie.numtraitement as numtraitement, sortie.type, sortie.approapprouve, sortie.quantite, sortie.dateperemption , sortie.datecreation as datecreation
from mvtstock sortie 
inner join essai e on sortie.id_essai = e.id
inner join pharmacie p on sortie.id_pharmacie = p.id
inner join produit prod on sortie.id_produit = prod.id
inner join conditionnement cond on sortie.id_conditionnement = cond.id
where sortie.type in ('CESSION_PUI', 'DISPENSATION', 'DESTRUCTION', 'DOTATION', 'RETOUR_PROMOTEUR', 'PREPARATION_SORTIE', 'AUTRE_SORTIE')
order by essai, pharma, produit, conditionnement, numlot,numtraitement, datecreation