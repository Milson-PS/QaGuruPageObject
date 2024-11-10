package utils;

import com.github.javafaker.Faker;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {

    static Faker faker = new Faker(new Locale("en"));

    public static String getRandomString(int len) {
        String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));

        return sb.toString();
    }

    public static String getRandomEmail() {
        return getRandomString(10) + "@qa.guru";
    }

    public static String getRandomAddress() {
        return getRandomString(10) + " " + getRandomString(10) + " " + getRandomString(10);

    }

    public static int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    // +3 (263) 253 - 66 - 12
    public static String getRandomPhoneIncorect() {
        return String.format("+%s (%s) %s - %s - %s", getRandomInt(1, 4), getRandomInt(111, 999),
                getRandomInt(111, 999), getRandomInt(11, 99), getRandomInt(11, 99));
    }


    public static String getRandomItemFromArray(String[] array) {
        int index = getRandomInt(0, array.length - 1);

        return array[index];
    }

    public static String getRandomCityByState(String state) {
        Map<String, String[]> citiesByState = new HashMap<>();
        citiesByState.put("NCR", new String[]{"Delhi", "Gurgaon", "Noida"});
        citiesByState.put("Uttar Pradesh", new String[]{"Lucknow", "Agra", "Merrut"});
        citiesByState.put("Haryana", new String[]{"Karnal", "Panipat"});
        citiesByState.put("Rajasthan", new String[]{"Jaiselmer", "Jaipur"});

        var citiesForThisState = citiesByState.get(state);
        return faker.options().option(citiesForThisState);
    }

    public static LocalDate getBirthDay(int minAge, int maxAge) {
        var birthDay = faker.date().birthday(minAge, maxAge);

        return LocalDate.ofInstant(birthDay.toInstant(), ZoneId.systemDefault());
    }


}