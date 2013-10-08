--
-- PostgreSQL database dump
--

-- Started on 2011-05-24 14:18:48

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

--
-- TOC entry 662 (class 2612 OID 16386)
-- Name: plpgsql; Type: PROCEDURAL LANGUAGE; Schema: -; Owner: postgres
--

CREATE PROCEDURAL LANGUAGE plpgsql;


ALTER PROCEDURAL LANGUAGE plpgsql OWNER TO postgres;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1835 (class 1259 OID 142214)
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
-- TOC entry 1834 (class 1259 OID 142212)
-- Dependencies: 6 1835
-- Name: arc_investigateur_sigrec_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE arc_investigateur_sigrec_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.arc_investigateur_sigrec_id_seq OWNER TO eclipse;

--
-- TOC entry 2896 (class 0 OID 0)
-- Dependencies: 1834
-- Name: arc_investigateur_sigrec_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE arc_investigateur_sigrec_id_seq OWNED BY arc_investigateur_sigrec.id;


--
-- TOC entry 1837 (class 1259 OID 142225)
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
-- TOC entry 1836 (class 1259 OID 142223)
-- Dependencies: 6 1837
-- Name: arc_promoteur_sigrec_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE arc_promoteur_sigrec_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.arc_promoteur_sigrec_id_seq OWNER TO eclipse;

--
-- TOC entry 2897 (class 0 OID 0)
-- Dependencies: 1836
-- Name: arc_promoteur_sigrec_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE arc_promoteur_sigrec_id_seq OWNED BY arc_promoteur_sigrec.id;


--
-- TOC entry 1838 (class 1259 OID 142234)
-- Dependencies: 6
-- Name: arcinvestigateur_service; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE arcinvestigateur_service (
    id_arcinvestigateur bigint NOT NULL,
    id_service bigint NOT NULL
);


ALTER TABLE public.arcinvestigateur_service OWNER TO eclipse;

--
-- TOC entry 1840 (class 1259 OID 142241)
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
-- TOC entry 1839 (class 1259 OID 142239)
-- Dependencies: 1840 6
-- Name: assurance_sigrec_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE assurance_sigrec_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.assurance_sigrec_id_seq OWNER TO eclipse;

--
-- TOC entry 2898 (class 0 OID 0)
-- Dependencies: 1839
-- Name: assurance_sigrec_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE assurance_sigrec_id_seq OWNED BY assurance_sigrec.id;


--
-- TOC entry 1842 (class 1259 OID 142249)
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
-- TOC entry 1841 (class 1259 OID 142247)
-- Dependencies: 1842 6
-- Name: bras_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE bras_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.bras_id_seq OWNER TO eclipse;

--
-- TOC entry 2899 (class 0 OID 0)
-- Dependencies: 1841
-- Name: bras_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE bras_id_seq OWNED BY bras.id;


--
-- TOC entry 1844 (class 1259 OID 142260)
-- Dependencies: 6
-- Name: categorie; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE categorie (
    id bigint NOT NULL,
    acte character varying(255),
    libelle character varying(255),
    id_theme bigint NOT NULL
);


ALTER TABLE public.categorie OWNER TO eclipse;

--
-- TOC entry 1843 (class 1259 OID 142258)
-- Dependencies: 6 1844
-- Name: categorie_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE categorie_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.categorie_id_seq OWNER TO eclipse;

--
-- TOC entry 2900 (class 0 OID 0)
-- Dependencies: 1843
-- Name: categorie_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE categorie_id_seq OWNED BY categorie.id;


--
-- TOC entry 1846 (class 1259 OID 142271)
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
-- TOC entry 1845 (class 1259 OID 142269)
-- Dependencies: 6 1846
-- Name: centre_sigrec_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE centre_sigrec_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.centre_sigrec_id_seq OWNER TO eclipse;

--
-- TOC entry 2901 (class 0 OID 0)
-- Dependencies: 1845
-- Name: centre_sigrec_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE centre_sigrec_id_seq OWNED BY centre_sigrec.id;


--
-- TOC entry 1848 (class 1259 OID 142282)
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
-- TOC entry 1847 (class 1259 OID 142280)
-- Dependencies: 6 1848
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
-- TOC entry 2902 (class 0 OID 0)
-- Dependencies: 1847
-- Name: co_investigateur_sigrec_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE co_investigateur_sigrec_id_seq OWNED BY co_investigateur_sigrec.id;


--
-- TOC entry 1850 (class 1259 OID 142293)
-- Dependencies: 6
-- Name: conditionnement; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE conditionnement (
    id bigint NOT NULL,
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


ALTER TABLE public.conditionnement OWNER TO eclipse;

--
-- TOC entry 1849 (class 1259 OID 142291)
-- Dependencies: 6 1850
-- Name: conditionnement_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE conditionnement_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.conditionnement_id_seq OWNER TO eclipse;

--
-- TOC entry 2903 (class 0 OID 0)
-- Dependencies: 1849
-- Name: conditionnement_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE conditionnement_id_seq OWNED BY conditionnement.id;


--
-- TOC entry 1852 (class 1259 OID 142304)
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
-- TOC entry 1851 (class 1259 OID 142302)
-- Dependencies: 1852 6
-- Name: contact_sigrec_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE contact_sigrec_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.contact_sigrec_id_seq OWNER TO eclipse;

--
-- TOC entry 2904 (class 0 OID 0)
-- Dependencies: 1851
-- Name: contact_sigrec_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE contact_sigrec_id_seq OWNED BY contact_sigrec.id;


--
-- TOC entry 1854 (class 1259 OID 142315)
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
-- TOC entry 1853 (class 1259 OID 142313)
-- Dependencies: 1854 6
-- Name: cro_sigrec_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE cro_sigrec_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.cro_sigrec_id_seq OWNER TO eclipse;

--
-- TOC entry 2905 (class 0 OID 0)
-- Dependencies: 1853
-- Name: cro_sigrec_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE cro_sigrec_id_seq OWNED BY cro_sigrec.id;


--
-- TOC entry 1856 (class 1259 OID 142323)
-- Dependencies: 6
-- Name: dispensation; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE dispensation (
    id bigint NOT NULL,
    commentaire text,
    datedispensation timestamp without time zone,
    dispense boolean,
    numordonnancier integer,
    id_ordonnancier bigint,
    id_pharmacie bigint NOT NULL,
    id_prescription bigint NOT NULL
);


ALTER TABLE public.dispensation OWNER TO eclipse;

--
-- TOC entry 1855 (class 1259 OID 142321)
-- Dependencies: 1856 6
-- Name: dispensation_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE dispensation_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.dispensation_id_seq OWNER TO eclipse;

--
-- TOC entry 2906 (class 0 OID 0)
-- Dependencies: 1855
-- Name: dispensation_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE dispensation_id_seq OWNED BY dispensation.id;


--
-- TOC entry 1857 (class 1259 OID 142332)
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
-- TOC entry 1859 (class 1259 OID 142342)
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
    id_detail_surcout bigint
);


ALTER TABLE public.donnees_prevision OWNER TO eclipse;

--
-- TOC entry 1858 (class 1259 OID 142340)
-- Dependencies: 1859 6
-- Name: donnees_prevision_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE donnees_prevision_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.donnees_prevision_id_seq OWNER TO eclipse;

--
-- TOC entry 2907 (class 0 OID 0)
-- Dependencies: 1858
-- Name: donnees_prevision_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE donnees_prevision_id_seq OWNED BY donnees_prevision.id;


--
-- TOC entry 1861 (class 1259 OID 142350)
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
-- TOC entry 1860 (class 1259 OID 142348)
-- Dependencies: 1861 6
-- Name: dotation_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE dotation_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.dotation_id_seq OWNER TO eclipse;

--
-- TOC entry 2908 (class 0 OID 0)
-- Dependencies: 1860
-- Name: dotation_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE dotation_id_seq OWNED BY dotation.id;


--
-- TOC entry 1863 (class 1259 OID 142361)
-- Dependencies: 6
-- Name: element_to_check; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE element_to_check (
    id bigint NOT NULL,
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


ALTER TABLE public.element_to_check OWNER TO eclipse;

--
-- TOC entry 1862 (class 1259 OID 142359)
-- Dependencies: 6 1863
-- Name: element_to_check_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE element_to_check_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.element_to_check_id_seq OWNER TO eclipse;

--
-- TOC entry 2909 (class 0 OID 0)
-- Dependencies: 1862
-- Name: element_to_check_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE element_to_check_id_seq OWNED BY element_to_check.id;


--
-- TOC entry 1865 (class 1259 OID 142372)
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
    id_promoteur bigint NOT NULL
);


ALTER TABLE public.essai OWNER TO eclipse;

--
-- TOC entry 1867 (class 1259 OID 142383)
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
-- TOC entry 1866 (class 1259 OID 142381)
-- Dependencies: 1867 6
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
-- TOC entry 2910 (class 0 OID 0)
-- Dependencies: 1866
-- Name: essai_commentaire_detail_administratif_archi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_commentaire_detail_administratif_archi_id_seq OWNED BY essai_commentaire_detail_administratif_archi.id;


--
-- TOC entry 1869 (class 1259 OID 142394)
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
-- TOC entry 1868 (class 1259 OID 142392)
-- Dependencies: 1869 6
-- Name: essai_commentaire_detail_faisabilite_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_commentaire_detail_faisabilite_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_commentaire_detail_faisabilite_id_seq OWNER TO eclipse;

--
-- TOC entry 2911 (class 0 OID 0)
-- Dependencies: 1868
-- Name: essai_commentaire_detail_faisabilite_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_commentaire_detail_faisabilite_id_seq OWNED BY essai_commentaire_detail_faisabilite.id;


--
-- TOC entry 1871 (class 1259 OID 142405)
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
-- TOC entry 1870 (class 1259 OID 142403)
-- Dependencies: 6 1871
-- Name: essai_commentaire_detail_recherche_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_commentaire_detail_recherche_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_commentaire_detail_recherche_id_seq OWNER TO eclipse;

--
-- TOC entry 2912 (class 0 OID 0)
-- Dependencies: 1870
-- Name: essai_commentaire_detail_recherche_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_commentaire_detail_recherche_id_seq OWNED BY essai_commentaire_detail_recherche.id;


--
-- TOC entry 1873 (class 1259 OID 142416)
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
    autorisationdistribution_docsdosspapier boolean,
    autorisationimportation_docsdosspapier boolean,
    bropro_docsdosspapier boolean,
    cpp_accord boolean,
    cpp_date timestamp without time zone,
    cpp_docsdosspapier boolean,
    cpp_nom character varying(255),
    conv_signee boolean,
    conv_date timestamp without time zone,
    conv_docsdosspapier boolean,
    proto_docsdosspapier boolean,
    id_essai bigint
);


ALTER TABLE public.essai_detail_administratif OWNER TO eclipse;

--
-- TOC entry 1872 (class 1259 OID 142414)
-- Dependencies: 1873 6
-- Name: essai_detail_administratif_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_administratif_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_administratif_id_seq OWNER TO eclipse;

--
-- TOC entry 2913 (class 0 OID 0)
-- Dependencies: 1872
-- Name: essai_detail_administratif_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_administratif_id_seq OWNED BY essai_detail_administratif.id;


--
-- TOC entry 1875 (class 1259 OID 142427)
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
-- TOC entry 1874 (class 1259 OID 142425)
-- Dependencies: 6 1875
-- Name: essai_detail_administratif_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_administratif_suivi_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_administratif_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 2914 (class 0 OID 0)
-- Dependencies: 1874
-- Name: essai_detail_administratif_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_administratif_suivi_id_seq OWNED BY essai_detail_administratif_suivi.id;


--
-- TOC entry 1877 (class 1259 OID 142435)
-- Dependencies: 6
-- Name: essai_detail_autres_documents; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_detail_autres_documents (
    id bigint NOT NULL,
    id_essai bigint
);


ALTER TABLE public.essai_detail_autres_documents OWNER TO eclipse;

--
-- TOC entry 1876 (class 1259 OID 142433)
-- Dependencies: 6 1877
-- Name: essai_detail_autres_documents_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_autres_documents_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_autres_documents_id_seq OWNER TO eclipse;

--
-- TOC entry 2915 (class 0 OID 0)
-- Dependencies: 1876
-- Name: essai_detail_autres_documents_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_autres_documents_id_seq OWNED BY essai_detail_autres_documents.id;


--
-- TOC entry 1879 (class 1259 OID 142443)
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
-- TOC entry 1878 (class 1259 OID 142441)
-- Dependencies: 1879 6
-- Name: essai_detail_autres_documents_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_autres_documents_suivi_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_autres_documents_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 2916 (class 0 OID 0)
-- Dependencies: 1878
-- Name: essai_detail_autres_documents_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_autres_documents_suivi_id_seq OWNED BY essai_detail_autres_documents_suivi.id;


--
-- TOC entry 1881 (class 1259 OID 142451)
-- Dependencies: 6
-- Name: essai_detail_contacts; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_detail_contacts (
    id bigint NOT NULL,
    id_essai bigint
);


ALTER TABLE public.essai_detail_contacts OWNER TO eclipse;

--
-- TOC entry 1880 (class 1259 OID 142449)
-- Dependencies: 6 1881
-- Name: essai_detail_contacts_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_contacts_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_contacts_id_seq OWNER TO eclipse;

--
-- TOC entry 2917 (class 0 OID 0)
-- Dependencies: 1880
-- Name: essai_detail_contacts_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_contacts_id_seq OWNED BY essai_detail_contacts.id;


--
-- TOC entry 1883 (class 1259 OID 142459)
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
-- TOC entry 1882 (class 1259 OID 142457)
-- Dependencies: 6 1883
-- Name: essai_detail_contacts_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_contacts_suivi_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_contacts_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 2918 (class 0 OID 0)
-- Dependencies: 1882
-- Name: essai_detail_contacts_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_contacts_suivi_id_seq OWNED BY essai_detail_contacts_suivi.id;


--
-- TOC entry 1885 (class 1259 OID 142467)
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
    id_essai bigint
);


ALTER TABLE public.essai_detail_dates OWNER TO eclipse;

--
-- TOC entry 1884 (class 1259 OID 142465)
-- Dependencies: 1885 6
-- Name: essai_detail_dates_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_dates_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_dates_id_seq OWNER TO eclipse;

--
-- TOC entry 2919 (class 0 OID 0)
-- Dependencies: 1884
-- Name: essai_detail_dates_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_dates_id_seq OWNED BY essai_detail_dates.id;


--
-- TOC entry 1887 (class 1259 OID 142475)
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
-- TOC entry 1886 (class 1259 OID 142473)
-- Dependencies: 6 1887
-- Name: essai_detail_dates_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_dates_suivi_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_dates_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 2920 (class 0 OID 0)
-- Dependencies: 1886
-- Name: essai_detail_dates_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_dates_suivi_id_seq OWNED BY essai_detail_dates_suivi.id;


--
-- TOC entry 1889 (class 1259 OID 142483)
-- Dependencies: 6
-- Name: essai_detail_design; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_detail_design (
    id bigint NOT NULL,
    id_essai bigint
);


ALTER TABLE public.essai_detail_design OWNER TO eclipse;

--
-- TOC entry 1888 (class 1259 OID 142481)
-- Dependencies: 6 1889
-- Name: essai_detail_design_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_design_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_design_id_seq OWNER TO eclipse;

--
-- TOC entry 2921 (class 0 OID 0)
-- Dependencies: 1888
-- Name: essai_detail_design_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_design_id_seq OWNED BY essai_detail_design.id;


--
-- TOC entry 1891 (class 1259 OID 142491)
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
-- TOC entry 1890 (class 1259 OID 142489)
-- Dependencies: 1891 6
-- Name: essai_detail_design_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_design_suivi_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_design_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 2922 (class 0 OID 0)
-- Dependencies: 1890
-- Name: essai_detail_design_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_design_suivi_id_seq OWNED BY essai_detail_design_suivi.id;


--
-- TOC entry 1893 (class 1259 OID 142499)
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
-- TOC entry 1892 (class 1259 OID 142497)
-- Dependencies: 6 1893
-- Name: essai_detail_etat_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_etat_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_etat_id_seq OWNER TO eclipse;

--
-- TOC entry 2923 (class 0 OID 0)
-- Dependencies: 1892
-- Name: essai_detail_etat_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_etat_id_seq OWNED BY essai_detail_etat.id;


--
-- TOC entry 1895 (class 1259 OID 142510)
-- Dependencies: 6
-- Name: essai_detail_faisabilite; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_detail_faisabilite (
    id bigint NOT NULL,
    concl_convsignee boolean,
    concl_dateaccord timestamp without time zone,
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
-- TOC entry 1894 (class 1259 OID 142508)
-- Dependencies: 6 1895
-- Name: essai_detail_faisabilite_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_faisabilite_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_faisabilite_id_seq OWNER TO eclipse;

--
-- TOC entry 2924 (class 0 OID 0)
-- Dependencies: 1894
-- Name: essai_detail_faisabilite_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_faisabilite_id_seq OWNED BY essai_detail_faisabilite.id;


--
-- TOC entry 1896 (class 1259 OID 142516)
-- Dependencies: 6
-- Name: essai_detail_faisabilite_service; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_detail_faisabilite_service (
    id_essai bigint NOT NULL,
    id_service bigint NOT NULL
);


ALTER TABLE public.essai_detail_faisabilite_service OWNER TO eclipse;

--
-- TOC entry 1898 (class 1259 OID 142523)
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
-- TOC entry 1897 (class 1259 OID 142521)
-- Dependencies: 1898 6
-- Name: essai_detail_faisabilite_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_faisabilite_suivi_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_faisabilite_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 2925 (class 0 OID 0)
-- Dependencies: 1897
-- Name: essai_detail_faisabilite_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_faisabilite_suivi_id_seq OWNED BY essai_detail_faisabilite_suivi.id;


--
-- TOC entry 1900 (class 1259 OID 142531)
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
    nbpatientsprevustotal integer,
    qualiteinsu integer,
    typeproduitevalue character varying(255),
    unitedureetotalepatientprevue character varying(255),
    unitedureetotaleprevue character varying(255),
    id_essai bigint
);


ALTER TABLE public.essai_detail_pharma OWNER TO eclipse;

--
-- TOC entry 1899 (class 1259 OID 142529)
-- Dependencies: 1900 6
-- Name: essai_detail_pharma_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_pharma_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_pharma_id_seq OWNER TO eclipse;

--
-- TOC entry 2926 (class 0 OID 0)
-- Dependencies: 1899
-- Name: essai_detail_pharma_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_pharma_id_seq OWNED BY essai_detail_pharma.id;


--
-- TOC entry 1901 (class 1259 OID 142540)
-- Dependencies: 6
-- Name: essai_detail_pharma_pharmacie; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_detail_pharma_pharmacie (
    id_detail_pharma bigint NOT NULL,
    id_pharmacie bigint NOT NULL
);


ALTER TABLE public.essai_detail_pharma_pharmacie OWNER TO eclipse;

--
-- TOC entry 1903 (class 1259 OID 142547)
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
-- TOC entry 1902 (class 1259 OID 142545)
-- Dependencies: 1903 6
-- Name: essai_detail_pharma_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_pharma_suivi_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_pharma_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 2927 (class 0 OID 0)
-- Dependencies: 1902
-- Name: essai_detail_pharma_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_pharma_suivi_id_seq OWNED BY essai_detail_pharma_suivi.id;


--
-- TOC entry 1905 (class 1259 OID 142555)
-- Dependencies: 6
-- Name: essai_detail_produit; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_detail_produit (
    id bigint NOT NULL,
    id_essai bigint
);


ALTER TABLE public.essai_detail_produit OWNER TO eclipse;

--
-- TOC entry 1904 (class 1259 OID 142553)
-- Dependencies: 6 1905
-- Name: essai_detail_produit_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_produit_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_produit_id_seq OWNER TO eclipse;

--
-- TOC entry 2928 (class 0 OID 0)
-- Dependencies: 1904
-- Name: essai_detail_produit_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_produit_id_seq OWNED BY essai_detail_produit.id;


--
-- TOC entry 1907 (class 1259 OID 142563)
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
-- TOC entry 1906 (class 1259 OID 142561)
-- Dependencies: 6 1907
-- Name: essai_detail_produit_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_produit_suivi_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_produit_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 2929 (class 0 OID 0)
-- Dependencies: 1906
-- Name: essai_detail_produit_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_produit_suivi_id_seq OWNED BY essai_detail_produit_suivi.id;


--
-- TOC entry 1909 (class 1259 OID 142571)
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
-- TOC entry 1908 (class 1259 OID 142569)
-- Dependencies: 1909 6
-- Name: essai_detail_recherche_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_recherche_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_recherche_id_seq OWNER TO eclipse;

--
-- TOC entry 2930 (class 0 OID 0)
-- Dependencies: 1908
-- Name: essai_detail_recherche_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_recherche_id_seq OWNED BY essai_detail_recherche.id;


--
-- TOC entry 1911 (class 1259 OID 142582)
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
-- TOC entry 1910 (class 1259 OID 142580)
-- Dependencies: 1911 6
-- Name: essai_detail_recherche_sigrec_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_recherche_sigrec_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_recherche_sigrec_id_seq OWNER TO eclipse;

--
-- TOC entry 2931 (class 0 OID 0)
-- Dependencies: 1910
-- Name: essai_detail_recherche_sigrec_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_recherche_sigrec_id_seq OWNED BY essai_detail_recherche_sigrec.id;


--
-- TOC entry 1913 (class 1259 OID 142593)
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
-- TOC entry 1912 (class 1259 OID 142591)
-- Dependencies: 6 1913
-- Name: essai_detail_recherche_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_recherche_suivi_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_recherche_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 2932 (class 0 OID 0)
-- Dependencies: 1912
-- Name: essai_detail_recherche_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_recherche_suivi_id_seq OWNED BY essai_detail_recherche_suivi.id;


--
-- TOC entry 1915 (class 1259 OID 142601)
-- Dependencies: 6
-- Name: essai_detail_surcout; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_detail_surcout (
    id bigint NOT NULL,
    id_essai bigint
);


ALTER TABLE public.essai_detail_surcout OWNER TO eclipse;

--
-- TOC entry 1914 (class 1259 OID 142599)
-- Dependencies: 1915 6
-- Name: essai_detail_surcout_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_surcout_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_surcout_id_seq OWNER TO eclipse;

--
-- TOC entry 2933 (class 0 OID 0)
-- Dependencies: 1914
-- Name: essai_detail_surcout_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_surcout_id_seq OWNED BY essai_detail_surcout.id;


--
-- TOC entry 1917 (class 1259 OID 142609)
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
-- TOC entry 1916 (class 1259 OID 142607)
-- Dependencies: 1917 6
-- Name: essai_detail_surcout_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_detail_surcout_suivi_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_detail_surcout_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 2934 (class 0 OID 0)
-- Dependencies: 1916
-- Name: essai_detail_surcout_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_detail_surcout_suivi_id_seq OWNED BY essai_detail_surcout_suivi.id;


--
-- TOC entry 1919 (class 1259 OID 142617)
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
    id_detailadministratif bigint NOT NULL
);


ALTER TABLE public.essai_document_detail_administratif OWNER TO eclipse;

--
-- TOC entry 1918 (class 1259 OID 142615)
-- Dependencies: 1919 6
-- Name: essai_document_detail_administratif_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_document_detail_administratif_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_document_detail_administratif_id_seq OWNER TO eclipse;

--
-- TOC entry 2935 (class 0 OID 0)
-- Dependencies: 1918
-- Name: essai_document_detail_administratif_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_document_detail_administratif_id_seq OWNED BY essai_document_detail_administratif.id;


--
-- TOC entry 1921 (class 1259 OID 142628)
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
-- TOC entry 1920 (class 1259 OID 142626)
-- Dependencies: 1921 6
-- Name: essai_document_detail_autres_documents_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_document_detail_autres_documents_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_document_detail_autres_documents_id_seq OWNER TO eclipse;

--
-- TOC entry 2936 (class 0 OID 0)
-- Dependencies: 1920
-- Name: essai_document_detail_autres_documents_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_document_detail_autres_documents_id_seq OWNED BY essai_document_detail_autres_documents.id;


--
-- TOC entry 1923 (class 1259 OID 142639)
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
-- TOC entry 1922 (class 1259 OID 142637)
-- Dependencies: 1923 6
-- Name: essai_document_detail_pharma_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_document_detail_pharma_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_document_detail_pharma_id_seq OWNER TO eclipse;

--
-- TOC entry 2937 (class 0 OID 0)
-- Dependencies: 1922
-- Name: essai_document_detail_pharma_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_document_detail_pharma_id_seq OWNED BY essai_document_detail_pharma.id;


--
-- TOC entry 1925 (class 1259 OID 142650)
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
-- TOC entry 1924 (class 1259 OID 142648)
-- Dependencies: 6 1925
-- Name: essai_document_detail_surcout_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_document_detail_surcout_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_document_detail_surcout_id_seq OWNER TO eclipse;

--
-- TOC entry 2938 (class 0 OID 0)
-- Dependencies: 1924
-- Name: essai_document_detail_surcout_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_document_detail_surcout_id_seq OWNED BY essai_document_detail_surcout.id;


--
-- TOC entry 1864 (class 1259 OID 142370)
-- Dependencies: 6 1865
-- Name: essai_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_id_seq OWNER TO eclipse;

--
-- TOC entry 2939 (class 0 OID 0)
-- Dependencies: 1864
-- Name: essai_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_id_seq OWNED BY essai.id;


--
-- TOC entry 1926 (class 1259 OID 142659)
-- Dependencies: 6
-- Name: essai_service; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE essai_service (
    id_essai bigint NOT NULL,
    id_service bigint NOT NULL
);


ALTER TABLE public.essai_service OWNER TO eclipse;

--
-- TOC entry 1928 (class 1259 OID 142666)
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
-- TOC entry 1927 (class 1259 OID 142664)
-- Dependencies: 6 1928
-- Name: essai_sigrec_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_sigrec_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_sigrec_id_seq OWNER TO eclipse;

--
-- TOC entry 2940 (class 0 OID 0)
-- Dependencies: 1927
-- Name: essai_sigrec_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_sigrec_id_seq OWNED BY essai_sigrec.id;


--
-- TOC entry 1930 (class 1259 OID 142677)
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
-- TOC entry 1929 (class 1259 OID 142675)
-- Dependencies: 1930 6
-- Name: essai_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE essai_suivi_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.essai_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 2941 (class 0 OID 0)
-- Dependencies: 1929
-- Name: essai_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE essai_suivi_id_seq OWNED BY essai_suivi.id;


--
-- TOC entry 1932 (class 1259 OID 142685)
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
-- TOC entry 1931 (class 1259 OID 142683)
-- Dependencies: 6 1932
-- Name: etablissement_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE etablissement_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.etablissement_id_seq OWNER TO eclipse;

--
-- TOC entry 2942 (class 0 OID 0)
-- Dependencies: 1931
-- Name: etablissement_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE etablissement_id_seq OWNED BY etablissement.id;


--
-- TOC entry 1934 (class 1259 OID 142696)
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
-- TOC entry 1933 (class 1259 OID 142694)
-- Dependencies: 6 1934
-- Name: etablissement_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE etablissement_suivi_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.etablissement_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 2943 (class 0 OID 0)
-- Dependencies: 1933
-- Name: etablissement_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE etablissement_suivi_id_seq OWNED BY etablissement_suivi.id;


--
-- TOC entry 1936 (class 1259 OID 142704)
-- Dependencies: 6
-- Name: evenement; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE evenement (
    id bigint NOT NULL,
    arc character varying(255),
    commentaire character varying(255),
    datedebut timestamp without time zone NOT NULL,
    datefin timestamp without time zone NOT NULL,
    heuredebut character varying(255),
    heurefin character varying(255),
    journee boolean,
    libelle character varying(255) NOT NULL,
    personnelpharmacie text,
    resultatvisite character varying(255),
    typeevenement character varying(255) NOT NULL,
    typevisite character varying(255),
    id_essai bigint
);


ALTER TABLE public.evenement OWNER TO eclipse;

--
-- TOC entry 1935 (class 1259 OID 142702)
-- Dependencies: 6 1936
-- Name: evenement_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE evenement_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.evenement_id_seq OWNER TO eclipse;

--
-- TOC entry 2944 (class 0 OID 0)
-- Dependencies: 1935
-- Name: evenement_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE evenement_id_seq OWNED BY evenement.id;


--
-- TOC entry 1938 (class 1259 OID 142715)
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
-- TOC entry 1937 (class 1259 OID 142713)
-- Dependencies: 6 1938
-- Name: evenement_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE evenement_suivi_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.evenement_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 2945 (class 0 OID 0)
-- Dependencies: 1937
-- Name: evenement_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE evenement_suivi_id_seq OWNED BY evenement_suivi.id;


--
-- TOC entry 1940 (class 1259 OID 142723)
-- Dependencies: 6
-- Name: grille; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE grille (
    id bigint NOT NULL,
    id_detail_surcout bigint,
    id_grille_modele bigint NOT NULL
);


ALTER TABLE public.grille OWNER TO eclipse;

--
-- TOC entry 1939 (class 1259 OID 142721)
-- Dependencies: 1940 6
-- Name: grille_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE grille_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.grille_id_seq OWNER TO eclipse;

--
-- TOC entry 2946 (class 0 OID 0)
-- Dependencies: 1939
-- Name: grille_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE grille_id_seq OWNED BY grille.id;


--
-- TOC entry 1942 (class 1259 OID 142731)
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
-- TOC entry 1941 (class 1259 OID 142729)
-- Dependencies: 1942 6
-- Name: grille_modele_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE grille_modele_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.grille_modele_id_seq OWNER TO eclipse;

--
-- TOC entry 2947 (class 0 OID 0)
-- Dependencies: 1941
-- Name: grille_modele_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE grille_modele_id_seq OWNED BY grille_modele.id;


--
-- TOC entry 1944 (class 1259 OID 142739)
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
-- TOC entry 1943 (class 1259 OID 142737)
-- Dependencies: 1944 6
-- Name: habilitation_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE habilitation_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.habilitation_id_seq OWNER TO eclipse;

--
-- TOC entry 2948 (class 0 OID 0)
-- Dependencies: 1943
-- Name: habilitation_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE habilitation_id_seq OWNED BY habilitation.id;


--
-- TOC entry 1946 (class 1259 OID 142750)
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
-- TOC entry 1945 (class 1259 OID 142748)
-- Dependencies: 1946 6
-- Name: historique_patient_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE historique_patient_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.historique_patient_id_seq OWNER TO eclipse;

--
-- TOC entry 2949 (class 0 OID 0)
-- Dependencies: 1945
-- Name: historique_patient_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE historique_patient_id_seq OWNED BY historique_patient.id;


--
-- TOC entry 1948 (class 1259 OID 142761)
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
-- TOC entry 1947 (class 1259 OID 142759)
-- Dependencies: 6 1948
-- Name: incident_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE incident_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.incident_id_seq OWNER TO eclipse;

--
-- TOC entry 2950 (class 0 OID 0)
-- Dependencies: 1947
-- Name: incident_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE incident_id_seq OWNED BY incident.id;


--
-- TOC entry 1950 (class 1259 OID 142772)
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
-- TOC entry 1949 (class 1259 OID 142770)
-- Dependencies: 1950 6
-- Name: incident_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE incident_suivi_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.incident_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 2951 (class 0 OID 0)
-- Dependencies: 1949
-- Name: incident_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE incident_suivi_id_seq OWNED BY incident_suivi.id;


--
-- TOC entry 1952 (class 1259 OID 142780)
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
-- TOC entry 1951 (class 1259 OID 142778)
-- Dependencies: 1952 6
-- Name: inclusion_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE inclusion_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.inclusion_id_seq OWNER TO eclipse;

--
-- TOC entry 2952 (class 0 OID 0)
-- Dependencies: 1951
-- Name: inclusion_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE inclusion_id_seq OWNED BY inclusion.id;


--
-- TOC entry 1953 (class 1259 OID 142789)
-- Dependencies: 6
-- Name: investigateur_service; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE investigateur_service (
    id_investigateur bigint NOT NULL,
    id_service bigint NOT NULL
);


ALTER TABLE public.investigateur_service OWNER TO eclipse;

--
-- TOC entry 1955 (class 1259 OID 142796)
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
-- TOC entry 1954 (class 1259 OID 142794)
-- Dependencies: 6 1955
-- Name: investigateur_sigrec_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE investigateur_sigrec_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.investigateur_sigrec_id_seq OWNER TO eclipse;

--
-- TOC entry 2953 (class 0 OID 0)
-- Dependencies: 1954
-- Name: investigateur_sigrec_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE investigateur_sigrec_id_seq OWNED BY investigateur_sigrec.id;


--
-- TOC entry 1957 (class 1259 OID 142807)
-- Dependencies: 6
-- Name: item; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE item (
    id bigint NOT NULL,
    acte character varying(255),
    categorie character varying(255),
    theme character varying(255),
    id_grille bigint NOT NULL
);


ALTER TABLE public.item OWNER TO eclipse;

--
-- TOC entry 1956 (class 1259 OID 142805)
-- Dependencies: 1957 6
-- Name: item_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE item_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.item_id_seq OWNER TO eclipse;

--
-- TOC entry 2954 (class 0 OID 0)
-- Dependencies: 1956
-- Name: item_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE item_id_seq OWNED BY item.id;


--
-- TOC entry 1958 (class 1259 OID 142816)
-- Dependencies: 6
-- Name: item_regle; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE item_regle (
    id_regle bigint NOT NULL,
    id_item bigint NOT NULL
);


ALTER TABLE public.item_regle OWNER TO eclipse;

--
-- TOC entry 1959 (class 1259 OID 142821)
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
-- TOC entry 1960 (class 1259 OID 142829)
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
    id bigint NOT NULL
);


ALTER TABLE public.mvt_approvisionnement OWNER TO eclipse;

--
-- TOC entry 1961 (class 1259 OID 142837)
-- Dependencies: 6
-- Name: mvt_autre_sortie; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE mvt_autre_sortie (
    commentaire text,
    id bigint NOT NULL
);


ALTER TABLE public.mvt_autre_sortie OWNER TO eclipse;

--
-- TOC entry 1962 (class 1259 OID 142845)
-- Dependencies: 6
-- Name: mvt_cession_pui; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE mvt_cession_pui (
    commentaire text,
    id bigint NOT NULL,
    id_pharmaciedest bigint
);


ALTER TABLE public.mvt_cession_pui OWNER TO eclipse;

--
-- TOC entry 1963 (class 1259 OID 142853)
-- Dependencies: 6
-- Name: mvt_destruction; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE mvt_destruction (
    commentaire text,
    id bigint NOT NULL
);


ALTER TABLE public.mvt_destruction OWNER TO eclipse;

--
-- TOC entry 1964 (class 1259 OID 142861)
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
-- TOC entry 1965 (class 1259 OID 142866)
-- Dependencies: 6
-- Name: mvt_dispensation_globale; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE mvt_dispensation_globale (
    quantitedispensee integer,
    id bigint NOT NULL,
    id_dotation bigint NOT NULL
);


ALTER TABLE public.mvt_dispensation_globale OWNER TO eclipse;

--
-- TOC entry 1966 (class 1259 OID 142871)
-- Dependencies: 6
-- Name: mvt_retour_promoteur; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE mvt_retour_promoteur (
    commentaire text,
    nomsocietetransport character varying(255),
    referenceenvoi character varying(255),
    id bigint NOT NULL
);


ALTER TABLE public.mvt_retour_promoteur OWNER TO eclipse;

--
-- TOC entry 1968 (class 1259 OID 142881)
-- Dependencies: 6
-- Name: mvtstock; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE mvtstock (
    id bigint NOT NULL,
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


ALTER TABLE public.mvtstock OWNER TO eclipse;

--
-- TOC entry 1970 (class 1259 OID 142892)
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
-- TOC entry 1969 (class 1259 OID 142890)
-- Dependencies: 6 1970
-- Name: mvtstock_document_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE mvtstock_document_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.mvtstock_document_id_seq OWNER TO eclipse;

--
-- TOC entry 2955 (class 0 OID 0)
-- Dependencies: 1969
-- Name: mvtstock_document_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE mvtstock_document_id_seq OWNED BY mvtstock_document.id;


--
-- TOC entry 1967 (class 1259 OID 142879)
-- Dependencies: 1968 6
-- Name: mvtstock_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE mvtstock_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.mvtstock_id_seq OWNER TO eclipse;

--
-- TOC entry 2956 (class 0 OID 0)
-- Dependencies: 1967
-- Name: mvtstock_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE mvtstock_id_seq OWNED BY mvtstock.id;


--
-- TOC entry 1972 (class 1259 OID 142903)
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
-- TOC entry 1971 (class 1259 OID 142901)
-- Dependencies: 6 1972
-- Name: ordonnancier_dispensation_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE ordonnancier_dispensation_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.ordonnancier_dispensation_id_seq OWNER TO eclipse;

--
-- TOC entry 2957 (class 0 OID 0)
-- Dependencies: 1971
-- Name: ordonnancier_dispensation_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE ordonnancier_dispensation_id_seq OWNED BY ordonnancier_dispensation.id;


--
-- TOC entry 1974 (class 1259 OID 142911)
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
-- TOC entry 1973 (class 1259 OID 142909)
-- Dependencies: 1974 6
-- Name: ordonnancier_fab_reconst_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE ordonnancier_fab_reconst_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.ordonnancier_fab_reconst_id_seq OWNER TO eclipse;

--
-- TOC entry 2958 (class 0 OID 0)
-- Dependencies: 1973
-- Name: ordonnancier_fab_reconst_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE ordonnancier_fab_reconst_id_seq OWNED BY ordonnancier_fab_reconst.id;


--
-- TOC entry 1976 (class 1259 OID 142919)
-- Dependencies: 6
-- Name: patient; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE patient (
    id bigint NOT NULL,
    adresse text,
    civilite character varying(255),
    codepostal character varying(255),
    datenaissance timestamp without time zone,
    initiales character varying(255),
    mail character varying(255),
    nom character varying(255),
    nomjeunefille character varying(255),
    numeroipp character varying(255),
    prenom character varying(255),
    telephone character varying(255),
    ville character varying(255)
);


ALTER TABLE public.patient OWNER TO eclipse;

--
-- TOC entry 1975 (class 1259 OID 142917)
-- Dependencies: 6 1976
-- Name: patient_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE patient_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.patient_id_seq OWNER TO eclipse;

--
-- TOC entry 2959 (class 0 OID 0)
-- Dependencies: 1975
-- Name: patient_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE patient_id_seq OWNED BY patient.id;


--
-- TOC entry 1978 (class 1259 OID 142930)
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
-- TOC entry 1977 (class 1259 OID 142928)
-- Dependencies: 6 1978
-- Name: patient_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE patient_suivi_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.patient_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 2960 (class 0 OID 0)
-- Dependencies: 1977
-- Name: patient_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE patient_suivi_id_seq OWNED BY patient_suivi.id;


--
-- TOC entry 1980 (class 1259 OID 142938)
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
    telephoneportable character varying(255),
    ville character varying(255),
    nomsociete character varying(255),
    titre character varying(255),
    typepharmacien character varying(255),
    id_promoteur bigint
);


ALTER TABLE public.personne OWNER TO eclipse;

--
-- TOC entry 1979 (class 1259 OID 142936)
-- Dependencies: 6 1980
-- Name: personne_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE personne_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.personne_id_seq OWNER TO eclipse;

--
-- TOC entry 2961 (class 0 OID 0)
-- Dependencies: 1979
-- Name: personne_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE personne_id_seq OWNED BY personne.id;


--
-- TOC entry 1982 (class 1259 OID 142949)
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
-- TOC entry 1981 (class 1259 OID 142947)
-- Dependencies: 6 1982
-- Name: personne_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE personne_suivi_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.personne_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 2962 (class 0 OID 0)
-- Dependencies: 1981
-- Name: personne_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE personne_suivi_id_seq OWNED BY personne_suivi.id;


--
-- TOC entry 1984 (class 1259 OID 142957)
-- Dependencies: 6
-- Name: pharmacie; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE pharmacie (
    id bigint NOT NULL,
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


ALTER TABLE public.pharmacie OWNER TO eclipse;

--
-- TOC entry 1983 (class 1259 OID 142955)
-- Dependencies: 6 1984
-- Name: pharmacie_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE pharmacie_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.pharmacie_id_seq OWNER TO eclipse;

--
-- TOC entry 2963 (class 0 OID 0)
-- Dependencies: 1983
-- Name: pharmacie_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE pharmacie_id_seq OWNED BY pharmacie.id;


--
-- TOC entry 1985 (class 1259 OID 142966)
-- Dependencies: 6
-- Name: pharmacie_site; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE pharmacie_site (
    id_pharmacie bigint NOT NULL,
    id_site bigint NOT NULL
);


ALTER TABLE public.pharmacie_site OWNER TO eclipse;

--
-- TOC entry 1987 (class 1259 OID 142973)
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
-- TOC entry 1986 (class 1259 OID 142971)
-- Dependencies: 6 1987
-- Name: pharmacie_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE pharmacie_suivi_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.pharmacie_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 2964 (class 0 OID 0)
-- Dependencies: 1986
-- Name: pharmacie_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE pharmacie_suivi_id_seq OWNED BY pharmacie_suivi.id;


--
-- TOC entry 1988 (class 1259 OID 142979)
-- Dependencies: 6
-- Name: pharmacien_pharmacie; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE pharmacien_pharmacie (
    id_pharmacien bigint NOT NULL,
    id_pharmacie bigint NOT NULL
);


ALTER TABLE public.pharmacien_pharmacie OWNER TO eclipse;

--
-- TOC entry 1990 (class 1259 OID 142986)
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
-- TOC entry 1989 (class 1259 OID 142984)
-- Dependencies: 6 1990
-- Name: pole_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE pole_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.pole_id_seq OWNER TO eclipse;

--
-- TOC entry 2965 (class 0 OID 0)
-- Dependencies: 1989
-- Name: pole_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE pole_id_seq OWNED BY pole.id;


--
-- TOC entry 1992 (class 1259 OID 142994)
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
-- TOC entry 1991 (class 1259 OID 142992)
-- Dependencies: 1992 6
-- Name: pole_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE pole_suivi_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.pole_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 2966 (class 0 OID 0)
-- Dependencies: 1991
-- Name: pole_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE pole_suivi_id_seq OWNED BY pole_suivi.id;


--
-- TOC entry 1994 (class 1259 OID 143002)
-- Dependencies: 6
-- Name: prescription; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE prescription (
    id bigint NOT NULL,
    commentaire text,
    datedebuttraitement timestamp without time zone,
    dateprescription timestamp without time zone,
    dispense boolean,
    numprescription integer,
    id_inclusion bigint NOT NULL,
    id_investigateur bigint NOT NULL,
    id_sequence bigint,
    id_service bigint NOT NULL
);


ALTER TABLE public.prescription OWNER TO eclipse;

--
-- TOC entry 1993 (class 1259 OID 143000)
-- Dependencies: 6 1994
-- Name: prescription_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE prescription_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.prescription_id_seq OWNER TO eclipse;

--
-- TOC entry 2967 (class 0 OID 0)
-- Dependencies: 1993
-- Name: prescription_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE prescription_id_seq OWNED BY prescription.id;


--
-- TOC entry 1996 (class 1259 OID 143013)
-- Dependencies: 6
-- Name: prescription_type; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE prescription_type (
    id bigint NOT NULL,
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


ALTER TABLE public.prescription_type OWNER TO eclipse;

--
-- TOC entry 1995 (class 1259 OID 143011)
-- Dependencies: 1996 6
-- Name: prescription_type_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE prescription_type_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.prescription_type_id_seq OWNER TO eclipse;

--
-- TOC entry 2968 (class 0 OID 0)
-- Dependencies: 1995
-- Name: prescription_type_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE prescription_type_id_seq OWNED BY prescription_type.id;


--
-- TOC entry 1998 (class 1259 OID 143024)
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
-- TOC entry 1997 (class 1259 OID 143022)
-- Dependencies: 6 1998
-- Name: prevision_sigrec_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE prevision_sigrec_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.prevision_sigrec_id_seq OWNER TO eclipse;

--
-- TOC entry 2969 (class 0 OID 0)
-- Dependencies: 1997
-- Name: prevision_sigrec_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE prevision_sigrec_id_seq OWNED BY prevision_sigrec.id;


--
-- TOC entry 2000 (class 1259 OID 143032)
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
-- TOC entry 2002 (class 1259 OID 143043)
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
-- TOC entry 2001 (class 1259 OID 143041)
-- Dependencies: 6 2002
-- Name: produit_detail_logistique_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE produit_detail_logistique_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.produit_detail_logistique_id_seq OWNER TO eclipse;

--
-- TOC entry 2970 (class 0 OID 0)
-- Dependencies: 2001
-- Name: produit_detail_logistique_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE produit_detail_logistique_id_seq OWNED BY produit_detail_logistique.id;


--
-- TOC entry 2004 (class 1259 OID 143051)
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
-- TOC entry 2003 (class 1259 OID 143049)
-- Dependencies: 6 2004
-- Name: produit_detail_stockage_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE produit_detail_stockage_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.produit_detail_stockage_id_seq OWNER TO eclipse;

--
-- TOC entry 2971 (class 0 OID 0)
-- Dependencies: 2003
-- Name: produit_detail_stockage_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE produit_detail_stockage_id_seq OWNED BY produit_detail_stockage.id;


--
-- TOC entry 2006 (class 1259 OID 143062)
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
-- TOC entry 2005 (class 1259 OID 143060)
-- Dependencies: 6 2006
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
-- TOC entry 2972 (class 0 OID 0)
-- Dependencies: 2005
-- Name: produit_document_actes_pharma_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE produit_document_actes_pharma_id_seq OWNED BY produit_document_actes_pharma.id;


--
-- TOC entry 1999 (class 1259 OID 143030)
-- Dependencies: 2000 6
-- Name: produit_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE produit_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.produit_id_seq OWNER TO eclipse;

--
-- TOC entry 2973 (class 0 OID 0)
-- Dependencies: 1999
-- Name: produit_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE produit_id_seq OWNED BY produit.id;


--
-- TOC entry 2008 (class 1259 OID 143073)
-- Dependencies: 6
-- Name: produit_prescrit; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE produit_prescrit (
    id bigint NOT NULL,
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


ALTER TABLE public.produit_prescrit OWNER TO eclipse;

--
-- TOC entry 2007 (class 1259 OID 143071)
-- Dependencies: 6 2008
-- Name: produit_prescrit_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE produit_prescrit_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.produit_prescrit_id_seq OWNER TO eclipse;

--
-- TOC entry 2974 (class 0 OID 0)
-- Dependencies: 2007
-- Name: produit_prescrit_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE produit_prescrit_id_seq OWNED BY produit_prescrit.id;


--
-- TOC entry 2009 (class 1259 OID 143082)
-- Dependencies: 6
-- Name: produit_service; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE produit_service (
    id_produit bigint NOT NULL,
    id_service bigint NOT NULL
);


ALTER TABLE public.produit_service OWNER TO eclipse;

--
-- TOC entry 2011 (class 1259 OID 143089)
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
-- TOC entry 2010 (class 1259 OID 143087)
-- Dependencies: 6 2011
-- Name: produit_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE produit_suivi_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.produit_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 2975 (class 0 OID 0)
-- Dependencies: 2010
-- Name: produit_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE produit_suivi_id_seq OWNED BY produit_suivi.id;


--
-- TOC entry 2012 (class 1259 OID 143095)
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
-- TOC entry 2014 (class 1259 OID 143105)
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
-- TOC entry 2013 (class 1259 OID 143103)
-- Dependencies: 6 2014
-- Name: promoteur_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE promoteur_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.promoteur_id_seq OWNER TO eclipse;

--
-- TOC entry 2976 (class 0 OID 0)
-- Dependencies: 2013
-- Name: promoteur_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE promoteur_id_seq OWNED BY promoteur.id;


--
-- TOC entry 2016 (class 1259 OID 143116)
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
-- TOC entry 2015 (class 1259 OID 143114)
-- Dependencies: 6 2016
-- Name: promoteur_sigrec_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE promoteur_sigrec_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.promoteur_sigrec_id_seq OWNER TO eclipse;

--
-- TOC entry 2977 (class 0 OID 0)
-- Dependencies: 2015
-- Name: promoteur_sigrec_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE promoteur_sigrec_id_seq OWNED BY promoteur_sigrec.id;


--
-- TOC entry 2018 (class 1259 OID 143127)
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
-- TOC entry 2017 (class 1259 OID 143125)
-- Dependencies: 2018 6
-- Name: promoteur_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE promoteur_suivi_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.promoteur_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 2978 (class 0 OID 0)
-- Dependencies: 2017
-- Name: promoteur_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE promoteur_suivi_id_seq OWNED BY promoteur_suivi.id;


--
-- TOC entry 2020 (class 1259 OID 143135)
-- Dependencies: 6
-- Name: regle_surcout; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE regle_surcout (
    id bigint NOT NULL,
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


ALTER TABLE public.regle_surcout OWNER TO eclipse;

--
-- TOC entry 2019 (class 1259 OID 143133)
-- Dependencies: 6 2020
-- Name: regle_surcout_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE regle_surcout_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.regle_surcout_id_seq OWNER TO eclipse;

--
-- TOC entry 2979 (class 0 OID 0)
-- Dependencies: 2019
-- Name: regle_surcout_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE regle_surcout_id_seq OWNED BY regle_surcout.id;


--
-- TOC entry 2022 (class 1259 OID 143146)
-- Dependencies: 6
-- Name: retour_patient; Type: TABLE; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE TABLE retour_patient (
    id bigint NOT NULL,
    commentaire text,
    commentaireentame text,
    commentaireetat text,
    date timestamp without time zone,
    dateetat timestamp without time zone,
    etat character varying(255),
    numordonnancier integer,
    quantite integer,
    type character varying(255),
    id_conditionnement bigint NOT NULL,
    id_detailstockage bigint NOT NULL,
    id_essai bigint NOT NULL,
    id_patient bigint,
    id_personne bigint NOT NULL,
    id_produit bigint NOT NULL
);


ALTER TABLE public.retour_patient OWNER TO eclipse;

--
-- TOC entry 2021 (class 1259 OID 143144)
-- Dependencies: 6 2022
-- Name: retour_patient_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE retour_patient_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.retour_patient_id_seq OWNER TO eclipse;

--
-- TOC entry 2980 (class 0 OID 0)
-- Dependencies: 2021
-- Name: retour_patient_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE retour_patient_id_seq OWNED BY retour_patient.id;


--
-- TOC entry 2024 (class 1259 OID 143157)
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
    id_bras_sequence bigint
);


ALTER TABLE public.sequence OWNER TO eclipse;

--
-- TOC entry 2023 (class 1259 OID 143155)
-- Dependencies: 6 2024
-- Name: sequence_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE sequence_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.sequence_id_seq OWNER TO eclipse;

--
-- TOC entry 2981 (class 0 OID 0)
-- Dependencies: 2023
-- Name: sequence_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE sequence_id_seq OWNED BY sequence.id;


--
-- TOC entry 2026 (class 1259 OID 143168)
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
-- TOC entry 2025 (class 1259 OID 143166)
-- Dependencies: 2026 6
-- Name: service_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE service_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.service_id_seq OWNER TO eclipse;

--
-- TOC entry 2982 (class 0 OID 0)
-- Dependencies: 2025
-- Name: service_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE service_id_seq OWNED BY service.id;


--
-- TOC entry 2028 (class 1259 OID 143176)
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
-- TOC entry 2027 (class 1259 OID 143174)
-- Dependencies: 2028 6
-- Name: service_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE service_suivi_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.service_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 2983 (class 0 OID 0)
-- Dependencies: 2027
-- Name: service_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE service_suivi_id_seq OWNED BY service_suivi.id;


--
-- TOC entry 2030 (class 1259 OID 143184)
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
-- TOC entry 2029 (class 1259 OID 143182)
-- Dependencies: 2030 6
-- Name: site_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE site_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.site_id_seq OWNER TO eclipse;

--
-- TOC entry 2984 (class 0 OID 0)
-- Dependencies: 2029
-- Name: site_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE site_id_seq OWNED BY site.id;


--
-- TOC entry 2032 (class 1259 OID 143195)
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
-- TOC entry 2031 (class 1259 OID 143193)
-- Dependencies: 2032 6
-- Name: site_suivi_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE site_suivi_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.site_suivi_id_seq OWNER TO eclipse;

--
-- TOC entry 2985 (class 0 OID 0)
-- Dependencies: 2031
-- Name: site_suivi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE site_suivi_id_seq OWNED BY site_suivi.id;


--
-- TOC entry 2034 (class 1259 OID 143203)
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
-- TOC entry 2033 (class 1259 OID 143201)
-- Dependencies: 6 2034
-- Name: stockage_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE stockage_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.stockage_id_seq OWNER TO eclipse;

--
-- TOC entry 2986 (class 0 OID 0)
-- Dependencies: 2033
-- Name: stockage_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE stockage_id_seq OWNED BY stockage.id;


--
-- TOC entry 2036 (class 1259 OID 143214)
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
-- TOC entry 2035 (class 1259 OID 143212)
-- Dependencies: 6 2036
-- Name: theme_id_seq; Type: SEQUENCE; Schema: public; Owner: eclipse
--

CREATE SEQUENCE theme_id_seq
    START WITH 100000
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.theme_id_seq OWNER TO eclipse;

--
-- TOC entry 2987 (class 0 OID 0)
-- Dependencies: 2035
-- Name: theme_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eclipse
--

ALTER SEQUENCE theme_id_seq OWNED BY theme.id;


--
-- TOC entry 2303 (class 2604 OID 142217)
-- Dependencies: 1835 1834 1835
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE arc_investigateur_sigrec ALTER COLUMN id SET DEFAULT nextval('arc_investigateur_sigrec_id_seq'::regclass);


--
-- TOC entry 2304 (class 2604 OID 142228)
-- Dependencies: 1837 1836 1837
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE arc_promoteur_sigrec ALTER COLUMN id SET DEFAULT nextval('arc_promoteur_sigrec_id_seq'::regclass);


--
-- TOC entry 2305 (class 2604 OID 142244)
-- Dependencies: 1839 1840 1840
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE assurance_sigrec ALTER COLUMN id SET DEFAULT nextval('assurance_sigrec_id_seq'::regclass);


--
-- TOC entry 2306 (class 2604 OID 142252)
-- Dependencies: 1842 1841 1842
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE bras ALTER COLUMN id SET DEFAULT nextval('bras_id_seq'::regclass);


--
-- TOC entry 2307 (class 2604 OID 142263)
-- Dependencies: 1844 1843 1844
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE categorie ALTER COLUMN id SET DEFAULT nextval('categorie_id_seq'::regclass);


--
-- TOC entry 2308 (class 2604 OID 142274)
-- Dependencies: 1845 1846 1846
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE centre_sigrec ALTER COLUMN id SET DEFAULT nextval('centre_sigrec_id_seq'::regclass);


--
-- TOC entry 2309 (class 2604 OID 142285)
-- Dependencies: 1847 1848 1848
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE co_investigateur_sigrec ALTER COLUMN id SET DEFAULT nextval('co_investigateur_sigrec_id_seq'::regclass);


--
-- TOC entry 2310 (class 2604 OID 142296)
-- Dependencies: 1850 1849 1850
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE conditionnement ALTER COLUMN id SET DEFAULT nextval('conditionnement_id_seq'::regclass);


--
-- TOC entry 2311 (class 2604 OID 142307)
-- Dependencies: 1852 1851 1852
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE contact_sigrec ALTER COLUMN id SET DEFAULT nextval('contact_sigrec_id_seq'::regclass);


--
-- TOC entry 2312 (class 2604 OID 142318)
-- Dependencies: 1853 1854 1854
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE cro_sigrec ALTER COLUMN id SET DEFAULT nextval('cro_sigrec_id_seq'::regclass);


--
-- TOC entry 2313 (class 2604 OID 142326)
-- Dependencies: 1856 1855 1856
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE dispensation ALTER COLUMN id SET DEFAULT nextval('dispensation_id_seq'::regclass);


--
-- TOC entry 2314 (class 2604 OID 142345)
-- Dependencies: 1859 1858 1859
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE donnees_prevision ALTER COLUMN id SET DEFAULT nextval('donnees_prevision_id_seq'::regclass);


--
-- TOC entry 2315 (class 2604 OID 142353)
-- Dependencies: 1860 1861 1861
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE dotation ALTER COLUMN id SET DEFAULT nextval('dotation_id_seq'::regclass);


--
-- TOC entry 2316 (class 2604 OID 142364)
-- Dependencies: 1862 1863 1863
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE element_to_check ALTER COLUMN id SET DEFAULT nextval('element_to_check_id_seq'::regclass);


--
-- TOC entry 2317 (class 2604 OID 142375)
-- Dependencies: 1864 1865 1865
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai ALTER COLUMN id SET DEFAULT nextval('essai_id_seq'::regclass);


--
-- TOC entry 2318 (class 2604 OID 142386)
-- Dependencies: 1867 1866 1867
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_commentaire_detail_administratif_archi ALTER COLUMN id SET DEFAULT nextval('essai_commentaire_detail_administratif_archi_id_seq'::regclass);


--
-- TOC entry 2319 (class 2604 OID 142397)
-- Dependencies: 1869 1868 1869
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_commentaire_detail_faisabilite ALTER COLUMN id SET DEFAULT nextval('essai_commentaire_detail_faisabilite_id_seq'::regclass);


--
-- TOC entry 2320 (class 2604 OID 142408)
-- Dependencies: 1870 1871 1871
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_commentaire_detail_recherche ALTER COLUMN id SET DEFAULT nextval('essai_commentaire_detail_recherche_id_seq'::regclass);


--
-- TOC entry 2321 (class 2604 OID 142419)
-- Dependencies: 1872 1873 1873
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_administratif ALTER COLUMN id SET DEFAULT nextval('essai_detail_administratif_id_seq'::regclass);


--
-- TOC entry 2322 (class 2604 OID 142430)
-- Dependencies: 1875 1874 1875
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_administratif_suivi ALTER COLUMN id SET DEFAULT nextval('essai_detail_administratif_suivi_id_seq'::regclass);


--
-- TOC entry 2323 (class 2604 OID 142438)
-- Dependencies: 1876 1877 1877
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_autres_documents ALTER COLUMN id SET DEFAULT nextval('essai_detail_autres_documents_id_seq'::regclass);


--
-- TOC entry 2324 (class 2604 OID 142446)
-- Dependencies: 1879 1878 1879
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_autres_documents_suivi ALTER COLUMN id SET DEFAULT nextval('essai_detail_autres_documents_suivi_id_seq'::regclass);


--
-- TOC entry 2325 (class 2604 OID 142454)
-- Dependencies: 1881 1880 1881
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_contacts ALTER COLUMN id SET DEFAULT nextval('essai_detail_contacts_id_seq'::regclass);


--
-- TOC entry 2326 (class 2604 OID 142462)
-- Dependencies: 1882 1883 1883
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_contacts_suivi ALTER COLUMN id SET DEFAULT nextval('essai_detail_contacts_suivi_id_seq'::regclass);


--
-- TOC entry 2327 (class 2604 OID 142470)
-- Dependencies: 1884 1885 1885
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_dates ALTER COLUMN id SET DEFAULT nextval('essai_detail_dates_id_seq'::regclass);


--
-- TOC entry 2328 (class 2604 OID 142478)
-- Dependencies: 1887 1886 1887
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_dates_suivi ALTER COLUMN id SET DEFAULT nextval('essai_detail_dates_suivi_id_seq'::regclass);


--
-- TOC entry 2329 (class 2604 OID 142486)
-- Dependencies: 1888 1889 1889
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_design ALTER COLUMN id SET DEFAULT nextval('essai_detail_design_id_seq'::regclass);


--
-- TOC entry 2330 (class 2604 OID 142494)
-- Dependencies: 1890 1891 1891
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_design_suivi ALTER COLUMN id SET DEFAULT nextval('essai_detail_design_suivi_id_seq'::regclass);


--
-- TOC entry 2331 (class 2604 OID 142502)
-- Dependencies: 1892 1893 1893
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_etat ALTER COLUMN id SET DEFAULT nextval('essai_detail_etat_id_seq'::regclass);


--
-- TOC entry 2332 (class 2604 OID 142513)
-- Dependencies: 1895 1894 1895
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_faisabilite ALTER COLUMN id SET DEFAULT nextval('essai_detail_faisabilite_id_seq'::regclass);


--
-- TOC entry 2333 (class 2604 OID 142526)
-- Dependencies: 1897 1898 1898
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_faisabilite_suivi ALTER COLUMN id SET DEFAULT nextval('essai_detail_faisabilite_suivi_id_seq'::regclass);


--
-- TOC entry 2334 (class 2604 OID 142534)
-- Dependencies: 1900 1899 1900
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_pharma ALTER COLUMN id SET DEFAULT nextval('essai_detail_pharma_id_seq'::regclass);


--
-- TOC entry 2335 (class 2604 OID 142550)
-- Dependencies: 1902 1903 1903
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_pharma_suivi ALTER COLUMN id SET DEFAULT nextval('essai_detail_pharma_suivi_id_seq'::regclass);


--
-- TOC entry 2336 (class 2604 OID 142558)
-- Dependencies: 1904 1905 1905
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_produit ALTER COLUMN id SET DEFAULT nextval('essai_detail_produit_id_seq'::regclass);


--
-- TOC entry 2337 (class 2604 OID 142566)
-- Dependencies: 1907 1906 1907
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_produit_suivi ALTER COLUMN id SET DEFAULT nextval('essai_detail_produit_suivi_id_seq'::regclass);


--
-- TOC entry 2338 (class 2604 OID 142574)
-- Dependencies: 1908 1909 1909
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_recherche ALTER COLUMN id SET DEFAULT nextval('essai_detail_recherche_id_seq'::regclass);


--
-- TOC entry 2339 (class 2604 OID 142585)
-- Dependencies: 1910 1911 1911
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_recherche_sigrec ALTER COLUMN id SET DEFAULT nextval('essai_detail_recherche_sigrec_id_seq'::regclass);


--
-- TOC entry 2340 (class 2604 OID 142596)
-- Dependencies: 1913 1912 1913
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_recherche_suivi ALTER COLUMN id SET DEFAULT nextval('essai_detail_recherche_suivi_id_seq'::regclass);


--
-- TOC entry 2341 (class 2604 OID 142604)
-- Dependencies: 1915 1914 1915
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_surcout ALTER COLUMN id SET DEFAULT nextval('essai_detail_surcout_id_seq'::regclass);


--
-- TOC entry 2342 (class 2604 OID 142612)
-- Dependencies: 1916 1917 1917
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_detail_surcout_suivi ALTER COLUMN id SET DEFAULT nextval('essai_detail_surcout_suivi_id_seq'::regclass);


--
-- TOC entry 2343 (class 2604 OID 142620)
-- Dependencies: 1919 1918 1919
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_document_detail_administratif ALTER COLUMN id SET DEFAULT nextval('essai_document_detail_administratif_id_seq'::regclass);


--
-- TOC entry 2344 (class 2604 OID 142631)
-- Dependencies: 1920 1921 1921
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_document_detail_autres_documents ALTER COLUMN id SET DEFAULT nextval('essai_document_detail_autres_documents_id_seq'::regclass);


--
-- TOC entry 2345 (class 2604 OID 142642)
-- Dependencies: 1922 1923 1923
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_document_detail_pharma ALTER COLUMN id SET DEFAULT nextval('essai_document_detail_pharma_id_seq'::regclass);


--
-- TOC entry 2346 (class 2604 OID 142653)
-- Dependencies: 1925 1924 1925
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_document_detail_surcout ALTER COLUMN id SET DEFAULT nextval('essai_document_detail_surcout_id_seq'::regclass);


--
-- TOC entry 2347 (class 2604 OID 142669)
-- Dependencies: 1928 1927 1928
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_sigrec ALTER COLUMN id SET DEFAULT nextval('essai_sigrec_id_seq'::regclass);


--
-- TOC entry 2348 (class 2604 OID 142680)
-- Dependencies: 1930 1929 1930
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE essai_suivi ALTER COLUMN id SET DEFAULT nextval('essai_suivi_id_seq'::regclass);


--
-- TOC entry 2349 (class 2604 OID 142688)
-- Dependencies: 1931 1932 1932
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE etablissement ALTER COLUMN id SET DEFAULT nextval('etablissement_id_seq'::regclass);


--
-- TOC entry 2350 (class 2604 OID 142699)
-- Dependencies: 1934 1933 1934
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE etablissement_suivi ALTER COLUMN id SET DEFAULT nextval('etablissement_suivi_id_seq'::regclass);


--
-- TOC entry 2351 (class 2604 OID 142707)
-- Dependencies: 1936 1935 1936
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE evenement ALTER COLUMN id SET DEFAULT nextval('evenement_id_seq'::regclass);


--
-- TOC entry 2352 (class 2604 OID 142718)
-- Dependencies: 1937 1938 1938
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE evenement_suivi ALTER COLUMN id SET DEFAULT nextval('evenement_suivi_id_seq'::regclass);


--
-- TOC entry 2353 (class 2604 OID 142726)
-- Dependencies: 1939 1940 1940
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE grille ALTER COLUMN id SET DEFAULT nextval('grille_id_seq'::regclass);


--
-- TOC entry 2354 (class 2604 OID 142734)
-- Dependencies: 1942 1941 1942
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE grille_modele ALTER COLUMN id SET DEFAULT nextval('grille_modele_id_seq'::regclass);


--
-- TOC entry 2355 (class 2604 OID 142742)
-- Dependencies: 1943 1944 1944
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE habilitation ALTER COLUMN id SET DEFAULT nextval('habilitation_id_seq'::regclass);


--
-- TOC entry 2356 (class 2604 OID 142753)
-- Dependencies: 1945 1946 1946
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE historique_patient ALTER COLUMN id SET DEFAULT nextval('historique_patient_id_seq'::regclass);


--
-- TOC entry 2357 (class 2604 OID 142764)
-- Dependencies: 1947 1948 1948
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE incident ALTER COLUMN id SET DEFAULT nextval('incident_id_seq'::regclass);


--
-- TOC entry 2358 (class 2604 OID 142775)
-- Dependencies: 1950 1949 1950
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE incident_suivi ALTER COLUMN id SET DEFAULT nextval('incident_suivi_id_seq'::regclass);


--
-- TOC entry 2359 (class 2604 OID 142783)
-- Dependencies: 1951 1952 1952
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE inclusion ALTER COLUMN id SET DEFAULT nextval('inclusion_id_seq'::regclass);


--
-- TOC entry 2360 (class 2604 OID 142799)
-- Dependencies: 1954 1955 1955
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE investigateur_sigrec ALTER COLUMN id SET DEFAULT nextval('investigateur_sigrec_id_seq'::regclass);


--
-- TOC entry 2361 (class 2604 OID 142810)
-- Dependencies: 1957 1956 1957
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE item ALTER COLUMN id SET DEFAULT nextval('item_id_seq'::regclass);


--
-- TOC entry 2362 (class 2604 OID 142884)
-- Dependencies: 1968 1967 1968
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE mvtstock ALTER COLUMN id SET DEFAULT nextval('mvtstock_id_seq'::regclass);


--
-- TOC entry 2363 (class 2604 OID 142895)
-- Dependencies: 1970 1969 1970
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE mvtstock_document ALTER COLUMN id SET DEFAULT nextval('mvtstock_document_id_seq'::regclass);


--
-- TOC entry 2364 (class 2604 OID 142906)
-- Dependencies: 1971 1972 1972
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE ordonnancier_dispensation ALTER COLUMN id SET DEFAULT nextval('ordonnancier_dispensation_id_seq'::regclass);


--
-- TOC entry 2365 (class 2604 OID 142914)
-- Dependencies: 1973 1974 1974
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE ordonnancier_fab_reconst ALTER COLUMN id SET DEFAULT nextval('ordonnancier_fab_reconst_id_seq'::regclass);


--
-- TOC entry 2366 (class 2604 OID 142922)
-- Dependencies: 1975 1976 1976
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE patient ALTER COLUMN id SET DEFAULT nextval('patient_id_seq'::regclass);


--
-- TOC entry 2367 (class 2604 OID 142933)
-- Dependencies: 1978 1977 1978
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE patient_suivi ALTER COLUMN id SET DEFAULT nextval('patient_suivi_id_seq'::regclass);


--
-- TOC entry 2368 (class 2604 OID 142941)
-- Dependencies: 1979 1980 1980
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE personne ALTER COLUMN id SET DEFAULT nextval('personne_id_seq'::regclass);


--
-- TOC entry 2369 (class 2604 OID 142952)
-- Dependencies: 1981 1982 1982
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE personne_suivi ALTER COLUMN id SET DEFAULT nextval('personne_suivi_id_seq'::regclass);


--
-- TOC entry 2370 (class 2604 OID 142960)
-- Dependencies: 1984 1983 1984
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE pharmacie ALTER COLUMN id SET DEFAULT nextval('pharmacie_id_seq'::regclass);


--
-- TOC entry 2371 (class 2604 OID 142976)
-- Dependencies: 1986 1987 1987
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE pharmacie_suivi ALTER COLUMN id SET DEFAULT nextval('pharmacie_suivi_id_seq'::regclass);


--
-- TOC entry 2372 (class 2604 OID 142989)
-- Dependencies: 1989 1990 1990
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE pole ALTER COLUMN id SET DEFAULT nextval('pole_id_seq'::regclass);


--
-- TOC entry 2373 (class 2604 OID 142997)
-- Dependencies: 1991 1992 1992
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE pole_suivi ALTER COLUMN id SET DEFAULT nextval('pole_suivi_id_seq'::regclass);


--
-- TOC entry 2374 (class 2604 OID 143005)
-- Dependencies: 1994 1993 1994
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE prescription ALTER COLUMN id SET DEFAULT nextval('prescription_id_seq'::regclass);


--
-- TOC entry 2375 (class 2604 OID 143016)
-- Dependencies: 1995 1996 1996
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE prescription_type ALTER COLUMN id SET DEFAULT nextval('prescription_type_id_seq'::regclass);


--
-- TOC entry 2376 (class 2604 OID 143027)
-- Dependencies: 1997 1998 1998
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE prevision_sigrec ALTER COLUMN id SET DEFAULT nextval('prevision_sigrec_id_seq'::regclass);


--
-- TOC entry 2377 (class 2604 OID 143035)
-- Dependencies: 1999 2000 2000
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE produit ALTER COLUMN id SET DEFAULT nextval('produit_id_seq'::regclass);


--
-- TOC entry 2378 (class 2604 OID 143046)
-- Dependencies: 2001 2002 2002
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE produit_detail_logistique ALTER COLUMN id SET DEFAULT nextval('produit_detail_logistique_id_seq'::regclass);


--
-- TOC entry 2379 (class 2604 OID 143054)
-- Dependencies: 2004 2003 2004
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE produit_detail_stockage ALTER COLUMN id SET DEFAULT nextval('produit_detail_stockage_id_seq'::regclass);


--
-- TOC entry 2380 (class 2604 OID 143065)
-- Dependencies: 2006 2005 2006
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE produit_document_actes_pharma ALTER COLUMN id SET DEFAULT nextval('produit_document_actes_pharma_id_seq'::regclass);


--
-- TOC entry 2381 (class 2604 OID 143076)
-- Dependencies: 2008 2007 2008
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE produit_prescrit ALTER COLUMN id SET DEFAULT nextval('produit_prescrit_id_seq'::regclass);


--
-- TOC entry 2382 (class 2604 OID 143092)
-- Dependencies: 2011 2010 2011
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE produit_suivi ALTER COLUMN id SET DEFAULT nextval('produit_suivi_id_seq'::regclass);


--
-- TOC entry 2383 (class 2604 OID 143108)
-- Dependencies: 2014 2013 2014
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE promoteur ALTER COLUMN id SET DEFAULT nextval('promoteur_id_seq'::regclass);


--
-- TOC entry 2384 (class 2604 OID 143119)
-- Dependencies: 2015 2016 2016
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE promoteur_sigrec ALTER COLUMN id SET DEFAULT nextval('promoteur_sigrec_id_seq'::regclass);


--
-- TOC entry 2385 (class 2604 OID 143130)
-- Dependencies: 2017 2018 2018
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE promoteur_suivi ALTER COLUMN id SET DEFAULT nextval('promoteur_suivi_id_seq'::regclass);


--
-- TOC entry 2386 (class 2604 OID 143138)
-- Dependencies: 2020 2019 2020
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE regle_surcout ALTER COLUMN id SET DEFAULT nextval('regle_surcout_id_seq'::regclass);


--
-- TOC entry 2387 (class 2604 OID 143149)
-- Dependencies: 2022 2021 2022
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE retour_patient ALTER COLUMN id SET DEFAULT nextval('retour_patient_id_seq'::regclass);


--
-- TOC entry 2388 (class 2604 OID 143160)
-- Dependencies: 2024 2023 2024
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE sequence ALTER COLUMN id SET DEFAULT nextval('sequence_id_seq'::regclass);


--
-- TOC entry 2389 (class 2604 OID 143171)
-- Dependencies: 2026 2025 2026
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE service ALTER COLUMN id SET DEFAULT nextval('service_id_seq'::regclass);


--
-- TOC entry 2390 (class 2604 OID 143179)
-- Dependencies: 2027 2028 2028
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE service_suivi ALTER COLUMN id SET DEFAULT nextval('service_suivi_id_seq'::regclass);


--
-- TOC entry 2391 (class 2604 OID 143187)
-- Dependencies: 2029 2030 2030
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE site ALTER COLUMN id SET DEFAULT nextval('site_id_seq'::regclass);


--
-- TOC entry 2392 (class 2604 OID 143198)
-- Dependencies: 2032 2031 2032
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE site_suivi ALTER COLUMN id SET DEFAULT nextval('site_suivi_id_seq'::regclass);


--
-- TOC entry 2393 (class 2604 OID 143206)
-- Dependencies: 2034 2033 2034
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE stockage ALTER COLUMN id SET DEFAULT nextval('stockage_id_seq'::regclass);


--
-- TOC entry 2394 (class 2604 OID 143217)
-- Dependencies: 2036 2035 2036
-- Name: id; Type: DEFAULT; Schema: public; Owner: eclipse
--

ALTER TABLE theme ALTER COLUMN id SET DEFAULT nextval('theme_id_seq'::regclass);


--
-- TOC entry 2396 (class 2606 OID 142222)
-- Dependencies: 1835 1835
-- Name: arc_investigateur_sigrec_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY arc_investigateur_sigrec
    ADD CONSTRAINT arc_investigateur_sigrec_pkey PRIMARY KEY (id);


--
-- TOC entry 2399 (class 2606 OID 142233)
-- Dependencies: 1837 1837
-- Name: arc_promoteur_sigrec_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY arc_promoteur_sigrec
    ADD CONSTRAINT arc_promoteur_sigrec_pkey PRIMARY KEY (id);


--
-- TOC entry 2402 (class 2606 OID 142238)
-- Dependencies: 1838 1838 1838
-- Name: arcinvestigateur_service_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY arcinvestigateur_service
    ADD CONSTRAINT arcinvestigateur_service_pkey PRIMARY KEY (id_arcinvestigateur, id_service);


--
-- TOC entry 2404 (class 2606 OID 142246)
-- Dependencies: 1840 1840
-- Name: assurance_sigrec_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY assurance_sigrec
    ADD CONSTRAINT assurance_sigrec_pkey PRIMARY KEY (id);


--
-- TOC entry 2407 (class 2606 OID 142257)
-- Dependencies: 1842 1842
-- Name: bras_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY bras
    ADD CONSTRAINT bras_pkey PRIMARY KEY (id);


--
-- TOC entry 2411 (class 2606 OID 142268)
-- Dependencies: 1844 1844
-- Name: categorie_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY categorie
    ADD CONSTRAINT categorie_pkey PRIMARY KEY (id);


--
-- TOC entry 2414 (class 2606 OID 142279)
-- Dependencies: 1846 1846
-- Name: centre_sigrec_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY centre_sigrec
    ADD CONSTRAINT centre_sigrec_pkey PRIMARY KEY (id);


--
-- TOC entry 2417 (class 2606 OID 142290)
-- Dependencies: 1848 1848
-- Name: co_investigateur_sigrec_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY co_investigateur_sigrec
    ADD CONSTRAINT co_investigateur_sigrec_pkey PRIMARY KEY (id);


--
-- TOC entry 2420 (class 2606 OID 142301)
-- Dependencies: 1850 1850
-- Name: conditionnement_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY conditionnement
    ADD CONSTRAINT conditionnement_pkey PRIMARY KEY (id);


--
-- TOC entry 2423 (class 2606 OID 142312)
-- Dependencies: 1852 1852
-- Name: contact_sigrec_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY contact_sigrec
    ADD CONSTRAINT contact_sigrec_pkey PRIMARY KEY (id);


--
-- TOC entry 2425 (class 2606 OID 142320)
-- Dependencies: 1854 1854
-- Name: cro_sigrec_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY cro_sigrec
    ADD CONSTRAINT cro_sigrec_pkey PRIMARY KEY (id);


--
-- TOC entry 2429 (class 2606 OID 142331)
-- Dependencies: 1856 1856
-- Name: dispensation_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY dispensation
    ADD CONSTRAINT dispensation_pkey PRIMARY KEY (id);


--
-- TOC entry 2434 (class 2606 OID 142339)
-- Dependencies: 1857 1857
-- Name: dispositif_medical_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY dispositif_medical
    ADD CONSTRAINT dispositif_medical_pkey PRIMARY KEY (id);


--
-- TOC entry 2436 (class 2606 OID 142347)
-- Dependencies: 1859 1859
-- Name: donnees_prevision_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY donnees_prevision
    ADD CONSTRAINT donnees_prevision_pkey PRIMARY KEY (id);


--
-- TOC entry 2438 (class 2606 OID 142358)
-- Dependencies: 1861 1861
-- Name: dotation_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY dotation
    ADD CONSTRAINT dotation_pkey PRIMARY KEY (id);


--
-- TOC entry 2446 (class 2606 OID 142369)
-- Dependencies: 1863 1863
-- Name: element_to_check_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY element_to_check
    ADD CONSTRAINT element_to_check_pkey PRIMARY KEY (id);


--
-- TOC entry 2456 (class 2606 OID 142391)
-- Dependencies: 1867 1867
-- Name: essai_commentaire_detail_administratif_archi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_commentaire_detail_administratif_archi
    ADD CONSTRAINT essai_commentaire_detail_administratif_archi_pkey PRIMARY KEY (id);


--
-- TOC entry 2459 (class 2606 OID 142402)
-- Dependencies: 1869 1869
-- Name: essai_commentaire_detail_faisabilite_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_commentaire_detail_faisabilite
    ADD CONSTRAINT essai_commentaire_detail_faisabilite_pkey PRIMARY KEY (id);


--
-- TOC entry 2462 (class 2606 OID 142413)
-- Dependencies: 1871 1871
-- Name: essai_commentaire_detail_recherche_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_commentaire_detail_recherche
    ADD CONSTRAINT essai_commentaire_detail_recherche_pkey PRIMARY KEY (id);


--
-- TOC entry 2465 (class 2606 OID 142424)
-- Dependencies: 1873 1873
-- Name: essai_detail_administratif_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_administratif
    ADD CONSTRAINT essai_detail_administratif_pkey PRIMARY KEY (id);


--
-- TOC entry 2467 (class 2606 OID 142432)
-- Dependencies: 1875 1875
-- Name: essai_detail_administratif_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_administratif_suivi
    ADD CONSTRAINT essai_detail_administratif_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2470 (class 2606 OID 142440)
-- Dependencies: 1877 1877
-- Name: essai_detail_autres_documents_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_autres_documents
    ADD CONSTRAINT essai_detail_autres_documents_pkey PRIMARY KEY (id);


--
-- TOC entry 2472 (class 2606 OID 142448)
-- Dependencies: 1879 1879
-- Name: essai_detail_autres_documents_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_autres_documents_suivi
    ADD CONSTRAINT essai_detail_autres_documents_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2475 (class 2606 OID 142456)
-- Dependencies: 1881 1881
-- Name: essai_detail_contacts_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_contacts
    ADD CONSTRAINT essai_detail_contacts_pkey PRIMARY KEY (id);


--
-- TOC entry 2477 (class 2606 OID 142464)
-- Dependencies: 1883 1883
-- Name: essai_detail_contacts_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_contacts_suivi
    ADD CONSTRAINT essai_detail_contacts_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2480 (class 2606 OID 142472)
-- Dependencies: 1885 1885
-- Name: essai_detail_dates_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_dates
    ADD CONSTRAINT essai_detail_dates_pkey PRIMARY KEY (id);


--
-- TOC entry 2482 (class 2606 OID 142480)
-- Dependencies: 1887 1887
-- Name: essai_detail_dates_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_dates_suivi
    ADD CONSTRAINT essai_detail_dates_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2485 (class 2606 OID 142488)
-- Dependencies: 1889 1889
-- Name: essai_detail_design_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_design
    ADD CONSTRAINT essai_detail_design_pkey PRIMARY KEY (id);


--
-- TOC entry 2487 (class 2606 OID 142496)
-- Dependencies: 1891 1891
-- Name: essai_detail_design_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_design_suivi
    ADD CONSTRAINT essai_detail_design_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2490 (class 2606 OID 142507)
-- Dependencies: 1893 1893
-- Name: essai_detail_etat_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_etat
    ADD CONSTRAINT essai_detail_etat_pkey PRIMARY KEY (id);


--
-- TOC entry 2493 (class 2606 OID 142515)
-- Dependencies: 1895 1895
-- Name: essai_detail_faisabilite_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_faisabilite
    ADD CONSTRAINT essai_detail_faisabilite_pkey PRIMARY KEY (id);


--
-- TOC entry 2495 (class 2606 OID 142520)
-- Dependencies: 1896 1896 1896
-- Name: essai_detail_faisabilite_service_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_faisabilite_service
    ADD CONSTRAINT essai_detail_faisabilite_service_pkey PRIMARY KEY (id_essai, id_service);


--
-- TOC entry 2497 (class 2606 OID 142528)
-- Dependencies: 1898 1898
-- Name: essai_detail_faisabilite_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_faisabilite_suivi
    ADD CONSTRAINT essai_detail_faisabilite_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2502 (class 2606 OID 142544)
-- Dependencies: 1901 1901 1901
-- Name: essai_detail_pharma_pharmacie_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_pharma_pharmacie
    ADD CONSTRAINT essai_detail_pharma_pharmacie_pkey PRIMARY KEY (id_detail_pharma, id_pharmacie);


--
-- TOC entry 2500 (class 2606 OID 142539)
-- Dependencies: 1900 1900
-- Name: essai_detail_pharma_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_pharma
    ADD CONSTRAINT essai_detail_pharma_pkey PRIMARY KEY (id);


--
-- TOC entry 2504 (class 2606 OID 142552)
-- Dependencies: 1903 1903
-- Name: essai_detail_pharma_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_pharma_suivi
    ADD CONSTRAINT essai_detail_pharma_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2507 (class 2606 OID 142560)
-- Dependencies: 1905 1905
-- Name: essai_detail_produit_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_produit
    ADD CONSTRAINT essai_detail_produit_pkey PRIMARY KEY (id);


--
-- TOC entry 2509 (class 2606 OID 142568)
-- Dependencies: 1907 1907
-- Name: essai_detail_produit_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_produit_suivi
    ADD CONSTRAINT essai_detail_produit_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2512 (class 2606 OID 142579)
-- Dependencies: 1909 1909
-- Name: essai_detail_recherche_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_recherche
    ADD CONSTRAINT essai_detail_recherche_pkey PRIMARY KEY (id);


--
-- TOC entry 2514 (class 2606 OID 142590)
-- Dependencies: 1911 1911
-- Name: essai_detail_recherche_sigrec_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_recherche_sigrec
    ADD CONSTRAINT essai_detail_recherche_sigrec_pkey PRIMARY KEY (id);


--
-- TOC entry 2516 (class 2606 OID 142598)
-- Dependencies: 1913 1913
-- Name: essai_detail_recherche_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_recherche_suivi
    ADD CONSTRAINT essai_detail_recherche_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2519 (class 2606 OID 142606)
-- Dependencies: 1915 1915
-- Name: essai_detail_surcout_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_surcout
    ADD CONSTRAINT essai_detail_surcout_pkey PRIMARY KEY (id);


--
-- TOC entry 2521 (class 2606 OID 142614)
-- Dependencies: 1917 1917
-- Name: essai_detail_surcout_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_detail_surcout_suivi
    ADD CONSTRAINT essai_detail_surcout_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2524 (class 2606 OID 142625)
-- Dependencies: 1919 1919
-- Name: essai_document_detail_administratif_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_document_detail_administratif
    ADD CONSTRAINT essai_document_detail_administratif_pkey PRIMARY KEY (id);


--
-- TOC entry 2527 (class 2606 OID 142636)
-- Dependencies: 1921 1921
-- Name: essai_document_detail_autres_documents_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_document_detail_autres_documents
    ADD CONSTRAINT essai_document_detail_autres_documents_pkey PRIMARY KEY (id);


--
-- TOC entry 2529 (class 2606 OID 142647)
-- Dependencies: 1923 1923
-- Name: essai_document_detail_pharma_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_document_detail_pharma
    ADD CONSTRAINT essai_document_detail_pharma_pkey PRIMARY KEY (id);


--
-- TOC entry 2531 (class 2606 OID 142658)
-- Dependencies: 1925 1925
-- Name: essai_document_detail_surcout_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_document_detail_surcout
    ADD CONSTRAINT essai_document_detail_surcout_pkey PRIMARY KEY (id);


--
-- TOC entry 2452 (class 2606 OID 142380)
-- Dependencies: 1865 1865
-- Name: essai_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai
    ADD CONSTRAINT essai_pkey PRIMARY KEY (id);


--
-- TOC entry 2534 (class 2606 OID 142663)
-- Dependencies: 1926 1926 1926
-- Name: essai_service_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_service
    ADD CONSTRAINT essai_service_pkey PRIMARY KEY (id_essai, id_service);


--
-- TOC entry 2536 (class 2606 OID 142674)
-- Dependencies: 1928 1928
-- Name: essai_sigrec_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_sigrec
    ADD CONSTRAINT essai_sigrec_pkey PRIMARY KEY (id);


--
-- TOC entry 2540 (class 2606 OID 142682)
-- Dependencies: 1930 1930
-- Name: essai_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY essai_suivi
    ADD CONSTRAINT essai_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2543 (class 2606 OID 142693)
-- Dependencies: 1932 1932
-- Name: etablissement_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY etablissement
    ADD CONSTRAINT etablissement_pkey PRIMARY KEY (id);


--
-- TOC entry 2545 (class 2606 OID 142701)
-- Dependencies: 1934 1934
-- Name: etablissement_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY etablissement_suivi
    ADD CONSTRAINT etablissement_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2548 (class 2606 OID 142712)
-- Dependencies: 1936 1936
-- Name: evenement_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY evenement
    ADD CONSTRAINT evenement_pkey PRIMARY KEY (id);


--
-- TOC entry 2551 (class 2606 OID 142720)
-- Dependencies: 1938 1938
-- Name: evenement_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY evenement_suivi
    ADD CONSTRAINT evenement_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2557 (class 2606 OID 142736)
-- Dependencies: 1942 1942
-- Name: grille_modele_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY grille_modele
    ADD CONSTRAINT grille_modele_pkey PRIMARY KEY (id);


--
-- TOC entry 2554 (class 2606 OID 142728)
-- Dependencies: 1940 1940
-- Name: grille_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY grille
    ADD CONSTRAINT grille_pkey PRIMARY KEY (id);


--
-- TOC entry 2559 (class 2606 OID 142747)
-- Dependencies: 1944 1944
-- Name: habilitation_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY habilitation
    ADD CONSTRAINT habilitation_pkey PRIMARY KEY (id);


--
-- TOC entry 2563 (class 2606 OID 142758)
-- Dependencies: 1946 1946
-- Name: historique_patient_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY historique_patient
    ADD CONSTRAINT historique_patient_pkey PRIMARY KEY (id);


--
-- TOC entry 2567 (class 2606 OID 142769)
-- Dependencies: 1948 1948
-- Name: incident_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY incident
    ADD CONSTRAINT incident_pkey PRIMARY KEY (id);


--
-- TOC entry 2570 (class 2606 OID 142777)
-- Dependencies: 1950 1950
-- Name: incident_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY incident_suivi
    ADD CONSTRAINT incident_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2574 (class 2606 OID 142788)
-- Dependencies: 1952 1952
-- Name: inclusion_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY inclusion
    ADD CONSTRAINT inclusion_pkey PRIMARY KEY (id);


--
-- TOC entry 2576 (class 2606 OID 142793)
-- Dependencies: 1953 1953 1953
-- Name: investigateur_service_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY investigateur_service
    ADD CONSTRAINT investigateur_service_pkey PRIMARY KEY (id_investigateur, id_service);


--
-- TOC entry 2578 (class 2606 OID 142804)
-- Dependencies: 1955 1955
-- Name: investigateur_sigrec_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY investigateur_sigrec
    ADD CONSTRAINT investigateur_sigrec_pkey PRIMARY KEY (id);


--
-- TOC entry 2581 (class 2606 OID 142815)
-- Dependencies: 1957 1957
-- Name: item_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY item
    ADD CONSTRAINT item_pkey PRIMARY KEY (id);


--
-- TOC entry 2583 (class 2606 OID 142820)
-- Dependencies: 1958 1958 1958
-- Name: item_regle_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY item_regle
    ADD CONSTRAINT item_regle_pkey PRIMARY KEY (id_regle, id_item);


--
-- TOC entry 2585 (class 2606 OID 142828)
-- Dependencies: 1959 1959
-- Name: medicament_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY medicament
    ADD CONSTRAINT medicament_pkey PRIMARY KEY (id);


--
-- TOC entry 2587 (class 2606 OID 142836)
-- Dependencies: 1960 1960
-- Name: mvt_approvisionnement_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY mvt_approvisionnement
    ADD CONSTRAINT mvt_approvisionnement_pkey PRIMARY KEY (id);


--
-- TOC entry 2589 (class 2606 OID 142844)
-- Dependencies: 1961 1961
-- Name: mvt_autre_sortie_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY mvt_autre_sortie
    ADD CONSTRAINT mvt_autre_sortie_pkey PRIMARY KEY (id);


--
-- TOC entry 2592 (class 2606 OID 142852)
-- Dependencies: 1962 1962
-- Name: mvt_cession_pui_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY mvt_cession_pui
    ADD CONSTRAINT mvt_cession_pui_pkey PRIMARY KEY (id);


--
-- TOC entry 2594 (class 2606 OID 142860)
-- Dependencies: 1963 1963
-- Name: mvt_destruction_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY mvt_destruction
    ADD CONSTRAINT mvt_destruction_pkey PRIMARY KEY (id);


--
-- TOC entry 2601 (class 2606 OID 142870)
-- Dependencies: 1965 1965
-- Name: mvt_dispensation_globale_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY mvt_dispensation_globale
    ADD CONSTRAINT mvt_dispensation_globale_pkey PRIMARY KEY (id);


--
-- TOC entry 2598 (class 2606 OID 142865)
-- Dependencies: 1964 1964
-- Name: mvt_dispensation_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY mvt_dispensation
    ADD CONSTRAINT mvt_dispensation_pkey PRIMARY KEY (id);


--
-- TOC entry 2603 (class 2606 OID 142878)
-- Dependencies: 1966 1966
-- Name: mvt_retour_promoteur_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY mvt_retour_promoteur
    ADD CONSTRAINT mvt_retour_promoteur_pkey PRIMARY KEY (id);


--
-- TOC entry 2612 (class 2606 OID 142900)
-- Dependencies: 1970 1970
-- Name: mvtstock_document_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY mvtstock_document
    ADD CONSTRAINT mvtstock_document_pkey PRIMARY KEY (id);


--
-- TOC entry 2610 (class 2606 OID 142889)
-- Dependencies: 1968 1968
-- Name: mvtstock_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY mvtstock
    ADD CONSTRAINT mvtstock_pkey PRIMARY KEY (id);


--
-- TOC entry 2615 (class 2606 OID 142908)
-- Dependencies: 1972 1972
-- Name: ordonnancier_dispensation_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY ordonnancier_dispensation
    ADD CONSTRAINT ordonnancier_dispensation_pkey PRIMARY KEY (id);


--
-- TOC entry 2618 (class 2606 OID 142916)
-- Dependencies: 1974 1974
-- Name: ordonnancier_fab_reconst_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY ordonnancier_fab_reconst
    ADD CONSTRAINT ordonnancier_fab_reconst_pkey PRIMARY KEY (id);


--
-- TOC entry 2620 (class 2606 OID 142927)
-- Dependencies: 1976 1976
-- Name: patient_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY patient
    ADD CONSTRAINT patient_pkey PRIMARY KEY (id);


--
-- TOC entry 2623 (class 2606 OID 142935)
-- Dependencies: 1978 1978
-- Name: patient_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY patient_suivi
    ADD CONSTRAINT patient_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2627 (class 2606 OID 142946)
-- Dependencies: 1980 1980
-- Name: personne_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY personne
    ADD CONSTRAINT personne_pkey PRIMARY KEY (id);


--
-- TOC entry 2630 (class 2606 OID 142954)
-- Dependencies: 1982 1982
-- Name: personne_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY personne_suivi
    ADD CONSTRAINT personne_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2633 (class 2606 OID 142965)
-- Dependencies: 1984 1984
-- Name: pharmacie_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY pharmacie
    ADD CONSTRAINT pharmacie_pkey PRIMARY KEY (id);


--
-- TOC entry 2635 (class 2606 OID 142970)
-- Dependencies: 1985 1985 1985
-- Name: pharmacie_site_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY pharmacie_site
    ADD CONSTRAINT pharmacie_site_pkey PRIMARY KEY (id_pharmacie, id_site);


--
-- TOC entry 2638 (class 2606 OID 142978)
-- Dependencies: 1987 1987
-- Name: pharmacie_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY pharmacie_suivi
    ADD CONSTRAINT pharmacie_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2640 (class 2606 OID 142983)
-- Dependencies: 1988 1988 1988
-- Name: pharmacien_pharmacie_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY pharmacien_pharmacie
    ADD CONSTRAINT pharmacien_pharmacie_pkey PRIMARY KEY (id_pharmacien, id_pharmacie);


--
-- TOC entry 2643 (class 2606 OID 142991)
-- Dependencies: 1990 1990
-- Name: pole_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY pole
    ADD CONSTRAINT pole_pkey PRIMARY KEY (id);


--
-- TOC entry 2646 (class 2606 OID 142999)
-- Dependencies: 1992 1992
-- Name: pole_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY pole_suivi
    ADD CONSTRAINT pole_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2652 (class 2606 OID 143010)
-- Dependencies: 1994 1994
-- Name: prescription_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY prescription
    ADD CONSTRAINT prescription_pkey PRIMARY KEY (id);


--
-- TOC entry 2657 (class 2606 OID 143021)
-- Dependencies: 1996 1996
-- Name: prescription_type_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY prescription_type
    ADD CONSTRAINT prescription_type_pkey PRIMARY KEY (id);


--
-- TOC entry 2659 (class 2606 OID 143029)
-- Dependencies: 1998 1998
-- Name: prevision_sigrec_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY prevision_sigrec
    ADD CONSTRAINT prevision_sigrec_pkey PRIMARY KEY (id);


--
-- TOC entry 2664 (class 2606 OID 143048)
-- Dependencies: 2002 2002
-- Name: produit_detail_logistique_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY produit_detail_logistique
    ADD CONSTRAINT produit_detail_logistique_pkey PRIMARY KEY (id);


--
-- TOC entry 2669 (class 2606 OID 143059)
-- Dependencies: 2004 2004
-- Name: produit_detail_stockage_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY produit_detail_stockage
    ADD CONSTRAINT produit_detail_stockage_pkey PRIMARY KEY (id);


--
-- TOC entry 2671 (class 2606 OID 143070)
-- Dependencies: 2006 2006
-- Name: produit_document_actes_pharma_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY produit_document_actes_pharma
    ADD CONSTRAINT produit_document_actes_pharma_pkey PRIMARY KEY (id);


--
-- TOC entry 2662 (class 2606 OID 143040)
-- Dependencies: 2000 2000
-- Name: produit_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY produit
    ADD CONSTRAINT produit_pkey PRIMARY KEY (id);


--
-- TOC entry 2676 (class 2606 OID 143081)
-- Dependencies: 2008 2008
-- Name: produit_prescrit_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY produit_prescrit
    ADD CONSTRAINT produit_prescrit_pkey PRIMARY KEY (id);


--
-- TOC entry 2678 (class 2606 OID 143086)
-- Dependencies: 2009 2009 2009
-- Name: produit_service_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY produit_service
    ADD CONSTRAINT produit_service_pkey PRIMARY KEY (id_produit, id_service);


--
-- TOC entry 2681 (class 2606 OID 143094)
-- Dependencies: 2011 2011
-- Name: produit_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY produit_suivi
    ADD CONSTRAINT produit_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2683 (class 2606 OID 143102)
-- Dependencies: 2012 2012
-- Name: produit_therapeutique_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY produit_therapeutique
    ADD CONSTRAINT produit_therapeutique_pkey PRIMARY KEY (id);


--
-- TOC entry 2685 (class 2606 OID 143113)
-- Dependencies: 2014 2014
-- Name: promoteur_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY promoteur
    ADD CONSTRAINT promoteur_pkey PRIMARY KEY (id);


--
-- TOC entry 2688 (class 2606 OID 143124)
-- Dependencies: 2016 2016
-- Name: promoteur_sigrec_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY promoteur_sigrec
    ADD CONSTRAINT promoteur_sigrec_pkey PRIMARY KEY (id);


--
-- TOC entry 2691 (class 2606 OID 143132)
-- Dependencies: 2018 2018
-- Name: promoteur_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY promoteur_suivi
    ADD CONSTRAINT promoteur_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2695 (class 2606 OID 143143)
-- Dependencies: 2020 2020
-- Name: regle_surcout_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY regle_surcout
    ADD CONSTRAINT regle_surcout_pkey PRIMARY KEY (id);


--
-- TOC entry 2703 (class 2606 OID 143154)
-- Dependencies: 2022 2022
-- Name: retour_patient_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY retour_patient
    ADD CONSTRAINT retour_patient_pkey PRIMARY KEY (id);


--
-- TOC entry 2706 (class 2606 OID 143165)
-- Dependencies: 2024 2024
-- Name: sequence_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY sequence
    ADD CONSTRAINT sequence_pkey PRIMARY KEY (id);


--
-- TOC entry 2710 (class 2606 OID 143173)
-- Dependencies: 2026 2026
-- Name: service_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY service
    ADD CONSTRAINT service_pkey PRIMARY KEY (id);


--
-- TOC entry 2713 (class 2606 OID 143181)
-- Dependencies: 2028 2028
-- Name: service_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY service_suivi
    ADD CONSTRAINT service_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2716 (class 2606 OID 143192)
-- Dependencies: 2030 2030
-- Name: site_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY site
    ADD CONSTRAINT site_pkey PRIMARY KEY (id);


--
-- TOC entry 2719 (class 2606 OID 143200)
-- Dependencies: 2032 2032
-- Name: site_suivi_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY site_suivi
    ADD CONSTRAINT site_suivi_pkey PRIMARY KEY (id);


--
-- TOC entry 2723 (class 2606 OID 143211)
-- Dependencies: 2034 2034
-- Name: stockage_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY stockage
    ADD CONSTRAINT stockage_pkey PRIMARY KEY (id);


--
-- TOC entry 2726 (class 2606 OID 143219)
-- Dependencies: 2036 2036
-- Name: theme_pkey; Type: CONSTRAINT; Schema: public; Owner: eclipse; Tablespace: 
--

ALTER TABLE ONLY theme
    ADD CONSTRAINT theme_pkey PRIMARY KEY (id);


--
-- TOC entry 2397 (class 1259 OID 143220)
-- Dependencies: 1835
-- Name: idx_arc_investigateur_essai_sigrec; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_arc_investigateur_essai_sigrec ON arc_investigateur_sigrec USING btree (id_essai);


--
-- TOC entry 2704 (class 1259 OID 144094)
-- Dependencies: 2024
-- Name: idx_bras_sequence; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_bras_sequence ON sequence USING btree (id_bras_sequence);


--
-- TOC entry 2408 (class 1259 OID 143269)
-- Dependencies: 1842
-- Name: idx_brase_parent; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_brase_parent ON bras USING btree (id_bras_parent);


--
-- TOC entry 2692 (class 1259 OID 144047)
-- Dependencies: 2020
-- Name: idx_categorie_regle; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_categorie_regle ON regle_surcout USING btree (id_categorie);


--
-- TOC entry 2418 (class 1259 OID 143292)
-- Dependencies: 1848
-- Name: idx_co_investigateur_essai_sigrec; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_co_investigateur_essai_sigrec ON co_investigateur_sigrec USING btree (id_essai);


--
-- TOC entry 2421 (class 1259 OID 143308)
-- Dependencies: 1850
-- Name: idx_conditionnement_produit; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_conditionnement_produit ON conditionnement USING btree (id_produit);


--
-- TOC entry 2672 (class 1259 OID 143997)
-- Dependencies: 2008
-- Name: idx_conditionnement_produit_prescrit; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_conditionnement_produit_prescrit ON produit_prescrit USING btree (id_conditionnement);


--
-- TOC entry 2696 (class 1259 OID 144062)
-- Dependencies: 2022
-- Name: idx_conditionnement_retourpatient; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_conditionnement_retourpatient ON retour_patient USING btree (id_conditionnement);


--
-- TOC entry 2405 (class 1259 OID 143257)
-- Dependencies: 1840
-- Name: idx_contact_assurance_sigrec; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_contact_assurance_sigrec ON assurance_sigrec USING btree (id_contact);


--
-- TOC entry 2415 (class 1259 OID 143286)
-- Dependencies: 1846
-- Name: idx_contact_centre_sigrec; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_contact_centre_sigrec ON centre_sigrec USING btree (id_contact);


--
-- TOC entry 2426 (class 1259 OID 143315)
-- Dependencies: 1854
-- Name: idx_contact_cro_sigrec; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_contact_cro_sigrec ON cro_sigrec USING btree (id_contact);


--
-- TOC entry 2686 (class 1259 OID 144034)
-- Dependencies: 2016
-- Name: idx_contact_promoteur_sigrec; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_contact_promoteur_sigrec ON promoteur_sigrec USING btree (id_contact);


--
-- TOC entry 2560 (class 1259 OID 143664)
-- Dependencies: 1944
-- Name: idx_detail_contacts_habilitation; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_detail_contacts_habilitation ON habilitation USING btree (id_detail_contacts);


--
-- TOC entry 2409 (class 1259 OID 143268)
-- Dependencies: 1842
-- Name: idx_detail_design_bras; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_detail_design_bras ON bras USING btree (id_detail_design);


--
-- TOC entry 2491 (class 1259 OID 143499)
-- Dependencies: 1893
-- Name: idx_detail_etat_essai; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_detail_etat_essai ON essai_detail_etat USING btree (id_essai);


--
-- TOC entry 2665 (class 1259 OID 143974)
-- Dependencies: 2004
-- Name: idx_detail_stockage_detail_logistique; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_detail_stockage_detail_logistique ON produit_detail_stockage USING btree (id_detail_logistique);


--
-- TOC entry 2697 (class 1259 OID 144063)
-- Dependencies: 2022
-- Name: idx_detailstockage_retourpatient; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_detailstockage_retourpatient ON retour_patient USING btree (id_detailstockage);


--
-- TOC entry 2599 (class 1259 OID 143790)
-- Dependencies: 1965
-- Name: idx_disp_globale_dotation; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_disp_globale_dotation ON mvt_dispensation_globale USING btree (id_dotation);


--
-- TOC entry 2430 (class 1259 OID 143327)
-- Dependencies: 1856
-- Name: idx_disp_ordon; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_disp_ordon ON dispensation USING btree (id_ordonnancier);


--
-- TOC entry 2431 (class 1259 OID 143328)
-- Dependencies: 1856
-- Name: idx_disp_pharmacie; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_disp_pharmacie ON dispensation USING btree (id_pharmacie);


--
-- TOC entry 2595 (class 1259 OID 143774)
-- Dependencies: 1964
-- Name: idx_dispensation_dispensation_produit; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_dispensation_dispensation_produit ON mvt_dispensation USING btree (id_dispensation);


--
-- TOC entry 2447 (class 1259 OID 143393)
-- Dependencies: 1863
-- Name: idx_dispensation_elementtocheck; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_dispensation_elementtocheck ON element_to_check USING btree (id_dispensation);


--
-- TOC entry 2439 (class 1259 OID 143355)
-- Dependencies: 1861
-- Name: idx_dotation_cond; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_dotation_cond ON dotation USING btree (id_conditionnement);


--
-- TOC entry 2440 (class 1259 OID 143358)
-- Dependencies: 1861
-- Name: idx_dotation_essai; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_dotation_essai ON dotation USING btree (id_essai);


--
-- TOC entry 2441 (class 1259 OID 143357)
-- Dependencies: 1861
-- Name: idx_dotation_personne; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_dotation_personne ON dotation USING btree (id_personne);


--
-- TOC entry 2442 (class 1259 OID 143354)
-- Dependencies: 1861
-- Name: idx_dotation_pharma; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_dotation_pharma ON dotation USING btree (id_pharmacie);


--
-- TOC entry 2443 (class 1259 OID 143356)
-- Dependencies: 1861
-- Name: idx_dotation_produit; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_dotation_produit ON dotation USING btree (id_produit);


--
-- TOC entry 2444 (class 1259 OID 143359)
-- Dependencies: 1861
-- Name: idx_dotation_service; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_dotation_service ON dotation USING btree (id_service);


--
-- TOC entry 2448 (class 1259 OID 143392)
-- Dependencies: 1863
-- Name: idx_elementtocheck_ordon; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_elementtocheck_ordon ON element_to_check USING btree (id_ordonnancier);


--
-- TOC entry 2449 (class 1259 OID 143391)
-- Dependencies: 1863
-- Name: idx_eltcheck_personne; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_eltcheck_personne ON element_to_check USING btree (id_personne);


--
-- TOC entry 2457 (class 1259 OID 143426)
-- Dependencies: 1867
-- Name: idx_essai_commentaire_detail_administratif; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_essai_commentaire_detail_administratif ON essai_commentaire_detail_administratif_archi USING btree (id_detailadministratif);


--
-- TOC entry 2460 (class 1259 OID 143432)
-- Dependencies: 1869
-- Name: idx_essai_commentaire_detail_faisabilite; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_essai_commentaire_detail_faisabilite ON essai_commentaire_detail_faisabilite USING btree (id_detailfaisabilite);


--
-- TOC entry 2463 (class 1259 OID 143438)
-- Dependencies: 1871
-- Name: idx_essai_commentaire_detail_recherche; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_essai_commentaire_detail_recherche ON essai_commentaire_detail_recherche USING btree (id_detailrecherche);


--
-- TOC entry 2427 (class 1259 OID 143314)
-- Dependencies: 1854
-- Name: idx_essai_cro_sigrec; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_essai_cro_sigrec ON cro_sigrec USING btree (id_essai);


--
-- TOC entry 2525 (class 1259 OID 143585)
-- Dependencies: 1919
-- Name: idx_essai_document_detail_administratif; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_essai_document_detail_administratif ON essai_document_detail_administratif USING btree (id_detailadministratif);


--
-- TOC entry 2532 (class 1259 OID 143601)
-- Dependencies: 1925
-- Name: idx_essai_document_detail_surcout; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_essai_document_detail_surcout ON essai_document_detail_surcout USING btree (id_detailsurcout);


--
-- TOC entry 2549 (class 1259 OID 143641)
-- Dependencies: 1936
-- Name: idx_essai_evenement; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_essai_evenement ON evenement USING btree (id_essai);


--
-- TOC entry 2565 (class 1259 OID 143682)
-- Dependencies: 1948
-- Name: idx_essai_incident; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_essai_incident ON incident USING btree (id_essai);


--
-- TOC entry 2571 (class 1259 OID 143694)
-- Dependencies: 1952
-- Name: idx_essai_inclusion; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_essai_inclusion ON inclusion USING btree (id_essai);


--
-- TOC entry 2660 (class 1259 OID 143961)
-- Dependencies: 2000
-- Name: idx_essai_produit_detail_produit; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_essai_produit_detail_produit ON produit USING btree (id_detailproduit);


--
-- TOC entry 2698 (class 1259 OID 144060)
-- Dependencies: 2022
-- Name: idx_essai_retourpatient; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_essai_retourpatient ON retour_patient USING btree (id_essai);


--
-- TOC entry 2631 (class 1259 OID 143870)
-- Dependencies: 1984
-- Name: idx_etab_pharma; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_etab_pharma ON pharmacie USING btree (id_etablissement);


--
-- TOC entry 2641 (class 1259 OID 143902)
-- Dependencies: 1990
-- Name: idx_etab_pole; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_etab_pole ON pole USING btree (id_etablissement);


--
-- TOC entry 2714 (class 1259 OID 144118)
-- Dependencies: 2030
-- Name: idx_etab_site; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_etab_site ON site USING btree (id_etablissement);


--
-- TOC entry 2579 (class 1259 OID 143726)
-- Dependencies: 1957
-- Name: idx_grille_item; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_grille_item ON item USING btree (id_grille);


--
-- TOC entry 2555 (class 1259 OID 143653)
-- Dependencies: 1940
-- Name: idx_grille_modele_grille; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_grille_modele_grille ON grille USING btree (id_grille_modele);


--
-- TOC entry 2724 (class 1259 OID 144142)
-- Dependencies: 2036
-- Name: idx_grille_modele_theme; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_grille_modele_theme ON theme USING btree (id_grille_modele);


--
-- TOC entry 2647 (class 1259 OID 143916)
-- Dependencies: 1994
-- Name: idx_inclusion_prescription; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_inclusion_prescription ON prescription USING btree (id_inclusion);


--
-- TOC entry 2648 (class 1259 OID 143917)
-- Dependencies: 1994
-- Name: idx_investigateur_prescription; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_investigateur_prescription ON prescription USING btree (id_investigateur);


--
-- TOC entry 2537 (class 1259 OID 143618)
-- Dependencies: 1928
-- Name: idx_investigateur_principal_essai_sigrec; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_investigateur_principal_essai_sigrec ON essai_sigrec USING btree (id_investigateurprincipal);


--
-- TOC entry 2604 (class 1259 OID 143809)
-- Dependencies: 1968
-- Name: idx_mvtstock_conditionnement; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_mvtstock_conditionnement ON mvtstock USING btree (id_conditionnement);


--
-- TOC entry 2605 (class 1259 OID 143806)
-- Dependencies: 1968
-- Name: idx_mvtstock_essai; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_mvtstock_essai ON mvtstock USING btree (id_essai);


--
-- TOC entry 2606 (class 1259 OID 143810)
-- Dependencies: 1968
-- Name: idx_mvtstock_personne; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_mvtstock_personne ON mvtstock USING btree (id_personne);


--
-- TOC entry 2607 (class 1259 OID 143808)
-- Dependencies: 1968
-- Name: idx_mvtstock_pharmacie; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_mvtstock_pharmacie ON mvtstock USING btree (id_pharmacie);


--
-- TOC entry 2590 (class 1259 OID 143757)
-- Dependencies: 1962
-- Name: idx_mvtstock_pharmaciedest; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_mvtstock_pharmaciedest ON mvt_cession_pui USING btree (id_pharmaciedest);


--
-- TOC entry 2608 (class 1259 OID 143807)
-- Dependencies: 1968
-- Name: idx_mvtstock_produit; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_mvtstock_produit ON mvtstock USING btree (id_produit);


--
-- TOC entry 2564 (class 1259 OID 143676)
-- Dependencies: 1946
-- Name: idx_patient_historique_patient; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_patient_historique_patient ON historique_patient USING btree (id_patient);


--
-- TOC entry 2572 (class 1259 OID 143695)
-- Dependencies: 1952
-- Name: idx_patient_inclusion; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_patient_inclusion ON inclusion USING btree (id_patient);


--
-- TOC entry 2699 (class 1259 OID 144061)
-- Dependencies: 2022
-- Name: idx_patient_retourpatient; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_patient_retourpatient ON retour_patient USING btree (id_patient);


--
-- TOC entry 2561 (class 1259 OID 143665)
-- Dependencies: 1944
-- Name: idx_personne_habilitation; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_personne_habilitation ON habilitation USING btree (id_personne);


--
-- TOC entry 2700 (class 1259 OID 144059)
-- Dependencies: 2022
-- Name: idx_personne_retourpatient; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_personne_retourpatient ON retour_patient USING btree (id_personne);


--
-- TOC entry 2453 (class 1259 OID 143415)
-- Dependencies: 1865
-- Name: idx_pharma_essai; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_pharma_essai ON essai USING btree (id_pharma);


--
-- TOC entry 2613 (class 1259 OID 144148)
-- Dependencies: 1972
-- Name: idx_pharma_ordo_disp; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_pharma_ordo_disp ON ordonnancier_dispensation USING btree (id_pharma);


--
-- TOC entry 2616 (class 1259 OID 144149)
-- Dependencies: 1974
-- Name: idx_pharma_ordo_fab_reconst; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_pharma_ordo_fab_reconst ON ordonnancier_fab_reconst USING btree (id_pharma);


--
-- TOC entry 2720 (class 1259 OID 144130)
-- Dependencies: 2034
-- Name: idx_pharmacie_stockage; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_pharmacie_stockage ON stockage USING btree (id_pharmacie);


--
-- TOC entry 2707 (class 1259 OID 144101)
-- Dependencies: 2026
-- Name: idx_pole_service; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_pole_service ON service USING btree (id_pole);


--
-- TOC entry 2653 (class 1259 OID 143938)
-- Dependencies: 1996
-- Name: idx_prescription_conditionnement; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_prescription_conditionnement ON prescription_type USING btree (id_conditionnement);


--
-- TOC entry 2432 (class 1259 OID 143326)
-- Dependencies: 1856
-- Name: idx_prescription_dispensation; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_prescription_dispensation ON dispensation USING btree (id_prescription);


--
-- TOC entry 2654 (class 1259 OID 143940)
-- Dependencies: 1996
-- Name: idx_prescription_produit; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_prescription_produit ON prescription_type USING btree (id_produit);


--
-- TOC entry 2673 (class 1259 OID 143995)
-- Dependencies: 2008
-- Name: idx_prescription_produit_prescrit; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_prescription_produit_prescrit ON produit_prescrit USING btree (id_prescription);


--
-- TOC entry 2655 (class 1259 OID 143939)
-- Dependencies: 1996
-- Name: idx_prescription_sequence; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_prescription_sequence ON prescription_type USING btree (id_sequence);


--
-- TOC entry 2666 (class 1259 OID 143973)
-- Dependencies: 2004
-- Name: idx_produit_pharmacie; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_produit_pharmacie ON produit_detail_stockage USING btree (id_pharmacie);


--
-- TOC entry 2596 (class 1259 OID 143773)
-- Dependencies: 1964
-- Name: idx_produit_prescrit_dispensation_produit; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_produit_prescrit_dispensation_produit ON mvt_dispensation USING btree (id_produitprescrit);


--
-- TOC entry 2450 (class 1259 OID 143390)
-- Dependencies: 1863
-- Name: idx_produit_prescrit_elementtocheck; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_produit_prescrit_elementtocheck ON element_to_check USING btree (id_produitprescrit);


--
-- TOC entry 2674 (class 1259 OID 143996)
-- Dependencies: 2008
-- Name: idx_produit_produit_prescrit; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_produit_produit_prescrit ON produit_prescrit USING btree (id_produit);


--
-- TOC entry 2701 (class 1259 OID 144058)
-- Dependencies: 2022
-- Name: idx_produit_retourpatient; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_produit_retourpatient ON retour_patient USING btree (id_produit);


--
-- TOC entry 2667 (class 1259 OID 143972)
-- Dependencies: 2004
-- Name: idx_produit_stockage; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_produit_stockage ON produit_detail_stockage USING btree (id_stockage);


--
-- TOC entry 2624 (class 1259 OID 143858)
-- Dependencies: 1980
-- Name: idx_promo_arcpromo; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_promo_arcpromo ON personne USING btree (id_promoteur);


--
-- TOC entry 2625 (class 1259 OID 143857)
-- Dependencies: 1980
-- Name: idx_promo_contactpromo; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_promo_contactpromo ON personne USING btree (id_promoteur);


--
-- TOC entry 2454 (class 1259 OID 143414)
-- Dependencies: 1865
-- Name: idx_promo_essai; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_promo_essai ON essai USING btree (id_promoteur);


--
-- TOC entry 2538 (class 1259 OID 143617)
-- Dependencies: 1928
-- Name: idx_promo_essai_sigrec; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_promo_essai_sigrec ON essai_sigrec USING btree (id_promoteur);


--
-- TOC entry 2400 (class 1259 OID 143236)
-- Dependencies: 1837
-- Name: idx_promoteur_arc_promoteur_sigrec; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_promoteur_arc_promoteur_sigrec ON arc_promoteur_sigrec USING btree (id_promoteur);


--
-- TOC entry 2649 (class 1259 OID 143915)
-- Dependencies: 1994
-- Name: idx_sequence_prescriptin; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_sequence_prescriptin ON prescription USING btree (id_sequence);


--
-- TOC entry 2650 (class 1259 OID 143914)
-- Dependencies: 1994
-- Name: idx_service_prescription; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_service_prescription ON prescription USING btree (id_service);


--
-- TOC entry 2708 (class 1259 OID 144100)
-- Dependencies: 2026
-- Name: idx_site_service; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_site_service ON service USING btree (id_site);


--
-- TOC entry 2721 (class 1259 OID 144131)
-- Dependencies: 2034
-- Name: idx_stockage_parent; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_stockage_parent ON stockage USING btree (id_stockage_parent);


--
-- TOC entry 2468 (class 1259 OID 143449)
-- Dependencies: 1875
-- Name: idx_suivi_detail_administratif; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_detail_administratif ON essai_detail_administratif_suivi USING btree (id_detail_administratif);


--
-- TOC entry 2473 (class 1259 OID 143460)
-- Dependencies: 1879
-- Name: idx_suivi_detail_autres_documents; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_detail_autres_documents ON essai_detail_autres_documents_suivi USING btree (id_detail_autres_documents);


--
-- TOC entry 2478 (class 1259 OID 143471)
-- Dependencies: 1883
-- Name: idx_suivi_detail_contacts; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_detail_contacts ON essai_detail_contacts_suivi USING btree (id_detail_contacts);


--
-- TOC entry 2483 (class 1259 OID 143482)
-- Dependencies: 1887
-- Name: idx_suivi_detail_dates; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_detail_dates ON essai_detail_dates_suivi USING btree (id_detail_dates);


--
-- TOC entry 2488 (class 1259 OID 143493)
-- Dependencies: 1891
-- Name: idx_suivi_detail_design; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_detail_design ON essai_detail_design_suivi USING btree (id_detail_design);


--
-- TOC entry 2498 (class 1259 OID 143520)
-- Dependencies: 1898
-- Name: idx_suivi_detail_faisabilite; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_detail_faisabilite ON essai_detail_faisabilite_suivi USING btree (id_detail_faisabilite);


--
-- TOC entry 2505 (class 1259 OID 143541)
-- Dependencies: 1903
-- Name: idx_suivi_detail_pharma; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_detail_pharma ON essai_detail_pharma_suivi USING btree (id_detail_pharma);


--
-- TOC entry 2510 (class 1259 OID 143552)
-- Dependencies: 1907
-- Name: idx_suivi_detail_produit; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_detail_produit ON essai_detail_produit_suivi USING btree (id_detail_produit);


--
-- TOC entry 2517 (class 1259 OID 143568)
-- Dependencies: 1913
-- Name: idx_suivi_detail_recherche; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_detail_recherche ON essai_detail_recherche_suivi USING btree (id_detail_recherche);


--
-- TOC entry 2522 (class 1259 OID 143579)
-- Dependencies: 1917
-- Name: idx_suivi_detail_surcout; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_detail_surcout ON essai_detail_surcout_suivi USING btree (id_detail_surcout);


--
-- TOC entry 2541 (class 1259 OID 143629)
-- Dependencies: 1930
-- Name: idx_suivi_essai; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_essai ON essai_suivi USING btree (id_essai);


--
-- TOC entry 2546 (class 1259 OID 143635)
-- Dependencies: 1934
-- Name: idx_suivi_etablissement; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_etablissement ON etablissement_suivi USING btree (id_etablissement);


--
-- TOC entry 2552 (class 1259 OID 143647)
-- Dependencies: 1938
-- Name: idx_suivi_evenement; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_evenement ON evenement_suivi USING btree (id_evenement);


--
-- TOC entry 2568 (class 1259 OID 143688)
-- Dependencies: 1950
-- Name: idx_suivi_incident; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_incident ON incident_suivi USING btree (id_incident);


--
-- TOC entry 2621 (class 1259 OID 143851)
-- Dependencies: 1978
-- Name: idx_suivi_patient; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_patient ON patient_suivi USING btree (id_patient);


--
-- TOC entry 2628 (class 1259 OID 143864)
-- Dependencies: 1982
-- Name: idx_suivi_personne; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_personne ON personne_suivi USING btree (id_personne);


--
-- TOC entry 2636 (class 1259 OID 143886)
-- Dependencies: 1987
-- Name: idx_suivi_pharmacie; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_pharmacie ON pharmacie_suivi USING btree (id_pharmacie);


--
-- TOC entry 2644 (class 1259 OID 143908)
-- Dependencies: 1992
-- Name: idx_suivi_pole; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_pole ON pole_suivi USING btree (id_pole);


--
-- TOC entry 2679 (class 1259 OID 144023)
-- Dependencies: 2011
-- Name: idx_suivi_produit; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_produit ON produit_suivi USING btree (id_produit);


--
-- TOC entry 2689 (class 1259 OID 144040)
-- Dependencies: 2018
-- Name: idx_suivi_promoteur; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_promoteur ON promoteur_suivi USING btree (id_promoteur);


--
-- TOC entry 2711 (class 1259 OID 144112)
-- Dependencies: 2028
-- Name: idx_suivi_service; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_service ON service_suivi USING btree (id_service);


--
-- TOC entry 2717 (class 1259 OID 144124)
-- Dependencies: 2032
-- Name: idx_suivi_site; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_suivi_site ON site_suivi USING btree (id_site);


--
-- TOC entry 2412 (class 1259 OID 143280)
-- Dependencies: 1844
-- Name: idx_theme_categorie; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_theme_categorie ON categorie USING btree (id_theme);


--
-- TOC entry 2693 (class 1259 OID 144046)
-- Dependencies: 2020
-- Name: idx_theme_regle; Type: INDEX; Schema: public; Owner: eclipse; Tablespace: 
--

CREATE INDEX idx_theme_regle ON regle_surcout USING btree (id_theme);


--
-- TOC entry 2802 (class 2606 OID 143642)
-- Dependencies: 1865 1936 2451
-- Name: fk1174a6939fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY evenement
    ADD CONSTRAINT fk1174a6939fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2839 (class 2606 OID 143846)
-- Dependencies: 2632 1974 1984
-- Name: fk12c037c73a903eb7; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY ordonnancier_fab_reconst
    ADD CONSTRAINT fk12c037c73a903eb7 FOREIGN KEY (id_pharma) REFERENCES pharmacie(id);


--
-- TOC entry 2781 (class 2606 OID 143526)
-- Dependencies: 1865 2451 1900
-- Name: fk17e192d939fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_pharma
    ADD CONSTRAINT fk17e192d939fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2841 (class 2606 OID 143859)
-- Dependencies: 2684 1980 2014
-- Name: fk1a6a27cc4285b151; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY personne
    ADD CONSTRAINT fk1a6a27cc4285b151 FOREIGN KEY (id_promoteur) REFERENCES promoteur(id);


--
-- TOC entry 2853 (class 2606 OID 143928)
-- Dependencies: 2709 2026 1994
-- Name: fk1b6fa41a1cf65f51; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY prescription
    ADD CONSTRAINT fk1b6fa41a1cf65f51 FOREIGN KEY (id_service) REFERENCES service(id);


--
-- TOC entry 2852 (class 2606 OID 143923)
-- Dependencies: 2705 2024 1994
-- Name: fk1b6fa41a807681fd; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY prescription
    ADD CONSTRAINT fk1b6fa41a807681fd FOREIGN KEY (id_sequence) REFERENCES sequence(id);


--
-- TOC entry 2854 (class 2606 OID 143933)
-- Dependencies: 1994 2573 1952
-- Name: fk1b6fa41adb692012; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY prescription
    ADD CONSTRAINT fk1b6fa41adb692012 FOREIGN KEY (id_inclusion) REFERENCES inclusion(id);


--
-- TOC entry 2851 (class 2606 OID 143918)
-- Dependencies: 2626 1994 1980
-- Name: fk1b6fa41aea08da8f; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY prescription
    ADD CONSTRAINT fk1b6fa41aea08da8f FOREIGN KEY (id_investigateur) REFERENCES personne(id);


--
-- TOC entry 2731 (class 2606 OID 143242)
-- Dependencies: 2422 1852 1837
-- Name: fk1df3b08e91ecd533; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY arc_promoteur_sigrec
    ADD CONSTRAINT fk1df3b08e91ecd533 FOREIGN KEY (id_contact) REFERENCES contact_sigrec(id);


--
-- TOC entry 2730 (class 2606 OID 143237)
-- Dependencies: 2016 1837 2687
-- Name: fk1df3b08ed4112aed; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY arc_promoteur_sigrec
    ADD CONSTRAINT fk1df3b08ed4112aed FOREIGN KEY (id_promoteur) REFERENCES promoteur_sigrec(id);


--
-- TOC entry 2848 (class 2606 OID 143897)
-- Dependencies: 1988 2626 1980
-- Name: fk1eabc02f24482761; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY pharmacien_pharmacie
    ADD CONSTRAINT fk1eabc02f24482761 FOREIGN KEY (id_pharmacien) REFERENCES personne(id);


--
-- TOC entry 2847 (class 2606 OID 143892)
-- Dependencies: 1984 1988 2632
-- Name: fk1eabc02f4de40194; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY pharmacien_pharmacie
    ADD CONSTRAINT fk1eabc02f4de40194 FOREIGN KEY (id_pharmacie) REFERENCES pharmacie(id);


--
-- TOC entry 2792 (class 2606 OID 143586)
-- Dependencies: 1919 2464 1873
-- Name: fk20a01eebb314ca7e; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_document_detail_administratif
    ADD CONSTRAINT fk20a01eebb314ca7e FOREIGN KEY (id_detailadministratif) REFERENCES essai_detail_administratif(id);


--
-- TOC entry 2795 (class 2606 OID 143602)
-- Dependencies: 1915 1925 2518
-- Name: fk24399e3f3233d23a; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_document_detail_surcout
    ADD CONSTRAINT fk24399e3f3233d23a FOREIGN KEY (id_detailsurcout) REFERENCES essai_detail_surcout(id);


--
-- TOC entry 2887 (class 2606 OID 144125)
-- Dependencies: 2032 2030 2715
-- Name: fk2694c8427aad8e07; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY site_suivi
    ADD CONSTRAINT fk2694c8427aad8e07 FOREIGN KEY (id_site) REFERENCES site(id);


--
-- TOC entry 2771 (class 2606 OID 143472)
-- Dependencies: 1881 1883 2474
-- Name: fk2a86a3aa27453d52; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_contacts_suivi
    ADD CONSTRAINT fk2a86a3aa27453d52 FOREIGN KEY (id_detail_contacts) REFERENCES essai_detail_contacts(id);


--
-- TOC entry 2736 (class 2606 OID 143270)
-- Dependencies: 2484 1889 1842
-- Name: fk2e4482387f8764; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY bras
    ADD CONSTRAINT fk2e4482387f8764 FOREIGN KEY (id_detail_design) REFERENCES essai_detail_design(id);


--
-- TOC entry 2737 (class 2606 OID 143275)
-- Dependencies: 1842 1842 2406
-- Name: fk2e44824d844dbc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY bras
    ADD CONSTRAINT fk2e44824d844dbc FOREIGN KEY (id_bras_parent) REFERENCES bras(id);


--
-- TOC entry 2747 (class 2606 OID 143334)
-- Dependencies: 1856 2632 1984
-- Name: fk2eaeffed4de40194; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY dispensation
    ADD CONSTRAINT fk2eaeffed4de40194 FOREIGN KEY (id_pharmacie) REFERENCES pharmacie(id);


--
-- TOC entry 2746 (class 2606 OID 143329)
-- Dependencies: 1856 1994 2651
-- Name: fk2eaeffed87ff1713; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY dispensation
    ADD CONSTRAINT fk2eaeffed87ff1713 FOREIGN KEY (id_prescription) REFERENCES prescription(id);


--
-- TOC entry 2748 (class 2606 OID 143339)
-- Dependencies: 2614 1856 1972
-- Name: fk2eaeffedb539f569; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY dispensation
    ADD CONSTRAINT fk2eaeffedb539f569 FOREIGN KEY (id_ordonnancier) REFERENCES ordonnancier_dispensation(id);


--
-- TOC entry 2807 (class 2606 OID 143671)
-- Dependencies: 2474 1881 1944
-- Name: fk2fee5dbe27453d52; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY habilitation
    ADD CONSTRAINT fk2fee5dbe27453d52 FOREIGN KEY (id_detail_contacts) REFERENCES essai_detail_contacts(id);


--
-- TOC entry 2806 (class 2606 OID 143666)
-- Dependencies: 1980 1944 2626
-- Name: fk2fee5dbe8800c21; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY habilitation
    ADD CONSTRAINT fk2fee5dbe8800c21 FOREIGN KEY (id_personne) REFERENCES personne(id);


--
-- TOC entry 2817 (class 2606 OID 143727)
-- Dependencies: 2553 1940 1957
-- Name: fk317b13976c82de; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY item
    ADD CONSTRAINT fk317b13976c82de FOREIGN KEY (id_grille) REFERENCES grille(id);


--
-- TOC entry 2774 (class 2606 OID 143488)
-- Dependencies: 1889 2451 1865
-- Name: fk345311a39fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_design
    ADD CONSTRAINT fk345311a39fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2849 (class 2606 OID 143903)
-- Dependencies: 1990 1932 2542
-- Name: fk3497b8cd45a413; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY pole
    ADD CONSTRAINT fk3497b8cd45a413 FOREIGN KEY (id_etablissement) REFERENCES etablissement(id);


--
-- TOC entry 2886 (class 2606 OID 144119)
-- Dependencies: 1932 2542 2030
-- Name: fk35df47cd45a413; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY site
    ADD CONSTRAINT fk35df47cd45a413 FOREIGN KEY (id_etablissement) REFERENCES etablissement(id);


--
-- TOC entry 2749 (class 2606 OID 143344)
-- Dependencies: 2661 1857 2000
-- Name: fk40b816e0ae5cce6; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY dispositif_medical
    ADD CONSTRAINT fk40b816e0ae5cce6 FOREIGN KEY (id) REFERENCES produit(id);


--
-- TOC entry 2743 (class 2606 OID 143309)
-- Dependencies: 2000 2661 1850
-- Name: fk46e35c70a1ddf650; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY conditionnement
    ADD CONSTRAINT fk46e35c70a1ddf650 FOREIGN KEY (id_produit) REFERENCES produit(id);


--
-- TOC entry 2768 (class 2606 OID 143455)
-- Dependencies: 1865 2451 1877
-- Name: fk4c82a77539fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_autres_documents
    ADD CONSTRAINT fk4c82a77539fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2846 (class 2606 OID 143887)
-- Dependencies: 2632 1987 1984
-- Name: fk4d5ce8dd4de40194; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY pharmacie_suivi
    ADD CONSTRAINT fk4d5ce8dd4de40194 FOREIGN KEY (id_pharmacie) REFERENCES pharmacie(id);


--
-- TOC entry 2776 (class 2606 OID 143500)
-- Dependencies: 1893 2451 1865
-- Name: fk4e973f7e39fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_etat
    ADD CONSTRAINT fk4e973f7e39fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2754 (class 2606 OID 143375)
-- Dependencies: 2026 2709 1861
-- Name: fk4f489b4c1cf65f51; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY dotation
    ADD CONSTRAINT fk4f489b4c1cf65f51 FOREIGN KEY (id_service) REFERENCES service(id);


--
-- TOC entry 2752 (class 2606 OID 143365)
-- Dependencies: 2451 1861 1865
-- Name: fk4f489b4c39fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY dotation
    ADD CONSTRAINT fk4f489b4c39fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2751 (class 2606 OID 143360)
-- Dependencies: 1984 2632 1861
-- Name: fk4f489b4c4de40194; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY dotation
    ADD CONSTRAINT fk4f489b4c4de40194 FOREIGN KEY (id_pharmacie) REFERENCES pharmacie(id);


--
-- TOC entry 2756 (class 2606 OID 143385)
-- Dependencies: 1980 1861 2626
-- Name: fk4f489b4c8800c21; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY dotation
    ADD CONSTRAINT fk4f489b4c8800c21 FOREIGN KEY (id_personne) REFERENCES personne(id);


--
-- TOC entry 2753 (class 2606 OID 143370)
-- Dependencies: 1861 2419 1850
-- Name: fk4f489b4c9d7535de; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY dotation
    ADD CONSTRAINT fk4f489b4c9d7535de FOREIGN KEY (id_conditionnement) REFERENCES conditionnement(id);


--
-- TOC entry 2755 (class 2606 OID 143380)
-- Dependencies: 1861 2000 2661
-- Name: fk4f489b4ca1ddf650; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY dotation
    ADD CONSTRAINT fk4f489b4ca1ddf650 FOREIGN KEY (id_produit) REFERENCES produit(id);


--
-- TOC entry 2882 (class 2606 OID 144095)
-- Dependencies: 2024 2406 1842
-- Name: fk507077c145975293; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY sequence
    ADD CONSTRAINT fk507077c145975293 FOREIGN KEY (id_bras_sequence) REFERENCES bras(id);


--
-- TOC entry 2809 (class 2606 OID 143683)
-- Dependencies: 1865 1948 2451
-- Name: fk52f44d239fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY incident
    ADD CONSTRAINT fk52f44d239fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2764 (class 2606 OID 143433)
-- Dependencies: 2492 1869 1895
-- Name: fk547c5a9a8897241c; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_commentaire_detail_faisabilite
    ADD CONSTRAINT fk547c5a9a8897241c FOREIGN KEY (id_detailfaisabilite) REFERENCES essai_detail_faisabilite(id);


--
-- TOC entry 2858 (class 2606 OID 143956)
-- Dependencies: 2535 1928 1998
-- Name: fk55375893a81b195e; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY prevision_sigrec
    ADD CONSTRAINT fk55375893a81b195e FOREIGN KEY (id_essai) REFERENCES essai_sigrec(id);


--
-- TOC entry 2777 (class 2606 OID 143505)
-- Dependencies: 1895 1865 2451
-- Name: fk5a8d447539fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_faisabilite
    ADD CONSTRAINT fk5a8d447539fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2810 (class 2606 OID 143689)
-- Dependencies: 1948 1950 2566
-- Name: fk5b30998db77789cb; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY incident_suivi
    ADD CONSTRAINT fk5b30998db77789cb FOREIGN KEY (id_incident) REFERENCES incident(id);


--
-- TOC entry 2740 (class 2606 OID 143293)
-- Dependencies: 1848 2413 1846
-- Name: fk5b85a4f55c631481; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY co_investigateur_sigrec
    ADD CONSTRAINT fk5b85a4f55c631481 FOREIGN KEY (id_centre) REFERENCES centre_sigrec(id);


--
-- TOC entry 2741 (class 2606 OID 143298)
-- Dependencies: 1852 1848 2422
-- Name: fk5b85a4f591ecd533; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY co_investigateur_sigrec
    ADD CONSTRAINT fk5b85a4f591ecd533 FOREIGN KEY (id_contact) REFERENCES contact_sigrec(id);


--
-- TOC entry 2742 (class 2606 OID 143303)
-- Dependencies: 1928 2535 1848
-- Name: fk5b85a4f5a81b195e; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY co_investigateur_sigrec
    ADD CONSTRAINT fk5b85a4f5a81b195e FOREIGN KEY (id_essai) REFERENCES essai_sigrec(id);


--
-- TOC entry 2762 (class 2606 OID 143421)
-- Dependencies: 2632 1984 1865
-- Name: fk5c5486d3a903eb7; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai
    ADD CONSTRAINT fk5c5486d3a903eb7 FOREIGN KEY (id_pharma) REFERENCES pharmacie(id);


--
-- TOC entry 2761 (class 2606 OID 143416)
-- Dependencies: 2684 2014 1865
-- Name: fk5c5486d4285b151; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai
    ADD CONSTRAINT fk5c5486d4285b151 FOREIGN KEY (id_promoteur) REFERENCES promoteur(id);


--
-- TOC entry 2787 (class 2606 OID 143558)
-- Dependencies: 1865 2451 1909
-- Name: fk5c79a91f39fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_recherche
    ADD CONSTRAINT fk5c79a91f39fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2738 (class 2606 OID 143281)
-- Dependencies: 2725 2036 1844
-- Name: fk5d54e13740161942; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY categorie
    ADD CONSTRAINT fk5d54e13740161942 FOREIGN KEY (id_theme) REFERENCES theme(id);


--
-- TOC entry 2739 (class 2606 OID 143287)
-- Dependencies: 2422 1852 1846
-- Name: fk5f43710391ecd533; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY centre_sigrec
    ADD CONSTRAINT fk5f43710391ecd533 FOREIGN KEY (id_contact) REFERENCES contact_sigrec(id);


--
-- TOC entry 2842 (class 2606 OID 143865)
-- Dependencies: 2626 1982 1980
-- Name: fk60fd9078800c21; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY personne_suivi
    ADD CONSTRAINT fk60fd9078800c21 FOREIGN KEY (id_personne) REFERENCES personne(id);


--
-- TOC entry 2831 (class 2606 OID 143801)
-- Dependencies: 1966 1968 2609
-- Name: fk61102bfd3e8f1c97; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvt_retour_promoteur
    ADD CONSTRAINT fk61102bfd3e8f1c97 FOREIGN KEY (id) REFERENCES mvtstock(id);


--
-- TOC entry 2889 (class 2606 OID 144137)
-- Dependencies: 2034 1984 2632
-- Name: fk658922294de40194; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY stockage
    ADD CONSTRAINT fk658922294de40194 FOREIGN KEY (id_pharmacie) REFERENCES pharmacie(id);


--
-- TOC entry 2888 (class 2606 OID 144132)
-- Dependencies: 2034 2034 2722
-- Name: fk65892229b4ed4491; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY stockage
    ADD CONSTRAINT fk65892229b4ed4491 FOREIGN KEY (id_stockage_parent) REFERENCES stockage(id);


--
-- TOC entry 2794 (class 2606 OID 143596)
-- Dependencies: 1923 1900 2499
-- Name: fk66a8bf19d08532d; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_document_detail_pharma
    ADD CONSTRAINT fk66a8bf19d08532d FOREIGN KEY (id_detail_pharma) REFERENCES essai_detail_pharma(id);


--
-- TOC entry 2822 (class 2606 OID 143752)
-- Dependencies: 1961 1968 2609
-- Name: fk67c907da3e8f1c97; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvt_autre_sortie
    ADD CONSTRAINT fk67c907da3e8f1c97 FOREIGN KEY (id) REFERENCES mvtstock(id);


--
-- TOC entry 2890 (class 2606 OID 144143)
-- Dependencies: 2556 1942 2036
-- Name: fk69375c9195ade5f; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY theme
    ADD CONSTRAINT fk69375c9195ade5f FOREIGN KEY (id_grille_modele) REFERENCES grille_modele(id);


--
-- TOC entry 2833 (class 2606 OID 143816)
-- Dependencies: 1968 2451 1865
-- Name: fk697c8a0b39fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvtstock
    ADD CONSTRAINT fk697c8a0b39fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2832 (class 2606 OID 143811)
-- Dependencies: 1968 2632 1984
-- Name: fk697c8a0b4de40194; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvtstock
    ADD CONSTRAINT fk697c8a0b4de40194 FOREIGN KEY (id_pharmacie) REFERENCES pharmacie(id);


--
-- TOC entry 2836 (class 2606 OID 143831)
-- Dependencies: 2626 1980 1968
-- Name: fk697c8a0b8800c21; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvtstock
    ADD CONSTRAINT fk697c8a0b8800c21 FOREIGN KEY (id_personne) REFERENCES personne(id);


--
-- TOC entry 2834 (class 2606 OID 143821)
-- Dependencies: 1968 2419 1850
-- Name: fk697c8a0b9d7535de; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvtstock
    ADD CONSTRAINT fk697c8a0b9d7535de FOREIGN KEY (id_conditionnement) REFERENCES conditionnement(id);


--
-- TOC entry 2835 (class 2606 OID 143826)
-- Dependencies: 1968 2661 2000
-- Name: fk697c8a0ba1ddf650; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvtstock
    ADD CONSTRAINT fk697c8a0ba1ddf650 FOREIGN KEY (id_produit) REFERENCES produit(id);


--
-- TOC entry 2769 (class 2606 OID 143461)
-- Dependencies: 1879 1877 2469
-- Name: fk698a35f0ec2855a; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_autres_documents_suivi
    ADD CONSTRAINT fk698a35f0ec2855a FOREIGN KEY (id_detail_autres_documents) REFERENCES essai_detail_autres_documents(id);


--
-- TOC entry 2789 (class 2606 OID 143569)
-- Dependencies: 2511 1909 1913
-- Name: fk6b5fd01a1cf64d65; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_recherche_suivi
    ADD CONSTRAINT fk6b5fd01a1cf64d65 FOREIGN KEY (id_detail_recherche) REFERENCES essai_detail_recherche(id);


--
-- TOC entry 2873 (class 2606 OID 144041)
-- Dependencies: 2018 2684 2014
-- Name: fk6bdaed84285b151; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY promoteur_suivi
    ADD CONSTRAINT fk6bdaed84285b151 FOREIGN KEY (id_promoteur) REFERENCES promoteur(id);


--
-- TOC entry 2860 (class 2606 OID 143967)
-- Dependencies: 2002 2661 2000
-- Name: fk6e9d2d16a1ddf650; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY produit_detail_logistique
    ADD CONSTRAINT fk6e9d2d16a1ddf650 FOREIGN KEY (id_produit) REFERENCES produit(id);


--
-- TOC entry 2779 (class 2606 OID 143515)
-- Dependencies: 1896 2026 2709
-- Name: fk71236ceb1cf65f51; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_faisabilite_service
    ADD CONSTRAINT fk71236ceb1cf65f51 FOREIGN KEY (id_service) REFERENCES service(id);


--
-- TOC entry 2778 (class 2606 OID 143510)
-- Dependencies: 1896 1895 2492
-- Name: fk71236ceb3607a129; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_faisabilite_service
    ADD CONSTRAINT fk71236ceb3607a129 FOREIGN KEY (id_essai) REFERENCES essai_detail_faisabilite(id);


--
-- TOC entry 2750 (class 2606 OID 143349)
-- Dependencies: 1915 1859 2518
-- Name: fk75589534f5ae6985; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY donnees_prevision
    ADD CONSTRAINT fk75589534f5ae6985 FOREIGN KEY (id_detail_surcout) REFERENCES essai_detail_surcout(id);


--
-- TOC entry 2883 (class 2606 OID 144102)
-- Dependencies: 2026 2642 1990
-- Name: fk7643c6b57aaafee9; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY service
    ADD CONSTRAINT fk7643c6b57aaafee9 FOREIGN KEY (id_pole) REFERENCES pole(id);


--
-- TOC entry 2884 (class 2606 OID 144107)
-- Dependencies: 2715 2026 2030
-- Name: fk7643c6b57aad8e07; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY service
    ADD CONSTRAINT fk7643c6b57aad8e07 FOREIGN KEY (id_site) REFERENCES site(id);


--
-- TOC entry 2820 (class 2606 OID 143742)
-- Dependencies: 2661 1959 2000
-- Name: fk77228d19ae5cce6; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY medicament
    ADD CONSTRAINT fk77228d19ae5cce6 FOREIGN KEY (id) REFERENCES produit(id);


--
-- TOC entry 2780 (class 2606 OID 143521)
-- Dependencies: 2492 1898 1895
-- Name: fk7a6b12f0530f1de7; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_faisabilite_suivi
    ADD CONSTRAINT fk7a6b12f0530f1de7 FOREIGN KEY (id_detail_faisabilite) REFERENCES essai_detail_faisabilite(id);


--
-- TOC entry 2803 (class 2606 OID 143648)
-- Dependencies: 1936 1938 2547
-- Name: fk7c0dd1e41aeddf50; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY evenement_suivi
    ADD CONSTRAINT fk7c0dd1e41aeddf50 FOREIGN KEY (id_evenement) REFERENCES evenement(id);


--
-- TOC entry 2864 (class 2606 OID 143990)
-- Dependencies: 2661 2006 2000
-- Name: fk7c4c166aa1ddf650; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY produit_document_actes_pharma
    ADD CONSTRAINT fk7c4c166aa1ddf650 FOREIGN KEY (id_produit) REFERENCES produit(id);


--
-- TOC entry 2828 (class 2606 OID 143785)
-- Dependencies: 1856 1964 2428
-- Name: fk800c37c11a1781c6; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvt_dispensation
    ADD CONSTRAINT fk800c37c11a1781c6 FOREIGN KEY (id_dispensation) REFERENCES dispensation(id);


--
-- TOC entry 2826 (class 2606 OID 143775)
-- Dependencies: 1968 2609 1964
-- Name: fk800c37c13e8f1c97; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvt_dispensation
    ADD CONSTRAINT fk800c37c13e8f1c97 FOREIGN KEY (id) REFERENCES mvtstock(id);


--
-- TOC entry 2827 (class 2606 OID 143780)
-- Dependencies: 1964 2675 2008
-- Name: fk800c37c16d6ee647; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvt_dispensation
    ADD CONSTRAINT fk800c37c16d6ee647 FOREIGN KEY (id_produitprescrit) REFERENCES produit_prescrit(id);


--
-- TOC entry 2763 (class 2606 OID 143427)
-- Dependencies: 1867 1873 2464
-- Name: fk823d05e4b314ca7e; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_commentaire_detail_administratif_archi
    ADD CONSTRAINT fk823d05e4b314ca7e FOREIGN KEY (id_detailadministratif) REFERENCES essai_detail_administratif(id);


--
-- TOC entry 2814 (class 2606 OID 143711)
-- Dependencies: 2709 1953 2026
-- Name: fk833c86321cf65f51; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY investigateur_service
    ADD CONSTRAINT fk833c86321cf65f51 FOREIGN KEY (id_service) REFERENCES service(id);


--
-- TOC entry 2813 (class 2606 OID 143706)
-- Dependencies: 1953 1980 2626
-- Name: fk833c8632ea08da8f; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY investigateur_service
    ADD CONSTRAINT fk833c8632ea08da8f FOREIGN KEY (id_investigateur) REFERENCES personne(id);


--
-- TOC entry 2772 (class 2606 OID 143477)
-- Dependencies: 1865 1885 2451
-- Name: fk843a3ba939fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_dates
    ADD CONSTRAINT fk843a3ba939fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2850 (class 2606 OID 143909)
-- Dependencies: 1990 1992 2642
-- Name: fk8449a7f37aaafee9; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY pole_suivi
    ADD CONSTRAINT fk8449a7f37aaafee9 FOREIGN KEY (id_pole) REFERENCES pole(id);


--
-- TOC entry 2808 (class 2606 OID 143677)
-- Dependencies: 1976 1946 2619
-- Name: fk8529d883aedb3264; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY historique_patient
    ADD CONSTRAINT fk8529d883aedb3264 FOREIGN KEY (id_patient) REFERENCES patient(id);


--
-- TOC entry 2821 (class 2606 OID 143747)
-- Dependencies: 1960 1968 2609
-- Name: fk869711473e8f1c97; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvt_approvisionnement
    ADD CONSTRAINT fk869711473e8f1c97 FOREIGN KEY (id) REFERENCES mvtstock(id);


--
-- TOC entry 2786 (class 2606 OID 143553)
-- Dependencies: 1905 2506 1907
-- Name: fk8a0ab6487dbf9eef; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_produit_suivi
    ADD CONSTRAINT fk8a0ab6487dbf9eef FOREIGN KEY (id_detail_produit) REFERENCES essai_detail_produit(id);


--
-- TOC entry 2819 (class 2606 OID 143737)
-- Dependencies: 2020 1958 2694
-- Name: fk8b91f4e11622e8a9; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY item_regle
    ADD CONSTRAINT fk8b91f4e11622e8a9 FOREIGN KEY (id_regle) REFERENCES regle_surcout(id);


--
-- TOC entry 2818 (class 2606 OID 143732)
-- Dependencies: 2580 1958 1957
-- Name: fk8b91f4e1e0ff5276; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY item_regle
    ADD CONSTRAINT fk8b91f4e1e0ff5276 FOREIGN KEY (id_item) REFERENCES item(id);


--
-- TOC entry 2871 (class 2606 OID 144029)
-- Dependencies: 2661 2012 2000
-- Name: fk92053556ae5cce6; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY produit_therapeutique
    ADD CONSTRAINT fk92053556ae5cce6 FOREIGN KEY (id) REFERENCES produit(id);


--
-- TOC entry 2773 (class 2606 OID 143483)
-- Dependencies: 2479 1887 1885
-- Name: fk9506d324a7a1603; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_dates_suivi
    ADD CONSTRAINT fk9506d324a7a1603 FOREIGN KEY (id_detail_dates) REFERENCES essai_detail_dates(id);


--
-- TOC entry 2793 (class 2606 OID 143591)
-- Dependencies: 1921 1877 2469
-- Name: fk966f83b5ec2855a; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_document_detail_autres_documents
    ADD CONSTRAINT fk966f83b5ec2855a FOREIGN KEY (id_detail_autres_documents) REFERENCES essai_detail_autres_documents(id);


--
-- TOC entry 2767 (class 2606 OID 143450)
-- Dependencies: 2464 1875 1873
-- Name: fk98df0126bf757d89; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_administratif_suivi
    ADD CONSTRAINT fk98df0126bf757d89 FOREIGN KEY (id_detail_administratif) REFERENCES essai_detail_administratif(id);


--
-- TOC entry 2885 (class 2606 OID 144113)
-- Dependencies: 2026 2709 2028
-- Name: fk9946e5301cf65f51; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY service_suivi
    ADD CONSTRAINT fk9946e5301cf65f51 FOREIGN KEY (id_service) REFERENCES service(id);


--
-- TOC entry 2790 (class 2606 OID 143574)
-- Dependencies: 1865 1915 2451
-- Name: fk9a1b427f39fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_surcout
    ADD CONSTRAINT fk9a1b427f39fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2765 (class 2606 OID 143439)
-- Dependencies: 2511 1871 1909
-- Name: fk9b1204844dc45cda; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_commentaire_detail_recherche
    ADD CONSTRAINT fk9b1204844dc45cda FOREIGN KEY (id_detailrecherche) REFERENCES essai_detail_recherche(id);


--
-- TOC entry 2791 (class 2606 OID 143580)
-- Dependencies: 1917 2518 1915
-- Name: fk9c00e17af5ae6985; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_surcout_suivi
    ADD CONSTRAINT fk9c00e17af5ae6985 FOREIGN KEY (id_detail_surcout) REFERENCES essai_detail_surcout(id);


--
-- TOC entry 2838 (class 2606 OID 143841)
-- Dependencies: 1984 2632 1972
-- Name: fk9ea891de3a903eb7; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY ordonnancier_dispensation
    ADD CONSTRAINT fk9ea891de3a903eb7 FOREIGN KEY (id_pharma) REFERENCES pharmacie(id);


--
-- TOC entry 2766 (class 2606 OID 143444)
-- Dependencies: 1873 1865 2451
-- Name: fka145932b39fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_administratif
    ADD CONSTRAINT fka145932b39fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2783 (class 2606 OID 143536)
-- Dependencies: 1901 2632 1984
-- Name: fka30ce23c4de40194; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_pharma_pharmacie
    ADD CONSTRAINT fka30ce23c4de40194 FOREIGN KEY (id_pharmacie) REFERENCES pharmacie(id);


--
-- TOC entry 2782 (class 2606 OID 143531)
-- Dependencies: 1901 2499 1900
-- Name: fka30ce23cd08532d; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_pharma_pharmacie
    ADD CONSTRAINT fka30ce23cd08532d FOREIGN KEY (id_detail_pharma) REFERENCES essai_detail_pharma(id);


--
-- TOC entry 2812 (class 2606 OID 143701)
-- Dependencies: 1865 1952 2451
-- Name: fka6cdb91c39fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY inclusion
    ADD CONSTRAINT fka6cdb91c39fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2811 (class 2606 OID 143696)
-- Dependencies: 1952 1976 2619
-- Name: fka6cdb91caedb3264; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY inclusion
    ADD CONSTRAINT fka6cdb91caedb3264 FOREIGN KEY (id_patient) REFERENCES patient(id);


--
-- TOC entry 2815 (class 2606 OID 143716)
-- Dependencies: 1955 2413 1846
-- Name: fka9985b025c631481; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY investigateur_sigrec
    ADD CONSTRAINT fka9985b025c631481 FOREIGN KEY (id_centre) REFERENCES centre_sigrec(id);


--
-- TOC entry 2816 (class 2606 OID 143721)
-- Dependencies: 2422 1852 1955
-- Name: fka9985b0291ecd533; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY investigateur_sigrec
    ADD CONSTRAINT fka9985b0291ecd533 FOREIGN KEY (id_contact) REFERENCES contact_sigrec(id);


--
-- TOC entry 2844 (class 2606 OID 143876)
-- Dependencies: 1985 1984 2632
-- Name: fkafea0d444de40194; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY pharmacie_site
    ADD CONSTRAINT fkafea0d444de40194 FOREIGN KEY (id_pharmacie) REFERENCES pharmacie(id);


--
-- TOC entry 2845 (class 2606 OID 143881)
-- Dependencies: 2715 1985 2030
-- Name: fkafea0d447aad8e07; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY pharmacie_site
    ADD CONSTRAINT fkafea0d447aad8e07 FOREIGN KEY (id_site) REFERENCES site(id);


--
-- TOC entry 2840 (class 2606 OID 143852)
-- Dependencies: 1978 2619 1976
-- Name: fkb01e8d80aedb3264; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY patient_suivi
    ADD CONSTRAINT fkb01e8d80aedb3264 FOREIGN KEY (id_patient) REFERENCES patient(id);


--
-- TOC entry 2805 (class 2606 OID 143659)
-- Dependencies: 1942 1940 2556
-- Name: fkb63afd47195ade5f; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY grille
    ADD CONSTRAINT fkb63afd47195ade5f FOREIGN KEY (id_grille_modele) REFERENCES grille_modele(id);


--
-- TOC entry 2804 (class 2606 OID 143654)
-- Dependencies: 1915 1940 2518
-- Name: fkb63afd47f5ae6985; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY grille
    ADD CONSTRAINT fkb63afd47f5ae6985 FOREIGN KEY (id_detail_surcout) REFERENCES essai_detail_surcout(id);


--
-- TOC entry 2784 (class 2606 OID 143542)
-- Dependencies: 1903 2499 1900
-- Name: fkb8bc0654d08532d; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_pharma_suivi
    ADD CONSTRAINT fkb8bc0654d08532d FOREIGN KEY (id_detail_pharma) REFERENCES essai_detail_pharma(id);


--
-- TOC entry 2856 (class 2606 OID 143946)
-- Dependencies: 1996 2024 2705
-- Name: fkbe86243f807681fd; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY prescription_type
    ADD CONSTRAINT fkbe86243f807681fd FOREIGN KEY (id_sequence) REFERENCES sequence(id);


--
-- TOC entry 2855 (class 2606 OID 143941)
-- Dependencies: 1996 1850 2419
-- Name: fkbe86243f9d7535de; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY prescription_type
    ADD CONSTRAINT fkbe86243f9d7535de FOREIGN KEY (id_conditionnement) REFERENCES conditionnement(id);


--
-- TOC entry 2857 (class 2606 OID 143951)
-- Dependencies: 2661 1996 2000
-- Name: fkbe86243fa1ddf650; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY prescription_type
    ADD CONSTRAINT fkbe86243fa1ddf650 FOREIGN KEY (id_produit) REFERENCES produit(id);


--
-- TOC entry 2868 (class 2606 OID 144013)
-- Dependencies: 2709 2009 2026
-- Name: fkc171821f1cf65f51; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY produit_service
    ADD CONSTRAINT fkc171821f1cf65f51 FOREIGN KEY (id_service) REFERENCES service(id);


--
-- TOC entry 2869 (class 2606 OID 144018)
-- Dependencies: 2000 2661 2009
-- Name: fkc171821fa1ddf650; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY produit_service
    ADD CONSTRAINT fkc171821fa1ddf650 FOREIGN KEY (id_produit) REFERENCES produit(id);


--
-- TOC entry 2870 (class 2606 OID 144024)
-- Dependencies: 2000 2661 2011
-- Name: fkc1e4e524a1ddf650; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY produit_suivi
    ADD CONSTRAINT fkc1e4e524a1ddf650 FOREIGN KEY (id_produit) REFERENCES produit(id);


--
-- TOC entry 2829 (class 2606 OID 143791)
-- Dependencies: 2609 1968 1965
-- Name: fkc343fea43e8f1c97; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvt_dispensation_globale
    ADD CONSTRAINT fkc343fea43e8f1c97 FOREIGN KEY (id) REFERENCES mvtstock(id);


--
-- TOC entry 2830 (class 2606 OID 143796)
-- Dependencies: 1861 1965 2437
-- Name: fkc343fea464b18985; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvt_dispensation_globale
    ADD CONSTRAINT fkc343fea464b18985 FOREIGN KEY (id_dotation) REFERENCES dotation(id);


--
-- TOC entry 2877 (class 2606 OID 144069)
-- Dependencies: 2451 2022 1865
-- Name: fkc38b7dd139fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY retour_patient
    ADD CONSTRAINT fkc38b7dd139fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2881 (class 2606 OID 144089)
-- Dependencies: 1980 2022 2626
-- Name: fkc38b7dd18800c21; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY retour_patient
    ADD CONSTRAINT fkc38b7dd18800c21 FOREIGN KEY (id_personne) REFERENCES personne(id);


--
-- TOC entry 2879 (class 2606 OID 144079)
-- Dependencies: 2022 1850 2419
-- Name: fkc38b7dd19d7535de; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY retour_patient
    ADD CONSTRAINT fkc38b7dd19d7535de FOREIGN KEY (id_conditionnement) REFERENCES conditionnement(id);


--
-- TOC entry 2880 (class 2606 OID 144084)
-- Dependencies: 2022 2661 2000
-- Name: fkc38b7dd1a1ddf650; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY retour_patient
    ADD CONSTRAINT fkc38b7dd1a1ddf650 FOREIGN KEY (id_produit) REFERENCES produit(id);


--
-- TOC entry 2876 (class 2606 OID 144064)
-- Dependencies: 2619 2022 1976
-- Name: fkc38b7dd1aedb3264; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY retour_patient
    ADD CONSTRAINT fkc38b7dd1aedb3264 FOREIGN KEY (id_patient) REFERENCES patient(id);


--
-- TOC entry 2878 (class 2606 OID 144074)
-- Dependencies: 2004 2022 2668
-- Name: fkc38b7dd1d8bb7cd7; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY retour_patient
    ADD CONSTRAINT fkc38b7dd1d8bb7cd7 FOREIGN KEY (id_detailstockage) REFERENCES produit_detail_stockage(id);


--
-- TOC entry 2801 (class 2606 OID 143636)
-- Dependencies: 1932 1934 2542
-- Name: fkcaf42771cd45a413; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY etablissement_suivi
    ADD CONSTRAINT fkcaf42771cd45a413 FOREIGN KEY (id_etablissement) REFERENCES etablissement(id);


--
-- TOC entry 2800 (class 2606 OID 143630)
-- Dependencies: 1865 1930 2451
-- Name: fkcd5e3ce839fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_suivi
    ADD CONSTRAINT fkcd5e3ce839fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2865 (class 2606 OID 143998)
-- Dependencies: 2651 2008 1994
-- Name: fkce7075e087ff1713; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY produit_prescrit
    ADD CONSTRAINT fkce7075e087ff1713 FOREIGN KEY (id_prescription) REFERENCES prescription(id);


--
-- TOC entry 2866 (class 2606 OID 144003)
-- Dependencies: 2419 1850 2008
-- Name: fkce7075e09d7535de; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY produit_prescrit
    ADD CONSTRAINT fkce7075e09d7535de FOREIGN KEY (id_conditionnement) REFERENCES conditionnement(id);


--
-- TOC entry 2867 (class 2606 OID 144008)
-- Dependencies: 2661 2000 2008
-- Name: fkce7075e0a1ddf650; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY produit_prescrit
    ADD CONSTRAINT fkce7075e0a1ddf650 FOREIGN KEY (id_produit) REFERENCES produit(id);


--
-- TOC entry 2872 (class 2606 OID 144035)
-- Dependencies: 2422 1852 2016
-- Name: fkd04e1a4191ecd533; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY promoteur_sigrec
    ADD CONSTRAINT fkd04e1a4191ecd533 FOREIGN KEY (id_contact) REFERENCES contact_sigrec(id);


--
-- TOC entry 2837 (class 2606 OID 143836)
-- Dependencies: 1968 2609 1970
-- Name: fkd0e894cf2bee4c2b; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvtstock_document
    ADD CONSTRAINT fkd0e894cf2bee4c2b FOREIGN KEY (id_mvtstock) REFERENCES mvtstock(id);


--
-- TOC entry 2875 (class 2606 OID 144053)
-- Dependencies: 2725 2020 2036
-- Name: fkd387012940161942; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY regle_surcout
    ADD CONSTRAINT fkd387012940161942 FOREIGN KEY (id_theme) REFERENCES theme(id);


--
-- TOC entry 2874 (class 2606 OID 144048)
-- Dependencies: 1844 2410 2020
-- Name: fkd387012961ea981e; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY regle_surcout
    ADD CONSTRAINT fkd387012961ea981e FOREIGN KEY (id_categorie) REFERENCES categorie(id);


--
-- TOC entry 2797 (class 2606 OID 143612)
-- Dependencies: 2709 1926 2026
-- Name: fkd3f3f8e31cf65f51; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_service
    ADD CONSTRAINT fkd3f3f8e31cf65f51 FOREIGN KEY (id_service) REFERENCES service(id);


--
-- TOC entry 2796 (class 2606 OID 143607)
-- Dependencies: 1865 2451 1926
-- Name: fkd3f3f8e339fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_service
    ADD CONSTRAINT fkd3f3f8e339fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2775 (class 2606 OID 143494)
-- Dependencies: 2484 1889 1891
-- Name: fkd4e62fd5387f8764; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_design_suivi
    ADD CONSTRAINT fkd4e62fd5387f8764 FOREIGN KEY (id_detail_design) REFERENCES essai_detail_design(id);


--
-- TOC entry 2734 (class 2606 OID 143258)
-- Dependencies: 1852 1840 2422
-- Name: fkdb9e6f7191ecd533; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY assurance_sigrec
    ADD CONSTRAINT fkdb9e6f7191ecd533 FOREIGN KEY (id_contact) REFERENCES contact_sigrec(id);


--
-- TOC entry 2735 (class 2606 OID 143263)
-- Dependencies: 1928 1840 2535
-- Name: fkdb9e6f71a81b195e; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY assurance_sigrec
    ADD CONSTRAINT fkdb9e6f71a81b195e FOREIGN KEY (id_essai) REFERENCES essai_sigrec(id);


--
-- TOC entry 2799 (class 2606 OID 143624)
-- Dependencies: 1955 1928 2577
-- Name: fkddbf4e314614c469; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_sigrec
    ADD CONSTRAINT fkddbf4e314614c469 FOREIGN KEY (id_investigateurprincipal) REFERENCES investigateur_sigrec(id);


--
-- TOC entry 2798 (class 2606 OID 143619)
-- Dependencies: 2016 1928 2687
-- Name: fkddbf4e31d4112aed; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_sigrec
    ADD CONSTRAINT fkddbf4e31d4112aed FOREIGN KEY (id_promoteur) REFERENCES promoteur_sigrec(id);


--
-- TOC entry 2825 (class 2606 OID 143768)
-- Dependencies: 2609 1963 1968
-- Name: fkdfdef25e3e8f1c97; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvt_destruction
    ADD CONSTRAINT fkdfdef25e3e8f1c97 FOREIGN KEY (id) REFERENCES mvtstock(id);


--
-- TOC entry 2727 (class 2606 OID 143221)
-- Dependencies: 1846 1835 2413
-- Name: fke2c002cf5c631481; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY arc_investigateur_sigrec
    ADD CONSTRAINT fke2c002cf5c631481 FOREIGN KEY (id_centre) REFERENCES centre_sigrec(id);


--
-- TOC entry 2728 (class 2606 OID 143226)
-- Dependencies: 1852 2422 1835
-- Name: fke2c002cf91ecd533; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY arc_investigateur_sigrec
    ADD CONSTRAINT fke2c002cf91ecd533 FOREIGN KEY (id_contact) REFERENCES contact_sigrec(id);


--
-- TOC entry 2729 (class 2606 OID 143231)
-- Dependencies: 2535 1835 1928
-- Name: fke2c002cfa81b195e; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY arc_investigateur_sigrec
    ADD CONSTRAINT fke2c002cfa81b195e FOREIGN KEY (id_essai) REFERENCES essai_sigrec(id);


--
-- TOC entry 2843 (class 2606 OID 143871)
-- Dependencies: 1984 2542 1932
-- Name: fke55d5022cd45a413; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY pharmacie
    ADD CONSTRAINT fke55d5022cd45a413 FOREIGN KEY (id_etablissement) REFERENCES etablissement(id);


--
-- TOC entry 2770 (class 2606 OID 143466)
-- Dependencies: 2451 1881 1865
-- Name: fke7ea68af39fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_contacts
    ADD CONSTRAINT fke7ea68af39fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2859 (class 2606 OID 143962)
-- Dependencies: 2506 2000 1905
-- Name: fked8dcda9ba4507a4; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY produit
    ADD CONSTRAINT fked8dcda9ba4507a4 FOREIGN KEY (id_detailproduit) REFERENCES essai_detail_produit(id);


--
-- TOC entry 2862 (class 2606 OID 143980)
-- Dependencies: 1984 2632 2004
-- Name: fkef34b7c14de40194; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY produit_detail_stockage
    ADD CONSTRAINT fkef34b7c14de40194 FOREIGN KEY (id_pharmacie) REFERENCES pharmacie(id);


--
-- TOC entry 2863 (class 2606 OID 143985)
-- Dependencies: 2004 2002 2663
-- Name: fkef34b7c1a24a8716; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY produit_detail_stockage
    ADD CONSTRAINT fkef34b7c1a24a8716 FOREIGN KEY (id_detail_logistique) REFERENCES produit_detail_logistique(id);


--
-- TOC entry 2861 (class 2606 OID 143975)
-- Dependencies: 2004 2034 2722
-- Name: fkef34b7c1d78f7902; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY produit_detail_stockage
    ADD CONSTRAINT fkef34b7c1d78f7902 FOREIGN KEY (id_stockage) REFERENCES stockage(id);


--
-- TOC entry 2758 (class 2606 OID 143399)
-- Dependencies: 1863 1856 2428
-- Name: fkf50b7c271a1781c6; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY element_to_check
    ADD CONSTRAINT fkf50b7c271a1781c6 FOREIGN KEY (id_dispensation) REFERENCES dispensation(id);


--
-- TOC entry 2757 (class 2606 OID 143394)
-- Dependencies: 2675 1863 2008
-- Name: fkf50b7c276d6ee647; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY element_to_check
    ADD CONSTRAINT fkf50b7c276d6ee647 FOREIGN KEY (id_produitprescrit) REFERENCES produit_prescrit(id);


--
-- TOC entry 2760 (class 2606 OID 143409)
-- Dependencies: 2626 1980 1863
-- Name: fkf50b7c278800c21; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY element_to_check
    ADD CONSTRAINT fkf50b7c278800c21 FOREIGN KEY (id_personne) REFERENCES personne(id);


--
-- TOC entry 2759 (class 2606 OID 143404)
-- Dependencies: 1863 1974 2617
-- Name: fkf50b7c2797d4f410; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY element_to_check
    ADD CONSTRAINT fkf50b7c2797d4f410 FOREIGN KEY (id_ordonnancier) REFERENCES ordonnancier_fab_reconst(id);


--
-- TOC entry 2744 (class 2606 OID 143316)
-- Dependencies: 1854 2422 1852
-- Name: fkf5281d5e91ecd533; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY cro_sigrec
    ADD CONSTRAINT fkf5281d5e91ecd533 FOREIGN KEY (id_contact) REFERENCES contact_sigrec(id);


--
-- TOC entry 2745 (class 2606 OID 143321)
-- Dependencies: 2535 1854 1928
-- Name: fkf5281d5ea81b195e; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY cro_sigrec
    ADD CONSTRAINT fkf5281d5ea81b195e FOREIGN KEY (id_essai) REFERENCES essai_sigrec(id);


--
-- TOC entry 2785 (class 2606 OID 143547)
-- Dependencies: 1865 2451 1905
-- Name: fkf62049cd39fd10dc; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_produit
    ADD CONSTRAINT fkf62049cd39fd10dc FOREIGN KEY (id_essai) REFERENCES essai(id);


--
-- TOC entry 2733 (class 2606 OID 143252)
-- Dependencies: 2026 1838 2709
-- Name: fkfa113201cf65f51; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY arcinvestigateur_service
    ADD CONSTRAINT fkfa113201cf65f51 FOREIGN KEY (id_service) REFERENCES service(id);


--
-- TOC entry 2732 (class 2606 OID 143247)
-- Dependencies: 2626 1838 1980
-- Name: fkfa11320dde432bd; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY arcinvestigateur_service
    ADD CONSTRAINT fkfa11320dde432bd FOREIGN KEY (id_arcinvestigateur) REFERENCES personne(id);


--
-- TOC entry 2824 (class 2606 OID 143763)
-- Dependencies: 1984 1962 2632
-- Name: fkfd557b77382dd136; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvt_cession_pui
    ADD CONSTRAINT fkfd557b77382dd136 FOREIGN KEY (id_pharmaciedest) REFERENCES pharmacie(id);


--
-- TOC entry 2823 (class 2606 OID 143758)
-- Dependencies: 1968 2609 1962
-- Name: fkfd557b773e8f1c97; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY mvt_cession_pui
    ADD CONSTRAINT fkfd557b773e8f1c97 FOREIGN KEY (id) REFERENCES mvtstock(id);


--
-- TOC entry 2788 (class 2606 OID 143563)
-- Dependencies: 1911 2535 1928
-- Name: fkfff0213fa81b195e; Type: FK CONSTRAINT; Schema: public; Owner: eclipse
--

ALTER TABLE ONLY essai_detail_recherche_sigrec
    ADD CONSTRAINT fkfff0213fa81b195e FOREIGN KEY (id_essai) REFERENCES essai_sigrec(id);


--
-- TOC entry 2895 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO eclipse;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2011-05-24 14:18:51

--
-- PostgreSQL database dump complete
--


-- Initialisation des valeurs de séquences : démarrage à 100000 sur toutes les séquences
ALTER SEQUENCE arc_investigateur_sigrec_id_seq RESTART WITH 100000;
ALTER SEQUENCE arc_promoteur_sigrec_id_seq RESTART WITH 100000;
ALTER SEQUENCE assurance_sigrec_id_seq RESTART WITH 100000;
ALTER SEQUENCE centre_sigrec_id_seq RESTART WITH 100000;
ALTER SEQUENCE co_investigateur_sigrec_id_seq RESTART WITH 100000;
ALTER SEQUENCE contact_sigrec_id_seq RESTART WITH 100000;
ALTER SEQUENCE cro_sigrec_id_seq RESTART WITH 100000;
ALTER SEQUENCE pharmacie_id_seq RESTART WITH 100000;
ALTER SEQUENCE pharmacie_suivi_id_seq RESTART WITH 100000;
ALTER SEQUENCE promoteur_id_seq RESTART WITH 100000;
ALTER SEQUENCE promoteur_sigrec_id_seq RESTART WITH 100000;
ALTER SEQUENCE promoteur_suivi_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_commentaire_detail_recherche_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_commentaire_detail_faisabilite_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_commentaire_detail_administratif_archi_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_document_detail_administratif_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_detail_recherche_suivi_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_detail_recherche_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_detail_contacts_suivi_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_detail_contacts_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_detail_faisabilite_suivi_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_detail_faisabilite_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_detail_administratif_suivi_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_detail_administratif_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_detail_dates_suivi_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_detail_dates_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_detail_produit_suivi_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_detail_produit_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_detail_pharma_suivi_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_detail_pharma_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_detail_design_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_detail_design_suivi_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_detail_surcout_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_document_detail_surcout_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_detail_surcout_suivi_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_document_detail_autres_documents_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_detail_autres_documents_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_detail_autres_documents_suivi_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_document_detail_pharma_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_detail_recherche_sigrec_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_sigrec_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_suivi_id_seq RESTART WITH 100000;
ALTER SEQUENCE investigateur_sigrec_id_seq RESTART WITH 100000;
ALTER SEQUENCE personne_id_seq RESTART WITH 100000;
ALTER SEQUENCE personne_suivi_id_seq RESTART WITH 100000;
ALTER SEQUENCE produit_id_seq RESTART WITH 100000;
ALTER SEQUENCE produit_suivi_id_seq RESTART WITH 100000;
ALTER SEQUENCE produit_detail_logistique_id_seq RESTART WITH 100000;
ALTER SEQUENCE produit_document_actes_pharma_id_seq RESTART WITH 100000;
ALTER SEQUENCE conditionnement_id_seq RESTART WITH 100000;
ALTER SEQUENCE service_id_seq RESTART WITH 100000;
ALTER SEQUENCE service_suivi_id_seq RESTART WITH 100000;
ALTER SEQUENCE prevision_sigrec_id_seq RESTART WITH 100000;
ALTER SEQUENCE pole_suivi_id_seq RESTART WITH 100000;
ALTER SEQUENCE pole_id_seq RESTART WITH 100000;
ALTER SEQUENCE etablissement_suivi_id_seq RESTART WITH 100000;
ALTER SEQUENCE etablissement_id_seq RESTART WITH 100000;
ALTER SEQUENCE site_suivi_id_seq RESTART WITH 100000;
ALTER SEQUENCE site_id_seq RESTART WITH 100000;
ALTER SEQUENCE habilitation_id_seq RESTART WITH 100000;
ALTER SEQUENCE stockage_id_seq RESTART WITH 100000;
ALTER SEQUENCE mvtstock_id_seq RESTART WITH 100000;
ALTER SEQUENCE bras_id_seq RESTART WITH 100000;
ALTER SEQUENCE sequence_id_seq RESTART WITH 100000;
ALTER SEQUENCE prescription_type_id_seq RESTART WITH 100000;
ALTER SEQUENCE patient_suivi_id_seq RESTART WITH 100000;
ALTER SEQUENCE patient_id_seq RESTART WITH 100000;
ALTER SEQUENCE historique_patient_id_seq RESTART WITH 100000;
ALTER SEQUENCE prescription_id_seq RESTART WITH 100000;
ALTER SEQUENCE produit_prescrit_id_seq RESTART WITH 100000;
ALTER SEQUENCE dispensation_id_seq RESTART WITH 100000;
ALTER SEQUENCE mvtstock_document_id_seq RESTART WITH 100000;
ALTER SEQUENCE produit_detail_stockage_id_seq RESTART WITH 100000;
ALTER SEQUENCE dotation_id_seq RESTART WITH 100000;
ALTER SEQUENCE element_to_check_id_seq RESTART WITH 100000;
ALTER SEQUENCE evenement_id_seq RESTART WITH 100000;
ALTER SEQUENCE evenement_suivi_id_seq RESTART WITH 100000;
ALTER SEQUENCE inclusion_id_seq RESTART WITH 100000;
ALTER SEQUENCE essai_detail_etat_id_seq RESTART WITH 100000;
ALTER SEQUENCE categorie_id_seq RESTART WITH 100000;
ALTER SEQUENCE theme_id_seq RESTART WITH 100000;
ALTER SEQUENCE grille_modele_id_seq RESTART WITH 100000;
ALTER SEQUENCE grille_id_seq RESTART WITH 100000;
ALTER SEQUENCE item_id_seq RESTART WITH 100000;
ALTER SEQUENCE donnees_prevision_id_seq RESTART WITH 100000;
ALTER SEQUENCE regle_surcout_id_seq RESTART WITH 100000;
ALTER SEQUENCE ordonnancier_dispensation_id_seq RESTART WITH 100000;
ALTER SEQUENCE ordonnancier_fab_reconst_id_seq RESTART WITH 100000;
ALTER SEQUENCE incident_id_seq RESTART WITH 100000;
ALTER SEQUENCE incident_suivi_id_seq RESTART WITH 100000;
ALTER SEQUENCE retour_patient_id_seq RESTART WITH 100000;


-- Table Etablissement
INSERT INTO etablissement(id, nom, telephone, fax, mail, adresseDirection, codePostal, ville, pays) VALUES (1, 'Etablissement 1', '', '', '', '', '', '', '');

-- Table Site
INSERT INTO site(id, code, nom, adresse, codePostal, ville, id_etablissement) VALUES (1, 'Site 1', 'Site 1', '', '', '', 1);

-- Table Pole
INSERT INTO pole(id, nom, id_etablissement) VALUES (1, 'Pole 1', 1);

-- Table Service
INSERT INTO service(id, nom, id_pole) VALUES (1, 'Service 1', 1);

-- Table Promoteur
INSERT INTO promoteur(id, raisonsociale, identifiant, type) VALUES (1, 'Promoteur 1', '', 'INDUSTRIEL');

-- Table Pharmacie
INSERT INTO pharmacie(id, nom, adresse, adresseLivraison, telephone, fax, responsablePrincipal, numOrdonnancierDisp, numOrdonnancierFab, id_etablissement) VALUES (1, 'Pharmacie 1', '', '', '', '', '', 0, 0, 1);

-- Table Pharmacie-Site
INSERT INTO pharmacie_site(id_pharmacie, id_site) VALUES (1, 1);

-- Table Personne
INSERT INTO personne(id, type, typePharmacien, login, nom, prenom, telephone, mail, fax, adresse, codePostal, ville, isAdmin, titre, nomSociete, id_promoteur, password) VALUES (1, 'PHARMACIEN', 'TITULAIRE', 'admin', 'admin', 'admin', '', '', '', '', '', '', true, null, '', null, 'ea617e2de44cac984883b76bd81092b6');
INSERT INTO personne(id, type, typePharmacien, login, nom, prenom, telephone, mail, fax, adresse, codePostal, ville, isAdmin, titre, nomSociete, id_promoteur, password) VALUES (2, 'PHARMACIEN', 'TITULAIRE', 'pharma1', 'Pharmacien 1', 'Pharmacien 1', '', '', '', '', '', '', true, null, '', null, 'ea617e2de44cac984883b76bd81092b6');
INSERT INTO personne(id, type, typePharmacien, login, nom, prenom, telephone, mail, fax, adresse, codePostal, ville, isAdmin, titre, nomSociete, id_promoteur, password) VALUES (3, 'PHARMACIEN', 'TITULAIRE', 'pharma2', 'Pharmacien 2', 'Pharmacien 2', '', '', '', '', '', '', true, null, '', null, 'ea617e2de44cac984883b76bd81092b6');