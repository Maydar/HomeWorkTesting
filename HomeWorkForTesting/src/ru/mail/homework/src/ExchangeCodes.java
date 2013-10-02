package ru.mail.homework.src;

/**
 * @author maydar
 * Enum, отвечающий за коды валют 
 */
public enum ExchangeCodes {
	RUB("RUB"), USD("USD"), EUR("EUR"),
	KGS("KGS"), JPY("JPY"), AZN("AZN"),
	AMD("AMD"), BYR("BYR"), BGN("BGN"),
	BRL("BRL"), HUF("HUF"), KRW("KRW"),
	DKK("DKK"), INR("INR"), KZT("KZT"),
	CAD("CAD"), CNY("CNY"), LVL("LVL"),
	LTL("LTL"), MDL("MDL"), RON("RON"),
	TMT("TMT"), NOK("NOK"), PLN("PLN"),
	XDR("XDR"), SGD("SGD"), TJS("TJS"),
	TRY("TRY"), UZS("UZS"), UAH("UAH"),
	GBP("GBP"), CZK("CZK"), SEK("SEK"),
	CHF("CHF"), ZAR("ZAR");
	
	
	private String exchangeString;
	
	private ExchangeCodes(String code) {
		exchangeString = code;
	}
	
	public String getExchangeCode(){
		return exchangeString;
	}
}
