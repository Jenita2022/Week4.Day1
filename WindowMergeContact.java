package week4.day1;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WindowMergeContact {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://leaftaps.com/opentaps/control/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		driver.findElement(By.id("username")).sendKeys("DemoSalesManager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		driver.findElement(By.className("decorativeSubmit")).click();
		driver.findElement(By.linkText("CRM/SFA")).click();
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//a[text()='Merge Contacts']")).click();

		//select From contact widget
		driver.findElement(By.xpath("//img[@alt='Lookup']")).click();

		String parentWin = driver.getWindowHandle();
		//get no.of open windows
		Set<String> winSet = driver.getWindowHandles();
		List<String> winlst = new ArrayList<String>(winSet);
		System.out.println(winlst);
		Thread.sleep(3000);
		//switch to new window (from contact)
		driver.switchTo().window(winlst.get(1));
		//select the first resulting contact
		driver.findElement(By.xpath("//td[@tabindex='0']//a")).click();
		Thread.sleep(4000);

		//get back to parent window
		driver.switchTo().window(parentWin);

		//select To contact widget
		driver.findElement(By.xpath("//input[@id='partyIdTo']/following::img")).click();
		//get no of open windows
		Set<String> winSet1 = driver.getWindowHandles();
		List<String> winlst1 = new ArrayList<String>(winSet1);
		//switch to first new window (to contact)
		driver.switchTo().window(winlst1.get(1));
		Thread.sleep(3000);
		//select the second resulting contact
		driver.findElement(By.xpath("(//td[@class='x-grid3-col x-grid3-cell x-grid3-td-partyId x-grid3-cell-first '])[2]//a")).click();
		Thread.sleep(5000);
		//get back to parent window
		driver.switchTo().window(parentWin);
		//Click on Merge button using Xpath Locator
		driver.findElement(By.linkText("Merge")).click();

		//Accept the alert
		Alert alt = driver.switchTo().alert();
		alt.accept();
		//verify the title
		System.out.println(driver.getTitle());

		//close all windows
		driver.quit();
	}

}
/* 1. Launch URL "http://leaftaps.com/opentaps/control/login"
 * 
 * 2. Enter UserName and Password Using Id Locator
 * 
 * 3. Click on Login Button using Class Locator
 * 
 * 4. Click on CRM/SFA Link
 * 
 * 5. Click on contacts Button
 * 	
 * 6. Click on Merge Contacts using Xpath Locator
 * 
 * 7. Click on Widget of From Contact
 * 
 * 8. Click on First Resulting Contact
 * 
 * 9. Click on Widget of To Contact
 * 
 * 10. Click on Second Resulting Contact
 * 
 * 11. Click on Merge button using Xpath Locator
 * 
 * 12. Accept the Alert
 * 
 * 13. Verify the title of the page
 */