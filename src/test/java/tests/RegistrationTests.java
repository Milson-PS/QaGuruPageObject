package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.RegistrationPage;
import pages.helpers.Attach;

import java.util.Map;


public class RegistrationTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();


    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
//        Configuration.browser = "chrome";
        Configuration.timeout = 10000;
//        Configuration.holdBrowserOpen = true;
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();

    }




    @Test
    @Tag("demoqa")
    void fillFormTest() {
        registrationPage.openPage()
                .cleanBanner()
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
                .submit();

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
    @Tag("demoqa")
    void minimumAmountData() {
        registrationPage.openPage()
                .cleanBanner()
                .setFirstName("Pavel")
                .setLastName("Milyukov")
                .setUserEmail("milyukov@yandex.ru")
                .setGender("Male")
                .setNumber("0123456789")
                .setDateOfBirth("16", "January", "1993")
                .setHobbies("Sports")
                .setAddress("ул.Ленина")
                .submit();

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
    @Tag("demoqa")
    void incorrectPhoneNumberTest() {
        registrationPage.openPage()
                .cleanBanner()
                .setFirstName("FirstName")
                .setLastName("LastName")
                .setGender("Male")
                .setNumber("5553535")
                .setDateOfBirth("17", "July", "2003")
                .submit();

        registrationPage.checkResultIsNotVisible();

    }
}