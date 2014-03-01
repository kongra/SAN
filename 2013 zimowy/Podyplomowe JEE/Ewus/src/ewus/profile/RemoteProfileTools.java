package ewus.profile;

import java.util.concurrent.Future;

import javax.ejb.Remote;

@Remote
public interface RemoteProfileTools {

  Profile findProfile(String login);

  Profile findProfile(String login, String password);

  Profile register(String login, String password, String firstName,
      String lastName);
  
  Future<Profile> remindPassword(String login);

}
