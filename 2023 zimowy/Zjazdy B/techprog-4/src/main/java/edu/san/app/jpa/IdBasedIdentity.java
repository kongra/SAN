// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san.app.jpa;

import java.util.Objects;

@SuppressWarnings("java:S119")
public interface IdBasedIdentity<T extends IdBasedIdentity<T, ID>, ID> {

  ID getId();

  default int hash() {
    return getId().hashCode();
  }

  default boolean isEqual(Object obj) {
    if (this == obj)
      return true;

    if (getClass().isInstance(obj)) {
      @SuppressWarnings("unchecked")
      final var other = (IdBasedIdentity<T, ID>) obj;
      final var otherId = Objects.requireNonNull(other.getId());
      return getId().equals(otherId);
    }

    return false;
  }

}
