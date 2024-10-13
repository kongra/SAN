// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.bank.money;

import java.math.BigDecimal;

public interface Money {

  BigDecimal amount();

  Currency currency();

}
