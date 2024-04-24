// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.profiles;

public interface MutableProfilesRepository extends ProfilesRepository {

  ProfileId createProfile(Username username, Password password);

}
