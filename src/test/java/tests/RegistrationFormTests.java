package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.RegistrationPage;


public class RegistrationFormTests {
    RegistrationPage registrationPage = new RegistrationPage();
    String firstName = "Igor";
    String lastName = "Zvyagintsev";
    String userEmail = "zvyagintsevis@yandex.ru";
    String gender = "Male";
    String mobile = "1234567890";
    String day = "13";
    String year = "1993";
    String month = "May";
    String subjects = "Commerce";
    String hobbySport = "Sport";
    String hobbyMusic = "Music";
    String picture = "nastroenie0007.jpg";
    String currentAddress = "Moscow";
    String state = "NCR";
    String city = "Delhi";

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub/";
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }

    @Test
    void successFillTest() {
        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserEmail(userEmail)
                .setGender(gender)
                .setMobile(mobile)
                .setDate(day, month, year)
                .setSubjects(subjects)
                .setHobbiesSports()
                .setHobbiesMusic()
                .setCurrentAddress(currentAddress)
                .setState(state)
                .setCity(city)
                .clickSubmit();


        // ???????????????? ???????????????????? ??????????
        registrationPage.checkForm(
                firstName, lastName, userEmail, gender, mobile,
                day, month, year, subjects, hobbySport, hobbyMusic,
                currentAddress, state, city);
    }

}
