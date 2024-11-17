import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

public class FileDownloadTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkDownload() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/download");

        String fileName = "random_data.txt";

        String dirPath = "C:/Users/Jadwiga/Downloads";

        File folder = new File(dirPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        driver.findElement(By.linkText(fileName)).click();
        Thread.sleep(1000);

        File[] listOfFiles = folder.listFiles();

        boolean flag = false;

        for (File file : listOfFiles) {
            String path = file.getAbsolutePath();
            if (path.contains(fileName)) {
                flag = true;
                break;
            }
        }

        Assert.assertTrue(
                flag,
                "Downloaded was not successful!");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
