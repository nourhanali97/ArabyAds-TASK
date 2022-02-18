package com.arabyAds.task;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.http.util.Asserts;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.support.ui.Select;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PerformanceUserScenario {
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
    @DisplayName("Performance User Login Test| Success")
    @Order(1)
    public void testPerformanceUserLogin() {
//        WebDriver driver=  new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        WebElement username = driver.findElement(By.id("user-name"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement login = driver.findElement(By.name("login-button"));
        username.sendKeys("performance_glitch_user");
        sleep();
        password.sendKeys("secret_sauce");
        sleep();
        StopWatch stopWatch= new StopWatch();
        stopWatch.start();
        login.click();
        stopWatch.stop();
        System.out.println(stopWatch.getTime()+" ");
        if (stopWatch.getTime() > 1000L)
        {
            System.out.println("Login API has performance issue it takes more than one second to respond");
        }
        String AcutalUrl = driver.getCurrentUrl();
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        assertEquals(expectedUrl, AcutalUrl);

    }

    @Test
    @DisplayName("Performance User Add item to cart | Success")
    @Order(2)
    public void TestPerformanceUserAddProductToCart() {
        Select sortby = new Select(driver.findElement(By.className("product_sort_container")));
        sortby.selectByValue("lohi");
        sleep();

        List<WebElement> productList = driver.findElements(By.className("inventory_item"));
        WebElement webElement = productList.get(0);
        webElement.findElement(By.className("btn_inventory")).click();
        driver.findElement(By.className("shopping_cart_link")).click();
        sleep();

    }

    @Test
    @DisplayName("Performance User make order checkout | Success")
    @Order(3)

    public void TestPerformanceUserCheckOut() {
        WebElement checkoutBtn = driver.findElement(By.name("checkout"));
        checkoutBtn.click();
        sleep();

        assertEquals("https://www.saucedemo.com/checkout-step-one.html", driver.getCurrentUrl());
    }


    @Test
    @DisplayName("add user information | Success")
    @Order(4)

    public void TestAddUserInformation() {
        driver.findElement(By.id("first-name")).sendKeys("Nourhan");
        sleep();
        driver.findElement(By.id("last-name")).sendKeys("Ali");
        sleep();
        driver.findElement(By.id("postal-code")).sendKeys("123456");
        sleep();
        driver.findElement(By.name("continue")).click();
        sleep();
        assertEquals("https://www.saucedemo.com/checkout-step-two.html", driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Performance User Complete order  | Success")
    @Order(5)

    public void testUserCompleteOrder() {
        driver.findElement(By.name("finish")).click();

        assertEquals("https://www.saucedemo.com/checkout-complete.html", driver.getCurrentUrl());
        sleep();
    }

}
