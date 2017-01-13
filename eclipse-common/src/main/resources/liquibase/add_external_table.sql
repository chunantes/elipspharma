-- Table: essais_externe

 DROP TABLE IF EXISTS essais_externe;

CREATE TABLE essais_externe ( 
id bigint NOT NULL, 
statut character varying(255), 
numinterne character varying(255) NOT NULL, 
codepromoteur character varying(255),
nomusuel character varying(255),
promoteur character varying(255),
typepromoteur character varying(255),
service character varying(255),
titreprotocole text,
typerecherche character varying(255),
objetrecherche character varying(255),
phaserecherche character varying(255),
naturerecherche character varying(255),
thematique character varying(255),
investigateur_nom character varying(255),
investigateur_prenom character varying(255),
dateprevdebetude timestamp,
dateprevfinetude timestamp,
dateprevfininclusion timestamp,
autoritecomp boolean,
nomautoritecomp character varying(255),
dateaccordac timestamp,
eudract character varying(255),
cpp boolean,
nomcpp character varying(255),
dateautorisationcpp timestamp,
conventionsignee boolean,
datesignatureconv timestamp,
nomcompagnieassurance character varying(255),
numcontratassurance character varying(255),
nomavenantassurance character varying(255),
nbpatientprevulocal integer,
dureetotprevue integer,
unitedureetotprevue character varying(255),
nbcentreprevu integer,
nbpatientprevutotal integer,
numcentre character varying(255),
CONSTRAINT essais_externe_pkey PRIMARY KEY (id)
);


