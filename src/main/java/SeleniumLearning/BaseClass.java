package SeleniumLearning;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

@Test
public class BaseClass {

	public WebDriver driver;
	//Locators of the page
	By username = By.id("inputUsername");
	By password = By.name("inputPassword");
	By forgotYourPassword = By.linkText("Forgot your password?");
	By signIn = By.xpath("//h1[text() = 'Sign in']");
	By signInButton = By.className("signInBtn");
	By labelForRememberMyUN = By.xpath("//label[@for='chkboxOne']");
	By incorrectUN = By.xpath("//p[contains(text(),'Incorrect username')]");
	By forgotPasswordText = By.xpath("//h2");

	@BeforeTest
	public void startInstance() {
		driver = new ChromeDriver();
		driver.manage().window().fullscreen();
	}

	@Test
	public void startTestCase() {

		driver.get("https://rahulshettyacademy.com/locatorspractice/");
		driver.findElement(username).sendKeys("rahul");
		driver.findElement(password).sendKeys("rahul"); //rahulshettyacademy
		driver.findElement(signInButton).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.findElement(incorrectUN).getText();
		if (driver.findElement(incorrectUN).getText().contains("Incorrect username or password")) {
			Assert.assertEquals(driver.findElement(incorrectUN).getText(), "* Incorrect username or password");
		}
		driver.findElement(forgotYourPassword).click();
		if(driver.findElement(forgotPasswordText).getText().equalsIgnoreCase("Forgot password")){
			Assert.assertEquals(driver.findElement(forgotPasswordText).getText(), "Forgot password");
		}
		
		
	}

	@AfterSuite
	public void tearDown() {
		driver.close();
	}

}
