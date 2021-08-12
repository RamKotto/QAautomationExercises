package training_package;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WindowsHandleAndSize {
    By yourShopBtn = By.xpath("(//a[@class='tab  '])[1]");
    By whyIsItMatter = By.xpath("(//div[@class='loyaltyprimarynav_Label_2Cstp'])[1]");

    WebDriverWait wait;
    WebDriver driver;

    public static void main(String[] args) {
        WindowsHandleAndSize whas = new WindowsHandleAndSize();
        whas.initDriverAndWait(5);
        whas.goTo("https://store.steampowered.com/");
        whas.setWindowSize(1024, 768);

        whas.openInNewBlankBy(whas.yourShopBtn);
        whas.checkNewTabBy(whas.whyIsItMatter);

        whas.driverQuit();
    }

    public void initDriverAndWait(int sec) {
        WebDriverManager.getInstance(ChromeDriver.class).setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
    }

    private WebDriver getDriver() {
        return this.driver;
    }

    private WebDriverWait getWait() {
        return this.wait;
    }

    public void goTo(String str) {
        getDriver().get(str);
    }

    public void driverQuit() {
        getDriver().quit();
    }

    public void setWindowSize(int width, int height) {
        getDriver().manage().window().setSize(new Dimension(width, height));
    }

    public void openInNewBlankBy(By byElement) {
        WebElement elem = getWait().until(ExpectedConditions.visibilityOfElementLocated(byElement));
        Actions action = new Actions(getDriver());
        action
                .moveToElement(elem)
                .contextClick()
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.ENTER)
                .build()
                .perform();
    }

    public void checkNewTabBy(By by) {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(by));
        System.out.println("We are  in new tab!! Congrats!");
    }
}
