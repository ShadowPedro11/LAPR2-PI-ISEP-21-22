package app.domain.model.VaccinationCenter;

import app.domain.model.VaccineType;
import app.domain.model.employee.CenterCoordinator;


public class MassVaccinationCenter extends VaccinationCenter {

    /**
     * Builds a vaccination center instance
     *
     * @param name
     * @param address
     * @param phoneNumber
     * @param emailAddress
     * @param faxNumber
     * @param webSiteAddress
     * @param openHour
     * @param closeHour
     * @param slotDuration
     * @param maxVaccinePerSlot
     * @param cc
     */
    private VaccineType vt;

    public MassVaccinationCenter(String name, String address, String phoneNumber, String emailAddress, String faxNumber, String webSiteAddress, String openHour, String closeHour, int slotDuration, int maxVaccinePerSlot, CenterCoordinator cc, VaccineType vt) {
        super(name, address, phoneNumber, emailAddress, faxNumber, webSiteAddress, openHour, closeHour, slotDuration, maxVaccinePerSlot, cc);
        this.vt = vt;
    }

    public String toString() {
        return "VacinationCenter{" +
                "name='" + getName() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", email='" + getEmailAddress() + '\'' +
                ", fax='" + getFaxNumber() + '\'' +
                ", website='" + getWebSiteAddress() + '\'' +
                ", openHours='" + getOpenHour() + '\'' +
                ", closingHours='" + getCloseHour() + '\'' +
                ", slotDuration=" + getSlotDuration() +
                ", vacinesSlotCap=" + getMaxVaccinePerSlot() +
                ", CenterCoordinator=" + getCc() +
                ", VaccineType=" + vt +
                "}\n";
    }

    public VaccineType getVt() {
        return vt;
    }
}
