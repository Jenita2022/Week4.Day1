package week4.day1;


import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.bouncycastle.crypto.tls.HashAlgorithm;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.SendKeysAction;

import com.mongodb.MapReduceCommand.OutputType;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon {

	private static final org.openqa.selenium.OutputType<Object> OutputType = null;

	public static void main(String[] args) throws IOException, InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		//2.search as oneplus 9 pro n
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("one plus 9 pro",Keys.ENTER);

		//3.Get the price of the first product
		String price = driver.findElement(By.xpath("//span[@class='a-price-whole']")).getText();
		System.out.println("Product Price: "+price);

		//4. Print the number of customer ratings for the first displayed product

		WebElement eleRatingStar = driver.findElement(By.xpath("//a[@role='button'][@class='a-popover-trigger a-declarative']/i"));
		Actions builder = new Actions(driver);
		builder.moveToElement(eleRatingStar).perform();

		String noOfCusRating = driver.findElement(By.xpath("(//a[@class='a-link-normal s-underline-text s-underline-link-text s-link-style'])[2]/span")).getText();
		System.out.println("No.Of Customer Rating: " +noOfCusRating);
		
		//5. Click the first text link of the first image
		driver.findElement(By.xpath("//a[@class='a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal']/span")).click();
		Thread.sleep(3000);
		Set<String> winSet = driver.getWindowHandles();//get no of open windows
		List<String> winlst=new ArrayList<String>(winSet);
		driver.switchTo().window(winlst.get(1));

		Thread.sleep(5000);
		//6. Take a screen shot of the product displayed
		File source = driver.getScreenshotAs(OutputType.FILE);
		File destination = new File("./phonescreen.png");
		FileUtils.copyFile(source, destination);

		Thread.sleep(6000);
		//String price1= driver.findElement(By.xpath("//span[@class='a-price a-text-price a-size-medium apexPriceToPay']/span[@class='a-offscreen']")).getText();
		String price1= driver.findElement(By.xpath("//table[@class='a-lineitem a-align-top']/tbody/tr[2]/td[2]/span/span[2]")).getText();
		System.out.println("Price to Pay: " +price1);
		//7. Click 'Add to Cart' button
		driver.findElement(By.id("add-to-cart-button")).click();
		Set<String> winSet1 = driver.getWindowHandles();//get no of open windows
		List<String> winlst1=new ArrayList<String>(winSet1);
		System.out.println(winlst1);
		driver.switchTo().window(winlst1.get(1));
		//8. Get the cart subtotal and verify if it is correct.
		//String cartSubTotal = driver.findElement(By.id("attach-accessory-cart-subtotal")).getText();
		Thread.sleep(5000);
		String cartSubTotal = driver.findElement(By.xpath("//div[@class='a-column a-span11 a-text-left a-spacing-top-large']/span[2]/span")).getText();
		System.out.println("Cart SubTotal: " +cartSubTotal);

		if (price1.equals(cartSubTotal)) {
			System.out.println("Price is correct");
		}else {
			System.out.println("Price is wrong");
		}
		
		//9.close the browser
		driver.quit();

	}

}
