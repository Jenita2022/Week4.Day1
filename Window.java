package week4.day1;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Window {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://www.leafground.com/pages/Window.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		//select open home page button
		driver.findElement(By.id("home")).click();
		String parentWindow = driver.getWindowHandle();

		Set<String> winSet = driver.getWindowHandles();
		List<String> winLst = new ArrayList<String>(winSet);
		System.out.println(winLst);
		driver.switchTo().window(winLst.get(1));
		System.out.println(driver.getTitle());
		driver.close();

		//switch to parent window
		driver.switchTo().window(parentWindow);
		//select open multiple windows button
		driver.findElement(By.xpath("//button[text()='Open Multiple Windows']")).click();
		Set<String> winSet1 = driver.getWindowHandles();
		List<String> winLst1 = new ArrayList<String>(winSet1);
		System.out.println(winLst1);
		driver.switchTo().window(winLst1.get(1));
		System.out.println("First new window title: " +driver.getTitle());
		driver.close();
		driver.switchTo().window(winLst1.get(2));
		System.out.println("SEcond new window title: " +driver.getTitle());
		driver.close();

		driver.switchTo().window(parentWindow);
		//select Do not close me
		driver.findElement(By.xpath("//button[text()='Do not close me ']")).click();
		Set<String> winset2 = driver.getWindowHandles();
		List<String> winlst2 = new ArrayList<String>(winset2);
		System.out.println("No. of windows" +winlst2.size() +winlst2);
		driver.switchTo().window(winlst2.get(1));
		System.out.println(driver.getTitle());
		driver.close();
		//Thread.sleep(3000);
		driver.switchTo().window(winlst2.get(2));
		System.out.println(driver.getTitle());
		driver.close();

		driver.switchTo().window(parentWindow);
		//select Wait for 5 seconds
		driver.findElement(By.xpath("//button[text()='Wait for 5 seconds']")).click();
		Thread.sleep(5000);
		Set<String> winset3 = driver.getWindowHandles();
		List<String> winlst3 = new ArrayList<String>(winset3);
		System.out.println(winlst3);
		for(int i=0;i<winlst3.size();i++) {
			driver.switchTo().window(winlst3.get(i));
			System.out.println(driver.getTitle());
			driver.close();
		}

		driver.quit();	}

}
