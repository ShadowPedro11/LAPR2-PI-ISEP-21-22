package app.domain.model.VaccinationCenter;

import app.domain.model.SNSUser;
import app.domain.model.WaitingRoom;
import app.domain.model.employee.CenterCoordinator;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

/**
 *Represents a vaccination center through name, address, phone number, email address, fax number, website address,
 * open hour, closing hour,  duration of a slot, maximum number of vaccines per slot and center coordinator.
 */
public abstract class VaccinationCenter {
    /**
     * The name of Vaccination Center.
     */
    private String name;
    /**
     * The address of Vaccination Center.
     */
    private String address;
    /**
     * The phone number of Vaccination Center.
     */
    private String phoneNumber;
    /**
     * The email address of Vaccination Center.
     */
    private String emailAddress;
    /**
     * The fax number of Vaccination Center.
     */
    private String faxNumber;
    /**
     * The website of Vaccination Center.
     */
    private String webSiteAddress;
    /**
     * The opening hour of Vaccination Center.
     */
    private String openHour;
    /**
     * The closing hour of Vaccination Center.
     */
    private String closeHour;
    /**
     * The slot duration of Vaccination Center.
     */
    private final int slotDuration;
    /**
     * The number of vaccines per slot of Vaccination Center.
     */
    private final int maxVaccinePerSlot;
    /**
     * The Center Coordinator responsible for Vaccination Center.
     */
    private CenterCoordinator cc;

    /**
     *Builds a vaccination center instance
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
    public VaccinationCenter(String name, String address, String phoneNumber, String emailAddress, String faxNumber
            , String webSiteAddress, String openHour, String closeHour, int slotDuration, int maxVaccinePerSlot, CenterCoordinator cc) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.faxNumber = faxNumber;
        this.webSiteAddress = webSiteAddress;
        this.openHour = openHour;
        this.closeHour = closeHour;
        this.slotDuration = slotDuration;
        this.maxVaccinePerSlot = maxVaccinePerSlot;
        this.cc = cc;
    }

    /**
     * @return Vaccination Center name, address, phone number, email, fax, website, open hour, closing hour,
     * slot duration, vaccines per slot, center coordinator.
     */
    public String toString() {
        return "VacinationCenter{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + emailAddress + '\'' +
                ", fax='" + faxNumber + '\'' +
                ", website='" + webSiteAddress + '\'' +
                ", openHours='" + openHour + '\'' +
                ", closingHours='" + closeHour + '\'' +
                ", slotDuration=" + slotDuration +
                ", vacinesSlotCap=" + maxVaccinePerSlot +
                ", CenterCoordinator=" + cc +
                "}\n";
    }
//============================================================================================================
    /**
     * @return Vaccination Center name
     */
    public String getName() {
        return name;
    }
    /**
     * @return Vaccination Center address
     */
    public String getAddress() {
        return address;
    }
    /**
     * @return Vaccination Center phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    /**
     * @return Vaccination Center email
     */
    public String getEmailAddress() {
        return emailAddress;
    }
    /**
     * @return Vaccination Center fax number
     */
    public String getFaxNumber() {
        return faxNumber;
    }
    /**
     * @return Vaccination Center website address
     */
    public String getWebSiteAddress() {
        return webSiteAddress;
    }
    /**
     * @return Vaccination Center openinh hour
     */
    public String getOpenHour() {
        return openHour;
    }
    /**
     * @return Vaccination Center closing hour
     */
    public String getCloseHour() {
        return closeHour;
    }
    /**
     * @return Vaccination Center slot duration
     */
    public int getSlotDuration() {
        return slotDuration;
    }
    /**
     * @return Vaccination Center vacciner per slot
     */
    public int getMaxVaccinePerSlot() {
        return maxVaccinePerSlot;
    }
    /**
     * @return Vaccination Center coordinator
     */
    public CenterCoordinator getCc() {
        return cc;
    }
}
