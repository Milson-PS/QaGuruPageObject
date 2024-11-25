package tests;

import org.junit.jupiter.api.*;
import pages.RegistrationPage;
import static io.qameta.allure.Allure.step;

class RegistrationTests extends TestBase {

    private RegistrationPage registrationPage;

    @BeforeEach
    void setUp() {
        registrationPage = new RegistrationPage();
    }

    @Tag("demoqa")
    @Test
    void fillFormTest() {
        step("Открыть страницу с формой", registrationPage::openPage);

        step("Заполнить поля", () -> {
            registrationPage.cleanBanner()
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
        });

        step("Проверка на заполнение полей", () -> {
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
        });
    }

    @Tag("demoqa")
    @Test
    void minimumAmountData() {
        step("Открыть страницу с формой", registrationPage::openPage);

        step("Заполнить минимально необходимые поля", () -> {
            registrationPage.cleanBanner()
                    .setFirstName("Pavel")
                    .setLastName("Milyukov")
                    .setUserEmail("milyukov@yandex.ru")
                    .setGender("Male")
                    .setNumber("0123456789")
                    .setDateOfBirth("16", "January", "1993")
                    .setHobbies("Sports")
                    .setAddress("ул.Ленина")
                    .submit();
        });

        step("Проверка на заполнение полей", () -> {
            registrationPage
                    .checkTableResponse("Student Name", "Pavel Milyukov")
                    .checkTableResponse("Student Email", "milyukov@yandex.ru")
                    .checkTableResponse("Gender", "Male")
                    .checkTableResponse("Mobile", "0123456789")
                    .checkTableResponse("Date of Birth", "16 January,1993")
                    .checkTableResponse("Hobbies", "Sports")
                    .checkTableResponse("Address", "ул.Ленина");
        });
    }
}