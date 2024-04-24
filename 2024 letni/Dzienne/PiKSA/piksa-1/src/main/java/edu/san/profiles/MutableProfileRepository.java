// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.profiles;

public interface MutableProfileRepository extends ProfileRepository {

  ProfileId createProfile(Username username, Password password);

}
