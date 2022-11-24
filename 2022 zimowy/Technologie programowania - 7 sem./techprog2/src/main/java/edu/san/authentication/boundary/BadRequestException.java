// © 2022 Konrad Grzanek <kongra@gmail.com>
package edu.san.authentication.boundary;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

public class BadRequestException extends WebApplicationException {

  public BadRequestException() {
    super(Status.BAD_REQUEST);
  }

  public BadRequestException(String message) {
    super(message, Status.BAD_REQUEST);
  }

  public BadRequestException(Throwable cause) {
    super(cause, Status.BAD_REQUEST);
  }

  public BadRequestException(String message, Throwable cause) {
    super(message, cause, Status.BAD_REQUEST);
  }

  private static final long serialVersionUID = 1L;

}
