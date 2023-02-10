package app.domain.store;

import app.controller.App;
import app.domain.dto.SNSUserDto;
import app.domain.model.RecoveryRoom;
import app.domain.model.SNSUser;
import app.domain.model.VaccinationCenter.MassVaccinationCenter;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.model.VaccineType;
import app.domain.model.WaitingRoom;
import app.domain.model.employee.CenterCoordinator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class AnalyzeCenterPerformanceStoreTest {

    VaccineType vt = new VaccineType("awsdf","x","Rna");
    VaccinationCenter vc1 = new MassVaccinationCenter("Centro1","Rua do Centro1","987654321","centro1@gmail.com","122-222-2222","www.centro1.pt","8:00","19:00",2,10,new CenterCoordinator("Ana","Casa da Ana",912345678,"ana@gmail.com",987654333),vt);
    WaitingRoom waitingRoom = vc1.getWaitingRoom();
    RecoveryRoom recoveryRoom = vc1.getRecoveryRoom();
    AnalyzeCenterPerformanceStore store = new AnalyzeCenterPerformanceStore();
    SNSUser user1 = new SNSUser("Alex","Masculino","2003/4/2","Francos",123456789,"alex@gmail.com",123456789,12345678);
    LocalDate date = LocalDate.of(2022,5,5);
    LocalTime time = LocalTime.of(12,10);

    @Test
    void testCheckDayForAnalysis() {
        waitingRoom.addToWaitingRoom(user1, date, time);
        assertTrue(store.checkDayForAnalysis(date, vc1));
    }

    @Test
    void bruteForceTest() {
        int[] example = {29, -32, -9, -25, 44, 12, -61, 51, -9, 44, 74, 4}; // 164
        int[] expected = {51, -9, 44,74,4};
        assertArrayEquals(expected, store.bruteForce(example));
    }

    @Test
    void kadaneTest() {
        int[] example = {29, -32, -9, -25, 44, 12, -61, 51, -9, 44, 74, 4}; // 164
        int[] expected = {51, -9, 44,74,4};
        assertArrayEquals(expected, store.kadane(example));
    }

    @Test
    void getSumTest() {
        int[] example = {29, -32, -9, -25, 44, 12, -61, 51, -9, 44, 74, 4}; // 164
        store.bruteForce(example);
        assertEquals("164", store.getSum());
    }

    @Test
    void setMinutesPerIntervalTest() {
        assertAll(()->{store.setMinutesPerInterval(30);});
    }

}