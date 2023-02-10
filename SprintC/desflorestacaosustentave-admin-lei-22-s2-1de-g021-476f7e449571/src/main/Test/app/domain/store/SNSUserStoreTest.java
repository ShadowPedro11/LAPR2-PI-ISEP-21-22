package app.domain.store;

import app.domain.dto.SNSUserDto;
import app.domain.model.SNSUser;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SNSUserStoreTest {

    SNSUserStore store = new SNSUserStore();

    ArrayList<SNSUserDto> testList = new ArrayList<>();

    SNSUserDto user1 = new SNSUserDto("Alex","Masculine","2003/4/2","Francos",123456789,"alex@gmail.com",123456789,12345678);
    SNSUserDto user2 = new SNSUserDto("Tiago","Masculine","2003/4/15","Trofa",234567890,"tiago@gmail.com",987654321,12312312);
    SNSUserDto user3 = new SNSUserDto("Pedro","Masculine","2003/7/5","ISEP",345678901,"pedro@gmail.com",987987987,87654321);
    SNSUserDto user4 = new SNSUserDto("Jose","Masculine","2002/5/2","Azurara",456789012,"jose@gmail.com",123456780,12345679);
    SNSUserDto user5 = new SNSUserDto("", "", "", "", 1, "", 1, 1);

    @Before
    void init () {
        testList.add(user1);
        testList.add(user2);
        testList.add(user3);
        testList.add(user4);


        /*
        store.userList.add(user1);
        store.userList.add(user2);
        store.userList.add(user3);
        store.userList.add(user4);
         */
    }

    @Test
    void validateNotNullCreation () {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    SNSUserDto user = new SNSUserDto(null, null, null, null, 0, null, 0, 0);
                });

    }

    /*
    @Test
    void getSnsUserByNumber () {
        store.userList.add(user1);
        assertEquals(user1, store.getUserBySNSNumber(123456789));
    }

    @Test
    void getUserTest () {
        store.userList.add(user1);
        assertEquals(user1, store.getUser(0));
    }
     */

    @Test
    void getUserNullTest () {
        assertNull(store.getUserBySNSNumber(0));
    }

    @Test
    void getUserList () {
        assertEquals(testList, store.getUserList());
    }

    @Test
    void validateVerifyCSVTypeHeader () {
        assertEquals(0, store.verifyCSVType("users"));
    }

    @Test
    void validateVerifyCSVTypeNoHeader () {
        assertEquals(1, store.verifyCSVType("usersNoHeader"));
    }

    @Test
    void validatePathToCSV () {
        assertTrue(store.validatePathToCSV("users"));
    }

    @Test
    void validatePathToCSVFileNotFound () {
        assertFalse(store.validatePathToCSV(""));
    }

    @Test
    void validateUserTest () {
        assertTrue(store.validateSNSUser(user1));
    }

    @Test
    void validateUserInvalidTest () {
        assertFalse(store.validateSNSUser(user5));
    }

    @Test
    void validateAddUsersConfirm () {
        assertTrue(store.addUsers("users"));
    }

    @Test
    void validateAddUserConfirmNoHeader () {
        assertTrue(store.addUsers("usersNoHeader"));
    }

    @Test
    void addUsersFalse () {
        assertFalse(store.addUsers("noFile"));
    }
}