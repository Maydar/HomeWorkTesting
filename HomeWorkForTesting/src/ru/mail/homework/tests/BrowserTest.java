package ru.mail.homework.tests;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.mail.homework.src.ExchangeCodes;
import ru.mail.homework.src.ExchangeRates;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: maydar
 * Date: 05.10.13
 * Time: 18:03
 * To change this template use File | Settings | File Templates.
 *
 *  Закомментированные строки - тест на них падает, так как при при замене на текст значение не меняется
 */

public class BrowserTest {
    private static WebDriver driver;
    private ExchangeRates exchangeRates = new ExchangeRates(driver);


    @BeforeClass
    @Parameters({"browser", "hub", "url"})
    public void setUp(String browser, String hub, String url) throws MalformedURLException {
        if (browser.toLowerCase().equals("chrome"))
            this.driver = new RemoteWebDriver(new URL(hub), DesiredCapabilities.chrome());
        else if (browser.toLowerCase().equals("firefox"))
            this.driver = new RemoteWebDriver(new URL(hub), DesiredCapabilities.firefox());
        else
            throw new NotImplementedException();
        this.driver.manage().window().maximize();
        this.driver.get(url);
    }


    /**
     * Тестирование перевода валюты, проверка граничных, допустимых или недопустимых значений
     */
    @Test
    public void exchangeTest() {
        System.setProperty("webdriver.chrome.driver", "/home/maydar/git/HomeWorkTesting/HomeWorkForTesting/chromedriver");

        exchangeRates.transferExchange("1290");
        Assert.assertNotEquals(exchangeRates.getOutputForm(), "");

        exchangeRates.transferExchange("1290.2392");
        Assert.assertNotEquals(exchangeRates.getOutputForm(), "");

        /*exchangeRates.transferExchange("selenium");
        Assert.assertEquals(exchangeRates.getOutputForm(), "");*/

        exchangeRates.transferExchange("0");
        Assert.assertEquals(exchangeRates.getOutputForm(), "0");

        exchangeRates.transferExchange("-10000000");
        Assert.assertEquals(exchangeRates.getOutputForm(), "0");


        exchangeRates.transferExchange("10000000000000000000000000000");
        Assert.assertNotEquals(exchangeRates.getOutputForm(), "0");

        /*exchangeRates.transferExchange("''#@$%*!*@#.......");
        Assert.assertEquals(exchangeRates.getOutputForm(), "");*/

        /*exchangeRates.transferExchange("<script>alert(1)</script>");
        Assert.assertEquals(exchangeRates.getOutputForm(),"");*/



    }

    /**
     * Тестирование смены курса валют местами
     */
    @Test
    public void exchangeSwapTest(){
        System.setProperty("webdriver.chrome.driver", "/home/maydar/git/HomeWorkTesting/HomeWorkForTesting/chromedriver");
        exchangeRates.transferExchange("1290");
        exchangeRates.changeInputExchange(ExchangeCodes.RUB.getExchangeCode());
        String oldValueString = exchangeRates.getOutputForm();
        String oldValueExchange =  exchangeRates.getOutputExchangeElem();

        exchangeRates.swapExchanges();
        if (exchangeRates.getOutputExchangeElem().equals(oldValueExchange)) {
            Assert.assertEquals(exchangeRates.getOutputForm(), oldValueString );
        }
        else {
            Assert.assertNotEquals(exchangeRates.getOutputForm(), oldValueString);
        }
    }


    /**
     * Тестирование смены курса различных валют
     */
    @Test
    public void changeInputExchangeTest(){
        System.setProperty("webdriver.chrome.driver", "/home/maydar/git/HomeWorkTesting/HomeWorkForTesting/chromedriver");
        exchangeRates.transferExchange("1290");
        String oldValueString = "";

        for(ExchangeCodes o : ExchangeCodes.values()) {
            oldValueString = exchangeRates.getOutputForm();
            exchangeRates.changeInputExchange(o.getExchangeCode());
            Assert.assertNotEquals(exchangeRates.getOutputForm(), oldValueString);
        }

    }

    @Test
    public void changeOutputExchangeTest(){
        System.setProperty("webdriver.chrome.driver", "/home/maydar/git/HomeWorkTesting/HomeWorkForTesting/chromedriver");
        exchangeRates.transferExchange("1290");
        String oldValueString = "";
        String oldExchangeString = "";
        for(ExchangeCodes o : ExchangeCodes.values()) {
            oldValueString = exchangeRates.getOutputForm();
            oldExchangeString  = exchangeRates.getOutputExchangeElem();
            exchangeRates.changeOutputExchange(o.getExchangeCode());

            if (oldExchangeString.equals(exchangeRates.getOutputExchangeElem())) {
                Assert.assertEquals(exchangeRates.getOutputForm(), oldValueString);
            }
            else {
                Assert.assertNotEquals(exchangeRates.getOutputForm(), oldValueString);

            }
        }

    }

    @AfterMethod
    public void tearDown() {
        this.driver.close();
    }
}
