package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.exceptions.BrowserException;


public class ElementUtil {

	private WebDriver driver;
	private Actions act;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		act = new Actions(driver);
	}

	private void nullCheck(CharSequence... value) {
		if (value == null) {
			throw new BrowserException("===Null Value===");
		}
	}

	public boolean isElementDisplayed(By locator) {
		try {
			return getElement(locator).isDisplayed();
		} catch (NoSuchElementException e) {
			System.out.println("Element not Present: " + locator);
			return false;
		}
	}

	public void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);

	}

	public void doSendKeys(By locator, CharSequence... value) {
		nullCheck(value);
		getElement(locator).sendKeys(value);

	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public String doElementGetText(By locator) {

		String eleText = getElement(locator).getText();
		System.out.println("Element Text:  " + eleText);
		return eleText;
	}

	public String customGetDomProperty(By locator, String value) {
		nullCheck(value);
		String propertyVal = getElement(locator).getDomProperty(value);
		System.out.println(propertyVal);
		return propertyVal;
	}

	public String customGetDomAttribute(By locator, String value) {
		nullCheck(value);
		String attributeVal = getElement(locator).getDomAttribute(value);
		System.out.println(attributeVal);
		return attributeVal;
	}

	// ================GET WEBELEMENTS USING BY LOCATORS===================

	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}

	//////////////// findElements Wrapper Methods ////////////////////////////

	public ArrayList<String> getElementsText(By locator) {
		List<WebElement> elements = getElements(locator);
		ArrayList<String> eleText = new ArrayList<String>();

		for (WebElement e : elements) {
			String aText = e.getText();
			if (aText.isEmpty()) {
				continue;
			} else {
				eleText.add(aText);
			}
		}
		return eleText;
	}

	public int listSize(List<WebElement> l) {
		return l.size();
	}

	public int listSize(By locator) {
		return getElements(locator).size();
	}

	public boolean checkElementDisplayed(By locator) {

		List<WebElement> eleText = getElements(locator);

		System.out.println(listSize(eleText));
		if (listSize(eleText) == 1) {
			return true;
		} else
			return false;
	}

	public boolean checkElementDisplayed(By locator, int expCount) {

		List<WebElement> eleText = getElements(locator);

		System.out.println(listSize(eleText));
		if (listSize(eleText) == expCount) {
			return true;
		} else
			return false;
	}

	// ==============SELECT DROPDOWN UTILS=================

	public boolean indexSelect(By locator, int index) {
		Select dropDown = new Select(getElement(locator));
		try {
			dropDown.selectByIndex(index);
			return true;
		} catch (NoSuchElementException e) {
			System.out.println("Index not Found: " + index);
			return false;
		}
	}

	public boolean valueSelect(By locator, String value) {
		Select dropDown = new Select(getElement(locator));
		try {
			dropDown.selectByValue(value);
			return true;
		} catch (NoSuchElementException e) {
			System.out.println("value Not Found: " + value);
			return false;
		}
	}

	public boolean visibleTextSelect(By locator, String visibleText) {
		Select dropDown = new Select(getElement(locator));
		try {

			dropDown.selectByVisibleText(visibleText);
			return true;
		} catch (NoSuchElementException e) {
			System.out.println("VisibleText Not Found: " + visibleText);
			return false;
		}

	}

	public boolean partialVisibleTextSelect(By locator, String partialText) {
		Select dropDown = new Select(getElement(locator));
		try {
			dropDown.selectByContainsVisibleText(partialText);
			return true;
		} catch (NoSuchElementException e) {
			System.out.println("PartialText Not Found: " + partialText);
			return false;
		}

	}

	public int getOptionsSize(List<WebElement> options) {
		return options.size();
	}

	public List<String> getAllOptions(By locator) {
		WebElement dropDown = getElement(locator);
		Select sel = new Select(dropDown);
		List<WebElement> options = sel.getOptions();
		List<String> optionsTextList = new ArrayList<String>();
		System.out.println(getOptionsSize(options));
		for (WebElement e : options) {
			String text = e.getText();
			optionsTextList.add(text.trim());
		}
		return optionsTextList;
	}

	public boolean checkSelectExpectedActualList(By locator, List<String> expectedList) {
		List<String> actualList = getAllOptions(locator);
		if (actualList.containsAll(expectedList)) {
			return true;
		} else
			return false;
	}

	public boolean selectDropDownValue(String value, By locator) {
		List<String> optionsText = getAllOptions(locator);
		if (optionsText.contains(value)) {
			for (String e : optionsText) {
				if (e.equals(value)) {
					doClick(locator);
					System.out.println(e);
				}
			}
			return true;
		} else {
			return false;
		}

	}

	public boolean selectDropDownValue(By locator, String value) {
		WebElement dropDown = getElement(locator);
		Select sel = new Select(dropDown);
		List<WebElement> options = sel.getOptions();
		boolean flag = false;
		for (WebElement e : options) {
			String text = e.getText();
			if (text.equals(value)) {
				e.click();
				System.out.println(text);
				flag = true;
				break;
			}
		}
		return flag;
	}

	/**
	 * 
	 * Input: WebElements using the By Locators Output: Text List of the WebElements
	 * 
	 * @param locator
	 * @return Dropdown text List
	 */
	public List<String> getOptionsNonSelectTags(By locator) {
		List<WebElement> dropDownEleList = driver.findElements(locator);
		List<String> dropDownTextList = new ArrayList<String>();

		for (WebElement e : dropDownEleList) {
			String text = e.getText();
			dropDownTextList.add(text);
		}
		return dropDownTextList;
	}

	/**
	 * Selects option for non Select DropDowns
	 * 
	 * 1. Single choice //selectChoice(locator, "choice 3"); 2. Multiple Choice
	 * //selectChoice(locator, "choice 1","choice 7","choice 4"); 3. Selects All
	 * when choice = 'All' // selectChoice(locator, "All");
	 * 
	 * @param clickLocator
	 * @param locator
	 * @param choice
	 * @throws InterruptedException
	 */

	public void selectChoice(By clickLocator, By locator, String... choice) throws InterruptedException {

		doClick(clickLocator);
		Thread.sleep(1000);
		List<WebElement> dropDownEleList = getElements(locator);
		Thread.sleep(2000);
		List<String> optionsNonSelectTags = getOptionsNonSelectTags(locator);

		List<String> asList = Arrays.asList(choice);
		System.out.println(asList);

		if (asList.get(0).equalsIgnoreCase("all")) {
			for (WebElement e : dropDownEleList) {
				e.click();
			}
		} else {

			if (optionsNonSelectTags.containsAll(asList)) {
				int choiceIndex = 0;
				for (String e : asList) {
					choiceIndex = optionsNonSelectTags.indexOf(e);
					dropDownEleList.get(choiceIndex).click();
					Thread.sleep(1000);
				}
			}
		}
	}

	// *************** Actions Class Utils *************************

	public void doMoveToElement(By locator) throws InterruptedException {
		act.moveToElement(getElement(locator)).perform();
		Thread.sleep(1000);
	}

	/**
	 * 
	 * @param parentLocator
	 * @param subMenuLocator
	 * @throws InterruptedException
	 */
	public void moveToParentClickSubMenu(By parentLocator, By subMenuLocator) throws InterruptedException {
		doMoveToElement(subMenuLocator);
		doClick(subMenuLocator);
	}

	/**
	 * 
	 * @param level1
	 * @param level2
	 * @param level3
	 * @param level4
	 * @throws InterruptedException
	 */
	public void fourLevelMenuHandle(By level1, By level2, By level3, By level4) throws InterruptedException {
		doClick(level1);
		doMoveToElement(level2);
		doMoveToElement(level3);
		doClick(level4);
	}

	public void doDragDrop(By srcEle, By trgtEle) {
		WebElement dragEle = getElement(srcEle);
		WebElement dropEle = getElement(trgtEle);
		act.dragAndDrop(dragEle, dropEle).perform();
	}

	public void doActionSendKeys(By locator, String value) {
		act.sendKeys(getElement(locator), value).perform();
	}

	public void doActionClick(By locator) {
		act.click(getElement(locator)).perform();
	}

	public void actionsSendKeysWithPause(By locator, String val, long pause) {

		WebElement textField = getElement(locator);
		char[] charArray = val.toCharArray();

		for (char c : charArray) {

			act.sendKeys(textField, String.valueOf(c)).pause(pause).perform();
		}

	}

	// ================GET WEBELEMENTS USING BY LOCATORS===================

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}
	
	// ==================== Wait Utils =========================
	
	/**
	 * 
	 An expectation for checking that an element is present on the DOM of a page. 
	 This does not necessarily mean that the element is visible.
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public  WebElement waitForElementPresence(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		
	}
	
	/**
	 * 
	 * An expectation for checking that an element is present on the DOM of a page and visible. 
	 * Visibility means that the element is not only displayed 
	 * but also has a height and width that is greater than 0.
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public  WebElement waitForElementVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		
	}
	
	public  List<WebElement> waitForElementsVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		}catch(TimeoutException e) {
			return Collections.emptyList();
		}
	}
	
	public void clickWithWait(By locator, int timeout) {
		waitForElementVisible(locator,timeout).click();
	}

	public void clickWhenReady(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}
	
	public void clickWhenReady(WebElement element, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}
	
	public void sendKeysWithWait(By locator, int timeout,CharSequence...str ) {
		waitForElementVisible(locator,timeout).sendKeys(str);
	}
	
	// ================= Wait Utils for JS Alerts =========================
	
	public  Alert waitForAlert(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.pollingEvery(Duration.ofSeconds(2))
		.ignoring(NoAlertPresentException.class)
		.withMessage("JS Alert Not Present");
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	
	public  void alertAccept(int timeout) {
		waitForAlert(timeout).accept();
	}
	
	public  void alertDismiss(int timeout) {
		waitForAlert(timeout).dismiss();
	}
	
	public  String alertGetText(int timeout) {
		return waitForAlert(timeout).getText();
	}
	
	public  void alertSendKeys(int timeout, String value) {
		waitForAlert(timeout).sendKeys(value);
	}
	
	//======================== Wait for Title ===========================
	
	public String waitForTitleContains(int timeout, String partOfTitle) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
		wait.until(ExpectedConditions.titleContains(partOfTitle));
		return driver.getTitle();
		}catch(TimeoutException e) {
			return null;
		}
	}
	
	public String waitForTitle(int timeout, String title) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
		wait.until(ExpectedConditions.titleIs(title));
		return driver.getTitle();
		}catch(TimeoutException e) {
			return null;
		}
	}
	
	public String waitForURLContains(int timeout, String partOfURL) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
		wait.until(ExpectedConditions.urlContains(partOfURL));
		return driver.getCurrentUrl();
		}catch(TimeoutException e) {
			return null;
		}
	}
	
	public String waitForURL(int timeout, String url) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
		wait.until(ExpectedConditions.urlToBe(url));
		return driver.getCurrentUrl();
		}catch(TimeoutException e) {
			return null;
		}
	}
	
	// ========================= Waits For Frames ================================
	
	public void waitForFrameAndSwitchTOIt(By frame, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
	}

	public void waitForFrameAndSwitchToIt(String frameNameOrID, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameNameOrID));
	}
	
	public void waitForFrameAndSwitchToIt(int frameIndex, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	}
	
	public void waitForFrameAndSwitchToIt(WebElement eleLocator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(eleLocator));
	}
	
	// =============================== Wait for Windows ==================================
	
	public boolean waitForWindows(int timeout,int expectedNoOfWindows) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		try{
			wait.until(ExpectedConditions.numberOfWindowsToBe(2));
			return true;
		}catch(TimeoutException e) {
			System.out.println("No. of Windows Mismatch");
			return false;
		}
	}
	
	// ======================== Wait for Page Load Complete ============================
	
	public boolean isPageReady(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		String state = wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete'")).toString();
		return Boolean.parseBoolean(state);
	}
	
	//================== Fluent Wait Utils ==========================
	
	public WebElement waitForElementVisibleWithFluentWait(By locator, int timeout, int pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class).withMessage("======Element Not Found=====");

		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public WebElement waitForElementPresenceWithFluentWait(By locator, int timeout, int pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class).withMessage("======Element Not Found=====");

		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
}
