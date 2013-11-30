package san.coll.fn;

public interface Binary<R, S, T> {

  R call(S lhs, T rhs);
  
}
