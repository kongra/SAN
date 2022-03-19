package edu.san;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SomeDTO {

  private final String email;

  private final long id;

}
