package edu.san.authentication.outbound;

import java.util.Optional;

import javax.validation.Valid;

import edu.san.authentication.ProfileId;
import edu.san.authentication.SignUpData;

public interface ProfileRepository {

  Optional<ProfileId> signUp(@Valid SignUpData signUpData);

  Optional<ProfileEntity> findByEmail(String email);

  boolean deleteByEmail(String email);

}
