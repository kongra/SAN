package edu.san.authentication.outbound;

import edu.san.authentication.ProfileId;
import edu.san.authentication.SignUpData;

public interface IProfileRepository {

  ProfileId signUp(SignUpData signUpData);

}
