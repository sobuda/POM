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
	
	
	public String getAccountsPageTitle() {
		return eUtil.waitForTitle(DEFAULT_TIMEOUT, HOME_PAGE_TITLE);
	}


	public String getAccountsPageURL() {
		return eUtil.waitForURLContains(MEDIUM_DEFAULT_TIMEOUT, HOME_PAGE_FRACT_URL);
	}


	public List<String> getAccPageHeaders() {
		List<WebElement> elements = eUtil.getElements(pageHeaders);
		List<String> headerTextList = new ArrayList<String>();
		for(WebElement e: elements) {
			headerTextList.add(e.getText());
		}
		return headerTextList;
	}
	
	public SearchResultsPage doSearch(String productName) {
		eUtil.doSendKeys(searchField, productName);
		eUtil.doActionClick(searchIcon);
		
		return new SearchResultsPage(driver);
	}


}
