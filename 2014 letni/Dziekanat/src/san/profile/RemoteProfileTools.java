package san.profile;

import javax.ejb.Remote;

@Remote
public interface RemoteProfileTools {

  Profile findProfile(long id);
  
}
