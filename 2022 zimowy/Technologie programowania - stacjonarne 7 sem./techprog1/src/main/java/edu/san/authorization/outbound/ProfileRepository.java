package edu.san.authorization.outbound;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import edu.san.authorization.ProfileId;
import edu.san.authorization.SignUpData;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
@Transactional
public class ProfileRepository
    implements PanacheRepository<ProfileEntity>, IProfileRepository {

  @Override
  public ProfileId signUp(SignUpData signUpData) {
    final var profileEntity = new ProfileEntity(/* id */ null,
        signUpData.getEmail(),
        signUpData.getFirstName(),
        signUpData.getLastName());

    persist(profileEntity);
    return new ProfileId(profileEntity.id);
  }

}
