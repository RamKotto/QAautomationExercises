package actions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class DragAndDrop {
    static String HOST = "https://crossbrowsertesting.github.io";
    static String path = "/drag-and-drop";

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.getInstance(ChromeDriver.class).setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.manage().window().maximize();

        driver.get(HOST + path);


        WebElement boxOne = driver.findElement(By.id("draggable"));
        WebElement boxTwo = driver.findElement(By.id("droppable"));

        // создаем объект Actions и перетаскиваем коробку A в коробку B
        Actions actions = new Actions(driver);

        actions
                .moveToElement(boxOne)
                .clickAndHold()
                .moveToElement(boxTwo)
                .release()
                .build()
                .perform();


        // проверяем, что действие произведено корректно:
        WebElement result = driver.findElement(By.xpath("//div[@id='droppable']/p"));
        Assert.assertEquals(result.getText(), "Dropped!");

        driver.quit();
    }
}
