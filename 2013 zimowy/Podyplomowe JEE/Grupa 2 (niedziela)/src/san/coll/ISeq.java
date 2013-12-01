package san.coll;

public interface ISeq {

  final ISeq NULL = Null.INSTANCE;

  Object first();

  ISeq rest();

  ISeq cons(Object obj);

}
