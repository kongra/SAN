// © 2022 Konrad Grzanek <kongra@gmail.com>
package edu.san.namesearch.control;

import java.io.IOException;
import java.nio.file.Path;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.collections.api.list.ImmutableList;

import edu.san.Longs;
import edu.san.authentication.control.FirstName;
import telsos.architecture.hexagonal.annotations.Port;
import telsos.architecture.hexagonal.annotations.PortType;
import telsos.math.newtype.NatLong;

@Port(PortType.OUTPUT)
public interface DataReader {

  @FunctionalInterface
  interface RowProcessor<T> {

    T apply(String[] row) throws IOException;

  }

  <T> ImmutableList<T> readCSV(Path file, RowProcessor<T> rowProcessor)
      throws IOException;

  default ImmutableList<FirstNameData> readFirstNames(Path file)
      throws IOException {
    return readCSV(file, row -> {
      final var nameString1 = row[0];
      final var nameString2 = null != nameString1 ? nameString1.trim() : "";
      final var nameString3 = StringUtils
          .capitalize(nameString2.toLowerCase());

      final var firstName = FirstName.of(nameString3)
          .orElseThrow(
              () -> new IOException("Illegal FirstName " + nameString3));

      final var count = NatLong.of(
          Longs.parseLong(row[1], 10)
              .rightOrElseThrow(IOException::new))
          .orElseThrow(IOException::new);

      final var gender = "M".equals(row[2]) ? Gender.MALE : Gender.FEMALE;

      return new FirstNameData(firstName, count, gender);
    });
  }

}
