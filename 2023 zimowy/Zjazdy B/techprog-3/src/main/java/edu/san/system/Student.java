// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san.system;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;

@Entity
public class Student {

  @Id
  private long id;

  @Version
  private short version;

  public Student() {
    //
  }

  public long getId() {
    return id;
  }

}
