package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.qa.opencart.constants.AppConstants.*;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

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
	
	@Step("Step 1: Get the Header with SearchKey")
	public String searchKeyHeader() {
		eUtil.waitForElementVisible(headerWithSearchKey, DEFAULT_TIMEOUT);
		return eUtil.doElementGetText(headerWithSearchKey);
	}

	@Step("Step 2: Get List of searchResults")
	public List<WebElement> searchResults() {
		List<WebElement> productsEle = eUtil.waitForElementsVisible(searchResults, DEFAULT_TIMEOUT);
		return productsEle;
	}

	@Step("Step : Select product: {0}")
	public ProductInfoPage selectProduct(String productName) {
		eUtil.waitForElementVisible(By.linkText(productName), DEFAULT_TIMEOUT).click();
		return new ProductInfoPage(driver);
	}
	
	
}
