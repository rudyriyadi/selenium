package curatwo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class MakeAppointmentTest {
    WebDriver driver;

@BeforeTest
public void init() {
    /* initiate browser */
    System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
    driver = new ChromeDriver();

//go to homepage
driver.navigate().to("https://katalon-demo-cura.herokuapp.com");
driver.manage().window().maximize();

/*//login
driver.findElement(By.id("txt-username")).sendKeys("John Doe");
driver.findElement(By.id("txt-password")).sendKeys("ThisIsNotAPassword");
driver.findElement(By.id("btn-login")).click();
Assert.assertEquals(driver.getCurrentUrl(),"https://katalon-demo-cura.herokuapp.com/#appointment");
*/
}

@Test
public void CheckElement () {
//check h2 element
    Assert.assertEquals(driver.findElement(By.cssSelector("section h2")).getText(),"Make Appointment");

//check dropdown element
    Select dropdownFacility = new Select(driver.findElement(By.id("combo_facility")));
    List <WebElement> dropdownoptions = dropdownFacility.getOptions();
    Assert.assertEquals(dropdownoptions.get(0).getAttribute("value"),"Tokyo CURA Healthcare Center");
    Assert.assertEquals(dropdownoptions.get(1).getAttribute("value"),"Hongkong CURA Healthcare Center");
    Assert.assertEquals(dropdownoptions.get(2).getAttribute("values"),"Seoul CURA Healthcare Center");

 /*//check text area
    Assert.assertEquals(driver.findElement(By.id("txt_comment")).getAttribute("placeholder"),"Comment");

  */

}
@Test (priority = 0)
    public void makeAppointmentWithCorrectValues () {

    //check dropdown
    Select dropdownfacility = new Select(driver.findElement(By.id("combo_facility")));
    dropdownfacility.selectByValue("Seoul CURA Healthcare Center");

    //check box
    driver.findElement(By.id("chk_hospotal_readmission")).click();

    //check radio button
    driver.findElement(By.id("radio_program_medicaid")).click();

    //Check date picker
    driver.findElement(By.id("txt_visit_date")).sendKeys("21/11/2024");

    //check text area
    driver.findElement (By.id("txt_comment")).sendKeys("Test text area");

    //check click button
    driver.findElement(By.id("btn-book-appointment")).click();
    Assert.assertEquals(driver.getCurrentUrl(),"https://katalon-demo-cura.herokuapp.com/appointment.php#summary");


}




}
