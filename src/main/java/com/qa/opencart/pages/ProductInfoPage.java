package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;

import static com.qa.opencart.constants.AppConstants.*;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	
	private WebDriver driver;
	private ElementUtil eUtil;
	
	private By productHeader = By.tagName("h1");
	private By productImages = By.xpath("//ul[@class='thumbnails']/li");
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eUtil = new ElementUtil(driver);
	}
	
	

	public String getProductHeader() {

		return eUtil.waitForElementVisible(productHeader, DEFAULT_TIMEOUT).getText();
	}
	
	public String getProductPageURL() {
		String productPageURL = eUtil.waitForURLContains(DEFAULT_TIMEOUT, PRODUCT_PAGE_FRACT_URL);
		return productPageURL;
	}



	public int getImageCount() {
		int count = eUtil.waitForElementsVisible(productImages, DEFAULT_TIMEOUT).size();
		return count;
	}

	
}
