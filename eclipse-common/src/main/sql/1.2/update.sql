-- Ajout de la table essai_document_detail_surcout
CREATE TABLE essai_document_detail_surcout (
    type character varying(31) NOT NULL,
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    nomdisque character varying(255) NOT NULL,
    nomuser character varying(255) NOT NULL,
    commentaire text,
    id_detailsurcout bigint NOT NULL
);


ALTER TABLE public.essai_document_detail_surcout OWNER TO eclipse;

CREATE SEQUENCE essai_document_detail_surcout_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

ALTER TABLE public.essai_document_detail_surcout_id_seq OWNER TO eclipse;

ALTER SEQUENCE essai_document_detail_surcout_id_seq OWNED BY essai_document_detail_surcout.id;

-- Ajout de la theme item_regle.
CREATE TABLE item_regle (
    id_regle bigint NOT NULL,
    id_item bigint NOT NULL
);

ALTER TABLE public.item_regle OWNER TO eclipse;

-- Modification de la table regle_surcout.
ALTER TABLE regle_surcout DROP COLUMN id_item;
ALTER TABLE regle_surcout ADD COLUMN id_theme bigint;
ALTER TABLE regle_surcout ADD COLUMN id_categorie bigint;

-- Modification de la table theme
ALTER TABLE theme DROP COLUMN categorie;


   
