package san.jee1.profile;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/resources/*")
public final class RestrictedResources implements Filter {

  public RestrictedResources() {
    // TODO Auto-generated constructor stub
  }

  @Override
  public void destroy() {
    // TODO Auto-generated method stub
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;

    var email = httpRequest.getSession().getAttribute("email");
    System.out.println("email is " + email);

    if (email == null) {
      httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
    }

    chain.doFilter(request, response);
  }

  @Override
  public void init(FilterConfig fConfig) throws ServletException {
    // TODO Auto-generated method stub
  }

}
