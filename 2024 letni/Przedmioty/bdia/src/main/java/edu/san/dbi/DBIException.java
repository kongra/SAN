// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.dbi;

public class DBIException extends RuntimeException {

  public DBIException() {}

  public DBIException(String message) {
    super(message);
  }

  public DBIException(Throwable cause) {
    super(cause);
  }

  public DBIException(String message, Throwable cause) {
    super(message, cause);
  }

  public DBIException(String message, Throwable cause,
      boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

}
