package app.domain.store;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckAndExportStoreTest {

    CheckAndExportStore store = new CheckAndExportStore();

    @Test
    void checkValid() {
        String expected1 = "26/08/2023";
        String expected2 = "27/08/2023";
        store.checkIntervalTimeRules(expected1,expected2);
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

    @Test
    void checkIntervalValid() {
        store.checkInterval("30/04/2023","01/05/2023");
    }

    @Test
    void checkIntervalInvalid() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    store.checkInterval("31/04/2023","25/04/2023");
                });
    }
}