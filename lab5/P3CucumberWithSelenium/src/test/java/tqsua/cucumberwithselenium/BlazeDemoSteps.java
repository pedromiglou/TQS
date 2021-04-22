package tqsua.cucumberwithselenium;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BlazeDemoSteps {

    private WebDriver webDriver;

    @When("I navigate to {string}")
    public void iNavigateTo(String url) {
        WebDriverManager.firefoxdriver().setup();
        webDriver = new FirefoxDriver();
        webDriver.get(url);
    }

    @And("I select origin Paris")
    public void iSelectOriginParis() {
        webDriver.findElement(By.name("fromPort")).click();
        webDriver.findElement(By.cssSelector(".form-inline:nth-child(1) > option:nth-child(1)")).click();
    }

    @And("I select destination London")
    public void iSelectDestinationLondon() {
        webDriver.findElement(By.name("toPort")).click();
        WebElement dropdown = webDriver.findElement(By.name("toPort"));
        dropdown.findElement(By.xpath("//option[. = 'London']")).click();
    }

    @And("I click on {string}")
    public void iClickOn(String arg0) {
        webDriver.findElement(By.cssSelector(".btn-primary")).click();
    }

    @Then("I should be shown the title {string}")
    public void iShouldBeShownTheTitleFlightsFromParisToLondon(String title) {
        assertThat(webDriver.findElement(By.cssSelector("h3")).getText(), is(title));
    }

    @When("I input the name {string}")
    public void iInputTheNamePedroAmaral(String name) {
        webDriver.findElement(By.cssSelector("tr:nth-child(5) .btn")).click();
        webDriver.findElement(By.id("inputName")).click();
        webDriver.findElement(By.id("inputName")).sendKeys(name);
    }

    @And("input the credit card number {int}")
    public void inputTheCreditCardNumber(int number) {
        webDriver.findElement(By.id("creditCardNumber")).click();
        webDriver.findElement(By.id("creditCardNumber")).sendKeys(String.valueOf(number));
    }

    @And("I choose the flight from United Airlines")
    public void iChooseTheFlightFromUnitedAirlines() {
        webDriver.findElement(By.cssSelector(".control-group:nth-child(8) > .controls")).click();
        webDriver.findElement(By.cssSelector(".btn-primary")).click();
    }

    @Then("I should see the title {string}")
    public void iShouldSeeTheTitleThankYouForYourPurchaseToday(String title) {
        assertThat(webDriver.findElement(By.cssSelector("h1")).getText(), is(title));
        webDriver.quit();
    }
}
