package app.domain.model;

import org.junit.jupiter.api.Test;

import java.beans.Expression;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompanyVaccinationCenterTest {

    private static List<VaccinationCenter>vaccinationCenterList = new ArrayList<>();
    Company company = new Company("Test");
    @Test

    void createVaccinationCenter() {
        VaccinationCenter vc1 = new VaccinationCenter("Centro1","Rua do Centro1","987654321","centro1@gmail.com","122-222-2222","www.centro1.pt","8:00","19:00",2,10,new CenterCoordinator("Ana","Casa da Ana",912345678,"ana@gmail.com",987654333));
        VaccinationCenter actual = company.createVaccinationCenter("Centro1","Rua do Centro1","987654321","centro1@gmail.com","122-222-2222","www.centro1.pt","8:00","19:00",2,10,new CenterCoordinator("Ana","Casa da Ana",912345678,"ana@gmail.com",987654333));
        assertNotNull(actual);
    }

    @Test
    void validateVaccinationCenterTestTrue() {
        VaccinationCenter vc1 = new VaccinationCenter("Centro1","Rua do Centro1","987654321","centro1@gmail.com","122-222-2222","www.centro1.pt","8:00","19:00",2,10,new CenterCoordinator("Ana","Casa da Ana",912345678,"ana@gmail.com",987654333));
        boolean actual = company.validateVaccinationCenter(vc1);
        assertTrue(actual);
    }

    @Test
    void validateVaccinationCenterTestFalse() {
        VaccinationCenter vc1 = new VaccinationCenter(null,null,null,null,null,null,null,null,0,0,new CenterCoordinator(null,null,999999999,null,9999999));
        boolean actual = company.validateVaccinationCenter(vc1);
        assertFalse(actual);
    }

    @Test
    void validateVaccinationCenterTestNullParameters() {
        VaccinationCenter vc1 = new VaccinationCenter(null,null,null,null,null,null,null,null,0,0,new CenterCoordinator(null,null,999999999,null,9999999));
        boolean actual = company.validateVaccinationCenter(vc1);
        assertFalse(actual);
    }

    @Test
    void validateVaccinationCenterTestEmptyParameter() {
        VaccinationCenter vc1 = new VaccinationCenter("","","987654321","centro1@gmail.com","122-222-2222","www.centro1.pt","8:00","19:00",2,10,new CenterCoordinator("Ana","Casa da Ana",912345678,"ana@gmail.com",987654333));
        boolean actual = company.validateVaccinationCenter(vc1);
        assertFalse(actual);
    }

    @Test
    void saveVaccinationCenterTrue() {
        VaccinationCenter vc1 = new VaccinationCenter("Centro1","Rua do Centro1","987654321","centro1@gmail.com","122-222-2222","www.centro1.pt","8:00","19:00",2,10,new CenterCoordinator("Ana","Casa da Ana",912345678,"ana@gmail.com",987654333));
        company.saveVaccinationCenter(vc1);

        boolean actual = true;
        if(Company.vaccinationCenterList.contains(vc1)){
            actual = true;
        }else{
            actual = false;
        }

        assertTrue(actual);
    }
    @Test
    void saveVaccinationCenterFalse() {
        VaccinationCenter vc1 = new VaccinationCenter(null,null,null,null,null,null,null,null,0,0,new CenterCoordinator(null,null,999999999,null,9999999));
        company.saveVaccinationCenter(vc1);
        boolean actual = true;
        if(Company.vaccinationCenterList.contains(vc1)){
            actual = true;
        }else{
            actual = false;
        }
        assertFalse(actual);
    }

    @Test
    void getVaccinationCenterList() {
        VaccinationCenter vc1 = new VaccinationCenter("Centro1","Rua do Centro1","987654321","centro1@gmail.com","122-222-2222","www.centro1.pt","8:00","19:00",2,10,new CenterCoordinator("Ana","Casa da Ana",912345678,"ana@gmail.com",987654333));
        VaccinationCenter vc2 = new VaccinationCenter("Centro2","Rua do Centro2","976543211","centro2@gmail.com","132-222-2222","www.centro2.pt","8:00","19:00",5,10,new CenterCoordinator("João","Casa do João",912345680,"joao@gmail.com",687654333));
        Company.vaccinationCenterList.add(vc1);
        Company.vaccinationCenterList.add(vc2);
        List<VaccinationCenter> expected = Company.getVaccinationCenterList();
        assertEquals(Company.vaccinationCenterList,expected);
    }

}