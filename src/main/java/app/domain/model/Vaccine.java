package app.domain.model;

public class Vaccine {

    /**
     * All variables that the Vaccine class needs.
     */
   private String name;
   private String brand;
   private String type;

    /**
     * Constructor for the Vaccine class,
     * creates a new vaccine with the following attributes:
     * @param name
     * @param brand
     * @param type
     */

   public Vaccine (String name, String brand, String type){
       this.name = name;
       this.brand = brand;
       this.type = type;
   }

    /**
     * This method prints all information about the vaccine.
     */
    public String toString() {
        return "Vaccine{" +
                "name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

   public String getName(){
       return name;
   }

    public String getBrand(){
        return brand;
    }

    public String getType(){
        return type;
    }
}