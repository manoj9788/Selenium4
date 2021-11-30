package windowManagement;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

public class WindowTests {

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
    public void newWindowTest () throws InterruptedException {

        driver.get("https://applitools.com");

        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.navigate().to("https://selenium.dev");

        driver.switchTo().newWindow(WindowType.TAB);
        driver.navigate().to("https://selenium.dev");
        Thread.sleep(5000);
    }
    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
