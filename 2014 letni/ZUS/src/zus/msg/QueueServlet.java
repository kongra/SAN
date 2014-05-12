package zus.msg;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/msg/QueueServlet")
public class QueueServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Resource(name = "queue1")
  private BlockingQueue<String> queue1;

  public QueueServlet() {
    super();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    String msg = request.getParameter("msg");
    queue1.offer(msg);
    
  }

}
