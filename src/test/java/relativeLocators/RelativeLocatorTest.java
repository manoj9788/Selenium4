package relativeLocators;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class RelativeLocatorTest {

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
    public void testToTheRightOf() {

        driver.get("https://automationbookstore.dev/");

        WebElement firstBook = driver.findElement(By.cssSelector("#pid1_title"));
        System.out.println("1: " + firstBook.getText());

        WebElement bookToTheRightOf = driver.findElement(with(By.tagName("h2")).toRightOf(firstBook));
        System.out.println("2: " + bookToTheRightOf.getText());

    }

    @Test
    public void testToTheLeftOfAndBelow() {

        driver.get("https://automationbookstore.dev/");

        WebElement firstBook = driver.findElement(By.cssSelector("#pid1_title"));
        WebElement AdvSelBook = driver.findElement(By.cssSelector("#pid6_title"));


        WebElement jtBook = driver.findElement(with(By.tagName("h2")).toLeftOf(AdvSelBook).below(firstBook));
        System.out.println("Final Book: " + jtBook.getText());

    }
}
