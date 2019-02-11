package demo;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Demo 
{
	WebDriver driver;
	  @Test
	  public void testWebsite() throws Exception 
	  {
		  List<WebElement> lst = driver.findElements(By.tagName("a"));
		  System.out.println("Number of Anchor TAGS ===> "+lst.size());
		  for (WebElement we : lst) 
		  {
			  String url = we.getAttribute("href");
			  test(url);
		  }
	  }
	  
	  public void test(String urln) throws Exception 
	  {
			URL url = new URL(urln);
			HttpURLConnection ht = (HttpURLConnection) url.openConnection();
			ht.setConnectTimeout(1000);
			ht.connect();
			Thread.sleep(800);
			if(ht.getResponseCode()==200)
			{
				System.out.println("Link Test Pass ==> "+url);
			}
			else
				System.out.println("Link Test Failed ==> "+url);
	  }
	  
	  @BeforeMethod
	  public void beforeMethod() 
	  {
		  System.setProperty("webdriver.gecko.driver", "D:\\geckodriver.exe");
		  driver = new FirefoxDriver();
		  driver.get("http://techsumtechnology.com");
		  driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
	  }

	  @AfterMethod
	  public void afterMethod() 
	  {
		  driver.quit();
	  }

}
