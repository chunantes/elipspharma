package fr.pharma.eclipse.domain.enums;

/**
 * Enumération des différentes thématiques d'un essai.
 
 * @version $Revision$ $Date$
 */
public enum Thematique
{
    /**
     * Anatomie.
     */
    ANATOMIE("42-01", "Anatomie"),

    /**
     * Anatomie et cytologie pathologiques.
     */
    ANATOMIE_CYTOLOGIE("42-03", "Anatomie et cytologie pathologiques"),

    /**
     * Anesthésiologie et réanimation chirurgicale.
     */
    ANESTHESIOLOGIE("48-01", "Anesthésiologie et réanimation chirurgicale"),

    /**
     * Bactériologie - virologie - hygiène hospitalière.
     */
    BACTERIOLOGIE("45-01", "Bactériologie - virologie - hygiène hospitalière"),

    /**
     * Biochimie et biologie moléculaire.
     */
    BIOCHIMIE("44-01", "Biochimie et biologie moléculaire"),

    /**
     * Biologie cellulaire.
     */
    BIOLOGIE_CELLULAIRE("42-03", "Biologie cellulaire"),

    /**
     * Biologie et médecine du développement et de la reproduction.
     */
    BIOLOGIE("54-05", "Biologie et médecine du développement et de la reproduction"),

    /**
     * Biophysique et médecine nucléaire.
     */
    BIOPHYSIQUE("43-01", "Biophysique et médecine nucléaire"),

    /**
     * Biostatistiques, informatique médicale et technologies de communication.
     */
    BIOSTATISTIQUE("46-04",
            "Biostatistiques, informatique médicale et technologies de communication"),

    /**
     * Cancérologie – radiothérapie.
     */
    CANCEROLOGIE("47-02", "Cancérologie – radiothérapie"),

    /**
     * Cardiologie.
     */
    CARDIOLOGIE("51-02", "Cardiologie"),

    /**
     * Chirurgie buccale, pathologie et thérapeutique, anesthésiologie et réanimation.
     */
    CHIRURGIE_BUCCALE("57-02",
            "Chirurgie buccale, pathologie et thérapeutique, anesthésiologie et réanimation"),

    /**
     * Chirurgie digestive.
     */
    CHIRURGIE_DIGESTIVE("52-02", "Chirurgie digestive"),
    /**
     * Chirurgie générale.
     */
    CHIRURGIE_GENERALE("53-02", "Chirurgie générale"),

    /**
     * Chirurgie infantile.
     */
    CHIRURGIE_INFANTILE("54-02", "Chirurgie infantile"),

    /**
     * Chirurgie maxillo-faciale et stomatologie.
     */
    STOMATOLOGIE("55-03", "Chirurgie maxillo-faciale et stomatologie"),

    /**
     * Chirurgie orthopédique et traumatologique.
     */
    CHIRURGIE_ORTHOPEDIQUE("50-02", "Chirurgie orthopédique et traumatologique"),

    /**
     * Chirurgie plastique, reconstructrice et esthétique – brûlologie.
     */
    CHIRURGIE_PLASTIQUE("50-04",
            "Chirurgie plastique, reconstructrice et esthétique – brûlologie"),

    /**
     * Chirurgie thoracique et cardiovasculaire.
     */
    CHIRURGIE_THORACIQUE("51-03", "Chirurgie thoracique et cardiovasculaire"),

    /**
     * Chirurgie vasculaire - médecine vasculaire.
     */
    CHIRURGIE_VASCULAIRE("51-04", "Chirurgie vasculaire - médecine vasculaire"),

    /**
     * Cytologie et histologie.
     */
    CYTOLOGIE_HISTOLOGIE("42-02", "Cytologie et histologie"),

    /**
     * Dermato-vénéréologie.
     */
    DERMATO_VENEROLOGIE("50-03", "Dermato-vénéréologie"),

    /**
     * Endocrinologie, diabète et maladies métaboliques.
     */
    ENDOCTRINOLOGIE("54-04", "Endocrinologie, diabète et maladies métaboliques"),

    /**
     * Epidémiologie, économie de la santé et prévention.
     */
    EPIDEMIOLOGIE("46-01", "Epidémiologie, économie de la santé et prévention"),

    /**
     * Gastroentérologie – hépatologie.
     */
    GASTROENTEROLOGIE("52-01", "Gastroentérologie – hépatologie"),

    /**
     * Génétique.
     */
    GENETIQUE("47-04", "Génétique"),

    /**
     * Gynécologie-obstétrique -gynécologie médicale.
     */
    GYNECOLOGIE("54-03", "Gynécologie-obstétrique -gynécologie médicale"),

    /**
     * Hématologie – transfusion.
     */
    HEMATOLOGIE("47-01", "Hématologie – transfusion"),

    /**
     * Immunologie.
     */
    IMMUNOLOGIE("47-03", "Immunologie"),

    /**
     * Maladies infectieuses - maladies tropicales.
     */
    MALADIE_INFECTIEUSE("45-03", "Maladies infectieuses - maladies tropicales"),

    /**
     * Médecine et santé au travail.
     */
    MEDECINE_TRAVAIL("46-02", "Médecine et santé au travail"),

    /**
     * Médecine interne - gériatrie et biologie du vieillissement.
     */
    MEDECINE_INTERNE("53-01", "Médecine interne - gériatrie et biologie du vieillissement"),

    /**
     * Médecine légale et droit de la santé.
     */
    MEDECINE_LEGALE("46-03", "Médecine légale et droit de la santé"),

    /**
     * Médecine physique et de réadaptation.
     */
    MEDECINE_PHYSIQUE("49-05", "Médecine physique et de réadaptation"),

    /**
     * Néphrologie.
     */
    NEPHROLOGIE("52-03", "Néphrologie"),

    /**
     * Neurochirurgie.
     */
    NEUROCHIRURGIE("49-02", "Neurochirurgie"),

    /**
     * Neurologie.
     */
    NEUROLOGIE("49-01", "Neurologie"),

    /**
     * Nutrition.
     */
    NUTRITION("42-04", "Nutrition"),

    /**
     * Odontologie conservatrice, endodontie.
     */
    ODONTOLOGIE("58-01", "Odontologie conservatrice, endodontie"),

    /**
     * Ophtalmologie.
     */
    OPHTALMOLOGIE("55-02", "Ophtalmologie"),

    /**
     * Orthopédie dento-faciale.
     */
    ORTHOPEDIE_DENTO("56-02", "Orthopédie dento-faciale"),

    /**
     * Oto-rhino-laryngologie.
     */
    OTHO_RHINO("55-01", "Oto-rhino-laryngologie"),

    /**
     * Parasitologie et mycologie.
     */
    PARASITOLOGIE("45-02", "Parasitologie et mycologie"),

    /**
     * Parodontologie.
     */
    PARADONTOLOGIE("57-01", "Parodontologie"),

    /**
     * Pédiatrie.
     */
    PEDIATRIE("54-01", "Pédiatrie"),

    /**
     * Pédodontie.
     */
    PEDODONTIE("56-01", "Pédodontie"),

    /**
     * Pédopsychiatrie.
     */
    PEDOPSYCHIATRIE("49-04", "Pédopsychiatrie"),

    /**
     * Pharmacologie fondamentale - Pharmacologie clinique.
     */
    PHARMACOLOGIE("48-03", "Pharmacologie fondamentale - Pharmacologie clinique"),

    /**
     * Physiologie.
     */
    PHYSIOLOGIE("44-02", "Physiologie"),

    /**
     * Pneumologie.
     */
    PNEUMOLOGIE("51-01", "Pneumologie"),

    /**
     * Prévention, épidémiologie, économie de la santé, odontologie légale.
     */
    PREVENTION("56-03", "Prévention, épidémiologie, économie de la santé, odontologie légale"),

    /**
     * Prothèses.
     */
    PROTHESE("58-02", "Prothèses"),

    /**
     * Psychiatrie d'adultes.
     */
    PSYCHIATRIE("49-03", "Psychiatrie d'adultes"),

    /**
     * Radiologie et imagerie médicale.
     */
    RADIOLOGIE("43-02", "Radiologie et imagerie médicale"),

    /**
     * Réanimation médicale.
     */
    REANIMATION("48-02", "Réanimation médicale"),

    /**
     * Rhumatologie.
     */
    RHUMATOLOGIE("50-01", "Rhumatologie"),

    /**
     * Sciences anatomiques et physiologiques, occlusodontiques, biomatériaux, biophysique,
     * radiologie.
     */
    SCIENCES_ANATOMIQUES("58-03",
            "Sciences anatomiques et physiologiques, occlusodontiques, biomatériaux, biophysique, radiologie"),
    /**
     * Sciences biologiques (biochimie, immunologie, histologie, embryologie, génétique, anatomie
     * pathologique, bactériologie, pharmacologie).
     */
    SCIENCES_BIOLOGIQUES(
            "57-03",
            "Sciences biologiques (biochimie, immunologie, histologie, embryologie, génétique, anatomie pathologique, bactériologie, pharmacologie)"),

    /**
     * Sciences biologiques pharmaceutiques.
     */
    SCIENCES_BIOLOGIQUES_PHARMA("41", "Sciences biologiques pharmaceutiques"),

    /**
     * Sciences du médicament.
     */
    SCIENCES_MEDICAMENT("40", "Sciences du médicament"),

    /**
     * Sciences physico-chimiques et technologies pharmaceutiques.
     */
    SCIENCES_PHYSICO("39", "Sciences physico-chimiques et technologies pharmaceutiques"),

    /**
     * Thérapeutique.
     */
    THERAPEUTIQUE("48-04", "Thérapeutique"),

    /**
     * Urologie.
     */
    UROLOGIE("52-04", "Urologie");

    /**
     * Libellé.
     */
    private String libelle;

    /**
     * Code.
     */
    private String code;

    /**
     * Constructeur privé.
     * @param libelle Libellé de la valeur de l'énumération.
     */
    private Thematique(final String code, final String libelle)
    {
        this.code = code;
        this.libelle = libelle;
    }

    /**
     * Getter sur libelle.
     * @return Retourne le libelle.
     */
    public String getLibelle()
    {
        return this.libelle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        return this.getLibelle();
    }

    /**
     * Getter pour code.
     * @return Le code
     */
    public String getCode()
    {
        return this.code;
    }

}
