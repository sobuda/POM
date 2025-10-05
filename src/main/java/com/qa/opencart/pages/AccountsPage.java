package com.qa.opencart.pages;

import static com.qa.opencart.constants.AppConstants.*;
import static com.qa.opencart.constants.AppConstants.HOME_PAGE_FRACT_URL;
import static com.qa.opencart.constants.AppConstants.HOME_PAGE_TITLE;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtil eUtil;
	
	private By pageHeaders = By.tagName("h2");
	private By searchField = By.name("search");
	private By searchIcon = By.xpath("//div[@id = 'search']//button");
	
	public AccountsPage(WebDriver driver) {
		this.driver=driver;
		eUtil= new ElementUtil(driver);
	}
	
	@Step("Step 1: Get Account Page Title")
	public String getAccountsPageTitle() {
		return eUtil.waitForTitle(DEFAULT_TIMEOUT, HOME_PAGE_TITLE);
	}

	@Step("Step 2: Get Account Page URL")
	public String getAccountsPageURL() {
		return eUtil.waitForURLContains(MEDIUM_DEFAULT_TIMEOUT, HOME_PAGE_FRACT_URL);
	}


	@Step("Step 3: Get All Account Page Headers")
	public List<String> getAccPageHeaders() {
		List<WebElement> elements = eUtil.getElements(pageHeaders);
		List<String> headerTextList = new ArrayList<String>();
		for(WebElement e: elements) {
			headerTextList.add(e.getText());
		}
		return headerTextList;
	}
	
	@Step("Step 4: Namvigate to Search Results Page.")
	public SearchResultsPage doSearch(String productName) {
		eUtil.doSendKeys(searchField, productName);
		eUtil.doActionClick(searchIcon);
		
		return new SearchResultsPage(driver);
	}


}
