package edu.san.authentication.outbound;

import edu.san.authentication.ProfileId;
import edu.san.authentication.SignUpData;

public interface ProfileRepositoryInterface {

  ProfileId signUp(SignUpData signUpData);

}
