package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	
	public static ThreadLocal<WebDriver> thLocal = new ThreadLocal<WebDriver>();
	
	public static String highlight;
	
	private final static Logger log = LogManager.getLogger(DriverFactory.class);

	
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
		log.info("BrowserName: "+browserName);
		//System.out.println("BrowserName: "+browserName);
		
		optionsManager = new OptionsManager(prop);
		
		highlight = prop.getProperty("highlight");
		
		switch (browserName.toLowerCase().trim()) {
		case "chrome" :
			thLocal.set(new ChromeDriver(optionsManager.getChromeOptions()));
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			break;
			
		case "edge" :
			thLocal.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			//driver = new EdgeDriver(optionsManager.getEdgeOptions());
			break;
			
		case "firefox" :
			thLocal.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			//driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			break;
			
		case "safari" :
			thLocal.set(new SafariDriver());
			//driver = new SafariDriver();
			break;
			
		default:
			log.info("Pass Valid Browser Name..." + browserName);
			throw new BrowserException("======INVALID BROWSER======");
		}
		ChainTestListener.log("Initialising driver....");
		getWebDriver().get(prop.getProperty("url"));
		getWebDriver().manage().window().maximize();
		getWebDriver().manage().deleteAllCookies();
		return getWebDriver();
	}
	
	public static WebDriver getWebDriver() {
		return thLocal.get();
	}
	
	//mvn clean install -Denv = "stage"
	public Properties initProp() {
		prop = new Properties();
		
		String envName = System.getProperty("env");
		try {
			FileInputStream fip =null ;
		
		if(envName==null) {
			log.info("env is null. SO Running Test on QA environment");
			fip = new FileInputStream("./src/test/resources/config/qa.config.properties");
		}
		else {
			log.info("Running Test on env: "+envName);
			
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
		return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.FILE);
	}
	
	public static byte[] getScreenShotByte() {
		return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
	}
	
	public static String getScreenShotBase64() {
		return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BASE64);
	}
	
	@AfterMethod
	public void logAfterInitBrowser() {
		ChainTestListener.log("Initialising driver...."+" DriverFactory");
	}
}
