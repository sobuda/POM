package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameWorkException;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager ;
	
	public static String highlight;
	/**
	 * 
	 * Used for Initializing driver with given browserName.
	 * Maximize Window
	 * Delete Cookies
	 * return WebDriver Instance
	 * @param browserName
	 */
	public WebDriver intDriver(Properties prop) {
		
		String browserName = prop.getProperty("browser");
		System.out.println("BrowserName: "+browserName);
		
		optionsManager = new OptionsManager(prop);
		
		highlight = prop.getProperty("highlight");
		
		switch (browserName.toLowerCase().trim()) {
		case "chrome" :
			driver = new ChromeDriver(optionsManager.getChromeOptions());
			break;
			
		case "edge" :
			driver = new EdgeDriver(optionsManager.getEdgeOptions());
			break;
			
		case "firefox" :
			driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			break;
			
		case "safari" :
			driver = new SafariDriver();
			break;
			
		default:
			System.out.println("Pass Valid Browser Name..." + browserName);
			throw new BrowserException("======INVALID BROWSER======");
		}
		ChainTestListener.log("Initialising driver....");
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		return driver;
	}
	
	
	
	//mvn clean install -Denv = "stage"
	public Properties initProp() {
		prop = new Properties();
		
		String envName = System.getProperty("env");
		try {
			FileInputStream fip =null ;
		
		if(envName==null) {
			System.out.println("env is null. SO Running Test on QA environment");
			fip = new FileInputStream("./src/test/resources/config/qa.config.properties");
		}
		else {
			System.out.println("Running Test on env: "+envName);
			
			switch (envName) {
			case "qa":
				fip = new FileInputStream("./src/test/resources/config/qa.config.properties");
				break;
			case "stage":
				fip = new FileInputStream("./src/test/resources/config/stage.config.properties");
				break;
			case "uat":
				fip = new FileInputStream("./src/test/resources/config/uat.config.properties");
				break;
			case "prod":
				fip = new FileInputStream("./src/test/resources/config/config.properties");
				break;
			case "dev":
				fip = new FileInputStream("./src/test/resources/config/dev.config.properties");
				break;
			default:
				throw new FrameWorkException("======INVALID ENV VAR===============");
			}
		}
		
			prop.load(fip);
			
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	public static File getScreenShotFile() {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	}
	
	public static byte[] getScreenShotByte() {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}
	
	public static String getScreenShotBase64() {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
	}
	
	@AfterMethod
	public void logAfterInitBrowser() {
		ChainTestListener.log("Initialising driver...."+" DriverFactory");
	}
}
