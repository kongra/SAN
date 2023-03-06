package edu.san.resinapp1;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Servlet1")
public class Servlet1 extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  public void init(ServletConfig config) throws ServletException {
    System.out.println("Servlet1::init()");
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/plain; charset=UTF-8");

    try (final var writer = response.getWriter()) {
      Optional.ofNullable(request.getParameter("name"))
          .ifPresent(writer::append);

      writer
          .append(", ąęśćółżźń, ")
          .append("served at: ").append(request.getContextPath());

    } catch (final IOException e) {
      e.printStackTrace(System.err);
    }
  }

}
