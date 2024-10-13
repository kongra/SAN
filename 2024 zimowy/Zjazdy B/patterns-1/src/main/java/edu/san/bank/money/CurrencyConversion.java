// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.bank.money;

public interface CurrencyConversion {

  Money convert(Money money, Currency targetCurrency);

}
