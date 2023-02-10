package app.domain.model;

import java.util.Random;

public class Password {

    public static String generatePassword() {

        final int length = 7;
        String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "1234567890";
        String combinedChars = capitalCaseLetters + numbers;
        Random random = new Random();
        char[] password = new char[length];


        password[0] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
        password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
        password[2] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));

        password[3] = numbers.charAt(random.nextInt(numbers.length()));
        password[4] = numbers.charAt(random.nextInt(numbers.length()));
        for(int i = 5; i< length ; i++) {
            password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
        }
        return String.valueOf(password);
    }
}
