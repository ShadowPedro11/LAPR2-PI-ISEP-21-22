package app.domain.store;

import app.controller.SnsUserVaccineScheduleController;
import app.domain.model.SNSUser;
import app.domain.model.SnsUserVaccineSchedule;
import app.domain.model.VaccinationCenter.HealthCareCenter;
import app.domain.model.VaccinationCenter.MassVaccinationCenter;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.model.VaccineType;
import app.domain.model.employee.CenterCoordinator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VaccineScheduleStoreTest {
    VaccineScheduleStore store = new VaccineScheduleStore();
    int snsUserNumber = 12354678;
    int snsUserNumber2 = 21354678;
    VaccineType vt1 = new VaccineType("qwert","Covid","mRNA");
    VaccineType vt2 = new VaccineType("asdfg","Dengue","mRNA");
    CenterCoordinator cc1 =new CenterCoordinator("Paulo","Casa do Paulo",965711204,"paulo@gmail.com",21345678);
    CenterCoordinator cc2 =new CenterCoordinator("Ana","Casa da Ana",927641959,"ana@gmail.com",12345678);
    MassVaccinationCenter vc1 = new MassVaccinationCenter("CentroTeste1","Rua do CentroTeste1","987321654","centroteste1@gmail.com","1234567890","www.centroteste1.com","8:00","16:00",5,5,cc1,vt1);
    HealthCareCenter vc2 = new HealthCareCenter("CentroTeste2","Rua do CentroTeste2","978321654","centroteste2@gmail.com","2134567890","www.centroteste2.com","10:00","18:00",5,5,cc2,"Centro Regional do Minho");
    String day = "30/08/2022";
    String hr = "17:00";
    String day1 = "28/08/2022";
    String hr1 = "16:00";
    SnsUserVaccineSchedule vcSkd1 = new SnsUserVaccineSchedule(snsUserNumber,vc1,vt1,day,hr);
    SnsUserVaccineSchedule vcSkd2 = new SnsUserVaccineSchedule(snsUserNumber,vc2,vt2,day1,hr1);



    @Test
    void createVaccinationScheduleForHealthCareCenter() {
        int snsUserNumber = 12354678;
        CenterCoordinator cc1 =new CenterCoordinator("Ana","Casa da Ana",927641959,"ana@gmail.com",12345678);
        HealthCareCenter vc2 = new HealthCareCenter("CentroTeste2","Rua do CentroTeste2","978321654","centroteste2@gmail.com","2134567890","www.centroteste2.com","10:00","18:00",5,5,cc1,"Centro Regional do Minho");
        VaccineType vt1 = new VaccineType("qwert","Covid","mRNA");
        String day = "28/08/2022";
        String hr = "16:00";
        SnsUserVaccineSchedule expected = new SnsUserVaccineSchedule(snsUserNumber,vc2,vt1,day,hr);
        SnsUserVaccineSchedule actual = store.createVaccinationSchedule(snsUserNumber,vc2,vt1,day,hr);
        assertTrue(expected.toDto().getSnsUserNumber()==actual.toDto().getSnsUserNumber()
                && expected.toDto().getVaccineType().getCode().compareTo(actual.toDto().getVaccineType().getCode())==0
                && expected.toDto().getVaccinationCenter().getName().compareTo(actual.toDto().getVaccinationCenter().getName())==0
                && expected.toDto().getDay().compareTo(actual.toDto().getDay())==0
                && expected.toDto().getHour().compareTo(actual.toDto().getHour())==0);
    }

    @Test
    void createVaccinationScheduleMassVaccinationCenter() {
        SnsUserVaccineSchedule expected = new SnsUserVaccineSchedule(snsUserNumber,vc1,vt1,day,hr);
        SnsUserVaccineSchedule actual = store.createVaccinationSchedule(snsUserNumber,vc1,vt1,day,hr);
        assertTrue(expected.toDto().getSnsUserNumber()==actual.toDto().getSnsUserNumber()
                && expected.toDto().getVaccineType().getCode().compareTo(actual.toDto().getVaccineType().getCode())==0
                && expected.toDto().getVaccinationCenter().getName().compareTo(actual.toDto().getVaccinationCenter().getName())==0
                && expected.toDto().getDay().compareTo(actual.toDto().getDay())==0
                && expected.toDto().getHour().compareTo(actual.toDto().getHour())==0);
    }

    @Test
    void validateVaccinationScheduleNullParameter() {
        SnsUserVaccineSchedule vcSkd = null;
        boolean actual = store.validateVaccinationSchedule(vcSkd);
        assertFalse(actual);
    }

    @Test
    void validateVaccinationScheduleDuplicated() {
        List<SnsUserVaccineSchedule> SnsUserVaccineShcheduleList = store.getSnsUserVaccineShcheduleList();
        SnsUserVaccineShcheduleList.add(vcSkd1);
        boolean expected = store.validateVaccinationSchedule(vcSkd1);
        assertFalse(expected);
    }

    @Test
    void validateVaccinationSchedule() {
        List<SnsUserVaccineSchedule> SnsUserVaccineShcheduleList = store.getSnsUserVaccineShcheduleList();
        SnsUserVaccineShcheduleList.add(vcSkd1);
        boolean expected = store.validateVaccinationSchedule(vcSkd2);
        assertTrue(expected);
    }

    @Test
    void checkDuplicatesNoDuplicates() {
        List<SnsUserVaccineSchedule> SnsUserVaccineShcheduleList = store.getSnsUserVaccineShcheduleList();
        SnsUserVaccineShcheduleList.add(vcSkd1);
        boolean expected = store.checkDuplicates(vcSkd2);
        assertTrue(expected);
    }

    @Test
    void checkDuplicatesDuplicates() {
        List<SnsUserVaccineSchedule> SnsUserVaccineShcheduleList = store.getSnsUserVaccineShcheduleList();
        SnsUserVaccineShcheduleList.add(vcSkd1);
        SnsUserVaccineSchedule vcSkd2 = new SnsUserVaccineSchedule(snsUserNumber,vc2,vcSkd1.toDto().getVaccineType(), day1,hr1);
        boolean expected = store.checkDuplicates(vcSkd2);
        assertFalse(expected);
    }

    @Test
    void saveVaccinationScheduleInValidSchedule() {
        SnsUserVaccineSchedule vcSkd = null;
        Boolean expected = store.saveVaccinationSchedule(vcSkd);
        assertFalse(expected);
    }

    @Test
    void saveVaccinationScheduleValidSchedule() {
        List<SnsUserVaccineSchedule> SnsUserVaccineShcheduleList = store.getSnsUserVaccineShcheduleList();
        SnsUserVaccineShcheduleList.add(vcSkd1);
        boolean expected = store.saveVaccinationSchedule(vcSkd2);
        assertTrue(expected);
    }

    @Test
    void checkerValid() {
        SNSUserStore userStore = new SNSUserStore();
        ArrayList<SNSUser> SnsUserList = userStore.getUserList();
        SNSUser user1 = new SNSUser("Testes","male","30/07/1998","Casa Teste",987321654,"testeuser@gmail.com",34125678,12345679);
        SnsUserList.add(user1);
        Boolean expected = store.checker(34125678,SnsUserList,"testeuser@gmail.com");
        assertTrue(expected);
    }

    @Test
    void checkerInvalid() {
        SNSUserStore userStore = new SNSUserStore();
        ArrayList<SNSUser> SnsUserList = userStore.getUserList();
        SNSUser user1 = new SNSUser("Testes","male","30/07/1998","Casa Teste",987321654,"testeuser@gmail.com",34125678,12345679);
        SnsUserList.add(user1);
        Boolean expected = store.checker(snsUserNumber,SnsUserList,"testeuser@gmail.com");
        assertFalse(expected);
    }

    @Test
    void chekSnsUserNumberRulesValid() {
        store.chekSnsUserNumberRules(123456789);
    }

    @Test
    void chekSnsUserNumberRulesInvalid() {
        assertThrows(IllegalArgumentException.class,
                () -> {
            store.chekSnsUserNumberRules(12345);
                });
    }

    @Test
    void chekDayDateRulesValid() {
        String expected = "26/08/2023";
        store.chekDayDateRules(expected);
    }

    @Test
    void chekDayDateRulesInvalidOneBar() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    store.chekDayDateRules("26/082022");
                });
    }

    @Test
    void chekDayDateRulesInvalidNoBars() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    store.chekDayDateRules("26082022");
                });
    }

    @Test
    void chekDayDateRulesInvalidFormat() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    store.chekDayDateRules("261/08/2022");
                });
    }

    @Test
    void chekDayDateRulesInvalidMonthLapYear() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    store.chekDayDateRules("12/13/2024");
                });
    }

    @Test
    void chekDayDateRulesInvalidMonthNormalYear() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    store.chekDayDateRules("12/13/2023");
                });
    }

    @Test
    void chekDayDateRulesInvalidDate() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    store.chekDayDateRules("12/11/2021");
                });
    }

    @Test
    void verifieDateParametersInLeapYearValid() {
        store.chekDayDateRules("29/02/2024");

    }

    @Test
    void verifieDateParametersInLeapYearInvalidDayFebruary() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    store.chekDayDateRules("30/02/2024");
                });
    }

    @Test
    void verifieDateParametersInLeapYearInvalidDay31DaysMonth() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    store.chekDayDateRules("32/03/2024");
                });
    }

    @Test
    void verifieDateParametersInLeapYearInvalidDay30DaysMonth() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    store.chekDayDateRules("31/04/2024");
                });
    }
    @Test
    void verifieDateParametersInNormalYearValid() {
        store.chekDayDateRules("28/02/2023");
    }

    @Test
    void verifieDateParametersInNormalInvalidDayFebruary() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    store.chekDayDateRules("29/02/2023");
                });
    }

    @Test
    void verifieDateParametersInNormalYearInvalidDay31DaysMonth() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    store.chekDayDateRules("32/03/2023");
                });
    }

    @Test
    void verifieDateParametersInNormalYearInvalidDay30DaysMonth() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    store.chekDayDateRules("31/04/2023");
                });
    }

    @Test
    void isLapYearValid() {
        boolean expected = store.isLeapYear(2024);
        assertTrue(expected);
    }

    @Test
    void isLapYearInvalid(){
        boolean expected = store.isLeapYear(2023);
        assertFalse(expected);
    }
}