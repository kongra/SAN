package san.jee1;

public final class Utils {

  private static final String PATH_ROOT = "/JEE1_niestacjonarne_lab";

  public static String url(String part) {
    return PATH_ROOT + part;
  }

  private Utils() {
  }

}
