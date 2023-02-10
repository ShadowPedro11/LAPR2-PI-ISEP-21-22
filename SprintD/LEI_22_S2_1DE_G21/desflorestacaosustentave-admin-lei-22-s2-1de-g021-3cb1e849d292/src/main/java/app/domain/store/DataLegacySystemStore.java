package app.domain.store;

import app.domain.dto.DataLegacySystemDto;
import app.domain.model.*;
import app.domain.model.VaccinationCenter.VaccinationCenter;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataLegacySystemStore {
    private String pathToCSV;
    private Company company;
    private SNSUserStore snsUserStore;
    private int numberUserSuccessfullyRegistered = 0;
    private int totalNumberOfUsersData = 0;
    private ArrayList<DataLegacySystemDto> userVaccinationDataList;
    private VaccinationCenter currentVC;
    private WaitingRoom waitingRoom;
    private RecoveryRoom recoveryRoom;


    public DataLegacySystemStore() {
        userVaccinationDataList = new ArrayList<>();
    }

    public void setCompany(Company company) {
        this.company = company;
        snsUserStore = company.getSNSUserStore();
        company.getCurrentVCOfCC();
        currentVC = company.getCurrentVC();
        waitingRoom = currentVC.getWaitingRoom();
        recoveryRoom = currentVC.getRecoveryRoom();
    }

    public int verifyCSVType(String pathToCSV) {
        File file = new File(pathToCSV);
        try {
            Scanner sc = new Scanner(file);
            String firstLine = sc.nextLine();
            if (firstLine.contains("SNSUSerNumber;VaccineName;Dose;LotNumber;ScheduledDateTime;ArrivalDateTime;NurseAdministrationDateTime;LeavingDateTime"))
                return 0;
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return 1;
    }

    public boolean read() {
        File file = new File(this.pathToCSV);
        userVaccinationDataList = new ArrayList<>();
        numberUserSuccessfullyRegistered = 0;
        totalNumberOfUsersData = 0;
        try {
            BufferedReader sc = new BufferedReader(new FileReader(file));
            String splitChar;
            if (verifyCSVType(this.pathToCSV) == 0) {
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
                totalNumberOfUsersData++;
                if (verifySNSUserNumber(Integer.parseInt(userInfo[0]))) {
                    if ((verifyIfSNSUserIsRegistered(Integer.parseInt(userInfo[0]))) && (verifyVaccineName(userInfo[1])) && verifyDose(userInfo[2], userInfo[1], Integer.parseInt(userInfo[0])) && verifyLotNumber(userInfo[3])) {
                        DataLegacySystemDto userVaccinationData = new DataLegacySystemDto(Integer.parseInt(userInfo[0]), userInfo[1], userInfo[2], userInfo[3],
                                LocalDateTime.of(splitDateTime(userInfo[4], 2), splitDateTime(userInfo[4], 0), splitDateTime(userInfo[4], 1), splitDateTime(userInfo[4], 3), splitDateTime(userInfo[4], 4)),
                                LocalDateTime.of(splitDateTime(userInfo[5], 2), splitDateTime(userInfo[5], 0), splitDateTime(userInfo[5], 1), splitDateTime(userInfo[5], 3), splitDateTime(userInfo[5], 4)),
                                LocalDateTime.of(splitDateTime(userInfo[6], 2), splitDateTime(userInfo[6], 0), splitDateTime(userInfo[6], 1), splitDateTime(userInfo[6], 3), splitDateTime(userInfo[6], 4)),
                                LocalDateTime.of(splitDateTime(userInfo[7], 2), splitDateTime(userInfo[7], 0), splitDateTime(userInfo[7], 1), splitDateTime(userInfo[7], 3), splitDateTime(userInfo[7], 4)));

                        userVaccinationDataList.add(userVaccinationData);
                        numberUserSuccessfullyRegistered++;
                    }
                } else {
                    throw new IllegalArgumentException("Invalid SNS User Number");
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public boolean verifyIfSNSUserIsRegistered(int snsUserNumber){
        return snsUserStore.getUserBySNSNumber(snsUserNumber) != null;
    }
    public boolean verifySNSUserNumber(int snsUserNumber) {
        if (snsUserNumber < 99999999 || snsUserNumber > 999999999) {
            return false;
        }
        return true;
    }

    public boolean verifyVaccineName(String vaccineName) {
        if ((company.getVaccineByName(vaccineName) != null) && (company.getVaccineByName(vaccineName).getName() != null) ) {
            if (company.getVaccineByName(vaccineName).getName().equals(vaccineName)) {
                return true;
            }
        }
        return false;
    }

    public boolean verifyDose(String dose, String vaccineName, int snsNumber) {
        if (dose.equals("Primeira") || dose.equals("Segunda") || dose.equals("Terceira") || dose.equals("Quarta") || dose.equals("Quinta")) {
            long userAge = snsUserStore.getUserBySNSNumber(snsNumber).getAge();
            List<VaccineAdministration> vaccineAdministrationsList = company.getVaccineByName(vaccineName).getAdministrations();
            VaccineAdministration administration = null;
            for (VaccineAdministration vaccineAdministration : vaccineAdministrationsList) {
                if ((vaccineAdministration.getMinAgeGroup() <= userAge) && (vaccineAdministration.getMaxAgeGroup() >= userAge)) {
                    administration = vaccineAdministration;
                }
            }
            int doseInt = 0;
            switch (dose) {
                case "Primeira":
                    doseInt = 1;
                    break;
                case "Segunda":
                    doseInt = 2;
                    break;
                case "Terceira":
                    doseInt = 3;
                    break;
                case "Quarta":
                    doseInt = 4;
                    break;
                case "Quinta":
                    doseInt = 5;
                    break;
            }
            if (administration != null && (administration.getNumberOfDoses() >= doseInt)) {
                return true;
            }
        }
        return false;
    }


    public boolean verifyLotNumber(String lotNumber) {
        if (lotNumber.charAt(5) == '-') {
            String[] lotNumberSplitted = lotNumber.split("-");
            Character[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
            Character[] algarisms = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
            boolean checkerLeftFromHyphen = false;
            boolean checkerRightFromHyphen = false;
            for (int i = 0; i < lotNumberSplitted[0].length(); i++) {
                for (Character character : alphabet) {
                    if ((lotNumberSplitted[0].charAt(i) == character)) {
                        checkerLeftFromHyphen = true;
                    }
                }
                for (Character algarism : algarisms) {
                    if (lotNumberSplitted[0].charAt(i) == algarism) {
                        checkerLeftFromHyphen = true;
                    }
                }

            }

            for (int i = 0; i < lotNumberSplitted[1].length(); i++) {
                for (Character character : alphabet) {
                    if ((lotNumberSplitted[1].charAt(i) == character)) {
                        checkerRightFromHyphen = true;
                    }
                }
                for (Character algarism : algarisms) {
                    if (lotNumberSplitted[1].charAt(i) == algarism) {
                        checkerRightFromHyphen = true;
                    }
                }
            }
            return (checkerLeftFromHyphen && checkerRightFromHyphen);
        }
        return false;
    }


    public int getTotalNumberOfUsersData() {
        return totalNumberOfUsersData;
    }

    public int getNumberUserSuccessfullyRegistered() {
        return numberUserSuccessfullyRegistered;
    }

    public int splitDateTime(String date, int index) {
        String[] dateTimeSplitted = date.split(" ");
        String[] dateSplitted = dateTimeSplitted[0].split("/");
        String[] timeSplitted = dateTimeSplitted[1].split(":");
        String[] dayMonthYearHourMinute = new String[5];
        for (int i = 0; i < 3; i++) {
            dayMonthYearHourMinute[i] = dateSplitted[i];
        }
        int j = 0;
        for (int i = 3; i < 5; i++) {
            dayMonthYearHourMinute[i] = timeSplitted[j];
            j++;
        }
        //index = 0 is the month
        //index = 1 is the day
        //index = 2 is the year
        //index = 3 is the hour
        //index = 4 is the minute
        if (index == 0) {
            return Integer.parseInt(dayMonthYearHourMinute[0]);
        } else if (index == 1) {
            return Integer.parseInt(dayMonthYearHourMinute[1]);
        } else if (index == 2) {
            return Integer.parseInt(dayMonthYearHourMinute[2]);
        } else if (index == 3) {
            return Integer.parseInt(dayMonthYearHourMinute[3]);
        } else if (index == 4) {
            return Integer.parseInt(dayMonthYearHourMinute[4]);
        }
        return -1;
    }

    public void selectionSortingAlgorithmArrivalDateTimeAscendant() {
        //ascendant - first to last
        for (int i = 0; i < userVaccinationDataList.size() - 1; i++) {
            int index = i;
            for (int j = i + 1; j < userVaccinationDataList.size(); j++) {
                if (userVaccinationDataList.get(j).getArrivalDateTime().isBefore(userVaccinationDataList.get(index).getArrivalDateTime())) {
                    index = j;//searching for lowest index
                }
            }
            DataLegacySystemDto userVaccinationDataSwap = userVaccinationDataList.get(index);
            userVaccinationDataList.set(index, userVaccinationDataList.get(i));
            userVaccinationDataList.set(i, userVaccinationDataSwap);
        }
    }

    public void selectionSortingAlgorithmArrivalDateTimeDescendant() {
        //descendant - last to first
        for (int i = 0; i < userVaccinationDataList.size() - 1; i++) {
            int index = i;
            for (int j = i + 1; j < userVaccinationDataList.size(); j++) {
                if (userVaccinationDataList.get(j).getArrivalDateTime().isAfter(userVaccinationDataList.get(index).getArrivalDateTime())) {
                    index = j;//searching for lowest index
                }
            }
            DataLegacySystemDto userVaccinationDataSwap = userVaccinationDataList.get(index);
            userVaccinationDataList.set(index, userVaccinationDataList.get(i));
            userVaccinationDataList.set(i, userVaccinationDataSwap);
        }
    }

    public void selectionSortingAlgorithmLeavingDateTimeAscendant() {
        //ascendant - first to last
        for (int i = 0; i < userVaccinationDataList.size() - 1; i++) {
            int index = i;
            for (int j = i + 1; j < userVaccinationDataList.size(); j++) {
                if (userVaccinationDataList.get(j).getLeavingDateTime().isBefore(userVaccinationDataList.get(index).getLeavingDateTime())) {
                    index = j;//searching for lowest index
                }
            }
            DataLegacySystemDto userVaccinationDataSwap = userVaccinationDataList.get(index);
            userVaccinationDataList.set(index, userVaccinationDataList.get(i));
            userVaccinationDataList.set(i, userVaccinationDataSwap);
        }
    }

    public void selectionSortingAlgorithmLeavingDateTimeDescendant() {
        //descendant - last to first
        for (int i = 0; i < userVaccinationDataList.size() - 1; i++) {
            int index = i;
            for (int j = i + 1; j < userVaccinationDataList.size(); j++) {
                if (userVaccinationDataList.get(j).getLeavingDateTime().isAfter(userVaccinationDataList.get(index).getLeavingDateTime())) {
                    index = j;//searching for lowest index
                }
            }
            DataLegacySystemDto userVaccinationDataSwap = userVaccinationDataList.get(index);
            userVaccinationDataList.set(index, userVaccinationDataList.get(i));
            userVaccinationDataList.set(i, userVaccinationDataSwap);
        }
    }


    public void bubbleSortingAlgorithmArrivalDateTimeAscendant() {
        //ascendant - first to last
        for (int i = 0; i < userVaccinationDataList.size() - 1; i++) {
            for (int j = 0; j < userVaccinationDataList.size() - i - 1; j++) {
                if (userVaccinationDataList.get(j + 1).getArrivalDateTime().isBefore(userVaccinationDataList.get(j).getArrivalDateTime())) {
                    DataLegacySystemDto userVaccinatioDataSwap = userVaccinationDataList.get(j);
                    userVaccinationDataList.set(j, userVaccinationDataList.get(j + 1));
                    userVaccinationDataList.set(j + 1, userVaccinatioDataSwap);
                }
            }
        }
    }

    public void bubbleSortingAlgorithmArrivalDateTimeDescendant() {
        //descendant - last to first
        for (int i = 0; i < userVaccinationDataList.size() - 1; i++) {

            for (int j = 0; j < userVaccinationDataList.size() - i - 1; j++) {
                if (userVaccinationDataList.get(j + 1).getArrivalDateTime().isAfter(userVaccinationDataList.get(j).getArrivalDateTime())) {
                    DataLegacySystemDto userVaccinationDataSwap = userVaccinationDataList.get(j);
                    userVaccinationDataList.set(j, userVaccinationDataList.get(j + 1));
                    userVaccinationDataList.set(j + 1, userVaccinationDataSwap);
                }
            }
        }
    }

    public void bubbleSortingAlgorithmLeavingDateTimeAscendant() {
        //ascendant - first to last
        for (int i = 0; i < userVaccinationDataList.size() - 1; i++) {
            for (int j = 0; j < userVaccinationDataList.size() - i - 1; j++) {
                if (userVaccinationDataList.get(j + 1).getLeavingDateTime().isBefore(userVaccinationDataList.get(j).getLeavingDateTime())) {
                    DataLegacySystemDto userVaccinationDataSwap = userVaccinationDataList.get(j);
                    userVaccinationDataList.set(j, userVaccinationDataList.get(j + 1));
                    userVaccinationDataList.set(j + 1, userVaccinationDataSwap);
                }
            }
        }
    }

    public void bubbleSortingAlgorithmLeavingDateTimeDescendant() {
        //descendant - last to first
        for (int i = 0; i < userVaccinationDataList.size() - 1; i++) {
            for (int j = 0; j < userVaccinationDataList.size() - i - 1; j++) {
                if (userVaccinationDataList.get(j + 1).getLeavingDateTime().isAfter(userVaccinationDataList.get(j).getLeavingDateTime())) {
                    DataLegacySystemDto userVaccinationDataSwap = userVaccinationDataList.get(j);
                    userVaccinationDataList.set(j, userVaccinationDataList.get(j + 1));
                    userVaccinationDataList.set(j + 1, userVaccinationDataSwap);
                }
            }
        }
    }

    public boolean checkIfUserWasInWaitingRoom(SNSUser snsUser) {
        List<SNSUser> wasInWaitingRoom = waitingRoom.getWasInWaitingRoom();
        for (SNSUser snsUserThatWasInWaitingRoom : wasInWaitingRoom) {
            if (snsUser.getSnsNumber() == snsUserThatWasInWaitingRoom.getSnsNumber()) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfUserWasInRecoveryRoom(SNSUser snsUser) {
        List<SNSUser> wasInRecoveryRoom = recoveryRoom.getWasInRecoveryRoom();
        for (SNSUser snsUserThatWasInRecoveryRoom : wasInRecoveryRoom) {
            if (snsUser.getSnsNumber() == snsUserThatWasInRecoveryRoom.getSnsNumber()) {
                return true;
            }
        }
        return false;
    }

    public void addToWaitingAndRecoveryRoomRegisters() {
        for (DataLegacySystemDto userVaccinationData : userVaccinationDataList) {
            SNSUser snsUser = snsUserStore.getUserBySNSNumber(userVaccinationData.getSnsNumber());

            if (!checkIfUserWasInWaitingRoom(snsUser)) {
                waitingRoom.addToWaitingRoom(snsUser,
                        LocalDate.of(userVaccinationData.getArrivalDateTime().getYear(), userVaccinationData.getArrivalDateTime().getMonth(), userVaccinationData.getArrivalDateTime().getDayOfMonth()),
                        LocalTime.of(userVaccinationData.getArrivalDateTime().getHour(), userVaccinationData.getArrivalDateTime().getMinute()));
            }

            if (!checkIfUserWasInRecoveryRoom(snsUser)) {
                recoveryRoom.addToRecoveryRoomRegisters(snsUser,
                        LocalDate.of(userVaccinationData.getLeavingDateTime().getYear(), userVaccinationData.getLeavingDateTime().getMonth(), userVaccinationData.getLeavingDateTime().getDayOfMonth()),
                        LocalTime.of(userVaccinationData.getLeavingDateTime().getHour(), userVaccinationData.getLeavingDateTime().getMinute()));
            }

        }
    }

    public boolean validatePathToCSV(String pathToCSV) {
        File file = new File(pathToCSV);
        if (file.exists()) {
            this.pathToCSV = pathToCSV;
            return true;
        }
        return false;
    }

    public String everythingToString() {
        String finalText = "";
        for (DataLegacySystemDto userVaccinationData : userVaccinationDataList) {
            finalText = String.format(finalText + userVaccinationData.toString(snsUserStore, company));
        }
        return finalText;
    }

}
