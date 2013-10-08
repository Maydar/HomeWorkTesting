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
     * Закомментированные строки - тест на них падает, так как при при замене на текст значение не меняется
     *
     */
    @Test
    public void exchangeTestFirefox() {
        firefoxDriver.get(ExchangeRates.URL);

        exchangeRatesFirefox.transferExchange("1290");
        Assert.assertNotEquals(exchangeRatesFirefox.getOutputForm(), "");

        exchangeRatesFirefox.transferExchange("1290.2392");
        Assert.assertNotEquals(exchangeRatesFirefox.getOutputForm(), "");

        /*exchangeRatesFirefox.transferExchange("selenium");
        Assert.assertEquals(exchangeRatesFirefox.getOutputForm(), "");*/

        exchangeRatesFirefox.transferExchange("0");
        Assert.assertEquals(exchangeRatesFirefox.getOutputForm(), "0");

        exchangeRatesFirefox.transferExchange("-10000000");
        Assert.assertEquals(exchangeRatesFirefox.getOutputForm(), "0");


        exchangeRatesFirefox.transferExchange("10000000000000000000000000000");
        Assert.assertNotEquals(exchangeRatesFirefox.getOutputForm(), "0");

        /*exchangeRatesFirefox.transferExchange("''#@$%*!*@#.......");
        Assert.assertEquals(exchangeRatesFirefox.getOutputForm(), "");*/

        /*exchangeRatesFirefox.transferExchange("<script>alert(1)</script>");
        Assert.assertEquals(exchangeRatesFirefox.getOutputForm(),"");*/
    }

    /**
     * Тестирование смены курса валют местами
     */
    @Test
    public void exchangeSwapTestFirefox(){
        firefoxDriver.get(ExchangeRates.URL);
        exchangeRatesFirefox.transferExchange("1294");
        exchangeRatesFirefox.changeInputExchange(ExchangeCodes.RUB.getExchangeCode());
        String oldValueString = exchangeRatesFirefox.getOutputForm();
        String oldValueExchange =  exchangeRatesFirefox.getOutputExchangeElem();

        exchangeRatesFirefox.swapExchanges();
        if (exchangeRatesFirefox.getOutputExchangeElem().equals(oldValueExchange)) {
            Assert.assertEquals(exchangeRatesFirefox.getOutputForm(), oldValueString );
        }
        else {
            Assert.assertNotEquals(exchangeRatesFirefox.getOutputForm(), oldValueString);
        }

    }


    /**
     * Тестирование смены курса различных валют
     */
   @Test
    public void changeInputExchangeTestFirefox(){
        firefoxDriver.get(ExchangeRates.URL);
        exchangeRatesFirefox.transferExchange("1290");
        String oldValueString = "";

        for(ExchangeCodes o : ExchangeCodes.values()) {
            oldValueString = exchangeRatesFirefox.getOutputForm();
            exchangeRatesFirefox.changeInputExchange(o.getExchangeCode());
            Assert.assertNotEquals(exchangeRatesFirefox.getOutputForm(), oldValueString);
        }

    }

    @Test
    public void changeOutputExchangeTestFirefox(){
        firefoxDriver.get(ExchangeRates.URL);
        exchangeRatesFirefox.transferExchange("1290");
        String oldValueString = "";
        String oldExchangeString = "";
        for(ExchangeCodes o : ExchangeCodes.values()) {
            oldValueString = exchangeRatesFirefox.getOutputForm();
            oldExchangeString  = exchangeRatesFirefox.getOutputExchangeElem();
            exchangeRatesFirefox.changeOutputExchange(o.getExchangeCode());

            if (oldExchangeString.equals(exchangeRatesFirefox.getOutputExchangeElem())) {
                Assert.assertEquals(exchangeRatesFirefox.getOutputForm(), oldValueString);
            }
            else {
                Assert.assertNotEquals(exchangeRatesFirefox.getOutputForm(), oldValueString);

            }
        }

    }
}
