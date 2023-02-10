package app.domain.store;

import app.domain.dto.SNSUserDto;
import app.domain.mapper.SNSUserMapper;
import app.domain.model.SNSUser;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SNSUserStore implements Serializable {

    /**
     * ArrayList with the users registered withing the system.
     */
    private ArrayList<SNSUser> userList;

    /**
     * ArrayList wich contains the users before registering them into the system.
     */
    private transient ArrayList<SNSUserDto> userListToVerify;

    /**
     * Constructor of SNSUserStore class
     */
    public SNSUserStore() {
        userList = new ArrayList<>();
    }

    /**
     * This method returns the SNSUser by using his/her SNS nuber.
     *
     * @param snsNumber of the user.
     * @return user of the given SNS number.
     */
    public SNSUser getUserBySNSNumber(int snsNumber) {
        for (SNSUser u : this.userList) {
            if (u.getSnsNumber() == snsNumber) {
                return u;
            }
        }
        return null;
    }

    /**
     * This method returns an SNS user using his/her index of the ArrayList containing all of SNS users.
     *
     * @param index of the user in the ArrayList of users.
     * @return user of the given index in the ArrayList.
     */
    public SNSUser getUser(int index) {
        return this.userList.get(index);
    }

    /**
     * This method returns the SNSUser ArrayList with all the users that are registered into the system.
     *
     * @return ArrayList containing all SNSUsers instances.
     */
    public ArrayList<SNSUser> getUserList() {
        ArrayList<SNSUser> list = this.userList;
        return list;
    }

    /**
     * This method returns a SNSUserDto ArrayList with a copy of all SNSUsers in the system.
     *
     * @return ArrayList containing a copy of every SNSUser.
     */
    public ArrayList<SNSUserDto> getUserListDto() {
        ArrayList<SNSUserDto> list = new ArrayList<>();
        for (SNSUser user : userList) {
            SNSUserDto newUser = SNSUserMapper.toDto(user);
            list.add(newUser);
        }
        return list;
    }

    /**
     * This method ensures that the file with the SNSUsers the user wants to register into the System.
     *
     * @param pathToCSV to the CSV file
     * @return true if, and only if, the CSV file exist in the given path the user introducced
     */
    public boolean validatePathToCSV(String pathToCSV) {
        File file = new File(pathToCSV);
        return file.exists();
    }

    /**
     * This method will verify what kind of CSV file the user wants to use.
     * It can use two types of CSV files,
     * 1ª can be a CSV file with a header, and it's information is separated with semicolon ";".
     * 2º can be a CSV without a header, and it's information is separeted with comma ",".
     *
     * @param pathToCSV to the CSV file.
     * @return 0 if the file contains a header(file of type nº 1) 1 if the file doesn't contain a header.
     */
    public int verifyCSVType(String pathToCSV) {
        File file = new File(pathToCSV);

        try {
            Scanner sc = new Scanner(file);
            String firstLine = sc.nextLine();
            if (firstLine.contains("Name;Sex;BirthDate;Address;PhoneNumber;Email;SNSUSerNumber;CitizenCardNumber"))
                return 0;
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return 1;
    }

    /**
     * This method verify if the given user is valid using the following validate methods.
     *
     * @param user that we want to test.
     * @return true if, and only if, the user is a valid one.
     */
    public boolean validateSNSUser(SNSUserDto user) {
        if (validateName(user) && validateSex(user) && validateBirthDate(user) && validateAddress(user) && validatePhoneNumber(user) && validateEmail(user) && validateSnsNumber(user) && validateCitizenCardNumber(user))
            return true;
        return false;
    }

    /**
     * This method will validate the name of the SNS user,
     * it makes sure itsn't a blank space.
     *
     * @param user that we want to test
     * @return true if, and only if, the user's name is valid.
     */
    private boolean validateName(SNSUserDto user) {
        return !user.getName().isBlank();
    }

    /**
     * This method will validate the user's gender,
     * it makes sure itsn't a blank space.
     *
     * @param user that we want to test
     * @return true if, and only if, the user's gender is valid.
     */
    private boolean validateSex(SNSUserDto user) {
        return (user.getSex().equals("Masculino") || user.getSex().equals("Feminino") || user.getSex().equals(""));
    }

    /**
     * This method will validate the user's E-mail,
     * it makes sure that the E-mail contains a "@" symbol and ".com" or ".pt" ending.
     *
     * @param user that we want to test
     * @return true if, and only if, the user's E-mail is valid.
     */
    private boolean validateEmail(SNSUserDto user) {
        if (user.getEmail().contains("@") && (user.getEmail().contains(".com") || user.getEmail().contains(".pt"))) {
            ArrayList<SNSUser> userList = getUserList();
            for (SNSUser test : userList) {
                if (test.getEmail().equals(user.getEmail())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * This method will validate the user's addres,
     * it makes sure that the addres is not blank space.
     *
     * @param user that we want to test
     * @return true if, and only if, the user's address is valid.
     */
    private boolean validateAddress(SNSUserDto user) {
        return !user.getAddress().isBlank();
    }

    /**
     * This method will validate the user's birthdate,
     * it makes sure that the birtdate is not blank space.
     *
     * @param user that we want to test
     * @return true if, and only if, the user's address is valid.
     */
    private boolean validateBirthDate(SNSUserDto user) {
        return !user.getBirthDate().isBlank();
    }

    /**
     * This method will validate the user's citizen card number,
     * it makes sure that the number is a valid one, and it is not repeated.
     *
     * @param user that we want to test
     * @return true if, and only if, the user's address is valid.
     */
    private boolean validateCitizenCardNumber(SNSUserDto user) {
        if (user.getCitizenCardNumber() < 9999999 || user.getCitizenCardNumber() > 99999999) {
            return false;
        } else {
            ArrayList<SNSUser> userList = getUserList();
            for (SNSUser test : userList) {
                if (test.getCitizenCardNumber() == user.getCitizenCardNumber()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * This method will validate the user's phone number,
     * it makes sure that the number is a valid one, and it is not repeated.
     *
     * @param user that we want to test
     * @return true if, and only if, the user's address is valid.
     */
    private boolean validatePhoneNumber(SNSUserDto user) {
        if (user.getPhoneNumber() < 99999999 || user.getPhoneNumber() > 999999999) {
            return false;
        } else {
            ArrayList<SNSUser> userList = getUserList();
            for (SNSUser test : userList) {
                if (test.getPhoneNumber() == user.getPhoneNumber()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * This method will validate the user's sns number,
     * it makes sure that the number is a valid one, and it is not repeated.
     *
     * @param user that we want to test
     * @return true if, and only if, the user's address is valid.
     */
    private boolean validateSnsNumber(SNSUserDto user) {
        if (user.getSnsNumber() < 99999999 || user.getSnsNumber() > 999999999) {
            return false;
        } else {
            ArrayList<SNSUser> userList = getUserList();
            for (SNSUser test : userList) {
                if (test.getSnsNumber() == user.getSnsNumber()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * This method creates a temporary ArrayList containing all users in the CSV file,
     * once the method verify that all the users are valid it will add them to the final userList ArrayList.
     *
     * @param pathToCSV to the CSV file
     * @return true if, and only if, the users are successfully added to the system.
     */
    private boolean addUser(String pathToCSV) {
        try {
            File file = new File(pathToCSV);
            BufferedReader sc = new BufferedReader(new FileReader(file));

            userListToVerify = new ArrayList<>();
            String splitChar;
            if (verifyCSVType(pathToCSV) == 0) {
                sc.readLine();
                splitChar = ";";
            } else {
                splitChar = ",";
            }

            /*
              This part will get the users from the CSV file
             */
            String line;
            while ((line = sc.readLine()) != null) {
                String[] userInfo = line.split(splitChar);
                SNSUserDto user = new SNSUserDto(userInfo[0], userInfo[1], userInfo[2], userInfo[3], Integer.parseInt(userInfo[4]), userInfo[5], Integer.parseInt(userInfo[6]), Integer.parseInt(userInfo[7]));
                if (validateSNSUser(user)) {
                    this.userListToVerify.add(user);
                } else {
                    return false;
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        }

        /*
            This part will add the users to the final ArrayList containing all users registered in the system.
         */
        for (SNSUserDto user : userListToVerify) {
            SNSUser newUser = SNSUserMapper.toModel(user);
            addUsersToSystem(newUser);
        }
        return true;
    }

    /**
     * This method will call the method addUser wich will add the users to the system.
     *
     * @param pathToCSV to the CSV file
     * @return true if, and only if, the users were successfully added.
     */
    public boolean addUsers(String pathToCSV) {
        if (validatePathToCSV(pathToCSV)) {
            return addUser(pathToCSV);
        }
        return false;
    }

    /**
     * This method will add the users to the ArrayList containing all users registered in the system.
     *
     * @param user we want to add.
     */
    public void addUsersToSystem(SNSUser user) {
        userList.add(user);
    }


}
