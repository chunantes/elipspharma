ALTER TABLE essai_detail_faisabilite DROP COLUMN concl_dateaccord;
ALTER TABLE essai_detail_pharma ADD COLUMN numeroCentre character varying(255);
ALTER TABLE essai_detail_dates ADD COLUMN dateReception timestamp without time zone;