package zus.cart;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CartController")
public class CartController extends HttpServlet {

  @EJB
  private Cart cart;

  public CartController() {
    super();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String action = request.getParameter("a");
    String product = request.getParameter("p");

    if ("add".equals(action)) {
      addProduct(product);
      System.out.print("Completed: add");
    }
    else if ("log".equals(action)) {
      logCart();
      System.out.print("Completed: log");
    }
    else if ("clear".equals(action)) {
      cart.clear();
      System.out.print("Completed: clear");
    }
    else {
      System.out.println("Unrecognized action " + action);
    }
    
  }

  private void logCart() {
    for (OrderItem oi : cart.getItems()) {
      System.out.println(oi);
    }
  }

  private void addProduct(String product) {
    cart.addProduct(product, 1);
  }

}
