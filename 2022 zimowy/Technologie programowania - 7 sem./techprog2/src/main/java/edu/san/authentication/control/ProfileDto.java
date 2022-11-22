package edu.san.authentication.control;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto {

  private Long id;

  private short version;

  private String email;

  private String firstName;

  private String lastName;

  private String profileKind;

}
