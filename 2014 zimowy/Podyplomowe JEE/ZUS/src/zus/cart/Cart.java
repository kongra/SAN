package zus.cart;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;

@Stateful
public class Cart {

  private final List<OrderItem> items = new ArrayList<>();

  public Cart() {
  }

  public void addProduct(String product, int count) {
    OrderItem oi = existingItem(product);
    if (oi == null) {
      items.add(new OrderItem(product, count));
    }
    else {
      oi.incCount(count);
    }
  }

  private OrderItem existingItem(String product) {
    for (OrderItem oi : items) {
      if (oi.getProduct().equals(product)) {
        return oi;
      }
    }
    return null;
  }

  public List<OrderItem> getItems() {
    return new ArrayList<>(items);
  }

  @Remove
  public void clear() {
    items.clear();
  }
}
