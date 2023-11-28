// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san.app.inventory.company;

import java.util.UUID;

import edu.san.logic.inventory.company.Email;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;

@Entity
class EmailEntity implements Email {

  @Id
  UUID id;

  @Version
  short version;

  String value;
}
