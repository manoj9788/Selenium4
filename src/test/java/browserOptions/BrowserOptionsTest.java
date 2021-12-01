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
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class BrowserOptionsTest {

    WebDriver driver;

    @BeforeAll
    public static void setUpWebDriverManager() {
        WebDriverManager.chromedriver().setup();

    }
    @BeforeEach
    public void setUpDriverInstance() throws MalformedURLException {
       // driver = new ChromeDriver();

        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeOptions);

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
