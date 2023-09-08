package com.xola.page.libraries;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utilities.libraries.SeleniumFunctions;

public class CheckoutPageObjects {

	@FindBy(xpath = "//h4[@class='customer-name']")
	private WebElement Name;
	
	@FindBy(xpath = "//div[@class='customer-email']")
	private WebElement eMail;
	
	@FindBy(xpath = "//div[@class='customer-phone']")
	private WebElement phone;
	
	@FindBy(xpath = "//input[@name='billingName']")
	private WebElement BillingName;
	
	@FindBy(id = "cc-number")
	private WebElement cardNumBerBox;
	
	@FindBy(name = "cvv")
	private WebElement securityCodeBox;
	
	@FindBy(xpath = "//dt[contains(text(),'Adults')]/child::var")
	private WebElement adultCount;
	
	@FindBy(xpath = "//dt[contains(text(),'Children ')]/child::var")
	private WebElement childCount;
	
	@FindBy(xpath = "//input[@value='pay-full']")
	private WebElement payFullRadio;
	
	@FindBy(xpath = "//input[@name='billingPostcode']")
	private WebElement zipcode;
	
	public WebElement getZipcode() {
		return zipcode;
	}



	@FindBy(xpath = "//button[@class='btn btn-success action-pay']/span[2]")
	private WebElement payBtn;
	
	@FindBy(xpath = "//select[@name='expiryMonth']")
	private WebElement monthDD;
	
	@FindBy(xpath = "//select[@name='expiryYear']")
	private WebElement yaerDD;
	
	
	
	public WebElement getMonthDD() {
		return monthDD;
	}



	public WebElement getYaerDD() {
		return yaerDD;
	}



	public WebElement getName() {
		return Name;
	}



	public WebElement geteMail() {
		return eMail;
	}



	public WebElement getPhone() {
		return phone;
	}



	public WebElement getBillingName() {
		return BillingName;
	}



	public WebElement getCardNumBerBox() {
		return cardNumBerBox;
	}



	public WebElement getSecurityCodeBox() {
		return securityCodeBox;
	}



	public WebElement getAdultCount() {
		return adultCount;
	}



	public WebElement getChildCount() {
		return childCount;
	}



	public WebElement getPayFullRadio() {
		return payFullRadio;
	}



	public WebElement getPayBtn() {
		return payBtn;
	}



	public CheckoutPageObjects() {
		PageFactory.initElements(new SeleniumFunctions().driver, this);
	}
}
