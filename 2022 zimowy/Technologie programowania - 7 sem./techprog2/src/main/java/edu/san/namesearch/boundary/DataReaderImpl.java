// © 2022 Konrad Grzanek <kongra@gmail.com>
package edu.san.namesearch.boundary;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.enterprise.context.ApplicationScoped;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import edu.san.authentication.control.FirstName;
import edu.san.namesearch.control.DataReader;
import edu.san.namesearch.control.FirstNameData;
import edu.san.namesearch.control.Gender;
import telsos.architecture.hexagonal.annotations.Adapter;
import telsos.architecture.hexagonal.annotations.AdapterType;
import telsos.math.newtype.NatLong;

@Adapter(AdapterType.SECONDARY)
@ApplicationScoped
class DataReaderImpl implements DataReader {

  @Override
  public ImmutableList<FirstNameData> readFirstNames(Path file)
      throws IOException {
    try (var reader = Files.newBufferedReader(file)) {
      final var parser = new CSVParserBuilder()
          .withSeparator(',')
          .withIgnoreQuotations(true)
          .build();

      try (var csvReader = new CSVReaderBuilder(reader)
          .withSkipLines(1) // Skipping the file heading
          .withCSVParser(parser)
          .build()) {

        final MutableList<FirstNameData> results = Lists.mutable.empty();

        do {
          final var line = csvReader.readNext();
          if (null == line) {
            break;
          }

          final var nameString1 = line[0];
          final var nameString2 = null != nameString1 ? nameString1.trim() : "";
          final var nameString3 = StringUtils
              .capitalize(nameString2.toLowerCase());

          final var firstName = FirstName.of(nameString3)
              .orElseThrow(
                  () -> new IOException("Illegal FirstName " + nameString3));
          
          final var count = NatLong.of(Long.parseLong(line[1]))
              .orElseThrow(IOException::new);
          
          final var gender = "M".equals(line[2]) ? Gender.MALE : Gender.FEMALE;

          results.add(new FirstNameData(firstName, count, gender));

        } while (true);

        return results.toImmutable();

      } catch (final CsvValidationException e) {
        throw new IOException(e);
      }
    }
  }

}
