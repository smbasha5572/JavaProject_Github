package practice;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class demo {
	public void brokenLinks() throws Throwable {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().fullscreen();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://google.com/");

		driver.navigate().to("https://www.theworldsworstwebsiteever.com/");

		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println(links.size());

		for (WebElement link : links) {
			String linkherf = link.getAttribute("href");
			
			   URL  url=new URL(linkherf);
			  URLConnection  urlconnection= url.openConnection();
			  HttpURLConnection  httpUrlConnection=(HttpURLConnection) urlconnection;
			  
			  httpUrlConnection.setConnectTimeout(5000);
			  
			  httpUrlConnection.connect();
			
			  if(httpUrlConnection.getResponseCode()==200) {
				  System.out.println(linkherf+" >> "+httpUrlConnection.getResponseCode()+" >> "+httpUrlConnection.getResponseMessage());
			  }else {
				  System.err.println(linkherf+" >> "+httpUrlConnection.getResponseCode()+" >> "+httpUrlConnection.getResponseMessage());
			 System.out.println("suite1");
			  }
			  
			  httpUrlConnection.disconnect();   
			  
		}
		}
}
