import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class CheckboxesTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkCheckboxes() {
        driver.get("http://the-internet.herokuapp.com/checkboxes");

        SoftAssert softAssert = new SoftAssert();
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("[type=checkbox]"));
        softAssert.assertFalse(checkboxes.get(0).isSelected());

        checkboxes.get(0).click();
        softAssert.assertTrue(checkboxes.get(0).isSelected());

        softAssert.assertTrue(checkboxes.get(1).isSelected());

        checkboxes.get(1).click();
        softAssert.assertFalse(checkboxes.get(1).isSelected());
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
