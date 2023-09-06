package com.raghib.selenium;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public class InvokingMultipleWindowsOrTabs extends BaseClass  {

	/*
	 * import static
	 * org.openqa.selenium.support.locators.RelativeLocator.withTagName;
	 * driver.findElement(withTagName("label").above());
	 */
	public static WebDriver driver;
	public static String browserName = "chrome";
	public static String browserVersion = "116";
	public static String location1 = "https://rahulshettyacademy.com/angularpractice/";
	public static String location2 = "https://rahulshettyacademy.com/";
	public static String nameBoxXPath = "//body/app-root[1]/form-comp[1]/div[1]/form[1]/div[1]/input[1]";
	public static String courseCssSelector = "a[href*='https://courses.rahulshettyacademy.com/p/']";

	public static void main(String[] args) throws InterruptedException {
		// Chrome Browser
		driver = BaseClass.getDriver(browserName, browserVersion);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.get(location1);
		
		driver.switchTo().newWindow(WindowType.TAB);
		// driver.switchTo().newWindow(WindowType.WINDOW);
		
		String parentWindowIdValue = driver.getWindowHandle();
		System.out.println("ParentWindowIdValue : "+parentWindowIdValue);

		Set<String> windowId = driver.getWindowHandles();
		Iterator<String> it = windowId.iterator();
		String parentWindowId = it.next();
		System.out.println("parentWindowId : "+parentWindowId);
		String childWindowId = it.next();
		System.out.println("childWindowId : "+childWindowId);

		driver.switchTo().window(childWindowId);

		driver.get(location2);
		String courseName = driver.findElements(By.cssSelector(courseCssSelector)).get(1).getText();
		System.out.println("courseName : " + courseName);

		driver.switchTo().window(parentWindowId);

		driver.findElement(By.xpath(nameBoxXPath)).sendKeys(courseName);

		Thread.sleep(5000);
		BaseClass.quitDriver();
	}
}
