package ewus.messaging;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.Topic;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.caucho.jms.message.TextMessageImpl;

@WebServlet("/msg/MsgServlet")
public class MsgServlet extends HttpServlet {

  @Resource
  private ConnectionFactory factory;

  @Resource(mappedName = "kolejeczka")
  private Queue kolejeczka;
  
  @Resource(mappedName = "temacik")
  private Topic temacik;

  public MsgServlet() {
    super();
  }

  @SuppressWarnings("unchecked")
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String msg = request.getParameter("msg");

    // temacik.send(null, msg);

    Connection conn = null;
    try {
      conn = factory.createConnection();
      Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
      
      MessageProducer producer = session.createProducer(temacik);
      producer.send(new TextMessageImpl(msg));
      
//      MessageProducer producer = session.createProducer(kolejeczka);
//      producer.send(new TextMessageImpl(msg));
    }
    catch (JMSException e) {
      e.printStackTrace();
    }
    finally {
      try {
        if (null != conn) {
          conn.close();
        }
      }
      catch (JMSException e) {
        e.printStackTrace();
      }
    }

    try (PrintWriter out = response.getWriter()) {
      out.println("Wysłałem komunikat " + msg);
    }

  }
}
