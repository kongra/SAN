package ewus.profile;

import java.util.Calendar;

import javax.annotation.Resource;
import javax.ejb.ScheduleExpression;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.TimerService;

@Stateless
public class Newsletter {

  @Resource
  private TimerService ts;

  public void setup(int hour, int minute, int dayOfMonth) {
    ScheduleExpression expr =
        new ScheduleExpression().dayOfMonth(dayOfMonth).hour(hour)
            .minute(minute);

    ts.createCalendarTimer(expr);
  }

  public void newSetup(int hour) {
    ScheduleExpression expr = new ScheduleExpression().hour(hour);
    ts.createCalendarTimer(expr);
  }

  @Timeout
  public void send1() {
    System.out.println("Sending (1) newsletter at "
        + Calendar.getInstance().toString());
    System.out.println("Newsletter sent.");
  }

}
