package com.utilities.libraries;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumFunctions {

	public static WebDriver driver;
	TargetLocator targetLocatorObj;

	static Properties prop = new Properties();
	
	// Method to open browser with implicit wait
	public static WebDriver launchBrowser() throws InterruptedException, FileNotFoundException, IOException {
		prop.load(new FileInputStream("C:\\Users\\frank\\eclipse-workspace\\New\\inter\\rescources\\environment.properties"));
		String sBrowser = prop.getProperty("browser");
		boolean bFlagForRetry;
		int iMaxRetry = 5;
		do {
			bFlagForRetry = false;
			try {
				if ("Firefox".equalsIgnoreCase(sBrowser)) {
					WebDriverManager.firefoxdriver().setup();
					driver = new FirefoxDriver();
				}
				else if ("Chrome".equalsIgnoreCase(sBrowser)) {
					WebDriverManager.chromedriver().setup();
					driver = new ChromeDriver();
				} else if ("ie".equalsIgnoreCase(sBrowser)) {
					WebDriverManager.iedriver().setup();
					driver = new InternetExplorerDriver();
				}
				else if ("safari".equalsIgnoreCase(sBrowser)) {
					System.setProperty("webdriver.safari.noinstall", "true");
					DesiredCapabilities capabilities = DesiredCapabilities.safari();
					SafariOptions options = new SafariOptions();
					// options.setUseCleanSession(true);
					capabilities.setCapability(SafariOptions.CAPABILITY, options);
					driver = new SafariDriver();
				} else if ("edge".equalsIgnoreCase(sBrowser)) {
					WebDriverManager.edgedriver().setup();
					driver = new EdgeDriver();
				}
			} catch (Exception ex) {
				Reporter.log(" " + ex);
				Thread.sleep(5000);
				bFlagForRetry = true;
				iMaxRetry--;
			}
		} while (bFlagForRetry && iMaxRetry > 0);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		return driver;
	}

	// Method for implicitwait
	public void implicitWait(int iTimeUnits) {
		driver.manage().timeouts().implicitlyWait(iTimeUnits, TimeUnit.SECONDS);
	}


	public void clearCookies() {
		driver.manage().deleteAllCookies();

	}

	// Method to close driver instance
	public WebDriver quitBrowser() {
		driver.quit();
		return driver;
	}

	// Method to Accept Modal Dialog Alert Popup
	public void handleAlertPopup() {
		try {
			driver.switchTo().alert().accept();
		} catch (NoAlertPresentException e) {
			Reporter.log("Alert not present " + e);
		}
	}

	// Method to press Escape key from Keyboard
	public void pressEscapeKey() {
		Actions oAction = new Actions(driver);
		oAction.sendKeys(Keys.ESCAPE);
	}

	// Method to wait for page header
	public ExpectedCondition<Boolean> WaitForPageHeader(final WebElement oElement, final String sText) {
		return new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return oElement.getText().contains(sText);
			}
		};
	}

	public ExpectedCondition<Boolean> CheckForPageTitle(final String sText) {
		return new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return driver.getTitle().contains(sText);
			}
		};
	}

	public void WaitForPageTitle(String sText) {
		WebDriverWait oWebDriverWait = new WebDriverWait(driver, Integer.parseInt(prop.getProperty("implicitwait")));
		oWebDriverWait.until(CheckForPageTitle(sText));
	}

	public ExpectedCondition<Boolean> CheckForPageUrl(final String sURL) {
		return new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return driver.getCurrentUrl().contains(sURL);
			}
		};
	}

	public void WaitForPageUrl(String sURL) {
		WebDriverWait oWebDriverWait = new WebDriverWait(driver, Integer.parseInt(prop.getProperty("implicitwait")));
		oWebDriverWait.until(CheckForPageUrl(sURL));
	}

	// Method to wait for given element to be clickable
	public void WaitForElementToClick(WebElement oWebElement) {
		WebDriverWait oWebDriverWait = new WebDriverWait(driver, Integer.parseInt(prop.getProperty("implicitwait")));
		oWebDriverWait.until(ExpectedConditions.elementToBeClickable(oWebElement));
	}

	/*
	 * //Method to wait for element public Predicate<WebDriver>
	 * elementDisplayed(final WebElement oElement) { return new
	 * Predicate<WebDriver>() {
	 * 
	 * @Override public boolean apply(WebDriver driver) { return
	 * isElementDisplayed(oElement); } }; }
	 */

	// Method to wait for given element to be displayed
	public boolean isElementDisplayed(WebElement oElement) {
		try {
			oElement.isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			Reporter.log("" + e);
			return false;
		}
	}

	// Method to wait for given element to be Selected
	public boolean isElementSelected(WebElement oElement) {
		try {
			oElement.isSelected();
			return true;
		} catch (NoSuchElementException e) {
			Reporter.log("" + e);
			return false;
		}
	}

	public boolean isElementDisplayed(WebElement oElement, boolean isCatchRequired) {
		try {
			oElement.isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			if (!isCatchRequired) {
				Reporter.log("" + e);
			}
			return false;
		}
	}

	// Method to navigate back in browser
	public void goToPreviousPage() {
		driver.navigate().back();

	}

	// Method to delete browser cookies
	public void deleteCookies() {
		driver.manage().deleteAllCookies();

	}

	public void closeFrame() {
		driver.switchTo().defaultContent();

	}

	// Method to get current page title
	public String getCurrentPageTitle() {

		return driver.getTitle().trim();
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl().trim();
	}

	// Method to check vitacost technical difficulties page
	public static void checkForTechnicalDifficulties() {
		if (driver
				.findElements(By.xpath(
						"//div[@style!='display:none;']/*[contains(text(),'We are having technical difficulties')]"))
				.size() > 0) {
			Reporter.log("Technical difficulty error code: ");
		}

	}

	public WebElement syncOnObject(WebElement oSyncObject, int iTimeUnit) {
		Wait<WebDriver> wait = new WebDriverWait(driver, iTimeUnit);
		return wait.until(ExpectedConditions.visibilityOf(oSyncObject));
	}

	public boolean isElementDisabled(WebElement oElement) {
		if (!oElement.isEnabled())
			return true;
		return false;
	}

	public WebElement syncOnAjaxObject(WebElement oSyncObject, int iTimeUnit) {
		Wait<WebDriver> wait = new WebDriverWait(driver, iTimeUnit);
		return wait.until(ExpectedConditions.visibilityOf(oSyncObject));
	}

	public String getCurrentWindowHandle() {
		return driver.getWindowHandle();
	}

	public int getOpenWindowCount() {
		return driver.getWindowHandles().size();
	}

	public String switchToNewWindowOpen(String sParentWindowHandle, String sChildWindowHandle) {
		for (String sWinHandle : driver.getWindowHandles()) {
			if (!(sWinHandle.equalsIgnoreCase(sChildWindowHandle) || sWinHandle.equals(sParentWindowHandle))) {
				driver.switchTo().window(sWinHandle);
				return sWinHandle;
			}
		}
		return null;
	}

	public String switchToNewWindowOpen(String sParentWindowHandle) {
		for (String sWinHandle : driver.getWindowHandles()) {
			if (!sWinHandle.equals(sParentWindowHandle)) {
				driver.switchTo().window(sWinHandle);
				return sWinHandle;
			}
		}
		return null;
	}

	public void closeWindowByHandle(String sWinHandle) {
		driver.switchTo().window(sWinHandle).close();
	}

	public void switchToWindowByHandle(String sWinHandle) {
		driver.switchTo().window(sWinHandle);
	}

	public void clickOpenInNewWindowOrTab(WebElement oElement, String sNewWindowOrTab) {
		Actions oActions = new Actions(SeleniumFunctions.driver);
		syncOnAjaxObject(oElement, Integer.parseInt(prop.getProperty("implicitwait")));
		switch (sNewWindowOrTab.toLowerCase()) {
		case "window":
			if (!("safari".equalsIgnoreCase(prop.getProperty("browser"))
					|| "ie".equalsIgnoreCase(prop.getProperty("browser"))))
				oElement.sendKeys(Keys.chord(Keys.SHIFT, Keys.RETURN));
			else {
				String sUrl = oElement.getAttribute("href");
				((JavascriptExecutor) driver).executeScript("window.open('" + sUrl + "')", oElement);
			}
			break;
		default:
			oActions.keyDown(Keys.CONTROL).click(oElement).keyUp(Keys.CONTROL).build().perform();
			break;
		}

	}

	public void mouseHoverToElement(WebElement oElement) {
		HighlightElement(oElement);
		Actions oAction = new Actions(driver);
		oAction.moveToElement(oElement).build().perform();
	}

	public void mouseHoverToCartIcon(WebElement oElement) {
		String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(mouseOverScript, oElement);
	}

	public void focusToElement(WebElement oElement) {
		if (!"safari".equalsIgnoreCase(prop.getProperty("browser"))) {
			new Actions(driver).moveToElement(oElement).perform();
		} else {
			String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(mouseOverScript, oElement);
		}
	}

	public <T> boolean waitForElement(WebElement oElement, Class<T> clsExpectedPage, boolean bExpectedExists,
			int iWaitSeconds) throws InterruptedException {
		boolean bExists = false;
		PageFactory.initElements(SeleniumFunctions.driver, clsExpectedPage);
		long dtEndTime = System.currentTimeMillis() + (iWaitSeconds * 1000);
		try {
			if (oElement != null) {
				do {
					bExists = isElementPresent(oElement);
				} while (bExists != bExpectedExists && System.currentTimeMillis() < dtEndTime);
			}
		} catch (NoSuchElementException e) {
			Reporter.log("Element Not Found " + e);
			return false;
		}

		return bExists;
	}

	public boolean isElementPresent(WebElement oElement) throws InterruptedException {
		boolean bExists = false;
		try {
			if (isElementDisplayed(oElement) && ExpectedConditions.visibilityOf(oElement) != null
					&& (!oElement.getAttribute("style").contains("display:none")
							&& !oElement.getAttribute("style").contains("display: none")))
				bExists = true;
		} catch (Exception e) {
			Reporter.log(" " + e);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				Reporter.log("Interrupted " + e1);
				throw e1;
			}
		}
		return bExists;
	}

	public void gotoURL(String sUrl) {
		driver.get(sUrl);
	}

	public WebDriver getFrame(String sFrameName) {
		targetLocatorObj = driver.switchTo();
		return targetLocatorObj.frame(sFrameName);
	}

	public WebDriver navigateiFrameViaindex(int iFrameIndex) {
		targetLocatorObj = driver.switchTo();
		return targetLocatorObj.frame(iFrameIndex);
	}

	// method to navigate to iFrame uisng webelement
	public WebDriver navigateiFrameViaWebElement(WebElement iframe) {
		targetLocatorObj = driver.switchTo();
		return targetLocatorObj.frame(iframe);
	}

	// Method to select given option
	public void selectGivenOption(WebElement oSelectObject, String sOption) {
		Select oSelectOptions = new Select(oSelectObject);
		oSelectOptions.selectByVisibleText(sOption);
	}

	public void waitForCondition(WebElement oElement, String sTextToPresent) {
		WebDriverWait oWebDriverWait = new WebDriverWait(driver, Integer.parseInt(prop.getProperty("implicitwait")));
		oWebDriverWait.until(CheckForElementText(oElement, sTextToPresent));
	}

	public ExpectedCondition<Boolean> CheckForElementText(final WebElement oElement, final String sTextToPresent) {
		return new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return oElement.getText().trim().contains(sTextToPresent);
			}
		};
	}

	public void waitForMsgPopUp() throws InterruptedException {
		if (!"firefox".equalsIgnoreCase(prop.getProperty("browser")))
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {

			}
	}


	// Method to select given option by value
	public void selectOptionByGivenValue(WebElement oSelectObject, String sValue) {
		Select oSelectOptions = new Select(oSelectObject);
		oSelectOptions.selectByValue(sValue);
	}

	public void HighlightElement(WebElement oElement) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].style.border='2px solid red'", oElement);
	}

	{

	}

	public void clickUsingJava(WebElement oElement) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].style.border='2px solid red'", oElement);
		executor.executeScript("arguments[0].click();", oElement);
	}

	public void waitForImageToLoad() throws InterruptedException {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {

		}
	}

	public void waitForShop() throws InterruptedException {
		Thread.sleep(2000);
	}



	public void refreshPage() {
		driver.get(getCurrentUrl());
	}

	public void focusToWindow() {
		for (String sWinHandle : SeleniumFunctions.driver.getWindowHandles()) {
			SeleniumFunctions.driver.switchTo().window(sWinHandle);
			break;
		}
	}

	public void scrollDown() {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.chord(Keys.CONTROL, Keys.END)).perform();
	}

	public void scrollHome() {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.chord(Keys.CONTROL, Keys.HOME)).perform();
	}

	public void scrollUpto(int pixels) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0," + pixels + ")");
	}

	public void click(WebElement oElement) {
		WaitForElementToClick(oElement);
		HighlightElement(oElement);
		if (isElementDisplayed(oElement)) {
			oElement.click();
		}
	}
	public void SendKeys(WebElement oElement,String sValue) {
		WaitForElementToClick(oElement);
		if (isElementDisplayed(oElement)) {
			HighlightElement(oElement);
			oElement.sendKeys(sValue);
		}
	}
	public String getElementText(WebElement oElement) {
		WaitForElementToClick(oElement);
		String Text="";
		if (isElementDisplayed(oElement)) {
			HighlightElement(oElement);
			Text= oElement.getText();
		}
		return Text;
	}
	public String getAttributeText(WebElement oElement, String Attribute) {
		WaitForElementToClick(oElement);
		String Text="";
		if (isElementDisplayed(oElement)) {
			HighlightElement(oElement);
			Text= oElement.getAttribute(Attribute);
		}
		return Text;
	}

	public void closeBrowser() {

		driver.close();
	}

	public String getText(WebElement oElement) {
		return (String) ((JavascriptExecutor) driver).executeScript("return jQuery(arguments[0]).text();", oElement);
	}

	public String getParentText(WebElement oElement) {
		String sParentText = oElement.getText().trim();
		sParentText = sParentText.replace("\n", " ");
		String sChildText = "";
		for (WebElement oChildElem : oElement.findElements(By.xpath(".//*"))) {
			if (!sChildText.contains(oChildElem.getText().trim()))
				if (oElement.findElements(By.xpath(".//*")).size() > 1)
					sChildText += " " + oChildElem.getText().trim();
				else
					sChildText += oChildElem.getText().trim();
		}
		sChildText = sChildText.trim();
		return sParentText.replace(sChildText, "").trim();
	}

	public void waitForJavaClick() throws InterruptedException {
		Thread.sleep(1000);
	}

	public void longWait() throws InterruptedException {
		Thread.sleep(5000);
	}

}
