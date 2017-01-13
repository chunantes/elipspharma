/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pharma.eclipse.externe;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.dictionary.maker.common.utils.CriteriaMakerUtils;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.enums.NatureRecherche;
import fr.pharma.eclipse.domain.enums.ObjetRecherche;
import fr.pharma.eclipse.domain.enums.PhaseRecherche;
import fr.pharma.eclipse.domain.enums.Thematique;
import fr.pharma.eclipse.domain.enums.TypePromoteur;
import fr.pharma.eclipse.domain.enums.TypeRecherche;
import fr.pharma.eclipse.domain.enums.UniteTempsPrevision;
import fr.pharma.eclipse.domain.model.sigrec.acteur.ContactSigrec;
import fr.pharma.eclipse.domain.model.sigrec.acteur.InvestigateurSigrec;
import fr.pharma.eclipse.domain.model.sigrec.acteur.PromoteurSigrec;
import fr.pharma.eclipse.domain.model.sigrec.essai.detail.AssuranceSigrec;
import fr.pharma.eclipse.domain.model.sigrec.essai.detail.DetailRechercheSigrec;
import fr.pharma.eclipse.domain.model.sigrec.essai.detail.PrevisionSigrec;
import fr.pharma.eclipse.exception.TechnicalException;
import fr.pharma.eclipse.utils.Utils;

/**
 *
 * @author sgl
 */
@SuppressWarnings("InitializerMayBeStatic")
public class EssaiExterneDao implements GenericDao<EssaiExterne>, Serializable {

    Logger log = LoggerFactory.getLogger(EssaiExterneDao.class);

    private static final String selectSQL = "select id, statut, numInterne, codePromoteur,nomUsuel, promoteur, typePromoteur, "
            + "service, titreProtocole, typeRecherche, objetRecherche, phaseRecherche, "
            + "natureRecherche, thematique, investigateur_nom,investigateur_prenom , "
            + "datePrevDebEtude, datePrevFinEtude , datePrevFinInclusion, autoriteComp,"
            + "nomAutoriteComp, dateAccordAC, eudract, cpp, nomCpp, dateAutorisationCpp, conventionSignee, dateSignatureConv,"
            + "nomCompagnieAssurance, numContratAssurance, nomAvenantAssurance, nbPatientPrevuLocal, dureeTotPrevue, uniteDureeTotPrevue,"
            + "nbCentrePrevu, nbPatientPrevuTotal, numCentre from essais_externe";

    private JdbcTemplate jdbcTemplate;

    public EssaiExterneDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private void sqlSearchFullText(StringBuilder sb, String field, String value) {
        sb.append(" upper(").append(field).append(") similar to '");
        sb.append(CriteriaMakerUtils.preparerValuePourSQLLike(value));
        sb.append("' ");
    }

    ResultSetExtractor<List<EssaiExterne>> extractorList = new ResultSetExtractor<List<EssaiExterne>>() {

        @Override
        public List<EssaiExterne> extractData(ResultSet rs) throws SQLException, DataAccessException {
            List<EssaiExterne> essaiSigrecs = new ArrayList<EssaiExterne>();
            while (rs.next()) {
                essaiSigrecs.add(extractEssai(rs));
            }
            return essaiSigrecs;
        }

    };

    ResultSetExtractor<EssaiExterne> extractor = new ResultSetExtractor<EssaiExterne>() {

        @Override
        public EssaiExterne extractData(ResultSet rs) throws SQLException, DataAccessException {

            if (rs.next()) {
                EssaiExterne essaiSigrec = extractEssai(rs);
                if (rs.next()) {
                    throw new TechnicalException("Ne peut retourner qu'une seul valeur");
                }
                return essaiSigrec;
            } else {
                return null;
            }
        }

    };

    private EssaiExterne extractEssai(ResultSet rs) throws SQLException {
        EssaiExterne e = new EssaiExterne();
        e.setId(rs.getLong(1));
        e.setDetailRecherche(new DetailRechercheSigrec());
        e.getDetailRecherche().setNumEnregistrement(rs.getString(3));
        e.setCodePromoteur(rs.getString(4));
        e.setNom(rs.getString(5));

        PromoteurSigrec promoteur = new PromoteurSigrec();
        promoteur.setContact(new ContactSigrec());
        promoteur.getContact().setRaisonSociale(rs.getString(6));
        promoteur.setType(getEnumValue(TypePromoteur.class, rs.getString(7)));
        e.setPromoteur(promoteur);

        e.setService(rs.getString(8));
        
        e.getDetailRecherche().setTitreProtocole(rs.getString(9));
        e.getDetailRecherche().setTypeRecherche(getEnumValue(TypeRecherche.class, rs.getString(10)));
        e.getDetailRecherche().setObjetRecherche(getEnumValue(ObjetRecherche.class, rs.getString(11)));
        e.getDetailRecherche().setPhaseRecherche(getEnumValue(PhaseRecherche.class, rs.getString(12)));
        e.getDetailRecherche().setNatureRecherche(getEnumValue(NatureRecherche.class, rs.getString(13)));
        
        e.setThematique(getEnumValue(Thematique.class, rs.getString(14)));

        if (StringUtils.isNotBlank(rs.getString(15)) || StringUtils.isNotBlank(rs.getString(16))) {
	        InvestigateurSigrec invest = new InvestigateurSigrec();
	        invest.setContact(new ContactSigrec());
	        invest.getContact().setNom(rs.getString(15));
	        invest.getContact().setPrenom(rs.getString(16));
	        e.setInvestigateurPrincipal(invest);
        }
        
        e.setPrevision(new PrevisionSigrec());
        e.getPrevision().setDateDebut(convertDateToCalendar(rs.getDate(17)));
        e.getPrevision().setDateFin(convertDateToCalendar(rs.getDate(18)));

        e.setDatePrevFinInclusion(convertDateToCalendar(rs.getDate(19)));
        e.setPresenceAC(getBoolean(rs,20));
        e.setNomAC(rs.getString(21));
        e.setDateAccordAC(convertDateToCalendar(rs.getDate(22)));
        e.setNumEudract(rs.getString(23));
        e.setPresenceCPP(getBoolean(rs, 24));
        e.setNomCPP(rs.getString(25));
        e.setDateAccordCPP(convertDateToCalendar(rs.getDate(26)));
        e.setConventionSigne(getBoolean(rs, 27));
        e.setDateConventionSigne(convertDateToCalendar(rs.getDate(28)));

        AssuranceSigrec assurance = new AssuranceSigrec();
        assurance.setContact(new ContactSigrec());
        assurance.getContact().setNom(rs.getString(29));
        assurance.setNumeroContrat(rs.getString(30));
        e.getAssurances().add(assurance);
        e.setNumAvenantAssurance(rs.getString(31));

        e.setNbPatientPrevLocal(rs.getInt(32));
        e.setDureeTotalPrev(rs.getBigDecimal(33));
        e.setUniteeDureeTotalPrev(getEnumValue(UniteTempsPrevision.class, rs.getString(34)));

        e.getPrevision().setNbCentres(rs.getInt(35));
        e.setNbPatientPrevusTotal(rs.getInt(36));
        e.setNumCentre(rs.getString(37));
        return e;
    }
    
    private static Boolean getBoolean(ResultSet rs, int id) throws SQLException {
    	final boolean bool = rs.getBoolean(id);
    	final Boolean retour;
    	if (rs.wasNull()) {
    		retour = null;
    	} else {
    		retour = Boolean.valueOf(bool);
    	}
    	return retour;
    }

    private StringBuilder buildSQLCriteria(SearchCriteria criteria) {
        StringBuilder queryBuilder = new StringBuilder(selectSQL);
        EssaiExterneSearchCriteria crit = (EssaiExterneSearchCriteria) criteria;
        boolean critApp = false;

        queryBuilder.append(" where ");

        // recherche par nom usuel - code promoteur - nom promoteur
        if (crit.getNom() != null && !crit.getNom().isEmpty()) {
            queryBuilder.append("(");
            sqlSearchFullText(queryBuilder, "nomUsuel", StringEscapeUtils.escapeSql(Utils.escapingSpecialChar(crit.getNom())));
            queryBuilder.append(" or ");
            sqlSearchFullText(queryBuilder, "codePromoteur", StringEscapeUtils.escapeSql(Utils.escapingSpecialChar(crit.getNom())));
            queryBuilder.append(" or ");
            sqlSearchFullText(queryBuilder, "promoteur", StringEscapeUtils.escapeSql(Utils.escapingSpecialChar(crit.getNom())));
            queryBuilder.append(")");
            critApp = true;
        }

        //recherche par numInterne
        if (crit.getNumInterne() != null && !crit.getNumInterne().isEmpty()) {
            if (critApp) {
                queryBuilder.append(" and");
            }
            sqlSearchFullText(queryBuilder, "numInterne", StringEscapeUtils.escapeSql(Utils.escapingSpecialChar(crit.getNumInterne())));
            critApp = true;
        }

        //recherche par service
        if (crit.getService() != null) {
            if (critApp) {
                queryBuilder.append(" and");
            }
            sqlSearchFullText(queryBuilder, "service", StringEscapeUtils.escapeSql(Utils.escapingSpecialChar(crit.getService().getNom())));
            critApp = true;
        }

        // recherche par titre protocole
        if (crit.getTitreProtocole() != null && !crit.getTitreProtocole().isEmpty()) {
            if (critApp) {
                queryBuilder.append(" and");
            }
            sqlSearchFullText(queryBuilder, "titreProtocole", StringEscapeUtils.escapeSql(Utils.escapingSpecialChar(crit.getTitreProtocole())));
            critApp = true;
        }
        if (!critApp) {
            queryBuilder.delete(queryBuilder.length() - 7, queryBuilder.length());
        }
        return queryBuilder;
    }

    @Override
    public List<EssaiExterne> getAll(SearchCriteria criteria) {

        return jdbcTemplate.query(buildSQLCriteria(criteria).toString(), extractorList);
    }

    @Override
    public List<EssaiExterne> getAll(SearchCriteria criteria, int maxResults) {
        StringBuilder query = buildSQLCriteria(criteria);

        // @TODO ajout du critère maxResults
        //return jdbcTemplate.query(query.toString(), extractorList);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EssaiExterne> getAll() {
        return jdbcTemplate.query(selectSQL, extractorList);
    }

    private Calendar convertDateToCalendar(Date date) {
        if (date == null) {
            return null;
        } else {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            return cal;
        }
    }

    @Override
    public EssaiExterne get(Long id) {
        StringBuilder queryBuilder = new StringBuilder(selectSQL);
        queryBuilder.append(" where id=");
        queryBuilder.append(String.valueOf(id));
        return jdbcTemplate.query(queryBuilder.toString(), extractor);
    }

    @Override
    public Long count(SearchCriteria criteria) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EssaiExterne save(EssaiExterne object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(EssaiExterne object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EssaiExterne> executeSQLQuery(String sql, Object[] params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<?> executeSQLQuery(String sql, Object[] params, String[] columns, Class<?> classResult) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<?> executeSQLQuery(String sql, Object[] params, String[] columns, Class<?> classResult, Map<String, Object> scalarType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Object[]> executeSQLQueryTabObject(String sql, Object[] params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long count() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dettach(EssaiExterne bean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EssaiExterne reattach(EssaiExterne bean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static <T extends Enum<T>> T getEnumValue(Class<T> enumType, String valeur) {
        Logger log = LoggerFactory.getLogger(EssaiExterneDao.class);
        if (valeur != null) {
            try {
                return Enum.valueOf(enumType, valeur);
                
            } catch (IllegalArgumentException ex) {
                log.warn("Valeur {} non valide {}",enumType.getName(), valeur);
            }
        }
        return null;
    }    
}
