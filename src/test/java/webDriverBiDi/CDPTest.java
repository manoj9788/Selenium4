package webDriverBiDi;

import com.google.common.net.MediaType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.NetworkInterceptor;
import org.openqa.selenium.remote.http.Contents;
import org.openqa.selenium.remote.http.HttpResponse;
import org.openqa.selenium.remote.http.Route;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class CDPTest {

    private ChromeDriver driver;
    private DevTools devTools;
    private Wait<WebDriver> wait;

   // @BeforeAll
    public static void setUpWebDriverManager() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUpDriverInstance() {
        driver = new ChromeDriver();
        devTools = driver.getDevTools();
        devTools.createSession();
    }

    @Test
    public void InterceptWithNewContent() throws IOException, InterruptedException {
        Path path = Paths.get("src/test/resources/selenium-logo.png");
        byte[] bytes = Files.readAllBytes(path);

        Route route = Route.matching(req -> req.toString().contains("jpg"))
                .to(() -> req -> new HttpResponse()
                        .addHeader("Content-Type", MediaType.PNG.toString())
                        .setContent(Contents.bytes(bytes)));

        driver.get("https://automationbookstore.dev/");
        // Thread.sleep only meant for demo purposes!
        driver.manage().window().maximize();
        Thread.sleep(5000);
        try (NetworkInterceptor ignored = new NetworkInterceptor(driver, route)) {
            driver.get("https://automationbookstore.dev/");
        }
        // Thread.sleep only meant for demo purposes!
        Thread.sleep(5000);
    }

    @Test
    public void NetworkInterceptor() throws IOException, InterruptedException {
        Path path = Paths.get("src/test/resources/selenium-logo.png");
        byte[] logo = Files.readAllBytes(path);

        Route route = Route.matching(req -> req.getUri().contains("logo") && req.getUri().endsWith("svg"))
                .to(() -> req -> new HttpResponse()
                        .addHeader("Content-Type", MediaType.PNG.toString())
                        .setContent(Contents.bytes(logo)));

        try( NetworkInterceptor interceptor  = new NetworkInterceptor(driver, route)) {
            driver.get("https://brave.com/");
        }
        Thread.sleep(5000);

    }

    @Test
    public void authenticate() {
        ((HasAuthentication) driver).register(() -> new UsernameAndPassword("admin", "admin"));

        driver.get("https://the-internet.herokuapp.com");

        driver.findElement(By.linkText("Digest Authentication")).click();

        wait.until(ele -> ele.findElement(By.tagName("body")).getText().contains("Congratulations"));
    }

    @Test
    public void EmulateGeoLocation() {
        Map coordinates = Map.of(
                "latitude", 13,
                "longitude", 80,
                "accuracy", 1
        );
        ((ChromeDriver) driver).executeCdpCommand(("Emulation.setGeolocationOverride"), coordinates);

        driver.navigate().to("https://kawasaki-india.com/dealerlocator/");
    }

}
