package tests;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.Locale;

import static utils.RandomUtils.getBirthDay;
import static utils.RandomUtils.getRandomCityByState;

public class TestData {

    Faker faker = new Faker(new Locale("en"));


    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String userEmail = faker.internet().emailAddress();
    String userGender = faker.options().option("Male", "Female", "Other");
    String userNumber = faker.phoneNumber().subscriberNumber(10);
    String streetAddress = faker.address().streetAddress();
    LocalDate birthdayDay = getBirthDay(14, 35);
    String userSubject = faker.options().option("Accounting", "Maths", "Arts", "Social Studies", "Chemistry", "Computer Science", "Commerce", "Physics", "Economics");
    String userInterest = faker.options().option("Sports", "Reading", "Music");
    String picturePath = "bag.png";
    String userState = faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");
    String userCity = getRandomCityByState(userState);


}
