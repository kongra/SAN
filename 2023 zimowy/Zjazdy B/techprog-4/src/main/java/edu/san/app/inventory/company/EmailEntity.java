// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san.app.inventory.company;

import java.util.Objects;
import java.util.UUID;

import edu.san.logic.inventory.company.Email;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;

@Entity
class EmailEntity implements Email {

  @Id
  private UUID id = UUID.randomUUID();

  @Version
  private short version;

  private String value;

  EmailEntity() {}

  EmailEntity(String value) {
    this.value = value;
  }

  UUID getId() {
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
    return getId().hashCode();
  }

  @Override
  public final boolean equals(Object obj) {
    if (this == obj)
      return true;

    if (obj instanceof EmailEntity emailEntity)
      return getId().equals(emailEntity.getId());

    return false;
  }

}
