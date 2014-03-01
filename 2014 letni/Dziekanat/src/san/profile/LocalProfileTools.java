package san.profile;

import javax.ejb.Local;

@Local
public interface LocalProfileTools extends RemoteProfileTools {

  void createProfile(Profile profile);
  
}
