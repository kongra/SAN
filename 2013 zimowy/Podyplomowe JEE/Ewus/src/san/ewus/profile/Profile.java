package san.ewus.profile;

import javax.sql.DataSource;

public final class Profile {
  
  public static final String LOGGEDIN = "Profile.LOGGED-IN";

  public static boolean isValid(DataSource dataSource, String login, String pass) {
    System.out.println(dataSource);
    
    return false;
  }
  
  private Profile() {
    ;
  }
}
