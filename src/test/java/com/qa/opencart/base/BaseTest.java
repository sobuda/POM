package com.qa.opencart.base;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.listeners.TestAllureListener;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.SearchResultsPage;
import com.qa.opencart.utils.JavascriptUtil;

public class BaseTest {

	WebDriver driver;
	
	DriverFactory df;
	protected Properties prop;
	protected AccountsPage accPage;
	protected LoginPage loginPage;
	protected SearchResultsPage searchPage;
	protected ProductInfoPage productInfoPage;
	protected RegistrationPage registerPage;
	
	private static Logger log = LogManager.getLogger(BaseTest.class);

	
	@Parameters({"browser"})
	
	@BeforeTest
	
	public void setUp(String browserName) {
		//ChainTestListener.log("BaseTest: "+browserName);
		df = new DriverFactory();
		prop = df.initProp();
		
		//browserName is passes from testng.xml
		if(browserName!=null) {
			prop.setProperty("browser", browserName);
		}
		
		driver = df.intDriver(prop);
		loginPage = new LoginPage(driver);
	}
	
//	@AfterMethod
//	public void logAfterInitBrowser() {
//		log.info("Initialising driver... BaseTest");
//		ChainTestListener.log("Initialising driver...."+" Base Test");
//	}
	@AfterMethod
	public void attachScreenShot(ITestResult result) {
		if(!(result.isSuccess())) {
			ChainTestListener.embed(DriverFactory.getScreenShotBase64(), "image/png");
		}
	}
	@AfterTest
	
	public void tearDown() {
		log.info("---------Closing the Browser---------------");
		driver.quit();
	}
}
