// © 2022 Konrad Grzanek <kongra@gmail.com>
package edu.san.authentication.entity;

import static org.assertj.core.api.Assertions.assertThat;

import javax.inject.Inject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.san.authentication.control.ProfileKind;
import edu.san.transactions.control.Transactor;
import io.quarkus.test.junit.QuarkusTest;
import telsos.string.Email;

@QuarkusTest
class CommentEntityTest {

  @Inject
  ProfileRepositoryImpl profileRepository;

  @Inject
  CommentRepositoryImpl commentRepository;

  @Inject
  Transactor transactor;

  Long profileId;

  Long commentId;

  @BeforeEach
  void setUp() {
    final var profile = new B2CEntity(
        "kongra@gmail.com",
        "Konrad",
        "Grzanek",
        ProfileKind.STANDARD,
        "Ul. Mokra, adr. Zachapany");

    transactor.inTransaction(() -> {
      profileRepository.persist(profile);
      assertThat(profile.id).isNotNull();
      assertThat(profile.uuid).isNotNull();
      profileId = profile.id;
    });
  }

  @AfterEach
  void tearDown() {
    transactor.inTransaction(() -> {
      commentRepository.delete(commentRepository.findById(commentId));
      profileRepository.delete(profileRepository.findById(profileId));
    });
  }

  @Test
  void testCommentCreation() {
    transactor.inTransaction(() -> {
      var email = Email.of("kongra@gmail.com").orElseThrow();
      var profile = profileRepository.findProfileEntityByEmail(email)
          .orElseThrow();

      var comment = new CommentEntity(
          "Mój pierwszy komentarz",
          "Test, test, test, ąęśćółżźń",
          profile);

      commentRepository.persist(comment);
      assertThat(comment).isNotNull();
      assertThat(comment.id).isNotNull();
      assertThat(comment.uuid).isNotNull();

      commentId = comment.id;
    });
  }

}
