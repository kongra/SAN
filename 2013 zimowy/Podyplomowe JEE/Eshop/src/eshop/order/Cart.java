package eshop.order;

import javax.ejb.Stateful;

@Stateful
public class Cart {
  
  private long counter = 0;
  
  public void runme() {
    counter++;
  }
  
  public void foo() {
    counter++;
  }
  
}
