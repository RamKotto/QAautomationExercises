package waits;

import browser_factory.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.time.Duration;

public class ImplicitWaits {
    static By luckyInput = By.xpath("(//input[@class='RNmpXc'])[2]");
    static String HOST = "https://www.google.com/";
    static String titleResult = "Дудлы Google";

    public void driverCreate() {
        BrowserFactory.createBrowser("chrome");
    }

    public void driverQuit() {
        BrowserFactory
                .getDriver()
                .quit();
    }

    public void clickIAmLuckyButton(By luckyInput) {
        BrowserFactory
                .getDriver()
                .findElement(luckyInput)
                .click();
    }

    public void goToPage(String host) {
        BrowserFactory
                .getDriver()
                .get(host);
    }

    public static void main(String[] args) {
        // Создание драйвера и неявного ожидания:
        ImplicitWaits iw = new ImplicitWaits();
        iw.driverCreate();
        WebDriver driver = BrowserFactory.getDriver();

        // Неявное ожидание:
        driver.manage()
                .timeouts()
                .implicitlyWait(Duration.ofSeconds(10));

        // Переходим на страницу гугл:
        iw.goToPage(HOST);

        // Кликаем на кнопку "Мне повезет!"
        // Задав неявное ожидание, селениум будет ждать появление элемента,
        // для совершения действия.
        iw.clickIAmLuckyButton(luckyInput);

        // Протестируем корректность нажатия на кнопку:
        Assert.assertEquals(driver.getTitle(), titleResult);

        // Закрываем драйвер
        iw.driverQuit();
    }
}

/*
    WebDriver driver = new FirefoxDriver();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.get("http://some_url");
    WebElement dynamicElement = driver.findElement(By.id("dynamicElement_id"));
 */
