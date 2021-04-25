package san.jee1.profile;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/profile/*")
public final class ProfileStatsFilter implements Filter {

  private final AtomicLong counter = new AtomicLong(0);

  public ProfileStatsFilter() {
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    long value = counter.incrementAndGet();
    System.out.println("/profile/* got hit " + value + " times.");

    chain.doFilter(request, response);
  }

  @Override
  public void destroy() {
    // TODO Auto-generated method stub
  }

  @Override
  public void init(FilterConfig fConfig) throws ServletException {
    // TODO
  }

}
