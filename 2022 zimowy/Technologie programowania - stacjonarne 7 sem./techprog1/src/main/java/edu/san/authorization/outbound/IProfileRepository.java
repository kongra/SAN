package edu.san.authorization.outbound;

import edu.san.authorization.ProfileId;
import edu.san.authorization.SignUpData;

public interface IProfileRepository {

  ProfileId signUp(SignUpData signUpData);

}
