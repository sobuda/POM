package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.qa.opencart.constants.AppConstants.*;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {

	private WebDriver driver;
	private ElementUtil eUtil;
	
	private By headerWithSearchKey = By.tagName("h1");
	private By searchResults = By.xpath("//div[@class='product-thumb']//h4");
	//private By productNameLink = By.linkText("MacBook Pro");
	
	public SearchResultsPage(WebDriver driver) {
		this.driver=driver;
		eUtil = new ElementUtil(driver);
	}
	
	public String searchKeyHeader() {
		eUtil.waitForElementVisible(headerWithSearchKey, DEFAULT_TIMEOUT);
		return eUtil.doElementGetText(headerWithSearchKey);
	}

	public List<WebElement> searchResults() {
		List<WebElement> productsEle = eUtil.waitForElementsVisible(searchResults, DEFAULT_TIMEOUT);
		return productsEle;
	}

	public ProductInfoPage selectProduct(String productName) {
		eUtil.waitForElementVisible(By.linkText(productName), DEFAULT_TIMEOUT).click();
		return new ProductInfoPage(driver);
	}
	
	
}
