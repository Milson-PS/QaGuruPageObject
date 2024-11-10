package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import utils.RandomUtils;


public class RegistrationFillFormTest extends TestBase {

    @Test
    void fillFormTest() {
        registrationPage.openPage()
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setUserEmail(testData.userEmail)
                .setGender(testData.userGender)
                .setNumber(testData.userNumber)
                .setDateOfBirth(testData.birthdayDay)
                .setSubjects(testData.userSubject)
                .setHobbies(testData.userInterest)
                .setPicture(testData.picturePath)
                .setAddress(testData.streetAddress)
                .setState(testData.userState)
                .setCity(testData.userCity)
                .Submit();

        registrationPage
                .checkTableResponse("Student Name", testData.firstName + " " + testData.lastName)
                .checkTableResponse("Student Email", testData.userEmail)
                .checkTableResponse("Gender", testData.userGender)
                .checkTableResponse("Mobile", testData.userNumber)
                .checkTableResponse("Date of Birth", testData.birthdayDay.format(formatter))
                .checkTableResponse("Subjects", testData.userSubject)
                .checkTableResponse("Hobbies", testData.userInterest)
                .checkTableResponse("Picture", testData.picturePath)
                .checkTableResponse("Address", testData.streetAddress)
                .checkTableResponse("State and City", testData.userState + " " + testData.userCity);
    }

        @Test
        void minimumAmountData() {
            registrationPage.openPage()
                    .setFirstName(testData.firstName)
                    .setLastName(testData.lastName)
                    .setUserEmail(testData.userEmail)
                    .setGender(testData.userGender)
                    .setNumber(testData.userNumber)
                    .setDateOfBirth(testData.birthdayDay)
                    .setHobbies(testData.userInterest)
                    .setAddress(testData.streetAddress)
                    .Submit();

            registrationPage
                    .checkTableResponse("Student Name", testData.firstName + " " + testData.lastName)
                    .checkTableResponse("Student Email", testData.userEmail)
                    .checkTableResponse("Gender", testData.userGender)
                    .checkTableResponse("Mobile", testData.userNumber)
                    .checkTableResponse("Date of Birth", testData.birthdayDay.format(formatter))
                    .checkTableResponse("Hobbies", testData.userInterest)
                    .checkTableResponse("Address", testData.streetAddress);
        }
        @Test
        void incorrectPhoneNumberTest() {
            registrationPage.openPage()
                    .setFirstName(testData.firstName)
                    .setLastName(testData.lastName)
                    .setGender(testData.userGender)
                    .setNumber(RandomUtils.getRandomPhoneIncorect())
                    .setDateOfBirth(testData.birthdayDay)
                    .Submit();

            registrationPage.checkResultIsNotVisible();

        }
    }










