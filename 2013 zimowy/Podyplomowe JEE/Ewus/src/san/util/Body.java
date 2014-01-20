package san.util;

public abstract class Body implements Runnable {

  /**
   * A Body that does nothing, somewhat like pass in Python
   */
  public static final Body pass = new Body() {
    @Override
    public void run() {
      ;
    }
  };

  @Override
  public abstract void run() throws Break, Continue;

  @SuppressWarnings("synthetic-access")
  protected final void doBreak() {
    throw new Break();
  }

  @SuppressWarnings("synthetic-access")
  protected final void doBreak(Object content) {
    throw new Break(content);
  }

  @SuppressWarnings("synthetic-access")
  protected final void doContinue() {
    throw new Continue();
  }

  @SuppressWarnings("synthetic-access")
  protected final void doContinue(Object content) {
    throw new Continue(content);
  }

  private static class FlowControl extends RuntimeException {

    private final Object content;

    FlowControl(Object content) {
      super();
      this.content = content;
    }

    FlowControl() {
      this(null);
    }

    @SuppressWarnings("unchecked")
    public <T> T content() {
      return (T) this.content;
    }

  }

  public static class Break extends FlowControl {

    private Break() {
      super();
    }

    private Break(Object content) {
      super(content);
    }

  }

  public static class Continue extends FlowControl {

    private Continue() {
      super();
    }

    private Continue(Object content) {
      super(content);
    }

  }

}
