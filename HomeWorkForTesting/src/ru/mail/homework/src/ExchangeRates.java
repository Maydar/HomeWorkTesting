package ru.mail.homework.src;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * @author maydar
 * Класc, отвечающий за подмес
 *
 */
public class ExchangeRates {
	private WebDriver driver;
	public static final String URL = "http://go.mail.ru/search?q=%D0%BA%D1%83%D1%80%D1%81+%D0%B2%D0%B0%D0%BB%D1%8E%D1%82";
	
	/**
	 * Найти элемент ввода
	 */
	@FindBy(id = "ival")
	private WebElement inputForm;	
	
	
	/**
	 * Найти элемент вывода
	 */
	@FindBy(id = "oval")
	private WebElement outputForm;
	
	/**
	 * Найти элемент переключения
	 */
	@FindBy(id = "change_conv")
	private WebElement switchElement;
	
	/**
	 * Найти элемент переключения валют
	 */
	@FindBy(id = "icode")
	private WebElement inputExchangeElement;
	@FindBy(id = "ocode")
	private WebElement outputExchangeElement;
	
	
	/**
	 * Найти drop down меню
	 */
	@FindBy(id = "select_first")
	private WebElement selectInputExchangeElem;
	
	@FindBy(id = "select_second")
	private WebElement selectOutputExchangeElem;
	
	
	/**
	 * Конструктор
	 * @param driver
	 */
	public ExchangeRates(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	/**
	 * Трансфер валюты
	 */
	public String transferExchange(String value) {
        inputForm.clear();
		inputForm.sendKeys(value);
		return getOutputForm();
	}
	
	/**
	 * переключение курсов валюты местами
	 */
	public void swapExchanges() {
		switchElement.click();
	}
	
	/**
	 * переключение входной валюты 
	 */
	public void changeInputExchange(final String value){
		inputExchangeElement.click();	
		List<WebElement> options = selectInputExchangeElem.findElements(By.tagName("span"));
		
	    for (WebElement option : options) {
	        if (value.equals(option.getAttribute("data-code"))) {
	            option.click();
	            break;
	        }
	    }
	    
	}
	
	
	/**
	 * переключение выходной валюты
	 */
	public void changeOutputExchange(final String value){
		outputExchangeElement.click();
		List<WebElement> options = selectOutputExchangeElem.findElements(By.tagName("span"));
		
	    for (WebElement option : options) {
            System.out.println(option.getAttribute("data-code"));
	        if (value.equals(option.getAttribute("data-code"))) {
	            option.click();
	            break;
	        }
	    }
	}
	
	
	/**
	 * геттеры для полей 
	 * @return string
	 */
	public String getOutputForm(){
		return outputForm.getText();
	}
	
	public String getInputForm(){
		return inputForm.getText();
	}
	
	public String getInputExchangeElem() {

		return inputExchangeElement.getText();
	}
	
	public String getOutputExchangeElem() {
		return outputExchangeElement.getText();
	}
}
