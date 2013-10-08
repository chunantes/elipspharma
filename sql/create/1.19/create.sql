--
-- PostgreSQL database dump
--

-- Started on 2011-11-02 10:29:22

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1846 (class 1259 OID 52663)
-- Dependencies: 6
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
    id_essai bigint
);


ALTER TABLE public.arc_investigateur_sigrec OWNER TO eclipse;

--
-- TOC entry 1847 (class 1259 OID 52669)
-- Dependencies: 1846 6
-- Name: arc_investigateur_sigrec_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE arc_investigateur_sigrec_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.arc_investigateur_sigrec_id_seq OWNER TO eclipse;

--
-- TOC entry 2934 (class 0 OID 0)
-- Dependencies: 1847
-- Name: arc_investigateur_sigrec_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE arc_investigateur_sigrec_id_seq OWNED BY arc_investigateur_sigrec.id;


--
-- TOC entry 1848 (class 1259 OID 52671)
-- Dependencies: 6
-- Name: arc_promoteur_sigrec; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE arc_promoteur_sigrec (
    id bigint NOT NULL,
    identifiant character varying(255),
    intervenantid integer,
    numadeli character varying(255),
    titre character varying(255),
    id_contact bigint,
    id_promoteur bigint
);


ALTER TABLE public.arc_promoteur_sigrec OWNER TO eclipse;

--
-- TOC entry 1849 (class 1259 OID 52677)
-- Dependencies: 1848 6
-- Name: arc_promoteur_sigrec_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE arc_promoteur_sigrec_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.arc_promoteur_sigrec_id_seq OWNER TO eclipse;

--
-- TOC entry 2935 (class 0 OID 0)
-- Dependencies: 1849
-- Name: arc_promoteur_sigrec_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE arc_promoteur_sigrec_id_seq OWNED BY arc_promoteur_sigrec.id;


--
-- TOC entry 1850 (class 1259 OID 52679)
-- Dependencies: 6
-- Name: arcinvestigateur_service; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE arcinvestigateur_service (
    id_arcinvestigateur bigint NOT NULL,
    id_service bigint NOT NULL
);


ALTER TABLE public.arcinvestigateur_service OWNER TO eclipse;

--
-- TOC entry 1851 (class 1259 OID 52682)
-- Dependencies: 6
-- Name: assurance_sigrec; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE assurance_sigrec (
    id bigint NOT NULL,
    datedebutvalidite timestamp without time zone,
    datefinvalidite timestamp without time zone,
    numerocontrat character varying(255),
    id_contact bigint,
    id_essai bigint
);


ALTER TABLE public.assurance_sigrec OWNER TO eclipse;

--
-- TOC entry 1852 (class 1259 OID 52685)
-- Dependencies: 6 1851
-- Name: assurance_sigrec_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE assurance_sigrec_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.assurance_sigrec_id_seq OWNER TO eclipse;

--
-- TOC entry 2936 (class 0 OID 0)
-- Dependencies: 1852
-- Name: assurance_sigrec_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE assurance_sigrec_id_seq OWNED BY assurance_sigrec.id;


--
-- TOC entry 1853 (class 1259 OID 52687)
-- Dependencies: 6
-- Name: bras; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE bras (
    id bigint NOT NULL,
    description text,
    nom character varying(255),
    type character varying(255),
    id_detail_design bigint NOT NULL,
    id_bras_parent bigint
);


ALTER TABLE public.bras OWNER TO eclipse;

--
-- TOC entry 1854 (class 1259 OID 52693)
-- Dependencies: 6 1853
-- Name: bras_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE bras_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.bras_id_seq OWNER TO eclipse;

--
-- TOC entry 2937 (class 0 OID 0)
-- Dependencies: 1854
-- Name: bras_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE bras_id_seq OWNED BY bras.id;


--
-- TOC entry 1855 (class 1259 OID 52695)
-- Dependencies: 6
-- Name: categorie; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE categorie (
    id bigint NOT NULL,
    libelle character varying(255),
    id_theme bigint NOT NULL,
    acte character varying(255)
);


ALTER TABLE public.categorie OWNER TO eclipse;

--
-- TOC entry 1856 (class 1259 OID 52701)
-- Dependencies: 6 1855
-- Name: categorie_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE categorie_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.categorie_id_seq OWNER TO eclipse;

--
-- TOC entry 2938 (class 0 OID 0)
-- Dependencies: 1856
-- Name: categorie_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE categorie_id_seq OWNED BY categorie.id;


--
-- TOC entry 1857 (class 1259 OID 52703)
-- Dependencies: 6
-- Name: centre_sigrec; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE centre_sigrec (
    id bigint NOT NULL,
    idcentre character varying(255),
    nom character varying(255),
    numero character varying(255),
    numerofiness character varying(255),
    id_contact bigint
);


ALTER TABLE public.centre_sigrec OWNER TO eclipse;

--
-- TOC entry 1858 (class 1259 OID 52709)
-- Dependencies: 6 1857
-- Name: centre_sigrec_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE centre_sigrec_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.centre_sigrec_id_seq OWNER TO eclipse;

--
-- TOC entry 2939 (class 0 OID 0)
-- Dependencies: 1858
-- Name: centre_sigrec_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE centre_sigrec_id_seq OWNED BY centre_sigrec.id;


--
-- TOC entry 1859 (class 1259 OID 52711)
-- Dependencies: 6
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
    id_essai bigint
);


ALTER TABLE public.co_investigateur_sigrec OWNER TO eclipse;

--
-- TOC entry 1860 (class 1259 OID 52717)
-- Dependencies: 1859 6
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
-- TOC entry 2940 (class 0 OID 0)
-- Dependencies: 1860
-- Name: co_investigateur_sigrec_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE co_investigateur_sigrec_id_seq OWNED BY co_investigateur_sigrec.id;


--
-- TOC entry 1861 (class 1259 OID 52719)
-- Dependencies: 6
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
    unitecontenance character varying(255)
);


ALTER TABLE public.conditionnement OWNER TO eclipse;

--
-- TOC entry 1862 (class 1259 OID 52725)
-- Dependencies: 1861 6
-- Name: conditionnement_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE conditionnement_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.conditionnement_id_seq OWNER TO eclipse;

--
-- TOC entry 2941 (class 0 OID 0)
-- Dependencies: 1862
-- Name: conditionnement_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE conditionnement_id_seq OWNED BY conditionnement.id;


--
-- TOC entry 1863 (class 1259 OID 52727)
-- Dependencies: 6
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
    ville character varying(255)
);


ALTER TABLE public.contact_sigrec OWNER TO eclipse;

--
-- TOC entry 1864 (class 1259 OID 52733)
-- Dependencies: 6 1863
-- Name: contact_sigrec_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE contact_sigrec_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.contact_sigrec_id_seq OWNER TO eclipse;

--
-- TOC entry 2942 (class 0 OID 0)
-- Dependencies: 1864
-- Name: contact_sigrec_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE contact_sigrec_id_seq OWNED BY contact_sigrec.id;


--
-- TOC entry 1865 (class 1259 OID 52735)
-- Dependencies: 6
-- Name: cro_sigrec; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE cro_sigrec (
    id bigint NOT NULL,
    identifiant character varying(255),
    id_contact bigint,
    id_essai bigint
);


ALTER TABLE public.cro_sigrec OWNER TO eclipse;

--
-- TOC entry 1866 (class 1259 OID 52738)
-- Dependencies: 6 1865
-- Name: cro_sigrec_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE cro_sigrec_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.cro_sigrec_id_seq OWNER TO eclipse;

--
-- TOC entry 2943 (class 0 OID 0)
-- Dependencies: 1866
-- Name: cro_sigrec_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE cro_sigrec_id_seq OWNED BY cro_sigrec.id;


--
-- TOC entry 1867 (class 1259 OID 52740)
-- Dependencies: 6
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
    commentaire text
);


ALTER TABLE public.dispensation OWNER TO eclipse;

--
-- TOC entry 1868 (class 1259 OID 52746)
-- Dependencies: 1867 6
-- Name: dispensation_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE dispensation_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.dispensation_id_seq OWNER TO eclipse;

--
-- TOC entry 2944 (class 0 OID 0)
-- Dependencies: 1868
-- Name: dispensation_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE dispensation_id_seq OWNED BY dispensation.id;


--
-- TOC entry 1869 (class 1259 OID 52748)
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
-- TOC entry 1870 (class 1259 OID 52754)
-- Dependencies: 6
-- Name: donnees_prevision; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE donnees_prevision (
    id bigint NOT NULL,
    nbannees integer,
    nbdestructions integer,
    nbdispensations integer,
    nbinclusions integer,
    nbprescriptions integer,
    nbreconstitutions integer,
    nbreetiquetages integer,
    id_detail_surcout bigint,
    nbvisitemonitoring integer,
    nbdispensationsrenouvellement integer
);


ALTER TABLE public.donnees_prevision OWNER TO eclipse;

--
-- TOC entry 1871 (class 1259 OID 52757)
-- Dependencies: 6 1870
-- Name: donnees_prevision_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE donnees_prevision_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.donnees_prevision_id_seq OWNER TO eclipse;

--
-- TOC entry 2945 (class 0 OID 0)
-- Dependencies: 1871
-- Name: donnees_prevision_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE donnees_prevision_id_seq OWNED BY donnees_prevision.id;


--
-- TOC entry 1872 (class 1259 OID 52759)
-- Dependencies: 6
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
    id_service bigint NOT NULL
);


ALTER TABLE public.dotation OWNER TO eclipse;

--
-- TOC entry 1873 (class 1259 OID 52765)
-- Dependencies: 6 1872
-- Name: dotation_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE dotation_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.dotation_id_seq OWNER TO eclipse;

--
-- TOC entry 2946 (class 0 OID 0)
-- Dependencies: 1873
-- Name: dotation_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE dotation_id_seq OWNED BY dotation.id;


--
-- TOC entry 1874 (class 1259 OID 52767)
-- Dependencies: 6
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
    id_personne bigint
);


ALTER TABLE public.element_to_check OWNER TO eclipse;

--
-- TOC entry 1875 (class 1259 OID 52773)
-- Dependencies: 6 1874
-- Name: element_to_check_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE element_to_check_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.element_to_check_id_seq OWNER TO eclipse;

--
-- TOC entry 2947 (class 0 OID 0)
-- Dependencies: 1875
-- Name: element_to_check_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE element_to_check_id_seq OWNED BY element_to_check.id;


--
-- TOC entry 1876 (class 1259 OID 52775)
-- Dependencies: 6
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
    conv_date timestamp without time zone
);


ALTER TABLE public.essai OWNER TO eclipse;

--
-- TOC entry 1877 (class 1259 OID 52781)
-- Dependencies: 6
-- Name: essai_commentaire_detail_administratif_archi; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_commentaire_detail_administratif_archi (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    libelle text NOT NULL,
    id_detailadministratif bigint NOT NULL
);


ALTER TABLE public.essai_commentaire_detail_administratif_archi OWNER TO eclipse;

--
-- TOC entry 1878 (class 1259 OID 52787)
-- Dependencies: 1877 6
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
-- TOC entry 2948 (class 0 OID 0)
-- Dependencies: 1878
-- Name: essai_commentaire_detail_administratif_archi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_commentaire_detail_administratif_archi_id_seq OWNED BY essai_commentaire_detail_administratif_archi.id;


--
-- TOC entry 1879 (class 1259 OID 52789)
-- Dependencies: 6
-- Name: essai_commentaire_detail_faisabilite; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_commentaire_detail_faisabilite (
    type character varying(31) NOT NULL,
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    libelle text NOT NULL,
    id_detailfaisabilite bigint NOT NULL
);


ALTER TABLE public.essai_commentaire_detail_faisabilite OWNER TO eclipse;

--
-- TOC entry 1880 (class 1259 OID 52795)
-- Dependencies: 6 1879
-- Name: essai_commentaire_detail_faisabilite_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_commentaire_detail_faisabilite_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_commentaire_detail_faisabilite_id_seq OWNER TO eclipse;

--
-- TOC entry 2949 (class 0 OID 0)
-- Dependencies: 1880
-- Name: essai_commentaire_detail_faisabilite_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_commentaire_detail_faisabilite_id_seq OWNED BY essai_commentaire_detail_faisabilite.id;


--
-- TOC entry 1881 (class 1259 OID 52797)
-- Dependencies: 6
-- Name: essai_commentaire_detail_recherche; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_commentaire_detail_recherche (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    libelle text NOT NULL,
    id_detailrecherche bigint NOT NULL
);


ALTER TABLE public.essai_commentaire_detail_recherche OWNER TO eclipse;

--
-- TOC entry 1882 (class 1259 OID 52803)
-- Dependencies: 1881 6
-- Name: essai_commentaire_detail_recherche_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_commentaire_detail_recherche_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_commentaire_detail_recherche_id_seq OWNER TO eclipse;

--
-- TOC entry 2950 (class 0 OID 0)
-- Dependencies: 1882
-- Name: essai_commentaire_detail_recherche_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_commentaire_detail_recherche_id_seq OWNED BY essai_commentaire_detail_recherche.id;


--
-- TOC entry 1883 (class 1259 OID 52805)
-- Dependencies: 6
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
    autorisationimportation_docsdosspapier boolean
);


ALTER TABLE public.essai_detail_administratif OWNER TO eclipse;

--
-- TOC entry 1884 (class 1259 OID 52811)
-- Dependencies: 1883 6
-- Name: essai_detail_administratif_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_administratif_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_administratif_id_seq OWNER TO eclipse;

--
-- TOC entry 2951 (class 0 OID 0)
-- Dependencies: 1884
-- Name: essai_detail_administratif_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_administratif_id_seq OWNED BY essai_detail_administratif.id;


--
-- TOC entry 1885 (class 1259 OID 52813)
-- Dependencies: 6
-- Name: essai_detail_administratif_suivi; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_detail_administratif_suivi (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_detail_administratif bigint NOT NULL
);


ALTER TABLE public.essai_detail_administratif_suivi OWNER TO eclipse;

--
-- TOC entry 1886 (class 1259 OID 52816)
-- Dependencies: 1885 6
-- Name: essai_detail_administratif_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_administratif_suivi_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_administratif_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 2952 (class 0 OID 0)
-- Dependencies: 1886
-- Name: essai_detail_administratif_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_administratif_suivi_id_seq OWNED BY essai_detail_administratif_suivi.id;


--
-- TOC entry 1887 (class 1259 OID 52818)
-- Dependencies: 6
-- Name: essai_detail_autres_documents; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_detail_autres_documents (
    id bigint NOT NULL,
    id_essai bigint
);


ALTER TABLE public.essai_detail_autres_documents OWNER TO eclipse;

--
-- TOC entry 1888 (class 1259 OID 52821)
-- Dependencies: 1887 6
-- Name: essai_detail_autres_documents_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_autres_documents_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_autres_documents_id_seq OWNER TO eclipse;

--
-- TOC entry 2953 (class 0 OID 0)
-- Dependencies: 1888
-- Name: essai_detail_autres_documents_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_autres_documents_id_seq OWNED BY essai_detail_autres_documents.id;


--
-- TOC entry 1889 (class 1259 OID 52823)
-- Dependencies: 6
-- Name: essai_detail_autres_documents_suivi; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_detail_autres_documents_suivi (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_detail_autres_documents bigint NOT NULL
);


ALTER TABLE public.essai_detail_autres_documents_suivi OWNER TO eclipse;

--
-- TOC entry 1890 (class 1259 OID 52826)
-- Dependencies: 6 1889
-- Name: essai_detail_autres_documents_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_autres_documents_suivi_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_autres_documents_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 2954 (class 0 OID 0)
-- Dependencies: 1890
-- Name: essai_detail_autres_documents_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_autres_documents_suivi_id_seq OWNED BY essai_detail_autres_documents_suivi.id;


--
-- TOC entry 1891 (class 1259 OID 52828)
-- Dependencies: 6
-- Name: essai_detail_contacts; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_detail_contacts (
    id bigint NOT NULL,
    id_essai bigint
);


ALTER TABLE public.essai_detail_contacts OWNER TO eclipse;

--
-- TOC entry 1892 (class 1259 OID 52831)
-- Dependencies: 1891 6
-- Name: essai_detail_contacts_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_contacts_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_contacts_id_seq OWNER TO eclipse;

--
-- TOC entry 2955 (class 0 OID 0)
-- Dependencies: 1892
-- Name: essai_detail_contacts_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_contacts_id_seq OWNED BY essai_detail_contacts.id;


--
-- TOC entry 1893 (class 1259 OID 52833)
-- Dependencies: 6
-- Name: essai_detail_contacts_suivi; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_detail_contacts_suivi (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_detail_contacts bigint NOT NULL
);


ALTER TABLE public.essai_detail_contacts_suivi OWNER TO eclipse;

--
-- TOC entry 1894 (class 1259 OID 52836)
-- Dependencies: 1893 6
-- Name: essai_detail_contacts_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_contacts_suivi_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_contacts_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 2956 (class 0 OID 0)
-- Dependencies: 1894
-- Name: essai_detail_contacts_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_contacts_suivi_id_seq OWNED BY essai_detail_contacts_suivi.id;


--
-- TOC entry 1895 (class 1259 OID 52838)
-- Dependencies: 6
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
    datereception timestamp without time zone
);


ALTER TABLE public.essai_detail_dates OWNER TO eclipse;

--
-- TOC entry 1896 (class 1259 OID 52841)
-- Dependencies: 6 1895
-- Name: essai_detail_dates_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_dates_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_dates_id_seq OWNER TO eclipse;

--
-- TOC entry 2957 (class 0 OID 0)
-- Dependencies: 1896
-- Name: essai_detail_dates_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_dates_id_seq OWNED BY essai_detail_dates.id;


--
-- TOC entry 1897 (class 1259 OID 52843)
-- Dependencies: 6
-- Name: essai_detail_dates_suivi; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_detail_dates_suivi (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_detail_dates bigint NOT NULL
);


ALTER TABLE public.essai_detail_dates_suivi OWNER TO eclipse;

--
-- TOC entry 1898 (class 1259 OID 52846)
-- Dependencies: 6 1897
-- Name: essai_detail_dates_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_dates_suivi_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_dates_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 2958 (class 0 OID 0)
-- Dependencies: 1898
-- Name: essai_detail_dates_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_dates_suivi_id_seq OWNED BY essai_detail_dates_suivi.id;


--
-- TOC entry 1899 (class 1259 OID 52848)
-- Dependencies: 6
-- Name: essai_detail_design; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_detail_design (
    id bigint NOT NULL,
    id_essai bigint,
    typedesign character varying(255)
);


ALTER TABLE public.essai_detail_design OWNER TO eclipse;

--
-- TOC entry 1900 (class 1259 OID 52851)
-- Dependencies: 1899 6
-- Name: essai_detail_design_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_design_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_design_id_seq OWNER TO eclipse;

--
-- TOC entry 2959 (class 0 OID 0)
-- Dependencies: 1900
-- Name: essai_detail_design_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_design_id_seq OWNED BY essai_detail_design.id;


--
-- TOC entry 1901 (class 1259 OID 52853)
-- Dependencies: 6
-- Name: essai_detail_design_suivi; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_detail_design_suivi (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_detail_design bigint NOT NULL
);


ALTER TABLE public.essai_detail_design_suivi OWNER TO eclipse;

--
-- TOC entry 1902 (class 1259 OID 52856)
-- Dependencies: 1901 6
-- Name: essai_detail_design_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_design_suivi_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_design_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 2960 (class 0 OID 0)
-- Dependencies: 1902
-- Name: essai_detail_design_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_design_suivi_id_seq OWNED BY essai_detail_design_suivi.id;


--
-- TOC entry 1903 (class 1259 OID 52858)
-- Dependencies: 6
-- Name: essai_detail_etat; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_detail_etat (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    commentaire text,
    etat character varying(255) NOT NULL,
    id_essai bigint NOT NULL
);


ALTER TABLE public.essai_detail_etat OWNER TO eclipse;

--
-- TOC entry 1904 (class 1259 OID 52864)
-- Dependencies: 1903 6
-- Name: essai_detail_etat_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_etat_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_etat_id_seq OWNER TO eclipse;

--
-- TOC entry 2961 (class 0 OID 0)
-- Dependencies: 1904
-- Name: essai_detail_etat_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_etat_id_seq OWNED BY essai_detail_etat.id;


--
-- TOC entry 1905 (class 1259 OID 52866)
-- Dependencies: 6
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
    id_essai bigint
);


ALTER TABLE public.essai_detail_faisabilite OWNER TO eclipse;

--
-- TOC entry 1906 (class 1259 OID 52869)
-- Dependencies: 1905 6
-- Name: essai_detail_faisabilite_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_faisabilite_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_faisabilite_id_seq OWNER TO eclipse;

--
-- TOC entry 2962 (class 0 OID 0)
-- Dependencies: 1906
-- Name: essai_detail_faisabilite_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_faisabilite_id_seq OWNED BY essai_detail_faisabilite.id;


--
-- TOC entry 1907 (class 1259 OID 52871)
-- Dependencies: 6
-- Name: essai_detail_faisabilite_service; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_detail_faisabilite_service (
    id_essai bigint NOT NULL,
    id_service bigint NOT NULL
);


ALTER TABLE public.essai_detail_faisabilite_service OWNER TO eclipse;

--
-- TOC entry 1908 (class 1259 OID 52874)
-- Dependencies: 6
-- Name: essai_detail_faisabilite_suivi; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_detail_faisabilite_suivi (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_detail_faisabilite bigint NOT NULL
);


ALTER TABLE public.essai_detail_faisabilite_suivi OWNER TO eclipse;

--
-- TOC entry 1909 (class 1259 OID 52877)
-- Dependencies: 6 1908
-- Name: essai_detail_faisabilite_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_faisabilite_suivi_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_faisabilite_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 2963 (class 0 OID 0)
-- Dependencies: 1909
-- Name: essai_detail_faisabilite_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_faisabilite_suivi_id_seq OWNED BY essai_detail_faisabilite_suivi.id;


--
-- TOC entry 1910 (class 1259 OID 52879)
-- Dependencies: 6
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
    numerocentre character varying(255)
);


ALTER TABLE public.essai_detail_pharma OWNER TO eclipse;

--
-- TOC entry 2049 (class 1259 OID 54606)
-- Dependencies: 6
-- Name: essai_detail_pharma_etablissement; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_detail_pharma_etablissement (
    id_detail_pharma bigint NOT NULL,
    id_etablissement bigint NOT NULL
);


ALTER TABLE public.essai_detail_pharma_etablissement OWNER TO eclipse;

--
-- TOC entry 1911 (class 1259 OID 52885)
-- Dependencies: 6 1910
-- Name: essai_detail_pharma_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_pharma_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_pharma_id_seq OWNER TO eclipse;

--
-- TOC entry 2964 (class 0 OID 0)
-- Dependencies: 1911
-- Name: essai_detail_pharma_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_pharma_id_seq OWNED BY essai_detail_pharma.id;


--
-- TOC entry 1912 (class 1259 OID 52887)
-- Dependencies: 6
-- Name: essai_detail_pharma_pharmacie; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_detail_pharma_pharmacie (
    id_detail_pharma bigint NOT NULL,
    id_pharmacie bigint NOT NULL
);


ALTER TABLE public.essai_detail_pharma_pharmacie OWNER TO eclipse;

--
-- TOC entry 1913 (class 1259 OID 52890)
-- Dependencies: 6
-- Name: essai_detail_pharma_suivi; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_detail_pharma_suivi (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_detail_pharma bigint NOT NULL
);


ALTER TABLE public.essai_detail_pharma_suivi OWNER TO eclipse;

--
-- TOC entry 1914 (class 1259 OID 52893)
-- Dependencies: 1913 6
-- Name: essai_detail_pharma_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_pharma_suivi_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_pharma_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 2965 (class 0 OID 0)
-- Dependencies: 1914
-- Name: essai_detail_pharma_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_pharma_suivi_id_seq OWNED BY essai_detail_pharma_suivi.id;


--
-- TOC entry 1915 (class 1259 OID 52895)
-- Dependencies: 6
-- Name: essai_detail_produit; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_detail_produit (
    id bigint NOT NULL,
    id_essai bigint
);


ALTER TABLE public.essai_detail_produit OWNER TO eclipse;

--
-- TOC entry 1916 (class 1259 OID 52898)
-- Dependencies: 6 1915
-- Name: essai_detail_produit_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_produit_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_produit_id_seq OWNER TO eclipse;

--
-- TOC entry 2966 (class 0 OID 0)
-- Dependencies: 1916
-- Name: essai_detail_produit_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_produit_id_seq OWNED BY essai_detail_produit.id;


--
-- TOC entry 1917 (class 1259 OID 52900)
-- Dependencies: 6
-- Name: essai_detail_produit_suivi; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_detail_produit_suivi (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_detail_produit bigint NOT NULL
);


ALTER TABLE public.essai_detail_produit_suivi OWNER TO eclipse;

--
-- TOC entry 1918 (class 1259 OID 52903)
-- Dependencies: 1917 6
-- Name: essai_detail_produit_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_produit_suivi_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_produit_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 2967 (class 0 OID 0)
-- Dependencies: 1918
-- Name: essai_detail_produit_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_produit_suivi_id_seq OWNED BY essai_detail_produit_suivi.id;


--
-- TOC entry 1919 (class 1259 OID 52905)
-- Dependencies: 6
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
    id_essai bigint
);


ALTER TABLE public.essai_detail_recherche OWNER TO eclipse;

--
-- TOC entry 1920 (class 1259 OID 52911)
-- Dependencies: 1919 6
-- Name: essai_detail_recherche_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_recherche_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_recherche_id_seq OWNER TO eclipse;

--
-- TOC entry 2968 (class 0 OID 0)
-- Dependencies: 1920
-- Name: essai_detail_recherche_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_recherche_id_seq OWNED BY essai_detail_recherche.id;


--
-- TOC entry 1921 (class 1259 OID 52913)
-- Dependencies: 6
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
    id_essai bigint
);


ALTER TABLE public.essai_detail_recherche_sigrec OWNER TO eclipse;

--
-- TOC entry 1922 (class 1259 OID 52919)
-- Dependencies: 6 1921
-- Name: essai_detail_recherche_sigrec_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_recherche_sigrec_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_recherche_sigrec_id_seq OWNER TO eclipse;

--
-- TOC entry 2969 (class 0 OID 0)
-- Dependencies: 1922
-- Name: essai_detail_recherche_sigrec_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_recherche_sigrec_id_seq OWNED BY essai_detail_recherche_sigrec.id;


--
-- TOC entry 1923 (class 1259 OID 52921)
-- Dependencies: 6
-- Name: essai_detail_recherche_suivi; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_detail_recherche_suivi (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_detail_recherche bigint NOT NULL
);


ALTER TABLE public.essai_detail_recherche_suivi OWNER TO eclipse;

--
-- TOC entry 1924 (class 1259 OID 52924)
-- Dependencies: 1923 6
-- Name: essai_detail_recherche_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_recherche_suivi_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_recherche_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 2970 (class 0 OID 0)
-- Dependencies: 1924
-- Name: essai_detail_recherche_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_recherche_suivi_id_seq OWNED BY essai_detail_recherche_suivi.id;


--
-- TOC entry 1925 (class 1259 OID 52926)
-- Dependencies: 6
-- Name: essai_detail_surcout; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_detail_surcout (
    id bigint NOT NULL,
    id_essai bigint
);


ALTER TABLE public.essai_detail_surcout OWNER TO eclipse;

--
-- TOC entry 1926 (class 1259 OID 52929)
-- Dependencies: 1925 6
-- Name: essai_detail_surcout_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_surcout_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_surcout_id_seq OWNER TO eclipse;

--
-- TOC entry 2971 (class 0 OID 0)
-- Dependencies: 1926
-- Name: essai_detail_surcout_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_surcout_id_seq OWNED BY essai_detail_surcout.id;


--
-- TOC entry 1927 (class 1259 OID 52931)
-- Dependencies: 6
-- Name: essai_detail_surcout_suivi; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_detail_surcout_suivi (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_detail_surcout bigint NOT NULL
);


ALTER TABLE public.essai_detail_surcout_suivi OWNER TO eclipse;

--
-- TOC entry 1928 (class 1259 OID 52934)
-- Dependencies: 1927 6
-- Name: essai_detail_surcout_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_surcout_suivi_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_surcout_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 2972 (class 0 OID 0)
-- Dependencies: 1928
-- Name: essai_detail_surcout_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_surcout_suivi_id_seq OWNED BY essai_detail_surcout_suivi.id;


--
-- TOC entry 1929 (class 1259 OID 52936)
-- Dependencies: 6
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
    version character varying(255),
    typedocumentbrochure character varying(255)
);


ALTER TABLE public.essai_document_detail_administratif OWNER TO eclipse;

--
-- TOC entry 1930 (class 1259 OID 52942)
-- Dependencies: 6 1929
-- Name: essai_document_detail_administratif_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_document_detail_administratif_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_document_detail_administratif_id_seq OWNER TO eclipse;

--
-- TOC entry 2973 (class 0 OID 0)
-- Dependencies: 1930
-- Name: essai_document_detail_administratif_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_document_detail_administratif_id_seq OWNED BY essai_document_detail_administratif.id;


--
-- TOC entry 1931 (class 1259 OID 52944)
-- Dependencies: 6
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
    id_detail_autres_documents bigint NOT NULL
);


ALTER TABLE public.essai_document_detail_autres_documents OWNER TO eclipse;

--
-- TOC entry 1932 (class 1259 OID 52950)
-- Dependencies: 6 1931
-- Name: essai_document_detail_autres_documents_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_document_detail_autres_documents_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_document_detail_autres_documents_id_seq OWNER TO eclipse;

--
-- TOC entry 2974 (class 0 OID 0)
-- Dependencies: 1932
-- Name: essai_document_detail_autres_documents_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_document_detail_autres_documents_id_seq OWNED BY essai_document_detail_autres_documents.id;


--
-- TOC entry 1933 (class 1259 OID 52952)
-- Dependencies: 6
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
    id_detail_pharma bigint NOT NULL
);


ALTER TABLE public.essai_document_detail_pharma OWNER TO eclipse;

--
-- TOC entry 1934 (class 1259 OID 52958)
-- Dependencies: 6 1933
-- Name: essai_document_detail_pharma_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_document_detail_pharma_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_document_detail_pharma_id_seq OWNER TO eclipse;

--
-- TOC entry 2975 (class 0 OID 0)
-- Dependencies: 1934
-- Name: essai_document_detail_pharma_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_document_detail_pharma_id_seq OWNED BY essai_document_detail_pharma.id;


--
-- TOC entry 1935 (class 1259 OID 52960)
-- Dependencies: 6
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
    id_detailsurcout bigint NOT NULL
);


ALTER TABLE public.essai_document_detail_surcout OWNER TO eclipse;

--
-- TOC entry 1936 (class 1259 OID 52966)
-- Dependencies: 6 1935
-- Name: essai_document_detail_surcout_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_document_detail_surcout_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_document_detail_surcout_id_seq OWNER TO eclipse;

--
-- TOC entry 2976 (class 0 OID 0)
-- Dependencies: 1936
-- Name: essai_document_detail_surcout_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_document_detail_surcout_id_seq OWNED BY essai_document_detail_surcout.id;


--
-- TOC entry 1937 (class 1259 OID 52968)
-- Dependencies: 6 1876
-- Name: essai_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_id_seq OWNER TO eclipse;

--
-- TOC entry 2977 (class 0 OID 0)
-- Dependencies: 1937
-- Name: essai_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_id_seq OWNED BY essai.id;


--
-- TOC entry 1938 (class 1259 OID 52970)
-- Dependencies: 6
-- Name: essai_service; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_service (
    id_essai bigint NOT NULL,
    id_service bigint NOT NULL
);


ALTER TABLE public.essai_service OWNER TO eclipse;

--
-- TOC entry 1939 (class 1259 OID 52973)
-- Dependencies: 6
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
    id_promoteur bigint
);


ALTER TABLE public.essai_sigrec OWNER TO eclipse;

--
-- TOC entry 1940 (class 1259 OID 52979)
-- Dependencies: 6 1939
-- Name: essai_sigrec_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_sigrec_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_sigrec_id_seq OWNER TO eclipse;

--
-- TOC entry 2978 (class 0 OID 0)
-- Dependencies: 1940
-- Name: essai_sigrec_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_sigrec_id_seq OWNED BY essai_sigrec.id;


--
-- TOC entry 1941 (class 1259 OID 52981)
-- Dependencies: 6
-- Name: essai_suivi; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_suivi (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_essai bigint NOT NULL
);


ALTER TABLE public.essai_suivi OWNER TO eclipse;

--
-- TOC entry 1942 (class 1259 OID 52984)
-- Dependencies: 1941 6
-- Name: essai_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_suivi_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 2979 (class 0 OID 0)
-- Dependencies: 1942
-- Name: essai_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_suivi_id_seq OWNED BY essai_suivi.id;


--
-- TOC entry 1943 (class 1259 OID 52986)
-- Dependencies: 6
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
    ville character varying(255)
);


ALTER TABLE public.etablissement OWNER TO eclipse;

--
-- TOC entry 1944 (class 1259 OID 52992)
-- Dependencies: 6 1943
-- Name: etablissement_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE etablissement_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.etablissement_id_seq OWNER TO eclipse;

--
-- TOC entry 2980 (class 0 OID 0)
-- Dependencies: 1944
-- Name: etablissement_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE etablissement_id_seq OWNED BY etablissement.id;


--
-- TOC entry 1945 (class 1259 OID 52994)
-- Dependencies: 6
-- Name: etablissement_suivi; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE etablissement_suivi (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_etablissement bigint NOT NULL
);


ALTER TABLE public.etablissement_suivi OWNER TO eclipse;

--
-- TOC entry 1946 (class 1259 OID 52997)
-- Dependencies: 1945 6
-- Name: etablissement_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE etablissement_suivi_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.etablissement_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 2981 (class 0 OID 0)
-- Dependencies: 1946
-- Name: etablissement_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE etablissement_suivi_id_seq OWNED BY etablissement_suivi.id;


--
-- TOC entry 1947 (class 1259 OID 52999)
-- Dependencies: 6
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
    datereception timestamp without time zone
);


ALTER TABLE public.evenement OWNER TO eclipse;

--
-- TOC entry 1948 (class 1259 OID 53005)
-- Dependencies: 6 1947
-- Name: evenement_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE evenement_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.evenement_id_seq OWNER TO eclipse;

--
-- TOC entry 2982 (class 0 OID 0)
-- Dependencies: 1948
-- Name: evenement_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE evenement_id_seq OWNED BY evenement.id;


--
-- TOC entry 1949 (class 1259 OID 53007)
-- Dependencies: 6
-- Name: evenement_suivi; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE evenement_suivi (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_evenement bigint NOT NULL
);


ALTER TABLE public.evenement_suivi OWNER TO eclipse;

--
-- TOC entry 1950 (class 1259 OID 53010)
-- Dependencies: 1949 6
-- Name: evenement_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE evenement_suivi_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.evenement_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 2983 (class 0 OID 0)
-- Dependencies: 1950
-- Name: evenement_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE evenement_suivi_id_seq OWNED BY evenement_suivi.id;


--
-- TOC entry 1951 (class 1259 OID 53012)
-- Dependencies: 6
-- Name: grille; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE grille (
    id bigint NOT NULL,
    id_grille_modele bigint NOT NULL,
    id_detail_surcout bigint
);


ALTER TABLE public.grille OWNER TO eclipse;

--
-- TOC entry 1952 (class 1259 OID 53015)
-- Dependencies: 6 1951
-- Name: grille_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE grille_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.grille_id_seq OWNER TO eclipse;

--
-- TOC entry 2984 (class 0 OID 0)
-- Dependencies: 1952
-- Name: grille_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE grille_id_seq OWNED BY grille.id;


--
-- TOC entry 1953 (class 1259 OID 53017)
-- Dependencies: 6
-- Name: grille_modele; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE grille_modele (
    id bigint NOT NULL,
    datecreation timestamp without time zone,
    datedebut timestamp without time zone,
    datefin timestamp without time zone,
    nom character varying(255)
);


ALTER TABLE public.grille_modele OWNER TO eclipse;

--
-- TOC entry 1954 (class 1259 OID 53020)
-- Dependencies: 1953 6
-- Name: grille_modele_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE grille_modele_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.grille_modele_id_seq OWNER TO eclipse;

--
-- TOC entry 2985 (class 0 OID 0)
-- Dependencies: 1954
-- Name: grille_modele_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE grille_modele_id_seq OWNED BY grille_modele.id;


--
-- TOC entry 1955 (class 1259 OID 53022)
-- Dependencies: 6
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
    id_personne bigint NOT NULL
);


ALTER TABLE public.habilitation OWNER TO eclipse;

--
-- TOC entry 1956 (class 1259 OID 53028)
-- Dependencies: 6 1955
-- Name: habilitation_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE habilitation_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.habilitation_id_seq OWNER TO eclipse;

--
-- TOC entry 2986 (class 0 OID 0)
-- Dependencies: 1956
-- Name: habilitation_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE habilitation_id_seq OWNED BY habilitation.id;


--
-- TOC entry 1957 (class 1259 OID 53030)
-- Dependencies: 6
-- Name: historique_patient; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE historique_patient (
    id bigint NOT NULL,
    commentaire text,
    date timestamp without time zone,
    poid double precision,
    surfacecorporelle double precision,
    taille double precision,
    id_patient bigint NOT NULL
);


ALTER TABLE public.historique_patient OWNER TO eclipse;

--
-- TOC entry 1958 (class 1259 OID 53036)
-- Dependencies: 1957 6
-- Name: historique_patient_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE historique_patient_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.historique_patient_id_seq OWNER TO eclipse;

--
-- TOC entry 2987 (class 0 OID 0)
-- Dependencies: 1958
-- Name: historique_patient_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE historique_patient_id_seq OWNED BY historique_patient.id;


--
-- TOC entry 1959 (class 1259 OID 53038)
-- Dependencies: 6
-- Name: incident; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE incident (
    id bigint NOT NULL,
    commentaire text,
    date timestamp without time zone NOT NULL,
    libelle character varying(255) NOT NULL,
    id_essai bigint NOT NULL
);


ALTER TABLE public.incident OWNER TO eclipse;

--
-- TOC entry 1960 (class 1259 OID 53044)
-- Dependencies: 6 1959
-- Name: incident_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE incident_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.incident_id_seq OWNER TO eclipse;

--
-- TOC entry 2988 (class 0 OID 0)
-- Dependencies: 1960
-- Name: incident_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE incident_id_seq OWNED BY incident.id;


--
-- TOC entry 1961 (class 1259 OID 53046)
-- Dependencies: 6
-- Name: incident_suivi; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE incident_suivi (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_incident bigint NOT NULL
);


ALTER TABLE public.incident_suivi OWNER TO eclipse;

--
-- TOC entry 1962 (class 1259 OID 53049)
-- Dependencies: 6 1961
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
-- TOC entry 2989 (class 0 OID 0)
-- Dependencies: 1962
-- Name: incident_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE incident_suivi_id_seq OWNED BY incident_suivi.id;


--
-- TOC entry 1963 (class 1259 OID 53051)
-- Dependencies: 6
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
    id_patient bigint NOT NULL
);


ALTER TABLE public.inclusion OWNER TO eclipse;

--
-- TOC entry 1964 (class 1259 OID 53057)
-- Dependencies: 1963 6
-- Name: inclusion_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE inclusion_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.inclusion_id_seq OWNER TO eclipse;

--
-- TOC entry 2990 (class 0 OID 0)
-- Dependencies: 1964
-- Name: inclusion_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE inclusion_id_seq OWNED BY inclusion.id;


--
-- TOC entry 1965 (class 1259 OID 53059)
-- Dependencies: 6
-- Name: investigateur_service; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE investigateur_service (
    id_investigateur bigint NOT NULL,
    id_service bigint NOT NULL
);


ALTER TABLE public.investigateur_service OWNER TO eclipse;

--
-- TOC entry 1966 (class 1259 OID 53062)
-- Dependencies: 6
-- Name: investigateur_sigrec; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE investigateur_sigrec (
    id bigint NOT NULL,
    identifiant character varying(255),
    intervenantid integer,
    numadeli character varying(255),
    titre character varying(255),
    id_contact bigint,
    id_centre bigint
);


ALTER TABLE public.investigateur_sigrec OWNER TO eclipse;

--
-- TOC entry 1967 (class 1259 OID 53068)
-- Dependencies: 6 1966
-- Name: investigateur_sigrec_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE investigateur_sigrec_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.investigateur_sigrec_id_seq OWNER TO eclipse;

--
-- TOC entry 2991 (class 0 OID 0)
-- Dependencies: 1967
-- Name: investigateur_sigrec_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE investigateur_sigrec_id_seq OWNED BY investigateur_sigrec.id;


--
-- TOC entry 1968 (class 1259 OID 53070)
-- Dependencies: 6
-- Name: item; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE item (
    id bigint NOT NULL,
    categorie character varying(255),
    theme character varying(255),
    id_grille bigint NOT NULL,
    acte character varying(255)
);


ALTER TABLE public.item OWNER TO eclipse;

--
-- TOC entry 1969 (class 1259 OID 53076)
-- Dependencies: 6 1968
-- Name: item_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE item_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.item_id_seq OWNER TO eclipse;

--
-- TOC entry 2992 (class 0 OID 0)
-- Dependencies: 1969
-- Name: item_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE item_id_seq OWNED BY item.id;


--
-- TOC entry 1970 (class 1259 OID 53078)
-- Dependencies: 6
-- Name: item_regle; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE item_regle (
    id_regle bigint NOT NULL,
    id_item bigint NOT NULL
);


ALTER TABLE public.item_regle OWNER TO eclipse;

--
-- TOC entry 1971 (class 1259 OID 53081)
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
-- TOC entry 1972 (class 1259 OID 53087)
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
-- TOC entry 1973 (class 1259 OID 53093)
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
-- TOC entry 1974 (class 1259 OID 53099)
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
-- TOC entry 1975 (class 1259 OID 53105)
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
-- TOC entry 1976 (class 1259 OID 53111)
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
-- TOC entry 1977 (class 1259 OID 53114)
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
-- TOC entry 2052 (class 1259 OID 54644)
-- Dependencies: 6
-- Name: mvt_preparation; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE mvt_preparation (
    id bigint NOT NULL
);


ALTER TABLE public.mvt_preparation OWNER TO eclipse;

--
-- TOC entry 2051 (class 1259 OID 54631)
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
-- TOC entry 1978 (class 1259 OID 53117)
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
-- TOC entry 1979 (class 1259 OID 53123)
-- Dependencies: 6
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
    dateperemption timestamp without time zone
);


ALTER TABLE public.mvtstock OWNER TO eclipse;

--
-- TOC entry 1980 (class 1259 OID 53129)
-- Dependencies: 6
-- Name: mvtstock_document; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE mvtstock_document (
    type character varying(31) NOT NULL,
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    nomdisque character varying(255) NOT NULL,
    nomuser character varying(255) NOT NULL,
    id_mvtstock bigint NOT NULL
);


ALTER TABLE public.mvtstock_document OWNER TO eclipse;

--
-- TOC entry 1981 (class 1259 OID 53135)
-- Dependencies: 1980 6
-- Name: mvtstock_document_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE mvtstock_document_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.mvtstock_document_id_seq OWNER TO eclipse;

--
-- TOC entry 2993 (class 0 OID 0)
-- Dependencies: 1981
-- Name: mvtstock_document_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE mvtstock_document_id_seq OWNED BY mvtstock_document.id;


--
-- TOC entry 1982 (class 1259 OID 53137)
-- Dependencies: 1979 6
-- Name: mvtstock_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE mvtstock_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.mvtstock_id_seq OWNER TO eclipse;

--
-- TOC entry 2994 (class 0 OID 0)
-- Dependencies: 1982
-- Name: mvtstock_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE mvtstock_id_seq OWNED BY mvtstock.id;


--
-- TOC entry 1983 (class 1259 OID 53139)
-- Dependencies: 6
-- Name: ordonnancier_dispensation; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE ordonnancier_dispensation (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    datedebut date NOT NULL,
    datefin date NOT NULL,
    id_pharma bigint NOT NULL
);


ALTER TABLE public.ordonnancier_dispensation OWNER TO eclipse;

--
-- TOC entry 1984 (class 1259 OID 53142)
-- Dependencies: 1983 6
-- Name: ordonnancier_dispensation_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE ordonnancier_dispensation_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.ordonnancier_dispensation_id_seq OWNER TO eclipse;

--
-- TOC entry 2995 (class 0 OID 0)
-- Dependencies: 1984
-- Name: ordonnancier_dispensation_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE ordonnancier_dispensation_id_seq OWNED BY ordonnancier_dispensation.id;


--
-- TOC entry 1985 (class 1259 OID 53144)
-- Dependencies: 6
-- Name: ordonnancier_fab_reconst; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE ordonnancier_fab_reconst (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    datedebut date NOT NULL,
    datefin date NOT NULL,
    id_pharma bigint NOT NULL
);


ALTER TABLE public.ordonnancier_fab_reconst OWNER TO eclipse;

--
-- TOC entry 1986 (class 1259 OID 53147)
-- Dependencies: 6 1985
-- Name: ordonnancier_fab_reconst_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE ordonnancier_fab_reconst_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.ordonnancier_fab_reconst_id_seq OWNER TO eclipse;

--
-- TOC entry 2996 (class 0 OID 0)
-- Dependencies: 1986
-- Name: ordonnancier_fab_reconst_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE ordonnancier_fab_reconst_id_seq OWNED BY ordonnancier_fab_reconst.id;


--
-- TOC entry 1987 (class 1259 OID 53149)
-- Dependencies: 6
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
    numerosejour character varying(255)
);


ALTER TABLE public.patient OWNER TO eclipse;

--
-- TOC entry 1988 (class 1259 OID 53155)
-- Dependencies: 1987 6
-- Name: patient_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE patient_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.patient_id_seq OWNER TO eclipse;

--
-- TOC entry 2997 (class 0 OID 0)
-- Dependencies: 1988
-- Name: patient_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE patient_id_seq OWNED BY patient.id;


--
-- TOC entry 1989 (class 1259 OID 53157)
-- Dependencies: 6
-- Name: patient_suivi; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE patient_suivi (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_patient bigint NOT NULL
);


ALTER TABLE public.patient_suivi OWNER TO eclipse;

--
-- TOC entry 1990 (class 1259 OID 53160)
-- Dependencies: 1989 6
-- Name: patient_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE patient_suivi_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.patient_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 2998 (class 0 OID 0)
-- Dependencies: 1990
-- Name: patient_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE patient_suivi_id_seq OWNED BY patient_suivi.id;


--
-- TOC entry 1991 (class 1259 OID 53162)
-- Dependencies: 6
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
    datevalidationformation timestamp without time zone
);


ALTER TABLE public.personne OWNER TO eclipse;

--
-- TOC entry 1992 (class 1259 OID 53168)
-- Dependencies: 1991 6
-- Name: personne_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE personne_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.personne_id_seq OWNER TO eclipse;

--
-- TOC entry 2999 (class 0 OID 0)
-- Dependencies: 1992
-- Name: personne_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE personne_id_seq OWNED BY personne.id;


--
-- TOC entry 1993 (class 1259 OID 53170)
-- Dependencies: 6
-- Name: personne_suivi; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE personne_suivi (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_personne bigint NOT NULL
);


ALTER TABLE public.personne_suivi OWNER TO eclipse;

--
-- TOC entry 1994 (class 1259 OID 53173)
-- Dependencies: 6 1993
-- Name: personne_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE personne_suivi_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.personne_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 3000 (class 0 OID 0)
-- Dependencies: 1994
-- Name: personne_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE personne_suivi_id_seq OWNED BY personne_suivi.id;


--
-- TOC entry 1995 (class 1259 OID 53175)
-- Dependencies: 2388 2389 6
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
    numordonnancierfab integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.pharmacie OWNER TO eclipse;

--
-- TOC entry 1996 (class 1259 OID 53183)
-- Dependencies: 1995 6
-- Name: pharmacie_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE pharmacie_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.pharmacie_id_seq OWNER TO eclipse;

--
-- TOC entry 3001 (class 0 OID 0)
-- Dependencies: 1996
-- Name: pharmacie_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE pharmacie_id_seq OWNED BY pharmacie.id;


--
-- TOC entry 1997 (class 1259 OID 53185)
-- Dependencies: 6
-- Name: pharmacie_site; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE pharmacie_site (
    id_pharmacie bigint NOT NULL,
    id_site bigint NOT NULL
);


ALTER TABLE public.pharmacie_site OWNER TO eclipse;

--
-- TOC entry 1998 (class 1259 OID 53188)
-- Dependencies: 6
-- Name: pharmacie_suivi; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE pharmacie_suivi (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_pharmacie bigint NOT NULL
);


ALTER TABLE public.pharmacie_suivi OWNER TO eclipse;

--
-- TOC entry 1999 (class 1259 OID 53191)
-- Dependencies: 6 1998
-- Name: pharmacie_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE pharmacie_suivi_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.pharmacie_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 3002 (class 0 OID 0)
-- Dependencies: 1999
-- Name: pharmacie_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE pharmacie_suivi_id_seq OWNED BY pharmacie_suivi.id;


--
-- TOC entry 2054 (class 1259 OID 54656)
-- Dependencies: 6
-- Name: pharmacien_document_pharmacien; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE pharmacien_document_pharmacien (
    type character varying(31) NOT NULL,
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    nomdisque character varying(255),
    nomuser character varying(255),
    id_pharmacien bigint NOT NULL
);


ALTER TABLE public.pharmacien_document_pharmacien OWNER TO eclipse;

--
-- TOC entry 2053 (class 1259 OID 54654)
-- Dependencies: 2054 6
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
-- TOC entry 3003 (class 0 OID 0)
-- Dependencies: 2053
-- Name: pharmacien_document_pharmacien_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE pharmacien_document_pharmacien_id_seq OWNED BY pharmacien_document_pharmacien.id;


--
-- TOC entry 2000 (class 1259 OID 53193)
-- Dependencies: 6
-- Name: pharmacien_pharmacie; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE pharmacien_pharmacie (
    id_pharmacien bigint NOT NULL,
    id_pharmacie bigint NOT NULL
);


ALTER TABLE public.pharmacien_pharmacie OWNER TO eclipse;

--
-- TOC entry 2001 (class 1259 OID 53196)
-- Dependencies: 6
-- Name: pole; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE pole (
    id bigint NOT NULL,
    nom character varying(255) NOT NULL,
    id_etablissement bigint NOT NULL
);


ALTER TABLE public.pole OWNER TO eclipse;

--
-- TOC entry 2002 (class 1259 OID 53199)
-- Dependencies: 6 2001
-- Name: pole_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE pole_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.pole_id_seq OWNER TO eclipse;

--
-- TOC entry 3004 (class 0 OID 0)
-- Dependencies: 2002
-- Name: pole_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE pole_id_seq OWNED BY pole.id;


--
-- TOC entry 2003 (class 1259 OID 53201)
-- Dependencies: 6
-- Name: pole_suivi; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE pole_suivi (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_pole bigint NOT NULL
);


ALTER TABLE public.pole_suivi OWNER TO eclipse;

--
-- TOC entry 2004 (class 1259 OID 53204)
-- Dependencies: 6 2003
-- Name: pole_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE pole_suivi_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.pole_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 3005 (class 0 OID 0)
-- Dependencies: 2004
-- Name: pole_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE pole_suivi_id_seq OWNED BY pole_suivi.id;


--
-- TOC entry 2050 (class 1259 OID 54621)
-- Dependencies: 6
-- Name: preparation; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE preparation (
    id bigint NOT NULL
);


ALTER TABLE public.preparation OWNER TO eclipse;

--
-- TOC entry 2005 (class 1259 OID 53206)
-- Dependencies: 6
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
    numvisite character varying(255)
);


ALTER TABLE public.prescription OWNER TO eclipse;

--
-- TOC entry 2006 (class 1259 OID 53212)
-- Dependencies: 6 2005
-- Name: prescription_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE prescription_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.prescription_id_seq OWNER TO eclipse;

--
-- TOC entry 3006 (class 0 OID 0)
-- Dependencies: 2006
-- Name: prescription_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE prescription_id_seq OWNED BY prescription.id;


--
-- TOC entry 2007 (class 1259 OID 53214)
-- Dependencies: 6
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
    dosage numeric(19,2)
);


ALTER TABLE public.prescription_type OWNER TO eclipse;

--
-- TOC entry 2008 (class 1259 OID 53220)
-- Dependencies: 2007 6
-- Name: prescription_type_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE prescription_type_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.prescription_type_id_seq OWNER TO eclipse;

--
-- TOC entry 3007 (class 0 OID 0)
-- Dependencies: 2008
-- Name: prescription_type_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE prescription_type_id_seq OWNED BY prescription_type.id;


--
-- TOC entry 2009 (class 1259 OID 53222)
-- Dependencies: 6
-- Name: prevision_sigrec; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE prevision_sigrec (
    id bigint NOT NULL,
    datedebut timestamp without time zone,
    datefin timestamp without time zone,
    dureetotale integer,
    nbcentres integer,
    id_essai bigint
);


ALTER TABLE public.prevision_sigrec OWNER TO eclipse;

--
-- TOC entry 2010 (class 1259 OID 53225)
-- Dependencies: 6 2009
-- Name: prevision_sigrec_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE prevision_sigrec_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.prevision_sigrec_id_seq OWNER TO eclipse;

--
-- TOC entry 3008 (class 0 OID 0)
-- Dependencies: 2010
-- Name: prevision_sigrec_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE prevision_sigrec_id_seq OWNED BY prevision_sigrec.id;


--
-- TOC entry 2011 (class 1259 OID 53227)
-- Dependencies: 6
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
    id_detailproduit bigint NOT NULL
);


ALTER TABLE public.produit OWNER TO eclipse;

--
-- TOC entry 2012 (class 1259 OID 53233)
-- Dependencies: 6
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
    id_produit bigint
);


ALTER TABLE public.produit_detail_logistique OWNER TO eclipse;

--
-- TOC entry 2013 (class 1259 OID 53236)
-- Dependencies: 6 2012
-- Name: produit_detail_logistique_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE produit_detail_logistique_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.produit_detail_logistique_id_seq OWNER TO eclipse;

--
-- TOC entry 3009 (class 0 OID 0)
-- Dependencies: 2013
-- Name: produit_detail_logistique_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE produit_detail_logistique_id_seq OWNED BY produit_detail_logistique.id;


--
-- TOC entry 2014 (class 1259 OID 53238)
-- Dependencies: 6
-- Name: produit_detail_stockage; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE produit_detail_stockage (
    id bigint NOT NULL,
    identifiantstockage character varying(255),
    type character varying(255),
    id_detail_logistique bigint,
    id_pharmacie bigint,
    id_stockage bigint
);


ALTER TABLE public.produit_detail_stockage OWNER TO eclipse;

--
-- TOC entry 2015 (class 1259 OID 53244)
-- Dependencies: 6 2014
-- Name: produit_detail_stockage_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE produit_detail_stockage_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.produit_detail_stockage_id_seq OWNER TO eclipse;

--
-- TOC entry 3010 (class 0 OID 0)
-- Dependencies: 2015
-- Name: produit_detail_stockage_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE produit_detail_stockage_id_seq OWNED BY produit_detail_stockage.id;


--
-- TOC entry 2016 (class 1259 OID 53246)
-- Dependencies: 6
-- Name: produit_document_actes_pharma; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE produit_document_actes_pharma (
    type character varying(31) NOT NULL,
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    nomdisque character varying(255) NOT NULL,
    nomuser character varying(255) NOT NULL,
    id_produit bigint NOT NULL
);


ALTER TABLE public.produit_document_actes_pharma OWNER TO eclipse;

--
-- TOC entry 2017 (class 1259 OID 53252)
-- Dependencies: 6 2016
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
-- TOC entry 3011 (class 0 OID 0)
-- Dependencies: 2017
-- Name: produit_document_actes_pharma_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE produit_document_actes_pharma_id_seq OWNED BY produit_document_actes_pharma.id;


--
-- TOC entry 2018 (class 1259 OID 53254)
-- Dependencies: 6 2011
-- Name: produit_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE produit_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.produit_id_seq OWNER TO eclipse;

--
-- TOC entry 3012 (class 0 OID 0)
-- Dependencies: 2018
-- Name: produit_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE produit_id_seq OWNED BY produit.id;


--
-- TOC entry 2019 (class 1259 OID 53256)
-- Dependencies: 6
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
    adispenser boolean
);


ALTER TABLE public.produit_prescrit OWNER TO eclipse;

--
-- TOC entry 2020 (class 1259 OID 53262)
-- Dependencies: 6 2019
-- Name: produit_prescrit_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE produit_prescrit_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.produit_prescrit_id_seq OWNER TO eclipse;

--
-- TOC entry 3013 (class 0 OID 0)
-- Dependencies: 2020
-- Name: produit_prescrit_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE produit_prescrit_id_seq OWNED BY produit_prescrit.id;


--
-- TOC entry 2021 (class 1259 OID 53264)
-- Dependencies: 6
-- Name: produit_service; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE produit_service (
    id_produit bigint NOT NULL,
    id_service bigint NOT NULL
);


ALTER TABLE public.produit_service OWNER TO eclipse;

--
-- TOC entry 2022 (class 1259 OID 53267)
-- Dependencies: 6
-- Name: produit_suivi; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE produit_suivi (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_produit bigint NOT NULL
);


ALTER TABLE public.produit_suivi OWNER TO eclipse;

--
-- TOC entry 2023 (class 1259 OID 53270)
-- Dependencies: 2022 6
-- Name: produit_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE produit_suivi_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.produit_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 3014 (class 0 OID 0)
-- Dependencies: 2023
-- Name: produit_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE produit_suivi_id_seq OWNED BY produit_suivi.id;


--
-- TOC entry 2024 (class 1259 OID 53272)
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
-- TOC entry 2025 (class 1259 OID 53278)
-- Dependencies: 6
-- Name: promoteur; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE promoteur (
    id bigint NOT NULL,
    identifiant character varying(255),
    raisonsociale character varying(255) NOT NULL,
    type character varying(255) NOT NULL
);


ALTER TABLE public.promoteur OWNER TO eclipse;

--
-- TOC entry 2026 (class 1259 OID 53284)
-- Dependencies: 6 2025
-- Name: promoteur_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE promoteur_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.promoteur_id_seq OWNER TO eclipse;

--
-- TOC entry 3015 (class 0 OID 0)
-- Dependencies: 2026
-- Name: promoteur_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE promoteur_id_seq OWNED BY promoteur.id;


--
-- TOC entry 2027 (class 1259 OID 53286)
-- Dependencies: 6
-- Name: promoteur_sigrec; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE promoteur_sigrec (
    id bigint NOT NULL,
    identifiant character varying(255),
    type character varying(255),
    id_contact bigint
);


ALTER TABLE public.promoteur_sigrec OWNER TO eclipse;

--
-- TOC entry 2028 (class 1259 OID 53292)
-- Dependencies: 6 2027
-- Name: promoteur_sigrec_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE promoteur_sigrec_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.promoteur_sigrec_id_seq OWNER TO eclipse;

--
-- TOC entry 3016 (class 0 OID 0)
-- Dependencies: 2028
-- Name: promoteur_sigrec_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE promoteur_sigrec_id_seq OWNED BY promoteur_sigrec.id;


--
-- TOC entry 2029 (class 1259 OID 53294)
-- Dependencies: 6
-- Name: promoteur_suivi; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE promoteur_suivi (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_promoteur bigint NOT NULL
);


ALTER TABLE public.promoteur_suivi OWNER TO eclipse;

--
-- TOC entry 2030 (class 1259 OID 53297)
-- Dependencies: 6 2029
-- Name: promoteur_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE promoteur_suivi_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.promoteur_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 3017 (class 0 OID 0)
-- Dependencies: 2030
-- Name: promoteur_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE promoteur_suivi_id_seq OWNED BY promoteur_suivi.id;


--
-- TOC entry 2031 (class 1259 OID 53299)
-- Dependencies: 6
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
    id_categorie bigint
);


ALTER TABLE public.regle_surcout OWNER TO eclipse;

--
-- TOC entry 2032 (class 1259 OID 53305)
-- Dependencies: 6 2031
-- Name: regle_surcout_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE regle_surcout_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.regle_surcout_id_seq OWNER TO eclipse;

--
-- TOC entry 3018 (class 0 OID 0)
-- Dependencies: 2032
-- Name: regle_surcout_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE regle_surcout_id_seq OWNED BY regle_surcout.id;


--
-- TOC entry 2033 (class 1259 OID 53307)
-- Dependencies: 6
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
    numlot character varying(255)
);


ALTER TABLE public.retour_patient OWNER TO eclipse;

--
-- TOC entry 2034 (class 1259 OID 53313)
-- Dependencies: 6 2033
-- Name: retour_patient_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE retour_patient_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.retour_patient_id_seq OWNER TO eclipse;

--
-- TOC entry 3019 (class 0 OID 0)
-- Dependencies: 2034
-- Name: retour_patient_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE retour_patient_id_seq OWNED BY retour_patient.id;


--
-- TOC entry 2035 (class 1259 OID 53315)
-- Dependencies: 6
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
    unite_duree character varying(255)
);


ALTER TABLE public.sequence OWNER TO eclipse;

--
-- TOC entry 2036 (class 1259 OID 53321)
-- Dependencies: 6 2035
-- Name: sequence_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE sequence_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.sequence_id_seq OWNER TO eclipse;

--
-- TOC entry 3020 (class 0 OID 0)
-- Dependencies: 2036
-- Name: sequence_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE sequence_id_seq OWNED BY sequence.id;


--
-- TOC entry 2037 (class 1259 OID 53323)
-- Dependencies: 6
-- Name: service; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE service (
    id bigint NOT NULL,
    nom character varying(255) NOT NULL,
    id_pole bigint,
    id_site bigint
);


ALTER TABLE public.service OWNER TO eclipse;

--
-- TOC entry 2038 (class 1259 OID 53326)
-- Dependencies: 6 2037
-- Name: service_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE service_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.service_id_seq OWNER TO eclipse;

--
-- TOC entry 3021 (class 0 OID 0)
-- Dependencies: 2038
-- Name: service_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE service_id_seq OWNED BY service.id;


--
-- TOC entry 2039 (class 1259 OID 53328)
-- Dependencies: 6
-- Name: service_suivi; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE service_suivi (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_service bigint NOT NULL
);


ALTER TABLE public.service_suivi OWNER TO eclipse;

--
-- TOC entry 2040 (class 1259 OID 53331)
-- Dependencies: 6 2039
-- Name: service_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE service_suivi_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.service_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 3022 (class 0 OID 0)
-- Dependencies: 2040
-- Name: service_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE service_suivi_id_seq OWNED BY service_suivi.id;


--
-- TOC entry 2041 (class 1259 OID 53333)
-- Dependencies: 6
-- Name: site; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE site (
    id bigint NOT NULL,
    adresse text,
    code character varying(255) NOT NULL,
    codepostal character varying(255),
    nom character varying(255) NOT NULL,
    ville character varying(255),
    id_etablissement bigint NOT NULL
);


ALTER TABLE public.site OWNER TO eclipse;

--
-- TOC entry 2042 (class 1259 OID 53339)
-- Dependencies: 6 2041
-- Name: site_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE site_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.site_id_seq OWNER TO eclipse;

--
-- TOC entry 3023 (class 0 OID 0)
-- Dependencies: 2042
-- Name: site_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE site_id_seq OWNED BY site.id;


--
-- TOC entry 2043 (class 1259 OID 53341)
-- Dependencies: 6
-- Name: site_suivi; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE site_suivi (
    id bigint NOT NULL,
    datemaj timestamp without time zone NOT NULL,
    majpar character varying(255) NOT NULL,
    id_site bigint
);


ALTER TABLE public.site_suivi OWNER TO eclipse;

--
-- TOC entry 2044 (class 1259 OID 53344)
-- Dependencies: 2043 6
-- Name: site_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE site_suivi_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.site_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 3024 (class 0 OID 0)
-- Dependencies: 2044
-- Name: site_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE site_suivi_id_seq OWNED BY site_suivi.id;


--
-- TOC entry 2045 (class 1259 OID 53346)
-- Dependencies: 6
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
    id_pharmacie bigint NOT NULL
);


ALTER TABLE public.stockage OWNER TO eclipse;

--
-- TOC entry 2046 (class 1259 OID 53352)
-- Dependencies: 6 2045
-- Name: stockage_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE stockage_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.stockage_id_seq OWNER TO eclipse;

--
-- TOC entry 3025 (class 0 OID 0)
-- Dependencies: 2046
-- Name: stockage_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE stockage_id_seq OWNED BY stockage.id;


--
-- TOC entry 2047 (class 1259 OID 53354)
-- Dependencies: 6
-- Name: theme; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE theme (
    id bigint NOT NULL,
    libelle character varying(255),
    id_grille_modele bigint NOT NULL
);


ALTER TABLE public.theme OWNER TO eclipse;

--
-- TOC entry 2048 (class 1259 OID 53357)
-- Dependencies: 6 2047
-- Name: theme_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE theme_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.theme_id_seq OWNER TO eclipse;

--
-- TOC entry 3026 (class 0 OID 0)
-- Dependencies: 2048
-- Name: theme_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE theme_id_seq OWNED BY theme.id;


--
-- TOC entry 2321 (class 2604 OID 53359)
-- Dependencies: 1847 1846
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE arc_investigateur_sigrec ALTER COLUMN id SET DEFAULT nextval('arc_investigateur_sigrec_id_seq'::regclass);


--
-- TOC entry 2322 (class 2604 OID 53360)
-- Dependencies: 1849 1848
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE arc_promoteur_sigrec ALTER COLUMN id SET DEFAULT nextval('arc_promoteur_sigrec_id_seq'::regclass);


--
-- TOC entry 2323 (class 2604 OID 53361)
-- Dependencies: 1852 1851
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE assurance_sigrec ALTER COLUMN id SET DEFAULT nextval('assurance_sigrec_id_seq'::regclass);


--
-- TOC entry 2324 (class 2604 OID 53362)
-- Dependencies: 1854 1853
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE bras ALTER COLUMN id SET DEFAULT nextval('bras_id_seq'::regclass);


--
-- TOC entry 2325 (class 2604 OID 53363)
-- Dependencies: 1856 1855
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE categorie ALTER COLUMN id SET DEFAULT nextval('categorie_id_seq'::regclass);


--
-- TOC entry 2326 (class 2604 OID 53364)
-- Dependencies: 1858 1857
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE centre_sigrec ALTER COLUMN id SET DEFAULT nextval('centre_sigrec_id_seq'::regclass);


--
-- TOC entry 2327 (class 2604 OID 53365)
-- Dependencies: 1860 1859
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE co_investigateur_sigrec ALTER COLUMN id SET DEFAULT nextval('co_investigateur_sigrec_id_seq'::regclass);


--
-- TOC entry 2328 (class 2604 OID 53366)
-- Dependencies: 1862 1861
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE conditionnement ALTER COLUMN id SET DEFAULT nextval('conditionnement_id_seq'::regclass);


--
-- TOC entry 2329 (class 2604 OID 53367)
-- Dependencies: 1864 1863
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE contact_sigrec ALTER COLUMN id SET DEFAULT nextval('contact_sigrec_id_seq'::regclass);


--
-- TOC entry 2330 (class 2604 OID 53368)
-- Dependencies: 1866 1865
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE cro_sigrec ALTER COLUMN id SET DEFAULT nextval('cro_sigrec_id_seq'::regclass);


--
-- TOC entry 2331 (class 2604 OID 53369)
-- Dependencies: 1868 1867
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE dispensation ALTER COLUMN id SET DEFAULT nextval('dispensation_id_seq'::regclass);


--
-- TOC entry 2332 (class 2604 OID 53370)
-- Dependencies: 1871 1870
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE donnees_prevision ALTER COLUMN id SET DEFAULT nextval('donnees_prevision_id_seq'::regclass);


--
-- TOC entry 2333 (class 2604 OID 53371)
-- Dependencies: 1873 1872
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE dotation ALTER COLUMN id SET DEFAULT nextval('dotation_id_seq'::regclass);


--
-- TOC entry 2334 (class 2604 OID 53372)
-- Dependencies: 1875 1874
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE element_to_check ALTER COLUMN id SET DEFAULT nextval('element_to_check_id_seq'::regclass);


--
-- TOC entry 2335 (class 2604 OID 53373)
-- Dependencies: 1937 1876
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai ALTER COLUMN id SET DEFAULT nextval('essai_id_seq'::regclass);


--
-- TOC entry 2336 (class 2604 OID 53374)
-- Dependencies: 1878 1877
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_commentaire_detail_administratif_archi ALTER COLUMN id SET DEFAULT nextval('essai_commentaire_detail_administratif_archi_id_seq'::regclass);


--
-- TOC entry 2337 (class 2604 OID 53375)
-- Dependencies: 1880 1879
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_commentaire_detail_faisabilite ALTER COLUMN id SET DEFAULT nextval('essai_commentaire_detail_faisabilite_id_seq'::regclass);


--
-- TOC entry 2338 (class 2604 OID 53376)
-- Dependencies: 1882 1881
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_commentaire_detail_recherche ALTER COLUMN id SET DEFAULT nextval('essai_commentaire_detail_recherche_id_seq'::regclass);


--
-- TOC entry 2339 (class 2604 OID 53377)
-- Dependencies: 1884 1883
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_administratif ALTER COLUMN id SET DEFAULT nextval('essai_detail_administratif_id_seq'::regclass);


--
-- TOC entry 2340 (class 2604 OID 53378)
-- Dependencies: 1886 1885
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_administratif_suivi ALTER COLUMN id SET DEFAULT nextval('essai_detail_administratif_suivi_id_seq'::regclass);


--
-- TOC entry 2341 (class 2604 OID 53379)
-- Dependencies: 1888 1887
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_autres_documents ALTER COLUMN id SET DEFAULT nextval('essai_detail_autres_documents_id_seq'::regclass);


--
-- TOC entry 2342 (class 2604 OID 53380)
-- Dependencies: 1890 1889
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_autres_documents_suivi ALTER COLUMN id SET DEFAULT nextval('essai_detail_autres_documents_suivi_id_seq'::regclass);


--
-- TOC entry 2343 (class 2604 OID 53381)
-- Dependencies: 1892 1891
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_contacts ALTER COLUMN id SET DEFAULT nextval('essai_detail_contacts_id_seq'::regclass);


--
-- TOC entry 2344 (class 2604 OID 53382)
-- Dependencies: 1894 1893
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_contacts_suivi ALTER COLUMN id SET DEFAULT nextval('essai_detail_contacts_suivi_id_seq'::regclass);


--
-- TOC entry 2345 (class 2604 OID 53383)
-- Dependencies: 1896 1895
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_dates ALTER COLUMN id SET DEFAULT nextval('essai_detail_dates_id_seq'::regclass);


--
-- TOC entry 2346 (class 2604 OID 53384)
-- Dependencies: 1898 1897
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_dates_suivi ALTER COLUMN id SET DEFAULT nextval('essai_detail_dates_suivi_id_seq'::regclass);


--
-- TOC entry 2347 (class 2604 OID 53385)
-- Dependencies: 1900 1899
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_design ALTER COLUMN id SET DEFAULT nextval('essai_detail_design_id_seq'::regclass);


--
-- TOC entry 2348 (class 2604 OID 53386)
-- Dependencies: 1902 1901
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_design_suivi ALTER COLUMN id SET DEFAULT nextval('essai_detail_design_suivi_id_seq'::regclass);


--
-- TOC entry 2349 (class 2604 OID 53387)
-- Dependencies: 1904 1903
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_etat ALTER COLUMN id SET DEFAULT nextval('essai_detail_etat_id_seq'::regclass);


--
-- TOC entry 2350 (class 2604 OID 53388)
-- Dependencies: 1906 1905
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_faisabilite ALTER COLUMN id SET DEFAULT nextval('essai_detail_faisabilite_id_seq'::regclass);


--
-- TOC entry 2351 (class 2604 OID 53389)
-- Dependencies: 1909 1908
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_faisabilite_suivi ALTER COLUMN id SET DEFAULT nextval('essai_detail_faisabilite_suivi_id_seq'::regclass);


--
-- TOC entry 2352 (class 2604 OID 53390)
-- Dependencies: 1911 1910
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_pharma ALTER COLUMN id SET DEFAULT nextval('essai_detail_pharma_id_seq'::regclass);


--
-- TOC entry 2353 (class 2604 OID 53391)
-- Dependencies: 1914 1913
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_pharma_suivi ALTER COLUMN id SET DEFAULT nextval('essai_detail_pharma_suivi_id_seq'::regclass);


--
-- TOC entry 2354 (class 2604 OID 53392)
-- Dependencies: 1916 1915
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_produit ALTER COLUMN id SET DEFAULT nextval('essai_detail_produit_id_seq'::regclass);


--
-- TOC entry 2355 (class 2604 OID 53393)
-- Dependencies: 1918 1917
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_produit_suivi ALTER COLUMN id SET DEFAULT nextval('essai_detail_produit_suivi_id_seq'::regclass);


--
-- TOC entry 2356 (class 2604 OID 53394)
-- Dependencies: 1920 1919
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_recherche ALTER COLUMN id SET DEFAULT nextval('essai_detail_recherche_id_seq'::regclass);


--
-- TOC entry 2357 (class 2604 OID 53395)
-- Dependencies: 1922 1921
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_recherche_sigrec ALTER COLUMN id SET DEFAULT nextval('essai_detail_recherche_sigrec_id_seq'::regclass);


--
-- TOC entry 2358 (class 2604 OID 53396)
-- Dependencies: 1924 1923
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_recherche_suivi ALTER COLUMN id SET DEFAULT nextval('essai_detail_recherche_suivi_id_seq'::regclass);


--
-- TOC entry 2359 (class 2604 OID 53397)
-- Dependencies: 1926 1925
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_surcout ALTER COLUMN id SET DEFAULT nextval('essai_detail_surcout_id_seq'::regclass);


--
-- TOC entry 2360 (class 2604 OID 53398)
-- Dependencies: 1928 1927
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_surcout_suivi ALTER COLUMN id SET DEFAULT nextval('essai_detail_surcout_suivi_id_seq'::regclass);


--
-- TOC entry 2361 (class 2604 OID 53399)
-- Dependencies: 1930 1929
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_document_detail_administratif ALTER COLUMN id SET DEFAULT nextval('essai_document_detail_administratif_id_seq'::regclass);


--
-- TOC entry 2362 (class 2604 OID 53400)
-- Dependencies: 1932 1931
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_document_detail_autres_documents ALTER COLUMN id SET DEFAULT nextval('essai_document_detail_autres_documents_id_seq'::regclass);


--
-- TOC entry 2363 (class 2604 OID 53401)
-- Dependencies: 1934 1933
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_document_detail_pharma ALTER COLUMN id SET DEFAULT nextval('essai_document_detail_pharma_id_seq'::regclass);


--
-- TOC entry 2364 (class 2604 OID 53402)
-- Dependencies: 1936 1935
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_document_detail_surcout ALTER COLUMN id SET DEFAULT nextval('essai_document_detail_surcout_id_seq'::regclass);


--
-- TOC entry 2365 (class 2604 OID 53403)
-- Dependencies: 1940 1939
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_sigrec ALTER COLUMN id SET DEFAULT nextval('essai_sigrec_id_seq'::regclass);


--
-- TOC entry 2366 (class 2604 OID 53404)
-- Dependencies: 1942 1941
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_suivi ALTER COLUMN id SET DEFAULT nextval('essai_suivi_id_seq'::regclass);


--
-- TOC entry 2367 (class 2604 OID 53405)
-- Dependencies: 1944 1943
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE etablissement ALTER COLUMN id SET DEFAULT nextval('etablissement_id_seq'::regclass);


--
-- TOC entry 2368 (class 2604 OID 53406)
-- Dependencies: 1946 1945
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE etablissement_suivi ALTER COLUMN id SET DEFAULT nextval('etablissement_suivi_id_seq'::regclass);


--
-- TOC entry 2369 (class 2604 OID 53407)
-- Dependencies: 1948 1947
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE evenement ALTER COLUMN id SET DEFAULT nextval('evenement_id_seq'::regclass);


--
-- TOC entry 2370 (class 2604 OID 53408)
-- Dependencies: 1950 1949
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE evenement_suivi ALTER COLUMN id SET DEFAULT nextval('evenement_suivi_id_seq'::regclass);


--
-- TOC entry 2371 (class 2604 OID 53409)
-- Dependencies: 1952 1951
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE grille ALTER COLUMN id SET DEFAULT nextval('grille_id_seq'::regclass);


--
-- TOC entry 2372 (class 2604 OID 53410)
-- Dependencies: 1954 1953
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE grille_modele ALTER COLUMN id SET DEFAULT nextval('grille_modele_id_seq'::regclass);


--
-- TOC entry 2373 (class 2604 OID 53411)
-- Dependencies: 1956 1955
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE habilitation ALTER COLUMN id SET DEFAULT nextval('habilitation_id_seq'::regclass);


--
-- TOC entry 2374 (class 2604 OID 53412)
-- Dependencies: 1958 1957
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE historique_patient ALTER COLUMN id SET DEFAULT nextval('historique_patient_id_seq'::regclass);


--
-- TOC entry 2375 (class 2604 OID 53413)
-- Dependencies: 1960 1959
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE incident ALTER COLUMN id SET DEFAULT nextval('incident_id_seq'::regclass);


--
-- TOC entry 2376 (class 2604 OID 53414)
-- Dependencies: 1962 1961
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE incident_suivi ALTER COLUMN id SET DEFAULT nextval('incident_suivi_id_seq'::regclass);


--
-- TOC entry 2377 (class 2604 OID 53415)
-- Dependencies: 1964 1963
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE inclusion ALTER COLUMN id SET DEFAULT nextval('inclusion_id_seq'::regclass);


--
-- TOC entry 2378 (class 2604 OID 53416)
-- Dependencies: 1967 1966
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE investigateur_sigrec ALTER COLUMN id SET DEFAULT nextval('investigateur_sigrec_id_seq'::regclass);


--
-- TOC entry 2379 (class 2604 OID 53417)
-- Dependencies: 1969 1968
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE item ALTER COLUMN id SET DEFAULT nextval('item_id_seq'::regclass);


--
-- TOC entry 2380 (class 2604 OID 53418)
-- Dependencies: 1982 1979
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE mvtstock ALTER COLUMN id SET DEFAULT nextval('mvtstock_id_seq'::regclass);


--
-- TOC entry 2381 (class 2604 OID 53419)
-- Dependencies: 1981 1980
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE mvtstock_document ALTER COLUMN id SET DEFAULT nextval('mvtstock_document_id_seq'::regclass);


--
-- TOC entry 2382 (class 2604 OID 53420)
-- Dependencies: 1984 1983
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE ordonnancier_dispensation ALTER COLUMN id SET DEFAULT nextval('ordonnancier_dispensation_id_seq'::regclass);


--
-- TOC entry 2383 (class 2604 OID 53421)
-- Dependencies: 1986 1985
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE ordonnancier_fab_reconst ALTER COLUMN id SET DEFAULT nextval('ordonnancier_fab_reconst_id_seq'::regclass);


--
-- TOC entry 2384 (class 2604 OID 53422)
-- Dependencies: 1988 1987
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE patient ALTER COLUMN id SET DEFAULT nextval('patient_id_seq'::regclass);


--
-- TOC entry 2385 (class 2604 OID 53423)
-- Dependencies: 1990 1989
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE patient_suivi ALTER COLUMN id SET DEFAULT nextval('patient_suivi_id_seq'::regclass);


--
-- TOC entry 2386 (class 2604 OID 53424)
-- Dependencies: 1992 1991
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE personne ALTER COLUMN id SET DEFAULT nextval('personne_id_seq'::regclass);


--
-- TOC entry 2387 (class 2604 OID 53425)
-- Dependencies: 1994 1993
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE personne_suivi ALTER COLUMN id SET DEFAULT nextval('personne_suivi_id_seq'::regclass);


--
-- TOC entry 2390 (class 2604 OID 53426)
-- Dependencies: 1996 1995
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE pharmacie ALTER COLUMN id SET DEFAULT nextval('pharmacie_id_seq'::regclass);


--
-- TOC entry 2391 (class 2604 OID 53427)
-- Dependencies: 1999 1998
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE pharmacie_suivi ALTER COLUMN id SET DEFAULT nextval('pharmacie_suivi_id_seq'::regclass);


--
-- TOC entry 2415 (class 2604 OID 54659)
-- Dependencies: 2053 2054 2054
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE pharmacien_document_pharmacien ALTER COLUMN id SET DEFAULT nextval('pharmacien_document_pharmacien_id_seq'::regclass);


--
-- TOC entry 2392 (class 2604 OID 53428)
-- Dependencies: 2002 2001
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE pole ALTER COLUMN id SET DEFAULT nextval('pole_id_seq'::regclass);


--
-- TOC entry 2393 (class 2604 OID 53429)
-- Dependencies: 2004 2003
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE pole_suivi ALTER COLUMN id SET DEFAULT nextval('pole_suivi_id_seq'::regclass);


--
-- TOC entry 2394 (class 2604 OID 53430)
-- Dependencies: 2006 2005
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE prescription ALTER COLUMN id SET DEFAULT nextval('prescription_id_seq'::regclass);


--
-- TOC entry 2395 (class 2604 OID 53431)
-- Dependencies: 2008 2007
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE prescription_type ALTER COLUMN id SET DEFAULT nextval('prescription_type_id_seq'::regclass);


--
-- TOC entry 2396 (class 2604 OID 53432)
-- Dependencies: 2010 2009
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE prevision_sigrec ALTER COLUMN id SET DEFAULT nextval('prevision_sigrec_id_seq'::regclass);


--
-- TOC entry 2397 (class 2604 OID 53433)
-- Dependencies: 2018 2011
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE produit ALTER COLUMN id SET DEFAULT nextval('produit_id_seq'::regclass);


--
-- TOC entry 2398 (class 2604 OID 53434)
-- Dependencies: 2013 2012
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE produit_detail_logistique ALTER COLUMN id SET DEFAULT nextval('produit_detail_logistique_id_seq'::regclass);


--
-- TOC entry 2399 (class 2604 OID 53435)
-- Dependencies: 2015 2014
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE produit_detail_stockage ALTER COLUMN id SET DEFAULT nextval('produit_detail_stockage_id_seq'::regclass);


--
-- TOC entry 2400 (class 2604 OID 53436)
-- Dependencies: 2017 2016
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE produit_document_actes_pharma ALTER COLUMN id SET DEFAULT nextval('produit_document_actes_pharma_id_seq'::regclass);


--
-- TOC entry 2401 (class 2604 OID 53437)
-- Dependencies: 2020 2019
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE produit_prescrit ALTER COLUMN id SET DEFAULT nextval('produit_prescrit_id_seq'::regclass);


--
-- TOC entry 2402 (class 2604 OID 53438)
-- Dependencies: 2023 2022
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE produit_suivi ALTER COLUMN id SET DEFAULT nextval('produit_suivi_id_seq'::regclass);


--
-- TOC entry 2403 (class 2604 OID 53439)
-- Dependencies: 2026 2025
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE promoteur ALTER COLUMN id SET DEFAULT nextval('promoteur_id_seq'::regclass);


--
-- TOC entry 2404 (class 2604 OID 53440)
-- Dependencies: 2028 2027
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE promoteur_sigrec ALTER COLUMN id SET DEFAULT nextval('promoteur_sigrec_id_seq'::regclass);


--
-- TOC entry 2405 (class 2604 OID 53441)
-- Dependencies: 2030 2029
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE promoteur_suivi ALTER COLUMN id SET DEFAULT nextval('promoteur_suivi_id_seq'::regclass);


--
-- TOC entry 2406 (class 2604 OID 53442)
-- Dependencies: 2032 2031
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE regle_surcout ALTER COLUMN id SET DEFAULT nextval('regle_surcout_id_seq'::regclass);


--
-- TOC entry 2407 (class 2604 OID 53443)
-- Dependencies: 2034 2033
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE retour_patient ALTER COLUMN id SET DEFAULT nextval('retour_patient_id_seq'::regclass);


--
-- TOC entry 2408 (class 2604 OID 53444)
-- Dependencies: 2036 2035
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE sequence ALTER COLUMN id SET DEFAULT nextval('sequence_id_seq'::regclass);


--
-- TOC entry 2409 (class 2604 OID 53445)
-- Dependencies: 2038 2037
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE service ALTER COLUMN id SET DEFAULT nextval('service_id_seq'::regclass);


--
-- TOC entry 2410 (class 2604 OID 53446)
-- Dependencies: 2040 2039
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE service_suivi ALTER COLUMN id SET DEFAULT nextval('service_suivi_id_seq'::regclass);


--
-- TOC entry 2411 (class 2604 OID 53447)
-- Dependencies: 2042 2041
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE site ALTER COLUMN id SET DEFAULT nextval('site_id_seq'::regclass);


--
-- TOC entry 2412 (class 2604 OID 53448)
-- Dependencies: 2044 2043
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE site_suivi ALTER COLUMN id SET DEFAULT nextval('site_suivi_id_seq'::regclass);


--
-- TOC entry 2413 (class 2604 OID 53449)
-- Dependencies: 2046 2045
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE stockage ALTER COLUMN id SET DEFAULT nextval('stockage_id_seq'::regclass);


--
-- TOC entry 2414 (class 2604 OID 53450)
-- Dependencies: 2048 2047
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE theme ALTER COLUMN id SET DEFAULT nextval('theme_id_seq'::regclass);


--
-- TOC entry 2417 (class 2606 OID 53452)
-- Dependencies: 1846 1846
-- Name: arc_investigateur_sigrec_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY arc_investigateur_sigrec
    ADD CONSTRAINT arc_investigateur_sigrec_pkey PRIMARY KEY (id);


--
-- TOC entry 2420 (class 2606 OID 53454)
-- Dependencies: 1848 1848
-- Name: arc_promoteur_sigrec_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY arc_promoteur_sigrec
    ADD CONSTRAINT arc_promoteur_sigrec_pkey PRIMARY KEY (id);


--
-- TOC entry 2423 (class 2606 OID 53456)
-- Dependencies: 1850 1850 1850
-- Name: arcinvestigateur_service_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY arcinvestigateur_service
    ADD CONSTRAINT arcinvestigateur_service_pkey PRIMARY KEY (id_arcinvestigateur, id_service);


--
-- TOC entry 2425 (class 2606 OID 53458)
-- Dependencies: 1851 1851
-- Name: assurance_sigrec_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY assurance_sigrec
    ADD CONSTRAINT assurance_sigrec_pkey PRIMARY KEY (id);


--
-- TOC entry 2428 (class 2606 OID 53460)
-- Dependencies: 1853 1853
-- Name: bras_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY bras
    ADD CONSTRAINT bras_pkey PRIMARY KEY (id);


--
-- TOC entry 2432 (class 2606 OID 53462)
-- Dependencies: 1855 1855
-- Name: categorie_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY categorie
    ADD CONSTRAINT categorie_pkey PRIMARY KEY (id);


--
-- TOC entry 2435 (class 2606 OID 53464)
-- Dependencies: 1857 1857
-- Name: centre_sigrec_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY centre_sigrec
    ADD CONSTRAINT centre_sigrec_pkey PRIMARY KEY (id);


--
-- TOC entry 2438 (class 2606 OID 53466)
-- Dependencies: 1859 1859
-- Name: co_investigateur_sigrec_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY co_investigateur_sigrec
    ADD CONSTRAINT co_investigateur_sigrec_pkey PRIMARY KEY (id);


--
-- TOC entry 2441 (class 2606 OID 53468)
-- Dependencies: 1861 1861
-- Name: conditionnement_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY conditionnement
    ADD CONSTRAINT conditionnement_pkey PRIMARY KEY (id);


--
-- TOC entry 2444 (class 2606 OID 53470)
-- Dependencies: 1863 1863
-- Name: contact_sigrec_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY contact_sigrec
    ADD CONSTRAINT contact_sigrec_pkey PRIMARY KEY (id);


--
-- TOC entry 2446 (class 2606 OID 53472)
-- Dependencies: 1865 1865
-- Name: cro_sigrec_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY cro_sigrec
    ADD CONSTRAINT cro_sigrec_pkey PRIMARY KEY (id);


--
-- TOC entry 2450 (class 2606 OID 53474)
-- Dependencies: 1867 1867
-- Name: dispensation_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY dispensation
    ADD CONSTRAINT dispensation_pkey PRIMARY KEY (id);


--
-- TOC entry 2455 (class 2606 OID 53476)
-- Dependencies: 1869 1869
-- Name: dispositif_medical_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY dispositif_medical
    ADD CONSTRAINT dispositif_medical_pkey PRIMARY KEY (id);


--
-- TOC entry 2457 (class 2606 OID 53478)
-- Dependencies: 1870 1870
-- Name: donnees_prevision_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY donnees_prevision
    ADD CONSTRAINT donnees_prevision_pkey PRIMARY KEY (id);


--
-- TOC entry 2459 (class 2606 OID 53480)
-- Dependencies: 1872 1872
-- Name: dotation_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY dotation
    ADD CONSTRAINT dotation_pkey PRIMARY KEY (id);


--
-- TOC entry 2467 (class 2606 OID 53482)
-- Dependencies: 1874 1874
-- Name: element_to_check_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY element_to_check
    ADD CONSTRAINT element_to_check_pkey PRIMARY KEY (id);


--
-- TOC entry 2477 (class 2606 OID 53484)
-- Dependencies: 1877 1877
-- Name: essai_commentaire_detail_administratif_archi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_commentaire_detail_administratif_archi
    ADD CONSTRAINT essai_commentaire_detail_administratif_archi_pkey PRIMARY KEY (id);


--
-- TOC entry 2480 (class 2606 OID 53486)
-- Dependencies: 1879 1879
-- Name: essai_commentaire_detail_faisabilite_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_commentaire_detail_faisabilite
    ADD CONSTRAINT essai_commentaire_detail_faisabilite_pkey PRIMARY KEY (id);


--
-- TOC entry 2483 (class 2606 OID 53488)
-- Dependencies: 1881 1881
-- Name: essai_commentaire_detail_recherche_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_commentaire_detail_recherche
    ADD CONSTRAINT essai_commentaire_detail_recherche_pkey PRIMARY KEY (id);


--
-- TOC entry 2486 (class 2606 OID 53490)
-- Dependencies: 1883 1883
-- Name: essai_detail_administratif_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_administratif
    ADD CONSTRAINT essai_detail_administratif_pkey PRIMARY KEY (id);


--
-- TOC entry 2488 (class 2606 OID 53492)
-- Dependencies: 1885 1885
-- Name: essai_detail_administratif_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_administratif_suivi
    ADD CONSTRAINT essai_detail_administratif_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2491 (class 2606 OID 53494)
-- Dependencies: 1887 1887
-- Name: essai_detail_autres_documents_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_autres_documents
    ADD CONSTRAINT essai_detail_autres_documents_pkey PRIMARY KEY (id);


--
-- TOC entry 2493 (class 2606 OID 53496)
-- Dependencies: 1889 1889
-- Name: essai_detail_autres_documents_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_autres_documents_suivi
    ADD CONSTRAINT essai_detail_autres_documents_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2496 (class 2606 OID 53498)
-- Dependencies: 1891 1891
-- Name: essai_detail_contacts_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_contacts
    ADD CONSTRAINT essai_detail_contacts_pkey PRIMARY KEY (id);


--
-- TOC entry 2498 (class 2606 OID 53500)
-- Dependencies: 1893 1893
-- Name: essai_detail_contacts_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_contacts_suivi
    ADD CONSTRAINT essai_detail_contacts_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2501 (class 2606 OID 53502)
-- Dependencies: 1895 1895
-- Name: essai_detail_dates_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_dates
    ADD CONSTRAINT essai_detail_dates_pkey PRIMARY KEY (id);


--
-- TOC entry 2503 (class 2606 OID 53504)
-- Dependencies: 1897 1897
-- Name: essai_detail_dates_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_dates_suivi
    ADD CONSTRAINT essai_detail_dates_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2506 (class 2606 OID 53506)
-- Dependencies: 1899 1899
-- Name: essai_detail_design_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_design
    ADD CONSTRAINT essai_detail_design_pkey PRIMARY KEY (id);


--
-- TOC entry 2508 (class 2606 OID 53508)
-- Dependencies: 1901 1901
-- Name: essai_detail_design_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_design_suivi
    ADD CONSTRAINT essai_detail_design_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2511 (class 2606 OID 53510)
-- Dependencies: 1903 1903
-- Name: essai_detail_etat_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_etat
    ADD CONSTRAINT essai_detail_etat_pkey PRIMARY KEY (id);


--
-- TOC entry 2514 (class 2606 OID 53512)
-- Dependencies: 1905 1905
-- Name: essai_detail_faisabilite_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_faisabilite
    ADD CONSTRAINT essai_detail_faisabilite_pkey PRIMARY KEY (id);


--
-- TOC entry 2516 (class 2606 OID 53514)
-- Dependencies: 1907 1907 1907
-- Name: essai_detail_faisabilite_service_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_faisabilite_service
    ADD CONSTRAINT essai_detail_faisabilite_service_pkey PRIMARY KEY (id_essai, id_service);


--
-- TOC entry 2518 (class 2606 OID 53516)
-- Dependencies: 1908 1908
-- Name: essai_detail_faisabilite_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_faisabilite_suivi
    ADD CONSTRAINT essai_detail_faisabilite_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2747 (class 2606 OID 54610)
-- Dependencies: 2049 2049 2049
-- Name: essai_detail_pharma_etablissement_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_pharma_etablissement
    ADD CONSTRAINT essai_detail_pharma_etablissement_pkey PRIMARY KEY (id_detail_pharma, id_etablissement);


--
-- TOC entry 2523 (class 2606 OID 53518)
-- Dependencies: 1912 1912 1912
-- Name: essai_detail_pharma_pharmacie_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_pharma_pharmacie
    ADD CONSTRAINT essai_detail_pharma_pharmacie_pkey PRIMARY KEY (id_detail_pharma, id_pharmacie);


--
-- TOC entry 2521 (class 2606 OID 53520)
-- Dependencies: 1910 1910
-- Name: essai_detail_pharma_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_pharma
    ADD CONSTRAINT essai_detail_pharma_pkey PRIMARY KEY (id);


--
-- TOC entry 2525 (class 2606 OID 53522)
-- Dependencies: 1913 1913
-- Name: essai_detail_pharma_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_pharma_suivi
    ADD CONSTRAINT essai_detail_pharma_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2528 (class 2606 OID 53524)
-- Dependencies: 1915 1915
-- Name: essai_detail_produit_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_produit
    ADD CONSTRAINT essai_detail_produit_pkey PRIMARY KEY (id);


--
-- TOC entry 2530 (class 2606 OID 53526)
-- Dependencies: 1917 1917
-- Name: essai_detail_produit_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_produit_suivi
    ADD CONSTRAINT essai_detail_produit_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2533 (class 2606 OID 53528)
-- Dependencies: 1919 1919
-- Name: essai_detail_recherche_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_recherche
    ADD CONSTRAINT essai_detail_recherche_pkey PRIMARY KEY (id);


--
-- TOC entry 2535 (class 2606 OID 53530)
-- Dependencies: 1921 1921
-- Name: essai_detail_recherche_sigrec_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_recherche_sigrec
    ADD CONSTRAINT essai_detail_recherche_sigrec_pkey PRIMARY KEY (id);


--
-- TOC entry 2537 (class 2606 OID 53532)
-- Dependencies: 1923 1923
-- Name: essai_detail_recherche_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_recherche_suivi
    ADD CONSTRAINT essai_detail_recherche_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2540 (class 2606 OID 53534)
-- Dependencies: 1925 1925
-- Name: essai_detail_surcout_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_surcout
    ADD CONSTRAINT essai_detail_surcout_pkey PRIMARY KEY (id);


--
-- TOC entry 2542 (class 2606 OID 53536)
-- Dependencies: 1927 1927
-- Name: essai_detail_surcout_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_surcout_suivi
    ADD CONSTRAINT essai_detail_surcout_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2545 (class 2606 OID 53538)
-- Dependencies: 1929 1929
-- Name: essai_document_detail_administratif_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_document_detail_administratif
    ADD CONSTRAINT essai_document_detail_administratif_pkey PRIMARY KEY (id);


--
-- TOC entry 2548 (class 2606 OID 53540)
-- Dependencies: 1931 1931
-- Name: essai_document_detail_autres_documents_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_document_detail_autres_documents
    ADD CONSTRAINT essai_document_detail_autres_documents_pkey PRIMARY KEY (id);


--
-- TOC entry 2550 (class 2606 OID 53542)
-- Dependencies: 1933 1933
-- Name: essai_document_detail_pharma_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_document_detail_pharma
    ADD CONSTRAINT essai_document_detail_pharma_pkey PRIMARY KEY (id);


--
-- TOC entry 2552 (class 2606 OID 53544)
-- Dependencies: 1935 1935
-- Name: essai_document_detail_surcout_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_document_detail_surcout
    ADD CONSTRAINT essai_document_detail_surcout_pkey PRIMARY KEY (id);


--
-- TOC entry 2473 (class 2606 OID 53546)
-- Dependencies: 1876 1876
-- Name: essai_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai
    ADD CONSTRAINT essai_pkey PRIMARY KEY (id);


--
-- TOC entry 2555 (class 2606 OID 53548)
-- Dependencies: 1938 1938 1938
-- Name: essai_service_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_service
    ADD CONSTRAINT essai_service_pkey PRIMARY KEY (id_essai, id_service);


--
-- TOC entry 2557 (class 2606 OID 53550)
-- Dependencies: 1939 1939
-- Name: essai_sigrec_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_sigrec
    ADD CONSTRAINT essai_sigrec_pkey PRIMARY KEY (id);


--
-- TOC entry 2561 (class 2606 OID 53552)
-- Dependencies: 1941 1941
-- Name: essai_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_suivi
    ADD CONSTRAINT essai_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2564 (class 2606 OID 53554)
-- Dependencies: 1943 1943
-- Name: etablissement_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY etablissement
    ADD CONSTRAINT etablissement_pkey PRIMARY KEY (id);


--
-- TOC entry 2566 (class 2606 OID 53556)
-- Dependencies: 1945 1945
-- Name: etablissement_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY etablissement_suivi
    ADD CONSTRAINT etablissement_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2569 (class 2606 OID 53558)
-- Dependencies: 1947 1947
-- Name: evenement_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY evenement
    ADD CONSTRAINT evenement_pkey PRIMARY KEY (id);


--
-- TOC entry 2572 (class 2606 OID 53560)
-- Dependencies: 1949 1949
-- Name: evenement_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY evenement_suivi
    ADD CONSTRAINT evenement_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2578 (class 2606 OID 53562)
-- Dependencies: 1953 1953
-- Name: grille_modele_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY grille_modele
    ADD CONSTRAINT grille_modele_pkey PRIMARY KEY (id);


--
-- TOC entry 2575 (class 2606 OID 53564)
-- Dependencies: 1951 1951
-- Name: grille_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY grille
    ADD CONSTRAINT grille_pkey PRIMARY KEY (id);


--
-- TOC entry 2580 (class 2606 OID 53566)
-- Dependencies: 1955 1955
-- Name: habilitation_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY habilitation
    ADD CONSTRAINT habilitation_pkey PRIMARY KEY (id);


--
-- TOC entry 2584 (class 2606 OID 53568)
-- Dependencies: 1957 1957
-- Name: historique_patient_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY historique_patient
    ADD CONSTRAINT historique_patient_pkey PRIMARY KEY (id);


--
-- TOC entry 2588 (class 2606 OID 53570)
-- Dependencies: 1959 1959
-- Name: incident_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY incident
    ADD CONSTRAINT incident_pkey PRIMARY KEY (id);


--
-- TOC entry 2591 (class 2606 OID 53572)
-- Dependencies: 1961 1961
-- Name: incident_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY incident_suivi
    ADD CONSTRAINT incident_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2595 (class 2606 OID 53574)
-- Dependencies: 1963 1963
-- Name: inclusion_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY inclusion
    ADD CONSTRAINT inclusion_pkey PRIMARY KEY (id);


--
-- TOC entry 2597 (class 2606 OID 53576)
-- Dependencies: 1965 1965 1965
-- Name: investigateur_service_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY investigateur_service
    ADD CONSTRAINT investigateur_service_pkey PRIMARY KEY (id_investigateur, id_service);


--
-- TOC entry 2599 (class 2606 OID 53578)
-- Dependencies: 1966 1966
-- Name: investigateur_sigrec_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY investigateur_sigrec
    ADD CONSTRAINT investigateur_sigrec_pkey PRIMARY KEY (id);


--
-- TOC entry 2602 (class 2606 OID 53580)
-- Dependencies: 1968 1968
-- Name: item_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY item
    ADD CONSTRAINT item_pkey PRIMARY KEY (id);


--
-- TOC entry 2604 (class 2606 OID 53582)
-- Dependencies: 1970 1970 1970
-- Name: item_regle_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY item_regle
    ADD CONSTRAINT item_regle_pkey PRIMARY KEY (id_regle, id_item);


--
-- TOC entry 2606 (class 2606 OID 53584)
-- Dependencies: 1971 1971
-- Name: medicament_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY medicament
    ADD CONSTRAINT medicament_pkey PRIMARY KEY (id);


--
-- TOC entry 2608 (class 2606 OID 53586)
-- Dependencies: 1972 1972
-- Name: mvt_approvisionnement_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY mvt_approvisionnement
    ADD CONSTRAINT mvt_approvisionnement_pkey PRIMARY KEY (id);


--
-- TOC entry 2610 (class 2606 OID 53588)
-- Dependencies: 1973 1973
-- Name: mvt_autre_sortie_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY mvt_autre_sortie
    ADD CONSTRAINT mvt_autre_sortie_pkey PRIMARY KEY (id);


--
-- TOC entry 2612 (class 2606 OID 53590)
-- Dependencies: 1974 1974
-- Name: mvt_cession_pui_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY mvt_cession_pui
    ADD CONSTRAINT mvt_cession_pui_pkey PRIMARY KEY (id);


--
-- TOC entry 2614 (class 2606 OID 53592)
-- Dependencies: 1975 1975
-- Name: mvt_destruction_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY mvt_destruction
    ADD CONSTRAINT mvt_destruction_pkey PRIMARY KEY (id);


--
-- TOC entry 2621 (class 2606 OID 53594)
-- Dependencies: 1977 1977
-- Name: mvt_dispensation_globale_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY mvt_dispensation_globale
    ADD CONSTRAINT mvt_dispensation_globale_pkey PRIMARY KEY (id);


--
-- TOC entry 2618 (class 2606 OID 53596)
-- Dependencies: 1976 1976
-- Name: mvt_dispensation_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY mvt_dispensation
    ADD CONSTRAINT mvt_dispensation_pkey PRIMARY KEY (id);


--
-- TOC entry 2753 (class 2606 OID 54648)
-- Dependencies: 2052 2052
-- Name: mvt_preparation_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY mvt_preparation
    ADD CONSTRAINT mvt_preparation_pkey PRIMARY KEY (id);


--
-- TOC entry 2751 (class 2606 OID 54638)
-- Dependencies: 2051 2051
-- Name: mvt_preparationentree_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY mvt_preparationentree
    ADD CONSTRAINT mvt_preparationentree_pkey PRIMARY KEY (id);


--
-- TOC entry 2623 (class 2606 OID 53598)
-- Dependencies: 1978 1978
-- Name: mvt_retour_promoteur_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY mvt_retour_promoteur
    ADD CONSTRAINT mvt_retour_promoteur_pkey PRIMARY KEY (id);


--
-- TOC entry 2632 (class 2606 OID 53600)
-- Dependencies: 1980 1980
-- Name: mvtstock_document_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY mvtstock_document
    ADD CONSTRAINT mvtstock_document_pkey PRIMARY KEY (id);


--
-- TOC entry 2630 (class 2606 OID 53602)
-- Dependencies: 1979 1979
-- Name: mvtstock_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY mvtstock
    ADD CONSTRAINT mvtstock_pkey PRIMARY KEY (id);


--
-- TOC entry 2635 (class 2606 OID 53604)
-- Dependencies: 1983 1983
-- Name: ordonnancier_dispensation_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY ordonnancier_dispensation
    ADD CONSTRAINT ordonnancier_dispensation_pkey PRIMARY KEY (id);


--
-- TOC entry 2638 (class 2606 OID 53606)
-- Dependencies: 1985 1985
-- Name: ordonnancier_fab_reconst_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY ordonnancier_fab_reconst
    ADD CONSTRAINT ordonnancier_fab_reconst_pkey PRIMARY KEY (id);


--
-- TOC entry 2640 (class 2606 OID 53608)
-- Dependencies: 1987 1987
-- Name: patient_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY patient
    ADD CONSTRAINT patient_pkey PRIMARY KEY (id);


--
-- TOC entry 2643 (class 2606 OID 53610)
-- Dependencies: 1989 1989
-- Name: patient_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY patient_suivi
    ADD CONSTRAINT patient_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2647 (class 2606 OID 53612)
-- Dependencies: 1991 1991
-- Name: personne_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY personne
    ADD CONSTRAINT personne_pkey PRIMARY KEY (id);


--
-- TOC entry 2650 (class 2606 OID 53614)
-- Dependencies: 1993 1993
-- Name: personne_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY personne_suivi
    ADD CONSTRAINT personne_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2653 (class 2606 OID 53616)
-- Dependencies: 1995 1995
-- Name: pharmacie_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY pharmacie
    ADD CONSTRAINT pharmacie_pkey PRIMARY KEY (id);


--
-- TOC entry 2655 (class 2606 OID 53618)
-- Dependencies: 1997 1997 1997
-- Name: pharmacie_site_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY pharmacie_site
    ADD CONSTRAINT pharmacie_site_pkey PRIMARY KEY (id_pharmacie, id_site);


--
-- TOC entry 2658 (class 2606 OID 53620)
-- Dependencies: 1998 1998
-- Name: pharmacie_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY pharmacie_suivi
    ADD CONSTRAINT pharmacie_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2756 (class 2606 OID 54664)
-- Dependencies: 2054 2054
-- Name: pharmacien_document_pharmacien_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY pharmacien_document_pharmacien
    ADD CONSTRAINT pharmacien_document_pharmacien_pkey PRIMARY KEY (id);


--
-- TOC entry 2660 (class 2606 OID 53622)
-- Dependencies: 2000 2000 2000
-- Name: pharmacien_pharmacie_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY pharmacien_pharmacie
    ADD CONSTRAINT pharmacien_pharmacie_pkey PRIMARY KEY (id_pharmacien, id_pharmacie);


--
-- TOC entry 2663 (class 2606 OID 53624)
-- Dependencies: 2001 2001
-- Name: pole_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY pole
    ADD CONSTRAINT pole_pkey PRIMARY KEY (id);


--
-- TOC entry 2666 (class 2606 OID 53626)
-- Dependencies: 2003 2003
-- Name: pole_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY pole_suivi
    ADD CONSTRAINT pole_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2749 (class 2606 OID 54625)
-- Dependencies: 2050 2050
-- Name: preparation_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY preparation
    ADD CONSTRAINT preparation_pkey PRIMARY KEY (id);


--
-- TOC entry 2672 (class 2606 OID 53628)
-- Dependencies: 2005 2005
-- Name: prescription_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY prescription
    ADD CONSTRAINT prescription_pkey PRIMARY KEY (id);


--
-- TOC entry 2677 (class 2606 OID 53630)
-- Dependencies: 2007 2007
-- Name: prescription_type_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY prescription_type
    ADD CONSTRAINT prescription_type_pkey PRIMARY KEY (id);


--
-- TOC entry 2679 (class 2606 OID 53632)
-- Dependencies: 2009 2009
-- Name: prevision_sigrec_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY prevision_sigrec
    ADD CONSTRAINT prevision_sigrec_pkey PRIMARY KEY (id);


--
-- TOC entry 2684 (class 2606 OID 53634)
-- Dependencies: 2012 2012
-- Name: produit_detail_logistique_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY produit_detail_logistique
    ADD CONSTRAINT produit_detail_logistique_pkey PRIMARY KEY (id);


--
-- TOC entry 2689 (class 2606 OID 53636)
-- Dependencies: 2014 2014
-- Name: produit_detail_stockage_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY produit_detail_stockage
    ADD CONSTRAINT produit_detail_stockage_pkey PRIMARY KEY (id);


--
-- TOC entry 2691 (class 2606 OID 53638)
-- Dependencies: 2016 2016
-- Name: produit_document_actes_pharma_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY produit_document_actes_pharma
    ADD CONSTRAINT produit_document_actes_pharma_pkey PRIMARY KEY (id);


--
-- TOC entry 2682 (class 2606 OID 53640)
-- Dependencies: 2011 2011
-- Name: produit_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY produit
    ADD CONSTRAINT produit_pkey PRIMARY KEY (id);


--
-- TOC entry 2696 (class 2606 OID 53642)
-- Dependencies: 2019 2019
-- Name: produit_prescrit_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY produit_prescrit
    ADD CONSTRAINT produit_prescrit_pkey PRIMARY KEY (id);


--
-- TOC entry 2698 (class 2606 OID 53644)
-- Dependencies: 2021 2021 2021
-- Name: produit_service_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY produit_service
    ADD CONSTRAINT produit_service_pkey PRIMARY KEY (id_produit, id_service);


--
-- TOC entry 2701 (class 2606 OID 53646)
-- Dependencies: 2022 2022
-- Name: produit_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY produit_suivi
    ADD CONSTRAINT produit_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2703 (class 2606 OID 53648)
-- Dependencies: 2024 2024
-- Name: produit_therapeutique_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY produit_therapeutique
    ADD CONSTRAINT produit_therapeutique_pkey PRIMARY KEY (id);


--
-- TOC entry 2705 (class 2606 OID 53650)
-- Dependencies: 2025 2025
-- Name: promoteur_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY promoteur
    ADD CONSTRAINT promoteur_pkey PRIMARY KEY (id);


--
-- TOC entry 2708 (class 2606 OID 53652)
-- Dependencies: 2027 2027
-- Name: promoteur_sigrec_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY promoteur_sigrec
    ADD CONSTRAINT promoteur_sigrec_pkey PRIMARY KEY (id);


--
-- TOC entry 2711 (class 2606 OID 53654)
-- Dependencies: 2029 2029
-- Name: promoteur_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY promoteur_suivi
    ADD CONSTRAINT promoteur_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2715 (class 2606 OID 53656)
-- Dependencies: 2031 2031
-- Name: regle_surcout_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY regle_surcout
    ADD CONSTRAINT regle_surcout_pkey PRIMARY KEY (id);


--
-- TOC entry 2723 (class 2606 OID 53658)
-- Dependencies: 2033 2033
-- Name: retour_patient_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY retour_patient
    ADD CONSTRAINT retour_patient_pkey PRIMARY KEY (id);


--
-- TOC entry 2726 (class 2606 OID 53660)
-- Dependencies: 2035 2035
-- Name: sequence_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY sequence
    ADD CONSTRAINT sequence_pkey PRIMARY KEY (id);


--
-- TOC entry 2729 (class 2606 OID 53662)
-- Dependencies: 2037 2037
-- Name: service_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY service
    ADD CONSTRAINT service_pkey PRIMARY KEY (id);


--
-- TOC entry 2732 (class 2606 OID 53664)
-- Dependencies: 2039 2039
-- Name: service_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY service_suivi
    ADD CONSTRAINT service_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2735 (class 2606 OID 53666)
-- Dependencies: 2041 2041
-- Name: site_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY site
    ADD CONSTRAINT site_pkey PRIMARY KEY (id);


--
-- TOC entry 2738 (class 2606 OID 53668)
-- Dependencies: 2043 2043
-- Name: site_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY site_suivi
    ADD CONSTRAINT site_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2742 (class 2606 OID 53670)
-- Dependencies: 2045 2045
-- Name: stockage_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY stockage
    ADD CONSTRAINT stockage_pkey PRIMARY KEY (id);


--
-- TOC entry 2745 (class 2606 OID 53672)
-- Dependencies: 2047 2047
-- Name: theme_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY theme
    ADD CONSTRAINT theme_pkey PRIMARY KEY (id);


--
-- TOC entry 2418 (class 1259 OID 53673)
-- Dependencies: 1846
-- Name: idx_arc_investigateur_essai_sigrec; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_arc_investigateur_essai_sigrec ON arc_investigateur_sigrec USING btree (id_essai);


--
-- TOC entry 2724 (class 1259 OID 53674)
-- Dependencies: 2035
-- Name: idx_bras_sequence; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_bras_sequence ON sequence USING btree (id_bras_sequence);


--
-- TOC entry 2429 (class 1259 OID 53675)
-- Dependencies: 1853
-- Name: idx_brase_parent; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_brase_parent ON bras USING btree (id_bras_parent);


--
-- TOC entry 2712 (class 1259 OID 53676)
-- Dependencies: 2031
-- Name: idx_categorie_regle; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_categorie_regle ON regle_surcout USING btree (id_categorie);


--
-- TOC entry 2439 (class 1259 OID 53677)
-- Dependencies: 1859
-- Name: idx_co_investigateur_essai_sigrec; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_co_investigateur_essai_sigrec ON co_investigateur_sigrec USING btree (id_essai);


--
-- TOC entry 2442 (class 1259 OID 53678)
-- Dependencies: 1861
-- Name: idx_conditionnement_produit; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_conditionnement_produit ON conditionnement USING btree (id_produit);


--
-- TOC entry 2692 (class 1259 OID 53679)
-- Dependencies: 2019
-- Name: idx_conditionnement_produit_prescrit; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_conditionnement_produit_prescrit ON produit_prescrit USING btree (id_conditionnement);


--
-- TOC entry 2716 (class 1259 OID 53680)
-- Dependencies: 2033
-- Name: idx_conditionnement_retourpatient; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_conditionnement_retourpatient ON retour_patient USING btree (id_conditionnement);


--
-- TOC entry 2426 (class 1259 OID 53681)
-- Dependencies: 1851
-- Name: idx_contact_assurance_sigrec; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_contact_assurance_sigrec ON assurance_sigrec USING btree (id_contact);


--
-- TOC entry 2436 (class 1259 OID 53682)
-- Dependencies: 1857
-- Name: idx_contact_centre_sigrec; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_contact_centre_sigrec ON centre_sigrec USING btree (id_contact);


--
-- TOC entry 2447 (class 1259 OID 53683)
-- Dependencies: 1865
-- Name: idx_contact_cro_sigrec; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_contact_cro_sigrec ON cro_sigrec USING btree (id_contact);


--
-- TOC entry 2706 (class 1259 OID 53684)
-- Dependencies: 2027
-- Name: idx_contact_promoteur_sigrec; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_contact_promoteur_sigrec ON promoteur_sigrec USING btree (id_contact);


--
-- TOC entry 2581 (class 1259 OID 53685)
-- Dependencies: 1955
-- Name: idx_detail_contacts_habilitation; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_detail_contacts_habilitation ON habilitation USING btree (id_detail_contacts);


--
-- TOC entry 2430 (class 1259 OID 53686)
-- Dependencies: 1853
-- Name: idx_detail_design_bras; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_detail_design_bras ON bras USING btree (id_detail_design);


--
-- TOC entry 2512 (class 1259 OID 53687)
-- Dependencies: 1903
-- Name: idx_detail_etat_essai; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_detail_etat_essai ON essai_detail_etat USING btree (id_essai);


--
-- TOC entry 2685 (class 1259 OID 53688)
-- Dependencies: 2014
-- Name: idx_detail_stockage_detail_logistique; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_detail_stockage_detail_logistique ON produit_detail_stockage USING btree (id_detail_logistique);


--
-- TOC entry 2717 (class 1259 OID 53689)
-- Dependencies: 2033
-- Name: idx_detailstockage_retourpatient; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_detailstockage_retourpatient ON retour_patient USING btree (id_detailstockage);


--
-- TOC entry 2619 (class 1259 OID 53690)
-- Dependencies: 1977
-- Name: idx_disp_globale_dotation; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_disp_globale_dotation ON mvt_dispensation_globale USING btree (id_dotation);


--
-- TOC entry 2451 (class 1259 OID 53691)
-- Dependencies: 1867
-- Name: idx_disp_ordon; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_disp_ordon ON dispensation USING btree (id_ordonnancier);


--
-- TOC entry 2452 (class 1259 OID 53692)
-- Dependencies: 1867
-- Name: idx_disp_pharmacie; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_disp_pharmacie ON dispensation USING btree (id_pharmacie);


--
-- TOC entry 2615 (class 1259 OID 53693)
-- Dependencies: 1976
-- Name: idx_dispensation_dispensation_produit; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_dispensation_dispensation_produit ON mvt_dispensation USING btree (id_dispensation);


--
-- TOC entry 2468 (class 1259 OID 53694)
-- Dependencies: 1874
-- Name: idx_dispensation_elementtocheck; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_dispensation_elementtocheck ON element_to_check USING btree (id_dispensation);


--
-- TOC entry 2460 (class 1259 OID 53695)
-- Dependencies: 1872
-- Name: idx_dotation_cond; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_dotation_cond ON dotation USING btree (id_conditionnement);


--
-- TOC entry 2461 (class 1259 OID 53696)
-- Dependencies: 1872
-- Name: idx_dotation_essai; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_dotation_essai ON dotation USING btree (id_essai);


--
-- TOC entry 2462 (class 1259 OID 53697)
-- Dependencies: 1872
-- Name: idx_dotation_personne; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_dotation_personne ON dotation USING btree (id_personne);


--
-- TOC entry 2463 (class 1259 OID 53698)
-- Dependencies: 1872
-- Name: idx_dotation_pharma; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_dotation_pharma ON dotation USING btree (id_pharmacie);


--
-- TOC entry 2464 (class 1259 OID 53699)
-- Dependencies: 1872
-- Name: idx_dotation_produit; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_dotation_produit ON dotation USING btree (id_produit);


--
-- TOC entry 2465 (class 1259 OID 53700)
-- Dependencies: 1872
-- Name: idx_dotation_service; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_dotation_service ON dotation USING btree (id_service);


--
-- TOC entry 2469 (class 1259 OID 53701)
-- Dependencies: 1874
-- Name: idx_elementtocheck_ordon; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_elementtocheck_ordon ON element_to_check USING btree (id_ordonnancier);


--
-- TOC entry 2470 (class 1259 OID 53702)
-- Dependencies: 1874
-- Name: idx_eltcheck_personne; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_eltcheck_personne ON element_to_check USING btree (id_personne);


--
-- TOC entry 2478 (class 1259 OID 53703)
-- Dependencies: 1877
-- Name: idx_essai_commentaire_detail_administratif; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_essai_commentaire_detail_administratif ON essai_commentaire_detail_administratif_archi USING btree (id_detailadministratif);


--
-- TOC entry 2481 (class 1259 OID 53704)
-- Dependencies: 1879
-- Name: idx_essai_commentaire_detail_faisabilite; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_essai_commentaire_detail_faisabilite ON essai_commentaire_detail_faisabilite USING btree (id_detailfaisabilite);


--
-- TOC entry 2484 (class 1259 OID 53705)
-- Dependencies: 1881
-- Name: idx_essai_commentaire_detail_recherche; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_essai_commentaire_detail_recherche ON essai_commentaire_detail_recherche USING btree (id_detailrecherche);


--
-- TOC entry 2448 (class 1259 OID 53706)
-- Dependencies: 1865
-- Name: idx_essai_cro_sigrec; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_essai_cro_sigrec ON cro_sigrec USING btree (id_essai);


--
-- TOC entry 2546 (class 1259 OID 53707)
-- Dependencies: 1929
-- Name: idx_essai_document_detail_administratif; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_essai_document_detail_administratif ON essai_document_detail_administratif USING btree (id_detailadministratif);


--
-- TOC entry 2553 (class 1259 OID 53708)
-- Dependencies: 1935
-- Name: idx_essai_document_detail_surcout; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_essai_document_detail_surcout ON essai_document_detail_surcout USING btree (id_detailsurcout);


--
-- TOC entry 2570 (class 1259 OID 53709)
-- Dependencies: 1947
-- Name: idx_essai_evenement; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_essai_evenement ON evenement USING btree (id_essai);


--
-- TOC entry 2586 (class 1259 OID 53710)
-- Dependencies: 1959
-- Name: idx_essai_incident; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_essai_incident ON incident USING btree (id_essai);


--
-- TOC entry 2592 (class 1259 OID 53711)
-- Dependencies: 1963
-- Name: idx_essai_inclusion; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_essai_inclusion ON inclusion USING btree (id_essai);


--
-- TOC entry 2680 (class 1259 OID 53712)
-- Dependencies: 2011
-- Name: idx_essai_produit_detail_produit; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_essai_produit_detail_produit ON produit USING btree (id_detailproduit);


--
-- TOC entry 2718 (class 1259 OID 53713)
-- Dependencies: 2033
-- Name: idx_essai_retourpatient; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_essai_retourpatient ON retour_patient USING btree (id_essai);


--
-- TOC entry 2651 (class 1259 OID 53714)
-- Dependencies: 1995
-- Name: idx_etab_pharma; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_etab_pharma ON pharmacie USING btree (id_etablissement);


--
-- TOC entry 2661 (class 1259 OID 53715)
-- Dependencies: 2001
-- Name: idx_etab_pole; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_etab_pole ON pole USING btree (id_etablissement);


--
-- TOC entry 2733 (class 1259 OID 53716)
-- Dependencies: 2041
-- Name: idx_etab_site; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_etab_site ON site USING btree (id_etablissement);


--
-- TOC entry 2600 (class 1259 OID 53717)
-- Dependencies: 1968
-- Name: idx_grille_item; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_grille_item ON item USING btree (id_grille);


--
-- TOC entry 2576 (class 1259 OID 53718)
-- Dependencies: 1951
-- Name: idx_grille_modele_grille; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_grille_modele_grille ON grille USING btree (id_grille_modele);


--
-- TOC entry 2743 (class 1259 OID 53719)
-- Dependencies: 2047
-- Name: idx_grille_modele_theme; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_grille_modele_theme ON theme USING btree (id_grille_modele);


--
-- TOC entry 2667 (class 1259 OID 53720)
-- Dependencies: 2005
-- Name: idx_inclusion_prescription; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_inclusion_prescription ON prescription USING btree (id_inclusion);


--
-- TOC entry 2668 (class 1259 OID 53721)
-- Dependencies: 2005
-- Name: idx_investigateur_prescription; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_investigateur_prescription ON prescription USING btree (id_investigateur);


--
-- TOC entry 2558 (class 1259 OID 53722)
-- Dependencies: 1939
-- Name: idx_investigateur_principal_essai_sigrec; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_investigateur_principal_essai_sigrec ON essai_sigrec USING btree (id_investigateurprincipal);


--
-- TOC entry 2624 (class 1259 OID 53723)
-- Dependencies: 1979
-- Name: idx_mvtstock_conditionnement; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_mvtstock_conditionnement ON mvtstock USING btree (id_conditionnement);


--
-- TOC entry 2625 (class 1259 OID 53724)
-- Dependencies: 1979
-- Name: idx_mvtstock_essai; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_mvtstock_essai ON mvtstock USING btree (id_essai);


--
-- TOC entry 2626 (class 1259 OID 53725)
-- Dependencies: 1979
-- Name: idx_mvtstock_personne; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_mvtstock_personne ON mvtstock USING btree (id_personne);


--
-- TOC entry 2627 (class 1259 OID 53726)
-- Dependencies: 1979
-- Name: idx_mvtstock_pharmacie; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_mvtstock_pharmacie ON mvtstock USING btree (id_pharmacie);


--
-- TOC entry 2628 (class 1259 OID 53727)
-- Dependencies: 1979
-- Name: idx_mvtstock_produit; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_mvtstock_produit ON mvtstock USING btree (id_produit);


--
-- TOC entry 2585 (class 1259 OID 53728)
-- Dependencies: 1957
-- Name: idx_patient_historique_patient; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_patient_historique_patient ON historique_patient USING btree (id_patient);


--
-- TOC entry 2593 (class 1259 OID 53729)
-- Dependencies: 1963
-- Name: idx_patient_inclusion; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_patient_inclusion ON inclusion USING btree (id_patient);


--
-- TOC entry 2719 (class 1259 OID 53730)
-- Dependencies: 2033
-- Name: idx_patient_retourpatient; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_patient_retourpatient ON retour_patient USING btree (id_patient);


--
-- TOC entry 2582 (class 1259 OID 53731)
-- Dependencies: 1955
-- Name: idx_personne_habilitation; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_personne_habilitation ON habilitation USING btree (id_personne);


--
-- TOC entry 2720 (class 1259 OID 53732)
-- Dependencies: 2033
-- Name: idx_personne_retourpatient; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_personne_retourpatient ON retour_patient USING btree (id_personne);


--
-- TOC entry 2474 (class 1259 OID 53733)
-- Dependencies: 1876
-- Name: idx_pharma_essai; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_pharma_essai ON essai USING btree (id_pharma);


--
-- TOC entry 2633 (class 1259 OID 53734)
-- Dependencies: 1983
-- Name: idx_pharma_ordo_disp; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_pharma_ordo_disp ON ordonnancier_dispensation USING btree (id_pharma);


--
-- TOC entry 2636 (class 1259 OID 53735)
-- Dependencies: 1985
-- Name: idx_pharma_ordo_fab_reconst; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_pharma_ordo_fab_reconst ON ordonnancier_fab_reconst USING btree (id_pharma);


--
-- TOC entry 2739 (class 1259 OID 53736)
-- Dependencies: 2045
-- Name: idx_pharmacie_stockage; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_pharmacie_stockage ON stockage USING btree (id_pharmacie);


--
-- TOC entry 2754 (class 1259 OID 54670)
-- Dependencies: 2054
-- Name: idx_pharmacien_document_pharmacien; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_pharmacien_document_pharmacien ON pharmacien_document_pharmacien USING btree (id_pharmacien);


--
-- TOC entry 2727 (class 1259 OID 53737)
-- Dependencies: 2037
-- Name: idx_pole_service; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_pole_service ON service USING btree (id_pole);


--
-- TOC entry 2673 (class 1259 OID 53738)
-- Dependencies: 2007
-- Name: idx_prescription_conditionnement; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_prescription_conditionnement ON prescription_type USING btree (id_conditionnement);


--
-- TOC entry 2453 (class 1259 OID 53739)
-- Dependencies: 1867
-- Name: idx_prescription_dispensation; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_prescription_dispensation ON dispensation USING btree (id_prescription);


--
-- TOC entry 2674 (class 1259 OID 53740)
-- Dependencies: 2007
-- Name: idx_prescription_produit; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_prescription_produit ON prescription_type USING btree (id_produit);


--
-- TOC entry 2693 (class 1259 OID 53741)
-- Dependencies: 2019
-- Name: idx_prescription_produit_prescrit; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_prescription_produit_prescrit ON produit_prescrit USING btree (id_prescription);


--
-- TOC entry 2675 (class 1259 OID 53742)
-- Dependencies: 2007
-- Name: idx_prescription_sequence; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_prescription_sequence ON prescription_type USING btree (id_sequence);


--
-- TOC entry 2686 (class 1259 OID 53743)
-- Dependencies: 2014
-- Name: idx_produit_pharmacie; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_produit_pharmacie ON produit_detail_stockage USING btree (id_pharmacie);


--
-- TOC entry 2616 (class 1259 OID 53744)
-- Dependencies: 1976
-- Name: idx_produit_prescrit_dispensation_produit; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_produit_prescrit_dispensation_produit ON mvt_dispensation USING btree (id_produitprescrit);


--
-- TOC entry 2471 (class 1259 OID 53745)
-- Dependencies: 1874
-- Name: idx_produit_prescrit_elementtocheck; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_produit_prescrit_elementtocheck ON element_to_check USING btree (id_produitprescrit);


--
-- TOC entry 2694 (class 1259 OID 53746)
-- Dependencies: 2019
-- Name: idx_produit_produit_prescrit; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_produit_produit_prescrit ON produit_prescrit USING btree (id_produit);


--
-- TOC entry 2721 (class 1259 OID 53747)
-- Dependencies: 2033
-- Name: idx_produit_retourpatient; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_produit_retourpatient ON retour_patient USING btree (id_produit);


--
-- TOC entry 2687 (class 1259 OID 53748)
-- Dependencies: 2014
-- Name: idx_produit_stockage; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_produit_stockage ON produit_detail_stockage USING btree (id_stockage);


--
-- TOC entry 2644 (class 1259 OID 53749)
-- Dependencies: 1991
-- Name: idx_promo_arcpromo; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_promo_arcpromo ON personne USING btree (id_promoteur);


--
-- TOC entry 2645 (class 1259 OID 53750)
-- Dependencies: 1991
-- Name: idx_promo_contactpromo; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_promo_contactpromo ON personne USING btree (id_promoteur);


--
-- TOC entry 2475 (class 1259 OID 53751)
-- Dependencies: 1876
-- Name: idx_promo_essai; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_promo_essai ON essai USING btree (id_promoteur);


--
-- TOC entry 2559 (class 1259 OID 53752)
-- Dependencies: 1939
-- Name: idx_promo_essai_sigrec; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_promo_essai_sigrec ON essai_sigrec USING btree (id_promoteur);


--
-- TOC entry 2421 (class 1259 OID 53753)
-- Dependencies: 1848
-- Name: idx_promoteur_arc_promoteur_sigrec; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_promoteur_arc_promoteur_sigrec ON arc_promoteur_sigrec USING btree (id_promoteur);


--
-- TOC entry 2669 (class 1259 OID 53754)
-- Dependencies: 2005
-- Name: idx_sequence_prescriptin; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_sequence_prescriptin ON prescription USING btree (id_sequence);


--
-- TOC entry 2670 (class 1259 OID 53755)
-- Dependencies: 2005
-- Name: idx_service_prescription; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_service_prescription ON prescription USING btree (id_service);


--
-- TOC entry 2740 (class 1259 OID 53756)
-- Dependencies: 2045
-- Name: idx_stockage_parent; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_stockage_parent ON stockage USING btree (id_stockage_parent);


--
-- TOC entry 2489 (class 1259 OID 53757)
-- Dependencies: 1885
-- Name: idx_suivi_detail_administratif; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_detail_administratif ON essai_detail_administratif_suivi USING btree (id_detail_administratif);


--
-- TOC entry 2494 (class 1259 OID 53758)
-- Dependencies: 1889
-- Name: idx_suivi_detail_autres_documents; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_detail_autres_documents ON essai_detail_autres_documents_suivi USING btree (id_detail_autres_documents);


--
-- TOC entry 2499 (class 1259 OID 53759)
-- Dependencies: 1893
-- Name: idx_suivi_detail_contacts; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_detail_contacts ON essai_detail_contacts_suivi USING btree (id_detail_contacts);


--
-- TOC entry 2504 (class 1259 OID 53760)
-- Dependencies: 1897
-- Name: idx_suivi_detail_dates; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_detail_dates ON essai_detail_dates_suivi USING btree (id_detail_dates);


--
-- TOC entry 2509 (class 1259 OID 53761)
-- Dependencies: 1901
-- Name: idx_suivi_detail_design; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_detail_design ON essai_detail_design_suivi USING btree (id_detail_design);


--
-- TOC entry 2519 (class 1259 OID 53762)
-- Dependencies: 1908
-- Name: idx_suivi_detail_faisabilite; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_detail_faisabilite ON essai_detail_faisabilite_suivi USING btree (id_detail_faisabilite);


--
-- TOC entry 2526 (class 1259 OID 53763)
-- Dependencies: 1913
-- Name: idx_suivi_detail_pharma; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_detail_pharma ON essai_detail_pharma_suivi USING btree (id_detail_pharma);


--
-- TOC entry 2531 (class 1259 OID 53764)
-- Dependencies: 1917
-- Name: idx_suivi_detail_produit; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_detail_produit ON essai_detail_produit_suivi USING btree (id_detail_produit);


--
-- TOC entry 2538 (class 1259 OID 53765)
-- Dependencies: 1923
-- Name: idx_suivi_detail_recherche; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_detail_recherche ON essai_detail_recherche_suivi USING btree (id_detail_recherche);


--
-- TOC entry 2543 (class 1259 OID 53766)
-- Dependencies: 1927
-- Name: idx_suivi_detail_surcout; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_detail_surcout ON essai_detail_surcout_suivi USING btree (id_detail_surcout);


--
-- TOC entry 2562 (class 1259 OID 53767)
-- Dependencies: 1941
-- Name: idx_suivi_essai; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_essai ON essai_suivi USING btree (id_essai);


--
-- TOC entry 2567 (class 1259 OID 53768)
-- Dependencies: 1945
-- Name: idx_suivi_etablissement; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_etablissement ON etablissement_suivi USING btree (id_etablissement);


--
-- TOC entry 2573 (class 1259 OID 53769)
-- Dependencies: 1949
-- Name: idx_suivi_evenement; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_evenement ON evenement_suivi USING btree (id_evenement);


--
-- TOC entry 2589 (class 1259 OID 53770)
-- Dependencies: 1961
-- Name: idx_suivi_incident; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_incident ON incident_suivi USING btree (id_incident);


--
-- TOC entry 2641 (class 1259 OID 53771)
-- Dependencies: 1989
-- Name: idx_suivi_patient; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_patient ON patient_suivi USING btree (id_patient);


--
-- TOC entry 2648 (class 1259 OID 53772)
-- Dependencies: 1993
-- Name: idx_suivi_personne; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_personne ON personne_suivi USING btree (id_personne);


--
-- TOC entry 2656 (class 1259 OID 53773)
-- Dependencies: 1998
-- Name: idx_suivi_pharmacie; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_pharmacie ON pharmacie_suivi USING btree (id_pharmacie);


--
-- TOC entry 2664 (class 1259 OID 53774)
-- Dependencies: 2003
-- Name: idx_suivi_pole; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_pole ON pole_suivi USING btree (id_pole);


--
-- TOC entry 2699 (class 1259 OID 53775)
-- Dependencies: 2022
-- Name: idx_suivi_produit; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_produit ON produit_suivi USING btree (id_produit);


--
-- TOC entry 2709 (class 1259 OID 53776)
-- Dependencies: 2029
-- Name: idx_suivi_promoteur; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_promoteur ON promoteur_suivi USING btree (id_promoteur);


--
-- TOC entry 2730 (class 1259 OID 53777)
-- Dependencies: 2039
-- Name: idx_suivi_service; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_service ON service_suivi USING btree (id_service);


--
-- TOC entry 2736 (class 1259 OID 53778)
-- Dependencies: 2043
-- Name: idx_suivi_site; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_site ON site_suivi USING btree (id_site);


--
-- TOC entry 2433 (class 1259 OID 53779)
-- Dependencies: 1855
-- Name: idx_theme_categorie; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_theme_categorie ON categorie USING btree (id_theme);


--
-- TOC entry 2713 (class 1259 OID 53780)
-- Dependencies: 2031
-- Name: idx_theme_regle; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_theme_regle ON regle_surcout USING btree (id_theme);


--
-- TOC entry 2833 (class 2606 OID 53781)
-- Dependencies: 1876 1947 2472
-- Name: fk1174a6939fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY evenement
    ADD CONSTRAINT fk1174a6939fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2832 (class 2606 OID 54601)
-- Dependencies: 2646 1991 1947
-- Name: fk1174a698800c21; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY evenement
    ADD CONSTRAINT fk1174a698800c21 FOREIGN KEY (id_personne) REFERENCES personne(id);


--
-- TOC entry 2870 (class 2606 OID 53786)
-- Dependencies: 1995 2652 1985
-- Name: fk12c037c73a903eb7; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY ordonnancier_fab_reconst
    ADD CONSTRAINT fk12c037c73a903eb7 FOREIGN KEY (id_pharma) REFERENCES pharmacie(id);


--
-- TOC entry 2811 (class 2606 OID 53791)
-- Dependencies: 1876 2472 1910
-- Name: fk17e192d939fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_pharma
    ADD CONSTRAINT fk17e192d939fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2872 (class 2606 OID 53796)
-- Dependencies: 2704 2025 1991
-- Name: fk1a6a27cc4285b151; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY personne
    ADD CONSTRAINT fk1a6a27cc4285b151 FOREIGN KEY (id_promoteur) REFERENCES promoteur(id);


--
-- TOC entry 2882 (class 2606 OID 53801)
-- Dependencies: 2728 2005 2037
-- Name: fk1b6fa41a1cf65f51; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY prescription
    ADD CONSTRAINT fk1b6fa41a1cf65f51 FOREIGN KEY (id_service) REFERENCES service(id);


--
-- TOC entry 2883 (class 2606 OID 53806)
-- Dependencies: 2035 2005 2725
-- Name: fk1b6fa41a807681fd; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY prescription
    ADD CONSTRAINT fk1b6fa41a807681fd FOREIGN KEY (id_sequence) REFERENCES sequence(id);


--
-- TOC entry 2884 (class 2606 OID 53811)
-- Dependencies: 2005 1963 2594
-- Name: fk1b6fa41adb692012; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY prescription
    ADD CONSTRAINT fk1b6fa41adb692012 FOREIGN KEY (id_inclusion) REFERENCES inclusion(id);


--
-- TOC entry 2885 (class 2606 OID 53816)
-- Dependencies: 2646 1991 2005
-- Name: fk1b6fa41aea08da8f; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY prescription
    ADD CONSTRAINT fk1b6fa41aea08da8f FOREIGN KEY (id_investigateur) REFERENCES personne(id);


--
-- TOC entry 2760 (class 2606 OID 53821)
-- Dependencies: 1848 1863 2443
-- Name: fk1df3b08e91ecd533; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY arc_promoteur_sigrec
    ADD CONSTRAINT fk1df3b08e91ecd533 FOREIGN KEY (id_contact) REFERENCES contact_sigrec(id);


--
-- TOC entry 2761 (class 2606 OID 53826)
-- Dependencies: 2027 2707 1848
-- Name: fk1df3b08ed4112aed; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY arc_promoteur_sigrec
    ADD CONSTRAINT fk1df3b08ed4112aed FOREIGN KEY (id_promoteur) REFERENCES promoteur_sigrec(id);


--
-- TOC entry 2878 (class 2606 OID 53831)
-- Dependencies: 2646 1991 2000
-- Name: fk1eabc02f24482761; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY pharmacien_pharmacie
    ADD CONSTRAINT fk1eabc02f24482761 FOREIGN KEY (id_pharmacien) REFERENCES personne(id);


--
-- TOC entry 2879 (class 2606 OID 53836)
-- Dependencies: 1995 2000 2652
-- Name: fk1eabc02f4de40194; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY pharmacien_pharmacie
    ADD CONSTRAINT fk1eabc02f4de40194 FOREIGN KEY (id_pharmacie) REFERENCES pharmacie(id);


--
-- TOC entry 2822 (class 2606 OID 53841)
-- Dependencies: 2485 1883 1929
-- Name: fk20a01eebb314ca7e; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_document_detail_administratif
    ADD CONSTRAINT fk20a01eebb314ca7e FOREIGN KEY (id_detailadministratif) REFERENCES essai_detail_administratif(id);


--
-- TOC entry 2825 (class 2606 OID 53846)
-- Dependencies: 1935 2539 1925
-- Name: fk24399e3f3233d23a; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_document_detail_surcout
    ADD CONSTRAINT fk24399e3f3233d23a FOREIGN KEY (id_detailsurcout) REFERENCES essai_detail_surcout(id);


--
-- TOC entry 2918 (class 2606 OID 53851)
-- Dependencies: 2734 2041 2043
-- Name: fk2694c8427aad8e07; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY site_suivi
    ADD CONSTRAINT fk2694c8427aad8e07 FOREIGN KEY (id_site) REFERENCES site(id);


--
-- TOC entry 2801 (class 2606 OID 53856)
-- Dependencies: 1893 2495 1891
-- Name: fk2a86a3aa27453d52; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_contacts_suivi
    ADD CONSTRAINT fk2a86a3aa27453d52 FOREIGN KEY (id_detail_contacts) REFERENCES essai_detail_contacts(id);


--
-- TOC entry 2766 (class 2606 OID 53861)
-- Dependencies: 1899 2505 1853
-- Name: fk2e4482387f8764; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY bras
    ADD CONSTRAINT fk2e4482387f8764 FOREIGN KEY (id_detail_design) REFERENCES essai_detail_design(id);


--
-- TOC entry 2767 (class 2606 OID 53866)
-- Dependencies: 2427 1853 1853
-- Name: fk2e44824d844dbc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY bras
    ADD CONSTRAINT fk2e44824d844dbc FOREIGN KEY (id_bras_parent) REFERENCES bras(id);


--
-- TOC entry 2776 (class 2606 OID 53871)
-- Dependencies: 2652 1867 1995
-- Name: fk2eaeffed4de40194; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY dispensation
    ADD CONSTRAINT fk2eaeffed4de40194 FOREIGN KEY (id_pharmacie) REFERENCES pharmacie(id);


--
-- TOC entry 2777 (class 2606 OID 53876)
-- Dependencies: 1867 2005 2671
-- Name: fk2eaeffed87ff1713; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY dispensation
    ADD CONSTRAINT fk2eaeffed87ff1713 FOREIGN KEY (id_prescription) REFERENCES prescription(id);


--
-- TOC entry 2778 (class 2606 OID 53881)
-- Dependencies: 1867 1983 2634
-- Name: fk2eaeffedbabca2b4; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY dispensation
    ADD CONSTRAINT fk2eaeffedbabca2b4 FOREIGN KEY (id_ordonnancier) REFERENCES ordonnancier_dispensation(id);


--
-- TOC entry 2837 (class 2606 OID 53886)
-- Dependencies: 1891 1955 2495
-- Name: fk2fee5dbe27453d52; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY habilitation
    ADD CONSTRAINT fk2fee5dbe27453d52 FOREIGN KEY (id_detail_contacts) REFERENCES essai_detail_contacts(id);


--
-- TOC entry 2838 (class 2606 OID 53891)
-- Dependencies: 2646 1955 1991
-- Name: fk2fee5dbe8800c21; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY habilitation
    ADD CONSTRAINT fk2fee5dbe8800c21 FOREIGN KEY (id_personne) REFERENCES personne(id);


--
-- TOC entry 2848 (class 2606 OID 53896)
-- Dependencies: 2574 1951 1968
-- Name: fk317b13976c82de; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY item
    ADD CONSTRAINT fk317b13976c82de FOREIGN KEY (id_grille) REFERENCES grille(id);


--
-- TOC entry 2804 (class 2606 OID 53901)
-- Dependencies: 2472 1876 1899
-- Name: fk345311a39fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_design
    ADD CONSTRAINT fk345311a39fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2880 (class 2606 OID 53906)
-- Dependencies: 2001 2563 1943
-- Name: fk3497b8cd45a413; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY pole
    ADD CONSTRAINT fk3497b8cd45a413 FOREIGN KEY (id_etablissement) REFERENCES etablissement(id);


--
-- TOC entry 2917 (class 2606 OID 53911)
-- Dependencies: 1943 2041 2563
-- Name: fk35df47cd45a413; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY site
    ADD CONSTRAINT fk35df47cd45a413 FOREIGN KEY (id_etablissement) REFERENCES etablissement(id);


--
-- TOC entry 2779 (class 2606 OID 53916)
-- Dependencies: 2011 1869 2681
-- Name: fk40b816e0ae5cce6; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY dispositif_medical
    ADD CONSTRAINT fk40b816e0ae5cce6 FOREIGN KEY (id) REFERENCES produit(id);


--
-- TOC entry 2925 (class 2606 OID 54639)
-- Dependencies: 2051 2607 1972
-- Name: fk414415ea389dcf45; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvt_preparationentree
    ADD CONSTRAINT fk414415ea389dcf45 FOREIGN KEY (id) REFERENCES mvt_approvisionnement(id);


--
-- TOC entry 2773 (class 2606 OID 53921)
-- Dependencies: 2681 2011 1861
-- Name: fk46e35c70a1ddf650; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY conditionnement
    ADD CONSTRAINT fk46e35c70a1ddf650 FOREIGN KEY (id_produit) REFERENCES produit(id);


--
-- TOC entry 2928 (class 2606 OID 54665)
-- Dependencies: 1991 2054 2646
-- Name: fk47ad8dfd24482761; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY pharmacien_document_pharmacien
    ADD CONSTRAINT fk47ad8dfd24482761 FOREIGN KEY (id_pharmacien) REFERENCES personne(id);


--
-- TOC entry 2798 (class 2606 OID 53926)
-- Dependencies: 2472 1876 1887
-- Name: fk4c82a77539fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_autres_documents
    ADD CONSTRAINT fk4c82a77539fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2877 (class 2606 OID 53931)
-- Dependencies: 1995 1998 2652
-- Name: fk4d5ce8dd4de40194; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY pharmacie_suivi
    ADD CONSTRAINT fk4d5ce8dd4de40194 FOREIGN KEY (id_pharmacie) REFERENCES pharmacie(id);


--
-- TOC entry 2806 (class 2606 OID 53936)
-- Dependencies: 1876 2472 1903
-- Name: fk4e973f7e39fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_etat
    ADD CONSTRAINT fk4e973f7e39fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2781 (class 2606 OID 53941)
-- Dependencies: 2728 1872 2037
-- Name: fk4f489b4c1cf65f51; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY dotation
    ADD CONSTRAINT fk4f489b4c1cf65f51 FOREIGN KEY (id_service) REFERENCES service(id);


--
-- TOC entry 2782 (class 2606 OID 53946)
-- Dependencies: 1872 2472 1876
-- Name: fk4f489b4c39fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY dotation
    ADD CONSTRAINT fk4f489b4c39fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2783 (class 2606 OID 53951)
-- Dependencies: 2652 1995 1872
-- Name: fk4f489b4c4de40194; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY dotation
    ADD CONSTRAINT fk4f489b4c4de40194 FOREIGN KEY (id_pharmacie) REFERENCES pharmacie(id);


--
-- TOC entry 2784 (class 2606 OID 53956)
-- Dependencies: 2646 1872 1991
-- Name: fk4f489b4c8800c21; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY dotation
    ADD CONSTRAINT fk4f489b4c8800c21 FOREIGN KEY (id_personne) REFERENCES personne(id);


--
-- TOC entry 2785 (class 2606 OID 53961)
-- Dependencies: 2440 1861 1872
-- Name: fk4f489b4c9d7535de; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY dotation
    ADD CONSTRAINT fk4f489b4c9d7535de FOREIGN KEY (id_conditionnement) REFERENCES conditionnement(id);


--
-- TOC entry 2786 (class 2606 OID 53966)
-- Dependencies: 2011 1872 2681
-- Name: fk4f489b4ca1ddf650; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY dotation
    ADD CONSTRAINT fk4f489b4ca1ddf650 FOREIGN KEY (id_produit) REFERENCES produit(id);


--
-- TOC entry 2913 (class 2606 OID 53971)
-- Dependencies: 1853 2427 2035
-- Name: fk507077c145975293; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY sequence
    ADD CONSTRAINT fk507077c145975293 FOREIGN KEY (id_bras_sequence) REFERENCES bras(id);


--
-- TOC entry 2840 (class 2606 OID 53976)
-- Dependencies: 1959 2472 1876
-- Name: fk52f44d239fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY incident
    ADD CONSTRAINT fk52f44d239fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2794 (class 2606 OID 53981)
-- Dependencies: 2513 1905 1879
-- Name: fk547c5a9a8897241c; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_commentaire_detail_faisabilite
    ADD CONSTRAINT fk547c5a9a8897241c FOREIGN KEY (id_detailfaisabilite) REFERENCES essai_detail_faisabilite(id);


--
-- TOC entry 2889 (class 2606 OID 53986)
-- Dependencies: 2009 2556 1939
-- Name: fk55375893a81b195e; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY prevision_sigrec
    ADD CONSTRAINT fk55375893a81b195e FOREIGN KEY (id_essai) REFERENCES essai_sigrec(id);


--
-- TOC entry 2807 (class 2606 OID 53991)
-- Dependencies: 2472 1876 1905
-- Name: fk5a8d447539fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_faisabilite
    ADD CONSTRAINT fk5a8d447539fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2841 (class 2606 OID 53996)
-- Dependencies: 1959 1961 2587
-- Name: fk5b30998db77789cb; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY incident_suivi
    ADD CONSTRAINT fk5b30998db77789cb FOREIGN KEY (id_incident) REFERENCES incident(id);


--
-- TOC entry 2770 (class 2606 OID 54001)
-- Dependencies: 2434 1859 1857
-- Name: fk5b85a4f55c631481; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY co_investigateur_sigrec
    ADD CONSTRAINT fk5b85a4f55c631481 FOREIGN KEY (id_centre) REFERENCES centre_sigrec(id);


--
-- TOC entry 2771 (class 2606 OID 54006)
-- Dependencies: 1863 1859 2443
-- Name: fk5b85a4f591ecd533; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY co_investigateur_sigrec
    ADD CONSTRAINT fk5b85a4f591ecd533 FOREIGN KEY (id_contact) REFERENCES contact_sigrec(id);


--
-- TOC entry 2772 (class 2606 OID 54011)
-- Dependencies: 1859 1939 2556
-- Name: fk5b85a4f5a81b195e; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY co_investigateur_sigrec
    ADD CONSTRAINT fk5b85a4f5a81b195e FOREIGN KEY (id_essai) REFERENCES essai_sigrec(id);


--
-- TOC entry 2791 (class 2606 OID 54016)
-- Dependencies: 2652 1995 1876
-- Name: fk5c5486d3a903eb7; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai
    ADD CONSTRAINT fk5c5486d3a903eb7 FOREIGN KEY (id_pharma) REFERENCES pharmacie(id);


--
-- TOC entry 2792 (class 2606 OID 54021)
-- Dependencies: 2704 2025 1876
-- Name: fk5c5486d4285b151; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai
    ADD CONSTRAINT fk5c5486d4285b151 FOREIGN KEY (id_promoteur) REFERENCES promoteur(id);


--
-- TOC entry 2817 (class 2606 OID 54026)
-- Dependencies: 1876 1919 2472
-- Name: fk5c79a91f39fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_recherche
    ADD CONSTRAINT fk5c79a91f39fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2768 (class 2606 OID 54031)
-- Dependencies: 1855 2744 2047
-- Name: fk5d54e13740161942; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY categorie
    ADD CONSTRAINT fk5d54e13740161942 FOREIGN KEY (id_theme) REFERENCES theme(id);


--
-- TOC entry 2769 (class 2606 OID 54036)
-- Dependencies: 2443 1863 1857
-- Name: fk5f43710391ecd533; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY centre_sigrec
    ADD CONSTRAINT fk5f43710391ecd533 FOREIGN KEY (id_contact) REFERENCES contact_sigrec(id);


--
-- TOC entry 2873 (class 2606 OID 54041)
-- Dependencies: 1991 1993 2646
-- Name: fk60fd9078800c21; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY personne_suivi
    ADD CONSTRAINT fk60fd9078800c21 FOREIGN KEY (id_personne) REFERENCES personne(id);


--
-- TOC entry 2862 (class 2606 OID 54046)
-- Dependencies: 1978 1979 2629
-- Name: fk61102bfd3e8f1c97; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvt_retour_promoteur
    ADD CONSTRAINT fk61102bfd3e8f1c97 FOREIGN KEY (id) REFERENCES mvtstock(id);


--
-- TOC entry 2919 (class 2606 OID 54051)
-- Dependencies: 1995 2045 2652
-- Name: fk658922294de40194; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY stockage
    ADD CONSTRAINT fk658922294de40194 FOREIGN KEY (id_pharmacie) REFERENCES pharmacie(id);


--
-- TOC entry 2920 (class 2606 OID 54056)
-- Dependencies: 2045 2045 2741
-- Name: fk65892229b4ed4491; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY stockage
    ADD CONSTRAINT fk65892229b4ed4491 FOREIGN KEY (id_stockage_parent) REFERENCES stockage(id);


--
-- TOC entry 2824 (class 2606 OID 54061)
-- Dependencies: 1933 1910 2520
-- Name: fk66a8bf19d08532d; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_document_detail_pharma
    ADD CONSTRAINT fk66a8bf19d08532d FOREIGN KEY (id_detail_pharma) REFERENCES essai_detail_pharma(id);


--
-- TOC entry 2853 (class 2606 OID 54066)
-- Dependencies: 1973 1979 2629
-- Name: fk67c907da3e8f1c97; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvt_autre_sortie
    ADD CONSTRAINT fk67c907da3e8f1c97 FOREIGN KEY (id) REFERENCES mvtstock(id);


--
-- TOC entry 2921 (class 2606 OID 54071)
-- Dependencies: 2047 1953 2577
-- Name: fk69375c9195ade5f; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY theme
    ADD CONSTRAINT fk69375c9195ade5f FOREIGN KEY (id_grille_modele) REFERENCES grille_modele(id);


--
-- TOC entry 2863 (class 2606 OID 54076)
-- Dependencies: 1979 1876 2472
-- Name: fk697c8a0b39fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvtstock
    ADD CONSTRAINT fk697c8a0b39fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2864 (class 2606 OID 54081)
-- Dependencies: 1979 1995 2652
-- Name: fk697c8a0b4de40194; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvtstock
    ADD CONSTRAINT fk697c8a0b4de40194 FOREIGN KEY (id_pharmacie) REFERENCES pharmacie(id);


--
-- TOC entry 2865 (class 2606 OID 54086)
-- Dependencies: 1979 1991 2646
-- Name: fk697c8a0b8800c21; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvtstock
    ADD CONSTRAINT fk697c8a0b8800c21 FOREIGN KEY (id_personne) REFERENCES personne(id);


--
-- TOC entry 2866 (class 2606 OID 54091)
-- Dependencies: 1979 1861 2440
-- Name: fk697c8a0b9d7535de; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvtstock
    ADD CONSTRAINT fk697c8a0b9d7535de FOREIGN KEY (id_conditionnement) REFERENCES conditionnement(id);


--
-- TOC entry 2867 (class 2606 OID 54096)
-- Dependencies: 1979 2011 2681
-- Name: fk697c8a0ba1ddf650; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvtstock
    ADD CONSTRAINT fk697c8a0ba1ddf650 FOREIGN KEY (id_produit) REFERENCES produit(id);


--
-- TOC entry 2799 (class 2606 OID 54101)
-- Dependencies: 1889 1887 2490
-- Name: fk698a35f0ec2855a; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_autres_documents_suivi
    ADD CONSTRAINT fk698a35f0ec2855a FOREIGN KEY (id_detail_autres_documents) REFERENCES essai_detail_autres_documents(id);


--
-- TOC entry 2819 (class 2606 OID 54106)
-- Dependencies: 1923 1919 2532
-- Name: fk6b5fd01a1cf64d65; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_recherche_suivi
    ADD CONSTRAINT fk6b5fd01a1cf64d65 FOREIGN KEY (id_detail_recherche) REFERENCES essai_detail_recherche(id);


--
-- TOC entry 2904 (class 2606 OID 54111)
-- Dependencies: 2029 2025 2704
-- Name: fk6bdaed84285b151; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY promoteur_suivi
    ADD CONSTRAINT fk6bdaed84285b151 FOREIGN KEY (id_promoteur) REFERENCES promoteur(id);


--
-- TOC entry 2891 (class 2606 OID 54116)
-- Dependencies: 2012 2681 2011
-- Name: fk6e9d2d16a1ddf650; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY produit_detail_logistique
    ADD CONSTRAINT fk6e9d2d16a1ddf650 FOREIGN KEY (id_produit) REFERENCES produit(id);


--
-- TOC entry 2808 (class 2606 OID 54121)
-- Dependencies: 2728 2037 1907
-- Name: fk71236ceb1cf65f51; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_faisabilite_service
    ADD CONSTRAINT fk71236ceb1cf65f51 FOREIGN KEY (id_service) REFERENCES service(id);


--
-- TOC entry 2809 (class 2606 OID 54126)
-- Dependencies: 1907 2513 1905
-- Name: fk71236ceb3607a129; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_faisabilite_service
    ADD CONSTRAINT fk71236ceb3607a129 FOREIGN KEY (id_essai) REFERENCES essai_detail_faisabilite(id);


--
-- TOC entry 2780 (class 2606 OID 54131)
-- Dependencies: 1870 1925 2539
-- Name: fk75589534f5ae6985; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY donnees_prevision
    ADD CONSTRAINT fk75589534f5ae6985 FOREIGN KEY (id_detail_surcout) REFERENCES essai_detail_surcout(id);


--
-- TOC entry 2914 (class 2606 OID 54136)
-- Dependencies: 2037 2001 2662
-- Name: fk7643c6b57aaafee9; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY service
    ADD CONSTRAINT fk7643c6b57aaafee9 FOREIGN KEY (id_pole) REFERENCES pole(id);


--
-- TOC entry 2915 (class 2606 OID 54141)
-- Dependencies: 2037 2734 2041
-- Name: fk7643c6b57aad8e07; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY service
    ADD CONSTRAINT fk7643c6b57aad8e07 FOREIGN KEY (id_site) REFERENCES site(id);


--
-- TOC entry 2851 (class 2606 OID 54146)
-- Dependencies: 2011 1971 2681
-- Name: fk77228d19ae5cce6; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY medicament
    ADD CONSTRAINT fk77228d19ae5cce6 FOREIGN KEY (id) REFERENCES produit(id);


--
-- TOC entry 2810 (class 2606 OID 54151)
-- Dependencies: 2513 1905 1908
-- Name: fk7a6b12f0530f1de7; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_faisabilite_suivi
    ADD CONSTRAINT fk7a6b12f0530f1de7 FOREIGN KEY (id_detail_faisabilite) REFERENCES essai_detail_faisabilite(id);


--
-- TOC entry 2834 (class 2606 OID 54156)
-- Dependencies: 1949 2568 1947
-- Name: fk7c0dd1e41aeddf50; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY evenement_suivi
    ADD CONSTRAINT fk7c0dd1e41aeddf50 FOREIGN KEY (id_evenement) REFERENCES evenement(id);


--
-- TOC entry 2895 (class 2606 OID 54161)
-- Dependencies: 2011 2681 2016
-- Name: fk7c4c166aa1ddf650; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY produit_document_actes_pharma
    ADD CONSTRAINT fk7c4c166aa1ddf650 FOREIGN KEY (id_produit) REFERENCES produit(id);


--
-- TOC entry 2857 (class 2606 OID 54166)
-- Dependencies: 1867 1976 2449
-- Name: fk800c37c11a1781c6; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvt_dispensation
    ADD CONSTRAINT fk800c37c11a1781c6 FOREIGN KEY (id_dispensation) REFERENCES dispensation(id);


--
-- TOC entry 2858 (class 2606 OID 54171)
-- Dependencies: 1979 1976 2629
-- Name: fk800c37c13e8f1c97; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvt_dispensation
    ADD CONSTRAINT fk800c37c13e8f1c97 FOREIGN KEY (id) REFERENCES mvtstock(id);


--
-- TOC entry 2859 (class 2606 OID 54176)
-- Dependencies: 2695 2019 1976
-- Name: fk800c37c16d6ee647; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvt_dispensation
    ADD CONSTRAINT fk800c37c16d6ee647 FOREIGN KEY (id_produitprescrit) REFERENCES produit_prescrit(id);


--
-- TOC entry 2793 (class 2606 OID 54181)
-- Dependencies: 1877 2485 1883
-- Name: fk823d05e4b314ca7e; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_commentaire_detail_administratif_archi
    ADD CONSTRAINT fk823d05e4b314ca7e FOREIGN KEY (id_detailadministratif) REFERENCES essai_detail_administratif(id);


--
-- TOC entry 2844 (class 2606 OID 54186)
-- Dependencies: 1965 2728 2037
-- Name: fk833c86321cf65f51; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY investigateur_service
    ADD CONSTRAINT fk833c86321cf65f51 FOREIGN KEY (id_service) REFERENCES service(id);


--
-- TOC entry 2845 (class 2606 OID 54191)
-- Dependencies: 1965 1991 2646
-- Name: fk833c8632ea08da8f; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY investigateur_service
    ADD CONSTRAINT fk833c8632ea08da8f FOREIGN KEY (id_investigateur) REFERENCES personne(id);


--
-- TOC entry 2802 (class 2606 OID 54196)
-- Dependencies: 1895 1876 2472
-- Name: fk843a3ba939fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_dates
    ADD CONSTRAINT fk843a3ba939fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2881 (class 2606 OID 54201)
-- Dependencies: 2003 2662 2001
-- Name: fk8449a7f37aaafee9; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY pole_suivi
    ADD CONSTRAINT fk8449a7f37aaafee9 FOREIGN KEY (id_pole) REFERENCES pole(id);


--
-- TOC entry 2839 (class 2606 OID 54206)
-- Dependencies: 1987 2639 1957
-- Name: fk8529d883aedb3264; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY historique_patient
    ADD CONSTRAINT fk8529d883aedb3264 FOREIGN KEY (id_patient) REFERENCES patient(id);


--
-- TOC entry 2852 (class 2606 OID 54211)
-- Dependencies: 1972 1979 2629
-- Name: fk869711473e8f1c97; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvt_approvisionnement
    ADD CONSTRAINT fk869711473e8f1c97 FOREIGN KEY (id) REFERENCES mvtstock(id);


--
-- TOC entry 2816 (class 2606 OID 54216)
-- Dependencies: 1915 1917 2527
-- Name: fk8a0ab6487dbf9eef; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_produit_suivi
    ADD CONSTRAINT fk8a0ab6487dbf9eef FOREIGN KEY (id_detail_produit) REFERENCES essai_detail_produit(id);


--
-- TOC entry 2849 (class 2606 OID 54221)
-- Dependencies: 2031 2714 1970
-- Name: fk8b91f4e11622e8a9; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY item_regle
    ADD CONSTRAINT fk8b91f4e11622e8a9 FOREIGN KEY (id_regle) REFERENCES regle_surcout(id);


--
-- TOC entry 2850 (class 2606 OID 54226)
-- Dependencies: 1970 2601 1968
-- Name: fk8b91f4e1e0ff5276; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY item_regle
    ADD CONSTRAINT fk8b91f4e1e0ff5276 FOREIGN KEY (id_item) REFERENCES item(id);


--
-- TOC entry 2902 (class 2606 OID 54231)
-- Dependencies: 2011 2681 2024
-- Name: fk92053556ae5cce6; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY produit_therapeutique
    ADD CONSTRAINT fk92053556ae5cce6 FOREIGN KEY (id) REFERENCES produit(id);


--
-- TOC entry 2922 (class 2606 OID 54611)
-- Dependencies: 2049 1943 2563
-- Name: fk93a102d0cd45a413; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_pharma_etablissement
    ADD CONSTRAINT fk93a102d0cd45a413 FOREIGN KEY (id_etablissement) REFERENCES etablissement(id);


--
-- TOC entry 2923 (class 2606 OID 54616)
-- Dependencies: 2520 2049 1910
-- Name: fk93a102d0d08532d; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_pharma_etablissement
    ADD CONSTRAINT fk93a102d0d08532d FOREIGN KEY (id_detail_pharma) REFERENCES essai_detail_pharma(id);


--
-- TOC entry 2803 (class 2606 OID 54236)
-- Dependencies: 1895 1897 2500
-- Name: fk9506d324a7a1603; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_dates_suivi
    ADD CONSTRAINT fk9506d324a7a1603 FOREIGN KEY (id_detail_dates) REFERENCES essai_detail_dates(id);


--
-- TOC entry 2823 (class 2606 OID 54241)
-- Dependencies: 1931 2490 1887
-- Name: fk966f83b5ec2855a; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_document_detail_autres_documents
    ADD CONSTRAINT fk966f83b5ec2855a FOREIGN KEY (id_detail_autres_documents) REFERENCES essai_detail_autres_documents(id);


--
-- TOC entry 2797 (class 2606 OID 54246)
-- Dependencies: 2485 1883 1885
-- Name: fk98df0126bf757d89; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_administratif_suivi
    ADD CONSTRAINT fk98df0126bf757d89 FOREIGN KEY (id_detail_administratif) REFERENCES essai_detail_administratif(id);


--
-- TOC entry 2916 (class 2606 OID 54251)
-- Dependencies: 2039 2728 2037
-- Name: fk9946e5301cf65f51; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY service_suivi
    ADD CONSTRAINT fk9946e5301cf65f51 FOREIGN KEY (id_service) REFERENCES service(id);


--
-- TOC entry 2820 (class 2606 OID 54256)
-- Dependencies: 1876 2472 1925
-- Name: fk9a1b427f39fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_surcout
    ADD CONSTRAINT fk9a1b427f39fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2795 (class 2606 OID 54261)
-- Dependencies: 1919 2532 1881
-- Name: fk9b1204844dc45cda; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_commentaire_detail_recherche
    ADD CONSTRAINT fk9b1204844dc45cda FOREIGN KEY (id_detailrecherche) REFERENCES essai_detail_recherche(id);


--
-- TOC entry 2821 (class 2606 OID 54266)
-- Dependencies: 2539 1927 1925
-- Name: fk9c00e17af5ae6985; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_surcout_suivi
    ADD CONSTRAINT fk9c00e17af5ae6985 FOREIGN KEY (id_detail_surcout) REFERENCES essai_detail_surcout(id);


--
-- TOC entry 2869 (class 2606 OID 54271)
-- Dependencies: 1983 1995 2652
-- Name: fk9ea891de3a903eb7; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY ordonnancier_dispensation
    ADD CONSTRAINT fk9ea891de3a903eb7 FOREIGN KEY (id_pharma) REFERENCES pharmacie(id);


--
-- TOC entry 2796 (class 2606 OID 54276)
-- Dependencies: 1883 1876 2472
-- Name: fka145932b39fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_administratif
    ADD CONSTRAINT fka145932b39fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2812 (class 2606 OID 54281)
-- Dependencies: 1995 1912 2652
-- Name: fka30ce23c4de40194; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_pharma_pharmacie
    ADD CONSTRAINT fka30ce23c4de40194 FOREIGN KEY (id_pharmacie) REFERENCES pharmacie(id);


--
-- TOC entry 2813 (class 2606 OID 54286)
-- Dependencies: 2520 1910 1912
-- Name: fka30ce23cd08532d; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_pharma_pharmacie
    ADD CONSTRAINT fka30ce23cd08532d FOREIGN KEY (id_detail_pharma) REFERENCES essai_detail_pharma(id);


--
-- TOC entry 2842 (class 2606 OID 54291)
-- Dependencies: 1876 2472 1963
-- Name: fka6cdb91c39fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY inclusion
    ADD CONSTRAINT fka6cdb91c39fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2843 (class 2606 OID 54296)
-- Dependencies: 1987 2639 1963
-- Name: fka6cdb91caedb3264; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY inclusion
    ADD CONSTRAINT fka6cdb91caedb3264 FOREIGN KEY (id_patient) REFERENCES patient(id);


--
-- TOC entry 2846 (class 2606 OID 54301)
-- Dependencies: 1966 2434 1857
-- Name: fka9985b025c631481; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY investigateur_sigrec
    ADD CONSTRAINT fka9985b025c631481 FOREIGN KEY (id_centre) REFERENCES centre_sigrec(id);


--
-- TOC entry 2847 (class 2606 OID 54306)
-- Dependencies: 2443 1966 1863
-- Name: fka9985b0291ecd533; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY investigateur_sigrec
    ADD CONSTRAINT fka9985b0291ecd533 FOREIGN KEY (id_contact) REFERENCES contact_sigrec(id);


--
-- TOC entry 2875 (class 2606 OID 54311)
-- Dependencies: 1995 1997 2652
-- Name: fkafea0d444de40194; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY pharmacie_site
    ADD CONSTRAINT fkafea0d444de40194 FOREIGN KEY (id_pharmacie) REFERENCES pharmacie(id);


--
-- TOC entry 2876 (class 2606 OID 54316)
-- Dependencies: 2734 1997 2041
-- Name: fkafea0d447aad8e07; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY pharmacie_site
    ADD CONSTRAINT fkafea0d447aad8e07 FOREIGN KEY (id_site) REFERENCES site(id);


--
-- TOC entry 2871 (class 2606 OID 54321)
-- Dependencies: 2639 1987 1989
-- Name: fkb01e8d80aedb3264; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY patient_suivi
    ADD CONSTRAINT fkb01e8d80aedb3264 FOREIGN KEY (id_patient) REFERENCES patient(id);


--
-- TOC entry 2924 (class 2606 OID 54626)
-- Dependencies: 2681 2050 2011
-- Name: fkb1982697ae5cce6; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY preparation
    ADD CONSTRAINT fkb1982697ae5cce6 FOREIGN KEY (id) REFERENCES produit(id);


--
-- TOC entry 2835 (class 2606 OID 54326)
-- Dependencies: 1951 1953 2577
-- Name: fkb63afd47195ade5f; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY grille
    ADD CONSTRAINT fkb63afd47195ade5f FOREIGN KEY (id_grille_modele) REFERENCES grille_modele(id);


--
-- TOC entry 2836 (class 2606 OID 54331)
-- Dependencies: 1951 2539 1925
-- Name: fkb63afd47f5ae6985; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY grille
    ADD CONSTRAINT fkb63afd47f5ae6985 FOREIGN KEY (id_detail_surcout) REFERENCES essai_detail_surcout(id);


--
-- TOC entry 2814 (class 2606 OID 54336)
-- Dependencies: 2520 1910 1913
-- Name: fkb8bc0654d08532d; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_pharma_suivi
    ADD CONSTRAINT fkb8bc0654d08532d FOREIGN KEY (id_detail_pharma) REFERENCES essai_detail_pharma(id);


--
-- TOC entry 2886 (class 2606 OID 54341)
-- Dependencies: 2007 2725 2035
-- Name: fkbe86243f807681fd; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY prescription_type
    ADD CONSTRAINT fkbe86243f807681fd FOREIGN KEY (id_sequence) REFERENCES sequence(id);


--
-- TOC entry 2887 (class 2606 OID 54346)
-- Dependencies: 1861 2440 2007
-- Name: fkbe86243f9d7535de; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY prescription_type
    ADD CONSTRAINT fkbe86243f9d7535de FOREIGN KEY (id_conditionnement) REFERENCES conditionnement(id);


--
-- TOC entry 2888 (class 2606 OID 54351)
-- Dependencies: 2011 2007 2681
-- Name: fkbe86243fa1ddf650; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY prescription_type
    ADD CONSTRAINT fkbe86243fa1ddf650 FOREIGN KEY (id_produit) REFERENCES produit(id);


--
-- TOC entry 2899 (class 2606 OID 54356)
-- Dependencies: 2728 2021 2037
-- Name: fkc171821f1cf65f51; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY produit_service
    ADD CONSTRAINT fkc171821f1cf65f51 FOREIGN KEY (id_service) REFERENCES service(id);


--
-- TOC entry 2900 (class 2606 OID 54361)
-- Dependencies: 2011 2681 2021
-- Name: fkc171821fa1ddf650; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY produit_service
    ADD CONSTRAINT fkc171821fa1ddf650 FOREIGN KEY (id_produit) REFERENCES produit(id);


--
-- TOC entry 2901 (class 2606 OID 54366)
-- Dependencies: 2011 2022 2681
-- Name: fkc1e4e524a1ddf650; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY produit_suivi
    ADD CONSTRAINT fkc1e4e524a1ddf650 FOREIGN KEY (id_produit) REFERENCES produit(id);


--
-- TOC entry 2860 (class 2606 OID 54371)
-- Dependencies: 2629 1977 1979
-- Name: fkc343fea43e8f1c97; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvt_dispensation_globale
    ADD CONSTRAINT fkc343fea43e8f1c97 FOREIGN KEY (id) REFERENCES mvtstock(id);


--
-- TOC entry 2861 (class 2606 OID 54376)
-- Dependencies: 1872 1977 2458
-- Name: fkc343fea464b18985; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvt_dispensation_globale
    ADD CONSTRAINT fkc343fea464b18985 FOREIGN KEY (id_dotation) REFERENCES dotation(id);


--
-- TOC entry 2907 (class 2606 OID 54381)
-- Dependencies: 1876 2472 2033
-- Name: fkc38b7dd139fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY retour_patient
    ADD CONSTRAINT fkc38b7dd139fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2908 (class 2606 OID 54386)
-- Dependencies: 2646 2033 1991
-- Name: fkc38b7dd18800c21; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY retour_patient
    ADD CONSTRAINT fkc38b7dd18800c21 FOREIGN KEY (id_personne) REFERENCES personne(id);


--
-- TOC entry 2909 (class 2606 OID 54391)
-- Dependencies: 2033 1861 2440
-- Name: fkc38b7dd19d7535de; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY retour_patient
    ADD CONSTRAINT fkc38b7dd19d7535de FOREIGN KEY (id_conditionnement) REFERENCES conditionnement(id);


--
-- TOC entry 2910 (class 2606 OID 54396)
-- Dependencies: 2033 2681 2011
-- Name: fkc38b7dd1a1ddf650; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY retour_patient
    ADD CONSTRAINT fkc38b7dd1a1ddf650 FOREIGN KEY (id_produit) REFERENCES produit(id);


--
-- TOC entry 2911 (class 2606 OID 54401)
-- Dependencies: 1987 2033 2639
-- Name: fkc38b7dd1aedb3264; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY retour_patient
    ADD CONSTRAINT fkc38b7dd1aedb3264 FOREIGN KEY (id_patient) REFERENCES patient(id);


--
-- TOC entry 2912 (class 2606 OID 54406)
-- Dependencies: 2033 2688 2014
-- Name: fkc38b7dd1d8bb7cd7; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY retour_patient
    ADD CONSTRAINT fkc38b7dd1d8bb7cd7 FOREIGN KEY (id_detailstockage) REFERENCES produit_detail_stockage(id);


--
-- TOC entry 2831 (class 2606 OID 54411)
-- Dependencies: 1943 2563 1945
-- Name: fkcaf42771cd45a413; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY etablissement_suivi
    ADD CONSTRAINT fkcaf42771cd45a413 FOREIGN KEY (id_etablissement) REFERENCES etablissement(id);


--
-- TOC entry 2830 (class 2606 OID 54416)
-- Dependencies: 1876 2472 1941
-- Name: fkcd5e3ce839fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_suivi
    ADD CONSTRAINT fkcd5e3ce839fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2896 (class 2606 OID 54421)
-- Dependencies: 2005 2019 2671
-- Name: fkce7075e087ff1713; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY produit_prescrit
    ADD CONSTRAINT fkce7075e087ff1713 FOREIGN KEY (id_prescription) REFERENCES prescription(id);


--
-- TOC entry 2897 (class 2606 OID 54426)
-- Dependencies: 1861 2440 2019
-- Name: fkce7075e09d7535de; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY produit_prescrit
    ADD CONSTRAINT fkce7075e09d7535de FOREIGN KEY (id_conditionnement) REFERENCES conditionnement(id);


--
-- TOC entry 2898 (class 2606 OID 54431)
-- Dependencies: 2011 2681 2019
-- Name: fkce7075e0a1ddf650; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY produit_prescrit
    ADD CONSTRAINT fkce7075e0a1ddf650 FOREIGN KEY (id_produit) REFERENCES produit(id);


--
-- TOC entry 2903 (class 2606 OID 54436)
-- Dependencies: 2027 2443 1863
-- Name: fkd04e1a4191ecd533; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY promoteur_sigrec
    ADD CONSTRAINT fkd04e1a4191ecd533 FOREIGN KEY (id_contact) REFERENCES contact_sigrec(id);


--
-- TOC entry 2868 (class 2606 OID 54441)
-- Dependencies: 1979 2629 1980
-- Name: fkd0e894cf2bee4c2b; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvtstock_document
    ADD CONSTRAINT fkd0e894cf2bee4c2b FOREIGN KEY (id_mvtstock) REFERENCES mvtstock(id);


--
-- TOC entry 2905 (class 2606 OID 54446)
-- Dependencies: 2744 2047 2031
-- Name: fkd387012940161942; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY regle_surcout
    ADD CONSTRAINT fkd387012940161942 FOREIGN KEY (id_theme) REFERENCES theme(id);


--
-- TOC entry 2906 (class 2606 OID 54451)
-- Dependencies: 2431 1855 2031
-- Name: fkd387012961ea981e; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY regle_surcout
    ADD CONSTRAINT fkd387012961ea981e FOREIGN KEY (id_categorie) REFERENCES categorie(id);


--
-- TOC entry 2826 (class 2606 OID 54456)
-- Dependencies: 2037 1938 2728
-- Name: fkd3f3f8e31cf65f51; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_service
    ADD CONSTRAINT fkd3f3f8e31cf65f51 FOREIGN KEY (id_service) REFERENCES service(id);


--
-- TOC entry 2827 (class 2606 OID 54461)
-- Dependencies: 2472 1938 1876
-- Name: fkd3f3f8e339fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_service
    ADD CONSTRAINT fkd3f3f8e339fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2805 (class 2606 OID 54466)
-- Dependencies: 1899 1901 2505
-- Name: fkd4e62fd5387f8764; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_design_suivi
    ADD CONSTRAINT fkd4e62fd5387f8764 FOREIGN KEY (id_detail_design) REFERENCES essai_detail_design(id);


--
-- TOC entry 2764 (class 2606 OID 54471)
-- Dependencies: 2443 1851 1863
-- Name: fkdb9e6f7191ecd533; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY assurance_sigrec
    ADD CONSTRAINT fkdb9e6f7191ecd533 FOREIGN KEY (id_contact) REFERENCES contact_sigrec(id);


--
-- TOC entry 2765 (class 2606 OID 54476)
-- Dependencies: 1851 1939 2556
-- Name: fkdb9e6f71a81b195e; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY assurance_sigrec
    ADD CONSTRAINT fkdb9e6f71a81b195e FOREIGN KEY (id_essai) REFERENCES essai_sigrec(id);


--
-- TOC entry 2828 (class 2606 OID 54481)
-- Dependencies: 2598 1939 1966
-- Name: fkddbf4e314614c469; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_sigrec
    ADD CONSTRAINT fkddbf4e314614c469 FOREIGN KEY (id_investigateurprincipal) REFERENCES investigateur_sigrec(id);


--
-- TOC entry 2829 (class 2606 OID 54486)
-- Dependencies: 2707 2027 1939
-- Name: fkddbf4e31d4112aed; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_sigrec
    ADD CONSTRAINT fkddbf4e31d4112aed FOREIGN KEY (id_promoteur) REFERENCES promoteur_sigrec(id);


--
-- TOC entry 2856 (class 2606 OID 54491)
-- Dependencies: 2629 1975 1979
-- Name: fkdfdef25e3e8f1c97; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvt_destruction
    ADD CONSTRAINT fkdfdef25e3e8f1c97 FOREIGN KEY (id) REFERENCES mvtstock(id);


--
-- TOC entry 2757 (class 2606 OID 54496)
-- Dependencies: 1846 1857 2434
-- Name: fke2c002cf5c631481; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY arc_investigateur_sigrec
    ADD CONSTRAINT fke2c002cf5c631481 FOREIGN KEY (id_centre) REFERENCES centre_sigrec(id);


--
-- TOC entry 2758 (class 2606 OID 54501)
-- Dependencies: 1846 2443 1863
-- Name: fke2c002cf91ecd533; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY arc_investigateur_sigrec
    ADD CONSTRAINT fke2c002cf91ecd533 FOREIGN KEY (id_contact) REFERENCES contact_sigrec(id);


--
-- TOC entry 2759 (class 2606 OID 54506)
-- Dependencies: 1939 1846 2556
-- Name: fke2c002cfa81b195e; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY arc_investigateur_sigrec
    ADD CONSTRAINT fke2c002cfa81b195e FOREIGN KEY (id_essai) REFERENCES essai_sigrec(id);


--
-- TOC entry 2874 (class 2606 OID 54511)
-- Dependencies: 1995 1943 2563
-- Name: fke55d5022cd45a413; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY pharmacie
    ADD CONSTRAINT fke55d5022cd45a413 FOREIGN KEY (id_etablissement) REFERENCES etablissement(id);


--
-- TOC entry 2800 (class 2606 OID 54516)
-- Dependencies: 1891 1876 2472
-- Name: fke7ea68af39fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_contacts
    ADD CONSTRAINT fke7ea68af39fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2890 (class 2606 OID 54521)
-- Dependencies: 2011 1915 2527
-- Name: fked8dcda9ba4507a4; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY produit
    ADD CONSTRAINT fked8dcda9ba4507a4 FOREIGN KEY (id_detailproduit) REFERENCES essai_detail_produit(id);


--
-- TOC entry 2892 (class 2606 OID 54526)
-- Dependencies: 2014 1995 2652
-- Name: fkef34b7c14de40194; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY produit_detail_stockage
    ADD CONSTRAINT fkef34b7c14de40194 FOREIGN KEY (id_pharmacie) REFERENCES pharmacie(id);


--
-- TOC entry 2893 (class 2606 OID 54531)
-- Dependencies: 2014 2012 2683
-- Name: fkef34b7c1a24a8716; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY produit_detail_stockage
    ADD CONSTRAINT fkef34b7c1a24a8716 FOREIGN KEY (id_detail_logistique) REFERENCES produit_detail_logistique(id);


--
-- TOC entry 2894 (class 2606 OID 54536)
-- Dependencies: 2014 2045 2741
-- Name: fkef34b7c1d78f7902; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY produit_detail_stockage
    ADD CONSTRAINT fkef34b7c1d78f7902 FOREIGN KEY (id_stockage) REFERENCES stockage(id);


--
-- TOC entry 2787 (class 2606 OID 54541)
-- Dependencies: 1867 2449 1874
-- Name: fkf50b7c271a1781c6; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY element_to_check
    ADD CONSTRAINT fkf50b7c271a1781c6 FOREIGN KEY (id_dispensation) REFERENCES dispensation(id);


--
-- TOC entry 2788 (class 2606 OID 54546)
-- Dependencies: 1874 2019 2695
-- Name: fkf50b7c276d6ee647; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY element_to_check
    ADD CONSTRAINT fkf50b7c276d6ee647 FOREIGN KEY (id_produitprescrit) REFERENCES produit_prescrit(id);


--
-- TOC entry 2789 (class 2606 OID 54551)
-- Dependencies: 1874 1991 2646
-- Name: fkf50b7c278800c21; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY element_to_check
    ADD CONSTRAINT fkf50b7c278800c21 FOREIGN KEY (id_personne) REFERENCES personne(id);


--
-- TOC entry 2790 (class 2606 OID 54556)
-- Dependencies: 1874 1985 2637
-- Name: fkf50b7c2797d4f410; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY element_to_check
    ADD CONSTRAINT fkf50b7c2797d4f410 FOREIGN KEY (id_ordonnancier) REFERENCES ordonnancier_fab_reconst(id);


--
-- TOC entry 2774 (class 2606 OID 54561)
-- Dependencies: 1865 1863 2443
-- Name: fkf5281d5e91ecd533; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY cro_sigrec
    ADD CONSTRAINT fkf5281d5e91ecd533 FOREIGN KEY (id_contact) REFERENCES contact_sigrec(id);


--
-- TOC entry 2775 (class 2606 OID 54566)
-- Dependencies: 2556 1865 1939
-- Name: fkf5281d5ea81b195e; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY cro_sigrec
    ADD CONSTRAINT fkf5281d5ea81b195e FOREIGN KEY (id_essai) REFERENCES essai_sigrec(id);


--
-- TOC entry 2815 (class 2606 OID 54571)
-- Dependencies: 1915 1876 2472
-- Name: fkf62049cd39fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_produit
    ADD CONSTRAINT fkf62049cd39fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2762 (class 2606 OID 54576)
-- Dependencies: 2037 2728 1850
-- Name: fkfa113201cf65f51; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY arcinvestigateur_service
    ADD CONSTRAINT fkfa113201cf65f51 FOREIGN KEY (id_service) REFERENCES service(id);


--
-- TOC entry 2763 (class 2606 OID 54581)
-- Dependencies: 1991 2646 1850
-- Name: fkfa11320dde432bd; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY arcinvestigateur_service
    ADD CONSTRAINT fkfa11320dde432bd FOREIGN KEY (id_arcinvestigateur) REFERENCES personne(id);


--
-- TOC entry 2854 (class 2606 OID 54586)
-- Dependencies: 1974 2652 1995
-- Name: fkfd557b77382dd136; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvt_cession_pui
    ADD CONSTRAINT fkfd557b77382dd136 FOREIGN KEY (id_pharmaciedest) REFERENCES pharmacie(id);


--
-- TOC entry 2855 (class 2606 OID 54591)
-- Dependencies: 1979 2629 1974
-- Name: fkfd557b773e8f1c97; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvt_cession_pui
    ADD CONSTRAINT fkfd557b773e8f1c97 FOREIGN KEY (id) REFERENCES mvtstock(id);


--
-- TOC entry 2927 (class 2606 OID 54649)
-- Dependencies: 2052 2629 1979
-- Name: fkfe8aa4433e8f1c97; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvt_preparation
    ADD CONSTRAINT fkfe8aa4433e8f1c97 FOREIGN KEY (id) REFERENCES mvtstock(id);


--
-- TOC entry 2818 (class 2606 OID 54596)
-- Dependencies: 2556 1921 1939
-- Name: fkfff0213fa81b195e; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_recherche_sigrec
    ADD CONSTRAINT fkfff0213fa81b195e FOREIGN KEY (id_essai) REFERENCES essai_sigrec(id);


--
-- TOC entry 2926 (class 2606 OID 54671)
-- Dependencies: 2637 1985 2051
-- Name: mvt_preparationentree_id_ordonnancier_fkey; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvt_preparationentree
    ADD CONSTRAINT mvt_preparationentree_id_ordonnancier_fkey FOREIGN KEY (id_ordonnancier) REFERENCES ordonnancier_fab_reconst(id);


--
-- TOC entry 2933 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2011-11-02 10:30:49

--
-- PostgreSQL database dump complete
--

