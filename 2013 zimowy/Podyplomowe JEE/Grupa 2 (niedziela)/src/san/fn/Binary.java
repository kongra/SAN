package san.fn;

public interface Binary {

  final Binary INTEGER_ADD = new Binary() {
    @Override
    public Object call(Object lhs, Object rhs) {
      Integer x = (Integer) lhs;
      Integer y = (Integer) rhs;
      return x + y;
    }
  };

  final Binary LONG_ADD = new Binary() {
    @Override
    public Object call(Object lhs, Object rhs) {
      Long x = (Long) lhs;
      Long y = (Long) rhs;
      return x + y;
    }
  };
  
  Object call(Object lhs, Object rhs);

}
