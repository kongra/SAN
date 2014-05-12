package zus.msg;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destination", propertyValue = "topic1"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class TopicBean implements MessageListener {

  @Override
  public void onMessage(Message m) {
    String msg;
    try {
      msg = ((TextMessage) m).getText();
      System.out.println("Odebrałem z topic1: " + msg);
    }
    catch (JMSException e) {
      e.printStackTrace();
    }
  }

}
