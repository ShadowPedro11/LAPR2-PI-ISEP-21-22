package app.domain.model;

import java.io.Serializable;
import java.util.List;

public class Vaccine implements Serializable {

    /**
     * All variables that the Vaccine class needs.
     */
   private String name;
   private String brand;
   private VaccineType type;
   private List<VaccineAdministration> administrations;

    /**
     * Constructor for the Vaccine class,
     * creates a new vaccine with the following attributes:
     * @param name
     * @param brand
     * @param type
     */

   public Vaccine (String name, String brand, VaccineType type, List<VaccineAdministration> administrations){
       this.name = name;
       this.brand = brand;
       this.type = type;
       this.administrations = administrations;
   }

    /**
     * This method prints all information about the vaccine.
     */
    public String toString() {
        return "Vaccine{" +
                "name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", type='" + type + '\'' +
                ", Administrations='" + administrations + '\'' +
                '}' + "\n";
    }

   public String getName(){
       return name;
   }

    public String getBrand(){
        return brand;
    }

    public VaccineType getType(){
        return type;
    }

    public boolean compareVaccineType(VaccineType vaccineType){
        if(this.type.equals(vaccineType)){
            return true;
        }
        return false;
    }

    public List<VaccineAdministration> getAdministrations() {
        return administrations;
    }
}