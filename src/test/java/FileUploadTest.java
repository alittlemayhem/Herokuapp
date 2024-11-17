import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.time.Duration;

public class FileUploadTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkFileUploadUsingButton() {
        driver.get("https://the-internet.herokuapp.com/upload");

        File file = new File("src/test/files/forUpload.txt");
        driver.findElement(By.id("file-upload")).sendKeys(file.getAbsolutePath());
        driver.findElement(By.id("file-submit")).click();

        String title = driver.findElement(By.tagName("h3")).getText();
        String fileName = driver.findElement(By.id("uploaded-files")).getText();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(
                title,
                "File Uploaded!",
                "Incorrect title.");
        softAssert.assertEquals(
                fileName,
                "forUpload.txt",
                "Wrong file is mentioned."
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}

