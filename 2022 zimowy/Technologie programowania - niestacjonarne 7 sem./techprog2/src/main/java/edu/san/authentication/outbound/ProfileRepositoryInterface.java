package edu.san.authentication.outbound;

import java.util.Optional;

import edu.san.authentication.ProfileId;
import edu.san.authentication.SignUpData;

public interface ProfileRepositoryInterface {

  Optional<ProfileId> signUp(SignUpData signUpData);

}
