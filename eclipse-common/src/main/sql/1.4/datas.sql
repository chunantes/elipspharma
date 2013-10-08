UPDATE conditionnement SET uniteDosage='MILLIGRAMME' WHERE uniteDosage='MG';
UPDATE conditionnement SET uniteDosage='MILLIGRAMME' WHERE uniteDosage='mg';
UPDATE conditionnement SET uniteDosage='MILLILITRE' WHERE uniteDosage='L';
UPDATE conditionnement SET uniteGestion='FLACON' WHERE unitegestion='DOSAGE';


-- Mise à ajour des libellés des types de pharmaciens.
UPDATE personne SET typePharmacien='ATTACHE' WHERE typePharmacien='PHARMACIEN_ATTACHE';
UPDATE personne SET typePharmacien='INTERNE_GARDE' WHERE typePharmacien='PHARMACIEN_INTERNE_GARDE';
UPDATE personne SET typePharmacien='ASSISTANT' WHERE typePharmacien='PHARMACIEN_ASSISTANT';