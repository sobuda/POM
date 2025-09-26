package com.qa.opencart.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavascriptUtil {

	private WebDriver driver;
	private JavascriptExecutor js;
	
	public JavascriptUtil(WebDriver driver) {
		this.driver =driver;
		js = (JavascriptExecutor)driver;
	}
	
	
	public String jsGetTitle() {
		return js.executeScript("return document.title;").toString();
		
	}
	
	public String jsGetURL() {
		return js.executeScript("return document.URL;").toString();
	}
	
	public void jsRefreshPage() {
		js.executeScript("history.go(0);");
	}
	
	public void jsGoToPreviousPage() {
		js.executeScript("history.go(-1);");
	}
	
	public void jsGoToForwardPage() {
		js.executeScript("history.go(1)");
	}
	
	public void jsAlert(String message) {
		js.executeScript("alert('"+message+"')");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.switchTo().alert().dismiss();
	}
	
	public String jsGetInnerText() {
		return js.executeScript("return document.documentElement.innerText;").toString();
	}
	
	public void jsPageScrollDown() {
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}
	
	public void jsPageScrollDown(String height) {
		js.executeScript("window.scrollTo(0,'"+height+"')");
	}
	
	public void jsPageScrollUp() {
		js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
	}
	
	public void jsScrollIntoView(WebElement element) {
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public void drawBorder(WebElement element) {
		js.executeScript("arguments[0].style.border='3px solid red'", element);
	}
	
	public void flash(WebElement element) {
		String bgColor = element.getCssValue("backgroundColor");
		for(int i=0;i<10;i++) {
			changeColor("rgb(0,200,0)", element);
			changeColor(bgColor, element);
		}
	}
	
	public void changeColor(String color,WebElement element) {
		js.executeScript("arguments[0].style.backgroundColor = '"+color+"'", element);
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void zoomChromeEdgeFirefox(String zoomPercent) {
		String zoom = "document.body.style.zoom = '"+zoomPercent+"%'";
		js.executeScript(zoom);
	}
	
	public void zoomFirefox(String zoom) {
		js.executeScript("document.body.style.MozTransform= 'scale("+zoom+")'");
	}
	
	public void jsClick(WebElement element) {
		js.executeScript("arguments[0].click();",element);
	}
	
	public void jsSendKeys(String id,String value) {
		js.executeScript("document.getElementById('"+id+"').value = '"+value+"'");
		//document.getElementById('input-email').value = 'testing'
	}
}

