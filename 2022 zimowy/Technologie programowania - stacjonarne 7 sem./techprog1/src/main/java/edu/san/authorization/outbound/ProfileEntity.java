package edu.san.authorization.outbound;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "profile")
class ProfileEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;

  String email;

  String firstName;

  String lastName;

}
