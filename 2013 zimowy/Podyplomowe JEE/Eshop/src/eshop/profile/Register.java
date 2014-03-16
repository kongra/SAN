package eshop.profile;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

@WebServlet("/profile/Register")
public class Register extends HttpServlet {

  @EJB
  private ProfileTools profileTools;

  public Register() {
    super();
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String login = request.getParameter("login");
    String password = request.getParameter("password");
    String firstName = request.getParameter("firstName");
    String lastName = request.getParameter("lastName");

    if (StringUtils.isBlank(login) || StringUtils.isBlank(password)) {
      System.out.println("Brak loginu i/lub hasła!!!");
      response.sendRedirect("./register.jsp");
      return;
    }

    Address address = new Address();
    address.setStreet("Tokarzewskiego");
    address.setCity("Łódź");
    address.setCountry("Poland");
    address.setNumber("2a");

    Gender gender = Gender.MALE;
    
    Profile profile =
        profileTools.registerB2C(login, password, firstName, lastName, address, gender);
    
    if (profile == null) {
      response.sendRedirect("./register.jsp");
      System.out.println("Login już istnieje!!!");
      return;
    }

    request.getSession().setAttribute(Profile.TAG, profile);
    response.sendRedirect("../index.jsp");
  }

  private static final long serialVersionUID = 1L;

}
