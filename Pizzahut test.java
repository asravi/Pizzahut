//This program automates the testing of the pizzahut.com site from initial load all the way to checkout. It also
//checks to see if the My information page at checkout comes. If so then it displays test passed, otherwise it is test
//failed.  

//Importing the Selenium Packages
package Test;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class Test {
	public static void main(String[] args) throws InterruptedException {

//Telling the system where the chromedriver executable file is. A new chromedriver instance is set up.
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Lenovo\\OneDrive\\Documents\\Selenium project\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
//The driver opens the pizzahut.com site
		driver.get("https://www.pizzahut.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
//Identifies the element in the middle of the page and clicks the object
		driver.findElement(By.id("__next")).click();

//Selects the carryout option and inputs zip code 20878		
		//WebElement zipcode = driver.findElement(By.id("zip-input")); 
		String zipcode = "20878";
		driver.findElement(By.xpath("//span[@id='find-occasion-carryout']")).click();
		driver.findElement(By.id("zip-input")).sendKeys(zipcode);	
		
//Selects the Muddy Branch pizza hut place
		driver.findElement(By.id("ph-find-store")).click();
		Thread.sleep(5000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)", "");

		Actions builder = new Actions(driver);
        Action mouseOverHome = builder
                .moveToElement(driver.findElement(By.xpath("//*[@id=\"ph-localization-id\"]/div[3]/div[3]/div[2]/div/div[1]/div/div/div/div[3]/a"))).click()
                .build();
        mouseOverHome.perform();
        Thread.sleep(10000);

//Goes through the ordering process by choosing Mushrooms and red onions as toppings. 
//Then clicks continue all the way to the checkout.
        WebElement toppings = driver.findElement(By.xpath("//span[text()='Mushrooms']"));
	      driver.findElement(By.xpath("//span[text()='Continue To Toppings']")).click();
	      //driver.findElement(By.xpath("//span[text()='Mushrooms']")).click();
	      //driver.findElement(By.xpath(toppings)).click();
	      toppings.click();
	      //driver.findElement(By.xpath("//span[text()='Red Onions']")).click();
	      driver.findElement(By.xpath("//a[@data-analytics-action='Continue']")).click();
	      driver.findElement(By.xpath("//a[text()='No thanks']")).click();
	      driver.findElement(By.id("view-order-top-upsell")).click();
	      driver.findElement(By.id("checkout-top-os")).click();
	      driver.findElement(By.id("co-guest-login")).click();

//Checks to see if the title of the page is My information. If so the test passes otherwise it fails. 
//The results are displayed in the console.     
      
	  try{
    	  if(driver.findElement(By.xpath("//h1[text()='My information']")).isDisplayed()){
    	  System.out.println("Test Passed");
    	  }

    	  }catch(Exception e){
    	  System.out.println("Test Failed");
    	  }

//Closes the browser
        driver.quit();
        
		
		}
}


