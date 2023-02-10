package app.domain.model;

import java.io.Serializable;

public class VaccineType implements Serializable {

    //  https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=15751
    private String code;
    private String description;
    private String technology;

    /**
     * Creates a new VaccineType instance with the following attributes:
     * @param code five alphanumerical characters.
     * @param description a brief description of the vaccine.
     * @param technology kind of tecnhology used in the vaccine.
     */
    public VaccineType (String code, String description, String technology) {
        this.code = code;
        this.description = description;
        this.technology = technology;
    }

    /**
     * A toString method wich souts all attibutes of the vaccine type.
     */
    @Override
    public String toString() {
        return "VaccineType{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", technology='" + technology + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getTechnology() {
        return technology;
    }
}
