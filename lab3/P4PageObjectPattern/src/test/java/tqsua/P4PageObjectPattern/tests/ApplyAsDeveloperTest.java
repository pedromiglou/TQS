package tqsua.P4PageObjectPattern.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tqsua.P4PageObjectPattern.webpages.DeveloperApplyPage;
import tqsua.P4PageObjectPattern.webpages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApplyAsDeveloperTest {
    WebDriver driver;

    @BeforeEach
    public void setup(){
        //use FF Driver
        System.setProperty("webdriver.gecko.driver", "/opt/Webdriver/bin/geckodriver");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void applyAsDeveloper() {
        //Create object of HomePage Class
        HomePage home = new HomePage(driver);

        //Check if page is opened
        assertTrue(home.isPageOpened());

        //Go to DeveloperApplyPage
        home.clickOnDeveloperApplyButton();

        //Create object of DeveloperApplyPage
        DeveloperApplyPage applyPage =new DeveloperApplyPage(driver);

        //Check if page is opened
        assertTrue(applyPage.isPageOpened());

        //Fill up data
        applyPage.setDeveloper_email("dejan@toptal.com");
        applyPage.setDeveloper_full_name("Dejan Zivanovic Automated Test");
        applyPage.setDeveloper_password("password123");
        applyPage.setDeveloper_password_confirmation("password123");

        //Click on join
        //applyPage.clickOnJoin();
    }

    @AfterEach
    public void close(){
        driver.close();
    }
}