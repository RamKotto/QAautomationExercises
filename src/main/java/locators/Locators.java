package locators;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class Locators {
    WebDriver driver;
    WebDriverWait wait;

    String HOST = "https://comaqa.gitbook.io";
    String path = "/selenium-webdriver-lectures/selenium-webdriver.-vvedenie/tipy-lokatorov";

    public static void main(String[] args) {
        Locators loc = new Locators();
        loc.locatorsTest();

    }

    public void locatorsTest() {
        WebDriverManager.getInstance(ChromeDriver.class).setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.manage().window().maximize();
        driver.get(HOST + path);

        // By ID:
        WebElement byId = driver.findElement(By.id("__GITBOOK__ROOT__CLIENT__"));

        // By.name:
        WebElement byName = driver.findElement(By.name("viewport"));

        // By.className
        WebElement byClassName = driver.findElement(By.className("reset-3c756112--pageItemWithChildren-56f27afc"));

        // By.TagName
        WebElement byTag = driver.findElement(By.tagName("div"));

        // By.LinkText
        driver.get("http://the-internet.herokuapp.com/");
        System.out.println("http://the-internet.herokuapp.com/");
        WebElement byLinkText = driver.findElement(By.linkText("Multiple Windows"));
        System.out.println(byLinkText.getAttribute("href"));

        // By.PartialLinkText
        System.out.println("http://the-internet.herokuapp.com/");
        WebElement byPartLinkText = driver.findElement(By.linkText("Key Presses"));
        System.out.println(byPartLinkText.getAttribute("href"));

        // By.cssSelector
        WebElement byCssSelector = driver.findElement(By.cssSelector("div.large-12"));
        System.out.println(byCssSelector.getAttribute("id"));

        // By.xpath
        WebElement byXpath = driver.findElement(By.xpath("//a[@href='/abtest']"));
        System.out.println(byXpath.getText());

        driver.quit();
    }
}

/*
1.By.id

????????????:
<div id="element_id">
     <p>some content</p>
</div>

?????????? ????????????????:
WebElement element = driver.findElement(By.id("element_id"));


2.By.name

????????????:
<div name="element_name">
     <p>some content</p>
</div>

?????????? ????????????????:
WebElement element = driver.findElement(By.name("element_name"));


3.By.className

????????????:
<img class="element_class">

?????????? ????????????????:
WebElement element = driver.findElement(By.className("element_class"));


4.By.TagName

????????????:
<div>
     <a class="logo" ref="...">...</a>
     <a class="support" ref="...">...</a>
</div>

?????????? ????????????????:
List<WebElement> elements = driver.findElements(By.tagName("a"));


5.By.LinkText

????????????:
<div>
     <a ref="...">text</a>
     <a ref="...">Another text</a>
</div>

?????????? ????????????????:
WebElement element = driver.findElement(By.linkText("text"));


6.By.PartialLinkText

?????????? ????????????????:
WebElement element = driver.findElements(By.partialLinkText("text"));


7.By.cssSelector

????????????:
<div class='main'>
     <p>text</p>
     <p>Another text</p>
</div>

?????????? ????????????????:
WebElement element=driver.FindElement(By.cssSelector("div.main"));


8.By.XPath

????????????:
<div class='main'>
     <p>text</p>
     <p>Another text</p>
</div>

?????????? ????????????????:
WebElement element = driver.findElement(By.xpath("//div[@class='main']"));
 */
