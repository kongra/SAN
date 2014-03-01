package san.profile;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/profile/Login")
public class Login extends HttpServlet {

  @EJB
  private LocalProfileTools profileTools;

  public Login() {
    super();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    PrintWriter out = response.getWriter();

    Profile user = new Profile();
    user.setName("Adam");
    user.setPassword("elemele");

    profileTools.createProfile(user);

    Profile foundUser = profileTools.findProfile(user.id());
    out.println(foundUser.getName());
    out.close();
  }

  private static final long serialVersionUID = 1L;
}
