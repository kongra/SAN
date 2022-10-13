package edu.san.authorization;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SignUpData {

  @Email
  @NotNull
  String email;

  @NotNull
  String firstName;

  @NotNull
  String lastName;

}
