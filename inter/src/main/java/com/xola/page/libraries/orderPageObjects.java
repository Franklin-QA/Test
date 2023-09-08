package com.xola.page.libraries;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utilities.libraries.SeleniumFunctions;

public class orderPageObjects {

	@FindBy(xpath = "//div[contains(text(),'Your booking ')]")
	private WebElement SuccessMessage;
	
	@FindBy(xpath = "//button[text()='Close']")
	private WebElement closeBtn;
	
	
	public WebElement getCloseBtn() {
		return closeBtn;
	}


	public WebElement getSuccessMessage() {
		return SuccessMessage;
	}


	public orderPageObjects() {
		PageFactory.initElements(new SeleniumFunctions().driver, this);
	}
}
