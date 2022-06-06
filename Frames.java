package week4.day1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Frames {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://chercher.tech/practice/frames-example-selenium-webdriver");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		String pageTopic = driver.findElement(By.xpath("//span[text()='Not a Friendly Topic']")).getText();
		System.out.println("Heading: " +pageTopic);

		//switch to frame1
		driver.switchTo().frame(0); //frame index according to DOM. Inspect and find it.
		driver.findElement(By.xpath("//body/input")).sendKeys("Learning Frames");
		Thread.sleep(3000);

		//switch to frame3
		WebElement eleFrame3 = driver.findElement(By.id("frame3"));
		driver.switchTo().frame(eleFrame3);
		driver.findElement(By.xpath("//body/input")).click();

		//Come out of all frames (frame1,3)
		driver.switchTo().defaultContent();
		Thread.sleep(3000);

		//switch to frame2
		WebElement eleFrame2 = driver.findElement(By.id("frame2"));
		driver.switchTo().frame(eleFrame2);
		WebElement eleAnimalDD = driver.findElement(By.id("animals"));
		Select options=new Select(eleAnimalDD);
		options.selectByVisibleText("Avatar");

		//come out of all frames
		driver.switchTo().defaultContent();

		if (pageTopic.contentEquals("Not a Friendly Topic"))
			System.out.println("Successful exit from all frames");
		else
			System.out.println("Not Successful");

	}

}
