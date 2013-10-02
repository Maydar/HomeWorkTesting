package ru.mail.homework.tests;

import java.net.URL;

import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import ru.mail.homework.src.ExchangeCodes;
import ru.mail.homework.src.ExchangeRates;

public class TestBrowserFirefox {
	private WebDriver firefoxDriver = new FirefoxDriver();		
	private ExchangeRates exchangeRatesFirefox = new ExchangeRates(firefoxDriver);
	
	@Test
	public void exchangeTestFirefox() {		
		firefoxDriver.get(ExchangeRates.URL);
		exchangeRatesFirefox.transferExchange(-100000);
		exchangeRatesFirefox.swapExchanges();
		exchangeRatesFirefox.changeInputExchange(ExchangeCodes.AZN.getExchangeCode());
	}
	
	
}
