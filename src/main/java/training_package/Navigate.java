package training_package;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class Navigate {
    public static void main(String[] args) {
        WebDriverManager.getInstance(ChromeDriver.class).setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.get("https://store.steampowered.com/");

        WebElement pointsStoreBtn = wait
                .until(ExpectedConditions
                        .visibilityOfElementLocated(By.xpath(
                                "(//a[@class='tab  '])[1]"
                        )));

        pointsStoreBtn.click();

        WebElement whyPointsNeedBtn = wait
                .until(ExpectedConditions
                        .visibilityOfElementLocated(By.xpath(
                                "//a[@href='/points/howitworks']"
                        )));

        // Навигация
        whyPointsNeedBtn.isDisplayed();

        driver.navigate().back();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "(//a[@class='tab  '])[1]"
        )));

        driver.navigate().forward();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//a[@href='/points/howitworks']"
        )));


        driver.quit();
    }
}
