package browserOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Credentials;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.URI;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class BrowserOptionsTest {

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
    public void browserOptionsTest() {
        driver.get("https://applitools.com");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();

    }
}
