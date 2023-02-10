package app.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VaccineTypeTest {

    @Test
    void vaccineTypeIsVaccineType () {
        VaccineType vt = new VaccineType("abc12", "A test vaccine type", "Live attenuated vaccine");
        assertInstanceOf(VaccineType.class, vt);
    }

}