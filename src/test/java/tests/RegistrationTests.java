package tests;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;


public class RegistrationTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    void fillFormTest() {
        registrationPage.openPage()
                .setFirstName("Pavel")
                .setLastName("Milyukov")
                .setUserEmail("milyukov@yandex.ru")
                .setGender("Male")
                .setNumber("0123456789")
                .setDateOfBirth("16", "January", "1993")
                .setSubjects("Computer Science")
                .setHobbies("Sports")
                .setPicture("bag.png")
                .setAddress("ул.Ленина")
                .setState("NCR")
                .setCity("Noida")
                .Submit();

        registrationPage
                .checkTableResponse("Student Name", "Pavel Milyukov")
                .checkTableResponse("Student Email", "milyukov@yandex.ru")
                .checkTableResponse("Gender", "Male")
                .checkTableResponse("Mobile", "0123456789")
                .checkTableResponse("Date of Birth", "16 January,1993")
                .checkTableResponse("Subjects", "Computer Science")
                .checkTableResponse("Hobbies", "Sports")
                .checkTableResponse("Picture", "bag.png")
                .checkTableResponse("Address", "ул.Ленина")
                .checkTableResponse("State and City", "NCR Noida");
    }

    @Test
    void minimumAmountData() {
        registrationPage.openPage()
                .setFirstName("Pavel")
                .setLastName("Milyukov")
                .setUserEmail("milyukov@yandex.ru")
                .setGender("Male")
                .setNumber("0123456789")
                .setDateOfBirth("16", "January", "1993")
                .setHobbies("Sports")
                .setAddress("ул.Ленина")
                .Submit();

        registrationPage
                .checkTableResponse("Student Name", "Pavel Milyukov")
                .checkTableResponse("Student Email", "milyukov@yandex.ru")
                .checkTableResponse("Gender", "Male")
                .checkTableResponse("Mobile", "0123456789")
                .checkTableResponse("Date of Birth", "16 January,1993")
                .checkTableResponse("Hobbies", "Sports")
                .checkTableResponse("Address", "ул.Ленина");
    }

    @Test
    void incorrectPhoneNumberTest() {
        registrationPage.openPage()
                .setFirstName("FirstName")
                .setLastName("LastName")
                .setGender("Male")
                .setNumber("5553535")
                .setDateOfBirth("17", "July", "2003")
                .Submit();

        registrationPage.checkResultIsNotVisible();

    }
}