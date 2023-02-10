package app.domain.store;

import app.domain.model.VaccinationEvent;
import app.domain.model.Vaccine;
import app.domain.model.VaccineType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VaccinationEventStoreTest {

    VaccinationEventStore store = new VaccinationEventStore();
    VaccineType vaccineType = new VaccineType("qwert", "Covid", "mRNA");
;

    @Test
    void addToList() {
        VaccinationEvent vaccinationEvent;
    }

    @Test
    void addVaccineEvent() {
        store.saveDataForVaccinationEvent(123456789, vaccineType);
        store.saveDataForVaccinationEvent("13EF4-05", "Pfizer", 3);
        assertTrue(store.addVaccineEvent());
    }

    @Test
    void checkIfUserHasEvent() {
        store.saveDataForVaccinationEvent(123456789, vaccineType);
        store.saveDataForVaccinationEvent("13EF4-05", "Pfizer", 3);
        store.addVaccineEvent();
        assertTrue(store.checkIfUserHasEvent(123456789));
    }
}