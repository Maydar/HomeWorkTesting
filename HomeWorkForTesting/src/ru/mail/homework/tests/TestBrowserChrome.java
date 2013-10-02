package ru.mail.homework.tests;

import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import ru.mail.homework.src.ExchangeCodes;
import ru.mail.homework.src.ExchangeRates;

public class TestBrowserChrome {
	private WebDriver chromeDriver = new ChromeDriver();	
	private ExchangeRates exchangeRatesChrome = new ExchangeRates(chromeDriver);

	@Before
	public void setChromeDriver(){
		System.setProperty("webdriver.chrome.driver", "../../chromedriver");
	}
	@Test
	public void exchangeTestChrome() {
		chromeDriver.get(ExchangeRates.URL);
		exchangeRatesChrome.transferExchange(-100000);
		exchangeRatesChrome.swapExchanges();
		exchangeRatesChrome.changeInputExchange(ExchangeCodes.AZN.getExchangeCode());
	}
}
