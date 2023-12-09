// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san.app.inventory.company;

import java.util.Objects;
import java.util.UUID;

import edu.san.app.jpa.IdBasedIdentity;
import edu.san.logic.inventory.company.Email;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;

@Entity
class EmailEntity implements Email, IdBasedIdentity<EmailEntity, UUID> {

  @Id
  private UUID id = UUID.randomUUID();

  @Version
  private short version;

  private String value;

  EmailEntity() {}

  EmailEntity(String value) {
    this.value = value;
  }

  @Override
  public String asString() {
    return getValue();
  }

  @Override
  public UUID getId() {
    return Objects.requireNonNull(id);
  }

  void setId(UUID id) {
    Objects.requireNonNull(id);
    this.id = id;
  }

  short getVersion() {
    return version;
  }

  void setVersion(short version) {
    this.version = version;
  }

  String getValue() {
    return value;
  }

  void setValue(String value) {
    this.value = value;
  }

  @Override
  public final int hashCode() {
    return hash();
  }

  @Override
  public final boolean equals(Object obj) {
    return isEqual(obj);
  }

}
