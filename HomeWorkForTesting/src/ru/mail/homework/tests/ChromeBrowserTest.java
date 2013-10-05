package ru.mail.homework.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.mail.homework.src.ExchangeCodes;
import ru.mail.homework.src.ExchangeRates;

/**
 * Created with IntelliJ IDEA.
 * User: maydar
 * Date: 05.10.13
 * Time: 18:03
 * To change this template use File | Settings | File Templates.
 */
public class ChromeBrowserTest {
    private WebDriver chromeDriver = new ChromeDriver();
    private ExchangeRates exchangeRatesChrome = new ExchangeRates(chromeDriver);

    @BeforeTest
    public void setChromeDriver(){
        System.setProperty("webdriver.Chrome.driver", "../../Chromedriver");
    }
    /**
     * Тестирование перевода валюты, проверка граничных, допустимых или недопустимых значений
     */
    @Test
    public void exchangeTestChrome() {
        chromeDriver.get(ExchangeRates.URL);

        exchangeRatesChrome.transferExchange("selenium");
        Assert.assertEquals("", exchangeRatesChrome.getOutputForm());

        exchangeRatesChrome.transferExchange("0");
        Assert.assertEquals("0", exchangeRatesChrome.getOutputForm());

        exchangeRatesChrome.transferExchange("-10000000");
        Assert.assertEquals("", exchangeRatesChrome.getOutputForm());


        exchangeRatesChrome.transferExchange("10000000000000000000000000000");
        Assert.assertEquals("", exchangeRatesChrome.getOutputForm());

        exchangeRatesChrome.transferExchange("''#@$%*!*@#");
        Assert.assertEquals("", exchangeRatesChrome.getOutputForm());

        exchangeRatesChrome.transferExchange("<script>alert(1)</script>");
        Assert.assertEquals("", exchangeRatesChrome.getOutputForm());



    }

    /**
     * Тестирование смены курса валют местами
     */
    @Test
    public void exchangeSwapTestChrome(){
        chromeDriver.get(ExchangeRates.URL);
        String oldValueString = exchangeRatesChrome.getOutputForm();
        exchangeRatesChrome.transferExchange("1290");
        exchangeRatesChrome.swapExchanges();
        if (exchangeRatesChrome.getOutputExchangeElem().equals(exchangeRatesChrome.getInputExchangeElem())) {
            Assert.assertEquals(oldValueString, exchangeRatesChrome.getOutputForm());
        }
        else {
            Assert.assertNotEquals(oldValueString, exchangeRatesChrome.getOutputForm());
        }
    }


    /**
     * Тестирование смены курса различных валют
     */
    @Test
    public void changeInputExchangeTestChrome(){
        chromeDriver.get(ExchangeRates.URL);
        exchangeRatesChrome.transferExchange("1290");
        String oldValueString = exchangeRatesChrome.getOutputForm();

        for(ExchangeCodes o : ExchangeCodes.values()) {

            exchangeRatesChrome.changeInputExchange(o.getExchangeCode());

            if (exchangeRatesChrome.getOutputExchangeElem().equals(exchangeRatesChrome.getInputExchangeElem())) {
                Assert.assertEquals(oldValueString, exchangeRatesChrome.getOutputForm());
            }
            else {
                Assert.assertNotEquals(oldValueString, exchangeRatesChrome.getOutputForm());
            }
        }

    }

    @Test
    public void changeOutputExchangeTestChrome(){
        chromeDriver.get(ExchangeRates.URL);
        exchangeRatesChrome.transferExchange("1290");
        String oldValueString = exchangeRatesChrome.getOutputForm();

        for(ExchangeCodes o : ExchangeCodes.values()) {

            exchangeRatesChrome.changeOutputExchange(o.getExchangeCode());

            if (exchangeRatesChrome.getOutputExchangeElem().equals(exchangeRatesChrome.getInputExchangeElem())) {
                Assert.assertEquals(oldValueString, exchangeRatesChrome.getOutputForm());
            }
            else {
                Assert.assertNotEquals(oldValueString, exchangeRatesChrome.getOutputForm());
            }
        }

    }
}
