package san.coll;

public interface ISeq extends Iterable {

  final ISeq NULL = Null.INSTANCE;

  Object first();

  ISeq rest();

  ISeq cons(Object obj);

}
