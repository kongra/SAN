package edu.san.indexer.entity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import edu.san.indexer.control.DataReader;
import edu.san.indexer.control.FirstName;
import edu.san.indexer.control.Gender;
import telsos.architecture.hexagonal.annotations.Adapter;
import telsos.architecture.hexagonal.annotations.AdapterType;
import telsos.math.newtype.NatLong;
import telsos.string.NonBlank;

@Adapter(AdapterType.SECONDARY)
class DataReaderImpl implements DataReader {

  @Override
  public ImmutableList<FirstName> readFirstNames(Path file) throws IOException {
    try (var reader = Files.newBufferedReader(file)) {
      final var parser = new CSVParserBuilder()
          .withSeparator(',')
          .withIgnoreQuotations(true)
          .build();

      try (var csvReader = new CSVReaderBuilder(reader)
          .withSkipLines(1) // Skipping the file heading
          .withCSVParser(parser)
          .build()) {

        final MutableList<FirstName> results = Lists.mutable.empty();

        do {
          final var line = csvReader.readNext();
          if (null == line) {
            break;
          }

          final var firstName = NonBlank.of(line[0])
              .orElseThrow(IOException::new);
          final var count = NatLong.of(Long.parseLong(line[1]))
              .orElseThrow(IOException::new);
          final var gender = "M".equals(line[2]) ? Gender.MALE : Gender.FEMALE;

          results.add(new FirstName(firstName, count, gender));

        } while (true);

        return results.toImmutable();

      } catch (final CsvValidationException e) {
        throw new IOException(e);
      }
    }
  }

}
