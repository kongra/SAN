package san.jipp.genericgame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Game {

  public static void main(String... args) {
    List<Integer> pointsGathered = new ArrayList<>();

    pointsGathered.add(12);
    pointsGathered.add(5);
    pointsGathered.add(8);

    // ...

    // pointsGathered.add("7");
    pointsGathered.add(7);

    int sum = sumPoints(pointsGathered);
    System.out.println("Zawodnik zgromadził " + sum + " punktów");
  }

  private static int sumPoints(List<Integer> pointsGathered) {
    int result = 0;
    for (var points : pointsGathered) {
      result += points;
    }
    return result;

//    for (var it = pointsGathered.iterator(); it.hasNext(); ) {
//      var points = it.next();
//      result += points;
//    }
//    return result;

//    for (int i = 0, n = pointsGathered.size(); i < n; i++) {
//      var points = pointsGathered.get(i);
//      result += points;
//    }

    // Iterator<Integer> iter = pointsGathered.iterator();
//    while(iter.hasNext()) {
//      Integer points = iter.next();
//      result += points.intValue();
//    }
  }

}
