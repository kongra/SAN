package wawa.fun;

public class Inc implements Unary<Integer, Integer> {

  @Override
  public Integer call(Integer x) {
    return x + 1;
  }
}
