package tqsua.P3SeleniumExtensions;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.seljup.SeleniumJupiter;

@ExtendWith(SeleniumJupiter.class)
class BlazeJupiterTest {

    @Test
    void testWithOneFirefox(FirefoxDriver driver) {
        driver.get("https://blazedemo.com/");
        driver.manage().window().setSize(new Dimension(866, 691));
        driver.findElement(By.name("fromPort")).click();
        driver.findElement(By.cssSelector(".form-inline:nth-child(1) > option:nth-child(1)")).click();
        driver.findElement(By.name("toPort")).click();
        {
            WebElement dropdown = driver.findElement(By.name("toPort"));
            dropdown.findElement(By.xpath("//option[. = 'London']")).click();
        }
        driver.findElement(By.cssSelector(".form-inline:nth-child(4) > option:nth-child(3)")).click();
        driver.findElement(By.cssSelector(".btn-primary")).click();
        assertThat(driver.findElement(By.cssSelector("h3")).getText(), is("Flights from Paris to London:"));
        driver.findElement(By.cssSelector("tr:nth-child(5) .btn")).click();
        driver.findElement(By.id("inputName")).click();
        driver.findElement(By.id("inputName")).sendKeys("Pedro Amaral");
        driver.findElement(By.id("creditCardNumber")).click();
        driver.findElement(By.id("creditCardNumber")).sendKeys("123456789");
        driver.findElement(By.cssSelector(".control-group:nth-child(8) > .controls")).click();
        driver.findElement(By.cssSelector(".btn-primary")).click();
        assertThat(driver.findElement(By.cssSelector("h1")).getText(), is("Thank you for your purchase today!"));
    }

}



