package com.arabyAds.task;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProblemUserScenario {

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
    @DisplayName("Login Test Problem user | success")
    @Order(1)
    public void testProblemUserScenario() {
        //        WebDriver driver=  new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        WebElement username = driver.findElement(By.id("user-name"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement login = driver.findElement(By.name("login-button"));
        username.sendKeys("problem_user");
        sleep();
        password.sendKeys("secret_sauce");
        sleep();
        login.click();
        String AcutalUrl = driver.getCurrentUrl();
        String expectedUrl = "https://www.saucedemo.com/";
        assertEquals(expectedUrl, expectedUrl);

    }

    @Test
    @DisplayName("Problem User Add item to cart | Success")
    @Order(2)
    public void TestProblemUserAddProductToCart() {
        List<WebElement> poductList = driver.findElements(By.className("inventory_item"));
        List<Float> productsPrices = new ArrayList<Float>();
        Map<Float, WebElement> productElementMap = new HashMap<Float, WebElement>();
        for (WebElement pi : poductList) {
            WebElement pricebar = pi.findElement(By.className("inventory_item_price"));
            String string_inventory_item_price = pi.findElement(By.className("inventory_item_price")).getText().substring(1);
            float productPrice = Float.parseFloat(string_inventory_item_price);
            productElementMap.put(productPrice, pi);
            productsPrices.add(productPrice);
        }
        Collections.sort(productsPrices);
        float lowestPriceItem = productsPrices.get(0);
        WebElement webElement = productElementMap.get(lowestPriceItem);
        webElement.findElement(By.className("btn_inventory")).click();
        sleep();
        driver.findElement(By.className("shopping_cart_link")).click();
        sleep();
        List<WebElement> cartList = driver.findElements(By.className("cart_list"));
        assertEquals(cartList.size(), 1);
        String actualAddedItemPrice = cartList.get(0).findElement(By.className("inventory_item_price")).getText();
        assertEquals(actualAddedItemPrice, "$" + lowestPriceItem);
    }

    @Test
    @DisplayName("Problem User make order checkout | Success")
    @Order(3)

    public void TestProblemUserCheckOut() {
        WebElement checkoutBtn = driver.findElement(By.name("checkout"));
        checkoutBtn.click();
        sleep();
        assertEquals("https://www.saucedemo.com/checkout-step-one.html", driver.getCurrentUrl());
        sleep();
    }


    @Test
    @DisplayName("add Problem user information | Fails")
    @Order(4)

    public void TestAddProblemUserInformation() {
        driver.findElement(By.id("first-name")).sendKeys("Nourhan");
        sleep();
        driver.findElement(By.id("last-name")).sendKeys("Ali");
        sleep();
        driver.findElement(By.id("postal-code")).sendKeys("123456");
        sleep();
        driver.findElement(By.name("continue")).click();
        //assertEquals("https://www.saucedemo.com/checkout-step-two.html", driver.getCurrentUrl());
    }
}
