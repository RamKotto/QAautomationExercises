package training_package;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent;

public class ScreenShots {
    public static void main(String[] args) {
        WebDriverManager.getInstance(ChromeDriver.class).setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.get("https://babyenglish.club/verb-tenses");

        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File("c:\\tmp\\screenshot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver.quit();
    }
}
