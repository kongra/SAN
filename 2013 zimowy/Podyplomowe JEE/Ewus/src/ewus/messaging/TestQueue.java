package ewus.messaging;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestQueue
 */
@WebServlet("/msg/TestQueue")
public class TestQueue extends HttpServlet {
  
  private static final long serialVersionUID = 1L;
  
  @Resource(name="kolejeczka")
  private BlockingQueue queue;

  public TestQueue() {
    super();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String msg = request.getParameter("msg");
    queue.offer(msg);
    
  }

}
