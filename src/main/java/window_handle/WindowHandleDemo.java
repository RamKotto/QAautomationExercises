package window_handle;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Set;

public class WindowHandleDemo {
    static String HOST = "https://www.kotak.com";
    static String path = "/en/home.html";

    static By loginButtonXpath = By.xpath("//a[@class='trk-login-net btn btn-primary']");

    public static void main(String[] args) {
        WebDriverManager.getInstance(ChromeDriver.class).setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // настройки для открытия браузера в режиме Full Screen
        driver.manage().window().maximize();

        // открываем нужную страницу
        driver.get(HOST + path);

        // нажимаем на элемент (логин) открывающий новую вкладку
        WebElement loginButton = wait.until(ExpectedConditions.presenceOfElementLocated(loginButtonXpath));
        loginButton.click();

        // текущий WindowHandle (Это ручка от главной страницы!)
        String homeWH = driver.getWindowHandle();

        // Получаем сэт вкладок:
        Set<String> handles = driver.getWindowHandles();

        // Принтим в консоль все имена открытых вкладок и создаем ссылку на новую вкладку:
        String loginWH = null;
        for (String handle : handles) {
            // принтим все оконные ручки =)
            System.out.println(handle);
            if (!homeWH.contentEquals(handle)) {
                loginWH = handle;
            }
        }

        // переключаемся на вкладку авторизации:
        driver.switchTo().window(loginWH);
        wait.until(ExpectedConditions.titleIs("Kotak Net Banking"));

        // проверяем, что мы переключились на вкладку с вводом логина:
        Assert.assertEquals(driver.getTitle(), "Kotak Net Banking");

        // переключаемся обратно, на домашнюю страницу
        driver.switchTo().window(homeWH);
        String homeTitle = "Savings Accounts, Personal Loans and Credit Cards - Kotak Mahindra Bank";
        wait.until(ExpectedConditions.titleIs(homeTitle));

        // проверяем, что переключились обратно:
        Assert.assertEquals(driver.getTitle(), homeTitle);

        // закрываем браузер
        driver.quit();
    }
}
