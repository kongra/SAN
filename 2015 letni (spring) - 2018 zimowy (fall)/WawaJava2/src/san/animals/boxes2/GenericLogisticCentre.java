package san.animals.boxes2;

public class GenericLogisticCentre {

  public static <T> Box<T> repackIntoNew(Box<T> box) {
    Box<T> other = new Box<>();
    T obj = box.get();
    other.put(obj);
    return other;
  }

}
