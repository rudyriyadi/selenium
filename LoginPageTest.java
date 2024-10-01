package curatwo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginPageTest{
    WebDriver driver;

    @BeforeTest
    public void init(){
    //initiate browser
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        driver = new ChromeDriver();
    //go to home page
        driver.navigate().to("https://katalon-demo-cura.herokuapp.com/profile.php#login");
        driver.manage().window().maximize();

    }

    @Test (priority = 0)
    public void CheckElement () {
    //check h2 element
        Assert.assertEquals(driver.findElement(By.cssSelector("section h2")).getText(),"Login");
    //check p element
        Assert.assertEquals(driver.findElement(By.cssSelector("section p")).getText(),"Please login to make appointment.");

    //check text box element
        Assert.assertEquals(driver.findElement(By.id("txt-username")).getAttribute("placeholder"),"Username");
        Assert.assertEquals(driver.findElement(By.id("txt-password")).getAttribute("placeholder"),"Password");

    }

    @Test (priority = 1)
    //login with null values
    public void loginWithNullValues () {
        driver.findElement(By.id("btn-login")).click();
        Assert.assertEquals(driver.findElement(By.className("text-danger")).getText(),"Login failed! Please ensure the username and password are valid.");
    }
    @Test (priority = 2)
    //login with wrong password
    public void loginWithWrongPassword () {
        driver.findElement(By.id("txt-username")).sendKeys("John Doe");
        driver.findElement(By.id("txt-password")).sendKeys("asdfgh");
        driver.findElement(By.id("btn-login")).click();
        Assert.assertEquals(driver.findElement(By.className("text-danger")).getText(),"Login failed! Please ensure the username and password are valid.");

    }

    @Test (priority = 3)
    //login with correct values
    public void loginWithCorrectValue () {
       driver.findElement(By.id("txt-username")).sendKeys("John Doe");
       driver.findElement(By.id("txt-password")).sendKeys("ThisIsNotAPassword");
       driver.findElement(By.id("btn-login")).click();
       Assert.assertEquals(driver.getCurrentUrl(),"https://katalon-demo-cura.herokuapp.com/#appointment");
    }

    @AfterTest
    //close window browser
    public void closeWindowBrowser () {
        driver.close();

    }

}
