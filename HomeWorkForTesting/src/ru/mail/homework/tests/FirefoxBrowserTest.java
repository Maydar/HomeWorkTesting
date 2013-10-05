package ru.mail.homework.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.mail.homework.src.ExchangeCodes;
import ru.mail.homework.src.ExchangeRates;

/**
 * Created with IntelliJ IDEA.
 * User: maydar
 * Date: 05.10.13
 * Time: 18:04
 * To change this template use File | Settings | File Templates.
 */
public class FirefoxBrowserTest {
    private WebDriver firefoxDriver = new FirefoxDriver();
    private ExchangeRates exchangeRatesFirefox = new ExchangeRates(firefoxDriver);

    /**
     * Тестирование перевода валюты, проверка граничных, допустимых или недопустимых значений
     */
    @Test

    public void exchangeTestFirefox() {
        firefoxDriver.get(ExchangeRates.URL);

        exchangeRatesFirefox.transferExchange("selenium");
        Assert.assertEquals("", exchangeRatesFirefox.getOutputForm());

        exchangeRatesFirefox.transferExchange("0");
        Assert.assertEquals("0", exchangeRatesFirefox.getOutputForm());

        exchangeRatesFirefox.transferExchange("-10000000");
        Assert.assertEquals("", exchangeRatesFirefox.getOutputForm());


        exchangeRatesFirefox.transferExchange("10000000000000000000000000000");
        Assert.assertEquals("", exchangeRatesFirefox.getOutputForm());

        exchangeRatesFirefox.transferExchange("''#@$%*!*@#");
        Assert.assertEquals("", exchangeRatesFirefox.getOutputForm());

        exchangeRatesFirefox.transferExchange("<script>alert(1)</script>");
        Assert.assertEquals("", exchangeRatesFirefox.getOutputForm());
    }

    /**
     * Тестирование смены курса валют местами
     */
    @Test
    public void exchangeSwapTestFirefox(){
        firefoxDriver.get(ExchangeRates.URL);
        exchangeRatesFirefox.transferExchange("1290");
        String oldValueString = exchangeRatesFirefox.getOutputForm();
        exchangeRatesFirefox.swapExchanges();
        if (exchangeRatesFirefox.getOutputExchangeElem().equals(exchangeRatesFirefox.getInputExchangeElem())) {
            Assert.assertEquals(oldValueString, exchangeRatesFirefox.getOutputForm());
        }
        else {
            Assert.assertNotEquals(oldValueString, exchangeRatesFirefox.getOutputForm());
        }

    }


    /**
     * Тестирование смены курса различных валют
     */
    @Test
    public void changeInputExchangeTestFirefox(){
        firefoxDriver.get(ExchangeRates.URL);
        exchangeRatesFirefox.transferExchange("1290");
        String oldValueString = exchangeRatesFirefox.getOutputForm();

        for(ExchangeCodes o : ExchangeCodes.values()) {

            exchangeRatesFirefox.changeInputExchange(o.getExchangeCode());

            if (exchangeRatesFirefox.getOutputExchangeElem().equals(exchangeRatesFirefox.getInputExchangeElem())) {
                Assert.assertEquals(oldValueString, exchangeRatesFirefox.getOutputForm());
            }
            else {
                Assert.assertNotEquals(oldValueString, exchangeRatesFirefox.getOutputForm());
            }
        }

    }

    @Test
    public void changeOutputExchangeTestFirefox(){
        firefoxDriver.get(ExchangeRates.URL);
        exchangeRatesFirefox.transferExchange("1290");
        String oldValueString = exchangeRatesFirefox.getOutputForm();

        for(ExchangeCodes o : ExchangeCodes.values()) {

            exchangeRatesFirefox.changeOutputExchange(o.getExchangeCode());

            if (exchangeRatesFirefox.getOutputExchangeElem().equals(exchangeRatesFirefox.getInputExchangeElem())) {
                Assert.assertEquals(oldValueString, exchangeRatesFirefox.getOutputForm());
            }
            else {

                Assert.assertNotEquals(oldValueString, exchangeRatesFirefox.getOutputForm());
            }
        }

    }
}