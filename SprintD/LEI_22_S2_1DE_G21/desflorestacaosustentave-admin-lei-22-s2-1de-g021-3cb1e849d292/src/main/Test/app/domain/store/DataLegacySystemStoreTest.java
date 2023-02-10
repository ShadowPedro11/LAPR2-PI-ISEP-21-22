package app.domain.store;

import app.controller.App;
import app.domain.model.SNSUser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataLegacySystemStoreTest {

    DataLegacySystemStore store = App.getInstance().getCompany().getDataLegacySystemStore();
    SNSUserStore snsUserStore = App.getInstance().getCompany().getSNSUserStore();

    @Test
    void verifyCSVType() {
        assertEquals(0, store.verifyCSVType("performanceDataFromGaoFuNationalCenterDoPortoVaccinationCenter.csv"));
    }

    @Test
    void verifyIfSNSUserIsRegistered() {
        SNSUser user1 = new SNSUser("Alex","Masculino","2003/4/2","Francos",123456789,"alex@gmail.com",123456789,12345678);
        snsUserStore.addUsersToSystem(user1);
        assertTrue(store.verifyIfSNSUserIsRegistered(user1.getSnsNumber()));
    }

    @Test
    void read() {

    }

    @Test
    void verifySNSUserNumber() {
    }

    @Test
    void verifyVaccineName() {
    }

    @Test
    void verifyDose() {
    }

    @Test
    void verifyLotNumber() {
    }

    @Test
    void getTotalNumberOfUsersData() {
    }

    @Test
    void getNumberUserSuccessfullyRegistered() {
    }

    @Test
    void splitDateTime() {
    }

    @Test
    void selectionSortingAlgorithmArrivalDateTimeAscendant() {
    }

    @Test
    void selectionSortingAlgorithmArrivalDateTimeDescendant() {
    }

    @Test
    void selectionSortingAlgorithmLeavingDateTimeAscendant() {
    }

    @Test
    void selectionSortingAlgorithmLeavingDateTimeDescendant() {
    }

    @Test
    void bubbleSortingAlgorithmArrivalDateTimeAscendant() {
    }

    @Test
    void bubbleSortingAlgorithmArrivalDateTimeDescendant() {
    }

    @Test
    void bubbleSortingAlgorithmLeavingDateTimeAscendant() {
    }

    @Test
    void bubbleSortingAlgorithmLeavingDateTimeDescendant() {
    }

    @Test
    void checkIfUserWasInWaitingRoom() {
    }

    @Test
    void checkIfUserWasInRecoveryRoom() {
    }

    @Test
    void addToWaitingAndRecoveryRoomRegisters() {
    }

    @Test
    void getUserVaccinationDataList() {
    }

    @Test
    void validatePathToCSV() {
    }

    @Test
    void everythingToString() {
    }
}