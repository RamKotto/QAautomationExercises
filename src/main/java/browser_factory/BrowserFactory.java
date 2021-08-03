package browser_factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BrowserFactory {
    private static WebDriver driver;
    private static WebDriverWait wait;

    private static final Logger LOGGER = LogManager.getLogger(BrowserFactory.class);

    //  Singleton
    public static WebDriver createBrowser(String browserName) {
        if (BrowserFactory.driver == null) {
            if(browserName.equalsIgnoreCase("chrome")) {
                LOGGER.info("Создание Chrome драйвера");
                createChromeDriver();
            } else if (browserName.equalsIgnoreCase("firefox")) {
                LOGGER.info("Создание FireFox драйвера");
                createFireFoxDriver();
            } else if (browserName.equalsIgnoreCase("ie")) {
                LOGGER.info("Создание IE драйвера");
                createIExplorerDriver();
            }
        }
        return driver;
    }

    public static void setWait(WebDriver driver, int seconds) {
        LOGGER.info("Установка явного ожидания на: " + seconds + " секунд.");
        wait =  new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }

    public static WebDriver getDriver() {
        LOGGER.info("Получение драйвера из BrowserFactory");
        return driver;
    }

    public static WebDriverWait getWait() {
        LOGGER.info("Получение явного ожидания из BrowserFactory");
        return wait;
    }

    private static void createChromeDriver() {
        WebDriverManager.getInstance(ChromeDriver.class).setup();
        driver = new ChromeDriver();
    }

    private static void createFireFoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }

    private static void createIExplorerDriver() {
        WebDriverManager.iedriver().setup();
        driver = new InternetExplorerDriver();
    }
}
