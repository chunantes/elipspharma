--
-- PostgreSQL database dump
--

-- Dumped from database version 8.3.5
-- Dumped by pg_dump version 9.1.9
-- Started on 2013-07-23 12:26:28 CEST

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 129 (class 1259 OID 2994856)
-- Dependencies: 3
-- Name: acl_essai; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE acl_essai (
    id_personne bigint NOT NULL,
    id_essai bigint NOT NULL
);




--
-- TOC entry 130 (class 1259 OID 2994861)
-- Dependencies: 3
-- Name: acl_pharmacie; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE acl_pharmacie (
    id_pharmacie bigint NOT NULL,
    id_personne bigint NOT NULL
);




--
-- TOC entry 132 (class 1259 OID 2994868)
-- Dependencies: 3
-- Name: arc_investigateur_sigrec; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE arc_investigateur_sigrec (
    id bigint NOT NULL,
    version bigint,
    identifiant character varying(255),
    intervenantid integer,
    numadeli character varying(255),
    titre character varying(255),
    id_contact bigint,
    id_centre bigint,
    id_essai bigint
);




--
-- TOC entry 131 (class 1259 OID 2994866)
-- Dependencies: 3 132
-- Name: arc_investigateur_sigrec_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE arc_investigateur_sigrec_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3099 (class 0 OID 0)
-- Dependencies: 131
-- Name: arc_investigateur_sigrec_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE arc_investigateur_sigrec_id_seq OWNED BY arc_investigateur_sigrec.id;


--
-- TOC entry 134 (class 1259 OID 2994879)
-- Dependencies: 3
-- Name: arc_promoteur_sigrec; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE arc_promoteur_sigrec (
    id bigint NOT NULL,
    version bigint,
    identifiant character varying(255),
    intervenantid integer,
    numadeli character varying(255),
    titre character varying(255),
    id_contact bigint,
    id_promoteur bigint
);




--
-- TOC entry 133 (class 1259 OID 2994877)
-- Dependencies: 134 3
-- Name: arc_promoteur_sigrec_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE arc_promoteur_sigrec_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3100 (class 0 OID 0)
-- Dependencies: 133
-- Name: arc_promoteur_sigrec_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE arc_promoteur_sigrec_id_seq OWNED BY arc_promoteur_sigrec.id;


--
-- TOC entry 135 (class 1259 OID 2994888)
-- Dependencies: 3
-- Name: arcinvestigateur_service; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE arcinvestigateur_service (
    id_arcinvestigateur bigint NOT NULL,
    id_service bigint NOT NULL
);




--
-- TOC entry 137 (class 1259 OID 2994895)
-- Dependencies: 3
-- Name: assurance_sigrec; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE assurance_sigrec (
    id bigint NOT NULL,
    version bigint,
    datedebutvalidite timestamp without time zone,
    datefinvalidite timestamp without time zone,
    numerocontrat character varying(255),
    id_contact bigint,
    id_essai bigint
);




--
-- TOC entry 136 (class 1259 OID 2994893)
-- Dependencies: 3 137
-- Name: assurance_sigrec_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE assurance_sigrec_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3101 (class 0 OID 0)
-- Dependencies: 136
-- Name: assurance_sigrec_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE assurance_sigrec_id_seq OWNED BY assurance_sigrec.id;


--
-- TOC entry 139 (class 1259 OID 2994903)
-- Dependencies: 3
-- Name: bras; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE bras (
    id bigint NOT NULL,
    version bigint,
    description text,
    nom character varying(255),
    type character varying(255),
    id_detail_design bigint NOT NULL,
    id_bras_parent bigint
);




--
-- TOC entry 138 (class 1259 OID 2994901)
-- Dependencies: 139 3
-- Name: bras_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE bras_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3102 (class 0 OID 0)
-- Dependencies: 138
-- Name: bras_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE bras_id_seq OWNED BY bras.id;


--
-- TOC entry 141 (class 1259 OID 2994914)
-- Dependencies: 3
-- Name: categorie; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE categorie (
    id bigint NOT NULL,
    version bigint,
    acte character varying(255),
    libelle character varying(255),
    id_theme bigint NOT NULL
);




--
-- TOC entry 140 (class 1259 OID 2994912)
-- Dependencies: 3 141
-- Name: categorie_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE categorie_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3103 (class 0 OID 0)
-- Dependencies: 140
-- Name: categorie_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE categorie_id_seq OWNED BY categorie.id;


--
-- TOC entry 143 (class 1259 OID 2994925)
-- Dependencies: 3
-- Name: centre_sigrec; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE centre_sigrec (
    id bigint NOT NULL,
    version bigint,
    idcentre character varying(255),
    nom character varying(255),
    numero character varying(255),
    numerofiness character varying(255),
    id_contact bigint
);




--
-- TOC entry 142 (class 1259 OID 2994923)
-- Dependencies: 3 143
-- Name: centre_sigrec_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE centre_sigrec_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3104 (class 0 OID 0)
-- Dependencies: 142
-- Name: centre_sigrec_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE centre_sigrec_id_seq OWNED BY centre_sigrec.id;


--
-- TOC entry 145 (class 1259 OID 2994936)
-- Dependencies: 3
-- Name: co_investigateur_sigrec; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE co_investigateur_sigrec (
    id bigint NOT NULL,
    version bigint,
    identifiant character varying(255),
    intervenantid integer,
    numadeli character varying(255),
    titre character varying(255),
    id_contact bigint,
    id_centre bigint,
    id_essai bigint
);




--
-- TOC entry 144 (class 1259 OID 2994934)
-- Dependencies: 3 145
-- Name: co_investigateur_sigrec_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE co_investigateur_sigrec_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3105 (class 0 OID 0)
-- Dependencies: 144
-- Name: co_investigateur_sigrec_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE co_investigateur_sigrec_id_seq OWNED BY co_investigateur_sigrec.id;


--
-- TOC entry 147 (class 1259 OID 2994947)
-- Dependencies: 3
-- Name: conditionnement; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE conditionnement (
    id bigint NOT NULL,
    version bigint,
    contenance numeric(19,2),
    dosage numeric(19,2),
    forme character varying(255),
    libelle character varying(255) NOT NULL,
    modeprescription character varying(255),
    nbuniteprescription numeric(19,2),
    quantiteparpatient integer,
    unitecontenance character varying(255),
    unitedosage character varying(255),
    unitegestion character varying(255),
    uniteprescription character varying(255),
    voieadministration character varying(255),
    id_produit bigint
);




--
-- TOC entry 146 (class 1259 OID 2994945)
-- Dependencies: 3 147
-- Name: conditionnement_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE conditionnement_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3106 (class 0 OID 0)
-- Dependencies: 146
-- Name: conditionnement_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE conditionnement_id_seq OWNED BY conditionnement.id;


--
-- TOC entry 149 (class 1259 OID 2994958)
-- Dependencies: 3
-- Name: contact_sigrec; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE contact_sigrec (
    id bigint NOT NULL,
    version bigint,
    adresse character varying(255),
    civilite character varying(255),
    codepostal character varying(255),
    email character varying(255),
    fax character varying(255),
    nom character varying(255),
    prenom character varying(255),
    raisonsociale character varying(255),
    telephone character varying(255),
    ville character varying(255)
);




--
-- TOC entry 148 (class 1259 OID 2994956)
-- Dependencies: 149 3
-- Name: contact_sigrec_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE contact_sigrec_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3107 (class 0 OID 0)
-- Dependencies: 148
-- Name: contact_sigrec_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE contact_sigrec_id_seq OWNED BY contact_sigrec.id;


--
-- TOC entry 151 (class 1259 OID 2994969)
-- Dependencies: 3
-- Name: cro_sigrec; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE cro_sigrec (
    id bigint NOT NULL,
    version bigint,
    identifiant character varying(255),
    id_contact bigint,
    id_essai bigint
);




--
-- TOC entry 150 (class 1259 OID 2994967)
-- Dependencies: 151 3
-- Name: cro_sigrec_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cro_sigrec_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3108 (class 0 OID 0)
-- Dependencies: 150
-- Name: cro_sigrec_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE cro_sigrec_id_seq OWNED BY cro_sigrec.id;


--
-- TOC entry 153 (class 1259 OID 2994977)
-- Dependencies: 3
-- Name: dispensation; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE dispensation (
    id bigint NOT NULL,
    version bigint,
    commentaire text,
    datedispensation timestamp without time zone,
    dispense boolean,
    numordonnancier integer,
    id_ordonnancier bigint,
    id_pharmacie bigint NOT NULL,
    id_prescription bigint NOT NULL
);




--
-- TOC entry 152 (class 1259 OID 2994975)
-- Dependencies: 153 3
-- Name: dispensation_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE dispensation_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3109 (class 0 OID 0)
-- Dependencies: 152
-- Name: dispensation_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE dispensation_id_seq OWNED BY dispensation.id;


--
-- TOC entry 154 (class 1259 OID 2994986)
-- Dependencies: 3
-- Name: dispositif_medical; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE dispositif_medical (
    classe character varying(255),
    codelogiciel character varying(255),
    codelppr character varying(255),
    fournisseur character varying(255),
    marquagece boolean,
    modele character varying(255),
    nature character varying(255),
    nomenclature character varying(255),
    numeromarche character varying(255),
    randomisation boolean,
    statutlpp character varying(255),
    id bigint NOT NULL
);




--
-- TOC entry 156 (class 1259 OID 2994996)
-- Dependencies: 3
-- Name: donnees_prevision; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE donnees_prevision (
    id bigint NOT NULL,
    version bigint,
    nbannees integer,
    nbapprovisionnements integer,
    nbaudits integer,
    nbdestructions integer,
    nbdispensations integer,
    nbdispensationsrenouvellement integer,
    nbinclusions integer,
    nbpreparationsnonsteriles integer,
    nbpreparationssteriles integer,
    nbprescriptions integer,
    nbreetiquetages integer,
    nbvisitemonitoring integer,
    id_detail_surcout bigint
);




--
-- TOC entry 155 (class 1259 OID 2994994)
-- Dependencies: 156 3
-- Name: donnees_prevision_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE donnees_prevision_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3110 (class 0 OID 0)
-- Dependencies: 155
-- Name: donnees_prevision_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE donnees_prevision_id_seq OWNED BY donnees_prevision.id;


--
-- TOC entry 158 (class 1259 OID 2995004)
-- Dependencies: 3
-- Name: dotation; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE dotation (
    id bigint NOT NULL,
    version bigint,
    commentaire text,
    datedemande timestamp without time zone NOT NULL,
    quantite integer NOT NULL,
    traitee boolean NOT NULL,
    id_conditionnement bigint NOT NULL,
    id_essai bigint NOT NULL,
    id_personne bigint NOT NULL,
    id_pharmacie bigint NOT NULL,
    id_produit bigint NOT NULL,
    id_service bigint NOT NULL
);




--
-- TOC entry 157 (class 1259 OID 2995002)
-- Dependencies: 3 158
-- Name: dotation_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE dotation_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3111 (class 0 OID 0)
-- Dependencies: 157
-- Name: dotation_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE dotation_id_seq OWNED BY dotation.id;


--
-- TOC entry 160 (class 1259 OID 2995015)
-- Dependencies: 3
-- Name: element_to_check; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE element_to_check (
    id bigint NOT NULL,
    version bigint,
    checked boolean,
    commentaire text,
    datechecked timestamp without time zone,
    nomchamps character varying(255),
    numordonnancier integer,
    type character varying(255),
    id_personne bigint,
    id_dispensation bigint NOT NULL,
    id_ordonnancier bigint,
    id_produitprescrit bigint NOT NULL
);




--
-- TOC entry 159 (class 1259 OID 2995013)
-- Dependencies: 160 3
-- Name: element_to_check_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE element_to_check_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3112 (class 0 OID 0)
-- Dependencies: 159
-- Name: element_to_check_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE element_to_check_id_seq OWNED BY element_to_check.id;


--
-- TOC entry 162 (class 1259 OID 2995026)
-- Dependencies: 3
-- Name: essai; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE essai (
    id bigint NOT NULL,
    version bigint,
    alerteactive boolean,
    anneecreation integer,
    codepromoteur character varying(255),
    conv_date timestamp without time zone,
    dci character varying(255),
    emplacementdossier character varying(255),
    etat character varying(255),
    libelleproduitevalue character varying(255),
    nom character varying(255),
    numinterne character varying(255),
    typepromoteur character varying(255),
    id_pharma bigint NOT NULL,
    id_promoteur bigint NOT NULL
);




--
-- TOC entry 164 (class 1259 OID 2995037)
-- Dependencies: 3
-- Name: essai_commentaire_detail_administratif_archi; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE essai_commentaire_detail_administratif_archi (
    id bigint NOT NULL,
    version bigint,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    libelle text NOT NULL,
    id_detailadministratif bigint NOT NULL
);




--
-- TOC entry 163 (class 1259 OID 2995035)
-- Dependencies: 164 3
-- Name: essai_commentaire_detail_administratif_archi_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE essai_commentaire_detail_administratif_archi_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3113 (class 0 OID 0)
-- Dependencies: 163
-- Name: essai_commentaire_detail_administratif_archi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE essai_commentaire_detail_administratif_archi_id_seq OWNED BY essai_commentaire_detail_administratif_archi.id;


--
-- TOC entry 166 (class 1259 OID 2995048)
-- Dependencies: 3
-- Name: essai_commentaire_detail_faisabilite; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE essai_commentaire_detail_faisabilite (
    type character varying(31) NOT NULL,
    id bigint NOT NULL,
    version bigint,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    libelle text NOT NULL,
    id_detailfaisabilite bigint NOT NULL
);




--
-- TOC entry 165 (class 1259 OID 2995046)
-- Dependencies: 166 3
-- Name: essai_commentaire_detail_faisabilite_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE essai_commentaire_detail_faisabilite_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3114 (class 0 OID 0)
-- Dependencies: 165
-- Name: essai_commentaire_detail_faisabilite_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE essai_commentaire_detail_faisabilite_id_seq OWNED BY essai_commentaire_detail_faisabilite.id;


--
-- TOC entry 168 (class 1259 OID 2995059)
-- Dependencies: 3
-- Name: essai_commentaire_detail_recherche; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE essai_commentaire_detail_recherche (
    id bigint NOT NULL,
    version bigint,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    libelle text NOT NULL,
    id_detailrecherche bigint NOT NULL
);




--
-- TOC entry 167 (class 1259 OID 2995057)
-- Dependencies: 3 168
-- Name: essai_commentaire_detail_recherche_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE essai_commentaire_detail_recherche_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3115 (class 0 OID 0)
-- Dependencies: 167
-- Name: essai_commentaire_detail_recherche_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE essai_commentaire_detail_recherche_id_seq OWNED BY essai_commentaire_detail_recherche.id;


--
-- TOC entry 170 (class 1259 OID 2995070)
-- Dependencies: 3
-- Name: essai_detail_administratif; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE essai_detail_administratif (
    id bigint NOT NULL,
    version bigint,
    ac_accord boolean,
    ac_date timestamp without time zone,
    ac_docsdosspapier boolean,
    ac_nom character varying(255),
    ac_numident character varying(255),
    ac_type character varying(255),
    arc_date timestamp without time zone,
    arc_duree integer,
    arc_ident character varying(255),
    arc_lieu character varying(255),
    assur_code_postal character varying(255),
    assur_date_debut_validite timestamp without time zone,
    assur_date_fin_validite timestamp without time zone,
    assur_docsdosspapier boolean,
    assur_nom_compagnie character varying(255),
    assur_numero_avenant character varying(255),
    assur_numero_contrat character varying(255),
    assur_ville character varying(255),
    autorisationdistribution_docsdosspapier boolean,
    autorisationimportation_docsdosspapier boolean,
    bropro_docsdosspapier boolean,
    cpp_accord boolean,
    cpp_date timestamp without time zone,
    cpp_docsdosspapier boolean,
    cpp_nom character varying(255),
    conv_signee boolean,
    conv_docsdosspapier boolean,
    proto_docsdosspapier boolean,
    id_essai bigint
);




--
-- TOC entry 169 (class 1259 OID 2995068)
-- Dependencies: 3 170
-- Name: essai_detail_administratif_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE essai_detail_administratif_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3116 (class 0 OID 0)
-- Dependencies: 169
-- Name: essai_detail_administratif_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE essai_detail_administratif_id_seq OWNED BY essai_detail_administratif.id;


--
-- TOC entry 172 (class 1259 OID 2995081)
-- Dependencies: 3
-- Name: essai_detail_administratif_suivi; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE essai_detail_administratif_suivi (
    id bigint NOT NULL,
    version bigint,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_detail_administratif bigint NOT NULL
);




--
-- TOC entry 171 (class 1259 OID 2995079)
-- Dependencies: 172 3
-- Name: essai_detail_administratif_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE essai_detail_administratif_suivi_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3117 (class 0 OID 0)
-- Dependencies: 171
-- Name: essai_detail_administratif_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE essai_detail_administratif_suivi_id_seq OWNED BY essai_detail_administratif_suivi.id;


--
-- TOC entry 174 (class 1259 OID 2995089)
-- Dependencies: 3
-- Name: essai_detail_autres_documents; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE essai_detail_autres_documents (
    id bigint NOT NULL,
    version bigint,
    id_essai bigint
);




--
-- TOC entry 173 (class 1259 OID 2995087)
-- Dependencies: 174 3
-- Name: essai_detail_autres_documents_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE essai_detail_autres_documents_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3118 (class 0 OID 0)
-- Dependencies: 173
-- Name: essai_detail_autres_documents_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE essai_detail_autres_documents_id_seq OWNED BY essai_detail_autres_documents.id;


--
-- TOC entry 176 (class 1259 OID 2995097)
-- Dependencies: 3
-- Name: essai_detail_autres_documents_suivi; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE essai_detail_autres_documents_suivi (
    id bigint NOT NULL,
    version bigint,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_detail_autres_documents bigint NOT NULL
);




--
-- TOC entry 175 (class 1259 OID 2995095)
-- Dependencies: 3 176
-- Name: essai_detail_autres_documents_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE essai_detail_autres_documents_suivi_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3119 (class 0 OID 0)
-- Dependencies: 175
-- Name: essai_detail_autres_documents_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE essai_detail_autres_documents_suivi_id_seq OWNED BY essai_detail_autres_documents_suivi.id;


--
-- TOC entry 178 (class 1259 OID 2995105)
-- Dependencies: 3
-- Name: essai_detail_contacts; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE essai_detail_contacts (
    id bigint NOT NULL,
    version bigint,
    id_essai bigint
);




--
-- TOC entry 177 (class 1259 OID 2995103)
-- Dependencies: 3 178
-- Name: essai_detail_contacts_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE essai_detail_contacts_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3120 (class 0 OID 0)
-- Dependencies: 177
-- Name: essai_detail_contacts_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE essai_detail_contacts_id_seq OWNED BY essai_detail_contacts.id;


--
-- TOC entry 180 (class 1259 OID 2995113)
-- Dependencies: 3
-- Name: essai_detail_contacts_suivi; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE essai_detail_contacts_suivi (
    id bigint NOT NULL,
    version bigint,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_detail_contacts bigint NOT NULL
);




--
-- TOC entry 179 (class 1259 OID 2995111)
-- Dependencies: 180 3
-- Name: essai_detail_contacts_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE essai_detail_contacts_suivi_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3121 (class 0 OID 0)
-- Dependencies: 179
-- Name: essai_detail_contacts_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE essai_detail_contacts_suivi_id_seq OWNED BY essai_detail_contacts_suivi.id;


--
-- TOC entry 182 (class 1259 OID 2995121)
-- Dependencies: 3
-- Name: essai_detail_dates; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE essai_detail_dates (
    id bigint NOT NULL,
    version bigint,
    activation timestamp without time zone,
    activationprev timestamp without time zone,
    cloture timestamp without time zone,
    debutetude timestamp without time zone,
    debutetudeprev timestamp without time zone,
    debutinclusion timestamp without time zone,
    debutinclusionprev timestamp without time zone,
    findispensations timestamp without time zone,
    finetude timestamp without time zone,
    finetudeprev timestamp without time zone,
    fininclusion timestamp without time zone,
    fininclusionprev timestamp without time zone,
    precloture timestamp without time zone,
    datereception timestamp without time zone,
    id_essai bigint
);




--
-- TOC entry 181 (class 1259 OID 2995119)
-- Dependencies: 3 182
-- Name: essai_detail_dates_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE essai_detail_dates_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3122 (class 0 OID 0)
-- Dependencies: 181
-- Name: essai_detail_dates_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE essai_detail_dates_id_seq OWNED BY essai_detail_dates.id;


--
-- TOC entry 184 (class 1259 OID 2995129)
-- Dependencies: 3
-- Name: essai_detail_dates_suivi; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE essai_detail_dates_suivi (
    id bigint NOT NULL,
    version bigint,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_detail_dates bigint NOT NULL
);




--
-- TOC entry 183 (class 1259 OID 2995127)
-- Dependencies: 184 3
-- Name: essai_detail_dates_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE essai_detail_dates_suivi_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3123 (class 0 OID 0)
-- Dependencies: 183
-- Name: essai_detail_dates_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE essai_detail_dates_suivi_id_seq OWNED BY essai_detail_dates_suivi.id;


--
-- TOC entry 186 (class 1259 OID 2995137)
-- Dependencies: 3
-- Name: essai_detail_design; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE essai_detail_design (
    id bigint NOT NULL,
    version bigint,
    typedesign character varying(255),
    id_essai bigint
);




--
-- TOC entry 185 (class 1259 OID 2995135)
-- Dependencies: 3 186
-- Name: essai_detail_design_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE essai_detail_design_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3124 (class 0 OID 0)
-- Dependencies: 185
-- Name: essai_detail_design_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE essai_detail_design_id_seq OWNED BY essai_detail_design.id;


--
-- TOC entry 188 (class 1259 OID 2995145)
-- Dependencies: 3
-- Name: essai_detail_design_suivi; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE essai_detail_design_suivi (
    id bigint NOT NULL,
    version bigint,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_detail_design bigint NOT NULL
);




--
-- TOC entry 187 (class 1259 OID 2995143)
-- Dependencies: 3 188
-- Name: essai_detail_design_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE essai_detail_design_suivi_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3125 (class 0 OID 0)
-- Dependencies: 187
-- Name: essai_detail_design_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE essai_detail_design_suivi_id_seq OWNED BY essai_detail_design_suivi.id;


--
-- TOC entry 190 (class 1259 OID 2995153)
-- Dependencies: 3
-- Name: essai_detail_etat; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE essai_detail_etat (
    id bigint NOT NULL,
    version bigint,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    commentaire text,
    etat character varying(255) NOT NULL,
    id_essai bigint NOT NULL
);




--
-- TOC entry 189 (class 1259 OID 2995151)
-- Dependencies: 3 190
-- Name: essai_detail_etat_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE essai_detail_etat_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3126 (class 0 OID 0)
-- Dependencies: 189
-- Name: essai_detail_etat_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE essai_detail_etat_id_seq OWNED BY essai_detail_etat.id;


--
-- TOC entry 192 (class 1259 OID 2995164)
-- Dependencies: 3
-- Name: essai_detail_faisabilite; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE essai_detail_faisabilite (
    id bigint NOT NULL,
    version bigint,
    concl_convsignee boolean,
    concl_date timestamp without time zone,
    concl_favorable boolean,
    etude_accordpharmacentrale boolean,
    etude_achatspui boolean,
    etude_circuitdef boolean,
    etude_conditionnement boolean,
    etude_dmdimportation boolean,
    etude_disppossible boolean,
    etude_distribautrespharmas boolean,
    etude_donneesstabilite boolean,
    etude_etiquetages boolean,
    etude_gestionaveugle boolean,
    etude_preparations boolean,
    etude_prestapharma boolean,
    etude_randompharma boolean,
    etude_randomengarde boolean,
    etude_reconstitutions boolean,
    etude_referencement boolean,
    etude_soctranspdef boolean,
    etude_suivistocks boolean,
    etude_suivitemp boolean,
    id_essai bigint
);




--
-- TOC entry 191 (class 1259 OID 2995162)
-- Dependencies: 3 192
-- Name: essai_detail_faisabilite_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE essai_detail_faisabilite_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3127 (class 0 OID 0)
-- Dependencies: 191
-- Name: essai_detail_faisabilite_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE essai_detail_faisabilite_id_seq OWNED BY essai_detail_faisabilite.id;


--
-- TOC entry 193 (class 1259 OID 2995170)
-- Dependencies: 3
-- Name: essai_detail_faisabilite_service; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE essai_detail_faisabilite_service (
    id_essai bigint NOT NULL,
    id_service bigint NOT NULL
);




--
-- TOC entry 195 (class 1259 OID 2995177)
-- Dependencies: 3
-- Name: essai_detail_faisabilite_suivi; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE essai_detail_faisabilite_suivi (
    id bigint NOT NULL,
    version bigint,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_detail_faisabilite bigint NOT NULL
);




--
-- TOC entry 194 (class 1259 OID 2995175)
-- Dependencies: 3 195
-- Name: essai_detail_faisabilite_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE essai_detail_faisabilite_suivi_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3128 (class 0 OID 0)
-- Dependencies: 194
-- Name: essai_detail_faisabilite_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE essai_detail_faisabilite_suivi_id_seq OWNED BY essai_detail_faisabilite_suivi.id;


--
-- TOC entry 197 (class 1259 OID 2995185)
-- Dependencies: 3
-- Name: essai_detail_pharma; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE essai_detail_pharma (
    id bigint NOT NULL,
    version bigint,
    formationspecifique boolean,
    envoistraitement boolean,
    gestionretour character varying(255),
    modalitedestruction text,
    modalitereception text,
    responsabilitecommande character varying(255),
    responsabiliteinsu character varying(255),
    responsabiliterandomisation character varying(255),
    typeretour character varying(255),
    aidedispensation text,
    conseilpatient text,
    contreetiquette text,
    destinataireinvestigateur boolean,
    destinatairepatient boolean,
    destinataireservice boolean,
    informationconditionnement text,
    numerotationconditionnement text,
    tracabilitepatient boolean,
    typedispensation character varying(255),
    dureetotalepatientprevue numeric(19,2),
    dureetotaleprevue numeric(19,2),
    nbcentresprevus integer,
    nbpatientsprevus integer,
    nbpatientsprevustotal integer,
    numerocentre character varying(255),
    qualiteinsu integer,
    typeproduitevalue character varying(255),
    unitedureetotalepatientprevue character varying(255),
    unitedureetotaleprevue character varying(255),
    id_essai bigint
);




--
-- TOC entry 198 (class 1259 OID 2995194)
-- Dependencies: 3
-- Name: essai_detail_pharma_etablissement; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE essai_detail_pharma_etablissement (
    id_detail_pharma bigint NOT NULL,
    id_etablissement bigint NOT NULL
);




--
-- TOC entry 196 (class 1259 OID 2995183)
-- Dependencies: 197 3
-- Name: essai_detail_pharma_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE essai_detail_pharma_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3129 (class 0 OID 0)
-- Dependencies: 196
-- Name: essai_detail_pharma_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE essai_detail_pharma_id_seq OWNED BY essai_detail_pharma.id;


--
-- TOC entry 199 (class 1259 OID 2995199)
-- Dependencies: 3
-- Name: essai_detail_pharma_pharmacie; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE essai_detail_pharma_pharmacie (
    id_detail_pharma bigint NOT NULL,
    id_pharmacie bigint NOT NULL
);




--
-- TOC entry 201 (class 1259 OID 2995206)
-- Dependencies: 3
-- Name: essai_detail_pharma_suivi; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE essai_detail_pharma_suivi (
    id bigint NOT NULL,
    version bigint,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_detail_pharma bigint NOT NULL
);




--
-- TOC entry 200 (class 1259 OID 2995204)
-- Dependencies: 201 3
-- Name: essai_detail_pharma_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE essai_detail_pharma_suivi_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3130 (class 0 OID 0)
-- Dependencies: 200
-- Name: essai_detail_pharma_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE essai_detail_pharma_suivi_id_seq OWNED BY essai_detail_pharma_suivi.id;


--
-- TOC entry 203 (class 1259 OID 2995214)
-- Dependencies: 3
-- Name: essai_detail_produit; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE essai_detail_produit (
    id bigint NOT NULL,
    version bigint,
    id_essai bigint
);




--
-- TOC entry 202 (class 1259 OID 2995212)
-- Dependencies: 203 3
-- Name: essai_detail_produit_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE essai_detail_produit_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3131 (class 0 OID 0)
-- Dependencies: 202
-- Name: essai_detail_produit_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE essai_detail_produit_id_seq OWNED BY essai_detail_produit.id;


--
-- TOC entry 205 (class 1259 OID 2995222)
-- Dependencies: 3
-- Name: essai_detail_produit_suivi; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE essai_detail_produit_suivi (
    id bigint NOT NULL,
    version bigint,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_detail_produit bigint NOT NULL
);




--
-- TOC entry 204 (class 1259 OID 2995220)
-- Dependencies: 205 3
-- Name: essai_detail_produit_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE essai_detail_produit_suivi_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3132 (class 0 OID 0)
-- Dependencies: 204
-- Name: essai_detail_produit_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE essai_detail_produit_suivi_id_seq OWNED BY essai_detail_produit_suivi.id;


--
-- TOC entry 207 (class 1259 OID 2995230)
-- Dependencies: 3
-- Name: essai_detail_recherche; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE essai_detail_recherche (
    id bigint NOT NULL,
    version bigint,
    motscles character varying(255),
    naturerecherche character varying(255),
    numenregistrement character varying(255),
    objetrecherche character varying(255),
    phaserecherche character varying(255),
    thematique character varying(255),
    titreprotocole text,
    typerecherche character varying(255),
    id_essai bigint
);




--
-- TOC entry 206 (class 1259 OID 2995228)
-- Dependencies: 207 3
-- Name: essai_detail_recherche_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE essai_detail_recherche_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3133 (class 0 OID 0)
-- Dependencies: 206
-- Name: essai_detail_recherche_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE essai_detail_recherche_id_seq OWNED BY essai_detail_recherche.id;


--
-- TOC entry 209 (class 1259 OID 2995241)
-- Dependencies: 3
-- Name: essai_detail_recherche_sigrec; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE essai_detail_recherche_sigrec (
    id bigint NOT NULL,
    version bigint,
    naturerecherche character varying(255),
    numenregistrement character varying(255),
    objetrecherche character varying(255),
    phaserecherche character varying(255),
    qualiteinsu character varying(255),
    titreprotocole text,
    typerecherche character varying(255),
    id_essai bigint
);




--
-- TOC entry 208 (class 1259 OID 2995239)
-- Dependencies: 209 3
-- Name: essai_detail_recherche_sigrec_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE essai_detail_recherche_sigrec_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3134 (class 0 OID 0)
-- Dependencies: 208
-- Name: essai_detail_recherche_sigrec_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE essai_detail_recherche_sigrec_id_seq OWNED BY essai_detail_recherche_sigrec.id;


--
-- TOC entry 211 (class 1259 OID 2995252)
-- Dependencies: 3
-- Name: essai_detail_recherche_suivi; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE essai_detail_recherche_suivi (
    id bigint NOT NULL,
    version bigint,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_detail_recherche bigint NOT NULL
);




--
-- TOC entry 210 (class 1259 OID 2995250)
-- Dependencies: 3 211
-- Name: essai_detail_recherche_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE essai_detail_recherche_suivi_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3135 (class 0 OID 0)
-- Dependencies: 210
-- Name: essai_detail_recherche_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE essai_detail_recherche_suivi_id_seq OWNED BY essai_detail_recherche_suivi.id;


--
-- TOC entry 213 (class 1259 OID 2995260)
-- Dependencies: 3
-- Name: essai_detail_surcout; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE essai_detail_surcout (
    id bigint NOT NULL,
    version bigint,
    id_essai bigint
);




--
-- TOC entry 212 (class 1259 OID 2995258)
-- Dependencies: 213 3
-- Name: essai_detail_surcout_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE essai_detail_surcout_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3136 (class 0 OID 0)
-- Dependencies: 212
-- Name: essai_detail_surcout_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE essai_detail_surcout_id_seq OWNED BY essai_detail_surcout.id;


--
-- TOC entry 215 (class 1259 OID 2995268)
-- Dependencies: 3
-- Name: essai_detail_surcout_suivi; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE essai_detail_surcout_suivi (
    id bigint NOT NULL,
    version bigint,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_detail_surcout bigint NOT NULL
);




--
-- TOC entry 214 (class 1259 OID 2995266)
-- Dependencies: 3 215
-- Name: essai_detail_surcout_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE essai_detail_surcout_suivi_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3137 (class 0 OID 0)
-- Dependencies: 214
-- Name: essai_detail_surcout_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE essai_detail_surcout_suivi_id_seq OWNED BY essai_detail_surcout_suivi.id;


--
-- TOC entry 217 (class 1259 OID 2995276)
-- Dependencies: 3
-- Name: essai_document_detail_administratif; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE essai_document_detail_administratif (
    type character varying(31) NOT NULL,
    id bigint NOT NULL,
    version bigint,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    nomdisque character varying(255),
    nomuser character varying(255),
    commentaire text,
    typedocumentprotocole character varying(255),
    version_doc character varying(255),
    typedocumentbrochure character varying(255),
    id_detailadministratif bigint NOT NULL
);




--
-- TOC entry 216 (class 1259 OID 2995274)
-- Dependencies: 3 217
-- Name: essai_document_detail_administratif_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE essai_document_detail_administratif_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3138 (class 0 OID 0)
-- Dependencies: 216
-- Name: essai_document_detail_administratif_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE essai_document_detail_administratif_id_seq OWNED BY essai_document_detail_administratif.id;


--
-- TOC entry 219 (class 1259 OID 2995287)
-- Dependencies: 3
-- Name: essai_document_detail_autres_documents; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE essai_document_detail_autres_documents (
    id bigint NOT NULL,
    version bigint,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    nomdisque character varying(255),
    nomuser character varying(255),
    commentaire text,
    type character varying(255) NOT NULL,
    id_detail_autres_documents bigint NOT NULL
);




--
-- TOC entry 218 (class 1259 OID 2995285)
-- Dependencies: 219 3
-- Name: essai_document_detail_autres_documents_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE essai_document_detail_autres_documents_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3139 (class 0 OID 0)
-- Dependencies: 218
-- Name: essai_document_detail_autres_documents_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE essai_document_detail_autres_documents_id_seq OWNED BY essai_document_detail_autres_documents.id;


--
-- TOC entry 221 (class 1259 OID 2995298)
-- Dependencies: 3
-- Name: essai_document_detail_pharma; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE essai_document_detail_pharma (
    type character varying(31) NOT NULL,
    id bigint NOT NULL,
    version bigint,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    nomdisque character varying(255),
    nomuser character varying(255),
    commentaire text,
    id_detail_pharma bigint NOT NULL
);




--
-- TOC entry 220 (class 1259 OID 2995296)
-- Dependencies: 221 3
-- Name: essai_document_detail_pharma_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE essai_document_detail_pharma_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3140 (class 0 OID 0)
-- Dependencies: 220
-- Name: essai_document_detail_pharma_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE essai_document_detail_pharma_id_seq OWNED BY essai_document_detail_pharma.id;


--
-- TOC entry 223 (class 1259 OID 2995309)
-- Dependencies: 3
-- Name: essai_document_detail_surcout; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE essai_document_detail_surcout (
    type character varying(31) NOT NULL,
    id bigint NOT NULL,
    version bigint,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    nomdisque character varying(255),
    nomuser character varying(255),
    commentaire text,
    id_detailsurcout bigint NOT NULL
);




--
-- TOC entry 222 (class 1259 OID 2995307)
-- Dependencies: 223 3
-- Name: essai_document_detail_surcout_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE essai_document_detail_surcout_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3141 (class 0 OID 0)
-- Dependencies: 222
-- Name: essai_document_detail_surcout_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE essai_document_detail_surcout_id_seq OWNED BY essai_document_detail_surcout.id;


--
-- TOC entry 161 (class 1259 OID 2995024)
-- Dependencies: 162 3
-- Name: essai_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE essai_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3142 (class 0 OID 0)
-- Dependencies: 161
-- Name: essai_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE essai_id_seq OWNED BY essai.id;


--
-- TOC entry 224 (class 1259 OID 2995318)
-- Dependencies: 3
-- Name: essai_service; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE essai_service (
    id_essai bigint NOT NULL,
    id_service bigint NOT NULL
);




--
-- TOC entry 226 (class 1259 OID 2995325)
-- Dependencies: 3
-- Name: essai_sigrec; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE essai_sigrec (
    id bigint NOT NULL,
    version bigint,
    codepromoteur character varying(255),
    multicentrique boolean,
    nbcentres integer,
    nom character varying(255),
    numidentac character varying(255),
    typepromoteur character varying(255),
    id_investigateurprincipal bigint,
    id_promoteur bigint
);




--
-- TOC entry 225 (class 1259 OID 2995323)
-- Dependencies: 226 3
-- Name: essai_sigrec_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE essai_sigrec_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3143 (class 0 OID 0)
-- Dependencies: 225
-- Name: essai_sigrec_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE essai_sigrec_id_seq OWNED BY essai_sigrec.id;


--
-- TOC entry 228 (class 1259 OID 2995336)
-- Dependencies: 3
-- Name: essai_suivi; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE essai_suivi (
    id bigint NOT NULL,
    version bigint,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_essai bigint NOT NULL
);




--
-- TOC entry 227 (class 1259 OID 2995334)
-- Dependencies: 228 3
-- Name: essai_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE essai_suivi_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3144 (class 0 OID 0)
-- Dependencies: 227
-- Name: essai_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE essai_suivi_id_seq OWNED BY essai_suivi.id;


--
-- TOC entry 230 (class 1259 OID 2995344)
-- Dependencies: 3
-- Name: etablissement; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE etablissement (
    id bigint NOT NULL,
    version bigint,
    adressedirection text,
    codepostal character varying(255),
    fax character varying(255),
    mail character varying(255),
    nom character varying(255) NOT NULL,
    pays character varying(255),
    telephone character varying(255),
    ville character varying(255)
);




--
-- TOC entry 229 (class 1259 OID 2995342)
-- Dependencies: 230 3
-- Name: etablissement_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE etablissement_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3145 (class 0 OID 0)
-- Dependencies: 229
-- Name: etablissement_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE etablissement_id_seq OWNED BY etablissement.id;


--
-- TOC entry 232 (class 1259 OID 2995355)
-- Dependencies: 3
-- Name: etablissement_suivi; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE etablissement_suivi (
    id bigint NOT NULL,
    version bigint,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_etablissement bigint NOT NULL
);




--
-- TOC entry 231 (class 1259 OID 2995353)
-- Dependencies: 232 3
-- Name: etablissement_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE etablissement_suivi_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3146 (class 0 OID 0)
-- Dependencies: 231
-- Name: etablissement_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE etablissement_suivi_id_seq OWNED BY etablissement_suivi.id;


--
-- TOC entry 234 (class 1259 OID 2995363)
-- Dependencies: 3
-- Name: evenement; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE evenement (
    id bigint NOT NULL,
    version bigint,
    arc character varying(255),
    commentaire character varying(255),
    datedebut timestamp without time zone NOT NULL,
    datefin timestamp without time zone NOT NULL,
    datereception timestamp without time zone,
    destinataire character varying(255),
    heuredebut character varying(255),
    heurefin character varying(255),
    journee boolean,
    libelle character varying(255),
    nombre integer,
    personnelpharmacie text,
    realisepar character varying(255),
    resultatvisite character varying(255),
    typeevenement character varying(255) NOT NULL,
    typevisite character varying(255),
    validation character varying(255),
    id_essai bigint,
    id_personne bigint
);




--
-- TOC entry 233 (class 1259 OID 2995361)
-- Dependencies: 3 234
-- Name: evenement_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE evenement_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3147 (class 0 OID 0)
-- Dependencies: 233
-- Name: evenement_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE evenement_id_seq OWNED BY evenement.id;


--
-- TOC entry 236 (class 1259 OID 2995374)
-- Dependencies: 3
-- Name: evenement_suivi; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE evenement_suivi (
    id bigint NOT NULL,
    version bigint,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_evenement bigint NOT NULL
);




--
-- TOC entry 235 (class 1259 OID 2995372)
-- Dependencies: 3 236
-- Name: evenement_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE evenement_suivi_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3148 (class 0 OID 0)
-- Dependencies: 235
-- Name: evenement_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE evenement_suivi_id_seq OWNED BY evenement_suivi.id;


--
-- TOC entry 238 (class 1259 OID 2995382)
-- Dependencies: 3
-- Name: grille; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE grille (
    id bigint NOT NULL,
    version bigint,
    id_detail_surcout bigint,
    id_grille_modele bigint NOT NULL
);




--
-- TOC entry 237 (class 1259 OID 2995380)
-- Dependencies: 238 3
-- Name: grille_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE grille_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3149 (class 0 OID 0)
-- Dependencies: 237
-- Name: grille_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE grille_id_seq OWNED BY grille.id;


--
-- TOC entry 240 (class 1259 OID 2995390)
-- Dependencies: 3
-- Name: grille_modele; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE grille_modele (
    id bigint NOT NULL,
    version bigint,
    datecreation timestamp without time zone,
    datedebut timestamp without time zone,
    datefin timestamp without time zone,
    nom character varying(255)
);




--
-- TOC entry 239 (class 1259 OID 2995388)
-- Dependencies: 3 240
-- Name: grille_modele_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE grille_modele_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3150 (class 0 OID 0)
-- Dependencies: 239
-- Name: grille_modele_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE grille_modele_id_seq OWNED BY grille_modele.id;


--
-- TOC entry 242 (class 1259 OID 2995398)
-- Dependencies: 3
-- Name: habilitation; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE habilitation (
    id bigint NOT NULL,
    version bigint,
    active boolean NOT NULL,
    creepar character varying(255) NOT NULL,
    desactivepar character varying(255),
    creele timestamp without time zone NOT NULL,
    desactivele timestamp without time zone,
    desactivable boolean NOT NULL,
    droit character varying(255) NOT NULL,
    id_detail_contacts bigint NOT NULL,
    id_personne bigint NOT NULL
);




--
-- TOC entry 241 (class 1259 OID 2995396)
-- Dependencies: 3 242
-- Name: habilitation_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE habilitation_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3151 (class 0 OID 0)
-- Dependencies: 241
-- Name: habilitation_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE habilitation_id_seq OWNED BY habilitation.id;


--
-- TOC entry 244 (class 1259 OID 2995409)
-- Dependencies: 3
-- Name: historique_patient; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE historique_patient (
    id bigint NOT NULL,
    version bigint,
    commentaire text,
    date timestamp without time zone,
    poid double precision,
    surfacecorporelle double precision,
    taille double precision,
    id_patient bigint NOT NULL
);




--
-- TOC entry 243 (class 1259 OID 2995407)
-- Dependencies: 3 244
-- Name: historique_patient_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE historique_patient_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3152 (class 0 OID 0)
-- Dependencies: 243
-- Name: historique_patient_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE historique_patient_id_seq OWNED BY historique_patient.id;


--
-- TOC entry 246 (class 1259 OID 2995420)
-- Dependencies: 3
-- Name: incident; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE incident (
    id bigint NOT NULL,
    version bigint,
    commentaire text,
    date timestamp without time zone NOT NULL,
    libelle character varying(255) NOT NULL,
    id_essai bigint NOT NULL
);




--
-- TOC entry 245 (class 1259 OID 2995418)
-- Dependencies: 246 3
-- Name: incident_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE incident_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3153 (class 0 OID 0)
-- Dependencies: 245
-- Name: incident_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE incident_id_seq OWNED BY incident.id;


--
-- TOC entry 248 (class 1259 OID 2995431)
-- Dependencies: 3
-- Name: incident_suivi; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE incident_suivi (
    id bigint NOT NULL,
    version bigint,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_incident bigint NOT NULL
);




--
-- TOC entry 247 (class 1259 OID 2995429)
-- Dependencies: 248 3
-- Name: incident_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE incident_suivi_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3154 (class 0 OID 0)
-- Dependencies: 247
-- Name: incident_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE incident_suivi_id_seq OWNED BY incident_suivi.id;


--
-- TOC entry 250 (class 1259 OID 2995439)
-- Dependencies: 3
-- Name: inclusion; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE inclusion (
    id bigint NOT NULL,
    version bigint,
    actif boolean,
    datedesinclusion timestamp without time zone,
    dateinclusion timestamp without time zone,
    numinclusion character varying(255),
    numrandomisation character varying(255),
    id_essai bigint NOT NULL,
    id_patient bigint NOT NULL
);




--
-- TOC entry 249 (class 1259 OID 2995437)
-- Dependencies: 3 250
-- Name: inclusion_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE inclusion_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3155 (class 0 OID 0)
-- Dependencies: 249
-- Name: inclusion_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE inclusion_id_seq OWNED BY inclusion.id;


--
-- TOC entry 251 (class 1259 OID 2995448)
-- Dependencies: 3
-- Name: investigateur_service; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE investigateur_service (
    id_investigateur bigint NOT NULL,
    id_service bigint NOT NULL
);




--
-- TOC entry 253 (class 1259 OID 2995455)
-- Dependencies: 3
-- Name: investigateur_sigrec; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE investigateur_sigrec (
    id bigint NOT NULL,
    version bigint,
    identifiant character varying(255),
    intervenantid integer,
    numadeli character varying(255),
    titre character varying(255),
    id_contact bigint,
    id_centre bigint
);




--
-- TOC entry 252 (class 1259 OID 2995453)
-- Dependencies: 253 3
-- Name: investigateur_sigrec_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE investigateur_sigrec_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3156 (class 0 OID 0)
-- Dependencies: 252
-- Name: investigateur_sigrec_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE investigateur_sigrec_id_seq OWNED BY investigateur_sigrec.id;


--
-- TOC entry 255 (class 1259 OID 2995466)
-- Dependencies: 3
-- Name: item; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE item (
    id bigint NOT NULL,
    version bigint,
    acte character varying(255),
    categorie character varying(255),
    theme character varying(255),
    id_grille bigint NOT NULL
);




--
-- TOC entry 254 (class 1259 OID 2995464)
-- Dependencies: 3 255
-- Name: item_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE item_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3157 (class 0 OID 0)
-- Dependencies: 254
-- Name: item_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE item_id_seq OWNED BY item.id;


--
-- TOC entry 256 (class 1259 OID 2995475)
-- Dependencies: 3
-- Name: item_regle; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE item_regle (
    id_item bigint NOT NULL,
    id_regle bigint NOT NULL
);




--
-- TOC entry 258 (class 1259 OID 2995482)
-- Dependencies: 3
-- Name: lignestock; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE lignestock (
    id bigint NOT NULL,
    version bigint,
    approapprouve boolean,
    dateperemption timestamp without time zone,
    numlot character varying(255),
    numtraitement character varying(255),
    quantite_dispensation_en_stock integer NOT NULL,
    quantite_global integer NOT NULL,
    stockage character varying(255),
    id_conditionnement bigint NOT NULL,
    id_essai bigint NOT NULL,
    id_pharmacie bigint NOT NULL,
    id_produit bigint NOT NULL
);




--
-- TOC entry 257 (class 1259 OID 2995480)
-- Dependencies: 258 3
-- Name: lignestock_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE lignestock_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3158 (class 0 OID 0)
-- Dependencies: 257
-- Name: lignestock_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE lignestock_id_seq OWNED BY lignestock.id;


--
-- TOC entry 259 (class 1259 OID 2995491)
-- Dependencies: 3
-- Name: medicament; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE medicament (
    conditionnement boolean,
    dci character varying(255),
    etiquetage boolean,
    fabrication boolean,
    mds boolean,
    nature character varying(255),
    reconstitutionpsm boolean,
    reconstitutionsimple boolean,
    stupefiant boolean,
    id bigint NOT NULL
);




--
-- TOC entry 260 (class 1259 OID 2995499)
-- Dependencies: 3
-- Name: mvt_approvisionnement; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE mvt_approvisionnement (
    commentaireextensionperemption text,
    commentairerefus text,
    datearriveecolis date,
    dateperemption date,
    datereception timestamp without time zone,
    extensionperemption boolean,
    historiqueextensionperemption text,
    motifrefus character varying(255),
    id bigint NOT NULL
);




--
-- TOC entry 261 (class 1259 OID 2995507)
-- Dependencies: 3
-- Name: mvt_autre_sortie; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE mvt_autre_sortie (
    commentaire text,
    commentaireraison text,
    raisonsortie character varying(255),
    id bigint NOT NULL
);




--
-- TOC entry 262 (class 1259 OID 2995515)
-- Dependencies: 3
-- Name: mvt_cession_pui; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE mvt_cession_pui (
    commentaire text,
    commentaireraison text,
    raisonsortie character varying(255),
    id bigint NOT NULL,
    id_pharmaciedest bigint
);




--
-- TOC entry 263 (class 1259 OID 2995523)
-- Dependencies: 3
-- Name: mvt_destruction; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE mvt_destruction (
    commentaire text,
    commentaireraison text,
    raisonsortie character varying(255),
    id bigint NOT NULL
);




--
-- TOC entry 264 (class 1259 OID 2995531)
-- Dependencies: 3
-- Name: mvt_dispensation; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE mvt_dispensation (
    id bigint NOT NULL,
    id_dispensation bigint NOT NULL,
    id_produitprescrit bigint NOT NULL
);




--
-- TOC entry 265 (class 1259 OID 2995536)
-- Dependencies: 3
-- Name: mvt_dispensation_globale; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE mvt_dispensation_globale (
    quantitedispensee integer,
    id bigint NOT NULL,
    id_dotation bigint NOT NULL
);




--
-- TOC entry 266 (class 1259 OID 2995541)
-- Dependencies: 3
-- Name: mvt_preparation; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE mvt_preparation (
    id bigint NOT NULL
);




--
-- TOC entry 267 (class 1259 OID 2995546)
-- Dependencies: 3
-- Name: mvt_preparationentree; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE mvt_preparationentree (
    composition text,
    datefabrication timestamp without time zone,
    numordonnancier integer,
    sterile boolean,
    id bigint NOT NULL,
    id_ordonnancier bigint
);




--
-- TOC entry 268 (class 1259 OID 2995554)
-- Dependencies: 3
-- Name: mvt_retour_promoteur; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE mvt_retour_promoteur (
    commentaire text,
    commentaireraison text,
    nomsocietetransport character varying(255),
    raisonsortie character varying(255),
    referenceenvoi character varying(255),
    id bigint NOT NULL
);




--
-- TOC entry 270 (class 1259 OID 2995564)
-- Dependencies: 3
-- Name: mvtstock; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE mvtstock (
    id bigint NOT NULL,
    version bigint,
    approapprouve boolean NOT NULL,
    datecreation timestamp without time zone NOT NULL,
    dateperemption timestamp without time zone,
    numlot character varying(255) NOT NULL,
    numtraitement character varying(255),
    quantite integer NOT NULL,
    type character varying(255),
    id_conditionnement bigint NOT NULL,
    id_essai bigint NOT NULL,
    id_personne bigint NOT NULL,
    id_pharmacie bigint NOT NULL,
    id_produit bigint NOT NULL
);




--
-- TOC entry 272 (class 1259 OID 2995575)
-- Dependencies: 3
-- Name: mvtstock_document; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE mvtstock_document (
    type character varying(31) NOT NULL,
    id bigint NOT NULL,
    version bigint,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    nomdisque character varying(255),
    nomuser character varying(255),
    id_mvtstock bigint NOT NULL
);




--
-- TOC entry 271 (class 1259 OID 2995573)
-- Dependencies: 272 3
-- Name: mvtstock_document_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE mvtstock_document_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3159 (class 0 OID 0)
-- Dependencies: 271
-- Name: mvtstock_document_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE mvtstock_document_id_seq OWNED BY mvtstock_document.id;


--
-- TOC entry 269 (class 1259 OID 2995562)
-- Dependencies: 270 3
-- Name: mvtstock_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE mvtstock_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3160 (class 0 OID 0)
-- Dependencies: 269
-- Name: mvtstock_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE mvtstock_id_seq OWNED BY mvtstock.id;


--
-- TOC entry 274 (class 1259 OID 2995586)
-- Dependencies: 3
-- Name: ordonnancier_dispensation; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE ordonnancier_dispensation (
    id bigint NOT NULL,
    version bigint,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    datedebut date NOT NULL,
    datefin date NOT NULL,
    id_pharma bigint NOT NULL
);




--
-- TOC entry 273 (class 1259 OID 2995584)
-- Dependencies: 274 3
-- Name: ordonnancier_dispensation_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE ordonnancier_dispensation_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3161 (class 0 OID 0)
-- Dependencies: 273
-- Name: ordonnancier_dispensation_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE ordonnancier_dispensation_id_seq OWNED BY ordonnancier_dispensation.id;


--
-- TOC entry 276 (class 1259 OID 2995594)
-- Dependencies: 3
-- Name: ordonnancier_fab_reconst; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE ordonnancier_fab_reconst (
    id bigint NOT NULL,
    version bigint,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    datedebut date NOT NULL,
    datefin date NOT NULL,
    id_pharma bigint NOT NULL
);




--
-- TOC entry 275 (class 1259 OID 2995592)
-- Dependencies: 276 3
-- Name: ordonnancier_fab_reconst_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE ordonnancier_fab_reconst_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3162 (class 0 OID 0)
-- Dependencies: 275
-- Name: ordonnancier_fab_reconst_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE ordonnancier_fab_reconst_id_seq OWNED BY ordonnancier_fab_reconst.id;


--
-- TOC entry 278 (class 1259 OID 2995602)
-- Dependencies: 3
-- Name: patient; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE patient (
    id bigint NOT NULL,
    version bigint,
    adresse text,
    civilite character varying(255),
    codepostal character varying(255),
    datenaissance timestamp without time zone,
    initiales character varying(255),
    mail character varying(255),
    nom character varying(255),
    nomjeunefille character varying(255),
    numeroipp character varying(255),
    numerosejour character varying(255),
    prenom character varying(255),
    telephone character varying(255),
    ville character varying(255)
);




--
-- TOC entry 277 (class 1259 OID 2995600)
-- Dependencies: 3 278
-- Name: patient_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE patient_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3163 (class 0 OID 0)
-- Dependencies: 277
-- Name: patient_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE patient_id_seq OWNED BY patient.id;


--
-- TOC entry 280 (class 1259 OID 2995613)
-- Dependencies: 3
-- Name: patient_suivi; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE patient_suivi (
    id bigint NOT NULL,
    version bigint,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_patient bigint NOT NULL
);




--
-- TOC entry 279 (class 1259 OID 2995611)
-- Dependencies: 3 280
-- Name: patient_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE patient_suivi_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3164 (class 0 OID 0)
-- Dependencies: 279
-- Name: patient_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE patient_suivi_id_seq OWNED BY patient_suivi.id;


--
-- TOC entry 282 (class 1259 OID 2995621)
-- Dependencies: 3
-- Name: personne; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE personne (
    type character varying(31) NOT NULL,
    id bigint NOT NULL,
    version bigint,
    adresse text,
    codepostal character varying(255),
    fax character varying(255),
    isadmin boolean,
    login character varying(255),
    mail character varying(255),
    nom character varying(255) NOT NULL,
    password character varying(255),
    prenom character varying(255),
    telephone character varying(255),
    telephoneportable character varying(255),
    ville character varying(255),
    nomsociete character varying(255),
    titre character varying(255),
    datearriveeservice timestamp without time zone,
    datedepartservice timestamp without time zone,
    datevalidationformation timestamp without time zone,
    typepharmacien character varying(255),
    id_promoteur bigint
);




--
-- TOC entry 281 (class 1259 OID 2995619)
-- Dependencies: 282 3
-- Name: personne_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE personne_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3165 (class 0 OID 0)
-- Dependencies: 281
-- Name: personne_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE personne_id_seq OWNED BY personne.id;


--
-- TOC entry 284 (class 1259 OID 2995632)
-- Dependencies: 3
-- Name: personne_suivi; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE personne_suivi (
    id bigint NOT NULL,
    version bigint,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_personne bigint NOT NULL
);




--
-- TOC entry 283 (class 1259 OID 2995630)
-- Dependencies: 284 3
-- Name: personne_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE personne_suivi_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3166 (class 0 OID 0)
-- Dependencies: 283
-- Name: personne_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE personne_suivi_id_seq OWNED BY personne_suivi.id;


--
-- TOC entry 286 (class 1259 OID 2995640)
-- Dependencies: 3
-- Name: pharmacie; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pharmacie (
    id bigint NOT NULL,
    version bigint,
    adresse character varying(255),
    adresselivraison character varying(255),
    fax character varying(255),
    nom character varying(255) NOT NULL,
    numordonnancierdisp integer NOT NULL,
    numordonnancierfab integer NOT NULL,
    responsableprincipal character varying(255),
    telephone character varying(255),
    id_etablissement bigint NOT NULL
);




--
-- TOC entry 285 (class 1259 OID 2995638)
-- Dependencies: 286 3
-- Name: pharmacie_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE pharmacie_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3167 (class 0 OID 0)
-- Dependencies: 285
-- Name: pharmacie_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE pharmacie_id_seq OWNED BY pharmacie.id;


--
-- TOC entry 287 (class 1259 OID 2995649)
-- Dependencies: 3
-- Name: pharmacie_site; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pharmacie_site (
    id_pharmacie bigint NOT NULL,
    id_site bigint NOT NULL
);




--
-- TOC entry 289 (class 1259 OID 2995656)
-- Dependencies: 3
-- Name: pharmacie_suivi; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pharmacie_suivi (
    id bigint NOT NULL,
    version bigint,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_pharmacie bigint NOT NULL
);




--
-- TOC entry 288 (class 1259 OID 2995654)
-- Dependencies: 3 289
-- Name: pharmacie_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE pharmacie_suivi_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3168 (class 0 OID 0)
-- Dependencies: 288
-- Name: pharmacie_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE pharmacie_suivi_id_seq OWNED BY pharmacie_suivi.id;


--
-- TOC entry 291 (class 1259 OID 2995664)
-- Dependencies: 3
-- Name: pharmacien_document_pharmacien; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pharmacien_document_pharmacien (
    type character varying(31) NOT NULL,
    id bigint NOT NULL,
    version bigint,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    nomdisque character varying(255),
    nomuser character varying(255),
    id_pharmacien bigint NOT NULL
);




--
-- TOC entry 290 (class 1259 OID 2995662)
-- Dependencies: 3 291
-- Name: pharmacien_document_pharmacien_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE pharmacien_document_pharmacien_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3169 (class 0 OID 0)
-- Dependencies: 290
-- Name: pharmacien_document_pharmacien_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE pharmacien_document_pharmacien_id_seq OWNED BY pharmacien_document_pharmacien.id;


--
-- TOC entry 292 (class 1259 OID 2995673)
-- Dependencies: 3
-- Name: pharmacien_pharmacie; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pharmacien_pharmacie (
    id_pharmacien bigint NOT NULL,
    id_pharmacie bigint NOT NULL
);




--
-- TOC entry 294 (class 1259 OID 2995680)
-- Dependencies: 3
-- Name: pole; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pole (
    id bigint NOT NULL,
    version bigint,
    nom character varying(255) NOT NULL,
    id_etablissement bigint NOT NULL
);




--
-- TOC entry 293 (class 1259 OID 2995678)
-- Dependencies: 3 294
-- Name: pole_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE pole_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3170 (class 0 OID 0)
-- Dependencies: 293
-- Name: pole_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE pole_id_seq OWNED BY pole.id;


--
-- TOC entry 296 (class 1259 OID 2995688)
-- Dependencies: 3
-- Name: pole_suivi; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pole_suivi (
    id bigint NOT NULL,
    version bigint,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_pole bigint NOT NULL
);




--
-- TOC entry 295 (class 1259 OID 2995686)
-- Dependencies: 3 296
-- Name: pole_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE pole_suivi_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3171 (class 0 OID 0)
-- Dependencies: 295
-- Name: pole_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE pole_suivi_id_seq OWNED BY pole_suivi.id;


--
-- TOC entry 297 (class 1259 OID 2995694)
-- Dependencies: 3
-- Name: preparation; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE preparation (
    id bigint NOT NULL
);




--
-- TOC entry 299 (class 1259 OID 2995701)
-- Dependencies: 3
-- Name: prescription; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE prescription (
    id bigint NOT NULL,
    version bigint,
    commentaire text,
    datedebuttraitement timestamp without time zone,
    dateprescription timestamp without time zone,
    dispense boolean,
    numprescription integer,
    numvisite character varying(255),
    id_inclusion bigint NOT NULL,
    id_investigateur bigint NOT NULL,
    id_sequence bigint,
    id_service bigint NOT NULL
);




--
-- TOC entry 298 (class 1259 OID 2995699)
-- Dependencies: 3 299
-- Name: prescription_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE prescription_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3172 (class 0 OID 0)
-- Dependencies: 298
-- Name: prescription_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE prescription_id_seq OWNED BY prescription.id;


--
-- TOC entry 301 (class 1259 OID 2995712)
-- Dependencies: 3
-- Name: prescription_type; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE prescription_type (
    id bigint NOT NULL,
    version bigint,
    nb_debut integer,
    unite_debut character varying(255),
    description text,
    dosage numeric(19,2),
    nb_duree integer,
    unite_duree character varying(255),
    nbfrequence integer,
    nbunitetempsfrequence integer,
    typeregularite character varying(255),
    unitefrequence character varying(255),
    nbunitedosage numeric(19,2),
    id_conditionnement bigint,
    id_produit bigint,
    id_sequence bigint
);




--
-- TOC entry 300 (class 1259 OID 2995710)
-- Dependencies: 3 301
-- Name: prescription_type_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE prescription_type_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3173 (class 0 OID 0)
-- Dependencies: 300
-- Name: prescription_type_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE prescription_type_id_seq OWNED BY prescription_type.id;


--
-- TOC entry 303 (class 1259 OID 2995723)
-- Dependencies: 3
-- Name: prevision_sigrec; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE prevision_sigrec (
    id bigint NOT NULL,
    version bigint,
    datedebut timestamp without time zone,
    datefin timestamp without time zone,
    dureetotale integer,
    nbcentres integer,
    id_essai bigint
);




--
-- TOC entry 302 (class 1259 OID 2995721)
-- Dependencies: 3 303
-- Name: prevision_sigrec_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE prevision_sigrec_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3174 (class 0 OID 0)
-- Dependencies: 302
-- Name: prevision_sigrec_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE prevision_sigrec_id_seq OWNED BY prevision_sigrec.id;


--
-- TOC entry 305 (class 1259 OID 2995731)
-- Dependencies: 3
-- Name: produit; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE produit (
    id bigint NOT NULL,
    version bigint,
    alerteactive boolean NOT NULL,
    classetherapeutique character varying(255),
    code character varying(255),
    conseils text,
    denomination character varying(255) NOT NULL,
    imputationuf boolean,
    type character varying(255),
    id_detailproduit bigint NOT NULL
);




--
-- TOC entry 307 (class 1259 OID 2995742)
-- Dependencies: 3
-- Name: produit_detail_logistique; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE produit_detail_logistique (
    id bigint NOT NULL,
    version bigint,
    abrilumiere boolean,
    comptabiliteretour boolean,
    conditionconservation character varying(255),
    delaialerteavtdateexpiration integer,
    miseadispo boolean,
    produitnonfourni boolean,
    quantiteautorise integer,
    stockseuil integer,
    id_produit bigint
);




--
-- TOC entry 306 (class 1259 OID 2995740)
-- Dependencies: 3 307
-- Name: produit_detail_logistique_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE produit_detail_logistique_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3175 (class 0 OID 0)
-- Dependencies: 306
-- Name: produit_detail_logistique_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE produit_detail_logistique_id_seq OWNED BY produit_detail_logistique.id;


--
-- TOC entry 309 (class 1259 OID 2995750)
-- Dependencies: 3
-- Name: produit_detail_stockage; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE produit_detail_stockage (
    id bigint NOT NULL,
    version bigint,
    identifiantstockage character varying(255),
    type character varying(255),
    id_detail_logistique bigint,
    id_pharmacie bigint,
    id_stockage bigint
);




--
-- TOC entry 308 (class 1259 OID 2995748)
-- Dependencies: 309 3
-- Name: produit_detail_stockage_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE produit_detail_stockage_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3176 (class 0 OID 0)
-- Dependencies: 308
-- Name: produit_detail_stockage_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE produit_detail_stockage_id_seq OWNED BY produit_detail_stockage.id;


--
-- TOC entry 311 (class 1259 OID 2995761)
-- Dependencies: 3
-- Name: produit_document_actes_pharma; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE produit_document_actes_pharma (
    type character varying(31) NOT NULL,
    id bigint NOT NULL,
    version bigint,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    nomdisque character varying(255),
    nomuser character varying(255),
    id_produit bigint NOT NULL
);




--
-- TOC entry 310 (class 1259 OID 2995759)
-- Dependencies: 3 311
-- Name: produit_document_actes_pharma_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE produit_document_actes_pharma_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3177 (class 0 OID 0)
-- Dependencies: 310
-- Name: produit_document_actes_pharma_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE produit_document_actes_pharma_id_seq OWNED BY produit_document_actes_pharma.id;


--
-- TOC entry 304 (class 1259 OID 2995729)
-- Dependencies: 3 305
-- Name: produit_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE produit_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3178 (class 0 OID 0)
-- Dependencies: 304
-- Name: produit_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE produit_id_seq OWNED BY produit.id;


--
-- TOC entry 313 (class 1259 OID 2995772)
-- Dependencies: 3
-- Name: produit_prescrit; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE produit_prescrit (
    id bigint NOT NULL,
    version bigint,
    adispenser boolean,
    nb_debut integer,
    unite_debut character varying(255),
    description text,
    dispense boolean,
    dosage numeric(19,2),
    nb_duree integer,
    unite_duree character varying(255),
    nbfrequence integer,
    nbunitetempsfrequence integer,
    typeregularite character varying(255),
    unitefrequence character varying(255),
    nbunitedosage numeric(19,2),
    numtraitement character varying(255),
    id_conditionnement bigint NOT NULL,
    id_prescription bigint NOT NULL,
    id_produit bigint NOT NULL
);




--
-- TOC entry 312 (class 1259 OID 2995770)
-- Dependencies: 3 313
-- Name: produit_prescrit_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE produit_prescrit_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3179 (class 0 OID 0)
-- Dependencies: 312
-- Name: produit_prescrit_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE produit_prescrit_id_seq OWNED BY produit_prescrit.id;


--
-- TOC entry 314 (class 1259 OID 2995781)
-- Dependencies: 3
-- Name: produit_service; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE produit_service (
    id_produit bigint NOT NULL,
    id_service bigint NOT NULL
);




--
-- TOC entry 316 (class 1259 OID 2995788)
-- Dependencies: 3
-- Name: produit_suivi; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE produit_suivi (
    id bigint NOT NULL,
    version bigint,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_produit bigint NOT NULL
);




--
-- TOC entry 315 (class 1259 OID 2995786)
-- Dependencies: 316 3
-- Name: produit_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE produit_suivi_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3180 (class 0 OID 0)
-- Dependencies: 315
-- Name: produit_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE produit_suivi_id_seq OWNED BY produit_suivi.id;


--
-- TOC entry 317 (class 1259 OID 2995794)
-- Dependencies: 3
-- Name: produit_therapeutique; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE produit_therapeutique (
    conditionnement boolean,
    etiquetage boolean,
    fabrication boolean,
    mds boolean,
    nature character varying(255),
    reconstitutionpsm boolean,
    reconstitutionsimple boolean,
    stupefiant boolean,
    typeproduittherapeutique character varying(255),
    id bigint NOT NULL
);




--
-- TOC entry 319 (class 1259 OID 2995804)
-- Dependencies: 3
-- Name: promoteur; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE promoteur (
    id bigint NOT NULL,
    version bigint,
    identifiant character varying(255),
    raisonsociale character varying(255) NOT NULL,
    type character varying(255) NOT NULL
);




--
-- TOC entry 318 (class 1259 OID 2995802)
-- Dependencies: 319 3
-- Name: promoteur_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE promoteur_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3181 (class 0 OID 0)
-- Dependencies: 318
-- Name: promoteur_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE promoteur_id_seq OWNED BY promoteur.id;


--
-- TOC entry 321 (class 1259 OID 2995815)
-- Dependencies: 3
-- Name: promoteur_sigrec; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE promoteur_sigrec (
    id bigint NOT NULL,
    version bigint,
    identifiant character varying(255),
    type character varying(255),
    id_contact bigint
);




--
-- TOC entry 320 (class 1259 OID 2995813)
-- Dependencies: 321 3
-- Name: promoteur_sigrec_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE promoteur_sigrec_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3182 (class 0 OID 0)
-- Dependencies: 320
-- Name: promoteur_sigrec_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE promoteur_sigrec_id_seq OWNED BY promoteur_sigrec.id;


--
-- TOC entry 323 (class 1259 OID 2995826)
-- Dependencies: 3
-- Name: promoteur_suivi; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE promoteur_suivi (
    id bigint NOT NULL,
    version bigint,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_promoteur bigint NOT NULL
);




--
-- TOC entry 322 (class 1259 OID 2995824)
-- Dependencies: 323 3
-- Name: promoteur_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE promoteur_suivi_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3183 (class 0 OID 0)
-- Dependencies: 322
-- Name: promoteur_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE promoteur_suivi_id_seq OWNED BY promoteur_suivi.id;


--
-- TOC entry 325 (class 1259 OID 2995834)
-- Dependencies: 3
-- Name: regle_surcout; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE regle_surcout (
    id bigint NOT NULL,
    version bigint,
    anneessuivantes numeric(19,2),
    max integer,
    min integer,
    mode character varying(255),
    montant numeric(19,2),
    perimetre character varying(255),
    premiereannee numeric(19,2),
    type character varying(255),
    id_categorie bigint,
    id_theme bigint
);




--
-- TOC entry 324 (class 1259 OID 2995832)
-- Dependencies: 325 3
-- Name: regle_surcout_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE regle_surcout_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3184 (class 0 OID 0)
-- Dependencies: 324
-- Name: regle_surcout_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE regle_surcout_id_seq OWNED BY regle_surcout.id;


--
-- TOC entry 327 (class 1259 OID 2995845)
-- Dependencies: 3
-- Name: retour_patient; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE retour_patient (
    id bigint NOT NULL,
    version bigint,
    commentaire text,
    commentaireentame text,
    commentaireetat text,
    date timestamp without time zone,
    dateetat timestamp without time zone,
    etat character varying(255),
    numlot character varying(255),
    numordonnancier integer,
    numtraitement character varying(255),
    quantite integer,
    type character varying(255),
    id_conditionnement bigint NOT NULL,
    id_detailstockage bigint NOT NULL,
    id_essai bigint NOT NULL,
    id_patient bigint,
    id_personne bigint NOT NULL,
    id_produit bigint NOT NULL
);




--
-- TOC entry 326 (class 1259 OID 2995843)
-- Dependencies: 327 3
-- Name: retour_patient_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE retour_patient_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3185 (class 0 OID 0)
-- Dependencies: 326
-- Name: retour_patient_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE retour_patient_id_seq OWNED BY retour_patient.id;


--
-- TOC entry 329 (class 1259 OID 2995856)
-- Dependencies: 3
-- Name: sequence; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE sequence (
    id bigint NOT NULL,
    version bigint,
    nb_debut integer,
    unite_debut character varying(255),
    description text,
    nb_fin integer,
    unite_fin character varying(255),
    nb_duree integer,
    nom character varying(255),
    type character varying(255),
    unite_duree character varying(255),
    id_bras_sequence bigint
);




--
-- TOC entry 328 (class 1259 OID 2995854)
-- Dependencies: 3 329
-- Name: sequence_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE sequence_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3186 (class 0 OID 0)
-- Dependencies: 328
-- Name: sequence_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE sequence_id_seq OWNED BY sequence.id;


--
-- TOC entry 331 (class 1259 OID 2995867)
-- Dependencies: 3
-- Name: service; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE service (
    id bigint NOT NULL,
    version bigint,
    nom character varying(255) NOT NULL,
    id_pole bigint,
    id_site bigint
);




--
-- TOC entry 330 (class 1259 OID 2995865)
-- Dependencies: 331 3
-- Name: service_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE service_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3187 (class 0 OID 0)
-- Dependencies: 330
-- Name: service_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE service_id_seq OWNED BY service.id;


--
-- TOC entry 333 (class 1259 OID 2995875)
-- Dependencies: 3
-- Name: service_suivi; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE service_suivi (
    id bigint NOT NULL,
    version bigint,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_service bigint
);




--
-- TOC entry 332 (class 1259 OID 2995873)
-- Dependencies: 333 3
-- Name: service_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE service_suivi_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3188 (class 0 OID 0)
-- Dependencies: 332
-- Name: service_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE service_suivi_id_seq OWNED BY service_suivi.id;


--
-- TOC entry 335 (class 1259 OID 2995883)
-- Dependencies: 3
-- Name: site; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE site (
    id bigint NOT NULL,
    version bigint,
    adresse text,
    code character varying(255) NOT NULL,
    codepostal character varying(255),
    nom character varying(255) NOT NULL,
    ville character varying(255),
    id_etablissement bigint NOT NULL
);




--
-- TOC entry 334 (class 1259 OID 2995881)
-- Dependencies: 3 335
-- Name: site_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE site_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3189 (class 0 OID 0)
-- Dependencies: 334
-- Name: site_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE site_id_seq OWNED BY site.id;


--
-- TOC entry 337 (class 1259 OID 2995894)
-- Dependencies: 3
-- Name: site_suivi; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE site_suivi (
    id bigint NOT NULL,
    version bigint,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_site bigint
);




--
-- TOC entry 336 (class 1259 OID 2995892)
-- Dependencies: 337 3
-- Name: site_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE site_suivi_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3190 (class 0 OID 0)
-- Dependencies: 336
-- Name: site_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE site_suivi_id_seq OWNED BY site_suivi.id;


--
-- TOC entry 339 (class 1259 OID 2995902)
-- Dependencies: 3
-- Name: stockage; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE stockage (
    id bigint NOT NULL,
    version bigint,
    conservation character varying(255),
    identifiantenregistreurtemp character varying(255),
    identifiantsondetemp character varying(255),
    identifiantstockage character varying(255),
    nom character varying(255) NOT NULL,
    id_stockage_parent bigint,
    id_pharmacie bigint NOT NULL
);




--
-- TOC entry 338 (class 1259 OID 2995900)
-- Dependencies: 3 339
-- Name: stockage_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE stockage_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3191 (class 0 OID 0)
-- Dependencies: 338
-- Name: stockage_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE stockage_id_seq OWNED BY stockage.id;


--
-- TOC entry 341 (class 1259 OID 2995913)
-- Dependencies: 3
-- Name: theme; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE theme (
    id bigint NOT NULL,
    version bigint,
    libelle character varying(255),
    id_grille_modele bigint NOT NULL
);




--
-- TOC entry 340 (class 1259 OID 2995911)
-- Dependencies: 3 341
-- Name: theme_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE theme_id_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3192 (class 0 OID 0)
-- Dependencies: 340
-- Name: theme_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE theme_id_seq OWNED BY theme.id;


--
-- TOC entry 2461 (class 2604 OID 2994871)
-- Dependencies: 131 132 132
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY arc_investigateur_sigrec ALTER COLUMN id SET DEFAULT nextval('arc_investigateur_sigrec_id_seq'::regclass);


--
-- TOC entry 2462 (class 2604 OID 2994882)
-- Dependencies: 134 133 134
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY arc_promoteur_sigrec ALTER COLUMN id SET DEFAULT nextval('arc_promoteur_sigrec_id_seq'::regclass);


--
-- TOC entry 2463 (class 2604 OID 2994898)
-- Dependencies: 137 136 137
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY assurance_sigrec ALTER COLUMN id SET DEFAULT nextval('assurance_sigrec_id_seq'::regclass);


--
-- TOC entry 2464 (class 2604 OID 2994906)
-- Dependencies: 139 138 139
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY bras ALTER COLUMN id SET DEFAULT nextval('bras_id_seq'::regclass);


--
-- TOC entry 2465 (class 2604 OID 2994917)
-- Dependencies: 140 141 141
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY categorie ALTER COLUMN id SET DEFAULT nextval('categorie_id_seq'::regclass);


--
-- TOC entry 2466 (class 2604 OID 2994928)
-- Dependencies: 142 143 143
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY centre_sigrec ALTER COLUMN id SET DEFAULT nextval('centre_sigrec_id_seq'::regclass);


--
-- TOC entry 2467 (class 2604 OID 2994939)
-- Dependencies: 144 145 145
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY co_investigateur_sigrec ALTER COLUMN id SET DEFAULT nextval('co_investigateur_sigrec_id_seq'::regclass);


--
-- TOC entry 2468 (class 2604 OID 2994950)
-- Dependencies: 146 147 147
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY conditionnement ALTER COLUMN id SET DEFAULT nextval('conditionnement_id_seq'::regclass);


--
-- TOC entry 2469 (class 2604 OID 2994961)
-- Dependencies: 148 149 149
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY contact_sigrec ALTER COLUMN id SET DEFAULT nextval('contact_sigrec_id_seq'::regclass);


--
-- TOC entry 2470 (class 2604 OID 2994972)
-- Dependencies: 151 150 151
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cro_sigrec ALTER COLUMN id SET DEFAULT nextval('cro_sigrec_id_seq'::regclass);


--
-- TOC entry 2471 (class 2604 OID 2994980)
-- Dependencies: 153 152 153
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY dispensation ALTER COLUMN id SET DEFAULT nextval('dispensation_id_seq'::regclass);


--
-- TOC entry 2472 (class 2604 OID 2994999)
-- Dependencies: 156 155 156
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY donnees_prevision ALTER COLUMN id SET DEFAULT nextval('donnees_prevision_id_seq'::regclass);


--
-- TOC entry 2473 (class 2604 OID 2995007)
-- Dependencies: 157 158 158
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY dotation ALTER COLUMN id SET DEFAULT nextval('dotation_id_seq'::regclass);


--
-- TOC entry 2474 (class 2604 OID 2995018)
-- Dependencies: 160 159 160
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY element_to_check ALTER COLUMN id SET DEFAULT nextval('element_to_check_id_seq'::regclass);


--
-- TOC entry 2475 (class 2604 OID 2995029)
-- Dependencies: 161 162 162
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai ALTER COLUMN id SET DEFAULT nextval('essai_id_seq'::regclass);


--
-- TOC entry 2476 (class 2604 OID 2995040)
-- Dependencies: 164 163 164
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_commentaire_detail_administratif_archi ALTER COLUMN id SET DEFAULT nextval('essai_commentaire_detail_administratif_archi_id_seq'::regclass);


--
-- TOC entry 2477 (class 2604 OID 2995051)
-- Dependencies: 166 165 166
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_commentaire_detail_faisabilite ALTER COLUMN id SET DEFAULT nextval('essai_commentaire_detail_faisabilite_id_seq'::regclass);


--
-- TOC entry 2478 (class 2604 OID 2995062)
-- Dependencies: 167 168 168
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_commentaire_detail_recherche ALTER COLUMN id SET DEFAULT nextval('essai_commentaire_detail_recherche_id_seq'::regclass);


--
-- TOC entry 2479 (class 2604 OID 2995073)
-- Dependencies: 170 169 170
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_detail_administratif ALTER COLUMN id SET DEFAULT nextval('essai_detail_administratif_id_seq'::regclass);


--
-- TOC entry 2480 (class 2604 OID 2995084)
-- Dependencies: 172 171 172
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_detail_administratif_suivi ALTER COLUMN id SET DEFAULT nextval('essai_detail_administratif_suivi_id_seq'::regclass);


--
-- TOC entry 2481 (class 2604 OID 2995092)
-- Dependencies: 173 174 174
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_detail_autres_documents ALTER COLUMN id SET DEFAULT nextval('essai_detail_autres_documents_id_seq'::regclass);


--
-- TOC entry 2482 (class 2604 OID 2995100)
-- Dependencies: 175 176 176
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_detail_autres_documents_suivi ALTER COLUMN id SET DEFAULT nextval('essai_detail_autres_documents_suivi_id_seq'::regclass);


--
-- TOC entry 2483 (class 2604 OID 2995108)
-- Dependencies: 178 177 178
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_detail_contacts ALTER COLUMN id SET DEFAULT nextval('essai_detail_contacts_id_seq'::regclass);


--
-- TOC entry 2484 (class 2604 OID 2995116)
-- Dependencies: 179 180 180
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_detail_contacts_suivi ALTER COLUMN id SET DEFAULT nextval('essai_detail_contacts_suivi_id_seq'::regclass);


--
-- TOC entry 2485 (class 2604 OID 2995124)
-- Dependencies: 182 181 182
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_detail_dates ALTER COLUMN id SET DEFAULT nextval('essai_detail_dates_id_seq'::regclass);


--
-- TOC entry 2486 (class 2604 OID 2995132)
-- Dependencies: 184 183 184
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_detail_dates_suivi ALTER COLUMN id SET DEFAULT nextval('essai_detail_dates_suivi_id_seq'::regclass);


--
-- TOC entry 2487 (class 2604 OID 2995140)
-- Dependencies: 185 186 186
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_detail_design ALTER COLUMN id SET DEFAULT nextval('essai_detail_design_id_seq'::regclass);


--
-- TOC entry 2488 (class 2604 OID 2995148)
-- Dependencies: 187 188 188
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_detail_design_suivi ALTER COLUMN id SET DEFAULT nextval('essai_detail_design_suivi_id_seq'::regclass);


--
-- TOC entry 2489 (class 2604 OID 2995156)
-- Dependencies: 189 190 190
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_detail_etat ALTER COLUMN id SET DEFAULT nextval('essai_detail_etat_id_seq'::regclass);


--
-- TOC entry 2490 (class 2604 OID 2995167)
-- Dependencies: 191 192 192
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_detail_faisabilite ALTER COLUMN id SET DEFAULT nextval('essai_detail_faisabilite_id_seq'::regclass);


--
-- TOC entry 2491 (class 2604 OID 2995180)
-- Dependencies: 194 195 195
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_detail_faisabilite_suivi ALTER COLUMN id SET DEFAULT nextval('essai_detail_faisabilite_suivi_id_seq'::regclass);


--
-- TOC entry 2492 (class 2604 OID 2995188)
-- Dependencies: 197 196 197
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_detail_pharma ALTER COLUMN id SET DEFAULT nextval('essai_detail_pharma_id_seq'::regclass);


--
-- TOC entry 2493 (class 2604 OID 2995209)
-- Dependencies: 200 201 201
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_detail_pharma_suivi ALTER COLUMN id SET DEFAULT nextval('essai_detail_pharma_suivi_id_seq'::regclass);


--
-- TOC entry 2494 (class 2604 OID 2995217)
-- Dependencies: 202 203 203
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_detail_produit ALTER COLUMN id SET DEFAULT nextval('essai_detail_produit_id_seq'::regclass);


--
-- TOC entry 2495 (class 2604 OID 2995225)
-- Dependencies: 205 204 205
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_detail_produit_suivi ALTER COLUMN id SET DEFAULT nextval('essai_detail_produit_suivi_id_seq'::regclass);


--
-- TOC entry 2496 (class 2604 OID 2995233)
-- Dependencies: 207 206 207
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_detail_recherche ALTER COLUMN id SET DEFAULT nextval('essai_detail_recherche_id_seq'::regclass);


--
-- TOC entry 2497 (class 2604 OID 2995244)
-- Dependencies: 209 208 209
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_detail_recherche_sigrec ALTER COLUMN id SET DEFAULT nextval('essai_detail_recherche_sigrec_id_seq'::regclass);


--
-- TOC entry 2498 (class 2604 OID 2995255)
-- Dependencies: 210 211 211
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_detail_recherche_suivi ALTER COLUMN id SET DEFAULT nextval('essai_detail_recherche_suivi_id_seq'::regclass);


--
-- TOC entry 2499 (class 2604 OID 2995263)
-- Dependencies: 212 213 213
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_detail_surcout ALTER COLUMN id SET DEFAULT nextval('essai_detail_surcout_id_seq'::regclass);


--
-- TOC entry 2500 (class 2604 OID 2995271)
-- Dependencies: 215 214 215
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_detail_surcout_suivi ALTER COLUMN id SET DEFAULT nextval('essai_detail_surcout_suivi_id_seq'::regclass);


--
-- TOC entry 2501 (class 2604 OID 2995279)
-- Dependencies: 216 217 217
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_document_detail_administratif ALTER COLUMN id SET DEFAULT nextval('essai_document_detail_administratif_id_seq'::regclass);


--
-- TOC entry 2502 (class 2604 OID 2995290)
-- Dependencies: 219 218 219
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_document_detail_autres_documents ALTER COLUMN id SET DEFAULT nextval('essai_document_detail_autres_documents_id_seq'::regclass);


--
-- TOC entry 2503 (class 2604 OID 2995301)
-- Dependencies: 221 220 221
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_document_detail_pharma ALTER COLUMN id SET DEFAULT nextval('essai_document_detail_pharma_id_seq'::regclass);


--
-- TOC entry 2504 (class 2604 OID 2995312)
-- Dependencies: 223 222 223
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_document_detail_surcout ALTER COLUMN id SET DEFAULT nextval('essai_document_detail_surcout_id_seq'::regclass);


--
-- TOC entry 2505 (class 2604 OID 2995328)
-- Dependencies: 226 225 226
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_sigrec ALTER COLUMN id SET DEFAULT nextval('essai_sigrec_id_seq'::regclass);


--
-- TOC entry 2506 (class 2604 OID 2995339)
-- Dependencies: 227 228 228
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_suivi ALTER COLUMN id SET DEFAULT nextval('essai_suivi_id_seq'::regclass);


--
-- TOC entry 2507 (class 2604 OID 2995347)
-- Dependencies: 229 230 230
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY etablissement ALTER COLUMN id SET DEFAULT nextval('etablissement_id_seq'::regclass);


--
-- TOC entry 2508 (class 2604 OID 2995358)
-- Dependencies: 231 232 232
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY etablissement_suivi ALTER COLUMN id SET DEFAULT nextval('etablissement_suivi_id_seq'::regclass);


--
-- TOC entry 2509 (class 2604 OID 2995366)
-- Dependencies: 233 234 234
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY evenement ALTER COLUMN id SET DEFAULT nextval('evenement_id_seq'::regclass);


--
-- TOC entry 2510 (class 2604 OID 2995377)
-- Dependencies: 235 236 236
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY evenement_suivi ALTER COLUMN id SET DEFAULT nextval('evenement_suivi_id_seq'::regclass);


--
-- TOC entry 2511 (class 2604 OID 2995385)
-- Dependencies: 237 238 238
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY grille ALTER COLUMN id SET DEFAULT nextval('grille_id_seq'::regclass);


--
-- TOC entry 2512 (class 2604 OID 2995393)
-- Dependencies: 239 240 240
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY grille_modele ALTER COLUMN id SET DEFAULT nextval('grille_modele_id_seq'::regclass);


--
-- TOC entry 2513 (class 2604 OID 2995401)
-- Dependencies: 242 241 242
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY habilitation ALTER COLUMN id SET DEFAULT nextval('habilitation_id_seq'::regclass);


--
-- TOC entry 2514 (class 2604 OID 2995412)
-- Dependencies: 244 243 244
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY historique_patient ALTER COLUMN id SET DEFAULT nextval('historique_patient_id_seq'::regclass);


--
-- TOC entry 2515 (class 2604 OID 2995423)
-- Dependencies: 246 245 246
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY incident ALTER COLUMN id SET DEFAULT nextval('incident_id_seq'::regclass);


--
-- TOC entry 2516 (class 2604 OID 2995434)
-- Dependencies: 247 248 248
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY incident_suivi ALTER COLUMN id SET DEFAULT nextval('incident_suivi_id_seq'::regclass);


--
-- TOC entry 2517 (class 2604 OID 2995442)
-- Dependencies: 249 250 250
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY inclusion ALTER COLUMN id SET DEFAULT nextval('inclusion_id_seq'::regclass);


--
-- TOC entry 2518 (class 2604 OID 2995458)
-- Dependencies: 253 252 253
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY investigateur_sigrec ALTER COLUMN id SET DEFAULT nextval('investigateur_sigrec_id_seq'::regclass);


--
-- TOC entry 2519 (class 2604 OID 2995469)
-- Dependencies: 255 254 255
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY item ALTER COLUMN id SET DEFAULT nextval('item_id_seq'::regclass);


--
-- TOC entry 2520 (class 2604 OID 2995485)
-- Dependencies: 258 257 258
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY lignestock ALTER COLUMN id SET DEFAULT nextval('lignestock_id_seq'::regclass);


--
-- TOC entry 2521 (class 2604 OID 2995567)
-- Dependencies: 269 270 270
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY mvtstock ALTER COLUMN id SET DEFAULT nextval('mvtstock_id_seq'::regclass);


--
-- TOC entry 2522 (class 2604 OID 2995578)
-- Dependencies: 272 271 272
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY mvtstock_document ALTER COLUMN id SET DEFAULT nextval('mvtstock_document_id_seq'::regclass);


--
-- TOC entry 2523 (class 2604 OID 2995589)
-- Dependencies: 273 274 274
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ordonnancier_dispensation ALTER COLUMN id SET DEFAULT nextval('ordonnancier_dispensation_id_seq'::regclass);


--
-- TOC entry 2524 (class 2604 OID 2995597)
-- Dependencies: 276 275 276
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ordonnancier_fab_reconst ALTER COLUMN id SET DEFAULT nextval('ordonnancier_fab_reconst_id_seq'::regclass);


--
-- TOC entry 2525 (class 2604 OID 2995605)
-- Dependencies: 278 277 278
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY patient ALTER COLUMN id SET DEFAULT nextval('patient_id_seq'::regclass);


--
-- TOC entry 2526 (class 2604 OID 2995616)
-- Dependencies: 280 279 280
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY patient_suivi ALTER COLUMN id SET DEFAULT nextval('patient_suivi_id_seq'::regclass);


--
-- TOC entry 2527 (class 2604 OID 2995624)
-- Dependencies: 281 282 282
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY personne ALTER COLUMN id SET DEFAULT nextval('personne_id_seq'::regclass);


--
-- TOC entry 2528 (class 2604 OID 2995635)
-- Dependencies: 283 284 284
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY personne_suivi ALTER COLUMN id SET DEFAULT nextval('personne_suivi_id_seq'::regclass);


--
-- TOC entry 2529 (class 2604 OID 2995643)
-- Dependencies: 286 285 286
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pharmacie ALTER COLUMN id SET DEFAULT nextval('pharmacie_id_seq'::regclass);


--
-- TOC entry 2530 (class 2604 OID 2995659)
-- Dependencies: 289 288 289
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pharmacie_suivi ALTER COLUMN id SET DEFAULT nextval('pharmacie_suivi_id_seq'::regclass);


--
-- TOC entry 2531 (class 2604 OID 2995667)
-- Dependencies: 290 291 291
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pharmacien_document_pharmacien ALTER COLUMN id SET DEFAULT nextval('pharmacien_document_pharmacien_id_seq'::regclass);


--
-- TOC entry 2532 (class 2604 OID 2995683)
-- Dependencies: 294 293 294
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pole ALTER COLUMN id SET DEFAULT nextval('pole_id_seq'::regclass);


--
-- TOC entry 2533 (class 2604 OID 2995691)
-- Dependencies: 296 295 296
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pole_suivi ALTER COLUMN id SET DEFAULT nextval('pole_suivi_id_seq'::regclass);


--
-- TOC entry 2534 (class 2604 OID 2995704)
-- Dependencies: 298 299 299
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prescription ALTER COLUMN id SET DEFAULT nextval('prescription_id_seq'::regclass);


--
-- TOC entry 2535 (class 2604 OID 2995715)
-- Dependencies: 300 301 301
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prescription_type ALTER COLUMN id SET DEFAULT nextval('prescription_type_id_seq'::regclass);


--
-- TOC entry 2536 (class 2604 OID 2995726)
-- Dependencies: 303 302 303
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prevision_sigrec ALTER COLUMN id SET DEFAULT nextval('prevision_sigrec_id_seq'::regclass);


--
-- TOC entry 2537 (class 2604 OID 2995734)
-- Dependencies: 304 305 305
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY produit ALTER COLUMN id SET DEFAULT nextval('produit_id_seq'::regclass);


--
-- TOC entry 2538 (class 2604 OID 2995745)
-- Dependencies: 306 307 307
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY produit_detail_logistique ALTER COLUMN id SET DEFAULT nextval('produit_detail_logistique_id_seq'::regclass);


--
-- TOC entry 2539 (class 2604 OID 2995753)
-- Dependencies: 308 309 309
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY produit_detail_stockage ALTER COLUMN id SET DEFAULT nextval('produit_detail_stockage_id_seq'::regclass);


--
-- TOC entry 2540 (class 2604 OID 2995764)
-- Dependencies: 311 310 311
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY produit_document_actes_pharma ALTER COLUMN id SET DEFAULT nextval('produit_document_actes_pharma_id_seq'::regclass);


--
-- TOC entry 2541 (class 2604 OID 2995775)
-- Dependencies: 312 313 313
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY produit_prescrit ALTER COLUMN id SET DEFAULT nextval('produit_prescrit_id_seq'::regclass);


--
-- TOC entry 2542 (class 2604 OID 2995791)
-- Dependencies: 316 315 316
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY produit_suivi ALTER COLUMN id SET DEFAULT nextval('produit_suivi_id_seq'::regclass);


--
-- TOC entry 2543 (class 2604 OID 2995807)
-- Dependencies: 318 319 319
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY promoteur ALTER COLUMN id SET DEFAULT nextval('promoteur_id_seq'::regclass);


--
-- TOC entry 2544 (class 2604 OID 2995818)
-- Dependencies: 320 321 321
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY promoteur_sigrec ALTER COLUMN id SET DEFAULT nextval('promoteur_sigrec_id_seq'::regclass);


--
-- TOC entry 2545 (class 2604 OID 2995829)
-- Dependencies: 323 322 323
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY promoteur_suivi ALTER COLUMN id SET DEFAULT nextval('promoteur_suivi_id_seq'::regclass);


--
-- TOC entry 2546 (class 2604 OID 2995837)
-- Dependencies: 324 325 325
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY regle_surcout ALTER COLUMN id SET DEFAULT nextval('regle_surcout_id_seq'::regclass);


--
-- TOC entry 2547 (class 2604 OID 2995848)
-- Dependencies: 326 327 327
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY retour_patient ALTER COLUMN id SET DEFAULT nextval('retour_patient_id_seq'::regclass);


--
-- TOC entry 2548 (class 2604 OID 2995859)
-- Dependencies: 329 328 329
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sequence ALTER COLUMN id SET DEFAULT nextval('sequence_id_seq'::regclass);


--
-- TOC entry 2549 (class 2604 OID 2995870)
-- Dependencies: 330 331 331
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY service ALTER COLUMN id SET DEFAULT nextval('service_id_seq'::regclass);


--
-- TOC entry 2550 (class 2604 OID 2995878)
-- Dependencies: 333 332 333
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY service_suivi ALTER COLUMN id SET DEFAULT nextval('service_suivi_id_seq'::regclass);


--
-- TOC entry 2551 (class 2604 OID 2995886)
-- Dependencies: 335 334 335
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY site ALTER COLUMN id SET DEFAULT nextval('site_id_seq'::regclass);


--
-- TOC entry 2552 (class 2604 OID 2995897)
-- Dependencies: 337 336 337
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY site_suivi ALTER COLUMN id SET DEFAULT nextval('site_suivi_id_seq'::regclass);


--
-- TOC entry 2553 (class 2604 OID 2995905)
-- Dependencies: 338 339 339
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY stockage ALTER COLUMN id SET DEFAULT nextval('stockage_id_seq'::regclass);


--
-- TOC entry 2554 (class 2604 OID 2995916)
-- Dependencies: 340 341 341
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY theme ALTER COLUMN id SET DEFAULT nextval('theme_id_seq'::regclass);


--
-- TOC entry 2562 (class 2606 OID 2994860)
-- Dependencies: 129 129 129 3093
-- Name: acl_essai_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY acl_essai
    ADD CONSTRAINT acl_essai_pkey PRIMARY KEY (id_personne, id_essai);


--
-- TOC entry 2566 (class 2606 OID 2994865)
-- Dependencies: 130 130 130 3093
-- Name: acl_pharmacie_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY acl_pharmacie
    ADD CONSTRAINT acl_pharmacie_pkey PRIMARY KEY (id_pharmacie, id_personne);


--
-- TOC entry 2568 (class 2606 OID 2994876)
-- Dependencies: 132 132 3093
-- Name: arc_investigateur_sigrec_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY arc_investigateur_sigrec
    ADD CONSTRAINT arc_investigateur_sigrec_pkey PRIMARY KEY (id);


--
-- TOC entry 2571 (class 2606 OID 2994887)
-- Dependencies: 134 134 3093
-- Name: arc_promoteur_sigrec_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY arc_promoteur_sigrec
    ADD CONSTRAINT arc_promoteur_sigrec_pkey PRIMARY KEY (id);


--
-- TOC entry 2574 (class 2606 OID 2994892)
-- Dependencies: 135 135 135 3093
-- Name: arcinvestigateur_service_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY arcinvestigateur_service
    ADD CONSTRAINT arcinvestigateur_service_pkey PRIMARY KEY (id_arcinvestigateur, id_service);


--
-- TOC entry 2576 (class 2606 OID 2994900)
-- Dependencies: 137 137 3093
-- Name: assurance_sigrec_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY assurance_sigrec
    ADD CONSTRAINT assurance_sigrec_pkey PRIMARY KEY (id);


--
-- TOC entry 2579 (class 2606 OID 2994911)
-- Dependencies: 139 139 3093
-- Name: bras_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY bras
    ADD CONSTRAINT bras_pkey PRIMARY KEY (id);


--
-- TOC entry 2583 (class 2606 OID 2994922)
-- Dependencies: 141 141 3093
-- Name: categorie_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY categorie
    ADD CONSTRAINT categorie_pkey PRIMARY KEY (id);


--
-- TOC entry 2586 (class 2606 OID 2994933)
-- Dependencies: 143 143 3093
-- Name: centre_sigrec_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY centre_sigrec
    ADD CONSTRAINT centre_sigrec_pkey PRIMARY KEY (id);


--
-- TOC entry 2589 (class 2606 OID 2994944)
-- Dependencies: 145 145 3093
-- Name: co_investigateur_sigrec_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY co_investigateur_sigrec
    ADD CONSTRAINT co_investigateur_sigrec_pkey PRIMARY KEY (id);


--
-- TOC entry 2592 (class 2606 OID 2994955)
-- Dependencies: 147 147 3093
-- Name: conditionnement_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY conditionnement
    ADD CONSTRAINT conditionnement_pkey PRIMARY KEY (id);


--
-- TOC entry 2595 (class 2606 OID 2994966)
-- Dependencies: 149 149 3093
-- Name: contact_sigrec_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY contact_sigrec
    ADD CONSTRAINT contact_sigrec_pkey PRIMARY KEY (id);


--
-- TOC entry 2597 (class 2606 OID 2994974)
-- Dependencies: 151 151 3093
-- Name: cro_sigrec_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY cro_sigrec
    ADD CONSTRAINT cro_sigrec_pkey PRIMARY KEY (id);


--
-- TOC entry 2601 (class 2606 OID 2994985)
-- Dependencies: 153 153 3093
-- Name: dispensation_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY dispensation
    ADD CONSTRAINT dispensation_pkey PRIMARY KEY (id);


--
-- TOC entry 2606 (class 2606 OID 2994993)
-- Dependencies: 154 154 3093
-- Name: dispositif_medical_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY dispositif_medical
    ADD CONSTRAINT dispositif_medical_pkey PRIMARY KEY (id);


--
-- TOC entry 2608 (class 2606 OID 2995001)
-- Dependencies: 156 156 3093
-- Name: donnees_prevision_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY donnees_prevision
    ADD CONSTRAINT donnees_prevision_pkey PRIMARY KEY (id);


--
-- TOC entry 2610 (class 2606 OID 2995012)
-- Dependencies: 158 158 3093
-- Name: dotation_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY dotation
    ADD CONSTRAINT dotation_pkey PRIMARY KEY (id);


--
-- TOC entry 2618 (class 2606 OID 2995023)
-- Dependencies: 160 160 3093
-- Name: element_to_check_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY element_to_check
    ADD CONSTRAINT element_to_check_pkey PRIMARY KEY (id);


--
-- TOC entry 2628 (class 2606 OID 2995045)
-- Dependencies: 164 164 3093
-- Name: essai_commentaire_detail_administratif_archi_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY essai_commentaire_detail_administratif_archi
    ADD CONSTRAINT essai_commentaire_detail_administratif_archi_pkey PRIMARY KEY (id);


--
-- TOC entry 2631 (class 2606 OID 2995056)
-- Dependencies: 166 166 3093
-- Name: essai_commentaire_detail_faisabilite_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY essai_commentaire_detail_faisabilite
    ADD CONSTRAINT essai_commentaire_detail_faisabilite_pkey PRIMARY KEY (id);


--
-- TOC entry 2634 (class 2606 OID 2995067)
-- Dependencies: 168 168 3093
-- Name: essai_commentaire_detail_recherche_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY essai_commentaire_detail_recherche
    ADD CONSTRAINT essai_commentaire_detail_recherche_pkey PRIMARY KEY (id);


--
-- TOC entry 2637 (class 2606 OID 2995078)
-- Dependencies: 170 170 3093
-- Name: essai_detail_administratif_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY essai_detail_administratif
    ADD CONSTRAINT essai_detail_administratif_pkey PRIMARY KEY (id);


--
-- TOC entry 2639 (class 2606 OID 2995086)
-- Dependencies: 172 172 3093
-- Name: essai_detail_administratif_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY essai_detail_administratif_suivi
    ADD CONSTRAINT essai_detail_administratif_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2642 (class 2606 OID 2995094)
-- Dependencies: 174 174 3093
-- Name: essai_detail_autres_documents_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY essai_detail_autres_documents
    ADD CONSTRAINT essai_detail_autres_documents_pkey PRIMARY KEY (id);


--
-- TOC entry 2644 (class 2606 OID 2995102)
-- Dependencies: 176 176 3093
-- Name: essai_detail_autres_documents_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY essai_detail_autres_documents_suivi
    ADD CONSTRAINT essai_detail_autres_documents_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2647 (class 2606 OID 2995110)
-- Dependencies: 178 178 3093
-- Name: essai_detail_contacts_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY essai_detail_contacts
    ADD CONSTRAINT essai_detail_contacts_pkey PRIMARY KEY (id);


--
-- TOC entry 2649 (class 2606 OID 2995118)
-- Dependencies: 180 180 3093
-- Name: essai_detail_contacts_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY essai_detail_contacts_suivi
    ADD CONSTRAINT essai_detail_contacts_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2652 (class 2606 OID 2995126)
-- Dependencies: 182 182 3093
-- Name: essai_detail_dates_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY essai_detail_dates
    ADD CONSTRAINT essai_detail_dates_pkey PRIMARY KEY (id);


--
-- TOC entry 2654 (class 2606 OID 2995134)
-- Dependencies: 184 184 3093
-- Name: essai_detail_dates_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY essai_detail_dates_suivi
    ADD CONSTRAINT essai_detail_dates_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2657 (class 2606 OID 2995142)
-- Dependencies: 186 186 3093
-- Name: essai_detail_design_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY essai_detail_design
    ADD CONSTRAINT essai_detail_design_pkey PRIMARY KEY (id);


--
-- TOC entry 2659 (class 2606 OID 2995150)
-- Dependencies: 188 188 3093
-- Name: essai_detail_design_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY essai_detail_design_suivi
    ADD CONSTRAINT essai_detail_design_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2662 (class 2606 OID 2995161)
-- Dependencies: 190 190 3093
-- Name: essai_detail_etat_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY essai_detail_etat
    ADD CONSTRAINT essai_detail_etat_pkey PRIMARY KEY (id);


--
-- TOC entry 2665 (class 2606 OID 2995169)
-- Dependencies: 192 192 3093
-- Name: essai_detail_faisabilite_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY essai_detail_faisabilite
    ADD CONSTRAINT essai_detail_faisabilite_pkey PRIMARY KEY (id);


--
-- TOC entry 2667 (class 2606 OID 2995174)
-- Dependencies: 193 193 193 3093
-- Name: essai_detail_faisabilite_service_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY essai_detail_faisabilite_service
    ADD CONSTRAINT essai_detail_faisabilite_service_pkey PRIMARY KEY (id_essai, id_service);


--
-- TOC entry 2669 (class 2606 OID 2995182)
-- Dependencies: 195 195 3093
-- Name: essai_detail_faisabilite_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY essai_detail_faisabilite_suivi
    ADD CONSTRAINT essai_detail_faisabilite_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2674 (class 2606 OID 2995198)
-- Dependencies: 198 198 198 3093
-- Name: essai_detail_pharma_etablissement_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY essai_detail_pharma_etablissement
    ADD CONSTRAINT essai_detail_pharma_etablissement_pkey PRIMARY KEY (id_detail_pharma, id_etablissement);


--
-- TOC entry 2676 (class 2606 OID 2995203)
-- Dependencies: 199 199 199 3093
-- Name: essai_detail_pharma_pharmacie_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY essai_detail_pharma_pharmacie
    ADD CONSTRAINT essai_detail_pharma_pharmacie_pkey PRIMARY KEY (id_detail_pharma, id_pharmacie);


--
-- TOC entry 2672 (class 2606 OID 2995193)
-- Dependencies: 197 197 3093
-- Name: essai_detail_pharma_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY essai_detail_pharma
    ADD CONSTRAINT essai_detail_pharma_pkey PRIMARY KEY (id);


--
-- TOC entry 2678 (class 2606 OID 2995211)
-- Dependencies: 201 201 3093
-- Name: essai_detail_pharma_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY essai_detail_pharma_suivi
    ADD CONSTRAINT essai_detail_pharma_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2681 (class 2606 OID 2995219)
-- Dependencies: 203 203 3093
-- Name: essai_detail_produit_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY essai_detail_produit
    ADD CONSTRAINT essai_detail_produit_pkey PRIMARY KEY (id);


--
-- TOC entry 2683 (class 2606 OID 2995227)
-- Dependencies: 205 205 3093
-- Name: essai_detail_produit_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY essai_detail_produit_suivi
    ADD CONSTRAINT essai_detail_produit_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2686 (class 2606 OID 2995238)
-- Dependencies: 207 207 3093
-- Name: essai_detail_recherche_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY essai_detail_recherche
    ADD CONSTRAINT essai_detail_recherche_pkey PRIMARY KEY (id);


--
-- TOC entry 2688 (class 2606 OID 2995249)
-- Dependencies: 209 209 3093
-- Name: essai_detail_recherche_sigrec_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY essai_detail_recherche_sigrec
    ADD CONSTRAINT essai_detail_recherche_sigrec_pkey PRIMARY KEY (id);


--
-- TOC entry 2690 (class 2606 OID 2995257)
-- Dependencies: 211 211 3093
-- Name: essai_detail_recherche_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY essai_detail_recherche_suivi
    ADD CONSTRAINT essai_detail_recherche_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2693 (class 2606 OID 2995265)
-- Dependencies: 213 213 3093
-- Name: essai_detail_surcout_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY essai_detail_surcout
    ADD CONSTRAINT essai_detail_surcout_pkey PRIMARY KEY (id);


--
-- TOC entry 2695 (class 2606 OID 2995273)
-- Dependencies: 215 215 3093
-- Name: essai_detail_surcout_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY essai_detail_surcout_suivi
    ADD CONSTRAINT essai_detail_surcout_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2698 (class 2606 OID 2995284)
-- Dependencies: 217 217 3093
-- Name: essai_document_detail_administratif_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY essai_document_detail_administratif
    ADD CONSTRAINT essai_document_detail_administratif_pkey PRIMARY KEY (id);


--
-- TOC entry 2701 (class 2606 OID 2995295)
-- Dependencies: 219 219 3093
-- Name: essai_document_detail_autres_documents_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY essai_document_detail_autres_documents
    ADD CONSTRAINT essai_document_detail_autres_documents_pkey PRIMARY KEY (id);


--
-- TOC entry 2703 (class 2606 OID 2995306)
-- Dependencies: 221 221 3093
-- Name: essai_document_detail_pharma_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY essai_document_detail_pharma
    ADD CONSTRAINT essai_document_detail_pharma_pkey PRIMARY KEY (id);


--
-- TOC entry 2705 (class 2606 OID 2995317)
-- Dependencies: 223 223 3093
-- Name: essai_document_detail_surcout_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY essai_document_detail_surcout
    ADD CONSTRAINT essai_document_detail_surcout_pkey PRIMARY KEY (id);


--
-- TOC entry 2624 (class 2606 OID 2995034)
-- Dependencies: 162 162 3093
-- Name: essai_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY essai
    ADD CONSTRAINT essai_pkey PRIMARY KEY (id);


--
-- TOC entry 2708 (class 2606 OID 2995322)
-- Dependencies: 224 224 224 3093
-- Name: essai_service_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY essai_service
    ADD CONSTRAINT essai_service_pkey PRIMARY KEY (id_essai, id_service);


--
-- TOC entry 2710 (class 2606 OID 2995333)
-- Dependencies: 226 226 3093
-- Name: essai_sigrec_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY essai_sigrec
    ADD CONSTRAINT essai_sigrec_pkey PRIMARY KEY (id);


--
-- TOC entry 2714 (class 2606 OID 2995341)
-- Dependencies: 228 228 3093
-- Name: essai_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY essai_suivi
    ADD CONSTRAINT essai_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2717 (class 2606 OID 2995352)
-- Dependencies: 230 230 3093
-- Name: etablissement_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY etablissement
    ADD CONSTRAINT etablissement_pkey PRIMARY KEY (id);


--
-- TOC entry 2719 (class 2606 OID 2995360)
-- Dependencies: 232 232 3093
-- Name: etablissement_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY etablissement_suivi
    ADD CONSTRAINT etablissement_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2722 (class 2606 OID 2995371)
-- Dependencies: 234 234 3093
-- Name: evenement_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY evenement
    ADD CONSTRAINT evenement_pkey PRIMARY KEY (id);


--
-- TOC entry 2726 (class 2606 OID 2995379)
-- Dependencies: 236 236 3093
-- Name: evenement_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY evenement_suivi
    ADD CONSTRAINT evenement_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2732 (class 2606 OID 2995395)
-- Dependencies: 240 240 3093
-- Name: grille_modele_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY grille_modele
    ADD CONSTRAINT grille_modele_pkey PRIMARY KEY (id);


--
-- TOC entry 2729 (class 2606 OID 2995387)
-- Dependencies: 238 238 3093
-- Name: grille_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY grille
    ADD CONSTRAINT grille_pkey PRIMARY KEY (id);


--
-- TOC entry 2734 (class 2606 OID 2995406)
-- Dependencies: 242 242 3093
-- Name: habilitation_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY habilitation
    ADD CONSTRAINT habilitation_pkey PRIMARY KEY (id);


--
-- TOC entry 2738 (class 2606 OID 2995417)
-- Dependencies: 244 244 3093
-- Name: historique_patient_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY historique_patient
    ADD CONSTRAINT historique_patient_pkey PRIMARY KEY (id);


--
-- TOC entry 2742 (class 2606 OID 2995428)
-- Dependencies: 246 246 3093
-- Name: incident_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY incident
    ADD CONSTRAINT incident_pkey PRIMARY KEY (id);


--
-- TOC entry 2745 (class 2606 OID 2995436)
-- Dependencies: 248 248 3093
-- Name: incident_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY incident_suivi
    ADD CONSTRAINT incident_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2749 (class 2606 OID 2995447)
-- Dependencies: 250 250 3093
-- Name: inclusion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY inclusion
    ADD CONSTRAINT inclusion_pkey PRIMARY KEY (id);


--
-- TOC entry 2751 (class 2606 OID 2995452)
-- Dependencies: 251 251 251 3093
-- Name: investigateur_service_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY investigateur_service
    ADD CONSTRAINT investigateur_service_pkey PRIMARY KEY (id_investigateur, id_service);


--
-- TOC entry 2753 (class 2606 OID 2995463)
-- Dependencies: 253 253 3093
-- Name: investigateur_sigrec_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY investigateur_sigrec
    ADD CONSTRAINT investigateur_sigrec_pkey PRIMARY KEY (id);


--
-- TOC entry 2756 (class 2606 OID 2995474)
-- Dependencies: 255 255 3093
-- Name: item_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY item
    ADD CONSTRAINT item_pkey PRIMARY KEY (id);


--
-- TOC entry 2758 (class 2606 OID 2995479)
-- Dependencies: 256 256 256 3093
-- Name: item_regle_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY item_regle
    ADD CONSTRAINT item_regle_pkey PRIMARY KEY (id_item, id_regle);


--
-- TOC entry 2764 (class 2606 OID 2995490)
-- Dependencies: 258 258 3093
-- Name: lignestock_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY lignestock
    ADD CONSTRAINT lignestock_pkey PRIMARY KEY (id);


--
-- TOC entry 2766 (class 2606 OID 2995498)
-- Dependencies: 259 259 3093
-- Name: medicament_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY medicament
    ADD CONSTRAINT medicament_pkey PRIMARY KEY (id);


--
-- TOC entry 2768 (class 2606 OID 2995506)
-- Dependencies: 260 260 3093
-- Name: mvt_approvisionnement_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY mvt_approvisionnement
    ADD CONSTRAINT mvt_approvisionnement_pkey PRIMARY KEY (id);


--
-- TOC entry 2770 (class 2606 OID 2995514)
-- Dependencies: 261 261 3093
-- Name: mvt_autre_sortie_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY mvt_autre_sortie
    ADD CONSTRAINT mvt_autre_sortie_pkey PRIMARY KEY (id);


--
-- TOC entry 2773 (class 2606 OID 2995522)
-- Dependencies: 262 262 3093
-- Name: mvt_cession_pui_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY mvt_cession_pui
    ADD CONSTRAINT mvt_cession_pui_pkey PRIMARY KEY (id);


--
-- TOC entry 2775 (class 2606 OID 2995530)
-- Dependencies: 263 263 3093
-- Name: mvt_destruction_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY mvt_destruction
    ADD CONSTRAINT mvt_destruction_pkey PRIMARY KEY (id);


--
-- TOC entry 2782 (class 2606 OID 2995540)
-- Dependencies: 265 265 3093
-- Name: mvt_dispensation_globale_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY mvt_dispensation_globale
    ADD CONSTRAINT mvt_dispensation_globale_pkey PRIMARY KEY (id);


--
-- TOC entry 2779 (class 2606 OID 2995535)
-- Dependencies: 264 264 3093
-- Name: mvt_dispensation_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY mvt_dispensation
    ADD CONSTRAINT mvt_dispensation_pkey PRIMARY KEY (id);


--
-- TOC entry 2784 (class 2606 OID 2995545)
-- Dependencies: 266 266 3093
-- Name: mvt_preparation_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY mvt_preparation
    ADD CONSTRAINT mvt_preparation_pkey PRIMARY KEY (id);


--
-- TOC entry 2787 (class 2606 OID 2995553)
-- Dependencies: 267 267 3093
-- Name: mvt_preparationentree_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY mvt_preparationentree
    ADD CONSTRAINT mvt_preparationentree_pkey PRIMARY KEY (id);


--
-- TOC entry 2789 (class 2606 OID 2995561)
-- Dependencies: 268 268 3093
-- Name: mvt_retour_promoteur_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY mvt_retour_promoteur
    ADD CONSTRAINT mvt_retour_promoteur_pkey PRIMARY KEY (id);


--
-- TOC entry 2798 (class 2606 OID 2995583)
-- Dependencies: 272 272 3093
-- Name: mvtstock_document_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY mvtstock_document
    ADD CONSTRAINT mvtstock_document_pkey PRIMARY KEY (id);


--
-- TOC entry 2796 (class 2606 OID 2995572)
-- Dependencies: 270 270 3093
-- Name: mvtstock_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY mvtstock
    ADD CONSTRAINT mvtstock_pkey PRIMARY KEY (id);


--
-- TOC entry 2800 (class 2606 OID 2995591)
-- Dependencies: 274 274 3093
-- Name: ordonnancier_dispensation_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY ordonnancier_dispensation
    ADD CONSTRAINT ordonnancier_dispensation_pkey PRIMARY KEY (id);


--
-- TOC entry 2802 (class 2606 OID 2995599)
-- Dependencies: 276 276 3093
-- Name: ordonnancier_fab_reconst_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY ordonnancier_fab_reconst
    ADD CONSTRAINT ordonnancier_fab_reconst_pkey PRIMARY KEY (id);


--
-- TOC entry 2804 (class 2606 OID 2995610)
-- Dependencies: 278 278 3093
-- Name: patient_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY patient
    ADD CONSTRAINT patient_pkey PRIMARY KEY (id);


--
-- TOC entry 2807 (class 2606 OID 2995618)
-- Dependencies: 280 280 3093
-- Name: patient_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY patient_suivi
    ADD CONSTRAINT patient_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2811 (class 2606 OID 2995629)
-- Dependencies: 282 282 3093
-- Name: personne_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY personne
    ADD CONSTRAINT personne_pkey PRIMARY KEY (id);


--
-- TOC entry 2814 (class 2606 OID 2995637)
-- Dependencies: 284 284 3093
-- Name: personne_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY personne_suivi
    ADD CONSTRAINT personne_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2817 (class 2606 OID 2995648)
-- Dependencies: 286 286 3093
-- Name: pharmacie_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY pharmacie
    ADD CONSTRAINT pharmacie_pkey PRIMARY KEY (id);


--
-- TOC entry 2819 (class 2606 OID 2995653)
-- Dependencies: 287 287 287 3093
-- Name: pharmacie_site_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY pharmacie_site
    ADD CONSTRAINT pharmacie_site_pkey PRIMARY KEY (id_pharmacie, id_site);


--
-- TOC entry 2822 (class 2606 OID 2995661)
-- Dependencies: 289 289 3093
-- Name: pharmacie_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY pharmacie_suivi
    ADD CONSTRAINT pharmacie_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2825 (class 2606 OID 2995672)
-- Dependencies: 291 291 3093
-- Name: pharmacien_document_pharmacien_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY pharmacien_document_pharmacien
    ADD CONSTRAINT pharmacien_document_pharmacien_pkey PRIMARY KEY (id);


--
-- TOC entry 2827 (class 2606 OID 2995677)
-- Dependencies: 292 292 292 3093
-- Name: pharmacien_pharmacie_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY pharmacien_pharmacie
    ADD CONSTRAINT pharmacien_pharmacie_pkey PRIMARY KEY (id_pharmacien, id_pharmacie);


--
-- TOC entry 2830 (class 2606 OID 2995685)
-- Dependencies: 294 294 3093
-- Name: pole_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY pole
    ADD CONSTRAINT pole_pkey PRIMARY KEY (id);


--
-- TOC entry 2833 (class 2606 OID 2995693)
-- Dependencies: 296 296 3093
-- Name: pole_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY pole_suivi
    ADD CONSTRAINT pole_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2835 (class 2606 OID 2995698)
-- Dependencies: 297 297 3093
-- Name: preparation_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY preparation
    ADD CONSTRAINT preparation_pkey PRIMARY KEY (id);


--
-- TOC entry 2841 (class 2606 OID 2995709)
-- Dependencies: 299 299 3093
-- Name: prescription_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY prescription
    ADD CONSTRAINT prescription_pkey PRIMARY KEY (id);


--
-- TOC entry 2846 (class 2606 OID 2995720)
-- Dependencies: 301 301 3093
-- Name: prescription_type_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY prescription_type
    ADD CONSTRAINT prescription_type_pkey PRIMARY KEY (id);


--
-- TOC entry 2848 (class 2606 OID 2995728)
-- Dependencies: 303 303 3093
-- Name: prevision_sigrec_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY prevision_sigrec
    ADD CONSTRAINT prevision_sigrec_pkey PRIMARY KEY (id);


--
-- TOC entry 2853 (class 2606 OID 2995747)
-- Dependencies: 307 307 3093
-- Name: produit_detail_logistique_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY produit_detail_logistique
    ADD CONSTRAINT produit_detail_logistique_pkey PRIMARY KEY (id);


--
-- TOC entry 2858 (class 2606 OID 2995758)
-- Dependencies: 309 309 3093
-- Name: produit_detail_stockage_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY produit_detail_stockage
    ADD CONSTRAINT produit_detail_stockage_pkey PRIMARY KEY (id);


--
-- TOC entry 2860 (class 2606 OID 2995769)
-- Dependencies: 311 311 3093
-- Name: produit_document_actes_pharma_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY produit_document_actes_pharma
    ADD CONSTRAINT produit_document_actes_pharma_pkey PRIMARY KEY (id);


--
-- TOC entry 2851 (class 2606 OID 2995739)
-- Dependencies: 305 305 3093
-- Name: produit_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY produit
    ADD CONSTRAINT produit_pkey PRIMARY KEY (id);


--
-- TOC entry 2865 (class 2606 OID 2995780)
-- Dependencies: 313 313 3093
-- Name: produit_prescrit_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY produit_prescrit
    ADD CONSTRAINT produit_prescrit_pkey PRIMARY KEY (id);


--
-- TOC entry 2867 (class 2606 OID 2995785)
-- Dependencies: 314 314 314 3093
-- Name: produit_service_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY produit_service
    ADD CONSTRAINT produit_service_pkey PRIMARY KEY (id_produit, id_service);


--
-- TOC entry 2870 (class 2606 OID 2995793)
-- Dependencies: 316 316 3093
-- Name: produit_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY produit_suivi
    ADD CONSTRAINT produit_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2872 (class 2606 OID 2995801)
-- Dependencies: 317 317 3093
-- Name: produit_therapeutique_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY produit_therapeutique
    ADD CONSTRAINT produit_therapeutique_pkey PRIMARY KEY (id);


--
-- TOC entry 2874 (class 2606 OID 2995812)
-- Dependencies: 319 319 3093
-- Name: promoteur_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY promoteur
    ADD CONSTRAINT promoteur_pkey PRIMARY KEY (id);


--
-- TOC entry 2877 (class 2606 OID 2995823)
-- Dependencies: 321 321 3093
-- Name: promoteur_sigrec_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY promoteur_sigrec
    ADD CONSTRAINT promoteur_sigrec_pkey PRIMARY KEY (id);


--
-- TOC entry 2880 (class 2606 OID 2995831)
-- Dependencies: 323 323 3093
-- Name: promoteur_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY promoteur_suivi
    ADD CONSTRAINT promoteur_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2884 (class 2606 OID 2995842)
-- Dependencies: 325 325 3093
-- Name: regle_surcout_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY regle_surcout
    ADD CONSTRAINT regle_surcout_pkey PRIMARY KEY (id);


--
-- TOC entry 2892 (class 2606 OID 2995853)
-- Dependencies: 327 327 3093
-- Name: retour_patient_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY retour_patient
    ADD CONSTRAINT retour_patient_pkey PRIMARY KEY (id);


--
-- TOC entry 2895 (class 2606 OID 2995864)
-- Dependencies: 329 329 3093
-- Name: sequence_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY sequence
    ADD CONSTRAINT sequence_pkey PRIMARY KEY (id);


--
-- TOC entry 2899 (class 2606 OID 2995872)
-- Dependencies: 331 331 3093
-- Name: service_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY service
    ADD CONSTRAINT service_pkey PRIMARY KEY (id);


--
-- TOC entry 2902 (class 2606 OID 2995880)
-- Dependencies: 333 333 3093
-- Name: service_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY service_suivi
    ADD CONSTRAINT service_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2905 (class 2606 OID 2995891)
-- Dependencies: 335 335 3093
-- Name: site_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY site
    ADD CONSTRAINT site_pkey PRIMARY KEY (id);


--
-- TOC entry 2908 (class 2606 OID 2995899)
-- Dependencies: 337 337 3093
-- Name: site_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY site_suivi
    ADD CONSTRAINT site_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2912 (class 2606 OID 2995910)
-- Dependencies: 339 339 3093
-- Name: stockage_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY stockage
    ADD CONSTRAINT stockage_pkey PRIMARY KEY (id);


--
-- TOC entry 2915 (class 2606 OID 2995918)
-- Dependencies: 341 341 3093
-- Name: theme_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY theme
    ADD CONSTRAINT theme_pkey PRIMARY KEY (id);


--
-- TOC entry 2559 (class 1259 OID 2995920)
-- Dependencies: 129 3093
-- Name: acl_essai_id_essai; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX acl_essai_id_essai ON acl_essai USING btree (id_essai);


--
-- TOC entry 2560 (class 1259 OID 2995919)
-- Dependencies: 129 3093
-- Name: acl_essai_id_personne; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX acl_essai_id_personne ON acl_essai USING btree (id_personne);


--
-- TOC entry 2563 (class 1259 OID 2995922)
-- Dependencies: 130 3093
-- Name: acl_pharmacie_id_personne; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX acl_pharmacie_id_personne ON acl_pharmacie USING btree (id_personne);


--
-- TOC entry 2564 (class 1259 OID 2995921)
-- Dependencies: 130 3093
-- Name: acl_pharmacie_id_pharmacie; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX acl_pharmacie_id_pharmacie ON acl_pharmacie USING btree (id_pharmacie);


--
-- TOC entry 2569 (class 1259 OID 2995923)
-- Dependencies: 132 3093
-- Name: idx_arc_investigateur_essai_sigrec; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_arc_investigateur_essai_sigrec ON arc_investigateur_sigrec USING btree (id_essai);


--
-- TOC entry 2893 (class 1259 OID 2996864)
-- Dependencies: 329 3093
-- Name: idx_bras_sequence; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_bras_sequence ON sequence USING btree (id_bras_sequence);


--
-- TOC entry 2580 (class 1259 OID 2995972)
-- Dependencies: 139 3093
-- Name: idx_brase_parent; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_brase_parent ON bras USING btree (id_bras_parent);


--
-- TOC entry 2881 (class 1259 OID 2996817)
-- Dependencies: 325 3093
-- Name: idx_categorie_regle; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_categorie_regle ON regle_surcout USING btree (id_categorie);


--
-- TOC entry 2590 (class 1259 OID 2995995)
-- Dependencies: 145 3093
-- Name: idx_co_investigateur_essai_sigrec; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_co_investigateur_essai_sigrec ON co_investigateur_sigrec USING btree (id_essai);


--
-- TOC entry 2593 (class 1259 OID 2996011)
-- Dependencies: 147 3093
-- Name: idx_conditionnement_produit; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_conditionnement_produit ON conditionnement USING btree (id_produit);


--
-- TOC entry 2861 (class 1259 OID 2996767)
-- Dependencies: 313 3093
-- Name: idx_conditionnement_produit_prescrit; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_conditionnement_produit_prescrit ON produit_prescrit USING btree (id_conditionnement);


--
-- TOC entry 2885 (class 1259 OID 2996832)
-- Dependencies: 327 3093
-- Name: idx_conditionnement_retourpatient; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_conditionnement_retourpatient ON retour_patient USING btree (id_conditionnement);


--
-- TOC entry 2577 (class 1259 OID 2995960)
-- Dependencies: 137 3093
-- Name: idx_contact_assurance_sigrec; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_contact_assurance_sigrec ON assurance_sigrec USING btree (id_contact);


--
-- TOC entry 2587 (class 1259 OID 2995989)
-- Dependencies: 143 3093
-- Name: idx_contact_centre_sigrec; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_contact_centre_sigrec ON centre_sigrec USING btree (id_contact);


--
-- TOC entry 2598 (class 1259 OID 2996018)
-- Dependencies: 151 3093
-- Name: idx_contact_cro_sigrec; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_contact_cro_sigrec ON cro_sigrec USING btree (id_contact);


--
-- TOC entry 2875 (class 1259 OID 2996804)
-- Dependencies: 321 3093
-- Name: idx_contact_promoteur_sigrec; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_contact_promoteur_sigrec ON promoteur_sigrec USING btree (id_contact);


--
-- TOC entry 2735 (class 1259 OID 2996383)
-- Dependencies: 242 3093
-- Name: idx_detail_contacts_habilitation; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_detail_contacts_habilitation ON habilitation USING btree (id_detail_contacts);


--
-- TOC entry 2581 (class 1259 OID 2995971)
-- Dependencies: 139 3093
-- Name: idx_detail_design_bras; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_detail_design_bras ON bras USING btree (id_detail_design);


--
-- TOC entry 2663 (class 1259 OID 2996202)
-- Dependencies: 190 3093
-- Name: idx_detail_etat_essai; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_detail_etat_essai ON essai_detail_etat USING btree (id_essai);


--
-- TOC entry 2854 (class 1259 OID 2996744)
-- Dependencies: 309 3093
-- Name: idx_detail_stockage_detail_logistique; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_detail_stockage_detail_logistique ON produit_detail_stockage USING btree (id_detail_logistique);


--
-- TOC entry 2886 (class 1259 OID 2996833)
-- Dependencies: 327 3093
-- Name: idx_detailstockage_retourpatient; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_detailstockage_retourpatient ON retour_patient USING btree (id_detailstockage);


--
-- TOC entry 2780 (class 1259 OID 2996533)
-- Dependencies: 265 3093
-- Name: idx_disp_globale_dotation; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_disp_globale_dotation ON mvt_dispensation_globale USING btree (id_dotation);


--
-- TOC entry 2602 (class 1259 OID 2996030)
-- Dependencies: 153 3093
-- Name: idx_disp_ordon; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_disp_ordon ON dispensation USING btree (id_ordonnancier);


--
-- TOC entry 2603 (class 1259 OID 2996031)
-- Dependencies: 153 3093
-- Name: idx_disp_pharmacie; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_disp_pharmacie ON dispensation USING btree (id_pharmacie);


--
-- TOC entry 2776 (class 1259 OID 2996517)
-- Dependencies: 264 3093
-- Name: idx_dispensation_dispensation_produit; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_dispensation_dispensation_produit ON mvt_dispensation USING btree (id_dispensation);


--
-- TOC entry 2619 (class 1259 OID 2996096)
-- Dependencies: 160 3093
-- Name: idx_dispensation_elementtocheck; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_dispensation_elementtocheck ON element_to_check USING btree (id_dispensation);


--
-- TOC entry 2611 (class 1259 OID 2996058)
-- Dependencies: 158 3093
-- Name: idx_dotation_cond; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_dotation_cond ON dotation USING btree (id_conditionnement);


--
-- TOC entry 2612 (class 1259 OID 2996061)
-- Dependencies: 158 3093
-- Name: idx_dotation_essai; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_dotation_essai ON dotation USING btree (id_essai);


--
-- TOC entry 2613 (class 1259 OID 2996060)
-- Dependencies: 158 3093
-- Name: idx_dotation_personne; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_dotation_personne ON dotation USING btree (id_personne);


--
-- TOC entry 2614 (class 1259 OID 2996057)
-- Dependencies: 158 3093
-- Name: idx_dotation_pharma; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_dotation_pharma ON dotation USING btree (id_pharmacie);


--
-- TOC entry 2615 (class 1259 OID 2996059)
-- Dependencies: 158 3093
-- Name: idx_dotation_produit; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_dotation_produit ON dotation USING btree (id_produit);


--
-- TOC entry 2616 (class 1259 OID 2996062)
-- Dependencies: 158 3093
-- Name: idx_dotation_service; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_dotation_service ON dotation USING btree (id_service);


--
-- TOC entry 2620 (class 1259 OID 2996095)
-- Dependencies: 160 3093
-- Name: idx_elementtocheck_ordon; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_elementtocheck_ordon ON element_to_check USING btree (id_ordonnancier);


--
-- TOC entry 2785 (class 1259 OID 2996549)
-- Dependencies: 267 3093
-- Name: idx_elementtocheck_ordon2; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_elementtocheck_ordon2 ON mvt_preparationentree USING btree (id_ordonnancier);


--
-- TOC entry 2621 (class 1259 OID 2996094)
-- Dependencies: 160 3093
-- Name: idx_eltcheck_personne; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_eltcheck_personne ON element_to_check USING btree (id_personne);


--
-- TOC entry 2629 (class 1259 OID 2996129)
-- Dependencies: 164 3093
-- Name: idx_essai_commentaire_detail_administratif; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_essai_commentaire_detail_administratif ON essai_commentaire_detail_administratif_archi USING btree (id_detailadministratif);


--
-- TOC entry 2632 (class 1259 OID 2996135)
-- Dependencies: 166 3093
-- Name: idx_essai_commentaire_detail_faisabilite; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_essai_commentaire_detail_faisabilite ON essai_commentaire_detail_faisabilite USING btree (id_detailfaisabilite);


--
-- TOC entry 2635 (class 1259 OID 2996141)
-- Dependencies: 168 3093
-- Name: idx_essai_commentaire_detail_recherche; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_essai_commentaire_detail_recherche ON essai_commentaire_detail_recherche USING btree (id_detailrecherche);


--
-- TOC entry 2599 (class 1259 OID 2996017)
-- Dependencies: 151 3093
-- Name: idx_essai_cro_sigrec; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_essai_cro_sigrec ON cro_sigrec USING btree (id_essai);


--
-- TOC entry 2699 (class 1259 OID 2996298)
-- Dependencies: 217 3093
-- Name: idx_essai_document_detail_administratif; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_essai_document_detail_administratif ON essai_document_detail_administratif USING btree (id_detailadministratif);


--
-- TOC entry 2706 (class 1259 OID 2996314)
-- Dependencies: 223 3093
-- Name: idx_essai_document_detail_surcout; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_essai_document_detail_surcout ON essai_document_detail_surcout USING btree (id_detailsurcout);


--
-- TOC entry 2723 (class 1259 OID 2996355)
-- Dependencies: 234 3093
-- Name: idx_essai_evenement; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_essai_evenement ON evenement USING btree (id_essai);


--
-- TOC entry 2740 (class 1259 OID 2996401)
-- Dependencies: 246 3093
-- Name: idx_essai_incident; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_essai_incident ON incident USING btree (id_essai);


--
-- TOC entry 2746 (class 1259 OID 2996413)
-- Dependencies: 250 3093
-- Name: idx_essai_inclusion; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_essai_inclusion ON inclusion USING btree (id_essai);


--
-- TOC entry 2849 (class 1259 OID 2996731)
-- Dependencies: 305 3093
-- Name: idx_essai_produit_detail_produit; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_essai_produit_detail_produit ON produit USING btree (id_detailproduit);


--
-- TOC entry 2887 (class 1259 OID 2996830)
-- Dependencies: 327 3093
-- Name: idx_essai_retourpatient; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_essai_retourpatient ON retour_patient USING btree (id_essai);


--
-- TOC entry 2815 (class 1259 OID 2996629)
-- Dependencies: 286 3093
-- Name: idx_etab_pharma; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_etab_pharma ON pharmacie USING btree (id_etablissement);


--
-- TOC entry 2828 (class 1259 OID 2996667)
-- Dependencies: 294 3093
-- Name: idx_etab_pole; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_etab_pole ON pole USING btree (id_etablissement);


--
-- TOC entry 2903 (class 1259 OID 2996888)
-- Dependencies: 335 3093
-- Name: idx_etab_site; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_etab_site ON site USING btree (id_etablissement);


--
-- TOC entry 2724 (class 1259 OID 2996354)
-- Dependencies: 234 3093
-- Name: idx_evenement_personne; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_evenement_personne ON evenement USING btree (id_personne);


--
-- TOC entry 2754 (class 1259 OID 2996445)
-- Dependencies: 255 3093
-- Name: idx_grille_item; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_grille_item ON item USING btree (id_grille);


--
-- TOC entry 2730 (class 1259 OID 2996372)
-- Dependencies: 238 3093
-- Name: idx_grille_modele_grille; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_grille_modele_grille ON grille USING btree (id_grille_modele);


--
-- TOC entry 2913 (class 1259 OID 2996912)
-- Dependencies: 341 3093
-- Name: idx_grille_modele_theme; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_grille_modele_theme ON theme USING btree (id_grille_modele);


--
-- TOC entry 2836 (class 1259 OID 2996686)
-- Dependencies: 299 3093
-- Name: idx_inclusion_prescription; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_inclusion_prescription ON prescription USING btree (id_inclusion);


--
-- TOC entry 2837 (class 1259 OID 2996687)
-- Dependencies: 299 3093
-- Name: idx_investigateur_prescription; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_investigateur_prescription ON prescription USING btree (id_investigateur);


--
-- TOC entry 2711 (class 1259 OID 2996331)
-- Dependencies: 226 3093
-- Name: idx_investigateur_principal_essai_sigrec; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_investigateur_principal_essai_sigrec ON essai_sigrec USING btree (id_investigateurprincipal);


--
-- TOC entry 2759 (class 1259 OID 2996463)
-- Dependencies: 258 3093
-- Name: idx_lignestock_conditionnement; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_lignestock_conditionnement ON lignestock USING btree (id_conditionnement);


--
-- TOC entry 2760 (class 1259 OID 2996464)
-- Dependencies: 258 3093
-- Name: idx_lignestock_essai; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_lignestock_essai ON lignestock USING btree (id_essai);


--
-- TOC entry 2761 (class 1259 OID 2996461)
-- Dependencies: 258 3093
-- Name: idx_lignestock_pharmacie; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_lignestock_pharmacie ON lignestock USING btree (id_pharmacie);


--
-- TOC entry 2762 (class 1259 OID 2996462)
-- Dependencies: 258 3093
-- Name: idx_lignestock_produit; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_lignestock_produit ON lignestock USING btree (id_produit);


--
-- TOC entry 2790 (class 1259 OID 2996568)
-- Dependencies: 270 3093
-- Name: idx_mvtstock_conditionnement; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_mvtstock_conditionnement ON mvtstock USING btree (id_conditionnement);


--
-- TOC entry 2791 (class 1259 OID 2996565)
-- Dependencies: 270 3093
-- Name: idx_mvtstock_essai; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_mvtstock_essai ON mvtstock USING btree (id_essai);


--
-- TOC entry 2792 (class 1259 OID 2996569)
-- Dependencies: 270 3093
-- Name: idx_mvtstock_personne; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_mvtstock_personne ON mvtstock USING btree (id_personne);


--
-- TOC entry 2793 (class 1259 OID 2996567)
-- Dependencies: 270 3093
-- Name: idx_mvtstock_pharmacie; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_mvtstock_pharmacie ON mvtstock USING btree (id_pharmacie);


--
-- TOC entry 2771 (class 1259 OID 2996500)
-- Dependencies: 262 3093
-- Name: idx_mvtstock_pharmaciedest; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_mvtstock_pharmaciedest ON mvt_cession_pui USING btree (id_pharmaciedest);


--
-- TOC entry 2794 (class 1259 OID 2996566)
-- Dependencies: 270 3093
-- Name: idx_mvtstock_produit; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_mvtstock_produit ON mvtstock USING btree (id_produit);


--
-- TOC entry 2739 (class 1259 OID 2996395)
-- Dependencies: 244 3093
-- Name: idx_patient_historique_patient; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_patient_historique_patient ON historique_patient USING btree (id_patient);


--
-- TOC entry 2747 (class 1259 OID 2996414)
-- Dependencies: 250 3093
-- Name: idx_patient_inclusion; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_patient_inclusion ON inclusion USING btree (id_patient);


--
-- TOC entry 2888 (class 1259 OID 2996831)
-- Dependencies: 327 3093
-- Name: idx_patient_retourpatient; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_patient_retourpatient ON retour_patient USING btree (id_patient);


--
-- TOC entry 2736 (class 1259 OID 2996384)
-- Dependencies: 242 3093
-- Name: idx_personne_habilitation; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_personne_habilitation ON habilitation USING btree (id_personne);


--
-- TOC entry 2889 (class 1259 OID 2996829)
-- Dependencies: 327 3093
-- Name: idx_personne_retourpatient; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_personne_retourpatient ON retour_patient USING btree (id_personne);


--
-- TOC entry 2625 (class 1259 OID 2996118)
-- Dependencies: 162 3093
-- Name: idx_pharma_essai; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_pharma_essai ON essai USING btree (id_pharma);


--
-- TOC entry 2909 (class 1259 OID 2996900)
-- Dependencies: 339 3093
-- Name: idx_pharmacie_stockage; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_pharmacie_stockage ON stockage USING btree (id_pharmacie);


--
-- TOC entry 2823 (class 1259 OID 2996651)
-- Dependencies: 291 3093
-- Name: idx_pharmacien_document_pharmacien; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_pharmacien_document_pharmacien ON pharmacien_document_pharmacien USING btree (id_pharmacien);


--
-- TOC entry 2896 (class 1259 OID 2996871)
-- Dependencies: 331 3093
-- Name: idx_pole_service; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_pole_service ON service USING btree (id_pole);


--
-- TOC entry 2842 (class 1259 OID 2996708)
-- Dependencies: 301 3093
-- Name: idx_prescription_conditionnement; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_prescription_conditionnement ON prescription_type USING btree (id_conditionnement);


--
-- TOC entry 2604 (class 1259 OID 2996029)
-- Dependencies: 153 3093
-- Name: idx_prescription_dispensation; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_prescription_dispensation ON dispensation USING btree (id_prescription);


--
-- TOC entry 2843 (class 1259 OID 2996710)
-- Dependencies: 301 3093
-- Name: idx_prescription_produit; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_prescription_produit ON prescription_type USING btree (id_produit);


--
-- TOC entry 2862 (class 1259 OID 2996765)
-- Dependencies: 313 3093
-- Name: idx_prescription_produit_prescrit; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_prescription_produit_prescrit ON produit_prescrit USING btree (id_prescription);


--
-- TOC entry 2844 (class 1259 OID 2996709)
-- Dependencies: 301 3093
-- Name: idx_prescription_sequence; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_prescription_sequence ON prescription_type USING btree (id_sequence);


--
-- TOC entry 2855 (class 1259 OID 2996743)
-- Dependencies: 309 3093
-- Name: idx_produit_pharmacie; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_produit_pharmacie ON produit_detail_stockage USING btree (id_pharmacie);


--
-- TOC entry 2777 (class 1259 OID 2996516)
-- Dependencies: 264 3093
-- Name: idx_produit_prescrit_dispensation_produit; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_produit_prescrit_dispensation_produit ON mvt_dispensation USING btree (id_produitprescrit);


--
-- TOC entry 2622 (class 1259 OID 2996093)
-- Dependencies: 160 3093
-- Name: idx_produit_prescrit_elementtocheck; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_produit_prescrit_elementtocheck ON element_to_check USING btree (id_produitprescrit);


--
-- TOC entry 2863 (class 1259 OID 2996766)
-- Dependencies: 313 3093
-- Name: idx_produit_produit_prescrit; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_produit_produit_prescrit ON produit_prescrit USING btree (id_produit);


--
-- TOC entry 2890 (class 1259 OID 2996828)
-- Dependencies: 327 3093
-- Name: idx_produit_retourpatient; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_produit_retourpatient ON retour_patient USING btree (id_produit);


--
-- TOC entry 2856 (class 1259 OID 2996742)
-- Dependencies: 309 3093
-- Name: idx_produit_stockage; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_produit_stockage ON produit_detail_stockage USING btree (id_stockage);


--
-- TOC entry 2808 (class 1259 OID 2996617)
-- Dependencies: 282 3093
-- Name: idx_promo_arcpromo; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_promo_arcpromo ON personne USING btree (id_promoteur);


--
-- TOC entry 2809 (class 1259 OID 2996616)
-- Dependencies: 282 3093
-- Name: idx_promo_contactpromo; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_promo_contactpromo ON personne USING btree (id_promoteur);


--
-- TOC entry 2626 (class 1259 OID 2996117)
-- Dependencies: 162 3093
-- Name: idx_promo_essai; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_promo_essai ON essai USING btree (id_promoteur);


--
-- TOC entry 2712 (class 1259 OID 2996330)
-- Dependencies: 226 3093
-- Name: idx_promo_essai_sigrec; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_promo_essai_sigrec ON essai_sigrec USING btree (id_promoteur);


--
-- TOC entry 2572 (class 1259 OID 2995939)
-- Dependencies: 134 3093
-- Name: idx_promoteur_arc_promoteur_sigrec; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_promoteur_arc_promoteur_sigrec ON arc_promoteur_sigrec USING btree (id_promoteur);


--
-- TOC entry 2838 (class 1259 OID 2996685)
-- Dependencies: 299 3093
-- Name: idx_sequence_prescriptin; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_sequence_prescriptin ON prescription USING btree (id_sequence);


--
-- TOC entry 2839 (class 1259 OID 2996684)
-- Dependencies: 299 3093
-- Name: idx_service_prescription; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_service_prescription ON prescription USING btree (id_service);


--
-- TOC entry 2897 (class 1259 OID 2996870)
-- Dependencies: 331 3093
-- Name: idx_site_service; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_site_service ON service USING btree (id_site);


--
-- TOC entry 2910 (class 1259 OID 2996901)
-- Dependencies: 339 3093
-- Name: idx_stockage_parent; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_stockage_parent ON stockage USING btree (id_stockage_parent);


--
-- TOC entry 2640 (class 1259 OID 2996152)
-- Dependencies: 172 3093
-- Name: idx_suivi_detail_administratif; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_suivi_detail_administratif ON essai_detail_administratif_suivi USING btree (id_detail_administratif);


--
-- TOC entry 2645 (class 1259 OID 2996163)
-- Dependencies: 176 3093
-- Name: idx_suivi_detail_autres_documents; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_suivi_detail_autres_documents ON essai_detail_autres_documents_suivi USING btree (id_detail_autres_documents);


--
-- TOC entry 2650 (class 1259 OID 2996174)
-- Dependencies: 180 3093
-- Name: idx_suivi_detail_contacts; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_suivi_detail_contacts ON essai_detail_contacts_suivi USING btree (id_detail_contacts);


--
-- TOC entry 2655 (class 1259 OID 2996185)
-- Dependencies: 184 3093
-- Name: idx_suivi_detail_dates; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_suivi_detail_dates ON essai_detail_dates_suivi USING btree (id_detail_dates);


--
-- TOC entry 2660 (class 1259 OID 2996196)
-- Dependencies: 188 3093
-- Name: idx_suivi_detail_design; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_suivi_detail_design ON essai_detail_design_suivi USING btree (id_detail_design);


--
-- TOC entry 2670 (class 1259 OID 2996223)
-- Dependencies: 195 3093
-- Name: idx_suivi_detail_faisabilite; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_suivi_detail_faisabilite ON essai_detail_faisabilite_suivi USING btree (id_detail_faisabilite);


--
-- TOC entry 2679 (class 1259 OID 2996254)
-- Dependencies: 201 3093
-- Name: idx_suivi_detail_pharma; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_suivi_detail_pharma ON essai_detail_pharma_suivi USING btree (id_detail_pharma);


--
-- TOC entry 2684 (class 1259 OID 2996265)
-- Dependencies: 205 3093
-- Name: idx_suivi_detail_produit; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_suivi_detail_produit ON essai_detail_produit_suivi USING btree (id_detail_produit);


--
-- TOC entry 2691 (class 1259 OID 2996281)
-- Dependencies: 211 3093
-- Name: idx_suivi_detail_recherche; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_suivi_detail_recherche ON essai_detail_recherche_suivi USING btree (id_detail_recherche);


--
-- TOC entry 2696 (class 1259 OID 2996292)
-- Dependencies: 215 3093
-- Name: idx_suivi_detail_surcout; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_suivi_detail_surcout ON essai_detail_surcout_suivi USING btree (id_detail_surcout);


--
-- TOC entry 2715 (class 1259 OID 2996342)
-- Dependencies: 228 3093
-- Name: idx_suivi_essai; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_suivi_essai ON essai_suivi USING btree (id_essai);


--
-- TOC entry 2720 (class 1259 OID 2996348)
-- Dependencies: 232 3093
-- Name: idx_suivi_etablissement; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_suivi_etablissement ON etablissement_suivi USING btree (id_etablissement);


--
-- TOC entry 2727 (class 1259 OID 2996366)
-- Dependencies: 236 3093
-- Name: idx_suivi_evenement; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_suivi_evenement ON evenement_suivi USING btree (id_evenement);


--
-- TOC entry 2743 (class 1259 OID 2996407)
-- Dependencies: 248 3093
-- Name: idx_suivi_incident; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_suivi_incident ON incident_suivi USING btree (id_incident);


--
-- TOC entry 2805 (class 1259 OID 2996610)
-- Dependencies: 280 3093
-- Name: idx_suivi_patient; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_suivi_patient ON patient_suivi USING btree (id_patient);


--
-- TOC entry 2812 (class 1259 OID 2996623)
-- Dependencies: 284 3093
-- Name: idx_suivi_personne; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_suivi_personne ON personne_suivi USING btree (id_personne);


--
-- TOC entry 2820 (class 1259 OID 2996645)
-- Dependencies: 289 3093
-- Name: idx_suivi_pharmacie; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_suivi_pharmacie ON pharmacie_suivi USING btree (id_pharmacie);


--
-- TOC entry 2831 (class 1259 OID 2996673)
-- Dependencies: 296 3093
-- Name: idx_suivi_pole; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_suivi_pole ON pole_suivi USING btree (id_pole);


--
-- TOC entry 2868 (class 1259 OID 2996793)
-- Dependencies: 316 3093
-- Name: idx_suivi_produit; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_suivi_produit ON produit_suivi USING btree (id_produit);


--
-- TOC entry 2878 (class 1259 OID 2996810)
-- Dependencies: 323 3093
-- Name: idx_suivi_promoteur; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_suivi_promoteur ON promoteur_suivi USING btree (id_promoteur);


--
-- TOC entry 2900 (class 1259 OID 2996882)
-- Dependencies: 333 3093
-- Name: idx_suivi_service; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_suivi_service ON service_suivi USING btree (id_service);


--
-- TOC entry 2906 (class 1259 OID 2996894)
-- Dependencies: 337 3093
-- Name: idx_suivi_site; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_suivi_site ON site_suivi USING btree (id_site);


--
-- TOC entry 2584 (class 1259 OID 2995983)
-- Dependencies: 141 3093
-- Name: idx_theme_categorie; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_theme_categorie ON categorie USING btree (id_theme);


--
-- TOC entry 2882 (class 1259 OID 2996816)
-- Dependencies: 325 3093
-- Name: idx_theme_regle; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_theme_regle ON regle_surcout USING btree (id_theme);


--
-- TOC entry 2993 (class 2606 OID 2996356)
-- Dependencies: 162 2623 234 3093
-- Name: fk1174a6939fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY evenement
    ADD CONSTRAINT fk1174a6939fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2994 (class 2606 OID 2996361)
-- Dependencies: 234 282 2810 3093
-- Name: fk1174a698800c21; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY evenement
    ADD CONSTRAINT fk1174a698800c21 FOREIGN KEY (id_personne) REFERENCES personne(id);


--
-- TOC entry 3038 (class 2606 OID 2996605)
-- Dependencies: 286 2816 276 3093
-- Name: fk12c037c73a903eb7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ordonnancier_fab_reconst
    ADD CONSTRAINT fk12c037c73a903eb7 FOREIGN KEY (id_pharma) REFERENCES pharmacie(id);


--
-- TOC entry 2970 (class 2606 OID 2996229)
-- Dependencies: 162 2623 197 3093
-- Name: fk17e192d939fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_detail_pharma
    ADD CONSTRAINT fk17e192d939fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 3013 (class 2606 OID 2996470)
-- Dependencies: 258 162 2623 3093
-- Name: fk187831f539fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY lignestock
    ADD CONSTRAINT fk187831f539fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 3012 (class 2606 OID 2996465)
-- Dependencies: 2816 258 286 3093
-- Name: fk187831f54de40194; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY lignestock
    ADD CONSTRAINT fk187831f54de40194 FOREIGN KEY (id_pharmacie) REFERENCES pharmacie(id);


--
-- TOC entry 3014 (class 2606 OID 2996475)
-- Dependencies: 147 258 2591 3093
-- Name: fk187831f59d7535de; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY lignestock
    ADD CONSTRAINT fk187831f59d7535de FOREIGN KEY (id_conditionnement) REFERENCES conditionnement(id);


--
-- TOC entry 3015 (class 2606 OID 2996480)
-- Dependencies: 305 2850 258 3093
-- Name: fk187831f5a1ddf650; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY lignestock
    ADD CONSTRAINT fk187831f5a1ddf650 FOREIGN KEY (id_produit) REFERENCES produit(id);


--
-- TOC entry 3040 (class 2606 OID 2996618)
-- Dependencies: 319 282 2873 3093
-- Name: fk1a6a27cc4285b151; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY personne
    ADD CONSTRAINT fk1a6a27cc4285b151 FOREIGN KEY (id_promoteur) REFERENCES promoteur(id);


--
-- TOC entry 3054 (class 2606 OID 2996698)
-- Dependencies: 299 2898 331 3093
-- Name: fk1b6fa41a1cf65f51; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prescription
    ADD CONSTRAINT fk1b6fa41a1cf65f51 FOREIGN KEY (id_service) REFERENCES service(id);


--
-- TOC entry 3053 (class 2606 OID 2996693)
-- Dependencies: 299 2894 329 3093
-- Name: fk1b6fa41a807681fd; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prescription
    ADD CONSTRAINT fk1b6fa41a807681fd FOREIGN KEY (id_sequence) REFERENCES sequence(id);


--
-- TOC entry 3055 (class 2606 OID 2996703)
-- Dependencies: 2748 250 299 3093
-- Name: fk1b6fa41adb692012; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prescription
    ADD CONSTRAINT fk1b6fa41adb692012 FOREIGN KEY (id_inclusion) REFERENCES inclusion(id);


--
-- TOC entry 3052 (class 2606 OID 2996688)
-- Dependencies: 282 2810 299 3093
-- Name: fk1b6fa41aea08da8f; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prescription
    ADD CONSTRAINT fk1b6fa41aea08da8f FOREIGN KEY (id_investigateur) REFERENCES personne(id);


--
-- TOC entry 2920 (class 2606 OID 2995945)
-- Dependencies: 134 2594 149 3093
-- Name: fk1df3b08e91ecd533; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY arc_promoteur_sigrec
    ADD CONSTRAINT fk1df3b08e91ecd533 FOREIGN KEY (id_contact) REFERENCES contact_sigrec(id);


--
-- TOC entry 2919 (class 2606 OID 2995940)
-- Dependencies: 321 2876 134 3093
-- Name: fk1df3b08ed4112aed; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY arc_promoteur_sigrec
    ADD CONSTRAINT fk1df3b08ed4112aed FOREIGN KEY (id_promoteur) REFERENCES promoteur_sigrec(id);


--
-- TOC entry 3048 (class 2606 OID 2996662)
-- Dependencies: 2810 292 282 3093
-- Name: fk1eabc02f24482761; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pharmacien_pharmacie
    ADD CONSTRAINT fk1eabc02f24482761 FOREIGN KEY (id_pharmacien) REFERENCES personne(id);


--
-- TOC entry 3047 (class 2606 OID 2996657)
-- Dependencies: 286 2816 292 3093
-- Name: fk1eabc02f4de40194; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pharmacien_pharmacie
    ADD CONSTRAINT fk1eabc02f4de40194 FOREIGN KEY (id_pharmacie) REFERENCES pharmacie(id);


--
-- TOC entry 2983 (class 2606 OID 2996299)
-- Dependencies: 2636 170 217 3093
-- Name: fk20a01eebb314ca7e; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_document_detail_administratif
    ADD CONSTRAINT fk20a01eebb314ca7e FOREIGN KEY (id_detailadministratif) REFERENCES essai_detail_administratif(id);


--
-- TOC entry 2986 (class 2606 OID 2996315)
-- Dependencies: 213 2692 223 3093
-- Name: fk24399e3f3233d23a; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_document_detail_surcout
    ADD CONSTRAINT fk24399e3f3233d23a FOREIGN KEY (id_detailsurcout) REFERENCES essai_detail_surcout(id);


--
-- TOC entry 3088 (class 2606 OID 2996895)
-- Dependencies: 337 335 2904 3093
-- Name: fk2694c8427aad8e07; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY site_suivi
    ADD CONSTRAINT fk2694c8427aad8e07 FOREIGN KEY (id_site) REFERENCES site(id);


--
-- TOC entry 2960 (class 2606 OID 2996175)
-- Dependencies: 180 2646 178 3093
-- Name: fk2a86a3aa27453d52; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_detail_contacts_suivi
    ADD CONSTRAINT fk2a86a3aa27453d52 FOREIGN KEY (id_detail_contacts) REFERENCES essai_detail_contacts(id);


--
-- TOC entry 2925 (class 2606 OID 2995973)
-- Dependencies: 139 2656 186 3093
-- Name: fk2e4482387f8764; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY bras
    ADD CONSTRAINT fk2e4482387f8764 FOREIGN KEY (id_detail_design) REFERENCES essai_detail_design(id);


--
-- TOC entry 2926 (class 2606 OID 2995978)
-- Dependencies: 139 139 2578 3093
-- Name: fk2e44824d844dbc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY bras
    ADD CONSTRAINT fk2e44824d844dbc FOREIGN KEY (id_bras_parent) REFERENCES bras(id);


--
-- TOC entry 2936 (class 2606 OID 2996037)
-- Dependencies: 2816 153 286 3093
-- Name: fk2eaeffed4de40194; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY dispensation
    ADD CONSTRAINT fk2eaeffed4de40194 FOREIGN KEY (id_pharmacie) REFERENCES pharmacie(id);


--
-- TOC entry 2935 (class 2606 OID 2996032)
-- Dependencies: 153 299 2840 3093
-- Name: fk2eaeffed87ff1713; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY dispensation
    ADD CONSTRAINT fk2eaeffed87ff1713 FOREIGN KEY (id_prescription) REFERENCES prescription(id);


--
-- TOC entry 2937 (class 2606 OID 2996042)
-- Dependencies: 274 2799 153 3093
-- Name: fk2eaeffedb539f569; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY dispensation
    ADD CONSTRAINT fk2eaeffedb539f569 FOREIGN KEY (id_ordonnancier) REFERENCES ordonnancier_dispensation(id);


--
-- TOC entry 2999 (class 2606 OID 2996390)
-- Dependencies: 2646 178 242 3093
-- Name: fk2fee5dbe27453d52; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY habilitation
    ADD CONSTRAINT fk2fee5dbe27453d52 FOREIGN KEY (id_detail_contacts) REFERENCES essai_detail_contacts(id);


--
-- TOC entry 2998 (class 2606 OID 2996385)
-- Dependencies: 2810 242 282 3093
-- Name: fk2fee5dbe8800c21; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY habilitation
    ADD CONSTRAINT fk2fee5dbe8800c21 FOREIGN KEY (id_personne) REFERENCES personne(id);


--
-- TOC entry 3009 (class 2606 OID 2996446)
-- Dependencies: 238 2728 255 3093
-- Name: fk317b13976c82de; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY item
    ADD CONSTRAINT fk317b13976c82de FOREIGN KEY (id_grille) REFERENCES grille(id);


--
-- TOC entry 2963 (class 2606 OID 2996191)
-- Dependencies: 2623 162 186 3093
-- Name: fk345311a39fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_detail_design
    ADD CONSTRAINT fk345311a39fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 3049 (class 2606 OID 2996668)
-- Dependencies: 230 294 2716 3093
-- Name: fk3497b8cd45a413; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pole
    ADD CONSTRAINT fk3497b8cd45a413 FOREIGN KEY (id_etablissement) REFERENCES etablissement(id);


--
-- TOC entry 3087 (class 2606 OID 2996889)
-- Dependencies: 230 335 2716 3093
-- Name: fk35df47cd45a413; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY site
    ADD CONSTRAINT fk35df47cd45a413 FOREIGN KEY (id_etablissement) REFERENCES etablissement(id);


--
-- TOC entry 2938 (class 2606 OID 2996047)
-- Dependencies: 2850 305 154 3093
-- Name: fk40b816e0ae5cce6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY dispositif_medical
    ADD CONSTRAINT fk40b816e0ae5cce6 FOREIGN KEY (id) REFERENCES produit(id);


--
-- TOC entry 3028 (class 2606 OID 2996550)
-- Dependencies: 260 267 2767 3093
-- Name: fk414415ea389dcf45; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY mvt_preparationentree
    ADD CONSTRAINT fk414415ea389dcf45 FOREIGN KEY (id) REFERENCES mvt_approvisionnement(id);


--
-- TOC entry 3029 (class 2606 OID 2996555)
-- Dependencies: 267 276 2801 3093
-- Name: fk414415ea97d4f410; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY mvt_preparationentree
    ADD CONSTRAINT fk414415ea97d4f410 FOREIGN KEY (id_ordonnancier) REFERENCES ordonnancier_fab_reconst(id);


--
-- TOC entry 2932 (class 2606 OID 2996012)
-- Dependencies: 305 147 2850 3093
-- Name: fk46e35c70a1ddf650; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY conditionnement
    ADD CONSTRAINT fk46e35c70a1ddf650 FOREIGN KEY (id_produit) REFERENCES produit(id);


--
-- TOC entry 3046 (class 2606 OID 2996652)
-- Dependencies: 291 2810 282 3093
-- Name: fk47ad8dfd24482761; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pharmacien_document_pharmacien
    ADD CONSTRAINT fk47ad8dfd24482761 FOREIGN KEY (id_pharmacien) REFERENCES personne(id);


--
-- TOC entry 2957 (class 2606 OID 2996158)
-- Dependencies: 174 162 2623 3093
-- Name: fk4c82a77539fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_detail_autres_documents
    ADD CONSTRAINT fk4c82a77539fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 3045 (class 2606 OID 2996646)
-- Dependencies: 286 2816 289 3093
-- Name: fk4d5ce8dd4de40194; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pharmacie_suivi
    ADD CONSTRAINT fk4d5ce8dd4de40194 FOREIGN KEY (id_pharmacie) REFERENCES pharmacie(id);


--
-- TOC entry 2965 (class 2606 OID 2996203)
-- Dependencies: 190 162 2623 3093
-- Name: fk4e973f7e39fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_detail_etat
    ADD CONSTRAINT fk4e973f7e39fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2943 (class 2606 OID 2996078)
-- Dependencies: 158 2898 331 3093
-- Name: fk4f489b4c1cf65f51; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY dotation
    ADD CONSTRAINT fk4f489b4c1cf65f51 FOREIGN KEY (id_service) REFERENCES service(id);


--
-- TOC entry 2941 (class 2606 OID 2996068)
-- Dependencies: 158 162 2623 3093
-- Name: fk4f489b4c39fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY dotation
    ADD CONSTRAINT fk4f489b4c39fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2940 (class 2606 OID 2996063)
-- Dependencies: 286 2816 158 3093
-- Name: fk4f489b4c4de40194; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY dotation
    ADD CONSTRAINT fk4f489b4c4de40194 FOREIGN KEY (id_pharmacie) REFERENCES pharmacie(id);


--
-- TOC entry 2945 (class 2606 OID 2996088)
-- Dependencies: 282 2810 158 3093
-- Name: fk4f489b4c8800c21; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY dotation
    ADD CONSTRAINT fk4f489b4c8800c21 FOREIGN KEY (id_personne) REFERENCES personne(id);


--
-- TOC entry 2942 (class 2606 OID 2996073)
-- Dependencies: 147 158 2591 3093
-- Name: fk4f489b4c9d7535de; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY dotation
    ADD CONSTRAINT fk4f489b4c9d7535de FOREIGN KEY (id_conditionnement) REFERENCES conditionnement(id);


--
-- TOC entry 2944 (class 2606 OID 2996083)
-- Dependencies: 2850 158 305 3093
-- Name: fk4f489b4ca1ddf650; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY dotation
    ADD CONSTRAINT fk4f489b4ca1ddf650 FOREIGN KEY (id_produit) REFERENCES produit(id);


--
-- TOC entry 3083 (class 2606 OID 2996865)
-- Dependencies: 139 2578 329 3093
-- Name: fk507077c145975293; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sequence
    ADD CONSTRAINT fk507077c145975293 FOREIGN KEY (id_bras_sequence) REFERENCES bras(id);


--
-- TOC entry 3001 (class 2606 OID 2996402)
-- Dependencies: 162 246 2623 3093
-- Name: fk52f44d239fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY incident
    ADD CONSTRAINT fk52f44d239fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2953 (class 2606 OID 2996136)
-- Dependencies: 192 2664 166 3093
-- Name: fk547c5a9a8897241c; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_commentaire_detail_faisabilite
    ADD CONSTRAINT fk547c5a9a8897241c FOREIGN KEY (id_detailfaisabilite) REFERENCES essai_detail_faisabilite(id);


--
-- TOC entry 3059 (class 2606 OID 2996726)
-- Dependencies: 2709 226 303 3093
-- Name: fk55375893a81b195e; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prevision_sigrec
    ADD CONSTRAINT fk55375893a81b195e FOREIGN KEY (id_essai) REFERENCES essai_sigrec(id);


--
-- TOC entry 2966 (class 2606 OID 2996208)
-- Dependencies: 192 162 2623 3093
-- Name: fk5a8d447539fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_detail_faisabilite
    ADD CONSTRAINT fk5a8d447539fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 3002 (class 2606 OID 2996408)
-- Dependencies: 2741 246 248 3093
-- Name: fk5b30998db77789cb; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY incident_suivi
    ADD CONSTRAINT fk5b30998db77789cb FOREIGN KEY (id_incident) REFERENCES incident(id);


--
-- TOC entry 2929 (class 2606 OID 2995996)
-- Dependencies: 2585 143 145 3093
-- Name: fk5b85a4f55c631481; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY co_investigateur_sigrec
    ADD CONSTRAINT fk5b85a4f55c631481 FOREIGN KEY (id_centre) REFERENCES centre_sigrec(id);


--
-- TOC entry 2930 (class 2606 OID 2996001)
-- Dependencies: 2594 149 145 3093
-- Name: fk5b85a4f591ecd533; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY co_investigateur_sigrec
    ADD CONSTRAINT fk5b85a4f591ecd533 FOREIGN KEY (id_contact) REFERENCES contact_sigrec(id);


--
-- TOC entry 2931 (class 2606 OID 2996006)
-- Dependencies: 145 226 2709 3093
-- Name: fk5b85a4f5a81b195e; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY co_investigateur_sigrec
    ADD CONSTRAINT fk5b85a4f5a81b195e FOREIGN KEY (id_essai) REFERENCES essai_sigrec(id);


--
-- TOC entry 2951 (class 2606 OID 2996124)
-- Dependencies: 286 2816 162 3093
-- Name: fk5c5486d3a903eb7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai
    ADD CONSTRAINT fk5c5486d3a903eb7 FOREIGN KEY (id_pharma) REFERENCES pharmacie(id);


--
-- TOC entry 2950 (class 2606 OID 2996119)
-- Dependencies: 2873 319 162 3093
-- Name: fk5c5486d4285b151; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai
    ADD CONSTRAINT fk5c5486d4285b151 FOREIGN KEY (id_promoteur) REFERENCES promoteur(id);


--
-- TOC entry 2978 (class 2606 OID 2996271)
-- Dependencies: 2623 162 207 3093
-- Name: fk5c79a91f39fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_detail_recherche
    ADD CONSTRAINT fk5c79a91f39fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2927 (class 2606 OID 2995984)
-- Dependencies: 341 141 2914 3093
-- Name: fk5d54e13740161942; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY categorie
    ADD CONSTRAINT fk5d54e13740161942 FOREIGN KEY (id_theme) REFERENCES theme(id);


--
-- TOC entry 2928 (class 2606 OID 2995990)
-- Dependencies: 143 2594 149 3093
-- Name: fk5f43710391ecd533; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY centre_sigrec
    ADD CONSTRAINT fk5f43710391ecd533 FOREIGN KEY (id_contact) REFERENCES contact_sigrec(id);


--
-- TOC entry 3041 (class 2606 OID 2996624)
-- Dependencies: 282 284 2810 3093
-- Name: fk60fd9078800c21; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY personne_suivi
    ADD CONSTRAINT fk60fd9078800c21 FOREIGN KEY (id_personne) REFERENCES personne(id);


--
-- TOC entry 3030 (class 2606 OID 2996560)
-- Dependencies: 268 2795 270 3093
-- Name: fk61102bfd3e8f1c97; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY mvt_retour_promoteur
    ADD CONSTRAINT fk61102bfd3e8f1c97 FOREIGN KEY (id) REFERENCES mvtstock(id);


--
-- TOC entry 3090 (class 2606 OID 2996907)
-- Dependencies: 339 286 2816 3093
-- Name: fk658922294de40194; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY stockage
    ADD CONSTRAINT fk658922294de40194 FOREIGN KEY (id_pharmacie) REFERENCES pharmacie(id);


--
-- TOC entry 3089 (class 2606 OID 2996902)
-- Dependencies: 2911 339 339 3093
-- Name: fk65892229b4ed4491; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY stockage
    ADD CONSTRAINT fk65892229b4ed4491 FOREIGN KEY (id_stockage_parent) REFERENCES stockage(id);


--
-- TOC entry 2985 (class 2606 OID 2996309)
-- Dependencies: 221 197 2671 3093
-- Name: fk66a8bf19d08532d; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_document_detail_pharma
    ADD CONSTRAINT fk66a8bf19d08532d FOREIGN KEY (id_detail_pharma) REFERENCES essai_detail_pharma(id);


--
-- TOC entry 3018 (class 2606 OID 2996495)
-- Dependencies: 270 261 2795 3093
-- Name: fk67c907da3e8f1c97; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY mvt_autre_sortie
    ADD CONSTRAINT fk67c907da3e8f1c97 FOREIGN KEY (id) REFERENCES mvtstock(id);


--
-- TOC entry 3091 (class 2606 OID 2996913)
-- Dependencies: 341 2731 240 3093
-- Name: fk69375c9195ade5f; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY theme
    ADD CONSTRAINT fk69375c9195ade5f FOREIGN KEY (id_grille_modele) REFERENCES grille_modele(id);


--
-- TOC entry 3032 (class 2606 OID 2996575)
-- Dependencies: 270 162 2623 3093
-- Name: fk697c8a0b39fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY mvtstock
    ADD CONSTRAINT fk697c8a0b39fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 3031 (class 2606 OID 2996570)
-- Dependencies: 270 2816 286 3093
-- Name: fk697c8a0b4de40194; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY mvtstock
    ADD CONSTRAINT fk697c8a0b4de40194 FOREIGN KEY (id_pharmacie) REFERENCES pharmacie(id);


--
-- TOC entry 3035 (class 2606 OID 2996590)
-- Dependencies: 270 282 2810 3093
-- Name: fk697c8a0b8800c21; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY mvtstock
    ADD CONSTRAINT fk697c8a0b8800c21 FOREIGN KEY (id_personne) REFERENCES personne(id);


--
-- TOC entry 3033 (class 2606 OID 2996580)
-- Dependencies: 2591 270 147 3093
-- Name: fk697c8a0b9d7535de; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY mvtstock
    ADD CONSTRAINT fk697c8a0b9d7535de FOREIGN KEY (id_conditionnement) REFERENCES conditionnement(id);


--
-- TOC entry 3034 (class 2606 OID 2996585)
-- Dependencies: 270 305 2850 3093
-- Name: fk697c8a0ba1ddf650; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY mvtstock
    ADD CONSTRAINT fk697c8a0ba1ddf650 FOREIGN KEY (id_produit) REFERENCES produit(id);


--
-- TOC entry 2958 (class 2606 OID 2996164)
-- Dependencies: 176 2641 174 3093
-- Name: fk698a35f0ec2855a; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_detail_autres_documents_suivi
    ADD CONSTRAINT fk698a35f0ec2855a FOREIGN KEY (id_detail_autres_documents) REFERENCES essai_detail_autres_documents(id);


--
-- TOC entry 2980 (class 2606 OID 2996282)
-- Dependencies: 2685 211 207 3093
-- Name: fk6b5fd01a1cf64d65; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_detail_recherche_suivi
    ADD CONSTRAINT fk6b5fd01a1cf64d65 FOREIGN KEY (id_detail_recherche) REFERENCES essai_detail_recherche(id);


--
-- TOC entry 3074 (class 2606 OID 2996811)
-- Dependencies: 323 319 2873 3093
-- Name: fk6bdaed84285b151; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY promoteur_suivi
    ADD CONSTRAINT fk6bdaed84285b151 FOREIGN KEY (id_promoteur) REFERENCES promoteur(id);


--
-- TOC entry 3061 (class 2606 OID 2996737)
-- Dependencies: 307 305 2850 3093
-- Name: fk6e9d2d16a1ddf650; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY produit_detail_logistique
    ADD CONSTRAINT fk6e9d2d16a1ddf650 FOREIGN KEY (id_produit) REFERENCES produit(id);


--
-- TOC entry 2968 (class 2606 OID 2996218)
-- Dependencies: 193 2898 331 3093
-- Name: fk71236ceb1cf65f51; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_detail_faisabilite_service
    ADD CONSTRAINT fk71236ceb1cf65f51 FOREIGN KEY (id_service) REFERENCES service(id);


--
-- TOC entry 2967 (class 2606 OID 2996213)
-- Dependencies: 192 2664 193 3093
-- Name: fk71236ceb3607a129; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_detail_faisabilite_service
    ADD CONSTRAINT fk71236ceb3607a129 FOREIGN KEY (id_essai) REFERENCES essai_detail_faisabilite(id);


--
-- TOC entry 2939 (class 2606 OID 2996052)
-- Dependencies: 156 213 2692 3093
-- Name: fk75589534f5ae6985; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY donnees_prevision
    ADD CONSTRAINT fk75589534f5ae6985 FOREIGN KEY (id_detail_surcout) REFERENCES essai_detail_surcout(id);


--
-- TOC entry 3084 (class 2606 OID 2996872)
-- Dependencies: 331 2829 294 3093
-- Name: fk7643c6b57aaafee9; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY service
    ADD CONSTRAINT fk7643c6b57aaafee9 FOREIGN KEY (id_pole) REFERENCES pole(id);


--
-- TOC entry 3085 (class 2606 OID 2996877)
-- Dependencies: 335 331 2904 3093
-- Name: fk7643c6b57aad8e07; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY service
    ADD CONSTRAINT fk7643c6b57aad8e07 FOREIGN KEY (id_site) REFERENCES site(id);


--
-- TOC entry 3016 (class 2606 OID 2996485)
-- Dependencies: 2850 259 305 3093
-- Name: fk77228d19ae5cce6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY medicament
    ADD CONSTRAINT fk77228d19ae5cce6 FOREIGN KEY (id) REFERENCES produit(id);


--
-- TOC entry 2969 (class 2606 OID 2996224)
-- Dependencies: 192 2664 195 3093
-- Name: fk7a6b12f0530f1de7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_detail_faisabilite_suivi
    ADD CONSTRAINT fk7a6b12f0530f1de7 FOREIGN KEY (id_detail_faisabilite) REFERENCES essai_detail_faisabilite(id);


--
-- TOC entry 2995 (class 2606 OID 2996367)
-- Dependencies: 2721 234 236 3093
-- Name: fk7c0dd1e41aeddf50; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY evenement_suivi
    ADD CONSTRAINT fk7c0dd1e41aeddf50 FOREIGN KEY (id_evenement) REFERENCES evenement(id);


--
-- TOC entry 3065 (class 2606 OID 2996760)
-- Dependencies: 2850 311 305 3093
-- Name: fk7c4c166aa1ddf650; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY produit_document_actes_pharma
    ADD CONSTRAINT fk7c4c166aa1ddf650 FOREIGN KEY (id_produit) REFERENCES produit(id);


--
-- TOC entry 3024 (class 2606 OID 2996528)
-- Dependencies: 2600 153 264 3093
-- Name: fk800c37c11a1781c6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY mvt_dispensation
    ADD CONSTRAINT fk800c37c11a1781c6 FOREIGN KEY (id_dispensation) REFERENCES dispensation(id);


--
-- TOC entry 3022 (class 2606 OID 2996518)
-- Dependencies: 2795 264 270 3093
-- Name: fk800c37c13e8f1c97; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY mvt_dispensation
    ADD CONSTRAINT fk800c37c13e8f1c97 FOREIGN KEY (id) REFERENCES mvtstock(id);


--
-- TOC entry 3023 (class 2606 OID 2996523)
-- Dependencies: 313 2864 264 3093
-- Name: fk800c37c16d6ee647; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY mvt_dispensation
    ADD CONSTRAINT fk800c37c16d6ee647 FOREIGN KEY (id_produitprescrit) REFERENCES produit_prescrit(id);


--
-- TOC entry 2952 (class 2606 OID 2996130)
-- Dependencies: 164 170 2636 3093
-- Name: fk823d05e4b314ca7e; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_commentaire_detail_administratif_archi
    ADD CONSTRAINT fk823d05e4b314ca7e FOREIGN KEY (id_detailadministratif) REFERENCES essai_detail_administratif(id);


--
-- TOC entry 3006 (class 2606 OID 2996430)
-- Dependencies: 2898 331 251 3093
-- Name: fk833c86321cf65f51; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY investigateur_service
    ADD CONSTRAINT fk833c86321cf65f51 FOREIGN KEY (id_service) REFERENCES service(id);


--
-- TOC entry 3005 (class 2606 OID 2996425)
-- Dependencies: 251 2810 282 3093
-- Name: fk833c8632ea08da8f; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY investigateur_service
    ADD CONSTRAINT fk833c8632ea08da8f FOREIGN KEY (id_investigateur) REFERENCES personne(id);


--
-- TOC entry 2961 (class 2606 OID 2996180)
-- Dependencies: 182 162 2623 3093
-- Name: fk843a3ba939fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_detail_dates
    ADD CONSTRAINT fk843a3ba939fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 3050 (class 2606 OID 2996674)
-- Dependencies: 294 296 2829 3093
-- Name: fk8449a7f37aaafee9; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pole_suivi
    ADD CONSTRAINT fk8449a7f37aaafee9 FOREIGN KEY (id_pole) REFERENCES pole(id);


--
-- TOC entry 3000 (class 2606 OID 2996396)
-- Dependencies: 278 2803 244 3093
-- Name: fk8529d883aedb3264; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY historique_patient
    ADD CONSTRAINT fk8529d883aedb3264 FOREIGN KEY (id_patient) REFERENCES patient(id);


--
-- TOC entry 3017 (class 2606 OID 2996490)
-- Dependencies: 270 2795 260 3093
-- Name: fk869711473e8f1c97; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY mvt_approvisionnement
    ADD CONSTRAINT fk869711473e8f1c97 FOREIGN KEY (id) REFERENCES mvtstock(id);


--
-- TOC entry 2977 (class 2606 OID 2996266)
-- Dependencies: 205 203 2680 3093
-- Name: fk8a0ab6487dbf9eef; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_detail_produit_suivi
    ADD CONSTRAINT fk8a0ab6487dbf9eef FOREIGN KEY (id_detail_produit) REFERENCES essai_detail_produit(id);


--
-- TOC entry 3011 (class 2606 OID 2996456)
-- Dependencies: 325 256 2883 3093
-- Name: fk8b91f4e11622e8a9; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY item_regle
    ADD CONSTRAINT fk8b91f4e11622e8a9 FOREIGN KEY (id_regle) REFERENCES regle_surcout(id);


--
-- TOC entry 3010 (class 2606 OID 2996451)
-- Dependencies: 255 2755 256 3093
-- Name: fk8b91f4e1e0ff5276; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY item_regle
    ADD CONSTRAINT fk8b91f4e1e0ff5276 FOREIGN KEY (id_item) REFERENCES item(id);


--
-- TOC entry 3072 (class 2606 OID 2996799)
-- Dependencies: 305 317 2850 3093
-- Name: fk92053556ae5cce6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY produit_therapeutique
    ADD CONSTRAINT fk92053556ae5cce6 FOREIGN KEY (id) REFERENCES produit(id);


--
-- TOC entry 2972 (class 2606 OID 2996239)
-- Dependencies: 2716 230 198 3093
-- Name: fk93a102d0cd45a413; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_detail_pharma_etablissement
    ADD CONSTRAINT fk93a102d0cd45a413 FOREIGN KEY (id_etablissement) REFERENCES etablissement(id);


--
-- TOC entry 2971 (class 2606 OID 2996234)
-- Dependencies: 2671 197 198 3093
-- Name: fk93a102d0d08532d; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_detail_pharma_etablissement
    ADD CONSTRAINT fk93a102d0d08532d FOREIGN KEY (id_detail_pharma) REFERENCES essai_detail_pharma(id);


--
-- TOC entry 2962 (class 2606 OID 2996186)
-- Dependencies: 2651 184 182 3093
-- Name: fk9506d324a7a1603; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_detail_dates_suivi
    ADD CONSTRAINT fk9506d324a7a1603 FOREIGN KEY (id_detail_dates) REFERENCES essai_detail_dates(id);


--
-- TOC entry 2984 (class 2606 OID 2996304)
-- Dependencies: 174 219 2641 3093
-- Name: fk966f83b5ec2855a; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_document_detail_autres_documents
    ADD CONSTRAINT fk966f83b5ec2855a FOREIGN KEY (id_detail_autres_documents) REFERENCES essai_detail_autres_documents(id);


--
-- TOC entry 2956 (class 2606 OID 2996153)
-- Dependencies: 172 170 2636 3093
-- Name: fk98df0126bf757d89; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_detail_administratif_suivi
    ADD CONSTRAINT fk98df0126bf757d89 FOREIGN KEY (id_detail_administratif) REFERENCES essai_detail_administratif(id);


--
-- TOC entry 3086 (class 2606 OID 2996883)
-- Dependencies: 333 2898 331 3093
-- Name: fk9946e5301cf65f51; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY service_suivi
    ADD CONSTRAINT fk9946e5301cf65f51 FOREIGN KEY (id_service) REFERENCES service(id);


--
-- TOC entry 2981 (class 2606 OID 2996287)
-- Dependencies: 213 2623 162 3093
-- Name: fk9a1b427f39fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_detail_surcout
    ADD CONSTRAINT fk9a1b427f39fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2954 (class 2606 OID 2996142)
-- Dependencies: 2685 207 168 3093
-- Name: fk9b1204844dc45cda; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_commentaire_detail_recherche
    ADD CONSTRAINT fk9b1204844dc45cda FOREIGN KEY (id_detailrecherche) REFERENCES essai_detail_recherche(id);


--
-- TOC entry 2982 (class 2606 OID 2996293)
-- Dependencies: 215 2692 213 3093
-- Name: fk9c00e17af5ae6985; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_detail_surcout_suivi
    ADD CONSTRAINT fk9c00e17af5ae6985 FOREIGN KEY (id_detail_surcout) REFERENCES essai_detail_surcout(id);


--
-- TOC entry 3037 (class 2606 OID 2996600)
-- Dependencies: 274 2816 286 3093
-- Name: fk9ea891de3a903eb7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ordonnancier_dispensation
    ADD CONSTRAINT fk9ea891de3a903eb7 FOREIGN KEY (id_pharma) REFERENCES pharmacie(id);


--
-- TOC entry 2955 (class 2606 OID 2996147)
-- Dependencies: 162 170 2623 3093
-- Name: fka145932b39fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_detail_administratif
    ADD CONSTRAINT fka145932b39fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2974 (class 2606 OID 2996249)
-- Dependencies: 286 199 2816 3093
-- Name: fka30ce23c4de40194; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_detail_pharma_pharmacie
    ADD CONSTRAINT fka30ce23c4de40194 FOREIGN KEY (id_pharmacie) REFERENCES pharmacie(id);


--
-- TOC entry 2973 (class 2606 OID 2996244)
-- Dependencies: 199 197 2671 3093
-- Name: fka30ce23cd08532d; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_detail_pharma_pharmacie
    ADD CONSTRAINT fka30ce23cd08532d FOREIGN KEY (id_detail_pharma) REFERENCES essai_detail_pharma(id);


--
-- TOC entry 3004 (class 2606 OID 2996420)
-- Dependencies: 250 162 2623 3093
-- Name: fka6cdb91c39fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY inclusion
    ADD CONSTRAINT fka6cdb91c39fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 3003 (class 2606 OID 2996415)
-- Dependencies: 2803 278 250 3093
-- Name: fka6cdb91caedb3264; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY inclusion
    ADD CONSTRAINT fka6cdb91caedb3264 FOREIGN KEY (id_patient) REFERENCES patient(id);


--
-- TOC entry 3007 (class 2606 OID 2996435)
-- Dependencies: 143 2585 253 3093
-- Name: fka9985b025c631481; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY investigateur_sigrec
    ADD CONSTRAINT fka9985b025c631481 FOREIGN KEY (id_centre) REFERENCES centre_sigrec(id);


--
-- TOC entry 3008 (class 2606 OID 2996440)
-- Dependencies: 253 2594 149 3093
-- Name: fka9985b0291ecd533; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY investigateur_sigrec
    ADD CONSTRAINT fka9985b0291ecd533 FOREIGN KEY (id_contact) REFERENCES contact_sigrec(id);


--
-- TOC entry 3043 (class 2606 OID 2996635)
-- Dependencies: 287 2816 286 3093
-- Name: fkafea0d444de40194; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pharmacie_site
    ADD CONSTRAINT fkafea0d444de40194 FOREIGN KEY (id_pharmacie) REFERENCES pharmacie(id);


--
-- TOC entry 3044 (class 2606 OID 2996640)
-- Dependencies: 287 2904 335 3093
-- Name: fkafea0d447aad8e07; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pharmacie_site
    ADD CONSTRAINT fkafea0d447aad8e07 FOREIGN KEY (id_site) REFERENCES site(id);


--
-- TOC entry 3039 (class 2606 OID 2996611)
-- Dependencies: 2803 278 280 3093
-- Name: fkb01e8d80aedb3264; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY patient_suivi
    ADD CONSTRAINT fkb01e8d80aedb3264 FOREIGN KEY (id_patient) REFERENCES patient(id);


--
-- TOC entry 3051 (class 2606 OID 2996679)
-- Dependencies: 2850 297 305 3093
-- Name: fkb1982697ae5cce6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY preparation
    ADD CONSTRAINT fkb1982697ae5cce6 FOREIGN KEY (id) REFERENCES produit(id);


--
-- TOC entry 2997 (class 2606 OID 2996378)
-- Dependencies: 238 240 2731 3093
-- Name: fkb63afd47195ade5f; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY grille
    ADD CONSTRAINT fkb63afd47195ade5f FOREIGN KEY (id_grille_modele) REFERENCES grille_modele(id);


--
-- TOC entry 2996 (class 2606 OID 2996373)
-- Dependencies: 213 2692 238 3093
-- Name: fkb63afd47f5ae6985; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY grille
    ADD CONSTRAINT fkb63afd47f5ae6985 FOREIGN KEY (id_detail_surcout) REFERENCES essai_detail_surcout(id);


--
-- TOC entry 2975 (class 2606 OID 2996255)
-- Dependencies: 201 197 2671 3093
-- Name: fkb8bc0654d08532d; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_detail_pharma_suivi
    ADD CONSTRAINT fkb8bc0654d08532d FOREIGN KEY (id_detail_pharma) REFERENCES essai_detail_pharma(id);


--
-- TOC entry 3057 (class 2606 OID 2996716)
-- Dependencies: 2894 329 301 3093
-- Name: fkbe86243f807681fd; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prescription_type
    ADD CONSTRAINT fkbe86243f807681fd FOREIGN KEY (id_sequence) REFERENCES sequence(id);


--
-- TOC entry 3056 (class 2606 OID 2996711)
-- Dependencies: 301 147 2591 3093
-- Name: fkbe86243f9d7535de; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prescription_type
    ADD CONSTRAINT fkbe86243f9d7535de FOREIGN KEY (id_conditionnement) REFERENCES conditionnement(id);


--
-- TOC entry 3058 (class 2606 OID 2996721)
-- Dependencies: 301 305 2850 3093
-- Name: fkbe86243fa1ddf650; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prescription_type
    ADD CONSTRAINT fkbe86243fa1ddf650 FOREIGN KEY (id_produit) REFERENCES produit(id);


--
-- TOC entry 3069 (class 2606 OID 2996783)
-- Dependencies: 331 314 2898 3093
-- Name: fkc171821f1cf65f51; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY produit_service
    ADD CONSTRAINT fkc171821f1cf65f51 FOREIGN KEY (id_service) REFERENCES service(id);


--
-- TOC entry 3070 (class 2606 OID 2996788)
-- Dependencies: 305 314 2850 3093
-- Name: fkc171821fa1ddf650; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY produit_service
    ADD CONSTRAINT fkc171821fa1ddf650 FOREIGN KEY (id_produit) REFERENCES produit(id);


--
-- TOC entry 3071 (class 2606 OID 2996794)
-- Dependencies: 305 316 2850 3093
-- Name: fkc1e4e524a1ddf650; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY produit_suivi
    ADD CONSTRAINT fkc1e4e524a1ddf650 FOREIGN KEY (id_produit) REFERENCES produit(id);


--
-- TOC entry 3025 (class 2606 OID 2996534)
-- Dependencies: 2795 270 265 3093
-- Name: fkc343fea43e8f1c97; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY mvt_dispensation_globale
    ADD CONSTRAINT fkc343fea43e8f1c97 FOREIGN KEY (id) REFERENCES mvtstock(id);


--
-- TOC entry 3026 (class 2606 OID 2996539)
-- Dependencies: 2609 158 265 3093
-- Name: fkc343fea464b18985; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY mvt_dispensation_globale
    ADD CONSTRAINT fkc343fea464b18985 FOREIGN KEY (id_dotation) REFERENCES dotation(id);


--
-- TOC entry 3078 (class 2606 OID 2996839)
-- Dependencies: 327 162 2623 3093
-- Name: fkc38b7dd139fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY retour_patient
    ADD CONSTRAINT fkc38b7dd139fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 3082 (class 2606 OID 2996859)
-- Dependencies: 327 2810 282 3093
-- Name: fkc38b7dd18800c21; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY retour_patient
    ADD CONSTRAINT fkc38b7dd18800c21 FOREIGN KEY (id_personne) REFERENCES personne(id);


--
-- TOC entry 3080 (class 2606 OID 2996849)
-- Dependencies: 327 147 2591 3093
-- Name: fkc38b7dd19d7535de; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY retour_patient
    ADD CONSTRAINT fkc38b7dd19d7535de FOREIGN KEY (id_conditionnement) REFERENCES conditionnement(id);


--
-- TOC entry 3081 (class 2606 OID 2996854)
-- Dependencies: 2850 305 327 3093
-- Name: fkc38b7dd1a1ddf650; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY retour_patient
    ADD CONSTRAINT fkc38b7dd1a1ddf650 FOREIGN KEY (id_produit) REFERENCES produit(id);


--
-- TOC entry 3077 (class 2606 OID 2996834)
-- Dependencies: 2803 327 278 3093
-- Name: fkc38b7dd1aedb3264; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY retour_patient
    ADD CONSTRAINT fkc38b7dd1aedb3264 FOREIGN KEY (id_patient) REFERENCES patient(id);


--
-- TOC entry 3079 (class 2606 OID 2996844)
-- Dependencies: 309 327 2857 3093
-- Name: fkc38b7dd1d8bb7cd7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY retour_patient
    ADD CONSTRAINT fkc38b7dd1d8bb7cd7 FOREIGN KEY (id_detailstockage) REFERENCES produit_detail_stockage(id);


--
-- TOC entry 2992 (class 2606 OID 2996349)
-- Dependencies: 2716 230 232 3093
-- Name: fkcaf42771cd45a413; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY etablissement_suivi
    ADD CONSTRAINT fkcaf42771cd45a413 FOREIGN KEY (id_etablissement) REFERENCES etablissement(id);


--
-- TOC entry 2991 (class 2606 OID 2996343)
-- Dependencies: 2623 162 228 3093
-- Name: fkcd5e3ce839fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_suivi
    ADD CONSTRAINT fkcd5e3ce839fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 3066 (class 2606 OID 2996768)
-- Dependencies: 299 2840 313 3093
-- Name: fkce7075e087ff1713; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY produit_prescrit
    ADD CONSTRAINT fkce7075e087ff1713 FOREIGN KEY (id_prescription) REFERENCES prescription(id);


--
-- TOC entry 3067 (class 2606 OID 2996773)
-- Dependencies: 147 2591 313 3093
-- Name: fkce7075e09d7535de; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY produit_prescrit
    ADD CONSTRAINT fkce7075e09d7535de FOREIGN KEY (id_conditionnement) REFERENCES conditionnement(id);


--
-- TOC entry 3068 (class 2606 OID 2996778)
-- Dependencies: 2850 313 305 3093
-- Name: fkce7075e0a1ddf650; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY produit_prescrit
    ADD CONSTRAINT fkce7075e0a1ddf650 FOREIGN KEY (id_produit) REFERENCES produit(id);


--
-- TOC entry 3073 (class 2606 OID 2996805)
-- Dependencies: 149 321 2594 3093
-- Name: fkd04e1a4191ecd533; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY promoteur_sigrec
    ADD CONSTRAINT fkd04e1a4191ecd533 FOREIGN KEY (id_contact) REFERENCES contact_sigrec(id);


--
-- TOC entry 3036 (class 2606 OID 2996595)
-- Dependencies: 270 2795 272 3093
-- Name: fkd0e894cf2bee4c2b; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY mvtstock_document
    ADD CONSTRAINT fkd0e894cf2bee4c2b FOREIGN KEY (id_mvtstock) REFERENCES mvtstock(id);


--
-- TOC entry 3076 (class 2606 OID 2996823)
-- Dependencies: 325 341 2914 3093
-- Name: fkd387012940161942; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY regle_surcout
    ADD CONSTRAINT fkd387012940161942 FOREIGN KEY (id_theme) REFERENCES theme(id);


--
-- TOC entry 3075 (class 2606 OID 2996818)
-- Dependencies: 325 141 2582 3093
-- Name: fkd387012961ea981e; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY regle_surcout
    ADD CONSTRAINT fkd387012961ea981e FOREIGN KEY (id_categorie) REFERENCES categorie(id);


--
-- TOC entry 2988 (class 2606 OID 2996325)
-- Dependencies: 331 224 2898 3093
-- Name: fkd3f3f8e31cf65f51; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_service
    ADD CONSTRAINT fkd3f3f8e31cf65f51 FOREIGN KEY (id_service) REFERENCES service(id);


--
-- TOC entry 2987 (class 2606 OID 2996320)
-- Dependencies: 162 224 2623 3093
-- Name: fkd3f3f8e339fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_service
    ADD CONSTRAINT fkd3f3f8e339fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2964 (class 2606 OID 2996197)
-- Dependencies: 2656 188 186 3093
-- Name: fkd4e62fd5387f8764; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_detail_design_suivi
    ADD CONSTRAINT fkd4e62fd5387f8764 FOREIGN KEY (id_detail_design) REFERENCES essai_detail_design(id);


--
-- TOC entry 2923 (class 2606 OID 2995961)
-- Dependencies: 137 2594 149 3093
-- Name: fkdb9e6f7191ecd533; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY assurance_sigrec
    ADD CONSTRAINT fkdb9e6f7191ecd533 FOREIGN KEY (id_contact) REFERENCES contact_sigrec(id);


--
-- TOC entry 2924 (class 2606 OID 2995966)
-- Dependencies: 226 137 2709 3093
-- Name: fkdb9e6f71a81b195e; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY assurance_sigrec
    ADD CONSTRAINT fkdb9e6f71a81b195e FOREIGN KEY (id_essai) REFERENCES essai_sigrec(id);


--
-- TOC entry 2990 (class 2606 OID 2996337)
-- Dependencies: 2752 253 226 3093
-- Name: fkddbf4e314614c469; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_sigrec
    ADD CONSTRAINT fkddbf4e314614c469 FOREIGN KEY (id_investigateurprincipal) REFERENCES investigateur_sigrec(id);


--
-- TOC entry 2989 (class 2606 OID 2996332)
-- Dependencies: 226 321 2876 3093
-- Name: fkddbf4e31d4112aed; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_sigrec
    ADD CONSTRAINT fkddbf4e31d4112aed FOREIGN KEY (id_promoteur) REFERENCES promoteur_sigrec(id);


--
-- TOC entry 3021 (class 2606 OID 2996511)
-- Dependencies: 263 270 2795 3093
-- Name: fkdfdef25e3e8f1c97; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY mvt_destruction
    ADD CONSTRAINT fkdfdef25e3e8f1c97 FOREIGN KEY (id) REFERENCES mvtstock(id);


--
-- TOC entry 2916 (class 2606 OID 2995924)
-- Dependencies: 132 2585 143 3093
-- Name: fke2c002cf5c631481; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY arc_investigateur_sigrec
    ADD CONSTRAINT fke2c002cf5c631481 FOREIGN KEY (id_centre) REFERENCES centre_sigrec(id);


--
-- TOC entry 2917 (class 2606 OID 2995929)
-- Dependencies: 132 149 2594 3093
-- Name: fke2c002cf91ecd533; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY arc_investigateur_sigrec
    ADD CONSTRAINT fke2c002cf91ecd533 FOREIGN KEY (id_contact) REFERENCES contact_sigrec(id);


--
-- TOC entry 2918 (class 2606 OID 2995934)
-- Dependencies: 226 132 2709 3093
-- Name: fke2c002cfa81b195e; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY arc_investigateur_sigrec
    ADD CONSTRAINT fke2c002cfa81b195e FOREIGN KEY (id_essai) REFERENCES essai_sigrec(id);


--
-- TOC entry 3042 (class 2606 OID 2996630)
-- Dependencies: 230 2716 286 3093
-- Name: fke55d5022cd45a413; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pharmacie
    ADD CONSTRAINT fke55d5022cd45a413 FOREIGN KEY (id_etablissement) REFERENCES etablissement(id);


--
-- TOC entry 2959 (class 2606 OID 2996169)
-- Dependencies: 162 2623 178 3093
-- Name: fke7ea68af39fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_detail_contacts
    ADD CONSTRAINT fke7ea68af39fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 3060 (class 2606 OID 2996732)
-- Dependencies: 203 2680 305 3093
-- Name: fked8dcda9ba4507a4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY produit
    ADD CONSTRAINT fked8dcda9ba4507a4 FOREIGN KEY (id_detailproduit) REFERENCES essai_detail_produit(id);


--
-- TOC entry 3063 (class 2606 OID 2996750)
-- Dependencies: 309 286 2816 3093
-- Name: fkef34b7c14de40194; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY produit_detail_stockage
    ADD CONSTRAINT fkef34b7c14de40194 FOREIGN KEY (id_pharmacie) REFERENCES pharmacie(id);


--
-- TOC entry 3064 (class 2606 OID 2996755)
-- Dependencies: 309 2852 307 3093
-- Name: fkef34b7c1a24a8716; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY produit_detail_stockage
    ADD CONSTRAINT fkef34b7c1a24a8716 FOREIGN KEY (id_detail_logistique) REFERENCES produit_detail_logistique(id);


--
-- TOC entry 3062 (class 2606 OID 2996745)
-- Dependencies: 309 339 2911 3093
-- Name: fkef34b7c1d78f7902; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY produit_detail_stockage
    ADD CONSTRAINT fkef34b7c1d78f7902 FOREIGN KEY (id_stockage) REFERENCES stockage(id);


--
-- TOC entry 2947 (class 2606 OID 2996102)
-- Dependencies: 2600 153 160 3093
-- Name: fkf50b7c271a1781c6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY element_to_check
    ADD CONSTRAINT fkf50b7c271a1781c6 FOREIGN KEY (id_dispensation) REFERENCES dispensation(id);


--
-- TOC entry 2946 (class 2606 OID 2996097)
-- Dependencies: 313 160 2864 3093
-- Name: fkf50b7c276d6ee647; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY element_to_check
    ADD CONSTRAINT fkf50b7c276d6ee647 FOREIGN KEY (id_produitprescrit) REFERENCES produit_prescrit(id);


--
-- TOC entry 2949 (class 2606 OID 2996112)
-- Dependencies: 2810 282 160 3093
-- Name: fkf50b7c278800c21; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY element_to_check
    ADD CONSTRAINT fkf50b7c278800c21 FOREIGN KEY (id_personne) REFERENCES personne(id);


--
-- TOC entry 2948 (class 2606 OID 2996107)
-- Dependencies: 160 2801 276 3093
-- Name: fkf50b7c2797d4f410; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY element_to_check
    ADD CONSTRAINT fkf50b7c2797d4f410 FOREIGN KEY (id_ordonnancier) REFERENCES ordonnancier_fab_reconst(id);


--
-- TOC entry 2933 (class 2606 OID 2996019)
-- Dependencies: 2594 151 149 3093
-- Name: fkf5281d5e91ecd533; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cro_sigrec
    ADD CONSTRAINT fkf5281d5e91ecd533 FOREIGN KEY (id_contact) REFERENCES contact_sigrec(id);


--
-- TOC entry 2934 (class 2606 OID 2996024)
-- Dependencies: 151 226 2709 3093
-- Name: fkf5281d5ea81b195e; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cro_sigrec
    ADD CONSTRAINT fkf5281d5ea81b195e FOREIGN KEY (id_essai) REFERENCES essai_sigrec(id);


--
-- TOC entry 2976 (class 2606 OID 2996260)
-- Dependencies: 2623 203 162 3093
-- Name: fkf62049cd39fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_detail_produit
    ADD CONSTRAINT fkf62049cd39fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2922 (class 2606 OID 2995955)
-- Dependencies: 331 135 2898 3093
-- Name: fkfa113201cf65f51; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY arcinvestigateur_service
    ADD CONSTRAINT fkfa113201cf65f51 FOREIGN KEY (id_service) REFERENCES service(id);


--
-- TOC entry 2921 (class 2606 OID 2995950)
-- Dependencies: 282 2810 135 3093
-- Name: fkfa11320dde432bd; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY arcinvestigateur_service
    ADD CONSTRAINT fkfa11320dde432bd FOREIGN KEY (id_arcinvestigateur) REFERENCES personne(id);


--
-- TOC entry 3020 (class 2606 OID 2996506)
-- Dependencies: 262 2816 286 3093
-- Name: fkfd557b77382dd136; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY mvt_cession_pui
    ADD CONSTRAINT fkfd557b77382dd136 FOREIGN KEY (id_pharmaciedest) REFERENCES pharmacie(id);


--
-- TOC entry 3019 (class 2606 OID 2996501)
-- Dependencies: 2795 270 262 3093
-- Name: fkfd557b773e8f1c97; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY mvt_cession_pui
    ADD CONSTRAINT fkfd557b773e8f1c97 FOREIGN KEY (id) REFERENCES mvtstock(id);


--
-- TOC entry 3027 (class 2606 OID 2996544)
-- Dependencies: 2795 266 270 3093
-- Name: fkfe8aa4433e8f1c97; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY mvt_preparation
    ADD CONSTRAINT fkfe8aa4433e8f1c97 FOREIGN KEY (id) REFERENCES mvtstock(id);


--
-- TOC entry 2979 (class 2606 OID 2996276)
-- Dependencies: 2709 226 209 3093
-- Name: fkfff0213fa81b195e; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY essai_detail_recherche_sigrec
    ADD CONSTRAINT fkfff0213fa81b195e FOREIGN KEY (id_essai) REFERENCES essai_sigrec(id);


--
-- TOC entry 3098 (class 0 OID 0)
-- Dependencies: 3
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

-- Completed on 2013-07-23 12:26:29 CEST

--
-- PostgreSQL database dump complete
--
