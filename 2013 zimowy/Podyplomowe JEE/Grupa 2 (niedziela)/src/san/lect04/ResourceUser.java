package san.lect04;

import java.util.Random;

public class ResourceUser<T> implements Runnable {
  
  private static final Random rnd = new Random();

  public ResourceUser(String name, int steps, int sleep, ResourcePool<T> pool) {
    this.name = name;
    this.steps = steps;
    this.sleep = sleep;
    this.pool = pool;
  }

  @Override
  public void run() {
    for(int i = 0; i < steps; i++) {
      System.out.println(name + " żąda zasobu");
      T resource = pool.get();
      System.out.println(name + " otrzymał " + resource);
      
      try {
        Thread.sleep(rnd.nextInt(sleep));
      }
      catch (InterruptedException e) {
        e.printStackTrace();
      }
      
      pool.release(resource);
      System.out.println(name + " zwolnił " + resource);
    }
  }

  private final String name;
  
  private final int steps;

  private final int sleep;

  private final ResourcePool<T> pool;
}
