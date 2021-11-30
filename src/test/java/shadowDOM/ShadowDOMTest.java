package shadowDOM;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ShadowDOMTest {

    WebDriver driver;


    @BeforeAll
    public static void setUpWebDriverManager() {
        WebDriverManager.chromedriver().setup();
    }
    @BeforeEach
    public void setUpDriverInstance() {
        driver = new ChromeDriver();
    }

    @Test
    public void ShadowDOMTest () throws InterruptedException {

        //SearchContext c = new SearchContext();
       /* Selenium 3 way!
       WebElement shadowHost = driver.findElement(By.cssSelector(""));
        WebElement shadowRootElement = (WebElement) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].shadowRoot",shadowHost);
        WebElement shadowContent =shadowRootElement.findElement(By.cssSelector(""));*/
        driver.get("https://books-pwakit.appspot.com/");
        WebElement shadowHost = driver.findElement(By.xpath("//book-app"));

        WebElement shadowContent = shadowHost.getShadowRoot().findElement(By.cssSelector("#input"));
        shadowContent.sendKeys("Hello");
        Thread.sleep(5000);
    }
}
