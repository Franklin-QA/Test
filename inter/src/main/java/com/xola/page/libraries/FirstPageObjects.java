package com.xola.page.libraries;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utilities.libraries.SeleniumFunctions;

public class FirstPageObjects {
	
	@FindBy(xpath = "//input[@value='private']")
	private WebElement privateRadioBtn;
	@FindBy(xpath = "//button[@aria-label='Increase Adults by 1. Current count is 1']")
	private WebElement adultIncreaseBtn;
	@FindBy(xpath = "//button[@aria-label='Increase Children by 1. Current count is 0']")
	private WebElement childIncreaseBtn;
	@FindBy(xpath = "//div[@class='ui-datepicker-item']/parent::a[text()='10']")
	private WebElement datePick;
	@FindBy(xpath = "//div[@class='arrival-time-btn-wrapper'][2]/button")
	private WebElement timeSlotButton;
	@FindBy(id = "customerName")
	private WebElement NameBox;
	@FindBy(id = "customerEmail")
	private WebElement eMailBox;
	@FindBy(id = "phone")
	private WebElement phoneBox;
	@FindBy(xpath  = "//button[text()='Continue']")
	private WebElement ContinueBtn;
	
	
	public WebElement getPrivateRadioBtn() {
		return privateRadioBtn;
	}
	public WebElement getAdultIncreaseBtn() {
		return adultIncreaseBtn;
	}
	public WebElement getChildIncreaseBtn() {
		return childIncreaseBtn;
	}
	public WebElement getDatePick() {
		return datePick;
	}
	public WebElement getTimeSlotButton() {
		return timeSlotButton;
	}
	public WebElement getNameBox() {
		return NameBox;
	}
	public WebElement geteMailBox() {
		return eMailBox;
	}
	public WebElement getPhoneBox() {
		return phoneBox;
	}
	public WebElement getContinueBtn() {
		return ContinueBtn;
	}
	
	public FirstPageObjects() {
		PageFactory.initElements(new SeleniumFunctions().driver, this);
	}
	
	
	
	
}
