package san.coll.tests;

public class TestFoo {
  
  static class TestFoo1 implements IFoo {
    {
      System.out.println(this.getClass());
    }
    
    @Override
    public void foo() {
      // TODO Auto-generated method stub
      
    }    
  }

  public static void main(String[] args) {
    IFoo obj = new IFoo() {
      @Override
      public void foo() {
        ;
      }
    };
    
    IFoo obj1 = new IFoo() {
      @Override
      public void foo() {
        ;
      }
    };
    
    IFoo obj2 = new IFoo() {
      @Override
      public void foo() {
        ;
      }
    };
  }

}
