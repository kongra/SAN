package zus.cart;

final class OrderItem implements Comparable<OrderItem> {

  private final String product;

  private int count;

  OrderItem(String product, int count) {
    this.product = product;
    this.count = count;
  }

  @Override
  public int compareTo(OrderItem o) {
    int thisCount = getCount();
    int ocount = o.getCount();

    if (thisCount < ocount) {
      return -1;
    }
    else if (thisCount > ocount) {
      return 1;
    }
    return 0;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + getCount();
    result = prime * result + ((product == null) ? 0 : product.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    OrderItem other = (OrderItem) obj;
    if (getCount() != other.getCount()) {
      return false;
    }
    if (product == null) {
      if (other.product != null) {
        return false;
      }
    }
    else if (!product.equals(other.product)) {
      return false;
    }
    return true;
  }

  public synchronized int getCount() {
    return count;
  }

  public synchronized void setCount(int count) {
    this.count = count;
  }

  public synchronized int incCount(int delta) {
    this.count += delta;
    return this.count;
  }

  public String getProduct() {
    return product;
  }

  @Override
  public String toString() {
    return "OrderItem [product=" + product + ", count=" + count + "]";
  }

}
