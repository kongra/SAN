// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.profile;

public class ProfileResource {

  private final ProfileManager profileManager = new ProfileManager();

  public String signIn(String username, String password) {
    return profileManager.isCorrectUser(username, password) ? "OK" : "FAILURE";
  }

}
