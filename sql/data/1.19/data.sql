--
-- PostgreSQL database dump
--

-- Started on 2011-11-02 10:33:50

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;


SELECT pg_catalog.setval('etablissement_id_seq', 100004, true);


INSERT INTO etablissement (id, adressedirection, codepostal, fax, mail, nom, pays, telephone, ville) VALUES (1, '', '44000', '', '', 'CHU Nantes', 'France', '', 'Nantes');
INSERT INTO etablissement (id, adressedirection, codepostal, fax, mail, nom, pays, telephone, ville) VALUES (100003, 'Boulevard Jacques Monod
', '44805 ', '', '', 'Institut de Cancérologie de l’Ouest - René Gauducheau', '', '02 40 67 99 00', 'Saint - Herblain');
INSERT INTO etablissement (id, adressedirection, codepostal, fax, mail, nom, pays, telephone, ville) VALUES (100004, '', '', '', '', 'CHU Strasbourg', '', '', '');

--
-- TOC entry 2329 (class 0 OID 0)
-- Dependencies: 1946
-- Name: etablissement_suivi_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('etablissement_suivi_id_seq', 100001, true);

INSERT INTO etablissement_suivi (id, datemaj, majpar, id_etablissement) VALUES (100000, '2011-03-25 11:35:39.45', 'muriel.courant', 100003);
INSERT INTO etablissement_suivi (id, datemaj, majpar, id_etablissement) VALUES (100001, '2011-06-24 15:56:58.59', 'muriel.courant', 100004);

SELECT pg_catalog.setval('site_id_seq', 100003, true);


--
-- TOC entry 2326 (class 0 OID 53333)
-- Dependencies: 2041
-- Data for Name: site; Type: TABLE DATA; Schema: public; Owner: eclipse
--

INSERT INTO site (id, adresse, code, codepostal, nom, ville, id_etablissement) VALUES (2, '', 'HGRL', '', 'Hôpital Guillaume/René Laennec', '', 1);
INSERT INTO site (id, adresse, code, codepostal, nom, ville, id_etablissement) VALUES (3, '', 'HSJ', '', 'Hôpital Saint-Jacques', '', 1);
INSERT INTO site (id, adresse, code, codepostal, nom, ville, id_etablissement) VALUES (1, '', 'HD/HME', '', 'Hôpital Hôtel-Dieu/Mère Enfant', '', 1);
INSERT INTO site (id, adresse, code, codepostal, nom, ville, id_etablissement) VALUES (100003, '', 'STRASBOURG - Site Est', '', 'SITE DE l''EST', '', 100004);

--

SELECT pg_catalog.setval('site_suivi_id_seq', 100003, true);


--
-- TOC entry 2326 (class 0 OID 53341)
-- Dependencies: 2043
-- Data for Name: site_suivi; Type: TABLE DATA; Schema: public; Owner: eclipse
--

INSERT INTO site_suivi (id, datemaj, majpar, id_site) VALUES (100000, '2011-01-14 14:39:04.253', 'admin', 2);
INSERT INTO site_suivi (id, datemaj, majpar, id_site) VALUES (100001, '2011-01-14 14:39:12.57', 'admin', 3);
INSERT INTO site_suivi (id, datemaj, majpar, id_site) VALUES (100002, '2011-01-14 14:39:28.529', 'admin', 1);
INSERT INTO site_suivi (id, datemaj, majpar, id_site) VALUES (100003, '2011-06-24 15:58:54.667', 'muriel.courant', 100003);


--

SELECT pg_catalog.setval('pole_id_seq', 100003, true);


--
-- TOC entry 2326 (class 0 OID 53196)
-- Dependencies: 2001
-- Data for Name: pole; Type: TABLE DATA; Schema: public; Owner: eclipse
--

INSERT INTO pole (id, nom, id_etablissement) VALUES (1, 'IMAD-Institut des maladies de l''appareil digestif', 1);
INSERT INTO pole (id, nom, id_etablissement) VALUES (3, 'Institut du Thorax', 1);
INSERT INTO pole (id, nom, id_etablissement) VALUES (15, 'Recherche-CIC', 1);
INSERT INTO pole (id, nom, id_etablissement) VALUES (2, 'Institut de Transplantations, Urologie, Néphrologie', 1);
INSERT INTO pole (id, nom, id_etablissement) VALUES (4, 'Pôle Anesthésie-Réanimations', 1);
INSERT INTO pole (id, nom, id_etablissement) VALUES (5, 'Pôle Gérontologie Clinique', 1);
INSERT INTO pole (id, nom, id_etablissement) VALUES (6, 'Pôle Imageries & Explorations Fonctionnelles', 1);
INSERT INTO pole (id, nom, id_etablissement) VALUES (7, 'Pôle Médecine Cancérologie Hématologie', 1);
INSERT INTO pole (id, nom, id_etablissement) VALUES (8, 'Pôle Medecine Physique & Réadaptation/Soins de suite', 1);
INSERT INTO pole (id, nom, id_etablissement) VALUES (9, 'Pôle Mère Enfant', 1);
INSERT INTO pole (id, nom, id_etablissement) VALUES (10, 'Pôle Neurosciences', 1);
INSERT INTO pole (id, nom, id_etablissement) VALUES (11, 'Pôle Ostéo-Articulaire', 1);
INSERT INTO pole (id, nom, id_etablissement) VALUES (12, 'Pôle Psychiatries', 1);
INSERT INTO pole (id, nom, id_etablissement) VALUES (13, 'Pôle Tête & Cou', 1);
INSERT INTO pole (id, nom, id_etablissement) VALUES (14, 'Pôle Urgences', 1);
INSERT INTO pole (id, nom, id_etablissement) VALUES (100003, 'Pôle Pharmacie-Stérilisation', 1);

SELECT pg_catalog.setval('pole_suivi_id_seq', 100000, true);


--
-- TOC entry 2326 (class 0 OID 53201)
-- Dependencies: 2003
-- Data for Name: pole_suivi; Type: TABLE DATA; Schema: public; Owner: eclipse
--

INSERT INTO pole_suivi (id, datemaj, majpar, id_pole) VALUES (100000, '2011-01-19 15:20:00.682', 'muriel.courant', 100003);


--

SELECT pg_catalog.setval('service_id_seq', 100002, true);


--
-- TOC entry 2327 (class 0 OID 53323)
-- Dependencies: 2037
-- Data for Name: service; Type: TABLE DATA; Schema: public; Owner: eclipse
--

INSERT INTO service (id, nom, id_pole, id_site) VALUES (1, 'GASTRO-ENTEROLOGIE-HEPATOLOGIE', 1, NULL);
INSERT INTO service (id, nom, id_pole, id_site) VALUES (2, 'HEMATOLOGIE', 7, NULL);
INSERT INTO service (id, nom, id_pole, id_site) VALUES (4, 'DERMATOLOGIE', 7, NULL);
INSERT INTO service (id, nom, id_pole, id_site) VALUES (5, 'MEDECINE INTERNE', 7, NULL);
INSERT INTO service (id, nom, id_pole, id_site) VALUES (6, 'CIC GASTRO-ENTEROLOGIE /NUTRITION', 15, NULL);
INSERT INTO service (id, nom, id_pole, id_site) VALUES (7, 'ANESTHESIE HD/JEAN MONNET/HME', 4, NULL);
INSERT INTO service (id, nom, id_pole, id_site) VALUES (9, 'MALADIES INFECTIEUSES ET TROPICALES', 7, NULL);
INSERT INTO service (id, nom, id_pole, id_site) VALUES (10, 'NEPHROLOGIE', 2, NULL);
INSERT INTO service (id, nom, id_pole, id_site) VALUES (11, 'OPHTALMOLOGIE', 13, NULL);
INSERT INTO service (id, nom, id_pole, id_site) VALUES (12, 'PEDIATRIE', 9, NULL);
INSERT INTO service (id, nom, id_pole, id_site) VALUES (13, 'RHUMATOLOGIE', 11, NULL);
INSERT INTO service (id, nom, id_pole, id_site) VALUES (14, 'UROLOGIE', 2, NULL);
INSERT INTO service (id, nom, id_pole, id_site) VALUES (15, 'URGENCES', 14, NULL);
INSERT INTO service (id, nom, id_pole, id_site) VALUES (17, 'PNEUMOLOGIE', 3, NULL);
INSERT INTO service (id, nom, id_pole, id_site) VALUES (18, 'NEUROLOGIE', 10, NULL);
INSERT INTO service (id, nom, id_pole, id_site) VALUES (19, 'EXPLORATIONS FONCTIONNELLES', 6, NULL);
INSERT INTO service (id, nom, id_pole, id_site) VALUES (20, 'ENDOCRINOLOGIE', 3, NULL);
INSERT INTO service (id, nom, id_pole, id_site) VALUES (21, 'MEDECINE AIGUE GERIATRIQUE', 5, NULL);
INSERT INTO service (id, nom, id_pole, id_site) VALUES (22, 'PSYCHIATRIE 1', 12, NULL);
INSERT INTO service (id, nom, id_pole, id_site) VALUES (23, 'MEDECINE DU SPORT ET DE L''EFFORT', 8, NULL);
INSERT INTO service (id, nom, id_pole, id_site) VALUES (16, 'CTCV Réanimation Chirurgie Thoracique Cardiaque et Vasculaire', 4, NULL);
INSERT INTO service (id, nom, id_pole, id_site) VALUES (8, 'GYNECOLOGIE & OBSTETRIQUE', 9, 1);
INSERT INTO service (id, nom, id_pole, id_site) VALUES (3, 'ONCOLOGIE PEDIATRIQUE', 7, 1);


SELECT pg_catalog.setval('service_suivi_id_seq', 100001, true);


--
-- TOC entry 2326 (class 0 OID 53328)
-- Dependencies: 2039
-- Data for Name: service_suivi; Type: TABLE DATA; Schema: public; Owner: eclipse
--

INSERT INTO service_suivi (id, datemaj, majpar, id_service) VALUES (100000, '2011-05-31 12:05:08.534', 'muriel.courant', 8);
INSERT INTO service_suivi (id, datemaj, majpar, id_service) VALUES (100001, '2011-05-31 12:05:41.622', 'muriel.courant', 3);

--
-- TOC entry 2331 (class 0 OID 0)
-- Dependencies: 1996
-- Name: pharmacie_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eclipse
--

SELECT pg_catalog.setval('pharmacie_id_seq', 100021, true);


--
-- TOC entry 2328 (class 0 OID 53175)
-- Dependencies: 1995
-- Data for Name: pharmacie; Type: TABLE DATA; Schema: public; Owner: eclipse
--

INSERT INTO pharmacie (id, adresse, adresselivraison, fax, nom, responsableprincipal, telephone, id_etablissement, numordonnancierdisp, numordonnancierfab) VALUES (2, '', '', '', 'PUI HOTEL-DIEU UPCO', '', '', 1, 0, 0);
INSERT INTO pharmacie (id, adresse, adresselivraison, fax, nom, responsableprincipal, telephone, id_etablissement, numordonnancierdisp, numordonnancierfab) VALUES (3, '', '', '', 'PUI HGRL', '', '', 1, 0, 0);
INSERT INTO pharmacie (id, adresse, adresselivraison, fax, nom, responsableprincipal, telephone, id_etablissement, numordonnancierdisp, numordonnancierfab) VALUES (4, '', '', '', 'PUI St JACQUES', '', '', 1, 0, 0);
INSERT INTO pharmacie (id, adresse, adresselivraison, fax, nom, responsableprincipal, telephone, id_etablissement, numordonnancierdisp, numordonnancierfab) VALUES (5, '', '', '', 'ARSENAL St JACQUES', '', '', 1, 0, 0);
INSERT INTO pharmacie (id, adresse, adresselivraison, fax, nom, responsableprincipal, telephone, id_etablissement, numordonnancierdisp, numordonnancierfab) VALUES (1, '1, place Alexis RICORDEAU
44093 NANTES Cedex 01', 'Quai MONCOUSU
44093 NANTES Cedex 01', '02 40 08 41 63', 'PUI HOTEL-DIEU RDJ', 'Mme ROUILLER-FURIC', '02 40 08 41 54', 1, 61, 13);

INSERT INTO pharmacie_site (id_pharmacie, id_site) VALUES (1, 1);
INSERT INTO pharmacie_site (id_pharmacie, id_site) VALUES (2, 1);
INSERT INTO pharmacie_site (id_pharmacie, id_site) VALUES (3, 2);
INSERT INTO pharmacie_site (id_pharmacie, id_site) VALUES (4, 3);
INSERT INTO pharmacie_site (id_pharmacie, id_site) VALUES (5, 3);




SELECT pg_catalog.setval('pharmacie_suivi_id_seq', 100101, true);





SELECT pg_catalog.setval('personne_id_seq', 100000, true);
-- Utilisateurs (password: eclipse*)
INSERT INTO personne (type, id, adresse, codepostal, fax, isadmin, login, mail, nom, password, prenom, telephone, ville, nomsociete, titre, typepharmacien, id_promoteur, telephoneportable, datearriveeservice, datedepartservice, datevalidationformation) VALUES ('PHARMACIEN', 1, '', '', '', true, 'administrateur', '', 'administrateur', 'ea617e2de44cac984883b76bd81092b6', 'administrateur', '', '', '', NULL, 'TITULAIRE', NULL, NULL, NULL, NULL, NULL);

