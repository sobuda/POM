package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.SearchResultsPage;

public class BaseTest {

	WebDriver driver;
	
	DriverFactory df;
	protected Properties prop;
	protected AccountsPage accPage;
	protected LoginPage loginPage;
	protected SearchResultsPage searchPage;
	protected ProductInfoPage productInfoPage;
	protected RegistrationPage registerPage;
	
	@BeforeTest
	
	public void setUp() {
		df = new DriverFactory();
		prop = df.initProp();
		driver = df.intDriver(prop);
		loginPage = new LoginPage(driver);
	}

	@AfterTest
	
	public void tearDown() {
		driver.quit();
	}
}
