package edu.san.authentication.infrastructure;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "profile")
@NoArgsConstructor
@AllArgsConstructor
class ProfileEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;

  @Email
  @NotNull
  @Column(unique = true)
  String email;

  @NotNull
  String firstName;

  @NotNull
  String lastName;

}
