public enum HealthUserType {
    SELF("self"),
    SPOUSE("spouse"),
    WIFE("wife"),
    HUSBAND("husband"),
    MOTHER("mother"),
    FATHER("father"),
    DAUGHTER("daughter"),
    SON("son"),
    SON1("son"),
    SON2("son"),
    SON3("son"),
    SON4("son"),
    DAUGHTER1("daughter"),
    DAUGHTER2("daughter"),
    DAUGHTER3("daughter"),
    DAUGHTER4("daughter"),
    // TEMPORARY FIX
    self("self"),
    spouse("spouse"),
    wife("wife"),
    husband("husband"),
    mother("mother"),
    father("father"),
    daughter("daughter"),
    son("son"),
    son1("son"),
    son2("son"),
    son3("son"),
    son4("son"),
    daughter1("daughter"),
    daughter2("daughter"),
    daughter3("daughter"),
    daughter4("daughter");

    private final String type;


    /**
     * @param type
     */
    private HealthUserType(String type) {
        this.type = type;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    public static HealthUserType getUserTypeEnumBytype(String type) {
        final HealthUserType userType = null;
        for (HealthUserType ut : HealthUserType.values()) {
            if (ut.getType().equalsIgnoreCase(type)) {
                return ut;
            }
        }
        return userType;
    }
}
