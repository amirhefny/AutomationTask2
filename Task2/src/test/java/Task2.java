import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class Task2 {
  private WebDriver driver;

    @BeforeClass
    private void SetUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }
    @Test(priority = 1)
    private void login(){
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }
    @Test(priority = 2)
    private void addProductToCart(){
        String itemName ="Sauce Labs Backpack";
        String button="//div[text()=\"%s\"]/parent::a/parent::div/following-sibling::div/button";
        String itemButton=String.format(button,itemName);
        driver.findElement(By.xpath(itemButton)).click();
        WebElement cart = driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a"));
        cart.click();
        if (isItemInCart()) {
            System.out.println("The item " + itemName + " in the cart.");
        } else {
            System.out.println("The item " + itemName + "is not in the cart.");
        }
    }

    public boolean isItemInCart() {
        List<WebElement> items = driver.findElements(By.xpath("//*[@id=\"item_4_title_link\"]/div"));
        return !items.isEmpty();
    }

    @AfterClass
    public void close(){
       driver.quit();
    }
}
