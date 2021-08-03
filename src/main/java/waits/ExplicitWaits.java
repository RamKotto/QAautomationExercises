package waits;

import browser_factory.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static browser_factory.BrowserFactory.*;

public class ExplicitWaits {
    By luckyButton = By.xpath("(//input[@class='RNmpXc'])[2]");
    String HOST = "https://www.google.com/";
    String titleResult = "Дудлы Google";

    void createDriver() {
        BrowserFactory.createBrowser("chrome");
    }

    public void driverQuit() {
        getDriver()
                .quit();
    }

    public void goToPage(String host) {
        getDriver()
                .get(host);
    }

    public static void main(String[] args) {
        ExplicitWaits ew = new ExplicitWaits();
        ew.createDriver();

        ew.goToPage(ew.HOST);

        // Задаем явное ожидание:
//        WebElement iAmLuckyButton = (new WebDriverWait(getDriver(), 10))
//                .until(ExpectedConditions
//                .presenceOfElementLocated(ew.luckyButton));

        // Второй вариант:
        setWait(getDriver(), 10);
        WebDriverWait wait = getWait();
        WebElement iAmLuckyButton = wait.until(ExpectedConditions.presenceOfElementLocated(ew.luckyButton));

        iAmLuckyButton.click();

        Assert.assertEquals(getDriver().getTitle(), ew.titleResult);

        ew.driverQuit();
    }

}

/*
WebDriver driver = new FirefoxDriver();
driver.get("http://some_url");
WebElement dynamicElement = (new WebDriverWait(driver, 10))
  .until(ExpectedConditions.presenceOfElementLocated(By.id("dynamicElement_id")));
 */
