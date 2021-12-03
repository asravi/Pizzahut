//This program  tests the pizzahut.com website using selenium which is a test automation tool.   It also
//checks to see if the My information page at checkout appears. If so then it displays test passed, otherwise it is test
//failed.  After the browser closes, the program uses the documenu api to return a list of pizza hut restaurants for a specified location. The parameters
//are name as Pizza Hut and state as MD. Then three locations are displayed in JSON format. Then a parser object is created which parses the JSON file 
// and returns the name of the restaurant, City, State, and address of the first record in the JSON file. 

//Importing the Selenium Packages
package Test;


import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.http.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/*import org.openqa.selenium.remote.http.HttpClient;
import org.openqa.selenium.remote.http.HttpRequest;
import org.openqa.selenium.remote.http.HttpResponse;*/
import org.openqa.selenium.interactions.Actions;

public class testclass {
	public static  void main(String[] args) throws InterruptedException {

//1. Create a new chromedriver instance. 
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Lenovo\\OneDrive\\Documents\\Update Selenium\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
//2. The chromedriver instance opens the pizzahut.com site
		String hut = ("https://www.pizzahut.com/");
		driver.manage().window().maximize();
		driver.get(hut);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
// 3. Clicks the center pizza image to be taken to the page where the user selects delivery or carryout
		driver.findElement(By.id("__next")).click();

//4. The options which include Delivery and Carryout are displayed. The user clicks the Carryout option icon which then displays the zipcode input field.
// The zipcode 20878 is typed into the zipcode field
				
		driver.findElement(By.xpath("//span[@id='find-occasion-carryout']")).click();
		String zipcode = "20878";
		driver.findElement(By.id("zip-input")).sendKeys(zipcode);	
	
//This section validates the zipcodes using an array and checks to see if they are numerical and five digits. If so, the result is displayed as true
// in the console. Otherwise it is displayed as false. 
		List<String> zips = new ArrayList<String>();
		zips.add(zipcode);
		zips.add("60176");
		zips.add("22003");
		zips.add("23452");
		zips.add("321");
		zips.add("adeej2");
		
		String regex = "^[0-9]{5}(?:-[0-9]{4})?$";
		Pattern pattern = Pattern.compile(regex);
		
		for (String zip : zips)
		{
			Matcher matcher = pattern.matcher(zip);
			System.out.println(matcher.matches());
		}
		
		
//5. After the 20878 zipcode is inputted, the "find a store" button is clicked. 
		driver.findElement(By.id("ph-find-store")).click();
		Thread.sleep(5000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)", "");
		driver.quit();
//6. Then the user scrolls down to view the locations and picks the first location the Muddy Branch Pizza Hut
		/*Actions builder = new Actions(driver);
        Action mouseOverHome = builder
                .moveToElement(driver.findElement(By.xpath("//*[@id=\"ph-localization-id\"]/div[3]/div[3]/div[2]/div/div[1]/div/div/div/div[3]/a"))).click()
                .build();
        mouseOverHome.perform();
        Thread.sleep(10000);

//The MuddyBranch Pizza Hut location is set. Then the user clicks the "Continue to toppings" option below where the user chooses mushrooms as their toppings.
//Then the continue button is clicked. 
         WebElement toppings = driver.findElement(By.xpath("//span[text()='Mushrooms']"));
	      driver.findElement(By.xpath("//span[text()='Continue To Toppings']")).click();
	      
	     toppings.click();
	     
// driver.findElement(By.xpath("//span[text()='Red Onions']")).click();
	     driver.findElement(By.xpath("//a[@data-analytics-action='Continue']")).click();
	     
//Asks the user if they would like extra cheese and the no thanks button is clicked
	     driver.findElement(By.xpath("//a[text()='No thanks']")).click();
	     
//The View Order button is clicked
	      driver.findElement(By.id("view-order-top-upsell")).click();

//The Checkout button is clicked
	      driver.findElement(By.id("checkout-top-os")).click();

//The Continue as Guest button is clicked	      
	      driver.findElement(By.id("co-guest-login")).click();

//This then takes the user to the information page. The script below checks to see if the title of the page is My information. If so the test passes otherwise it fails. 
//The test results are displayed in the console.     
      
	  try{
    	  if(driver.findElement(By.xpath("//h1[text()='My information']")).isDisplayed()){
    	  System.out.println("Test Passed");
    	  }

    	  }catch(Exception e){
    	  System.out.println("Test Failed");
    	  }

//Closes the browser
        driver.quit();       
		/*HttpRequest request = New HttpRequest("GET","https://documenu.p.rapidapi.com/restaurants/search/fields?restaurant_website=https%3A%2F%2Fwww.pizzahut.com%2F&state=MD&zip_code=20878&size=3");
		request.addHeader("x-api-key", "fd52f31448cb790c2591e7fcd75536f4");
		request.addHeader("x-rapidapi-host", "documenu.p.rapidapi.com");
		request.addHeader("x-rapidapi-key", "834d6be3acmshde6b136c4b74a24p1ce2e4jsnedbc8c3c7f9f");*/
		JSONParser jsonParser = new JSONParser();
        HttpRequest request = HttpRequest.newBuilder()
        		.uri(URI.create("https://documenu.p.rapidapi.com/restaurants/search/fields?restaurant_name=Pizza%20Hut&state=MD&size=3"))
        		.header("x-api-key", "fd52f31448cb790c2591e7fcd75536f4")
        		.header("x-rapidapi-host", "documenu.p.rapidapi.com")
        		.header("x-rapidapi-key", "834d6be3acmshde6b136c4b74a24p1ce2e4jsnedbc8c3c7f9f")
        		.method("GET", HttpRequest.BodyPublishers.noBody())
        		.build();
        try {
        	HttpResponse response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            Object obj = jsonParser.parse((String)response.body());
            JSONObject jobj = (JSONObject)obj;
            JSONArray data = (JSONArray)jobj.get("data");
            JSONObject first = (JSONObject)data.get(0); 
            String restaurantName = (String)first.get("restaurant_name");
            String restaurantPhone = (String)first.get("restaurant_phone");
            JSONObject address = (JSONObject)first.get("address");
            String city = (String)address.get("city");
            String state = (String)address.get("state");
            String street = (String)address.get("street");
            String format = (String)address.get("formatted");
            System.out.println(restaurantName);
            System.out.println(restaurantPhone);
            System.out.println(city);
            System.out.println(state);
            System.out.println(street);
            System.out.println(format);
        
	}catch(Exception e){
  	  System.out.println("Test Failed");
  	  }     
        
        	
	}
}


	
		
	
		
	


