package zus.msg;

import java.io.IOException;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/msg/TopicServlet")
public class TopicServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Resource
  private ConnectionFactory cf;
  
  @Resource(mappedName = "topic1")
  private Topic topic1;
  
  public TopicServlet() {
    super();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    String msg = request.getParameter("msg");
    Connection conn = null;
    try {
      conn = cf.createConnection();
      Session sess = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
      MessageProducer publisher = sess.createProducer(topic1);
      
      TextMessage m = sess.createTextMessage(msg);
      publisher.send(m);      
    }
    catch (JMSException e) {
      e.printStackTrace();
    }
    finally {
      if(null != conn) {
        try {
          conn.close();
        }
        catch (JMSException e) {
          e.printStackTrace();
        }
      }
    }
    
  }

}
