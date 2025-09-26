package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.exceptions.BrowserException;

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
		
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		return driver;
	}
	
	public Properties initProp() {
		prop = new Properties();
		
		try {
			FileInputStream fip = new FileInputStream("./src/test/resources/config/config.properties");
			prop.load(fip);
			
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
}
