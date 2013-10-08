package ru.mail.homework.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.mail.homework.src.ExchangeCodes;
import ru.mail.homework.src.ExchangeRates;

import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: maydar
 * Date: 05.10.13
 * Time: 18:03
 * To change this template use File | Settings | File Templates.
 *
 *  Закомментированные строки - тест на них падает, так как при при замене на текст значение не меняется
 */

public class ChromeBrowserTest {
    private static WebDriver chromeDriver = new ChromeDriver();
    private ExchangeRates exchangeRatesChrome = new ExchangeRates(chromeDriver);

    /**
     * Тестирование перевода валюты, проверка граничных, допустимых или недопустимых значений
     */
    @Test
    public void exchangeTestChrome() {
        System.setProperty("webdriver.chrome.driver", "/home/maydar/git/HomeWorkTesting/HomeWorkForTesting/chromedriver");
        chromeDriver.get(ExchangeRates.URL);

        exchangeRatesChrome.transferExchange("1290");
        Assert.assertNotEquals(exchangeRatesChrome.getOutputForm(), "");

        exchangeRatesChrome.transferExchange("1290.2392");
        Assert.assertNotEquals(exchangeRatesChrome.getOutputForm(), "");

        /*exchangeRatesChrome.transferExchange("selenium");
        Assert.assertEquals(exchangeRatesChrome.getOutputForm(), "");*/

        exchangeRatesChrome.transferExchange("0");
        Assert.assertEquals(exchangeRatesChrome.getOutputForm(), "0");

        exchangeRatesChrome.transferExchange("-10000000");
        Assert.assertEquals(exchangeRatesChrome.getOutputForm(), "0");


        exchangeRatesChrome.transferExchange("10000000000000000000000000000");
        Assert.assertNotEquals(exchangeRatesChrome.getOutputForm(), "0");

        /*exchangeRatesChrome.transferExchange("''#@$%*!*@#.......");
        Assert.assertEquals(exchangeRatesChrome.getOutputForm(), "");*/

        /*exchangeRatesChrome.transferExchange("<script>alert(1)</script>");
        Assert.assertEquals(exchangeRatesChrome.getOutputForm(),"");*/



    }

    /**
     * Тестирование смены курса валют местами
     */
    @Test
    public void exchangeSwapTestChrome(){
        System.setProperty("webdriver.chrome.driver", "/home/maydar/git/HomeWorkTesting/HomeWorkForTesting/chromedriver");
        chromeDriver.get(ExchangeRates.URL);
        exchangeRatesChrome.transferExchange("1290");
        exchangeRatesChrome.changeInputExchange(ExchangeCodes.RUB.getExchangeCode());
        String oldValueString = exchangeRatesChrome.getOutputForm();
        String oldValueExchange =  exchangeRatesChrome.getOutputExchangeElem();

        exchangeRatesChrome.swapExchanges();
        if (exchangeRatesChrome.getOutputExchangeElem().equals(oldValueExchange)) {
            Assert.assertEquals(exchangeRatesChrome.getOutputForm(), oldValueString );
        }
        else {
            Assert.assertNotEquals(exchangeRatesChrome.getOutputForm(), oldValueString);
        }
    }


    /**
     * Тестирование смены курса различных валют
     */
    @Test
    public void changeInputExchangeTestChrome(){
        System.setProperty("webdriver.chrome.driver", "/home/maydar/git/HomeWorkTesting/HomeWorkForTesting/chromedriver");
        chromeDriver.get(ExchangeRates.URL);
        exchangeRatesChrome.transferExchange("1290");
        String oldValueString = "";

        for(ExchangeCodes o : ExchangeCodes.values()) {
            oldValueString = exchangeRatesChrome.getOutputForm();
            exchangeRatesChrome.changeInputExchange(o.getExchangeCode());
            Assert.assertNotEquals(exchangeRatesChrome.getOutputForm(), oldValueString);
        }

    }

    @Test
    public void changeOutputExchangeTestChrome(){
        System.setProperty("webdriver.chrome.driver", "/home/maydar/git/HomeWorkTesting/HomeWorkForTesting/chromedriver");
        chromeDriver.get(ExchangeRates.URL);
        exchangeRatesChrome.transferExchange("1290");
        String oldValueString = "";
        String oldExchangeString = "";
        for(ExchangeCodes o : ExchangeCodes.values()) {
            oldValueString = exchangeRatesChrome.getOutputForm();
            oldExchangeString  = exchangeRatesChrome.getOutputExchangeElem();
            exchangeRatesChrome.changeOutputExchange(o.getExchangeCode());

            if (oldExchangeString.equals(exchangeRatesChrome.getOutputExchangeElem())) {
                Assert.assertEquals(exchangeRatesChrome.getOutputForm(), oldValueString);
            }
            else {
                Assert.assertNotEquals(exchangeRatesChrome.getOutputForm(), oldValueString);

            }
        }

    }
}
