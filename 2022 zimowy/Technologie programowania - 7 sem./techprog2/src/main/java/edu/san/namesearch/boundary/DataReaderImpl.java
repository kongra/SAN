// © 2022 Konrad Grzanek <kongra@gmail.com>
package edu.san.namesearch.boundary;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import edu.san.namesearch.control.DataReader;
import telsos.architecture.hexagonal.annotations.Adapter;
import telsos.architecture.hexagonal.annotations.AdapterType;

@Adapter(AdapterType.SECONDARY)
@ApplicationScoped
class DataReaderImpl implements DataReader {

  @Override
  public <T> ImmutableList<T> readCSV(Path file, RowProcessor<T> rowProcessor)
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

        final MutableList<T> results = Lists.mutable.empty();

        do {
          final var row = csvReader.readNext();
          if (null == row) {
            break;
          }

          results.add(rowProcessor.apply(row));

        } while (true);

        return results.toImmutable();

      } catch (final CsvValidationException e) {
        throw new IOException(e);
      }
    }
  }

}
