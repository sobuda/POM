package com.qa.opencart.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;

import static com.qa.opencart.constants.AppConstants.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eUtil;

	private By productHeader = By.tagName("h1");
	
	private By productImages = By.xpath("//ul[@class='thumbnails']/li");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");

	private static Logger log = LogManager.getLogger(ProductInfoPage.class);

	private Map<String, String> productMap;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eUtil = new ElementUtil(driver);
	}

	public String getProductHeader() {
		String header = eUtil.waitForElementVisible(productHeader, DEFAULT_TIMEOUT).getText();
		log.info(header);

		return header;
	}

	@Step("Get selected Product Page url.")
	public String getProductPageURL() {
		String productPageURL = eUtil.waitForURLContains(DEFAULT_TIMEOUT, PRODUCT_PAGE_FRACT_URL);
		return productPageURL;
	}

	@Step("Get Image Count for product")
	public int getImageCount() {
		int count = eUtil.waitForElementsVisible(productImages, DEFAULT_TIMEOUT).size();
		log.info("Images: "+count);
		return count;
	}

	@Step("Get Product details")
	public Map<String, String> getProductMapDetails() {
		productMap = new HashMap<String, String>();
		getProductMetaData();
		getProductPriceData();
		log.info(productMap);
		return productMap;
	}

	private Map<String, String> getProductMetaData() {

		List<WebElement> metaData = eUtil.waitForElementsVisible(productMetaData, DEFAULT_TIMEOUT);
		for (WebElement e : metaData) {
			String mdata = e.getText();
			String meta[] = mdata.split(":");
			productMap.put(meta[0], meta[1].trim());
		}
		return productMap;
	}

	private void getProductPriceData() {

		List<WebElement> priceList = eUtil.waitForElementsVisible(productPriceData, DEFAULT_TIMEOUT);
		String productPrice = priceList.get(0).getText().trim();
		String exTaxPrice = priceList.get(1).getText().split(":")[1].trim();
		productMap.put("ProductPrice", productPrice);
		productMap.put("ExTaxPrice", exTaxPrice);

	}

}
