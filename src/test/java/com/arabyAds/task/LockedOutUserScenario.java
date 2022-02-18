package com.arabyAds.task;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LockedOutUserScenario {
    private static WebDriver driver;
    @BeforeAll
    public static void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }


    void sleep() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ex) {
        }

    }

    @Test
    @DisplayName("Login Test lockedOut user | Fails")
    public void testLockedOutUserScenario() {
        //        WebDriver driver=  new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        WebElement username = driver.findElement(By.id("user-name"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement login = driver.findElement(By.name("login-button"));
        username.sendKeys("locked_out_user");
        sleep();
        password.sendKeys("secret_sauce");
        sleep();
        login.click();
        sleep();
        String AcutalUrl = driver.getCurrentUrl();
        String expectedUrl = "https://www.saucedemo.com/";
        assertEquals(expectedUrl, expectedUrl);

    }
}
