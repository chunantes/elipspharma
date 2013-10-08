--
-- PostgreSQL database dump
--

-- Started on 2013-05-28 14:34:46

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

--
-- TOC entry 679 (class 2612 OID 16386)
-- Name: plpgsql; Type: PROCEDURAL LANGUAGE; Schema: -; Owner: postgres
--


ALTER PROCEDURAL LANGUAGE plpgsql OWNER TO postgres;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1851 (class 1259 OID 404724)
-- Dependencies: 2328 6
-- Name: arc_investigateur_sigrec; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE arc_investigateur_sigrec (
    id bigint NOT NULL,
    identifiant character varying(255),
    intervenantid integer,
    numadeli character varying(255),
    titre character varying(255),
    id_contact bigint,
    id_centre bigint,
    id_essai bigint,
    version bigint DEFAULT 0
);


ALTER TABLE public.arc_investigateur_sigrec OWNER TO eclipse;

--
-- TOC entry 1852 (class 1259 OID 404731)
-- Dependencies: 6 1851
-- Name: arc_investigateur_sigrec_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE arc_investigateur_sigrec_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.arc_investigateur_sigrec_id_seq OWNER TO eclipse;

--
-- TOC entry 3163 (class 0 OID 0)
-- Dependencies: 1852
-- Name: arc_investigateur_sigrec_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE arc_investigateur_sigrec_id_seq OWNED BY arc_investigateur_sigrec.id;


--
-- TOC entry 3164 (class 0 OID 0)
-- Dependencies: 1852
-- Name: arc_investigateur_sigrec_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('arc_investigateur_sigrec_id_seq', 1, false);


--
-- TOC entry 1853 (class 1259 OID 404733)
-- Dependencies: 2330 6
-- Name: arc_promoteur_sigrec; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE arc_promoteur_sigrec (
    id bigint NOT NULL,
    identifiant character varying(255),
    intervenantid integer,
    numadeli character varying(255),
    titre character varying(255),
    id_contact bigint,
    id_promoteur bigint,
    version bigint DEFAULT 0
);


ALTER TABLE public.arc_promoteur_sigrec OWNER TO eclipse;

--
-- TOC entry 1854 (class 1259 OID 404740)
-- Dependencies: 6 1853
-- Name: arc_promoteur_sigrec_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE arc_promoteur_sigrec_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.arc_promoteur_sigrec_id_seq OWNER TO eclipse;

--
-- TOC entry 3165 (class 0 OID 0)
-- Dependencies: 1854
-- Name: arc_promoteur_sigrec_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE arc_promoteur_sigrec_id_seq OWNED BY arc_promoteur_sigrec.id;


--
-- TOC entry 3166 (class 0 OID 0)
-- Dependencies: 1854
-- Name: arc_promoteur_sigrec_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('arc_promoteur_sigrec_id_seq', 1, false);


--
-- TOC entry 1855 (class 1259 OID 404742)
-- Dependencies: 6
-- Name: arcinvestigateur_service; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE arcinvestigateur_service (
    id_arcinvestigateur bigint NOT NULL,
    id_service bigint NOT NULL
);


ALTER TABLE public.arcinvestigateur_service OWNER TO eclipse;

--
-- TOC entry 1856 (class 1259 OID 404745)
-- Dependencies: 2332 6
-- Name: assurance_sigrec; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE assurance_sigrec (
    id bigint NOT NULL,
    datedebutvalidite timestamp without time zone,
    datefinvalidite timestamp without time zone,
    numerocontrat character varying(255),
    id_contact bigint,
    id_essai bigint,
    version bigint DEFAULT 0
);


ALTER TABLE public.assurance_sigrec OWNER TO eclipse;

--
-- TOC entry 1857 (class 1259 OID 404749)
-- Dependencies: 6 1856
-- Name: assurance_sigrec_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE assurance_sigrec_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.assurance_sigrec_id_seq OWNER TO eclipse;

--
-- TOC entry 3167 (class 0 OID 0)
-- Dependencies: 1857
-- Name: assurance_sigrec_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE assurance_sigrec_id_seq OWNED BY assurance_sigrec.id;


--
-- TOC entry 3168 (class 0 OID 0)
-- Dependencies: 1857
-- Name: assurance_sigrec_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('assurance_sigrec_id_seq', 1, false);


--
-- TOC entry 1858 (class 1259 OID 404751)
-- Dependencies: 2334 6
-- Name: bras; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE bras (
    id bigint NOT NULL,
    description text,
    nom character varying(255),
    type character varying(255),
    id_detail_design bigint NOT NULL,
    id_bras_parent bigint,
    version bigint DEFAULT 0
);


ALTER TABLE public.bras OWNER TO eclipse;

--
-- TOC entry 1859 (class 1259 OID 404758)
-- Dependencies: 1858 6
-- Name: bras_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE bras_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.bras_id_seq OWNER TO eclipse;

--
-- TOC entry 3169 (class 0 OID 0)
-- Dependencies: 1859
-- Name: bras_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE bras_id_seq OWNED BY bras.id;


--
-- TOC entry 3170 (class 0 OID 0)
-- Dependencies: 1859
-- Name: bras_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('bras_id_seq', 1, false);


--
-- TOC entry 1860 (class 1259 OID 404760)
-- Dependencies: 2336 6
-- Name: categorie; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE categorie (
    id bigint NOT NULL,
    libelle character varying(255),
    id_theme bigint NOT NULL,
    acte character varying(255),
    version bigint DEFAULT 0
);


ALTER TABLE public.categorie OWNER TO eclipse;

--
-- TOC entry 1861 (class 1259 OID 404767)
-- Dependencies: 6 1860
-- Name: categorie_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE categorie_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.categorie_id_seq OWNER TO eclipse;

--
-- TOC entry 3171 (class 0 OID 0)
-- Dependencies: 1861
-- Name: categorie_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE categorie_id_seq OWNED BY categorie.id;


--
-- TOC entry 3172 (class 0 OID 0)
-- Dependencies: 1861
-- Name: categorie_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('categorie_id_seq', 1, false);


--
-- TOC entry 1862 (class 1259 OID 404769)
-- Dependencies: 2338 6
-- Name: centre_sigrec; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE centre_sigrec (
    id bigint NOT NULL,
    idcentre character varying(255),
    nom character varying(255),
    numero character varying(255),
    numerofiness character varying(255),
    id_contact bigint,
    version bigint DEFAULT 0
);


ALTER TABLE public.centre_sigrec OWNER TO eclipse;

--
-- TOC entry 1863 (class 1259 OID 404776)
-- Dependencies: 6 1862
-- Name: centre_sigrec_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE centre_sigrec_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.centre_sigrec_id_seq OWNER TO eclipse;

--
-- TOC entry 3173 (class 0 OID 0)
-- Dependencies: 1863
-- Name: centre_sigrec_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE centre_sigrec_id_seq OWNED BY centre_sigrec.id;


--
-- TOC entry 3174 (class 0 OID 0)
-- Dependencies: 1863
-- Name: centre_sigrec_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('centre_sigrec_id_seq', 1, false);


--
-- TOC entry 1864 (class 1259 OID 404778)
-- Dependencies: 2340 6
-- Name: co_investigateur_sigrec; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE co_investigateur_sigrec (
    id bigint NOT NULL,
    identifiant character varying(255),
    intervenantid integer,
    numadeli character varying(255),
    titre character varying(255),
    id_contact bigint,
    id_centre bigint,
    id_essai bigint,
    version bigint DEFAULT 0
);


ALTER TABLE public.co_investigateur_sigrec OWNER TO eclipse;

--
-- TOC entry 1865 (class 1259 OID 404785)
-- Dependencies: 6 1864
-- Name: co_investigateur_sigrec_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE co_investigateur_sigrec_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.co_investigateur_sigrec_id_seq OWNER TO eclipse;

--
-- TOC entry 3175 (class 0 OID 0)
-- Dependencies: 1865
-- Name: co_investigateur_sigrec_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE co_investigateur_sigrec_id_seq OWNED BY co_investigateur_sigrec.id;


--
-- TOC entry 3176 (class 0 OID 0)
-- Dependencies: 1865
-- Name: co_investigateur_sigrec_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('co_investigateur_sigrec_id_seq', 100000, false);


--
-- TOC entry 1866 (class 1259 OID 404787)
-- Dependencies: 2342 6
-- Name: conditionnement; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE conditionnement (
    id bigint NOT NULL,
    dosage numeric(19,2),
    libelle character varying(255) NOT NULL,
    modeprescription character varying(255),
    nbuniteprescription numeric(19,2),
    quantiteparpatient integer,
    unitedosage character varying(255),
    unitegestion character varying(255),
    uniteprescription character varying(255),
    voieadministration character varying(255),
    id_produit bigint,
    forme character varying(255),
    contenance numeric(19,2),
    unitecontenance character varying(255),
    version bigint DEFAULT 0
);


ALTER TABLE public.conditionnement OWNER TO eclipse;

--
-- TOC entry 1867 (class 1259 OID 404794)
-- Dependencies: 6 1866
-- Name: conditionnement_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE conditionnement_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.conditionnement_id_seq OWNER TO eclipse;

--
-- TOC entry 3177 (class 0 OID 0)
-- Dependencies: 1867
-- Name: conditionnement_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE conditionnement_id_seq OWNED BY conditionnement.id;


--
-- TOC entry 3178 (class 0 OID 0)
-- Dependencies: 1867
-- Name: conditionnement_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('conditionnement_id_seq', 1, false);


--
-- TOC entry 1868 (class 1259 OID 404796)
-- Dependencies: 2344 6
-- Name: contact_sigrec; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE contact_sigrec (
    id bigint NOT NULL,
    adresse character varying(255),
    civilite character varying(255),
    codepostal character varying(255),
    email character varying(255),
    fax character varying(255),
    nom character varying(255),
    prenom character varying(255),
    raisonsociale character varying(255),
    telephone character varying(255),
    ville character varying(255),
    version bigint DEFAULT 0
);


ALTER TABLE public.contact_sigrec OWNER TO eclipse;

--
-- TOC entry 1869 (class 1259 OID 404803)
-- Dependencies: 1868 6
-- Name: contact_sigrec_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE contact_sigrec_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.contact_sigrec_id_seq OWNER TO eclipse;

--
-- TOC entry 3179 (class 0 OID 0)
-- Dependencies: 1869
-- Name: contact_sigrec_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE contact_sigrec_id_seq OWNED BY contact_sigrec.id;


--
-- TOC entry 3180 (class 0 OID 0)
-- Dependencies: 1869
-- Name: contact_sigrec_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('contact_sigrec_id_seq', 1, false);


--
-- TOC entry 1870 (class 1259 OID 404805)
-- Dependencies: 2346 6
-- Name: cro_sigrec; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE cro_sigrec (
    id bigint NOT NULL,
    identifiant character varying(255),
    id_contact bigint,
    id_essai bigint,
    version bigint DEFAULT 0
);


ALTER TABLE public.cro_sigrec OWNER TO eclipse;

--
-- TOC entry 1871 (class 1259 OID 404809)
-- Dependencies: 6 1870
-- Name: cro_sigrec_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE cro_sigrec_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.cro_sigrec_id_seq OWNER TO eclipse;

--
-- TOC entry 3181 (class 0 OID 0)
-- Dependencies: 1871
-- Name: cro_sigrec_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE cro_sigrec_id_seq OWNED BY cro_sigrec.id;


--
-- TOC entry 3182 (class 0 OID 0)
-- Dependencies: 1871
-- Name: cro_sigrec_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('cro_sigrec_id_seq', 1, false);


--
-- TOC entry 1872 (class 1259 OID 404811)
-- Dependencies: 2348 6
-- Name: dispensation; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE dispensation (
    id bigint NOT NULL,
    datedispensation timestamp without time zone,
    dispense boolean,
    id_prescription bigint NOT NULL,
    numordonnancier integer,
    id_ordonnancier bigint,
    id_pharmacie bigint NOT NULL,
    commentaire text,
    version bigint DEFAULT 0
);


ALTER TABLE public.dispensation OWNER TO eclipse;

--
-- TOC entry 1873 (class 1259 OID 404818)
-- Dependencies: 1872 6
-- Name: dispensation_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE dispensation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.dispensation_id_seq OWNER TO eclipse;

--
-- TOC entry 3183 (class 0 OID 0)
-- Dependencies: 1873
-- Name: dispensation_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE dispensation_id_seq OWNED BY dispensation.id;


--
-- TOC entry 3184 (class 0 OID 0)
-- Dependencies: 1873
-- Name: dispensation_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('dispensation_id_seq', 1, false);


--
-- TOC entry 1874 (class 1259 OID 404820)
-- Dependencies: 6
-- Name: dispositif_medical; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
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


ALTER TABLE public.dispositif_medical OWNER TO eclipse;

--
-- TOC entry 1875 (class 1259 OID 404826)
-- Dependencies: 2350 6
-- Name: donnees_prevision; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE donnees_prevision (
    id bigint NOT NULL,
    nbannees integer,
    nbdestructions integer,
    nbdispensations integer,
    nbinclusions integer,
    nbprescriptions integer,
    nbreetiquetages integer,
    id_detail_surcout bigint,
    nbvisitemonitoring integer,
    nbdispensationsrenouvellement integer,
    nbaudits integer,
    nbapprovisionnements integer,
    nbpreparationssteriles integer,
    nbpreparationsnonsteriles integer,
    version bigint DEFAULT 0
);


ALTER TABLE public.donnees_prevision OWNER TO eclipse;

--
-- TOC entry 1876 (class 1259 OID 404830)
-- Dependencies: 1875 6
-- Name: donnees_prevision_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE donnees_prevision_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.donnees_prevision_id_seq OWNER TO eclipse;

--
-- TOC entry 3185 (class 0 OID 0)
-- Dependencies: 1876
-- Name: donnees_prevision_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE donnees_prevision_id_seq OWNED BY donnees_prevision.id;


--
-- TOC entry 3186 (class 0 OID 0)
-- Dependencies: 1876
-- Name: donnees_prevision_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('donnees_prevision_id_seq', 1, false);


--
-- TOC entry 1877 (class 1259 OID 404832)
-- Dependencies: 2352 6
-- Name: dotation; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE dotation (
    id bigint NOT NULL,
    commentaire text,
    datedemande timestamp without time zone NOT NULL,
    quantite integer NOT NULL,
    traitee boolean NOT NULL,
    id_conditionnement bigint NOT NULL,
    id_essai bigint NOT NULL,
    id_personne bigint NOT NULL,
    id_pharmacie bigint NOT NULL,
    id_produit bigint NOT NULL,
    id_service bigint NOT NULL,
    version bigint DEFAULT 0
);


ALTER TABLE public.dotation OWNER TO eclipse;

--
-- TOC entry 1878 (class 1259 OID 404839)
-- Dependencies: 6 1877
-- Name: dotation_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE dotation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.dotation_id_seq OWNER TO eclipse;

--
-- TOC entry 3187 (class 0 OID 0)
-- Dependencies: 1878
-- Name: dotation_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE dotation_id_seq OWNED BY dotation.id;


--
-- TOC entry 3188 (class 0 OID 0)
-- Dependencies: 1878
-- Name: dotation_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('dotation_id_seq', 1, false);


--
-- TOC entry 1879 (class 1259 OID 404841)
-- Dependencies: 2354 6
-- Name: element_to_check; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE element_to_check (
    id bigint NOT NULL,
    checked boolean,
    commentaire text,
    nomchamps character varying(255),
    type character varying(255),
    id_dispensation bigint NOT NULL,
    id_produitprescrit bigint NOT NULL,
    numordonnancier integer,
    id_ordonnancier bigint,
    datechecked timestamp without time zone,
    id_personne bigint,
    version bigint DEFAULT 0
);


ALTER TABLE public.element_to_check OWNER TO eclipse;

--
-- TOC entry 1880 (class 1259 OID 404848)
-- Dependencies: 6 1879
-- Name: element_to_check_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE element_to_check_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.element_to_check_id_seq OWNER TO eclipse;

--
-- TOC entry 3189 (class 0 OID 0)
-- Dependencies: 1880
-- Name: element_to_check_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE element_to_check_id_seq OWNED BY element_to_check.id;


--
-- TOC entry 3190 (class 0 OID 0)
-- Dependencies: 1880
-- Name: element_to_check_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('element_to_check_id_seq', 1, false);


--
-- TOC entry 1881 (class 1259 OID 404850)
-- Dependencies: 2356 6
-- Name: essai; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai (
    id bigint NOT NULL,
    alerteactive boolean NOT NULL,
    anneecreation integer,
    codepromoteur character varying(255) NOT NULL,
    dci character varying(255),
    emplacementdossier character varying(255),
    etat character varying(255) NOT NULL,
    libelleproduitevalue character varying(255),
    nom character varying(255) NOT NULL,
    numinterne character varying(255) NOT NULL,
    typepromoteur character varying(255) NOT NULL,
    id_pharma bigint NOT NULL,
    id_promoteur bigint NOT NULL,
    conv_date timestamp without time zone,
    version bigint DEFAULT 0
);


ALTER TABLE public.essai OWNER TO eclipse;

--
-- TOC entry 1882 (class 1259 OID 404857)
-- Dependencies: 2358 6
-- Name: essai_commentaire_detail_administratif_archi; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_commentaire_detail_administratif_archi (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    libelle text NOT NULL,
    id_detailadministratif bigint NOT NULL,
    version bigint DEFAULT 0
);


ALTER TABLE public.essai_commentaire_detail_administratif_archi OWNER TO eclipse;

--
-- TOC entry 1883 (class 1259 OID 404864)
-- Dependencies: 6 1882
-- Name: essai_commentaire_detail_administratif_archi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_commentaire_detail_administratif_archi_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_commentaire_detail_administratif_archi_id_seq OWNER TO eclipse;

--
-- TOC entry 3191 (class 0 OID 0)
-- Dependencies: 1883
-- Name: essai_commentaire_detail_administratif_archi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_commentaire_detail_administratif_archi_id_seq OWNED BY essai_commentaire_detail_administratif_archi.id;


--
-- TOC entry 3192 (class 0 OID 0)
-- Dependencies: 1883
-- Name: essai_commentaire_detail_administratif_archi_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('essai_commentaire_detail_administratif_archi_id_seq', 100000, false);


--
-- TOC entry 1884 (class 1259 OID 404866)
-- Dependencies: 2360 6
-- Name: essai_commentaire_detail_faisabilite; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_commentaire_detail_faisabilite (
    type character varying(31) NOT NULL,
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    libelle text NOT NULL,
    id_detailfaisabilite bigint NOT NULL,
    version bigint DEFAULT 0
);


ALTER TABLE public.essai_commentaire_detail_faisabilite OWNER TO eclipse;

--
-- TOC entry 1885 (class 1259 OID 404873)
-- Dependencies: 1884 6
-- Name: essai_commentaire_detail_faisabilite_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_commentaire_detail_faisabilite_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_commentaire_detail_faisabilite_id_seq OWNER TO eclipse;

--
-- TOC entry 3193 (class 0 OID 0)
-- Dependencies: 1885
-- Name: essai_commentaire_detail_faisabilite_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_commentaire_detail_faisabilite_id_seq OWNED BY essai_commentaire_detail_faisabilite.id;


--
-- TOC entry 3194 (class 0 OID 0)
-- Dependencies: 1885
-- Name: essai_commentaire_detail_faisabilite_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('essai_commentaire_detail_faisabilite_id_seq', 1, false);


--
-- TOC entry 1886 (class 1259 OID 404875)
-- Dependencies: 2362 6
-- Name: essai_commentaire_detail_recherche; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_commentaire_detail_recherche (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    libelle text NOT NULL,
    id_detailrecherche bigint NOT NULL,
    version bigint DEFAULT 0
);


ALTER TABLE public.essai_commentaire_detail_recherche OWNER TO eclipse;

--
-- TOC entry 1887 (class 1259 OID 404882)
-- Dependencies: 6 1886
-- Name: essai_commentaire_detail_recherche_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_commentaire_detail_recherche_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_commentaire_detail_recherche_id_seq OWNER TO eclipse;

--
-- TOC entry 3195 (class 0 OID 0)
-- Dependencies: 1887
-- Name: essai_commentaire_detail_recherche_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_commentaire_detail_recherche_id_seq OWNED BY essai_commentaire_detail_recherche.id;


--
-- TOC entry 3196 (class 0 OID 0)
-- Dependencies: 1887
-- Name: essai_commentaire_detail_recherche_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('essai_commentaire_detail_recherche_id_seq', 1, false);


--
-- TOC entry 1888 (class 1259 OID 404884)
-- Dependencies: 2364 6
-- Name: essai_detail_administratif; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_detail_administratif (
    id bigint NOT NULL,
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
    bropro_docsdosspapier boolean,
    cpp_accord boolean,
    cpp_date timestamp without time zone,
    cpp_docsdosspapier boolean,
    cpp_nom character varying(255),
    conv_signee boolean,
    conv_docsdosspapier boolean,
    proto_docsdosspapier boolean,
    id_essai bigint,
    autorisationdistribution_docsdosspapier boolean,
    autorisationimportation_docsdosspapier boolean,
    version bigint DEFAULT 0
);


ALTER TABLE public.essai_detail_administratif OWNER TO eclipse;

--
-- TOC entry 1889 (class 1259 OID 404891)
-- Dependencies: 1888 6
-- Name: essai_detail_administratif_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_administratif_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_administratif_id_seq OWNER TO eclipse;

--
-- TOC entry 3197 (class 0 OID 0)
-- Dependencies: 1889
-- Name: essai_detail_administratif_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_administratif_id_seq OWNED BY essai_detail_administratif.id;


--
-- TOC entry 3198 (class 0 OID 0)
-- Dependencies: 1889
-- Name: essai_detail_administratif_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('essai_detail_administratif_id_seq', 1, false);


--
-- TOC entry 1890 (class 1259 OID 404893)
-- Dependencies: 2366 6
-- Name: essai_detail_administratif_suivi; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_detail_administratif_suivi (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_detail_administratif bigint NOT NULL,
    version bigint DEFAULT 0
);


ALTER TABLE public.essai_detail_administratif_suivi OWNER TO eclipse;

--
-- TOC entry 1891 (class 1259 OID 404897)
-- Dependencies: 1890 6
-- Name: essai_detail_administratif_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_administratif_suivi_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_administratif_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 3199 (class 0 OID 0)
-- Dependencies: 1891
-- Name: essai_detail_administratif_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_administratif_suivi_id_seq OWNED BY essai_detail_administratif_suivi.id;


--
-- TOC entry 3200 (class 0 OID 0)
-- Dependencies: 1891
-- Name: essai_detail_administratif_suivi_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('essai_detail_administratif_suivi_id_seq', 1, false);


--
-- TOC entry 1892 (class 1259 OID 404899)
-- Dependencies: 2368 6
-- Name: essai_detail_autres_documents; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_detail_autres_documents (
    id bigint NOT NULL,
    id_essai bigint,
    version bigint DEFAULT 0
);


ALTER TABLE public.essai_detail_autres_documents OWNER TO eclipse;

--
-- TOC entry 1893 (class 1259 OID 404903)
-- Dependencies: 6 1892
-- Name: essai_detail_autres_documents_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_autres_documents_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_autres_documents_id_seq OWNER TO eclipse;

--
-- TOC entry 3201 (class 0 OID 0)
-- Dependencies: 1893
-- Name: essai_detail_autres_documents_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_autres_documents_id_seq OWNED BY essai_detail_autres_documents.id;


--
-- TOC entry 3202 (class 0 OID 0)
-- Dependencies: 1893
-- Name: essai_detail_autres_documents_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('essai_detail_autres_documents_id_seq', 1, false);


--
-- TOC entry 1894 (class 1259 OID 404905)
-- Dependencies: 2370 6
-- Name: essai_detail_autres_documents_suivi; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_detail_autres_documents_suivi (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_detail_autres_documents bigint NOT NULL,
    version bigint DEFAULT 0
);


ALTER TABLE public.essai_detail_autres_documents_suivi OWNER TO eclipse;

--
-- TOC entry 1895 (class 1259 OID 404909)
-- Dependencies: 6 1894
-- Name: essai_detail_autres_documents_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_autres_documents_suivi_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_autres_documents_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 3203 (class 0 OID 0)
-- Dependencies: 1895
-- Name: essai_detail_autres_documents_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_autres_documents_suivi_id_seq OWNED BY essai_detail_autres_documents_suivi.id;


--
-- TOC entry 3204 (class 0 OID 0)
-- Dependencies: 1895
-- Name: essai_detail_autres_documents_suivi_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('essai_detail_autres_documents_suivi_id_seq', 1, false);


--
-- TOC entry 1896 (class 1259 OID 404911)
-- Dependencies: 2372 6
-- Name: essai_detail_contacts; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_detail_contacts (
    id bigint NOT NULL,
    id_essai bigint,
    version bigint DEFAULT 0
);


ALTER TABLE public.essai_detail_contacts OWNER TO eclipse;

--
-- TOC entry 1897 (class 1259 OID 404915)
-- Dependencies: 1896 6
-- Name: essai_detail_contacts_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_contacts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_contacts_id_seq OWNER TO eclipse;

--
-- TOC entry 3205 (class 0 OID 0)
-- Dependencies: 1897
-- Name: essai_detail_contacts_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_contacts_id_seq OWNED BY essai_detail_contacts.id;


--
-- TOC entry 3206 (class 0 OID 0)
-- Dependencies: 1897
-- Name: essai_detail_contacts_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('essai_detail_contacts_id_seq', 1, false);


--
-- TOC entry 1898 (class 1259 OID 404917)
-- Dependencies: 2374 6
-- Name: essai_detail_contacts_suivi; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_detail_contacts_suivi (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_detail_contacts bigint NOT NULL,
    version bigint DEFAULT 0
);


ALTER TABLE public.essai_detail_contacts_suivi OWNER TO eclipse;

--
-- TOC entry 1899 (class 1259 OID 404921)
-- Dependencies: 1898 6
-- Name: essai_detail_contacts_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_contacts_suivi_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_contacts_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 3207 (class 0 OID 0)
-- Dependencies: 1899
-- Name: essai_detail_contacts_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_contacts_suivi_id_seq OWNED BY essai_detail_contacts_suivi.id;


--
-- TOC entry 3208 (class 0 OID 0)
-- Dependencies: 1899
-- Name: essai_detail_contacts_suivi_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('essai_detail_contacts_suivi_id_seq', 1, false);


--
-- TOC entry 1900 (class 1259 OID 404923)
-- Dependencies: 2376 6
-- Name: essai_detail_dates; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_detail_dates (
    id bigint NOT NULL,
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
    id_essai bigint,
    datereception timestamp without time zone,
    version bigint DEFAULT 0
);


ALTER TABLE public.essai_detail_dates OWNER TO eclipse;

--
-- TOC entry 1901 (class 1259 OID 404927)
-- Dependencies: 1900 6
-- Name: essai_detail_dates_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_dates_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_dates_id_seq OWNER TO eclipse;

--
-- TOC entry 3209 (class 0 OID 0)
-- Dependencies: 1901
-- Name: essai_detail_dates_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_dates_id_seq OWNED BY essai_detail_dates.id;


--
-- TOC entry 3210 (class 0 OID 0)
-- Dependencies: 1901
-- Name: essai_detail_dates_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('essai_detail_dates_id_seq', 1, false);


--
-- TOC entry 1902 (class 1259 OID 404929)
-- Dependencies: 2378 6
-- Name: essai_detail_dates_suivi; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_detail_dates_suivi (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_detail_dates bigint NOT NULL,
    version bigint DEFAULT 0
);


ALTER TABLE public.essai_detail_dates_suivi OWNER TO eclipse;

--
-- TOC entry 1903 (class 1259 OID 404933)
-- Dependencies: 6 1902
-- Name: essai_detail_dates_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_dates_suivi_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_dates_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 3211 (class 0 OID 0)
-- Dependencies: 1903
-- Name: essai_detail_dates_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_dates_suivi_id_seq OWNED BY essai_detail_dates_suivi.id;


--
-- TOC entry 3212 (class 0 OID 0)
-- Dependencies: 1903
-- Name: essai_detail_dates_suivi_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('essai_detail_dates_suivi_id_seq', 1, false);


--
-- TOC entry 1904 (class 1259 OID 404935)
-- Dependencies: 2380 6
-- Name: essai_detail_design; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_detail_design (
    id bigint NOT NULL,
    id_essai bigint,
    typedesign character varying(255),
    version bigint DEFAULT 0
);


ALTER TABLE public.essai_detail_design OWNER TO eclipse;

--
-- TOC entry 1905 (class 1259 OID 404939)
-- Dependencies: 1904 6
-- Name: essai_detail_design_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_design_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_design_id_seq OWNER TO eclipse;

--
-- TOC entry 3213 (class 0 OID 0)
-- Dependencies: 1905
-- Name: essai_detail_design_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_design_id_seq OWNED BY essai_detail_design.id;


--
-- TOC entry 3214 (class 0 OID 0)
-- Dependencies: 1905
-- Name: essai_detail_design_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('essai_detail_design_id_seq', 1, false);


--
-- TOC entry 1906 (class 1259 OID 404941)
-- Dependencies: 2382 6
-- Name: essai_detail_design_suivi; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_detail_design_suivi (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_detail_design bigint NOT NULL,
    version bigint DEFAULT 0
);


ALTER TABLE public.essai_detail_design_suivi OWNER TO eclipse;

--
-- TOC entry 1907 (class 1259 OID 404945)
-- Dependencies: 6 1906
-- Name: essai_detail_design_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_design_suivi_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_design_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 3215 (class 0 OID 0)
-- Dependencies: 1907
-- Name: essai_detail_design_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_design_suivi_id_seq OWNED BY essai_detail_design_suivi.id;


--
-- TOC entry 3216 (class 0 OID 0)
-- Dependencies: 1907
-- Name: essai_detail_design_suivi_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('essai_detail_design_suivi_id_seq', 1, false);


--
-- TOC entry 1908 (class 1259 OID 404947)
-- Dependencies: 2384 6
-- Name: essai_detail_etat; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_detail_etat (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    commentaire text,
    etat character varying(255) NOT NULL,
    id_essai bigint NOT NULL,
    version bigint DEFAULT 0
);


ALTER TABLE public.essai_detail_etat OWNER TO eclipse;

--
-- TOC entry 1909 (class 1259 OID 404954)
-- Dependencies: 1908 6
-- Name: essai_detail_etat_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_etat_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_etat_id_seq OWNER TO eclipse;

--
-- TOC entry 3217 (class 0 OID 0)
-- Dependencies: 1909
-- Name: essai_detail_etat_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_etat_id_seq OWNED BY essai_detail_etat.id;


--
-- TOC entry 3218 (class 0 OID 0)
-- Dependencies: 1909
-- Name: essai_detail_etat_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('essai_detail_etat_id_seq', 1, false);


--
-- TOC entry 1910 (class 1259 OID 404956)
-- Dependencies: 2386 6
-- Name: essai_detail_faisabilite; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_detail_faisabilite (
    id bigint NOT NULL,
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
    id_essai bigint,
    version bigint DEFAULT 0
);


ALTER TABLE public.essai_detail_faisabilite OWNER TO eclipse;

--
-- TOC entry 1911 (class 1259 OID 404960)
-- Dependencies: 1910 6
-- Name: essai_detail_faisabilite_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_faisabilite_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_faisabilite_id_seq OWNER TO eclipse;

--
-- TOC entry 3219 (class 0 OID 0)
-- Dependencies: 1911
-- Name: essai_detail_faisabilite_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_faisabilite_id_seq OWNED BY essai_detail_faisabilite.id;


--
-- TOC entry 3220 (class 0 OID 0)
-- Dependencies: 1911
-- Name: essai_detail_faisabilite_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('essai_detail_faisabilite_id_seq', 1, false);


--
-- TOC entry 1912 (class 1259 OID 404962)
-- Dependencies: 6
-- Name: essai_detail_faisabilite_service; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_detail_faisabilite_service (
    id_essai bigint NOT NULL,
    id_service bigint NOT NULL
);


ALTER TABLE public.essai_detail_faisabilite_service OWNER TO eclipse;

--
-- TOC entry 1913 (class 1259 OID 404965)
-- Dependencies: 2388 6
-- Name: essai_detail_faisabilite_suivi; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_detail_faisabilite_suivi (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_detail_faisabilite bigint NOT NULL,
    version bigint DEFAULT 0
);


ALTER TABLE public.essai_detail_faisabilite_suivi OWNER TO eclipse;

--
-- TOC entry 1914 (class 1259 OID 404969)
-- Dependencies: 1913 6
-- Name: essai_detail_faisabilite_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_faisabilite_suivi_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_faisabilite_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 3221 (class 0 OID 0)
-- Dependencies: 1914
-- Name: essai_detail_faisabilite_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_faisabilite_suivi_id_seq OWNED BY essai_detail_faisabilite_suivi.id;


--
-- TOC entry 3222 (class 0 OID 0)
-- Dependencies: 1914
-- Name: essai_detail_faisabilite_suivi_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('essai_detail_faisabilite_suivi_id_seq', 1, false);


--
-- TOC entry 1915 (class 1259 OID 404971)
-- Dependencies: 2390 6
-- Name: essai_detail_pharma; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_detail_pharma (
    id bigint NOT NULL,
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
    qualiteinsu integer,
    typeproduitevalue character varying(255),
    id_essai bigint,
    unitedureetotaleprevue character varying(255),
    unitedureetotalepatientprevue character varying(255),
    nbpatientsprevustotal integer,
    numerocentre character varying(255),
    version bigint DEFAULT 0
);


ALTER TABLE public.essai_detail_pharma OWNER TO eclipse;

--
-- TOC entry 1916 (class 1259 OID 404978)
-- Dependencies: 6
-- Name: essai_detail_pharma_etablissement; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_detail_pharma_etablissement (
    id_detail_pharma bigint NOT NULL,
    id_etablissement bigint NOT NULL
);


ALTER TABLE public.essai_detail_pharma_etablissement OWNER TO eclipse;

--
-- TOC entry 1917 (class 1259 OID 404981)
-- Dependencies: 6 1915
-- Name: essai_detail_pharma_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_pharma_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_pharma_id_seq OWNER TO eclipse;

--
-- TOC entry 3223 (class 0 OID 0)
-- Dependencies: 1917
-- Name: essai_detail_pharma_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_pharma_id_seq OWNED BY essai_detail_pharma.id;


--
-- TOC entry 3224 (class 0 OID 0)
-- Dependencies: 1917
-- Name: essai_detail_pharma_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('essai_detail_pharma_id_seq', 1, false);


--
-- TOC entry 1918 (class 1259 OID 404983)
-- Dependencies: 6
-- Name: essai_detail_pharma_pharmacie; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_detail_pharma_pharmacie (
    id_detail_pharma bigint NOT NULL,
    id_pharmacie bigint NOT NULL
);


ALTER TABLE public.essai_detail_pharma_pharmacie OWNER TO eclipse;

--
-- TOC entry 1919 (class 1259 OID 404986)
-- Dependencies: 2392 6
-- Name: essai_detail_pharma_suivi; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_detail_pharma_suivi (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_detail_pharma bigint NOT NULL,
    version bigint DEFAULT 0
);


ALTER TABLE public.essai_detail_pharma_suivi OWNER TO eclipse;

--
-- TOC entry 1920 (class 1259 OID 404990)
-- Dependencies: 6 1919
-- Name: essai_detail_pharma_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_pharma_suivi_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_pharma_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 3225 (class 0 OID 0)
-- Dependencies: 1920
-- Name: essai_detail_pharma_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_pharma_suivi_id_seq OWNED BY essai_detail_pharma_suivi.id;


--
-- TOC entry 3226 (class 0 OID 0)
-- Dependencies: 1920
-- Name: essai_detail_pharma_suivi_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('essai_detail_pharma_suivi_id_seq', 1, false);


--
-- TOC entry 1921 (class 1259 OID 404992)
-- Dependencies: 2394 6
-- Name: essai_detail_produit; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_detail_produit (
    id bigint NOT NULL,
    id_essai bigint,
    version bigint DEFAULT 0
);


ALTER TABLE public.essai_detail_produit OWNER TO eclipse;

--
-- TOC entry 1922 (class 1259 OID 404996)
-- Dependencies: 1921 6
-- Name: essai_detail_produit_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_produit_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_produit_id_seq OWNER TO eclipse;

--
-- TOC entry 3227 (class 0 OID 0)
-- Dependencies: 1922
-- Name: essai_detail_produit_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_produit_id_seq OWNED BY essai_detail_produit.id;


--
-- TOC entry 3228 (class 0 OID 0)
-- Dependencies: 1922
-- Name: essai_detail_produit_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('essai_detail_produit_id_seq', 1, false);


--
-- TOC entry 1923 (class 1259 OID 404998)
-- Dependencies: 2396 6
-- Name: essai_detail_produit_suivi; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_detail_produit_suivi (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_detail_produit bigint NOT NULL,
    version bigint DEFAULT 0
);


ALTER TABLE public.essai_detail_produit_suivi OWNER TO eclipse;

--
-- TOC entry 1924 (class 1259 OID 405002)
-- Dependencies: 6 1923
-- Name: essai_detail_produit_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_produit_suivi_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_produit_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 3229 (class 0 OID 0)
-- Dependencies: 1924
-- Name: essai_detail_produit_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_produit_suivi_id_seq OWNED BY essai_detail_produit_suivi.id;


--
-- TOC entry 3230 (class 0 OID 0)
-- Dependencies: 1924
-- Name: essai_detail_produit_suivi_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('essai_detail_produit_suivi_id_seq', 1, false);


--
-- TOC entry 1925 (class 1259 OID 405004)
-- Dependencies: 2398 6
-- Name: essai_detail_recherche; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_detail_recherche (
    id bigint NOT NULL,
    motscles character varying(255),
    naturerecherche character varying(255),
    numenregistrement character varying(255),
    objetrecherche character varying(255),
    phaserecherche character varying(255),
    thematique character varying(255),
    titreprotocole text,
    typerecherche character varying(255),
    id_essai bigint,
    version bigint DEFAULT 0
);


ALTER TABLE public.essai_detail_recherche OWNER TO eclipse;

--
-- TOC entry 1926 (class 1259 OID 405011)
-- Dependencies: 6 1925
-- Name: essai_detail_recherche_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_recherche_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_recherche_id_seq OWNER TO eclipse;

--
-- TOC entry 3231 (class 0 OID 0)
-- Dependencies: 1926
-- Name: essai_detail_recherche_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_recherche_id_seq OWNED BY essai_detail_recherche.id;


--
-- TOC entry 3232 (class 0 OID 0)
-- Dependencies: 1926
-- Name: essai_detail_recherche_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('essai_detail_recherche_id_seq', 1, false);


--
-- TOC entry 1927 (class 1259 OID 405013)
-- Dependencies: 2400 6
-- Name: essai_detail_recherche_sigrec; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_detail_recherche_sigrec (
    id bigint NOT NULL,
    naturerecherche character varying(255),
    numenregistrement character varying(255),
    objetrecherche character varying(255),
    phaserecherche character varying(255),
    qualiteinsu character varying(255),
    titreprotocole text,
    typerecherche character varying(255),
    id_essai bigint,
    version bigint DEFAULT 0
);


ALTER TABLE public.essai_detail_recherche_sigrec OWNER TO eclipse;

--
-- TOC entry 1928 (class 1259 OID 405020)
-- Dependencies: 6 1927
-- Name: essai_detail_recherche_sigrec_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_recherche_sigrec_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_recherche_sigrec_id_seq OWNER TO eclipse;

--
-- TOC entry 3233 (class 0 OID 0)
-- Dependencies: 1928
-- Name: essai_detail_recherche_sigrec_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_recherche_sigrec_id_seq OWNED BY essai_detail_recherche_sigrec.id;


--
-- TOC entry 3234 (class 0 OID 0)
-- Dependencies: 1928
-- Name: essai_detail_recherche_sigrec_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('essai_detail_recherche_sigrec_id_seq', 1, false);


--
-- TOC entry 1929 (class 1259 OID 405022)
-- Dependencies: 2402 6
-- Name: essai_detail_recherche_suivi; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_detail_recherche_suivi (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_detail_recherche bigint NOT NULL,
    version bigint DEFAULT 0
);


ALTER TABLE public.essai_detail_recherche_suivi OWNER TO eclipse;

--
-- TOC entry 1930 (class 1259 OID 405026)
-- Dependencies: 1929 6
-- Name: essai_detail_recherche_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_recherche_suivi_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_recherche_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 3235 (class 0 OID 0)
-- Dependencies: 1930
-- Name: essai_detail_recherche_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_recherche_suivi_id_seq OWNED BY essai_detail_recherche_suivi.id;


--
-- TOC entry 3236 (class 0 OID 0)
-- Dependencies: 1930
-- Name: essai_detail_recherche_suivi_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('essai_detail_recherche_suivi_id_seq', 1, false);


--
-- TOC entry 1931 (class 1259 OID 405028)
-- Dependencies: 2404 6
-- Name: essai_detail_surcout; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_detail_surcout (
    id bigint NOT NULL,
    id_essai bigint,
    version bigint DEFAULT 0
);


ALTER TABLE public.essai_detail_surcout OWNER TO eclipse;

--
-- TOC entry 1932 (class 1259 OID 405032)
-- Dependencies: 6 1931
-- Name: essai_detail_surcout_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_surcout_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_surcout_id_seq OWNER TO eclipse;

--
-- TOC entry 3237 (class 0 OID 0)
-- Dependencies: 1932
-- Name: essai_detail_surcout_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_surcout_id_seq OWNED BY essai_detail_surcout.id;


--
-- TOC entry 3238 (class 0 OID 0)
-- Dependencies: 1932
-- Name: essai_detail_surcout_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('essai_detail_surcout_id_seq', 1, false);


--
-- TOC entry 1933 (class 1259 OID 405034)
-- Dependencies: 2406 6
-- Name: essai_detail_surcout_suivi; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_detail_surcout_suivi (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_detail_surcout bigint NOT NULL,
    version bigint DEFAULT 0
);


ALTER TABLE public.essai_detail_surcout_suivi OWNER TO eclipse;

--
-- TOC entry 1934 (class 1259 OID 405038)
-- Dependencies: 6 1933
-- Name: essai_detail_surcout_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_surcout_suivi_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_surcout_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 3239 (class 0 OID 0)
-- Dependencies: 1934
-- Name: essai_detail_surcout_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_surcout_suivi_id_seq OWNED BY essai_detail_surcout_suivi.id;


--
-- TOC entry 3240 (class 0 OID 0)
-- Dependencies: 1934
-- Name: essai_detail_surcout_suivi_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('essai_detail_surcout_suivi_id_seq', 1, false);


--
-- TOC entry 1935 (class 1259 OID 405040)
-- Dependencies: 2408 6
-- Name: essai_document_detail_administratif; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_document_detail_administratif (
    type character varying(31) NOT NULL,
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    nomdisque character varying(255) NOT NULL,
    nomuser character varying(255) NOT NULL,
    commentaire text,
    id_detailadministratif bigint NOT NULL,
    typedocumentprotocole character varying(255),
    version_doc character varying(255),
    typedocumentbrochure character varying(255),
    version bigint DEFAULT 0
);


ALTER TABLE public.essai_document_detail_administratif OWNER TO eclipse;

--
-- TOC entry 1936 (class 1259 OID 405047)
-- Dependencies: 1935 6
-- Name: essai_document_detail_administratif_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_document_detail_administratif_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_document_detail_administratif_id_seq OWNER TO eclipse;

--
-- TOC entry 3241 (class 0 OID 0)
-- Dependencies: 1936
-- Name: essai_document_detail_administratif_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_document_detail_administratif_id_seq OWNED BY essai_document_detail_administratif.id;


--
-- TOC entry 3242 (class 0 OID 0)
-- Dependencies: 1936
-- Name: essai_document_detail_administratif_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('essai_document_detail_administratif_id_seq', 1, false);


--
-- TOC entry 1937 (class 1259 OID 405049)
-- Dependencies: 2410 6
-- Name: essai_document_detail_autres_documents; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_document_detail_autres_documents (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    nomdisque character varying(255) NOT NULL,
    nomuser character varying(255) NOT NULL,
    commentaire text,
    type character varying(255) NOT NULL,
    id_detail_autres_documents bigint NOT NULL,
    version bigint DEFAULT 0
);


ALTER TABLE public.essai_document_detail_autres_documents OWNER TO eclipse;

--
-- TOC entry 1938 (class 1259 OID 405056)
-- Dependencies: 6 1937
-- Name: essai_document_detail_autres_documents_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_document_detail_autres_documents_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_document_detail_autres_documents_id_seq OWNER TO eclipse;

--
-- TOC entry 3243 (class 0 OID 0)
-- Dependencies: 1938
-- Name: essai_document_detail_autres_documents_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_document_detail_autres_documents_id_seq OWNED BY essai_document_detail_autres_documents.id;


--
-- TOC entry 3244 (class 0 OID 0)
-- Dependencies: 1938
-- Name: essai_document_detail_autres_documents_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('essai_document_detail_autres_documents_id_seq', 1, false);


--
-- TOC entry 1939 (class 1259 OID 405058)
-- Dependencies: 2412 6
-- Name: essai_document_detail_pharma; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_document_detail_pharma (
    type character varying(31) NOT NULL,
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    nomdisque character varying(255) NOT NULL,
    nomuser character varying(255) NOT NULL,
    commentaire text,
    id_detail_pharma bigint NOT NULL,
    version bigint DEFAULT 0
);


ALTER TABLE public.essai_document_detail_pharma OWNER TO eclipse;

--
-- TOC entry 1940 (class 1259 OID 405065)
-- Dependencies: 6 1939
-- Name: essai_document_detail_pharma_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_document_detail_pharma_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_document_detail_pharma_id_seq OWNER TO eclipse;

--
-- TOC entry 3245 (class 0 OID 0)
-- Dependencies: 1940
-- Name: essai_document_detail_pharma_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_document_detail_pharma_id_seq OWNED BY essai_document_detail_pharma.id;


--
-- TOC entry 3246 (class 0 OID 0)
-- Dependencies: 1940
-- Name: essai_document_detail_pharma_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('essai_document_detail_pharma_id_seq', 1, false);


--
-- TOC entry 1941 (class 1259 OID 405067)
-- Dependencies: 2414 6
-- Name: essai_document_detail_surcout; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_document_detail_surcout (
    type character varying(31) NOT NULL,
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    nomdisque character varying(255) NOT NULL,
    nomuser character varying(255) NOT NULL,
    commentaire text,
    id_detailsurcout bigint NOT NULL,
    version bigint DEFAULT 0
);


ALTER TABLE public.essai_document_detail_surcout OWNER TO eclipse;

--
-- TOC entry 1942 (class 1259 OID 405074)
-- Dependencies: 6 1941
-- Name: essai_document_detail_surcout_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_document_detail_surcout_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_document_detail_surcout_id_seq OWNER TO eclipse;

--
-- TOC entry 3247 (class 0 OID 0)
-- Dependencies: 1942
-- Name: essai_document_detail_surcout_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_document_detail_surcout_id_seq OWNED BY essai_document_detail_surcout.id;


--
-- TOC entry 3248 (class 0 OID 0)
-- Dependencies: 1942
-- Name: essai_document_detail_surcout_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('essai_document_detail_surcout_id_seq', 1, false);


--
-- TOC entry 1943 (class 1259 OID 405076)
-- Dependencies: 6 1881
-- Name: essai_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_id_seq OWNER TO eclipse;

--
-- TOC entry 3249 (class 0 OID 0)
-- Dependencies: 1943
-- Name: essai_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_id_seq OWNED BY essai.id;


--
-- TOC entry 3250 (class 0 OID 0)
-- Dependencies: 1943
-- Name: essai_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('essai_id_seq', 1, false);


--
-- TOC entry 1944 (class 1259 OID 405078)
-- Dependencies: 6
-- Name: essai_service; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_service (
    id_essai bigint NOT NULL,
    id_service bigint NOT NULL
);


ALTER TABLE public.essai_service OWNER TO eclipse;

--
-- TOC entry 1945 (class 1259 OID 405081)
-- Dependencies: 2416 6
-- Name: essai_sigrec; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_sigrec (
    id bigint NOT NULL,
    codepromoteur character varying(255),
    multicentrique boolean,
    nbcentres integer,
    nom character varying(255),
    numidentac character varying(255),
    typepromoteur character varying(255),
    id_investigateurprincipal bigint,
    id_promoteur bigint,
    version bigint DEFAULT 0
);


ALTER TABLE public.essai_sigrec OWNER TO eclipse;

--
-- TOC entry 1946 (class 1259 OID 405088)
-- Dependencies: 6 1945
-- Name: essai_sigrec_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_sigrec_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_sigrec_id_seq OWNER TO eclipse;

--
-- TOC entry 3251 (class 0 OID 0)
-- Dependencies: 1946
-- Name: essai_sigrec_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_sigrec_id_seq OWNED BY essai_sigrec.id;


--
-- TOC entry 3252 (class 0 OID 0)
-- Dependencies: 1946
-- Name: essai_sigrec_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('essai_sigrec_id_seq', 1, false);


--
-- TOC entry 1947 (class 1259 OID 405090)
-- Dependencies: 2418 6
-- Name: essai_suivi; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_suivi (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_essai bigint NOT NULL,
    version bigint DEFAULT 0
);


ALTER TABLE public.essai_suivi OWNER TO eclipse;

--
-- TOC entry 1948 (class 1259 OID 405094)
-- Dependencies: 6 1947
-- Name: essai_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_suivi_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 3253 (class 0 OID 0)
-- Dependencies: 1948
-- Name: essai_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_suivi_id_seq OWNED BY essai_suivi.id;


--
-- TOC entry 3254 (class 0 OID 0)
-- Dependencies: 1948
-- Name: essai_suivi_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('essai_suivi_id_seq', 1, false);


--
-- TOC entry 1949 (class 1259 OID 405096)
-- Dependencies: 2420 6
-- Name: etablissement; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE etablissement (
    id bigint NOT NULL,
    adressedirection text,
    codepostal character varying(255),
    fax character varying(255),
    mail character varying(255),
    nom character varying(255) NOT NULL,
    pays character varying(255),
    telephone character varying(255),
    ville character varying(255),
    version bigint DEFAULT 0
);


ALTER TABLE public.etablissement OWNER TO eclipse;

--
-- TOC entry 1950 (class 1259 OID 405103)
-- Dependencies: 1949 6
-- Name: etablissement_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE etablissement_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.etablissement_id_seq OWNER TO eclipse;

--
-- TOC entry 3255 (class 0 OID 0)
-- Dependencies: 1950
-- Name: etablissement_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE etablissement_id_seq OWNED BY etablissement.id;


--
-- TOC entry 3256 (class 0 OID 0)
-- Dependencies: 1950
-- Name: etablissement_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('etablissement_id_seq', 1, false);


--
-- TOC entry 1951 (class 1259 OID 405105)
-- Dependencies: 2422 6
-- Name: etablissement_suivi; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE etablissement_suivi (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_etablissement bigint NOT NULL,
    version bigint DEFAULT 0
);


ALTER TABLE public.etablissement_suivi OWNER TO eclipse;

--
-- TOC entry 1952 (class 1259 OID 405109)
-- Dependencies: 1951 6
-- Name: etablissement_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE etablissement_suivi_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.etablissement_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 3257 (class 0 OID 0)
-- Dependencies: 1952
-- Name: etablissement_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE etablissement_suivi_id_seq OWNED BY etablissement_suivi.id;


--
-- TOC entry 3258 (class 0 OID 0)
-- Dependencies: 1952
-- Name: etablissement_suivi_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('etablissement_suivi_id_seq', 1, false);


--
-- TOC entry 1953 (class 1259 OID 405111)
-- Dependencies: 2424 6
-- Name: evenement; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE evenement (
    id bigint NOT NULL,
    commentaire character varying(255),
    datedebut timestamp without time zone NOT NULL,
    heuredebut character varying(255),
    libelle character varying(255),
    resultatvisite character varying(255),
    typeevenement character varying(255) NOT NULL,
    typevisite character varying(255),
    id_essai bigint,
    arc character varying(255),
    datefin timestamp without time zone NOT NULL,
    heurefin character varying(255),
    journee boolean,
    personnelpharmacie text,
    nombre integer,
    validation character varying(255),
    realisepar character varying(255),
    id_personne bigint,
    destinataire character varying(255),
    datereception timestamp without time zone,
    version bigint DEFAULT 0
);


ALTER TABLE public.evenement OWNER TO eclipse;

--
-- TOC entry 1954 (class 1259 OID 405118)
-- Dependencies: 6 1953
-- Name: evenement_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE evenement_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.evenement_id_seq OWNER TO eclipse;

--
-- TOC entry 3259 (class 0 OID 0)
-- Dependencies: 1954
-- Name: evenement_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE evenement_id_seq OWNED BY evenement.id;


--
-- TOC entry 3260 (class 0 OID 0)
-- Dependencies: 1954
-- Name: evenement_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('evenement_id_seq', 1, false);


--
-- TOC entry 1955 (class 1259 OID 405120)
-- Dependencies: 2426 6
-- Name: evenement_suivi; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE evenement_suivi (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_evenement bigint NOT NULL,
    version bigint DEFAULT 0
);


ALTER TABLE public.evenement_suivi OWNER TO eclipse;

--
-- TOC entry 1956 (class 1259 OID 405124)
-- Dependencies: 1955 6
-- Name: evenement_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE evenement_suivi_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.evenement_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 3261 (class 0 OID 0)
-- Dependencies: 1956
-- Name: evenement_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE evenement_suivi_id_seq OWNED BY evenement_suivi.id;


--
-- TOC entry 3262 (class 0 OID 0)
-- Dependencies: 1956
-- Name: evenement_suivi_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('evenement_suivi_id_seq', 1, false);


--
-- TOC entry 1957 (class 1259 OID 405126)
-- Dependencies: 2428 6
-- Name: grille; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE grille (
    id bigint NOT NULL,
    id_grille_modele bigint NOT NULL,
    id_detail_surcout bigint,
    version bigint DEFAULT 0
);


ALTER TABLE public.grille OWNER TO eclipse;

--
-- TOC entry 1958 (class 1259 OID 405130)
-- Dependencies: 6 1957
-- Name: grille_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE grille_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.grille_id_seq OWNER TO eclipse;

--
-- TOC entry 3263 (class 0 OID 0)
-- Dependencies: 1958
-- Name: grille_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE grille_id_seq OWNED BY grille.id;


--
-- TOC entry 3264 (class 0 OID 0)
-- Dependencies: 1958
-- Name: grille_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('grille_id_seq', 1, false);


--
-- TOC entry 1959 (class 1259 OID 405132)
-- Dependencies: 2430 6
-- Name: grille_modele; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE grille_modele (
    id bigint NOT NULL,
    datecreation timestamp without time zone,
    datedebut timestamp without time zone,
    datefin timestamp without time zone,
    nom character varying(255),
    version bigint DEFAULT 0
);


ALTER TABLE public.grille_modele OWNER TO eclipse;

--
-- TOC entry 1960 (class 1259 OID 405136)
-- Dependencies: 1959 6
-- Name: grille_modele_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE grille_modele_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.grille_modele_id_seq OWNER TO eclipse;

--
-- TOC entry 3265 (class 0 OID 0)
-- Dependencies: 1960
-- Name: grille_modele_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE grille_modele_id_seq OWNED BY grille_modele.id;


--
-- TOC entry 3266 (class 0 OID 0)
-- Dependencies: 1960
-- Name: grille_modele_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('grille_modele_id_seq', 1, false);


--
-- TOC entry 1961 (class 1259 OID 405138)
-- Dependencies: 2432 6
-- Name: habilitation; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE habilitation (
    id bigint NOT NULL,
    active boolean NOT NULL,
    creepar character varying(255) NOT NULL,
    desactivepar character varying(255),
    creele timestamp without time zone NOT NULL,
    desactivele timestamp without time zone,
    desactivable boolean NOT NULL,
    droit character varying(255) NOT NULL,
    id_detail_contacts bigint NOT NULL,
    id_personne bigint NOT NULL,
    version bigint DEFAULT 0
);


ALTER TABLE public.habilitation OWNER TO eclipse;

--
-- TOC entry 1962 (class 1259 OID 405145)
-- Dependencies: 6 1961
-- Name: habilitation_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE habilitation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.habilitation_id_seq OWNER TO eclipse;

--
-- TOC entry 3267 (class 0 OID 0)
-- Dependencies: 1962
-- Name: habilitation_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE habilitation_id_seq OWNED BY habilitation.id;


--
-- TOC entry 3268 (class 0 OID 0)
-- Dependencies: 1962
-- Name: habilitation_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('habilitation_id_seq', 1, false);


--
-- TOC entry 1963 (class 1259 OID 405147)
-- Dependencies: 2434 6
-- Name: historique_patient; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE historique_patient (
    id bigint NOT NULL,
    commentaire text,
    date timestamp without time zone,
    poid double precision,
    surfacecorporelle double precision,
    taille double precision,
    id_patient bigint NOT NULL,
    version bigint DEFAULT 0
);


ALTER TABLE public.historique_patient OWNER TO eclipse;

--
-- TOC entry 1964 (class 1259 OID 405154)
-- Dependencies: 1963 6
-- Name: historique_patient_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE historique_patient_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.historique_patient_id_seq OWNER TO eclipse;

--
-- TOC entry 3269 (class 0 OID 0)
-- Dependencies: 1964
-- Name: historique_patient_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE historique_patient_id_seq OWNED BY historique_patient.id;


--
-- TOC entry 3270 (class 0 OID 0)
-- Dependencies: 1964
-- Name: historique_patient_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('historique_patient_id_seq', 1, false);


--
-- TOC entry 1965 (class 1259 OID 405156)
-- Dependencies: 2436 6
-- Name: incident; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE incident (
    id bigint NOT NULL,
    commentaire text,
    date timestamp without time zone NOT NULL,
    libelle character varying(255) NOT NULL,
    id_essai bigint NOT NULL,
    version bigint DEFAULT 0
);


ALTER TABLE public.incident OWNER TO eclipse;

--
-- TOC entry 1966 (class 1259 OID 405163)
-- Dependencies: 1965 6
-- Name: incident_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE incident_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.incident_id_seq OWNER TO eclipse;

--
-- TOC entry 3271 (class 0 OID 0)
-- Dependencies: 1966
-- Name: incident_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE incident_id_seq OWNED BY incident.id;


--
-- TOC entry 3272 (class 0 OID 0)
-- Dependencies: 1966
-- Name: incident_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('incident_id_seq', 1, false);


--
-- TOC entry 1967 (class 1259 OID 405165)
-- Dependencies: 2438 6
-- Name: incident_suivi; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE incident_suivi (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_incident bigint NOT NULL,
    version bigint DEFAULT 0
);


ALTER TABLE public.incident_suivi OWNER TO eclipse;

--
-- TOC entry 1968 (class 1259 OID 405169)
-- Dependencies: 1967 6
-- Name: incident_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE incident_suivi_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.incident_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 3273 (class 0 OID 0)
-- Dependencies: 1968
-- Name: incident_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE incident_suivi_id_seq OWNED BY incident_suivi.id;


--
-- TOC entry 3274 (class 0 OID 0)
-- Dependencies: 1968
-- Name: incident_suivi_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('incident_suivi_id_seq', 1, false);


--
-- TOC entry 1969 (class 1259 OID 405171)
-- Dependencies: 2440 6
-- Name: inclusion; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE inclusion (
    id bigint NOT NULL,
    actif boolean,
    datedesinclusion timestamp without time zone,
    dateinclusion timestamp without time zone,
    numinclusion character varying(255),
    numrandomisation character varying(255),
    id_essai bigint NOT NULL,
    id_patient bigint NOT NULL,
    version bigint DEFAULT 0
);


ALTER TABLE public.inclusion OWNER TO eclipse;

--
-- TOC entry 1970 (class 1259 OID 405178)
-- Dependencies: 6 1969
-- Name: inclusion_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE inclusion_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.inclusion_id_seq OWNER TO eclipse;

--
-- TOC entry 3275 (class 0 OID 0)
-- Dependencies: 1970
-- Name: inclusion_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE inclusion_id_seq OWNED BY inclusion.id;


--
-- TOC entry 3276 (class 0 OID 0)
-- Dependencies: 1970
-- Name: inclusion_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('inclusion_id_seq', 1, false);


--
-- TOC entry 1971 (class 1259 OID 405180)
-- Dependencies: 6
-- Name: investigateur_service; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE investigateur_service (
    id_investigateur bigint NOT NULL,
    id_service bigint NOT NULL
);


ALTER TABLE public.investigateur_service OWNER TO eclipse;

--
-- TOC entry 1972 (class 1259 OID 405183)
-- Dependencies: 2442 6
-- Name: investigateur_sigrec; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE investigateur_sigrec (
    id bigint NOT NULL,
    identifiant character varying(255),
    intervenantid integer,
    numadeli character varying(255),
    titre character varying(255),
    id_contact bigint,
    id_centre bigint,
    version bigint DEFAULT 0
);


ALTER TABLE public.investigateur_sigrec OWNER TO eclipse;

--
-- TOC entry 1973 (class 1259 OID 405190)
-- Dependencies: 6 1972
-- Name: investigateur_sigrec_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE investigateur_sigrec_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.investigateur_sigrec_id_seq OWNER TO eclipse;

--
-- TOC entry 3277 (class 0 OID 0)
-- Dependencies: 1973
-- Name: investigateur_sigrec_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE investigateur_sigrec_id_seq OWNED BY investigateur_sigrec.id;


--
-- TOC entry 3278 (class 0 OID 0)
-- Dependencies: 1973
-- Name: investigateur_sigrec_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('investigateur_sigrec_id_seq', 1, false);


--
-- TOC entry 1974 (class 1259 OID 405192)
-- Dependencies: 2444 6
-- Name: item; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE item (
    id bigint NOT NULL,
    categorie character varying(255),
    theme character varying(255),
    id_grille bigint NOT NULL,
    acte character varying(255),
    version bigint DEFAULT 0
);


ALTER TABLE public.item OWNER TO eclipse;

--
-- TOC entry 1975 (class 1259 OID 405199)
-- Dependencies: 6 1974
-- Name: item_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE item_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.item_id_seq OWNER TO eclipse;

--
-- TOC entry 3279 (class 0 OID 0)
-- Dependencies: 1975
-- Name: item_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE item_id_seq OWNED BY item.id;


--
-- TOC entry 3280 (class 0 OID 0)
-- Dependencies: 1975
-- Name: item_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('item_id_seq', 1, false);


--
-- TOC entry 1976 (class 1259 OID 405201)
-- Dependencies: 6
-- Name: item_regle; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE item_regle (
    id_regle bigint NOT NULL,
    id_item bigint NOT NULL
);


ALTER TABLE public.item_regle OWNER TO eclipse;

--
-- TOC entry 1977 (class 1259 OID 405204)
-- Dependencies: 2446 6
-- Name: lignestock; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE lignestock (
    id bigint NOT NULL,
    approapprouve boolean,
    dateperemption timestamp without time zone,
    numlot character varying(255),
    numtraitement character varying(255),
    quantite_dispensation_en_stock integer NOT NULL,
    quantite_global integer NOT NULL,
    stockage character varying(255),
    version bigint DEFAULT 0,
    id_conditionnement bigint NOT NULL,
    id_essai bigint NOT NULL,
    id_pharmacie bigint NOT NULL,
    id_produit bigint NOT NULL
);


ALTER TABLE public.lignestock OWNER TO eclipse;

--
-- TOC entry 1978 (class 1259 OID 405211)
-- Dependencies: 1977 6
-- Name: lignestock_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE lignestock_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.lignestock_id_seq OWNER TO eclipse;

--
-- TOC entry 3281 (class 0 OID 0)
-- Dependencies: 1978
-- Name: lignestock_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE lignestock_id_seq OWNED BY lignestock.id;


--
-- TOC entry 3282 (class 0 OID 0)
-- Dependencies: 1978
-- Name: lignestock_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('lignestock_id_seq', 1, false);


--
-- TOC entry 1979 (class 1259 OID 405213)
-- Dependencies: 6
-- Name: medicament; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
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


ALTER TABLE public.medicament OWNER TO eclipse;

--
-- TOC entry 1980 (class 1259 OID 405219)
-- Dependencies: 6
-- Name: mvt_approvisionnement; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE mvt_approvisionnement (
    commentaireextensionperemption text,
    commentairerefus text,
    datearriveecolis date,
    dateperemption date,
    datereception timestamp without time zone,
    extensionperemption boolean,
    motifrefus character varying(255),
    id bigint NOT NULL,
    historiqueextensionperemption text
);


ALTER TABLE public.mvt_approvisionnement OWNER TO eclipse;

--
-- TOC entry 1981 (class 1259 OID 405225)
-- Dependencies: 6
-- Name: mvt_autre_sortie; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE mvt_autre_sortie (
    commentaire text,
    id bigint NOT NULL,
    raisonsortie character varying(255),
    commentaireraison text
);


ALTER TABLE public.mvt_autre_sortie OWNER TO eclipse;

--
-- TOC entry 1982 (class 1259 OID 405231)
-- Dependencies: 6
-- Name: mvt_cession_pui; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE mvt_cession_pui (
    commentaire text,
    id bigint NOT NULL,
    id_pharmaciedest bigint,
    raisonsortie character varying(255),
    commentaireraison text
);


ALTER TABLE public.mvt_cession_pui OWNER TO eclipse;

--
-- TOC entry 1983 (class 1259 OID 405237)
-- Dependencies: 6
-- Name: mvt_destruction; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE mvt_destruction (
    commentaire text,
    id bigint NOT NULL,
    raisonsortie character varying(255),
    commentaireraison text
);


ALTER TABLE public.mvt_destruction OWNER TO eclipse;

--
-- TOC entry 1984 (class 1259 OID 405243)
-- Dependencies: 6
-- Name: mvt_dispensation; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE mvt_dispensation (
    id bigint NOT NULL,
    id_dispensation bigint NOT NULL,
    id_produitprescrit bigint NOT NULL
);


ALTER TABLE public.mvt_dispensation OWNER TO eclipse;

--
-- TOC entry 1985 (class 1259 OID 405246)
-- Dependencies: 6
-- Name: mvt_dispensation_globale; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE mvt_dispensation_globale (
    id bigint NOT NULL,
    id_dotation bigint NOT NULL,
    quantitedispensee integer
);


ALTER TABLE public.mvt_dispensation_globale OWNER TO eclipse;

--
-- TOC entry 1986 (class 1259 OID 405249)
-- Dependencies: 6
-- Name: mvt_preparation; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE mvt_preparation (
    id bigint NOT NULL
);


ALTER TABLE public.mvt_preparation OWNER TO eclipse;

--
-- TOC entry 1987 (class 1259 OID 405252)
-- Dependencies: 6
-- Name: mvt_preparationentree; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE mvt_preparationentree (
    composition text,
    datefabrication timestamp without time zone,
    id bigint NOT NULL,
    numordonnancier integer,
    id_ordonnancier bigint,
    sterile boolean
);


ALTER TABLE public.mvt_preparationentree OWNER TO eclipse;

--
-- TOC entry 1988 (class 1259 OID 405258)
-- Dependencies: 6
-- Name: mvt_retour_promoteur; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE mvt_retour_promoteur (
    commentaire text,
    nomsocietetransport character varying(255),
    referenceenvoi character varying(255),
    id bigint NOT NULL,
    raisonsortie character varying(255),
    commentaireraison text
);


ALTER TABLE public.mvt_retour_promoteur OWNER TO eclipse;

--
-- TOC entry 1989 (class 1259 OID 405264)
-- Dependencies: 2448 6
-- Name: mvtstock; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE mvtstock (
    id bigint NOT NULL,
    approapprouve boolean NOT NULL,
    datecreation timestamp without time zone NOT NULL,
    numlot character varying(255) NOT NULL,
    numtraitement character varying(255),
    quantite integer NOT NULL,
    type character varying(255),
    id_conditionnement bigint NOT NULL,
    id_essai bigint NOT NULL,
    id_personne bigint NOT NULL,
    id_pharmacie bigint NOT NULL,
    id_produit bigint NOT NULL,
    dateperemption timestamp without time zone,
    version bigint DEFAULT 0
);


ALTER TABLE public.mvtstock OWNER TO eclipse;

--
-- TOC entry 1990 (class 1259 OID 405271)
-- Dependencies: 2450 6
-- Name: mvtstock_document; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE mvtstock_document (
    type character varying(31) NOT NULL,
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    nomdisque character varying(255) NOT NULL,
    nomuser character varying(255) NOT NULL,
    id_mvtstock bigint NOT NULL,
    version bigint DEFAULT 0
);


ALTER TABLE public.mvtstock_document OWNER TO eclipse;

--
-- TOC entry 1991 (class 1259 OID 405278)
-- Dependencies: 6 1990
-- Name: mvtstock_document_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE mvtstock_document_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.mvtstock_document_id_seq OWNER TO eclipse;

--
-- TOC entry 3283 (class 0 OID 0)
-- Dependencies: 1991
-- Name: mvtstock_document_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE mvtstock_document_id_seq OWNED BY mvtstock_document.id;


--
-- TOC entry 3284 (class 0 OID 0)
-- Dependencies: 1991
-- Name: mvtstock_document_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('mvtstock_document_id_seq', 1, false);


--
-- TOC entry 1992 (class 1259 OID 405280)
-- Dependencies: 1989 6
-- Name: mvtstock_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE mvtstock_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.mvtstock_id_seq OWNER TO eclipse;

--
-- TOC entry 3285 (class 0 OID 0)
-- Dependencies: 1992
-- Name: mvtstock_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE mvtstock_id_seq OWNED BY mvtstock.id;


--
-- TOC entry 3286 (class 0 OID 0)
-- Dependencies: 1992
-- Name: mvtstock_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('mvtstock_id_seq', 1, false);


--
-- TOC entry 1993 (class 1259 OID 405282)
-- Dependencies: 2452 6
-- Name: ordonnancier_dispensation; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE ordonnancier_dispensation (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    datedebut date NOT NULL,
    datefin date NOT NULL,
    id_pharma bigint NOT NULL,
    version bigint DEFAULT 0
);


ALTER TABLE public.ordonnancier_dispensation OWNER TO eclipse;

--
-- TOC entry 1994 (class 1259 OID 405286)
-- Dependencies: 1993 6
-- Name: ordonnancier_dispensation_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE ordonnancier_dispensation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.ordonnancier_dispensation_id_seq OWNER TO eclipse;

--
-- TOC entry 3287 (class 0 OID 0)
-- Dependencies: 1994
-- Name: ordonnancier_dispensation_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE ordonnancier_dispensation_id_seq OWNED BY ordonnancier_dispensation.id;


--
-- TOC entry 3288 (class 0 OID 0)
-- Dependencies: 1994
-- Name: ordonnancier_dispensation_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('ordonnancier_dispensation_id_seq', 1, false);


--
-- TOC entry 1995 (class 1259 OID 405288)
-- Dependencies: 2454 6
-- Name: ordonnancier_fab_reconst; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE ordonnancier_fab_reconst (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    datedebut date NOT NULL,
    datefin date NOT NULL,
    id_pharma bigint NOT NULL,
    version bigint DEFAULT 0
);


ALTER TABLE public.ordonnancier_fab_reconst OWNER TO eclipse;

--
-- TOC entry 1996 (class 1259 OID 405292)
-- Dependencies: 6 1995
-- Name: ordonnancier_fab_reconst_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE ordonnancier_fab_reconst_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.ordonnancier_fab_reconst_id_seq OWNER TO eclipse;

--
-- TOC entry 3289 (class 0 OID 0)
-- Dependencies: 1996
-- Name: ordonnancier_fab_reconst_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE ordonnancier_fab_reconst_id_seq OWNED BY ordonnancier_fab_reconst.id;


--
-- TOC entry 3290 (class 0 OID 0)
-- Dependencies: 1996
-- Name: ordonnancier_fab_reconst_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('ordonnancier_fab_reconst_id_seq', 1, false);


--
-- TOC entry 1997 (class 1259 OID 405294)
-- Dependencies: 2456 6
-- Name: patient; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE patient (
    id bigint NOT NULL,
    adresse text,
    civilite character varying(255),
    codepostal character varying(255),
    datenaissance timestamp without time zone,
    mail character varying(255),
    nom character varying(255),
    nomjeunefille character varying(255),
    numeroipp character varying(255),
    prenom character varying(255),
    telephone character varying(255),
    ville character varying(255),
    initiales character varying(255),
    numerosejour character varying(255),
    version bigint DEFAULT 0
);


ALTER TABLE public.patient OWNER TO eclipse;

--
-- TOC entry 1998 (class 1259 OID 405301)
-- Dependencies: 1997 6
-- Name: patient_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE patient_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.patient_id_seq OWNER TO eclipse;

--
-- TOC entry 3291 (class 0 OID 0)
-- Dependencies: 1998
-- Name: patient_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE patient_id_seq OWNED BY patient.id;


--
-- TOC entry 3292 (class 0 OID 0)
-- Dependencies: 1998
-- Name: patient_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('patient_id_seq', 1, false);


--
-- TOC entry 1999 (class 1259 OID 405303)
-- Dependencies: 2458 6
-- Name: patient_suivi; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE patient_suivi (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_patient bigint NOT NULL,
    version bigint DEFAULT 0
);


ALTER TABLE public.patient_suivi OWNER TO eclipse;

--
-- TOC entry 2000 (class 1259 OID 405307)
-- Dependencies: 1999 6
-- Name: patient_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE patient_suivi_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.patient_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 3293 (class 0 OID 0)
-- Dependencies: 2000
-- Name: patient_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE patient_suivi_id_seq OWNED BY patient_suivi.id;


--
-- TOC entry 3294 (class 0 OID 0)
-- Dependencies: 2000
-- Name: patient_suivi_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('patient_suivi_id_seq', 1, false);


--
-- TOC entry 2001 (class 1259 OID 405309)
-- Dependencies: 2460 6
-- Name: personne; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE personne (
    type character varying(31) NOT NULL,
    id bigint NOT NULL,
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
    ville character varying(255),
    nomsociete character varying(255),
    titre character varying(255),
    typepharmacien character varying(255),
    id_promoteur bigint,
    telephoneportable character varying(255),
    datearriveeservice timestamp without time zone,
    datedepartservice timestamp without time zone,
    datevalidationformation timestamp without time zone,
    version bigint DEFAULT 0
);


ALTER TABLE public.personne OWNER TO eclipse;

--
-- TOC entry 2002 (class 1259 OID 405316)
-- Dependencies: 2001 6
-- Name: personne_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE personne_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.personne_id_seq OWNER TO eclipse;

--
-- TOC entry 3295 (class 0 OID 0)
-- Dependencies: 2002
-- Name: personne_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE personne_id_seq OWNED BY personne.id;


--
-- TOC entry 3296 (class 0 OID 0)
-- Dependencies: 2002
-- Name: personne_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('personne_id_seq', 1, false);


--
-- TOC entry 2003 (class 1259 OID 405318)
-- Dependencies: 2462 6
-- Name: personne_suivi; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE personne_suivi (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_personne bigint NOT NULL,
    version bigint DEFAULT 0
);


ALTER TABLE public.personne_suivi OWNER TO eclipse;

--
-- TOC entry 2004 (class 1259 OID 405322)
-- Dependencies: 2003 6
-- Name: personne_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE personne_suivi_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.personne_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 3297 (class 0 OID 0)
-- Dependencies: 2004
-- Name: personne_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE personne_suivi_id_seq OWNED BY personne_suivi.id;


--
-- TOC entry 3298 (class 0 OID 0)
-- Dependencies: 2004
-- Name: personne_suivi_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('personne_suivi_id_seq', 1, false);


--
-- TOC entry 2005 (class 1259 OID 405324)
-- Dependencies: 2464 2465 2466 6
-- Name: pharmacie; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE pharmacie (
    id bigint NOT NULL,
    adresse character varying(255),
    adresselivraison character varying(255),
    fax character varying(255),
    nom character varying(255) NOT NULL,
    responsableprincipal character varying(255),
    telephone character varying(255),
    id_etablissement bigint NOT NULL,
    numordonnancierdisp integer DEFAULT 0 NOT NULL,
    numordonnancierfab integer DEFAULT 0 NOT NULL,
    version bigint DEFAULT 0
);


ALTER TABLE public.pharmacie OWNER TO eclipse;

--
-- TOC entry 2006 (class 1259 OID 405333)
-- Dependencies: 6 2005
-- Name: pharmacie_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE pharmacie_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.pharmacie_id_seq OWNER TO eclipse;

--
-- TOC entry 3299 (class 0 OID 0)
-- Dependencies: 2006
-- Name: pharmacie_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE pharmacie_id_seq OWNED BY pharmacie.id;


--
-- TOC entry 3300 (class 0 OID 0)
-- Dependencies: 2006
-- Name: pharmacie_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('pharmacie_id_seq', 1, false);


--
-- TOC entry 2007 (class 1259 OID 405335)
-- Dependencies: 6
-- Name: pharmacie_site; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE pharmacie_site (
    id_pharmacie bigint NOT NULL,
    id_site bigint NOT NULL
);


ALTER TABLE public.pharmacie_site OWNER TO eclipse;

--
-- TOC entry 2008 (class 1259 OID 405338)
-- Dependencies: 2468 6
-- Name: pharmacie_suivi; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE pharmacie_suivi (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_pharmacie bigint NOT NULL,
    version bigint DEFAULT 0
);


ALTER TABLE public.pharmacie_suivi OWNER TO eclipse;

--
-- TOC entry 2009 (class 1259 OID 405342)
-- Dependencies: 6 2008
-- Name: pharmacie_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE pharmacie_suivi_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.pharmacie_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 3301 (class 0 OID 0)
-- Dependencies: 2009
-- Name: pharmacie_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE pharmacie_suivi_id_seq OWNED BY pharmacie_suivi.id;


--
-- TOC entry 3302 (class 0 OID 0)
-- Dependencies: 2009
-- Name: pharmacie_suivi_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('pharmacie_suivi_id_seq', 1, false);


--
-- TOC entry 2010 (class 1259 OID 405344)
-- Dependencies: 2470 6
-- Name: pharmacien_document_pharmacien; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE pharmacien_document_pharmacien (
    type character varying(31) NOT NULL,
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    nomdisque character varying(255),
    nomuser character varying(255),
    id_pharmacien bigint NOT NULL,
    version bigint DEFAULT 0
);


ALTER TABLE public.pharmacien_document_pharmacien OWNER TO eclipse;

--
-- TOC entry 2011 (class 1259 OID 405351)
-- Dependencies: 2010 6
-- Name: pharmacien_document_pharmacien_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE pharmacien_document_pharmacien_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.pharmacien_document_pharmacien_id_seq OWNER TO eclipse;

--
-- TOC entry 3303 (class 0 OID 0)
-- Dependencies: 2011
-- Name: pharmacien_document_pharmacien_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE pharmacien_document_pharmacien_id_seq OWNED BY pharmacien_document_pharmacien.id;


--
-- TOC entry 3304 (class 0 OID 0)
-- Dependencies: 2011
-- Name: pharmacien_document_pharmacien_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('pharmacien_document_pharmacien_id_seq', 1, false);


--
-- TOC entry 2012 (class 1259 OID 405353)
-- Dependencies: 6
-- Name: pharmacien_pharmacie; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE pharmacien_pharmacie (
    id_pharmacien bigint NOT NULL,
    id_pharmacie bigint NOT NULL
);


ALTER TABLE public.pharmacien_pharmacie OWNER TO eclipse;

--
-- TOC entry 2013 (class 1259 OID 405356)
-- Dependencies: 2472 6
-- Name: pole; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE pole (
    id bigint NOT NULL,
    nom character varying(255) NOT NULL,
    id_etablissement bigint NOT NULL,
    version bigint DEFAULT 0
);


ALTER TABLE public.pole OWNER TO eclipse;

--
-- TOC entry 2014 (class 1259 OID 405360)
-- Dependencies: 2013 6
-- Name: pole_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE pole_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.pole_id_seq OWNER TO eclipse;

--
-- TOC entry 3305 (class 0 OID 0)
-- Dependencies: 2014
-- Name: pole_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE pole_id_seq OWNED BY pole.id;


--
-- TOC entry 3306 (class 0 OID 0)
-- Dependencies: 2014
-- Name: pole_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('pole_id_seq', 1, false);


--
-- TOC entry 2015 (class 1259 OID 405362)
-- Dependencies: 2474 6
-- Name: pole_suivi; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE pole_suivi (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_pole bigint NOT NULL,
    version bigint DEFAULT 0
);


ALTER TABLE public.pole_suivi OWNER TO eclipse;

--
-- TOC entry 2016 (class 1259 OID 405366)
-- Dependencies: 2015 6
-- Name: pole_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE pole_suivi_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.pole_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 3307 (class 0 OID 0)
-- Dependencies: 2016
-- Name: pole_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE pole_suivi_id_seq OWNED BY pole_suivi.id;


--
-- TOC entry 3308 (class 0 OID 0)
-- Dependencies: 2016
-- Name: pole_suivi_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('pole_suivi_id_seq', 1, false);


--
-- TOC entry 2017 (class 1259 OID 405368)
-- Dependencies: 6
-- Name: preparation; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE preparation (
    id bigint NOT NULL
);


ALTER TABLE public.preparation OWNER TO eclipse;

--
-- TOC entry 2018 (class 1259 OID 405371)
-- Dependencies: 2476 6
-- Name: prescription; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE prescription (
    id bigint NOT NULL,
    datedebuttraitement timestamp without time zone,
    dateprescription timestamp without time zone,
    dispense boolean,
    numprescription integer,
    id_inclusion bigint NOT NULL,
    id_investigateur bigint NOT NULL,
    id_sequence bigint,
    id_service bigint NOT NULL,
    commentaire text,
    numvisite character varying(255),
    version bigint DEFAULT 0
);


ALTER TABLE public.prescription OWNER TO eclipse;

--
-- TOC entry 2019 (class 1259 OID 405378)
-- Dependencies: 6 2018
-- Name: prescription_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE prescription_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.prescription_id_seq OWNER TO eclipse;

--
-- TOC entry 3309 (class 0 OID 0)
-- Dependencies: 2019
-- Name: prescription_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE prescription_id_seq OWNED BY prescription.id;


--
-- TOC entry 3310 (class 0 OID 0)
-- Dependencies: 2019
-- Name: prescription_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('prescription_id_seq', 1, false);


--
-- TOC entry 2020 (class 1259 OID 405380)
-- Dependencies: 2478 6
-- Name: prescription_type; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE prescription_type (
    id bigint NOT NULL,
    nb_debut integer,
    unite_debut character varying(255),
    description text,
    nb_duree integer,
    unite_duree character varying(255),
    nbfrequence integer,
    nbunitetempsfrequence integer,
    typeregularite character varying(255),
    unitefrequence character varying(255),
    nbunitedosage numeric(19,2),
    id_conditionnement bigint,
    id_produit bigint,
    id_sequence bigint,
    dosage numeric(19,2),
    version bigint DEFAULT 0
);


ALTER TABLE public.prescription_type OWNER TO eclipse;

--
-- TOC entry 2021 (class 1259 OID 405387)
-- Dependencies: 2020 6
-- Name: prescription_type_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE prescription_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.prescription_type_id_seq OWNER TO eclipse;

--
-- TOC entry 3311 (class 0 OID 0)
-- Dependencies: 2021
-- Name: prescription_type_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE prescription_type_id_seq OWNED BY prescription_type.id;


--
-- TOC entry 3312 (class 0 OID 0)
-- Dependencies: 2021
-- Name: prescription_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('prescription_type_id_seq', 1, false);


--
-- TOC entry 2022 (class 1259 OID 405389)
-- Dependencies: 2480 6
-- Name: prevision_sigrec; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE prevision_sigrec (
    id bigint NOT NULL,
    datedebut timestamp without time zone,
    datefin timestamp without time zone,
    dureetotale integer,
    nbcentres integer,
    id_essai bigint,
    version bigint DEFAULT 0
);


ALTER TABLE public.prevision_sigrec OWNER TO eclipse;

--
-- TOC entry 2023 (class 1259 OID 405393)
-- Dependencies: 6 2022
-- Name: prevision_sigrec_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE prevision_sigrec_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.prevision_sigrec_id_seq OWNER TO eclipse;

--
-- TOC entry 3313 (class 0 OID 0)
-- Dependencies: 2023
-- Name: prevision_sigrec_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE prevision_sigrec_id_seq OWNED BY prevision_sigrec.id;


--
-- TOC entry 3314 (class 0 OID 0)
-- Dependencies: 2023
-- Name: prevision_sigrec_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('prevision_sigrec_id_seq', 1, false);


--
-- TOC entry 2024 (class 1259 OID 405395)
-- Dependencies: 2482 6
-- Name: produit; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE produit (
    id bigint NOT NULL,
    alerteactive boolean NOT NULL,
    classetherapeutique character varying(255),
    code character varying(255),
    conseils text,
    denomination character varying(255) NOT NULL,
    imputationuf boolean,
    type character varying(255),
    id_detailproduit bigint NOT NULL,
    version bigint DEFAULT 0
);


ALTER TABLE public.produit OWNER TO eclipse;

--
-- TOC entry 2025 (class 1259 OID 405402)
-- Dependencies: 2484 6
-- Name: produit_detail_logistique; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE produit_detail_logistique (
    id bigint NOT NULL,
    abrilumiere boolean,
    comptabiliteretour boolean,
    conditionconservation character varying(255),
    delaialerteavtdateexpiration integer,
    miseadispo boolean,
    produitnonfourni boolean,
    quantiteautorise integer,
    stockseuil integer,
    id_produit bigint,
    version bigint DEFAULT 0
);


ALTER TABLE public.produit_detail_logistique OWNER TO eclipse;

--
-- TOC entry 2026 (class 1259 OID 405406)
-- Dependencies: 2025 6
-- Name: produit_detail_logistique_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE produit_detail_logistique_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.produit_detail_logistique_id_seq OWNER TO eclipse;

--
-- TOC entry 3315 (class 0 OID 0)
-- Dependencies: 2026
-- Name: produit_detail_logistique_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE produit_detail_logistique_id_seq OWNED BY produit_detail_logistique.id;


--
-- TOC entry 3316 (class 0 OID 0)
-- Dependencies: 2026
-- Name: produit_detail_logistique_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('produit_detail_logistique_id_seq', 1, false);


--
-- TOC entry 2027 (class 1259 OID 405408)
-- Dependencies: 2486 6
-- Name: produit_detail_stockage; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE produit_detail_stockage (
    id bigint NOT NULL,
    identifiantstockage character varying(255),
    type character varying(255),
    id_detail_logistique bigint,
    id_pharmacie bigint,
    id_stockage bigint,
    version bigint DEFAULT 0
);


ALTER TABLE public.produit_detail_stockage OWNER TO eclipse;

--
-- TOC entry 2028 (class 1259 OID 405415)
-- Dependencies: 2027 6
-- Name: produit_detail_stockage_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE produit_detail_stockage_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.produit_detail_stockage_id_seq OWNER TO eclipse;

--
-- TOC entry 3317 (class 0 OID 0)
-- Dependencies: 2028
-- Name: produit_detail_stockage_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE produit_detail_stockage_id_seq OWNED BY produit_detail_stockage.id;


--
-- TOC entry 3318 (class 0 OID 0)
-- Dependencies: 2028
-- Name: produit_detail_stockage_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('produit_detail_stockage_id_seq', 1, false);


--
-- TOC entry 2029 (class 1259 OID 405417)
-- Dependencies: 2488 6
-- Name: produit_document_actes_pharma; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE produit_document_actes_pharma (
    type character varying(31) NOT NULL,
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    nomdisque character varying(255) NOT NULL,
    nomuser character varying(255) NOT NULL,
    id_produit bigint NOT NULL,
    version bigint DEFAULT 0
);


ALTER TABLE public.produit_document_actes_pharma OWNER TO eclipse;

--
-- TOC entry 2030 (class 1259 OID 405424)
-- Dependencies: 6 2029
-- Name: produit_document_actes_pharma_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE produit_document_actes_pharma_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.produit_document_actes_pharma_id_seq OWNER TO eclipse;

--
-- TOC entry 3319 (class 0 OID 0)
-- Dependencies: 2030
-- Name: produit_document_actes_pharma_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE produit_document_actes_pharma_id_seq OWNED BY produit_document_actes_pharma.id;


--
-- TOC entry 3320 (class 0 OID 0)
-- Dependencies: 2030
-- Name: produit_document_actes_pharma_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('produit_document_actes_pharma_id_seq', 100000, false);


--
-- TOC entry 2031 (class 1259 OID 405426)
-- Dependencies: 6 2024
-- Name: produit_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE produit_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.produit_id_seq OWNER TO eclipse;

--
-- TOC entry 3321 (class 0 OID 0)
-- Dependencies: 2031
-- Name: produit_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE produit_id_seq OWNED BY produit.id;


--
-- TOC entry 3322 (class 0 OID 0)
-- Dependencies: 2031
-- Name: produit_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('produit_id_seq', 1, false);


--
-- TOC entry 2032 (class 1259 OID 405428)
-- Dependencies: 2490 6
-- Name: produit_prescrit; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE produit_prescrit (
    id bigint NOT NULL,
    nb_debut integer,
    unite_debut character varying(255),
    description text,
    dispense boolean,
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
    id_produit bigint NOT NULL,
    dosage numeric(19,2),
    adispenser boolean,
    version bigint DEFAULT 0
);


ALTER TABLE public.produit_prescrit OWNER TO eclipse;

--
-- TOC entry 2033 (class 1259 OID 405435)
-- Dependencies: 6 2032
-- Name: produit_prescrit_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE produit_prescrit_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.produit_prescrit_id_seq OWNER TO eclipse;

--
-- TOC entry 3323 (class 0 OID 0)
-- Dependencies: 2033
-- Name: produit_prescrit_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE produit_prescrit_id_seq OWNED BY produit_prescrit.id;


--
-- TOC entry 3324 (class 0 OID 0)
-- Dependencies: 2033
-- Name: produit_prescrit_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('produit_prescrit_id_seq', 1, false);


--
-- TOC entry 2034 (class 1259 OID 405437)
-- Dependencies: 6
-- Name: produit_service; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE produit_service (
    id_produit bigint NOT NULL,
    id_service bigint NOT NULL
);


ALTER TABLE public.produit_service OWNER TO eclipse;

--
-- TOC entry 2035 (class 1259 OID 405440)
-- Dependencies: 2492 6
-- Name: produit_suivi; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE produit_suivi (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_produit bigint NOT NULL,
    version bigint DEFAULT 0
);


ALTER TABLE public.produit_suivi OWNER TO eclipse;

--
-- TOC entry 2036 (class 1259 OID 405444)
-- Dependencies: 2035 6
-- Name: produit_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE produit_suivi_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.produit_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 3325 (class 0 OID 0)
-- Dependencies: 2036
-- Name: produit_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE produit_suivi_id_seq OWNED BY produit_suivi.id;


--
-- TOC entry 3326 (class 0 OID 0)
-- Dependencies: 2036
-- Name: produit_suivi_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('produit_suivi_id_seq', 1, false);


--
-- TOC entry 2037 (class 1259 OID 405446)
-- Dependencies: 6
-- Name: produit_therapeutique; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
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


ALTER TABLE public.produit_therapeutique OWNER TO eclipse;

--
-- TOC entry 2038 (class 1259 OID 405452)
-- Dependencies: 2494 6
-- Name: promoteur; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE promoteur (
    id bigint NOT NULL,
    identifiant character varying(255),
    raisonsociale character varying(255) NOT NULL,
    type character varying(255) NOT NULL,
    version bigint DEFAULT 0
);


ALTER TABLE public.promoteur OWNER TO eclipse;

--
-- TOC entry 2039 (class 1259 OID 405459)
-- Dependencies: 6 2038
-- Name: promoteur_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE promoteur_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.promoteur_id_seq OWNER TO eclipse;

--
-- TOC entry 3327 (class 0 OID 0)
-- Dependencies: 2039
-- Name: promoteur_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE promoteur_id_seq OWNED BY promoteur.id;


--
-- TOC entry 3328 (class 0 OID 0)
-- Dependencies: 2039
-- Name: promoteur_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('promoteur_id_seq', 1, false);


--
-- TOC entry 2040 (class 1259 OID 405461)
-- Dependencies: 2496 6
-- Name: promoteur_sigrec; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE promoteur_sigrec (
    id bigint NOT NULL,
    identifiant character varying(255),
    type character varying(255),
    id_contact bigint,
    version bigint DEFAULT 0
);


ALTER TABLE public.promoteur_sigrec OWNER TO eclipse;

--
-- TOC entry 2041 (class 1259 OID 405468)
-- Dependencies: 2040 6
-- Name: promoteur_sigrec_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE promoteur_sigrec_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.promoteur_sigrec_id_seq OWNER TO eclipse;

--
-- TOC entry 3329 (class 0 OID 0)
-- Dependencies: 2041
-- Name: promoteur_sigrec_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE promoteur_sigrec_id_seq OWNED BY promoteur_sigrec.id;


--
-- TOC entry 3330 (class 0 OID 0)
-- Dependencies: 2041
-- Name: promoteur_sigrec_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('promoteur_sigrec_id_seq', 1, false);


--
-- TOC entry 2042 (class 1259 OID 405470)
-- Dependencies: 2498 6
-- Name: promoteur_suivi; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE promoteur_suivi (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_promoteur bigint NOT NULL,
    version bigint DEFAULT 0
);


ALTER TABLE public.promoteur_suivi OWNER TO eclipse;

--
-- TOC entry 2043 (class 1259 OID 405474)
-- Dependencies: 6 2042
-- Name: promoteur_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE promoteur_suivi_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.promoteur_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 3331 (class 0 OID 0)
-- Dependencies: 2043
-- Name: promoteur_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE promoteur_suivi_id_seq OWNED BY promoteur_suivi.id;


--
-- TOC entry 3332 (class 0 OID 0)
-- Dependencies: 2043
-- Name: promoteur_suivi_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('promoteur_suivi_id_seq', 1, false);


--
-- TOC entry 2044 (class 1259 OID 405476)
-- Dependencies: 2500 6
-- Name: regle_surcout; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE regle_surcout (
    id bigint NOT NULL,
    type character varying(255),
    premiereannee numeric(19,2),
    anneessuivantes numeric(19,2),
    max integer,
    min integer,
    mode character varying(255),
    montant numeric(19,2),
    perimetre character varying(255),
    id_theme bigint,
    id_categorie bigint,
    version bigint DEFAULT 0
);


ALTER TABLE public.regle_surcout OWNER TO eclipse;

--
-- TOC entry 2045 (class 1259 OID 405483)
-- Dependencies: 6 2044
-- Name: regle_surcout_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE regle_surcout_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.regle_surcout_id_seq OWNER TO eclipse;

--
-- TOC entry 3333 (class 0 OID 0)
-- Dependencies: 2045
-- Name: regle_surcout_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE regle_surcout_id_seq OWNED BY regle_surcout.id;


--
-- TOC entry 3334 (class 0 OID 0)
-- Dependencies: 2045
-- Name: regle_surcout_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('regle_surcout_id_seq', 1, false);


--
-- TOC entry 2046 (class 1259 OID 405485)
-- Dependencies: 2502 6
-- Name: retour_patient; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE retour_patient (
    id bigint NOT NULL,
    date timestamp without time zone,
    id_conditionnement bigint NOT NULL,
    id_essai bigint NOT NULL,
    id_patient bigint NOT NULL,
    id_personne bigint NOT NULL,
    id_produit bigint NOT NULL,
    commentaire text,
    quantite integer,
    id_detailstockage bigint NOT NULL,
    numordonnancier integer,
    etat character varying(255),
    type character varying(255),
    dateetat timestamp without time zone,
    commentaireetat text,
    commentaireentame text,
    numtraitement character varying(255),
    numlot character varying(255),
    version bigint DEFAULT 0
);


ALTER TABLE public.retour_patient OWNER TO eclipse;

--
-- TOC entry 2047 (class 1259 OID 405492)
-- Dependencies: 2046 6
-- Name: retour_patient_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE retour_patient_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.retour_patient_id_seq OWNER TO eclipse;

--
-- TOC entry 3335 (class 0 OID 0)
-- Dependencies: 2047
-- Name: retour_patient_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE retour_patient_id_seq OWNED BY retour_patient.id;


--
-- TOC entry 3336 (class 0 OID 0)
-- Dependencies: 2047
-- Name: retour_patient_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('retour_patient_id_seq', 1, false);


--
-- TOC entry 2048 (class 1259 OID 405494)
-- Dependencies: 2504 6
-- Name: sequence; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE sequence (
    id bigint NOT NULL,
    nb_debut integer,
    unite_debut character varying(255),
    description text,
    nb_fin integer,
    unite_fin character varying(255),
    nom character varying(255),
    type character varying(255),
    id_bras_sequence bigint,
    nb_duree integer,
    unite_duree character varying(255),
    version bigint DEFAULT 0
);


ALTER TABLE public.sequence OWNER TO eclipse;

--
-- TOC entry 2049 (class 1259 OID 405501)
-- Dependencies: 2048 6
-- Name: sequence_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE sequence_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.sequence_id_seq OWNER TO eclipse;

--
-- TOC entry 3337 (class 0 OID 0)
-- Dependencies: 2049
-- Name: sequence_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE sequence_id_seq OWNED BY sequence.id;


--
-- TOC entry 3338 (class 0 OID 0)
-- Dependencies: 2049
-- Name: sequence_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('sequence_id_seq', 1, false);


--
-- TOC entry 2050 (class 1259 OID 405503)
-- Dependencies: 2506 6
-- Name: service; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE service (
    id bigint NOT NULL,
    nom character varying(255) NOT NULL,
    id_pole bigint,
    id_site bigint,
    version bigint DEFAULT 0
);


ALTER TABLE public.service OWNER TO eclipse;

--
-- TOC entry 2051 (class 1259 OID 405507)
-- Dependencies: 6 2050
-- Name: service_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE service_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.service_id_seq OWNER TO eclipse;

--
-- TOC entry 3339 (class 0 OID 0)
-- Dependencies: 2051
-- Name: service_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE service_id_seq OWNED BY service.id;


--
-- TOC entry 3340 (class 0 OID 0)
-- Dependencies: 2051
-- Name: service_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('service_id_seq', 1, false);


--
-- TOC entry 2052 (class 1259 OID 405509)
-- Dependencies: 2508 6
-- Name: service_suivi; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE service_suivi (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_service bigint NOT NULL,
    version bigint DEFAULT 0
);


ALTER TABLE public.service_suivi OWNER TO eclipse;

--
-- TOC entry 2053 (class 1259 OID 405513)
-- Dependencies: 6 2052
-- Name: service_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE service_suivi_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.service_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 3341 (class 0 OID 0)
-- Dependencies: 2053
-- Name: service_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE service_suivi_id_seq OWNED BY service_suivi.id;


--
-- TOC entry 3342 (class 0 OID 0)
-- Dependencies: 2053
-- Name: service_suivi_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('service_suivi_id_seq', 1, false);


--
-- TOC entry 2054 (class 1259 OID 405515)
-- Dependencies: 2510 6
-- Name: site; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE site (
    id bigint NOT NULL,
    adresse text,
    code character varying(255) NOT NULL,
    codepostal character varying(255),
    nom character varying(255) NOT NULL,
    ville character varying(255),
    id_etablissement bigint NOT NULL,
    version bigint DEFAULT 0
);


ALTER TABLE public.site OWNER TO eclipse;

--
-- TOC entry 2055 (class 1259 OID 405522)
-- Dependencies: 2054 6
-- Name: site_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE site_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.site_id_seq OWNER TO eclipse;

--
-- TOC entry 3343 (class 0 OID 0)
-- Dependencies: 2055
-- Name: site_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE site_id_seq OWNED BY site.id;


--
-- TOC entry 3344 (class 0 OID 0)
-- Dependencies: 2055
-- Name: site_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('site_id_seq', 1, false);


--
-- TOC entry 2056 (class 1259 OID 405524)
-- Dependencies: 2512 6
-- Name: site_suivi; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE site_suivi (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_site bigint,
    version bigint DEFAULT 0
);


ALTER TABLE public.site_suivi OWNER TO eclipse;

--
-- TOC entry 2057 (class 1259 OID 405528)
-- Dependencies: 2056 6
-- Name: site_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE site_suivi_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.site_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 3345 (class 0 OID 0)
-- Dependencies: 2057
-- Name: site_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE site_suivi_id_seq OWNED BY site_suivi.id;


--
-- TOC entry 3346 (class 0 OID 0)
-- Dependencies: 2057
-- Name: site_suivi_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('site_suivi_id_seq', 1, false);


--
-- TOC entry 2058 (class 1259 OID 405530)
-- Dependencies: 2514 6
-- Name: stockage; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE stockage (
    id bigint NOT NULL,
    conservation character varying(255),
    identifiantenregistreurtemp character varying(255),
    identifiantsondetemp character varying(255),
    identifiantstockage character varying(255),
    nom character varying(255) NOT NULL,
    id_stockage_parent bigint,
    id_pharmacie bigint NOT NULL,
    version bigint DEFAULT 0
);


ALTER TABLE public.stockage OWNER TO eclipse;

--
-- TOC entry 2059 (class 1259 OID 405537)
-- Dependencies: 6 2058
-- Name: stockage_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE stockage_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.stockage_id_seq OWNER TO eclipse;

--
-- TOC entry 3347 (class 0 OID 0)
-- Dependencies: 2059
-- Name: stockage_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE stockage_id_seq OWNED BY stockage.id;


--
-- TOC entry 3348 (class 0 OID 0)
-- Dependencies: 2059
-- Name: stockage_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('stockage_id_seq', 1, false);


--
-- TOC entry 2060 (class 1259 OID 405539)
-- Dependencies: 2516 6
-- Name: theme; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE theme (
    id bigint NOT NULL,
    libelle character varying(255),
    id_grille_modele bigint NOT NULL,
    version bigint DEFAULT 0
);


ALTER TABLE public.theme OWNER TO eclipse;

--
-- TOC entry 2061 (class 1259 OID 405543)
-- Dependencies: 2060 6
-- Name: theme_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE theme_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.theme_id_seq OWNER TO eclipse;

--
-- TOC entry 3349 (class 0 OID 0)
-- Dependencies: 2061
-- Name: theme_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE theme_id_seq OWNED BY theme.id;


--
-- TOC entry 3350 (class 0 OID 0)
-- Dependencies: 2061
-- Name: theme_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('theme_id_seq', 1, false);


--
-- TOC entry 2329 (class 2604 OID 405545)
-- Dependencies: 1852 1851
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE arc_investigateur_sigrec ALTER COLUMN id SET DEFAULT nextval('arc_investigateur_sigrec_id_seq'::regclass);


--
-- TOC entry 2331 (class 2604 OID 405546)
-- Dependencies: 1854 1853
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE arc_promoteur_sigrec ALTER COLUMN id SET DEFAULT nextval('arc_promoteur_sigrec_id_seq'::regclass);


--
-- TOC entry 2333 (class 2604 OID 405547)
-- Dependencies: 1857 1856
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE assurance_sigrec ALTER COLUMN id SET DEFAULT nextval('assurance_sigrec_id_seq'::regclass);


--
-- TOC entry 2335 (class 2604 OID 405548)
-- Dependencies: 1859 1858
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE bras ALTER COLUMN id SET DEFAULT nextval('bras_id_seq'::regclass);


--
-- TOC entry 2337 (class 2604 OID 405549)
-- Dependencies: 1861 1860
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE categorie ALTER COLUMN id SET DEFAULT nextval('categorie_id_seq'::regclass);


--
-- TOC entry 2339 (class 2604 OID 405550)
-- Dependencies: 1863 1862
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE centre_sigrec ALTER COLUMN id SET DEFAULT nextval('centre_sigrec_id_seq'::regclass);


--
-- TOC entry 2341 (class 2604 OID 405551)
-- Dependencies: 1865 1864
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE co_investigateur_sigrec ALTER COLUMN id SET DEFAULT nextval('co_investigateur_sigrec_id_seq'::regclass);


--
-- TOC entry 2343 (class 2604 OID 405552)
-- Dependencies: 1867 1866
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE conditionnement ALTER COLUMN id SET DEFAULT nextval('conditionnement_id_seq'::regclass);


--
-- TOC entry 2345 (class 2604 OID 405553)
-- Dependencies: 1869 1868
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE contact_sigrec ALTER COLUMN id SET DEFAULT nextval('contact_sigrec_id_seq'::regclass);


--
-- TOC entry 2347 (class 2604 OID 405554)
-- Dependencies: 1871 1870
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE cro_sigrec ALTER COLUMN id SET DEFAULT nextval('cro_sigrec_id_seq'::regclass);


--
-- TOC entry 2349 (class 2604 OID 405555)
-- Dependencies: 1873 1872
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE dispensation ALTER COLUMN id SET DEFAULT nextval('dispensation_id_seq'::regclass);


--
-- TOC entry 2351 (class 2604 OID 405556)
-- Dependencies: 1876 1875
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE donnees_prevision ALTER COLUMN id SET DEFAULT nextval('donnees_prevision_id_seq'::regclass);


--
-- TOC entry 2353 (class 2604 OID 405557)
-- Dependencies: 1878 1877
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE dotation ALTER COLUMN id SET DEFAULT nextval('dotation_id_seq'::regclass);


--
-- TOC entry 2355 (class 2604 OID 405558)
-- Dependencies: 1880 1879
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE element_to_check ALTER COLUMN id SET DEFAULT nextval('element_to_check_id_seq'::regclass);


--
-- TOC entry 2357 (class 2604 OID 405559)
-- Dependencies: 1943 1881
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai ALTER COLUMN id SET DEFAULT nextval('essai_id_seq'::regclass);


--
-- TOC entry 2359 (class 2604 OID 405560)
-- Dependencies: 1883 1882
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_commentaire_detail_administratif_archi ALTER COLUMN id SET DEFAULT nextval('essai_commentaire_detail_administratif_archi_id_seq'::regclass);


--
-- TOC entry 2361 (class 2604 OID 405561)
-- Dependencies: 1885 1884
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_commentaire_detail_faisabilite ALTER COLUMN id SET DEFAULT nextval('essai_commentaire_detail_faisabilite_id_seq'::regclass);


--
-- TOC entry 2363 (class 2604 OID 405562)
-- Dependencies: 1887 1886
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_commentaire_detail_recherche ALTER COLUMN id SET DEFAULT nextval('essai_commentaire_detail_recherche_id_seq'::regclass);


--
-- TOC entry 2365 (class 2604 OID 405563)
-- Dependencies: 1889 1888
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_administratif ALTER COLUMN id SET DEFAULT nextval('essai_detail_administratif_id_seq'::regclass);


--
-- TOC entry 2367 (class 2604 OID 405564)
-- Dependencies: 1891 1890
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_administratif_suivi ALTER COLUMN id SET DEFAULT nextval('essai_detail_administratif_suivi_id_seq'::regclass);


--
-- TOC entry 2369 (class 2604 OID 405565)
-- Dependencies: 1893 1892
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_autres_documents ALTER COLUMN id SET DEFAULT nextval('essai_detail_autres_documents_id_seq'::regclass);


--
-- TOC entry 2371 (class 2604 OID 405566)
-- Dependencies: 1895 1894
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_autres_documents_suivi ALTER COLUMN id SET DEFAULT nextval('essai_detail_autres_documents_suivi_id_seq'::regclass);


--
-- TOC entry 2373 (class 2604 OID 405567)
-- Dependencies: 1897 1896
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_contacts ALTER COLUMN id SET DEFAULT nextval('essai_detail_contacts_id_seq'::regclass);


--
-- TOC entry 2375 (class 2604 OID 405568)
-- Dependencies: 1899 1898
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_contacts_suivi ALTER COLUMN id SET DEFAULT nextval('essai_detail_contacts_suivi_id_seq'::regclass);


--
-- TOC entry 2377 (class 2604 OID 405569)
-- Dependencies: 1901 1900
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_dates ALTER COLUMN id SET DEFAULT nextval('essai_detail_dates_id_seq'::regclass);


--
-- TOC entry 2379 (class 2604 OID 405570)
-- Dependencies: 1903 1902
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_dates_suivi ALTER COLUMN id SET DEFAULT nextval('essai_detail_dates_suivi_id_seq'::regclass);


--
-- TOC entry 2381 (class 2604 OID 405571)
-- Dependencies: 1905 1904
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_design ALTER COLUMN id SET DEFAULT nextval('essai_detail_design_id_seq'::regclass);


--
-- TOC entry 2383 (class 2604 OID 405572)
-- Dependencies: 1907 1906
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_design_suivi ALTER COLUMN id SET DEFAULT nextval('essai_detail_design_suivi_id_seq'::regclass);


--
-- TOC entry 2385 (class 2604 OID 405573)
-- Dependencies: 1909 1908
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_etat ALTER COLUMN id SET DEFAULT nextval('essai_detail_etat_id_seq'::regclass);


--
-- TOC entry 2387 (class 2604 OID 405574)
-- Dependencies: 1911 1910
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_faisabilite ALTER COLUMN id SET DEFAULT nextval('essai_detail_faisabilite_id_seq'::regclass);


--
-- TOC entry 2389 (class 2604 OID 405575)
-- Dependencies: 1914 1913
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_faisabilite_suivi ALTER COLUMN id SET DEFAULT nextval('essai_detail_faisabilite_suivi_id_seq'::regclass);


--
-- TOC entry 2391 (class 2604 OID 405576)
-- Dependencies: 1917 1915
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_pharma ALTER COLUMN id SET DEFAULT nextval('essai_detail_pharma_id_seq'::regclass);


--
-- TOC entry 2393 (class 2604 OID 405577)
-- Dependencies: 1920 1919
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_pharma_suivi ALTER COLUMN id SET DEFAULT nextval('essai_detail_pharma_suivi_id_seq'::regclass);


--
-- TOC entry 2395 (class 2604 OID 405578)
-- Dependencies: 1922 1921
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_produit ALTER COLUMN id SET DEFAULT nextval('essai_detail_produit_id_seq'::regclass);


--
-- TOC entry 2397 (class 2604 OID 405579)
-- Dependencies: 1924 1923
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_produit_suivi ALTER COLUMN id SET DEFAULT nextval('essai_detail_produit_suivi_id_seq'::regclass);


--
-- TOC entry 2399 (class 2604 OID 405580)
-- Dependencies: 1926 1925
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_recherche ALTER COLUMN id SET DEFAULT nextval('essai_detail_recherche_id_seq'::regclass);


--
-- TOC entry 2401 (class 2604 OID 405581)
-- Dependencies: 1928 1927
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_recherche_sigrec ALTER COLUMN id SET DEFAULT nextval('essai_detail_recherche_sigrec_id_seq'::regclass);


--
-- TOC entry 2403 (class 2604 OID 405582)
-- Dependencies: 1930 1929
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_recherche_suivi ALTER COLUMN id SET DEFAULT nextval('essai_detail_recherche_suivi_id_seq'::regclass);


--
-- TOC entry 2405 (class 2604 OID 405583)
-- Dependencies: 1932 1931
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_surcout ALTER COLUMN id SET DEFAULT nextval('essai_detail_surcout_id_seq'::regclass);


--
-- TOC entry 2407 (class 2604 OID 405584)
-- Dependencies: 1934 1933
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_surcout_suivi ALTER COLUMN id SET DEFAULT nextval('essai_detail_surcout_suivi_id_seq'::regclass);


--
-- TOC entry 2409 (class 2604 OID 405585)
-- Dependencies: 1936 1935
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_document_detail_administratif ALTER COLUMN id SET DEFAULT nextval('essai_document_detail_administratif_id_seq'::regclass);


--
-- TOC entry 2411 (class 2604 OID 405586)
-- Dependencies: 1938 1937
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_document_detail_autres_documents ALTER COLUMN id SET DEFAULT nextval('essai_document_detail_autres_documents_id_seq'::regclass);


--
-- TOC entry 2413 (class 2604 OID 405587)
-- Dependencies: 1940 1939
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_document_detail_pharma ALTER COLUMN id SET DEFAULT nextval('essai_document_detail_pharma_id_seq'::regclass);


--
-- TOC entry 2415 (class 2604 OID 405588)
-- Dependencies: 1942 1941
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_document_detail_surcout ALTER COLUMN id SET DEFAULT nextval('essai_document_detail_surcout_id_seq'::regclass);


--
-- TOC entry 2417 (class 2604 OID 405589)
-- Dependencies: 1946 1945
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_sigrec ALTER COLUMN id SET DEFAULT nextval('essai_sigrec_id_seq'::regclass);


--
-- TOC entry 2419 (class 2604 OID 405590)
-- Dependencies: 1948 1947
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_suivi ALTER COLUMN id SET DEFAULT nextval('essai_suivi_id_seq'::regclass);


--
-- TOC entry 2421 (class 2604 OID 405591)
-- Dependencies: 1950 1949
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE etablissement ALTER COLUMN id SET DEFAULT nextval('etablissement_id_seq'::regclass);


--
-- TOC entry 2423 (class 2604 OID 405592)
-- Dependencies: 1952 1951
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE etablissement_suivi ALTER COLUMN id SET DEFAULT nextval('etablissement_suivi_id_seq'::regclass);


--
-- TOC entry 2425 (class 2604 OID 405593)
-- Dependencies: 1954 1953
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE evenement ALTER COLUMN id SET DEFAULT nextval('evenement_id_seq'::regclass);


--
-- TOC entry 2427 (class 2604 OID 405594)
-- Dependencies: 1956 1955
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE evenement_suivi ALTER COLUMN id SET DEFAULT nextval('evenement_suivi_id_seq'::regclass);


--
-- TOC entry 2429 (class 2604 OID 405595)
-- Dependencies: 1958 1957
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE grille ALTER COLUMN id SET DEFAULT nextval('grille_id_seq'::regclass);


--
-- TOC entry 2431 (class 2604 OID 405596)
-- Dependencies: 1960 1959
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE grille_modele ALTER COLUMN id SET DEFAULT nextval('grille_modele_id_seq'::regclass);


--
-- TOC entry 2433 (class 2604 OID 405597)
-- Dependencies: 1962 1961
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE habilitation ALTER COLUMN id SET DEFAULT nextval('habilitation_id_seq'::regclass);


--
-- TOC entry 2435 (class 2604 OID 405598)
-- Dependencies: 1964 1963
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE historique_patient ALTER COLUMN id SET DEFAULT nextval('historique_patient_id_seq'::regclass);


--
-- TOC entry 2437 (class 2604 OID 405599)
-- Dependencies: 1966 1965
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE incident ALTER COLUMN id SET DEFAULT nextval('incident_id_seq'::regclass);


--
-- TOC entry 2439 (class 2604 OID 405600)
-- Dependencies: 1968 1967
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE incident_suivi ALTER COLUMN id SET DEFAULT nextval('incident_suivi_id_seq'::regclass);


--
-- TOC entry 2441 (class 2604 OID 405601)
-- Dependencies: 1970 1969
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE inclusion ALTER COLUMN id SET DEFAULT nextval('inclusion_id_seq'::regclass);


--
-- TOC entry 2443 (class 2604 OID 405602)
-- Dependencies: 1973 1972
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE investigateur_sigrec ALTER COLUMN id SET DEFAULT nextval('investigateur_sigrec_id_seq'::regclass);


--
-- TOC entry 2445 (class 2604 OID 405603)
-- Dependencies: 1975 1974
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE item ALTER COLUMN id SET DEFAULT nextval('item_id_seq'::regclass);


--
-- TOC entry 2447 (class 2604 OID 405604)
-- Dependencies: 1978 1977
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE lignestock ALTER COLUMN id SET DEFAULT nextval('lignestock_id_seq'::regclass);


--
-- TOC entry 2449 (class 2604 OID 405605)
-- Dependencies: 1992 1989
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE mvtstock ALTER COLUMN id SET DEFAULT nextval('mvtstock_id_seq'::regclass);


--
-- TOC entry 2451 (class 2604 OID 405606)
-- Dependencies: 1991 1990
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE mvtstock_document ALTER COLUMN id SET DEFAULT nextval('mvtstock_document_id_seq'::regclass);


--
-- TOC entry 2453 (class 2604 OID 405607)
-- Dependencies: 1994 1993
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE ordonnancier_dispensation ALTER COLUMN id SET DEFAULT nextval('ordonnancier_dispensation_id_seq'::regclass);


--
-- TOC entry 2455 (class 2604 OID 405608)
-- Dependencies: 1996 1995
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE ordonnancier_fab_reconst ALTER COLUMN id SET DEFAULT nextval('ordonnancier_fab_reconst_id_seq'::regclass);


--
-- TOC entry 2457 (class 2604 OID 405609)
-- Dependencies: 1998 1997
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE patient ALTER COLUMN id SET DEFAULT nextval('patient_id_seq'::regclass);


--
-- TOC entry 2459 (class 2604 OID 405610)
-- Dependencies: 2000 1999
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE patient_suivi ALTER COLUMN id SET DEFAULT nextval('patient_suivi_id_seq'::regclass);


--
-- TOC entry 2461 (class 2604 OID 405611)
-- Dependencies: 2002 2001
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE personne ALTER COLUMN id SET DEFAULT nextval('personne_id_seq'::regclass);


--
-- TOC entry 2463 (class 2604 OID 405612)
-- Dependencies: 2004 2003
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE personne_suivi ALTER COLUMN id SET DEFAULT nextval('personne_suivi_id_seq'::regclass);


--
-- TOC entry 2467 (class 2604 OID 405613)
-- Dependencies: 2006 2005
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE pharmacie ALTER COLUMN id SET DEFAULT nextval('pharmacie_id_seq'::regclass);


--
-- TOC entry 2469 (class 2604 OID 405614)
-- Dependencies: 2009 2008
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE pharmacie_suivi ALTER COLUMN id SET DEFAULT nextval('pharmacie_suivi_id_seq'::regclass);


--
-- TOC entry 2471 (class 2604 OID 405615)
-- Dependencies: 2011 2010
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE pharmacien_document_pharmacien ALTER COLUMN id SET DEFAULT nextval('pharmacien_document_pharmacien_id_seq'::regclass);


--
-- TOC entry 2473 (class 2604 OID 405616)
-- Dependencies: 2014 2013
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE pole ALTER COLUMN id SET DEFAULT nextval('pole_id_seq'::regclass);


--
-- TOC entry 2475 (class 2604 OID 405617)
-- Dependencies: 2016 2015
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE pole_suivi ALTER COLUMN id SET DEFAULT nextval('pole_suivi_id_seq'::regclass);


--
-- TOC entry 2477 (class 2604 OID 405618)
-- Dependencies: 2019 2018
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE prescription ALTER COLUMN id SET DEFAULT nextval('prescription_id_seq'::regclass);


--
-- TOC entry 2479 (class 2604 OID 405619)
-- Dependencies: 2021 2020
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE prescription_type ALTER COLUMN id SET DEFAULT nextval('prescription_type_id_seq'::regclass);


--
-- TOC entry 2481 (class 2604 OID 405620)
-- Dependencies: 2023 2022
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE prevision_sigrec ALTER COLUMN id SET DEFAULT nextval('prevision_sigrec_id_seq'::regclass);


--
-- TOC entry 2483 (class 2604 OID 405621)
-- Dependencies: 2031 2024
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE produit ALTER COLUMN id SET DEFAULT nextval('produit_id_seq'::regclass);


--
-- TOC entry 2485 (class 2604 OID 405622)
-- Dependencies: 2026 2025
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE produit_detail_logistique ALTER COLUMN id SET DEFAULT nextval('produit_detail_logistique_id_seq'::regclass);


--
-- TOC entry 2487 (class 2604 OID 405623)
-- Dependencies: 2028 2027
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE produit_detail_stockage ALTER COLUMN id SET DEFAULT nextval('produit_detail_stockage_id_seq'::regclass);


--
-- TOC entry 2489 (class 2604 OID 405624)
-- Dependencies: 2030 2029
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE produit_document_actes_pharma ALTER COLUMN id SET DEFAULT nextval('produit_document_actes_pharma_id_seq'::regclass);


--
-- TOC entry 2491 (class 2604 OID 405625)
-- Dependencies: 2033 2032
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE produit_prescrit ALTER COLUMN id SET DEFAULT nextval('produit_prescrit_id_seq'::regclass);


--
-- TOC entry 2493 (class 2604 OID 405626)
-- Dependencies: 2036 2035
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE produit_suivi ALTER COLUMN id SET DEFAULT nextval('produit_suivi_id_seq'::regclass);


--
-- TOC entry 2495 (class 2604 OID 405627)
-- Dependencies: 2039 2038
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE promoteur ALTER COLUMN id SET DEFAULT nextval('promoteur_id_seq'::regclass);


--
-- TOC entry 2497 (class 2604 OID 405628)
-- Dependencies: 2041 2040
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE promoteur_sigrec ALTER COLUMN id SET DEFAULT nextval('promoteur_sigrec_id_seq'::regclass);


--
-- TOC entry 2499 (class 2604 OID 405629)
-- Dependencies: 2043 2042
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE promoteur_suivi ALTER COLUMN id SET DEFAULT nextval('promoteur_suivi_id_seq'::regclass);


--
-- TOC entry 2501 (class 2604 OID 405630)
-- Dependencies: 2045 2044
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE regle_surcout ALTER COLUMN id SET DEFAULT nextval('regle_surcout_id_seq'::regclass);


--
-- TOC entry 2503 (class 2604 OID 405631)
-- Dependencies: 2047 2046
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE retour_patient ALTER COLUMN id SET DEFAULT nextval('retour_patient_id_seq'::regclass);


--
-- TOC entry 2505 (class 2604 OID 405632)
-- Dependencies: 2049 2048
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE sequence ALTER COLUMN id SET DEFAULT nextval('sequence_id_seq'::regclass);


--
-- TOC entry 2507 (class 2604 OID 405633)
-- Dependencies: 2051 2050
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE service ALTER COLUMN id SET DEFAULT nextval('service_id_seq'::regclass);


--
-- TOC entry 2509 (class 2604 OID 405634)
-- Dependencies: 2053 2052
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE service_suivi ALTER COLUMN id SET DEFAULT nextval('service_suivi_id_seq'::regclass);


--
-- TOC entry 2511 (class 2604 OID 405635)
-- Dependencies: 2055 2054
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE site ALTER COLUMN id SET DEFAULT nextval('site_id_seq'::regclass);


--
-- TOC entry 2513 (class 2604 OID 405636)
-- Dependencies: 2057 2056
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE site_suivi ALTER COLUMN id SET DEFAULT nextval('site_suivi_id_seq'::regclass);


--
-- TOC entry 2515 (class 2604 OID 405637)
-- Dependencies: 2059 2058
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE stockage ALTER COLUMN id SET DEFAULT nextval('stockage_id_seq'::regclass);


--
-- TOC entry 2517 (class 2604 OID 405638)
-- Dependencies: 2061 2060
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE theme ALTER COLUMN id SET DEFAULT nextval('theme_id_seq'::regclass);


--
-- TOC entry 3041 (class 0 OID 404724)
-- Dependencies: 1851
-- Data for Name: arc_investigateur_sigrec; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3042 (class 0 OID 404733)
-- Dependencies: 1853
-- Data for Name: arc_promoteur_sigrec; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3043 (class 0 OID 404742)
-- Dependencies: 1855
-- Data for Name: arcinvestigateur_service; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3044 (class 0 OID 404745)
-- Dependencies: 1856
-- Data for Name: assurance_sigrec; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3045 (class 0 OID 404751)
-- Dependencies: 1858
-- Data for Name: bras; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3046 (class 0 OID 404760)
-- Dependencies: 1860
-- Data for Name: categorie; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3047 (class 0 OID 404769)
-- Dependencies: 1862
-- Data for Name: centre_sigrec; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3048 (class 0 OID 404778)
-- Dependencies: 1864
-- Data for Name: co_investigateur_sigrec; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3049 (class 0 OID 404787)
-- Dependencies: 1866
-- Data for Name: conditionnement; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3050 (class 0 OID 404796)
-- Dependencies: 1868
-- Data for Name: contact_sigrec; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3051 (class 0 OID 404805)
-- Dependencies: 1870
-- Data for Name: cro_sigrec; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3052 (class 0 OID 404811)
-- Dependencies: 1872
-- Data for Name: dispensation; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3053 (class 0 OID 404820)
-- Dependencies: 1874
-- Data for Name: dispositif_medical; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3054 (class 0 OID 404826)
-- Dependencies: 1875
-- Data for Name: donnees_prevision; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3055 (class 0 OID 404832)
-- Dependencies: 1877
-- Data for Name: dotation; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3056 (class 0 OID 404841)
-- Dependencies: 1879
-- Data for Name: element_to_check; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3057 (class 0 OID 404850)
-- Dependencies: 1881
-- Data for Name: essai; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3058 (class 0 OID 404857)
-- Dependencies: 1882
-- Data for Name: essai_commentaire_detail_administratif_archi; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3059 (class 0 OID 404866)
-- Dependencies: 1884
-- Data for Name: essai_commentaire_detail_faisabilite; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3060 (class 0 OID 404875)
-- Dependencies: 1886
-- Data for Name: essai_commentaire_detail_recherche; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3061 (class 0 OID 404884)
-- Dependencies: 1888
-- Data for Name: essai_detail_administratif; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3062 (class 0 OID 404893)
-- Dependencies: 1890
-- Data for Name: essai_detail_administratif_suivi; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3063 (class 0 OID 404899)
-- Dependencies: 1892
-- Data for Name: essai_detail_autres_documents; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3064 (class 0 OID 404905)
-- Dependencies: 1894
-- Data for Name: essai_detail_autres_documents_suivi; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3065 (class 0 OID 404911)
-- Dependencies: 1896
-- Data for Name: essai_detail_contacts; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3066 (class 0 OID 404917)
-- Dependencies: 1898
-- Data for Name: essai_detail_contacts_suivi; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3067 (class 0 OID 404923)
-- Dependencies: 1900
-- Data for Name: essai_detail_dates; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3068 (class 0 OID 404929)
-- Dependencies: 1902
-- Data for Name: essai_detail_dates_suivi; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3069 (class 0 OID 404935)
-- Dependencies: 1904
-- Data for Name: essai_detail_design; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3070 (class 0 OID 404941)
-- Dependencies: 1906
-- Data for Name: essai_detail_design_suivi; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3071 (class 0 OID 404947)
-- Dependencies: 1908
-- Data for Name: essai_detail_etat; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3072 (class 0 OID 404956)
-- Dependencies: 1910
-- Data for Name: essai_detail_faisabilite; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3073 (class 0 OID 404962)
-- Dependencies: 1912
-- Data for Name: essai_detail_faisabilite_service; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3074 (class 0 OID 404965)
-- Dependencies: 1913
-- Data for Name: essai_detail_faisabilite_suivi; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3075 (class 0 OID 404971)
-- Dependencies: 1915
-- Data for Name: essai_detail_pharma; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3076 (class 0 OID 404978)
-- Dependencies: 1916
-- Data for Name: essai_detail_pharma_etablissement; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3077 (class 0 OID 404983)
-- Dependencies: 1918
-- Data for Name: essai_detail_pharma_pharmacie; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3078 (class 0 OID 404986)
-- Dependencies: 1919
-- Data for Name: essai_detail_pharma_suivi; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3079 (class 0 OID 404992)
-- Dependencies: 1921
-- Data for Name: essai_detail_produit; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3080 (class 0 OID 404998)
-- Dependencies: 1923
-- Data for Name: essai_detail_produit_suivi; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3081 (class 0 OID 405004)
-- Dependencies: 1925
-- Data for Name: essai_detail_recherche; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3082 (class 0 OID 405013)
-- Dependencies: 1927
-- Data for Name: essai_detail_recherche_sigrec; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3083 (class 0 OID 405022)
-- Dependencies: 1929
-- Data for Name: essai_detail_recherche_suivi; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3084 (class 0 OID 405028)
-- Dependencies: 1931
-- Data for Name: essai_detail_surcout; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3085 (class 0 OID 405034)
-- Dependencies: 1933
-- Data for Name: essai_detail_surcout_suivi; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3086 (class 0 OID 405040)
-- Dependencies: 1935
-- Data for Name: essai_document_detail_administratif; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3087 (class 0 OID 405049)
-- Dependencies: 1937
-- Data for Name: essai_document_detail_autres_documents; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3088 (class 0 OID 405058)
-- Dependencies: 1939
-- Data for Name: essai_document_detail_pharma; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3089 (class 0 OID 405067)
-- Dependencies: 1941
-- Data for Name: essai_document_detail_surcout; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3090 (class 0 OID 405078)
-- Dependencies: 1944
-- Data for Name: essai_service; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3091 (class 0 OID 405081)
-- Dependencies: 1945
-- Data for Name: essai_sigrec; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3092 (class 0 OID 405090)
-- Dependencies: 1947
-- Data for Name: essai_suivi; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3093 (class 0 OID 405096)
-- Dependencies: 1949
-- Data for Name: etablissement; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3094 (class 0 OID 405105)
-- Dependencies: 1951
-- Data for Name: etablissement_suivi; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3095 (class 0 OID 405111)
-- Dependencies: 1953
-- Data for Name: evenement; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3096 (class 0 OID 405120)
-- Dependencies: 1955
-- Data for Name: evenement_suivi; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3097 (class 0 OID 405126)
-- Dependencies: 1957
-- Data for Name: grille; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3098 (class 0 OID 405132)
-- Dependencies: 1959
-- Data for Name: grille_modele; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3099 (class 0 OID 405138)
-- Dependencies: 1961
-- Data for Name: habilitation; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3100 (class 0 OID 405147)
-- Dependencies: 1963
-- Data for Name: historique_patient; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3101 (class 0 OID 405156)
-- Dependencies: 1965
-- Data for Name: incident; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3102 (class 0 OID 405165)
-- Dependencies: 1967
-- Data for Name: incident_suivi; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3103 (class 0 OID 405171)
-- Dependencies: 1969
-- Data for Name: inclusion; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3104 (class 0 OID 405180)
-- Dependencies: 1971
-- Data for Name: investigateur_service; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3105 (class 0 OID 405183)
-- Dependencies: 1972
-- Data for Name: investigateur_sigrec; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3106 (class 0 OID 405192)
-- Dependencies: 1974
-- Data for Name: item; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3107 (class 0 OID 405201)
-- Dependencies: 1976
-- Data for Name: item_regle; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3108 (class 0 OID 405204)
-- Dependencies: 1977
-- Data for Name: lignestock; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3109 (class 0 OID 405213)
-- Dependencies: 1979
-- Data for Name: medicament; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3110 (class 0 OID 405219)
-- Dependencies: 1980
-- Data for Name: mvt_approvisionnement; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3111 (class 0 OID 405225)
-- Dependencies: 1981
-- Data for Name: mvt_autre_sortie; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3112 (class 0 OID 405231)
-- Dependencies: 1982
-- Data for Name: mvt_cession_pui; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3113 (class 0 OID 405237)
-- Dependencies: 1983
-- Data for Name: mvt_destruction; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3114 (class 0 OID 405243)
-- Dependencies: 1984
-- Data for Name: mvt_dispensation; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3115 (class 0 OID 405246)
-- Dependencies: 1985
-- Data for Name: mvt_dispensation_globale; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3116 (class 0 OID 405249)
-- Dependencies: 1986
-- Data for Name: mvt_preparation; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3117 (class 0 OID 405252)
-- Dependencies: 1987
-- Data for Name: mvt_preparationentree; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3118 (class 0 OID 405258)
-- Dependencies: 1988
-- Data for Name: mvt_retour_promoteur; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3119 (class 0 OID 405264)
-- Dependencies: 1989
-- Data for Name: mvtstock; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3120 (class 0 OID 405271)
-- Dependencies: 1990
-- Data for Name: mvtstock_document; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3121 (class 0 OID 405282)
-- Dependencies: 1993
-- Data for Name: ordonnancier_dispensation; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3122 (class 0 OID 405288)
-- Dependencies: 1995
-- Data for Name: ordonnancier_fab_reconst; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3123 (class 0 OID 405294)
-- Dependencies: 1997
-- Data for Name: patient; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3124 (class 0 OID 405303)
-- Dependencies: 1999
-- Data for Name: patient_suivi; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3125 (class 0 OID 405309)
-- Dependencies: 2001
-- Data for Name: personne; Type: TABLE DATA; Schema: public; Owner: eclipse
--

INSERT INTO personne (type, id, adresse, codepostal, fax, isadmin, login, mail, nom, password, prenom, telephone, ville, nomsociete, titre, typepharmacien, id_promoteur, telephoneportable, datearriveeservice, datedepartservice, datevalidationformation, version) VALUES ('PHARMACIEN', 100004, '', '', '', true, 'netapsys', '', 'netapsys', 'ea617e2de44cac984883b76bd81092b6', '', '', '', NULL, NULL, 'TITULAIRE', NULL, '', NULL, NULL, NULL, 0);
INSERT INTO personne (type, id, adresse, codepostal, fax, isadmin, login, mail, nom, password, prenom, telephone, ville, nomsociete, titre, typepharmacien, id_promoteur, telephoneportable, datearriveeservice, datedepartservice, datevalidationformation, version) VALUES ('PHARMACIEN', 1, '', '', '', true, 'administrateur', '', 'administrateur', '8ebff02282df2f7c72f10293ba322d00', 'administrateur', '', '', '', NULL, 'TITULAIRE', NULL, '', NULL, NULL, NULL, 0);


--
-- TOC entry 3126 (class 0 OID 405318)
-- Dependencies: 2003
-- Data for Name: personne_suivi; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3127 (class 0 OID 405324)
-- Dependencies: 2005
-- Data for Name: pharmacie; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3128 (class 0 OID 405335)
-- Dependencies: 2007
-- Data for Name: pharmacie_site; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3129 (class 0 OID 405338)
-- Dependencies: 2008
-- Data for Name: pharmacie_suivi; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3130 (class 0 OID 405344)
-- Dependencies: 2010
-- Data for Name: pharmacien_document_pharmacien; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3131 (class 0 OID 405353)
-- Dependencies: 2012
-- Data for Name: pharmacien_pharmacie; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3132 (class 0 OID 405356)
-- Dependencies: 2013
-- Data for Name: pole; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3133 (class 0 OID 405362)
-- Dependencies: 2015
-- Data for Name: pole_suivi; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3134 (class 0 OID 405368)
-- Dependencies: 2017
-- Data for Name: preparation; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3135 (class 0 OID 405371)
-- Dependencies: 2018
-- Data for Name: prescription; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3136 (class 0 OID 405380)
-- Dependencies: 2020
-- Data for Name: prescription_type; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3137 (class 0 OID 405389)
-- Dependencies: 2022
-- Data for Name: prevision_sigrec; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3138 (class 0 OID 405395)
-- Dependencies: 2024
-- Data for Name: produit; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3139 (class 0 OID 405402)
-- Dependencies: 2025
-- Data for Name: produit_detail_logistique; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3140 (class 0 OID 405408)
-- Dependencies: 2027
-- Data for Name: produit_detail_stockage; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3141 (class 0 OID 405417)
-- Dependencies: 2029
-- Data for Name: produit_document_actes_pharma; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3142 (class 0 OID 405428)
-- Dependencies: 2032
-- Data for Name: produit_prescrit; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3143 (class 0 OID 405437)
-- Dependencies: 2034
-- Data for Name: produit_service; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3144 (class 0 OID 405440)
-- Dependencies: 2035
-- Data for Name: produit_suivi; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3145 (class 0 OID 405446)
-- Dependencies: 2037
-- Data for Name: produit_therapeutique; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3146 (class 0 OID 405452)
-- Dependencies: 2038
-- Data for Name: promoteur; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3147 (class 0 OID 405461)
-- Dependencies: 2040
-- Data for Name: promoteur_sigrec; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3148 (class 0 OID 405470)
-- Dependencies: 2042
-- Data for Name: promoteur_suivi; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3149 (class 0 OID 405476)
-- Dependencies: 2044
-- Data for Name: regle_surcout; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3150 (class 0 OID 405485)
-- Dependencies: 2046
-- Data for Name: retour_patient; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3151 (class 0 OID 405494)
-- Dependencies: 2048
-- Data for Name: sequence; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3152 (class 0 OID 405503)
-- Dependencies: 2050
-- Data for Name: service; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3153 (class 0 OID 405509)
-- Dependencies: 2052
-- Data for Name: service_suivi; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3154 (class 0 OID 405515)
-- Dependencies: 2054
-- Data for Name: site; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3155 (class 0 OID 405524)
-- Dependencies: 2056
-- Data for Name: site_suivi; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3156 (class 0 OID 405530)
-- Dependencies: 2058
-- Data for Name: stockage; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 3157 (class 0 OID 405539)
-- Dependencies: 2060
-- Data for Name: theme; Type: TABLE DATA; Schema: public; Owner: eclipse
--



--
-- TOC entry 2519 (class 2606 OID 405640)
-- Dependencies: 1851 1851
-- Name: arc_investigateur_sigrec_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY arc_investigateur_sigrec
    ADD CONSTRAINT arc_investigateur_sigrec_pkey PRIMARY KEY (id);


--
-- TOC entry 2522 (class 2606 OID 405642)
-- Dependencies: 1853 1853
-- Name: arc_promoteur_sigrec_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY arc_promoteur_sigrec
    ADD CONSTRAINT arc_promoteur_sigrec_pkey PRIMARY KEY (id);


--
-- TOC entry 2525 (class 2606 OID 405644)
-- Dependencies: 1855 1855 1855
-- Name: arcinvestigateur_service_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY arcinvestigateur_service
    ADD CONSTRAINT arcinvestigateur_service_pkey PRIMARY KEY (id_arcinvestigateur, id_service);


--
-- TOC entry 2527 (class 2606 OID 405646)
-- Dependencies: 1856 1856
-- Name: assurance_sigrec_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY assurance_sigrec
    ADD CONSTRAINT assurance_sigrec_pkey PRIMARY KEY (id);


--
-- TOC entry 2530 (class 2606 OID 405648)
-- Dependencies: 1858 1858
-- Name: bras_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY bras
    ADD CONSTRAINT bras_pkey PRIMARY KEY (id);


--
-- TOC entry 2534 (class 2606 OID 405650)
-- Dependencies: 1860 1860
-- Name: categorie_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY categorie
    ADD CONSTRAINT categorie_pkey PRIMARY KEY (id);


--
-- TOC entry 2537 (class 2606 OID 405652)
-- Dependencies: 1862 1862
-- Name: centre_sigrec_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY centre_sigrec
    ADD CONSTRAINT centre_sigrec_pkey PRIMARY KEY (id);


--
-- TOC entry 2540 (class 2606 OID 405654)
-- Dependencies: 1864 1864
-- Name: co_investigateur_sigrec_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY co_investigateur_sigrec
    ADD CONSTRAINT co_investigateur_sigrec_pkey PRIMARY KEY (id);


--
-- TOC entry 2543 (class 2606 OID 405656)
-- Dependencies: 1866 1866
-- Name: conditionnement_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY conditionnement
    ADD CONSTRAINT conditionnement_pkey PRIMARY KEY (id);


--
-- TOC entry 2546 (class 2606 OID 405658)
-- Dependencies: 1868 1868
-- Name: contact_sigrec_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY contact_sigrec
    ADD CONSTRAINT contact_sigrec_pkey PRIMARY KEY (id);


--
-- TOC entry 2548 (class 2606 OID 405660)
-- Dependencies: 1870 1870
-- Name: cro_sigrec_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY cro_sigrec
    ADD CONSTRAINT cro_sigrec_pkey PRIMARY KEY (id);


--
-- TOC entry 2552 (class 2606 OID 405662)
-- Dependencies: 1872 1872
-- Name: dispensation_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY dispensation
    ADD CONSTRAINT dispensation_pkey PRIMARY KEY (id);


--
-- TOC entry 2557 (class 2606 OID 405664)
-- Dependencies: 1874 1874
-- Name: dispositif_medical_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY dispositif_medical
    ADD CONSTRAINT dispositif_medical_pkey PRIMARY KEY (id);


--
-- TOC entry 2559 (class 2606 OID 405666)
-- Dependencies: 1875 1875
-- Name: donnees_prevision_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY donnees_prevision
    ADD CONSTRAINT donnees_prevision_pkey PRIMARY KEY (id);


--
-- TOC entry 2561 (class 2606 OID 405668)
-- Dependencies: 1877 1877
-- Name: dotation_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY dotation
    ADD CONSTRAINT dotation_pkey PRIMARY KEY (id);


--
-- TOC entry 2569 (class 2606 OID 405670)
-- Dependencies: 1879 1879
-- Name: element_to_check_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY element_to_check
    ADD CONSTRAINT element_to_check_pkey PRIMARY KEY (id);


--
-- TOC entry 2579 (class 2606 OID 405672)
-- Dependencies: 1882 1882
-- Name: essai_commentaire_detail_administratif_archi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_commentaire_detail_administratif_archi
    ADD CONSTRAINT essai_commentaire_detail_administratif_archi_pkey PRIMARY KEY (id);


--
-- TOC entry 2582 (class 2606 OID 405674)
-- Dependencies: 1884 1884
-- Name: essai_commentaire_detail_faisabilite_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_commentaire_detail_faisabilite
    ADD CONSTRAINT essai_commentaire_detail_faisabilite_pkey PRIMARY KEY (id);


--
-- TOC entry 2585 (class 2606 OID 405676)
-- Dependencies: 1886 1886
-- Name: essai_commentaire_detail_recherche_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_commentaire_detail_recherche
    ADD CONSTRAINT essai_commentaire_detail_recherche_pkey PRIMARY KEY (id);


--
-- TOC entry 2588 (class 2606 OID 405678)
-- Dependencies: 1888 1888
-- Name: essai_detail_administratif_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_administratif
    ADD CONSTRAINT essai_detail_administratif_pkey PRIMARY KEY (id);


--
-- TOC entry 2590 (class 2606 OID 405680)
-- Dependencies: 1890 1890
-- Name: essai_detail_administratif_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_administratif_suivi
    ADD CONSTRAINT essai_detail_administratif_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2593 (class 2606 OID 405682)
-- Dependencies: 1892 1892
-- Name: essai_detail_autres_documents_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_autres_documents
    ADD CONSTRAINT essai_detail_autres_documents_pkey PRIMARY KEY (id);


--
-- TOC entry 2595 (class 2606 OID 405684)
-- Dependencies: 1894 1894
-- Name: essai_detail_autres_documents_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_autres_documents_suivi
    ADD CONSTRAINT essai_detail_autres_documents_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2598 (class 2606 OID 405686)
-- Dependencies: 1896 1896
-- Name: essai_detail_contacts_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_contacts
    ADD CONSTRAINT essai_detail_contacts_pkey PRIMARY KEY (id);


--
-- TOC entry 2600 (class 2606 OID 405688)
-- Dependencies: 1898 1898
-- Name: essai_detail_contacts_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_contacts_suivi
    ADD CONSTRAINT essai_detail_contacts_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2603 (class 2606 OID 405690)
-- Dependencies: 1900 1900
-- Name: essai_detail_dates_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_dates
    ADD CONSTRAINT essai_detail_dates_pkey PRIMARY KEY (id);


--
-- TOC entry 2605 (class 2606 OID 405692)
-- Dependencies: 1902 1902
-- Name: essai_detail_dates_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_dates_suivi
    ADD CONSTRAINT essai_detail_dates_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2608 (class 2606 OID 405694)
-- Dependencies: 1904 1904
-- Name: essai_detail_design_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_design
    ADD CONSTRAINT essai_detail_design_pkey PRIMARY KEY (id);


--
-- TOC entry 2610 (class 2606 OID 405696)
-- Dependencies: 1906 1906
-- Name: essai_detail_design_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_design_suivi
    ADD CONSTRAINT essai_detail_design_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2613 (class 2606 OID 405698)
-- Dependencies: 1908 1908
-- Name: essai_detail_etat_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_etat
    ADD CONSTRAINT essai_detail_etat_pkey PRIMARY KEY (id);


--
-- TOC entry 2616 (class 2606 OID 405700)
-- Dependencies: 1910 1910
-- Name: essai_detail_faisabilite_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_faisabilite
    ADD CONSTRAINT essai_detail_faisabilite_pkey PRIMARY KEY (id);


--
-- TOC entry 2618 (class 2606 OID 405702)
-- Dependencies: 1912 1912 1912
-- Name: essai_detail_faisabilite_service_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_faisabilite_service
    ADD CONSTRAINT essai_detail_faisabilite_service_pkey PRIMARY KEY (id_essai, id_service);


--
-- TOC entry 2620 (class 2606 OID 405704)
-- Dependencies: 1913 1913
-- Name: essai_detail_faisabilite_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_faisabilite_suivi
    ADD CONSTRAINT essai_detail_faisabilite_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2625 (class 2606 OID 405706)
-- Dependencies: 1916 1916 1916
-- Name: essai_detail_pharma_etablissement_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_pharma_etablissement
    ADD CONSTRAINT essai_detail_pharma_etablissement_pkey PRIMARY KEY (id_detail_pharma, id_etablissement);


--
-- TOC entry 2627 (class 2606 OID 405708)
-- Dependencies: 1918 1918 1918
-- Name: essai_detail_pharma_pharmacie_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_pharma_pharmacie
    ADD CONSTRAINT essai_detail_pharma_pharmacie_pkey PRIMARY KEY (id_detail_pharma, id_pharmacie);


--
-- TOC entry 2623 (class 2606 OID 405710)
-- Dependencies: 1915 1915
-- Name: essai_detail_pharma_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_pharma
    ADD CONSTRAINT essai_detail_pharma_pkey PRIMARY KEY (id);


--
-- TOC entry 2629 (class 2606 OID 405712)
-- Dependencies: 1919 1919
-- Name: essai_detail_pharma_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_pharma_suivi
    ADD CONSTRAINT essai_detail_pharma_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2632 (class 2606 OID 405714)
-- Dependencies: 1921 1921
-- Name: essai_detail_produit_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_produit
    ADD CONSTRAINT essai_detail_produit_pkey PRIMARY KEY (id);


--
-- TOC entry 2634 (class 2606 OID 405716)
-- Dependencies: 1923 1923
-- Name: essai_detail_produit_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_produit_suivi
    ADD CONSTRAINT essai_detail_produit_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2637 (class 2606 OID 405718)
-- Dependencies: 1925 1925
-- Name: essai_detail_recherche_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_recherche
    ADD CONSTRAINT essai_detail_recherche_pkey PRIMARY KEY (id);


--
-- TOC entry 2639 (class 2606 OID 405720)
-- Dependencies: 1927 1927
-- Name: essai_detail_recherche_sigrec_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_recherche_sigrec
    ADD CONSTRAINT essai_detail_recherche_sigrec_pkey PRIMARY KEY (id);


--
-- TOC entry 2641 (class 2606 OID 405722)
-- Dependencies: 1929 1929
-- Name: essai_detail_recherche_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_recherche_suivi
    ADD CONSTRAINT essai_detail_recherche_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2644 (class 2606 OID 405724)
-- Dependencies: 1931 1931
-- Name: essai_detail_surcout_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_surcout
    ADD CONSTRAINT essai_detail_surcout_pkey PRIMARY KEY (id);


--
-- TOC entry 2646 (class 2606 OID 405726)
-- Dependencies: 1933 1933
-- Name: essai_detail_surcout_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_surcout_suivi
    ADD CONSTRAINT essai_detail_surcout_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2649 (class 2606 OID 405728)
-- Dependencies: 1935 1935
-- Name: essai_document_detail_administratif_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_document_detail_administratif
    ADD CONSTRAINT essai_document_detail_administratif_pkey PRIMARY KEY (id);


--
-- TOC entry 2652 (class 2606 OID 405730)
-- Dependencies: 1937 1937
-- Name: essai_document_detail_autres_documents_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_document_detail_autres_documents
    ADD CONSTRAINT essai_document_detail_autres_documents_pkey PRIMARY KEY (id);


--
-- TOC entry 2654 (class 2606 OID 405732)
-- Dependencies: 1939 1939
-- Name: essai_document_detail_pharma_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_document_detail_pharma
    ADD CONSTRAINT essai_document_detail_pharma_pkey PRIMARY KEY (id);


--
-- TOC entry 2656 (class 2606 OID 405734)
-- Dependencies: 1941 1941
-- Name: essai_document_detail_surcout_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_document_detail_surcout
    ADD CONSTRAINT essai_document_detail_surcout_pkey PRIMARY KEY (id);


--
-- TOC entry 2575 (class 2606 OID 405736)
-- Dependencies: 1881 1881
-- Name: essai_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai
    ADD CONSTRAINT essai_pkey PRIMARY KEY (id);


--
-- TOC entry 2659 (class 2606 OID 405738)
-- Dependencies: 1944 1944 1944
-- Name: essai_service_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_service
    ADD CONSTRAINT essai_service_pkey PRIMARY KEY (id_essai, id_service);


--
-- TOC entry 2661 (class 2606 OID 405740)
-- Dependencies: 1945 1945
-- Name: essai_sigrec_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_sigrec
    ADD CONSTRAINT essai_sigrec_pkey PRIMARY KEY (id);


--
-- TOC entry 2665 (class 2606 OID 405742)
-- Dependencies: 1947 1947
-- Name: essai_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_suivi
    ADD CONSTRAINT essai_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2668 (class 2606 OID 405744)
-- Dependencies: 1949 1949
-- Name: etablissement_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY etablissement
    ADD CONSTRAINT etablissement_pkey PRIMARY KEY (id);


--
-- TOC entry 2670 (class 2606 OID 405746)
-- Dependencies: 1951 1951
-- Name: etablissement_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY etablissement_suivi
    ADD CONSTRAINT etablissement_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2673 (class 2606 OID 405748)
-- Dependencies: 1953 1953
-- Name: evenement_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY evenement
    ADD CONSTRAINT evenement_pkey PRIMARY KEY (id);


--
-- TOC entry 2676 (class 2606 OID 405750)
-- Dependencies: 1955 1955
-- Name: evenement_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY evenement_suivi
    ADD CONSTRAINT evenement_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2682 (class 2606 OID 405752)
-- Dependencies: 1959 1959
-- Name: grille_modele_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY grille_modele
    ADD CONSTRAINT grille_modele_pkey PRIMARY KEY (id);


--
-- TOC entry 2679 (class 2606 OID 405754)
-- Dependencies: 1957 1957
-- Name: grille_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY grille
    ADD CONSTRAINT grille_pkey PRIMARY KEY (id);


--
-- TOC entry 2684 (class 2606 OID 405756)
-- Dependencies: 1961 1961
-- Name: habilitation_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY habilitation
    ADD CONSTRAINT habilitation_pkey PRIMARY KEY (id);


--
-- TOC entry 2688 (class 2606 OID 405758)
-- Dependencies: 1963 1963
-- Name: historique_patient_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY historique_patient
    ADD CONSTRAINT historique_patient_pkey PRIMARY KEY (id);


--
-- TOC entry 2692 (class 2606 OID 405760)
-- Dependencies: 1965 1965
-- Name: incident_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY incident
    ADD CONSTRAINT incident_pkey PRIMARY KEY (id);


--
-- TOC entry 2695 (class 2606 OID 405762)
-- Dependencies: 1967 1967
-- Name: incident_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY incident_suivi
    ADD CONSTRAINT incident_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2699 (class 2606 OID 405764)
-- Dependencies: 1969 1969
-- Name: inclusion_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY inclusion
    ADD CONSTRAINT inclusion_pkey PRIMARY KEY (id);


--
-- TOC entry 2701 (class 2606 OID 405766)
-- Dependencies: 1971 1971 1971
-- Name: investigateur_service_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY investigateur_service
    ADD CONSTRAINT investigateur_service_pkey PRIMARY KEY (id_investigateur, id_service);


--
-- TOC entry 2703 (class 2606 OID 405768)
-- Dependencies: 1972 1972
-- Name: investigateur_sigrec_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY investigateur_sigrec
    ADD CONSTRAINT investigateur_sigrec_pkey PRIMARY KEY (id);


--
-- TOC entry 2706 (class 2606 OID 405770)
-- Dependencies: 1974 1974
-- Name: item_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY item
    ADD CONSTRAINT item_pkey PRIMARY KEY (id);


--
-- TOC entry 2708 (class 2606 OID 405772)
-- Dependencies: 1976 1976 1976
-- Name: item_regle_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY item_regle
    ADD CONSTRAINT item_regle_pkey PRIMARY KEY (id_regle, id_item);


--
-- TOC entry 2714 (class 2606 OID 405774)
-- Dependencies: 1977 1977
-- Name: lignestock_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY lignestock
    ADD CONSTRAINT lignestock_pkey PRIMARY KEY (id);


--
-- TOC entry 2716 (class 2606 OID 405776)
-- Dependencies: 1979 1979
-- Name: medicament_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY medicament
    ADD CONSTRAINT medicament_pkey PRIMARY KEY (id);


--
-- TOC entry 2718 (class 2606 OID 405778)
-- Dependencies: 1980 1980
-- Name: mvt_approvisionnement_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY mvt_approvisionnement
    ADD CONSTRAINT mvt_approvisionnement_pkey PRIMARY KEY (id);


--
-- TOC entry 2720 (class 2606 OID 405780)
-- Dependencies: 1981 1981
-- Name: mvt_autre_sortie_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY mvt_autre_sortie
    ADD CONSTRAINT mvt_autre_sortie_pkey PRIMARY KEY (id);


--
-- TOC entry 2722 (class 2606 OID 405782)
-- Dependencies: 1982 1982
-- Name: mvt_cession_pui_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY mvt_cession_pui
    ADD CONSTRAINT mvt_cession_pui_pkey PRIMARY KEY (id);


--
-- TOC entry 2724 (class 2606 OID 405784)
-- Dependencies: 1983 1983
-- Name: mvt_destruction_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY mvt_destruction
    ADD CONSTRAINT mvt_destruction_pkey PRIMARY KEY (id);


--
-- TOC entry 2731 (class 2606 OID 405786)
-- Dependencies: 1985 1985
-- Name: mvt_dispensation_globale_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY mvt_dispensation_globale
    ADD CONSTRAINT mvt_dispensation_globale_pkey PRIMARY KEY (id);


--
-- TOC entry 2728 (class 2606 OID 405788)
-- Dependencies: 1984 1984
-- Name: mvt_dispensation_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY mvt_dispensation
    ADD CONSTRAINT mvt_dispensation_pkey PRIMARY KEY (id);


--
-- TOC entry 2733 (class 2606 OID 405790)
-- Dependencies: 1986 1986
-- Name: mvt_preparation_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY mvt_preparation
    ADD CONSTRAINT mvt_preparation_pkey PRIMARY KEY (id);


--
-- TOC entry 2735 (class 2606 OID 405792)
-- Dependencies: 1987 1987
-- Name: mvt_preparationentree_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY mvt_preparationentree
    ADD CONSTRAINT mvt_preparationentree_pkey PRIMARY KEY (id);


--
-- TOC entry 2737 (class 2606 OID 405794)
-- Dependencies: 1988 1988
-- Name: mvt_retour_promoteur_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY mvt_retour_promoteur
    ADD CONSTRAINT mvt_retour_promoteur_pkey PRIMARY KEY (id);


--
-- TOC entry 2746 (class 2606 OID 405796)
-- Dependencies: 1990 1990
-- Name: mvtstock_document_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY mvtstock_document
    ADD CONSTRAINT mvtstock_document_pkey PRIMARY KEY (id);


--
-- TOC entry 2744 (class 2606 OID 405798)
-- Dependencies: 1989 1989
-- Name: mvtstock_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY mvtstock
    ADD CONSTRAINT mvtstock_pkey PRIMARY KEY (id);


--
-- TOC entry 2749 (class 2606 OID 405800)
-- Dependencies: 1993 1993
-- Name: ordonnancier_dispensation_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY ordonnancier_dispensation
    ADD CONSTRAINT ordonnancier_dispensation_pkey PRIMARY KEY (id);


--
-- TOC entry 2752 (class 2606 OID 405802)
-- Dependencies: 1995 1995
-- Name: ordonnancier_fab_reconst_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY ordonnancier_fab_reconst
    ADD CONSTRAINT ordonnancier_fab_reconst_pkey PRIMARY KEY (id);


--
-- TOC entry 2754 (class 2606 OID 405804)
-- Dependencies: 1997 1997
-- Name: patient_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY patient
    ADD CONSTRAINT patient_pkey PRIMARY KEY (id);


--
-- TOC entry 2757 (class 2606 OID 405806)
-- Dependencies: 1999 1999
-- Name: patient_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY patient_suivi
    ADD CONSTRAINT patient_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2761 (class 2606 OID 405808)
-- Dependencies: 2001 2001
-- Name: personne_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY personne
    ADD CONSTRAINT personne_pkey PRIMARY KEY (id);


--
-- TOC entry 2764 (class 2606 OID 405810)
-- Dependencies: 2003 2003
-- Name: personne_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY personne_suivi
    ADD CONSTRAINT personne_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2767 (class 2606 OID 405812)
-- Dependencies: 2005 2005
-- Name: pharmacie_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY pharmacie
    ADD CONSTRAINT pharmacie_pkey PRIMARY KEY (id);


--
-- TOC entry 2769 (class 2606 OID 405814)
-- Dependencies: 2007 2007 2007
-- Name: pharmacie_site_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY pharmacie_site
    ADD CONSTRAINT pharmacie_site_pkey PRIMARY KEY (id_pharmacie, id_site);


--
-- TOC entry 2772 (class 2606 OID 405816)
-- Dependencies: 2008 2008
-- Name: pharmacie_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY pharmacie_suivi
    ADD CONSTRAINT pharmacie_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2775 (class 2606 OID 405818)
-- Dependencies: 2010 2010
-- Name: pharmacien_document_pharmacien_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY pharmacien_document_pharmacien
    ADD CONSTRAINT pharmacien_document_pharmacien_pkey PRIMARY KEY (id);


--
-- TOC entry 2777 (class 2606 OID 405820)
-- Dependencies: 2012 2012 2012
-- Name: pharmacien_pharmacie_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY pharmacien_pharmacie
    ADD CONSTRAINT pharmacien_pharmacie_pkey PRIMARY KEY (id_pharmacien, id_pharmacie);


--
-- TOC entry 2780 (class 2606 OID 405822)
-- Dependencies: 2013 2013
-- Name: pole_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY pole
    ADD CONSTRAINT pole_pkey PRIMARY KEY (id);


--
-- TOC entry 2783 (class 2606 OID 405824)
-- Dependencies: 2015 2015
-- Name: pole_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY pole_suivi
    ADD CONSTRAINT pole_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2785 (class 2606 OID 405826)
-- Dependencies: 2017 2017
-- Name: preparation_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY preparation
    ADD CONSTRAINT preparation_pkey PRIMARY KEY (id);


--
-- TOC entry 2791 (class 2606 OID 405828)
-- Dependencies: 2018 2018
-- Name: prescription_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY prescription
    ADD CONSTRAINT prescription_pkey PRIMARY KEY (id);


--
-- TOC entry 2796 (class 2606 OID 405830)
-- Dependencies: 2020 2020
-- Name: prescription_type_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY prescription_type
    ADD CONSTRAINT prescription_type_pkey PRIMARY KEY (id);


--
-- TOC entry 2798 (class 2606 OID 405832)
-- Dependencies: 2022 2022
-- Name: prevision_sigrec_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY prevision_sigrec
    ADD CONSTRAINT prevision_sigrec_pkey PRIMARY KEY (id);


--
-- TOC entry 2803 (class 2606 OID 405834)
-- Dependencies: 2025 2025
-- Name: produit_detail_logistique_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY produit_detail_logistique
    ADD CONSTRAINT produit_detail_logistique_pkey PRIMARY KEY (id);


--
-- TOC entry 2808 (class 2606 OID 405836)
-- Dependencies: 2027 2027
-- Name: produit_detail_stockage_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY produit_detail_stockage
    ADD CONSTRAINT produit_detail_stockage_pkey PRIMARY KEY (id);


--
-- TOC entry 2810 (class 2606 OID 405838)
-- Dependencies: 2029 2029
-- Name: produit_document_actes_pharma_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY produit_document_actes_pharma
    ADD CONSTRAINT produit_document_actes_pharma_pkey PRIMARY KEY (id);


--
-- TOC entry 2801 (class 2606 OID 405840)
-- Dependencies: 2024 2024
-- Name: produit_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY produit
    ADD CONSTRAINT produit_pkey PRIMARY KEY (id);


--
-- TOC entry 2815 (class 2606 OID 405842)
-- Dependencies: 2032 2032
-- Name: produit_prescrit_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY produit_prescrit
    ADD CONSTRAINT produit_prescrit_pkey PRIMARY KEY (id);


--
-- TOC entry 2817 (class 2606 OID 405844)
-- Dependencies: 2034 2034 2034
-- Name: produit_service_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY produit_service
    ADD CONSTRAINT produit_service_pkey PRIMARY KEY (id_produit, id_service);


--
-- TOC entry 2820 (class 2606 OID 405846)
-- Dependencies: 2035 2035
-- Name: produit_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY produit_suivi
    ADD CONSTRAINT produit_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2822 (class 2606 OID 405848)
-- Dependencies: 2037 2037
-- Name: produit_therapeutique_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY produit_therapeutique
    ADD CONSTRAINT produit_therapeutique_pkey PRIMARY KEY (id);


--
-- TOC entry 2824 (class 2606 OID 405850)
-- Dependencies: 2038 2038
-- Name: promoteur_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY promoteur
    ADD CONSTRAINT promoteur_pkey PRIMARY KEY (id);


--
-- TOC entry 2827 (class 2606 OID 405852)
-- Dependencies: 2040 2040
-- Name: promoteur_sigrec_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY promoteur_sigrec
    ADD CONSTRAINT promoteur_sigrec_pkey PRIMARY KEY (id);


--
-- TOC entry 2830 (class 2606 OID 405854)
-- Dependencies: 2042 2042
-- Name: promoteur_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY promoteur_suivi
    ADD CONSTRAINT promoteur_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2834 (class 2606 OID 405856)
-- Dependencies: 2044 2044
-- Name: regle_surcout_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY regle_surcout
    ADD CONSTRAINT regle_surcout_pkey PRIMARY KEY (id);


--
-- TOC entry 2842 (class 2606 OID 405858)
-- Dependencies: 2046 2046
-- Name: retour_patient_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY retour_patient
    ADD CONSTRAINT retour_patient_pkey PRIMARY KEY (id);


--
-- TOC entry 2845 (class 2606 OID 405860)
-- Dependencies: 2048 2048
-- Name: sequence_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY sequence
    ADD CONSTRAINT sequence_pkey PRIMARY KEY (id);


--
-- TOC entry 2848 (class 2606 OID 405862)
-- Dependencies: 2050 2050
-- Name: service_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY service
    ADD CONSTRAINT service_pkey PRIMARY KEY (id);


--
-- TOC entry 2851 (class 2606 OID 405864)
-- Dependencies: 2052 2052
-- Name: service_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY service_suivi
    ADD CONSTRAINT service_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2854 (class 2606 OID 405866)
-- Dependencies: 2054 2054
-- Name: site_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY site
    ADD CONSTRAINT site_pkey PRIMARY KEY (id);


--
-- TOC entry 2857 (class 2606 OID 405868)
-- Dependencies: 2056 2056
-- Name: site_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY site_suivi
    ADD CONSTRAINT site_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2861 (class 2606 OID 405870)
-- Dependencies: 2058 2058
-- Name: stockage_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY stockage
    ADD CONSTRAINT stockage_pkey PRIMARY KEY (id);


--
-- TOC entry 2864 (class 2606 OID 405872)
-- Dependencies: 2060 2060
-- Name: theme_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY theme
    ADD CONSTRAINT theme_pkey PRIMARY KEY (id);


--
-- TOC entry 2520 (class 1259 OID 405873)
-- Dependencies: 1851
-- Name: idx_arc_investigateur_essai_sigrec; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_arc_investigateur_essai_sigrec ON arc_investigateur_sigrec USING btree (id_essai);


--
-- TOC entry 2843 (class 1259 OID 405874)
-- Dependencies: 2048
-- Name: idx_bras_sequence; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_bras_sequence ON sequence USING btree (id_bras_sequence);


--
-- TOC entry 2531 (class 1259 OID 405875)
-- Dependencies: 1858
-- Name: idx_brase_parent; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_brase_parent ON bras USING btree (id_bras_parent);


--
-- TOC entry 2831 (class 1259 OID 405876)
-- Dependencies: 2044
-- Name: idx_categorie_regle; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_categorie_regle ON regle_surcout USING btree (id_categorie);


--
-- TOC entry 2541 (class 1259 OID 405877)
-- Dependencies: 1864
-- Name: idx_co_investigateur_essai_sigrec; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_co_investigateur_essai_sigrec ON co_investigateur_sigrec USING btree (id_essai);


--
-- TOC entry 2544 (class 1259 OID 405878)
-- Dependencies: 1866
-- Name: idx_conditionnement_produit; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_conditionnement_produit ON conditionnement USING btree (id_produit);


--
-- TOC entry 2811 (class 1259 OID 405879)
-- Dependencies: 2032
-- Name: idx_conditionnement_produit_prescrit; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_conditionnement_produit_prescrit ON produit_prescrit USING btree (id_conditionnement);


--
-- TOC entry 2835 (class 1259 OID 405880)
-- Dependencies: 2046
-- Name: idx_conditionnement_retourpatient; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_conditionnement_retourpatient ON retour_patient USING btree (id_conditionnement);


--
-- TOC entry 2528 (class 1259 OID 405881)
-- Dependencies: 1856
-- Name: idx_contact_assurance_sigrec; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_contact_assurance_sigrec ON assurance_sigrec USING btree (id_contact);


--
-- TOC entry 2538 (class 1259 OID 405882)
-- Dependencies: 1862
-- Name: idx_contact_centre_sigrec; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_contact_centre_sigrec ON centre_sigrec USING btree (id_contact);


--
-- TOC entry 2549 (class 1259 OID 405883)
-- Dependencies: 1870
-- Name: idx_contact_cro_sigrec; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_contact_cro_sigrec ON cro_sigrec USING btree (id_contact);


--
-- TOC entry 2825 (class 1259 OID 405884)
-- Dependencies: 2040
-- Name: idx_contact_promoteur_sigrec; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_contact_promoteur_sigrec ON promoteur_sigrec USING btree (id_contact);


--
-- TOC entry 2685 (class 1259 OID 405885)
-- Dependencies: 1961
-- Name: idx_detail_contacts_habilitation; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_detail_contacts_habilitation ON habilitation USING btree (id_detail_contacts);


--
-- TOC entry 2532 (class 1259 OID 405886)
-- Dependencies: 1858
-- Name: idx_detail_design_bras; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_detail_design_bras ON bras USING btree (id_detail_design);


--
-- TOC entry 2614 (class 1259 OID 405887)
-- Dependencies: 1908
-- Name: idx_detail_etat_essai; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_detail_etat_essai ON essai_detail_etat USING btree (id_essai);


--
-- TOC entry 2804 (class 1259 OID 405888)
-- Dependencies: 2027
-- Name: idx_detail_stockage_detail_logistique; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_detail_stockage_detail_logistique ON produit_detail_stockage USING btree (id_detail_logistique);


--
-- TOC entry 2836 (class 1259 OID 405889)
-- Dependencies: 2046
-- Name: idx_detailstockage_retourpatient; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_detailstockage_retourpatient ON retour_patient USING btree (id_detailstockage);


--
-- TOC entry 2729 (class 1259 OID 405890)
-- Dependencies: 1985
-- Name: idx_disp_globale_dotation; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_disp_globale_dotation ON mvt_dispensation_globale USING btree (id_dotation);


--
-- TOC entry 2553 (class 1259 OID 405891)
-- Dependencies: 1872
-- Name: idx_disp_ordon; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_disp_ordon ON dispensation USING btree (id_ordonnancier);


--
-- TOC entry 2554 (class 1259 OID 405892)
-- Dependencies: 1872
-- Name: idx_disp_pharmacie; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_disp_pharmacie ON dispensation USING btree (id_pharmacie);


--
-- TOC entry 2725 (class 1259 OID 405893)
-- Dependencies: 1984
-- Name: idx_dispensation_dispensation_produit; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_dispensation_dispensation_produit ON mvt_dispensation USING btree (id_dispensation);


--
-- TOC entry 2570 (class 1259 OID 405894)
-- Dependencies: 1879
-- Name: idx_dispensation_elementtocheck; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_dispensation_elementtocheck ON element_to_check USING btree (id_dispensation);


--
-- TOC entry 2562 (class 1259 OID 405895)
-- Dependencies: 1877
-- Name: idx_dotation_cond; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_dotation_cond ON dotation USING btree (id_conditionnement);


--
-- TOC entry 2563 (class 1259 OID 405896)
-- Dependencies: 1877
-- Name: idx_dotation_essai; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_dotation_essai ON dotation USING btree (id_essai);


--
-- TOC entry 2564 (class 1259 OID 405897)
-- Dependencies: 1877
-- Name: idx_dotation_personne; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_dotation_personne ON dotation USING btree (id_personne);


--
-- TOC entry 2565 (class 1259 OID 405898)
-- Dependencies: 1877
-- Name: idx_dotation_pharma; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_dotation_pharma ON dotation USING btree (id_pharmacie);


--
-- TOC entry 2566 (class 1259 OID 405899)
-- Dependencies: 1877
-- Name: idx_dotation_produit; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_dotation_produit ON dotation USING btree (id_produit);


--
-- TOC entry 2567 (class 1259 OID 405900)
-- Dependencies: 1877
-- Name: idx_dotation_service; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_dotation_service ON dotation USING btree (id_service);


--
-- TOC entry 2571 (class 1259 OID 405901)
-- Dependencies: 1879
-- Name: idx_elementtocheck_ordon; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_elementtocheck_ordon ON element_to_check USING btree (id_ordonnancier);


--
-- TOC entry 2572 (class 1259 OID 405902)
-- Dependencies: 1879
-- Name: idx_eltcheck_personne; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_eltcheck_personne ON element_to_check USING btree (id_personne);


--
-- TOC entry 2580 (class 1259 OID 405903)
-- Dependencies: 1882
-- Name: idx_essai_commentaire_detail_administratif; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_essai_commentaire_detail_administratif ON essai_commentaire_detail_administratif_archi USING btree (id_detailadministratif);


--
-- TOC entry 2583 (class 1259 OID 405904)
-- Dependencies: 1884
-- Name: idx_essai_commentaire_detail_faisabilite; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_essai_commentaire_detail_faisabilite ON essai_commentaire_detail_faisabilite USING btree (id_detailfaisabilite);


--
-- TOC entry 2586 (class 1259 OID 405905)
-- Dependencies: 1886
-- Name: idx_essai_commentaire_detail_recherche; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_essai_commentaire_detail_recherche ON essai_commentaire_detail_recherche USING btree (id_detailrecherche);


--
-- TOC entry 2550 (class 1259 OID 405906)
-- Dependencies: 1870
-- Name: idx_essai_cro_sigrec; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_essai_cro_sigrec ON cro_sigrec USING btree (id_essai);


--
-- TOC entry 2650 (class 1259 OID 405907)
-- Dependencies: 1935
-- Name: idx_essai_document_detail_administratif; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_essai_document_detail_administratif ON essai_document_detail_administratif USING btree (id_detailadministratif);


--
-- TOC entry 2657 (class 1259 OID 405908)
-- Dependencies: 1941
-- Name: idx_essai_document_detail_surcout; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_essai_document_detail_surcout ON essai_document_detail_surcout USING btree (id_detailsurcout);


--
-- TOC entry 2674 (class 1259 OID 405909)
-- Dependencies: 1953
-- Name: idx_essai_evenement; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_essai_evenement ON evenement USING btree (id_essai);


--
-- TOC entry 2690 (class 1259 OID 405910)
-- Dependencies: 1965
-- Name: idx_essai_incident; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_essai_incident ON incident USING btree (id_essai);


--
-- TOC entry 2696 (class 1259 OID 405911)
-- Dependencies: 1969
-- Name: idx_essai_inclusion; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_essai_inclusion ON inclusion USING btree (id_essai);


--
-- TOC entry 2799 (class 1259 OID 405912)
-- Dependencies: 2024
-- Name: idx_essai_produit_detail_produit; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_essai_produit_detail_produit ON produit USING btree (id_detailproduit);


--
-- TOC entry 2837 (class 1259 OID 405913)
-- Dependencies: 2046
-- Name: idx_essai_retourpatient; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_essai_retourpatient ON retour_patient USING btree (id_essai);


--
-- TOC entry 2765 (class 1259 OID 405914)
-- Dependencies: 2005
-- Name: idx_etab_pharma; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_etab_pharma ON pharmacie USING btree (id_etablissement);


--
-- TOC entry 2778 (class 1259 OID 405915)
-- Dependencies: 2013
-- Name: idx_etab_pole; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_etab_pole ON pole USING btree (id_etablissement);


--
-- TOC entry 2852 (class 1259 OID 405916)
-- Dependencies: 2054
-- Name: idx_etab_site; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_etab_site ON site USING btree (id_etablissement);


--
-- TOC entry 2704 (class 1259 OID 405917)
-- Dependencies: 1974
-- Name: idx_grille_item; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_grille_item ON item USING btree (id_grille);


--
-- TOC entry 2680 (class 1259 OID 405918)
-- Dependencies: 1957
-- Name: idx_grille_modele_grille; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_grille_modele_grille ON grille USING btree (id_grille_modele);


--
-- TOC entry 2862 (class 1259 OID 405919)
-- Dependencies: 2060
-- Name: idx_grille_modele_theme; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_grille_modele_theme ON theme USING btree (id_grille_modele);


--
-- TOC entry 2786 (class 1259 OID 405920)
-- Dependencies: 2018
-- Name: idx_inclusion_prescription; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_inclusion_prescription ON prescription USING btree (id_inclusion);


--
-- TOC entry 2787 (class 1259 OID 405921)
-- Dependencies: 2018
-- Name: idx_investigateur_prescription; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_investigateur_prescription ON prescription USING btree (id_investigateur);


--
-- TOC entry 2662 (class 1259 OID 405922)
-- Dependencies: 1945
-- Name: idx_investigateur_principal_essai_sigrec; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_investigateur_principal_essai_sigrec ON essai_sigrec USING btree (id_investigateurprincipal);


--
-- TOC entry 2709 (class 1259 OID 405923)
-- Dependencies: 1977
-- Name: idx_lignestock_conditionnement; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_lignestock_conditionnement ON lignestock USING btree (id_conditionnement);


--
-- TOC entry 2710 (class 1259 OID 405924)
-- Dependencies: 1977
-- Name: idx_lignestock_essai; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_lignestock_essai ON lignestock USING btree (id_essai);


--
-- TOC entry 2711 (class 1259 OID 405925)
-- Dependencies: 1977
-- Name: idx_lignestock_pharmacie; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_lignestock_pharmacie ON lignestock USING btree (id_pharmacie);


--
-- TOC entry 2712 (class 1259 OID 405926)
-- Dependencies: 1977
-- Name: idx_lignestock_produit; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_lignestock_produit ON lignestock USING btree (id_produit);


--
-- TOC entry 2738 (class 1259 OID 405927)
-- Dependencies: 1989
-- Name: idx_mvtstock_conditionnement; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_mvtstock_conditionnement ON mvtstock USING btree (id_conditionnement);


--
-- TOC entry 2739 (class 1259 OID 405928)
-- Dependencies: 1989
-- Name: idx_mvtstock_essai; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_mvtstock_essai ON mvtstock USING btree (id_essai);


--
-- TOC entry 2740 (class 1259 OID 405929)
-- Dependencies: 1989
-- Name: idx_mvtstock_personne; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_mvtstock_personne ON mvtstock USING btree (id_personne);


--
-- TOC entry 2741 (class 1259 OID 405930)
-- Dependencies: 1989
-- Name: idx_mvtstock_pharmacie; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_mvtstock_pharmacie ON mvtstock USING btree (id_pharmacie);


--
-- TOC entry 2742 (class 1259 OID 405931)
-- Dependencies: 1989
-- Name: idx_mvtstock_produit; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_mvtstock_produit ON mvtstock USING btree (id_produit);


--
-- TOC entry 2689 (class 1259 OID 405932)
-- Dependencies: 1963
-- Name: idx_patient_historique_patient; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_patient_historique_patient ON historique_patient USING btree (id_patient);


--
-- TOC entry 2697 (class 1259 OID 405933)
-- Dependencies: 1969
-- Name: idx_patient_inclusion; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_patient_inclusion ON inclusion USING btree (id_patient);


--
-- TOC entry 2838 (class 1259 OID 405934)
-- Dependencies: 2046
-- Name: idx_patient_retourpatient; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_patient_retourpatient ON retour_patient USING btree (id_patient);


--
-- TOC entry 2686 (class 1259 OID 405935)
-- Dependencies: 1961
-- Name: idx_personne_habilitation; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_personne_habilitation ON habilitation USING btree (id_personne);


--
-- TOC entry 2839 (class 1259 OID 405936)
-- Dependencies: 2046
-- Name: idx_personne_retourpatient; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_personne_retourpatient ON retour_patient USING btree (id_personne);


--
-- TOC entry 2576 (class 1259 OID 405937)
-- Dependencies: 1881
-- Name: idx_pharma_essai; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_pharma_essai ON essai USING btree (id_pharma);


--
-- TOC entry 2747 (class 1259 OID 405938)
-- Dependencies: 1993
-- Name: idx_pharma_ordo_disp; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_pharma_ordo_disp ON ordonnancier_dispensation USING btree (id_pharma);


--
-- TOC entry 2750 (class 1259 OID 405939)
-- Dependencies: 1995
-- Name: idx_pharma_ordo_fab_reconst; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_pharma_ordo_fab_reconst ON ordonnancier_fab_reconst USING btree (id_pharma);


--
-- TOC entry 2858 (class 1259 OID 405940)
-- Dependencies: 2058
-- Name: idx_pharmacie_stockage; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_pharmacie_stockage ON stockage USING btree (id_pharmacie);


--
-- TOC entry 2773 (class 1259 OID 405941)
-- Dependencies: 2010
-- Name: idx_pharmacien_document_pharmacien; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_pharmacien_document_pharmacien ON pharmacien_document_pharmacien USING btree (id_pharmacien);


--
-- TOC entry 2846 (class 1259 OID 405942)
-- Dependencies: 2050
-- Name: idx_pole_service; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_pole_service ON service USING btree (id_pole);


--
-- TOC entry 2792 (class 1259 OID 405943)
-- Dependencies: 2020
-- Name: idx_prescription_conditionnement; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_prescription_conditionnement ON prescription_type USING btree (id_conditionnement);


--
-- TOC entry 2555 (class 1259 OID 405944)
-- Dependencies: 1872
-- Name: idx_prescription_dispensation; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_prescription_dispensation ON dispensation USING btree (id_prescription);


--
-- TOC entry 2793 (class 1259 OID 405945)
-- Dependencies: 2020
-- Name: idx_prescription_produit; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_prescription_produit ON prescription_type USING btree (id_produit);


--
-- TOC entry 2812 (class 1259 OID 405946)
-- Dependencies: 2032
-- Name: idx_prescription_produit_prescrit; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_prescription_produit_prescrit ON produit_prescrit USING btree (id_prescription);


--
-- TOC entry 2794 (class 1259 OID 405947)
-- Dependencies: 2020
-- Name: idx_prescription_sequence; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_prescription_sequence ON prescription_type USING btree (id_sequence);


--
-- TOC entry 2805 (class 1259 OID 405948)
-- Dependencies: 2027
-- Name: idx_produit_pharmacie; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_produit_pharmacie ON produit_detail_stockage USING btree (id_pharmacie);


--
-- TOC entry 2726 (class 1259 OID 405949)
-- Dependencies: 1984
-- Name: idx_produit_prescrit_dispensation_produit; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_produit_prescrit_dispensation_produit ON mvt_dispensation USING btree (id_produitprescrit);


--
-- TOC entry 2573 (class 1259 OID 405950)
-- Dependencies: 1879
-- Name: idx_produit_prescrit_elementtocheck; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_produit_prescrit_elementtocheck ON element_to_check USING btree (id_produitprescrit);


--
-- TOC entry 2813 (class 1259 OID 405951)
-- Dependencies: 2032
-- Name: idx_produit_produit_prescrit; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_produit_produit_prescrit ON produit_prescrit USING btree (id_produit);


--
-- TOC entry 2840 (class 1259 OID 405952)
-- Dependencies: 2046
-- Name: idx_produit_retourpatient; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_produit_retourpatient ON retour_patient USING btree (id_produit);


--
-- TOC entry 2806 (class 1259 OID 405953)
-- Dependencies: 2027
-- Name: idx_produit_stockage; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_produit_stockage ON produit_detail_stockage USING btree (id_stockage);


--
-- TOC entry 2758 (class 1259 OID 405954)
-- Dependencies: 2001
-- Name: idx_promo_arcpromo; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_promo_arcpromo ON personne USING btree (id_promoteur);


--
-- TOC entry 2759 (class 1259 OID 405955)
-- Dependencies: 2001
-- Name: idx_promo_contactpromo; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_promo_contactpromo ON personne USING btree (id_promoteur);


--
-- TOC entry 2577 (class 1259 OID 405956)
-- Dependencies: 1881
-- Name: idx_promo_essai; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_promo_essai ON essai USING btree (id_promoteur);


--
-- TOC entry 2663 (class 1259 OID 405957)
-- Dependencies: 1945
-- Name: idx_promo_essai_sigrec; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_promo_essai_sigrec ON essai_sigrec USING btree (id_promoteur);


--
-- TOC entry 2523 (class 1259 OID 405958)
-- Dependencies: 1853
-- Name: idx_promoteur_arc_promoteur_sigrec; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_promoteur_arc_promoteur_sigrec ON arc_promoteur_sigrec USING btree (id_promoteur);


--
-- TOC entry 2788 (class 1259 OID 405959)
-- Dependencies: 2018
-- Name: idx_sequence_prescriptin; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_sequence_prescriptin ON prescription USING btree (id_sequence);


--
-- TOC entry 2789 (class 1259 OID 405960)
-- Dependencies: 2018
-- Name: idx_service_prescription; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_service_prescription ON prescription USING btree (id_service);


--
-- TOC entry 2859 (class 1259 OID 405961)
-- Dependencies: 2058
-- Name: idx_stockage_parent; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_stockage_parent ON stockage USING btree (id_stockage_parent);


--
-- TOC entry 2591 (class 1259 OID 405962)
-- Dependencies: 1890
-- Name: idx_suivi_detail_administratif; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_detail_administratif ON essai_detail_administratif_suivi USING btree (id_detail_administratif);


--
-- TOC entry 2596 (class 1259 OID 405963)
-- Dependencies: 1894
-- Name: idx_suivi_detail_autres_documents; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_detail_autres_documents ON essai_detail_autres_documents_suivi USING btree (id_detail_autres_documents);


--
-- TOC entry 2601 (class 1259 OID 405964)
-- Dependencies: 1898
-- Name: idx_suivi_detail_contacts; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_detail_contacts ON essai_detail_contacts_suivi USING btree (id_detail_contacts);


--
-- TOC entry 2606 (class 1259 OID 405965)
-- Dependencies: 1902
-- Name: idx_suivi_detail_dates; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_detail_dates ON essai_detail_dates_suivi USING btree (id_detail_dates);


--
-- TOC entry 2611 (class 1259 OID 405966)
-- Dependencies: 1906
-- Name: idx_suivi_detail_design; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_detail_design ON essai_detail_design_suivi USING btree (id_detail_design);


--
-- TOC entry 2621 (class 1259 OID 405967)
-- Dependencies: 1913
-- Name: idx_suivi_detail_faisabilite; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_detail_faisabilite ON essai_detail_faisabilite_suivi USING btree (id_detail_faisabilite);


--
-- TOC entry 2630 (class 1259 OID 405968)
-- Dependencies: 1919
-- Name: idx_suivi_detail_pharma; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_detail_pharma ON essai_detail_pharma_suivi USING btree (id_detail_pharma);


--
-- TOC entry 2635 (class 1259 OID 405969)
-- Dependencies: 1923
-- Name: idx_suivi_detail_produit; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_detail_produit ON essai_detail_produit_suivi USING btree (id_detail_produit);


--
-- TOC entry 2642 (class 1259 OID 405970)
-- Dependencies: 1929
-- Name: idx_suivi_detail_recherche; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_detail_recherche ON essai_detail_recherche_suivi USING btree (id_detail_recherche);


--
-- TOC entry 2647 (class 1259 OID 405971)
-- Dependencies: 1933
-- Name: idx_suivi_detail_surcout; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_detail_surcout ON essai_detail_surcout_suivi USING btree (id_detail_surcout);


--
-- TOC entry 2666 (class 1259 OID 405972)
-- Dependencies: 1947
-- Name: idx_suivi_essai; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_essai ON essai_suivi USING btree (id_essai);


--
-- TOC entry 2671 (class 1259 OID 405973)
-- Dependencies: 1951
-- Name: idx_suivi_etablissement; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_etablissement ON etablissement_suivi USING btree (id_etablissement);


--
-- TOC entry 2677 (class 1259 OID 405974)
-- Dependencies: 1955
-- Name: idx_suivi_evenement; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_evenement ON evenement_suivi USING btree (id_evenement);


--
-- TOC entry 2693 (class 1259 OID 405975)
-- Dependencies: 1967
-- Name: idx_suivi_incident; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_incident ON incident_suivi USING btree (id_incident);


--
-- TOC entry 2755 (class 1259 OID 405976)
-- Dependencies: 1999
-- Name: idx_suivi_patient; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_patient ON patient_suivi USING btree (id_patient);


--
-- TOC entry 2762 (class 1259 OID 405977)
-- Dependencies: 2003
-- Name: idx_suivi_personne; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_personne ON personne_suivi USING btree (id_personne);


--
-- TOC entry 2770 (class 1259 OID 405978)
-- Dependencies: 2008
-- Name: idx_suivi_pharmacie; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_pharmacie ON pharmacie_suivi USING btree (id_pharmacie);


--
-- TOC entry 2781 (class 1259 OID 405979)
-- Dependencies: 2015
-- Name: idx_suivi_pole; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_pole ON pole_suivi USING btree (id_pole);


--
-- TOC entry 2818 (class 1259 OID 405980)
-- Dependencies: 2035
-- Name: idx_suivi_produit; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_produit ON produit_suivi USING btree (id_produit);


--
-- TOC entry 2828 (class 1259 OID 405981)
-- Dependencies: 2042
-- Name: idx_suivi_promoteur; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_promoteur ON promoteur_suivi USING btree (id_promoteur);


--
-- TOC entry 2849 (class 1259 OID 405982)
-- Dependencies: 2052
-- Name: idx_suivi_service; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_service ON service_suivi USING btree (id_service);


--
-- TOC entry 2855 (class 1259 OID 405983)
-- Dependencies: 2056
-- Name: idx_suivi_site; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_site ON site_suivi USING btree (id_site);


--
-- TOC entry 2535 (class 1259 OID 405984)
-- Dependencies: 1860
-- Name: idx_theme_categorie; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_theme_categorie ON categorie USING btree (id_theme);


--
-- TOC entry 2832 (class 1259 OID 405985)
-- Dependencies: 2044
-- Name: idx_theme_regle; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_theme_regle ON regle_surcout USING btree (id_theme);


--
-- TOC entry 2942 (class 2606 OID 405986)
-- Dependencies: 2574 1953 1881
-- Name: fk1174a6939fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY evenement
    ADD CONSTRAINT fk1174a6939fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2943 (class 2606 OID 405991)
-- Dependencies: 2760 2001 1953
-- Name: fk1174a698800c21; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY evenement
    ADD CONSTRAINT fk1174a698800c21 FOREIGN KEY (id_personne) REFERENCES personne(id);


--
-- TOC entry 2987 (class 2606 OID 405996)
-- Dependencies: 2766 2005 1995
-- Name: fk12c037c73a903eb7; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY ordonnancier_fab_reconst
    ADD CONSTRAINT fk12c037c73a903eb7 FOREIGN KEY (id_pharma) REFERENCES pharmacie(id);


--
-- TOC entry 2919 (class 2606 OID 406001)
-- Dependencies: 1881 1915 2574
-- Name: fk17e192d939fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_pharma
    ADD CONSTRAINT fk17e192d939fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2961 (class 2606 OID 406006)
-- Dependencies: 1881 1977 2574
-- Name: fk187831f539fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY lignestock
    ADD CONSTRAINT fk187831f539fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2962 (class 2606 OID 406011)
-- Dependencies: 2005 1977 2766
-- Name: fk187831f54de40194; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY lignestock
    ADD CONSTRAINT fk187831f54de40194 FOREIGN KEY (id_pharmacie) REFERENCES pharmacie(id);


--
-- TOC entry 2963 (class 2606 OID 406016)
-- Dependencies: 1866 1977 2542
-- Name: fk187831f59d7535de; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY lignestock
    ADD CONSTRAINT fk187831f59d7535de FOREIGN KEY (id_conditionnement) REFERENCES conditionnement(id);


--
-- TOC entry 2964 (class 2606 OID 406021)
-- Dependencies: 1977 2024 2800
-- Name: fk187831f5a1ddf650; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY lignestock
    ADD CONSTRAINT fk187831f5a1ddf650 FOREIGN KEY (id_produit) REFERENCES produit(id);


--
-- TOC entry 2989 (class 2606 OID 406026)
-- Dependencies: 2001 2038 2823
-- Name: fk1a6a27cc4285b151; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY personne
    ADD CONSTRAINT fk1a6a27cc4285b151 FOREIGN KEY (id_promoteur) REFERENCES promoteur(id);


--
-- TOC entry 3001 (class 2606 OID 406031)
-- Dependencies: 2018 2050 2847
-- Name: fk1b6fa41a1cf65f51; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY prescription
    ADD CONSTRAINT fk1b6fa41a1cf65f51 FOREIGN KEY (id_service) REFERENCES service(id);


--
-- TOC entry 3002 (class 2606 OID 406036)
-- Dependencies: 2018 2048 2844
-- Name: fk1b6fa41a807681fd; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY prescription
    ADD CONSTRAINT fk1b6fa41a807681fd FOREIGN KEY (id_sequence) REFERENCES sequence(id);


--
-- TOC entry 3003 (class 2606 OID 406041)
-- Dependencies: 2018 1969 2698
-- Name: fk1b6fa41adb692012; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY prescription
    ADD CONSTRAINT fk1b6fa41adb692012 FOREIGN KEY (id_inclusion) REFERENCES inclusion(id);


--
-- TOC entry 3004 (class 2606 OID 406046)
-- Dependencies: 2018 2001 2760
-- Name: fk1b6fa41aea08da8f; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY prescription
    ADD CONSTRAINT fk1b6fa41aea08da8f FOREIGN KEY (id_investigateur) REFERENCES personne(id);


--
-- TOC entry 2868 (class 2606 OID 406051)
-- Dependencies: 1853 1868 2545
-- Name: fk1df3b08e91ecd533; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY arc_promoteur_sigrec
    ADD CONSTRAINT fk1df3b08e91ecd533 FOREIGN KEY (id_contact) REFERENCES contact_sigrec(id);


--
-- TOC entry 2869 (class 2606 OID 406056)
-- Dependencies: 1853 2826 2040
-- Name: fk1df3b08ed4112aed; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY arc_promoteur_sigrec
    ADD CONSTRAINT fk1df3b08ed4112aed FOREIGN KEY (id_promoteur) REFERENCES promoteur_sigrec(id);


--
-- TOC entry 2996 (class 2606 OID 406061)
-- Dependencies: 2001 2760 2012
-- Name: fk1eabc02f24482761; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY pharmacien_pharmacie
    ADD CONSTRAINT fk1eabc02f24482761 FOREIGN KEY (id_pharmacien) REFERENCES personne(id);


--
-- TOC entry 2997 (class 2606 OID 406066)
-- Dependencies: 2005 2012 2766
-- Name: fk1eabc02f4de40194; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY pharmacien_pharmacie
    ADD CONSTRAINT fk1eabc02f4de40194 FOREIGN KEY (id_pharmacie) REFERENCES pharmacie(id);


--
-- TOC entry 2932 (class 2606 OID 406071)
-- Dependencies: 1888 2587 1935
-- Name: fk20a01eebb314ca7e; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_document_detail_administratif
    ADD CONSTRAINT fk20a01eebb314ca7e FOREIGN KEY (id_detailadministratif) REFERENCES essai_detail_administratif(id);


--
-- TOC entry 2935 (class 2606 OID 406076)
-- Dependencies: 1931 2643 1941
-- Name: fk24399e3f3233d23a; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_document_detail_surcout
    ADD CONSTRAINT fk24399e3f3233d23a FOREIGN KEY (id_detailsurcout) REFERENCES essai_detail_surcout(id);


--
-- TOC entry 3037 (class 2606 OID 406081)
-- Dependencies: 2054 2056 2853
-- Name: fk2694c8427aad8e07; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY site_suivi
    ADD CONSTRAINT fk2694c8427aad8e07 FOREIGN KEY (id_site) REFERENCES site(id);


--
-- TOC entry 2909 (class 2606 OID 406086)
-- Dependencies: 2597 1896 1898
-- Name: fk2a86a3aa27453d52; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_contacts_suivi
    ADD CONSTRAINT fk2a86a3aa27453d52 FOREIGN KEY (id_detail_contacts) REFERENCES essai_detail_contacts(id);


--
-- TOC entry 2874 (class 2606 OID 406091)
-- Dependencies: 1858 1904 2607
-- Name: fk2e4482387f8764; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY bras
    ADD CONSTRAINT fk2e4482387f8764 FOREIGN KEY (id_detail_design) REFERENCES essai_detail_design(id);


--
-- TOC entry 2875 (class 2606 OID 406096)
-- Dependencies: 1858 1858 2529
-- Name: fk2e44824d844dbc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY bras
    ADD CONSTRAINT fk2e44824d844dbc FOREIGN KEY (id_bras_parent) REFERENCES bras(id);


--
-- TOC entry 2884 (class 2606 OID 406101)
-- Dependencies: 2766 1872 2005
-- Name: fk2eaeffed4de40194; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY dispensation
    ADD CONSTRAINT fk2eaeffed4de40194 FOREIGN KEY (id_pharmacie) REFERENCES pharmacie(id);


--
-- TOC entry 2885 (class 2606 OID 406106)
-- Dependencies: 1872 2790 2018
-- Name: fk2eaeffed87ff1713; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY dispensation
    ADD CONSTRAINT fk2eaeffed87ff1713 FOREIGN KEY (id_prescription) REFERENCES prescription(id);


--
-- TOC entry 2886 (class 2606 OID 406111)
-- Dependencies: 1993 1872 2748
-- Name: fk2eaeffedbabca2b4; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY dispensation
    ADD CONSTRAINT fk2eaeffedbabca2b4 FOREIGN KEY (id_ordonnancier) REFERENCES ordonnancier_dispensation(id);


--
-- TOC entry 2947 (class 2606 OID 406116)
-- Dependencies: 1896 2597 1961
-- Name: fk2fee5dbe27453d52; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY habilitation
    ADD CONSTRAINT fk2fee5dbe27453d52 FOREIGN KEY (id_detail_contacts) REFERENCES essai_detail_contacts(id);


--
-- TOC entry 2948 (class 2606 OID 406121)
-- Dependencies: 1961 2760 2001
-- Name: fk2fee5dbe8800c21; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY habilitation
    ADD CONSTRAINT fk2fee5dbe8800c21 FOREIGN KEY (id_personne) REFERENCES personne(id);


--
-- TOC entry 2958 (class 2606 OID 406126)
-- Dependencies: 1974 2678 1957
-- Name: fk317b13976c82de; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY item
    ADD CONSTRAINT fk317b13976c82de FOREIGN KEY (id_grille) REFERENCES grille(id);


--
-- TOC entry 2912 (class 2606 OID 406131)
-- Dependencies: 1904 1881 2574
-- Name: fk345311a39fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_design
    ADD CONSTRAINT fk345311a39fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2998 (class 2606 OID 406136)
-- Dependencies: 2013 2667 1949
-- Name: fk3497b8cd45a413; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY pole
    ADD CONSTRAINT fk3497b8cd45a413 FOREIGN KEY (id_etablissement) REFERENCES etablissement(id);


--
-- TOC entry 3036 (class 2606 OID 406141)
-- Dependencies: 1949 2054 2667
-- Name: fk35df47cd45a413; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY site
    ADD CONSTRAINT fk35df47cd45a413 FOREIGN KEY (id_etablissement) REFERENCES etablissement(id);


--
-- TOC entry 2887 (class 2606 OID 406146)
-- Dependencies: 2024 1874 2800
-- Name: fk40b816e0ae5cce6; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY dispositif_medical
    ADD CONSTRAINT fk40b816e0ae5cce6 FOREIGN KEY (id) REFERENCES produit(id);


--
-- TOC entry 2977 (class 2606 OID 406151)
-- Dependencies: 1980 1987 2717
-- Name: fk414415ea389dcf45; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvt_preparationentree
    ADD CONSTRAINT fk414415ea389dcf45 FOREIGN KEY (id) REFERENCES mvt_approvisionnement(id);


--
-- TOC entry 2881 (class 2606 OID 406156)
-- Dependencies: 2800 1866 2024
-- Name: fk46e35c70a1ddf650; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY conditionnement
    ADD CONSTRAINT fk46e35c70a1ddf650 FOREIGN KEY (id_produit) REFERENCES produit(id);


--
-- TOC entry 2995 (class 2606 OID 406161)
-- Dependencies: 2010 2001 2760
-- Name: fk47ad8dfd24482761; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY pharmacien_document_pharmacien
    ADD CONSTRAINT fk47ad8dfd24482761 FOREIGN KEY (id_pharmacien) REFERENCES personne(id);


--
-- TOC entry 2906 (class 2606 OID 406166)
-- Dependencies: 1881 1892 2574
-- Name: fk4c82a77539fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_autres_documents
    ADD CONSTRAINT fk4c82a77539fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2994 (class 2606 OID 406171)
-- Dependencies: 2008 2005 2766
-- Name: fk4d5ce8dd4de40194; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY pharmacie_suivi
    ADD CONSTRAINT fk4d5ce8dd4de40194 FOREIGN KEY (id_pharmacie) REFERENCES pharmacie(id);


--
-- TOC entry 2914 (class 2606 OID 406176)
-- Dependencies: 1908 1881 2574
-- Name: fk4e973f7e39fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_etat
    ADD CONSTRAINT fk4e973f7e39fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2889 (class 2606 OID 406181)
-- Dependencies: 1877 2050 2847
-- Name: fk4f489b4c1cf65f51; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY dotation
    ADD CONSTRAINT fk4f489b4c1cf65f51 FOREIGN KEY (id_service) REFERENCES service(id);


--
-- TOC entry 2890 (class 2606 OID 406186)
-- Dependencies: 1877 1881 2574
-- Name: fk4f489b4c39fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY dotation
    ADD CONSTRAINT fk4f489b4c39fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2891 (class 2606 OID 406191)
-- Dependencies: 1877 2005 2766
-- Name: fk4f489b4c4de40194; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY dotation
    ADD CONSTRAINT fk4f489b4c4de40194 FOREIGN KEY (id_pharmacie) REFERENCES pharmacie(id);


--
-- TOC entry 2892 (class 2606 OID 406196)
-- Dependencies: 1877 2001 2760
-- Name: fk4f489b4c8800c21; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY dotation
    ADD CONSTRAINT fk4f489b4c8800c21 FOREIGN KEY (id_personne) REFERENCES personne(id);


--
-- TOC entry 2893 (class 2606 OID 406201)
-- Dependencies: 1877 1866 2542
-- Name: fk4f489b4c9d7535de; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY dotation
    ADD CONSTRAINT fk4f489b4c9d7535de FOREIGN KEY (id_conditionnement) REFERENCES conditionnement(id);


--
-- TOC entry 2894 (class 2606 OID 406206)
-- Dependencies: 1877 2024 2800
-- Name: fk4f489b4ca1ddf650; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY dotation
    ADD CONSTRAINT fk4f489b4ca1ddf650 FOREIGN KEY (id_produit) REFERENCES produit(id);


--
-- TOC entry 3032 (class 2606 OID 406211)
-- Dependencies: 2048 1858 2529
-- Name: fk507077c145975293; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY sequence
    ADD CONSTRAINT fk507077c145975293 FOREIGN KEY (id_bras_sequence) REFERENCES bras(id);


--
-- TOC entry 2950 (class 2606 OID 406216)
-- Dependencies: 1965 1881 2574
-- Name: fk52f44d239fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY incident
    ADD CONSTRAINT fk52f44d239fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2902 (class 2606 OID 406221)
-- Dependencies: 1884 1910 2615
-- Name: fk547c5a9a8897241c; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_commentaire_detail_faisabilite
    ADD CONSTRAINT fk547c5a9a8897241c FOREIGN KEY (id_detailfaisabilite) REFERENCES essai_detail_faisabilite(id);


--
-- TOC entry 3008 (class 2606 OID 406226)
-- Dependencies: 2022 1945 2660
-- Name: fk55375893a81b195e; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY prevision_sigrec
    ADD CONSTRAINT fk55375893a81b195e FOREIGN KEY (id_essai) REFERENCES essai_sigrec(id);


--
-- TOC entry 2915 (class 2606 OID 406231)
-- Dependencies: 1910 1881 2574
-- Name: fk5a8d447539fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_faisabilite
    ADD CONSTRAINT fk5a8d447539fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2951 (class 2606 OID 406236)
-- Dependencies: 2691 1965 1967
-- Name: fk5b30998db77789cb; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY incident_suivi
    ADD CONSTRAINT fk5b30998db77789cb FOREIGN KEY (id_incident) REFERENCES incident(id);


--
-- TOC entry 2878 (class 2606 OID 406241)
-- Dependencies: 1862 1864 2536
-- Name: fk5b85a4f55c631481; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY co_investigateur_sigrec
    ADD CONSTRAINT fk5b85a4f55c631481 FOREIGN KEY (id_centre) REFERENCES centre_sigrec(id);


--
-- TOC entry 2879 (class 2606 OID 406246)
-- Dependencies: 1864 1868 2545
-- Name: fk5b85a4f591ecd533; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY co_investigateur_sigrec
    ADD CONSTRAINT fk5b85a4f591ecd533 FOREIGN KEY (id_contact) REFERENCES contact_sigrec(id);


--
-- TOC entry 2880 (class 2606 OID 406251)
-- Dependencies: 1864 1945 2660
-- Name: fk5b85a4f5a81b195e; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY co_investigateur_sigrec
    ADD CONSTRAINT fk5b85a4f5a81b195e FOREIGN KEY (id_essai) REFERENCES essai_sigrec(id);


--
-- TOC entry 2899 (class 2606 OID 406256)
-- Dependencies: 1881 2005 2766
-- Name: fk5c5486d3a903eb7; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai
    ADD CONSTRAINT fk5c5486d3a903eb7 FOREIGN KEY (id_pharma) REFERENCES pharmacie(id);


--
-- TOC entry 2900 (class 2606 OID 406261)
-- Dependencies: 1881 2038 2823
-- Name: fk5c5486d4285b151; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai
    ADD CONSTRAINT fk5c5486d4285b151 FOREIGN KEY (id_promoteur) REFERENCES promoteur(id);


--
-- TOC entry 2927 (class 2606 OID 406266)
-- Dependencies: 1925 1881 2574
-- Name: fk5c79a91f39fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_recherche
    ADD CONSTRAINT fk5c79a91f39fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2876 (class 2606 OID 406271)
-- Dependencies: 1860 2060 2863
-- Name: fk5d54e13740161942; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY categorie
    ADD CONSTRAINT fk5d54e13740161942 FOREIGN KEY (id_theme) REFERENCES theme(id);


--
-- TOC entry 2877 (class 2606 OID 406276)
-- Dependencies: 1862 1868 2545
-- Name: fk5f43710391ecd533; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY centre_sigrec
    ADD CONSTRAINT fk5f43710391ecd533 FOREIGN KEY (id_contact) REFERENCES contact_sigrec(id);


--
-- TOC entry 2990 (class 2606 OID 406281)
-- Dependencies: 2003 2001 2760
-- Name: fk60fd9078800c21; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY personne_suivi
    ADD CONSTRAINT fk60fd9078800c21 FOREIGN KEY (id_personne) REFERENCES personne(id);


--
-- TOC entry 2979 (class 2606 OID 406286)
-- Dependencies: 1988 1989 2743
-- Name: fk61102bfd3e8f1c97; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvt_retour_promoteur
    ADD CONSTRAINT fk61102bfd3e8f1c97 FOREIGN KEY (id) REFERENCES mvtstock(id);


--
-- TOC entry 3038 (class 2606 OID 406291)
-- Dependencies: 2058 2005 2766
-- Name: fk658922294de40194; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY stockage
    ADD CONSTRAINT fk658922294de40194 FOREIGN KEY (id_pharmacie) REFERENCES pharmacie(id);


--
-- TOC entry 3039 (class 2606 OID 406296)
-- Dependencies: 2058 2058 2860
-- Name: fk65892229b4ed4491; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY stockage
    ADD CONSTRAINT fk65892229b4ed4491 FOREIGN KEY (id_stockage_parent) REFERENCES stockage(id);


--
-- TOC entry 2934 (class 2606 OID 406301)
-- Dependencies: 1939 1915 2622
-- Name: fk66a8bf19d08532d; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_document_detail_pharma
    ADD CONSTRAINT fk66a8bf19d08532d FOREIGN KEY (id_detail_pharma) REFERENCES essai_detail_pharma(id);


--
-- TOC entry 2967 (class 2606 OID 406306)
-- Dependencies: 1981 1989 2743
-- Name: fk67c907da3e8f1c97; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvt_autre_sortie
    ADD CONSTRAINT fk67c907da3e8f1c97 FOREIGN KEY (id) REFERENCES mvtstock(id);


--
-- TOC entry 3040 (class 2606 OID 406311)
-- Dependencies: 2060 1959 2681
-- Name: fk69375c9195ade5f; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY theme
    ADD CONSTRAINT fk69375c9195ade5f FOREIGN KEY (id_grille_modele) REFERENCES grille_modele(id);


--
-- TOC entry 2980 (class 2606 OID 406316)
-- Dependencies: 1989 1881 2574
-- Name: fk697c8a0b39fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvtstock
    ADD CONSTRAINT fk697c8a0b39fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2981 (class 2606 OID 406321)
-- Dependencies: 1989 2005 2766
-- Name: fk697c8a0b4de40194; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvtstock
    ADD CONSTRAINT fk697c8a0b4de40194 FOREIGN KEY (id_pharmacie) REFERENCES pharmacie(id);


--
-- TOC entry 2982 (class 2606 OID 406326)
-- Dependencies: 1989 2001 2760
-- Name: fk697c8a0b8800c21; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvtstock
    ADD CONSTRAINT fk697c8a0b8800c21 FOREIGN KEY (id_personne) REFERENCES personne(id);


--
-- TOC entry 2983 (class 2606 OID 406331)
-- Dependencies: 1989 1866 2542
-- Name: fk697c8a0b9d7535de; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvtstock
    ADD CONSTRAINT fk697c8a0b9d7535de FOREIGN KEY (id_conditionnement) REFERENCES conditionnement(id);


--
-- TOC entry 2984 (class 2606 OID 406336)
-- Dependencies: 1989 2024 2800
-- Name: fk697c8a0ba1ddf650; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvtstock
    ADD CONSTRAINT fk697c8a0ba1ddf650 FOREIGN KEY (id_produit) REFERENCES produit(id);


--
-- TOC entry 2907 (class 2606 OID 406341)
-- Dependencies: 1894 1892 2592
-- Name: fk698a35f0ec2855a; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_autres_documents_suivi
    ADD CONSTRAINT fk698a35f0ec2855a FOREIGN KEY (id_detail_autres_documents) REFERENCES essai_detail_autres_documents(id);


--
-- TOC entry 2929 (class 2606 OID 406346)
-- Dependencies: 1929 1925 2636
-- Name: fk6b5fd01a1cf64d65; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_recherche_suivi
    ADD CONSTRAINT fk6b5fd01a1cf64d65 FOREIGN KEY (id_detail_recherche) REFERENCES essai_detail_recherche(id);


--
-- TOC entry 3023 (class 2606 OID 406351)
-- Dependencies: 2042 2038 2823
-- Name: fk6bdaed84285b151; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY promoteur_suivi
    ADD CONSTRAINT fk6bdaed84285b151 FOREIGN KEY (id_promoteur) REFERENCES promoteur(id);


--
-- TOC entry 3010 (class 2606 OID 406356)
-- Dependencies: 2025 2024 2800
-- Name: fk6e9d2d16a1ddf650; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY produit_detail_logistique
    ADD CONSTRAINT fk6e9d2d16a1ddf650 FOREIGN KEY (id_produit) REFERENCES produit(id);


--
-- TOC entry 2916 (class 2606 OID 406361)
-- Dependencies: 1912 2050 2847
-- Name: fk71236ceb1cf65f51; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_faisabilite_service
    ADD CONSTRAINT fk71236ceb1cf65f51 FOREIGN KEY (id_service) REFERENCES service(id);


--
-- TOC entry 2917 (class 2606 OID 406366)
-- Dependencies: 1912 1910 2615
-- Name: fk71236ceb3607a129; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_faisabilite_service
    ADD CONSTRAINT fk71236ceb3607a129 FOREIGN KEY (id_essai) REFERENCES essai_detail_faisabilite(id);


--
-- TOC entry 2888 (class 2606 OID 406371)
-- Dependencies: 1875 1931 2643
-- Name: fk75589534f5ae6985; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY donnees_prevision
    ADD CONSTRAINT fk75589534f5ae6985 FOREIGN KEY (id_detail_surcout) REFERENCES essai_detail_surcout(id);


--
-- TOC entry 3033 (class 2606 OID 406376)
-- Dependencies: 2050 2013 2779
-- Name: fk7643c6b57aaafee9; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY service
    ADD CONSTRAINT fk7643c6b57aaafee9 FOREIGN KEY (id_pole) REFERENCES pole(id);


--
-- TOC entry 3034 (class 2606 OID 406381)
-- Dependencies: 2050 2054 2853
-- Name: fk7643c6b57aad8e07; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY service
    ADD CONSTRAINT fk7643c6b57aad8e07 FOREIGN KEY (id_site) REFERENCES site(id);


--
-- TOC entry 2965 (class 2606 OID 406386)
-- Dependencies: 1979 2024 2800
-- Name: fk77228d19ae5cce6; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY medicament
    ADD CONSTRAINT fk77228d19ae5cce6 FOREIGN KEY (id) REFERENCES produit(id);


--
-- TOC entry 2918 (class 2606 OID 406391)
-- Dependencies: 1913 1910 2615
-- Name: fk7a6b12f0530f1de7; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_faisabilite_suivi
    ADD CONSTRAINT fk7a6b12f0530f1de7 FOREIGN KEY (id_detail_faisabilite) REFERENCES essai_detail_faisabilite(id);


--
-- TOC entry 2944 (class 2606 OID 406396)
-- Dependencies: 1955 1953 2672
-- Name: fk7c0dd1e41aeddf50; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY evenement_suivi
    ADD CONSTRAINT fk7c0dd1e41aeddf50 FOREIGN KEY (id_evenement) REFERENCES evenement(id);


--
-- TOC entry 3014 (class 2606 OID 406401)
-- Dependencies: 2029 2024 2800
-- Name: fk7c4c166aa1ddf650; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY produit_document_actes_pharma
    ADD CONSTRAINT fk7c4c166aa1ddf650 FOREIGN KEY (id_produit) REFERENCES produit(id);


--
-- TOC entry 2971 (class 2606 OID 406406)
-- Dependencies: 1984 1872 2551
-- Name: fk800c37c11a1781c6; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvt_dispensation
    ADD CONSTRAINT fk800c37c11a1781c6 FOREIGN KEY (id_dispensation) REFERENCES dispensation(id);


--
-- TOC entry 2972 (class 2606 OID 406411)
-- Dependencies: 1984 1989 2743
-- Name: fk800c37c13e8f1c97; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvt_dispensation
    ADD CONSTRAINT fk800c37c13e8f1c97 FOREIGN KEY (id) REFERENCES mvtstock(id);


--
-- TOC entry 2973 (class 2606 OID 406416)
-- Dependencies: 1984 2032 2814
-- Name: fk800c37c16d6ee647; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvt_dispensation
    ADD CONSTRAINT fk800c37c16d6ee647 FOREIGN KEY (id_produitprescrit) REFERENCES produit_prescrit(id);


--
-- TOC entry 2901 (class 2606 OID 406421)
-- Dependencies: 1882 1888 2587
-- Name: fk823d05e4b314ca7e; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_commentaire_detail_administratif_archi
    ADD CONSTRAINT fk823d05e4b314ca7e FOREIGN KEY (id_detailadministratif) REFERENCES essai_detail_administratif(id);


--
-- TOC entry 2954 (class 2606 OID 406426)
-- Dependencies: 1971 2050 2847
-- Name: fk833c86321cf65f51; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY investigateur_service
    ADD CONSTRAINT fk833c86321cf65f51 FOREIGN KEY (id_service) REFERENCES service(id);


--
-- TOC entry 2955 (class 2606 OID 406431)
-- Dependencies: 1971 2001 2760
-- Name: fk833c8632ea08da8f; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY investigateur_service
    ADD CONSTRAINT fk833c8632ea08da8f FOREIGN KEY (id_investigateur) REFERENCES personne(id);


--
-- TOC entry 2910 (class 2606 OID 406436)
-- Dependencies: 1900 1881 2574
-- Name: fk843a3ba939fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_dates
    ADD CONSTRAINT fk843a3ba939fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2999 (class 2606 OID 406441)
-- Dependencies: 2015 2013 2779
-- Name: fk8449a7f37aaafee9; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY pole_suivi
    ADD CONSTRAINT fk8449a7f37aaafee9 FOREIGN KEY (id_pole) REFERENCES pole(id);


--
-- TOC entry 2949 (class 2606 OID 406446)
-- Dependencies: 1963 1997 2753
-- Name: fk8529d883aedb3264; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY historique_patient
    ADD CONSTRAINT fk8529d883aedb3264 FOREIGN KEY (id_patient) REFERENCES patient(id);


--
-- TOC entry 2966 (class 2606 OID 406451)
-- Dependencies: 1980 1989 2743
-- Name: fk869711473e8f1c97; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvt_approvisionnement
    ADD CONSTRAINT fk869711473e8f1c97 FOREIGN KEY (id) REFERENCES mvtstock(id);


--
-- TOC entry 2926 (class 2606 OID 406456)
-- Dependencies: 1923 1921 2631
-- Name: fk8a0ab6487dbf9eef; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_produit_suivi
    ADD CONSTRAINT fk8a0ab6487dbf9eef FOREIGN KEY (id_detail_produit) REFERENCES essai_detail_produit(id);


--
-- TOC entry 2959 (class 2606 OID 406461)
-- Dependencies: 1976 2044 2833
-- Name: fk8b91f4e11622e8a9; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY item_regle
    ADD CONSTRAINT fk8b91f4e11622e8a9 FOREIGN KEY (id_regle) REFERENCES regle_surcout(id);


--
-- TOC entry 2960 (class 2606 OID 406466)
-- Dependencies: 1974 1976 2705
-- Name: fk8b91f4e1e0ff5276; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY item_regle
    ADD CONSTRAINT fk8b91f4e1e0ff5276 FOREIGN KEY (id_item) REFERENCES item(id);


--
-- TOC entry 3021 (class 2606 OID 406471)
-- Dependencies: 2037 2024 2800
-- Name: fk92053556ae5cce6; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY produit_therapeutique
    ADD CONSTRAINT fk92053556ae5cce6 FOREIGN KEY (id) REFERENCES produit(id);


--
-- TOC entry 2920 (class 2606 OID 406476)
-- Dependencies: 1916 1949 2667
-- Name: fk93a102d0cd45a413; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_pharma_etablissement
    ADD CONSTRAINT fk93a102d0cd45a413 FOREIGN KEY (id_etablissement) REFERENCES etablissement(id);


--
-- TOC entry 2921 (class 2606 OID 406481)
-- Dependencies: 1916 1915 2622
-- Name: fk93a102d0d08532d; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_pharma_etablissement
    ADD CONSTRAINT fk93a102d0d08532d FOREIGN KEY (id_detail_pharma) REFERENCES essai_detail_pharma(id);


--
-- TOC entry 2911 (class 2606 OID 406486)
-- Dependencies: 1902 1900 2602
-- Name: fk9506d324a7a1603; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_dates_suivi
    ADD CONSTRAINT fk9506d324a7a1603 FOREIGN KEY (id_detail_dates) REFERENCES essai_detail_dates(id);


--
-- TOC entry 2933 (class 2606 OID 406491)
-- Dependencies: 1937 1892 2592
-- Name: fk966f83b5ec2855a; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_document_detail_autres_documents
    ADD CONSTRAINT fk966f83b5ec2855a FOREIGN KEY (id_detail_autres_documents) REFERENCES essai_detail_autres_documents(id);


--
-- TOC entry 2905 (class 2606 OID 406496)
-- Dependencies: 1890 1888 2587
-- Name: fk98df0126bf757d89; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_administratif_suivi
    ADD CONSTRAINT fk98df0126bf757d89 FOREIGN KEY (id_detail_administratif) REFERENCES essai_detail_administratif(id);


--
-- TOC entry 3035 (class 2606 OID 406501)
-- Dependencies: 2052 2050 2847
-- Name: fk9946e5301cf65f51; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY service_suivi
    ADD CONSTRAINT fk9946e5301cf65f51 FOREIGN KEY (id_service) REFERENCES service(id);


--
-- TOC entry 2930 (class 2606 OID 406506)
-- Dependencies: 1931 1881 2574
-- Name: fk9a1b427f39fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_surcout
    ADD CONSTRAINT fk9a1b427f39fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2903 (class 2606 OID 406511)
-- Dependencies: 1886 1925 2636
-- Name: fk9b1204844dc45cda; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_commentaire_detail_recherche
    ADD CONSTRAINT fk9b1204844dc45cda FOREIGN KEY (id_detailrecherche) REFERENCES essai_detail_recherche(id);


--
-- TOC entry 2931 (class 2606 OID 406516)
-- Dependencies: 1933 1931 2643
-- Name: fk9c00e17af5ae6985; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_surcout_suivi
    ADD CONSTRAINT fk9c00e17af5ae6985 FOREIGN KEY (id_detail_surcout) REFERENCES essai_detail_surcout(id);


--
-- TOC entry 2986 (class 2606 OID 406521)
-- Dependencies: 1993 2005 2766
-- Name: fk9ea891de3a903eb7; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY ordonnancier_dispensation
    ADD CONSTRAINT fk9ea891de3a903eb7 FOREIGN KEY (id_pharma) REFERENCES pharmacie(id);


--
-- TOC entry 2904 (class 2606 OID 406526)
-- Dependencies: 1888 1881 2574
-- Name: fka145932b39fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_administratif
    ADD CONSTRAINT fka145932b39fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2922 (class 2606 OID 406531)
-- Dependencies: 1918 2005 2766
-- Name: fka30ce23c4de40194; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_pharma_pharmacie
    ADD CONSTRAINT fka30ce23c4de40194 FOREIGN KEY (id_pharmacie) REFERENCES pharmacie(id);


--
-- TOC entry 2923 (class 2606 OID 406536)
-- Dependencies: 1918 1915 2622
-- Name: fka30ce23cd08532d; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_pharma_pharmacie
    ADD CONSTRAINT fka30ce23cd08532d FOREIGN KEY (id_detail_pharma) REFERENCES essai_detail_pharma(id);


--
-- TOC entry 2952 (class 2606 OID 406541)
-- Dependencies: 1969 1881 2574
-- Name: fka6cdb91c39fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY inclusion
    ADD CONSTRAINT fka6cdb91c39fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2953 (class 2606 OID 406546)
-- Dependencies: 1969 1997 2753
-- Name: fka6cdb91caedb3264; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY inclusion
    ADD CONSTRAINT fka6cdb91caedb3264 FOREIGN KEY (id_patient) REFERENCES patient(id);


--
-- TOC entry 2956 (class 2606 OID 406551)
-- Dependencies: 1972 1862 2536
-- Name: fka9985b025c631481; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY investigateur_sigrec
    ADD CONSTRAINT fka9985b025c631481 FOREIGN KEY (id_centre) REFERENCES centre_sigrec(id);


--
-- TOC entry 2957 (class 2606 OID 406556)
-- Dependencies: 1972 1868 2545
-- Name: fka9985b0291ecd533; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY investigateur_sigrec
    ADD CONSTRAINT fka9985b0291ecd533 FOREIGN KEY (id_contact) REFERENCES contact_sigrec(id);


--
-- TOC entry 2992 (class 2606 OID 406561)
-- Dependencies: 2007 2005 2766
-- Name: fkafea0d444de40194; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY pharmacie_site
    ADD CONSTRAINT fkafea0d444de40194 FOREIGN KEY (id_pharmacie) REFERENCES pharmacie(id);


--
-- TOC entry 2993 (class 2606 OID 406566)
-- Dependencies: 2007 2054 2853
-- Name: fkafea0d447aad8e07; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY pharmacie_site
    ADD CONSTRAINT fkafea0d447aad8e07 FOREIGN KEY (id_site) REFERENCES site(id);


--
-- TOC entry 2988 (class 2606 OID 406571)
-- Dependencies: 1999 1997 2753
-- Name: fkb01e8d80aedb3264; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY patient_suivi
    ADD CONSTRAINT fkb01e8d80aedb3264 FOREIGN KEY (id_patient) REFERENCES patient(id);


--
-- TOC entry 3000 (class 2606 OID 406576)
-- Dependencies: 2017 2024 2800
-- Name: fkb1982697ae5cce6; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY preparation
    ADD CONSTRAINT fkb1982697ae5cce6 FOREIGN KEY (id) REFERENCES produit(id);


--
-- TOC entry 2945 (class 2606 OID 406581)
-- Dependencies: 1957 1959 2681
-- Name: fkb63afd47195ade5f; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY grille
    ADD CONSTRAINT fkb63afd47195ade5f FOREIGN KEY (id_grille_modele) REFERENCES grille_modele(id);


--
-- TOC entry 2946 (class 2606 OID 406586)
-- Dependencies: 1957 1931 2643
-- Name: fkb63afd47f5ae6985; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY grille
    ADD CONSTRAINT fkb63afd47f5ae6985 FOREIGN KEY (id_detail_surcout) REFERENCES essai_detail_surcout(id);


--
-- TOC entry 2924 (class 2606 OID 406591)
-- Dependencies: 1919 1915 2622
-- Name: fkb8bc0654d08532d; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_pharma_suivi
    ADD CONSTRAINT fkb8bc0654d08532d FOREIGN KEY (id_detail_pharma) REFERENCES essai_detail_pharma(id);


--
-- TOC entry 3005 (class 2606 OID 406596)
-- Dependencies: 2020 2048 2844
-- Name: fkbe86243f807681fd; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY prescription_type
    ADD CONSTRAINT fkbe86243f807681fd FOREIGN KEY (id_sequence) REFERENCES sequence(id);


--
-- TOC entry 3006 (class 2606 OID 406601)
-- Dependencies: 2020 1866 2542
-- Name: fkbe86243f9d7535de; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY prescription_type
    ADD CONSTRAINT fkbe86243f9d7535de FOREIGN KEY (id_conditionnement) REFERENCES conditionnement(id);


--
-- TOC entry 3007 (class 2606 OID 406606)
-- Dependencies: 2020 2024 2800
-- Name: fkbe86243fa1ddf650; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY prescription_type
    ADD CONSTRAINT fkbe86243fa1ddf650 FOREIGN KEY (id_produit) REFERENCES produit(id);


--
-- TOC entry 3018 (class 2606 OID 406611)
-- Dependencies: 2034 2050 2847
-- Name: fkc171821f1cf65f51; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY produit_service
    ADD CONSTRAINT fkc171821f1cf65f51 FOREIGN KEY (id_service) REFERENCES service(id);


--
-- TOC entry 3019 (class 2606 OID 406616)
-- Dependencies: 2034 2024 2800
-- Name: fkc171821fa1ddf650; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY produit_service
    ADD CONSTRAINT fkc171821fa1ddf650 FOREIGN KEY (id_produit) REFERENCES produit(id);


--
-- TOC entry 3020 (class 2606 OID 406621)
-- Dependencies: 2035 2024 2800
-- Name: fkc1e4e524a1ddf650; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY produit_suivi
    ADD CONSTRAINT fkc1e4e524a1ddf650 FOREIGN KEY (id_produit) REFERENCES produit(id);


--
-- TOC entry 2974 (class 2606 OID 406626)
-- Dependencies: 1985 1989 2743
-- Name: fkc343fea43e8f1c97; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvt_dispensation_globale
    ADD CONSTRAINT fkc343fea43e8f1c97 FOREIGN KEY (id) REFERENCES mvtstock(id);


--
-- TOC entry 2975 (class 2606 OID 406631)
-- Dependencies: 1985 1877 2560
-- Name: fkc343fea464b18985; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvt_dispensation_globale
    ADD CONSTRAINT fkc343fea464b18985 FOREIGN KEY (id_dotation) REFERENCES dotation(id);


--
-- TOC entry 3026 (class 2606 OID 406636)
-- Dependencies: 2046 1881 2574
-- Name: fkc38b7dd139fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY retour_patient
    ADD CONSTRAINT fkc38b7dd139fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 3027 (class 2606 OID 406641)
-- Dependencies: 2046 2001 2760
-- Name: fkc38b7dd18800c21; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY retour_patient
    ADD CONSTRAINT fkc38b7dd18800c21 FOREIGN KEY (id_personne) REFERENCES personne(id);


--
-- TOC entry 3028 (class 2606 OID 406646)
-- Dependencies: 2046 1866 2542
-- Name: fkc38b7dd19d7535de; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY retour_patient
    ADD CONSTRAINT fkc38b7dd19d7535de FOREIGN KEY (id_conditionnement) REFERENCES conditionnement(id);


--
-- TOC entry 3029 (class 2606 OID 406651)
-- Dependencies: 2046 2024 2800
-- Name: fkc38b7dd1a1ddf650; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY retour_patient
    ADD CONSTRAINT fkc38b7dd1a1ddf650 FOREIGN KEY (id_produit) REFERENCES produit(id);


--
-- TOC entry 3030 (class 2606 OID 406656)
-- Dependencies: 2046 1997 2753
-- Name: fkc38b7dd1aedb3264; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY retour_patient
    ADD CONSTRAINT fkc38b7dd1aedb3264 FOREIGN KEY (id_patient) REFERENCES patient(id);


--
-- TOC entry 3031 (class 2606 OID 406661)
-- Dependencies: 2046 2027 2807
-- Name: fkc38b7dd1d8bb7cd7; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY retour_patient
    ADD CONSTRAINT fkc38b7dd1d8bb7cd7 FOREIGN KEY (id_detailstockage) REFERENCES produit_detail_stockage(id);


--
-- TOC entry 2941 (class 2606 OID 406666)
-- Dependencies: 1951 1949 2667
-- Name: fkcaf42771cd45a413; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY etablissement_suivi
    ADD CONSTRAINT fkcaf42771cd45a413 FOREIGN KEY (id_etablissement) REFERENCES etablissement(id);


--
-- TOC entry 2940 (class 2606 OID 406671)
-- Dependencies: 1947 1881 2574
-- Name: fkcd5e3ce839fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_suivi
    ADD CONSTRAINT fkcd5e3ce839fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 3015 (class 2606 OID 406676)
-- Dependencies: 2032 2018 2790
-- Name: fkce7075e087ff1713; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY produit_prescrit
    ADD CONSTRAINT fkce7075e087ff1713 FOREIGN KEY (id_prescription) REFERENCES prescription(id);


--
-- TOC entry 3016 (class 2606 OID 406681)
-- Dependencies: 2032 1866 2542
-- Name: fkce7075e09d7535de; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY produit_prescrit
    ADD CONSTRAINT fkce7075e09d7535de FOREIGN KEY (id_conditionnement) REFERENCES conditionnement(id);


--
-- TOC entry 3017 (class 2606 OID 406686)
-- Dependencies: 2032 2024 2800
-- Name: fkce7075e0a1ddf650; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY produit_prescrit
    ADD CONSTRAINT fkce7075e0a1ddf650 FOREIGN KEY (id_produit) REFERENCES produit(id);


--
-- TOC entry 3022 (class 2606 OID 406691)
-- Dependencies: 2040 1868 2545
-- Name: fkd04e1a4191ecd533; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY promoteur_sigrec
    ADD CONSTRAINT fkd04e1a4191ecd533 FOREIGN KEY (id_contact) REFERENCES contact_sigrec(id);


--
-- TOC entry 2985 (class 2606 OID 406696)
-- Dependencies: 1990 1989 2743
-- Name: fkd0e894cf2bee4c2b; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvtstock_document
    ADD CONSTRAINT fkd0e894cf2bee4c2b FOREIGN KEY (id_mvtstock) REFERENCES mvtstock(id);


--
-- TOC entry 3024 (class 2606 OID 406701)
-- Dependencies: 2044 2863 2060
-- Name: fkd387012940161942; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY regle_surcout
    ADD CONSTRAINT fkd387012940161942 FOREIGN KEY (id_theme) REFERENCES theme(id);


--
-- TOC entry 3025 (class 2606 OID 406706)
-- Dependencies: 2044 2533 1860
-- Name: fkd387012961ea981e; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY regle_surcout
    ADD CONSTRAINT fkd387012961ea981e FOREIGN KEY (id_categorie) REFERENCES categorie(id);


--
-- TOC entry 2936 (class 2606 OID 406711)
-- Dependencies: 2847 2050 1944
-- Name: fkd3f3f8e31cf65f51; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_service
    ADD CONSTRAINT fkd3f3f8e31cf65f51 FOREIGN KEY (id_service) REFERENCES service(id);


--
-- TOC entry 2937 (class 2606 OID 406716)
-- Dependencies: 1944 2574 1881
-- Name: fkd3f3f8e339fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_service
    ADD CONSTRAINT fkd3f3f8e339fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2913 (class 2606 OID 406721)
-- Dependencies: 1904 2607 1906
-- Name: fkd4e62fd5387f8764; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_design_suivi
    ADD CONSTRAINT fkd4e62fd5387f8764 FOREIGN KEY (id_detail_design) REFERENCES essai_detail_design(id);


--
-- TOC entry 2872 (class 2606 OID 406726)
-- Dependencies: 1856 1868 2545
-- Name: fkdb9e6f7191ecd533; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY assurance_sigrec
    ADD CONSTRAINT fkdb9e6f7191ecd533 FOREIGN KEY (id_contact) REFERENCES contact_sigrec(id);


--
-- TOC entry 2873 (class 2606 OID 406731)
-- Dependencies: 1856 1945 2660
-- Name: fkdb9e6f71a81b195e; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY assurance_sigrec
    ADD CONSTRAINT fkdb9e6f71a81b195e FOREIGN KEY (id_essai) REFERENCES essai_sigrec(id);


--
-- TOC entry 2938 (class 2606 OID 406736)
-- Dependencies: 1945 1972 2702
-- Name: fkddbf4e314614c469; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_sigrec
    ADD CONSTRAINT fkddbf4e314614c469 FOREIGN KEY (id_investigateurprincipal) REFERENCES investigateur_sigrec(id);


--
-- TOC entry 2939 (class 2606 OID 406741)
-- Dependencies: 2040 1945 2826
-- Name: fkddbf4e31d4112aed; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_sigrec
    ADD CONSTRAINT fkddbf4e31d4112aed FOREIGN KEY (id_promoteur) REFERENCES promoteur_sigrec(id);


--
-- TOC entry 2970 (class 2606 OID 406746)
-- Dependencies: 1983 1989 2743
-- Name: fkdfdef25e3e8f1c97; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvt_destruction
    ADD CONSTRAINT fkdfdef25e3e8f1c97 FOREIGN KEY (id) REFERENCES mvtstock(id);


--
-- TOC entry 2865 (class 2606 OID 406751)
-- Dependencies: 1851 1862 2536
-- Name: fke2c002cf5c631481; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY arc_investigateur_sigrec
    ADD CONSTRAINT fke2c002cf5c631481 FOREIGN KEY (id_centre) REFERENCES centre_sigrec(id);


--
-- TOC entry 2866 (class 2606 OID 406756)
-- Dependencies: 1851 1868 2545
-- Name: fke2c002cf91ecd533; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY arc_investigateur_sigrec
    ADD CONSTRAINT fke2c002cf91ecd533 FOREIGN KEY (id_contact) REFERENCES contact_sigrec(id);


--
-- TOC entry 2867 (class 2606 OID 406761)
-- Dependencies: 1851 1945 2660
-- Name: fke2c002cfa81b195e; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY arc_investigateur_sigrec
    ADD CONSTRAINT fke2c002cfa81b195e FOREIGN KEY (id_essai) REFERENCES essai_sigrec(id);


--
-- TOC entry 2991 (class 2606 OID 406766)
-- Dependencies: 2005 1949 2667
-- Name: fke55d5022cd45a413; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY pharmacie
    ADD CONSTRAINT fke55d5022cd45a413 FOREIGN KEY (id_etablissement) REFERENCES etablissement(id);


--
-- TOC entry 2908 (class 2606 OID 406771)
-- Dependencies: 1896 1881 2574
-- Name: fke7ea68af39fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_contacts
    ADD CONSTRAINT fke7ea68af39fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 3009 (class 2606 OID 406776)
-- Dependencies: 2024 1921 2631
-- Name: fked8dcda9ba4507a4; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY produit
    ADD CONSTRAINT fked8dcda9ba4507a4 FOREIGN KEY (id_detailproduit) REFERENCES essai_detail_produit(id);


--
-- TOC entry 3011 (class 2606 OID 406781)
-- Dependencies: 2027 2005 2766
-- Name: fkef34b7c14de40194; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY produit_detail_stockage
    ADD CONSTRAINT fkef34b7c14de40194 FOREIGN KEY (id_pharmacie) REFERENCES pharmacie(id);


--
-- TOC entry 3012 (class 2606 OID 406786)
-- Dependencies: 2025 2802 2027
-- Name: fkef34b7c1a24a8716; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY produit_detail_stockage
    ADD CONSTRAINT fkef34b7c1a24a8716 FOREIGN KEY (id_detail_logistique) REFERENCES produit_detail_logistique(id);


--
-- TOC entry 3013 (class 2606 OID 406791)
-- Dependencies: 2860 2027 2058
-- Name: fkef34b7c1d78f7902; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY produit_detail_stockage
    ADD CONSTRAINT fkef34b7c1d78f7902 FOREIGN KEY (id_stockage) REFERENCES stockage(id);


--
-- TOC entry 2895 (class 2606 OID 406796)
-- Dependencies: 2551 1879 1872
-- Name: fkf50b7c271a1781c6; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY element_to_check
    ADD CONSTRAINT fkf50b7c271a1781c6 FOREIGN KEY (id_dispensation) REFERENCES dispensation(id);


--
-- TOC entry 2896 (class 2606 OID 406801)
-- Dependencies: 2032 1879 2814
-- Name: fkf50b7c276d6ee647; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY element_to_check
    ADD CONSTRAINT fkf50b7c276d6ee647 FOREIGN KEY (id_produitprescrit) REFERENCES produit_prescrit(id);


--
-- TOC entry 2897 (class 2606 OID 406806)
-- Dependencies: 1879 2760 2001
-- Name: fkf50b7c278800c21; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY element_to_check
    ADD CONSTRAINT fkf50b7c278800c21 FOREIGN KEY (id_personne) REFERENCES personne(id);


--
-- TOC entry 2898 (class 2606 OID 406811)
-- Dependencies: 1879 2751 1995
-- Name: fkf50b7c2797d4f410; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY element_to_check
    ADD CONSTRAINT fkf50b7c2797d4f410 FOREIGN KEY (id_ordonnancier) REFERENCES ordonnancier_fab_reconst(id);


--
-- TOC entry 2882 (class 2606 OID 406816)
-- Dependencies: 1868 2545 1870
-- Name: fkf5281d5e91ecd533; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY cro_sigrec
    ADD CONSTRAINT fkf5281d5e91ecd533 FOREIGN KEY (id_contact) REFERENCES contact_sigrec(id);


--
-- TOC entry 2883 (class 2606 OID 406821)
-- Dependencies: 2660 1870 1945
-- Name: fkf5281d5ea81b195e; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY cro_sigrec
    ADD CONSTRAINT fkf5281d5ea81b195e FOREIGN KEY (id_essai) REFERENCES essai_sigrec(id);


--
-- TOC entry 2925 (class 2606 OID 406826)
-- Dependencies: 1921 1881 2574
-- Name: fkf62049cd39fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_produit
    ADD CONSTRAINT fkf62049cd39fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2870 (class 2606 OID 406831)
-- Dependencies: 1855 2050 2847
-- Name: fkfa113201cf65f51; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY arcinvestigateur_service
    ADD CONSTRAINT fkfa113201cf65f51 FOREIGN KEY (id_service) REFERENCES service(id);


--
-- TOC entry 2871 (class 2606 OID 406836)
-- Dependencies: 2760 1855 2001
-- Name: fkfa11320dde432bd; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY arcinvestigateur_service
    ADD CONSTRAINT fkfa11320dde432bd FOREIGN KEY (id_arcinvestigateur) REFERENCES personne(id);


--
-- TOC entry 2968 (class 2606 OID 406841)
-- Dependencies: 1982 2005 2766
-- Name: fkfd557b77382dd136; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvt_cession_pui
    ADD CONSTRAINT fkfd557b77382dd136 FOREIGN KEY (id_pharmaciedest) REFERENCES pharmacie(id);


--
-- TOC entry 2969 (class 2606 OID 406846)
-- Dependencies: 1982 1989 2743
-- Name: fkfd557b773e8f1c97; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvt_cession_pui
    ADD CONSTRAINT fkfd557b773e8f1c97 FOREIGN KEY (id) REFERENCES mvtstock(id);


--
-- TOC entry 2976 (class 2606 OID 406851)
-- Dependencies: 1989 1986 2743
-- Name: fkfe8aa4433e8f1c97; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvt_preparation
    ADD CONSTRAINT fkfe8aa4433e8f1c97 FOREIGN KEY (id) REFERENCES mvtstock(id);


--
-- TOC entry 2928 (class 2606 OID 406856)
-- Dependencies: 2660 1927 1945
-- Name: fkfff0213fa81b195e; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_recherche_sigrec
    ADD CONSTRAINT fkfff0213fa81b195e FOREIGN KEY (id_essai) REFERENCES essai_sigrec(id);


--
-- TOC entry 2978 (class 2606 OID 406861)
-- Dependencies: 2751 1987 1995
-- Name: mvt_preparationentree_id_ordonnancier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvt_preparationentree
    ADD CONSTRAINT mvt_preparationentree_id_ordonnancier_fkey FOREIGN KEY (id_ordonnancier) REFERENCES ordonnancier_fab_reconst(id);


--
-- TOC entry 3162 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2013-05-28 14:34:47

--
-- PostgreSQL database dump complete
--

