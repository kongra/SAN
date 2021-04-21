package san.jee1.profile;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
public final class TrafficFilter implements Filter {

  public TrafficFilter() {
  }

  @Override
  public void init(FilterConfig fConfig) throws ServletException {
    System.out.println("TrafficFilter.init()");
  }

  @Override
  public void destroy() {
    System.out.println("TrafficFilter.destroy()");
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    System.out.println("TrafficFilter.doFilter()");

    // pass the request along the filter chain
    chain.doFilter(request, response);
  }

}
