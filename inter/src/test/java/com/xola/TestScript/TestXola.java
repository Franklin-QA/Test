package com.xola.TestScript;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.utilities.libraries.BaseTest;
import com.utilities.libraries.SeleniumFunctions;
import com.xola.page.libraries.CheckoutPageObjects;
import com.xola.page.libraries.FirstPageObjects;
import com.xola.page.libraries.orderPageObjects;


public class TestXola extends BaseTest {
	SeleniumFunctions functions;
	FirstPageObjects Homeobj;
	CheckoutPageObjects checkout;
	orderPageObjects orderObj;
	Properties prop ;
	
	@BeforeClass
	public void initialSetup() {
		Homeobj = new FirstPageObjects();
		functions= new SeleniumFunctions();
		prop= new Properties();
		checkout= new CheckoutPageObjects();
		orderObj= new orderPageObjects();
		try {
			prop.load(new FileInputStream("C:\\Users\\frank\\eclipse-workspace\\New\\inter\\rescources\\environment.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test() {
		functions.click(Homeobj.getPrivateRadioBtn());
		functions.click(Homeobj.getAdultIncreaseBtn());
		functions.click(Homeobj.getChildIncreaseBtn());
		functions.click(Homeobj.getDatePick());
		functions.click(Homeobj.getTimeSlotButton());
		functions.SendKeys(Homeobj.getNameBox(), prop.getProperty("name"));
		functions.SendKeys(Homeobj.geteMailBox(), prop.getProperty("email"));
		functions.SendKeys(Homeobj.getPhoneBox(), prop.getProperty("phone"));
		functions.click(Homeobj.getContinueBtn());
		Assert.assertEquals(functions.getCurrentPageTitle(), "Xola - Checkout");
		System.out.println("firstPageDone");
		Assert.assertTrue(functions.getElementText(checkout.getName()).equalsIgnoreCase(prop.getProperty("name")));
		Assert.assertTrue(functions.getElementText(checkout.geteMail()).contains(prop.getProperty("email")));
		Assert.assertTrue(functions.getElementText(checkout.getPhone()).contains(prop.getProperty("phone")));
		Assert.assertTrue(functions.getAttributeText(checkout.getBillingName(),"value").contains(prop.getProperty("name")));
		String cardNumber=prop.getProperty("cardNumber");
		for (int i=0;i<cardNumber.length();i++) {
			functions.SendKeys(checkout.getCardNumBerBox(), String.valueOf(cardNumber.charAt(i)));
		}
		
		
		functions.SendKeys(checkout.getSecurityCodeBox(), prop.getProperty("cvv"));
		functions.selectOptionByGivenValue(checkout.getMonthDD(), "2");
		functions.selectOptionByGivenValue(checkout.getYaerDD(), "2024");
		functions.SendKeys(checkout.getZipcode(),prop.getProperty("zipcode"));
		functions.click(checkout.getPayFullRadio());
		System.out.println("LastStep");
		functions.click(checkout.getPayBtn());
		System.out.println("SecondPageDone");
		System.out.println(functions.getElementText(orderObj.getSuccessMessage()).trim());
		Assert.assertTrue(functions.getElementText(orderObj.getSuccessMessage()).trim().equalsIgnoreCase(prop.getProperty("SuccessMessage")));
		Assert.assertTrue(functions.getElementText(checkout.getName()).equalsIgnoreCase(prop.getProperty("name")));
		functions.click(orderObj.getCloseBtn());
		System.out.println("Test Case Passed");
		
		
	}

}
