package demos;

import java.net.MalformedURLException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static java.time.Duration.ofSeconds;


public class Demoshybrid extends Capability{
	
	AndroidDriver<AndroidElement> driver;
	@BeforeTest
	public void setup() throws MalformedURLException
	{
		driver = capabilities();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	//positive
	//id is want called as resource-id, content-desc can be also called as what accessibility id
	@Test(enabled=false)
	public void testcase1()
	{
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Niharika");
		//driver.findElement(By.xpath("//*[@id='com.androidsample.generalstore:id/nameField']")).sendKeys("Niharika");
		driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
		//i have to work with drop down
		driver.findElement(By.id("android:id/text1")).click();
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Australia\"))").click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
	}
	
	//negative
	@Test(enabled=false)
	public void testcase2()
	{
		//driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Niharika");
		//driver.findElement(By.xpath("//*[@id='com.androidsample.generalstore:id/nameField']")).sendKeys("Niharika");
		driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
		//i have to work with drop down
		driver.findElement(By.id("android:id/text1")).click();
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Australia\"))").click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		//Basically to read the pop up message if any popup comes.
		String message = driver.findElement(By.xpath("//android.widget.Toast[1]")).getAttribute("name");
		System.out.println(message);
		Assert.assertEquals(message, "Please enter your name");
	}
	
	@Test(enabled=false)
	public void testcase3() throws InterruptedException
	{
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Niharika");
		//driver.findElement(By.xpath("//*[@id='com.androidsample.generalstore:id/nameField']")).sendKeys("Niharika");
		driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
		//i have to work with drop down
		driver.findElement(By.id("android:id/text1")).click();
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Australia\"))").click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		
		//if i go with index
		driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
		driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		
		Thread.sleep(5000);
		
		String amount1 = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(0).getText();
		amount1 = amount1.substring(1);
		Double amount1value = Double.parseDouble(amount1);
		System.out.println(amount1value);
		
		String amount2 = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(1).getText();
		amount2 = amount2.substring(1);
		Double amount2value = Double.parseDouble(amount2);
		System.out.println(amount2value);
		
		
		String totalamount = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
		totalamount = totalamount.substring(2);
		Double Totalvalue = Double.parseDouble(totalamount);
		System.out.println(Totalvalue);
		
		//i have to sum it and check if it is equal
		Double TotalsumofValue = amount1value+amount2value;
		System.out.println(TotalsumofValue);
		
		Assert.assertEquals(Totalvalue, TotalsumofValue);
	}
	
	//he wants to serach for a specify product and click on the product 
	@Test
	public void testcase4() throws InterruptedException
	{
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Niharika");
		//driver.findElement(By.xpath("//*[@id='com.androidsample.generalstore:id/nameField']")).sendKeys("Niharika");
		driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
		//i have to work with drop down
		driver.findElement(By.id("android:id/text1")).click();
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Australia\"))").click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		Thread.sleep(8000);
		
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView(new UiSelector().textMatches(\"Converse All Star\"))");
		
		int count = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
		
		for(int i=0;i<count;i++)
		{
			String name = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
			//System.out.println(name);
			if(name.equals("Converse All Star"))
			{
				System.out.println(name);
				driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
				break;
			}
		}
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
//      Tap on the checkbox
      WebElement cbox = driver.findElement(By.className("android.widget.CheckBox"));
      TouchAction t = new TouchAction(driver);
      t.tap(tapOptions().withElement(element(cbox))).perform();
      
//    Long press on terms
    WebElement terms = driver.findElement(By.xpath("//*[@text='Please read our terms of conditions']"));
    t.longPress(longPressOptions().withElement(element(terms)).withDuration(ofSeconds(3))).release().perform();
    
//  Close the terms
  driver.findElement(By.id("android:id/button1")).click();
  
//Click on visit website
driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
Thread.sleep(9000);

//i have beeen navigated to the browser and i want to wotrk with the browser
Set<String> contextNames = driver.getContextHandles();
for (String contextName : contextNames) {
    System.out.println(contextName); //prints out something like NATIVE_APP \n WEBVIEW_1
}
driver.context("WEBVIEW_com.androidsample.generalstore");
driver.findElement(By.xpath("//*[@name='q']")).sendKeys("IBM");
//i want to click on enter
driver.findElement(By.xpath("//*[@name='q']")).sendKeys(Keys.ENTER);
driver.pressKey(new KeyEvent(AndroidKey.BACK));
driver.context("NATIVE_APP");


	}

}
