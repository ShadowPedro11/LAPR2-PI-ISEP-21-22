package app.domain.model;

import app.domain.model.VaccinationCenter.MassVaccinationCenter;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.model.employee.CenterCoordinator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class WaitingRoomTest {
    CenterCoordinator cc1 =new CenterCoordinator("Ana","Casa da Ana",927641959,"ana@gmail.com",12345678);
    VaccineType vt1 = new VaccineType("qwert","Covid","mRNA");
    SNSUser user = new SNSUser("João","Masculine","28/4/2002","Paredes",123456789,"joao@gmail.com",892375901,83472195);
    SNSUser user2 = new SNSUser("Diana","Feminine","28/6/2004","Cabeceiras",927834832,"diana@gmail.com",12345678,213987628);
    MassVaccinationCenter vc1 = new MassVaccinationCenter("CentroTeste1","Rua do CentroTeste1","987321654","centroteste1@gmail.com","1234567890","www.centroteste1.com","8:00","16:00",5,5,cc1,vt1);

    @Test
    void addToWaitingRoom() {
        assertTrue(WaitingRoom.addToWaitingRoom(vc1, user));
    }

    @Test
    void getWaitingRoom() {
        MassVaccinationCenter vc1 = new MassVaccinationCenter("CentroTeste1","Rua do CentroTeste1","987321654","centroteste1@gmail.com","1234567890","www.centroteste1.com","8:00","16:00",5,5,cc1,vt1);
        WaitingRoom.addToWaitingRoom(vc1, user);
        WaitingRoom.addToWaitingRoom(vc1, user2);
        ArrayList<SNSUser> expected = new ArrayList<>();
        expected.add(user);
        expected.add(user2);

        assertEquals(expected,WaitingRoom.getWaitingRoom(vc1));
    }

    @Test
    void getWaitingRoomEmpty(){
        CenterCoordinator cc2 =new CenterCoordinator("Pedro","Casa do Pedro",924096624,"pedro@gmail.com",14567823);
        MassVaccinationCenter vc2 = new MassVaccinationCenter("CentroTeste2","Rua do CentroTeste2","934852983","centroteste2@gmail.com","6738910293","www.centroteste2.com","8:00","16:00",5,5,cc2,vt1);
        ArrayList<SNSUser> expected = new ArrayList<>();
        assertEquals(expected, WaitingRoom.getWaitingRoom(vc2));
    }


}