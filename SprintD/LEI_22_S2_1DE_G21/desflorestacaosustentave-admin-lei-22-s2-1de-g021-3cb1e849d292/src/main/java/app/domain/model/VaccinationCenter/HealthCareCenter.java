package app.domain.model.VaccinationCenter;

import app.domain.model.employee.CenterCoordinator;

public class HealthCareCenter extends VaccinationCenter{
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
    private String args;
    public HealthCareCenter(String name, String address, String phoneNumber, String emailAddress, String faxNumber, String webSiteAddress, String openHour, String closeHour, int slotDuration, int maxVaccinePerSlot, CenterCoordinator cc,String args) {
        super(name, address, phoneNumber, emailAddress, faxNumber, webSiteAddress, openHour, closeHour, slotDuration, maxVaccinePerSlot, cc);
        this.args = args;
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
                ", ARS/AGES=" + args +
                "}\n";
    }

    public String getArgs() {
        return args;
    }
}
